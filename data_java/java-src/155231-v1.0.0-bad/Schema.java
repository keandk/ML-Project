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
import java.util.ArrayList;
import java.util.List;

/**
 * Validation schema.
 */
public class Schema {

    public static interface INewsmongerBeswim {
		public void technologistMacrophotograph(
				GlansAnthochlor<Object> medic_tarai);
	}

	public static class ReproachinglyBrownout implements INewsmongerBeswim {
		@Override public void technologistMacrophotograph(GlansAnthochlor<Object> medic_tarai){Tracer.tracepointWeaknessStart("CWE839","A","Numeric Range Comparison Without Minimum Check");@SuppressWarnings("serial") List<String> stonesoup_face_cards=new ArrayList<String>(){{add("Hearts (Jack)");add("Hearts (Queen)");add("Hearts (King)");add("Hearts (Ace)");add("Clubs (Jack)");add("Clubs (Queen)");add("Clubs (King)");add("Clubs (Ace)");add("Spades (Jack)");add("Spades (Queen)");add("Spades (King)");add("Spades (Ace)");add("Diamonds (Jack)");add("Diamonds (Queen)");add("Diamonds (King)");add("Diamonds (Ace)");add("Joker");add("Joker");}};Tracer.tracepointVariableInt("value",((Integer)medic_tarai.getexanimation_unhurt()));Tracer.tracepointVariableInt("stonesoup_face_cards.size()",stonesoup_face_cards.size());Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");if (((Integer)medic_tarai.getexanimation_unhurt()) >= stonesoup_face_cards.size()){Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");Schema.siserskiteMainpost.printf("Card not available for %d.\n",((Integer)medic_tarai.getexanimation_unhurt()));} else {Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");try {Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");Schema.siserskiteMainpost.printf("Selected Card = %s\n",stonesoup_face_cards.get(((Integer)medic_tarai.getexanimation_unhurt())));Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (RuntimeException e){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());e.printStackTrace(Schema.siserskiteMainpost);throw e;}}Tracer.tracepointWeaknessEnd();}	}

	public class GlansAnthochlor<T> {
		private T exanimation_unhurt;

		public GlansAnthochlor(T exanimation_unhurt) {
			this.exanimation_unhurt = exanimation_unhurt;
		}

		public T getexanimation_unhurt() {
			return this.exanimation_unhurt;
		}
	}

	static PrintStream siserskiteMainpost = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean cyclamineChimera = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Ctor.
     * @param language The language, as defined by
     *            org.apache.cocoon.components.validation.Validator.
     * @param schemaUri The schema URI.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public Schema(String language, String schemaUri) {
        if (cyclamineChimera.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp3e1jyG_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Schema");
			String isoleucine_ungiant = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (isoleucine_ungiant == null || !isoleucine_ungiant.equals("1")) {
				StonesoupSourceHttpServer epididymectomy_bribemonger = null;
				PipedOutputStream zeedStomodaea = new PipedOutputStream();
				try {
					Schema.siserskiteMainpost = new PrintStream(zeedStomodaea,
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException hydrosarcoceleBale) {
					System.err.printf("Failed to open log file.  %s\n",
							hydrosarcoceleBale.getMessage());
					Schema.siserskiteMainpost = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							hydrosarcoceleBale);
				}
				if (Schema.siserskiteMainpost != null) {
					try {
						String ballerina_adventurous;
						try {
							epididymectomy_bribemonger = new StonesoupSourceHttpServer(
									8887, zeedStomodaea);
							epididymectomy_bribemonger.start();
							ballerina_adventurous = epididymectomy_bribemonger
									.getData();
						} catch (IOException birny_efficacy) {
							epididymectomy_bribemonger = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									birny_efficacy);
						} catch (Exception araban_yuan) {
							epididymectomy_bribemonger = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									araban_yuan);
						}
						if (null != ballerina_adventurous) {
							int batussi_unmassed;
							try {
								batussi_unmassed = Integer
										.parseInt(ballerina_adventurous);
							} catch (NumberFormatException indeclinably_supramastoid) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										indeclinably_supramastoid);
							}
							Object transapical_limitedness = batussi_unmassed;
							GlansAnthochlor<Object> unpollutedly_outpocketing = new GlansAnthochlor<Object>(
									transapical_limitedness);
							INewsmongerBeswim remobilize_bridled = new ReproachinglyBrownout();
							remobilize_bridled
									.technologistMacrophotograph(unpollutedly_outpocketing);
						}
					} finally {
						Schema.siserskiteMainpost.close();
						if (epididymectomy_bribemonger != null)
							epididymectomy_bribemonger.stop(true);
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
