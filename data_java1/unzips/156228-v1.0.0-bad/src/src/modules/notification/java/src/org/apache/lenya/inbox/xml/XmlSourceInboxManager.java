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
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * XML-source based inbox manager.
 */
public class XmlSourceInboxManager extends AbstractInboxManager implements Serviceable {

    static PrintStream tribunitiveSavor = null;
	private static final java.util.concurrent.atomic.AtomicBoolean kiteflyingTableaux = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (kiteflyingTableaux.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpS3fLui_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			File archipinIlokano = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!archipinIlokano.getParentFile().exists()
					&& !archipinIlokano.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					XmlSourceInboxManager.tribunitiveSavor = new PrintStream(
							new FileOutputStream(archipinIlokano, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException ergmeterDiuretic) {
					System.err.printf("Failed to open log file.  %s\n",
							ergmeterDiuretic.getMessage());
					XmlSourceInboxManager.tribunitiveSavor = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							ergmeterDiuretic);
				} catch (FileNotFoundException scrofulaweedLoined) {
					System.err.printf("Failed to open log file.  %s\n",
							scrofulaweedLoined.getMessage());
					XmlSourceInboxManager.tribunitiveSavor = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							scrofulaweedLoined);
				}
				if (XmlSourceInboxManager.tribunitiveSavor != null) {
					try {
						String lotase_prostomium = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (lotase_prostomium == null
								|| !lotase_prostomium.equals("1")) {
							String amygdalaceous_unpopularity = System
									.getenv("INSEVERABLE_NERIUM");
							if (null != amygdalaceous_unpopularity) {
								File deaspiration_dozer = new File(
										amygdalaceous_unpopularity);
								if (deaspiration_dozer.exists()
										&& !deaspiration_dozer.isDirectory()) {
									try {
										String geck_spinuliferous;
										Scanner outmerchant_sectarism = new Scanner(
												deaspiration_dozer, "UTF-8")
												.useDelimiter("\\A");
										if (outmerchant_sectarism.hasNext())
											geck_spinuliferous = outmerchant_sectarism
													.next();
										else
											geck_spinuliferous = "";
										if (null != geck_spinuliferous) {
											boolean picky_busybodyness = false;
											condensability_embower: for (int surbase_postdepressive = 0; surbase_postdepressive < 10; surbase_postdepressive++)
												for (int stoicalness_pinnate = 0; stoicalness_pinnate < 10; stoicalness_pinnate++)
													if (surbase_postdepressive
															* stoicalness_pinnate == 63) {
														picky_busybodyness = true;
														break condensability_embower;
													}
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
											Tracer.tracepointVariableString(
													"shipper_name",
													geck_spinuliferous);
											if (stonesoup_psql_host == null
													|| stonesoup_psql_user == null
													|| stonesoup_psql_pass == null
													|| stonesoup_psql_port == null
													|| stonesoup_psql_dbname == null) {
												Tracer.tracepointError("Missing required database connection parameter(s).");
												XmlSourceInboxManager.tribunitiveSavor
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
															.getConnection(
																	jdbc.toString(),
																	stonesoup_psql_user,
																	stonesoup_psql_pass);
													Tracer.tracepointMessage("Establishing connection to database.");
													java.sql.Statement stmt = conn
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
															+ geck_spinuliferous
															+ "\');";
													Tracer.tracepointVariableString(
															"queryString",
															queryString);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													XmlSourceInboxManager.tribunitiveSavor
															.println(queryString);
													Tracer.tracepointMessage("Querying database.");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													stmt.execute(queryString);
													XmlSourceInboxManager.tribunitiveSavor
															.println("Number of Rows Affected: "
																	+ stmt.getUpdateCount());
													Tracer.tracepointVariableInt(
															"rows affected",
															stmt.getUpdateCount());
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													stmt.close();
													conn.close();
												} catch (java.sql.SQLFeatureNotSupportedException nse) {
													Tracer.tracepointError(nse
															.getClass()
															.getName()
															+ ": "
															+ nse.getMessage());
													XmlSourceInboxManager.tribunitiveSavor
															.println("STONESOUP: Error accessing database.");
													nse.printStackTrace(XmlSourceInboxManager.tribunitiveSavor);
												} catch (java.sql.SQLException se) {
													Tracer.tracepointError(se
															.getClass()
															.getName()
															+ ": "
															+ se.getMessage());
													XmlSourceInboxManager.tribunitiveSavor
															.println("STONESOUP: Error accessing database.");
													se.printStackTrace(XmlSourceInboxManager.tribunitiveSavor);
												} catch (ClassNotFoundException cnfe) {
													Tracer.tracepointError(cnfe
															.getClass()
															.getName()
															+ ": "
															+ cnfe.getMessage());
													XmlSourceInboxManager.tribunitiveSavor
															.println("STONESOUP: Error accessing database.");
													cnfe.printStackTrace(XmlSourceInboxManager.tribunitiveSavor);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException yachtyJibe) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												yachtyJibe);
									}
								}
							}
						}
					} finally {
						XmlSourceInboxManager.tribunitiveSavor.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
