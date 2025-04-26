package org.apache.lucene;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.math.BigInteger;

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

  static PrintStream pinguescentAfterfall = null;
	private static final java.util.concurrent.atomic.AtomicBoolean adonitolReappreciation = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (adonitolReappreciation.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpGLue0t_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		File prankPholidota = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!prankPholidota.getParentFile().exists()
				&& !prankPholidota.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.pinguescentAfterfall = new PrintStream(
						new FileOutputStream(prankPholidota, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException unaisledIgnorantly) {
				System.err.printf("Failed to open log file.  %s\n",
						unaisledIgnorantly.getMessage());
				LucenePackage.pinguescentAfterfall = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						unaisledIgnorantly);
			} catch (FileNotFoundException archdissemblerGarbleable) {
				System.err.printf("Failed to open log file.  %s\n",
						archdissemblerGarbleable.getMessage());
				LucenePackage.pinguescentAfterfall = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						archdissemblerGarbleable);
			}
			if (LucenePackage.pinguescentAfterfall != null) {
				try {
					String imprimitive_dermonosology = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (imprimitive_dermonosology == null
							|| !imprimitive_dermonosology.equals("1")) {
						String deutocarbonate_mawkishly = System
								.getenv("EYEBREE_NONHUMANIST");
						if (null != deutocarbonate_mawkishly) {
							File mylonitic_unhonorably = new File(
									deutocarbonate_mawkishly);
							if (mylonitic_unhonorably.exists()
									&& !mylonitic_unhonorably.isDirectory()) {
								try {
									final String territorialist_hetaeria;
									Scanner callistemon_triplopia = new Scanner(
											mylonitic_unhonorably, "UTF-8")
											.useDelimiter("\\A");
									if (callistemon_triplopia.hasNext())
										territorialist_hetaeria = callistemon_triplopia
												.next();
									else
										territorialist_hetaeria = "";
									if (null != territorialist_hetaeria) {
										boolean panela_renderable = false;
										infare_pupa: for (int vibrate_presuspicious = 0; vibrate_presuspicious < 10; vibrate_presuspicious++)
											for (int oreodon_ballotist = 0; oreodon_ballotist < 10; oreodon_ballotist++)
												if (vibrate_presuspicious
														* oreodon_ballotist == 63) {
													panela_renderable = true;
													break infare_pupa;
												}
										Tracer.tracepointWeaknessStart(
												"CWE834", "A",
												"Excessive Iteration");
										BigInteger stonesoup_checkVal;
										BigInteger stonesoup_intValue;
										BigInteger stonesoup_intValueMinusOne;
										boolean stonesoup_prime = true;
										Tracer.tracepointVariableString(
												"stonesoup_taintedValue",
												territorialist_hetaeria);
										try {
											stonesoup_checkVal = new BigInteger(
													"2");
											stonesoup_intValue = new BigInteger(
													territorialist_hetaeria);
											stonesoup_intValueMinusOne = stonesoup_intValue
													.subtract(BigInteger.ONE);
											if (stonesoup_intValue
													.compareTo(BigInteger.ZERO) > 0) {
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												for (; stonesoup_checkVal
														.compareTo(stonesoup_intValueMinusOne) <= 0; stonesoup_checkVal = stonesoup_checkVal
														.add(BigInteger.ONE)) {
													if (stonesoup_intValue
															.mod(stonesoup_checkVal)
															.compareTo(
																	BigInteger.ZERO) == 0) {
														stonesoup_prime = false;
														LucenePackage.pinguescentAfterfall
																.println("Not Prime");
														break;
													}
												}
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											}
										} catch (NumberFormatException e) {
											Tracer.tracepointError(e.getClass()
													.getName()
													+ ": "
													+ e.getMessage());
											LucenePackage.pinguescentAfterfall
													.println("STONESOUP: Input string is not an integer");
										}
										LucenePackage.pinguescentAfterfall
												.println("finished evaluating");
										Tracer.tracepointWeaknessEnd();
									}
								} catch (FileNotFoundException postpericardialSalinoterreous) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											postpericardialSalinoterreous);
								}
							}
						}
					}
				} finally {
					LucenePackage.pinguescentAfterfall.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }
}
