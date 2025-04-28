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
import java.io.IOException;

/**
 * Avalon-based element set.
 */
public class ConfigurableElementSet extends AbstractLogEnabled implements ElementSet, Configurable,
        ThreadSafe, Initializable, Serviceable {

    static PrintStream perfectionmentDiductor = null;
	private static final java.util.concurrent.atomic.AtomicBoolean nonfermentativeHitchy = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (nonfermentativeHitchy.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpwjhssj_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File dimensionlessTrappous = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!dimensionlessTrappous.getParentFile().exists()
					&& !dimensionlessTrappous.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.perfectionmentDiductor = new PrintStream(
							new FileOutputStream(dimensionlessTrappous, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException reignitionSelihoth) {
					System.err.printf("Failed to open log file.  %s\n",
							reignitionSelihoth.getMessage());
					ConfigurableElementSet.perfectionmentDiductor = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							reignitionSelihoth);
				} catch (FileNotFoundException perichordalDicyanogen) {
					System.err.printf("Failed to open log file.  %s\n",
							perichordalDicyanogen.getMessage());
					ConfigurableElementSet.perfectionmentDiductor = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							perichordalDicyanogen);
				}
				if (ConfigurableElementSet.perfectionmentDiductor != null) {
					try {
						String gasteralgia_gestaltist = System
								.getenv("RODENT_QUADRIBASIC");
						if (null != gasteralgia_gestaltist) {
							Object petcock_ngoko = gasteralgia_gestaltist;
							try {
								String iambus_triumvirship = System
										.getProperty("os.name");
								if (null != iambus_triumvirship) {
									if (!iambus_triumvirship
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException peloric_basion) {
							} finally {
								Tracer.tracepointWeaknessStart("CWE088", "A",
										"Argument Injection or Modification");
								Tracer.tracepointVariableString("value",
										((String) petcock_ngoko));
								String stonesoup_proc_cmd = "find . -iname ";
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								stonesoup_proc_cmd += ((String) petcock_ngoko);
								Tracer.tracepointVariableString(
										"stonesoup_proc_cmd",
										stonesoup_proc_cmd);
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								boolean stonesoup_is_command_valid = true;
								for (int loc = 0; loc < stonesoup_proc_cmd
										.length(); loc++) {
									if ((stonesoup_proc_cmd.charAt(loc) == ';')
											&& stonesoup_proc_cmd
													.charAt(loc - 1) != '\\') {
										Tracer.tracepointMessage("Invalid command, shell escape detected.");
										ConfigurableElementSet.perfectionmentDiductor
												.println("Invalid command, shell escape detected.");
										stonesoup_is_command_valid = false;
									}
								}
								if (stonesoup_is_command_valid) {
									java.lang.ProcessBuilder stonesoup_proc_builder = new java.lang.ProcessBuilder(
											"bash", "-c", stonesoup_proc_cmd);
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
										Tracer.tracepointError(ioe.getClass()
												.getName()
												+ ": "
												+ ioe.getMessage());
										ConfigurableElementSet.perfectionmentDiductor
												.println("STONESOUP: Failed to open subprocess.");
									}
									if (stonesoup_proc != null) {
										String stonesoup_proc_output_line = null;
										java.io.BufferedReader stonesoup_proc_reader = new java.io.BufferedReader(
												new java.io.InputStreamReader(
														stonesoup_proc
																.getInputStream()));
										try {
											Tracer.tracepointMessage("Reading stdout/stderr from subprocess.");
											while ((stonesoup_proc_output_line = stonesoup_proc_reader
													.readLine()) != null) {
												ConfigurableElementSet.perfectionmentDiductor
														.println(stonesoup_proc_output_line);
											}
										} catch (IOException ioe) {
											Tracer.tracepointError(ioe
													.getClass().getName()
													+ ": " + ioe.getMessage());
											ConfigurableElementSet.perfectionmentDiductor
													.println("STONESOUP: Error reading subprocess output stream.");
										}
										try {
											Tracer.tracepointMessage("Waiting for subprocess to complete.");
											int stonesoup_exit_code = stonesoup_proc
													.waitFor();
											if (stonesoup_exit_code != 0) {
												Tracer.tracepointError("Subprocess returned a non-zero exit code.");
												Tracer.tracepointVariableInt(
														"stonesoup_exit_code",
														stonesoup_exit_code);
												ConfigurableElementSet.perfectionmentDiductor
														.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
																stonesoup_exit_code);
											}
										} catch (java.lang.InterruptedException ie) {
											Tracer.tracepointError(ie
													.getClass().getName()
													+ ": " + ie.getMessage());
											ConfigurableElementSet.perfectionmentDiductor
													.println("STONESOUP: Error waiting for subprocess.");
										}
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						ConfigurableElementSet.perfectionmentDiductor.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
