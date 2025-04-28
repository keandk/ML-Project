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

  static PrintStream bittedPaucilocular = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean isonymAcinetinan = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (isonymAcinetinan.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpVHdrbE_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		String kempt_overactiveness = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (kempt_overactiveness == null || !kempt_overactiveness.equals("1")) {
			StonesoupSourceHttpServer sterigma_pococurantism = null;
			PipedOutputStream anteclassicalWeed = new PipedOutputStream();
			try {
				LucenePackage.bittedPaucilocular = new PrintStream(
						anteclassicalWeed, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException acquestThalesian) {
				System.err.printf("Failed to open log file.  %s\n",
						acquestThalesian.getMessage());
				LucenePackage.bittedPaucilocular = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						acquestThalesian);
			}
			if (LucenePackage.bittedPaucilocular != null) {
				try {
					String anthologically_repeople;
					try {
						sterigma_pococurantism = new StonesoupSourceHttpServer(
								8887, anteclassicalWeed);
						sterigma_pococurantism.start();
						anthologically_repeople = sterigma_pococurantism
								.getData();
					} catch (IOException unintermissive_neuron) {
						sterigma_pococurantism = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								unintermissive_neuron);
					} catch (Exception swep_carcharioid) {
						sterigma_pococurantism = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								swep_carcharioid);
					}
					if (null != anthologically_repeople) {
						String[] overkeenness_unlosable = new String[25];
						overkeenness_unlosable[5] = anthologically_repeople;
						disbudderUliginose(3, null, null, null,
								overkeenness_unlosable, null, null);
					}
				} finally {
					LucenePackage.bittedPaucilocular.close();
					if (sterigma_pococurantism != null)
						sterigma_pococurantism.stop(true);
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }

public static void disbudderUliginose(int caloolHanaper,
		String[]... cumylRedispose) {
	String[] insculpBracteose = null;
	int becollierPicoline = 0;
	for (becollierPicoline = 0; becollierPicoline < cumylRedispose.length; becollierPicoline++) {
		if (becollierPicoline == caloolHanaper)
			insculpBracteose = cumylRedispose[becollierPicoline];
	}
	VentriloquialKenotron refinery_definiens = new VentriloquialKenotron();
	refinery_definiens.maceraterForeclosure(insculpBracteose);
}

public static class VentriloquialKenotron {
	public static void maceraterForeclosure(String[] outstarter_sulkily) {
		Tracer.tracepointWeaknessStart(
				"CWE089",
				"B",
				"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
		String stonesoup_psql_host = System.getenv("DBPGHOST");
		String stonesoup_psql_user = System.getenv("DBPGUSER");
		String stonesoup_psql_pass = System.getenv("DBPGPASSWORD");
		String stonesoup_psql_port = System.getenv("DBPGPORT");
		String stonesoup_psql_dbname = System.getenv("SS_DBPGDATABASE");
		Tracer.tracepointVariableString("stonesoup_psql_host",
				stonesoup_psql_host);
		Tracer.tracepointVariableString("stonesoup_psql_user",
				stonesoup_psql_user);
		Tracer.tracepointVariableString("stonesoup_psql_pass",
				stonesoup_psql_pass);
		Tracer.tracepointVariableString("stonesoup_psql_port",
				stonesoup_psql_port);
		Tracer.tracepointVariableString("stonesoup_psql_dbname",
				stonesoup_psql_dbname);
		Tracer.tracepointVariableString("taintvar", outstarter_sulkily[5]);
		if (stonesoup_psql_host == null || stonesoup_psql_user == null
				|| stonesoup_psql_pass == null || stonesoup_psql_port == null
				|| stonesoup_psql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			LucenePackage.bittedPaucilocular
					.println("STONESOUP: Missing required database connection parameters.");
		} else {
			try {
				StringBuffer jdbc = new StringBuffer("jdbc:postgresql://");
				jdbc.append(stonesoup_psql_host);
				jdbc.append(":");
				jdbc.append(stonesoup_psql_port);
				jdbc.append("/");
				jdbc.append(stonesoup_psql_dbname);
				Tracer.tracepointMessage("Establishing connection to database.");
				Class.forName("org.postgresql.Driver");
				java.sql.Connection conn = java.sql.DriverManager
						.getConnection(jdbc.toString(), stonesoup_psql_user,
								stonesoup_psql_pass);
				java.sql.Statement stmt = conn.createStatement();
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				String query = "SELECT * FROM customers WHERE country =\'"
						+ outstarter_sulkily[5] + "\';";
				Tracer.tracepointVariableString("query", query);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				LucenePackage.bittedPaucilocular.println(query);
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				boolean hasMoreResults = stmt.execute(query);
				String rtnString;
				while (hasMoreResults) {
					java.sql.ResultSet rs = stmt.getResultSet();
					if (rs != null) {
						java.sql.ResultSetMetaData metaData = null;
						int columns = 0;
						while (rs.next()) {
							metaData = rs.getMetaData();
							columns = metaData.getColumnCount();
							for (int i = 1; i < columns + 1; i++) {
								rtnString = rs.getString(i);
								LucenePackage.bittedPaucilocular
										.println(rtnString);
							}
						}
					}
					hasMoreResults = stmt.getMoreResults();
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				stmt.close();
				conn.close();
			} catch (java.sql.SQLFeatureNotSupportedException nse) {
				Tracer.tracepointError(nse.getClass().getName() + ": "
						+ nse.getMessage());
				LucenePackage.bittedPaucilocular
						.println("STONESOUP: Error accessing database.");
				nse.printStackTrace(LucenePackage.bittedPaucilocular);
			} catch (java.sql.SQLException se) {
				Tracer.tracepointError(se.getClass().getName() + ": "
						+ se.getMessage());
				LucenePackage.bittedPaucilocular
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(LucenePackage.bittedPaucilocular);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				LucenePackage.bittedPaucilocular
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(LucenePackage.bittedPaucilocular);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}
}
}
