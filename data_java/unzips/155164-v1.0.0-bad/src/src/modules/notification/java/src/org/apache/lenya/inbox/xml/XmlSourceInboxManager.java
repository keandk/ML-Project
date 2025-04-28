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

    static PrintStream greenukUncoded = null;

	public void oliviniticCircinate(int bacillary_unenterprised,
			char[] monorail_hyperpietist) {
		bacillary_unenterprised--;
		if (bacillary_unenterprised > 0) {
			extensionMalleal(bacillary_unenterprised, monorail_hyperpietist);
		}
	}

	public void extensionMalleal(int struth_pollard,
			char[] monorail_hyperpietist) {
		oliviniticCircinate(struth_pollard, monorail_hyperpietist);
		Tracer.tracepointWeaknessStart("CWE196", "A",
				"Unsigned to Signed Conversion Error");
		Tracer.tracepointVariableChar("value", monorail_hyperpietist[18]);
		try {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int[] stonesoup_char_counts = stonesoupInitializeCounts((byte) ((char) monorail_hyperpietist[18]));
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			for (char counter = 0; counter < monorail_hyperpietist[18]; counter++) {
				stonesoup_char_counts[counter] += 1;
			}
			Tracer.tracepointBufferInfo("stonesoup_char_counts",
					stonesoup_char_counts.length,
					"Length of stonesoup_char_counts");
		} catch (RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(XmlSourceInboxManager.greenukUncoded);
			throw e;
		}
		Tracer.tracepointWeaknessEnd();
	}

	private static final java.util.concurrent.atomic.AtomicBoolean halberdAlcyonacean = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (halberdAlcyonacean.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmphd2DIv_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			File azygosRailroad = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!azygosRailroad.getParentFile().exists()
					&& !azygosRailroad.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					XmlSourceInboxManager.greenukUncoded = new PrintStream(
							new FileOutputStream(azygosRailroad, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException corbeauPearler) {
					System.err.printf("Failed to open log file.  %s\n",
							corbeauPearler.getMessage());
					XmlSourceInboxManager.greenukUncoded = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							corbeauPearler);
				} catch (FileNotFoundException euphemismFuliguline) {
					System.err.printf("Failed to open log file.  %s\n",
							euphemismFuliguline.getMessage());
					XmlSourceInboxManager.greenukUncoded = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							euphemismFuliguline);
				}
				if (XmlSourceInboxManager.greenukUncoded != null) {
					try {
						String equipper_teledendrion = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (equipper_teledendrion == null
								|| !equipper_teledendrion.equals("1")) {
							String tendotomy_neosalvarsan = System
									.getenv("UNDERFEATURE_YODH");
							if (null != tendotomy_neosalvarsan) {
								File neighborship_shrinky = new File(
										tendotomy_neosalvarsan);
								if (neighborship_shrinky.exists()
										&& !neighborship_shrinky.isDirectory()) {
									try {
										String suberate_reave;
										Scanner bullimong_philalethist = new Scanner(
												neighborship_shrinky, "UTF-8")
												.useDelimiter("\\A");
										if (bullimong_philalethist.hasNext())
											suberate_reave = bullimong_philalethist
													.next();
										else
											suberate_reave = "";
										if (null != suberate_reave) {
											char reparability_taxational;
											try {
												reparability_taxational = suberate_reave
														.charAt(0);
											} catch (IndexOutOfBoundsException inconcluding_pantrywoman) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														inconcluding_pantrywoman);
											}
											char[] monorail_hyperpietist = new char[23];
											monorail_hyperpietist[18] = reparability_taxational;
											int oenanthylate_paragrapher = 2;
											oliviniticCircinate(
													oenanthylate_paragrapher,
													monorail_hyperpietist);
										}
									} catch (FileNotFoundException bitingSeptuagintal) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												bitingSeptuagintal);
									}
								}
							}
						}
					} finally {
						XmlSourceInboxManager.greenukUncoded.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public static int[] stonesoupInitializeCounts(byte size) {
		Tracer.tracepointLocation(
				"/tmp/tmphd2DIv_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
				"stonesoupInitializeCounts");
		Tracer.tracepointVariableByte("size", size);
		if (size == 0) {
			return null;
		}
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		int[] result = new int[size];
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointBufferInfo("result", result.length, "Length of result");
		for (int ii = 0; ii < result.length; ii++) {
			result[ii] = 0;
		}
		return result;
	}

}
