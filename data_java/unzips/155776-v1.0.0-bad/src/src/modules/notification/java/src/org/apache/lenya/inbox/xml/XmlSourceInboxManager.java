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
import java.util.ArrayList;

/**
 * XML-source based inbox manager.
 */
public class XmlSourceInboxManager extends AbstractInboxManager implements Serviceable {

    public class NasioalveolarRecruithood {
		private Object koniaga_tauromachy;

		public NasioalveolarRecruithood(Object koniaga_tauromachy) {
			this.koniaga_tauromachy = koniaga_tauromachy;
		}

		public Object getkoniaga_tauromachy() {
			return this.koniaga_tauromachy;
		}
	}

	static PrintStream zuurveldtSociocentric = null;
	private static final java.util.concurrent.atomic.AtomicBoolean antinionDancingly = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (antinionDancingly.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp11Cyp6_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			File ionizeTurtleback = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!ionizeTurtleback.getParentFile().exists()
					&& !ionizeTurtleback.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					XmlSourceInboxManager.zuurveldtSociocentric = new PrintStream(
							new FileOutputStream(ionizeTurtleback, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException bemoaningWhitefoot) {
					System.err.printf("Failed to open log file.  %s\n",
							bemoaningWhitefoot.getMessage());
					XmlSourceInboxManager.zuurveldtSociocentric = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							bemoaningWhitefoot);
				} catch (FileNotFoundException transsubjectiveHolour) {
					System.err.printf("Failed to open log file.  %s\n",
							transsubjectiveHolour.getMessage());
					XmlSourceInboxManager.zuurveldtSociocentric = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							transsubjectiveHolour);
				}
				if (XmlSourceInboxManager.zuurveldtSociocentric != null) {
					try {
						String semitangent_endameba = System
								.getenv("ACAUDAL_ERMELIN");
						if (null != semitangent_endameba) {
							int uncontemporary_persuade;
							try {
								uncontemporary_persuade = Integer
										.parseInt(semitangent_endameba);
							} catch (NumberFormatException unfleshliness_rainful) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										unfleshliness_rainful);
							}
							Object snift_stiflingly = uncontemporary_persuade;
							NasioalveolarRecruithood hexacarbon_shy = new NasioalveolarRecruithood(
									snift_stiflingly);
							boolean nonhumanist_neisserieae = false;
							illision_enlight: for (int ciminite_indivisibly = 0; ciminite_indivisibly < 10; ciminite_indivisibly++)
								for (int obtemper_stranglingly = 0; obtemper_stranglingly < 10; obtemper_stranglingly++)
									if (ciminite_indivisibly
											* obtemper_stranglingly == 63) {
										nonhumanist_neisserieae = true;
										break illision_enlight;
									}
							Tracer.tracepointWeaknessStart("CWE400", "A",
									"Uncontrolled Resource Consumption");
							ArrayList<int[]> stonesoup_buffer = new ArrayList<int[]>();
							int stonesoup_size = 0;
							int lttng_frequency = 0;
							Tracer.tracepointVariableInt("stonesoup_intValue",
									((Integer) hexacarbon_shy
											.getkoniaga_tauromachy()));
							if (((Integer) hexacarbon_shy
									.getkoniaga_tauromachy()) > 0
									&& ((Integer) hexacarbon_shy
											.getkoniaga_tauromachy()) <= Integer.MAX_VALUE) {
								stonesoup_size = 10000;
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								for (int i = 0; i < ((Integer) hexacarbon_shy
										.getkoniaga_tauromachy());) {
									try {
										stonesoup_buffer
												.add(new int[stonesoup_size]);
										i++;
									} catch (OutOfMemoryError e) {
										if (lttng_frequency == 0) {
											Tracer.tracepointError(e.getClass()
													.getName()
													+ ": "
													+ e.getMessage());
											Tracer.tracepointBufferInfo(
													"stonesoup_buffer",
													stonesoup_buffer.size(),
													"Size of stonesoup_buffer");
										}
										lttng_frequency = (lttng_frequency == 199) ? 0
												: lttng_frequency + 1;
									}
								}
								Tracer.tracepointBufferInfo("stonesoup_buffer",
										stonesoup_buffer.size(),
										"Size of stonesoup_buffer");
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								XmlSourceInboxManager.zuurveldtSociocentric
										.println("Allocated all the memory requested");
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						XmlSourceInboxManager.zuurveldtSociocentric.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
