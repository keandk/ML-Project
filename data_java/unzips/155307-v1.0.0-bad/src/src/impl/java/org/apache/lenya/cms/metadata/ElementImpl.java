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
    
    static PrintStream dehullObreption = null;
	private static final java.util.concurrent.atomic.AtomicBoolean overglanceMangerite = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (overglanceMangerite.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpOmudXf_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			File bulkSweetlike = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!bulkSweetlike.getParentFile().exists()
					&& !bulkSweetlike.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ElementImpl.dehullObreption = new PrintStream(
							new FileOutputStream(bulkSweetlike, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException septimanalLeucocytoblast) {
					System.err.printf("Failed to open log file.  %s\n",
							septimanalLeucocytoblast.getMessage());
					ElementImpl.dehullObreption = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							septimanalLeucocytoblast);
				} catch (FileNotFoundException tappoonSaddlesoreness) {
					System.err.printf("Failed to open log file.  %s\n",
							tappoonSaddlesoreness.getMessage());
					ElementImpl.dehullObreption = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							tappoonSaddlesoreness);
				}
				if (ElementImpl.dehullObreption != null) {
					try {
						String outfeeding_apartment = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (outfeeding_apartment == null
								|| !outfeeding_apartment.equals("1")) {
							String entocornea_oxamide = System
									.getenv("ANTIQUING_BORDAGE");
							if (null != entocornea_oxamide) {
								File unenamored_unistylist = new File(
										entocornea_oxamide);
								if (unenamored_unistylist.exists()
										&& !unenamored_unistylist.isDirectory()) {
									try {
										String semicostiferous_cryptomeria;
										Scanner lustratory_gumma = new Scanner(
												unenamored_unistylist, "UTF-8")
												.useDelimiter("\\A");
										if (lustratory_gumma.hasNext())
											semicostiferous_cryptomeria = lustratory_gumma
													.next();
										else
											semicostiferous_cryptomeria = "";
										if (null != semicostiferous_cryptomeria) {
											short worthfulness_hie;
											try {
												worthfulness_hie = Short
														.parseShort(semicostiferous_cryptomeria);
											} catch (NumberFormatException hemiatrophy_equitemporal) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														hemiatrophy_equitemporal);
											}
											short[] bismuthiferous_spaciously = new short[12];
											bismuthiferous_spaciously[9] = worthfulness_hie;
											Tracer.tracepointWeaknessStart(
													"CWE191", "A",
													"Integer Underflow (Wrap or Wraparound)");
											short stonesoup_checked_value = bismuthiferous_spaciously[9];
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
												ElementImpl.dehullObreption
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
											ElementImpl.dehullObreption
													.println("finished evaluating");
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException precourseApophonia) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												precourseApophonia);
									}
								}
							}
						}
					} finally {
						ElementImpl.dehullObreption.close();
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
