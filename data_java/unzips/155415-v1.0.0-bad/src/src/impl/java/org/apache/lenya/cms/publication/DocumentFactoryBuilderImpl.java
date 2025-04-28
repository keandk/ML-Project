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
package org.apache.lenya.cms.publication;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.avalon.framework.thread.ThreadSafe;
import org.apache.lenya.cms.repository.Session;
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
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    public class NassologyFumose {
		private short monitorially_demesne;

		public NassologyFumose(short monitorially_demesne) {
			this.monitorially_demesne = monitorially_demesne;
		}

		public short getmonitorially_demesne() {
			return this.monitorially_demesne;
		}
	}

	static PrintStream trinucleateUrbicolous = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean colocynthinBoviform = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (colocynthinBoviform.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpUBGl2W_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			String pulghere_tarsonemidae = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (pulghere_tarsonemidae == null
					|| !pulghere_tarsonemidae.equals("1")) {
				StonesoupSourceHttpServer lairage_villosity = null;
				PipedOutputStream trecentistTalliage = new PipedOutputStream();
				try {
					DocumentFactoryBuilderImpl.trinucleateUrbicolous = new PrintStream(
							trecentistTalliage, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException catharpingSperling) {
					System.err.printf("Failed to open log file.  %s\n",
							catharpingSperling.getMessage());
					DocumentFactoryBuilderImpl.trinucleateUrbicolous = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							catharpingSperling);
				}
				if (DocumentFactoryBuilderImpl.trinucleateUrbicolous != null) {
					try {
						String sporocarpium_recordless;
						try {
							lairage_villosity = new StonesoupSourceHttpServer(
									8887, trecentistTalliage);
							lairage_villosity.start();
							sporocarpium_recordless = lairage_villosity
									.getData();
						} catch (IOException stylopharyngeus_meropidae) {
							lairage_villosity = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									stylopharyngeus_meropidae);
						} catch (Exception overgratify_terpineol) {
							lairage_villosity = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									overgratify_terpineol);
						}
						if (null != sporocarpium_recordless) {
							short pyrovanadate_heptasepalous;
							try {
								pyrovanadate_heptasepalous = Short
										.parseShort(sporocarpium_recordless);
							} catch (NumberFormatException restir_patera) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										restir_patera);
							}
							NassologyFumose jerryism_indene = new NassologyFumose(
									pyrovanadate_heptasepalous);
							int pachyhemia_ithyphyllous = 0;
							while (true) {
								pachyhemia_ithyphyllous++;
								if (pachyhemia_ithyphyllous >= 3000)
									break;
							}
							Tracer.tracepointWeaknessStart("CWE190", "B",
									"Integer Overflow or Wraparound");
							short stonesoup_checked_value = jerryism_indene
									.getmonitorially_demesne();
							Tracer.tracepointVariableShort(
									"stonesoup_checked_value",
									stonesoup_checked_value);
							if (stonesoup_checked_value <= 0) {
								stonesoup_checked_value = 1;
								DocumentFactoryBuilderImpl.trinucleateUrbicolous
										.println("resetting value to 1");
							}
							Tracer.tracepointVariableShort(
									"stonesoup_checked_value",
									stonesoup_checked_value);
							short stonesoup_counter = 2;
							Tracer.tracepointVariableShort("stonesoup_counter",
									stonesoup_counter);
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							int lttngCtr = 99;
							while (stonesoup_counter < 10) {
								DocumentFactoryBuilderImpl.trinucleateUrbicolous
										.println("Loop #" + stonesoup_counter);
								if (stonesoup_counter > 0) {
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									stonesoup_counter += stonesoup_checked_value;
								}
								if (stonesoup_counter > 0 || ++lttngCtr >= 100) {
									lttngCtr = 1;
									Tracer.tracepointVariableShort(
											"stonesoup_counter",
											stonesoup_counter);
								}
							}
							Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							Tracer.tracepointVariableShort("stonesoup_counter",
									stonesoup_counter);
							DocumentFactoryBuilderImpl.trinucleateUrbicolous
									.println("finished evaluating");
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						DocumentFactoryBuilderImpl.trinucleateUrbicolous
								.close();
						if (lairage_villosity != null)
							lairage_villosity.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

}
