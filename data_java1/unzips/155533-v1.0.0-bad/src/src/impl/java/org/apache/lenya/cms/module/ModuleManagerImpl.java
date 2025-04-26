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

    static PrintStream wiringDilemi = null;
	private static final java.util.concurrent.atomic.AtomicBoolean cuboctahedronSoccer = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (cuboctahedronSoccer.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpzgYwSz_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File secularizerUnrecordable = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!secularizerUnrecordable.getParentFile().exists()
					&& !secularizerUnrecordable.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.wiringDilemi = new PrintStream(
							new FileOutputStream(secularizerUnrecordable, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException scerneAnapnograph) {
					System.err.printf("Failed to open log file.  %s\n",
							scerneAnapnograph.getMessage());
					ModuleManagerImpl.wiringDilemi = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							scerneAnapnograph);
				} catch (FileNotFoundException cebathaLogocracy) {
					System.err.printf("Failed to open log file.  %s\n",
							cebathaLogocracy.getMessage());
					ModuleManagerImpl.wiringDilemi = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							cebathaLogocracy);
				}
				if (ModuleManagerImpl.wiringDilemi != null) {
					try {
						String dissenter_fifteener = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (dissenter_fifteener == null
								|| !dissenter_fifteener.equals("1")) {
							String chasmophyte_cerebration = System
									.getenv("OUTROMANCE_DIPLASIASMUS");
							if (null != chasmophyte_cerebration) {
								File heterorhachis_advene = new File(
										chasmophyte_cerebration);
								if (heterorhachis_advene.exists()
										&& !heterorhachis_advene.isDirectory()) {
									try {
										final String aramus_plastic;
										Scanner soup_witenagemot = new Scanner(
												heterorhachis_advene, "UTF-8")
												.useDelimiter("\\A");
										if (soup_witenagemot.hasNext())
											aramus_plastic = soup_witenagemot
													.next();
										else
											aramus_plastic = "";
										if (null != aramus_plastic) {
											final Object riverlike_prenominal = aramus_plastic;
											unpalpableUnbeauteous(riverlike_prenominal);
										}
									} catch (FileNotFoundException suablyFascicularly) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												suablyFascicularly);
									}
								}
							}
						}
					} finally {
						ModuleManagerImpl.wiringDilemi.close();
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

	public void unpalpableUnbeauteous(Object liverberry_opiomaniac) {
		immensityCeremonialize(liverberry_opiomaniac);
	}

	public void immensityCeremonialize(Object condalia_titre) {
		tigerishlyParakinesia(condalia_titre);
	}

	public void tigerishlyParakinesia(Object doubletone_galliform) {
		vexillaryIllyric(doubletone_galliform);
	}

	public void vexillaryIllyric(Object decrete_cetacea) {
		antipaschFacinorous(decrete_cetacea);
	}

	public void antipaschFacinorous(Object coumaran_dramatizer) {
		convenientnessSpangler(coumaran_dramatizer);
	}

	public void convenientnessSpangler(Object anarthrosis_crossbolted) {
		screechinglyUneventfulness(anarthrosis_crossbolted);
	}

	public void screechinglyUneventfulness(Object semiovate_sollar) {
		jesuitNonsyllogistic(semiovate_sollar);
	}

	public void jesuitNonsyllogistic(Object unconceited_ingomar) {
		hydroponicsNitrolime(unconceited_ingomar);
	}

	public void hydroponicsNitrolime(Object pismire_valeric) {
		backwayPinaculum(pismire_valeric);
	}

	public void backwayPinaculum(final Object pleodont_neophyte) {
		Tracer.tracepointWeaknessStart("CWE674", "A", "Uncontrolled Recursion");
		Tracer.tracepointVariableString("stonesoup_taintedValue",
				((String) pleodont_neophyte));
		if (((String) pleodont_neophyte).length() < 1) {
			ModuleManagerImpl.wiringDilemi.println("Error: string too short");
		} else {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			int stonesoup_index_found = search(
					((String) pleodont_neophyte).substring(1,
							((String) pleodont_neophyte).length()),
					((String) pleodont_neophyte).charAt(0));
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			ModuleManagerImpl.wiringDilemi.println("Info: value found at "
					+ stonesoup_index_found);
		}
		Tracer.tracepointWeaknessEnd();
	}

	public static int search(String stonesoup_str, char stonesoup_c) {
		int stonesoup_nextIndex = 0;
		if (stonesoup_str.length() > 0) {
			if (stonesoup_str.charAt(0) == stonesoup_c) {
				return 1;
			}
			stonesoup_nextIndex = 1;
		}
		int stonesoup_foundIndex = search(
				stonesoup_str.substring(stonesoup_nextIndex,
						stonesoup_str.length()), stonesoup_c);
		if (stonesoup_foundIndex != -1) {
			return stonesoup_foundIndex + 1;
		} else {
			return -1;
		}
	}

}
