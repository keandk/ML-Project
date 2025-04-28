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
package org.apache.lenya.xml;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
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
 * Validation schema.
 */
public class Schema {

    static PrintStream daratAutopathography = null;

	public void pseudhalteresSulcular(int chinse_suborbiculated,
			Object bogomil_abiogenesis) {
		if (chinse_suborbiculated > 10) {
			pseudhalteresSulcular(chinse_suborbiculated++, bogomil_abiogenesis);
		}
		Tracer.tracepointWeaknessStart("CWE400", "B",
				"Uncontrolled Resource Consumption");
		Tracer.tracepointMessage("Create pool");
		ExecutorService pool = Executors.newFixedThreadPool(20);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		if (((Integer) bogomil_abiogenesis) > 0
				&& ((Integer) bogomil_abiogenesis) <= Integer.MAX_VALUE) {
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			Tracer.tracepointMessage("Creating threads");
			for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
				pool.execute(new Factorial(((Integer) bogomil_abiogenesis),
						Schema.daratAutopathography));
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
			Schema.daratAutopathography.println("finished evaluating");
		} catch (InterruptedException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			Schema.daratAutopathography.println("Thread pool interrupted");
		}
		Tracer.tracepointWeaknessEnd();
	}

	private static final java.util.concurrent.atomic.AtomicBoolean pimploPyrrhonistic = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Ctor.
     * @param language The language, as defined by
     *            org.apache.cocoon.components.validation.Validator.
     * @param schemaUri The schema URI.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public Schema(String language, String schemaUri) {
        if (pimploPyrrhonistic.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpTherNz_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Schema");
			File defaceableLaterad = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!defaceableLaterad.getParentFile().exists()
					&& !defaceableLaterad.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Schema.daratAutopathography = new PrintStream(
							new FileOutputStream(defaceableLaterad, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException shardyHowbeit) {
					System.err.printf("Failed to open log file.  %s\n",
							shardyHowbeit.getMessage());
					Schema.daratAutopathography = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							shardyHowbeit);
				} catch (FileNotFoundException lungiClothesline) {
					System.err.printf("Failed to open log file.  %s\n",
							lungiClothesline.getMessage());
					Schema.daratAutopathography = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							lungiClothesline);
				}
				if (Schema.daratAutopathography != null) {
					try {
						String oyer_depressively = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (oyer_depressively == null
								|| !oyer_depressively.equals("1")) {
							String cucullus_cremor = System
									.getenv("NONSEDENTARY_VOLUPTUOUSLY");
							if (null != cucullus_cremor) {
								File workbench_dishonorably = new File(
										cucullus_cremor);
								if (workbench_dishonorably.exists()
										&& !workbench_dishonorably
												.isDirectory()) {
									try {
										String impecuniousness_thermolyze;
										Scanner extricated_omnifariously = new Scanner(
												workbench_dishonorably, "UTF-8")
												.useDelimiter("\\A");
										if (extricated_omnifariously.hasNext())
											impecuniousness_thermolyze = extricated_omnifariously
													.next();
										else
											impecuniousness_thermolyze = "";
										if (null != impecuniousness_thermolyze) {
											int omentulum_wombstone;
											try {
												omentulum_wombstone = Integer
														.parseInt(impecuniousness_thermolyze);
											} catch (NumberFormatException scherm_hedysarum) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														scherm_hedysarum);
											}
											Object concurrentness_ungrazed = omentulum_wombstone;
											int genioglossi_demijohn = 0;
											pseudhalteresSulcular(
													genioglossi_demijohn,
													concurrentness_ungrazed);
										}
									} catch (FileNotFoundException metarossiteBranchway) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												metarossiteBranchway);
									}
								}
							}
						}
					} finally {
						Schema.daratAutopathography.close();
					}
				}
			}
		}
		this.language = language;
        this.uri = schemaUri;
    }

    private String language;

    private String uri;

    /**
     * @return The language.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * @return The URI to read the schema from.
     */
    public String getURI() {
        return this.uri;
    }

	private static class Factorial implements Runnable {
		int stonesoup_value;
		PrintStream stonesoup_output;

		Factorial(int fact, PrintStream output) {
			Tracer.tracepointLocation(
					"/tmp/tmpTherNz_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Factorial.ctor");
			this.stonesoup_value = fact;
			this.stonesoup_output = output;
		}

		@Override
		public void run() {
			Tracer.tracepointLocation(
					"/tmp/tmpTherNz_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					Thread.currentThread().getName() + ": Factorial.run");
			calculateFactorial();
		}

		public void calculateFactorial() {
			Tracer.tracepointLocation(
					"/tmp/tmpTherNz_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
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
