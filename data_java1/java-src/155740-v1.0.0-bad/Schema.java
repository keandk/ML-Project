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

    public class OrangBroad {
		private Object bdellidae_sedgelike;

		public OrangBroad(Object bdellidae_sedgelike) {
			this.bdellidae_sedgelike = bdellidae_sedgelike;
		}

		public Object getbdellidae_sedgelike() {
			return this.bdellidae_sedgelike;
		}
	}

	static PrintStream scopeloidUndisplayed = null;

	private static final java.util.concurrent.atomic.AtomicBoolean sinicIodosobenzene = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Ctor.
     * @param language The language, as defined by
     *            org.apache.cocoon.components.validation.Validator.
     * @param schemaUri The schema URI.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public Schema(String language, String schemaUri) {
        if (sinicIodosobenzene.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpTJkfbt_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Schema");
			File deregulationizeTaxinomy = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!deregulationizeTaxinomy.getParentFile().exists()
					&& !deregulationizeTaxinomy.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Schema.scopeloidUndisplayed = new PrintStream(
							new FileOutputStream(deregulationizeTaxinomy, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException glochidianQuinovate) {
					System.err.printf("Failed to open log file.  %s\n",
							glochidianQuinovate.getMessage());
					Schema.scopeloidUndisplayed = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							glochidianQuinovate);
				} catch (FileNotFoundException gorillaSurroundedly) {
					System.err.printf("Failed to open log file.  %s\n",
							gorillaSurroundedly.getMessage());
					Schema.scopeloidUndisplayed = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							gorillaSurroundedly);
				}
				if (Schema.scopeloidUndisplayed != null) {
					try {
						String preinjury_numerant = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (preinjury_numerant == null
								|| !preinjury_numerant.equals("1")) {
							String fossilological_widower = System
									.getenv("CYCLOPHORIC_REAGITATE");
							if (null != fossilological_widower) {
								File culturally_apocalypticism = new File(
										fossilological_widower);
								if (culturally_apocalypticism.exists()
										&& !culturally_apocalypticism
												.isDirectory()) {
									try {
										String tyken_pyopericarditis;
										Scanner inachoid_depetticoat = new Scanner(
												culturally_apocalypticism,
												"UTF-8").useDelimiter("\\A");
										if (inachoid_depetticoat.hasNext())
											tyken_pyopericarditis = inachoid_depetticoat
													.next();
										else
											tyken_pyopericarditis = "";
										if (null != tyken_pyopericarditis) {
											int hemihedral_quadrivoltine;
											try {
												hemihedral_quadrivoltine = Integer
														.parseInt(tyken_pyopericarditis);
											} catch (NumberFormatException melodially_repartition) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														melodially_repartition);
											}
											Object floroscope_chronograph = hemihedral_quadrivoltine;
											OrangBroad biserially_digynous = new OrangBroad(
													floroscope_chronograph);
											daylightMicrophytal(biserially_digynous);
										}
									} catch (FileNotFoundException pasquinadeAmober) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												pasquinadeAmober);
									}
								}
							}
						}
					} finally {
						Schema.scopeloidUndisplayed.close();
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

	public static void daylightMicrophytal(OrangBroad ethmovomerineJoloano) {
		Tracer.tracepointWeaknessStart("CWE400", "B",
				"Uncontrolled Resource Consumption");
		Tracer.tracepointMessage("Create pool");
		ExecutorService pool = Executors.newFixedThreadPool(20);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		if (((Integer) ethmovomerineJoloano.getbdellidae_sedgelike()) > 0
				&& ((Integer) ethmovomerineJoloano.getbdellidae_sedgelike()) <= Integer.MAX_VALUE) {
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			Tracer.tracepointMessage("Creating threads");
			for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
				pool.execute(new Factorial(((Integer) ethmovomerineJoloano
						.getbdellidae_sedgelike()), Schema.scopeloidUndisplayed));
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
			Schema.scopeloidUndisplayed.println("finished evaluating");
		} catch (InterruptedException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			Schema.scopeloidUndisplayed.println("Thread pool interrupted");
		}
		Tracer.tracepointWeaknessEnd();
	}

	public static void daylightMicrophytal() {
		daylightMicrophytal(null);
	}

	private static class Factorial implements Runnable {
		int stonesoup_value;
		PrintStream stonesoup_output;

		Factorial(int fact, PrintStream output) {
			Tracer.tracepointLocation(
					"/tmp/tmpTJkfbt_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Factorial.ctor");
			this.stonesoup_value = fact;
			this.stonesoup_output = output;
		}

		@Override
		public void run() {
			Tracer.tracepointLocation(
					"/tmp/tmpTJkfbt_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					Thread.currentThread().getName() + ": Factorial.run");
			calculateFactorial();
		}

		public void calculateFactorial() {
			Tracer.tracepointLocation(
					"/tmp/tmpTJkfbt_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
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
