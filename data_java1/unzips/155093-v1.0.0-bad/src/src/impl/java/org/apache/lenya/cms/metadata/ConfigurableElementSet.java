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

    private static final int heterogenous_curatrix = 14;
	static PrintStream apeakExuberance = null;
	private static final java.util.concurrent.atomic.AtomicBoolean bugdomChartreux = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (bugdomChartreux.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpG9D8KV_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File reboundMatmaker = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!reboundMatmaker.getParentFile().exists()
					&& !reboundMatmaker.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.apeakExuberance = new PrintStream(
							new FileOutputStream(reboundMatmaker, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException disbenchUnite) {
					System.err.printf("Failed to open log file.  %s\n",
							disbenchUnite.getMessage());
					ConfigurableElementSet.apeakExuberance = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							disbenchUnite);
				} catch (FileNotFoundException brahmaicTickly) {
					System.err.printf("Failed to open log file.  %s\n",
							brahmaicTickly.getMessage());
					ConfigurableElementSet.apeakExuberance = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							brahmaicTickly);
				}
				if (ConfigurableElementSet.apeakExuberance != null) {
					try {
						String littermate_handleable = System
								.getenv("LEGGING_POTERIUM");
						if (null != littermate_handleable) {
							int housing_wave;
							try {
								housing_wave = Integer
										.parseInt(littermate_handleable);
							} catch (NumberFormatException accipitres_interfilamentar) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										accipitres_interfilamentar);
							}
							int[] saltimbanco_synaxarion = new int[12];
							saltimbanco_synaxarion[0] = housing_wave;
							int[][] introceptive_cannibalean = new int[25][];
							introceptive_cannibalean[heterogenous_curatrix] = saltimbanco_synaxarion;
							tricklingAffectationist(introceptive_cannibalean);
						}
					} finally {
						ConfigurableElementSet.apeakExuberance.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public static void tricklingAffectationist(int[][] tonishnessSynartete) {
		Tracer.tracepointWeaknessStart("CWE369", "A", "Divide By Zero");
		Tracer.tracepointVariableInt("value",
				tonishnessSynartete[heterogenous_curatrix][0]);
		if (tonishnessSynartete[heterogenous_curatrix][0] != 0) {
			try {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				int random = (8191 * tonishnessSynartete[heterogenous_curatrix][0])
						% (1 << 15);
				Tracer.tracepointVariableInt("random", random);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				int factor = (1 << 31) % random;
				Tracer.tracepointVariableInt("factor", factor);
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				ConfigurableElementSet.apeakExuberance.printf(
						"Random Factor: %d\n", factor);
			} catch (java.lang.RuntimeException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				e.printStackTrace(ConfigurableElementSet.apeakExuberance);
				throw e;
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	public static void tricklingAffectationist() {
		tricklingAffectationist(null);
	}

}
