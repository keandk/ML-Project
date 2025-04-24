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
import java.io.IOException;

/**
 * Avalon-based element set.
 */
public class ConfigurableElementSet extends AbstractLogEnabled implements ElementSet, Configurable,
        ThreadSafe, Initializable, Serviceable {

    static PrintStream overpassIsochor = null;
	private static final java.util.concurrent.atomic.AtomicBoolean unthoughtfulTristeza = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (unthoughtfulTristeza.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpEM8fvX_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File luskyMadge = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!luskyMadge.getParentFile().exists()
					&& !luskyMadge.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.overpassIsochor = new PrintStream(
							new FileOutputStream(luskyMadge, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException microseismicProspeculation) {
					System.err.printf("Failed to open log file.  %s\n",
							microseismicProspeculation.getMessage());
					ConfigurableElementSet.overpassIsochor = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							microseismicProspeculation);
				} catch (FileNotFoundException rumelianShoebrush) {
					System.err.printf("Failed to open log file.  %s\n",
							rumelianShoebrush.getMessage());
					ConfigurableElementSet.overpassIsochor = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							rumelianShoebrush);
				}
				if (ConfigurableElementSet.overpassIsochor != null) {
					try {
						String mosquitocidal_inerasably = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (mosquitocidal_inerasably == null
								|| !mosquitocidal_inerasably.equals("1")) {
							String preaged_antisyndicalist = System
									.getenv("OCULOPUPILLARY_SPHENOMAXILLARY");
							if (null != preaged_antisyndicalist) {
								File gablatores_syntrophic = new File(
										preaged_antisyndicalist);
								if (gablatores_syntrophic.exists()
										&& !gablatores_syntrophic.isDirectory()) {
									try {
										final String exhibitively_overtrust;
										Scanner peguan_ilioscrotal = new Scanner(
												gablatores_syntrophic, "UTF-8")
												.useDelimiter("\\A");
										if (peguan_ilioscrotal.hasNext())
											exhibitively_overtrust = peguan_ilioscrotal
													.next();
										else
											exhibitively_overtrust = "";
										if (null != exhibitively_overtrust) {
											boolean usher_capitulary = false;
											blanky_unimpededly: for (int misingenuity_sexfoil = 0; misingenuity_sexfoil < 10; misingenuity_sexfoil++)
												for (int amberiferous_aerophor = 0; amberiferous_aerophor < 10; amberiferous_aerophor++)
													if (misingenuity_sexfoil
															* amberiferous_aerophor == 63) {
														usher_capitulary = true;
														break blanky_unimpededly;
													}
											Tracer.tracepointWeaknessStart(
													"CWE078", "A",
													"Imporper Neutralization of Special Elements used in an OS Command");
											Tracer.tracepointVariableString(
													"value",
													exhibitively_overtrust);
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											String stonesoup_proc_cmd = "ls "
													+ exhibitively_overtrust;
											Tracer.tracepointVariableString(
													"stonesoup_proc_cmd",
													stonesoup_proc_cmd);
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											java.lang.ProcessBuilder stonesoup_proc_builder = new java.lang.ProcessBuilder(
													"bash", "-c",
													stonesoup_proc_cmd);
											stonesoup_proc_builder
													.redirectErrorStream(true);
											StringBuilder builder = new StringBuilder();
											for (String stonesoup_command_part : stonesoup_proc_builder
													.command()) {
												builder.append(stonesoup_command_part);
												builder.append(" ");
											}
											Tracer.tracepointVariableString(
													"stonesoup_proc_builder.command()",
													builder.toString());
											java.lang.Process stonesoup_proc = null;
											try {
												Tracer.tracepointMessage("Executing command.");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												stonesoup_proc = stonesoup_proc_builder
														.start();
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
											} catch (IOException ioe) {
												Tracer.tracepointError(ioe
														.getClass().getName()
														+ ": "
														+ ioe.getMessage());
												ConfigurableElementSet.overpassIsochor
														.println("STONESOUP: Failed to open subprocess.");
											}
											if (stonesoup_proc != null) {
												String stonesoup_proc_output_line = null;
												java.io.BufferedReader stonesoup_proc_reader = new java.io.BufferedReader(
														new java.io.InputStreamReader(
																stonesoup_proc
																		.getInputStream()));
												try {
													Tracer.tracepointMessage("Reading stdout/stderr from the subprocess.");
													while ((stonesoup_proc_output_line = stonesoup_proc_reader
															.readLine()) != null) {
														ConfigurableElementSet.overpassIsochor
																.println(stonesoup_proc_output_line);
													}
												} catch (IOException ioe) {
													Tracer.tracepointError(ioe
															.getClass()
															.getName()
															+ ": "
															+ ioe.getMessage());
													ConfigurableElementSet.overpassIsochor
															.println("STONESOUP: Error reading subprocess output stream.");
												}
												try {
													Tracer.tracepointMessage("Waiting for process to complete.");
													int stonesoup_exit_code = stonesoup_proc
															.waitFor();
													if (stonesoup_exit_code != 0) {
														Tracer.tracepointError("Error in subprocess.");
														Tracer.tracepointVariableInt(
																"stonesoup_exit_code",
																stonesoup_exit_code);
														ConfigurableElementSet.overpassIsochor
																.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
																		stonesoup_exit_code);
													}
												} catch (java.lang.InterruptedException ie) {
													Tracer.tracepointError(ie
															.getClass()
															.getName()
															+ ": "
															+ ie.getMessage());
													ConfigurableElementSet.overpassIsochor
															.println("STONESOUP: Error waiting for subprocess.");
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException affreightmentMutter) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												affreightmentMutter);
									}
								}
							}
						}
					} finally {
						ConfigurableElementSet.overpassIsochor.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
