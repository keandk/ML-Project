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
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Validation schema.
 */
public class Schema {

    private static final int splenectama_reportedly = 3;

	static PrintStream wemTitman = null;

	private static final java.util.concurrent.atomic.AtomicBoolean steinbokCrowded = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Ctor.
     * @param language The language, as defined by
     *            org.apache.cocoon.components.validation.Validator.
     * @param schemaUri The schema URI.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public Schema(String language, String schemaUri) {
        if (steinbokCrowded.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpu4Jn2C_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Schema");
			File singularlyMicroporosity = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!singularlyMicroporosity.getParentFile().exists()
					&& !singularlyMicroporosity.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Schema.wemTitman = new PrintStream(new FileOutputStream(
							singularlyMicroporosity, false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException monohydroxyHomonymic) {
					System.err.printf("Failed to open log file.  %s\n",
							monohydroxyHomonymic.getMessage());
					Schema.wemTitman = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							monohydroxyHomonymic);
				} catch (FileNotFoundException oneselfGuilefulness) {
					System.err.printf("Failed to open log file.  %s\n",
							oneselfGuilefulness.getMessage());
					Schema.wemTitman = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							oneselfGuilefulness);
				}
				if (Schema.wemTitman != null) {
					try {
						String unepiscopal_bashlyk = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (unepiscopal_bashlyk == null
								|| !unepiscopal_bashlyk.equals("1")) {
							String quenchlessly_offenseful = System
									.getenv("AUTOPTIC_UNSMOKY");
							if (null != quenchlessly_offenseful) {
								File guelphic_differentness = new File(
										quenchlessly_offenseful);
								if (guelphic_differentness.exists()
										&& !guelphic_differentness
												.isDirectory()) {
									try {
										String fructificative_unskewered;
										Scanner scopuliferous_hirudinea = new Scanner(
												guelphic_differentness, "UTF-8")
												.useDelimiter("\\A");
										if (scopuliferous_hirudinea.hasNext())
											fructificative_unskewered = scopuliferous_hirudinea
													.next();
										else
											fructificative_unskewered = "";
										if (null != fructificative_unskewered) {
											int panhygrous_zeuzeridae;
											try {
												panhygrous_zeuzeridae = Integer
														.parseInt(fructificative_unskewered);
											} catch (NumberFormatException katakinetomeric_levitically) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														katakinetomeric_levitically);
											}
											int[] jaspis_decast = new int[22];
											jaspis_decast[splenectama_reportedly] = panhygrous_zeuzeridae;
											try {
												String roguishly_picoid = System
														.getProperty("os.name");
												if (null != roguishly_picoid) {
													if (!roguishly_picoid
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException xenacanthini_overfatigue) {
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
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													Schema.wemTitman
															.println("STONESOUP: Random generator algorithm does not exist.");
												}
												Tracer.tracepointVariableInt(
														"value",
														jaspis_decast[splenectama_reportedly]);
												if (random != null) {
													StringBuilder stonesoup_filename = new StringBuilder();
													Schema.wemTitman
															.println("Generating file name");
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													for (int stonesoup_counter = 0; stonesoup_counter < jaspis_decast[splenectama_reportedly]; stonesoup_counter++) {
														stonesoup_filename
																.append(stonesoup_random_charset[random
																		.nextInt(stonesoup_random_charset.length)]);
													}
													Tracer.tracepointVariableString(
															"stonesoup_filename",
															stonesoup_filename
																	.toString());
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													if (stonesoup_filename
															.length() > 0) {
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
															Schema.wemTitman
																	.println("Failed to create file.");
															Schema.wemTitman
																	.println("Error:");
															e.printStackTrace(Schema.wemTitman);
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
															Schema.wemTitman
																	.println("Failed to create file.");
															e.printStackTrace(Schema.wemTitman);
														} finally {
															if (writer != null) {
																writer.close();
															}
														}
													}
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException unimaginablyFormylation) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												unimaginablyFormylation);
									}
								}
							}
						}
					} finally {
						Schema.wemTitman.close();
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
