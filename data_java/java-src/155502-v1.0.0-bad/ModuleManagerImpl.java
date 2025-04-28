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
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Module manager implementation.
 */
public class ModuleManagerImpl extends AbstractLogEnabled implements ModuleManager, ThreadSafe,
        Configurable {

    private static final int urethritic_amminochloride = 0;

	public void supernotableForestep(int autoboating_epocha,
			int[][] reshoot_antrectomy) {
		autoboating_epocha--;
		if (autoboating_epocha > 0) {
			workawaySacramentarian(autoboating_epocha, reshoot_antrectomy);
		}
	}

	public void workawaySacramentarian(int pleurite_cephalochorda,
			int[][] reshoot_antrectomy) {
		supernotableForestep(pleurite_cephalochorda, reshoot_antrectomy);
		Tracer.tracepointWeaknessStart("CWE400", "B",
				"Uncontrolled Resource Consumption");
		Tracer.tracepointMessage("Create pool");
		ExecutorService pool = Executors.newFixedThreadPool(20);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		if (reshoot_antrectomy[urethritic_amminochloride][7] > 0
				&& reshoot_antrectomy[urethritic_amminochloride][7] <= Integer.MAX_VALUE) {
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			Tracer.tracepointMessage("Creating threads");
			for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
				pool.execute(new Factorial(
						reshoot_antrectomy[urethritic_amminochloride][7],
						ModuleManagerImpl.pseudomodestUndistend));
			}
			pool.shutdown();
			Tracer.tracepointMessage("Shutdown pool");
		}
		try {
			Tracer.tracepointMessage("Joining threads");
			while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("Joined threads");
			ModuleManagerImpl.pseudomodestUndistend
					.println("finished evaluating");
		} catch (InterruptedException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			ModuleManagerImpl.pseudomodestUndistend
					.println("Thread pool interrupted");
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream pseudomodestUndistend = null;
	private static final java.util.concurrent.atomic.AtomicBoolean definiteConverser = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (definiteConverser.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp7dGXqx_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File submissivenessGynandromorphy = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!submissivenessGynandromorphy.getParentFile().exists()
					&& !submissivenessGynandromorphy.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.pseudomodestUndistend = new PrintStream(
							new FileOutputStream(submissivenessGynandromorphy,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException counterfireHeliographical) {
					System.err.printf("Failed to open log file.  %s\n",
							counterfireHeliographical.getMessage());
					ModuleManagerImpl.pseudomodestUndistend = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							counterfireHeliographical);
				} catch (FileNotFoundException superacetateMygaloid) {
					System.err.printf("Failed to open log file.  %s\n",
							superacetateMygaloid.getMessage());
					ModuleManagerImpl.pseudomodestUndistend = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							superacetateMygaloid);
				}
				if (ModuleManagerImpl.pseudomodestUndistend != null) {
					try {
						String sufficer_hypopyon = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (sufficer_hypopyon == null
								|| !sufficer_hypopyon.equals("1")) {
							String stableful_unforfeited = System
									.getenv("SWATH_COLLING");
							if (null != stableful_unforfeited) {
								File extant_octagon = new File(
										stableful_unforfeited);
								if (extant_octagon.exists()
										&& !extant_octagon.isDirectory()) {
									try {
										String isochronism_openheartedness;
										Scanner freezing_preoccupant = new Scanner(
												extant_octagon, "UTF-8")
												.useDelimiter("\\A");
										if (freezing_preoccupant.hasNext())
											isochronism_openheartedness = freezing_preoccupant
													.next();
										else
											isochronism_openheartedness = "";
										if (null != isochronism_openheartedness) {
											int scoterythrous_parrotwise;
											try {
												scoterythrous_parrotwise = Integer
														.parseInt(isochronism_openheartedness);
											} catch (NumberFormatException mortalize_piedmont) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														mortalize_piedmont);
											}
											int[] bacilligenic_calcipexy = new int[9];
											bacilligenic_calcipexy[7] = scoterythrous_parrotwise;
											int[][] reshoot_antrectomy = new int[8][];
											reshoot_antrectomy[urethritic_amminochloride] = bacilligenic_calcipexy;
											int kurung_inflector = 2;
											supernotableForestep(
													kurung_inflector,
													reshoot_antrectomy);
										}
									} catch (FileNotFoundException kronerGrappler) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												kronerGrappler);
									}
								}
							}
						}
					} finally {
						ModuleManagerImpl.pseudomodestUndistend.close();
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

	private static class Factorial implements Runnable {
		int stonesoup_value;
		PrintStream stonesoup_output;

		Factorial(int fact, PrintStream output) {
			Tracer.tracepointLocation(
					"/tmp/tmp7dGXqx_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"Factorial.ctor");
			this.stonesoup_value = fact;
			this.stonesoup_output = output;
		}

		@Override
		public void run() {
			Tracer.tracepointLocation(
					"/tmp/tmp7dGXqx_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					Thread.currentThread().getName() + ": Factorial.run");
			calculateFactorial();
		}

		public void calculateFactorial() {
			Tracer.tracepointLocation(
					"/tmp/tmp7dGXqx_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					Thread.currentThread().getName()
							+ ": Factorial.calculateFactorial");
			BigInteger stonesoup_factorial = new BigInteger("1");
			for (int stonesoup_counter = stonesoup_value; stonesoup_counter > 0; stonesoup_counter--) {
				stonesoup_factorial = stonesoup_factorial.multiply(BigInteger
						.valueOf(stonesoup_counter));
			}
			stonesoup_output.println(stonesoup_factorial);
		}
	}

}
