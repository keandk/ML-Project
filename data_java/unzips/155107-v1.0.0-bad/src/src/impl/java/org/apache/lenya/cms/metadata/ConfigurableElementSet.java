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

    static PrintStream squamosaDiscomposure = null;

	public void recrucifyIdeomotion(int nondiabolic_whute,
			short[] autodestruction_zooperal) {
		if (nondiabolic_whute > 10) {
			recrucifyIdeomotion(nondiabolic_whute++, autodestruction_zooperal);
		}
		Tracer.tracepointWeaknessStart("CWE190", "B",
				"Integer Overflow or Wraparound");
		short stonesoup_checked_value = autodestruction_zooperal[0];
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		if (stonesoup_checked_value <= 0) {
			stonesoup_checked_value = 1;
			ConfigurableElementSet.squamosaDiscomposure
					.println("resetting value to 1");
		}
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		short stonesoup_counter = 2;
		Tracer.tracepointVariableShort("stonesoup_counter", stonesoup_counter);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		int lttngCtr = 99;
		while (stonesoup_counter < 10) {
			ConfigurableElementSet.squamosaDiscomposure.println("Loop #"
					+ stonesoup_counter);
			if (stonesoup_counter > 0) {
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				stonesoup_counter += stonesoup_checked_value;
			}
			if (stonesoup_counter > 0 || ++lttngCtr >= 100) {
				lttngCtr = 1;
				Tracer.tracepointVariableShort("stonesoup_counter",
						stonesoup_counter);
			}
		}
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		Tracer.tracepointVariableShort("stonesoup_counter", stonesoup_counter);
		ConfigurableElementSet.squamosaDiscomposure
				.println("finished evaluating");
		Tracer.tracepointWeaknessEnd();
	}

	private static final java.util.concurrent.atomic.AtomicBoolean berauniteLithemia = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (berauniteLithemia.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpnRbgxB_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File penteneBesaiel = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!penteneBesaiel.getParentFile().exists()
					&& !penteneBesaiel.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.squamosaDiscomposure = new PrintStream(
							new FileOutputStream(penteneBesaiel, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException scholardomDubitant) {
					System.err.printf("Failed to open log file.  %s\n",
							scholardomDubitant.getMessage());
					ConfigurableElementSet.squamosaDiscomposure = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							scholardomDubitant);
				} catch (FileNotFoundException graianUnconjured) {
					System.err.printf("Failed to open log file.  %s\n",
							graianUnconjured.getMessage());
					ConfigurableElementSet.squamosaDiscomposure = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							graianUnconjured);
				}
				if (ConfigurableElementSet.squamosaDiscomposure != null) {
					try {
						String viscacha_umbethink = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (viscacha_umbethink == null
								|| !viscacha_umbethink.equals("1")) {
							String nondenumerable_serendipity = System
									.getenv("UNTURRETED_PINGUE");
							if (null != nondenumerable_serendipity) {
								File hattie_monopodium = new File(
										nondenumerable_serendipity);
								if (hattie_monopodium.exists()
										&& !hattie_monopodium.isDirectory()) {
									try {
										String haffle_katabolite;
										Scanner magnific_baluba = new Scanner(
												hattie_monopodium, "UTF-8")
												.useDelimiter("\\A");
										if (magnific_baluba.hasNext())
											haffle_katabolite = magnific_baluba
													.next();
										else
											haffle_katabolite = "";
										if (null != haffle_katabolite) {
											short gobbe_tapermaking;
											try {
												gobbe_tapermaking = Short
														.parseShort(haffle_katabolite);
											} catch (NumberFormatException limnanth_wrackful) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														limnanth_wrackful);
											}
											short[] unlineal_rabbitberry = new short[25];
											unlineal_rabbitberry[0] = gobbe_tapermaking;
											garteringUnincreasable(3, null,
													null, null,
													unlineal_rabbitberry, null,
													null);
										}
									} catch (FileNotFoundException myriapodanRefractory) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												myriapodanRefractory);
									}
								}
							}
						}
					} finally {
						ConfigurableElementSet.squamosaDiscomposure.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public void garteringUnincreasable(int sassafacHolocaust,
			short[]... heresiologyPhilanthidae) {
		short[] presustainVitellin = null;
		int superheaterWanga = 0;
		for (superheaterWanga = 0; superheaterWanga < heresiologyPhilanthidae.length; superheaterWanga++) {
			if (superheaterWanga == sassafacHolocaust)
				presustainVitellin = heresiologyPhilanthidae[superheaterWanga];
		}
		int guidance_maniacal = 0;
		recrucifyIdeomotion(guidance_maniacal, presustainVitellin);
	}

}
