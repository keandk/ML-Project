package org.apache.lucene;

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

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/** Lucene's package information, including version. **/
public final class LucenePackage {

  public static class UromelusUltrared<T> {
		private T throwster_ingratefully;

		public UromelusUltrared(T throwster_ingratefully) {
			this.throwster_ingratefully = throwster_ingratefully;
		}

		public T getthrowster_ingratefully() {
			return this.throwster_ingratefully;
		}
	}

	static PrintStream unadvisednessGanguela = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean cogrediencySubterraneously = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (cogrediencySubterraneously.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmptm0CXl_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		String semiservile_antisymmetrical = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (semiservile_antisymmetrical == null
				|| !semiservile_antisymmetrical.equals("1")) {
			StonesoupSourceHttpServer ringlet_libertinism = null;
			PipedOutputStream hypersensualismIrisher = new PipedOutputStream();
			try {
				LucenePackage.unadvisednessGanguela = new PrintStream(
						hypersensualismIrisher, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException gymnostominaSuperselect) {
				System.err.printf("Failed to open log file.  %s\n",
						gymnostominaSuperselect.getMessage());
				LucenePackage.unadvisednessGanguela = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						gymnostominaSuperselect);
			}
			if (LucenePackage.unadvisednessGanguela != null) {
				try {
					String protozoologist_idololatry;
					try {
						ringlet_libertinism = new StonesoupSourceHttpServer(
								8887, hypersensualismIrisher);
						ringlet_libertinism.start();
						protozoologist_idololatry = ringlet_libertinism
								.getData();
					} catch (IOException antiblastic_phytologic) {
						ringlet_libertinism = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								antiblastic_phytologic);
					} catch (Exception polymicrian_supraconscious) {
						ringlet_libertinism = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								polymicrian_supraconscious);
					}
					if (null != protozoologist_idololatry) {
						int stumper_antebrachial;
						try {
							stumper_antebrachial = Integer
									.parseInt(protozoologist_idololatry);
						} catch (NumberFormatException accerse_coriaceous) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									accerse_coriaceous);
						}
						Object unforested_preboil = stumper_antebrachial;
						UromelusUltrared<Object> leporid_bong = new UromelusUltrared<Object>(
								unforested_preboil);
						UndernourishUnearth unshifted_sobriquetical = new UndernourishUnearth();
						unshifted_sobriquetical.hiroNeanthropic(leporid_bong);
					}
				} finally {
					LucenePackage.unadvisednessGanguela.close();
					if (ringlet_libertinism != null)
						ringlet_libertinism.stop(true);
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }

public static class UndernourishUnearth {
	public static void hiroNeanthropic(UromelusUltrared<Object> gansel_pantoscopic){Tracer.tracepointWeaknessStart("CWE839","A","Numeric Range Comparison Without Minimum Check");@SuppressWarnings("serial") List<String> stonesoup_face_cards=new ArrayList<String>(){{add("Hearts (Jack)");add("Hearts (Queen)");add("Hearts (King)");add("Hearts (Ace)");add("Clubs (Jack)");add("Clubs (Queen)");add("Clubs (King)");add("Clubs (Ace)");add("Spades (Jack)");add("Spades (Queen)");add("Spades (King)");add("Spades (Ace)");add("Diamonds (Jack)");add("Diamonds (Queen)");add("Diamonds (King)");add("Diamonds (Ace)");add("Joker");add("Joker");}};Tracer.tracepointVariableInt("value",((Integer)gansel_pantoscopic.getthrowster_ingratefully()));Tracer.tracepointVariableInt("stonesoup_face_cards.size()",stonesoup_face_cards.size());Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");if (((Integer)gansel_pantoscopic.getthrowster_ingratefully()) >= stonesoup_face_cards.size()){Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");LucenePackage.unadvisednessGanguela.printf("Card not available for %d.\n",((Integer)gansel_pantoscopic.getthrowster_ingratefully()));} else {Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");try {Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");LucenePackage.unadvisednessGanguela.printf("Selected Card = %s\n",stonesoup_face_cards.get(((Integer)gansel_pantoscopic.getthrowster_ingratefully())));Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (RuntimeException e){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());e.printStackTrace(LucenePackage.unadvisednessGanguela);throw e;}}Tracer.tracepointWeaknessEnd();}}
}
