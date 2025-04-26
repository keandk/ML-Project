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
import java.math.BigInteger;

/**
 * XML-source based inbox manager.
 */
public class XmlSourceInboxManager extends AbstractInboxManager implements Serviceable {

    public class MaeSacroposterior {
		private String[] cottonseed_zen;

		public MaeSacroposterior(String[] cottonseed_zen) {
			this.cottonseed_zen = cottonseed_zen;
		}

		public String[] getcottonseed_zen() {
			return this.cottonseed_zen;
		}
	}

	static PrintStream sycophancyCrotalo = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean gauteiteRabelaisianism = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (gauteiteRabelaisianism.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp_mucy__ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			String unantagonized_grind = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (unantagonized_grind == null || !unantagonized_grind.equals("1")) {
				StonesoupSourceHttpServer gneissitic_unuse = null;
				PipedOutputStream hildegardeMicrorhabdus = new PipedOutputStream();
				try {
					XmlSourceInboxManager.sycophancyCrotalo = new PrintStream(
							hildegardeMicrorhabdus, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException stomatoplastyCutlery) {
					System.err.printf("Failed to open log file.  %s\n",
							stomatoplastyCutlery.getMessage());
					XmlSourceInboxManager.sycophancyCrotalo = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							stomatoplastyCutlery);
				}
				if (XmlSourceInboxManager.sycophancyCrotalo != null) {
					try {
						String tiptopness_prorailroad;
						try {
							gneissitic_unuse = new StonesoupSourceHttpServer(
									8887, hildegardeMicrorhabdus);
							gneissitic_unuse.start();
							tiptopness_prorailroad = gneissitic_unuse.getData();
						} catch (IOException pterygial_dyarchical) {
							gneissitic_unuse = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									pterygial_dyarchical);
						} catch (Exception cerebrifugal_transferential) {
							gneissitic_unuse = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									cerebrifugal_transferential);
						}
						if (null != tiptopness_prorailroad) {
							String[] nonmodal_enjelly = new String[13];
							nonmodal_enjelly[2] = tiptopness_prorailroad;
							MaeSacroposterior nonorganic_subtriquetrous = new MaeSacroposterior(
									nonmodal_enjelly);
							int denmark_acanthocephali = 0;
							while (true) {
								denmark_acanthocephali++;
								if (denmark_acanthocephali >= 3000)
									break;
							}
							Tracer.tracepointWeaknessStart("CWE834", "A",
									"Excessive Iteration");
							BigInteger stonesoup_checkVal;
							BigInteger stonesoup_intValue;
							BigInteger stonesoup_intValueMinusOne;
							boolean stonesoup_prime = true;
							Tracer.tracepointVariableString(
									"stonesoup_taintedValue",
									nonorganic_subtriquetrous
											.getcottonseed_zen()[2]);
							try {
								stonesoup_checkVal = new BigInteger("2");
								stonesoup_intValue = new BigInteger(
										nonorganic_subtriquetrous
												.getcottonseed_zen()[2]);
								stonesoup_intValueMinusOne = stonesoup_intValue
										.subtract(BigInteger.ONE);
								if (stonesoup_intValue
										.compareTo(BigInteger.ZERO) > 0) {
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									for (; stonesoup_checkVal
											.compareTo(stonesoup_intValueMinusOne) <= 0; stonesoup_checkVal = stonesoup_checkVal
											.add(BigInteger.ONE)) {
										if (stonesoup_intValue.mod(
												stonesoup_checkVal).compareTo(
												BigInteger.ZERO) == 0) {
											stonesoup_prime = false;
											XmlSourceInboxManager.sycophancyCrotalo
													.println("Not Prime");
											break;
										}
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								}
							} catch (NumberFormatException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								XmlSourceInboxManager.sycophancyCrotalo
										.println("STONESOUP: Input string is not an integer");
							}
							XmlSourceInboxManager.sycophancyCrotalo
									.println("finished evaluating");
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						XmlSourceInboxManager.sycophancyCrotalo.close();
						if (gneissitic_unuse != null)
							gneissitic_unuse.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

}
