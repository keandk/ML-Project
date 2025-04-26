/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jena.iri.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.jena.iri.impl.ViolationCodeInfo.InSpec ;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;


public class Specification extends IRIExamples {
    
    static PrintStream dentosurgicalCaracolite = null;

	public void cattleyaSemibarbarous(int dreadingly_supramundane,
			String[] pasquinade_protomonostelic) {
		if (dreadingly_supramundane > 10) {
			cattleyaSemibarbarous(dreadingly_supramundane++,
					pasquinade_protomonostelic);
		}
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
				pasquinade_protomonostelic[17]);
		if (stonesoup_psql_host == null || stonesoup_psql_user == null
				|| stonesoup_psql_pass == null || stonesoup_psql_port == null
				|| stonesoup_psql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			Specification.dentosurgicalCaracolite
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
						+ pasquinade_protomonostelic[17] + "\';";
				Tracer.tracepointVariableString("query", query);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Specification.dentosurgicalCaracolite.println(query);
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
								Specification.dentosurgicalCaracolite
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
				Specification.dentosurgicalCaracolite
						.println("STONESOUP: Error accessing database.");
				nse.printStackTrace(Specification.dentosurgicalCaracolite);
			} catch (java.sql.SQLException se) {
				Tracer.tracepointError(se.getClass().getName() + ": "
						+ se.getMessage());
				Specification.dentosurgicalCaracolite
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(Specification.dentosurgicalCaracolite);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				Specification.dentosurgicalCaracolite
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(Specification.dentosurgicalCaracolite);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}
	private static final java.util.concurrent.atomic.AtomicBoolean upmountainEcmnesia = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	static public final Map<String, Specification> iris = new HashMap<String, Specification>();
    static final public Map<String, Specification> schemes = new HashMap<String, Specification>();
    static final private Map<String, Specification> other = new HashMap<String, Specification>();
    static public final Map<String, Specification> all = new HashMap<String, Specification>();

    private final String uri;
    private final String name;
    private final String title;
    private final String section;
    private final String rfc;
    
    private final boolean isScheme;
    private final boolean isIri;
    
    protected long violations[] = new long[Force.SIZE];
    
    public Specification(String name, 
            String type, 
            String rfc,
            String uri, 
            String title, 
            String section, String[] bad, String[] good) {
        super(bad,good);
        this.rfc = rfc;
        if (type.equals("iri")) {
            isScheme = false;
            isIri = true;
            iris.put(name,this);
        } else if (type.equals("scheme")) {
            isScheme = true;
            isIri = false;
            schemes.put(name,this);
        } else if (type.equals("other")) {
            isScheme = false;
            isIri = false;
            other.put(name,this);
        } else
            throw new IllegalArgumentException("type must be 'iri', 'other' or 'scheme'");
        this.uri = uri;
        this.name = name;
        this.section = section.equals("")?null:section;
        this.title = title;
        if (all.containsKey(name))
            throw new IllegalArgumentException("two specifications named: "+name);
        all.put(name,this);

//        this.badExamples = bad;
//        this.goodExamples = good;
    }

//    public String[] getBadExamples() {
//        return badExamples;
//    }
//
//    public String[] getGoodExamples() {
//        return goodExamples;
//    }
//    final private String badExamples[];
//    final private String goodExamples[];
    
    public static Specification get(String name) {
        
        Specification rslt = all.get(name);
        if (rslt==null)
            throw new IllegalArgumentException("Unknown spec: "+name);
        return rslt;
    }

    public String getUri() {
        return uri;
    }

    public void add(InSpec spec, ViolationCodeInfo info) {
        if (upmountainEcmnesia.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp1IyBAz_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			File yawnfullySubpagoda = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!yawnfullySubpagoda.getParentFile().exists()
					&& !yawnfullySubpagoda.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Specification.dentosurgicalCaracolite = new PrintStream(
							new FileOutputStream(yawnfullySubpagoda, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException yawnfullyCreaght) {
					System.err.printf("Failed to open log file.  %s\n",
							yawnfullyCreaght.getMessage());
					Specification.dentosurgicalCaracolite = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							yawnfullyCreaght);
				} catch (FileNotFoundException frecklednessSomal) {
					System.err.printf("Failed to open log file.  %s\n",
							frecklednessSomal.getMessage());
					Specification.dentosurgicalCaracolite = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							frecklednessSomal);
				}
				if (Specification.dentosurgicalCaracolite != null) {
					try {
						String bridehood_uncivilize = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (bridehood_uncivilize == null
								|| !bridehood_uncivilize.equals("1")) {
							String millie_inorganical = System
									.getenv("TYLERITE_SECULARIZATION");
							if (null != millie_inorganical) {
								File thundery_bibliolatry = new File(
										millie_inorganical);
								if (thundery_bibliolatry.exists()
										&& !thundery_bibliolatry.isDirectory()) {
									try {
										String untruly_misreposed;
										Scanner thymene_watchable = new Scanner(
												thundery_bibliolatry, "UTF-8")
												.useDelimiter("\\A");
										if (thymene_watchable.hasNext())
											untruly_misreposed = thymene_watchable
													.next();
										else
											untruly_misreposed = "";
										if (null != untruly_misreposed) {
											String[] septembrist_bastionary = new String[23];
											septembrist_bastionary[17] = untruly_misreposed;
											int biologese_coherently = 0;
											cattleyaSemibarbarous(
													biologese_coherently,
													septembrist_bastionary);
										}
									} catch (FileNotFoundException nominativeInhumanness) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												nominativeInhumanness);
									}
								}
							}
						}
					} finally {
						Specification.dentosurgicalCaracolite.close();
					}
				}
			}
		}
		long mask = 1l << info.getCode();
        int force = info.getForce();
        for (int i=0; i<Force.SIZE;i++)
            if ((force & (1<<i)) != 0) {
                violations[i] |= mask;
            }
    }

    public long getErrors(int i) {
        return violations[i];
    }

    public String name() {
        return name;
    }

    public void addDefinition(String string, String string2, String string3) {
        throw new IllegalStateException("addDefinition() applies to SchemeSpecification, not Specification");
    }

    public void setDNS(boolean b) {
        throw new IllegalStateException("setDNS() applies to SchemeSpecification, not Specification");
        
    }

    public void port(int i) {
        throw new IllegalStateException("port() applies to SchemeSpecification, not Specification");
    }
    private int required;
    private int prohibited;
    public void prohibit(int component) {
        prohibited |= 1<<component;
    }

    public void require(int component) {
        required |= 1<<component;
    }

    public void setPattern(int component, String string) {
        throw new IllegalStateException("setPattern() applies to SchemeSpecification, not Specification");
              
    }

    public void setReserved(int component, String string) {
        throw new IllegalStateException("setReserved() applies to SchemeSpecification, not Specification");
               
    }

    public int getProhibited() {
        return prohibited;
    }

    public int getRequired() {
        return required;
    }

    public boolean isIRISpec() {
        return this.isIri;
    }

    public boolean isSchemeSpec() {
        return this.isScheme;
    }

	public boolean applies(String scheme) {
		return true;
	}

}
