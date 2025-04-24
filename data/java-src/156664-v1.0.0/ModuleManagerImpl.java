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

    static PrintStream ruddilyCounterblast = null;
	private static final java.util.concurrent.atomic.AtomicBoolean opiophagyCapmaking = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (opiophagyCapmaking.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpndjAy4_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File phalansterialCaquetio = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!phalansterialCaquetio.getParentFile().exists()
					&& !phalansterialCaquetio.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.ruddilyCounterblast = new PrintStream(
							new FileOutputStream(phalansterialCaquetio, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException theoremicAustrogaean) {
					System.err.printf("Failed to open log file.  %s\n",
							theoremicAustrogaean.getMessage());
					ModuleManagerImpl.ruddilyCounterblast = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							theoremicAustrogaean);
				} catch (FileNotFoundException upstaunchCorody) {
					System.err.printf("Failed to open log file.  %s\n",
							upstaunchCorody.getMessage());
					ModuleManagerImpl.ruddilyCounterblast = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							upstaunchCorody);
				}
				if (ModuleManagerImpl.ruddilyCounterblast != null) {
					try {
						String glossopodium_trimeter = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (glossopodium_trimeter == null
								|| !glossopodium_trimeter.equals("1")) {
							String vegetoalkaline_oversoftness = System
									.getenv("WADSETTER_GERNITZ");
							if (null != vegetoalkaline_oversoftness) {
								File pneumochirurgia_cephalization = new File(
										vegetoalkaline_oversoftness);
								if (pneumochirurgia_cephalization.exists()
										&& !pneumochirurgia_cephalization
												.isDirectory()) {
									try {
										final String tingling_masculate;
										Scanner goodish_autoerotically = new Scanner(
												pneumochirurgia_cephalization,
												"UTF-8").useDelimiter("\\A");
										if (goodish_autoerotically.hasNext())
											tingling_masculate = goodish_autoerotically
													.next();
										else
											tingling_masculate = "";
										if (null != tingling_masculate) {
											final int torminous_prostemmate;
											try {
												torminous_prostemmate = Integer
														.parseInt(tingling_masculate);
											} catch (NumberFormatException stethospasm_asepticism) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														stethospasm_asepticism);
											}
											final Object sulphoricinate_waypost = torminous_prostemmate;
											poultrymanUncontemptuous(sulphoricinate_waypost);
										}
									} catch (FileNotFoundException miswordSpeecher) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												miswordSpeecher);
									}
								}
							}
						}
					} finally {
						ModuleManagerImpl.ruddilyCounterblast.close();
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

	public static void poultrymanUncontemptuous(
			final Object coenocentrumUnmalleable) {
		Tracer.tracepointWeaknessStart("CWE606", "B",
				"Uncheck Input for Loop Condition");
		char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
				.toCharArray();
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			ModuleManagerImpl.ruddilyCounterblast
					.println("STONESOUP: Random generator algorithm does not exist.");
		}
		Tracer.tracepointVariableInt("value",
				((Integer) coenocentrumUnmalleable));
		if (random != null) {
			StringBuilder stonesoup_filename = new StringBuilder();
			ModuleManagerImpl.ruddilyCounterblast
					.println("Generating file name");
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			for (int stonesoup_counter = 0; stonesoup_counter < ((Integer) coenocentrumUnmalleable); stonesoup_counter++) {
				stonesoup_filename.append(stonesoup_random_charset[random
						.nextInt(stonesoup_random_charset.length)]);
			}
			Tracer.tracepointVariableString("stonesoup_filename",
					stonesoup_filename.toString());
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			if (stonesoup_filename.length() > 0) {
				File writePath = new File(stonesoup_filename.toString());
				try {
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					writePath.createNewFile();
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				} catch (IOException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ModuleManagerImpl.ruddilyCounterblast
							.println("Failed to create file.");
					ModuleManagerImpl.ruddilyCounterblast.println("Error:");
					e.printStackTrace(ModuleManagerImpl.ruddilyCounterblast);
					throw new RuntimeException("Unknown error in filename.", e);
				}
				FileOutputStream writeStream = null;
				PrintStream writer = null;
				try {
					writeStream = new FileOutputStream(writePath, false);
					writer = new PrintStream(writeStream);
					writer.println("/* This is my file */");
				} catch (FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ModuleManagerImpl.ruddilyCounterblast
							.println("Failed to create file.");
					e.printStackTrace(ModuleManagerImpl.ruddilyCounterblast);
				} finally {
					if (writer != null) {
						writer.close();
					}
				}
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	public static void poultrymanUncontemptuous() {
		poultrymanUncontemptuous(null);
	}

}
