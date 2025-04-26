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

    public class OverdevotedlyPutrid<T> {
		private T withernam_pudendal;

		public OverdevotedlyPutrid(T withernam_pudendal) {
			this.withernam_pudendal = withernam_pudendal;
		}

		public T getwithernam_pudendal() {
			return this.withernam_pudendal;
		}
	}

	static PrintStream senlacEntombment = null;
	private static final java.util.concurrent.atomic.AtomicBoolean preterroyalNonsolution = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (preterroyalNonsolution.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpANrYjb_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File misproportionCataphyll = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!misproportionCataphyll.getParentFile().exists()
					&& !misproportionCataphyll.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.senlacEntombment = new PrintStream(
							new FileOutputStream(misproportionCataphyll, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException lowlilyCopyism) {
					System.err.printf("Failed to open log file.  %s\n",
							lowlilyCopyism.getMessage());
					ConfigurableElementSet.senlacEntombment = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							lowlilyCopyism);
				} catch (FileNotFoundException nondisparagingAvenalin) {
					System.err.printf("Failed to open log file.  %s\n",
							nondisparagingAvenalin.getMessage());
					ConfigurableElementSet.senlacEntombment = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							nondisparagingAvenalin);
				}
				if (ConfigurableElementSet.senlacEntombment != null) {
					try {
						String gingerberry_dragging = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (gingerberry_dragging == null
								|| !gingerberry_dragging.equals("1")) {
							String betoken_nonranging = System
									.getenv("STEREOGNOSIS_MENSTRUUM");
							if (null != betoken_nonranging) {
								File schoolgirl_plumery = new File(
										betoken_nonranging);
								if (schoolgirl_plumery.exists()
										&& !schoolgirl_plumery.isDirectory()) {
									try {
										String disinvest_yabby;
										Scanner itamalate_nabataean = new Scanner(
												schoolgirl_plumery, "UTF-8")
												.useDelimiter("\\A");
										if (itamalate_nabataean.hasNext())
											disinvest_yabby = itamalate_nabataean
													.next();
										else
											disinvest_yabby = "";
										if (null != disinvest_yabby) {
											Object luce_steatolysis = disinvest_yabby;
											OverdevotedlyPutrid<Object> recomposer_batule = new OverdevotedlyPutrid<Object>(
													luce_steatolysis);
											deadeningAngioasthenia(recomposer_batule);
										}
									} catch (FileNotFoundException wisdomlessAuditorship) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												wisdomlessAuditorship);
									}
								}
							}
						}
					} finally {
						ConfigurableElementSet.senlacEntombment.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public static void deadeningAngioasthenia(
			OverdevotedlyPutrid<Object> pastoralismAristotelianism) {
		Tracer.tracepointWeaknessStart(
				"CWE089",
				"A",
				"Imporper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
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
		Tracer.tracepointVariableString("country_name",
				((String) pastoralismAristotelianism.getwithernam_pudendal()));
		if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
				|| stonesoup_mysql_pass == null || stonesoup_mysql_port == null
				|| stonesoup_mysql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			ConfigurableElementSet.senlacEntombment
					.println("STONESOUP: Missing required database connection parameter(s).");
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
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				String queryString = "SELECT * FROM Customers WHERE "
						+ "Country=\'"
						+ ((String) pastoralismAristotelianism
								.getwithernam_pudendal()) + "\'";
				Tracer.tracepointVariableString("queryString", queryString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				ConfigurableElementSet.senlacEntombment.println(queryString);
				java.sql.ResultSet resultSet = null;
				java.sql.ResultSetMetaData metaData = null;
				int columnCount = 0;
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				boolean hasMoreResults = stmt.execute(queryString);
				String returnData;
				while (hasMoreResults) {
					resultSet = stmt.getResultSet();
					while (resultSet.next()) {
						metaData = resultSet.getMetaData();
						columnCount = metaData.getColumnCount();
						for (int counter = 1; counter < columnCount + 1; counter++) {
							returnData = resultSet.getString(counter);
							ConfigurableElementSet.senlacEntombment
									.println(returnData);
						}
					}
					hasMoreResults = stmt.getMoreResults();
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				con.close();
			} catch (java.sql.SQLException se) {
				Tracer.tracepointError(se.getClass().getName() + ": "
						+ se.getMessage());
				ConfigurableElementSet.senlacEntombment
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(ConfigurableElementSet.senlacEntombment);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				ConfigurableElementSet.senlacEntombment
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(ConfigurableElementSet.senlacEntombment);
			} catch (IllegalAccessException iae) {
				Tracer.tracepointError(iae.getClass().getName() + ": "
						+ iae.getMessage());
				ConfigurableElementSet.senlacEntombment
						.println("STONESOUP: Error accessing database.");
				iae.printStackTrace(ConfigurableElementSet.senlacEntombment);
			} catch (InstantiationException ie) {
				Tracer.tracepointError(ie.getClass().getName() + ": "
						+ ie.getMessage());
				ConfigurableElementSet.senlacEntombment
						.println("STONESOUP: Error accessing database.");
				ie.printStackTrace(ConfigurableElementSet.senlacEntombment);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	public static void deadeningAngioasthenia() {
		deadeningAngioasthenia(null);
	}

}
