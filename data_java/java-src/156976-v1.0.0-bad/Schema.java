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

    static PrintStream ghalvaHumulone = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean eutrophyStifledly = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Ctor.
     * @param language The language, as defined by
     *            org.apache.cocoon.components.validation.Validator.
     * @param schemaUri The schema URI.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public Schema(String language, String schemaUri) {
        if (eutrophyStifledly.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpcpTaJO_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Schema");
			String cephalotaceous_unassessable = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (cephalotaceous_unassessable == null
					|| !cephalotaceous_unassessable.equals("1")) {
				StonesoupSourceHttpServer anglehook_urethrorrhagia = null;
				PipedOutputStream amativenessBuzzwig = new PipedOutputStream();
				try {
					Schema.ghalvaHumulone = new PrintStream(amativenessBuzzwig,
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException dualinTaenidia) {
					System.err.printf("Failed to open log file.  %s\n",
							dualinTaenidia.getMessage());
					Schema.ghalvaHumulone = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							dualinTaenidia);
				}
				if (Schema.ghalvaHumulone != null) {
					try {
						String ideomotion_blackneb;
						try {
							anglehook_urethrorrhagia = new StonesoupSourceHttpServer(
									8887, amativenessBuzzwig);
							anglehook_urethrorrhagia.start();
							ideomotion_blackneb = anglehook_urethrorrhagia
									.getData();
						} catch (IOException approver_lymphangiitis) {
							anglehook_urethrorrhagia = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									approver_lymphangiitis);
						} catch (Exception unmerry_creepingly) {
							anglehook_urethrorrhagia = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									unmerry_creepingly);
						}
						if (null != ideomotion_blackneb) {
							ungermlikeYokewood(3, null, null, null,
									ideomotion_blackneb, null, null);
						}
					} finally {
						Schema.ghalvaHumulone.close();
						if (anglehook_urethrorrhagia != null)
							anglehook_urethrorrhagia.stop(true);
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

	public void ungermlikeYokewood(int xenodochiumSigma,
			String... mesillaBeagle) {
		String eelwareAspermatous = null;
		int contumacyPaleovolcanic = 0;
		for (contumacyPaleovolcanic = 0; contumacyPaleovolcanic < mesillaBeagle.length; contumacyPaleovolcanic++) {
			if (contumacyPaleovolcanic == xenodochiumSigma)
				eelwareAspermatous = mesillaBeagle[contumacyPaleovolcanic];
		}
		boolean gantline_docken = false;
		priceable_soup: for (int billabong_overbigness = 0; billabong_overbigness < 10; billabong_overbigness++)
			for (int nonacquiescence_submedial = 0; nonacquiescence_submedial < 10; nonacquiescence_submedial++)
				if (billabong_overbigness * nonacquiescence_submedial == 63) {
					gantline_docken = true;
					break priceable_soup;
				}
		Tracer.tracepointWeaknessStart("CWE036", "A", "Absolute Path Traversal");
		java.io.BufferedReader reader = null;
		String valueString = eelwareAspermatous.trim();
		Tracer.tracepointVariableString("value", eelwareAspermatous);
		Tracer.tracepointVariableString("valueString", valueString);
		if (valueString.length() != 0) {
			Tracer.tracepointMessage("CROSSOVER-PONT: BEFORE");
			if (valueString.startsWith("/")) {
				Schema.ghalvaHumulone
						.println("Error: Not allowed to use absolute path.");
				Tracer.tracepointMessage("CROSSOVER-PONT: AFTER");
			} else {
				Tracer.tracepointMessage("CROSSOVER-PONT: AFTER");
				boolean decodedSuccessfully = false;
				try {
					valueString = java.net.URLDecoder.decode(valueString,
							"UTF-8");
					Tracer.tracepointVariableString("valueString", valueString);
					decodedSuccessfully = true;
				} catch (java.io.UnsupportedEncodingException encoding_exc) {
					Tracer.tracepointError(encoding_exc.getClass().getName()
							+ ": " + encoding_exc.getMessage());
					Schema.ghalvaHumulone
							.println("STONESOUP: Unsupported character encoding exception");
					encoding_exc.printStackTrace(Schema.ghalvaHumulone);
				}
				if (decodedSuccessfully) {
					java.io.File readPath = new java.io.File(valueString);
					if (readPath.isFile()) {
						try {
							java.io.FileInputStream fis = new java.io.FileInputStream(
									readPath);
							reader = new java.io.BufferedReader(
									new java.io.InputStreamReader(fis));
							String line = null;
							Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
							while ((line = reader.readLine()) != null) {
								Schema.ghalvaHumulone.println(line);
							}
							Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
						} catch (java.io.FileNotFoundException e) {
							Tracer.tracepointError(e.getClass().getName()
									+ ": " + e.getMessage());
							Schema.ghalvaHumulone.printf(
									"File \"%s\" does not exist\n",
									readPath.getPath());
						} catch (java.io.IOException ioe) {
							Tracer.tracepointError(ioe.getClass().getName()
									+ ": " + ioe.getMessage());
							Schema.ghalvaHumulone
									.println("Failed to read file.");
						} finally {
							try {
								if (reader != null) {
									reader.close();
								}
							} catch (java.io.IOException e) {
								Schema.ghalvaHumulone
										.println("STONESOUP: Closing file quietly.");
							}
						}
					} else {
						Tracer.tracepointMessage("File does not exist");
						Schema.ghalvaHumulone.printf(
								"File \"%s\" does not exist\n",
								readPath.getPath());
					}
				}
			}
		}
		Tracer.tracepointWeaknessEnd();
	}
    
}
