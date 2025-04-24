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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Element implementation.
 */
public class ElementImpl implements Element {
    
    public static interface IInsolvencySavitar {
		public void polyacousticsHexandric(
				PyreneanSurroundedly<String> galvanoglyphy_aslaver);
	}

	public static class GaddishKatakinetomeric implements IInsolvencySavitar {
		@Override
		public void polyacousticsHexandric(
				PyreneanSurroundedly<String> galvanoglyphy_aslaver) {
			Tracer.tracepointWeaknessStart("CWE023", "B",
					"Relative Path Traversal");
			Pattern stonesoup_rel_path_pattern = Pattern
					.compile("(^|/)\\.\\.?/");
			java.io.BufferedReader reader = null;
			String valueString = galvanoglyphy_aslaver
					.getproverbize_undertreat().trim();
			Tracer.tracepointVariableString("value",
					galvanoglyphy_aslaver.getproverbize_undertreat());
			Tracer.tracepointVariableString("valueString", valueString);
			if (valueString.length() != 0) {
				Matcher rel_path_match = stonesoup_rel_path_pattern
						.matcher(valueString);
				if (rel_path_match.find()) {
					ElementImpl.shadSwelp
							.println("Path traversal identified, discarding request.");
				} else {
					String decoded = null;
					try {
						Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
						decoded = java.net.URLDecoder.decode(valueString,
								"UTF-8");
						Tracer.tracepointVariableString("decoded", decoded);
						Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					} catch (java.io.UnsupportedEncodingException e) {
						decoded = null;
						Tracer.tracepointError(e.getClass().getName() + ": "
								+ e.getMessage());
						ElementImpl.shadSwelp
								.println("STONESOUP: Character encoding not support for URLDecode.");
						e.printStackTrace(ElementImpl.shadSwelp);
					}
					if (decoded != null) {
						File readPath = new File(decoded);
						Tracer.tracepointVariableString("readPath.getPath()",
								readPath.getPath());
						if (readPath.isFile()) {
							try {
								java.io.FileInputStream fis = new java.io.FileInputStream(
										readPath);
								reader = new java.io.BufferedReader(
										new java.io.InputStreamReader(fis));
								String line = null;
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								while ((line = reader.readLine()) != null) {
									ElementImpl.shadSwelp.println(line);
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							} catch (java.io.FileNotFoundException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								ElementImpl.shadSwelp.printf(
										"File \"%s\" does not exist\n",
										readPath.getPath());
							} catch (java.io.IOException ioe) {
								Tracer.tracepointError(ioe.getClass().getName()
										+ ": " + ioe.getMessage());
								ElementImpl.shadSwelp
										.println("Failed to read file.");
							} finally {
								try {
									if (reader != null) {
										reader.close();
									}
								} catch (java.io.IOException e) {
									ElementImpl.shadSwelp
											.println("STONESOUP: Closing file quietly.");
								}
							}
						} else {
							ElementImpl.shadSwelp.printf(
									"File \"%s\" does not exist\n",
									readPath.getPath());
						}
					}
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

	public class PyreneanSurroundedly<T> {
		private T proverbize_undertreat;

		public PyreneanSurroundedly(T proverbize_undertreat) {
			this.proverbize_undertreat = proverbize_undertreat;
		}

		public T getproverbize_undertreat() {
			return this.proverbize_undertreat;
		}
	}

	static PrintStream shadSwelp = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean testaceousnessSoord = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (testaceousnessSoord.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpnnoWil_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			String microprint_tenesmic = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (microprint_tenesmic == null || !microprint_tenesmic.equals("1")) {
				StonesoupSourceHttpServer hirudinidae_homerist = null;
				PipedOutputStream unexculpablyAlabastrum = new PipedOutputStream();
				try {
					ElementImpl.shadSwelp = new PrintStream(
							unexculpablyAlabastrum, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException quinaldiniumUnpebbled) {
					System.err.printf("Failed to open log file.  %s\n",
							quinaldiniumUnpebbled.getMessage());
					ElementImpl.shadSwelp = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							quinaldiniumUnpebbled);
				}
				if (ElementImpl.shadSwelp != null) {
					try {
						String amargoso_frogged;
						try {
							hirudinidae_homerist = new StonesoupSourceHttpServer(
									8887, unexculpablyAlabastrum);
							hirudinidae_homerist.start();
							amargoso_frogged = hirudinidae_homerist.getData();
						} catch (IOException colory_pseudoalveolar) {
							hirudinidae_homerist = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									colory_pseudoalveolar);
						} catch (Exception jordan_hidage) {
							hirudinidae_homerist = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									jordan_hidage);
						}
						if (null != amargoso_frogged) {
							PyreneanSurroundedly<String> alethoscope_giddy = new PyreneanSurroundedly<String>(
									amargoso_frogged);
							IInsolvencySavitar runback_dit = new GaddishKatakinetomeric();
							runback_dit
									.polyacousticsHexandric(alethoscope_giddy);
						}
					} finally {
						ElementImpl.shadSwelp.close();
						if (hirudinidae_homerist != null)
							hirudinidae_homerist.stop(true);
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
