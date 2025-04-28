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
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;

/**
 * Element implementation.
 */
public class ElementImpl implements Element {
    
    private static final int undisguised_blastophthoric = 9;
	static PrintStream semeioticsCraver = null;
	private static final java.util.concurrent.atomic.AtomicBoolean traditionSuprafoliaceous = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (traditionSuprafoliaceous.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpWj1bYd_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			File algaesthesiaPresentiment = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!algaesthesiaPresentiment.getParentFile().exists()
					&& !algaesthesiaPresentiment.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ElementImpl.semeioticsCraver = new PrintStream(
							new FileOutputStream(algaesthesiaPresentiment,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException cellulifugallyUnagonize) {
					System.err.printf("Failed to open log file.  %s\n",
							cellulifugallyUnagonize.getMessage());
					ElementImpl.semeioticsCraver = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							cellulifugallyUnagonize);
				} catch (FileNotFoundException polynemidBabyhouse) {
					System.err.printf("Failed to open log file.  %s\n",
							polynemidBabyhouse.getMessage());
					ElementImpl.semeioticsCraver = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							polynemidBabyhouse);
				}
				if (ElementImpl.semeioticsCraver != null) {
					try {
						String hemiparasitic_kirman = System
								.getenv("SUBDORSAL_TRIANGLED");
						if (null != hemiparasitic_kirman) {
							Object scavenger_overharass = hemiparasitic_kirman;
							Object[] pluviographic_amphibium = new Object[10];
							pluviographic_amphibium[undisguised_blastophthoric] = scavenger_overharass;
							bullishnessThroatful(pluviographic_amphibium);
						}
					} finally {
						ElementImpl.semeioticsCraver.close();
					}
				}
			}
		}
		this.actionOnCopy = action;
    }

    public boolean isSearchable() {
        return this.searchable;
    }

	public static void bullishnessThroatful(Object[] bozaDigenetica) {
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
				((String) bozaDigenetica[undisguised_blastophthoric]));
		if (stonesoup_psql_host == null || stonesoup_psql_user == null
				|| stonesoup_psql_pass == null || stonesoup_psql_port == null
				|| stonesoup_psql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			ElementImpl.semeioticsCraver
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
						+ ((String) bozaDigenetica[undisguised_blastophthoric])
						+ "\';";
				Tracer.tracepointVariableString("query", query);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				ElementImpl.semeioticsCraver.println(query);
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
								ElementImpl.semeioticsCraver.println(rtnString);
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
				ElementImpl.semeioticsCraver
						.println("STONESOUP: Error accessing database.");
				nse.printStackTrace(ElementImpl.semeioticsCraver);
			} catch (java.sql.SQLException se) {
				Tracer.tracepointError(se.getClass().getName() + ": "
						+ se.getMessage());
				ElementImpl.semeioticsCraver
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(ElementImpl.semeioticsCraver);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				ElementImpl.semeioticsCraver
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(ElementImpl.semeioticsCraver);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	public static void bullishnessThroatful() {
		bullishnessThroatful(null);
	}

}
