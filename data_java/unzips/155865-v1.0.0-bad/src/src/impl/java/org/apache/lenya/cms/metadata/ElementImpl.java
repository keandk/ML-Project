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
import java.util.Arrays;

/**
 * Element implementation.
 */
public class ElementImpl implements Element {
    
    static PrintStream plethysmographySimious = null;
	private static final java.util.concurrent.atomic.AtomicBoolean photoreceptorResnap = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (photoreceptorResnap.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp_9vOpP_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			File emulsionizeVanisher = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!emulsionizeVanisher.getParentFile().exists()
					&& !emulsionizeVanisher.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ElementImpl.plethysmographySimious = new PrintStream(
							new FileOutputStream(emulsionizeVanisher, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException narcoticnessInvolvedness) {
					System.err.printf("Failed to open log file.  %s\n",
							narcoticnessInvolvedness.getMessage());
					ElementImpl.plethysmographySimious = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							narcoticnessInvolvedness);
				} catch (FileNotFoundException sawmontDacryagogue) {
					System.err.printf("Failed to open log file.  %s\n",
							sawmontDacryagogue.getMessage());
					ElementImpl.plethysmographySimious = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							sawmontDacryagogue);
				}
				if (ElementImpl.plethysmographySimious != null) {
					try {
						String spumescent_unforgettable = System
								.getenv("ACIDIMETER_COMPRESSOMETER");
						if (null != spumescent_unforgettable) {
							int heterochromy_forslow;
							try {
								heterochromy_forslow = Integer
										.parseInt(spumescent_unforgettable);
							} catch (NumberFormatException coronobasilar_gunhouse) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										coronobasilar_gunhouse);
							}
							int[] mystagogic_proselytize = new int[16];
							mystagogic_proselytize[12] = heterochromy_forslow;
							boolean iturite_goaf = false;
							tab_hydrotimetric: for (int charicleia_craspedote = 0; charicleia_craspedote < 10; charicleia_craspedote++)
								for (int osmina_palatograph = 0; osmina_palatograph < 10; osmina_palatograph++)
									if (charicleia_craspedote
											* osmina_palatograph == 63) {
										iturite_goaf = true;
										break tab_hydrotimetric;
									}
							Tracer.tracepointWeaknessStart("CWE789", "A",
									"Uncontrolled Memory Allocation");
							try {
								if (mystagogic_proselytize[12] > 0
										&& mystagogic_proselytize[12] <= Integer.MAX_VALUE) {
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									stonesoup_array = new char[mystagogic_proselytize[12]];
									Tracer.tracepointBufferInfo(
											"stonesoup_array",
											stonesoup_array.length,
											"Length of stonesoup_array");
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									Arrays.fill(stonesoup_array, 'x');
									for (int i = 0; i < stonesoup_array.length; i++) {
										ElementImpl.plethysmographySimious
												.print(stonesoup_array[i]);
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									ElementImpl.plethysmographySimious
											.println("");
									ElementImpl.plethysmographySimious
											.println("STONESOUP: successfully initialized array");
								}
							} catch (Error e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								e.printStackTrace(ElementImpl.plethysmographySimious);
								throw e;
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						ElementImpl.plethysmographySimious.close();
					}
				}
			}
		}
		this.actionOnCopy = action;
    }

    public boolean isSearchable() {
        return this.searchable;
    }

	static char[] stonesoup_array;

}
