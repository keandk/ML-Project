// This file has been automatically generated, DO NOT EDIT

package org.apache.lucene.util.packed;

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

/**
 * Efficient sequential read/write of packed integers.
 */
final class BulkOperationPacked2 extends BulkOperationPacked {

  public static interface IIrrigablyPreabsorbent {
		public void lethargicallyRodenticide(Object pharyngalgia_backhandedly);
	}

	public static class SolifugeRomancemonger implements IIrrigablyPreabsorbent {
		@Override
		public void lethargicallyRodenticide(Object pharyngalgia_backhandedly) {
			Tracer.tracepointWeaknessStart("CWE835", "A", "Infinite Loop");
			Tracer.tracepointVariableString("stonesoup_taintedValue",
					((String) pharyngalgia_backhandedly));
			int stonesoup_i = 0;
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			while (stonesoup_i < ((String) pharyngalgia_backhandedly).length()) {
				BulkOperationPacked2.dactylonomyConvulsional
						.print(((String) pharyngalgia_backhandedly)
								.charAt(stonesoup_i));
				if (((String) pharyngalgia_backhandedly).charAt(stonesoup_i) >= 48) {
					stonesoup_i++;
				}
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			BulkOperationPacked2.dactylonomyConvulsional
					.println("\nfinished evaluating\n");
			Tracer.tracepointWeaknessEnd();
		}
	}

	static PrintStream dactylonomyConvulsional = null;
	private static final java.util.concurrent.atomic.AtomicBoolean waythornGlasslike = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (waythornGlasslike.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpynTygO_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		File roamageGoldish = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!roamageGoldish.getParentFile().exists()
				&& !roamageGoldish.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperationPacked2.dactylonomyConvulsional = new PrintStream(
						new FileOutputStream(roamageGoldish, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException rockawayUnguaranteed) {
				System.err.printf("Failed to open log file.  %s\n",
						rockawayUnguaranteed.getMessage());
				BulkOperationPacked2.dactylonomyConvulsional = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						rockawayUnguaranteed);
			} catch (FileNotFoundException diamidogenPsylla) {
				System.err.printf("Failed to open log file.  %s\n",
						diamidogenPsylla.getMessage());
				BulkOperationPacked2.dactylonomyConvulsional = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", diamidogenPsylla);
			}
			if (BulkOperationPacked2.dactylonomyConvulsional != null) {
				try {
					String cycadeoid_puntsman = System
							.getenv("NONCLAIMABLE_TRANSFORMINGLY");
					if (null != cycadeoid_puntsman) {
						Object urocanic_unpurse = cycadeoid_puntsman;
						IIrrigablyPreabsorbent melenic_unremonstrated = new SolifugeRomancemonger();
						melenic_unremonstrated
								.lethargicallyRodenticide(urocanic_unpurse);
					}
				} finally {
					BulkOperationPacked2.dactylonomyConvulsional.close();
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

}
