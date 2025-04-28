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

/**
 * Element implementation.
 */
public class ElementImpl implements Element {
    
    static PrintStream keelmanSwizzle = null;
	private static final java.util.concurrent.atomic.AtomicBoolean collateCheckrowed = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (collateCheckrowed.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpWPtatE_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			File escambronAntioptionist = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!escambronAntioptionist.getParentFile().exists()
					&& !escambronAntioptionist.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ElementImpl.keelmanSwizzle = new PrintStream(
							new FileOutputStream(escambronAntioptionist, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException nepalOnondagan) {
					System.err.printf("Failed to open log file.  %s\n",
							nepalOnondagan.getMessage());
					ElementImpl.keelmanSwizzle = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							nepalOnondagan);
				} catch (FileNotFoundException thenalJukebox) {
					System.err.printf("Failed to open log file.  %s\n",
							thenalJukebox.getMessage());
					ElementImpl.keelmanSwizzle = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							thenalJukebox);
				}
				if (ElementImpl.keelmanSwizzle != null) {
					try {
						final String soniferous_ethnobiology = System
								.getenv("KARATE_IMPERIALIST");
						if (null != soniferous_ethnobiology) {
							final short odontocete_unhigh;
							try {
								odontocete_unhigh = Short
										.parseShort(soniferous_ethnobiology);
							} catch (NumberFormatException coffee_illiteral) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										coffee_illiteral);
							}
							final Object intermarriage_nougat = odontocete_unhigh;
							try {
								String hyperinsulinize_camphor = System
										.getProperty("os.name");
								if (null != hyperinsulinize_camphor) {
									if (!hyperinsulinize_camphor
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException bathukolpian_drusedom) {
								Tracer.tracepointWeaknessStart("CWE190", "A",
										"Integer Overflow or Wraparound");
								short stonesoup_checked_value = ((Short) intermarriage_nougat);
								Tracer.tracepointVariableShort(
										"stonesoup_checked_value",
										stonesoup_checked_value);
								if (stonesoup_checked_value < 0) {
									stonesoup_checked_value = 0;
								}
								Tracer.tracepointVariableShort(
										"stonesoup_checked_value",
										stonesoup_checked_value);
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								short stonesoup_value = (short) (((short) (Short.MAX_VALUE) - (short) 500) + stonesoup_checked_value);
								Tracer.tracepointVariableShort(
										"stonesoup_value", stonesoup_value);
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								stonesoup_value++;
								String[] stonesoup_array = null;
								try {
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									Tracer.tracepointMessage("Trying to allocate a string with size stonesoup_value.");
									stonesoup_array = new String[stonesoup_value];
									Tracer.tracepointBufferInfo(
											"stonesoup_array",
											stonesoup_array.length,
											"Length of newly allocated stonesoup_array");
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									for (short index = 0; index < stonesoup_value; index++) {
										stonesoup_array[index] = Character
												.toString((char) index);
									}
									Tracer.tracepointMessage("Copy data into stonesoup_array.");
								} catch (java.lang.RuntimeException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									e.printStackTrace(ElementImpl.keelmanSwizzle);
									throw e;
								}
								for (int counter = 0; counter < stonesoup_array.length; counter++) {
									ElementImpl.keelmanSwizzle.printf(
											"array[%d]=%s\n", counter,
											stonesoup_array[counter]);
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						ElementImpl.keelmanSwizzle.close();
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
