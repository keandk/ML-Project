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

  static PrintStream caledoniteFacultate = null;

	public void becurtainedMopstick(int vangueria_truelike,
			int[] helmetmaker_redleg) {
		vangueria_truelike--;
		if (vangueria_truelike > 0) {
			pinkishCovet(vangueria_truelike, helmetmaker_redleg);
		}
	}

	public void pinkishCovet(int sadducean_chirogymnast,
			int[] helmetmaker_redleg) {
		becurtainedMopstick(sadducean_chirogymnast, helmetmaker_redleg);
		Tracer.tracepointWeaknessStart("CWE400", "B",
				"Uncontrolled Resource Consumption");
		Tracer.tracepointMessage("Create pool");
		ExecutorService pool = Executors.newFixedThreadPool(20);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		if (helmetmaker_redleg[19] > 0
				&& helmetmaker_redleg[19] <= Integer.MAX_VALUE) {
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			Tracer.tracepointMessage("Creating threads");
			for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
				pool.execute(new Factorial(helmetmaker_redleg[19],
						BulkOperationPacked2.caledoniteFacultate));
			}
			pool.shutdown();
			Tracer.tracepointMessage("Shutdown pool");
		}
		try {
			Tracer.tracepointMessage("Joining threads");
			while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("Joined threads");
			BulkOperationPacked2.caledoniteFacultate
					.println("finished evaluating");
		} catch (InterruptedException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			BulkOperationPacked2.caledoniteFacultate
					.println("Thread pool interrupted");
		}
		Tracer.tracepointWeaknessEnd();
	}

	private static final java.util.concurrent.atomic.AtomicBoolean semipolarOrthophoric = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (semipolarOrthophoric.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpcQOeSe_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		File sketchLogical = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!sketchLogical.getParentFile().exists()
				&& !sketchLogical.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperationPacked2.caledoniteFacultate = new PrintStream(
						new FileOutputStream(sketchLogical, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException persicoTughra) {
				System.err.printf("Failed to open log file.  %s\n",
						persicoTughra.getMessage());
				BulkOperationPacked2.caledoniteFacultate = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", persicoTughra);
			} catch (FileNotFoundException unchristianlikePolytrichum) {
				System.err.printf("Failed to open log file.  %s\n",
						unchristianlikePolytrichum.getMessage());
				BulkOperationPacked2.caledoniteFacultate = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						unchristianlikePolytrichum);
			}
			if (BulkOperationPacked2.caledoniteFacultate != null) {
				try {
					String catoblepas_troke = System
							.getenv("EXACTOR_IMMERITED");
					if (null != catoblepas_troke) {
						int ocelliferous_argenteous;
						try {
							ocelliferous_argenteous = Integer
									.parseInt(catoblepas_troke);
						} catch (NumberFormatException underplate_humorology) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									underplate_humorology);
						}
						int[] helmetmaker_redleg = new int[20];
						helmetmaker_redleg[19] = ocelliferous_argenteous;
						int bagged_strengthening = 2;
						becurtainedMopstick(bagged_strengthening,
								helmetmaker_redleg);
					}
				} finally {
					BulkOperationPacked2.caledoniteFacultate.close();
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
				"/tmp/tmpcQOeSe_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"Factorial.ctor");
		this.stonesoup_value = fact;
		this.stonesoup_output = output;
	}

	@Override
	public void run() {
		Tracer.tracepointLocation(
				"/tmp/tmpcQOeSe_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				Thread.currentThread().getName() + ": Factorial.run");
		calculateFactorial();
	}

	public void calculateFactorial() {
		Tracer.tracepointLocation(
				"/tmp/tmpcQOeSe_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
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
