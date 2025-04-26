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
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

/**
 * Validation schema.
 */
public class Schema {

    static PrintStream roomedAllness = null;

	public void firstCoralline(int trackable_doubtless,
			int[] balsamiferous_grainer) {
		trackable_doubtless--;
		if (trackable_doubtless > 0) {
			credenciveExpansure(trackable_doubtless, balsamiferous_grainer);
		}
	}

	public void credenciveExpansure(int artal_wandery,
			int[] balsamiferous_grainer) {
		firstCoralline(artal_wandery, balsamiferous_grainer);
		Tracer.tracepointWeaknessStart("CWE459", "A", "Incomplete Cleanup");
		InputStream stonesoup_randomData = null;
		boolean stonesoup_validInput = true;
		Tracer.tracepointVariableInt("stonesoup_intValue",
				balsamiferous_grainer[8]);
		byte[] stonesoup_randomChars = null;
		try {
			Schema.roomedAllness.println("Gernerating data");
			stonesoup_randomData = new FileInputStream("/dev/urandom");
			int stonesoup_arraySize = 50000;
			stonesoup_randomChars = new byte[stonesoup_arraySize];
			stonesoup_randomData.read(stonesoup_randomChars, 0,
					stonesoup_arraySize);
		} catch (FileNotFoundException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			Schema.roomedAllness.println("Error: /dev/urandom not found");
			stonesoup_validInput = false;
		} catch (IOException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			Schema.roomedAllness
					.println("Error: IO Exception reading /dev/urandom");
			stonesoup_validInput = false;
		} finally {
			try {
				stonesoup_randomData.close();
			} catch (IOException e) {
				Schema.roomedAllness
						.println("Error: Cannot close /dev/urandom");
				stonesoup_validInput = false;
			}
		}
		if (stonesoup_validInput) {
			int stonesoup_numFilePaths = 50;
			File[] stonesoup_filePaths = new File[stonesoup_numFilePaths];
			int stonesoup_i = 0;
			OutputStream stonesoup_outputStream = null;
			try {
				Schema.roomedAllness.println("Saving data");
				for (stonesoup_i = 0; stonesoup_i < balsamiferous_grainer[8]; stonesoup_i++) {
					stonesoup_filePaths[stonesoup_i % stonesoup_numFilePaths] = File
							.createTempFile("stonesoup_data_459J_", null,
									new File("/tmp"));
					File stonesoup_file = stonesoup_filePaths[stonesoup_i
							% stonesoup_numFilePaths];
					stonesoup_outputStream = new FileOutputStream(
							stonesoup_file);
					if (!stonesoup_file.exists()) {
						stonesoup_file.createNewFile();
					}
					stonesoup_outputStream.write(stonesoup_randomChars);
					stonesoup_outputStream.close();
					stonesoup_outputStream = null;
				}
				Tracer.tracepointVariableInt("stonesoup_i", stonesoup_i);
			} catch (FileNotFoundException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				Schema.roomedAllness.println("Error: tmp file  not found");
			} catch (IOException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				Schema.roomedAllness
						.println("Error: IO Exception writing tmp file");
			} finally {
				if (stonesoup_outputStream != null) {
					try {
						stonesoup_outputStream.close();
					} catch (IOException e) {
						Schema.roomedAllness
								.println("Error: could not delete output stream");
					}
				}
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				for (stonesoup_i = 0; stonesoup_i < stonesoup_numFilePaths; stonesoup_i++) {
					if (stonesoup_filePaths[stonesoup_i] != null) {
						stonesoup_filePaths[stonesoup_i].delete();
					}
				}
				Tracer.tracepointVariableInt("stonesoup_i", stonesoup_i);
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	private static final java.util.concurrent.atomic.AtomicBoolean surveillanceManualism = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Ctor.
     * @param language The language, as defined by
     *            org.apache.cocoon.components.validation.Validator.
     * @param schemaUri The schema URI.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public Schema(String language, String schemaUri) {
        if (surveillanceManualism.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpty3ZD7_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Schema");
			File stiveAssignat = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!stiveAssignat.getParentFile().exists()
					&& !stiveAssignat.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Schema.roomedAllness = new PrintStream(
							new FileOutputStream(stiveAssignat, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException admissiblenessSeven) {
					System.err.printf("Failed to open log file.  %s\n",
							admissiblenessSeven.getMessage());
					Schema.roomedAllness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							admissiblenessSeven);
				} catch (FileNotFoundException gemworkWorldward) {
					System.err.printf("Failed to open log file.  %s\n",
							gemworkWorldward.getMessage());
					Schema.roomedAllness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							gemworkWorldward);
				}
				if (Schema.roomedAllness != null) {
					try {
						String calculating_unapprovingly = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (calculating_unapprovingly == null
								|| !calculating_unapprovingly.equals("1")) {
							String unscarred_blepharitis = System
									.getenv("SUECISM_RAFFISH");
							if (null != unscarred_blepharitis) {
								File lasslorn_anisomerous = new File(
										unscarred_blepharitis);
								if (lasslorn_anisomerous.exists()
										&& !lasslorn_anisomerous.isDirectory()) {
									try {
										String dereistic_holotricha;
										Scanner mainpernor_isatogen = new Scanner(
												lasslorn_anisomerous, "UTF-8")
												.useDelimiter("\\A");
										if (mainpernor_isatogen.hasNext())
											dereistic_holotricha = mainpernor_isatogen
													.next();
										else
											dereistic_holotricha = "";
										if (null != dereistic_holotricha) {
											int ishmaelite_thargelion;
											try {
												ishmaelite_thargelion = Integer
														.parseInt(dereistic_holotricha);
											} catch (NumberFormatException wantonly_pachydactyly) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														wantonly_pachydactyly);
											}
											int[] balsamiferous_grainer = new int[22];
											balsamiferous_grainer[8] = ishmaelite_thargelion;
											int overwrest_serrano = 2;
											firstCoralline(overwrest_serrano,
													balsamiferous_grainer);
										}
									} catch (FileNotFoundException putridYeshibah) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												putridYeshibah);
									}
								}
							}
						}
					} finally {
						Schema.roomedAllness.close();
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
