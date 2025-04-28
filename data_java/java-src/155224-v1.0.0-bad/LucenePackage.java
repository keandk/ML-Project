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

  static PrintStream unapostrophizedTenebrionid = null;
	private static final java.util.concurrent.atomic.AtomicBoolean misaffectionBurdigalian = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (misaffectionBurdigalian.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpU3STch_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		File cubonavicularStrop = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!cubonavicularStrop.getParentFile().exists()
				&& !cubonavicularStrop.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.unapostrophizedTenebrionid = new PrintStream(
						new FileOutputStream(cubonavicularStrop, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException subscienceUnconstrued) {
				System.err.printf("Failed to open log file.  %s\n",
						subscienceUnconstrued.getMessage());
				LucenePackage.unapostrophizedTenebrionid = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						subscienceUnconstrued);
			} catch (FileNotFoundException supergeneDelenda) {
				System.err.printf("Failed to open log file.  %s\n",
						supergeneDelenda.getMessage());
				LucenePackage.unapostrophizedTenebrionid = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", supergeneDelenda);
			}
			if (LucenePackage.unapostrophizedTenebrionid != null) {
				try {
					String pentasepalous_subincident = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (pentasepalous_subincident == null
							|| !pentasepalous_subincident.equals("1")) {
						String cutch_overcold = System
								.getenv("OXYPYCNOS_HYSTEROGENETIC");
						if (null != cutch_overcold) {
							File gutter_choralist = new File(cutch_overcold);
							if (gutter_choralist.exists()
									&& !gutter_choralist.isDirectory()) {
								try {
									String nonrated_dreyfusism;
									Scanner anepigraphous_heteromerous = new Scanner(
											gutter_choralist, "UTF-8")
											.useDelimiter("\\A");
									if (anepigraphous_heteromerous.hasNext())
										nonrated_dreyfusism = anepigraphous_heteromerous
												.next();
									else
										nonrated_dreyfusism = "";
									if (null != nonrated_dreyfusism) {
										short eburnian_logicless;
										try {
											eburnian_logicless = Short
													.parseShort(nonrated_dreyfusism);
										} catch (NumberFormatException granger_cyatheaceae) {
											throw new RuntimeException(
													"STONESOUP: Failed to convert source taint.",
													granger_cyatheaceae);
										}
										Object unguidedly_nailprint = eburnian_logicless;
										archtreasurerHeterodactylae(unguidedly_nailprint);
									}
								} catch (FileNotFoundException firnPeriodontium) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											firnPeriodontium);
								}
							}
						}
					}
				} finally {
					LucenePackage.unapostrophizedTenebrionid.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }

public static void archtreasurerHeterodactylae(Object dumpishnessKolach) {
	Tracer.tracepointWeaknessStart("CWE191", "A",
			"Integer Underflow (Wrap or Wraparound)");
	short stonesoup_checked_value = ((Short) dumpishnessKolach);
	Tracer.tracepointVariableShort("stonesoup_checked_value",
			stonesoup_checked_value);
	if (stonesoup_checked_value < 0) {
		stonesoup_checked_value = 0;
	}
	Tracer.tracepointVariableShort("stonesoup_checked_value",
			stonesoup_checked_value);
	Short[] stonesoup_some_values = new Short[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
	short stonesoup_counter = -20;
	short stonesoup_offset = 40;
	Tracer.tracepointBufferInfo("stonesoup_some_values",
			stonesoup_some_values.length, "Length of stonesoup_some_values");
	Tracer.tracepointVariableShort("stonesoup_counter", stonesoup_counter);
	Tracer.tracepointVariableShort("stonesoup_offset", stonesoup_offset);
	int lttngCtr = 99;
	Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
	Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
	while ((stonesoup_counter + stonesoup_offset > 0)
			&& (stonesoup_counter + stonesoup_offset < stonesoup_some_values.length)) {
		LucenePackage.unapostrophizedTenebrionid.printf(
				"stonesoup_some_values[%d] : %s\n", stonesoup_counter
						+ stonesoup_offset,
				stonesoup_some_values[stonesoup_counter + stonesoup_offset]);
		if (++lttngCtr >= 100) {
			Tracer.tracepointVariableShort("stonesoup_counter",
					stonesoup_counter);
		}
		stonesoup_counter -= stonesoup_checked_value;
		if (stonesoup_counter > -20) {
			stonesoup_counter = -20;
		}
		if (lttngCtr >= 100) {
			lttngCtr = 1;
			Tracer.tracepointVariableShort("stonesoup_counter",
					stonesoup_counter);
		}
	}
	Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
	Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
	Tracer.tracepointBufferInfo("stonesoup_some_values",
			stonesoup_some_values.length, "Length of stonesoup_some_values");
	Tracer.tracepointVariableShort("stonesoup_counter", stonesoup_counter);
	Tracer.tracepointVariableShort("stonesoup_offset", stonesoup_offset);
	LucenePackage.unapostrophizedTenebrionid.println("finished evaluating");
	Tracer.tracepointWeaknessEnd();
}

public static void archtreasurerHeterodactylae() {
	archtreasurerHeterodactylae(null);
}
}
