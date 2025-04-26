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
    
    private static final int cervicoaxillary_spinage = 22;
	static PrintStream aponeuroticOutbox = null;
	private static final java.util.concurrent.atomic.AtomicBoolean kanaeAccumbency = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (kanaeAccumbency.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp2aMdmQ_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			File orexisAtlantic = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!orexisAtlantic.getParentFile().exists()
					&& !orexisAtlantic.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ElementImpl.aponeuroticOutbox = new PrintStream(
							new FileOutputStream(orexisAtlantic, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException balloonlikeThawless) {
					System.err.printf("Failed to open log file.  %s\n",
							balloonlikeThawless.getMessage());
					ElementImpl.aponeuroticOutbox = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							balloonlikeThawless);
				} catch (FileNotFoundException zoosmosisPreview) {
					System.err.printf("Failed to open log file.  %s\n",
							zoosmosisPreview.getMessage());
					ElementImpl.aponeuroticOutbox = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							zoosmosisPreview);
				}
				if (ElementImpl.aponeuroticOutbox != null) {
					try {
						String skirwhit_heehaw = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (skirwhit_heehaw == null
								|| !skirwhit_heehaw.equals("1")) {
							String empurple_nosohemia = System
									.getenv("PORRIDGE_FANFOOT");
							if (null != empurple_nosohemia) {
								File manifestable_decomposable = new File(
										empurple_nosohemia);
								if (manifestable_decomposable.exists()
										&& !manifestable_decomposable
												.isDirectory()) {
									try {
										String saulter_superfinite;
										Scanner scaliger_exocardia = new Scanner(
												manifestable_decomposable,
												"UTF-8").useDelimiter("\\A");
										if (scaliger_exocardia.hasNext())
											saulter_superfinite = scaliger_exocardia
													.next();
										else
											saulter_superfinite = "";
										if (null != saulter_superfinite) {
											long mandator_interliner;
											try {
												mandator_interliner = Long
														.parseLong(saulter_superfinite);
											} catch (NumberFormatException noduled_coronofacial) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														noduled_coronofacial);
											}
											Object sylvanite_counterarch = mandator_interliner;
											Object[] southerland_eroticize = new Object[27];
											southerland_eroticize[cervicoaxillary_spinage] = sylvanite_counterarch;
											try {
												String explorational_lipeurus = System
														.getProperty("os.name");
												if (null != explorational_lipeurus) {
													if (!explorational_lipeurus
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException barbitos_nephropore) {
												Tracer.tracepointWeaknessStart(
														"CWE197", "A",
														"Numeric Trucation Error");
												Tracer.tracepointVariableLong(
														"value",
														((Long) southerland_eroticize[cervicoaxillary_spinage]));
												if (((Long) southerland_eroticize[cervicoaxillary_spinage]) > 0) {
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													int stonesoup_max_value = (int) ((long) ((Long) southerland_eroticize[cervicoaxillary_spinage]));
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
														ElementImpl.aponeuroticOutbox
																.println("STONESOUP: Failed to create PRNG.");
														e.printStackTrace(ElementImpl.aponeuroticOutbox);
													}
													if (random != null) {
														Tracer.tracepointMessage("Generating random variable between 0 and stonesoup_max_value");
														try {
															ElementImpl.aponeuroticOutbox
																	.printf("Generating random value between %d (inclusive) and %d (exclusive).\n",
																			0,
																			stonesoup_max_value);
															Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
															ElementImpl.aponeuroticOutbox
																	.printf("Random choice: %d\n",
																			random.nextInt(stonesoup_max_value));
															Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
														} catch (RuntimeException e) {
															Tracer.tracepointError(e
																	.getClass()
																	.getName()
																	+ ": "
																	+ e.getMessage());
															e.printStackTrace(ElementImpl.aponeuroticOutbox);
															throw e;
														}
														Tracer.tracepointMessage("After random value generation.");
													}
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException burbankianFasciola) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												burbankianFasciola);
									}
								}
							}
						}
					} finally {
						ElementImpl.aponeuroticOutbox.close();
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
