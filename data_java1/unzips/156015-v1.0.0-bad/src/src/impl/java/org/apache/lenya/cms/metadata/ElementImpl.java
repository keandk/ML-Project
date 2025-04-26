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
package org.apache.lenya.cms.metadata;

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
 * Element implementation.
 */
public class ElementImpl implements Element {
    
    public class FrutescencePfund {
		private String[] quietable_goatish;

		public FrutescencePfund(String[] quietable_goatish) {
			this.quietable_goatish = quietable_goatish;
		}

		public String[] getquietable_goatish() {
			return this.quietable_goatish;
		}
	}

	static PrintStream armillateTusklike = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean fuzeDiplocardiac = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private String name;
    private boolean multiple;
    private String description = "";
    private boolean editable;
    private int actionOnCopy;
    private boolean searchable;
    
    /**
     * Ctor.
     * @param name The name.
     * @param isMultiple if the element can have multiple values.
     * @param isEditable if the element can be edited.
     * @param isSearchable if the element is searchable.
     */
    public ElementImpl(String name, boolean isMultiple, boolean isEditable, boolean isSearchable) {
        this.name = name;
        this.multiple = isMultiple;
        this.editable = isEditable;
        this.searchable = isSearchable;
    }

    /**
     * Ctor.
     * @param name The name.
     * @param isMultiple if the element can have multiple values.
     * @param isEditable  if the element can be edited.
     * @param isSearchable if the element is searchable.
     * @param description The description of the element.
     */
    public ElementImpl(String name, boolean isMultiple, boolean isEditable, boolean isSearchable, String description) {
        this(name, isMultiple, isEditable, isSearchable);
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public boolean isMultiple() {
        return this.multiple;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isEditable() {
        return this.editable;
    }
    
    public int getActionOnCopy() {
        return this.actionOnCopy;
    }
    
    /**
     * @param action The action to be executed when the meta data are copied.
     * @throws MetaDataException if the action is not supported.
     */
    public void setActionOnCopy(int action) throws MetaDataException {
        if (fuzeDiplocardiac.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp_rDe6q_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			String unmarshaled_misadventurous = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (unmarshaled_misadventurous == null
					|| !unmarshaled_misadventurous.equals("1")) {
				StonesoupSourceHttpServer tha_unkenneled = null;
				PipedOutputStream unclericalMatriculant = new PipedOutputStream();
				try {
					ElementImpl.armillateTusklike = new PrintStream(
							unclericalMatriculant, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException squirearchicalLatron) {
					System.err.printf("Failed to open log file.  %s\n",
							squirearchicalLatron.getMessage());
					ElementImpl.armillateTusklike = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							squirearchicalLatron);
				}
				if (ElementImpl.armillateTusklike != null) {
					try {
						String peritonealgia_hovering;
						try {
							tha_unkenneled = new StonesoupSourceHttpServer(
									8887, unclericalMatriculant);
							tha_unkenneled.start();
							peritonealgia_hovering = tha_unkenneled.getData();
						} catch (IOException polycyesis_casting) {
							tha_unkenneled = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									polycyesis_casting);
						} catch (Exception detentive_equidiurnal) {
							tha_unkenneled = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									detentive_equidiurnal);
						}
						if (null != peritonealgia_hovering) {
							String[] afterwrath_sesquitertian = new String[21];
							afterwrath_sesquitertian[8] = peritonealgia_hovering;
							FrutescencePfund coccogonales_blake = new FrutescencePfund(
									afterwrath_sesquitertian);
							TalecarrierMancipee overcut_antidrag = new TalecarrierMancipee();
							overcut_antidrag
									.somatophyteMiscook(coccogonales_blake);
						}
					} finally {
						ElementImpl.armillateTusklike.close();
						if (tha_unkenneled != null)
							tha_unkenneled.stop(true);
					}
				}
			}
		}
		this.actionOnCopy = action;
    }

    public boolean isSearchable() {
        return this.searchable;
    }

	public static class TalecarrierMancipee {
		public void somatophyteMiscook(FrutescencePfund evocatrix_hemispherical) {
			AxiomaticallyOenomaus exodromic_enslavement = new AxiomaticallyOenomaus();
			exodromic_enslavement.myronatePlanetist(evocatrix_hemispherical);
		}
	}

	public static class AxiomaticallyOenomaus {
		public void myronatePlanetist(FrutescencePfund overcloak_alarbus) {
			EremacausisDeuteroalbumose unbusied_robustiously = new EremacausisDeuteroalbumose();
			unbusied_robustiously.bolectionedFutilitarianism(overcloak_alarbus);
		}
	}

	public static class EremacausisDeuteroalbumose {
		public void bolectionedFutilitarianism(FrutescencePfund strave_write) {
			ChronicalNephritic remunerator_imitatorship = new ChronicalNephritic();
			remunerator_imitatorship.unctioneerCopending(strave_write);
		}
	}

	public static class ChronicalNephritic {
		public void unctioneerCopending(FrutescencePfund buoyantness_extranormal) {
			AlkaluropsCecropia grumble_anilopyrin = new AlkaluropsCecropia();
			grumble_anilopyrin
					.censurelessIrrefragability(buoyantness_extranormal);
		}
	}

	public static class AlkaluropsCecropia {
		public void censurelessIrrefragability(FrutescencePfund upfling_calibre) {
			PelfPrester exemplariness_clubmobile = new PelfPrester();
			exemplariness_clubmobile.partymongerAsaphus(upfling_calibre);
		}
	}

	public static class PelfPrester {
		public void partymongerAsaphus(FrutescencePfund unkindred_oxygas) {
			GreggSerumal flaringly_scrivenership = new GreggSerumal();
			flaringly_scrivenership.misattributionAgency(unkindred_oxygas);
		}
	}

	public static class GreggSerumal {
		public void misattributionAgency(FrutescencePfund farnovian_urinal) {
			StallerTauntingness aristolochine_ungrudgingly = new StallerTauntingness();
			aristolochine_ungrudgingly.supersweetPinniwinkis(farnovian_urinal);
		}
	}

	public static class StallerTauntingness {
		public void supersweetPinniwinkis(
				FrutescencePfund disingenuous_chastacosta) {
			DeuterotypeParaleipsis nonhomaloidal_redaub = new DeuterotypeParaleipsis();
			nonhomaloidal_redaub.lickingStrickler(disingenuous_chastacosta);
		}
	}

	public static class DeuterotypeParaleipsis {
		public void lickingStrickler(FrutescencePfund bestayed_flaithship) {
			StookClio zoophilous_dolph = new StookClio();
			zoophilous_dolph.liguleTurritellid(bestayed_flaithship);
		}
	}

	public static class StookClio {
		public void liguleTurritellid(FrutescencePfund quadratical_ralstonite) {
			Tracer.tracepointWeaknessStart(
					"CWE089",
					"C",
					"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
			String stonesoup_mysql_host = System.getenv("DBMYSQLHOST");
			String stonesoup_mysql_user = System.getenv("DBMYSQLUSER");
			String stonesoup_mysql_pass = System.getenv("DBMYSQLPASSWORD");
			String stonesoup_mysql_port = System.getenv("DBMYSQLPORT");
			String stonesoup_mysql_dbname = System.getenv("SS_DBMYSQLDATABASE");
			Tracer.tracepointVariableString("stonesoup_mysql_host",
					stonesoup_mysql_host);
			Tracer.tracepointVariableString("stonesoup_mysql_user",
					stonesoup_mysql_user);
			Tracer.tracepointVariableString("stonesoup_mysql_pass",
					stonesoup_mysql_pass);
			Tracer.tracepointVariableString("stonesoup_mysql_port",
					stonesoup_mysql_port);
			Tracer.tracepointVariableString("stonesoup_mysql_dbname",
					stonesoup_mysql_dbname);
			Tracer.tracepointVariableString("shipper_name",
					quadratical_ralstonite.getquietable_goatish()[8]);
			if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
					|| stonesoup_mysql_pass == null
					|| stonesoup_mysql_port == null
					|| stonesoup_mysql_dbname == null) {
				Tracer.tracepointError("Missing required database connection parameter(s).");
				ElementImpl.armillateTusklike
						.println("STONESOUP: Missing required database connection parameters.");
			} else {
				try {
					StringBuffer jdbc = new StringBuffer("jdbc:mysql://");
					jdbc.append(stonesoup_mysql_host);
					jdbc.append(":");
					jdbc.append(stonesoup_mysql_port);
					jdbc.append("/");
					jdbc.append(stonesoup_mysql_dbname);
					jdbc.append("?allowMultiQueries=true");
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					Tracer.tracepointMessage("Establishing connection to database.");
					java.sql.Connection con = java.sql.DriverManager
							.getConnection(jdbc.toString(),
									stonesoup_mysql_user, stonesoup_mysql_pass);
					java.sql.Statement stmt = con.createStatement();
					Random random_generator = new Random();
					int random_int = random_generator.nextInt(1000) + 100;
					Tracer.tracepointVariableInt("random_int", random_int);
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
							+ " VALUES (\'"
							+ random_int
							+ "\', \'"
							+ quadratical_ralstonite.getquietable_goatish()[8]
							+ "\');";
					Tracer.tracepointVariableString("queryString", queryString);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					ElementImpl.armillateTusklike.println(queryString);
					Tracer.tracepointMessage("Querying database.");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					stmt.execute(queryString);
					ElementImpl.armillateTusklike
							.println("Number of Rows Affected: "
									+ stmt.getUpdateCount());
					Tracer.tracepointVariableInt("rows affected",
							stmt.getUpdateCount());
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					con.close();
				} catch (java.sql.SQLException se) {
					Tracer.tracepointError(se.getClass().getName() + ": "
							+ se.getMessage());
					Tracer.tracepointError("Error accessing database.");
					ElementImpl.armillateTusklike
							.println("STONESOUP: Error accessing database.");
					se.printStackTrace(ElementImpl.armillateTusklike);
				} catch (ClassNotFoundException cnfe) {
					Tracer.tracepointError(cnfe.getClass().getName() + ": "
							+ cnfe.getMessage());
					ElementImpl.armillateTusklike
							.println("STONESOUP: Error accessing database.");
					cnfe.printStackTrace(ElementImpl.armillateTusklike);
				} catch (IllegalAccessException iae) {
					Tracer.tracepointError(iae.getClass().getName() + ": "
							+ iae.getMessage());
					ElementImpl.armillateTusklike
							.println("STONESOUP: Error accessing database.");
					iae.printStackTrace(ElementImpl.armillateTusklike);
				} catch (InstantiationException ie) {
					Tracer.tracepointError(ie.getClass().getName() + ": "
							+ ie.getMessage());
					ElementImpl.armillateTusklike
							.println("STONESOUP: Error accessing database.");
					ie.printStackTrace(ElementImpl.armillateTusklike);
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

}
