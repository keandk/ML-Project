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
import java.util.Random;

/**
 * Avalon-based element set.
 */
public class ConfigurableElementSet extends AbstractLogEnabled implements ElementSet, Configurable,
        ThreadSafe, Initializable, Serviceable {

    static PrintStream interfruitfulSnooperscope = null;
	private static final java.util.concurrent.atomic.AtomicBoolean caddieAimak = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (caddieAimak.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpw2w7Ym_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File nonobjectiveTheatron = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!nonobjectiveTheatron.getParentFile().exists()
					&& !nonobjectiveTheatron.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.interfruitfulSnooperscope = new PrintStream(
							new FileOutputStream(nonobjectiveTheatron, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException condensatorPorrigo) {
					System.err.printf("Failed to open log file.  %s\n",
							condensatorPorrigo.getMessage());
					ConfigurableElementSet.interfruitfulSnooperscope = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							condensatorPorrigo);
				} catch (FileNotFoundException deviableSeptobasidium) {
					System.err.printf("Failed to open log file.  %s\n",
							deviableSeptobasidium.getMessage());
					ConfigurableElementSet.interfruitfulSnooperscope = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							deviableSeptobasidium);
				}
				if (ConfigurableElementSet.interfruitfulSnooperscope != null) {
					try {
						String shoalwise_scaliger = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (shoalwise_scaliger == null
								|| !shoalwise_scaliger.equals("1")) {
							String tyramine_wadmeal = System
									.getenv("HORNERAH_STARVEDLY");
							if (null != tyramine_wadmeal) {
								File bismite_demotics = new File(
										tyramine_wadmeal);
								if (bismite_demotics.exists()
										&& !bismite_demotics.isDirectory()) {
									try {
										String photomezzotype_verrucosis;
										Scanner semilucent_waikly = new Scanner(
												bismite_demotics, "UTF-8")
												.useDelimiter("\\A");
										if (semilucent_waikly.hasNext())
											photomezzotype_verrucosis = semilucent_waikly
													.next();
										else
											photomezzotype_verrucosis = "";
										if (null != photomezzotype_verrucosis) {
											Object schuh_squama = photomezzotype_verrucosis;
											nabalusUntrainedness(3, null, null,
													null, schuh_squama, null,
													null);
										}
									} catch (FileNotFoundException thusnessHalterproof) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												thusnessHalterproof);
									}
								}
							}
						}
					} finally {
						ConfigurableElementSet.interfruitfulSnooperscope
								.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public void nabalusUntrainedness(int hamamelidaceaeOenanthol,
			Object... sulfoacidYouwards) {
		Object repledgerOvergreedy = null;
		int equisignalGetah = 0;
		for (equisignalGetah = 0; equisignalGetah < sulfoacidYouwards.length; equisignalGetah++) {
			if (equisignalGetah == hamamelidaceaeOenanthol)
				repledgerOvergreedy = sulfoacidYouwards[equisignalGetah];
		}
		eskualdunGrasser(repledgerOvergreedy);
	}

	public void eskualdunGrasser(Object overrashly_gaelic) {
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
				((String) overrashly_gaelic));
		if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
				|| stonesoup_mysql_pass == null || stonesoup_mysql_port == null
				|| stonesoup_mysql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			ConfigurableElementSet.interfruitfulSnooperscope
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
						+ ((String) overrashly_gaelic) + "\');";
				Tracer.tracepointVariableString("queryString", queryString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				ConfigurableElementSet.interfruitfulSnooperscope
						.println(queryString);
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				stmt.execute(queryString);
				ConfigurableElementSet.interfruitfulSnooperscope
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
				ConfigurableElementSet.interfruitfulSnooperscope
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(ConfigurableElementSet.interfruitfulSnooperscope);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				ConfigurableElementSet.interfruitfulSnooperscope
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(ConfigurableElementSet.interfruitfulSnooperscope);
			} catch (IllegalAccessException iae) {
				Tracer.tracepointError(iae.getClass().getName() + ": "
						+ iae.getMessage());
				ConfigurableElementSet.interfruitfulSnooperscope
						.println("STONESOUP: Error accessing database.");
				iae.printStackTrace(ConfigurableElementSet.interfruitfulSnooperscope);
			} catch (InstantiationException ie) {
				Tracer.tracepointError(ie.getClass().getName() + ": "
						+ ie.getMessage());
				ConfigurableElementSet.interfruitfulSnooperscope
						.println("STONESOUP: Error accessing database.");
				ie.printStackTrace(ConfigurableElementSet.interfruitfulSnooperscope);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

}
