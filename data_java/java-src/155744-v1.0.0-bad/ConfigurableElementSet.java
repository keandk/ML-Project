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

/**
 * Avalon-based element set.
 */
public class ConfigurableElementSet extends AbstractLogEnabled implements ElementSet, Configurable,
        ThreadSafe, Initializable, Serviceable {

    public class SubfuscousAgalloch<T> {
		private T sivatheriinae_fendillate;

		public SubfuscousAgalloch(T sivatheriinae_fendillate) {
			this.sivatheriinae_fendillate = sivatheriinae_fendillate;
		}

		public T getsivatheriinae_fendillate() {
			return this.sivatheriinae_fendillate;
		}
	}

	static PrintStream tinklerThallious = null;
	private static final java.util.concurrent.atomic.AtomicBoolean avidiousOverdust = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (avidiousOverdust.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpIXlZP7_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File conservationStolzite = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!conservationStolzite.getParentFile().exists()
					&& !conservationStolzite.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.tinklerThallious = new PrintStream(
							new FileOutputStream(conservationStolzite, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException candareenGreenside) {
					System.err.printf("Failed to open log file.  %s\n",
							candareenGreenside.getMessage());
					ConfigurableElementSet.tinklerThallious = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							candareenGreenside);
				} catch (FileNotFoundException lapidarianNephron) {
					System.err.printf("Failed to open log file.  %s\n",
							lapidarianNephron.getMessage());
					ConfigurableElementSet.tinklerThallious = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							lapidarianNephron);
				}
				if (ConfigurableElementSet.tinklerThallious != null) {
					try {
						String pyramidally_starlight = System
								.getenv("POROS_TENTWARDS");
						if (null != pyramidally_starlight) {
							SubfuscousAgalloch<String> unadaptably_ciliiform = new SubfuscousAgalloch<String>(
									pyramidally_starlight);
							try {
								String trogonoid_leisten = System
										.getProperty("os.name");
								if (null != trogonoid_leisten) {
									if (!trogonoid_leisten
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException jitneur_tollable) {
							} finally {
								Tracer.tracepointWeaknessStart("CWE674", "A",
										"Uncontrolled Recursion");
								Tracer.tracepointVariableString(
										"stonesoup_taintedValue",
										unadaptably_ciliiform
												.getsivatheriinae_fendillate());
								if (unadaptably_ciliiform
										.getsivatheriinae_fendillate().length() < 1) {
									ConfigurableElementSet.tinklerThallious
											.println("Error: string too short");
								} else {
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									int stonesoup_index_found = search(
											unadaptably_ciliiform
													.getsivatheriinae_fendillate()
													.substring(
															1,
															unadaptably_ciliiform
																	.getsivatheriinae_fendillate()
																	.length()),
											unadaptably_ciliiform
													.getsivatheriinae_fendillate()
													.charAt(0));
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									ConfigurableElementSet.tinklerThallious
											.println("Info: value found at "
													+ stonesoup_index_found);
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						ConfigurableElementSet.tinklerThallious.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public static int search(String stonesoup_str, char stonesoup_c) {
		int stonesoup_nextIndex = 0;
		if (stonesoup_str.length() > 0) {
			if (stonesoup_str.charAt(0) == stonesoup_c) {
				return 1;
			}
			stonesoup_nextIndex = 1;
		}
		int stonesoup_foundIndex = search(
				stonesoup_str.substring(stonesoup_nextIndex,
						stonesoup_str.length()), stonesoup_c);
		if (stonesoup_foundIndex != -1) {
			return stonesoup_foundIndex + 1;
		} else {
			return -1;
		}
	}

}
