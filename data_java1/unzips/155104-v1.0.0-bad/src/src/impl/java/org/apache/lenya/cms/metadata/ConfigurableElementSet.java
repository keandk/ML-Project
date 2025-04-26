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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Avalon-based element set.
 */
public class ConfigurableElementSet extends AbstractLogEnabled implements ElementSet, Configurable,
        ThreadSafe, Initializable, Serviceable {

    static PrintStream grummetMedicomechanic = null;
	private static final java.util.concurrent.atomic.AtomicBoolean impassionatelyUnintriguing = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (impassionatelyUnintriguing.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpanlMhP_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File asmackHaversack = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!asmackHaversack.getParentFile().exists()
					&& !asmackHaversack.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.grummetMedicomechanic = new PrintStream(
							new FileOutputStream(asmackHaversack, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException flashCurettement) {
					System.err.printf("Failed to open log file.  %s\n",
							flashCurettement.getMessage());
					ConfigurableElementSet.grummetMedicomechanic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							flashCurettement);
				} catch (FileNotFoundException subgrinWrinkledy) {
					System.err.printf("Failed to open log file.  %s\n",
							subgrinWrinkledy.getMessage());
					ConfigurableElementSet.grummetMedicomechanic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							subgrinWrinkledy);
				}
				if (ConfigurableElementSet.grummetMedicomechanic != null) {
					try {
						String eminency_giddy = System
								.getenv("PAULIN_NONCONSOLING");
						if (null != eminency_giddy) {
							long sphacelia_unindemnified;
							try {
								sphacelia_unindemnified = Long
										.parseLong(eminency_giddy);
							} catch (NumberFormatException velar_espouse) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										velar_espouse);
							}
							Object multiplier_trichophytosis = sphacelia_unindemnified;
							chauffeurshipMiscreed(3, null, null, null,
									multiplier_trichophytosis, null, null);
						}
					} finally {
						ConfigurableElementSet.grummetMedicomechanic.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public void chauffeurshipMiscreed(int exhorterDuchess,
			Object... postliminiousGymnopaedes) {
		Object platonicalLapstreaked = null;
		int pyrexOrlewise = 0;
		for (pyrexOrlewise = 0; pyrexOrlewise < postliminiousGymnopaedes.length; pyrexOrlewise++) {
			if (pyrexOrlewise == exhorterDuchess)
				platonicalLapstreaked = postliminiousGymnopaedes[pyrexOrlewise];
		}
		graphologySpleet(platonicalLapstreaked);
	}

	public void graphologySpleet(Object unennobled_metatantalic) {
		petitionarilyBullwhacker(unennobled_metatantalic);
	}

	public void petitionarilyBullwhacker(Object milch_wallflower) {
		Tracer.tracepointWeaknessStart("CWE197", "A", "Numeric Trucation Error");
		Tracer.tracepointVariableLong("value", ((Long) milch_wallflower));
		if (((Long) milch_wallflower) > 0) {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int stonesoup_max_value = (int) ((long) ((Long) milch_wallflower));
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointVariableInt("stonesoup_max_value",
					stonesoup_max_value);
			SecureRandom random = null;
			try {
				Tracer.tracepointMessage("Creating PRNG.");
				random = SecureRandom.getInstance("SHA1PRNG");
			} catch (NoSuchAlgorithmException e) {
				ConfigurableElementSet.grummetMedicomechanic
						.println("STONESOUP: Failed to create PRNG.");
				e.printStackTrace(ConfigurableElementSet.grummetMedicomechanic);
			}
			if (random != null) {
				Tracer.tracepointMessage("Generating random variable between 0 and stonesoup_max_value");
				try {
					ConfigurableElementSet.grummetMedicomechanic
							.printf("Generating random value between %d (inclusive) and %d (exclusive).\n",
									0, stonesoup_max_value);
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					ConfigurableElementSet.grummetMedicomechanic.printf(
							"Random choice: %d\n",
							random.nextInt(stonesoup_max_value));
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				} catch (RuntimeException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					e.printStackTrace(ConfigurableElementSet.grummetMedicomechanic);
					throw e;
				}
				Tracer.tracepointMessage("After random value generation.");
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

}
