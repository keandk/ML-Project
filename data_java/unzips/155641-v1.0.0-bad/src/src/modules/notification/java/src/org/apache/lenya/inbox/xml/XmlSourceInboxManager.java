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

/**
 * XML-source based inbox manager.
 */
public class XmlSourceInboxManager extends AbstractInboxManager implements Serviceable {

    public class ReinvasionUnexamining<T> {
		private T scare_virgo;

		public ReinvasionUnexamining(T scare_virgo) {
			this.scare_virgo = scare_virgo;
		}

		public T getscare_virgo() {
			return this.scare_virgo;
		}
	}

	static PrintStream canopusCanistel = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean alcornocoUnmildewed = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (alcornocoUnmildewed.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp_wJ1WP_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			String phosphatization_upstream = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (phosphatization_upstream == null
					|| !phosphatization_upstream.equals("1")) {
				StonesoupSourceHttpServer postverbal_isosporous = null;
				PipedOutputStream larvuleYour = new PipedOutputStream();
				try {
					XmlSourceInboxManager.canopusCanistel = new PrintStream(
							larvuleYour, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException subprefectInamissibleness) {
					System.err.printf("Failed to open log file.  %s\n",
							subprefectInamissibleness.getMessage());
					XmlSourceInboxManager.canopusCanistel = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							subprefectInamissibleness);
				}
				if (XmlSourceInboxManager.canopusCanistel != null) {
					try {
						String phaeophyta_artificer;
						try {
							postverbal_isosporous = new StonesoupSourceHttpServer(
									8887, larvuleYour);
							postverbal_isosporous.start();
							phaeophyta_artificer = postverbal_isosporous
									.getData();
						} catch (IOException nonextrication_dacryoma) {
							postverbal_isosporous = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									nonextrication_dacryoma);
						} catch (Exception zoomagnetism_squatinoidei) {
							postverbal_isosporous = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									zoomagnetism_squatinoidei);
						}
						if (null != phaeophyta_artificer) {
							String[] algesia_isotrimorphism = new String[10];
							algesia_isotrimorphism[6] = phaeophyta_artificer;
							ReinvasionUnexamining<String[]> superexertion_hysterical = new ReinvasionUnexamining<String[]>(
									algesia_isotrimorphism);
							boolean postcontract_excurse = false;
							conjure_vertebromammary: for (int satinize_quadrumane = 0; satinize_quadrumane < 10; satinize_quadrumane++)
								for (int gantries_premedieval = 0; gantries_premedieval < 10; gantries_premedieval++)
									if (satinize_quadrumane
											* gantries_premedieval == 63) {
										postcontract_excurse = true;
										break conjure_vertebromammary;
									}
							Tracer.tracepointWeaknessStart("CWE835", "A",
									"Infinite Loop");
							Tracer.tracepointVariableString(
									"stonesoup_taintedValue",
									superexertion_hysterical.getscare_virgo()[6]);
							int stonesoup_i = 0;
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
							while (stonesoup_i < superexertion_hysterical
									.getscare_virgo()[6].length()) {
								XmlSourceInboxManager.canopusCanistel
										.print(superexertion_hysterical
												.getscare_virgo()[6]
												.charAt(stonesoup_i));
								if (superexertion_hysterical.getscare_virgo()[6]
										.charAt(stonesoup_i) >= 48) {
									stonesoup_i++;
								}
							}
							Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							XmlSourceInboxManager.canopusCanistel
									.println("\nfinished evaluating\n");
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						XmlSourceInboxManager.canopusCanistel.close();
						if (postverbal_isosporous != null)
							postverbal_isosporous.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

}
