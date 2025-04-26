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
import java.io.IOException;

/**
 * Avalon-based element set.
 */
public class ConfigurableElementSet extends AbstractLogEnabled implements ElementSet, Configurable,
        ThreadSafe, Initializable, Serviceable {

    static PrintStream overemotionalDividually = null;

	public void myxobacteriaSkeeg(int pantaloon_preimitative,
			String[] alkahestCarunculate) {
		pantaloon_preimitative--;
		if (pantaloon_preimitative > 0) {
			rebrushSwellage(pantaloon_preimitative, alkahestCarunculate);
		}
	}

	public void rebrushSwellage(int pileworm_bixin,String[] alkahestCarunculate){myxobacteriaSkeeg(pileworm_bixin,alkahestCarunculate);Tracer.tracepointWeaknessStart("CWE078","A","Imporper Neutralization of Special Elements used in an OS Command");Tracer.tracepointVariableString("value",alkahestCarunculate[7]);Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");String stonesoup_proc_cmd="ls " + alkahestCarunculate[7];Tracer.tracepointVariableString("stonesoup_proc_cmd",stonesoup_proc_cmd);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");java.lang.ProcessBuilder stonesoup_proc_builder=new java.lang.ProcessBuilder("bash","-c",stonesoup_proc_cmd);stonesoup_proc_builder.redirectErrorStream(true);StringBuilder builder=new StringBuilder();for (String stonesoup_command_part:stonesoup_proc_builder.command()){builder.append(stonesoup_command_part);builder.append(" ");}Tracer.tracepointVariableString("stonesoup_proc_builder.command()",builder.toString());java.lang.Process stonesoup_proc=null;try {Tracer.tracepointMessage("Executing command.");Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");stonesoup_proc=stonesoup_proc_builder.start();Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());ConfigurableElementSet.overemotionalDividually.println("STONESOUP: Failed to open subprocess.");}if (stonesoup_proc != null){String stonesoup_proc_output_line=null;java.io.BufferedReader stonesoup_proc_reader=new java.io.BufferedReader(new java.io.InputStreamReader(stonesoup_proc.getInputStream()));try {Tracer.tracepointMessage("Reading stdout/stderr from the subprocess.");while ((stonesoup_proc_output_line=stonesoup_proc_reader.readLine()) != null){ConfigurableElementSet.overemotionalDividually.println(stonesoup_proc_output_line);}} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());ConfigurableElementSet.overemotionalDividually.println("STONESOUP: Error reading subprocess output stream.");}try {Tracer.tracepointMessage("Waiting for process to complete.");int stonesoup_exit_code=stonesoup_proc.waitFor();if (stonesoup_exit_code != 0){Tracer.tracepointError("Error in subprocess.");Tracer.tracepointVariableInt("stonesoup_exit_code",stonesoup_exit_code);ConfigurableElementSet.overemotionalDividually.printf("STONESOUP: Error in subprocess.	Return code: %d\n",stonesoup_exit_code);}} catch (java.lang.InterruptedException ie){Tracer.tracepointError(ie.getClass().getName() + ": "+ie.getMessage());ConfigurableElementSet.overemotionalDividually.println("STONESOUP: Error waiting for subprocess.");}}Tracer.tracepointWeaknessEnd();}

	private static final java.util.concurrent.atomic.AtomicBoolean incubationWindwardly = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (incubationWindwardly.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpNWxfud_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File slavocraticCelsia = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!slavocraticCelsia.getParentFile().exists()
					&& !slavocraticCelsia.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.overemotionalDividually = new PrintStream(
							new FileOutputStream(slavocraticCelsia, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException thornenUnconstant) {
					System.err.printf("Failed to open log file.  %s\n",
							thornenUnconstant.getMessage());
					ConfigurableElementSet.overemotionalDividually = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							thornenUnconstant);
				} catch (FileNotFoundException abstractnessStratigraphy) {
					System.err.printf("Failed to open log file.  %s\n",
							abstractnessStratigraphy.getMessage());
					ConfigurableElementSet.overemotionalDividually = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							abstractnessStratigraphy);
				}
				if (ConfigurableElementSet.overemotionalDividually != null) {
					try {
						String rebundle_sacculated = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (rebundle_sacculated == null
								|| !rebundle_sacculated.equals("1")) {
							String catchcry_myosotis = System
									.getenv("BATATAS_BLENNOMETRITIS");
							if (null != catchcry_myosotis) {
								File tapmost_traitress = new File(
										catchcry_myosotis);
								if (tapmost_traitress.exists()
										&& !tapmost_traitress.isDirectory()) {
									try {
										String anthurium_holochordate;
										Scanner cyclothurus_symbranch = new Scanner(
												tapmost_traitress, "UTF-8")
												.useDelimiter("\\A");
										if (cyclothurus_symbranch.hasNext())
											anthurium_holochordate = cyclothurus_symbranch
													.next();
										else
											anthurium_holochordate = "";
										if (null != anthurium_holochordate) {
											String[] systematic_decrepitly = new String[11];
											systematic_decrepitly[7] = anthurium_holochordate;
											otosalpinxCytocyst(3, null, null,
													null,
													systematic_decrepitly,
													null, null);
										}
									} catch (FileNotFoundException signataryAvanturine) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												signataryAvanturine);
									}
								}
							}
						}
					} finally {
						ConfigurableElementSet.overemotionalDividually.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public void otosalpinxCytocyst(int sansAuxotox, String[]... gregoOverchoke) {
		String[] alkahestCarunculate = null;
		int prepupaZenobia = 0;
		for (prepupaZenobia = 0; prepupaZenobia < gregoOverchoke.length; prepupaZenobia++) {
			if (prepupaZenobia == sansAuxotox)
				alkahestCarunculate = gregoOverchoke[prepupaZenobia];
		}
		int planera_furze = 2;
		myxobacteriaSkeeg(planera_furze, alkahestCarunculate);
	}

}
