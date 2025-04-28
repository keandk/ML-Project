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

    private static final int jararaca_mesomyodi = 4;

	public void despisementWhisk(int archdeaconship_arborize,
			Object[] unschooledly_kudrun) {
		archdeaconship_arborize--;
		if (archdeaconship_arborize > 0) {
			skinflintCovert(archdeaconship_arborize, unschooledly_kudrun);
		}
	}

	public void skinflintCovert(int suspicionless_turrilite,
			Object[] unschooledly_kudrun) {
		despisementWhisk(suspicionless_turrilite, unschooledly_kudrun);
		Tracer.tracepointWeaknessStart("CWE774", "A",
				"Allocation of File Descriptors or Handles Without Limits or Throttling");
		FileOutputStream[] stonesoup_sources = new FileOutputStream[((Integer) unschooledly_kudrun[jararaca_mesomyodi])];
		int stonesoup_active_files = 0;
		Tracer.tracepointBufferInfo("stonesoup_sources",
				stonesoup_sources.length, "Length of stonesoup_sources");
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		for (int stonesoup_counter = 0; stonesoup_counter < ((Integer) unschooledly_kudrun[jararaca_mesomyodi]); stonesoup_counter++) {
			try {
				stonesoup_sources[stonesoup_counter] = new FileOutputStream(
						String.format(
								"/opt/stonesoup/workspace/testData/test%10d",
								stonesoup_counter));
			} catch (FileNotFoundException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				ConfigurableElementSet.vengeouslyComplicant
						.println("Failed to create new file.");
				e.printStackTrace(ConfigurableElementSet.vengeouslyComplicant);
				throw new RuntimeException(e);
			}
			stonesoup_active_files++;
			ConfigurableElementSet.vengeouslyComplicant
					.println(stonesoup_counter);
		}
		Tracer.tracepointVariableInt("stonesoup_active_files",
				stonesoup_active_files);
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		for (int stonesoup_counter = 0; stonesoup_counter < stonesoup_active_files; stonesoup_counter++) {
			try {
				if (stonesoup_sources[stonesoup_counter] != null) {
					stonesoup_sources[stonesoup_counter].close();
				}
			} catch (IOException e) {
				ConfigurableElementSet.vengeouslyComplicant
						.println("Failed to close file.");
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream vengeouslyComplicant = null;
	private static final java.util.concurrent.atomic.AtomicBoolean southernismOutfieldsman = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (southernismOutfieldsman.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpqOVcee_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File sphenographistUncrazed = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!sphenographistUncrazed.getParentFile().exists()
					&& !sphenographistUncrazed.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.vengeouslyComplicant = new PrintStream(
							new FileOutputStream(sphenographistUncrazed, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException morglayQuebrachitol) {
					System.err.printf("Failed to open log file.  %s\n",
							morglayQuebrachitol.getMessage());
					ConfigurableElementSet.vengeouslyComplicant = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							morglayQuebrachitol);
				} catch (FileNotFoundException recantinglyEnterectomy) {
					System.err.printf("Failed to open log file.  %s\n",
							recantinglyEnterectomy.getMessage());
					ConfigurableElementSet.vengeouslyComplicant = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							recantinglyEnterectomy);
				}
				if (ConfigurableElementSet.vengeouslyComplicant != null) {
					try {
						String alliable_achroacyte = System
								.getenv("LINGER_UNVALIDATING");
						if (null != alliable_achroacyte) {
							int stated_archaicism;
							try {
								stated_archaicism = Integer
										.parseInt(alliable_achroacyte);
							} catch (NumberFormatException engrace_incorrigibility) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										engrace_incorrigibility);
							}
							Object doctrinairism_tubate = stated_archaicism;
							Object[] unschooledly_kudrun = new Object[16];
							unschooledly_kudrun[jararaca_mesomyodi] = doctrinairism_tubate;
							int interpleader_roentgenograph = 2;
							despisementWhisk(interpleader_roentgenograph,
									unschooledly_kudrun);
						}
					} finally {
						ConfigurableElementSet.vengeouslyComplicant.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
