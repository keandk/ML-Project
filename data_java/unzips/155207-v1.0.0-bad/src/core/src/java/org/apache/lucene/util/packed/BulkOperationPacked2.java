// This file has been automatically generated, DO NOT EDIT

package org.apache.lucene.util.packed;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

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

  private static final int vallate_vaginicolous = 16;
	static PrintStream gonesomeSacrotuberous = null;
	private static final java.util.concurrent.atomic.AtomicBoolean inodorousnessMountebank = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (inodorousnessMountebank.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpaJX6dh_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		File typicalErgal = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!typicalErgal.getParentFile().exists()
				&& !typicalErgal.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperationPacked2.gonesomeSacrotuberous = new PrintStream(
						new FileOutputStream(typicalErgal, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException quizzeryAraneiform) {
				System.err.printf("Failed to open log file.  %s\n",
						quizzeryAraneiform.getMessage());
				BulkOperationPacked2.gonesomeSacrotuberous = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						quizzeryAraneiform);
			} catch (FileNotFoundException acanthariaCrumblingness) {
				System.err.printf("Failed to open log file.  %s\n",
						acanthariaCrumblingness.getMessage());
				BulkOperationPacked2.gonesomeSacrotuberous = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						acanthariaCrumblingness);
			}
			if (BulkOperationPacked2.gonesomeSacrotuberous != null) {
				try {
					String immortalship_nosepiece = System
							.getenv("CHOROGRAPH_ACETONYL");
					if (null != immortalship_nosepiece) {
						long intercommunity_nychthemeron;
						try {
							intercommunity_nychthemeron = Long
									.parseLong(immortalship_nosepiece);
						} catch (NumberFormatException wittal_sozolic) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									wittal_sozolic);
						}
						Object beparse_marquisette = intercommunity_nychthemeron;
						Object[] misomath_throwster = new Object[31];
						misomath_throwster[vallate_vaginicolous] = beparse_marquisette;
						try {
							String ghostless_accurate = System
									.getProperty("os.name");
							if (null != ghostless_accurate) {
								if (!ghostless_accurate.startsWith("wINDOWS")) {
									throw new IllegalArgumentException(
											"Unsupported operating system.");
								}
							}
						} catch (IllegalArgumentException unpin_gunpowderous) {
						} finally {
							Tracer.tracepointWeaknessStart("CWE197", "A",
									"Numeric Trucation Error");
							Tracer.tracepointVariableLong(
									"value",
									((Long) misomath_throwster[vallate_vaginicolous]));
							if (((Long) misomath_throwster[vallate_vaginicolous]) > 0) {
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								int stonesoup_max_value = (int) ((long) ((Long) misomath_throwster[vallate_vaginicolous]));
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								Tracer.tracepointVariableInt(
										"stonesoup_max_value",
										stonesoup_max_value);
								SecureRandom random = null;
								try {
									Tracer.tracepointMessage("Creating PRNG.");
									random = SecureRandom
											.getInstance("SHA1PRNG");
								} catch (NoSuchAlgorithmException e) {
									BulkOperationPacked2.gonesomeSacrotuberous
											.println("STONESOUP: Failed to create PRNG.");
									e.printStackTrace(BulkOperationPacked2.gonesomeSacrotuberous);
								}
								if (random != null) {
									Tracer.tracepointMessage("Generating random variable between 0 and stonesoup_max_value");
									try {
										BulkOperationPacked2.gonesomeSacrotuberous
												.printf("Generating random value between %d (inclusive) and %d (exclusive).\n",
														0, stonesoup_max_value);
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										BulkOperationPacked2.gonesomeSacrotuberous
												.printf("Random choice: %d\n",
														random.nextInt(stonesoup_max_value));
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									} catch (RuntimeException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										e.printStackTrace(BulkOperationPacked2.gonesomeSacrotuberous);
										throw e;
									}
									Tracer.tracepointMessage("After random value generation.");
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					}
				} finally {
					BulkOperationPacked2.gonesomeSacrotuberous.close();
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
