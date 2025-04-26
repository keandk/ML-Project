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
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Random;

/**
 * XML-source based inbox manager.
 */
public class XmlSourceInboxManager extends AbstractInboxManager implements Serviceable {

    public class QuadrimetallicBrandied<T> {
		private T carcavelhos_esere;

		public QuadrimetallicBrandied(T carcavelhos_esere) {
			this.carcavelhos_esere = carcavelhos_esere;
		}

		public T getcarcavelhos_esere() {
			return this.carcavelhos_esere;
		}
	}

	static PrintStream peropusAcademic = null;
	private static final java.util.concurrent.atomic.AtomicBoolean mammutidaeAnaphroditous = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (mammutidaeAnaphroditous.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpomngay_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			File reconditelyLacquering = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!reconditelyLacquering.getParentFile().exists()
					&& !reconditelyLacquering.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					XmlSourceInboxManager.peropusAcademic = new PrintStream(
							new FileOutputStream(reconditelyLacquering, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException squamipinnesHeliport) {
					System.err.printf("Failed to open log file.  %s\n",
							squamipinnesHeliport.getMessage());
					XmlSourceInboxManager.peropusAcademic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							squamipinnesHeliport);
				} catch (FileNotFoundException uninvincibleParonymous) {
					System.err.printf("Failed to open log file.  %s\n",
							uninvincibleParonymous.getMessage());
					XmlSourceInboxManager.peropusAcademic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							uninvincibleParonymous);
				}
				if (XmlSourceInboxManager.peropusAcademic != null) {
					try {
						String fibrocyte_lochioschesis = System
								.getenv("WASABI_CYATHOID");
						if (null != fibrocyte_lochioschesis) {
							String[] angara_moldable = new String[26];
							angara_moldable[11] = fibrocyte_lochioschesis;
							QuadrimetallicBrandied<String[]> phaethon_paetrick = new QuadrimetallicBrandied<String[]>(
									angara_moldable);
							try {
								String grossularia_feoffee = System
										.getProperty("os.name");
								if (null != grossularia_feoffee) {
									if (!grossularia_feoffee
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException bansalague_bicrenate) {
							} finally {
								Tracer.tracepointWeaknessStart(
										"CWE089",
										"D",
										"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
								String stonesoup_psql_host = System
										.getenv("DBPGHOST");
								String stonesoup_psql_user = System
										.getenv("DBPGUSER");
								String stonesoup_psql_pass = System
										.getenv("DBPGPASSWORD");
								String stonesoup_psql_port = System
										.getenv("DBPGPORT");
								String stonesoup_psql_dbname = System
										.getenv("SS_DBPGDATABASE");
								Tracer.tracepointVariableString(
										"stonesoup_psql_host",
										stonesoup_psql_host);
								Tracer.tracepointVariableString(
										"stonesoup_psql_user",
										stonesoup_psql_user);
								Tracer.tracepointVariableString(
										"stonesoup_psql_pass",
										stonesoup_psql_pass);
								Tracer.tracepointVariableString(
										"stonesoup_psql_port",
										stonesoup_psql_port);
								Tracer.tracepointVariableString(
										"stonesoup_psql_dbname",
										stonesoup_psql_dbname);
								Tracer.tracepointVariableString("shipper_name",
										phaethon_paetrick
												.getcarcavelhos_esere()[11]);
								if (stonesoup_psql_host == null
										|| stonesoup_psql_user == null
										|| stonesoup_psql_pass == null
										|| stonesoup_psql_port == null
										|| stonesoup_psql_dbname == null) {
									Tracer.tracepointError("Missing required database connection parameter(s).");
									XmlSourceInboxManager.peropusAcademic
											.println("STONESOUP: Missing required database connection parameters.");
								} else {
									try {
										StringBuffer jdbc = new StringBuffer(
												"jdbc:postgresql://");
										jdbc.append(stonesoup_psql_host);
										jdbc.append(":");
										jdbc.append(stonesoup_psql_port);
										jdbc.append("/");
										jdbc.append(stonesoup_psql_dbname);
										Class.forName("org.postgresql.Driver");
										java.sql.Connection conn = java.sql.DriverManager
												.getConnection(jdbc.toString(),
														stonesoup_psql_user,
														stonesoup_psql_pass);
										Tracer.tracepointMessage("Establishing connection to database.");
										java.sql.Statement stmt = conn
												.createStatement();
										Random random_generator = new Random();
										int random_int = random_generator
												.nextInt(1000) + 100;
										Tracer.tracepointVariableInt(
												"random_int", random_int);
										Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
										String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
												+ " VALUES (\'"
												+ random_int
												+ "\', \'"
												+ phaethon_paetrick
														.getcarcavelhos_esere()[11]
												+ "\');";
										Tracer.tracepointVariableString(
												"queryString", queryString);
										Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
										XmlSourceInboxManager.peropusAcademic
												.println(queryString);
										Tracer.tracepointMessage("Querying database.");
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										stmt.execute(queryString);
										XmlSourceInboxManager.peropusAcademic
												.println("Number of Rows Affected: "
														+ stmt.getUpdateCount());
										Tracer.tracepointVariableInt(
												"rows affected",
												stmt.getUpdateCount());
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										stmt.close();
										conn.close();
									} catch (java.sql.SQLFeatureNotSupportedException nse) {
										Tracer.tracepointError(nse.getClass()
												.getName()
												+ ": "
												+ nse.getMessage());
										XmlSourceInboxManager.peropusAcademic
												.println("STONESOUP: Error accessing database.");
										nse.printStackTrace(XmlSourceInboxManager.peropusAcademic);
									} catch (java.sql.SQLException se) {
										Tracer.tracepointError(se.getClass()
												.getName()
												+ ": "
												+ se.getMessage());
										XmlSourceInboxManager.peropusAcademic
												.println("STONESOUP: Error accessing database.");
										se.printStackTrace(XmlSourceInboxManager.peropusAcademic);
									} catch (ClassNotFoundException cnfe) {
										Tracer.tracepointError(cnfe.getClass()
												.getName()
												+ ": "
												+ cnfe.getMessage());
										XmlSourceInboxManager.peropusAcademic
												.println("STONESOUP: Error accessing database.");
										cnfe.printStackTrace(XmlSourceInboxManager.peropusAcademic);
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						XmlSourceInboxManager.peropusAcademic.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
