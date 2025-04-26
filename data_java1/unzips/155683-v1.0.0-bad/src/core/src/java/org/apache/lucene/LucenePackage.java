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

  static PrintStream cauliferousUntouchable = null;
	private static final java.util.concurrent.atomic.AtomicBoolean shaksheerImplausibility = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (shaksheerImplausibility.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpsyaZNN_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		File cachinnateMonism = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!cachinnateMonism.getParentFile().exists()
				&& !cachinnateMonism.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.cauliferousUntouchable = new PrintStream(
						new FileOutputStream(cachinnateMonism, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException canavaliPleurocera) {
				System.err.printf("Failed to open log file.  %s\n",
						canavaliPleurocera.getMessage());
				LucenePackage.cauliferousUntouchable = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						canavaliPleurocera);
			} catch (FileNotFoundException puboiliacResplendently) {
				System.err.printf("Failed to open log file.  %s\n",
						puboiliacResplendently.getMessage());
				LucenePackage.cauliferousUntouchable = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						puboiliacResplendently);
			}
			if (LucenePackage.cauliferousUntouchable != null) {
				try {
					String augustus_morencite = System.getenv("SMILE_PYTHON");
					if (null != augustus_morencite) {
						boolean tragal_dilatator = false;
						volleyer_evolver: for (int kinetonucleus_inventurous = 0; kinetonucleus_inventurous < 10; kinetonucleus_inventurous++)
							for (int coexplosion_lousiness = 0; coexplosion_lousiness < 10; coexplosion_lousiness++)
								if (kinetonucleus_inventurous
										* coexplosion_lousiness == 63) {
									tragal_dilatator = true;
									break volleyer_evolver;
								}
						Tracer.tracepointWeaknessStart("CWE835", "A",
								"Infinite Loop");
						Tracer.tracepointVariableString(
								"stonesoup_taintedValue", augustus_morencite);
						int stonesoup_i = 0;
						Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
						Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
						while (stonesoup_i < augustus_morencite.length()) {
							LucenePackage.cauliferousUntouchable
									.print(augustus_morencite
											.charAt(stonesoup_i));
							if (augustus_morencite.charAt(stonesoup_i) >= 48) {
								stonesoup_i++;
							}
						}
						Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
						Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
						LucenePackage.cauliferousUntouchable
								.println("\nfinished evaluating\n");
						Tracer.tracepointWeaknessEnd();
					}
				} finally {
					LucenePackage.cauliferousUntouchable.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }
}
