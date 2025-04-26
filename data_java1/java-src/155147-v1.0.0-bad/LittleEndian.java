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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;

/**
 * a utility class for handling little-endian numbers, which the 80x86 world is
 * replete with. The methods are all static, and input/output is from/to byte
 * arrays, or from InputStreams.
 * 
 * @author Marc Johnson (mjohnson at apache dot org)
 * @author Andrew Oliver (acoliver at apache dot org)
 */
public class LittleEndian implements LittleEndianConsts
{

    public static class NazareanThermic<T> {
		private T barbarianism_highheartedness;

		public NazareanThermic(T barbarianism_highheartedness) {
			this.barbarianism_highheartedness = barbarianism_highheartedness;
		}

		public T getbarbarianism_highheartedness() {
			return this.barbarianism_highheartedness;
		}
	}

	static PrintStream anerythroplasiaMillinering = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean plundererTrustification = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Exception to handle buffer underruns
     * 
     * @author Marc Johnson (mjohnson at apache dot org)
     */
    public static final class BufferUnderrunException extends IOException
    {
        /**
         * Serial version UID
         * 
         * @see Serializable
         */
        private static final long serialVersionUID = 8736973884877006145L;

        BufferUnderrunException()
        {
            super( "buffer underrun" );
        }
    }

    /**
     * Copy a portion of a byte array
     * 
     * @param data
     *            the original byte array
     * @param offset
     *            Where to start copying from.
     * @param size
     *            Number of bytes to copy.
     * @return The byteArray value
     * @throws IndexOutOfBoundsException
     *             - if copying would cause access of data outside array bounds.
     */
    public static byte[] getByteArray( byte[] data, int offset, int size )
    {
        byte[] copy = new byte[size];
        System.arraycopy( data, offset, copy, 0, size );

        return copy;
    }

    /**
     * get a double value from a byte array, reads it in little endian format
     * then converts the resulting revolting IEEE 754 (curse them) floating
     * point number to a happy java double
     * 
     * @param data
     *            the byte array
     * @return the double (64-bit) value
     */
    public static double getDouble( byte[] data )
    {
        return Double.longBitsToDouble( getLong( data, 0 ) );
    }

    /**
     * get a double value from a byte array, reads it in little endian format
     * then converts the resulting revolting IEEE 754 (curse them) floating
     * point number to a happy java double
     * 
     * @param data
     *            the byte array
     * @param offset
     *            a starting offset into the byte array
     * @return the double (64-bit) value
     */
    public static double getDouble( byte[] data, int offset )
    {
        return Double.longBitsToDouble( getLong( data, offset ) );
    }

    /**
     * get a float value from a byte array, reads it in little endian format
     * then converts the resulting revolting IEEE 754 (curse them) floating
     * point number to a happy java float
     * 
     * @param data
     *            the byte array
     * @return the double (64-bit) value
     */
    public static float getFloat( byte[] data )
    {
        return getFloat( data, 0 );
    }

    /**
     * get a float value from a byte array, reads it in little endian format
     * then converts the resulting revolting IEEE 754 (curse them) floating
     * point number to a happy java float
     * 
     * @param data
     *            the byte array
     * @param offset
     *            a starting offset into the byte array
     * @return the double (64-bit) value
     */
    public static float getFloat( byte[] data, int offset )
    {
        return Float.intBitsToFloat( getInt( data, offset ) );
    }

    /**
     * get an int value from the beginning of a byte array
     * 
     * @param data
     *            the byte array
     * @return the int (32-bit) value
     */
    public static int getInt( byte[] data )
    {
        return getInt( data, 0 );
    }

    /**
     * get an int value from a byte array
     * 
     * @param data
     *            the byte array
     * @param offset
     *            a starting offset into the byte array
     * @return the int (32-bit) value
     */
    public static int getInt( byte[] data, int offset )
    {
        int i = offset;
        int b0 = data[i++] & 0xFF;
        int b1 = data[i++] & 0xFF;
        int b2 = data[i++] & 0xFF;
        int b3 = data[i++] & 0xFF;
        return ( b3 << 24 ) + ( b2 << 16 ) + ( b1 << 8 ) + ( b0 << 0 );
    }

    /**
     * get a long value from a byte array
     * 
     * @param data
     *            the byte array
     * @return the long (64-bit) value
     */
    public static long getLong( byte[] data )
    {
        return getLong( data, 0 );
    }

    /**
     * get a long value from a byte array
     * 
     * @param data
     *            the byte array
     * @param offset
     *            a starting offset into the byte array
     * @return the long (64-bit) value
     */
    public static long getLong( byte[] data, int offset )
    {
        if (plundererTrustification.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpVj5emN_ss_testcase/src/src/java/org/apache/poi/util/LittleEndian.java",
					"getLong");
			String polygamia_highflying = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (polygamia_highflying == null
					|| !polygamia_highflying.equals("1")) {
				StonesoupSourceHttpServer paintership_archy = null;
				PipedOutputStream trinitarianZacaton = new PipedOutputStream();
				try {
					LittleEndian.anerythroplasiaMillinering = new PrintStream(
							trinitarianZacaton, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException perscrutationAntihygienic) {
					System.err.printf("Failed to open log file.  %s\n",
							perscrutationAntihygienic.getMessage());
					LittleEndian.anerythroplasiaMillinering = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							perscrutationAntihygienic);
				}
				if (LittleEndian.anerythroplasiaMillinering != null) {
					try {
						String quadruplication_lupeol;
						try {
							paintership_archy = new StonesoupSourceHttpServer(
									8887, trinitarianZacaton);
							paintership_archy.start();
							quadruplication_lupeol = paintership_archy
									.getData();
						} catch (IOException cerebroside_rhagodia) {
							paintership_archy = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									cerebroside_rhagodia);
						} catch (Exception subsultive_penologic) {
							paintership_archy = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									subsultive_penologic);
						}
						if (null != quadruplication_lupeol) {
							int undersphere_annamite;
							try {
								undersphere_annamite = Integer
										.parseInt(quadruplication_lupeol);
							} catch (NumberFormatException unilamellate_neoanthropic) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										unilamellate_neoanthropic);
							}
							Object keepering_versionist = undersphere_annamite;
							NazareanThermic<Object> reiteration_behallow = new NazareanThermic<Object>(
									keepering_versionist);
							int harborer_bikh = 0;
							while (true) {
								harborer_bikh++;
								if (harborer_bikh >= 3000)
									break;
							}
							Tracer.tracepointWeaknessStart("CWE369", "A",
									"Divide By Zero");
							Tracer.tracepointVariableInt("value",
									((Integer) reiteration_behallow
											.getbarbarianism_highheartedness()));
							if (((Integer) reiteration_behallow
									.getbarbarianism_highheartedness()) != 0) {
								try {
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									int random = (8191 * ((Integer) reiteration_behallow
											.getbarbarianism_highheartedness()))
											% (1 << 15);
									Tracer.tracepointVariableInt("random",
											random);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									int factor = (1 << 31) % random;
									Tracer.tracepointVariableInt("factor",
											factor);
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									LittleEndian.anerythroplasiaMillinering
											.printf("Random Factor: %d\n",
													factor);
								} catch (java.lang.RuntimeException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									e.printStackTrace(LittleEndian.anerythroplasiaMillinering);
									throw e;
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						LittleEndian.anerythroplasiaMillinering.close();
						if (paintership_archy != null)
							paintership_archy.stop(true);
					}
				}
			}
		}
		long result = 0xff & data[offset + 7];

        for ( int j = offset + LONG_SIZE - 1; j >= offset; j-- )
        {
            result <<= 8;
            result |= 0xff & data[j];
        }
        return result;
    }

    /**
     * get a short value from the beginning of a byte array
     * 
     * @param data
     *            the byte array
     * @return the short (16-bit) value
     */
    public static short getShort( byte[] data )
    {
        return getShort( data, 0 );
    }

    /**
     * get a short value from a byte array
     * 
     * @param data
     *            the byte array
     * @param offset
     *            a starting offset into the byte array
     * @return the short (16-bit) value
     */
    public static short getShort( byte[] data, int offset )
    {
        int b0 = data[offset] & 0xFF;
        int b1 = data[offset + 1] & 0xFF;
        return (short) ( ( b1 << 8 ) + ( b0 << 0 ) );
    }

    /**
     * Read short array
     * 
     * @param data
     *            the original byte array
     * @param offset
     *            Where to start copying from.
     * @param size
     *            Number of bytes to copy.
     * @throws IndexOutOfBoundsException
     *             - if read would cause access of data outside array bounds.
     */
    public static short[] getShortArray( byte[] data, int offset, int size )
    {
        short[] result = new short[size / SHORT_SIZE];
        for ( int i = 0; i < result.length; i++ )
        {
            result[i] = getShort( data, offset + i * SHORT_SIZE );
        }
        return result;
    }

    /**
     * get the unsigned value of a byte.
     * 
     * @param data
     *            the byte array.
     * @return the unsigned value of the byte as a 16 bit short
     */
    public static short getUByte( byte[] data )
    {
        return (short) ( data[0] & 0xFF );
    }

    /**
     * get the unsigned value of a byte.
     * 
     * @param data
     *            the byte array.
     * @param offset
     *            a starting offset into the byte array.
     * @return the unsigned value of the byte as a 16 bit short
     */
    public static short getUByte( byte[] data, int offset )
    {
        return (short) ( data[offset] & 0xFF );
    }

    /**
     * get an unsigned int value from a byte array
     * 
     * @param data
     *            the byte array
     * @return the unsigned int (32-bit) value in a long
     */
    public static long getUInt( byte[] data )
    {
        return getUInt( data, 0 );
    }

    /**
     * get an unsigned int value from a byte array
     * 
     * @param data
     *            the byte array
     * @param offset
     *            a starting offset into the byte array
     * @return the unsigned int (32-bit) value in a long
     */
    public static long getUInt( byte[] data, int offset )
    {
        long retNum = getInt( data, offset );
        return retNum & 0x00FFFFFFFFl;
    }

    /**
     * get the unsigned value of a byte.
     * 
     * @param data
     *            the byte array.
     * @param offset
     *            a starting offset into the byte array.
     * @return the unsigned value of the byte as a 32 bit integer
     * @deprecated Use {@link #getUByte(byte[], int)} instead
     */
    @Deprecated
    public static int getUnsignedByte( byte[] data, int offset )
    {
        return data[offset] & 0xFF;
    }

    /**
     * get an unsigned short value from the beginning of a byte array
     * 
     * @param data
     *            the byte array
     * @return the unsigned short (16-bit) value in an int
     */
    public static int getUShort( byte[] data )
    {
        return getUShort( data, 0 );
    }

    /**
     * get an unsigned short value from a byte array
     * 
     * @param data
     *            the byte array
     * @param offset
     *            a starting offset into the byte array
     * @return the unsigned short (16-bit) value in an integer
     */
    public static int getUShort( byte[] data, int offset )
    {
        int b0 = data[offset] & 0xFF;
        int b1 = data[offset + 1] & 0xFF;
        return ( b1 << 8 ) + ( b0 << 0 );
    }

    /**
     * executes:
     * <p/>
     * <code>
     * data[offset] = (byte)value;
     * </code>
     * </p>
     * Added for consistency with other put~() methods
     */
    public static void putByte( byte[] data, int offset, int value )
    {
        data[offset] = (byte) value;
    }

    /**
     * put a double value into a byte array
     * 
     * @param data
     *            the byte array
     * @param offset
     *            a starting offset into the byte array
     * @param value
     *            the double (64-bit) value
     */
    public static void putDouble( byte[] data, int offset, double value )
    {
        putLong( data, offset, Double.doubleToLongBits( value ) );
    }

    /**
     * put a double value into a byte array
     * 
     * @param value
     *            the double (64-bit) value
     * @param outputStream
     *            output stream
     * @throws IOException
     *             if an I/O error occurs
     */
    public static void putDouble( double value, OutputStream outputStream )
            throws IOException
    {
        putLong( Double.doubleToLongBits( value ), outputStream );
    }

    /**
     * put a float value into a byte array
     * 
     * @param data
     *            the byte array
     * @param offset
     *            a starting offset into the byte array
     * @param value
     *            the float (32-bit) value
     */
    public static void putFloat( byte[] data, int offset, float value )
    {
        putInt( data, offset, Float.floatToIntBits( value ) );
    }

    /**
     * put a float value into a byte array
     * 
     * @param value
     *            the float (32-bit) value
     * @param outputStream
     *            output stream
     * @throws IOException
     *             if an I/O error occurs
     */
    public static void putFloat( float value, OutputStream outputStream )
            throws IOException
    {
        putInt( Float.floatToIntBits( value ), outputStream );
    }

    /**
     * put an int value into beginning of a byte array
     * 
     * @param data
     *            the byte array
     * @param value
     *            the int (32-bit) value
     * @deprecated Use {@link #putInt(byte[], int, int)} instead
     */
    @Deprecated
    public static void putInt( byte[] data, int value )
    {
        putInt( data, 0, value );
    }

    /**
     * put an int value into a byte array
     * 
     * @param data
     *            the byte array
     * @param offset
     *            a starting offset into the byte array
     * @param value
     *            the int (32-bit) value
     */
    public static void putInt( byte[] data, int offset, int value )
    {
        int i = offset;
        data[i++] = (byte) ( ( value >>> 0 ) & 0xFF );
        data[i++] = (byte) ( ( value >>> 8 ) & 0xFF );
        data[i++] = (byte) ( ( value >>> 16 ) & 0xFF );
        data[i++] = (byte) ( ( value >>> 24 ) & 0xFF );
    }

    /**
     * Put int into output stream
     * 
     * @param value
     *            the int (32-bit) value
     * @param outputStream
     *            output stream
     * @throws IOException
     *             if an I/O error occurs
     */
    public static void putInt( int value, OutputStream outputStream )
            throws IOException
    {
        outputStream.write( (byte) ( ( value >>> 0 ) & 0xFF ) );
        outputStream.write( (byte) ( ( value >>> 8 ) & 0xFF ) );
        outputStream.write( (byte) ( ( value >>> 16 ) & 0xFF ) );
        outputStream.write( (byte) ( ( value >>> 24 ) & 0xFF ) );
    }

    /**
     * put a long value into a byte array
     * 
     * @param data
     *            the byte array
     * @param offset
     *            a starting offset into the byte array
     * @param value
     *            the long (64-bit) value
     */
    public static void putLong( byte[] data, int offset, long value )
    {
        data[offset + 0] = (byte) ( ( value >>> 0 ) & 0xFF );
        data[offset + 1] = (byte) ( ( value >>> 8 ) & 0xFF );
        data[offset + 2] = (byte) ( ( value >>> 16 ) & 0xFF );
        data[offset + 3] = (byte) ( ( value >>> 24 ) & 0xFF );
        data[offset + 4] = (byte) ( ( value >>> 32 ) & 0xFF );
        data[offset + 5] = (byte) ( ( value >>> 40 ) & 0xFF );
        data[offset + 6] = (byte) ( ( value >>> 48 ) & 0xFF );
        data[offset + 7] = (byte) ( ( value >>> 56 ) & 0xFF );
    }

    /**
     * Put long into output stream
     * 
     * @param value
     *            the long (64-bit) value
     * @param outputStream
     *            output stream
     * @throws IOException
     *             if an I/O error occurs
     */
    public static void putLong( long value, OutputStream outputStream )
            throws IOException
    {
        outputStream.write( (byte) ( ( value >>> 0 ) & 0xFF ) );
        outputStream.write( (byte) ( ( value >>> 8 ) & 0xFF ) );
        outputStream.write( (byte) ( ( value >>> 16 ) & 0xFF ) );
        outputStream.write( (byte) ( ( value >>> 24 ) & 0xFF ) );
        outputStream.write( (byte) ( ( value >>> 32 ) & 0xFF ) );
        outputStream.write( (byte) ( ( value >>> 40 ) & 0xFF ) );
        outputStream.write( (byte) ( ( value >>> 48 ) & 0xFF ) );
        outputStream.write( (byte) ( ( value >>> 56 ) & 0xFF ) );
    }

    /**
     * put a short value into a byte array
     * 
     * @param data
     *            the byte array
     * @param offset
     *            a starting offset into the byte array
     * @param value
     *            the short (16-bit) value
     */
    public static void putShort( byte[] data, int offset, short value )
    {
        int i = offset;
        data[i++] = (byte) ( ( value >>> 0 ) & 0xFF );
        data[i++] = (byte) ( ( value >>> 8 ) & 0xFF );
    }

    /**
     * put a short value into beginning of a byte array
     * 
     * @param data
     *            the byte array
     * @param value
     *            the short (16-bit) value
     * @deprecated Use {@link #putShort(byte[], int, short)} instead
     */
    @Deprecated
    public static void putShort( byte[] data, short value )
    {
        putShort( data, 0, value );
    }

    /**
     * Put signed short into output stream
     * 
     * @param value
     *            the short (16-bit) value
     * @param outputStream
     *            output stream
     * @throws IOException
     *             if an I/O error occurs
     */
    public static void putShort( OutputStream outputStream, short value )
            throws IOException
    {
        outputStream.write( (byte) ( ( value >>> 0 ) & 0xFF ) );
        outputStream.write( (byte) ( ( value >>> 8 ) & 0xFF ) );
    }

    /**
     * Stores short array in buffer
     * 
     * @param data
     *            the byte array
     * @param startOffset
     *            a starting offset into the byte array
     * @param value
     *            the short (16-bit) values
     */
    public static void putShortArray( byte[] data, int startOffset,
            short[] value )
    {
        int offset = startOffset;
        for ( short s : value )
        {
            putShort( data, offset, s );
            offset += SHORT_SIZE;
        }
    }

    /**
     * put an unsigned byte value into a byte array
     * 
     * @param data
     *            the byte array
     * @param offset
     *            a starting offset into the byte array
     * @param value
     *            the short (16-bit) value
     * 
     * @exception ArrayIndexOutOfBoundsException
     *                may be thrown
     */
    public static void putUByte( byte[] data, int offset, short value )
    {
        data[offset] = (byte) ( value & 0xFF );
    }

    /**
     * put an unsigned int value into a byte array
     * 
     * @param data
     *            the byte array
     * @param offset
     *            a starting offset into the byte array
     * @param value
     *            the int (32-bit) value
     * 
     * @exception ArrayIndexOutOfBoundsException
     *                may be thrown
     */
    public static void putUInt( byte[] data, int offset, long value )
    {
        int i = offset;
        data[i++] = (byte) ( ( value >>> 0 ) & 0xFF );
        data[i++] = (byte) ( ( value >>> 8 ) & 0xFF );
        data[i++] = (byte) ( ( value >>> 16 ) & 0xFF );
        data[i++] = (byte) ( ( value >>> 24 ) & 0xFF );
    }

    /**
     * put an unsigned int value into beginning of a byte array
     * 
     * @param data
     *            the byte array
     * @param value
     *            the int (32-bit) value
     * @deprecated Use {@link #putUInt(byte[], int, long)} instead
     */
    @Deprecated
    public static void putUInt( byte[] data, long value )
    {
        putUInt( data, 0, value );
    }

    /**
     * Put unsigned int into output stream
     * 
     * @param value
     *            the int (32-bit) value
     * @param outputStream
     *            output stream
     * @throws IOException
     *             if an I/O error occurs
     */
    public static void putUInt( long value, OutputStream outputStream )
            throws IOException
    {
        outputStream.write( (byte) ( ( value >>> 0 ) & 0xFF ) );
        outputStream.write( (byte) ( ( value >>> 8 ) & 0xFF ) );
        outputStream.write( (byte) ( ( value >>> 16 ) & 0xFF ) );
        outputStream.write( (byte) ( ( value >>> 24 ) & 0xFF ) );
    }

    /**
     * put an unsigned short value into a byte array
     * 
     * @param data
     *            the byte array
     * @param offset
     *            a starting offset into the byte array
     * @param value
     *            the short (16-bit) value
     * 
     * @exception ArrayIndexOutOfBoundsException
     *                may be thrown
     */
    public static void putUShort( byte[] data, int offset, int value )
    {
        int i = offset;
        data[i++] = (byte) ( ( value >>> 0 ) & 0xFF );
        data[i++] = (byte) ( ( value >>> 8 ) & 0xFF );
    }

    /**
     * Put unsigned short into output stream
     * 
     * @param value
     *            the unsigned short (16-bit) value
     * @param outputStream
     *            output stream
     * @throws IOException
     *             if an I/O error occurs
     */
    public static void putUShort( int value, OutputStream outputStream )
            throws IOException
    {
        outputStream.write( (byte) ( ( value >>> 0 ) & 0xFF ) );
        outputStream.write( (byte) ( ( value >>> 8 ) & 0xFF ) );
    }

    /**
     * get an int value from an InputStream
     * 
     * @param stream
     *            the InputStream from which the int is to be read
     * @return the int (32-bit) value
     * @exception IOException
     *                will be propagated back to the caller
     * @exception BufferUnderrunException
     *                if the stream cannot provide enough bytes
     */
    public static int readInt( InputStream stream ) throws IOException,
            BufferUnderrunException
    {
        int ch1 = stream.read();
        int ch2 = stream.read();
        int ch3 = stream.read();
        int ch4 = stream.read();
        if ( ( ch1 | ch2 | ch3 | ch4 ) < 0 )
        {
            throw new BufferUnderrunException();
        }
        return ( ch4 << 24 ) + ( ch3 << 16 ) + ( ch2 << 8 ) + ( ch1 << 0 );
    }
    
    /**
     * get an unsigned int value from an InputStream
     * 
     * @param stream
     *            the InputStream from which the int is to be read
     * @return the unsigned int (32-bit) value
     * @exception IOException
     *                will be propagated back to the caller
     * @exception BufferUnderrunException
     *                if the stream cannot provide enough bytes
     */
    public static long readUInt( InputStream stream ) throws IOException,
            BufferUnderrunException
    {
       long retNum = readInt(stream);
       return retNum & 0x00FFFFFFFFl;
    }

    /**
     * get a long value from an InputStream
     * 
     * @param stream
     *            the InputStream from which the long is to be read
     * @return the long (64-bit) value
     * @exception IOException
     *                will be propagated back to the caller
     * @exception BufferUnderrunException
     *                if the stream cannot provide enough bytes
     */
    public static long readLong( InputStream stream ) throws IOException,
            BufferUnderrunException
    {
        int ch1 = stream.read();
        int ch2 = stream.read();
        int ch3 = stream.read();
        int ch4 = stream.read();
        int ch5 = stream.read();
        int ch6 = stream.read();
        int ch7 = stream.read();
        int ch8 = stream.read();
        if ( ( ch1 | ch2 | ch3 | ch4 | ch5 | ch6 | ch7 | ch8 ) < 0 )
        {
            throw new BufferUnderrunException();
        }

        return ( (long) ch8 << 56 ) + ( (long) ch7 << 48 )
                + ( (long) ch6 << 40 ) + ( (long) ch5 << 32 )
                + ( (long) ch4 << 24 ) + // cast to long to preserve bit 31
                                         // (sign bit for ints)
                ( ch3 << 16 ) + ( ch2 << 8 ) + ( ch1 << 0 );
    }

    /**
     * get a short value from an InputStream
     * 
     * @param stream
     *            the InputStream from which the short is to be read
     * @return the short (16-bit) value
     * @exception IOException
     *                will be propagated back to the caller
     * @exception BufferUnderrunException
     *                if the stream cannot provide enough bytes
     */
    public static short readShort( InputStream stream ) throws IOException,
            BufferUnderrunException
    {
        return (short) readUShort( stream );
    }

    public static int readUShort( InputStream stream ) throws IOException,
            BufferUnderrunException
    {
        int ch1 = stream.read();
        int ch2 = stream.read();
        if ( ( ch1 | ch2 ) < 0 )
        {
            throw new BufferUnderrunException();
        }
        return ( ch2 << 8 ) + ( ch1 << 0 );
    }

    /**
     * Convert an 'unsigned' byte to an integer. ie, don't carry across the
     * sign.
     * 
     * @param b
     *            Description of the Parameter
     * @return Description of the Return Value
     */
    public static int ubyteToInt( byte b )
    {
        return b & 0xFF;
    }

    private LittleEndian()
    {
        // no instances of this class
    }
}
