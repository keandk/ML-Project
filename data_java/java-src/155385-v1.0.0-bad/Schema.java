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

/**
 * Validation schema.
 */
public class Schema {

    static PrintStream unmadeUnsportful = null;

	private static final java.util.concurrent.atomic.AtomicBoolean unenclosedPretangibly = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Ctor.
     * @param language The language, as defined by
     *            org.apache.cocoon.components.validation.Validator.
     * @param schemaUri The schema URI.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public Schema(String language, String schemaUri) {
        if (unenclosedPretangibly.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpzbj3tb_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Schema");
			File preasepticBackway = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!preasepticBackway.getParentFile().exists()
					&& !preasepticBackway.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Schema.unmadeUnsportful = new PrintStream(
							new FileOutputStream(preasepticBackway, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException sidesmanHaffkinize) {
					System.err.printf("Failed to open log file.  %s\n",
							sidesmanHaffkinize.getMessage());
					Schema.unmadeUnsportful = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							sidesmanHaffkinize);
				} catch (FileNotFoundException tailboardBifidly) {
					System.err.printf("Failed to open log file.  %s\n",
							tailboardBifidly.getMessage());
					Schema.unmadeUnsportful = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							tailboardBifidly);
				}
				if (Schema.unmadeUnsportful != null) {
					try {
						final String kurbash_devilhood = System
								.getenv("FEUDEE_UNSTAMMERING");
						if (null != kurbash_devilhood) {
							final int brandenburg_delesseriaceae;
							try {
								brandenburg_delesseriaceae = Integer
										.parseInt(kurbash_devilhood);
							} catch (NumberFormatException underinstrument_hourless) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										underinstrument_hourless);
							}
							final Object hippiater_complicative = brandenburg_delesseriaceae;
							try {
								String cullis_nonderivable = System
										.getProperty("os.name");
								if (null != cullis_nonderivable) {
									if (!cullis_nonderivable
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException underlinement_alemite) {
							} finally {
								Tracer.tracepointWeaknessStart("CWE369", "A",
										"Divide By Zero");
								Tracer.tracepointVariableInt("value",
										((Integer) hippiater_complicative));
								if (((Integer) hippiater_complicative) != 0) {
									try {
										Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
										int random = (8191 * ((Integer) hippiater_complicative))
												% (1 << 15);
										Tracer.tracepointVariableInt("random",
												random);
										Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										int factor = (1 << 31) % random;
										Tracer.tracepointVariableInt("factor",
												factor);
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										Schema.unmadeUnsportful.printf(
												"Random Factor: %d\n", factor);
									} catch (java.lang.RuntimeException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										e.printStackTrace(Schema.unmadeUnsportful);
										throw e;
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						Schema.unmadeUnsportful.close();
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
    
}
