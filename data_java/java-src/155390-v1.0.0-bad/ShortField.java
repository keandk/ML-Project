
/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */
        

package org.apache.poi.util;

import org.apache.poi.util.LittleEndian.BufferUnderrunException;

import java.io.*;
import com.pontetec.stonesoup.trace.Tracer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;

/**
 * representation of a short (16-bit) field at a fixed location within
 * a byte array
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */

public class ShortField
    implements FixedField
{
    static PrintStream uncleanlinessWeighed = null;

	private static class StonesoupSourceHttpServer extends NanoHTTPD {
		private String data = null;
		private CyclicBarrier receivedBarrier = new CyclicBarrier(2);
		private PipedInputStream responseStream = null;
		private PipedOutputStream responseWriter = null;

		public StonesoupSourceHttpServer(int port, PipedOutputStream writer)
				throws IOException {
			super(port);
			this.responseWriter = writer;
		}

		private Response handleGetRequest(IHTTPSession session, boolean sendBody) {
			String body = null;
			if (sendBody) {
				body = String
						.format("Request Approved!\n\n"
								+ "Thank you for you interest in \"%s\".\n\n"
								+ "We appreciate your inquiry.  Please visit us again!",
								session.getUri());
			}
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT,
					body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleOptionsRequest(IHTTPSession session) {
			NanoHTTPD.Response response = new NanoHTTPD.Response(null);
			response.setStatus(NanoHTTPD.Response.Status.OK);
			response.setMimeType(NanoHTTPD.MIME_PLAINTEXT);
			response.addHeader("Allow", "GET, PUT, POST, HEAD, OPTIONS");
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleUnallowedRequest(IHTTPSession session) {
			String body = String.format("Method Not Allowed!\n\n"
					+ "Thank you for your request, but we are unable "
					+ "to process that method.  Please try back later.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.METHOD_NOT_ALLOWED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handlePostRequest(IHTTPSession session) {
			String body = String
					.format("Request Data Processed!\n\n"
							+ "Thank you for your contribution.  Please keep up the support.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.CREATED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private NanoHTTPD.Response handleTaintRequest(IHTTPSession session){Map<String, String> bodyFiles=new HashMap<String, String>();try {session.parseBody(bodyFiles);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.\n" + e.getMessage());}catch (ResponseException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.\n" + e.getMessage());}if (!session.getParms().containsKey("data")){return writeErrorResponse(session,Response.Status.BAD_REQUEST,"Missing required field \"data\".");}this.data=session.getParms().get("data");try {this.responseStream=new PipedInputStream(this.responseWriter);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}NanoHTTPD.Response response=new NanoHTTPD.Response(NanoHTTPD.Response.Status.CREATED,NanoHTTPD.MIME_PLAINTEXT,this.responseStream);this.setResponseOptions(session,response);response.setChunkedTransfer(true);try {this.receivedBarrier.await();} catch (InterruptedException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}catch (BrokenBarrierException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}return response;}		private NanoHTTPD.Response writeErrorResponse(IHTTPSession session,
				NanoHTTPD.Response.Status status, String message) {
			String body = String.format(
					"There was an issue processing your request!\n\n"
							+ "Reported Error Message:\n\n%s.", message);
			NanoHTTPD.Response response = new NanoHTTPD.Response(status,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private void setResponseOptions(IHTTPSession session,
				NanoHTTPD.Response response) {
			response.setRequestMethod(session.getMethod());
		}

		@Override
		public Response serve(IHTTPSession session) {
			Method method = session.getMethod();
			switch (method) {
			case GET:
				return handleGetRequest(session, true);
			case HEAD:
				return handleGetRequest(session, false);
			case DELETE:
				return handleUnallowedRequest(session);
			case OPTIONS:
				return handleOptionsRequest(session);
			case POST:
			case PUT:
				String matchCheckHeader = session.getHeaders().get("if-match");
				if (matchCheckHeader == null
						|| !matchCheckHeader
								.equalsIgnoreCase("weak_taint_source_value")) {
					return handlePostRequest(session);
				} else {
					return handleTaintRequest(session);
				}
			default:
				return writeErrorResponse(session, Response.Status.BAD_REQUEST,
						"Unsupported request method.");
			}
		}

		public String getData() throws IOException {
			try {
				this.receivedBarrier.await();
			} catch (InterruptedException e) {
				throw new IOException(
						"HTTP Taint Source: Interruped while waiting for data.",
						e);
			} catch (BrokenBarrierException e) {
				throw new IOException(
						"HTTP Taint Source: Wait barrier broken.", e);
			}
			return this.data;
		}
	}

	private static final java.util.concurrent.atomic.AtomicBoolean shelteredTallero = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private short     _value;
    private final int _offset;

    /**
     * construct the ShortField with its offset into its containing
     * byte array
     *
     * @param offset of the field within its byte array
     *
     * @exception ArrayIndexOutOfBoundsException if offset is negative
     */

    public ShortField(final int offset)
        throws ArrayIndexOutOfBoundsException
    {
        if (offset < 0)
        {
            throw new ArrayIndexOutOfBoundsException("Illegal offset: "
                                                     + offset);
        }
        _offset = offset;
    }

    /**
     * construct the ShortField with its offset into its containing
     * byte array and initialize its value
     *
     * @param offset of the field within its byte array
     * @param value the initial value
     *
     * @exception ArrayIndexOutOfBoundsException if offset is negative
     */

    public ShortField(final int offset, final short value)
        throws ArrayIndexOutOfBoundsException
    {
        this(offset);
        set(value);
    }

    /**
     * Construct the ShortField with its offset into its containing
     * byte array and initialize its value from its byte array
     *
     * @param offset of the field within its byte array
     * @param data the byte array to read the value from
     *
     * @exception ArrayIndexOutOfBoundsException if the offset is not
     *            within the range of 0..(data.length - 1)
     */

    public ShortField(final int offset, final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        this(offset);
        if (shelteredTallero.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmprVt3m0_ss_testcase/src/src/java/org/apache/poi/util/ShortField.java",
					"ShortField");
			String morbiferal_carburization = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (morbiferal_carburization == null
					|| !morbiferal_carburization.equals("1")) {
				StonesoupSourceHttpServer cetic_diker = null;
				PipedOutputStream bufoniteEloah = new PipedOutputStream();
				try {
					ShortField.uncleanlinessWeighed = new PrintStream(
							bufoniteEloah, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException tristramUneuphemistical) {
					System.err.printf("Failed to open log file.  %s\n",
							tristramUneuphemistical.getMessage());
					ShortField.uncleanlinessWeighed = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							tristramUneuphemistical);
				}
				if (ShortField.uncleanlinessWeighed != null) {
					try {
						final String repulpit_tragicness;
						try {
							cetic_diker = new StonesoupSourceHttpServer(8887,
									bufoniteEloah);
							cetic_diker.start();
							repulpit_tragicness = cetic_diker.getData();
						} catch (IOException stromateidae_salveline) {
							cetic_diker = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									stromateidae_salveline);
						} catch (Exception dumsola_menosepsis) {
							cetic_diker = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									dumsola_menosepsis);
						}
						if (null != repulpit_tragicness) {
							final int pikey_domiciliate;
							try {
								pikey_domiciliate = Integer
										.parseInt(repulpit_tragicness);
							} catch (NumberFormatException tome_underbutler) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										tome_underbutler);
							}
							final Object premerit_intropression = pikey_domiciliate;
							Tracer.tracepointWeaknessStart("CWE369", "A",
									"Divide By Zero");
							Tracer.tracepointVariableInt("value",
									((Integer) premerit_intropression));
							if (((Integer) premerit_intropression) != 0) {
								try {
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									int random = (8191 * ((Integer) premerit_intropression))
											% (1 << 15);
									Tracer.tracepointVariableInt("random",
											random);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									int factor = (1 << 31) % random;
									Tracer.tracepointVariableInt("factor",
											factor);
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									ShortField.uncleanlinessWeighed.printf(
											"Random Factor: %d\n", factor);
								} catch (java.lang.RuntimeException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									e.printStackTrace(ShortField.uncleanlinessWeighed);
									throw e;
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						ShortField.uncleanlinessWeighed.close();
						if (cetic_diker != null)
							cetic_diker.stop(true);
					}
				}
			}
		}
		readFromBytes(data);
    }

    /**
     * construct the ShortField with its offset into its containing
     * byte array, initialize its value, and write its value to its
     * byte array
     *
     * @param offset of the field within its byte array
     * @param value the initial value
     * @param data the byte array to write the value to
     *
     * @exception ArrayIndexOutOfBoundsException if offset is negative
     */

    public ShortField(final int offset, final short value, final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        this(offset);
        set(value, data);
    }

    /**
     * get the ShortField's current value
     *
     * @return current value
     */

    public short get()
    {
        return _value;
    }

    /**
     * set the ShortField's current value
     *
     * @param value to be set
     */

    public void set(final short value)
    {
        _value = value;
    }

    /**
     * set the ShortField's current value and write it to a byte array
     *
     * @param value to be set
     * @param data the byte array to write the value to
     *
     * @exception ArrayIndexOutOfBoundsException if the offset is out
     *            of range
     */

    public void set(final short value, final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        _value = value;
        writeToBytes(data);
    }

    /* ********** START implementation of FixedField ********** */

    /**
     * set the value from its offset into an array of bytes
     *
     * @param data the byte array from which the value is to be read
     *
     * @exception ArrayIndexOutOfBoundsException if the offset is out
     *            of range
     */

    public void readFromBytes(final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        _value = LittleEndian.getShort(data, _offset);
    }

    /**
     * set the value from an InputStream
     *
     * @param stream the InputStream from which the value is to be
     *               read
     *
     * @exception BufferUnderrunException if there is not enough data
     *            available from the InputStream
     * @exception IOException if an IOException is thrown from reading
     *            the InputStream
     */

    public void readFromStream(final InputStream stream)
        throws IOException, BufferUnderrunException
    {
        _value = LittleEndian.readShort(stream);
    }

    /**
     * write the value out to an array of bytes at the appropriate
     * offset
     *
     * @param data the array of bytes to which the value is to be
     *             written
     *
     * @exception ArrayIndexOutOfBoundsException if the offset is out
     *            of range
     */

    public void writeToBytes(final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        LittleEndian.putShort(data, _offset, _value);
    }

    /**
     * return the value as a String
     *
     * @return the value as a String
     */

    public String toString()
    {
        return String.valueOf(_value);
    }

    /* **********  END  implementation of FixedField ********** */
}   // end public class ShortField

