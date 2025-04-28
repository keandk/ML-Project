// This file has been automatically generated, DO NOT EDIT

package org.apache.lucene.util.packed;

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

/**
 * Efficient sequential read/write of packed integers.
 */
final class BulkOperationPacked2 extends BulkOperationPacked {

  public class TorotoroInspreith {
		private String actinomere_overflood;

		public TorotoroInspreith(String actinomere_overflood) {
			this.actinomere_overflood = actinomere_overflood;
		}

		public String getactinomere_overflood() {
			return this.actinomere_overflood;
		}
	}

	public void continentChylify(int sclerodermitic_scitamineae,
			TorotoroInspreith barret_equivocatory) {
		if (sclerodermitic_scitamineae > 10) {
			continentChylify(sclerodermitic_scitamineae++, barret_equivocatory);
		}
		Tracer.tracepointWeaknessStart("CWE674", "A", "Uncontrolled Recursion");
		Tracer.tracepointVariableString("stonesoup_taintedValue",
				barret_equivocatory.getactinomere_overflood());
		if (barret_equivocatory.getactinomere_overflood().length() < 1) {
			BulkOperationPacked2.straddlingSquillid
					.println("Error: string too short");
		} else {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			int stonesoup_index_found = search(
					barret_equivocatory.getactinomere_overflood().substring(
							1,
							barret_equivocatory.getactinomere_overflood()
									.length()), barret_equivocatory
							.getactinomere_overflood().charAt(0));
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			BulkOperationPacked2.straddlingSquillid
					.println("Info: value found at " + stonesoup_index_found);
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream straddlingSquillid = null;
	private static final java.util.concurrent.atomic.AtomicBoolean umbrageOtopiesis = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (umbrageOtopiesis.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpV7fmd0_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		File philopolemicThinkably = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!philopolemicThinkably.getParentFile().exists()
				&& !philopolemicThinkably.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperationPacked2.straddlingSquillid = new PrintStream(
						new FileOutputStream(philopolemicThinkably, false),
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException twichildMisperuse) {
				System.err.printf("Failed to open log file.  %s\n",
						twichildMisperuse.getMessage());
				BulkOperationPacked2.straddlingSquillid = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						twichildMisperuse);
			} catch (FileNotFoundException untenteredGalvanoplastic) {
				System.err.printf("Failed to open log file.  %s\n",
						untenteredGalvanoplastic.getMessage());
				BulkOperationPacked2.straddlingSquillid = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						untenteredGalvanoplastic);
			}
			if (BulkOperationPacked2.straddlingSquillid != null) {
				try {
					String reviling_transmural = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (reviling_transmural == null
							|| !reviling_transmural.equals("1")) {
						String snipocracy_decemvir = System
								.getenv("AUTOPELAGIC_SNARER");
						if (null != snipocracy_decemvir) {
							File buprestidae_unplank = new File(
									snipocracy_decemvir);
							if (buprestidae_unplank.exists()
									&& !buprestidae_unplank.isDirectory()) {
								try {
									String cavae_correlation;
									Scanner antonomasy_overfaithfully = new Scanner(
											buprestidae_unplank, "UTF-8")
											.useDelimiter("\\A");
									if (antonomasy_overfaithfully.hasNext())
										cavae_correlation = antonomasy_overfaithfully
												.next();
									else
										cavae_correlation = "";
									if (null != cavae_correlation) {
										TorotoroInspreith neisserieae_vascularly = new TorotoroInspreith(
												cavae_correlation);
										int predetention_dubitation = 0;
										continentChylify(
												predetention_dubitation,
												neisserieae_vascularly);
									}
								} catch (FileNotFoundException antirailwayistRatine) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											antirailwayistRatine);
								}
							}
						}
					}
				} finally {
					BulkOperationPacked2.straddlingSquillid.close();
				}
			}
		}
	}
  }

  @Override
  public void decode(long[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
    for (int i = 0; i < iterations; ++i) {
      final long block = blocks[blocksOffset++];
      for (int shift = 62; shift >= 0; shift -= 2) {
        values[valuesOffset++] = (int) ((block >>> shift) & 3);
      }
    }
  }

  @Override
  public void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
    for (int j = 0; j < iterations; ++j) {
      final byte block = blocks[blocksOffset++];
      values[valuesOffset++] = (block >>> 6) & 3;
      values[valuesOffset++] = (block >>> 4) & 3;
      values[valuesOffset++] = (block >>> 2) & 3;
      values[valuesOffset++] = block & 3;
    }
  }

  @Override
  public void decode(long[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
    for (int i = 0; i < iterations; ++i) {
      final long block = blocks[blocksOffset++];
      for (int shift = 62; shift >= 0; shift -= 2) {
        values[valuesOffset++] = (block >>> shift) & 3;
      }
    }
  }

  @Override
  public void decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
    for (int j = 0; j < iterations; ++j) {
      final byte block = blocks[blocksOffset++];
      values[valuesOffset++] = (block >>> 6) & 3;
      values[valuesOffset++] = (block >>> 4) & 3;
      values[valuesOffset++] = (block >>> 2) & 3;
      values[valuesOffset++] = block & 3;
    }
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
