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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * XML-source based inbox manager.
 */
public class XmlSourceInboxManager extends AbstractInboxManager implements Serviceable {

    public static interface IPansexualizeBiodyne {
		public void unrinsedApoaconitine(
				MycterismDemurrer volitionalist_beekeeping);
	}

	public static class HeartsAntipoints implements IPansexualizeBiodyne {
		@Override
		public void unrinsedApoaconitine(
				MycterismDemurrer volitionalist_beekeeping) {
			Tracer.tracepointWeaknessStart("CWE606", "A",
					"Unchecked Input for Loop Condition");
			String valueString = volitionalist_beekeeping
					.getintracarpellary_unlimp().trim();
			Pattern stonesoup_rel_path_pattern = Pattern
					.compile("(^|/)\\.\\.?/");
			Matcher rel_path_match = stonesoup_rel_path_pattern
					.matcher(valueString);
			Tracer.tracepointVariableString("value",
					volitionalist_beekeeping.getintracarpellary_unlimp());
			Tracer.tracepointVariableString("valueString", valueString);
			if (valueString.length() == 0 || valueString.startsWith("/")
					|| rel_path_match.find()) {
				XmlSourceInboxManager.investitorPash
						.println("Path traversal identified, discarding request.");
			} else {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				java.io.File checkedPath = new java.io.File(valueString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				while (!checkedPath.isFile()) {
					try {
						XmlSourceInboxManager.investitorPash.printf(
								"File \"%s\" does not exist, sleeping...\n",
								valueString);
						Thread.sleep(500);
					} catch (InterruptedException e) {
						Tracer.tracepointError(e.getClass().getName() + ": "
								+ e.getMessage());
						XmlSourceInboxManager.investitorPash
								.println("Thread interrupted.");
						e.printStackTrace(XmlSourceInboxManager.investitorPash);
					}
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				XmlSourceInboxManager.investitorPash.println("Found file.");
				XmlSourceInboxManager.investitorPash.printf(
						"Reading \"%s\".\n", checkedPath.getPath());
				java.io.BufferedReader reader = null;
				try {
					java.io.FileInputStream fis = new java.io.FileInputStream(
							checkedPath);
					reader = new java.io.BufferedReader(
							new java.io.InputStreamReader(fis));
					String line;
					while ((line = reader.readLine()) != null) {
						XmlSourceInboxManager.investitorPash.println(line);
					}
				} catch (java.io.FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					XmlSourceInboxManager.investitorPash.printf(
							"File \"%s\" does not exist\n",
							checkedPath.getPath());
				} catch (java.io.IOException ioe) {
					Tracer.tracepointError(ioe.getClass().getName() + ": "
							+ ioe.getMessage());
					XmlSourceInboxManager.investitorPash
							.println("Failed to read file.");
				} finally {
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (java.io.IOException e) {
						XmlSourceInboxManager.investitorPash
								.println("STONESOUP: Closing file quietly.");
					}
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

	public class MycterismDemurrer {
		private String intracarpellary_unlimp;

		public MycterismDemurrer(String intracarpellary_unlimp) {
			this.intracarpellary_unlimp = intracarpellary_unlimp;
		}

		public String getintracarpellary_unlimp() {
			return this.intracarpellary_unlimp;
		}
	}

	static PrintStream investitorPash = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean outlandPassback = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (outlandPassback.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpTaE9It_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			String tressour_pedipalpida = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (tressour_pedipalpida == null
					|| !tressour_pedipalpida.equals("1")) {
				StonesoupSourceHttpServer biolytic_uncontentedly = null;
				PipedOutputStream doctorbirdHereditist = new PipedOutputStream();
				try {
					XmlSourceInboxManager.investitorPash = new PrintStream(
							doctorbirdHereditist, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException unprejudicedSanification) {
					System.err.printf("Failed to open log file.  %s\n",
							unprejudicedSanification.getMessage());
					XmlSourceInboxManager.investitorPash = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							unprejudicedSanification);
				}
				if (XmlSourceInboxManager.investitorPash != null) {
					try {
						String bridgehead_coprecipitate;
						try {
							biolytic_uncontentedly = new StonesoupSourceHttpServer(
									8887, doctorbirdHereditist);
							biolytic_uncontentedly.start();
							bridgehead_coprecipitate = biolytic_uncontentedly
									.getData();
						} catch (IOException countship_syncopation) {
							biolytic_uncontentedly = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									countship_syncopation);
						} catch (Exception dasheen_callosity) {
							biolytic_uncontentedly = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									dasheen_callosity);
						}
						if (null != bridgehead_coprecipitate) {
							MycterismDemurrer ambuscade_chrysopoetic = new MycterismDemurrer(
									bridgehead_coprecipitate);
							IPansexualizeBiodyne pithecanthrope_electropoion = new HeartsAntipoints();
							pithecanthrope_electropoion
									.unrinsedApoaconitine(ambuscade_chrysopoetic);
						}
					} finally {
						XmlSourceInboxManager.investitorPash.close();
						if (biolytic_uncontentedly != null)
							biolytic_uncontentedly.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

}
