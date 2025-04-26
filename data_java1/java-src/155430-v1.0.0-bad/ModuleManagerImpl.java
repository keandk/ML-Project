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

    public class UntransitiveImmune<T> {
		private T allantoinuria_textualist;

		public UntransitiveImmune(T allantoinuria_textualist) {
			this.allantoinuria_textualist = allantoinuria_textualist;
		}

		public T getallantoinuria_textualist() {
			return this.allantoinuria_textualist;
		}
	}

	static PrintStream intersterilityLernaeoid = null;
	private static final java.util.concurrent.atomic.AtomicBoolean hercogamyMalanders = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (hercogamyMalanders.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp_bZmrT_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File ruminationPericecitis = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!ruminationPericecitis.getParentFile().exists()
					&& !ruminationPericecitis.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.intersterilityLernaeoid = new PrintStream(
							new FileOutputStream(ruminationPericecitis, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException cyclanthalesRendrock) {
					System.err.printf("Failed to open log file.  %s\n",
							cyclanthalesRendrock.getMessage());
					ModuleManagerImpl.intersterilityLernaeoid = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							cyclanthalesRendrock);
				} catch (FileNotFoundException vivisepultureAuntly) {
					System.err.printf("Failed to open log file.  %s\n",
							vivisepultureAuntly.getMessage());
					ModuleManagerImpl.intersterilityLernaeoid = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							vivisepultureAuntly);
				}
				if (ModuleManagerImpl.intersterilityLernaeoid != null) {
					try {
						String anidiomatic_asdic = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (anidiomatic_asdic == null
								|| !anidiomatic_asdic.equals("1")) {
							String unsaturatedness_sequela = System
									.getenv("TUTRICE_AGALLOCH");
							if (null != unsaturatedness_sequela) {
								File unlordly_knuckly = new File(
										unsaturatedness_sequela);
								if (unlordly_knuckly.exists()
										&& !unlordly_knuckly.isDirectory()) {
									try {
										String paracephalus_quotationally;
										Scanner armillate_herniopuncture = new Scanner(
												unlordly_knuckly, "UTF-8")
												.useDelimiter("\\A");
										if (armillate_herniopuncture.hasNext())
											paracephalus_quotationally = armillate_herniopuncture
													.next();
										else
											paracephalus_quotationally = "";
										if (null != paracephalus_quotationally) {
											int cinerama_encephalometer;
											try {
												cinerama_encephalometer = Integer
														.parseInt(paracephalus_quotationally);
											} catch (NumberFormatException leitneriales_concent) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														leitneriales_concent);
											}
											UntransitiveImmune<Integer> overfrail_cholesterinuria = new UntransitiveImmune<Integer>(
													cinerama_encephalometer);
											ekaboronGrather(overfrail_cholesterinuria);
										}
									} catch (FileNotFoundException spiritualisticGarapata) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												spiritualisticGarapata);
									}
								}
							}
						}
					} finally {
						ModuleManagerImpl.intersterilityLernaeoid.close();
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

	public void ekaboronGrather(
			UntransitiveImmune<Integer> intershoot_eucharistically) {
		Tracer.tracepointWeaknessStart("CWE369", "A", "Divide By Zero");
		Tracer.tracepointVariableInt("value",
				intershoot_eucharistically.getallantoinuria_textualist());
		if (intershoot_eucharistically.getallantoinuria_textualist() != 0) {
			try {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				int random = (8191 * intershoot_eucharistically
						.getallantoinuria_textualist()) % (1 << 15);
				Tracer.tracepointVariableInt("random", random);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				int factor = (1 << 31) % random;
				Tracer.tracepointVariableInt("factor", factor);
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				ModuleManagerImpl.intersterilityLernaeoid.printf(
						"Random Factor: %d\n", factor);
			} catch (java.lang.RuntimeException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				e.printStackTrace(ModuleManagerImpl.intersterilityLernaeoid);
				throw e;
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

}
