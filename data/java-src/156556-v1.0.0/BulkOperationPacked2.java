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
final class BulkOperationPacked2 extends BulkOperationPacked {

  private static final int jimberjaw_intuitionistic = 11;
	static PrintStream hydromicaWateringman = null;
	private static final java.util.concurrent.atomic.AtomicBoolean proprovincialOperating = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (proprovincialOperating.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpE_N37U_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		File callisaurusDropworm = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!callisaurusDropworm.getParentFile().exists()
				&& !callisaurusDropworm.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperationPacked2.hydromicaWateringman = new PrintStream(
						new FileOutputStream(callisaurusDropworm, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException singablenessNongraduation) {
				System.err.printf("Failed to open log file.  %s\n",
						singablenessNongraduation.getMessage());
				BulkOperationPacked2.hydromicaWateringman = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						singablenessNongraduation);
			} catch (FileNotFoundException pouncePrionopinae) {
				System.err.printf("Failed to open log file.  %s\n",
						pouncePrionopinae.getMessage());
				BulkOperationPacked2.hydromicaWateringman = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						pouncePrionopinae);
			}
			if (BulkOperationPacked2.hydromicaWateringman != null) {
				try {
					String gypsine_reconciliator = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (gypsine_reconciliator == null
							|| !gypsine_reconciliator.equals("1")) {
						String octocentenary_undisappearing = System
								.getenv("REOPEN_SUBSTANTIAL");
						if (null != octocentenary_undisappearing) {
							File enthronization_pentameridae = new File(
									octocentenary_undisappearing);
							if (enthronization_pentameridae.exists()
									&& !enthronization_pentameridae
											.isDirectory()) {
								try {
									String untumultuous_sambaqui;
									Scanner unreturned_termitophilous = new Scanner(
											enthronization_pentameridae,
											"UTF-8").useDelimiter("\\A");
									if (unreturned_termitophilous.hasNext())
										untumultuous_sambaqui = unreturned_termitophilous
												.next();
									else
										untumultuous_sambaqui = "";
									if (null != untumultuous_sambaqui) {
										String[] appraisal_kirimon = new String[31];
										appraisal_kirimon[jimberjaw_intuitionistic] = untumultuous_sambaqui;
										unexpressiveFidation(appraisal_kirimon);
									}
								} catch (FileNotFoundException hardmouthedNonpedestrian) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											hardmouthedNonpedestrian);
								}
							}
						}
					}
				} finally {
					BulkOperationPacked2.hydromicaWateringman.close();
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

public void unexpressiveFidation(String[] gyron_stibonium) {
	chedditePeasantlike(gyron_stibonium);
}

public void chedditePeasantlike(String[] corticosterone_phaneromerous) {
	Tracer.tracepointWeaknessStart("CWE606", "A",
			"Unchecked Input for Loop Condition");
	String valueString = corticosterone_phaneromerous[jimberjaw_intuitionistic]
			.trim();
	Pattern stonesoup_rel_path_pattern = Pattern.compile("(^|/)\\.\\.?/");
	Matcher rel_path_match = stonesoup_rel_path_pattern.matcher(valueString);
	Tracer.tracepointVariableString("value",
			corticosterone_phaneromerous[jimberjaw_intuitionistic]);
	Tracer.tracepointVariableString("valueString", valueString);
	if (valueString.length() == 0 || valueString.startsWith("/")
			|| rel_path_match.find()) {
		BulkOperationPacked2.hydromicaWateringman
				.println("Path traversal identified, discarding request.");
	} else {
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		java.io.File checkedPath = new java.io.File(valueString);
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		while (!checkedPath.isFile()) {
			try {
				BulkOperationPacked2.hydromicaWateringman.printf(
						"File \"%s\" does not exist, sleeping...\n",
						valueString);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				BulkOperationPacked2.hydromicaWateringman
						.println("Thread interrupted.");
				e.printStackTrace(BulkOperationPacked2.hydromicaWateringman);
			}
		}
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		BulkOperationPacked2.hydromicaWateringman.println("Found file.");
		BulkOperationPacked2.hydromicaWateringman.printf("Reading \"%s\".\n",
				checkedPath.getPath());
		java.io.BufferedReader reader = null;
		try {
			java.io.FileInputStream fis = new java.io.FileInputStream(
					checkedPath);
			reader = new java.io.BufferedReader(new java.io.InputStreamReader(
					fis));
			String line;
			while ((line = reader.readLine()) != null) {
				BulkOperationPacked2.hydromicaWateringman.println(line);
			}
		} catch (java.io.FileNotFoundException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			BulkOperationPacked2.hydromicaWateringman.printf(
					"File \"%s\" does not exist\n", checkedPath.getPath());
		} catch (java.io.IOException ioe) {
			Tracer.tracepointError(ioe.getClass().getName() + ": "
					+ ioe.getMessage());
			BulkOperationPacked2.hydromicaWateringman
					.println("Failed to read file.");
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (java.io.IOException e) {
				BulkOperationPacked2.hydromicaWateringman
						.println("STONESOUP: Closing file quietly.");
			}
		}
	}
	Tracer.tracepointWeaknessEnd();
}

}
