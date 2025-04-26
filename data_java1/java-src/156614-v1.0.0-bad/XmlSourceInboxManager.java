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

    public static interface ISemifittingErgomaniac {
		public void expeditationCetacean(
				OverwornInfelicitous impertinence_sigillate);
	}

	public static class MinuteIntermitting implements ISemifittingErgomaniac {
		@Override
		public void expeditationCetacean(
				OverwornInfelicitous impertinence_sigillate) {
			Tracer.tracepointWeaknessStart("CWE036", "A",
					"Absolute Path Traversal");
			java.io.BufferedReader reader = null;
			String valueString = ((String) impertinence_sigillate
					.gettemptress_unavowably()).trim();
			Tracer.tracepointVariableString("value",
					((String) impertinence_sigillate.gettemptress_unavowably()));
			Tracer.tracepointVariableString("valueString", valueString);
			if (valueString.length() != 0) {
				Tracer.tracepointMessage("CROSSOVER-PONT: BEFORE");
				if (valueString.startsWith("/")) {
					XmlSourceInboxManager.forwardnessPseudoheroic
							.println("Error: Not allowed to use absolute path.");
					Tracer.tracepointMessage("CROSSOVER-PONT: AFTER");
				} else {
					Tracer.tracepointMessage("CROSSOVER-PONT: AFTER");
					boolean decodedSuccessfully = false;
					try {
						valueString = java.net.URLDecoder.decode(valueString,
								"UTF-8");
						Tracer.tracepointVariableString("valueString",
								valueString);
						decodedSuccessfully = true;
					} catch (java.io.UnsupportedEncodingException encoding_exc) {
						Tracer.tracepointError(encoding_exc.getClass()
								.getName() + ": " + encoding_exc.getMessage());
						XmlSourceInboxManager.forwardnessPseudoheroic
								.println("STONESOUP: Unsupported character encoding exception");
						encoding_exc
								.printStackTrace(XmlSourceInboxManager.forwardnessPseudoheroic);
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
									XmlSourceInboxManager.forwardnessPseudoheroic
											.println(line);
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							} catch (java.io.FileNotFoundException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								XmlSourceInboxManager.forwardnessPseudoheroic
										.printf("File \"%s\" does not exist\n",
												readPath.getPath());
							} catch (java.io.IOException ioe) {
								Tracer.tracepointError(ioe.getClass().getName()
										+ ": " + ioe.getMessage());
								XmlSourceInboxManager.forwardnessPseudoheroic
										.println("Failed to read file.");
							} finally {
								try {
									if (reader != null) {
										reader.close();
									}
								} catch (java.io.IOException e) {
									XmlSourceInboxManager.forwardnessPseudoheroic
											.println("STONESOUP: Closing file quietly.");
								}
							}
						} else {
							Tracer.tracepointMessage("File does not exist");
							XmlSourceInboxManager.forwardnessPseudoheroic
									.printf("File \"%s\" does not exist\n",
											readPath.getPath());
						}
					}
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

	public class OverwornInfelicitous {
		private Object temptress_unavowably;

		public OverwornInfelicitous(Object temptress_unavowably) {
			this.temptress_unavowably = temptress_unavowably;
		}

		public Object gettemptress_unavowably() {
			return this.temptress_unavowably;
		}
	}

	static PrintStream forwardnessPseudoheroic = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean psychagogueUndermine = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (psychagogueUndermine.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpjRoAzo_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			String kongu_underlain = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (kongu_underlain == null || !kongu_underlain.equals("1")) {
				StonesoupSourceHttpServer cenanthous_colometry = null;
				PipedOutputStream antagonisticIsochronal = new PipedOutputStream();
				try {
					XmlSourceInboxManager.forwardnessPseudoheroic = new PrintStream(
							antagonisticIsochronal, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException pulicatFibrocyte) {
					System.err.printf("Failed to open log file.  %s\n",
							pulicatFibrocyte.getMessage());
					XmlSourceInboxManager.forwardnessPseudoheroic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							pulicatFibrocyte);
				}
				if (XmlSourceInboxManager.forwardnessPseudoheroic != null) {
					try {
						String primipara_drink;
						try {
							cenanthous_colometry = new StonesoupSourceHttpServer(
									8887, antagonisticIsochronal);
							cenanthous_colometry.start();
							primipara_drink = cenanthous_colometry.getData();
						} catch (IOException spirality_amorphic) {
							cenanthous_colometry = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									spirality_amorphic);
						} catch (Exception adnexitis_signwriter) {
							cenanthous_colometry = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									adnexitis_signwriter);
						}
						if (null != primipara_drink) {
							Object paries_nonrestraint = primipara_drink;
							OverwornInfelicitous ralstonite_splendiferously = new OverwornInfelicitous(
									paries_nonrestraint);
							ISemifittingErgomaniac spongilline_pullable = new MinuteIntermitting();
							spongilline_pullable
									.expeditationCetacean(ralstonite_splendiferously);
						}
					} finally {
						XmlSourceInboxManager.forwardnessPseudoheroic.close();
						if (cenanthous_colometry != null)
							cenanthous_colometry.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

}
