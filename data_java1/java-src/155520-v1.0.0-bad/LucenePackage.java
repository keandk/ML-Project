package org.apache.lucene;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.ArrayList;

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

  public static class RamessideLeucocarpous<T> {
		private T ungrilled_sandaliform;

		public RamessideLeucocarpous(T ungrilled_sandaliform) {
			this.ungrilled_sandaliform = ungrilled_sandaliform;
		}

		public T getungrilled_sandaliform() {
			return this.ungrilled_sandaliform;
		}
	}

	static PrintStream boatheaderWagonmaker = null;
	private static final java.util.concurrent.atomic.AtomicBoolean metromalaciaShellworker = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (metromalaciaShellworker.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpypU2Ja_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		File gymnodiniumForefoot = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!gymnodiniumForefoot.getParentFile().exists()
				&& !gymnodiniumForefoot.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.boatheaderWagonmaker = new PrintStream(
						new FileOutputStream(gymnodiniumForefoot, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException cestodeCobblership) {
				System.err.printf("Failed to open log file.  %s\n",
						cestodeCobblership.getMessage());
				LucenePackage.boatheaderWagonmaker = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						cestodeCobblership);
			} catch (FileNotFoundException lithoclasticPodophthalmate) {
				System.err.printf("Failed to open log file.  %s\n",
						lithoclasticPodophthalmate.getMessage());
				LucenePackage.boatheaderWagonmaker = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						lithoclasticPodophthalmate);
			}
			if (LucenePackage.boatheaderWagonmaker != null) {
				try {
					String shrub_subcaecal = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (shrub_subcaecal == null || !shrub_subcaecal.equals("1")) {
						String binge_entablement = System
								.getenv("JOVICENTRICALLY_ACCITE");
						if (null != binge_entablement) {
							File apotelesmatic_communalist = new File(
									binge_entablement);
							if (apotelesmatic_communalist.exists()
									&& !apotelesmatic_communalist.isDirectory()) {
								try {
									String muffet_desirous;
									Scanner camenae_workplace = new Scanner(
											apotelesmatic_communalist, "UTF-8")
											.useDelimiter("\\A");
									if (camenae_workplace.hasNext())
										muffet_desirous = camenae_workplace
												.next();
									else
										muffet_desirous = "";
									if (null != muffet_desirous) {
										int mousefish_incrystal;
										try {
											mousefish_incrystal = Integer
													.parseInt(muffet_desirous);
										} catch (NumberFormatException loyolism_exacerbescence) {
											throw new RuntimeException(
													"STONESOUP: Failed to convert source taint.",
													loyolism_exacerbescence);
										}
										int[] miserabilist_etymon = new int[30];
										miserabilist_etymon[8] = mousefish_incrystal;
										RamessideLeucocarpous<int[]> venatical_prepossessor = new RamessideLeucocarpous<int[]>(
												miserabilist_etymon);
										sequestDiscommender(venatical_prepossessor);
									}
								} catch (FileNotFoundException arbalisterHypha) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											arbalisterHypha);
								}
							}
						}
					}
				} finally {
					LucenePackage.boatheaderWagonmaker.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }

public static void sequestDiscommender(RamessideLeucocarpous<int[]> lymphorrhagicPalaeechinoidea){Tracer.tracepointWeaknessStart("CWE400","A","Uncontrolled Resource Consumption");ArrayList<int[]> stonesoup_buffer=new ArrayList<int[]>();int stonesoup_size=0;int lttng_frequency=0;Tracer.tracepointVariableInt("stonesoup_intValue",lymphorrhagicPalaeechinoidea.getungrilled_sandaliform()[8]);if (lymphorrhagicPalaeechinoidea.getungrilled_sandaliform()[8] > 0 && lymphorrhagicPalaeechinoidea.getungrilled_sandaliform()[8] <= Integer.MAX_VALUE){stonesoup_size=10000;Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");for (int i=0;i < lymphorrhagicPalaeechinoidea.getungrilled_sandaliform()[8];){try {stonesoup_buffer.add(new int[stonesoup_size]);i++;} catch (OutOfMemoryError e){if (lttng_frequency == 0){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());Tracer.tracepointBufferInfo("stonesoup_buffer",stonesoup_buffer.size(),"Size of stonesoup_buffer");}lttng_frequency=(lttng_frequency == 199)?0:lttng_frequency + 1;}}Tracer.tracepointBufferInfo("stonesoup_buffer",stonesoup_buffer.size(),"Size of stonesoup_buffer");Tracer.tracepointMessage("TRIGGER-POINT: AFTER");Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");LucenePackage.boatheaderWagonmaker.println("Allocated all the memory requested");}Tracer.tracepointWeaknessEnd();}

public static void sequestDiscommender() {
	sequestDiscommender(null);
}
}
