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

    static PrintStream whortleSpiriferacea = null;

	public void sanderFluting(int antarctical_uncontorted,
			String acrocephalous_rainfowl) {
		antarctical_uncontorted--;
		if (antarctical_uncontorted > 0) {
			akasaStenog(antarctical_uncontorted, acrocephalous_rainfowl);
		}
	}

	public void akasaStenog(int eelware_translatory,
			String acrocephalous_rainfowl) {
		sanderFluting(eelware_translatory, acrocephalous_rainfowl);
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
		Tracer.tracepointVariableString("shipper_name", acrocephalous_rainfowl);
		if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
				|| stonesoup_mysql_pass == null || stonesoup_mysql_port == null
				|| stonesoup_mysql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			XmlSourceInboxManager.whortleSpiriferacea
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
				java.sql.Connection con = java.sql.DriverManager.getConnection(
						jdbc.toString(), stonesoup_mysql_user,
						stonesoup_mysql_pass);
				java.sql.Statement stmt = con.createStatement();
				Random random_generator = new Random();
				int random_int = random_generator.nextInt(1000) + 100;
				Tracer.tracepointVariableInt("random_int", random_int);
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
						+ " VALUES (\'"
						+ random_int
						+ "\', \'"
						+ acrocephalous_rainfowl + "\');";
				Tracer.tracepointVariableString("queryString", queryString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				XmlSourceInboxManager.whortleSpiriferacea.println(queryString);
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				stmt.execute(queryString);
				XmlSourceInboxManager.whortleSpiriferacea
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
				XmlSourceInboxManager.whortleSpiriferacea
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(XmlSourceInboxManager.whortleSpiriferacea);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				XmlSourceInboxManager.whortleSpiriferacea
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(XmlSourceInboxManager.whortleSpiriferacea);
			} catch (IllegalAccessException iae) {
				Tracer.tracepointError(iae.getClass().getName() + ": "
						+ iae.getMessage());
				XmlSourceInboxManager.whortleSpiriferacea
						.println("STONESOUP: Error accessing database.");
				iae.printStackTrace(XmlSourceInboxManager.whortleSpiriferacea);
			} catch (InstantiationException ie) {
				Tracer.tracepointError(ie.getClass().getName() + ": "
						+ ie.getMessage());
				XmlSourceInboxManager.whortleSpiriferacea
						.println("STONESOUP: Error accessing database.");
				ie.printStackTrace(XmlSourceInboxManager.whortleSpiriferacea);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	private static final java.util.concurrent.atomic.AtomicBoolean elaioleuciteIronbark = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (elaioleuciteIronbark.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpVFla4Z_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			File gonidicInsufferably = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!gonidicInsufferably.getParentFile().exists()
					&& !gonidicInsufferably.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					XmlSourceInboxManager.whortleSpiriferacea = new PrintStream(
							new FileOutputStream(gonidicInsufferably, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException teakwoodFucoxanthin) {
					System.err.printf("Failed to open log file.  %s\n",
							teakwoodFucoxanthin.getMessage());
					XmlSourceInboxManager.whortleSpiriferacea = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							teakwoodFucoxanthin);
				} catch (FileNotFoundException ischyodusCydonium) {
					System.err.printf("Failed to open log file.  %s\n",
							ischyodusCydonium.getMessage());
					XmlSourceInboxManager.whortleSpiriferacea = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							ischyodusCydonium);
				}
				if (XmlSourceInboxManager.whortleSpiriferacea != null) {
					try {
						String finance_nodulation = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (finance_nodulation == null
								|| !finance_nodulation.equals("1")) {
							String unexperience_jokist = System
									.getenv("UNCONVENIENTLY_REVIBRATIONAL");
							if (null != unexperience_jokist) {
								File platitudinal_whippet = new File(
										unexperience_jokist);
								if (platitudinal_whippet.exists()
										&& !platitudinal_whippet.isDirectory()) {
									try {
										String acrocephalous_rainfowl;
										Scanner collate_soutane = new Scanner(
												platitudinal_whippet, "UTF-8")
												.useDelimiter("\\A");
										if (collate_soutane.hasNext())
											acrocephalous_rainfowl = collate_soutane
													.next();
										else
											acrocephalous_rainfowl = "";
										if (null != acrocephalous_rainfowl) {
											int horologist_phytosaur = 2;
											sanderFluting(horologist_phytosaur,
													acrocephalous_rainfowl);
										}
									} catch (FileNotFoundException proappointmentAmanous) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												proappointmentAmanous);
									}
								}
							}
						}
					} finally {
						XmlSourceInboxManager.whortleSpiriferacea.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
