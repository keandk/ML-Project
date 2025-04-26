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

    public class UnfrugalnessAsale<T> {
		private T bribery_picnicker;

		public UnfrugalnessAsale(T bribery_picnicker) {
			this.bribery_picnicker = bribery_picnicker;
		}

		public T getbribery_picnicker() {
			return this.bribery_picnicker;
		}
	}

	static PrintStream minionlyWestfalite = null;
	private static final java.util.concurrent.atomic.AtomicBoolean untraceriedWittal = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (untraceriedWittal.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpCPQ27K_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			File environsBrushman = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!environsBrushman.getParentFile().exists()
					&& !environsBrushman.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DocumentFactoryBuilderImpl.minionlyWestfalite = new PrintStream(
							new FileOutputStream(environsBrushman, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException intrafoliaceousShanny) {
					System.err.printf("Failed to open log file.  %s\n",
							intrafoliaceousShanny.getMessage());
					DocumentFactoryBuilderImpl.minionlyWestfalite = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							intrafoliaceousShanny);
				} catch (FileNotFoundException pictCyphonism) {
					System.err.printf("Failed to open log file.  %s\n",
							pictCyphonism.getMessage());
					DocumentFactoryBuilderImpl.minionlyWestfalite = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							pictCyphonism);
				}
				if (DocumentFactoryBuilderImpl.minionlyWestfalite != null) {
					try {
						String tetrapharmacal_lateritic = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (tetrapharmacal_lateritic == null
								|| !tetrapharmacal_lateritic.equals("1")) {
							String nagmaal_tragicness = System
									.getenv("EXCLUSIVITY_REMOVER");
							if (null != nagmaal_tragicness) {
								File cerophilous_footmanry = new File(
										nagmaal_tragicness);
								if (cerophilous_footmanry.exists()
										&& !cerophilous_footmanry.isDirectory()) {
									try {
										String situs_patrico;
										Scanner unface_towhee = new Scanner(
												cerophilous_footmanry, "UTF-8")
												.useDelimiter("\\A");
										if (unface_towhee.hasNext())
											situs_patrico = unface_towhee
													.next();
										else
											situs_patrico = "";
										if (null != situs_patrico) {
											Object chimer_narcoticalness = situs_patrico;
											UnfrugalnessAsale<Object> prince_aterian = new UnfrugalnessAsale<Object>(
													chimer_narcoticalness);
											shatterwitCompanator(prince_aterian);
										}
									} catch (FileNotFoundException nominativeBilly) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												nominativeBilly);
									}
								}
							}
						}
					} finally {
						DocumentFactoryBuilderImpl.minionlyWestfalite.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public void shatterwitCompanator(UnfrugalnessAsale<Object> arrogator_malpighiaceous){Tracer.tracepointWeaknessStart("CWE078","A","Imporper Neutralization of Special Elements used in an OS Command");Tracer.tracepointVariableString("value",((String)arrogator_malpighiaceous.getbribery_picnicker()));Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");String stonesoup_proc_cmd="ls " + ((String)arrogator_malpighiaceous.getbribery_picnicker());Tracer.tracepointVariableString("stonesoup_proc_cmd",stonesoup_proc_cmd);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");java.lang.ProcessBuilder stonesoup_proc_builder=new java.lang.ProcessBuilder("bash","-c",stonesoup_proc_cmd);stonesoup_proc_builder.redirectErrorStream(true);StringBuilder builder=new StringBuilder();for (String stonesoup_command_part:stonesoup_proc_builder.command()){builder.append(stonesoup_command_part);builder.append(" ");}Tracer.tracepointVariableString("stonesoup_proc_builder.command()",builder.toString());java.lang.Process stonesoup_proc=null;try {Tracer.tracepointMessage("Executing command.");Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");stonesoup_proc=stonesoup_proc_builder.start();Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());DocumentFactoryBuilderImpl.minionlyWestfalite.println("STONESOUP: Failed to open subprocess.");}if (stonesoup_proc != null){String stonesoup_proc_output_line=null;java.io.BufferedReader stonesoup_proc_reader=new java.io.BufferedReader(new java.io.InputStreamReader(stonesoup_proc.getInputStream()));try {Tracer.tracepointMessage("Reading stdout/stderr from the subprocess.");while ((stonesoup_proc_output_line=stonesoup_proc_reader.readLine()) != null){DocumentFactoryBuilderImpl.minionlyWestfalite.println(stonesoup_proc_output_line);}} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());DocumentFactoryBuilderImpl.minionlyWestfalite.println("STONESOUP: Error reading subprocess output stream.");}try {Tracer.tracepointMessage("Waiting for process to complete.");int stonesoup_exit_code=stonesoup_proc.waitFor();if (stonesoup_exit_code != 0){Tracer.tracepointError("Error in subprocess.");Tracer.tracepointVariableInt("stonesoup_exit_code",stonesoup_exit_code);DocumentFactoryBuilderImpl.minionlyWestfalite.printf("STONESOUP: Error in subprocess.	Return code: %d\n",stonesoup_exit_code);}} catch (java.lang.InterruptedException ie){Tracer.tracepointError(ie.getClass().getName() + ": "+ie.getMessage());DocumentFactoryBuilderImpl.minionlyWestfalite.println("STONESOUP: Error waiting for subprocess.");}}Tracer.tracepointWeaknessEnd();}

}
