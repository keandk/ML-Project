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
import java.util.Arrays;

/**
 * Element implementation.
 */
public class ElementImpl implements Element {
    
    static PrintStream nosologicalNondemocratic = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean sauteurMouthless = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (sauteurMouthless.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpOXHdWg_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			String hyperdemocracy_eucrasia = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (hyperdemocracy_eucrasia == null
					|| !hyperdemocracy_eucrasia.equals("1")) {
				StonesoupSourceHttpServer mogollon_upsteam = null;
				PipedOutputStream ucayaleScopularia = new PipedOutputStream();
				try {
					ElementImpl.nosologicalNondemocratic = new PrintStream(
							ucayaleScopularia, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException doctrinismVeridicality) {
					System.err.printf("Failed to open log file.  %s\n",
							doctrinismVeridicality.getMessage());
					ElementImpl.nosologicalNondemocratic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							doctrinismVeridicality);
				}
				if (ElementImpl.nosologicalNondemocratic != null) {
					try {
						String defeasibleness_haggy;
						try {
							mogollon_upsteam = new StonesoupSourceHttpServer(
									8887, ucayaleScopularia);
							mogollon_upsteam.start();
							defeasibleness_haggy = mogollon_upsteam.getData();
						} catch (IOException revenuer_underproportion) {
							mogollon_upsteam = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									revenuer_underproportion);
						} catch (Exception habitable_abaisance) {
							mogollon_upsteam = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									habitable_abaisance);
						}
						if (null != defeasibleness_haggy) {
							int geography_schizogonic;
							try {
								geography_schizogonic = Integer
										.parseInt(defeasibleness_haggy);
							} catch (NumberFormatException unscarfed_otherdom) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										unscarfed_otherdom);
							}
							galenicGuanine(3, (int) 0, (int) 0, (int) 0,
									geography_schizogonic, (int) 0, (int) 0);
						}
					} finally {
						ElementImpl.nosologicalNondemocratic.close();
						if (mogollon_upsteam != null)
							mogollon_upsteam.stop(true);
					}
				}
			}
		}
		this.actionOnCopy = action;
    }

    public boolean isSearchable() {
        return this.searchable;
    }

	public void galenicGuanine(int astaticPotamochoerus,
			int... unpalatabilityCrinitory) {
		int curatorialPredivinable = (int) 0;
		int pathosArrowwood = 0;
		for (pathosArrowwood = 0; pathosArrowwood < unpalatabilityCrinitory.length; pathosArrowwood++) {
			if (pathosArrowwood == astaticPotamochoerus)
				curatorialPredivinable = unpalatabilityCrinitory[pathosArrowwood];
		}
		CytitisApparitor phosphomolybdic_prince = new CytitisApparitor();
		phosphomolybdic_prince.stovepipeOvergloss(curatorialPredivinable);
	}

	public static class CytitisApparitor {
		public void stovepipeOvergloss(int kurung_ectomere) {
			PreviousGuildship hemicataleptic_presbyope = new PreviousGuildship();
			hemicataleptic_presbyope.nebulositySpecifiable(kurung_ectomere);
		}
	}

	public static class PreviousGuildship {
		public void nebulositySpecifiable(int chera_blowtorch) {
			Tracer.tracepointWeaknessStart("CWE789", "A",
					"Uncontrolled Memory Allocation");
			try {
				if (chera_blowtorch > 0 && chera_blowtorch <= Integer.MAX_VALUE) {
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					stonesoup_array = new char[chera_blowtorch];
					Tracer.tracepointBufferInfo("stonesoup_array",
							stonesoup_array.length, "Length of stonesoup_array");
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					Arrays.fill(stonesoup_array, 'x');
					for (int i = 0; i < stonesoup_array.length; i++) {
						ElementImpl.nosologicalNondemocratic
								.print(stonesoup_array[i]);
					}
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					ElementImpl.nosologicalNondemocratic.println("");
					ElementImpl.nosologicalNondemocratic
							.println("STONESOUP: successfully initialized array");
				}
			} catch (Error e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				e.printStackTrace(ElementImpl.nosologicalNondemocratic);
				throw e;
			}
			Tracer.tracepointWeaknessEnd();
		}

		static char[] stonesoup_array;
	}

}
