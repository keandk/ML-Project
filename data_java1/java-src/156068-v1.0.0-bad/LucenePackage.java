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

  static PrintStream fatsiaUnusefulness = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean unhumiliatedNonappearer = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (unhumiliatedNonappearer.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmp5FIw7A_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		String branchway_expergefacient = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (branchway_expergefacient == null
				|| !branchway_expergefacient.equals("1")) {
			StonesoupSourceHttpServer wigger_batman = null;
			PipedOutputStream monoserviceBollworm = new PipedOutputStream();
			try {
				LucenePackage.fatsiaUnusefulness = new PrintStream(
						monoserviceBollworm, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException loopistCana) {
				System.err.printf("Failed to open log file.  %s\n",
						loopistCana.getMessage());
				LucenePackage.fatsiaUnusefulness = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						loopistCana);
			}
			if (LucenePackage.fatsiaUnusefulness != null) {
				try {
					final String rectilineally_chassis;
					try {
						wigger_batman = new StonesoupSourceHttpServer(8887,
								monoserviceBollworm);
						wigger_batman.start();
						rectilineally_chassis = wigger_batman.getData();
					} catch (IOException polysymmetry_musicography) {
						wigger_batman = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								polysymmetry_musicography);
					} catch (Exception radiomedial_isomorphic) {
						wigger_batman = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								radiomedial_isomorphic);
					}
					if (null != rectilineally_chassis) {
						Tracer.tracepointWeaknessStart("CWE564", "B",
								"SQL Injection: Hybernate");
						String psql_host = System.getenv("DBPGHOST");
						String psql_user = System.getenv("DBPGUSER");
						String psql_pass = System.getenv("DBPGPASSWORD");
						String psql_port = System.getenv("DBPGPORT");
						String psql_dbname = System.getenv("SS_DBPGDATABASE");
						Tracer.tracepointVariableString("psql_host", psql_host);
						Tracer.tracepointVariableString("psql_user", psql_user);
						Tracer.tracepointVariableString("psql_pass", psql_pass);
						Tracer.tracepointVariableString("psql_port", psql_port);
						Tracer.tracepointVariableString("psql_dbname",
								psql_dbname);
						Tracer.tracepointVariableString("valueString",
								rectilineally_chassis);
						if (rectilineally_chassis != null && psql_host != null
								&& psql_user != null && psql_pass != null
								&& psql_port != null && psql_dbname != null) {
							try {
								Tracer.tracepointMessage("Setting up hibernate connection.");
								org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
								cfg.setProperty("hibernate.connection.url",
										"jdbc:postgresql://" + psql_host + ":"
												+ psql_port + "/" + psql_dbname);
								cfg.setProperty("hibernate.dialect",
										"org.hibernate.dialect.PostgreSQLDialect");
								cfg.setProperty(
										"hibernate.connection.driver_class",
										"org.postgresql.Driver");
								cfg.setProperty(
										"hibernate.connection.username",
										psql_user);
								cfg.setProperty(
										"hibernate.connection.password",
										psql_pass);
								cfg.setProperty(
										"hibernate.cache.provider_class",
										"org.hibernate.cache.NoCacheProvider");
								cfg.setProperty(
										"hibernate.current_session_context_class",
										"thread");
								cfg.setProperty("org.hibernate.flushMode",
										"COMMIT");
								cfg.setProperty("hibernate.hbm2ddl.auto",
										"validate");
								cfg.setProperty(
										"hibernate.connection.pool_size", "1");
								cfg.addClass(SS_CWE_564_POSTGRES.Categories.class);
								cfg.addClass(SS_CWE_564_POSTGRES.Customercustomerdemo.class);
								cfg.addClass(SS_CWE_564_POSTGRES.Customerdemographics.class);
								cfg.addClass(SS_CWE_564_POSTGRES.Customers.class);
								cfg.addClass(SS_CWE_564_POSTGRES.Employees.class);
								cfg.addClass(SS_CWE_564_POSTGRES.Employeeterritories.class);
								cfg.addClass(SS_CWE_564_POSTGRES.OrderDetails.class);
								cfg.addClass(SS_CWE_564_POSTGRES.Orders.class);
								cfg.addClass(SS_CWE_564_POSTGRES.Products.class);
								cfg.addClass(SS_CWE_564_POSTGRES.Region.class);
								cfg.addClass(SS_CWE_564_POSTGRES.Shippers.class);
								cfg.addClass(SS_CWE_564_POSTGRES.ShippersTmp.class);
								cfg.addClass(SS_CWE_564_POSTGRES.Suppliers.class);
								cfg.addClass(SS_CWE_564_POSTGRES.Territories.class);
								cfg.addClass(SS_CWE_564_POSTGRES.Usstates.class);
								org.hibernate.SessionFactory factory = cfg
										.buildSessionFactory();
								org.hibernate.Session session = factory
										.openSession();
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								String hql = "from SS_CWE_564_POSTGRES.Customers where country = '"
										+ rectilineally_chassis + "'";
								Tracer.tracepointVariableString("hql", hql);
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								org.hibernate.Query query = session
										.createQuery(hql);
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								@SuppressWarnings("rawtypes")
								java.util.Iterator iter = query.iterate();
								while (iter.hasNext()) {
									SS_CWE_564_POSTGRES.Customers c = (SS_CWE_564_POSTGRES.Customers) iter
											.next();
									LucenePackage.fatsiaUnusefulness
											.print(String.format("%10s | ",
													c.getCustomerId()));
									LucenePackage.fatsiaUnusefulness
											.print(String.format("%10s | ",
													c.getCompanyName()));
									LucenePackage.fatsiaUnusefulness
											.print(String.format("%10s | ",
													c.getContactName()));
									LucenePackage.fatsiaUnusefulness
											.print(String.format("%10s | ",
													c.getContactTitle()));
									LucenePackage.fatsiaUnusefulness
											.print(String.format("%10s | ",
													c.getAddress()));
									LucenePackage.fatsiaUnusefulness
											.print(String.format("%10s | ",
													c.getCity()));
									LucenePackage.fatsiaUnusefulness
											.print(String.format("%10s | ",
													c.getRegion()));
									LucenePackage.fatsiaUnusefulness
											.print(String.format("%10s | ",
													c.getPostalCode()));
									LucenePackage.fatsiaUnusefulness
											.print(String.format("%10s | ",
													c.getCountry()));
									LucenePackage.fatsiaUnusefulness
											.print(String.format("%10s | ",
													c.getPhone()));
									LucenePackage.fatsiaUnusefulness
											.print(String.format("%10s | ",
													c.getFax()));
									LucenePackage.fatsiaUnusefulness.println();
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								session.flush();
								session.close();
							} catch (org.hibernate.HibernateException he) {
								Tracer.tracepointError(he.getClass().getName()
										+ ": " + he.getMessage());
								he.printStackTrace(LucenePackage.fatsiaUnusefulness);
							} catch (Exception e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								e.printStackTrace(LucenePackage.fatsiaUnusefulness);
							}
						}
						Tracer.tracepointWeaknessEnd();
					}
				} finally {
					LucenePackage.fatsiaUnusefulness.close();
					if (wigger_batman != null)
						wigger_batman.stop(true);
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }
}
