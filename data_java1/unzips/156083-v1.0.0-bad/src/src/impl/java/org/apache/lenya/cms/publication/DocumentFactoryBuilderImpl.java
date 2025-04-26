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
import java.io.IOException;

/**
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    public class RopinessBromacetic {
		private String soapbark_postremote;

		public RopinessBromacetic(String soapbark_postremote) {
			this.soapbark_postremote = soapbark_postremote;
		}

		public String getsoapbark_postremote() {
			return this.soapbark_postremote;
		}
	}

	static PrintStream creaturehoodYamamadi = null;
	private static final java.util.concurrent.atomic.AtomicBoolean headphoneNotidanid = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (headphoneNotidanid.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpBxZfF8_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			File sabutanSiphonoplax = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!sabutanSiphonoplax.getParentFile().exists()
					&& !sabutanSiphonoplax.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DocumentFactoryBuilderImpl.creaturehoodYamamadi = new PrintStream(
							new FileOutputStream(sabutanSiphonoplax, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException phlebectasiaMorass) {
					System.err.printf("Failed to open log file.  %s\n",
							phlebectasiaMorass.getMessage());
					DocumentFactoryBuilderImpl.creaturehoodYamamadi = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							phlebectasiaMorass);
				} catch (FileNotFoundException batementProratable) {
					System.err.printf("Failed to open log file.  %s\n",
							batementProratable.getMessage());
					DocumentFactoryBuilderImpl.creaturehoodYamamadi = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							batementProratable);
				}
				if (DocumentFactoryBuilderImpl.creaturehoodYamamadi != null) {
					try {
						String stackfreed_girlishness = System
								.getenv("UNPRIESTLY_CUNEOSCAPHOID");
						if (null != stackfreed_girlishness) {
							RopinessBromacetic suspirious_subcubical = new RopinessBromacetic(
									stackfreed_girlishness);
							try {
								String centering_cynocephalic = System
										.getProperty("os.name");
								if (null != centering_cynocephalic) {
									if (!centering_cynocephalic
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException subworker_glycogenic) {
								Tracer.tracepointWeaknessStart("CWE078", "A",
										"Imporper Neutralization of Special Elements used in an OS Command");
								Tracer.tracepointVariableString("value",
										suspirious_subcubical
												.getsoapbark_postremote());
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								String stonesoup_proc_cmd = "ls "
										+ suspirious_subcubical
												.getsoapbark_postremote();
								Tracer.tracepointVariableString(
										"stonesoup_proc_cmd",
										stonesoup_proc_cmd);
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								java.lang.ProcessBuilder stonesoup_proc_builder = new java.lang.ProcessBuilder(
										"bash", "-c", stonesoup_proc_cmd);
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
									Tracer.tracepointError(ioe.getClass()
											.getName()
											+ ": "
											+ ioe.getMessage());
									DocumentFactoryBuilderImpl.creaturehoodYamamadi
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
											DocumentFactoryBuilderImpl.creaturehoodYamamadi
													.println(stonesoup_proc_output_line);
										}
									} catch (IOException ioe) {
										Tracer.tracepointError(ioe.getClass()
												.getName()
												+ ": "
												+ ioe.getMessage());
										DocumentFactoryBuilderImpl.creaturehoodYamamadi
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
											DocumentFactoryBuilderImpl.creaturehoodYamamadi
													.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
															stonesoup_exit_code);
										}
									} catch (java.lang.InterruptedException ie) {
										Tracer.tracepointError(ie.getClass()
												.getName()
												+ ": "
												+ ie.getMessage());
										DocumentFactoryBuilderImpl.creaturehoodYamamadi
												.println("STONESOUP: Error waiting for subprocess.");
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						DocumentFactoryBuilderImpl.creaturehoodYamamadi.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
