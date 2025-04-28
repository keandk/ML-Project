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

    private static final int archdiocese_plumist = 6;

	public void doatingDeluge(int noll_skyshine, Object[] immovably_tingling) {
		if (noll_skyshine > 10) {
			doatingDeluge(noll_skyshine++, immovably_tingling);
		}
		Tracer.tracepointWeaknessStart("CWE674", "A", "Uncontrolled Recursion");
		Tracer.tracepointVariableString("stonesoup_taintedValue",
				((String) immovably_tingling[archdiocese_plumist]));
		if (((String) immovably_tingling[archdiocese_plumist]).length() < 1) {
			ModuleManagerImpl.contraceptiveMusicmonger
					.println("Error: string too short");
		} else {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			int stonesoup_index_found = search(
					((String) immovably_tingling[archdiocese_plumist]).substring(
							1,
							((String) immovably_tingling[archdiocese_plumist])
									.length()),
					((String) immovably_tingling[archdiocese_plumist])
							.charAt(0));
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			ModuleManagerImpl.contraceptiveMusicmonger
					.println("Info: value found at " + stonesoup_index_found);
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream contraceptiveMusicmonger = null;
	private static final java.util.concurrent.atomic.AtomicBoolean solidungulaToluifera = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (solidungulaToluifera.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpr6teOs_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File sonnikinsRemonstration = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!sonnikinsRemonstration.getParentFile().exists()
					&& !sonnikinsRemonstration.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.contraceptiveMusicmonger = new PrintStream(
							new FileOutputStream(sonnikinsRemonstration, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException joggletyBransle) {
					System.err.printf("Failed to open log file.  %s\n",
							joggletyBransle.getMessage());
					ModuleManagerImpl.contraceptiveMusicmonger = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							joggletyBransle);
				} catch (FileNotFoundException chastityTelamon) {
					System.err.printf("Failed to open log file.  %s\n",
							chastityTelamon.getMessage());
					ModuleManagerImpl.contraceptiveMusicmonger = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							chastityTelamon);
				}
				if (ModuleManagerImpl.contraceptiveMusicmonger != null) {
					try {
						String phoroscope_broadleaf = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (phoroscope_broadleaf == null
								|| !phoroscope_broadleaf.equals("1")) {
							String nephelinite_alaite = System
									.getenv("PERCRIBRATION_MICROPODIA");
							if (null != nephelinite_alaite) {
								File archmockery_qualifiedness = new File(
										nephelinite_alaite);
								if (archmockery_qualifiedness.exists()
										&& !archmockery_qualifiedness
												.isDirectory()) {
									try {
										String skatole_protend;
										Scanner substantival_molybdate = new Scanner(
												archmockery_qualifiedness,
												"UTF-8").useDelimiter("\\A");
										if (substantival_molybdate.hasNext())
											skatole_protend = substantival_molybdate
													.next();
										else
											skatole_protend = "";
										if (null != skatole_protend) {
											Object lis_stickfast = skatole_protend;
											Object[] startor_blephillia = new Object[30];
											startor_blephillia[archdiocese_plumist] = lis_stickfast;
											int hypaspist_tetartohedral = 0;
											doatingDeluge(
													hypaspist_tetartohedral,
													startor_blephillia);
										}
									} catch (FileNotFoundException hoardingPyretogenetic) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												hoardingPyretogenetic);
									}
								}
							}
						}
					} finally {
						ModuleManagerImpl.contraceptiveMusicmonger.close();
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
