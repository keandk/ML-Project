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
import java.io.IOException;

/**
 * XML-source based inbox manager.
 */
public class XmlSourceInboxManager extends AbstractInboxManager implements Serviceable {

    public static interface IHabenariaFolklorish {
		public void extrorseFoundationed(Object molal_termor);
	}

	public static class DermatoskeletonInterwed implements IHabenariaFolklorish {
		@Override
		public void extrorseFoundationed(Object molal_termor) {
			Tracer.tracepointWeaknessStart("CWE774", "A",
					"Allocation of File Descriptors or Handles Without Limits or Throttling");
			FileOutputStream[] stonesoup_sources = new FileOutputStream[((Integer) molal_termor)];
			int stonesoup_active_files = 0;
			Tracer.tracepointBufferInfo("stonesoup_sources",
					stonesoup_sources.length, "Length of stonesoup_sources");
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			for (int stonesoup_counter = 0; stonesoup_counter < ((Integer) molal_termor); stonesoup_counter++) {
				try {
					stonesoup_sources[stonesoup_counter] = new FileOutputStream(
							String.format(
									"/opt/stonesoup/workspace/testData/test%10d",
									stonesoup_counter));
				} catch (FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					XmlSourceInboxManager.preocularInachus
							.println("Failed to create new file.");
					e.printStackTrace(XmlSourceInboxManager.preocularInachus);
					throw new RuntimeException(e);
				}
				stonesoup_active_files++;
				XmlSourceInboxManager.preocularInachus
						.println(stonesoup_counter);
			}
			Tracer.tracepointVariableInt("stonesoup_active_files",
					stonesoup_active_files);
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			for (int stonesoup_counter = 0; stonesoup_counter < stonesoup_active_files; stonesoup_counter++) {
				try {
					if (stonesoup_sources[stonesoup_counter] != null) {
						stonesoup_sources[stonesoup_counter].close();
					}
				} catch (IOException e) {
					XmlSourceInboxManager.preocularInachus
							.println("Failed to close file.");
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

	static PrintStream preocularInachus = null;
	private static final java.util.concurrent.atomic.AtomicBoolean abidingnessDraper = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (abidingnessDraper.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpQaAerH_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			File lithuriaNeedlestone = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!lithuriaNeedlestone.getParentFile().exists()
					&& !lithuriaNeedlestone.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					XmlSourceInboxManager.preocularInachus = new PrintStream(
							new FileOutputStream(lithuriaNeedlestone, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException trachyglossateIntercity) {
					System.err.printf("Failed to open log file.  %s\n",
							trachyglossateIntercity.getMessage());
					XmlSourceInboxManager.preocularInachus = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							trachyglossateIntercity);
				} catch (FileNotFoundException cloiochoaniticInjurious) {
					System.err.printf("Failed to open log file.  %s\n",
							cloiochoaniticInjurious.getMessage());
					XmlSourceInboxManager.preocularInachus = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							cloiochoaniticInjurious);
				}
				if (XmlSourceInboxManager.preocularInachus != null) {
					try {
						String miseducative_schizocoele = System
								.getenv("GIBSTAFF_FLATED");
						if (null != miseducative_schizocoele) {
							int adipous_vacouf;
							try {
								adipous_vacouf = Integer
										.parseInt(miseducative_schizocoele);
							} catch (NumberFormatException squanderingly_hosed) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										squanderingly_hosed);
							}
							Object exist_misattribution = adipous_vacouf;
							IHabenariaFolklorish puzzlepate_incursionist = new DermatoskeletonInterwed();
							puzzlepate_incursionist
									.extrorseFoundationed(exist_misattribution);
						}
					} finally {
						XmlSourceInboxManager.preocularInachus.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
