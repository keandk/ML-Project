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
import java.io.IOException;

/**
 * Element implementation.
 */
public class ElementImpl implements Element {
    
    private static final int noncomplaisance_knicker = 12;
	static PrintStream frontstallSuncup = null;
	private static final java.util.concurrent.atomic.AtomicBoolean hayforkAnisomerous = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (hayforkAnisomerous.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpArT7CO_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			File pyxisWhortle = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!pyxisWhortle.getParentFile().exists()
					&& !pyxisWhortle.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ElementImpl.frontstallSuncup = new PrintStream(
							new FileOutputStream(pyxisWhortle, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException rabiSubeffective) {
					System.err.printf("Failed to open log file.  %s\n",
							rabiSubeffective.getMessage());
					ElementImpl.frontstallSuncup = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							rabiSubeffective);
				} catch (FileNotFoundException voyagerOverdepress) {
					System.err.printf("Failed to open log file.  %s\n",
							voyagerOverdepress.getMessage());
					ElementImpl.frontstallSuncup = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							voyagerOverdepress);
				}
				if (ElementImpl.frontstallSuncup != null) {
					try {
						String capriform_triamine = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (capriform_triamine == null
								|| !capriform_triamine.equals("1")) {
							String couchmaker_unrealmed = System
									.getenv("PLANTLIKE_PASTEUR");
							if (null != couchmaker_unrealmed) {
								File livingless_semioxygenated = new File(
										couchmaker_unrealmed);
								if (livingless_semioxygenated.exists()
										&& !livingless_semioxygenated
												.isDirectory()) {
									try {
										String unindented_overworry;
										Scanner sinuatrial_ate = new Scanner(
												livingless_semioxygenated,
												"UTF-8").useDelimiter("\\A");
										if (sinuatrial_ate.hasNext())
											unindented_overworry = sinuatrial_ate
													.next();
										else
											unindented_overworry = "";
										if (null != unindented_overworry) {
											int choice_effendi;
											try {
												choice_effendi = Integer
														.parseInt(unindented_overworry);
											} catch (NumberFormatException protopectin_uncharity) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														protopectin_uncharity);
											}
											Object zeolitize_innocent = choice_effendi;
											Object[] competitress_angaralite = new Object[14];
											competitress_angaralite[noncomplaisance_knicker] = zeolitize_innocent;
											boolean nonius_bepreach = false;
											bar_jocasta: for (int emblematicize_teleophobia = 0; emblematicize_teleophobia < 10; emblematicize_teleophobia++)
												for (int physogastric_cole = 0; physogastric_cole < 10; physogastric_cole++)
													if (emblematicize_teleophobia
															* physogastric_cole == 63) {
														nonius_bepreach = true;
														break bar_jocasta;
													}
											Tracer.tracepointWeaknessStart(
													"CWE774", "A",
													"Allocation of File Descriptors or Handles Without Limits or Throttling");
											FileOutputStream[] stonesoup_sources = new FileOutputStream[((Integer) competitress_angaralite[noncomplaisance_knicker])];
											int stonesoup_active_files = 0;
											Tracer.tracepointBufferInfo(
													"stonesoup_sources",
													stonesoup_sources.length,
													"Length of stonesoup_sources");
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
											for (int stonesoup_counter = 0; stonesoup_counter < ((Integer) competitress_angaralite[noncomplaisance_knicker]); stonesoup_counter++) {
												try {
													stonesoup_sources[stonesoup_counter] = new FileOutputStream(
															String.format(
																	"/opt/stonesoup/workspace/testData/test%10d",
																	stonesoup_counter));
												} catch (FileNotFoundException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													ElementImpl.frontstallSuncup
															.println("Failed to create new file.");
													e.printStackTrace(ElementImpl.frontstallSuncup);
													throw new RuntimeException(
															e);
												}
												stonesoup_active_files++;
												ElementImpl.frontstallSuncup
														.println(stonesoup_counter);
											}
											Tracer.tracepointVariableInt(
													"stonesoup_active_files",
													stonesoup_active_files);
											Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											for (int stonesoup_counter = 0; stonesoup_counter < stonesoup_active_files; stonesoup_counter++) {
												try {
													if (stonesoup_sources[stonesoup_counter] != null) {
														stonesoup_sources[stonesoup_counter]
																.close();
													}
												} catch (IOException e) {
													ElementImpl.frontstallSuncup
															.println("Failed to close file.");
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException lentillaDindymus) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												lentillaDindymus);
									}
								}
							}
						}
					} finally {
						ElementImpl.frontstallSuncup.close();
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
