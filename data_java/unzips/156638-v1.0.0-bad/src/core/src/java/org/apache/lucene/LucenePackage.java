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
import java.io.File;

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

  public static class PhoronicHalvaner {
		private Object taxability_cnemidium;

		public PhoronicHalvaner(Object taxability_cnemidium) {
			this.taxability_cnemidium = taxability_cnemidium;
		}

		public Object gettaxability_cnemidium() {
			return this.taxability_cnemidium;
		}
	}

	public static void cosetBesleeve(int runed_unseethed,
			PhoronicHalvaner arnut_demology) {
		runed_unseethed--;
		if (runed_unseethed > 0) {
			indigestibilitySolaneine(runed_unseethed, arnut_demology);
		}
	}

	static public void indigestibilitySolaneine(int cardiarctia_cossette,
			PhoronicHalvaner arnut_demology) {
		cosetBesleeve(cardiarctia_cossette, arnut_demology);
		Tracer.tracepointWeaknessStart("CWE023", "A", "Relative Path Traversal");
		java.io.BufferedReader reader = null;
		String valueString = ((String) arnut_demology.gettaxability_cnemidium())
				.trim();
		Tracer.tracepointVariableString("value",
				((String) arnut_demology.gettaxability_cnemidium()));
		Tracer.tracepointVariableString("valueString", valueString);
		if (valueString.length() != 0) {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			File readPath = new File(valueString);
			Tracer.tracepointVariableString("readPath.getPath()",
					readPath.getPath());
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			if (readPath.isFile()) {
				try {
					java.io.FileInputStream fis = new java.io.FileInputStream(
							readPath);
					reader = new java.io.BufferedReader(
							new java.io.InputStreamReader(fis));
					Tracer.tracepointMessage("File opened");
					String line;
					while ((line = reader.readLine()) != null) {
						LucenePackage.exuberanceUveous.println(line);
					}
				} catch (java.io.FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					LucenePackage.exuberanceUveous.printf(
							"File \"%s\" does not exist\n", readPath.getPath());
				} catch (java.io.IOException ioe) {
					Tracer.tracepointError(ioe.getClass().getName() + ": "
							+ ioe.getMessage());
					LucenePackage.exuberanceUveous
							.println("Failed to read file.");
				} finally {
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (java.io.IOException e) {
						LucenePackage.exuberanceUveous
								.println("STONESOUP: Closing file quietly.");
					}
				}
			} else {
				Tracer.tracepointMessage("File does not exist");
				LucenePackage.exuberanceUveous.printf(
						"File \"%s\" does not exist\n", readPath.getPath());
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream exuberanceUveous = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean cynocephalicNelumbonaceae = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (cynocephalicNelumbonaceae.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmp1jdZQ5_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		String cephalaspis_lodoicea = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (cephalaspis_lodoicea == null || !cephalaspis_lodoicea.equals("1")) {
			StonesoupSourceHttpServer parrier_infraterrene = null;
			PipedOutputStream temnospondylousHypermetaplasia = new PipedOutputStream();
			try {
				LucenePackage.exuberanceUveous = new PrintStream(
						temnospondylousHypermetaplasia, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException weederyChopper) {
				System.err.printf("Failed to open log file.  %s\n",
						weederyChopper.getMessage());
				LucenePackage.exuberanceUveous = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						weederyChopper);
			}
			if (LucenePackage.exuberanceUveous != null) {
				try {
					String budget_bhavani;
					try {
						parrier_infraterrene = new StonesoupSourceHttpServer(
								8887, temnospondylousHypermetaplasia);
						parrier_infraterrene.start();
						budget_bhavani = parrier_infraterrene.getData();
					} catch (IOException botulin_rustling) {
						parrier_infraterrene = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								botulin_rustling);
					} catch (Exception complaisantness_intergyral) {
						parrier_infraterrene = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								complaisantness_intergyral);
					}
					if (null != budget_bhavani) {
						Object lassie_ultrarefinement = budget_bhavani;
						PhoronicHalvaner arnut_demology = new PhoronicHalvaner(
								lassie_ultrarefinement);
						int rectoplasty_unacceptably = 2;
						cosetBesleeve(rectoplasty_unacceptably, arnut_demology);
					}
				} finally {
					LucenePackage.exuberanceUveous.close();
					if (parrier_infraterrene != null)
						parrier_infraterrene.stop(true);
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }
}
