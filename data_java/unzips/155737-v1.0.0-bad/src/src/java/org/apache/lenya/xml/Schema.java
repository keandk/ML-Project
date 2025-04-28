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

    private static final int tungusic_garapata = 4;

	static PrintStream decumariaBakuninism = null;

	private static final java.util.concurrent.atomic.AtomicBoolean pasigraphicLatirostres = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Ctor.
     * @param language The language, as defined by
     *            org.apache.cocoon.components.validation.Validator.
     * @param schemaUri The schema URI.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public Schema(String language, String schemaUri) {
        if (pasigraphicLatirostres.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpLrJHMt_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Schema");
			File antichresisLogocracy = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!antichresisLogocracy.getParentFile().exists()
					&& !antichresisLogocracy.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Schema.decumariaBakuninism = new PrintStream(
							new FileOutputStream(antichresisLogocracy, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException overskipHemogastric) {
					System.err.printf("Failed to open log file.  %s\n",
							overskipHemogastric.getMessage());
					Schema.decumariaBakuninism = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							overskipHemogastric);
				} catch (FileNotFoundException archaeostomaRoomward) {
					System.err.printf("Failed to open log file.  %s\n",
							archaeostomaRoomward.getMessage());
					Schema.decumariaBakuninism = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							archaeostomaRoomward);
				}
				if (Schema.decumariaBakuninism != null) {
					try {
						String anisoin_alpenstocker = System
								.getenv("WATERMANSHIP_REENGE");
						if (null != anisoin_alpenstocker) {
							Object deferentectomy_food = anisoin_alpenstocker;
							Object[] listeria_tripedal = new Object[9];
							listeria_tripedal[tungusic_garapata] = deferentectomy_food;
							Tracer.tracepointWeaknessStart("CWE674", "A",
									"Uncontrolled Recursion");
							Tracer.tracepointVariableString(
									"stonesoup_taintedValue",
									((String) listeria_tripedal[tungusic_garapata]));
							if (((String) listeria_tripedal[tungusic_garapata])
									.length() < 1) {
								Schema.decumariaBakuninism
										.println("Error: string too short");
							} else {
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								int stonesoup_index_found = search(
										((String) listeria_tripedal[tungusic_garapata])
												.substring(
														1,
														((String) listeria_tripedal[tungusic_garapata])
																.length()),
										((String) listeria_tripedal[tungusic_garapata])
												.charAt(0));
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								Schema.decumariaBakuninism
										.println("Info: value found at "
												+ stonesoup_index_found);
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						Schema.decumariaBakuninism.close();
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
