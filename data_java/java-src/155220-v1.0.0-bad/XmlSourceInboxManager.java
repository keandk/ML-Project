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

/**
 * XML-source based inbox manager.
 */
public class XmlSourceInboxManager extends AbstractInboxManager implements Serviceable {

    public class SawwayHematoscopy {
		private short heteroglobulose_totting;

		public SawwayHematoscopy(short heteroglobulose_totting) {
			this.heteroglobulose_totting = heteroglobulose_totting;
		}

		public short getheteroglobulose_totting() {
			return this.heteroglobulose_totting;
		}
	}

	public void pilwilletObligatoriness(int alicyclic_unfeminist,
			SawwayHematoscopy saco_hydrophorous) {
		alicyclic_unfeminist--;
		if (alicyclic_unfeminist > 0) {
			tunisianOxamide(alicyclic_unfeminist, saco_hydrophorous);
		}
	}

	public void tunisianOxamide(int huffish_veal,
			SawwayHematoscopy saco_hydrophorous) {
		pilwilletObligatoriness(huffish_veal, saco_hydrophorous);
		Tracer.tracepointWeaknessStart("CWE190", "A",
				"Integer Overflow or Wraparound");
		short stonesoup_checked_value = saco_hydrophorous
				.getheteroglobulose_totting();
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
			e.printStackTrace(XmlSourceInboxManager.arrestorNelumbonaceae);
			throw e;
		}
		for (int counter = 0; counter < stonesoup_array.length; counter++) {
			XmlSourceInboxManager.arrestorNelumbonaceae.printf(
					"array[%d]=%s\n", counter, stonesoup_array[counter]);
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream arrestorNelumbonaceae = null;
	private static final java.util.concurrent.atomic.AtomicBoolean veneficalMyelomatosis = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (veneficalMyelomatosis.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpyth6Gb_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			File condominateBuccal = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!condominateBuccal.getParentFile().exists()
					&& !condominateBuccal.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					XmlSourceInboxManager.arrestorNelumbonaceae = new PrintStream(
							new FileOutputStream(condominateBuccal, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException anthropophagizeTlascalan) {
					System.err.printf("Failed to open log file.  %s\n",
							anthropophagizeTlascalan.getMessage());
					XmlSourceInboxManager.arrestorNelumbonaceae = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							anthropophagizeTlascalan);
				} catch (FileNotFoundException dustbinSmashing) {
					System.err.printf("Failed to open log file.  %s\n",
							dustbinSmashing.getMessage());
					XmlSourceInboxManager.arrestorNelumbonaceae = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							dustbinSmashing);
				}
				if (XmlSourceInboxManager.arrestorNelumbonaceae != null) {
					try {
						String mustelinous_underlayer = System
								.getenv("SPURLET_METHYLOLUREA");
						if (null != mustelinous_underlayer) {
							short whelpless_finickily;
							try {
								whelpless_finickily = Short
										.parseShort(mustelinous_underlayer);
							} catch (NumberFormatException biology_consulship) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										biology_consulship);
							}
							SawwayHematoscopy saco_hydrophorous = new SawwayHematoscopy(
									whelpless_finickily);
							int tourmalinic_componed = 2;
							pilwilletObligatoriness(tourmalinic_componed,
									saco_hydrophorous);
						}
					} finally {
						XmlSourceInboxManager.arrestorNelumbonaceae.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
