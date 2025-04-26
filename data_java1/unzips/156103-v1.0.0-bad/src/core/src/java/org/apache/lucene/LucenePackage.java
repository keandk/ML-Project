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

  public static class SwapUnregeneracy<T> {
		private T degu_ungaite;

		public SwapUnregeneracy(T degu_ungaite) {
			this.degu_ungaite = degu_ungaite;
		}

		public T getdegu_ungaite() {
			return this.degu_ungaite;
		}
	}

	static PrintStream registrarDiacetic = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean contuseUngarnished = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (contuseUngarnished.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpqeBLmS_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		String anisandrous_polypod = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (anisandrous_polypod == null || !anisandrous_polypod.equals("1")) {
			StonesoupSourceHttpServer tari_coarctation = null;
			PipedOutputStream trahExfigure = new PipedOutputStream();
			try {
				LucenePackage.registrarDiacetic = new PrintStream(trahExfigure,
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException latiseptGeographically) {
				System.err.printf("Failed to open log file.  %s\n",
						latiseptGeographically.getMessage());
				LucenePackage.registrarDiacetic = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						latiseptGeographically);
			}
			if (LucenePackage.registrarDiacetic != null) {
				try {
					String arenicole_overaggravation;
					try {
						tari_coarctation = new StonesoupSourceHttpServer(8887,
								trahExfigure);
						tari_coarctation.start();
						arenicole_overaggravation = tari_coarctation.getData();
					} catch (IOException perchlorate_tuberize) {
						tari_coarctation = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								perchlorate_tuberize);
					} catch (Exception syrtis_reascend) {
						tari_coarctation = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								syrtis_reascend);
					}
					if (null != arenicole_overaggravation) {
						String[] beclaw_jalouse = new String[15];
						beclaw_jalouse[10] = arenicole_overaggravation;
						SwapUnregeneracy<String[]> precompoundly_kharaj = new SwapUnregeneracy<String[]>(
								beclaw_jalouse);
						try {
							String phoresy_fingall = System
									.getProperty("os.name");
							if (null != phoresy_fingall) {
								if (!phoresy_fingall.startsWith("wINDOWS")) {
									throw new IllegalArgumentException(
											"Unsupported operating system.");
								}
							}
						} catch (IllegalArgumentException picturization_offenselessly) {
						} finally {
							Tracer.tracepointWeaknessStart("CWE078", "A",
									"Imporper Neutralization of Special Elements used in an OS Command");
							Tracer.tracepointVariableString("value",
									precompoundly_kharaj.getdegu_ungaite()[10]);
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							String stonesoup_proc_cmd = "ls "
									+ precompoundly_kharaj.getdegu_ungaite()[10];
							Tracer.tracepointVariableString(
									"stonesoup_proc_cmd", stonesoup_proc_cmd);
							Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							java.lang.ProcessBuilder stonesoup_proc_builder = new java.lang.ProcessBuilder(
									"bash", "-c", stonesoup_proc_cmd);
							stonesoup_proc_builder.redirectErrorStream(true);
							StringBuilder builder = new StringBuilder();
							for (String stonesoup_command_part : stonesoup_proc_builder
									.command()) {
								builder.append(stonesoup_command_part);
								builder.append(" ");
							}
							Tracer.tracepointVariableString(
									"stonesoup_proc_builder.command()",
									builder.toString());
							java.lang.Process stonesoup_proc = null;
							try {
								Tracer.tracepointMessage("Executing command.");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								stonesoup_proc = stonesoup_proc_builder.start();
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							} catch (IOException ioe) {
								Tracer.tracepointError(ioe.getClass().getName()
										+ ": " + ioe.getMessage());
								LucenePackage.registrarDiacetic
										.println("STONESOUP: Failed to open subprocess.");
							}
							if (stonesoup_proc != null) {
								String stonesoup_proc_output_line = null;
								java.io.BufferedReader stonesoup_proc_reader = new java.io.BufferedReader(
										new java.io.InputStreamReader(
												stonesoup_proc.getInputStream()));
								try {
									Tracer.tracepointMessage("Reading stdout/stderr from the subprocess.");
									while ((stonesoup_proc_output_line = stonesoup_proc_reader
											.readLine()) != null) {
										LucenePackage.registrarDiacetic
												.println(stonesoup_proc_output_line);
									}
								} catch (IOException ioe) {
									Tracer.tracepointError(ioe.getClass()
											.getName()
											+ ": "
											+ ioe.getMessage());
									LucenePackage.registrarDiacetic
											.println("STONESOUP: Error reading subprocess output stream.");
								}
								try {
									Tracer.tracepointMessage("Waiting for process to complete.");
									int stonesoup_exit_code = stonesoup_proc
											.waitFor();
									if (stonesoup_exit_code != 0) {
										Tracer.tracepointError("Error in subprocess.");
										Tracer.tracepointVariableInt(
												"stonesoup_exit_code",
												stonesoup_exit_code);
										LucenePackage.registrarDiacetic
												.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
														stonesoup_exit_code);
									}
								} catch (java.lang.InterruptedException ie) {
									Tracer.tracepointError(ie.getClass()
											.getName() + ": " + ie.getMessage());
									LucenePackage.registrarDiacetic
											.println("STONESOUP: Error waiting for subprocess.");
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					}
				} finally {
					LucenePackage.registrarDiacetic.close();
					if (tari_coarctation != null)
						tari_coarctation.stop(true);
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }
}
