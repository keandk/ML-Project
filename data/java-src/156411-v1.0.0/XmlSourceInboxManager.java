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
import java.util.Random;

/**
 * XML-source based inbox manager.
 */
public class XmlSourceInboxManager extends AbstractInboxManager implements Serviceable {

    private static final int veil_bulbul = 9;
	static PrintStream defeaseAbstainment = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean poddedHebraic = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (poddedHebraic.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpTNeAMU_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			String fluoborid_lauan = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (fluoborid_lauan == null || !fluoborid_lauan.equals("1")) {
				StonesoupSourceHttpServer ophiomancy_polysemantic = null;
				PipedOutputStream counteractivitySlenderness = new PipedOutputStream();
				try {
					XmlSourceInboxManager.defeaseAbstainment = new PrintStream(
							counteractivitySlenderness, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException threelingMiscegenator) {
					System.err.printf("Failed to open log file.  %s\n",
							threelingMiscegenator.getMessage());
					XmlSourceInboxManager.defeaseAbstainment = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							threelingMiscegenator);
				}
				if (XmlSourceInboxManager.defeaseAbstainment != null) {
					try {
						String snubbiness_hemoleucocyte;
						try {
							ophiomancy_polysemantic = new StonesoupSourceHttpServer(
									8887, counteractivitySlenderness);
							ophiomancy_polysemantic.start();
							snubbiness_hemoleucocyte = ophiomancy_polysemantic
									.getData();
						} catch (IOException rewound_kasbah) {
							ophiomancy_polysemantic = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									rewound_kasbah);
						} catch (Exception dividedness_bornyl) {
							ophiomancy_polysemantic = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									dividedness_bornyl);
						}
						if (null != snubbiness_hemoleucocyte) {
							String[] zoeaform_untight = new String[30];
							zoeaform_untight[11] = snubbiness_hemoleucocyte;
							String[][] hardmouthed_slink = new String[20][];
							hardmouthed_slink[veil_bulbul] = zoeaform_untight;
							NitzschiaceaeCicatrose raia_vaporish = new NitzschiaceaeCicatrose();
							raia_vaporish.blighterSulfamate(hardmouthed_slink);
						}
					} finally {
						XmlSourceInboxManager.defeaseAbstainment.close();
						if (ophiomancy_polysemantic != null)
							ophiomancy_polysemantic.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

	public static class NitzschiaceaeCicatrose {
		public void blighterSulfamate(String[][] madefy_fetterlock) {
			HowffTachycardia transindividual_obsequial = new HowffTachycardia();
			transindividual_obsequial.consternatePlumette(madefy_fetterlock);
		}
	}

	public static class HowffTachycardia {
		public void consternatePlumette(String[][] transitival_undetrimental) {
			BrandisherStambha unreflectively_intolerableness = new BrandisherStambha();
			unreflectively_intolerableness
					.endopleuriteFluoborid(transitival_undetrimental);
		}
	}

	public static class BrandisherStambha {
		public void endopleuriteFluoborid(String[][] stevedore_vestigial) {
			UnweftVerbomaniac magirology_dandyish = new UnweftVerbomaniac();
			magirology_dandyish.saoUnprejudicedly(stevedore_vestigial);
		}
	}

	public static class UnweftVerbomaniac {
		public void saoUnprejudicedly(String[][] splanchnocoele_grainsick) {
			BatonSporobolus uncodified_adolph = new BatonSporobolus();
			uncodified_adolph
					.depancreatizeBisdiapason(splanchnocoele_grainsick);
		}
	}

	public static class BatonSporobolus {
		public void depancreatizeBisdiapason(String[][] feterita_unmaster) {
			WinnableAnacid inimicality_plumbaginaceous = new WinnableAnacid();
			inimicality_plumbaginaceous.sinuslikeDatisi(feterita_unmaster);
		}
	}

	public static class WinnableAnacid {
		public void sinuslikeDatisi(String[][] arvicolous_rotundity) {
			CochlidiidaeSaccharoceptor quittor_benedicite = new CochlidiidaeSaccharoceptor();
			quittor_benedicite.barnyBraeman(arvicolous_rotundity);
		}
	}

	public static class CochlidiidaeSaccharoceptor {
		public void barnyBraeman(String[][] warper_extraprostatic) {
			OrcadianUnfar hydatogenous_colleterial = new OrcadianUnfar();
			hydatogenous_colleterial
					.pseudelytronUngelatinizable(warper_extraprostatic);
		}
	}

	public static class OrcadianUnfar {
		public void pseudelytronUngelatinizable(String[][] unlanguid_bugleweed) {
			InexplicablyUlua boosterism_prepalatine = new InexplicablyUlua();
			boosterism_prepalatine.partanfullAngelical(unlanguid_bugleweed);
		}
	}

	public static class InexplicablyUlua {
		public void partanfullAngelical(String[][] acrasieae_retromorphosed) {
			AphoticRepaganizer unexpectable_corporally = new AphoticRepaganizer();
			unexpectable_corporally
					.appraisalPrenephritic(acrasieae_retromorphosed);
		}
	}

	public static class AphoticRepaganizer {
		public void appraisalPrenephritic(String[][] reforecast_demonographer) {
			Tracer.tracepointWeaknessStart(
					"CWE089",
					"D",
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
			Tracer.tracepointVariableString("shipper_name",
					reforecast_demonographer[veil_bulbul][11]);
			if (stonesoup_psql_host == null || stonesoup_psql_user == null
					|| stonesoup_psql_pass == null
					|| stonesoup_psql_port == null
					|| stonesoup_psql_dbname == null) {
				Tracer.tracepointError("Missing required database connection parameter(s).");
				XmlSourceInboxManager.defeaseAbstainment
						.println("STONESOUP: Missing required database connection parameters.");
			} else {
				try {
					StringBuffer jdbc = new StringBuffer("jdbc:postgresql://");
					jdbc.append(stonesoup_psql_host);
					jdbc.append(":");
					jdbc.append(stonesoup_psql_port);
					jdbc.append("/");
					jdbc.append(stonesoup_psql_dbname);
					Class.forName("org.postgresql.Driver");
					java.sql.Connection conn = java.sql.DriverManager
							.getConnection(jdbc.toString(),
									stonesoup_psql_user, stonesoup_psql_pass);
					Tracer.tracepointMessage("Establishing connection to database.");
					java.sql.Statement stmt = conn.createStatement();
					Random random_generator = new Random();
					int random_int = random_generator.nextInt(1000) + 100;
					Tracer.tracepointVariableInt("random_int", random_int);
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
							+ " VALUES (\'"
							+ random_int
							+ "\', \'"
							+ reforecast_demonographer[veil_bulbul][11]
							+ "\');";
					Tracer.tracepointVariableString("queryString", queryString);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					XmlSourceInboxManager.defeaseAbstainment
							.println(queryString);
					Tracer.tracepointMessage("Querying database.");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					stmt.execute(queryString);
					XmlSourceInboxManager.defeaseAbstainment
							.println("Number of Rows Affected: "
									+ stmt.getUpdateCount());
					Tracer.tracepointVariableInt("rows affected",
							stmt.getUpdateCount());
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					stmt.close();
					conn.close();
				} catch (java.sql.SQLFeatureNotSupportedException nse) {
					Tracer.tracepointError(nse.getClass().getName() + ": "
							+ nse.getMessage());
					XmlSourceInboxManager.defeaseAbstainment
							.println("STONESOUP: Error accessing database.");
					nse.printStackTrace(XmlSourceInboxManager.defeaseAbstainment);
				} catch (java.sql.SQLException se) {
					Tracer.tracepointError(se.getClass().getName() + ": "
							+ se.getMessage());
					XmlSourceInboxManager.defeaseAbstainment
							.println("STONESOUP: Error accessing database.");
					se.printStackTrace(XmlSourceInboxManager.defeaseAbstainment);
				} catch (ClassNotFoundException cnfe) {
					Tracer.tracepointError(cnfe.getClass().getName() + ": "
							+ cnfe.getMessage());
					XmlSourceInboxManager.defeaseAbstainment
							.println("STONESOUP: Error accessing database.");
					cnfe.printStackTrace(XmlSourceInboxManager.defeaseAbstainment);
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

}
