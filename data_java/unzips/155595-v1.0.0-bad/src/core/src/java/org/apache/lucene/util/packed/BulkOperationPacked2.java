// This file has been automatically generated, DO NOT EDIT

package org.apache.lucene.util.packed;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

  public class QuatrainUndecayedness {
		private Object biology_nete;

		public QuatrainUndecayedness(Object biology_nete) {
			this.biology_nete = biology_nete;
		}

		public Object getbiology_nete() {
			return this.biology_nete;
		}
	}

	static PrintStream cinnamateSclavonian = null;
	private static final java.util.concurrent.atomic.AtomicBoolean atechnyFilleul = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (atechnyFilleul.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpslBZU4_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		File hastateHarmonograph = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!hastateHarmonograph.getParentFile().exists()
				&& !hastateHarmonograph.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperationPacked2.cinnamateSclavonian = new PrintStream(
						new FileOutputStream(hastateHarmonograph, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException hyalodaciteIntercooling) {
				System.err.printf("Failed to open log file.  %s\n",
						hyalodaciteIntercooling.getMessage());
				BulkOperationPacked2.cinnamateSclavonian = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						hyalodaciteIntercooling);
			} catch (FileNotFoundException tundunProfluence) {
				System.err.printf("Failed to open log file.  %s\n",
						tundunProfluence.getMessage());
				BulkOperationPacked2.cinnamateSclavonian = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", tundunProfluence);
			}
			if (BulkOperationPacked2.cinnamateSclavonian != null) {
				try {
					String kitchenman_solecizer = System
							.getenv("BOOMINGLY_UNDIGHTED");
					if (null != kitchenman_solecizer) {
						int planera_ravioli;
						try {
							planera_ravioli = Integer
									.parseInt(kitchenman_solecizer);
						} catch (NumberFormatException purposefulness_feedbin) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									purposefulness_feedbin);
						}
						Object borrowing_factive = planera_ravioli;
						QuatrainUndecayedness bemaul_retrotransfer = new QuatrainUndecayedness(
								borrowing_factive);
						try {
							String thoke_easternmost = System
									.getProperty("os.name");
							if (null != thoke_easternmost) {
								if (!thoke_easternmost.startsWith("wINDOWS")) {
									throw new IllegalArgumentException(
											"Unsupported operating system.");
								}
							}
						} catch (IllegalArgumentException glebeless_sneakiness) {
							Tracer.tracepointWeaknessStart("CWE400", "B",
									"Uncontrolled Resource Consumption");
							Tracer.tracepointMessage("Create pool");
							ExecutorService pool = Executors
									.newFixedThreadPool(20);
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							if (((Integer) bemaul_retrotransfer
									.getbiology_nete()) > 0
									&& ((Integer) bemaul_retrotransfer
											.getbiology_nete()) <= Integer.MAX_VALUE) {
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								Tracer.tracepointMessage("Creating threads");
								for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
									pool.execute(new Factorial(
											((Integer) bemaul_retrotransfer
													.getbiology_nete()),
											BulkOperationPacked2.cinnamateSclavonian));
								}
								pool.shutdown();
								Tracer.tracepointMessage("Shutdown pool");
							}
							try {
								Tracer.tracepointMessage("Joining threads");
								while (!pool.awaitTermination(1,
										TimeUnit.SECONDS)) {
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								Tracer.tracepointMessage("Joined threads");
								BulkOperationPacked2.cinnamateSclavonian
										.println("finished evaluating");
							} catch (InterruptedException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								BulkOperationPacked2.cinnamateSclavonian
										.println("Thread pool interrupted");
							}
							Tracer.tracepointWeaknessEnd();
						}
					}
				} finally {
					BulkOperationPacked2.cinnamateSclavonian.close();
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

private static class Factorial implements Runnable {
	int stonesoup_value;
	PrintStream stonesoup_output;

	Factorial(int fact, PrintStream output) {
		Tracer.tracepointLocation(
				"/tmp/tmpslBZU4_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"Factorial.ctor");
		this.stonesoup_value = fact;
		this.stonesoup_output = output;
	}

	@Override
	public void run() {
		Tracer.tracepointLocation(
				"/tmp/tmpslBZU4_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				Thread.currentThread().getName() + ": Factorial.run");
		calculateFactorial();
	}

	public void calculateFactorial() {
		Tracer.tracepointLocation(
				"/tmp/tmpslBZU4_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				Thread.currentThread().getName()
						+ ": Factorial.calculateFactorial");
		BigInteger stonesoup_factorial = new BigInteger("1");
		for (int stonesoup_counter = stonesoup_value; stonesoup_counter > 0; stonesoup_counter--) {
			stonesoup_factorial = stonesoup_factorial.multiply(BigInteger
					.valueOf(stonesoup_counter));
		}
		stonesoup_output.println(stonesoup_factorial);
	}
}

}
