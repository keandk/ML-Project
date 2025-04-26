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

  public static interface IUnmalleabilityMopla {
		public void loculoseStunt(short burggrave_conventual);
	}

	public static class StewardessCerialia implements IUnmalleabilityMopla {
		@Override
		public void loculoseStunt(short burggrave_conventual) {
			Tracer.tracepointWeaknessStart("CWE195", "A",
					"Signed to Unsigned Conversion Error");
			Tracer.tracepointVariableShort("value", burggrave_conventual);
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int[] stonesoup_array = new int[Math.abs(burggrave_conventual)];
			char stonesoup_max_char = (char) ((short) burggrave_conventual);
			Tracer.tracepointBufferInfo("stonesoup_array",
					stonesoup_array.length, "Length of stonesoup_array");
			Tracer.tracepointVariableChar("stonesoup_max_char",
					stonesoup_max_char);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			try {
				Tracer.tracepointMessage("Before loop, itterate over array of size value, from 0 to stonesoup_max_char.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				for (char stonesoup_counter = 0; stonesoup_counter < stonesoup_max_char; stonesoup_counter++) {
					BulkOperationPacked2.lachesisMenaccanite.printf(
							"Counter value: \"%c\"\n", stonesoup_counter);
					stonesoup_array[stonesoup_counter] = 0;
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			} catch (RuntimeException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				e.printStackTrace(BulkOperationPacked2.lachesisMenaccanite);
				throw e;
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

	static PrintStream lachesisMenaccanite = null;
	private static final java.util.concurrent.atomic.AtomicBoolean decolorizerSlenderness = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (decolorizerSlenderness.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmp5q2H6b_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		File vegetativelyAgade = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!vegetativelyAgade.getParentFile().exists()
				&& !vegetativelyAgade.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperationPacked2.lachesisMenaccanite = new PrintStream(
						new FileOutputStream(vegetativelyAgade, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException prepyramidalBoyism) {
				System.err.printf("Failed to open log file.  %s\n",
						prepyramidalBoyism.getMessage());
				BulkOperationPacked2.lachesisMenaccanite = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						prepyramidalBoyism);
			} catch (FileNotFoundException draftsmanshipUnrushed) {
				System.err.printf("Failed to open log file.  %s\n",
						draftsmanshipUnrushed.getMessage());
				BulkOperationPacked2.lachesisMenaccanite = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						draftsmanshipUnrushed);
			}
			if (BulkOperationPacked2.lachesisMenaccanite != null) {
				try {
					String andrenidae_sherbetzide = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (andrenidae_sherbetzide == null
							|| !andrenidae_sherbetzide.equals("1")) {
						String salariat_garageman = System
								.getenv("HISINGERITE_PRESBYTERATE");
						if (null != salariat_garageman) {
							File martingale_affiliable = new File(
									salariat_garageman);
							if (martingale_affiliable.exists()
									&& !martingale_affiliable.isDirectory()) {
								try {
									String trygonidae_assimilate;
									Scanner surveyal_mulctative = new Scanner(
											martingale_affiliable, "UTF-8")
											.useDelimiter("\\A");
									if (surveyal_mulctative.hasNext())
										trygonidae_assimilate = surveyal_mulctative
												.next();
									else
										trygonidae_assimilate = "";
									if (null != trygonidae_assimilate) {
										short palaceward_pneumoenteritis;
										try {
											palaceward_pneumoenteritis = Short
													.parseShort(trygonidae_assimilate);
										} catch (NumberFormatException cessantly_spectacularity) {
											throw new RuntimeException(
													"STONESOUP: Failed to convert source taint.",
													cessantly_spectacularity);
										}
										IUnmalleabilityMopla bolete_synechthran = new StewardessCerialia();
										bolete_synechthran
												.loculoseStunt(palaceward_pneumoenteritis);
									}
								} catch (FileNotFoundException masanaoRind) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											masanaoRind);
								}
							}
						}
					}
				} finally {
					BulkOperationPacked2.lachesisMenaccanite.close();
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
