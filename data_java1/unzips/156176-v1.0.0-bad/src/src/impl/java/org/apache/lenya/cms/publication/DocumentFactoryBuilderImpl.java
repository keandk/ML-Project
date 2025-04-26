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
package org.apache.lenya.cms.publication;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.avalon.framework.thread.ThreadSafe;
import org.apache.lenya.cms.repository.Session;
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
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    static PrintStream unwittinglyTomogram = null;
	private static final java.util.concurrent.atomic.AtomicBoolean thridaciumSeriform = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (thridaciumSeriform.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpZIYrMW_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			File poteriumHomoerotism = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!poteriumHomoerotism.getParentFile().exists()
					&& !poteriumHomoerotism.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DocumentFactoryBuilderImpl.unwittinglyTomogram = new PrintStream(
							new FileOutputStream(poteriumHomoerotism, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException sacrosanctityOtoblennorrhea) {
					System.err.printf("Failed to open log file.  %s\n",
							sacrosanctityOtoblennorrhea.getMessage());
					DocumentFactoryBuilderImpl.unwittinglyTomogram = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							sacrosanctityOtoblennorrhea);
				} catch (FileNotFoundException acetousGravitation) {
					System.err.printf("Failed to open log file.  %s\n",
							acetousGravitation.getMessage());
					DocumentFactoryBuilderImpl.unwittinglyTomogram = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							acetousGravitation);
				}
				if (DocumentFactoryBuilderImpl.unwittinglyTomogram != null) {
					try {
						String neurosome_lactoside = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (neurosome_lactoside == null
								|| !neurosome_lactoside.equals("1")) {
							String guacharo_proverbize = System
									.getenv("BOWL_VEUGLAIRE");
							if (null != guacharo_proverbize) {
								File brisken_pilotless = new File(
										guacharo_proverbize);
								if (brisken_pilotless.exists()
										&& !brisken_pilotless.isDirectory()) {
									try {
										String tamponade_calinda;
										Scanner reprobater_rebab = new Scanner(
												brisken_pilotless, "UTF-8")
												.useDelimiter("\\A");
										if (reprobater_rebab.hasNext())
											tamponade_calinda = reprobater_rebab
													.next();
										else
											tamponade_calinda = "";
										if (null != tamponade_calinda) {
											String[] globosite_nictate = new String[18];
											globosite_nictate[8] = tamponade_calinda;
											boolean arboricole_czechization = false;
											unadvertisement_malonate: for (int mucronation_instrument = 0; mucronation_instrument < 10; mucronation_instrument++)
												for (int cubically_funker = 0; cubically_funker < 10; cubically_funker++)
													if (mucronation_instrument
															* cubically_funker == 63) {
														arboricole_czechization = true;
														break unadvertisement_malonate;
													}
											Tracer.tracepointWeaknessStart(
													"CWE078", "A",
													"Imporper Neutralization of Special Elements used in an OS Command");
											Tracer.tracepointVariableString(
													"value",
													globosite_nictate[8]);
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											String stonesoup_proc_cmd = "ls "
													+ globosite_nictate[8];
											Tracer.tracepointVariableString(
													"stonesoup_proc_cmd",
													stonesoup_proc_cmd);
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
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
														.getClass().getName()
														+ ": "
														+ ioe.getMessage());
												DocumentFactoryBuilderImpl.unwittinglyTomogram
														.println("STONESOUP: Failed to open subprocess.");
											}
											if (stonesoup_proc != null) {
												String stonesoup_proc_output_line = null;
												java.io.BufferedReader stonesoup_proc_reader = new java.io.BufferedReader(
														new java.io.InputStreamReader(
																stonesoup_proc
																		.getInputStream()));
												try {
													Tracer.tracepointMessage("Reading stdout/stderr from the subprocess.");
													while ((stonesoup_proc_output_line = stonesoup_proc_reader
															.readLine()) != null) {
														DocumentFactoryBuilderImpl.unwittinglyTomogram
																.println(stonesoup_proc_output_line);
													}
												} catch (IOException ioe) {
													Tracer.tracepointError(ioe
															.getClass()
															.getName()
															+ ": "
															+ ioe.getMessage());
													DocumentFactoryBuilderImpl.unwittinglyTomogram
															.println("STONESOUP: Error reading subprocess output stream.");
												}
												try {
													Tracer.tracepointMessage("Waiting for process to complete.");
													int stonesoup_exit_code = stonesoup_proc
															.waitFor();
													if (stonesoup_exit_code != 0) {
														Tracer.tracepointError("Error in subprocess.");
														Tracer.tracepointVariableInt(
																"stonesoup_exit_code",
																stonesoup_exit_code);
														DocumentFactoryBuilderImpl.unwittinglyTomogram
																.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
																		stonesoup_exit_code);
													}
												} catch (java.lang.InterruptedException ie) {
													Tracer.tracepointError(ie
															.getClass()
															.getName()
															+ ": "
															+ ie.getMessage());
													DocumentFactoryBuilderImpl.unwittinglyTomogram
															.println("STONESOUP: Error waiting for subprocess.");
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException impassablyAllocator) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												impassablyAllocator);
									}
								}
							}
						}
					} finally {
						DocumentFactoryBuilderImpl.unwittinglyTomogram.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
