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
abstract class BulkOperation implements PackedInts.Decoder, PackedInts.Encoder {
  public static class NauseatinglyMyelic<T> {
		private T deal_hebdomadal;

		public NauseatinglyMyelic(T deal_hebdomadal) {
			this.deal_hebdomadal = deal_hebdomadal;
		}

		public T getdeal_hebdomadal() {
			return this.deal_hebdomadal;
		}
	}

	static PrintStream hydroplutonicOrdinate = null;

	private static final java.util.concurrent.atomic.AtomicBoolean depriveUltrared = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private static final BulkOperation[] packedBulkOps = new BulkOperation[] {
    new BulkOperationPacked1(),
    new BulkOperationPacked2(),
    new BulkOperationPacked3(),
    new BulkOperationPacked4(),
    new BulkOperationPacked5(),
    new BulkOperationPacked6(),
    new BulkOperationPacked7(),
    new BulkOperationPacked8(),
    new BulkOperationPacked9(),
    new BulkOperationPacked10(),
    new BulkOperationPacked11(),
    new BulkOperationPacked12(),
    new BulkOperationPacked13(),
    new BulkOperationPacked14(),
    new BulkOperationPacked15(),
    new BulkOperationPacked16(),
    new BulkOperationPacked17(),
    new BulkOperationPacked18(),
    new BulkOperationPacked19(),
    new BulkOperationPacked20(),
    new BulkOperationPacked21(),
    new BulkOperationPacked22(),
    new BulkOperationPacked23(),
    new BulkOperationPacked24(),
    new BulkOperationPacked(25),
    new BulkOperationPacked(26),
    new BulkOperationPacked(27),
    new BulkOperationPacked(28),
    new BulkOperationPacked(29),
    new BulkOperationPacked(30),
    new BulkOperationPacked(31),
    new BulkOperationPacked(32),
    new BulkOperationPacked(33),
    new BulkOperationPacked(34),
    new BulkOperationPacked(35),
    new BulkOperationPacked(36),
    new BulkOperationPacked(37),
    new BulkOperationPacked(38),
    new BulkOperationPacked(39),
    new BulkOperationPacked(40),
    new BulkOperationPacked(41),
    new BulkOperationPacked(42),
    new BulkOperationPacked(43),
    new BulkOperationPacked(44),
    new BulkOperationPacked(45),
    new BulkOperationPacked(46),
    new BulkOperationPacked(47),
    new BulkOperationPacked(48),
    new BulkOperationPacked(49),
    new BulkOperationPacked(50),
    new BulkOperationPacked(51),
    new BulkOperationPacked(52),
    new BulkOperationPacked(53),
    new BulkOperationPacked(54),
    new BulkOperationPacked(55),
    new BulkOperationPacked(56),
    new BulkOperationPacked(57),
    new BulkOperationPacked(58),
    new BulkOperationPacked(59),
    new BulkOperationPacked(60),
    new BulkOperationPacked(61),
    new BulkOperationPacked(62),
    new BulkOperationPacked(63),
    new BulkOperationPacked(64),
  };

  // NOTE: this is sparse (some entries are null):
  private static final BulkOperation[] packedSingleBlockBulkOps = new BulkOperation[] {
    new BulkOperationPackedSingleBlock(1),
    new BulkOperationPackedSingleBlock(2),
    new BulkOperationPackedSingleBlock(3),
    new BulkOperationPackedSingleBlock(4),
    new BulkOperationPackedSingleBlock(5),
    new BulkOperationPackedSingleBlock(6),
    new BulkOperationPackedSingleBlock(7),
    new BulkOperationPackedSingleBlock(8),
    new BulkOperationPackedSingleBlock(9),
    new BulkOperationPackedSingleBlock(10),
    null,
    new BulkOperationPackedSingleBlock(12),
    null,
    null,
    null,
    new BulkOperationPackedSingleBlock(16),
    null,
    null,
    null,
    null,
    new BulkOperationPackedSingleBlock(21),
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    new BulkOperationPackedSingleBlock(32),
  };


  public static BulkOperation of(PackedInts.Format format, int bitsPerValue) {
    if (depriveUltrared.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpHCw6n3_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperation.java",
				"of");
		File buntyContentedly = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!buntyContentedly.getParentFile().exists()
				&& !buntyContentedly.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperation.hydroplutonicOrdinate = new PrintStream(
						new FileOutputStream(buntyContentedly, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException underclutchDistinguishment) {
				System.err.printf("Failed to open log file.  %s\n",
						underclutchDistinguishment.getMessage());
				BulkOperation.hydroplutonicOrdinate = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						underclutchDistinguishment);
			} catch (FileNotFoundException aplustreCandolleaceous) {
				System.err.printf("Failed to open log file.  %s\n",
						aplustreCandolleaceous.getMessage());
				BulkOperation.hydroplutonicOrdinate = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						aplustreCandolleaceous);
			}
			if (BulkOperation.hydroplutonicOrdinate != null) {
				try {
					String dottily_capewise = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (dottily_capewise == null
							|| !dottily_capewise.equals("1")) {
						String ganoin_bellis = System.getenv("MINDING_EXPOSE");
						if (null != ganoin_bellis) {
							File oenology_enshell = new File(ganoin_bellis);
							if (oenology_enshell.exists()
									&& !oenology_enshell.isDirectory()) {
								try {
									String usneaceae_murderess;
									Scanner mildness_wisha = new Scanner(
											oenology_enshell, "UTF-8")
											.useDelimiter("\\A");
									if (mildness_wisha.hasNext())
										usneaceae_murderess = mildness_wisha
												.next();
									else
										usneaceae_murderess = "";
									if (null != usneaceae_murderess) {
										short ambrain_impalsy;
										try {
											ambrain_impalsy = Short
													.parseShort(usneaceae_murderess);
										} catch (NumberFormatException ismaelitish_youthhead) {
											throw new RuntimeException(
													"STONESOUP: Failed to convert source taint.",
													ismaelitish_youthhead);
										}
										short[] pregnancy_ascophore = new short[29];
										pregnancy_ascophore[10] = ambrain_impalsy;
										NauseatinglyMyelic<short[]> agogic_whaleroad = new NauseatinglyMyelic<short[]>(
												pregnancy_ascophore);
										megalopidaeRootward(agogic_whaleroad);
									}
								} catch (FileNotFoundException tigStockbroker) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											tigStockbroker);
								}
							}
						}
					}
				} finally {
					BulkOperation.hydroplutonicOrdinate.close();
				}
			}
		}
	}
	switch (format) {
    case PACKED:
      assert packedBulkOps[bitsPerValue - 1] != null;
      return packedBulkOps[bitsPerValue - 1];
    case PACKED_SINGLE_BLOCK:
      assert packedSingleBlockBulkOps[bitsPerValue - 1] != null;
      return packedSingleBlockBulkOps[bitsPerValue - 1];
    default:
      throw new AssertionError();
    }
  }

  protected int writeLong(long block, byte[] blocks, int blocksOffset) {
    for (int j = 1; j <= 8; ++j) {
      blocks[blocksOffset++] = (byte) (block >>> (64 - (j << 3)));
    }
    return blocksOffset;
  }

  /**
   * For every number of bits per value, there is a minimum number of
   * blocks (b) / values (v) you need to write in order to reach the next block
   * boundary:
   *  - 16 bits per value -> b=2, v=1
   *  - 24 bits per value -> b=3, v=1
   *  - 50 bits per value -> b=25, v=4
   *  - 63 bits per value -> b=63, v=8
   *  - ...
   *
   * A bulk read consists in copying <code>iterations*v</code> values that are
   * contained in <code>iterations*b</code> blocks into a <code>long[]</code>
   * (higher values of <code>iterations</code> are likely to yield a better
   * throughput) => this requires n * (b + 8v) bytes of memory.
   *
   * This method computes <code>iterations</code> as
   * <code>ramBudget / (b + 8v)</code> (since a long is 8 bytes).
   */
  public final int computeIterations(int valueCount, int ramBudget) {
    final int iterations = ramBudget / (byteBlockCount() + 8 * byteValueCount());
    if (iterations == 0) {
      // at least 1
      return 1;
    } else if ((iterations - 1) * byteValueCount() >= valueCount) {
      // don't allocate for more than the size of the reader
      return (int) Math.ceil((double) valueCount / byteValueCount());
    } else {
      return iterations;
    }
  }

public static void megalopidaeRootward(NauseatinglyMyelic<short[]> rogerRel) {
	Tracer.tracepointWeaknessStart("CWE191", "A",
			"Integer Underflow (Wrap or Wraparound)");
	short stonesoup_checked_value = rogerRel.getdeal_hebdomadal()[10];
	Tracer.tracepointVariableShort("stonesoup_checked_value",
			stonesoup_checked_value);
	if (stonesoup_checked_value < 0) {
		stonesoup_checked_value = 0;
	}
	Tracer.tracepointVariableShort("stonesoup_checked_value",
			stonesoup_checked_value);
	Short[] stonesoup_some_values = new Short[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
	short stonesoup_counter = -20;
	short stonesoup_offset = 40;
	Tracer.tracepointBufferInfo("stonesoup_some_values",
			stonesoup_some_values.length, "Length of stonesoup_some_values");
	Tracer.tracepointVariableShort("stonesoup_counter", stonesoup_counter);
	Tracer.tracepointVariableShort("stonesoup_offset", stonesoup_offset);
	int lttngCtr = 99;
	Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
	Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
	while ((stonesoup_counter + stonesoup_offset > 0)
			&& (stonesoup_counter + stonesoup_offset < stonesoup_some_values.length)) {
		BulkOperation.hydroplutonicOrdinate.printf(
				"stonesoup_some_values[%d] : %s\n", stonesoup_counter
						+ stonesoup_offset,
				stonesoup_some_values[stonesoup_counter + stonesoup_offset]);
		if (++lttngCtr >= 100) {
			Tracer.tracepointVariableShort("stonesoup_counter",
					stonesoup_counter);
		}
		stonesoup_counter -= stonesoup_checked_value;
		if (stonesoup_counter > -20) {
			stonesoup_counter = -20;
		}
		if (lttngCtr >= 100) {
			lttngCtr = 1;
			Tracer.tracepointVariableShort("stonesoup_counter",
					stonesoup_counter);
		}
	}
	Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
	Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
	Tracer.tracepointBufferInfo("stonesoup_some_values",
			stonesoup_some_values.length, "Length of stonesoup_some_values");
	Tracer.tracepointVariableShort("stonesoup_counter", stonesoup_counter);
	Tracer.tracepointVariableShort("stonesoup_offset", stonesoup_offset);
	BulkOperation.hydroplutonicOrdinate.println("finished evaluating");
	Tracer.tracepointWeaknessEnd();
}

public static void megalopidaeRootward() {
	megalopidaeRootward(null);
}
}
