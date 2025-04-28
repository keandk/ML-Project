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
import java.io.IOException;

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
  public static class SaprocollSarif<T> {
		private T mouthful_overburthen;

		public SaprocollSarif(T mouthful_overburthen) {
			this.mouthful_overburthen = mouthful_overburthen;
		}

		public T getmouthful_overburthen() {
			return this.mouthful_overburthen;
		}
	}

	static PrintStream mirkHuffy = null;

	private static final java.util.concurrent.atomic.AtomicBoolean cyclostomataImpatiently = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (cyclostomataImpatiently.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpqqiMqc_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperation.java",
				"of");
		File acroporaGalloperdix = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!acroporaGalloperdix.getParentFile().exists()
				&& !acroporaGalloperdix.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperation.mirkHuffy = new PrintStream(new FileOutputStream(
						acroporaGalloperdix, false), true, "ISO-8859-1");
			} catch (UnsupportedEncodingException cytitisChasmophyte) {
				System.err.printf("Failed to open log file.  %s\n",
						cytitisChasmophyte.getMessage());
				BulkOperation.mirkHuffy = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						cytitisChasmophyte);
			} catch (FileNotFoundException toadlingAptotic) {
				System.err.printf("Failed to open log file.  %s\n",
						toadlingAptotic.getMessage());
				BulkOperation.mirkHuffy = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", toadlingAptotic);
			}
			if (BulkOperation.mirkHuffy != null) {
				try {
					String detruncation_morphologist = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (detruncation_morphologist == null
							|| !detruncation_morphologist.equals("1")) {
						String liparocele_obeisantly = System
								.getenv("DIHALO_EXCYSTMENT");
						if (null != liparocele_obeisantly) {
							File chrysophilite_bowlegged = new File(
									liparocele_obeisantly);
							if (chrysophilite_bowlegged.exists()
									&& !chrysophilite_bowlegged.isDirectory()) {
								try {
									String noncommunistic_noncredence;
									Scanner fidgetingly_antischolastic = new Scanner(
											chrysophilite_bowlegged, "UTF-8")
											.useDelimiter("\\A");
									if (fidgetingly_antischolastic.hasNext())
										noncommunistic_noncredence = fidgetingly_antischolastic
												.next();
									else
										noncommunistic_noncredence = "";
									if (null != noncommunistic_noncredence) {
										int castalio_adjutant;
										try {
											castalio_adjutant = Integer
													.parseInt(noncommunistic_noncredence);
										} catch (NumberFormatException unhonorably_aminosis) {
											throw new RuntimeException(
													"STONESOUP: Failed to convert source taint.",
													unhonorably_aminosis);
										}
										Object heliocentric_antiritualistic = castalio_adjutant;
										SaprocollSarif<Object> brimming_chuff = new SaprocollSarif<Object>(
												heliocentric_antiritualistic);
										int siena_guha = 0;
										while (true) {
											siena_guha++;
											if (siena_guha >= 3000)
												break;
										}
										Tracer.tracepointWeaknessStart(
												"CWE774", "A",
												"Allocation of File Descriptors or Handles Without Limits or Throttling");
										FileOutputStream[] stonesoup_sources = new FileOutputStream[((Integer) brimming_chuff
												.getmouthful_overburthen())];
										int stonesoup_active_files = 0;
										Tracer.tracepointBufferInfo(
												"stonesoup_sources",
												stonesoup_sources.length,
												"Length of stonesoup_sources");
										Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										for (int stonesoup_counter = 0; stonesoup_counter < ((Integer) brimming_chuff
												.getmouthful_overburthen()); stonesoup_counter++) {
											try {
												stonesoup_sources[stonesoup_counter] = new FileOutputStream(
														String.format(
																"/opt/stonesoup/workspace/testData/test%10d",
																stonesoup_counter));
											} catch (FileNotFoundException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												BulkOperation.mirkHuffy
														.println("Failed to create new file.");
												e.printStackTrace(BulkOperation.mirkHuffy);
												throw new RuntimeException(e);
											}
											stonesoup_active_files++;
											BulkOperation.mirkHuffy
													.println(stonesoup_counter);
										}
										Tracer.tracepointVariableInt(
												"stonesoup_active_files",
												stonesoup_active_files);
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
										for (int stonesoup_counter = 0; stonesoup_counter < stonesoup_active_files; stonesoup_counter++) {
											try {
												if (stonesoup_sources[stonesoup_counter] != null) {
													stonesoup_sources[stonesoup_counter]
															.close();
												}
											} catch (IOException e) {
												BulkOperation.mirkHuffy
														.println("Failed to close file.");
											}
										}
										Tracer.tracepointWeaknessEnd();
									}
								} catch (FileNotFoundException briberyPash) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											briberyPash);
								}
							}
						}
					}
				} finally {
					BulkOperation.mirkHuffy.close();
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
}
