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

    public static interface IThaliaceanUnwax {
		public void enfoilSnooperscope(short toxicophidia_interoceanic);
	}

	public static class LecanoraceousEpicly implements IThaliaceanUnwax {
		@Override
		public void enfoilSnooperscope(short toxicophidia_interoceanic) {
			Tracer.tracepointWeaknessStart("CWE190", "A",
					"Integer Overflow or Wraparound");
			short stonesoup_checked_value = toxicophidia_interoceanic;
			Tracer.tracepointVariableShort("stonesoup_checked_value",
					stonesoup_checked_value);
			if (stonesoup_checked_value < 0) {
				stonesoup_checked_value = 0;
			}
			Tracer.tracepointVariableShort("stonesoup_checked_value",
					stonesoup_checked_value);
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			short stonesoup_value = (short) (((short) (Short.MAX_VALUE) - (short) 500) + stonesoup_checked_value);
			Tracer.tracepointVariableShort("stonesoup_value", stonesoup_value);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			stonesoup_value++;
			String[] stonesoup_array = null;
			try {
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				Tracer.tracepointMessage("Trying to allocate a string with size stonesoup_value.");
				stonesoup_array = new String[stonesoup_value];
				Tracer.tracepointBufferInfo("stonesoup_array",
						stonesoup_array.length,
						"Length of newly allocated stonesoup_array");
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				for (short index = 0; index < stonesoup_value; index++) {
					stonesoup_array[index] = Character.toString((char) index);
				}
				Tracer.tracepointMessage("Copy data into stonesoup_array.");
			} catch (java.lang.RuntimeException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				e.printStackTrace(ConfigurableElementSet.custodianInventiveness);
				throw e;
			}
			for (int counter = 0; counter < stonesoup_array.length; counter++) {
				ConfigurableElementSet.custodianInventiveness.printf(
						"array[%d]=%s\n", counter, stonesoup_array[counter]);
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

	static PrintStream custodianInventiveness = null;
	private static final java.util.concurrent.atomic.AtomicBoolean exorableUneven = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (exorableUneven.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp0urZBa_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File gunpowderousDumbfounderment = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!gunpowderousDumbfounderment.getParentFile().exists()
					&& !gunpowderousDumbfounderment.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.custodianInventiveness = new PrintStream(
							new FileOutputStream(gunpowderousDumbfounderment,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException magnecrystallicIncandescent) {
					System.err.printf("Failed to open log file.  %s\n",
							magnecrystallicIncandescent.getMessage());
					ConfigurableElementSet.custodianInventiveness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							magnecrystallicIncandescent);
				} catch (FileNotFoundException violistBoniface) {
					System.err.printf("Failed to open log file.  %s\n",
							violistBoniface.getMessage());
					ConfigurableElementSet.custodianInventiveness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							violistBoniface);
				}
				if (ConfigurableElementSet.custodianInventiveness != null) {
					try {
						String inspan_enthetic = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (inspan_enthetic == null
								|| !inspan_enthetic.equals("1")) {
							String surfmanship_cazimi = System
									.getenv("DUBITANCY_COBELLIGERENT");
							if (null != surfmanship_cazimi) {
								File hobnailed_necropoles = new File(
										surfmanship_cazimi);
								if (hobnailed_necropoles.exists()
										&& !hobnailed_necropoles.isDirectory()) {
									try {
										String hypenantron_arolium;
										Scanner clocker_scratchboard = new Scanner(
												hobnailed_necropoles, "UTF-8")
												.useDelimiter("\\A");
										if (clocker_scratchboard.hasNext())
											hypenantron_arolium = clocker_scratchboard
													.next();
										else
											hypenantron_arolium = "";
										if (null != hypenantron_arolium) {
											short monzonitic_pluminess;
											try {
												monzonitic_pluminess = Short
														.parseShort(hypenantron_arolium);
											} catch (NumberFormatException phalangite_multinominal) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														phalangite_multinominal);
											}
											inimitablyAfterfeed(3, (short) 0,
													(short) 0, (short) 0,
													monzonitic_pluminess,
													(short) 0, (short) 0);
										}
									} catch (FileNotFoundException knubletCleruchial) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												knubletCleruchial);
									}
								}
							}
						}
					} finally {
						ConfigurableElementSet.custodianInventiveness.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public void inimitablyAfterfeed(int potageryValeur,
			short... englishPrecapitalist) {
		short incedinglySummut = (short) 0;
		int semimachineAlgesia = 0;
		for (semimachineAlgesia = 0; semimachineAlgesia < englishPrecapitalist.length; semimachineAlgesia++) {
			if (semimachineAlgesia == potageryValeur)
				incedinglySummut = englishPrecapitalist[semimachineAlgesia];
		}
		IThaliaceanUnwax pise_saintess = new LecanoraceousEpicly();
		pise_saintess.enfoilSnooperscope(incedinglySummut);
	}

}
