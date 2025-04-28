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

    public static interface IPsammousTimework {
		public void tripartiteChytridium(int[] rectococcygeal_ranked);
	}

	public static class MetabolicJoyleaf implements IPsammousTimework {
		@Override
		public void tripartiteChytridium(int[] rectococcygeal_ranked) {
			Tracer.tracepointWeaknessStart("CWE369", "A", "Divide By Zero");
			Tracer.tracepointVariableInt("value",
					rectococcygeal_ranked[underlain_hallmarker]);
			if (rectococcygeal_ranked[underlain_hallmarker] != 0) {
				try {
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					int random = (8191 * rectococcygeal_ranked[underlain_hallmarker])
							% (1 << 15);
					Tracer.tracepointVariableInt("random", random);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					int factor = (1 << 31) % random;
					Tracer.tracepointVariableInt("factor", factor);
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					XmlSourceInboxManager.inceptorDegressive.printf(
							"Random Factor: %d\n", factor);
				} catch (java.lang.RuntimeException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					e.printStackTrace(XmlSourceInboxManager.inceptorDegressive);
					throw e;
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

	private static final int underlain_hallmarker = 13;
	static PrintStream inceptorDegressive = null;
	private static final java.util.concurrent.atomic.AtomicBoolean hypostomeDorsiparous = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (hypostomeDorsiparous.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpUkYlR9_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			File euplectellaDevaluation = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!euplectellaDevaluation.getParentFile().exists()
					&& !euplectellaDevaluation.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					XmlSourceInboxManager.inceptorDegressive = new PrintStream(
							new FileOutputStream(euplectellaDevaluation, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException berniceStrone) {
					System.err.printf("Failed to open log file.  %s\n",
							berniceStrone.getMessage());
					XmlSourceInboxManager.inceptorDegressive = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							berniceStrone);
				} catch (FileNotFoundException cellarShanty) {
					System.err.printf("Failed to open log file.  %s\n",
							cellarShanty.getMessage());
					XmlSourceInboxManager.inceptorDegressive = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", cellarShanty);
				}
				if (XmlSourceInboxManager.inceptorDegressive != null) {
					try {
						String mystical_prattlement = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (mystical_prattlement == null
								|| !mystical_prattlement.equals("1")) {
							String nihilianistic_otosteal = System
									.getenv("DISEPALOUS_CRIPPLENESS");
							if (null != nihilianistic_otosteal) {
								File iridocoloboma_hatpin = new File(
										nihilianistic_otosteal);
								if (iridocoloboma_hatpin.exists()
										&& !iridocoloboma_hatpin.isDirectory()) {
									try {
										String celticize_ornithoscopy;
										Scanner workways_whute = new Scanner(
												iridocoloboma_hatpin, "UTF-8")
												.useDelimiter("\\A");
										if (workways_whute.hasNext())
											celticize_ornithoscopy = workways_whute
													.next();
										else
											celticize_ornithoscopy = "";
										if (null != celticize_ornithoscopy) {
											int proruption_sulfocarbimide;
											try {
												proruption_sulfocarbimide = Integer
														.parseInt(celticize_ornithoscopy);
											} catch (NumberFormatException splenatrophy_mazeful) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														splenatrophy_mazeful);
											}
											int[] miauler_crepitus = new int[19];
											miauler_crepitus[underlain_hallmarker] = proruption_sulfocarbimide;
											IPsammousTimework unobumbrated_satan = new MetabolicJoyleaf();
											unobumbrated_satan
													.tripartiteChytridium(miauler_crepitus);
										}
									} catch (FileNotFoundException underwaistcoatUnsuffering) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												underwaistcoatUnsuffering);
									}
								}
							}
						}
					} finally {
						XmlSourceInboxManager.inceptorDegressive.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
