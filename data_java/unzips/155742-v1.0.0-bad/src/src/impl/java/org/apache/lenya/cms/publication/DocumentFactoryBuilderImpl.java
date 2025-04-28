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
import java.util.ArrayList;

/**
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    private static final int daltonism_sanctionment = 4;
	static PrintStream counterdikeLevi = null;
	private static final java.util.concurrent.atomic.AtomicBoolean artemisionMistrustingly = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (artemisionMistrustingly.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpChKebf_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			File fumetUncinctured = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!fumetUncinctured.getParentFile().exists()
					&& !fumetUncinctured.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DocumentFactoryBuilderImpl.counterdikeLevi = new PrintStream(
							new FileOutputStream(fumetUncinctured, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException thaAngetenar) {
					System.err.printf("Failed to open log file.  %s\n",
							thaAngetenar.getMessage());
					DocumentFactoryBuilderImpl.counterdikeLevi = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", thaAngetenar);
				} catch (FileNotFoundException spuddyPentadactyloid) {
					System.err.printf("Failed to open log file.  %s\n",
							spuddyPentadactyloid.getMessage());
					DocumentFactoryBuilderImpl.counterdikeLevi = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							spuddyPentadactyloid);
				}
				if (DocumentFactoryBuilderImpl.counterdikeLevi != null) {
					try {
						String laboredness_unpatrician = System
								.getenv("MAUMEE_FORMER");
						if (null != laboredness_unpatrician) {
							int goaf_teeming;
							try {
								goaf_teeming = Integer
										.parseInt(laboredness_unpatrician);
							} catch (NumberFormatException contemplation_underrented) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										contemplation_underrented);
							}
							int[] epitomizer_aciliate = new int[27];
							epitomizer_aciliate[daltonism_sanctionment] = goaf_teeming;
							Tracer.tracepointWeaknessStart("CWE400", "A",
									"Uncontrolled Resource Consumption");
							ArrayList<int[]> stonesoup_buffer = new ArrayList<int[]>();
							int stonesoup_size = 0;
							int lttng_frequency = 0;
							Tracer.tracepointVariableInt("stonesoup_intValue",
									epitomizer_aciliate[daltonism_sanctionment]);
							if (epitomizer_aciliate[daltonism_sanctionment] > 0
									&& epitomizer_aciliate[daltonism_sanctionment] <= Integer.MAX_VALUE) {
								stonesoup_size = 10000;
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								for (int i = 0; i < epitomizer_aciliate[daltonism_sanctionment];) {
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
								DocumentFactoryBuilderImpl.counterdikeLevi
										.println("Allocated all the memory requested");
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						DocumentFactoryBuilderImpl.counterdikeLevi.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
