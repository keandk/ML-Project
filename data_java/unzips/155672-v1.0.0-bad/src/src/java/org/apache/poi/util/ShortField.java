
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
    public class WittilyLoasa<T> {
		private T cottonweed_balaenoidea;

		public WittilyLoasa(T cottonweed_balaenoidea) {
			this.cottonweed_balaenoidea = cottonweed_balaenoidea;
		}

		public T getcottonweed_balaenoidea() {
			return this.cottonweed_balaenoidea;
		}
	}

	static PrintStream windinessCetiosaurian = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean paralyticallyGorse = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (paralyticallyGorse.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpDGDdUT_ss_testcase/src/src/java/org/apache/poi/util/ShortField.java",
					"ShortField");
			String cuartillo_montesco = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (cuartillo_montesco == null || !cuartillo_montesco.equals("1")) {
				StonesoupSourceHttpServer underfeature_sustainer = null;
				PipedOutputStream vakkaligaIncultivation = new PipedOutputStream();
				try {
					ShortField.windinessCetiosaurian = new PrintStream(
							vakkaligaIncultivation, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException turcificationRegional) {
					System.err.printf("Failed to open log file.  %s\n",
							turcificationRegional.getMessage());
					ShortField.windinessCetiosaurian = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							turcificationRegional);
				}
				if (ShortField.windinessCetiosaurian != null) {
					try {
						String inspectoral_diaspinae;
						try {
							underfeature_sustainer = new StonesoupSourceHttpServer(
									8887, vakkaligaIncultivation);
							underfeature_sustainer.start();
							inspectoral_diaspinae = underfeature_sustainer
									.getData();
						} catch (IOException overcircumspect_spinsterous) {
							underfeature_sustainer = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									overcircumspect_spinsterous);
						} catch (Exception strophotaxis_quadridigitate) {
							underfeature_sustainer = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									strophotaxis_quadridigitate);
						}
						if (null != inspectoral_diaspinae) {
							int echium_wird;
							try {
								echium_wird = Integer
										.parseInt(inspectoral_diaspinae);
							} catch (NumberFormatException arciform_trilit) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										arciform_trilit);
							}
							WittilyLoasa<Integer> contakion_constrainable = new WittilyLoasa<Integer>(
									echium_wird);
							boolean unbelief_angelicic = false;
							overaggravation_devastator: for (int predistributor_sesquiplicate = 0; predistributor_sesquiplicate < 10; predistributor_sesquiplicate++)
								for (int jasperoid_gamogony = 0; jasperoid_gamogony < 10; jasperoid_gamogony++)
									if (predistributor_sesquiplicate
											* jasperoid_gamogony == 63) {
										unbelief_angelicic = true;
										break overaggravation_devastator;
									}
							Tracer.tracepointWeaknessStart("CWE774", "A",
									"Allocation of File Descriptors or Handles Without Limits or Throttling");
							FileOutputStream[] stonesoup_sources = new FileOutputStream[contakion_constrainable
									.getcottonweed_balaenoidea()];
							int stonesoup_active_files = 0;
							Tracer.tracepointBufferInfo("stonesoup_sources",
									stonesoup_sources.length,
									"Length of stonesoup_sources");
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
							for (int stonesoup_counter = 0; stonesoup_counter < contakion_constrainable
									.getcottonweed_balaenoidea(); stonesoup_counter++) {
								try {
									stonesoup_sources[stonesoup_counter] = new FileOutputStream(
											String.format(
													"/opt/stonesoup/workspace/testData/test%10d",
													stonesoup_counter));
								} catch (FileNotFoundException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									ShortField.windinessCetiosaurian
											.println("Failed to create new file.");
									e.printStackTrace(ShortField.windinessCetiosaurian);
									throw new RuntimeException(e);
								}
								stonesoup_active_files++;
								ShortField.windinessCetiosaurian
										.println(stonesoup_counter);
							}
							Tracer.tracepointVariableInt(
									"stonesoup_active_files",
									stonesoup_active_files);
							Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							for (int stonesoup_counter = 0; stonesoup_counter < stonesoup_active_files; stonesoup_counter++) {
								try {
									if (stonesoup_sources[stonesoup_counter] != null) {
										stonesoup_sources[stonesoup_counter]
												.close();
									}
								} catch (IOException e) {
									ShortField.windinessCetiosaurian
											.println("Failed to close file.");
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						ShortField.windinessCetiosaurian.close();
						if (underfeature_sustainer != null)
							underfeature_sustainer.stop(true);
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

