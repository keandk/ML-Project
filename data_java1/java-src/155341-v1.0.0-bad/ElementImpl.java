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

/**
 * Element implementation.
 */
public class ElementImpl implements Element {
    
    static PrintStream kwartaGeek = null;
	private static final java.util.concurrent.atomic.AtomicBoolean subconicalSphaeraphides = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (subconicalSphaeraphides.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpq3pUs8_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			File anthropotoxinPerakim = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!anthropotoxinPerakim.getParentFile().exists()
					&& !anthropotoxinPerakim.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ElementImpl.kwartaGeek = new PrintStream(
							new FileOutputStream(anthropotoxinPerakim, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException urogravimeterPluckily) {
					System.err.printf("Failed to open log file.  %s\n",
							urogravimeterPluckily.getMessage());
					ElementImpl.kwartaGeek = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							urogravimeterPluckily);
				} catch (FileNotFoundException acuesthesiaThrowster) {
					System.err.printf("Failed to open log file.  %s\n",
							acuesthesiaThrowster.getMessage());
					ElementImpl.kwartaGeek = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							acuesthesiaThrowster);
				}
				if (ElementImpl.kwartaGeek != null) {
					try {
						String majagua_playbox = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (majagua_playbox == null
								|| !majagua_playbox.equals("1")) {
							String triodontes_nitrosify = System
									.getenv("AUTHORITY_STANZAICALLY");
							if (null != triodontes_nitrosify) {
								File nonneutrality_aponeurotomy = new File(
										triodontes_nitrosify);
								if (nonneutrality_aponeurotomy.exists()
										&& !nonneutrality_aponeurotomy
												.isDirectory()) {
									try {
										String dictyosiphon_bumblebee;
										Scanner haloxene_passiflora = new Scanner(
												nonneutrality_aponeurotomy,
												"UTF-8").useDelimiter("\\A");
										if (haloxene_passiflora.hasNext())
											dictyosiphon_bumblebee = haloxene_passiflora
													.next();
										else
											dictyosiphon_bumblebee = "";
										if (null != dictyosiphon_bumblebee) {
											short wisent_coniothyrium;
											try {
												wisent_coniothyrium = Short
														.parseShort(dictyosiphon_bumblebee);
											} catch (NumberFormatException proemptosis_lunarium) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														proemptosis_lunarium);
											}
											Object bakerly_organogenic = wisent_coniothyrium;
											boolean masked_cephalometric = false;
											smokables_bestialist: for (int complimentarily_hexaradial = 0; complimentarily_hexaradial < 10; complimentarily_hexaradial++)
												for (int phytiferous_isomyaria = 0; phytiferous_isomyaria < 10; phytiferous_isomyaria++)
													if (complimentarily_hexaradial
															* phytiferous_isomyaria == 63) {
														masked_cephalometric = true;
														break smokables_bestialist;
													}
											Tracer.tracepointWeaknessStart(
													"CWE191", "A",
													"Integer Underflow (Wrap or Wraparound)");
											short stonesoup_checked_value = ((Short) bakerly_organogenic);
											Tracer.tracepointVariableShort(
													"stonesoup_checked_value",
													stonesoup_checked_value);
											if (stonesoup_checked_value < 0) {
												stonesoup_checked_value = 0;
											}
											Tracer.tracepointVariableShort(
													"stonesoup_checked_value",
													stonesoup_checked_value);
											Short[] stonesoup_some_values = new Short[] {
													0, 1, 2, 3, 4, 5, 6, 7, 8,
													9, 10, 11, 12, 13, 14, 15,
													16, 17, 18, 19, 20 };
											short stonesoup_counter = -20;
											short stonesoup_offset = 40;
											Tracer.tracepointBufferInfo(
													"stonesoup_some_values",
													stonesoup_some_values.length,
													"Length of stonesoup_some_values");
											Tracer.tracepointVariableShort(
													"stonesoup_counter",
													stonesoup_counter);
											Tracer.tracepointVariableShort(
													"stonesoup_offset",
													stonesoup_offset);
											int lttngCtr = 99;
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
											while ((stonesoup_counter
													+ stonesoup_offset > 0)
													&& (stonesoup_counter
															+ stonesoup_offset < stonesoup_some_values.length)) {
												ElementImpl.kwartaGeek
														.printf("stonesoup_some_values[%d] : %s\n",
																stonesoup_counter
																		+ stonesoup_offset,
																stonesoup_some_values[stonesoup_counter
																		+ stonesoup_offset]);
												if (++lttngCtr >= 100) {
													Tracer.tracepointVariableShort(
															"stonesoup_counter",
															stonesoup_counter);
												}
												stonesoup_counter -= stonesoup_checked_value;
												if (stonesoup_counter > -20) {
													stonesoup_counter = -20;
												}
												if (lttngCtr >= 100) {
													lttngCtr = 1;
													Tracer.tracepointVariableShort(
															"stonesoup_counter",
															stonesoup_counter);
												}
											}
											Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											Tracer.tracepointBufferInfo(
													"stonesoup_some_values",
													stonesoup_some_values.length,
													"Length of stonesoup_some_values");
											Tracer.tracepointVariableShort(
													"stonesoup_counter",
													stonesoup_counter);
											Tracer.tracepointVariableShort(
													"stonesoup_offset",
													stonesoup_offset);
											ElementImpl.kwartaGeek
													.println("finished evaluating");
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException untouchableDisaccustomed) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												untouchableDisaccustomed);
									}
								}
							}
						}
					} finally {
						ElementImpl.kwartaGeek.close();
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
