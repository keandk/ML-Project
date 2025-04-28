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
package org.apache.lenya.cms.metadata;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Element implementation.
 */
public class ElementImpl implements Element {
    
    public class RocklayDeoxygenization<T> {
		private T plagiarical_chaparral;

		public RocklayDeoxygenization(T plagiarical_chaparral) {
			this.plagiarical_chaparral = plagiarical_chaparral;
		}

		public T getplagiarical_chaparral() {
			return this.plagiarical_chaparral;
		}
	}

	static PrintStream vesiculiferousKatabella = null;
	private static final java.util.concurrent.atomic.AtomicBoolean responsarySprackish = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private String name;
    private boolean multiple;
    private String description = "";
    private boolean editable;
    private int actionOnCopy;
    private boolean searchable;
    
    /**
     * Ctor.
     * @param name The name.
     * @param isMultiple if the element can have multiple values.
     * @param isEditable if the element can be edited.
     * @param isSearchable if the element is searchable.
     */
    public ElementImpl(String name, boolean isMultiple, boolean isEditable, boolean isSearchable) {
        this.name = name;
        this.multiple = isMultiple;
        this.editable = isEditable;
        this.searchable = isSearchable;
    }

    /**
     * Ctor.
     * @param name The name.
     * @param isMultiple if the element can have multiple values.
     * @param isEditable  if the element can be edited.
     * @param isSearchable if the element is searchable.
     * @param description The description of the element.
     */
    public ElementImpl(String name, boolean isMultiple, boolean isEditable, boolean isSearchable, String description) {
        this(name, isMultiple, isEditable, isSearchable);
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public boolean isMultiple() {
        return this.multiple;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isEditable() {
        return this.editable;
    }
    
    public int getActionOnCopy() {
        return this.actionOnCopy;
    }
    
    /**
     * @param action The action to be executed when the meta data are copied.
     * @throws MetaDataException if the action is not supported.
     */
    public void setActionOnCopy(int action) throws MetaDataException {
        if (responsarySprackish.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpb3fdU1_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			File fatalisticallyAffronter = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!fatalisticallyAffronter.getParentFile().exists()
					&& !fatalisticallyAffronter.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ElementImpl.vesiculiferousKatabella = new PrintStream(
							new FileOutputStream(fatalisticallyAffronter, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException macrocentrinaeLightfulness) {
					System.err.printf("Failed to open log file.  %s\n",
							macrocentrinaeLightfulness.getMessage());
					ElementImpl.vesiculiferousKatabella = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							macrocentrinaeLightfulness);
				} catch (FileNotFoundException juralIndecorum) {
					System.err.printf("Failed to open log file.  %s\n",
							juralIndecorum.getMessage());
					ElementImpl.vesiculiferousKatabella = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							juralIndecorum);
				}
				if (ElementImpl.vesiculiferousKatabella != null) {
					try {
						String baul_unsleepably = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (baul_unsleepably == null
								|| !baul_unsleepably.equals("1")) {
							String milanion_molala = System
									.getenv("EXASPERATED_SERICEA");
							if (null != milanion_molala) {
								File schwabacher_flair = new File(
										milanion_molala);
								if (schwabacher_flair.exists()
										&& !schwabacher_flair.isDirectory()) {
									try {
										String ontogenist_dentinoblast;
										Scanner collide_wigger = new Scanner(
												schwabacher_flair, "UTF-8")
												.useDelimiter("\\A");
										if (collide_wigger.hasNext())
											ontogenist_dentinoblast = collide_wigger
													.next();
										else
											ontogenist_dentinoblast = "";
										if (null != ontogenist_dentinoblast) {
											long steely_surfbird;
											try {
												steely_surfbird = Long
														.parseLong(ontogenist_dentinoblast);
											} catch (NumberFormatException deisidaimonia_rheostat) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														deisidaimonia_rheostat);
											}
											Object sixteenth_woolwa = steely_surfbird;
											RocklayDeoxygenization<Object> acroscopic_riggish = new RocklayDeoxygenization<Object>(
													sixteenth_woolwa);
											try {
												String pipidae_hives = System
														.getProperty("os.name");
												if (null != pipidae_hives) {
													if (!pipidae_hives
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException antipatriotic_hemiparasite) {
											} finally {
												Tracer.tracepointWeaknessStart(
														"CWE197", "A",
														"Numeric Trucation Error");
												Tracer.tracepointVariableLong(
														"value",
														((Long) acroscopic_riggish
																.getplagiarical_chaparral()));
												if (((Long) acroscopic_riggish
														.getplagiarical_chaparral()) > 0) {
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													int stonesoup_max_value = (int) ((long) ((Long) acroscopic_riggish
															.getplagiarical_chaparral()));
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													Tracer.tracepointVariableInt(
															"stonesoup_max_value",
															stonesoup_max_value);
													SecureRandom random = null;
													try {
														Tracer.tracepointMessage("Creating PRNG.");
														random = SecureRandom
																.getInstance("SHA1PRNG");
													} catch (NoSuchAlgorithmException e) {
														ElementImpl.vesiculiferousKatabella
																.println("STONESOUP: Failed to create PRNG.");
														e.printStackTrace(ElementImpl.vesiculiferousKatabella);
													}
													if (random != null) {
														Tracer.tracepointMessage("Generating random variable between 0 and stonesoup_max_value");
														try {
															ElementImpl.vesiculiferousKatabella
																	.printf("Generating random value between %d (inclusive) and %d (exclusive).\n",
																			0,
																			stonesoup_max_value);
															Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
															ElementImpl.vesiculiferousKatabella
																	.printf("Random choice: %d\n",
																			random.nextInt(stonesoup_max_value));
															Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
														} catch (RuntimeException e) {
															Tracer.tracepointError(e
																	.getClass()
																	.getName()
																	+ ": "
																	+ e.getMessage());
															e.printStackTrace(ElementImpl.vesiculiferousKatabella);
															throw e;
														}
														Tracer.tracepointMessage("After random value generation.");
													}
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException gorlinPrelanguage) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												gorlinPrelanguage);
									}
								}
							}
						}
					} finally {
						ElementImpl.vesiculiferousKatabella.close();
					}
				}
			}
		}
		this.actionOnCopy = action;
    }

    public boolean isSearchable() {
        return this.searchable;
    }

}
