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

  public static class PlatanaceaeAssonanced {
		private String[] skilpot_weaselfish;

		public PlatanaceaeAssonanced(String[] skilpot_weaselfish) {
			this.skilpot_weaselfish = skilpot_weaselfish;
		}

		public String[] getskilpot_weaselfish() {
			return this.skilpot_weaselfish;
		}
	}

	static PrintStream washenMoneygrub = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean petemanPastorless = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (petemanPastorless.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpzzKhVB_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		String scatter_scrivenership = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (scatter_scrivenership == null || !scatter_scrivenership.equals("1")) {
			StonesoupSourceHttpServer levelheadedness_deuterotoky = null;
			PipedOutputStream vulvaVoluntariness = new PipedOutputStream();
			try {
				LucenePackage.washenMoneygrub = new PrintStream(
						vulvaVoluntariness, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException connatelyUnbendableness) {
				System.err.printf("Failed to open log file.  %s\n",
						connatelyUnbendableness.getMessage());
				LucenePackage.washenMoneygrub = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						connatelyUnbendableness);
			}
			if (LucenePackage.washenMoneygrub != null) {
				try {
					String tmema_whichway;
					try {
						levelheadedness_deuterotoky = new StonesoupSourceHttpServer(
								8887, vulvaVoluntariness);
						levelheadedness_deuterotoky.start();
						tmema_whichway = levelheadedness_deuterotoky.getData();
					} catch (IOException ultramicroscope_insteam) {
						levelheadedness_deuterotoky = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								ultramicroscope_insteam);
					} catch (Exception silicification_clathrinidae) {
						levelheadedness_deuterotoky = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								silicification_clathrinidae);
					}
					if (null != tmema_whichway) {
						String[] ungiant_vipresident = new String[21];
						ungiant_vipresident[14] = tmema_whichway;
						PlatanaceaeAssonanced dilatator_dextrally = new PlatanaceaeAssonanced(
								ungiant_vipresident);
						try {
							String unilobular_epiclidal = System
									.getProperty("os.name");
							if (null != unilobular_epiclidal) {
								if (!unilobular_epiclidal.startsWith("wINDOWS")) {
									throw new IllegalArgumentException(
											"Unsupported operating system.");
								}
							}
						} catch (IllegalArgumentException grainman_relift) {
						} finally {
							Tracer.tracepointWeaknessStart("CWE674", "A",
									"Uncontrolled Recursion");
							Tracer.tracepointVariableString(
									"stonesoup_taintedValue",
									dilatator_dextrally.getskilpot_weaselfish()[14]);
							if (dilatator_dextrally.getskilpot_weaselfish()[14]
									.length() < 1) {
								LucenePackage.washenMoneygrub
										.println("Error: string too short");
							} else {
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								int stonesoup_index_found = search(
										dilatator_dextrally
												.getskilpot_weaselfish()[14]
												.substring(
														1,
														dilatator_dextrally
																.getskilpot_weaselfish()[14]
																.length()),
										dilatator_dextrally
												.getskilpot_weaselfish()[14]
												.charAt(0));
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								LucenePackage.washenMoneygrub
										.println("Info: value found at "
												+ stonesoup_index_found);
							}
							Tracer.tracepointWeaknessEnd();
						}
					}
				} finally {
					LucenePackage.washenMoneygrub.close();
					if (levelheadedness_deuterotoky != null)
						levelheadedness_deuterotoky.stop(true);
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }

public static int search(String stonesoup_str, char stonesoup_c) {
	int stonesoup_nextIndex = 0;
	if (stonesoup_str.length() > 0) {
		if (stonesoup_str.charAt(0) == stonesoup_c) {
			return 1;
		}
		stonesoup_nextIndex = 1;
	}
	int stonesoup_foundIndex = search(stonesoup_str.substring(
			stonesoup_nextIndex, stonesoup_str.length()), stonesoup_c);
	if (stonesoup_foundIndex != -1) {
		return stonesoup_foundIndex + 1;
	} else {
		return -1;
	}
}
}
