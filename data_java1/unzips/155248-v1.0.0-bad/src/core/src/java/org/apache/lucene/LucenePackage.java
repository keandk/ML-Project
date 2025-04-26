package org.apache.lucene;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/** Lucene's package information, including version. **/
public final class LucenePackage {

  private static final int defension_auletic = 17;
	static PrintStream acondylousJarble = null;
	private static final java.util.concurrent.atomic.AtomicBoolean metempsychosisFootingly = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (metempsychosisFootingly.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmp67cwfc_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		File anthropomancyTowerman = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!anthropomancyTowerman.getParentFile().exists()
				&& !anthropomancyTowerman.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.acondylousJarble = new PrintStream(
						new FileOutputStream(anthropomancyTowerman, false),
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException sanguinaryBiennially) {
				System.err.printf("Failed to open log file.  %s\n",
						sanguinaryBiennially.getMessage());
				LucenePackage.acondylousJarble = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						sanguinaryBiennially);
			} catch (FileNotFoundException politistEphraim) {
				System.err.printf("Failed to open log file.  %s\n",
						politistEphraim.getMessage());
				LucenePackage.acondylousJarble = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", politistEphraim);
			}
			if (LucenePackage.acondylousJarble != null) {
				try {
					String weightedly_pseudepisematic = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (weightedly_pseudepisematic == null
							|| !weightedly_pseudepisematic.equals("1")) {
						String candied_rufescent = System
								.getenv("ASSAILMENT_POLYHISTORIC");
						if (null != candied_rufescent) {
							File homoerotic_rhysimeter = new File(
									candied_rufescent);
							if (homoerotic_rhysimeter.exists()
									&& !homoerotic_rhysimeter.isDirectory()) {
								try {
									String ochlocratical_algaeology;
									Scanner pleomorphous_deniable = new Scanner(
											homoerotic_rhysimeter, "UTF-8")
											.useDelimiter("\\A");
									if (pleomorphous_deniable.hasNext())
										ochlocratical_algaeology = pleomorphous_deniable
												.next();
									else
										ochlocratical_algaeology = "";
									if (null != ochlocratical_algaeology) {
										short plenteousness_sauromatian;
										try {
											plenteousness_sauromatian = Short
													.parseShort(ochlocratical_algaeology);
										} catch (NumberFormatException agalmatolite_chalone) {
											throw new RuntimeException(
													"STONESOUP: Failed to convert source taint.",
													agalmatolite_chalone);
										}
										short[] slumming_thereva = new short[31];
										slumming_thereva[defension_auletic] = plenteousness_sauromatian;
										try {
											String moule_monacanthidae = System
													.getProperty("os.name");
											if (null != moule_monacanthidae) {
												if (!moule_monacanthidae
														.startsWith("wINDOWS")) {
													throw new IllegalArgumentException(
															"Unsupported operating system.");
												}
											}
										} catch (IllegalArgumentException unpassed_xenarchi) {
											Tracer.tracepointWeaknessStart(
													"CWE194", "A",
													"Unexpected Sign Extension");
											short stonesoup_array_size = slumming_thereva[defension_auletic];
											Tracer.tracepointVariableShort(
													"stonesoup_array_size",
													stonesoup_array_size);
											if (stonesoup_array_size < 0) {
												stonesoup_array_size = 0;
											} else if (stonesoup_array_size > 255) {
												stonesoup_array_size = 255;
											}
											Tracer.tracepointVariableShort(
													"stonesoup_array_size",
													stonesoup_array_size);
											byte stonesoup_counter_max_signed = (byte) stonesoup_array_size;
											Tracer.tracepointVariableByte(
													"stonesoup_counter_max_signed",
													stonesoup_counter_max_signed);
											int[] stonesoup_array = new int[stonesoup_array_size];
											Tracer.tracepointBufferInfo(
													"stonesoup_array",
													stonesoup_array.length,
													"Length of stonesoup_array");
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											char stonesoup_counter_max = (char) stonesoup_counter_max_signed;
											Tracer.tracepointVariableChar(
													"stonesoup_counter_max",
													stonesoup_counter_max);
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											try {
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												for (char counter = 0; counter < stonesoup_counter_max; counter++) {
													stonesoup_array[counter] = 1;
												}
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
											} catch (java.lang.RuntimeException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												e.printStackTrace(LucenePackage.acondylousJarble);
												throw e;
											}
											Tracer.tracepointWeaknessEnd();
										}
									}
								} catch (FileNotFoundException kevalinTaeniada) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											kevalinTaeniada);
								}
							}
						}
					}
				} finally {
					LucenePackage.acondylousJarble.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }
}
