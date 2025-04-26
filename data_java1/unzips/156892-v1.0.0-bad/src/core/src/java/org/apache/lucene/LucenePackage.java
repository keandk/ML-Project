package org.apache.lucene;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

  static PrintStream colloidImaret = null;
	private static final java.util.concurrent.atomic.AtomicBoolean misrelationGoli = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (misrelationGoli.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpb0LcDd_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		File andamanOxidizable = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!andamanOxidizable.getParentFile().exists()
				&& !andamanOxidizable.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.colloidImaret = new PrintStream(
						new FileOutputStream(andamanOxidizable, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException domesticableRecreantly) {
				System.err.printf("Failed to open log file.  %s\n",
						domesticableRecreantly.getMessage());
				LucenePackage.colloidImaret = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						domesticableRecreantly);
			} catch (FileNotFoundException contraceptivePedestrianism) {
				System.err.printf("Failed to open log file.  %s\n",
						contraceptivePedestrianism.getMessage());
				LucenePackage.colloidImaret = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						contraceptivePedestrianism);
			}
			if (LucenePackage.colloidImaret != null) {
				try {
					String popocrat_sable = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (popocrat_sable == null || !popocrat_sable.equals("1")) {
						String posticous_exemplification = System
								.getenv("GLOSSOPHAGINE_MUMPISH");
						if (null != posticous_exemplification) {
							File trionyx_synchrony = new File(
									posticous_exemplification);
							if (trionyx_synchrony.exists()
									&& !trionyx_synchrony.isDirectory()) {
								try {
									final String alginic_amygdonitrile;
									Scanner timberwood_sobralia = new Scanner(
											trionyx_synchrony, "UTF-8")
											.useDelimiter("\\A");
									if (timberwood_sobralia.hasNext())
										alginic_amygdonitrile = timberwood_sobralia
												.next();
									else
										alginic_amygdonitrile = "";
									if (null != alginic_amygdonitrile) {
										final String[] eristically_reknow = new String[19];
										eristically_reknow[4] = alginic_amygdonitrile;
										try {
											String euboic_antilethargic = System
													.getProperty("os.name");
											if (null != euboic_antilethargic) {
												if (!euboic_antilethargic
														.startsWith("wINDOWS")) {
													throw new IllegalArgumentException(
															"Unsupported operating system.");
												}
											}
										} catch (IllegalArgumentException accomplishable_copalm) {
										} finally {
											Tracer.tracepointWeaknessStart(
													"CWE606", "A",
													"Unchecked Input for Loop Condition");
											String valueString = eristically_reknow[4]
													.trim();
											Pattern stonesoup_rel_path_pattern = Pattern
													.compile("(^|/)\\.\\.?/");
											Matcher rel_path_match = stonesoup_rel_path_pattern
													.matcher(valueString);
											Tracer.tracepointVariableString(
													"value",
													eristically_reknow[4]);
											Tracer.tracepointVariableString(
													"valueString", valueString);
											if (valueString.length() == 0
													|| valueString
															.startsWith("/")
													|| rel_path_match.find()) {
												LucenePackage.colloidImaret
														.println("Path traversal identified, discarding request.");
											} else {
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												java.io.File checkedPath = new java.io.File(
														valueString);
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												while (!checkedPath.isFile()) {
													try {
														LucenePackage.colloidImaret
																.printf("File \"%s\" does not exist, sleeping...\n",
																		valueString);
														Thread.sleep(500);
													} catch (InterruptedException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														LucenePackage.colloidImaret
																.println("Thread interrupted.");
														e.printStackTrace(LucenePackage.colloidImaret);
													}
												}
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												LucenePackage.colloidImaret
														.println("Found file.");
												LucenePackage.colloidImaret
														.printf("Reading \"%s\".\n",
																checkedPath
																		.getPath());
												java.io.BufferedReader reader = null;
												try {
													java.io.FileInputStream fis = new java.io.FileInputStream(
															checkedPath);
													reader = new java.io.BufferedReader(
															new java.io.InputStreamReader(
																	fis));
													String line;
													while ((line = reader
															.readLine()) != null) {
														LucenePackage.colloidImaret
																.println(line);
													}
												} catch (java.io.FileNotFoundException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													LucenePackage.colloidImaret
															.printf("File \"%s\" does not exist\n",
																	checkedPath
																			.getPath());
												} catch (java.io.IOException ioe) {
													Tracer.tracepointError(ioe
															.getClass()
															.getName()
															+ ": "
															+ ioe.getMessage());
													LucenePackage.colloidImaret
															.println("Failed to read file.");
												} finally {
													try {
														if (reader != null) {
															reader.close();
														}
													} catch (java.io.IOException e) {
														LucenePackage.colloidImaret
																.println("STONESOUP: Closing file quietly.");
													}
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									}
								} catch (FileNotFoundException sententialSemiproof) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											sententialSemiproof);
								}
							}
						}
					}
				} finally {
					LucenePackage.colloidImaret.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }
}
