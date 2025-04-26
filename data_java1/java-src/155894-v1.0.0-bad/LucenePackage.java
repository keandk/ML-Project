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

  public static interface ITruncationPolymorphous {
		public void resplendentlyGadolinia(
				NongeographicalStated cosech_gastrological);
	}

	public static class GipperMatrilinearism implements ITruncationPolymorphous {
		@Override
		public void resplendentlyGadolinia(
				NongeographicalStated cosech_gastrological) {
			Tracer.tracepointWeaknessStart("CWE835", "A", "Infinite Loop");
			Tracer.tracepointVariableString("stonesoup_taintedValue",
					cosech_gastrological.getunwoundable_archmediocrity());
			int stonesoup_i = 0;
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			while (stonesoup_i < cosech_gastrological
					.getunwoundable_archmediocrity().length()) {
				LucenePackage.soleilTautousious.print(cosech_gastrological
						.getunwoundable_archmediocrity().charAt(stonesoup_i));
				if (cosech_gastrological.getunwoundable_archmediocrity()
						.charAt(stonesoup_i) >= 48) {
					stonesoup_i++;
				}
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			LucenePackage.soleilTautousious.println("\nfinished evaluating\n");
			Tracer.tracepointWeaknessEnd();
		}
	}

	public static class NongeographicalStated {
		private String unwoundable_archmediocrity;

		public NongeographicalStated(String unwoundable_archmediocrity) {
			this.unwoundable_archmediocrity = unwoundable_archmediocrity;
		}

		public String getunwoundable_archmediocrity() {
			return this.unwoundable_archmediocrity;
		}
	}

	static PrintStream soleilTautousious = null;
	private static final java.util.concurrent.atomic.AtomicBoolean condaliaDoesnt = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (condaliaDoesnt.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpngey_i_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		File techniphoneSpermacetilike = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!techniphoneSpermacetilike.getParentFile().exists()
				&& !techniphoneSpermacetilike.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.soleilTautousious = new PrintStream(
						new FileOutputStream(techniphoneSpermacetilike, false),
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException theonomyColumbiformes) {
				System.err.printf("Failed to open log file.  %s\n",
						theonomyColumbiformes.getMessage());
				LucenePackage.soleilTautousious = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						theonomyColumbiformes);
			} catch (FileNotFoundException uranometricalEase) {
				System.err.printf("Failed to open log file.  %s\n",
						uranometricalEase.getMessage());
				LucenePackage.soleilTautousious = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						uranometricalEase);
			}
			if (LucenePackage.soleilTautousious != null) {
				try {
					String polanisia_exotoxic = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (polanisia_exotoxic == null
							|| !polanisia_exotoxic.equals("1")) {
						String bullyragging_hemianopic = System
								.getenv("UNENTANGLE_DISTOMIDAE");
						if (null != bullyragging_hemianopic) {
							File anilopyrin_quadruplane = new File(
									bullyragging_hemianopic);
							if (anilopyrin_quadruplane.exists()
									&& !anilopyrin_quadruplane.isDirectory()) {
								try {
									String oleaginousness_bementite;
									Scanner snowberg_bipalium = new Scanner(
											anilopyrin_quadruplane, "UTF-8")
											.useDelimiter("\\A");
									if (snowberg_bipalium.hasNext())
										oleaginousness_bementite = snowberg_bipalium
												.next();
									else
										oleaginousness_bementite = "";
									if (null != oleaginousness_bementite) {
										NongeographicalStated tridrachm_chattelship = new NongeographicalStated(
												oleaginousness_bementite);
										ITruncationPolymorphous potmaking_moonscape = new GipperMatrilinearism();
										potmaking_moonscape
												.resplendentlyGadolinia(tridrachm_chattelship);
									}
								} catch (FileNotFoundException stereognosisLila) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											stereognosisLila);
								}
							}
						}
					}
				} finally {
					LucenePackage.soleilTautousious.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }
}
