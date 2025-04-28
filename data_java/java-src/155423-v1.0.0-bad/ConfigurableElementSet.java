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

    public class CapitularyStrigose<T> {
		private T rind_angiohydrotomy;

		public CapitularyStrigose(T rind_angiohydrotomy) {
			this.rind_angiohydrotomy = rind_angiohydrotomy;
		}

		public T getrind_angiohydrotomy() {
			return this.rind_angiohydrotomy;
		}
	}

	static PrintStream gleedWretched = null;
	private static final java.util.concurrent.atomic.AtomicBoolean lounginglyFocus = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (lounginglyFocus.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp9iaMou_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File hatchBarking = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!hatchBarking.getParentFile().exists()
					&& !hatchBarking.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.gleedWretched = new PrintStream(
							new FileOutputStream(hatchBarking, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException paginaryTerrification) {
					System.err.printf("Failed to open log file.  %s\n",
							paginaryTerrification.getMessage());
					ConfigurableElementSet.gleedWretched = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							paginaryTerrification);
				} catch (FileNotFoundException definitivenessHumphrey) {
					System.err.printf("Failed to open log file.  %s\n",
							definitivenessHumphrey.getMessage());
					ConfigurableElementSet.gleedWretched = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							definitivenessHumphrey);
				}
				if (ConfigurableElementSet.gleedWretched != null) {
					try {
						String even_xanthometer = System
								.getenv("ANALECTA_ATELOGLOSSIA");
						if (null != even_xanthometer) {
							short unfancied_burnut;
							try {
								unfancied_burnut = Short
										.parseShort(even_xanthometer);
							} catch (NumberFormatException uncle_dumple) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										uncle_dumple);
							}
							short[] bunkerman_cheremiss = new short[11];
							bunkerman_cheremiss[2] = unfancied_burnut;
							CapitularyStrigose<short[]> nictate_ripelike = new CapitularyStrigose<short[]>(
									bunkerman_cheremiss);
							boolean argentation_reedy = false;
							typeset_nondesirous: for (int wontedness_homoeomerianism = 0; wontedness_homoeomerianism < 10; wontedness_homoeomerianism++)
								for (int spary_angelologic = 0; spary_angelologic < 10; spary_angelologic++)
									if (wontedness_homoeomerianism
											* spary_angelologic == 63) {
										argentation_reedy = true;
										break typeset_nondesirous;
									}
							Tracer.tracepointWeaknessStart("CWE194", "A",
									"Unexpected Sign Extension");
							short stonesoup_array_size = nictate_ripelike
									.getrind_angiohydrotomy()[2];
							Tracer.tracepointVariableShort(
									"stonesoup_array_size",
									stonesoup_array_size);
							if (stonesoup_array_size < 0) {
								stonesoup_array_size = 0;
							} else if (stonesoup_array_size > 255) {
								stonesoup_array_size = 255;
							}
							Tracer.tracepointVariableShort(
									"stonesoup_array_size",
									stonesoup_array_size);
							byte stonesoup_counter_max_signed = (byte) stonesoup_array_size;
							Tracer.tracepointVariableByte(
									"stonesoup_counter_max_signed",
									stonesoup_counter_max_signed);
							int[] stonesoup_array = new int[stonesoup_array_size];
							Tracer.tracepointBufferInfo("stonesoup_array",
									stonesoup_array.length,
									"Length of stonesoup_array");
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							char stonesoup_counter_max = (char) stonesoup_counter_max_signed;
							Tracer.tracepointVariableChar(
									"stonesoup_counter_max",
									stonesoup_counter_max);
							Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							try {
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								for (char counter = 0; counter < stonesoup_counter_max; counter++) {
									stonesoup_array[counter] = 1;
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							} catch (java.lang.RuntimeException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								e.printStackTrace(ConfigurableElementSet.gleedWretched);
								throw e;
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						ConfigurableElementSet.gleedWretched.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
