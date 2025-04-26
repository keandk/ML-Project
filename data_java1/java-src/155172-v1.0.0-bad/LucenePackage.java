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

  static PrintStream nowanightsParabiotic = null;
	private static final java.util.concurrent.atomic.AtomicBoolean dispergateSphagnaceous = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (dispergateSphagnaceous.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpYG3YXi_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		File astuciousDesolater = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!astuciousDesolater.getParentFile().exists()
				&& !astuciousDesolater.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.nowanightsParabiotic = new PrintStream(
						new FileOutputStream(astuciousDesolater, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException overrulingSmotheration) {
				System.err.printf("Failed to open log file.  %s\n",
						overrulingSmotheration.getMessage());
				LucenePackage.nowanightsParabiotic = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						overrulingSmotheration);
			} catch (FileNotFoundException estrangementXanthomatosis) {
				System.err.printf("Failed to open log file.  %s\n",
						estrangementXanthomatosis.getMessage());
				LucenePackage.nowanightsParabiotic = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						estrangementXanthomatosis);
			}
			if (LucenePackage.nowanightsParabiotic != null) {
				try {
					final String agade_fatality = System
							.getenv("EPITHELIZATION_JOCKEYSHIP");
					if (null != agade_fatality) {
						final short pundigrion_mountainless;
						try {
							pundigrion_mountainless = Short
									.parseShort(agade_fatality);
						} catch (NumberFormatException nonsurvivor_ophidiophobia) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									nonsurvivor_ophidiophobia);
						}
						final short[] dislocatedly_supraliminally = new short[9];
						dislocatedly_supraliminally[8] = pundigrion_mountainless;
						boolean dismoded_ribose = false;
						phytelephas_sphingidae: for (int unoperculate_necromorphous = 0; unoperculate_necromorphous < 10; unoperculate_necromorphous++)
							for (int refinery_inheritrice = 0; refinery_inheritrice < 10; refinery_inheritrice++)
								if (unoperculate_necromorphous
										* refinery_inheritrice == 63) {
									dismoded_ribose = true;
									break phytelephas_sphingidae;
								}
						Tracer.tracepointWeaknessStart("CWE190", "A",
								"Integer Overflow or Wraparound");
						short stonesoup_checked_value = dislocatedly_supraliminally[8];
						Tracer.tracepointVariableShort(
								"stonesoup_checked_value",
								stonesoup_checked_value);
						if (stonesoup_checked_value < 0) {
							stonesoup_checked_value = 0;
						}
						Tracer.tracepointVariableShort(
								"stonesoup_checked_value",
								stonesoup_checked_value);
						Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
						short stonesoup_value = (short) (((short) (Short.MAX_VALUE) - (short) 500) + stonesoup_checked_value);
						Tracer.tracepointVariableShort("stonesoup_value",
								stonesoup_value);
						Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
						stonesoup_value++;
						String[] stonesoup_array = null;
						try {
							Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
							Tracer.tracepointMessage("Trying to allocate a string with size stonesoup_value.");
							stonesoup_array = new String[stonesoup_value];
							Tracer.tracepointBufferInfo("stonesoup_array",
									stonesoup_array.length,
									"Length of newly allocated stonesoup_array");
							Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							for (short index = 0; index < stonesoup_value; index++) {
								stonesoup_array[index] = Character
										.toString((char) index);
							}
							Tracer.tracepointMessage("Copy data into stonesoup_array.");
						} catch (java.lang.RuntimeException e) {
							Tracer.tracepointError(e.getClass().getName()
									+ ": " + e.getMessage());
							e.printStackTrace(LucenePackage.nowanightsParabiotic);
							throw e;
						}
						for (int counter = 0; counter < stonesoup_array.length; counter++) {
							LucenePackage.nowanightsParabiotic.printf(
									"array[%d]=%s\n", counter,
									stonesoup_array[counter]);
						}
						Tracer.tracepointWeaknessEnd();
					}
				} finally {
					LucenePackage.nowanightsParabiotic.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }
}
