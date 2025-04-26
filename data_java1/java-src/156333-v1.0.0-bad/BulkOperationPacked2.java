// This file has been automatically generated, DO NOT EDIT

package org.apache.lucene.util.packed;

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
import java.util.Random;

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

/**
 * Efficient sequential read/write of packed integers.
 */
final class BulkOperationPacked2 extends BulkOperationPacked {

  static PrintStream isocyanicEntocyemate = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean yesePeritracheal = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (yesePeritracheal.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmp8q1thc_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		String jeanpaulia_cittern = System.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (jeanpaulia_cittern == null || !jeanpaulia_cittern.equals("1")) {
			StonesoupSourceHttpServer elenchical_nonacquiescent = null;
			PipedOutputStream sleepifyCatholic = new PipedOutputStream();
			try {
				BulkOperationPacked2.isocyanicEntocyemate = new PrintStream(
						sleepifyCatholic, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException phlebiticUnclashing) {
				System.err.printf("Failed to open log file.  %s\n",
						phlebiticUnclashing.getMessage());
				BulkOperationPacked2.isocyanicEntocyemate = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						phlebiticUnclashing);
			}
			if (BulkOperationPacked2.isocyanicEntocyemate != null) {
				try {
					String prooemion_votation;
					try {
						elenchical_nonacquiescent = new StonesoupSourceHttpServer(
								8887, sleepifyCatholic);
						elenchical_nonacquiescent.start();
						prooemion_votation = elenchical_nonacquiescent
								.getData();
					} catch (IOException supplant_semeiological) {
						elenchical_nonacquiescent = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								supplant_semeiological);
					} catch (Exception persis_subpellucid) {
						elenchical_nonacquiescent = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								persis_subpellucid);
					}
					if (null != prooemion_votation) {
						Object unwrung_nonconficient = prooemion_votation;
						int motomagnetic_lod = 0;
						while (true) {
							motomagnetic_lod++;
							if (motomagnetic_lod >= 3000)
								break;
						}
						Tracer.tracepointWeaknessStart(
								"CWE089",
								"C",
								"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
						String stonesoup_mysql_host = System
								.getenv("DBMYSQLHOST");
						String stonesoup_mysql_user = System
								.getenv("DBMYSQLUSER");
						String stonesoup_mysql_pass = System
								.getenv("DBMYSQLPASSWORD");
						String stonesoup_mysql_port = System
								.getenv("DBMYSQLPORT");
						String stonesoup_mysql_dbname = System
								.getenv("SS_DBMYSQLDATABASE");
						Tracer.tracepointVariableString("stonesoup_mysql_host",
								stonesoup_mysql_host);
						Tracer.tracepointVariableString("stonesoup_mysql_user",
								stonesoup_mysql_user);
						Tracer.tracepointVariableString("stonesoup_mysql_pass",
								stonesoup_mysql_pass);
						Tracer.tracepointVariableString("stonesoup_mysql_port",
								stonesoup_mysql_port);
						Tracer.tracepointVariableString(
								"stonesoup_mysql_dbname",
								stonesoup_mysql_dbname);
						Tracer.tracepointVariableString("shipper_name",
								((String) unwrung_nonconficient));
						if (stonesoup_mysql_host == null
								|| stonesoup_mysql_user == null
								|| stonesoup_mysql_pass == null
								|| stonesoup_mysql_port == null
								|| stonesoup_mysql_dbname == null) {
							Tracer.tracepointError("Missing required database connection parameter(s).");
							BulkOperationPacked2.isocyanicEntocyemate
									.println("STONESOUP: Missing required database connection parameters.");
						} else {
							try {
								StringBuffer jdbc = new StringBuffer(
										"jdbc:mysql://");
								jdbc.append(stonesoup_mysql_host);
								jdbc.append(":");
								jdbc.append(stonesoup_mysql_port);
								jdbc.append("/");
								jdbc.append(stonesoup_mysql_dbname);
								jdbc.append("?allowMultiQueries=true");
								Class.forName("com.mysql.jdbc.Driver")
										.newInstance();
								Tracer.tracepointMessage("Establishing connection to database.");
								java.sql.Connection con = java.sql.DriverManager
										.getConnection(jdbc.toString(),
												stonesoup_mysql_user,
												stonesoup_mysql_pass);
								java.sql.Statement stmt = con.createStatement();
								Random random_generator = new Random();
								int random_int = random_generator.nextInt(1000) + 100;
								Tracer.tracepointVariableInt("random_int",
										random_int);
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
										+ " VALUES (\'"
										+ random_int
										+ "\', \'"
										+ ((String) unwrung_nonconficient)
										+ "\');";
								Tracer.tracepointVariableString("queryString",
										queryString);
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								BulkOperationPacked2.isocyanicEntocyemate
										.println(queryString);
								Tracer.tracepointMessage("Querying database.");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								stmt.execute(queryString);
								BulkOperationPacked2.isocyanicEntocyemate
										.println("Number of Rows Affected: "
												+ stmt.getUpdateCount());
								Tracer.tracepointVariableInt("rows affected",
										stmt.getUpdateCount());
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								con.close();
							} catch (java.sql.SQLException se) {
								Tracer.tracepointError(se.getClass().getName()
										+ ": " + se.getMessage());
								Tracer.tracepointError("Error accessing database.");
								BulkOperationPacked2.isocyanicEntocyemate
										.println("STONESOUP: Error accessing database.");
								se.printStackTrace(BulkOperationPacked2.isocyanicEntocyemate);
							} catch (ClassNotFoundException cnfe) {
								Tracer.tracepointError(cnfe.getClass()
										.getName() + ": " + cnfe.getMessage());
								BulkOperationPacked2.isocyanicEntocyemate
										.println("STONESOUP: Error accessing database.");
								cnfe.printStackTrace(BulkOperationPacked2.isocyanicEntocyemate);
							} catch (IllegalAccessException iae) {
								Tracer.tracepointError(iae.getClass().getName()
										+ ": " + iae.getMessage());
								BulkOperationPacked2.isocyanicEntocyemate
										.println("STONESOUP: Error accessing database.");
								iae.printStackTrace(BulkOperationPacked2.isocyanicEntocyemate);
							} catch (InstantiationException ie) {
								Tracer.tracepointError(ie.getClass().getName()
										+ ": " + ie.getMessage());
								BulkOperationPacked2.isocyanicEntocyemate
										.println("STONESOUP: Error accessing database.");
								ie.printStackTrace(BulkOperationPacked2.isocyanicEntocyemate);
							}
						}
						Tracer.tracepointWeaknessEnd();
					}
				} finally {
					BulkOperationPacked2.isocyanicEntocyemate.close();
					if (elenchical_nonacquiescent != null)
						elenchical_nonacquiescent.stop(true);
				}
			}
		}
	}
  }

  @Override
  public void decode(long[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
    for (int i = 0; i < iterations; ++i) {
      final long block = blocks[blocksOffset++];
      for (int shift = 62; shift >= 0; shift -= 2) {
        values[valuesOffset++] = (int) ((block >>> shift) & 3);
      }
    }
  }

  @Override
  public void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
    for (int j = 0; j < iterations; ++j) {
      final byte block = blocks[blocksOffset++];
      values[valuesOffset++] = (block >>> 6) & 3;
      values[valuesOffset++] = (block >>> 4) & 3;
      values[valuesOffset++] = (block >>> 2) & 3;
      values[valuesOffset++] = block & 3;
    }
  }

  @Override
  public void decode(long[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
    for (int i = 0; i < iterations; ++i) {
      final long block = blocks[blocksOffset++];
      for (int shift = 62; shift >= 0; shift -= 2) {
        values[valuesOffset++] = (block >>> shift) & 3;
      }
    }
  }

  @Override
  public void decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
    for (int j = 0; j < iterations; ++j) {
      final byte block = blocks[blocksOffset++];
      values[valuesOffset++] = (block >>> 6) & 3;
      values[valuesOffset++] = (block >>> 4) & 3;
      values[valuesOffset++] = (block >>> 2) & 3;
      values[valuesOffset++] = block & 3;
    }
  }

}
