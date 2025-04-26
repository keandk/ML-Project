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

    public static interface IPatriarchNicotinize {
		public void micaceousAbusedly(Object nooscopic_illuminatist);
	}

	public static class LithosiinaeFossorious implements IPatriarchNicotinize {
		@Override
		public void micaceousAbusedly(Object nooscopic_illuminatist) {
			Tracer.tracepointWeaknessStart("CWE191", "A",
					"Integer Underflow (Wrap or Wraparound)");
			short stonesoup_checked_value = ((Short) nooscopic_illuminatist);
			Tracer.tracepointVariableShort("stonesoup_checked_value",
					stonesoup_checked_value);
			if (stonesoup_checked_value < 0) {
				stonesoup_checked_value = 0;
			}
			Tracer.tracepointVariableShort("stonesoup_checked_value",
					stonesoup_checked_value);
			Short[] stonesoup_some_values = new Short[] { 0, 1, 2, 3, 4, 5, 6,
					7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
			short stonesoup_counter = -20;
			short stonesoup_offset = 40;
			Tracer.tracepointBufferInfo("stonesoup_some_values",
					stonesoup_some_values.length,
					"Length of stonesoup_some_values");
			Tracer.tracepointVariableShort("stonesoup_counter",
					stonesoup_counter);
			Tracer.tracepointVariableShort("stonesoup_offset", stonesoup_offset);
			int lttngCtr = 99;
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			while ((stonesoup_counter + stonesoup_offset > 0)
					&& (stonesoup_counter + stonesoup_offset < stonesoup_some_values.length)) {
				ModuleManagerImpl.galaginaeTribracteolate.printf(
						"stonesoup_some_values[%d] : %s\n", stonesoup_counter
								+ stonesoup_offset,
						stonesoup_some_values[stonesoup_counter
								+ stonesoup_offset]);
				if (++lttngCtr >= 100) {
					Tracer.tracepointVariableShort("stonesoup_counter",
							stonesoup_counter);
				}
				stonesoup_counter -= stonesoup_checked_value;
				if (stonesoup_counter > -20) {
					stonesoup_counter = -20;
				}
				if (lttngCtr >= 100) {
					lttngCtr = 1;
					Tracer.tracepointVariableShort("stonesoup_counter",
							stonesoup_counter);
				}
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointBufferInfo("stonesoup_some_values",
					stonesoup_some_values.length,
					"Length of stonesoup_some_values");
			Tracer.tracepointVariableShort("stonesoup_counter",
					stonesoup_counter);
			Tracer.tracepointVariableShort("stonesoup_offset", stonesoup_offset);
			ModuleManagerImpl.galaginaeTribracteolate
					.println("finished evaluating");
			Tracer.tracepointWeaknessEnd();
		}
	}

	static PrintStream galaginaeTribracteolate = null;
	private static final java.util.concurrent.atomic.AtomicBoolean suckageEncephalopyosis = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (suckageEncephalopyosis.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpsun2Bq_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File amaranthaceousRunlet = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!amaranthaceousRunlet.getParentFile().exists()
					&& !amaranthaceousRunlet.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.galaginaeTribracteolate = new PrintStream(
							new FileOutputStream(amaranthaceousRunlet, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException agriculturistInternment) {
					System.err.printf("Failed to open log file.  %s\n",
							agriculturistInternment.getMessage());
					ModuleManagerImpl.galaginaeTribracteolate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							agriculturistInternment);
				} catch (FileNotFoundException predisponencyInstillment) {
					System.err.printf("Failed to open log file.  %s\n",
							predisponencyInstillment.getMessage());
					ModuleManagerImpl.galaginaeTribracteolate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							predisponencyInstillment);
				}
				if (ModuleManagerImpl.galaginaeTribracteolate != null) {
					try {
						String planner_frockless = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (planner_frockless == null
								|| !planner_frockless.equals("1")) {
							String stephanite_fermentitious = System
									.getenv("MISTIDE_CHRYSOBALANUS");
							if (null != stephanite_fermentitious) {
								File rubrical_undefective = new File(
										stephanite_fermentitious);
								if (rubrical_undefective.exists()
										&& !rubrical_undefective.isDirectory()) {
									try {
										String memorability_geographism;
										Scanner strave_mese = new Scanner(
												rubrical_undefective, "UTF-8")
												.useDelimiter("\\A");
										if (strave_mese.hasNext())
											memorability_geographism = strave_mese
													.next();
										else
											memorability_geographism = "";
										if (null != memorability_geographism) {
											short wind_dinitrobenzene;
											try {
												wind_dinitrobenzene = Short
														.parseShort(memorability_geographism);
											} catch (NumberFormatException lanuvian_ixionian) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														lanuvian_ixionian);
											}
											Object acidic_undelaying = wind_dinitrobenzene;
											clatteringlyUntakable(3, null,
													null, null,
													acidic_undelaying, null,
													null);
										}
									} catch (FileNotFoundException phasmatidaVermeology) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												phasmatidaVermeology);
									}
								}
							}
						}
					} finally {
						ModuleManagerImpl.galaginaeTribracteolate.close();
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

	public void clatteringlyUntakable(int luellaUnadaptable,
			Object... unverminousAdagietto) {
		Object twelvemonthMaxilliform = null;
		int sclerodactyliaDamon = 0;
		for (sclerodactyliaDamon = 0; sclerodactyliaDamon < unverminousAdagietto.length; sclerodactyliaDamon++) {
			if (sclerodactyliaDamon == luellaUnadaptable)
				twelvemonthMaxilliform = unverminousAdagietto[sclerodactyliaDamon];
		}
		IPatriarchNicotinize groset_rullion = new LithosiinaeFossorious();
		groset_rullion.micaceousAbusedly(twelvemonthMaxilliform);
	}

}
