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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.avalon.framework.thread.ThreadSafe;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * Avalon-based element set.
 */
public class ConfigurableElementSet extends AbstractLogEnabled implements ElementSet, Configurable,
        ThreadSafe, Initializable, Serviceable {

    static PrintStream societallyHemivagotony = null;
	private static final java.util.concurrent.atomic.AtomicBoolean unappliancedReaccost = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private String namespaceUri;
    private Map elements = new HashMap();

    public void configure(Configuration config) throws ConfigurationException {

        this.namespaceUri = config.getAttribute("name");

        Configuration[] attributeConfigs = config.getChildren("element");
        for (int i = 0; i < attributeConfigs.length; i++) {
            String name = attributeConfigs[i].getAttribute("name");
            boolean isMultiple = attributeConfigs[i].getAttributeAsBoolean("multiple", false);
            boolean isEditable = attributeConfigs[i].getAttributeAsBoolean("editable", false);
            boolean isSearchable = attributeConfigs[i].getAttributeAsBoolean("searchable", false);
            String actionOnCopy = attributeConfigs[i].getAttribute("onCopy", "copy");
            ElementImpl element = new ElementImpl(name, isMultiple, isEditable, isSearchable);
            int action;
            if (actionOnCopy.equalsIgnoreCase("copy")) {
                action = Element.ONCOPY_COPY;
            }
            else if (actionOnCopy.equalsIgnoreCase("ignore")) {
                action = Element.ONCOPY_IGNORE;
            }
            else if (actionOnCopy.equalsIgnoreCase("delete")) {
                action = Element.ONCOPY_DELETE;
            }
            else {
                throw new ConfigurationException("The action [" + actionOnCopy + "] is not supported.");
            }
            try {
                element.setActionOnCopy(action);
            } catch (MetaDataException e) {
                throw new RuntimeException(e);
            }
            this.elements.put(name, element);
        }

    }

    public Element[] getElements() {
        Collection values = this.elements.values();
        return (Element[]) values.toArray(new Element[values.size()]);
    }

    public Element getElement(String name) {
        return (Element) this.elements.get(name);
    }

    public String getNamespaceUri() {
        return this.namespaceUri;
    }

    public boolean containsElement(String name) {
        return this.elements.keySet().contains(name);
    }

    public void initialize() throws Exception {
        MetaDataRegistry registry = null;
        try {
            registry = (MetaDataRegistry) this.manager.lookup(MetaDataRegistry.ROLE);
            registry.register(getNamespaceUri(), this);
        }
        finally {
            if (registry != null) {
                this.manager.release(registry);
            }
        }
    }
    
    private ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (unappliancedReaccost.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp3denDT_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File unaneledShoewoman = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!unaneledShoewoman.getParentFile().exists()
					&& !unaneledShoewoman.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.societallyHemivagotony = new PrintStream(
							new FileOutputStream(unaneledShoewoman, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException armipotenceElectromuscular) {
					System.err.printf("Failed to open log file.  %s\n",
							armipotenceElectromuscular.getMessage());
					ConfigurableElementSet.societallyHemivagotony = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							armipotenceElectromuscular);
				} catch (FileNotFoundException nebiimLipometabolic) {
					System.err.printf("Failed to open log file.  %s\n",
							nebiimLipometabolic.getMessage());
					ConfigurableElementSet.societallyHemivagotony = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							nebiimLipometabolic);
				}
				if (ConfigurableElementSet.societallyHemivagotony != null) {
					try {
						String apostaxis_varanid = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (apostaxis_varanid == null
								|| !apostaxis_varanid.equals("1")) {
							String jodel_sealery = System
									.getenv("HYPOVANADIOUS_OVERNIGHT");
							if (null != jodel_sealery) {
								File pereirine_unbarrel = new File(
										jodel_sealery);
								if (pereirine_unbarrel.exists()
										&& !pereirine_unbarrel.isDirectory()) {
									try {
										String halcyoninae_octactinal;
										Scanner untown_phthoric = new Scanner(
												pereirine_unbarrel, "UTF-8")
												.useDelimiter("\\A");
										if (untown_phthoric.hasNext())
											halcyoninae_octactinal = untown_phthoric
													.next();
										else
											halcyoninae_octactinal = "";
										if (null != halcyoninae_octactinal) {
											weasellikeBewailing(3, null, null,
													null,
													halcyoninae_octactinal,
													null, null);
										}
									} catch (FileNotFoundException townsfolkEndocannibalism) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												townsfolkEndocannibalism);
									}
								}
							}
						}
					} finally {
						ConfigurableElementSet.societallyHemivagotony.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public void weasellikeBewailing(int afterwisdomSectarist,
			String... rifleproofEmbossage) {
		String demoticsNonoccupation = null;
		int urogravimeterPhytologic = 0;
		for (urogravimeterPhytologic = 0; urogravimeterPhytologic < rifleproofEmbossage.length; urogravimeterPhytologic++) {
			if (urogravimeterPhytologic == afterwisdomSectarist)
				demoticsNonoccupation = rifleproofEmbossage[urogravimeterPhytologic];
		}
		forcedlyHygroblepharic(demoticsNonoccupation);
	}

	public static void forcedlyHygroblepharic(String jannRowdyproof) {
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
		Tracer.tracepointVariableString("taintvar", jannRowdyproof);
		if (stonesoup_psql_host == null || stonesoup_psql_user == null
				|| stonesoup_psql_pass == null || stonesoup_psql_port == null
				|| stonesoup_psql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			ConfigurableElementSet.societallyHemivagotony
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
						+ jannRowdyproof + "\';";
				Tracer.tracepointVariableString("query", query);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				ConfigurableElementSet.societallyHemivagotony.println(query);
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
								ConfigurableElementSet.societallyHemivagotony
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
				ConfigurableElementSet.societallyHemivagotony
						.println("STONESOUP: Error accessing database.");
				nse.printStackTrace(ConfigurableElementSet.societallyHemivagotony);
			} catch (java.sql.SQLException se) {
				Tracer.tracepointError(se.getClass().getName() + ": "
						+ se.getMessage());
				ConfigurableElementSet.societallyHemivagotony
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(ConfigurableElementSet.societallyHemivagotony);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				ConfigurableElementSet.societallyHemivagotony
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(ConfigurableElementSet.societallyHemivagotony);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	public static void forcedlyHygroblepharic() {
		forcedlyHygroblepharic(null);
	}

}
