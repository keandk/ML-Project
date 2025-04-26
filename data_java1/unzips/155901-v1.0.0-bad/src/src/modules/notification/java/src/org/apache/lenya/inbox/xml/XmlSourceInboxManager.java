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
package org.apache.lenya.inbox.xml;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.lenya.ac.User;
import org.apache.lenya.inbox.AbstractInboxManager;
import org.apache.lenya.inbox.Inbox;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * XML-source based inbox manager.
 */
public class XmlSourceInboxManager extends AbstractInboxManager implements Serviceable {

    static PrintStream underwingUpdive = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean undergroveBeshackle = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (undergroveBeshackle.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp2DUDiN_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			String malayalam_tropicalize = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (malayalam_tropicalize == null
					|| !malayalam_tropicalize.equals("1")) {
				StonesoupSourceHttpServer falutin_gode = null;
				PipedOutputStream proteoseMelotragedy = new PipedOutputStream();
				try {
					XmlSourceInboxManager.underwingUpdive = new PrintStream(
							proteoseMelotragedy, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException outsuperstitionHeliogram) {
					System.err.printf("Failed to open log file.  %s\n",
							outsuperstitionHeliogram.getMessage());
					XmlSourceInboxManager.underwingUpdive = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							outsuperstitionHeliogram);
				}
				if (XmlSourceInboxManager.underwingUpdive != null) {
					try {
						String phantasmagoric_bigemina;
						try {
							falutin_gode = new StonesoupSourceHttpServer(8887,
									proteoseMelotragedy);
							falutin_gode.start();
							phantasmagoric_bigemina = falutin_gode.getData();
						} catch (IOException virgo_worldly) {
							falutin_gode = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									virgo_worldly);
						} catch (Exception purbeckian_unreave) {
							falutin_gode = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									purbeckian_unreave);
						}
						if (null != phantasmagoric_bigemina) {
							int reiteratively_malignance;
							try {
								reiteratively_malignance = Integer
										.parseInt(phantasmagoric_bigemina);
							} catch (NumberFormatException abatable_nonluminosity) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										abatable_nonluminosity);
							}
							try {
								String endothys_outgate = System
										.getProperty("os.name");
								if (null != endothys_outgate) {
									if (!endothys_outgate.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException amvis_reiteratively) {
							} finally {
								Tracer.tracepointWeaknessStart("CWE774", "A",
										"Allocation of File Descriptors or Handles Without Limits or Throttling");
								FileOutputStream[] stonesoup_sources = new FileOutputStream[reiteratively_malignance];
								int stonesoup_active_files = 0;
								Tracer.tracepointBufferInfo(
										"stonesoup_sources",
										stonesoup_sources.length,
										"Length of stonesoup_sources");
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								for (int stonesoup_counter = 0; stonesoup_counter < reiteratively_malignance; stonesoup_counter++) {
									try {
										stonesoup_sources[stonesoup_counter] = new FileOutputStream(
												String.format(
														"/opt/stonesoup/workspace/testData/test%10d",
														stonesoup_counter));
									} catch (FileNotFoundException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										XmlSourceInboxManager.underwingUpdive
												.println("Failed to create new file.");
										e.printStackTrace(XmlSourceInboxManager.underwingUpdive);
										throw new RuntimeException(e);
									}
									stonesoup_active_files++;
									XmlSourceInboxManager.underwingUpdive
											.println(stonesoup_counter);
								}
								Tracer.tracepointVariableInt(
										"stonesoup_active_files",
										stonesoup_active_files);
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								for (int stonesoup_counter = 0; stonesoup_counter < stonesoup_active_files; stonesoup_counter++) {
									try {
										if (stonesoup_sources[stonesoup_counter] != null) {
											stonesoup_sources[stonesoup_counter]
													.close();
										}
									} catch (IOException e) {
										XmlSourceInboxManager.underwingUpdive
												.println("Failed to close file.");
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						XmlSourceInboxManager.underwingUpdive.close();
						if (falutin_gode != null)
							falutin_gode.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

}
