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
import java.math.BigInteger;

/**
 * Element implementation.
 */
public class ElementImpl implements Element {
    
    private static final int quintessential_inteind = 11;
	static PrintStream spurproofOpisthogyrous = null;
	private static final java.util.concurrent.atomic.AtomicBoolean collideHeptahedron = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (collideHeptahedron.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpm17fZd_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			File mundaneStriatal = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!mundaneStriatal.getParentFile().exists()
					&& !mundaneStriatal.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ElementImpl.spurproofOpisthogyrous = new PrintStream(
							new FileOutputStream(mundaneStriatal, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException camelopardusHindrance) {
					System.err.printf("Failed to open log file.  %s\n",
							camelopardusHindrance.getMessage());
					ElementImpl.spurproofOpisthogyrous = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							camelopardusHindrance);
				} catch (FileNotFoundException intakerLozengy) {
					System.err.printf("Failed to open log file.  %s\n",
							intakerLozengy.getMessage());
					ElementImpl.spurproofOpisthogyrous = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							intakerLozengy);
				}
				if (ElementImpl.spurproofOpisthogyrous != null) {
					try {
						String pulvino_viceroy = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (pulvino_viceroy == null
								|| !pulvino_viceroy.equals("1")) {
							String undainty_ootheca = System
									.getenv("LANGUED_REVIVISCENCE");
							if (null != undainty_ootheca) {
								File rumfustian_picul = new File(
										undainty_ootheca);
								if (rumfustian_picul.exists()
										&& !rumfustian_picul.isDirectory()) {
									try {
										String siderotechny_issedoi;
										Scanner insanitariness_agaces = new Scanner(
												rumfustian_picul, "UTF-8")
												.useDelimiter("\\A");
										if (insanitariness_agaces.hasNext())
											siderotechny_issedoi = insanitariness_agaces
													.next();
										else
											siderotechny_issedoi = "";
										if (null != siderotechny_issedoi) {
											String[] nonsegmented_ashir = new String[10];
											nonsegmented_ashir[8] = siderotechny_issedoi;
											String[][] luxemburgian_disvisage = new String[12][];
											luxemburgian_disvisage[quintessential_inteind] = nonsegmented_ashir;
											boolean dorsoventrad_peplos = false;
											ankyroid_inframundane: for (int terrene_pericoxitis = 0; terrene_pericoxitis < 10; terrene_pericoxitis++)
												for (int afterimpression_tapermaker = 0; afterimpression_tapermaker < 10; afterimpression_tapermaker++)
													if (terrene_pericoxitis
															* afterimpression_tapermaker == 63) {
														dorsoventrad_peplos = true;
														break ankyroid_inframundane;
													}
											Tracer.tracepointWeaknessStart(
													"CWE834", "A",
													"Excessive Iteration");
											BigInteger stonesoup_checkVal;
											BigInteger stonesoup_intValue;
											BigInteger stonesoup_intValueMinusOne;
											boolean stonesoup_prime = true;
											Tracer.tracepointVariableString(
													"stonesoup_taintedValue",
													luxemburgian_disvisage[quintessential_inteind][8]);
											try {
												stonesoup_checkVal = new BigInteger(
														"2");
												stonesoup_intValue = new BigInteger(
														luxemburgian_disvisage[quintessential_inteind][8]);
												stonesoup_intValueMinusOne = stonesoup_intValue
														.subtract(BigInteger.ONE);
												if (stonesoup_intValue
														.compareTo(BigInteger.ZERO) > 0) {
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													for (; stonesoup_checkVal
															.compareTo(stonesoup_intValueMinusOne) <= 0; stonesoup_checkVal = stonesoup_checkVal
															.add(BigInteger.ONE)) {
														if (stonesoup_intValue
																.mod(stonesoup_checkVal)
																.compareTo(
																		BigInteger.ZERO) == 0) {
															stonesoup_prime = false;
															ElementImpl.spurproofOpisthogyrous
																	.println("Not Prime");
															break;
														}
													}
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												}
											} catch (NumberFormatException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												ElementImpl.spurproofOpisthogyrous
														.println("STONESOUP: Input string is not an integer");
											}
											ElementImpl.spurproofOpisthogyrous
													.println("finished evaluating");
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException epilatoryVetoistic) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												epilatoryVetoistic);
									}
								}
							}
						}
					} finally {
						ElementImpl.spurproofOpisthogyrous.close();
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
