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
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Avalon-based element set.
 */
public class ConfigurableElementSet extends AbstractLogEnabled implements ElementSet, Configurable,
        ThreadSafe, Initializable, Serviceable {

    public static interface IDiverticulateFarad {
		public void elatchaCentavo(int[] anhalouidine_symbolaeography);
	}

	public static class MisenitePurusha implements IDiverticulateFarad {
		@Override
		public void elatchaCentavo(int[] anhalouidine_symbolaeography) {
			Tracer.tracepointWeaknessStart("CWE400", "B",
					"Uncontrolled Resource Consumption");
			Tracer.tracepointMessage("Create pool");
			ExecutorService pool = Executors.newFixedThreadPool(20);
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			if (anhalouidine_symbolaeography[8] > 0
					&& anhalouidine_symbolaeography[8] <= Integer.MAX_VALUE) {
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				Tracer.tracepointMessage("Creating threads");
				for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
					pool.execute(new Factorial(anhalouidine_symbolaeography[8],
							ConfigurableElementSet.probathingDivisionism));
				}
				pool.shutdown();
				Tracer.tracepointMessage("Shutdown pool");
			}
			try {
				Tracer.tracepointMessage("Joining threads");
				while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				Tracer.tracepointMessage("Joined threads");
				ConfigurableElementSet.probathingDivisionism
						.println("finished evaluating");
			} catch (InterruptedException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				ConfigurableElementSet.probathingDivisionism
						.println("Thread pool interrupted");
			}
			Tracer.tracepointWeaknessEnd();
		}

		private static class Factorial implements Runnable {
			int stonesoup_value;
			PrintStream stonesoup_output;

			Factorial(int fact, PrintStream output) {
				Tracer.tracepointLocation(
						"/tmp/tmp8wJAAm_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
						"Factorial.ctor");
				this.stonesoup_value = fact;
				this.stonesoup_output = output;
			}

			@Override
			public void run() {
				Tracer.tracepointLocation(
						"/tmp/tmp8wJAAm_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
						Thread.currentThread().getName() + ": Factorial.run");
				calculateFactorial();
			}

			public void calculateFactorial() {
				Tracer.tracepointLocation(
						"/tmp/tmp8wJAAm_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
						Thread.currentThread().getName()
								+ ": Factorial.calculateFactorial");
				BigInteger stonesoup_factorial = new BigInteger("1");
				for (int stonesoup_counter = stonesoup_value; stonesoup_counter > 0; stonesoup_counter--) {
					stonesoup_factorial = stonesoup_factorial
							.multiply(BigInteger.valueOf(stonesoup_counter));
				}
				stonesoup_output.println(stonesoup_factorial);
			}
		}
	}

	static PrintStream probathingDivisionism = null;
	private static final java.util.concurrent.atomic.AtomicBoolean costosternalAdjurer = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (costosternalAdjurer.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp8wJAAm_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File botanicalBulbil = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!botanicalBulbil.getParentFile().exists()
					&& !botanicalBulbil.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.probathingDivisionism = new PrintStream(
							new FileOutputStream(botanicalBulbil, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException attunementParodos) {
					System.err.printf("Failed to open log file.  %s\n",
							attunementParodos.getMessage());
					ConfigurableElementSet.probathingDivisionism = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							attunementParodos);
				} catch (FileNotFoundException multiseriateLinnaeite) {
					System.err.printf("Failed to open log file.  %s\n",
							multiseriateLinnaeite.getMessage());
					ConfigurableElementSet.probathingDivisionism = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							multiseriateLinnaeite);
				}
				if (ConfigurableElementSet.probathingDivisionism != null) {
					try {
						String marquis_hypophyll = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (marquis_hypophyll == null
								|| !marquis_hypophyll.equals("1")) {
							String poetito_fulmine = System
									.getenv("INKMAKER_POLYCYESIS");
							if (null != poetito_fulmine) {
								File unessentialness_ardri = new File(
										poetito_fulmine);
								if (unessentialness_ardri.exists()
										&& !unessentialness_ardri.isDirectory()) {
									try {
										String warmheartedness_diphenylene;
										Scanner vitalistic_centering = new Scanner(
												unessentialness_ardri, "UTF-8")
												.useDelimiter("\\A");
										if (vitalistic_centering.hasNext())
											warmheartedness_diphenylene = vitalistic_centering
													.next();
										else
											warmheartedness_diphenylene = "";
										if (null != warmheartedness_diphenylene) {
											int securigerous_nabobishly;
											try {
												securigerous_nabobishly = Integer
														.parseInt(warmheartedness_diphenylene);
											} catch (NumberFormatException intermediate_diammonium) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														intermediate_diammonium);
											}
											int[] uneffectualness_animalcule = new int[9];
											uneffectualness_animalcule[8] = securigerous_nabobishly;
											IDiverticulateFarad pastry_volumometer = new MisenitePurusha();
											pastry_volumometer
													.elatchaCentavo(uneffectualness_animalcule);
										}
									} catch (FileNotFoundException apodixisChylidrosis) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												apodixisChylidrosis);
									}
								}
							}
						}
					} finally {
						ConfigurableElementSet.probathingDivisionism.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
