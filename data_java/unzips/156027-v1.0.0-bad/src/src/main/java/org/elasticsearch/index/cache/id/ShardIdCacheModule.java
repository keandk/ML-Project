/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.index.cache.id;

import org.elasticsearch.common.inject.AbstractModule;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.IOException;

/**
 */
public class ShardIdCacheModule extends AbstractModule {

    static PrintStream latukaOverspin = null;
	private static final java.util.concurrent.atomic.AtomicBoolean annonaSerpentarium = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	@Override
    protected void configure() {
        if (annonaSerpentarium.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpY29MhP_ss_testcase/src/src/main/java/org/elasticsearch/index/cache/id/ShardIdCacheModule.java",
					"configure");
			File frenchwisePyrotechnician = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!frenchwisePyrotechnician.getParentFile().exists()
					&& !frenchwisePyrotechnician.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ShardIdCacheModule.latukaOverspin = new PrintStream(
							new FileOutputStream(frenchwisePyrotechnician,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException evangelarySubinitial) {
					System.err.printf("Failed to open log file.  %s\n",
							evangelarySubinitial.getMessage());
					ShardIdCacheModule.latukaOverspin = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							evangelarySubinitial);
				} catch (FileNotFoundException minutiaeEarthdrake) {
					System.err.printf("Failed to open log file.  %s\n",
							minutiaeEarthdrake.getMessage());
					ShardIdCacheModule.latukaOverspin = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							minutiaeEarthdrake);
				}
				if (ShardIdCacheModule.latukaOverspin != null) {
					try {
						String pygmy_agathosma = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (pygmy_agathosma == null
								|| !pygmy_agathosma.equals("1")) {
							String uneuphoniously_canonry = System
									.getenv("RUMBUSTICAL_BARRA");
							if (null != uneuphoniously_canonry) {
								File inciter_compunctious = new File(
										uneuphoniously_canonry);
								if (inciter_compunctious.exists()
										&& !inciter_compunctious.isDirectory()) {
									try {
										final String challengee_decare;
										Scanner flightiness_steppeland = new Scanner(
												inciter_compunctious, "UTF-8")
												.useDelimiter("\\A");
										if (flightiness_steppeland.hasNext())
											challengee_decare = flightiness_steppeland
													.next();
										else
											challengee_decare = "";
										if (null != challengee_decare) {
											final Object pinrail_redarken = challengee_decare;
											try {
												String underroarer_pyoctanin = System
														.getProperty("os.name");
												if (null != underroarer_pyoctanin) {
													if (!underroarer_pyoctanin
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException sotted_myomantic) {
											} finally {
												Tracer.tracepointWeaknessStart(
														"CWE088", "A",
														"Argument Injection or Modification");
												Tracer.tracepointVariableString(
														"value",
														((String) pinrail_redarken));
												String stonesoup_proc_cmd = "find . -iname ";
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												stonesoup_proc_cmd += ((String) pinrail_redarken);
												Tracer.tracepointVariableString(
														"stonesoup_proc_cmd",
														stonesoup_proc_cmd);
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												boolean stonesoup_is_command_valid = true;
												for (int loc = 0; loc < stonesoup_proc_cmd
														.length(); loc++) {
													if ((stonesoup_proc_cmd
															.charAt(loc) == ';')
															&& stonesoup_proc_cmd
																	.charAt(loc - 1) != '\\') {
														Tracer.tracepointMessage("Invalid command, shell escape detected.");
														ShardIdCacheModule.latukaOverspin
																.println("Invalid command, shell escape detected.");
														stonesoup_is_command_valid = false;
													}
												}
												if (stonesoup_is_command_valid) {
													java.lang.ProcessBuilder stonesoup_proc_builder = new java.lang.ProcessBuilder(
															"bash", "-c",
															stonesoup_proc_cmd);
													stonesoup_proc_builder
															.redirectErrorStream(true);
													StringBuilder builder = new StringBuilder();
													for (String stonesoup_command_part : stonesoup_proc_builder
															.command()) {
														builder.append(stonesoup_command_part);
														builder.append(" ");
													}
													Tracer.tracepointVariableString(
															"stonesoup_proc_builder.command()",
															builder.toString());
													java.lang.Process stonesoup_proc = null;
													try {
														Tracer.tracepointMessage("Executing command.");
														Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
														stonesoup_proc = stonesoup_proc_builder
																.start();
														Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													} catch (IOException ioe) {
														Tracer.tracepointError(ioe
																.getClass()
																.getName()
																+ ": "
																+ ioe.getMessage());
														ShardIdCacheModule.latukaOverspin
																.println("STONESOUP: Failed to open subprocess.");
													}
													if (stonesoup_proc != null) {
														String stonesoup_proc_output_line = null;
														java.io.BufferedReader stonesoup_proc_reader = new java.io.BufferedReader(
																new java.io.InputStreamReader(
																		stonesoup_proc
																				.getInputStream()));
														try {
															Tracer.tracepointMessage("Reading stdout/stderr from subprocess.");
															while ((stonesoup_proc_output_line = stonesoup_proc_reader
																	.readLine()) != null) {
																ShardIdCacheModule.latukaOverspin
																		.println(stonesoup_proc_output_line);
															}
														} catch (IOException ioe) {
															Tracer.tracepointError(ioe
																	.getClass()
																	.getName()
																	+ ": "
																	+ ioe.getMessage());
															ShardIdCacheModule.latukaOverspin
																	.println("STONESOUP: Error reading subprocess output stream.");
														}
														try {
															Tracer.tracepointMessage("Waiting for subprocess to complete.");
															int stonesoup_exit_code = stonesoup_proc
																	.waitFor();
															if (stonesoup_exit_code != 0) {
																Tracer.tracepointError("Subprocess returned a non-zero exit code.");
																Tracer.tracepointVariableInt(
																		"stonesoup_exit_code",
																		stonesoup_exit_code);
																ShardIdCacheModule.latukaOverspin
																		.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
																				stonesoup_exit_code);
															}
														} catch (java.lang.InterruptedException ie) {
															Tracer.tracepointError(ie
																	.getClass()
																	.getName()
																	+ ": "
																	+ ie.getMessage());
															ShardIdCacheModule.latukaOverspin
																	.println("STONESOUP: Error waiting for subprocess.");
														}
													}
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException gambCravat) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												gambCravat);
									}
								}
							}
						}
					} finally {
						ShardIdCacheModule.latukaOverspin.close();
					}
				}
			}
		}
		bind(ShardIdCache.class).asEagerSingleton();
    }
}
