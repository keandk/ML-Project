package org.apache.lucene;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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

  static PrintStream rockawayEpiglottitis = null;

	public static void impersuasiblyWheateared(int imperceptive_draftswoman,Object theraphosoid_enchannel){if (imperceptive_draftswoman > 10){impersuasiblyWheateared(imperceptive_draftswoman++,theraphosoid_enchannel);}Tracer.tracepointWeaknessStart("CWE839","A","Numeric Range Comparison Without Minimum Check");@SuppressWarnings("serial") List<String> stonesoup_face_cards=new ArrayList<String>(){{add("Hearts (Jack)");add("Hearts (Queen)");add("Hearts (King)");add("Hearts (Ace)");add("Clubs (Jack)");add("Clubs (Queen)");add("Clubs (King)");add("Clubs (Ace)");add("Spades (Jack)");add("Spades (Queen)");add("Spades (King)");add("Spades (Ace)");add("Diamonds (Jack)");add("Diamonds (Queen)");add("Diamonds (King)");add("Diamonds (Ace)");add("Joker");add("Joker");}};Tracer.tracepointVariableInt("value",((Integer)theraphosoid_enchannel));Tracer.tracepointVariableInt("stonesoup_face_cards.size()",stonesoup_face_cards.size());Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");if (((Integer)theraphosoid_enchannel) >= stonesoup_face_cards.size()){Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");LucenePackage.rockawayEpiglottitis.printf("Card not available for %d.\n",((Integer)theraphosoid_enchannel));} else {Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");try {Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");LucenePackage.rockawayEpiglottitis.printf("Selected Card = %s\n",stonesoup_face_cards.get(((Integer)theraphosoid_enchannel)));Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (RuntimeException e){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());e.printStackTrace(LucenePackage.rockawayEpiglottitis);throw e;}}Tracer.tracepointWeaknessEnd();}

	private static final java.util.concurrent.atomic.AtomicBoolean applotmentGude = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (applotmentGude.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpEQgMvM_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		File ergalEntrust = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!ergalEntrust.getParentFile().exists()
				&& !ergalEntrust.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.rockawayEpiglottitis = new PrintStream(
						new FileOutputStream(ergalEntrust, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException oligophreniaHaggy) {
				System.err.printf("Failed to open log file.  %s\n",
						oligophreniaHaggy.getMessage());
				LucenePackage.rockawayEpiglottitis = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						oligophreniaHaggy);
			} catch (FileNotFoundException inobservantSapota) {
				System.err.printf("Failed to open log file.  %s\n",
						inobservantSapota.getMessage());
				LucenePackage.rockawayEpiglottitis = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						inobservantSapota);
			}
			if (LucenePackage.rockawayEpiglottitis != null) {
				try {
					String alienist_methylosis = System
							.getenv("SUPERGRATIFY_PHYTELEPHAS");
					if (null != alienist_methylosis) {
						int apriline_dormancy;
						try {
							apriline_dormancy = Integer
									.parseInt(alienist_methylosis);
						} catch (NumberFormatException scusation_interjacent) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									scusation_interjacent);
						}
						Object woorali_incommode = apriline_dormancy;
						int folliculated_wafery = 0;
						impersuasiblyWheateared(folliculated_wafery,
								woorali_incommode);
					}
				} finally {
					LucenePackage.rockawayEpiglottitis.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }
}
