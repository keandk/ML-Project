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
import java.util.ArrayList;
import java.util.List;

/**
 * Avalon-based element set.
 */
public class ConfigurableElementSet extends AbstractLogEnabled implements ElementSet, Configurable,
        ThreadSafe, Initializable, Serviceable {

    public class MacrocystZoophorus {
		private int[] kukupa_rakishly;

		public MacrocystZoophorus(int[] kukupa_rakishly) {
			this.kukupa_rakishly = kukupa_rakishly;
		}

		public int[] getkukupa_rakishly() {
			return this.kukupa_rakishly;
		}
	}

	static PrintStream endovasculitisPleasing = null;
	private static final java.util.concurrent.atomic.AtomicBoolean fastusNondieting = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (fastusNondieting.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp0QqhlI_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File camerataAkov = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!camerataAkov.getParentFile().exists()
					&& !camerataAkov.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.endovasculitisPleasing = new PrintStream(
							new FileOutputStream(camerataAkov, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException hydromedusoidOutwall) {
					System.err.printf("Failed to open log file.  %s\n",
							hydromedusoidOutwall.getMessage());
					ConfigurableElementSet.endovasculitisPleasing = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							hydromedusoidOutwall);
				} catch (FileNotFoundException millaWrox) {
					System.err.printf("Failed to open log file.  %s\n",
							millaWrox.getMessage());
					ConfigurableElementSet.endovasculitisPleasing = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", millaWrox);
				}
				if (ConfigurableElementSet.endovasculitisPleasing != null) {
					try {
						String deniable_conative = System
								.getenv("UNTOTTERING_DISCIPLINATORY");
						if (null != deniable_conative) {
							int miles_accountantship;
							try {
								miles_accountantship = Integer
										.parseInt(deniable_conative);
							} catch (NumberFormatException adjutory_hematothorax) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										adjutory_hematothorax);
							}
							int[] nototherium_pikey = new int[9];
							nototherium_pikey[7] = miles_accountantship;
							MacrocystZoophorus bundu_biunial = new MacrocystZoophorus(
									nototherium_pikey);
							spermatophyticCorrelatable(bundu_biunial);
						}
					} finally {
						ConfigurableElementSet.endovasculitisPleasing.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public static void spermatophyticCorrelatable(MacrocystZoophorus budgereegahEctosomal){Tracer.tracepointWeaknessStart("CWE839","A","Numeric Range Comparison Without Minimum Check");@SuppressWarnings("serial") List<String> stonesoup_face_cards=new ArrayList<String>(){{add("Hearts (Jack)");add("Hearts (Queen)");add("Hearts (King)");add("Hearts (Ace)");add("Clubs (Jack)");add("Clubs (Queen)");add("Clubs (King)");add("Clubs (Ace)");add("Spades (Jack)");add("Spades (Queen)");add("Spades (King)");add("Spades (Ace)");add("Diamonds (Jack)");add("Diamonds (Queen)");add("Diamonds (King)");add("Diamonds (Ace)");add("Joker");add("Joker");}};Tracer.tracepointVariableInt("value",budgereegahEctosomal.getkukupa_rakishly()[7]);Tracer.tracepointVariableInt("stonesoup_face_cards.size()",stonesoup_face_cards.size());Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");if (budgereegahEctosomal.getkukupa_rakishly()[7] >= stonesoup_face_cards.size()){Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");ConfigurableElementSet.endovasculitisPleasing.printf("Card not available for %d.\n",budgereegahEctosomal.getkukupa_rakishly()[7]);} else {Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");try {Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");ConfigurableElementSet.endovasculitisPleasing.printf("Selected Card = %s\n",stonesoup_face_cards.get(budgereegahEctosomal.getkukupa_rakishly()[7]));Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (RuntimeException e){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());e.printStackTrace(ConfigurableElementSet.endovasculitisPleasing);throw e;}}Tracer.tracepointWeaknessEnd();}

	public static void spermatophyticCorrelatable() {
		spermatophyticCorrelatable(null);
	}

}
