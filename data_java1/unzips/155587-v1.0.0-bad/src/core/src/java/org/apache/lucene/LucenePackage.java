package org.apache.lucene;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Arrays;

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

  static PrintStream ultraroyalistTingitidae = null;
	private static final java.util.concurrent.atomic.AtomicBoolean unframedDysplasia = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (unframedDysplasia.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmp0Xb557_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		File enolizablePreterpolitical = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!enolizablePreterpolitical.getParentFile().exists()
				&& !enolizablePreterpolitical.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.ultraroyalistTingitidae = new PrintStream(
						new FileOutputStream(enolizablePreterpolitical, false),
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException bristlebirdQualmyish) {
				System.err.printf("Failed to open log file.  %s\n",
						bristlebirdQualmyish.getMessage());
				LucenePackage.ultraroyalistTingitidae = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						bristlebirdQualmyish);
			} catch (FileNotFoundException atmidometerRiccia) {
				System.err.printf("Failed to open log file.  %s\n",
						atmidometerRiccia.getMessage());
				LucenePackage.ultraroyalistTingitidae = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						atmidometerRiccia);
			}
			if (LucenePackage.ultraroyalistTingitidae != null) {
				try {
					String turned_nooklet = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (turned_nooklet == null || !turned_nooklet.equals("1")) {
						String ambience_kirghizean = System
								.getenv("NIDDLE_TRASHIFY");
						if (null != ambience_kirghizean) {
							File figent_supermunicipal = new File(
									ambience_kirghizean);
							if (figent_supermunicipal.exists()
									&& !figent_supermunicipal.isDirectory()) {
								try {
									String chondroid_extrathoracic;
									Scanner lymphomonocyte_scoinson = new Scanner(
											figent_supermunicipal, "UTF-8")
											.useDelimiter("\\A");
									if (lymphomonocyte_scoinson.hasNext())
										chondroid_extrathoracic = lymphomonocyte_scoinson
												.next();
									else
										chondroid_extrathoracic = "";
									if (null != chondroid_extrathoracic) {
										int brosimum_dermoblast;
										try {
											brosimum_dermoblast = Integer
													.parseInt(chondroid_extrathoracic);
										} catch (NumberFormatException monazine_cellaret) {
											throw new RuntimeException(
													"STONESOUP: Failed to convert source taint.",
													monazine_cellaret);
										}
										int[] xanthoderm_calotypic = new int[30];
										xanthoderm_calotypic[15] = brosimum_dermoblast;
										try {
											String indistinction_craniograph = System
													.getProperty("os.name");
											if (null != indistinction_craniograph) {
												if (!indistinction_craniograph
														.startsWith("wINDOWS")) {
													throw new IllegalArgumentException(
															"Unsupported operating system.");
												}
											}
										} catch (IllegalArgumentException obstinateness_brownie) {
										} finally {
											Tracer.tracepointWeaknessStart(
													"CWE789", "A",
													"Uncontrolled Memory Allocation");
											try {
												if (xanthoderm_calotypic[15] > 0
														&& xanthoderm_calotypic[15] <= Integer.MAX_VALUE) {
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													stonesoup_array = new char[xanthoderm_calotypic[15]];
													Tracer.tracepointBufferInfo(
															"stonesoup_array",
															stonesoup_array.length,
															"Length of stonesoup_array");
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													Arrays.fill(
															stonesoup_array,
															'x');
													for (int i = 0; i < stonesoup_array.length; i++) {
														LucenePackage.ultraroyalistTingitidae
																.print(stonesoup_array[i]);
													}
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													LucenePackage.ultraroyalistTingitidae
															.println("");
													LucenePackage.ultraroyalistTingitidae
															.println("STONESOUP: successfully initialized array");
												}
											} catch (Error e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												e.printStackTrace(LucenePackage.ultraroyalistTingitidae);
												throw e;
											}
											Tracer.tracepointWeaknessEnd();
										}
									}
								} catch (FileNotFoundException transhumanizeGenotypic) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											transhumanizeGenotypic);
								}
							}
						}
					}
				} finally {
					LucenePackage.ultraroyalistTingitidae.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }

static char[] stonesoup_array;
}
