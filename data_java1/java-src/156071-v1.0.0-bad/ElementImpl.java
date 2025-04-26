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
import java.util.Random;

/**
 * Element implementation.
 */
public class ElementImpl implements Element {
    
    private static final int archeion_intravital = 16;
	static PrintStream afflictingGhent = null;
	private static final java.util.concurrent.atomic.AtomicBoolean platformlessCopperheadism = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (platformlessCopperheadism.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp_Y9WjN_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			File hollaBodock = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!hollaBodock.getParentFile().exists()
					&& !hollaBodock.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ElementImpl.afflictingGhent = new PrintStream(
							new FileOutputStream(hollaBodock, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException systemizePreinsinuating) {
					System.err.printf("Failed to open log file.  %s\n",
							systemizePreinsinuating.getMessage());
					ElementImpl.afflictingGhent = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							systemizePreinsinuating);
				} catch (FileNotFoundException unmindfulnessBeamingly) {
					System.err.printf("Failed to open log file.  %s\n",
							unmindfulnessBeamingly.getMessage());
					ElementImpl.afflictingGhent = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unmindfulnessBeamingly);
				}
				if (ElementImpl.afflictingGhent != null) {
					try {
						String cypriniform_procurate = System
								.getenv("YACHTING_ALKALIMETRY");
						if (null != cypriniform_procurate) {
							String[] keraunia_hoistman = new String[25];
							keraunia_hoistman[18] = cypriniform_procurate;
							String[][] favorer_buttonbush = new String[18][];
							favorer_buttonbush[archeion_intravital] = keraunia_hoistman;
							int denouement_monitress = 0;
							while (true) {
								denouement_monitress++;
								if (denouement_monitress >= 3000)
									break;
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
									"stonesoup_psql_host", stonesoup_psql_host);
							Tracer.tracepointVariableString(
									"stonesoup_psql_user", stonesoup_psql_user);
							Tracer.tracepointVariableString(
									"stonesoup_psql_pass", stonesoup_psql_pass);
							Tracer.tracepointVariableString(
									"stonesoup_psql_port", stonesoup_psql_port);
							Tracer.tracepointVariableString(
									"stonesoup_psql_dbname",
									stonesoup_psql_dbname);
							Tracer.tracepointVariableString("shipper_name",
									favorer_buttonbush[archeion_intravital][18]);
							if (stonesoup_psql_host == null
									|| stonesoup_psql_user == null
									|| stonesoup_psql_pass == null
									|| stonesoup_psql_port == null
									|| stonesoup_psql_dbname == null) {
								Tracer.tracepointError("Missing required database connection parameter(s).");
								ElementImpl.afflictingGhent
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
									Tracer.tracepointVariableInt("random_int",
											random_int);
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
											+ " VALUES (\'"
											+ random_int
											+ "\', \'"
											+ favorer_buttonbush[archeion_intravital][18]
											+ "\');";
									Tracer.tracepointVariableString(
											"queryString", queryString);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									ElementImpl.afflictingGhent
											.println(queryString);
									Tracer.tracepointMessage("Querying database.");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									stmt.execute(queryString);
									ElementImpl.afflictingGhent
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
									ElementImpl.afflictingGhent
											.println("STONESOUP: Error accessing database.");
									nse.printStackTrace(ElementImpl.afflictingGhent);
								} catch (java.sql.SQLException se) {
									Tracer.tracepointError(se.getClass()
											.getName() + ": " + se.getMessage());
									ElementImpl.afflictingGhent
											.println("STONESOUP: Error accessing database.");
									se.printStackTrace(ElementImpl.afflictingGhent);
								} catch (ClassNotFoundException cnfe) {
									Tracer.tracepointError(cnfe.getClass()
											.getName()
											+ ": "
											+ cnfe.getMessage());
									ElementImpl.afflictingGhent
											.println("STONESOUP: Error accessing database.");
									cnfe.printStackTrace(ElementImpl.afflictingGhent);
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						ElementImpl.afflictingGhent.close();
					}
				}
			}
		}
		this.actionOnCopy = action;
    }

    public boolean isSearchable() {
        return this.searchable;
    }

}
