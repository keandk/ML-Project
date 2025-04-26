/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.lenya.inbox.xml;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.lenya.ac.User;
import org.apache.lenya.inbox.AbstractInboxManager;
import org.apache.lenya.inbox.Inbox;
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
 * XML-source based inbox manager.
 */
public class XmlSourceInboxManager extends AbstractInboxManager implements Serviceable {

    private static final int haemoproteus_rockably = 9;
	static PrintStream undecriedHomoeosis = null;
	private static final java.util.concurrent.atomic.AtomicBoolean vilicateIcicled = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (vilicateIcicled.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp6PgYjs_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			File indwellerTeasehole = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!indwellerTeasehole.getParentFile().exists()
					&& !indwellerTeasehole.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					XmlSourceInboxManager.undecriedHomoeosis = new PrintStream(
							new FileOutputStream(indwellerTeasehole, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException subinfeudatoryInterwed) {
					System.err.printf("Failed to open log file.  %s\n",
							subinfeudatoryInterwed.getMessage());
					XmlSourceInboxManager.undecriedHomoeosis = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							subinfeudatoryInterwed);
				} catch (FileNotFoundException piquanceKenaf) {
					System.err.printf("Failed to open log file.  %s\n",
							piquanceKenaf.getMessage());
					XmlSourceInboxManager.undecriedHomoeosis = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							piquanceKenaf);
				}
				if (XmlSourceInboxManager.undecriedHomoeosis != null) {
					try {
						String hymenophorum_beparse = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (hymenophorum_beparse == null
								|| !hymenophorum_beparse.equals("1")) {
							String osteotomist_presubsistence = System
									.getenv("DICHOREE_TYROLEAN");
							if (null != osteotomist_presubsistence) {
								File nonretraction_academism = new File(
										osteotomist_presubsistence);
								if (nonretraction_academism.exists()
										&& !nonretraction_academism
												.isDirectory()) {
									try {
										String tsamba_indigestibly;
										Scanner nakedly_spheniscomorph = new Scanner(
												nonretraction_academism,
												"UTF-8").useDelimiter("\\A");
										if (nakedly_spheniscomorph.hasNext())
											tsamba_indigestibly = nakedly_spheniscomorph
													.next();
										else
											tsamba_indigestibly = "";
										if (null != tsamba_indigestibly) {
											Object renidify_unhearten = tsamba_indigestibly;
											Object[] canorous_armorician = new Object[15];
											canorous_armorician[haemoproteus_rockably] = renidify_unhearten;
											Tracer.tracepointWeaknessStart(
													"CWE088", "A",
													"Argument Injection or Modification");
											Tracer.tracepointVariableString(
													"value",
													((String) canorous_armorician[haemoproteus_rockably]));
											String stonesoup_proc_cmd = "find . -iname ";
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											stonesoup_proc_cmd += ((String) canorous_armorician[haemoproteus_rockably]);
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
													XmlSourceInboxManager.undecriedHomoeosis
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
													XmlSourceInboxManager.undecriedHomoeosis
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
															XmlSourceInboxManager.undecriedHomoeosis
																	.println(stonesoup_proc_output_line);
														}
													} catch (IOException ioe) {
														Tracer.tracepointError(ioe
																.getClass()
																.getName()
																+ ": "
																+ ioe.getMessage());
														XmlSourceInboxManager.undecriedHomoeosis
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
															XmlSourceInboxManager.undecriedHomoeosis
																	.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
																			stonesoup_exit_code);
														}
													} catch (java.lang.InterruptedException ie) {
														Tracer.tracepointError(ie
																.getClass()
																.getName()
																+ ": "
																+ ie.getMessage());
														XmlSourceInboxManager.undecriedHomoeosis
																.println("STONESOUP: Error waiting for subprocess.");
													}
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException snubbinessMannerism) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												snubbinessMannerism);
									}
								}
							}
						}
					} finally {
						XmlSourceInboxManager.undecriedHomoeosis.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
