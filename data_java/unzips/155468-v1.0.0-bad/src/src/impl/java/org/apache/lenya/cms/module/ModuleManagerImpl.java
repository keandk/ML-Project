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
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

/**
 * Module manager implementation.
 */
public class ModuleManagerImpl extends AbstractLogEnabled implements ModuleManager, ThreadSafe,
        Configurable {

    public class DicycleAntihygienic<T> {
		private T dispraiser_telepost;

		public DicycleAntihygienic(T dispraiser_telepost) {
			this.dispraiser_telepost = dispraiser_telepost;
		}

		public T getdispraiser_telepost() {
			return this.dispraiser_telepost;
		}
	}

	static PrintStream burglarSerenify = null;
	private static final java.util.concurrent.atomic.AtomicBoolean theologiUninterred = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (theologiUninterred.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpaJrB8g_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File unclerklyFrogling = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!unclerklyFrogling.getParentFile().exists()
					&& !unclerklyFrogling.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.burglarSerenify = new PrintStream(
							new FileOutputStream(unclerklyFrogling, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException encoronateCometic) {
					System.err.printf("Failed to open log file.  %s\n",
							encoronateCometic.getMessage());
					ModuleManagerImpl.burglarSerenify = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							encoronateCometic);
				} catch (FileNotFoundException dynamogenesisInustion) {
					System.err.printf("Failed to open log file.  %s\n",
							dynamogenesisInustion.getMessage());
					ModuleManagerImpl.burglarSerenify = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							dynamogenesisInustion);
				}
				if (ModuleManagerImpl.burglarSerenify != null) {
					try {
						String spondylioid_baa = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (spondylioid_baa == null
								|| !spondylioid_baa.equals("1")) {
							String chalta_demigorge = System
									.getenv("TENSIBLE_CYCLOPEDIC");
							if (null != chalta_demigorge) {
								File roomful_landslip = new File(
										chalta_demigorge);
								if (roomful_landslip.exists()
										&& !roomful_landslip.isDirectory()) {
									try {
										String cresswort_haplont;
										Scanner troft_ophicephaloid = new Scanner(
												roomful_landslip, "UTF-8")
												.useDelimiter("\\A");
										if (troft_ophicephaloid.hasNext())
											cresswort_haplont = troft_ophicephaloid
													.next();
										else
											cresswort_haplont = "";
										if (null != cresswort_haplont) {
											int basidiomycetous_osteofibroma;
											try {
												basidiomycetous_osteofibroma = Integer
														.parseInt(cresswort_haplont);
											} catch (NumberFormatException minhah_math) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														minhah_math);
											}
											DicycleAntihygienic<Integer> wrannock_antistrike = new DicycleAntihygienic<Integer>(
													basidiomycetous_osteofibroma);
											try {
												String logger_podosomatous = System
														.getProperty("os.name");
												if (null != logger_podosomatous) {
													if (!logger_podosomatous
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException spodomancy_multiplane) {
											} finally {
												Tracer.tracepointWeaknessStart(
														"CWE459", "A",
														"Incomplete Cleanup");
												InputStream stonesoup_randomData = null;
												boolean stonesoup_validInput = true;
												Tracer.tracepointVariableInt(
														"stonesoup_intValue",
														wrannock_antistrike
																.getdispraiser_telepost());
												byte[] stonesoup_randomChars = null;
												try {
													ModuleManagerImpl.burglarSerenify
															.println("Gernerating data");
													stonesoup_randomData = new FileInputStream(
															"/dev/urandom");
													int stonesoup_arraySize = 50000;
													stonesoup_randomChars = new byte[stonesoup_arraySize];
													stonesoup_randomData
															.read(stonesoup_randomChars,
																	0,
																	stonesoup_arraySize);
												} catch (FileNotFoundException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													ModuleManagerImpl.burglarSerenify
															.println("Error: /dev/urandom not found");
													stonesoup_validInput = false;
												} catch (IOException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													ModuleManagerImpl.burglarSerenify
															.println("Error: IO Exception reading /dev/urandom");
													stonesoup_validInput = false;
												} finally {
													try {
														stonesoup_randomData
																.close();
													} catch (IOException e) {
														ModuleManagerImpl.burglarSerenify
																.println("Error: Cannot close /dev/urandom");
														stonesoup_validInput = false;
													}
												}
												if (stonesoup_validInput) {
													int stonesoup_numFilePaths = 50;
													File[] stonesoup_filePaths = new File[stonesoup_numFilePaths];
													int stonesoup_i = 0;
													OutputStream stonesoup_outputStream = null;
													try {
														ModuleManagerImpl.burglarSerenify
																.println("Saving data");
														for (stonesoup_i = 0; stonesoup_i < wrannock_antistrike
																.getdispraiser_telepost(); stonesoup_i++) {
															stonesoup_filePaths[stonesoup_i
																	% stonesoup_numFilePaths] = File
																	.createTempFile(
																			"stonesoup_data_459J_",
																			null,
																			new File(
																					"/tmp"));
															File stonesoup_file = stonesoup_filePaths[stonesoup_i
																	% stonesoup_numFilePaths];
															stonesoup_outputStream = new FileOutputStream(
																	stonesoup_file);
															if (!stonesoup_file
																	.exists()) {
																stonesoup_file
																		.createNewFile();
															}
															stonesoup_outputStream
																	.write(stonesoup_randomChars);
															stonesoup_outputStream
																	.close();
															stonesoup_outputStream = null;
														}
														Tracer.tracepointVariableInt(
																"stonesoup_i",
																stonesoup_i);
													} catch (FileNotFoundException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														ModuleManagerImpl.burglarSerenify
																.println("Error: tmp file  not found");
													} catch (IOException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														ModuleManagerImpl.burglarSerenify
																.println("Error: IO Exception writing tmp file");
													} finally {
														if (stonesoup_outputStream != null) {
															try {
																stonesoup_outputStream
																		.close();
															} catch (IOException e) {
																ModuleManagerImpl.burglarSerenify
																		.println("Error: could not delete output stream");
															}
														}
														Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
														Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
														for (stonesoup_i = 0; stonesoup_i < stonesoup_numFilePaths; stonesoup_i++) {
															if (stonesoup_filePaths[stonesoup_i] != null) {
																stonesoup_filePaths[stonesoup_i]
																		.delete();
															}
														}
														Tracer.tracepointVariableInt(
																"stonesoup_i",
																stonesoup_i);
														Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
														Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													}
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException ossianismTerroristic) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												ossianismTerroristic);
									}
								}
							}
						}
					} finally {
						ModuleManagerImpl.burglarSerenify.close();
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
