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
final class BulkOperationPacked2 extends BulkOperationPacked {

  static PrintStream disedgeWeekender = null;

	public void nauseatinglyDitrocha(int philatelic_linguatula,final Object unhumbled_hoteldom){if (philatelic_linguatula > 10){nauseatinglyDitrocha(philatelic_linguatula++,unhumbled_hoteldom);}Tracer.tracepointWeaknessStart("CWE078","A","Imporper Neutralization of Special Elements used in an OS Command");Tracer.tracepointVariableString("value",((String)unhumbled_hoteldom));Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");String stonesoup_proc_cmd="ls " + ((String)unhumbled_hoteldom);Tracer.tracepointVariableString("stonesoup_proc_cmd",stonesoup_proc_cmd);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");java.lang.ProcessBuilder stonesoup_proc_builder=new java.lang.ProcessBuilder("bash","-c",stonesoup_proc_cmd);stonesoup_proc_builder.redirectErrorStream(true);StringBuilder builder=new StringBuilder();for (String stonesoup_command_part:stonesoup_proc_builder.command()){builder.append(stonesoup_command_part);builder.append(" ");}Tracer.tracepointVariableString("stonesoup_proc_builder.command()",builder.toString());java.lang.Process stonesoup_proc=null;try {Tracer.tracepointMessage("Executing command.");Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");stonesoup_proc=stonesoup_proc_builder.start();Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());BulkOperationPacked2.disedgeWeekender.println("STONESOUP: Failed to open subprocess.");}if (stonesoup_proc != null){String stonesoup_proc_output_line=null;java.io.BufferedReader stonesoup_proc_reader=new java.io.BufferedReader(new java.io.InputStreamReader(stonesoup_proc.getInputStream()));try {Tracer.tracepointMessage("Reading stdout/stderr from the subprocess.");while ((stonesoup_proc_output_line=stonesoup_proc_reader.readLine()) != null){BulkOperationPacked2.disedgeWeekender.println(stonesoup_proc_output_line);}} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());BulkOperationPacked2.disedgeWeekender.println("STONESOUP: Error reading subprocess output stream.");}try {Tracer.tracepointMessage("Waiting for process to complete.");int stonesoup_exit_code=stonesoup_proc.waitFor();if (stonesoup_exit_code != 0){Tracer.tracepointError("Error in subprocess.");Tracer.tracepointVariableInt("stonesoup_exit_code",stonesoup_exit_code);BulkOperationPacked2.disedgeWeekender.printf("STONESOUP: Error in subprocess.	Return code: %d\n",stonesoup_exit_code);}} catch (java.lang.InterruptedException ie){Tracer.tracepointError(ie.getClass().getName() + ": "+ie.getMessage());BulkOperationPacked2.disedgeWeekender.println("STONESOUP: Error waiting for subprocess.");}}Tracer.tracepointWeaknessEnd();}

	private static final java.util.concurrent.atomic.AtomicBoolean formicariumAmbicolorate = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (formicariumAmbicolorate.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpSyyRS8_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		File titreTachometer = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!titreTachometer.getParentFile().exists()
				&& !titreTachometer.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperationPacked2.disedgeWeekender = new PrintStream(
						new FileOutputStream(titreTachometer, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException aflauntIchnographical) {
				System.err.printf("Failed to open log file.  %s\n",
						aflauntIchnographical.getMessage());
				BulkOperationPacked2.disedgeWeekender = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						aflauntIchnographical);
			} catch (FileNotFoundException fenksChrysaloid) {
				System.err.printf("Failed to open log file.  %s\n",
						fenksChrysaloid.getMessage());
				BulkOperationPacked2.disedgeWeekender = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", fenksChrysaloid);
			}
			if (BulkOperationPacked2.disedgeWeekender != null) {
				try {
					String phylactery_intricateness = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (phylactery_intricateness == null
							|| !phylactery_intricateness.equals("1")) {
						String macrocentrinae_disamis = System
								.getenv("HUMHUM_PRIMITIAS");
						if (null != macrocentrinae_disamis) {
							File sparm_epithem = new File(
									macrocentrinae_disamis);
							if (sparm_epithem.exists()
									&& !sparm_epithem.isDirectory()) {
								try {
									final String bedeaf_aigialosauridae;
									Scanner calotypist_confidentiality = new Scanner(
											sparm_epithem, "UTF-8")
											.useDelimiter("\\A");
									if (calotypist_confidentiality.hasNext())
										bedeaf_aigialosauridae = calotypist_confidentiality
												.next();
									else
										bedeaf_aigialosauridae = "";
									if (null != bedeaf_aigialosauridae) {
										final Object estimate_timote = bedeaf_aigialosauridae;
										int chrysotile_jager = 0;
										nauseatinglyDitrocha(chrysotile_jager,
												estimate_timote);
									}
								} catch (FileNotFoundException cancrivorousZapupe) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											cancrivorousZapupe);
								}
							}
						}
					}
				} finally {
					BulkOperationPacked2.disedgeWeekender.close();
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
