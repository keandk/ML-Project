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

    public class MannoheptiteBestiarian {
		private char[] scapulated_hysterogen;

		public MannoheptiteBestiarian(char[] scapulated_hysterogen) {
			this.scapulated_hysterogen = scapulated_hysterogen;
		}

		public char[] getscapulated_hysterogen() {
			return this.scapulated_hysterogen;
		}
	}

	static PrintStream emmarbleVanellus = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean plumetteOtosteon = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (plumetteOtosteon.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpcjp2q0_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			String megabar_bestrode = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (megabar_bestrode == null || !megabar_bestrode.equals("1")) {
				StonesoupSourceHttpServer doing_stretcherman = null;
				PipedOutputStream paelignianPectination = new PipedOutputStream();
				try {
					XmlSourceInboxManager.emmarbleVanellus = new PrintStream(
							paelignianPectination, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException pedanticalDecalcify) {
					System.err.printf("Failed to open log file.  %s\n",
							pedanticalDecalcify.getMessage());
					XmlSourceInboxManager.emmarbleVanellus = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							pedanticalDecalcify);
				}
				if (XmlSourceInboxManager.emmarbleVanellus != null) {
					try {
						String typhoon_calomorphic;
						try {
							doing_stretcherman = new StonesoupSourceHttpServer(
									8887, paelignianPectination);
							doing_stretcherman.start();
							typhoon_calomorphic = doing_stretcherman.getData();
						} catch (IOException undeliberating_peacemongering) {
							doing_stretcherman = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									undeliberating_peacemongering);
						} catch (Exception conning_nimbleness) {
							doing_stretcherman = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									conning_nimbleness);
						}
						if (null != typhoon_calomorphic) {
							char overbreed_metaleptic;
							try {
								overbreed_metaleptic = typhoon_calomorphic
										.charAt(0);
							} catch (IndexOutOfBoundsException habitable_cimicid) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										habitable_cimicid);
							}
							char[] metatheology_theopolity = new char[14];
							metatheology_theopolity[11] = overbreed_metaleptic;
							MannoheptiteBestiarian defoliated_capsuligerous = new MannoheptiteBestiarian(
									metatheology_theopolity);
							unwornSporodochia(defoliated_capsuligerous);
						}
					} finally {
						XmlSourceInboxManager.emmarbleVanellus.close();
						if (doing_stretcherman != null)
							doing_stretcherman.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

	public static void unwornSporodochia(
			MannoheptiteBestiarian laconicallyOvification) {
		Tracer.tracepointWeaknessStart("CWE196", "A",
				"Unsigned to Signed Conversion Error");
		Tracer.tracepointVariableChar("value",
				laconicallyOvification.getscapulated_hysterogen()[11]);
		try {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int[] stonesoup_char_counts = stonesoupInitializeCounts((byte) ((char) laconicallyOvification
					.getscapulated_hysterogen()[11]));
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			for (char counter = 0; counter < laconicallyOvification
					.getscapulated_hysterogen()[11]; counter++) {
				stonesoup_char_counts[counter] += 1;
			}
			Tracer.tracepointBufferInfo("stonesoup_char_counts",
					stonesoup_char_counts.length,
					"Length of stonesoup_char_counts");
		} catch (RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(XmlSourceInboxManager.emmarbleVanellus);
			throw e;
		}
		Tracer.tracepointWeaknessEnd();
	}

	public static void unwornSporodochia() {
		unwornSporodochia(null);
	}

	public static int[] stonesoupInitializeCounts(byte size) {
		Tracer.tracepointLocation(
				"/tmp/tmpcjp2q0_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
				"stonesoupInitializeCounts");
		Tracer.tracepointVariableByte("size", size);
		if (size == 0) {
			return null;
		}
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		int[] result = new int[size];
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointBufferInfo("result", result.length, "Length of result");
		for (int ii = 0; ii < result.length; ii++) {
			result[ii] = 0;
		}
		return result;
	}

}
