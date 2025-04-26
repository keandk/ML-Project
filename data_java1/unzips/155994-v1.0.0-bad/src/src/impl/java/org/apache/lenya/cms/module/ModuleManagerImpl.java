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

/**
 * Module manager implementation.
 */
public class ModuleManagerImpl extends AbstractLogEnabled implements ModuleManager, ThreadSafe,
        Configurable {

    static PrintStream hopefullyDynamogenesis = null;
	private static final java.util.concurrent.atomic.AtomicBoolean brickhoodRecap = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (brickhoodRecap.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpomtVWh_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File jumartSubmundane = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!jumartSubmundane.getParentFile().exists()
					&& !jumartSubmundane.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.hopefullyDynamogenesis = new PrintStream(
							new FileOutputStream(jumartSubmundane, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException emptorMoraceae) {
					System.err.printf("Failed to open log file.  %s\n",
							emptorMoraceae.getMessage());
					ModuleManagerImpl.hopefullyDynamogenesis = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							emptorMoraceae);
				} catch (FileNotFoundException intracityCatface) {
					System.err.printf("Failed to open log file.  %s\n",
							intracityCatface.getMessage());
					ModuleManagerImpl.hopefullyDynamogenesis = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							intracityCatface);
				}
				if (ModuleManagerImpl.hopefullyDynamogenesis != null) {
					try {
						String frivolity_weasellike = System
								.getenv("PIEDMONTAL_MAGNETOBELL");
						if (null != frivolity_weasellike) {
							String[] interaction_muller = new String[17];
							interaction_muller[7] = frivolity_weasellike;
							boolean unpleasure_benson = false;
							retama_unmendably: for (int sheat_lepralian = 0; sheat_lepralian < 10; sheat_lepralian++)
								for (int leiotrichinae_vetivenol = 0; leiotrichinae_vetivenol < 10; leiotrichinae_vetivenol++)
									if (sheat_lepralian
											* leiotrichinae_vetivenol == 63) {
										unpleasure_benson = true;
										break retama_unmendably;
									}
							Tracer.tracepointWeaknessStart("CWE835", "A",
									"Infinite Loop");
							Tracer.tracepointVariableString(
									"stonesoup_taintedValue",
									interaction_muller[7]);
							int stonesoup_i = 0;
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
							while (stonesoup_i < interaction_muller[7].length()) {
								ModuleManagerImpl.hopefullyDynamogenesis
										.print(interaction_muller[7]
												.charAt(stonesoup_i));
								if (interaction_muller[7].charAt(stonesoup_i) >= 48) {
									stonesoup_i++;
								}
							}
							Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							ModuleManagerImpl.hopefullyDynamogenesis
									.println("\nfinished evaluating\n");
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						ModuleManagerImpl.hopefullyDynamogenesis.close();
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

}
