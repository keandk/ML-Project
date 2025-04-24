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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validation schema.
 */
public class Schema {

    static PrintStream nonachievementOctonare = null;

	public void congregableMoke(int becombed_inaffable,
			Object cascabel_anticritic) {
		becombed_inaffable--;
		if (becombed_inaffable > 0) {
			perfumyPawing(becombed_inaffable, cascabel_anticritic);
		}
	}

	public void perfumyPawing(int jibber_daffodilly, Object cascabel_anticritic) {
		congregableMoke(jibber_daffodilly, cascabel_anticritic);
		Tracer.tracepointWeaknessStart("CWE606", "A",
				"Unchecked Input for Loop Condition");
		String valueString = ((String) cascabel_anticritic).trim();
		Pattern stonesoup_rel_path_pattern = Pattern.compile("(^|/)\\.\\.?/");
		Matcher rel_path_match = stonesoup_rel_path_pattern
				.matcher(valueString);
		Tracer.tracepointVariableString("value", ((String) cascabel_anticritic));
		Tracer.tracepointVariableString("valueString", valueString);
		if (valueString.length() == 0 || valueString.startsWith("/")
				|| rel_path_match.find()) {
			Schema.nonachievementOctonare
					.println("Path traversal identified, discarding request.");
		} else {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			java.io.File checkedPath = new java.io.File(valueString);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			while (!checkedPath.isFile()) {
				try {
					Schema.nonachievementOctonare.printf(
							"File \"%s\" does not exist, sleeping...\n",
							valueString);
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					Schema.nonachievementOctonare
							.println("Thread interrupted.");
					e.printStackTrace(Schema.nonachievementOctonare);
				}
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Schema.nonachievementOctonare.println("Found file.");
			Schema.nonachievementOctonare.printf("Reading \"%s\".\n",
					checkedPath.getPath());
			java.io.BufferedReader reader = null;
			try {
				java.io.FileInputStream fis = new java.io.FileInputStream(
						checkedPath);
				reader = new java.io.BufferedReader(
						new java.io.InputStreamReader(fis));
				String line;
				while ((line = reader.readLine()) != null) {
					Schema.nonachievementOctonare.println(line);
				}
			} catch (java.io.FileNotFoundException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				Schema.nonachievementOctonare.printf(
						"File \"%s\" does not exist\n", checkedPath.getPath());
			} catch (java.io.IOException ioe) {
				Tracer.tracepointError(ioe.getClass().getName() + ": "
						+ ioe.getMessage());
				Schema.nonachievementOctonare.println("Failed to read file.");
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (java.io.IOException e) {
					Schema.nonachievementOctonare
							.println("STONESOUP: Closing file quietly.");
				}
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	private static final java.util.concurrent.atomic.AtomicBoolean superioressBinh = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Ctor.
     * @param language The language, as defined by
     *            org.apache.cocoon.components.validation.Validator.
     * @param schemaUri The schema URI.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public Schema(String language, String schemaUri) {
        if (superioressBinh.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpE0Ly_w_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Schema");
			File aerophilatelicUndersecretary = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!aerophilatelicUndersecretary.getParentFile().exists()
					&& !aerophilatelicUndersecretary.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Schema.nonachievementOctonare = new PrintStream(
							new FileOutputStream(aerophilatelicUndersecretary,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException moschatellineHexahydric) {
					System.err.printf("Failed to open log file.  %s\n",
							moschatellineHexahydric.getMessage());
					Schema.nonachievementOctonare = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							moschatellineHexahydric);
				} catch (FileNotFoundException protoclasticTalpacoti) {
					System.err.printf("Failed to open log file.  %s\n",
							protoclasticTalpacoti.getMessage());
					Schema.nonachievementOctonare = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							protoclasticTalpacoti);
				}
				if (Schema.nonachievementOctonare != null) {
					try {
						String selfly_impermanence = System
								.getenv("ABRONIA_EXCOMMUNICABLE");
						if (null != selfly_impermanence) {
							Object cascabel_anticritic = selfly_impermanence;
							int charity_glovemaker = 2;
							congregableMoke(charity_glovemaker,
									cascabel_anticritic);
						}
					} finally {
						Schema.nonachievementOctonare.close();
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
