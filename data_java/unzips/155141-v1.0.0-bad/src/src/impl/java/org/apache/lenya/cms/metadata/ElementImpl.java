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
    
    public static interface INumismaticBringal {
		public void aetheogamicPseudonavicular(
				ChauiTheomorphize unition_nyctinasty);
	}

	public static class GossipinessFeatherhead implements INumismaticBringal {
		@Override
		public void aetheogamicPseudonavicular(
				ChauiTheomorphize unition_nyctinasty) {
			Tracer.tracepointWeaknessStart("CWE190", "B",
					"Integer Overflow or Wraparound");
			short stonesoup_checked_value = unition_nyctinasty
					.getundisguisable_nanocephalia()[8];
			Tracer.tracepointVariableShort("stonesoup_checked_value",
					stonesoup_checked_value);
			if (stonesoup_checked_value <= 0) {
				stonesoup_checked_value = 1;
				ElementImpl.gymnothoraxCarica.println("resetting value to 1");
			}
			Tracer.tracepointVariableShort("stonesoup_checked_value",
					stonesoup_checked_value);
			short stonesoup_counter = 2;
			Tracer.tracepointVariableShort("stonesoup_counter",
					stonesoup_counter);
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int lttngCtr = 99;
			while (stonesoup_counter < 10) {
				ElementImpl.gymnothoraxCarica.println("Loop #"
						+ stonesoup_counter);
				if (stonesoup_counter > 0) {
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					stonesoup_counter += stonesoup_checked_value;
				}
				if (stonesoup_counter > 0 || ++lttngCtr >= 100) {
					lttngCtr = 1;
					Tracer.tracepointVariableShort("stonesoup_counter",
							stonesoup_counter);
				}
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointVariableShort("stonesoup_counter",
					stonesoup_counter);
			ElementImpl.gymnothoraxCarica.println("finished evaluating");
			Tracer.tracepointWeaknessEnd();
		}
	}

	public class ChauiTheomorphize {
		private short[] undisguisable_nanocephalia;

		public ChauiTheomorphize(short[] undisguisable_nanocephalia) {
			this.undisguisable_nanocephalia = undisguisable_nanocephalia;
		}

		public short[] getundisguisable_nanocephalia() {
			return this.undisguisable_nanocephalia;
		}
	}

	static PrintStream gymnothoraxCarica = null;
	private static final java.util.concurrent.atomic.AtomicBoolean acetphenetidDismissible = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (acetphenetidDismissible.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpgxKDgz_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			File raadPrecursive = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!raadPrecursive.getParentFile().exists()
					&& !raadPrecursive.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ElementImpl.gymnothoraxCarica = new PrintStream(
							new FileOutputStream(raadPrecursive, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException contratabularMillisecond) {
					System.err.printf("Failed to open log file.  %s\n",
							contratabularMillisecond.getMessage());
					ElementImpl.gymnothoraxCarica = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							contratabularMillisecond);
				} catch (FileNotFoundException cymballikeBrittany) {
					System.err.printf("Failed to open log file.  %s\n",
							cymballikeBrittany.getMessage());
					ElementImpl.gymnothoraxCarica = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							cymballikeBrittany);
				}
				if (ElementImpl.gymnothoraxCarica != null) {
					try {
						String cercopidae_unfairly = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (cercopidae_unfairly == null
								|| !cercopidae_unfairly.equals("1")) {
							String modiolus_phacoidoscope = System
									.getenv("SERBDOM_REQUISITIONARY");
							if (null != modiolus_phacoidoscope) {
								File capshore_fibrinopurulent = new File(
										modiolus_phacoidoscope);
								if (capshore_fibrinopurulent.exists()
										&& !capshore_fibrinopurulent
												.isDirectory()) {
									try {
										String chemicovital_isohaline;
										Scanner stomatal_pseudatoll = new Scanner(
												capshore_fibrinopurulent,
												"UTF-8").useDelimiter("\\A");
										if (stomatal_pseudatoll.hasNext())
											chemicovital_isohaline = stomatal_pseudatoll
													.next();
										else
											chemicovital_isohaline = "";
										if (null != chemicovital_isohaline) {
											short phytometer_triternate;
											try {
												phytometer_triternate = Short
														.parseShort(chemicovital_isohaline);
											} catch (NumberFormatException multilaminate_lotic) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														multilaminate_lotic);
											}
											short[] digitated_fleeciness = new short[30];
											digitated_fleeciness[8] = phytometer_triternate;
											ChauiTheomorphize superadornment_neolatry = new ChauiTheomorphize(
													digitated_fleeciness);
											INumismaticBringal preponderance_wretchedness = new GossipinessFeatherhead();
											preponderance_wretchedness
													.aetheogamicPseudonavicular(superadornment_neolatry);
										}
									} catch (FileNotFoundException treateeBlad) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												treateeBlad);
									}
								}
							}
						}
					} finally {
						ElementImpl.gymnothoraxCarica.close();
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
