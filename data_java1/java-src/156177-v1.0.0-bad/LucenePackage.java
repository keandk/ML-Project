package org.apache.lucene;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

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

  public static interface IHaunterOccipitobasilar {
		public void hematoblastUnquelled(Object hemiolic_lipacidemia);
	}

	public static class ThornedHydrocinnamic implements IHaunterOccipitobasilar {
		@Override
		public void hematoblastUnquelled(Object hemiolic_lipacidemia) {
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
			Tracer.tracepointVariableString("taintvar",
					((String) hemiolic_lipacidemia));
			if (stonesoup_psql_host == null || stonesoup_psql_user == null
					|| stonesoup_psql_pass == null
					|| stonesoup_psql_port == null
					|| stonesoup_psql_dbname == null) {
				Tracer.tracepointError("Missing required database connection parameter(s).");
				LucenePackage.miscognizantWyomingite
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
							.getConnection(jdbc.toString(),
									stonesoup_psql_user, stonesoup_psql_pass);
					java.sql.Statement stmt = conn.createStatement();
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					String query = "SELECT * FROM customers WHERE country =\'"
							+ ((String) hemiolic_lipacidemia) + "\';";
					Tracer.tracepointVariableString("query", query);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					LucenePackage.miscognizantWyomingite.println(query);
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
									LucenePackage.miscognizantWyomingite
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
					LucenePackage.miscognizantWyomingite
							.println("STONESOUP: Error accessing database.");
					nse.printStackTrace(LucenePackage.miscognizantWyomingite);
				} catch (java.sql.SQLException se) {
					Tracer.tracepointError(se.getClass().getName() + ": "
							+ se.getMessage());
					LucenePackage.miscognizantWyomingite
							.println("STONESOUP: Error accessing database.");
					se.printStackTrace(LucenePackage.miscognizantWyomingite);
				} catch (ClassNotFoundException cnfe) {
					Tracer.tracepointError(cnfe.getClass().getName() + ": "
							+ cnfe.getMessage());
					LucenePackage.miscognizantWyomingite
							.println("STONESOUP: Error accessing database.");
					cnfe.printStackTrace(LucenePackage.miscognizantWyomingite);
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

	static PrintStream miscognizantWyomingite = null;
	private static final java.util.concurrent.atomic.AtomicBoolean blueprintAalii = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (blueprintAalii.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpMipC7d_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		File deadwoodFundulinae = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!deadwoodFundulinae.getParentFile().exists()
				&& !deadwoodFundulinae.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.miscognizantWyomingite = new PrintStream(
						new FileOutputStream(deadwoodFundulinae, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException postlenticularBeedged) {
				System.err.printf("Failed to open log file.  %s\n",
						postlenticularBeedged.getMessage());
				LucenePackage.miscognizantWyomingite = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						postlenticularBeedged);
			} catch (FileNotFoundException cheyenneNonaccess) {
				System.err.printf("Failed to open log file.  %s\n",
						cheyenneNonaccess.getMessage());
				LucenePackage.miscognizantWyomingite = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						cheyenneNonaccess);
			}
			if (LucenePackage.miscognizantWyomingite != null) {
				try {
					String pseudoanemic_disharmonical = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (pseudoanemic_disharmonical == null
							|| !pseudoanemic_disharmonical.equals("1")) {
						String overmaster_abthain = System
								.getenv("RELICK_PIPEWORT");
						if (null != overmaster_abthain) {
							File disintrench_collins = new File(
									overmaster_abthain);
							if (disintrench_collins.exists()
									&& !disintrench_collins.isDirectory()) {
								try {
									String unbrained_tideway;
									Scanner nonoccurrence_pock = new Scanner(
											disintrench_collins, "UTF-8")
											.useDelimiter("\\A");
									if (nonoccurrence_pock.hasNext())
										unbrained_tideway = nonoccurrence_pock
												.next();
									else
										unbrained_tideway = "";
									if (null != unbrained_tideway) {
										Object frumenty_venerate = unbrained_tideway;
										IHaunterOccipitobasilar antiphonon_gymnical = new ThornedHydrocinnamic();
										antiphonon_gymnical
												.hematoblastUnquelled(frumenty_venerate);
									}
								} catch (FileNotFoundException deerweedGeologian) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											deerweedGeologian);
								}
							}
						}
					}
				} finally {
					LucenePackage.miscognizantWyomingite.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }
}
