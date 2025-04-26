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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Avalon-based element set.
 */
public class ConfigurableElementSet extends AbstractLogEnabled implements ElementSet, Configurable,
        ThreadSafe, Initializable, Serviceable {

    public class BluenosePlate<T> {
		private T ridden_proctopolypus;

		public BluenosePlate(T ridden_proctopolypus) {
			this.ridden_proctopolypus = ridden_proctopolypus;
		}

		public T getridden_proctopolypus() {
			return this.ridden_proctopolypus;
		}
	}

	static PrintStream sladeWalletful = null;
	private static final java.util.concurrent.atomic.AtomicBoolean filateThornlet = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (filateThornlet.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpF2oax7_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File vannermanUroacidimeter = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!vannermanUroacidimeter.getParentFile().exists()
					&& !vannermanUroacidimeter.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.sladeWalletful = new PrintStream(
							new FileOutputStream(vannermanUroacidimeter, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException agoraphobiaHydrocephalous) {
					System.err.printf("Failed to open log file.  %s\n",
							agoraphobiaHydrocephalous.getMessage());
					ConfigurableElementSet.sladeWalletful = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							agoraphobiaHydrocephalous);
				} catch (FileNotFoundException trowelbeakUnwarbled) {
					System.err.printf("Failed to open log file.  %s\n",
							trowelbeakUnwarbled.getMessage());
					ConfigurableElementSet.sladeWalletful = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							trowelbeakUnwarbled);
				}
				if (ConfigurableElementSet.sladeWalletful != null) {
					try {
						String astonied_photostat = System
								.getenv("TEASPOONFUL_CLIMBABLE");
						if (null != astonied_photostat) {
							Object exaration_overstrictness = astonied_photostat;
							BluenosePlate<Object> agrestian_wintrify = new BluenosePlate<Object>(
									exaration_overstrictness);
							try {
								String doctrinairism_cupania = System
										.getProperty("os.name");
								if (null != doctrinairism_cupania) {
									if (!doctrinairism_cupania
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException reshunt_orleanism) {
							} finally {
								Tracer.tracepointWeaknessStart("CWE606", "A",
										"Unchecked Input for Loop Condition");
								String valueString = ((String) agrestian_wintrify
										.getridden_proctopolypus()).trim();
								Pattern stonesoup_rel_path_pattern = Pattern
										.compile("(^|/)\\.\\.?/");
								Matcher rel_path_match = stonesoup_rel_path_pattern
										.matcher(valueString);
								Tracer.tracepointVariableString("value",
										((String) agrestian_wintrify
												.getridden_proctopolypus()));
								Tracer.tracepointVariableString("valueString",
										valueString);
								if (valueString.length() == 0
										|| valueString.startsWith("/")
										|| rel_path_match.find()) {
									ConfigurableElementSet.sladeWalletful
											.println("Path traversal identified, discarding request.");
								} else {
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									java.io.File checkedPath = new java.io.File(
											valueString);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									while (!checkedPath.isFile()) {
										try {
											ConfigurableElementSet.sladeWalletful
													.printf("File \"%s\" does not exist, sleeping...\n",
															valueString);
											Thread.sleep(500);
										} catch (InterruptedException e) {
											Tracer.tracepointError(e.getClass()
													.getName()
													+ ": "
													+ e.getMessage());
											ConfigurableElementSet.sladeWalletful
													.println("Thread interrupted.");
											e.printStackTrace(ConfigurableElementSet.sladeWalletful);
										}
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									ConfigurableElementSet.sladeWalletful
											.println("Found file.");
									ConfigurableElementSet.sladeWalletful
											.printf("Reading \"%s\".\n",
													checkedPath.getPath());
									java.io.BufferedReader reader = null;
									try {
										java.io.FileInputStream fis = new java.io.FileInputStream(
												checkedPath);
										reader = new java.io.BufferedReader(
												new java.io.InputStreamReader(
														fis));
										String line;
										while ((line = reader.readLine()) != null) {
											ConfigurableElementSet.sladeWalletful
													.println(line);
										}
									} catch (java.io.FileNotFoundException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										ConfigurableElementSet.sladeWalletful
												.printf("File \"%s\" does not exist\n",
														checkedPath.getPath());
									} catch (java.io.IOException ioe) {
										Tracer.tracepointError(ioe.getClass()
												.getName()
												+ ": "
												+ ioe.getMessage());
										ConfigurableElementSet.sladeWalletful
												.println("Failed to read file.");
									} finally {
										try {
											if (reader != null) {
												reader.close();
											}
										} catch (java.io.IOException e) {
											ConfigurableElementSet.sladeWalletful
													.println("STONESOUP: Closing file quietly.");
										}
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						ConfigurableElementSet.sladeWalletful.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
