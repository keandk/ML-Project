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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Module manager implementation.
 */
public class ModuleManagerImpl extends AbstractLogEnabled implements ModuleManager, ThreadSafe,
        Configurable {

    public class CharleenRheophile {
		private int drawstring_epithelize;

		public CharleenRheophile(int drawstring_epithelize) {
			this.drawstring_epithelize = drawstring_epithelize;
		}

		public int getdrawstring_epithelize() {
			return this.drawstring_epithelize;
		}
	}

	static PrintStream purificantHypovanadious = null;
	private static final java.util.concurrent.atomic.AtomicBoolean asperserReanoint = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (asperserReanoint.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpoMLEpp_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File reimpatriateAcrodactylum = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!reimpatriateAcrodactylum.getParentFile().exists()
					&& !reimpatriateAcrodactylum.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.purificantHypovanadious = new PrintStream(
							new FileOutputStream(reimpatriateAcrodactylum,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException unreformablePartimembered) {
					System.err.printf("Failed to open log file.  %s\n",
							unreformablePartimembered.getMessage());
					ModuleManagerImpl.purificantHypovanadious = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unreformablePartimembered);
				} catch (FileNotFoundException pedagogicallyKarelian) {
					System.err.printf("Failed to open log file.  %s\n",
							pedagogicallyKarelian.getMessage());
					ModuleManagerImpl.purificantHypovanadious = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							pedagogicallyKarelian);
				}
				if (ModuleManagerImpl.purificantHypovanadious != null) {
					try {
						String perisporiaceous_psychologics = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (perisporiaceous_psychologics == null
								|| !perisporiaceous_psychologics.equals("1")) {
							String photologic_votation = System
									.getenv("DACOITY_PHENOMENALIZE");
							if (null != photologic_votation) {
								File brunoniaceae_adeniform = new File(
										photologic_votation);
								if (brunoniaceae_adeniform.exists()
										&& !brunoniaceae_adeniform
												.isDirectory()) {
									try {
										String nonnaturalistic_haulmy;
										Scanner ecospecifically_tungan = new Scanner(
												brunoniaceae_adeniform, "UTF-8")
												.useDelimiter("\\A");
										if (ecospecifically_tungan.hasNext())
											nonnaturalistic_haulmy = ecospecifically_tungan
													.next();
										else
											nonnaturalistic_haulmy = "";
										if (null != nonnaturalistic_haulmy) {
											int jesuitist_markedness;
											try {
												jesuitist_markedness = Integer
														.parseInt(nonnaturalistic_haulmy);
											} catch (NumberFormatException sorb_trixy) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														sorb_trixy);
											}
											CharleenRheophile divata_yoy = new CharleenRheophile(
													jesuitist_markedness);
											boolean drupa_stethoscope = false;
											heelmaker_plateasm: for (int distich_stupid = 0; distich_stupid < 10; distich_stupid++)
												for (int monticuliporoid_tropicopolitan = 0; monticuliporoid_tropicopolitan < 10; monticuliporoid_tropicopolitan++)
													if (distich_stupid
															* monticuliporoid_tropicopolitan == 63) {
														drupa_stethoscope = true;
														break heelmaker_plateasm;
													}
											Tracer.tracepointWeaknessStart(
													"CWE606", "B",
													"Uncheck Input for Loop Condition");
											char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
													.toCharArray();
											SecureRandom random = null;
											try {
												random = SecureRandom
														.getInstance("SHA1PRNG");
											} catch (NoSuchAlgorithmException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												ModuleManagerImpl.purificantHypovanadious
														.println("STONESOUP: Random generator algorithm does not exist.");
											}
											Tracer.tracepointVariableInt(
													"value",
													divata_yoy
															.getdrawstring_epithelize());
											if (random != null) {
												StringBuilder stonesoup_filename = new StringBuilder();
												ModuleManagerImpl.purificantHypovanadious
														.println("Generating file name");
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												for (int stonesoup_counter = 0; stonesoup_counter < divata_yoy
														.getdrawstring_epithelize(); stonesoup_counter++) {
													stonesoup_filename
															.append(stonesoup_random_charset[random
																	.nextInt(stonesoup_random_charset.length)]);
												}
												Tracer.tracepointVariableString(
														"stonesoup_filename",
														stonesoup_filename
																.toString());
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												if (stonesoup_filename.length() > 0) {
													File writePath = new File(
															stonesoup_filename
																	.toString());
													try {
														Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
														writePath
																.createNewFile();
														Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													} catch (IOException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														ModuleManagerImpl.purificantHypovanadious
																.println("Failed to create file.");
														ModuleManagerImpl.purificantHypovanadious
																.println("Error:");
														e.printStackTrace(ModuleManagerImpl.purificantHypovanadious);
														throw new RuntimeException(
																"Unknown error in filename.",
																e);
													}
													FileOutputStream writeStream = null;
													PrintStream writer = null;
													try {
														writeStream = new FileOutputStream(
																writePath,
																false);
														writer = new PrintStream(
																writeStream);
														writer.println("/* This is my file */");
													} catch (FileNotFoundException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														ModuleManagerImpl.purificantHypovanadious
																.println("Failed to create file.");
														e.printStackTrace(ModuleManagerImpl.purificantHypovanadious);
													} finally {
														if (writer != null) {
															writer.close();
														}
													}
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException vastityFantasy) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												vastityFantasy);
									}
								}
							}
						}
					} finally {
						ModuleManagerImpl.purificantHypovanadious.close();
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
