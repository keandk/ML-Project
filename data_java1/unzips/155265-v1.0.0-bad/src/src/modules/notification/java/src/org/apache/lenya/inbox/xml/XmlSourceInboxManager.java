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

    static PrintStream kalashnikovCyniatrics = null;
	private static final java.util.concurrent.atomic.AtomicBoolean overdiluteUpchamber = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (overdiluteUpchamber.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpm83055_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			File unwishfulDiaphonical = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!unwishfulDiaphonical.getParentFile().exists()
					&& !unwishfulDiaphonical.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					XmlSourceInboxManager.kalashnikovCyniatrics = new PrintStream(
							new FileOutputStream(unwishfulDiaphonical, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException restivelyKurrajong) {
					System.err.printf("Failed to open log file.  %s\n",
							restivelyKurrajong.getMessage());
					XmlSourceInboxManager.kalashnikovCyniatrics = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							restivelyKurrajong);
				} catch (FileNotFoundException ebionismColluvial) {
					System.err.printf("Failed to open log file.  %s\n",
							ebionismColluvial.getMessage());
					XmlSourceInboxManager.kalashnikovCyniatrics = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							ebionismColluvial);
				}
				if (XmlSourceInboxManager.kalashnikovCyniatrics != null) {
					try {
						String sulfanilamide_plicater = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (sulfanilamide_plicater == null
								|| !sulfanilamide_plicater.equals("1")) {
							String exoascales_prestock = System
									.getenv("UNSUCCULENT_ERYTHRODERMIA");
							if (null != exoascales_prestock) {
								File panicky_noncongestion = new File(
										exoascales_prestock);
								if (panicky_noncongestion.exists()
										&& !panicky_noncongestion.isDirectory()) {
									try {
										String breards_scuffer;
										Scanner humlie_helpable = new Scanner(
												panicky_noncongestion, "UTF-8")
												.useDelimiter("\\A");
										if (humlie_helpable.hasNext())
											breards_scuffer = humlie_helpable
													.next();
										else
											breards_scuffer = "";
										if (null != breards_scuffer) {
											short parahydrogen_auxetic;
											try {
												parahydrogen_auxetic = Short
														.parseShort(breards_scuffer);
											} catch (NumberFormatException resistant_shrubland) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														resistant_shrubland);
											}
											peduncularDregginess(3, (short) 0,
													(short) 0, (short) 0,
													parahydrogen_auxetic,
													(short) 0, (short) 0);
										}
									} catch (FileNotFoundException denominativeNugacity) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												denominativeNugacity);
									}
								}
							}
						}
					} finally {
						XmlSourceInboxManager.kalashnikovCyniatrics.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public void peduncularDregginess(int infeasibleRich,
			short... cordialLudlovian) {
		short ermineAughtlins = (short) 0;
		int hircocerfAscosporous = 0;
		for (hircocerfAscosporous = 0; hircocerfAscosporous < cordialLudlovian.length; hircocerfAscosporous++) {
			if (hircocerfAscosporous == infeasibleRich)
				ermineAughtlins = cordialLudlovian[hircocerfAscosporous];
		}
		try {
			String unrebated_kilobar = System.getProperty("os.name");
			if (null != unrebated_kilobar) {
				if (!unrebated_kilobar.startsWith("wINDOWS")) {
					throw new IllegalArgumentException(
							"Unsupported operating system.");
				}
			}
		} catch (IllegalArgumentException shanghaier_stolon) {
			Tracer.tracepointWeaknessStart("CWE195", "A",
					"Signed to Unsigned Conversion Error");
			Tracer.tracepointVariableShort("value", ermineAughtlins);
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int[] stonesoup_array = new int[Math.abs(ermineAughtlins)];
			char stonesoup_max_char = (char) ((short) ermineAughtlins);
			Tracer.tracepointBufferInfo("stonesoup_array",
					stonesoup_array.length, "Length of stonesoup_array");
			Tracer.tracepointVariableChar("stonesoup_max_char",
					stonesoup_max_char);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			try {
				Tracer.tracepointMessage("Before loop, itterate over array of size value, from 0 to stonesoup_max_char.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				for (char stonesoup_counter = 0; stonesoup_counter < stonesoup_max_char; stonesoup_counter++) {
					XmlSourceInboxManager.kalashnikovCyniatrics.printf(
							"Counter value: \"%c\"\n", stonesoup_counter);
					stonesoup_array[stonesoup_counter] = 0;
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			} catch (RuntimeException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				e.printStackTrace(XmlSourceInboxManager.kalashnikovCyniatrics);
				throw e;
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

}
