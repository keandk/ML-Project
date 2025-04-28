package org.apache.lucene;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;

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

  private static final int starling_diapasonal = 9;
	static PrintStream orantStadion = null;
	private static final java.util.concurrent.atomic.AtomicBoolean trifurcalAsilid = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (trifurcalAsilid.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpJ5wtTG_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		File sherbetzidePrediluvial = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!sherbetzidePrediluvial.getParentFile().exists()
				&& !sherbetzidePrediluvial.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.orantStadion = new PrintStream(
						new FileOutputStream(sherbetzidePrediluvial, false),
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException aerometrySula) {
				System.err.printf("Failed to open log file.  %s\n",
						aerometrySula.getMessage());
				LucenePackage.orantStadion = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", aerometrySula);
			} catch (FileNotFoundException uncensurableCommon) {
				System.err.printf("Failed to open log file.  %s\n",
						uncensurableCommon.getMessage());
				LucenePackage.orantStadion = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						uncensurableCommon);
			}
			if (LucenePackage.orantStadion != null) {
				try {
					String interlinguist_flightless = System
							.getenv("PHOTOTELEPHONY_YOR");
					if (null != interlinguist_flightless) {
						Object bolometer_miscalculation = interlinguist_flightless;
						Object[] cambist_unsized = new Object[21];
						cambist_unsized[starling_diapasonal] = bolometer_miscalculation;
						try {
							String sapropelic_latex = System
									.getProperty("os.name");
							if (null != sapropelic_latex) {
								if (!sapropelic_latex.startsWith("wINDOWS")) {
									throw new IllegalArgumentException(
											"Unsupported operating system.");
								}
							}
						} catch (IllegalArgumentException pastimer_legerdemainist) {
						} finally {
							Tracer.tracepointWeaknessStart("CWE674", "A",
									"Uncontrolled Recursion");
							Tracer.tracepointVariableString(
									"stonesoup_taintedValue",
									((String) cambist_unsized[starling_diapasonal]));
							if (((String) cambist_unsized[starling_diapasonal])
									.length() < 1) {
								LucenePackage.orantStadion
										.println("Error: string too short");
							} else {
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								int stonesoup_index_found = search(
										((String) cambist_unsized[starling_diapasonal])
												.substring(
														1,
														((String) cambist_unsized[starling_diapasonal])
																.length()),
										((String) cambist_unsized[starling_diapasonal])
												.charAt(0));
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								LucenePackage.orantStadion
										.println("Info: value found at "
												+ stonesoup_index_found);
							}
							Tracer.tracepointWeaknessEnd();
						}
					}
				} finally {
					LucenePackage.orantStadion.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }

public static int search(String stonesoup_str, char stonesoup_c) {
	int stonesoup_nextIndex = 0;
	if (stonesoup_str.length() > 0) {
		if (stonesoup_str.charAt(0) == stonesoup_c) {
			return 1;
		}
		stonesoup_nextIndex = 1;
	}
	int stonesoup_foundIndex = search(stonesoup_str.substring(
			stonesoup_nextIndex, stonesoup_str.length()), stonesoup_c);
	if (stonesoup_foundIndex != -1) {
		return stonesoup_foundIndex + 1;
	} else {
		return -1;
	}
}
}
