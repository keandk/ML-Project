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
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Validation schema.
 */
public class Schema {

    static PrintStream vermiciousEcstasy = null;

	private static final java.util.concurrent.atomic.AtomicBoolean fluttererSnubbed = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Ctor.
     * @param language The language, as defined by
     *            org.apache.cocoon.components.validation.Validator.
     * @param schemaUri The schema URI.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public Schema(String language, String schemaUri) {
        if (fluttererSnubbed.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpJCuNEs_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Schema");
			File membraniformUnregained = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!membraniformUnregained.getParentFile().exists()
					&& !membraniformUnregained.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Schema.vermiciousEcstasy = new PrintStream(
							new FileOutputStream(membraniformUnregained, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException lunatellusPaleontological) {
					System.err.printf("Failed to open log file.  %s\n",
							lunatellusPaleontological.getMessage());
					Schema.vermiciousEcstasy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							lunatellusPaleontological);
				} catch (FileNotFoundException anisomyodianAnadipsic) {
					System.err.printf("Failed to open log file.  %s\n",
							anisomyodianAnadipsic.getMessage());
					Schema.vermiciousEcstasy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							anisomyodianAnadipsic);
				}
				if (Schema.vermiciousEcstasy != null) {
					try {
						String flindosa_parlatory = System
								.getenv("RUFFLER_STROPHIC");
						if (null != flindosa_parlatory) {
							int labioalveolar_sparerib;
							try {
								labioalveolar_sparerib = Integer
										.parseInt(flindosa_parlatory);
							} catch (NumberFormatException dugdug_pighead) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										dugdug_pighead);
							}
							sivaiteLeiotrichinae(3, (int) 0, (int) 0, (int) 0,
									labioalveolar_sparerib, (int) 0, (int) 0);
						}
					} finally {
						Schema.vermiciousEcstasy.close();
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

	public void sivaiteLeiotrichinae(int micromicrofaradBacteriostat,
			int... cardiocirrhosisHoovey) {
		int mohawkIncrystal = (int) 0;
		int proscriptionThreateningly = 0;
		for (proscriptionThreateningly = 0; proscriptionThreateningly < cardiocirrhosisHoovey.length; proscriptionThreateningly++) {
			if (proscriptionThreateningly == micromicrofaradBacteriostat)
				mohawkIncrystal = cardiocirrhosisHoovey[proscriptionThreateningly];
		}
		int animation_nonaffiliated = 0;
		while (true) {
			animation_nonaffiliated++;
			if (animation_nonaffiliated >= 3000)
				break;
		}
		Tracer.tracepointWeaknessStart("CWE400", "B",
				"Uncontrolled Resource Consumption");
		Tracer.tracepointMessage("Create pool");
		ExecutorService pool = Executors.newFixedThreadPool(20);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		if (mohawkIncrystal > 0 && mohawkIncrystal <= Integer.MAX_VALUE) {
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			Tracer.tracepointMessage("Creating threads");
			for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
				pool.execute(new Factorial(mohawkIncrystal,
						Schema.vermiciousEcstasy));
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
			Schema.vermiciousEcstasy.println("finished evaluating");
		} catch (InterruptedException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			Schema.vermiciousEcstasy.println("Thread pool interrupted");
		}
		Tracer.tracepointWeaknessEnd();
	}

	private static class Factorial implements Runnable {
		int stonesoup_value;
		PrintStream stonesoup_output;

		Factorial(int fact, PrintStream output) {
			Tracer.tracepointLocation(
					"/tmp/tmpJCuNEs_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Factorial.ctor");
			this.stonesoup_value = fact;
			this.stonesoup_output = output;
		}

		@Override
		public void run() {
			Tracer.tracepointLocation(
					"/tmp/tmpJCuNEs_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					Thread.currentThread().getName() + ": Factorial.run");
			calculateFactorial();
		}

		public void calculateFactorial() {
			Tracer.tracepointLocation(
					"/tmp/tmpJCuNEs_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
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
