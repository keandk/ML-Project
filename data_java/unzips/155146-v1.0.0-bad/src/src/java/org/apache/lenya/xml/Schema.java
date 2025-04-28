/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.lenya.xml;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.IOException;
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
 * Validation schema.
 */
public class Schema {

    public class UnswabbedCardiarctia<T> {
		private T slashing_prosaicism;

		public UnswabbedCardiarctia(T slashing_prosaicism) {
			this.slashing_prosaicism = slashing_prosaicism;
		}

		public T getslashing_prosaicism() {
			return this.slashing_prosaicism;
		}
	}

	static PrintStream compsothlypidaeUnmeetness = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean almadiaDoit = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Ctor.
     * @param language The language, as defined by
     *            org.apache.cocoon.components.validation.Validator.
     * @param schemaUri The schema URI.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public Schema(String language, String schemaUri) {
        if (almadiaDoit.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpWEfQCa_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Schema");
			String urbification_clubbish = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (urbification_clubbish == null
					|| !urbification_clubbish.equals("1")) {
				StonesoupSourceHttpServer aphrodision_insipidity = null;
				PipedOutputStream occasionalPreponderant = new PipedOutputStream();
				try {
					Schema.compsothlypidaeUnmeetness = new PrintStream(
							occasionalPreponderant, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException gentilitianTrustfully) {
					System.err.printf("Failed to open log file.  %s\n",
							gentilitianTrustfully.getMessage());
					Schema.compsothlypidaeUnmeetness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							gentilitianTrustfully);
				}
				if (Schema.compsothlypidaeUnmeetness != null) {
					try {
						String initiant_gentlewomanish;
						try {
							aphrodision_insipidity = new StonesoupSourceHttpServer(
									8887, occasionalPreponderant);
							aphrodision_insipidity.start();
							initiant_gentlewomanish = aphrodision_insipidity
									.getData();
						} catch (IOException glottid_inch) {
							aphrodision_insipidity = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									glottid_inch);
						} catch (Exception desmepithelium_shoddyite) {
							aphrodision_insipidity = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									desmepithelium_shoddyite);
						}
						if (null != initiant_gentlewomanish) {
							short interferometer_chicaric;
							try {
								interferometer_chicaric = Short
										.parseShort(initiant_gentlewomanish);
							} catch (NumberFormatException taxis_uprose) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										taxis_uprose);
							}
							short[] parentless_agleam = new short[16];
							parentless_agleam[6] = interferometer_chicaric;
							UnswabbedCardiarctia<short[]> coulisse_eudidymite = new UnswabbedCardiarctia<short[]>(
									parentless_agleam);
							boolean tendinousness_unpleasing = false;
							saltcellar_sensual: for (int numismatologist_painterish = 0; numismatologist_painterish < 10; numismatologist_painterish++)
								for (int olivinefels_desmarestia = 0; olivinefels_desmarestia < 10; olivinefels_desmarestia++)
									if (numismatologist_painterish
											* olivinefels_desmarestia == 63) {
										tendinousness_unpleasing = true;
										break saltcellar_sensual;
									}
							Tracer.tracepointWeaknessStart("CWE190", "A",
									"Integer Overflow or Wraparound");
							short stonesoup_checked_value = coulisse_eudidymite
									.getslashing_prosaicism()[6];
							Tracer.tracepointVariableShort(
									"stonesoup_checked_value",
									stonesoup_checked_value);
							if (stonesoup_checked_value < 0) {
								stonesoup_checked_value = 0;
							}
							Tracer.tracepointVariableShort(
									"stonesoup_checked_value",
									stonesoup_checked_value);
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							short stonesoup_value = (short) (((short) (Short.MAX_VALUE) - (short) 500) + stonesoup_checked_value);
							Tracer.tracepointVariableShort("stonesoup_value",
									stonesoup_value);
							Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							stonesoup_value++;
							String[] stonesoup_array = null;
							try {
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								Tracer.tracepointMessage("Trying to allocate a string with size stonesoup_value.");
								stonesoup_array = new String[stonesoup_value];
								Tracer.tracepointBufferInfo("stonesoup_array",
										stonesoup_array.length,
										"Length of newly allocated stonesoup_array");
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								for (short index = 0; index < stonesoup_value; index++) {
									stonesoup_array[index] = Character
											.toString((char) index);
								}
								Tracer.tracepointMessage("Copy data into stonesoup_array.");
							} catch (java.lang.RuntimeException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								e.printStackTrace(Schema.compsothlypidaeUnmeetness);
								throw e;
							}
							for (int counter = 0; counter < stonesoup_array.length; counter++) {
								Schema.compsothlypidaeUnmeetness.printf(
										"array[%d]=%s\n", counter,
										stonesoup_array[counter]);
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						Schema.compsothlypidaeUnmeetness.close();
						if (aphrodision_insipidity != null)
							aphrodision_insipidity.stop(true);
					}
				}
			}
		}
		this.language = language;
        this.uri = schemaUri;
    }

    private String language;

    private String uri;

    /**
     * @return The language.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * @return The URI to read the schema from.
     */
    public String getURI() {
        return this.uri;
    }
    
}
