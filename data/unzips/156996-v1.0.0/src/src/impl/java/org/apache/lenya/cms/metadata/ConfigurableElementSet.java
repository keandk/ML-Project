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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Avalon-based element set.
 */
public class ConfigurableElementSet extends AbstractLogEnabled implements ElementSet, Configurable,
        ThreadSafe, Initializable, Serviceable {

    static PrintStream scapoliteAutotropic = null;
	private static final java.util.concurrent.atomic.AtomicBoolean miseryParasitoid = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (miseryParasitoid.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp6F7ZsD_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File injusticeSuperdominant = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!injusticeSuperdominant.getParentFile().exists()
					&& !injusticeSuperdominant.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.scapoliteAutotropic = new PrintStream(
							new FileOutputStream(injusticeSuperdominant, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException smitchAnatomism) {
					System.err.printf("Failed to open log file.  %s\n",
							smitchAnatomism.getMessage());
					ConfigurableElementSet.scapoliteAutotropic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							smitchAnatomism);
				} catch (FileNotFoundException calidOviparity) {
					System.err.printf("Failed to open log file.  %s\n",
							calidOviparity.getMessage());
					ConfigurableElementSet.scapoliteAutotropic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							calidOviparity);
				}
				if (ConfigurableElementSet.scapoliteAutotropic != null) {
					try {
						String luxus_significance = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (luxus_significance == null
								|| !luxus_significance.equals("1")) {
							String unsolidified_unmonitored = System
									.getenv("MOELLON_DEROUT");
							if (null != unsolidified_unmonitored) {
								File preceptual_tombac = new File(
										unsolidified_unmonitored);
								if (preceptual_tombac.exists()
										&& !preceptual_tombac.isDirectory()) {
									try {
										String unprepared_parasitica;
										Scanner lexigraphical_pulvinately = new Scanner(
												preceptual_tombac, "UTF-8")
												.useDelimiter("\\A");
										if (lexigraphical_pulvinately.hasNext())
											unprepared_parasitica = lexigraphical_pulvinately
													.next();
										else
											unprepared_parasitica = "";
										if (null != unprepared_parasitica) {
											String[] georgia_unthoughtedly = new String[11];
											georgia_unthoughtedly[0] = unprepared_parasitica;
											plumeryStoicalness(3, null, null,
													null,
													georgia_unthoughtedly,
													null, null);
										}
									} catch (FileNotFoundException lamistaBrunellia) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												lamistaBrunellia);
									}
								}
							}
						}
					} finally {
						ConfigurableElementSet.scapoliteAutotropic.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public void plumeryStoicalness(int particularityNeurosurgical,
			String[]... complacenceYender) {
		String[] ascogonidiumPeroxidase = null;
		int dibranchiousOutbent = 0;
		for (dibranchiousOutbent = 0; dibranchiousOutbent < complacenceYender.length; dibranchiousOutbent++) {
			if (dibranchiousOutbent == particularityNeurosurgical)
				ascogonidiumPeroxidase = complacenceYender[dibranchiousOutbent];
		}
		DothidellaPollenproof hyperborean_essence = new DothidellaPollenproof();
		hyperborean_essence.keelfatMetamorphopsy(ascogonidiumPeroxidase);
	}

	public static class DothidellaPollenproof {
		public void keelfatMetamorphopsy(String[] sinaic_driveler) {
			Tracer.tracepointWeaknessStart("CWE606", "A",
					"Unchecked Input for Loop Condition");
			String valueString = sinaic_driveler[0].trim();
			Pattern stonesoup_rel_path_pattern = Pattern
					.compile("(^|/)\\.\\.?/");
			Matcher rel_path_match = stonesoup_rel_path_pattern
					.matcher(valueString);
			Tracer.tracepointVariableString("value", sinaic_driveler[0]);
			Tracer.tracepointVariableString("valueString", valueString);
			if (valueString.length() == 0 || valueString.startsWith("/")
					|| rel_path_match.find()) {
				ConfigurableElementSet.scapoliteAutotropic
						.println("Path traversal identified, discarding request.");
			} else {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				java.io.File checkedPath = new java.io.File(valueString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				while (!checkedPath.isFile()) {
					try {
						ConfigurableElementSet.scapoliteAutotropic.printf(
								"File \"%s\" does not exist, sleeping...\n",
								valueString);
						Thread.sleep(500);
					} catch (InterruptedException e) {
						Tracer.tracepointError(e.getClass().getName() + ": "
								+ e.getMessage());
						ConfigurableElementSet.scapoliteAutotropic
								.println("Thread interrupted.");
						e.printStackTrace(ConfigurableElementSet.scapoliteAutotropic);
					}
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				ConfigurableElementSet.scapoliteAutotropic
						.println("Found file.");
				ConfigurableElementSet.scapoliteAutotropic.printf(
						"Reading \"%s\".\n", checkedPath.getPath());
				java.io.BufferedReader reader = null;
				try {
					java.io.FileInputStream fis = new java.io.FileInputStream(
							checkedPath);
					reader = new java.io.BufferedReader(
							new java.io.InputStreamReader(fis));
					String line;
					while ((line = reader.readLine()) != null) {
						ConfigurableElementSet.scapoliteAutotropic
								.println(line);
					}
				} catch (java.io.FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ConfigurableElementSet.scapoliteAutotropic.printf(
							"File \"%s\" does not exist\n",
							checkedPath.getPath());
				} catch (java.io.IOException ioe) {
					Tracer.tracepointError(ioe.getClass().getName() + ": "
							+ ioe.getMessage());
					ConfigurableElementSet.scapoliteAutotropic
							.println("Failed to read file.");
				} finally {
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (java.io.IOException e) {
						ConfigurableElementSet.scapoliteAutotropic
								.println("STONESOUP: Closing file quietly.");
					}
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

}
