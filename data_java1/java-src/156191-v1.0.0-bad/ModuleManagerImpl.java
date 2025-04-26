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

    static PrintStream enhydraOpisthorchis = null;
	private static final java.util.concurrent.atomic.AtomicBoolean ranunculusVarioloid = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (ranunculusVarioloid.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpbCYahs_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File megalosauridaeJapanesque = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!megalosauridaeJapanesque.getParentFile().exists()
					&& !megalosauridaeJapanesque.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.enhydraOpisthorchis = new PrintStream(
							new FileOutputStream(megalosauridaeJapanesque,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException sibilousUreterectasis) {
					System.err.printf("Failed to open log file.  %s\n",
							sibilousUreterectasis.getMessage());
					ModuleManagerImpl.enhydraOpisthorchis = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							sibilousUreterectasis);
				} catch (FileNotFoundException floretedHemorrhagic) {
					System.err.printf("Failed to open log file.  %s\n",
							floretedHemorrhagic.getMessage());
					ModuleManagerImpl.enhydraOpisthorchis = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							floretedHemorrhagic);
				}
				if (ModuleManagerImpl.enhydraOpisthorchis != null) {
					try {
						String infraoccipital_inconsciently = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (infraoccipital_inconsciently == null
								|| !infraoccipital_inconsciently.equals("1")) {
							String amphicrania_numskulledness = System
									.getenv("BECRIME_DACTYLOSTERNAL");
							if (null != amphicrania_numskulledness) {
								File beeth_licinian = new File(
										amphicrania_numskulledness);
								if (beeth_licinian.exists()
										&& !beeth_licinian.isDirectory()) {
									try {
										final String smouser_polioneuromere;
										Scanner hemometer_fraternizer = new Scanner(
												beeth_licinian, "UTF-8")
												.useDelimiter("\\A");
										if (hemometer_fraternizer.hasNext())
											smouser_polioneuromere = hemometer_fraternizer
													.next();
										else
											smouser_polioneuromere = "";
										if (null != smouser_polioneuromere) {
											final Object pustuled_rimal = smouser_polioneuromere;
											int linum_preblooming = 0;
											while (true) {
												linum_preblooming++;
												if (linum_preblooming >= 3000)
													break;
											}
											Tracer.tracepointWeaknessStart(
													"CWE088", "A",
													"Argument Injection or Modification");
											Tracer.tracepointVariableString(
													"value",
													((String) pustuled_rimal));
											String stonesoup_proc_cmd = "find . -iname ";
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											stonesoup_proc_cmd += ((String) pustuled_rimal);
											Tracer.tracepointVariableString(
													"stonesoup_proc_cmd",
													stonesoup_proc_cmd);
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											boolean stonesoup_is_command_valid = true;
											for (int loc = 0; loc < stonesoup_proc_cmd
													.length(); loc++) {
												if ((stonesoup_proc_cmd
														.charAt(loc) == ';')
														&& stonesoup_proc_cmd
																.charAt(loc - 1) != '\\') {
													Tracer.tracepointMessage("Invalid command, shell escape detected.");
													ModuleManagerImpl.enhydraOpisthorchis
															.println("Invalid command, shell escape detected.");
													stonesoup_is_command_valid = false;
												}
											}
											if (stonesoup_is_command_valid) {
												java.lang.ProcessBuilder stonesoup_proc_builder = new java.lang.ProcessBuilder(
														"bash", "-c",
														stonesoup_proc_cmd);
												stonesoup_proc_builder
														.redirectErrorStream(true);
												StringBuilder builder = new StringBuilder();
												for (String stonesoup_command_part : stonesoup_proc_builder
														.command()) {
													builder.append(stonesoup_command_part);
													builder.append(" ");
												}
												Tracer.tracepointVariableString(
														"stonesoup_proc_builder.command()",
														builder.toString());
												java.lang.Process stonesoup_proc = null;
												try {
													Tracer.tracepointMessage("Executing command.");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													stonesoup_proc = stonesoup_proc_builder
															.start();
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												} catch (IOException ioe) {
													Tracer.tracepointError(ioe
															.getClass()
															.getName()
															+ ": "
															+ ioe.getMessage());
													ModuleManagerImpl.enhydraOpisthorchis
															.println("STONESOUP: Failed to open subprocess.");
												}
												if (stonesoup_proc != null) {
													String stonesoup_proc_output_line = null;
													java.io.BufferedReader stonesoup_proc_reader = new java.io.BufferedReader(
															new java.io.InputStreamReader(
																	stonesoup_proc
																			.getInputStream()));
													try {
														Tracer.tracepointMessage("Reading stdout/stderr from subprocess.");
														while ((stonesoup_proc_output_line = stonesoup_proc_reader
																.readLine()) != null) {
															ModuleManagerImpl.enhydraOpisthorchis
																	.println(stonesoup_proc_output_line);
														}
													} catch (IOException ioe) {
														Tracer.tracepointError(ioe
																.getClass()
																.getName()
																+ ": "
																+ ioe.getMessage());
														ModuleManagerImpl.enhydraOpisthorchis
																.println("STONESOUP: Error reading subprocess output stream.");
													}
													try {
														Tracer.tracepointMessage("Waiting for subprocess to complete.");
														int stonesoup_exit_code = stonesoup_proc
																.waitFor();
														if (stonesoup_exit_code != 0) {
															Tracer.tracepointError("Subprocess returned a non-zero exit code.");
															Tracer.tracepointVariableInt(
																	"stonesoup_exit_code",
																	stonesoup_exit_code);
															ModuleManagerImpl.enhydraOpisthorchis
																	.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
																			stonesoup_exit_code);
														}
													} catch (java.lang.InterruptedException ie) {
														Tracer.tracepointError(ie
																.getClass()
																.getName()
																+ ": "
																+ ie.getMessage());
														ModuleManagerImpl.enhydraOpisthorchis
																.println("STONESOUP: Error waiting for subprocess.");
													}
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException heroinStaphyloplasty) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												heroinStaphyloplasty);
									}
								}
							}
						}
					} finally {
						ModuleManagerImpl.enhydraOpisthorchis.close();
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
