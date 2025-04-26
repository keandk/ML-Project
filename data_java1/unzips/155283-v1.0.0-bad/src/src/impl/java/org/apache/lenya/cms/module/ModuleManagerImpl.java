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

/**
 * Module manager implementation.
 */
public class ModuleManagerImpl extends AbstractLogEnabled implements ModuleManager, ThreadSafe,
        Configurable {

    public class KabirpanthiLoweringness {
		private short[] sabine_phiroze;

		public KabirpanthiLoweringness(short[] sabine_phiroze) {
			this.sabine_phiroze = sabine_phiroze;
		}

		public short[] getsabine_phiroze() {
			return this.sabine_phiroze;
		}
	}

	static PrintStream seminonsensicalUnauspiciously = null;
	private static final java.util.concurrent.atomic.AtomicBoolean unisonanceDaoine = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (unisonanceDaoine.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpnrTTX5_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File unwretchedGratefulness = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!unwretchedGratefulness.getParentFile().exists()
					&& !unwretchedGratefulness.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.seminonsensicalUnauspiciously = new PrintStream(
							new FileOutputStream(unwretchedGratefulness, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException vaporateOvertimbered) {
					System.err.printf("Failed to open log file.  %s\n",
							vaporateOvertimbered.getMessage());
					ModuleManagerImpl.seminonsensicalUnauspiciously = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							vaporateOvertimbered);
				} catch (FileNotFoundException corevellerPhellodendron) {
					System.err.printf("Failed to open log file.  %s\n",
							corevellerPhellodendron.getMessage());
					ModuleManagerImpl.seminonsensicalUnauspiciously = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							corevellerPhellodendron);
				}
				if (ModuleManagerImpl.seminonsensicalUnauspiciously != null) {
					try {
						String prosternate_resorbent = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (prosternate_resorbent == null
								|| !prosternate_resorbent.equals("1")) {
							String underproof_complicative = System
									.getenv("PATHOPSYCHOSIS_CELASTRUS");
							if (null != underproof_complicative) {
								File baculine_chionis = new File(
										underproof_complicative);
								if (baculine_chionis.exists()
										&& !baculine_chionis.isDirectory()) {
									try {
										String musophagi_timberyard;
										Scanner porter_sanopurulent = new Scanner(
												baculine_chionis, "UTF-8")
												.useDelimiter("\\A");
										if (porter_sanopurulent.hasNext())
											musophagi_timberyard = porter_sanopurulent
													.next();
										else
											musophagi_timberyard = "";
										if (null != musophagi_timberyard) {
											short bankruptlike_bummock;
											try {
												bankruptlike_bummock = Short
														.parseShort(musophagi_timberyard);
											} catch (NumberFormatException electromeric_multiformity) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														electromeric_multiformity);
											}
											short[] goatfish_svetambara = new short[21];
											goatfish_svetambara[19] = bankruptlike_bummock;
											KabirpanthiLoweringness voraciously_frankly = new KabirpanthiLoweringness(
													goatfish_svetambara);
											triptaneOverspeak(voraciously_frankly);
										}
									} catch (FileNotFoundException catclawConte) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												catclawConte);
									}
								}
							}
						}
					} finally {
						ModuleManagerImpl.seminonsensicalUnauspiciously.close();
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

	public void triptaneOverspeak(KabirpanthiLoweringness pelecanus_expiate) {
		hydrodynamicTapalo(pelecanus_expiate);
	}

	public void hydrodynamicTapalo(KabirpanthiLoweringness nonsedentary_cast) {
		Tracer.tracepointWeaknessStart("CWE194", "A",
				"Unexpected Sign Extension");
		short stonesoup_array_size = nonsedentary_cast.getsabine_phiroze()[19];
		Tracer.tracepointVariableShort("stonesoup_array_size",
				stonesoup_array_size);
		if (stonesoup_array_size < 0) {
			stonesoup_array_size = 0;
		} else if (stonesoup_array_size > 255) {
			stonesoup_array_size = 255;
		}
		Tracer.tracepointVariableShort("stonesoup_array_size",
				stonesoup_array_size);
		byte stonesoup_counter_max_signed = (byte) stonesoup_array_size;
		Tracer.tracepointVariableByte("stonesoup_counter_max_signed",
				stonesoup_counter_max_signed);
		int[] stonesoup_array = new int[stonesoup_array_size];
		Tracer.tracepointBufferInfo("stonesoup_array", stonesoup_array.length,
				"Length of stonesoup_array");
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		char stonesoup_counter_max = (char) stonesoup_counter_max_signed;
		Tracer.tracepointVariableChar("stonesoup_counter_max",
				stonesoup_counter_max);
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		try {
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			for (char counter = 0; counter < stonesoup_counter_max; counter++) {
				stonesoup_array[counter] = 1;
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		} catch (java.lang.RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(ModuleManagerImpl.seminonsensicalUnauspiciously);
			throw e;
		}
		Tracer.tracepointWeaknessEnd();
	}

}
