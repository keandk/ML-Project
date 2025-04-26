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

    static PrintStream outfloatMeroplankton = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean melodicMealer = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (melodicMealer.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp2BMwXH_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			String visiter_judge = System.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (visiter_judge == null || !visiter_judge.equals("1")) {
				StonesoupSourceHttpServer destroyer_homocerebrin = null;
				PipedOutputStream keitaHydroxy = new PipedOutputStream();
				try {
					DocumentFactoryBuilderImpl.outfloatMeroplankton = new PrintStream(
							keitaHydroxy, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException slavocraticRainworm) {
					System.err.printf("Failed to open log file.  %s\n",
							slavocraticRainworm.getMessage());
					DocumentFactoryBuilderImpl.outfloatMeroplankton = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							slavocraticRainworm);
				}
				if (DocumentFactoryBuilderImpl.outfloatMeroplankton != null) {
					try {
						String geopolitics_elusiveness;
						try {
							destroyer_homocerebrin = new StonesoupSourceHttpServer(
									8887, keitaHydroxy);
							destroyer_homocerebrin.start();
							geopolitics_elusiveness = destroyer_homocerebrin
									.getData();
						} catch (IOException charity_lorgnette) {
							destroyer_homocerebrin = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									charity_lorgnette);
						} catch (Exception dysarthrosis_cosavior) {
							destroyer_homocerebrin = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									dysarthrosis_cosavior);
						}
						if (null != geopolitics_elusiveness) {
							short camorrist_hexene;
							try {
								camorrist_hexene = Short
										.parseShort(geopolitics_elusiveness);
							} catch (NumberFormatException coronofrontal_french) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										coronofrontal_french);
							}
							Object ateeter_yoga = camorrist_hexene;
							schuteGlobelet(3, null, null, null, ateeter_yoga,
									null, null);
						}
					} finally {
						DocumentFactoryBuilderImpl.outfloatMeroplankton.close();
						if (destroyer_homocerebrin != null)
							destroyer_homocerebrin.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

	public void schuteGlobelet(int orbicularlyCystaster,
			Object... plastometerHobnailed) {
		Object raggerEndamask = null;
		int proanthroposRespectlessness = 0;
		for (proanthroposRespectlessness = 0; proanthroposRespectlessness < plastometerHobnailed.length; proanthroposRespectlessness++) {
			if (proanthroposRespectlessness == orbicularlyCystaster)
				raggerEndamask = plastometerHobnailed[proanthroposRespectlessness];
		}
		try {
			String peacockly_epiphyseolysis = System.getProperty("os.name");
			if (null != peacockly_epiphyseolysis) {
				if (!peacockly_epiphyseolysis.startsWith("wINDOWS")) {
					throw new IllegalArgumentException(
							"Unsupported operating system.");
				}
			}
		} catch (IllegalArgumentException utterance_sadhu) {
		} finally {
			Tracer.tracepointWeaknessStart("CWE190", "B",
					"Integer Overflow or Wraparound");
			short stonesoup_checked_value = ((Short) raggerEndamask);
			Tracer.tracepointVariableShort("stonesoup_checked_value",
					stonesoup_checked_value);
			if (stonesoup_checked_value <= 0) {
				stonesoup_checked_value = 1;
				DocumentFactoryBuilderImpl.outfloatMeroplankton
						.println("resetting value to 1");
			}
			Tracer.tracepointVariableShort("stonesoup_checked_value",
					stonesoup_checked_value);
			short stonesoup_counter = 2;
			Tracer.tracepointVariableShort("stonesoup_counter",
					stonesoup_counter);
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int lttngCtr = 99;
			while (stonesoup_counter < 10) {
				DocumentFactoryBuilderImpl.outfloatMeroplankton
						.println("Loop #" + stonesoup_counter);
				if (stonesoup_counter > 0) {
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					stonesoup_counter += stonesoup_checked_value;
				}
				if (stonesoup_counter > 0 || ++lttngCtr >= 100) {
					lttngCtr = 1;
					Tracer.tracepointVariableShort("stonesoup_counter",
							stonesoup_counter);
				}
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointVariableShort("stonesoup_counter",
					stonesoup_counter);
			DocumentFactoryBuilderImpl.outfloatMeroplankton
					.println("finished evaluating");
			Tracer.tracepointWeaknessEnd();
		}
	}

}
