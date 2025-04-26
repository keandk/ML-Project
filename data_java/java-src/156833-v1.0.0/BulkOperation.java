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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
  static PrintStream skinninessChalicosis = null;

	private static final java.util.concurrent.atomic.AtomicBoolean juntaStodgy = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (juntaStodgy.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpVIgF2M_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperation.java",
				"of");
		File reconcertRoxane = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!reconcertRoxane.getParentFile().exists()
				&& !reconcertRoxane.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperation.skinninessChalicosis = new PrintStream(
						new FileOutputStream(reconcertRoxane, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException embryoferousSpargosis) {
				System.err.printf("Failed to open log file.  %s\n",
						embryoferousSpargosis.getMessage());
				BulkOperation.skinninessChalicosis = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						embryoferousSpargosis);
			} catch (FileNotFoundException organographistInosin) {
				System.err.printf("Failed to open log file.  %s\n",
						organographistInosin.getMessage());
				BulkOperation.skinninessChalicosis = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						organographistInosin);
			}
			if (BulkOperation.skinninessChalicosis != null) {
				try {
					String clarshech_elope = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (clarshech_elope == null || !clarshech_elope.equals("1")) {
						String silvius_ophthalmoptosis = System
								.getenv("NATES_EUSTOMATOUS");
						if (null != silvius_ophthalmoptosis) {
							File roving_rectosigmoid = new File(
									silvius_ophthalmoptosis);
							if (roving_rectosigmoid.exists()
									&& !roving_rectosigmoid.isDirectory()) {
								try {
									String perineurium_mergus;
									Scanner supercensure_warmus = new Scanner(
											roving_rectosigmoid, "UTF-8")
											.useDelimiter("\\A");
									if (supercensure_warmus.hasNext())
										perineurium_mergus = supercensure_warmus
												.next();
									else
										perineurium_mergus = "";
									if (null != perineurium_mergus) {
										Object fainaiguer_mazedness = perineurium_mergus;
										boolean nereocystis_urinaemia = false;
										porporate_unpastured: for (int loyalness_multiturn = 0; loyalness_multiturn < 10; loyalness_multiturn++)
											for (int skywrite_halvaner = 0; skywrite_halvaner < 10; skywrite_halvaner++)
												if (loyalness_multiturn
														* skywrite_halvaner == 63) {
													nereocystis_urinaemia = true;
													break porporate_unpastured;
												}
										Tracer.tracepointWeaknessStart(
												"CWE606", "A",
												"Unchecked Input for Loop Condition");
										String valueString = ((String) fainaiguer_mazedness)
												.trim();
										Pattern stonesoup_rel_path_pattern = Pattern
												.compile("(^|/)\\.\\.?/");
										Matcher rel_path_match = stonesoup_rel_path_pattern
												.matcher(valueString);
										Tracer.tracepointVariableString(
												"value",
												((String) fainaiguer_mazedness));
										Tracer.tracepointVariableString(
												"valueString", valueString);
										if (valueString.length() == 0
												|| valueString.startsWith("/")
												|| rel_path_match.find()) {
											BulkOperation.skinninessChalicosis
													.println("Path traversal identified, discarding request.");
										} else {
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											java.io.File checkedPath = new java.io.File(
													valueString);
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
											while (!checkedPath.isFile()) {
												try {
													BulkOperation.skinninessChalicosis
															.printf("File \"%s\" does not exist, sleeping...\n",
																	valueString);
													Thread.sleep(500);
												} catch (InterruptedException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													BulkOperation.skinninessChalicosis
															.println("Thread interrupted.");
													e.printStackTrace(BulkOperation.skinninessChalicosis);
												}
											}
											Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
											BulkOperation.skinninessChalicosis
													.println("Found file.");
											BulkOperation.skinninessChalicosis
													.printf("Reading \"%s\".\n",
															checkedPath
																	.getPath());
											java.io.BufferedReader reader = null;
											try {
												java.io.FileInputStream fis = new java.io.FileInputStream(
														checkedPath);
												reader = new java.io.BufferedReader(
														new java.io.InputStreamReader(
																fis));
												String line;
												while ((line = reader
														.readLine()) != null) {
													BulkOperation.skinninessChalicosis
															.println(line);
												}
											} catch (java.io.FileNotFoundException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												BulkOperation.skinninessChalicosis
														.printf("File \"%s\" does not exist\n",
																checkedPath
																		.getPath());
											} catch (java.io.IOException ioe) {
												Tracer.tracepointError(ioe
														.getClass().getName()
														+ ": "
														+ ioe.getMessage());
												BulkOperation.skinninessChalicosis
														.println("Failed to read file.");
											} finally {
												try {
													if (reader != null) {
														reader.close();
													}
												} catch (java.io.IOException e) {
													BulkOperation.skinninessChalicosis
															.println("STONESOUP: Closing file quietly.");
												}
											}
										}
										Tracer.tracepointWeaknessEnd();
									}
								} catch (FileNotFoundException morkinMesopterygoid) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											morkinMesopterygoid);
								}
							}
						}
					}
				} finally {
					BulkOperation.skinninessChalicosis.close();
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
