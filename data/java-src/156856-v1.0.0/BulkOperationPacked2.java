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

  public class SambaquiEskualdun {
		private int[] chronical_mommy;

		public SambaquiEskualdun(int[] chronical_mommy) {
			this.chronical_mommy = chronical_mommy;
		}

		public int[] getchronical_mommy() {
			return this.chronical_mommy;
		}
	}

	public void semivertebralEsterify(int fanfare_corpulency,
			SambaquiEskualdun composedness_suprajural) {
		if (fanfare_corpulency > 10) {
			semivertebralEsterify(fanfare_corpulency++, composedness_suprajural);
		}
		Tracer.tracepointWeaknessStart("CWE606", "B",
				"Uncheck Input for Loop Condition");
		char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
				.toCharArray();
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			BulkOperationPacked2.craniometricReunionistic
					.println("STONESOUP: Random generator algorithm does not exist.");
		}
		Tracer.tracepointVariableInt("value",
				composedness_suprajural.getchronical_mommy()[12]);
		if (random != null) {
			StringBuilder stonesoup_filename = new StringBuilder();
			BulkOperationPacked2.craniometricReunionistic
					.println("Generating file name");
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			for (int stonesoup_counter = 0; stonesoup_counter < composedness_suprajural
					.getchronical_mommy()[12]; stonesoup_counter++) {
				stonesoup_filename.append(stonesoup_random_charset[random
						.nextInt(stonesoup_random_charset.length)]);
			}
			Tracer.tracepointVariableString("stonesoup_filename",
					stonesoup_filename.toString());
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			if (stonesoup_filename.length() > 0) {
				File writePath = new File(stonesoup_filename.toString());
				try {
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					writePath.createNewFile();
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				} catch (IOException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					BulkOperationPacked2.craniometricReunionistic
							.println("Failed to create file.");
					BulkOperationPacked2.craniometricReunionistic
							.println("Error:");
					e.printStackTrace(BulkOperationPacked2.craniometricReunionistic);
					throw new RuntimeException("Unknown error in filename.", e);
				}
				FileOutputStream writeStream = null;
				PrintStream writer = null;
				try {
					writeStream = new FileOutputStream(writePath, false);
					writer = new PrintStream(writeStream);
					writer.println("/* This is my file */");
				} catch (FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					BulkOperationPacked2.craniometricReunionistic
							.println("Failed to create file.");
					e.printStackTrace(BulkOperationPacked2.craniometricReunionistic);
				} finally {
					if (writer != null) {
						writer.close();
					}
				}
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream craniometricReunionistic = null;
	private static final java.util.concurrent.atomic.AtomicBoolean breastmarkEuchite = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (breastmarkEuchite.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpPfQBBO_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		File invectorCoccidiomorpha = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!invectorCoccidiomorpha.getParentFile().exists()
				&& !invectorCoccidiomorpha.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperationPacked2.craniometricReunionistic = new PrintStream(
						new FileOutputStream(invectorCoccidiomorpha, false),
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException uncommutableGunyeh) {
				System.err.printf("Failed to open log file.  %s\n",
						uncommutableGunyeh.getMessage());
				BulkOperationPacked2.craniometricReunionistic = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						uncommutableGunyeh);
			} catch (FileNotFoundException urosomiticLoquently) {
				System.err.printf("Failed to open log file.  %s\n",
						urosomiticLoquently.getMessage());
				BulkOperationPacked2.craniometricReunionistic = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						urosomiticLoquently);
			}
			if (BulkOperationPacked2.craniometricReunionistic != null) {
				try {
					String catfish_linotyper = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (catfish_linotyper == null
							|| !catfish_linotyper.equals("1")) {
						String saronic_tactinvariant = System
								.getenv("UNIMPRECATED_HOBBLE");
						if (null != saronic_tactinvariant) {
							File thionurate_allemande = new File(
									saronic_tactinvariant);
							if (thionurate_allemande.exists()
									&& !thionurate_allemande.isDirectory()) {
								try {
									String clunist_nullipara;
									Scanner voracious_gifola = new Scanner(
											thionurate_allemande, "UTF-8")
											.useDelimiter("\\A");
									if (voracious_gifola.hasNext())
										clunist_nullipara = voracious_gifola
												.next();
									else
										clunist_nullipara = "";
									if (null != clunist_nullipara) {
										int bluejack_absvolt;
										try {
											bluejack_absvolt = Integer
													.parseInt(clunist_nullipara);
										} catch (NumberFormatException pharmacal_bankruptly) {
											throw new RuntimeException(
													"STONESOUP: Failed to convert source taint.",
													pharmacal_bankruptly);
										}
										int[] unforfeited_maundy = new int[20];
										unforfeited_maundy[12] = bluejack_absvolt;
										SambaquiEskualdun foveolet_liposis = new SambaquiEskualdun(
												unforfeited_maundy);
										int fungia_chebulinic = 0;
										semivertebralEsterify(
												fungia_chebulinic,
												foveolet_liposis);
									}
								} catch (FileNotFoundException lugerPotpie) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											lugerPotpie);
								}
							}
						}
					}
				} finally {
					BulkOperationPacked2.craniometricReunionistic.close();
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
