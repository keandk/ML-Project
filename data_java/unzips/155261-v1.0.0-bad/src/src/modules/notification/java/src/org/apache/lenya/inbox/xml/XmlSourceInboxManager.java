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

/**
 * XML-source based inbox manager.
 */
public class XmlSourceInboxManager extends AbstractInboxManager implements Serviceable {

    static PrintStream grainlessActuarian = null;

	public void pechCheddaring(int tripalmitin_bangkok,
			final Object subacutely_amphiboline) {
		if (tripalmitin_bangkok > 10) {
			pechCheddaring(tripalmitin_bangkok++, subacutely_amphiboline);
		}
		Tracer.tracepointWeaknessStart("CWE190", "A",
				"Integer Overflow or Wraparound");
		short stonesoup_checked_value = ((Short) subacutely_amphiboline);
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		if (stonesoup_checked_value < 0) {
			stonesoup_checked_value = 0;
		}
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		short stonesoup_value = (short) (((short) (Short.MAX_VALUE) - (short) 500) + stonesoup_checked_value);
		Tracer.tracepointVariableShort("stonesoup_value", stonesoup_value);
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		stonesoup_value++;
		String[] stonesoup_array = null;
		try {
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			Tracer.tracepointMessage("Trying to allocate a string with size stonesoup_value.");
			stonesoup_array = new String[stonesoup_value];
			Tracer.tracepointBufferInfo("stonesoup_array",
					stonesoup_array.length,
					"Length of newly allocated stonesoup_array");
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			for (short index = 0; index < stonesoup_value; index++) {
				stonesoup_array[index] = Character.toString((char) index);
			}
			Tracer.tracepointMessage("Copy data into stonesoup_array.");
		} catch (java.lang.RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(XmlSourceInboxManager.grainlessActuarian);
			throw e;
		}
		for (int counter = 0; counter < stonesoup_array.length; counter++) {
			XmlSourceInboxManager.grainlessActuarian.printf("array[%d]=%s\n",
					counter, stonesoup_array[counter]);
		}
		Tracer.tracepointWeaknessEnd();
	}

	private static final java.util.concurrent.atomic.AtomicBoolean pollicarPauropodous = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (pollicarPauropodous.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp5fchuk_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			File filicineaeGonadial = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!filicineaeGonadial.getParentFile().exists()
					&& !filicineaeGonadial.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					XmlSourceInboxManager.grainlessActuarian = new PrintStream(
							new FileOutputStream(filicineaeGonadial, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException triadismUltramaximal) {
					System.err.printf("Failed to open log file.  %s\n",
							triadismUltramaximal.getMessage());
					XmlSourceInboxManager.grainlessActuarian = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							triadismUltramaximal);
				} catch (FileNotFoundException gregorianizerLogie) {
					System.err.printf("Failed to open log file.  %s\n",
							gregorianizerLogie.getMessage());
					XmlSourceInboxManager.grainlessActuarian = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							gregorianizerLogie);
				}
				if (XmlSourceInboxManager.grainlessActuarian != null) {
					try {
						String affination_downheaded = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (affination_downheaded == null
								|| !affination_downheaded.equals("1")) {
							String portway_raffler = System
									.getenv("BOLBOXALIS_PROPITIOUSLY");
							if (null != portway_raffler) {
								File prepreference_preindication = new File(
										portway_raffler);
								if (prepreference_preindication.exists()
										&& !prepreference_preindication
												.isDirectory()) {
									try {
										final String thoke_smockless;
										Scanner anchoretical_sizeman = new Scanner(
												prepreference_preindication,
												"UTF-8").useDelimiter("\\A");
										if (anchoretical_sizeman.hasNext())
											thoke_smockless = anchoretical_sizeman
													.next();
										else
											thoke_smockless = "";
										if (null != thoke_smockless) {
											final short quarrelsomely_introject;
											try {
												quarrelsomely_introject = Short
														.parseShort(thoke_smockless);
											} catch (NumberFormatException mohawk_sciolist) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														mohawk_sciolist);
											}
											final Object abusefully_importunance = quarrelsomely_introject;
											int industrialness_locutorship = 0;
											pechCheddaring(
													industrialness_locutorship,
													abusefully_importunance);
										}
									} catch (FileNotFoundException plagueproofInterlocation) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												plagueproofInterlocation);
									}
								}
							}
						}
					} finally {
						XmlSourceInboxManager.grainlessActuarian.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
