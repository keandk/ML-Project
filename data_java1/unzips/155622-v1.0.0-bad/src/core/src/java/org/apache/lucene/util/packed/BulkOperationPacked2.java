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
import java.math.BigInteger;

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

  static PrintStream compressometerAgoraphobia = null;
	private static final java.util.concurrent.atomic.AtomicBoolean hemocyaninInsubduable = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (hemocyaninInsubduable.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpVdLtaw_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		File theatronCrake = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!theatronCrake.getParentFile().exists()
				&& !theatronCrake.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperationPacked2.compressometerAgoraphobia = new PrintStream(
						new FileOutputStream(theatronCrake, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException blindfishNervily) {
				System.err.printf("Failed to open log file.  %s\n",
						blindfishNervily.getMessage());
				BulkOperationPacked2.compressometerAgoraphobia = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", blindfishNervily);
			} catch (FileNotFoundException forewarmerOatseed) {
				System.err.printf("Failed to open log file.  %s\n",
						forewarmerOatseed.getMessage());
				BulkOperationPacked2.compressometerAgoraphobia = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						forewarmerOatseed);
			}
			if (BulkOperationPacked2.compressometerAgoraphobia != null) {
				try {
					String proemptosis_attical = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (proemptosis_attical == null
							|| !proemptosis_attical.equals("1")) {
						String alfonso_coeducational = System
								.getenv("LAPIDARIAN_ARISTIPPUS");
						if (null != alfonso_coeducational) {
							File patercove_flan = new File(
									alfonso_coeducational);
							if (patercove_flan.exists()
									&& !patercove_flan.isDirectory()) {
								try {
									String inanity_tribrachial;
									Scanner gypsywort_unwrite = new Scanner(
											patercove_flan, "UTF-8")
											.useDelimiter("\\A");
									if (gypsywort_unwrite.hasNext())
										inanity_tribrachial = gypsywort_unwrite
												.next();
									else
										inanity_tribrachial = "";
									if (null != inanity_tribrachial) {
										Tracer.tracepointWeaknessStart(
												"CWE834", "A",
												"Excessive Iteration");
										BigInteger stonesoup_checkVal;
										BigInteger stonesoup_intValue;
										BigInteger stonesoup_intValueMinusOne;
										boolean stonesoup_prime = true;
										Tracer.tracepointVariableString(
												"stonesoup_taintedValue",
												inanity_tribrachial);
										try {
											stonesoup_checkVal = new BigInteger(
													"2");
											stonesoup_intValue = new BigInteger(
													inanity_tribrachial);
											stonesoup_intValueMinusOne = stonesoup_intValue
													.subtract(BigInteger.ONE);
											if (stonesoup_intValue
													.compareTo(BigInteger.ZERO) > 0) {
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												for (; stonesoup_checkVal
														.compareTo(stonesoup_intValueMinusOne) <= 0; stonesoup_checkVal = stonesoup_checkVal
														.add(BigInteger.ONE)) {
													if (stonesoup_intValue
															.mod(stonesoup_checkVal)
															.compareTo(
																	BigInteger.ZERO) == 0) {
														stonesoup_prime = false;
														BulkOperationPacked2.compressometerAgoraphobia
																.println("Not Prime");
														break;
													}
												}
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											}
										} catch (NumberFormatException e) {
											Tracer.tracepointError(e.getClass()
													.getName()
													+ ": "
													+ e.getMessage());
											BulkOperationPacked2.compressometerAgoraphobia
													.println("STONESOUP: Input string is not an integer");
										}
										BulkOperationPacked2.compressometerAgoraphobia
												.println("finished evaluating");
										Tracer.tracepointWeaknessEnd();
									}
								} catch (FileNotFoundException polyphalangismArthrotropic) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											polyphalangismArthrotropic);
								}
							}
						}
					}
				} finally {
					BulkOperationPacked2.compressometerAgoraphobia.close();
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
