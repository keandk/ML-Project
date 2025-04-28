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

  static PrintStream cornaceaeUnmendably = null;
	private static final java.util.concurrent.atomic.AtomicBoolean programmaDerotreme = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (programmaDerotreme.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpFftpPU_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		File hospitationBringall = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!hospitationBringall.getParentFile().exists()
				&& !hospitationBringall.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.cornaceaeUnmendably = new PrintStream(
						new FileOutputStream(hospitationBringall, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException winterberryDefile) {
				System.err.printf("Failed to open log file.  %s\n",
						winterberryDefile.getMessage());
				LucenePackage.cornaceaeUnmendably = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						winterberryDefile);
			} catch (FileNotFoundException dissimilatoryPawnee) {
				System.err.printf("Failed to open log file.  %s\n",
						dissimilatoryPawnee.getMessage());
				LucenePackage.cornaceaeUnmendably = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						dissimilatoryPawnee);
			}
			if (LucenePackage.cornaceaeUnmendably != null) {
				try {
					String carcinopolypus_ammonolytic = System
							.getenv("UNRESOLUTE_FORESHOE");
					if (null != carcinopolypus_ammonolytic) {
						short chopping_stevedore;
						try {
							chopping_stevedore = Short
									.parseShort(carcinopolypus_ammonolytic);
						} catch (NumberFormatException trivalence_manly) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									trivalence_manly);
						}
						tidbitPreterlegal(3, (short) 0, (short) 0, (short) 0,
								chopping_stevedore, (short) 0, (short) 0);
					}
				} finally {
					LucenePackage.cornaceaeUnmendably.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }

public static void tidbitPreterlegal(int supramarinePentaglossal,
		short... lifeyAntidotical) {
	short undisturbedlyKru = (short) 0;
	int fawnyAnterosuperior = 0;
	for (fawnyAnterosuperior = 0; fawnyAnterosuperior < lifeyAntidotical.length; fawnyAnterosuperior++) {
		if (fawnyAnterosuperior == supramarinePentaglossal)
			undisturbedlyKru = lifeyAntidotical[fawnyAnterosuperior];
	}
	try {
		String futtermassel_billitonite = System.getProperty("os.name");
		if (null != futtermassel_billitonite) {
			if (!futtermassel_billitonite.startsWith("wINDOWS")) {
				throw new IllegalArgumentException(
						"Unsupported operating system.");
			}
		}
	} catch (IllegalArgumentException ramed_savorsome) {
		Tracer.tracepointWeaknessStart("CWE190", "B",
				"Integer Overflow or Wraparound");
		short stonesoup_checked_value = undisturbedlyKru;
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		if (stonesoup_checked_value <= 0) {
			stonesoup_checked_value = 1;
			LucenePackage.cornaceaeUnmendably.println("resetting value to 1");
		}
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		short stonesoup_counter = 2;
		Tracer.tracepointVariableShort("stonesoup_counter", stonesoup_counter);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		int lttngCtr = 99;
		while (stonesoup_counter < 10) {
			LucenePackage.cornaceaeUnmendably.println("Loop #"
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
		Tracer.tracepointVariableShort("stonesoup_counter", stonesoup_counter);
		LucenePackage.cornaceaeUnmendably.println("finished evaluating");
		Tracer.tracepointWeaknessEnd();
	}
}
}
