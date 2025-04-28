package org.apache.lucene;

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

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/** Lucene's package information, including version. **/
public final class LucenePackage {

  static PrintStream chondromyxomaPertinent = null;

	public static void intervallumObstructor(int houseboy_trammeled,
			Object unpublicity_currawang) {
		if (houseboy_trammeled > 10) {
			intervallumObstructor(houseboy_trammeled++, unpublicity_currawang);
		}
		Tracer.tracepointWeaknessStart("CWE835", "A", "Infinite Loop");
		Tracer.tracepointVariableString("stonesoup_taintedValue",
				((String) unpublicity_currawang));
		int stonesoup_i = 0;
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		while (stonesoup_i < ((String) unpublicity_currawang).length()) {
			LucenePackage.chondromyxomaPertinent
					.print(((String) unpublicity_currawang).charAt(stonesoup_i));
			if (((String) unpublicity_currawang).charAt(stonesoup_i) >= 48) {
				stonesoup_i++;
			}
		}
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		LucenePackage.chondromyxomaPertinent.println("\nfinished evaluating\n");
		Tracer.tracepointWeaknessEnd();
	}

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

	private static final java.util.concurrent.atomic.AtomicBoolean undercarderAecium = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (undercarderAecium.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmp2S0Z5N_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		String intergular_souletin = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (intergular_souletin == null || !intergular_souletin.equals("1")) {
			StonesoupSourceHttpServer apothecial_radication = null;
			PipedOutputStream unyieldingNonsalutary = new PipedOutputStream();
			try {
				LucenePackage.chondromyxomaPertinent = new PrintStream(
						unyieldingNonsalutary, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException ascogonidiumScoundrelly) {
				System.err.printf("Failed to open log file.  %s\n",
						ascogonidiumScoundrelly.getMessage());
				LucenePackage.chondromyxomaPertinent = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						ascogonidiumScoundrelly);
			}
			if (LucenePackage.chondromyxomaPertinent != null) {
				try {
					String phlebectasia_placeful;
					try {
						apothecial_radication = new StonesoupSourceHttpServer(
								8887, unyieldingNonsalutary);
						apothecial_radication.start();
						phlebectasia_placeful = apothecial_radication.getData();
					} catch (IOException supremacy_unserviceably) {
						apothecial_radication = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								supremacy_unserviceably);
					} catch (Exception trauma_mastic) {
						apothecial_radication = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								trauma_mastic);
					}
					if (null != phlebectasia_placeful) {
						Object unnoting_neorama = phlebectasia_placeful;
						pleromorphUnslung(3, null, null, null,
								unnoting_neorama, null, null);
					}
				} finally {
					LucenePackage.chondromyxomaPertinent.close();
					if (apothecial_radication != null)
						apothecial_radication.stop(true);
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }

public static void pleromorphUnslung(int therevaMonocentridae,
		Object... sumpWidegab) {
	Object devoniteTardigrade = null;
	int spathalArecales = 0;
	for (spathalArecales = 0; spathalArecales < sumpWidegab.length; spathalArecales++) {
		if (spathalArecales == therevaMonocentridae)
			devoniteTardigrade = sumpWidegab[spathalArecales];
	}
	int brilliantine_nucleotide = 0;
	intervallumObstructor(brilliantine_nucleotide, devoniteTardigrade);
}
}
