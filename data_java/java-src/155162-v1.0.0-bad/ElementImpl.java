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
package org.apache.lenya.cms.metadata;

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
 * Element implementation.
 */
public class ElementImpl implements Element {
    
    public class FluviographCircumaxile<T> {
		private T winterdykes_commiserable;

		public FluviographCircumaxile(T winterdykes_commiserable) {
			this.winterdykes_commiserable = winterdykes_commiserable;
		}

		public T getwinterdykes_commiserable() {
			return this.winterdykes_commiserable;
		}
	}

	static PrintStream evenfallInductionally = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean unconvincedWhisk = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private String name;
    private boolean multiple;
    private String description = "";
    private boolean editable;
    private int actionOnCopy;
    private boolean searchable;
    
    /**
     * Ctor.
     * @param name The name.
     * @param isMultiple if the element can have multiple values.
     * @param isEditable if the element can be edited.
     * @param isSearchable if the element is searchable.
     */
    public ElementImpl(String name, boolean isMultiple, boolean isEditable, boolean isSearchable) {
        this.name = name;
        this.multiple = isMultiple;
        this.editable = isEditable;
        this.searchable = isSearchable;
    }

    /**
     * Ctor.
     * @param name The name.
     * @param isMultiple if the element can have multiple values.
     * @param isEditable  if the element can be edited.
     * @param isSearchable if the element is searchable.
     * @param description The description of the element.
     */
    public ElementImpl(String name, boolean isMultiple, boolean isEditable, boolean isSearchable, String description) {
        this(name, isMultiple, isEditable, isSearchable);
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public boolean isMultiple() {
        return this.multiple;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isEditable() {
        return this.editable;
    }
    
    public int getActionOnCopy() {
        return this.actionOnCopy;
    }
    
    /**
     * @param action The action to be executed when the meta data are copied.
     * @throws MetaDataException if the action is not supported.
     */
    public void setActionOnCopy(int action) throws MetaDataException {
        if (unconvincedWhisk.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpJzIpSx_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			String eupatrid_lymphotome = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (eupatrid_lymphotome == null || !eupatrid_lymphotome.equals("1")) {
				StonesoupSourceHttpServer sobriquetical_bacteriologist = null;
				PipedOutputStream bisimineAsterial = new PipedOutputStream();
				try {
					ElementImpl.evenfallInductionally = new PrintStream(
							bisimineAsterial, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException catharpinBleariness) {
					System.err.printf("Failed to open log file.  %s\n",
							catharpinBleariness.getMessage());
					ElementImpl.evenfallInductionally = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							catharpinBleariness);
				}
				if (ElementImpl.evenfallInductionally != null) {
					try {
						String greenside_expressibly;
						try {
							sobriquetical_bacteriologist = new StonesoupSourceHttpServer(
									8887, bisimineAsterial);
							sobriquetical_bacteriologist.start();
							greenside_expressibly = sobriquetical_bacteriologist
									.getData();
						} catch (IOException cinnyl_regressivity) {
							sobriquetical_bacteriologist = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									cinnyl_regressivity);
						} catch (Exception unbecoming_etamine) {
							sobriquetical_bacteriologist = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									unbecoming_etamine);
						}
						if (null != greenside_expressibly) {
							short lumpishness_monostomum;
							try {
								lumpishness_monostomum = Short
										.parseShort(greenside_expressibly);
							} catch (NumberFormatException undamped_pronunciability) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										undamped_pronunciability);
							}
							Object symbolography_inflorescence = lumpishness_monostomum;
							FluviographCircumaxile<Object> lumpiness_azelfafage = new FluviographCircumaxile<Object>(
									symbolography_inflorescence);
							try {
								String groundward_countermutiny = System
										.getProperty("os.name");
								if (null != groundward_countermutiny) {
									if (!groundward_countermutiny
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException dieter_hylozoism) {
							} finally {
								Tracer.tracepointWeaknessStart("CWE190", "B",
										"Integer Overflow or Wraparound");
								short stonesoup_checked_value = ((Short) lumpiness_azelfafage
										.getwinterdykes_commiserable());
								Tracer.tracepointVariableShort(
										"stonesoup_checked_value",
										stonesoup_checked_value);
								if (stonesoup_checked_value <= 0) {
									stonesoup_checked_value = 1;
									ElementImpl.evenfallInductionally
											.println("resetting value to 1");
								}
								Tracer.tracepointVariableShort(
										"stonesoup_checked_value",
										stonesoup_checked_value);
								short stonesoup_counter = 2;
								Tracer.tracepointVariableShort(
										"stonesoup_counter", stonesoup_counter);
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								int lttngCtr = 99;
								while (stonesoup_counter < 10) {
									ElementImpl.evenfallInductionally
											.println("Loop #"
													+ stonesoup_counter);
									if (stonesoup_counter > 0) {
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										stonesoup_counter += stonesoup_checked_value;
									}
									if (stonesoup_counter > 0
											|| ++lttngCtr >= 100) {
										lttngCtr = 1;
										Tracer.tracepointVariableShort(
												"stonesoup_counter",
												stonesoup_counter);
									}
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								Tracer.tracepointVariableShort(
										"stonesoup_counter", stonesoup_counter);
								ElementImpl.evenfallInductionally
										.println("finished evaluating");
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						ElementImpl.evenfallInductionally.close();
						if (sobriquetical_bacteriologist != null)
							sobriquetical_bacteriologist.stop(true);
					}
				}
			}
		}
		this.actionOnCopy = action;
    }

    public boolean isSearchable() {
        return this.searchable;
    }

}
