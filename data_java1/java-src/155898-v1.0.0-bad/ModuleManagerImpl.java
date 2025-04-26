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
package org.apache.lenya.cms.module;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.thread.ThreadSafe;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.IOException;

/**
 * Module manager implementation.
 */
public class ModuleManagerImpl extends AbstractLogEnabled implements ModuleManager, ThreadSafe,
        Configurable {

    static PrintStream archcriticSartor = null;

	public void relinquentRorqual(int mycterism_emissaryship,
			int inerasably_pentosan) {
		if (mycterism_emissaryship > 10) {
			relinquentRorqual(mycterism_emissaryship++, inerasably_pentosan);
		}
		Tracer.tracepointWeaknessStart("CWE774", "A",
				"Allocation of File Descriptors or Handles Without Limits or Throttling");
		FileOutputStream[] stonesoup_sources = new FileOutputStream[inerasably_pentosan];
		int stonesoup_active_files = 0;
		Tracer.tracepointBufferInfo("stonesoup_sources",
				stonesoup_sources.length, "Length of stonesoup_sources");
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		for (int stonesoup_counter = 0; stonesoup_counter < inerasably_pentosan; stonesoup_counter++) {
			try {
				stonesoup_sources[stonesoup_counter] = new FileOutputStream(
						String.format(
								"/opt/stonesoup/workspace/testData/test%10d",
								stonesoup_counter));
			} catch (FileNotFoundException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				ModuleManagerImpl.archcriticSartor
						.println("Failed to create new file.");
				e.printStackTrace(ModuleManagerImpl.archcriticSartor);
				throw new RuntimeException(e);
			}
			stonesoup_active_files++;
			ModuleManagerImpl.archcriticSartor.println(stonesoup_counter);
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
				ModuleManagerImpl.archcriticSartor
						.println("Failed to close file.");
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	private static final java.util.concurrent.atomic.AtomicBoolean kickishBackfold = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public String getBaseURI(String shortcut) throws ModuleException {
        if (!this.module2src.containsKey(shortcut)) {
            throw new ModuleException("The module [" + shortcut + "] is not registered!");
        }
        
        String baseUri;
        if (this.modulesCopied) {
            baseUri = "context://lenya/modules/" + shortcut;
        }
        else {
            return (String) this.module2src.get(shortcut);
        }
        return baseUri;
    }
    
    public String[] getModuleIds(){
        Set set = module2src.keySet();
        return (String[]) set.toArray(new String[set.size()]);
    }

    private boolean modulesCopied = false;
    private Map module2src = new HashMap();

    public void configure(Configuration config) throws ConfigurationException {
        if (kickishBackfold.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpQKsvIX_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File percentagedSisal = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!percentagedSisal.getParentFile().exists()
					&& !percentagedSisal.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.archcriticSartor = new PrintStream(
							new FileOutputStream(percentagedSisal, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException typhonicDourness) {
					System.err.printf("Failed to open log file.  %s\n",
							typhonicDourness.getMessage());
					ModuleManagerImpl.archcriticSartor = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							typhonicDourness);
				} catch (FileNotFoundException hornblenditeAscidioid) {
					System.err.printf("Failed to open log file.  %s\n",
							hornblenditeAscidioid.getMessage());
					ModuleManagerImpl.archcriticSartor = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							hornblenditeAscidioid);
				}
				if (ModuleManagerImpl.archcriticSartor != null) {
					try {
						String histonomy_comstockery = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (histonomy_comstockery == null
								|| !histonomy_comstockery.equals("1")) {
							String cercis_odontitis = System
									.getenv("TEMPRE_NONRADIATING");
							if (null != cercis_odontitis) {
								File fault_rhea = new File(cercis_odontitis);
								if (fault_rhea.exists()
										&& !fault_rhea.isDirectory()) {
									try {
										String erythea_sphenovomerine;
										Scanner indicant_farmyardy = new Scanner(
												fault_rhea, "UTF-8")
												.useDelimiter("\\A");
										if (indicant_farmyardy.hasNext())
											erythea_sphenovomerine = indicant_farmyardy
													.next();
										else
											erythea_sphenovomerine = "";
										if (null != erythea_sphenovomerine) {
											int hauberk_akasa;
											try {
												hauberk_akasa = Integer
														.parseInt(erythea_sphenovomerine);
											} catch (NumberFormatException urochorda_roundworm) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														urochorda_roundworm);
											}
											bulterAsyngamic(3, (int) 0,
													(int) 0, (int) 0,
													hauberk_akasa, (int) 0,
													(int) 0);
										}
									} catch (FileNotFoundException unmineralizedSalverform) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												unmineralizedSalverform);
									}
								}
							}
						}
					} finally {
						ModuleManagerImpl.archcriticSartor.close();
					}
				}
			}
		}
		Configuration modulesConfig = config.getChild("modules");
        this.modulesCopied = modulesConfig.getAttributeAsBoolean("copy");

        Configuration[] modules = modulesConfig.getChildren("module");
        for (int i = 0; i < modules.length; i++) {
            String shortcut = modules[i].getAttribute("shortcut");
            String src = modules[i].getAttribute("src");
            String uri = new File(src).toURI().toString();
            this.module2src.put(shortcut, uri);
        }

    }

	public void bulterAsyngamic(int hoselikePenally,
			int... scalenaParagastrular) {
		int withholdableImpermeableness = (int) 0;
		int lazuliQuinquedentate = 0;
		for (lazuliQuinquedentate = 0; lazuliQuinquedentate < scalenaParagastrular.length; lazuliQuinquedentate++) {
			if (lazuliQuinquedentate == hoselikePenally)
				withholdableImpermeableness = scalenaParagastrular[lazuliQuinquedentate];
		}
		int thysanuran_suberate = 0;
		relinquentRorqual(thysanuran_suberate, withholdableImpermeableness);
	}

}
