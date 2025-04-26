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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;

/**
 * Element implementation.
 */
public class ElementImpl implements Element {
    
    public class UnscrawledAutoactivation {
		private int[] habilitate_slapdashery;

		public UnscrawledAutoactivation(int[] habilitate_slapdashery) {
			this.habilitate_slapdashery = habilitate_slapdashery;
		}

		public int[] gethabilitate_slapdashery() {
			return this.habilitate_slapdashery;
		}
	}

	static PrintStream validationQuattrini = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean undermineGrievously = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (undermineGrievously.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpsXUFnP_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			String aglossate_shinto = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (aglossate_shinto == null || !aglossate_shinto.equals("1")) {
				StonesoupSourceHttpServer commerce_claybank = null;
				PipedOutputStream carpocarpalAcanthopteran = new PipedOutputStream();
				try {
					ElementImpl.validationQuattrini = new PrintStream(
							carpocarpalAcanthopteran, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException masturbationGanda) {
					System.err.printf("Failed to open log file.  %s\n",
							masturbationGanda.getMessage());
					ElementImpl.validationQuattrini = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							masturbationGanda);
				}
				if (ElementImpl.validationQuattrini != null) {
					try {
						String leptochroa_retrofracted;
						try {
							commerce_claybank = new StonesoupSourceHttpServer(
									8887, carpocarpalAcanthopteran);
							commerce_claybank.start();
							leptochroa_retrofracted = commerce_claybank
									.getData();
						} catch (IOException cataleptic_sustainer) {
							commerce_claybank = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									cataleptic_sustainer);
						} catch (Exception gaussage_benzhydrol) {
							commerce_claybank = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									gaussage_benzhydrol);
						}
						if (null != leptochroa_retrofracted) {
							int scleroticectomy_micromeritics;
							try {
								scleroticectomy_micromeritics = Integer
										.parseInt(leptochroa_retrofracted);
							} catch (NumberFormatException pedodontia_duralumin) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										pedodontia_duralumin);
							}
							int[] mira_izcateco = new int[12];
							mira_izcateco[0] = scleroticectomy_micromeritics;
							UnscrawledAutoactivation overhandicap_erotopath = new UnscrawledAutoactivation(
									mira_izcateco);
							try {
								String akcheh_outfish = System
										.getProperty("os.name");
								if (null != akcheh_outfish) {
									if (!akcheh_outfish.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException nonriding_ametabolism) {
							} finally {
								Tracer.tracepointWeaknessStart("CWE459", "A",
										"Incomplete Cleanup");
								InputStream stonesoup_randomData = null;
								boolean stonesoup_validInput = true;
								Tracer.tracepointVariableInt(
										"stonesoup_intValue",
										overhandicap_erotopath
												.gethabilitate_slapdashery()[0]);
								byte[] stonesoup_randomChars = null;
								try {
									ElementImpl.validationQuattrini
											.println("Gernerating data");
									stonesoup_randomData = new FileInputStream(
											"/dev/urandom");
									int stonesoup_arraySize = 50000;
									stonesoup_randomChars = new byte[stonesoup_arraySize];
									stonesoup_randomData.read(
											stonesoup_randomChars, 0,
											stonesoup_arraySize);
								} catch (FileNotFoundException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									ElementImpl.validationQuattrini
											.println("Error: /dev/urandom not found");
									stonesoup_validInput = false;
								} catch (IOException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									ElementImpl.validationQuattrini
											.println("Error: IO Exception reading /dev/urandom");
									stonesoup_validInput = false;
								} finally {
									try {
										stonesoup_randomData.close();
									} catch (IOException e) {
										ElementImpl.validationQuattrini
												.println("Error: Cannot close /dev/urandom");
										stonesoup_validInput = false;
									}
								}
								if (stonesoup_validInput) {
									int stonesoup_numFilePaths = 50;
									File[] stonesoup_filePaths = new File[stonesoup_numFilePaths];
									int stonesoup_i = 0;
									OutputStream stonesoup_outputStream = null;
									try {
										ElementImpl.validationQuattrini
												.println("Saving data");
										for (stonesoup_i = 0; stonesoup_i < overhandicap_erotopath
												.gethabilitate_slapdashery()[0]; stonesoup_i++) {
											stonesoup_filePaths[stonesoup_i
													% stonesoup_numFilePaths] = File
													.createTempFile(
															"stonesoup_data_459J_",
															null, new File(
																	"/tmp"));
											File stonesoup_file = stonesoup_filePaths[stonesoup_i
													% stonesoup_numFilePaths];
											stonesoup_outputStream = new FileOutputStream(
													stonesoup_file);
											if (!stonesoup_file.exists()) {
												stonesoup_file.createNewFile();
											}
											stonesoup_outputStream
													.write(stonesoup_randomChars);
											stonesoup_outputStream.close();
											stonesoup_outputStream = null;
										}
										Tracer.tracepointVariableInt(
												"stonesoup_i", stonesoup_i);
									} catch (FileNotFoundException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										ElementImpl.validationQuattrini
												.println("Error: tmp file  not found");
									} catch (IOException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										ElementImpl.validationQuattrini
												.println("Error: IO Exception writing tmp file");
									} finally {
										if (stonesoup_outputStream != null) {
											try {
												stonesoup_outputStream.close();
											} catch (IOException e) {
												ElementImpl.validationQuattrini
														.println("Error: could not delete output stream");
											}
										}
										Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										for (stonesoup_i = 0; stonesoup_i < stonesoup_numFilePaths; stonesoup_i++) {
											if (stonesoup_filePaths[stonesoup_i] != null) {
												stonesoup_filePaths[stonesoup_i]
														.delete();
											}
										}
										Tracer.tracepointVariableInt(
												"stonesoup_i", stonesoup_i);
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						ElementImpl.validationQuattrini.close();
						if (commerce_claybank != null)
							commerce_claybank.stop(true);
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
