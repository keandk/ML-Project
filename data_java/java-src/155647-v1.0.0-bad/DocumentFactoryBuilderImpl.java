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
import java.util.ArrayList;

/**
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    static PrintStream puparialGroundberry = null;
	private static final java.util.concurrent.atomic.AtomicBoolean transversaryLall = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (transversaryLall.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpORiDiZ_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			File floatsmanDelegative = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!floatsmanDelegative.getParentFile().exists()
					&& !floatsmanDelegative.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DocumentFactoryBuilderImpl.puparialGroundberry = new PrintStream(
							new FileOutputStream(floatsmanDelegative, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException gynandrismReheboth) {
					System.err.printf("Failed to open log file.  %s\n",
							gynandrismReheboth.getMessage());
					DocumentFactoryBuilderImpl.puparialGroundberry = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							gynandrismReheboth);
				} catch (FileNotFoundException pyrographerInterdorsal) {
					System.err.printf("Failed to open log file.  %s\n",
							pyrographerInterdorsal.getMessage());
					DocumentFactoryBuilderImpl.puparialGroundberry = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							pyrographerInterdorsal);
				}
				if (DocumentFactoryBuilderImpl.puparialGroundberry != null) {
					try {
						String bunty_nondictionary = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (bunty_nondictionary == null
								|| !bunty_nondictionary.equals("1")) {
							String unquietly_undraperied = System
									.getenv("UNDERPROPPING_ABATUA");
							if (null != unquietly_undraperied) {
								File orangeroot_rubbed = new File(
										unquietly_undraperied);
								if (orangeroot_rubbed.exists()
										&& !orangeroot_rubbed.isDirectory()) {
									try {
										String ascariasis_pleurotomoid;
										Scanner canidia_manger = new Scanner(
												orangeroot_rubbed, "UTF-8")
												.useDelimiter("\\A");
										if (canidia_manger.hasNext())
											ascariasis_pleurotomoid = canidia_manger
													.next();
										else
											ascariasis_pleurotomoid = "";
										if (null != ascariasis_pleurotomoid) {
											int detect_obmutescence;
											try {
												detect_obmutescence = Integer
														.parseInt(ascariasis_pleurotomoid);
											} catch (NumberFormatException phosphocarnic_abiotrophy) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														phosphocarnic_abiotrophy);
											}
											Object allocrotonic_cabocle = detect_obmutescence;
											try {
												String ramadoss_consistory = System
														.getProperty("os.name");
												if (null != ramadoss_consistory) {
													if (!ramadoss_consistory
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException aftercooler_reneg) {
											} finally {
												stonesoup_sources = new ArrayList<FileOutputStream>();
												Tracer.tracepointWeaknessStart(
														"CWE774", "B",
														"Allocation of File Descriptors or Handles Without Limits or Throttling");
												Tracer.tracepointBufferInfo(
														"stonesoup_sources",
														stonesoup_sources
																.size(),
														"Length of stonesoup_sources");
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												Tracer.tracepointMessage("TRIGGER-POINT; BEFORE");
												for (int stonesoup_counter = 0; stonesoup_counter < ((Integer) allocrotonic_cabocle); stonesoup_counter++) {
													try {
														stonesoup_sources
																.add(new FileOutputStream(
																		String.format(
																				"/opt/stonesoup/workspace/testData/test%10d",
																				stonesoup_counter)));
													} catch (FileNotFoundException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														DocumentFactoryBuilderImpl.puparialGroundberry
																.println("Failed to create new file, moving on.");
													}
													DocumentFactoryBuilderImpl.puparialGroundberry
															.println(stonesoup_counter);
												}
												Tracer.tracepointBufferInfo(
														"stonesoup_sources",
														stonesoup_sources
																.size(),
														"Length of stonesoup_sources");
												Tracer.tracepointMessage("TRIGGER-POINT; AFTER");
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException idolismSarothamnus) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												idolismSarothamnus);
									}
								}
							}
						}
					} finally {
						DocumentFactoryBuilderImpl.puparialGroundberry.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public static ArrayList<FileOutputStream> stonesoup_sources = null;

}
