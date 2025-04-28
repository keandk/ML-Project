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
package org.apache.lenya.cms.publication;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.avalon.framework.thread.ThreadSafe;
import org.apache.lenya.cms.repository.Session;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    public class SynarteteRucksack {
		private Object anepithymia_subhall;

		public SynarteteRucksack(Object anepithymia_subhall) {
			this.anepithymia_subhall = anepithymia_subhall;
		}

		public Object getanepithymia_subhall() {
			return this.anepithymia_subhall;
		}
	}

	static PrintStream katharsisNomadian = null;
	private static final java.util.concurrent.atomic.AtomicBoolean cameralistOvermelodied = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (cameralistOvermelodied.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpYtJ8h8_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			File wergilShirttail = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!wergilShirttail.getParentFile().exists()
					&& !wergilShirttail.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DocumentFactoryBuilderImpl.katharsisNomadian = new PrintStream(
							new FileOutputStream(wergilShirttail, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException theriomorphosisPhilippizer) {
					System.err.printf("Failed to open log file.  %s\n",
							theriomorphosisPhilippizer.getMessage());
					DocumentFactoryBuilderImpl.katharsisNomadian = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							theriomorphosisPhilippizer);
				} catch (FileNotFoundException vaporishBergschrund) {
					System.err.printf("Failed to open log file.  %s\n",
							vaporishBergschrund.getMessage());
					DocumentFactoryBuilderImpl.katharsisNomadian = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							vaporishBergschrund);
				}
				if (DocumentFactoryBuilderImpl.katharsisNomadian != null) {
					try {
						String perturbation_faille = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (perturbation_faille == null
								|| !perturbation_faille.equals("1")) {
							String unarguable_myxopapilloma = System
									.getenv("CONFUSTICATE_TENOMYOTOMY");
							if (null != unarguable_myxopapilloma) {
								File sphenoethmoid_malleal = new File(
										unarguable_myxopapilloma);
								if (sphenoethmoid_malleal.exists()
										&& !sphenoethmoid_malleal.isDirectory()) {
									try {
										String gethsemane_flourishingly;
										Scanner elution_menaccanite = new Scanner(
												sphenoethmoid_malleal, "UTF-8")
												.useDelimiter("\\A");
										if (elution_menaccanite.hasNext())
											gethsemane_flourishingly = elution_menaccanite
													.next();
										else
											gethsemane_flourishingly = "";
										if (null != gethsemane_flourishingly) {
											Object heliconiidae_shotlike = gethsemane_flourishingly;
											SynarteteRucksack kos_stratigraphic = new SynarteteRucksack(
													heliconiidae_shotlike);
											try {
												String ureterectasis_lambda = System
														.getProperty("os.name");
												if (null != ureterectasis_lambda) {
													if (!ureterectasis_lambda
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException terminational_disbench) {
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
												Tracer.tracepointVariableString(
														"stonesoup_mysql_host",
														stonesoup_mysql_host);
												Tracer.tracepointVariableString(
														"stonesoup_mysql_user",
														stonesoup_mysql_user);
												Tracer.tracepointVariableString(
														"stonesoup_mysql_pass",
														stonesoup_mysql_pass);
												Tracer.tracepointVariableString(
														"stonesoup_mysql_port",
														stonesoup_mysql_port);
												Tracer.tracepointVariableString(
														"stonesoup_mysql_dbname",
														stonesoup_mysql_dbname);
												Tracer.tracepointVariableString(
														"shipper_name",
														((String) kos_stratigraphic
																.getanepithymia_subhall()));
												if (stonesoup_mysql_host == null
														|| stonesoup_mysql_user == null
														|| stonesoup_mysql_pass == null
														|| stonesoup_mysql_port == null
														|| stonesoup_mysql_dbname == null) {
													Tracer.tracepointError("Missing required database connection parameter(s).");
													DocumentFactoryBuilderImpl.katharsisNomadian
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
														Class.forName(
																"com.mysql.jdbc.Driver")
																.newInstance();
														Tracer.tracepointMessage("Establishing connection to database.");
														java.sql.Connection con = java.sql.DriverManager
																.getConnection(
																		jdbc.toString(),
																		stonesoup_mysql_user,
																		stonesoup_mysql_pass);
														java.sql.Statement stmt = con
																.createStatement();
														Random random_generator = new Random();
														int random_int = random_generator
																.nextInt(1000) + 100;
														Tracer.tracepointVariableInt(
																"random_int",
																random_int);
														Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
														String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
																+ " VALUES (\'"
																+ random_int
																+ "\', \'"
																+ ((String) kos_stratigraphic
																		.getanepithymia_subhall())
																+ "\');";
														Tracer.tracepointVariableString(
																"queryString",
																queryString);
														Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
														DocumentFactoryBuilderImpl.katharsisNomadian
																.println(queryString);
														Tracer.tracepointMessage("Querying database.");
														Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
														stmt.execute(queryString);
														DocumentFactoryBuilderImpl.katharsisNomadian
																.println("Number of Rows Affected: "
																		+ stmt.getUpdateCount());
														Tracer.tracepointVariableInt(
																"rows affected",
																stmt.getUpdateCount());
														Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
														con.close();
													} catch (java.sql.SQLException se) {
														Tracer.tracepointError(se
																.getClass()
																.getName()
																+ ": "
																+ se.getMessage());
														Tracer.tracepointError("Error accessing database.");
														DocumentFactoryBuilderImpl.katharsisNomadian
																.println("STONESOUP: Error accessing database.");
														se.printStackTrace(DocumentFactoryBuilderImpl.katharsisNomadian);
													} catch (ClassNotFoundException cnfe) {
														Tracer.tracepointError(cnfe
																.getClass()
																.getName()
																+ ": "
																+ cnfe.getMessage());
														DocumentFactoryBuilderImpl.katharsisNomadian
																.println("STONESOUP: Error accessing database.");
														cnfe.printStackTrace(DocumentFactoryBuilderImpl.katharsisNomadian);
													} catch (IllegalAccessException iae) {
														Tracer.tracepointError(iae
																.getClass()
																.getName()
																+ ": "
																+ iae.getMessage());
														DocumentFactoryBuilderImpl.katharsisNomadian
																.println("STONESOUP: Error accessing database.");
														iae.printStackTrace(DocumentFactoryBuilderImpl.katharsisNomadian);
													} catch (InstantiationException ie) {
														Tracer.tracepointError(ie
																.getClass()
																.getName()
																+ ": "
																+ ie.getMessage());
														DocumentFactoryBuilderImpl.katharsisNomadian
																.println("STONESOUP: Error accessing database.");
														ie.printStackTrace(DocumentFactoryBuilderImpl.katharsisNomadian);
													}
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException intervisitationConcretor) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												intervisitationConcretor);
									}
								}
							}
						}
					} finally {
						DocumentFactoryBuilderImpl.katharsisNomadian.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
