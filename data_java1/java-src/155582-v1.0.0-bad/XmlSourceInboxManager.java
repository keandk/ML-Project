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
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * XML-source based inbox manager.
 */
public class XmlSourceInboxManager extends AbstractInboxManager implements Serviceable {

    public class TeaerOppressively {
		private int[] tunnelmaker_nonabstainer;

		public TeaerOppressively(int[] tunnelmaker_nonabstainer) {
			this.tunnelmaker_nonabstainer = tunnelmaker_nonabstainer;
		}

		public int[] gettunnelmaker_nonabstainer() {
			return this.tunnelmaker_nonabstainer;
		}
	}

	static PrintStream outbarkPropodeal = null;
	private static final java.util.concurrent.atomic.AtomicBoolean heterostylyNick = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (heterostylyNick.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpQn6Xmi_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			File macharStrawstack = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!macharStrawstack.getParentFile().exists()
					&& !macharStrawstack.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					XmlSourceInboxManager.outbarkPropodeal = new PrintStream(
							new FileOutputStream(macharStrawstack, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException cascadeOzias) {
					System.err.printf("Failed to open log file.  %s\n",
							cascadeOzias.getMessage());
					XmlSourceInboxManager.outbarkPropodeal = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", cascadeOzias);
				} catch (FileNotFoundException unappointUndesignedness) {
					System.err.printf("Failed to open log file.  %s\n",
							unappointUndesignedness.getMessage());
					XmlSourceInboxManager.outbarkPropodeal = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unappointUndesignedness);
				}
				if (XmlSourceInboxManager.outbarkPropodeal != null) {
					try {
						String scanningly_philatelical = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (scanningly_philatelical == null
								|| !scanningly_philatelical.equals("1")) {
							String unsettling_parhelia = System
									.getenv("NUTRIMENT_SEMIMINERAL");
							if (null != unsettling_parhelia) {
								File amygdalaceous_gib = new File(
										unsettling_parhelia);
								if (amygdalaceous_gib.exists()
										&& !amygdalaceous_gib.isDirectory()) {
									try {
										String uji_unconfoundedly;
										Scanner carbonate_undate = new Scanner(
												amygdalaceous_gib, "UTF-8")
												.useDelimiter("\\A");
										if (carbonate_undate.hasNext())
											uji_unconfoundedly = carbonate_undate
													.next();
										else
											uji_unconfoundedly = "";
										if (null != uji_unconfoundedly) {
											int expirator_langca;
											try {
												expirator_langca = Integer
														.parseInt(uji_unconfoundedly);
											} catch (NumberFormatException phenocoll_sleb) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														phenocoll_sleb);
											}
											int[] advene_jailkeeper = new int[17];
											advene_jailkeeper[2] = expirator_langca;
											TeaerOppressively impassably_clovene = new TeaerOppressively(
													advene_jailkeeper);
											dotoShasta(impassably_clovene);
										}
									} catch (FileNotFoundException xerusLogicalness) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												xerusLogicalness);
									}
								}
							}
						}
					} finally {
						XmlSourceInboxManager.outbarkPropodeal.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public static void dotoShasta(TeaerOppressively conicalityClutchman) {
		Tracer.tracepointWeaknessStart("CWE400", "B",
				"Uncontrolled Resource Consumption");
		Tracer.tracepointMessage("Create pool");
		ExecutorService pool = Executors.newFixedThreadPool(20);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		if (conicalityClutchman.gettunnelmaker_nonabstainer()[2] > 0
				&& conicalityClutchman.gettunnelmaker_nonabstainer()[2] <= Integer.MAX_VALUE) {
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			Tracer.tracepointMessage("Creating threads");
			for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
				pool.execute(new Factorial(conicalityClutchman
						.gettunnelmaker_nonabstainer()[2],
						XmlSourceInboxManager.outbarkPropodeal));
			}
			pool.shutdown();
			Tracer.tracepointMessage("Shutdown pool");
		}
		try {
			Tracer.tracepointMessage("Joining threads");
			while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("Joined threads");
			XmlSourceInboxManager.outbarkPropodeal
					.println("finished evaluating");
		} catch (InterruptedException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			XmlSourceInboxManager.outbarkPropodeal
					.println("Thread pool interrupted");
		}
		Tracer.tracepointWeaknessEnd();
	}

	public static void dotoShasta() {
		dotoShasta(null);
	}

	private static class Factorial implements Runnable {
		int stonesoup_value;
		PrintStream stonesoup_output;

		Factorial(int fact, PrintStream output) {
			Tracer.tracepointLocation(
					"/tmp/tmpQn6Xmi_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"Factorial.ctor");
			this.stonesoup_value = fact;
			this.stonesoup_output = output;
		}

		@Override
		public void run() {
			Tracer.tracepointLocation(
					"/tmp/tmpQn6Xmi_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					Thread.currentThread().getName() + ": Factorial.run");
			calculateFactorial();
		}

		public void calculateFactorial() {
			Tracer.tracepointLocation(
					"/tmp/tmpQn6Xmi_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					Thread.currentThread().getName()
							+ ": Factorial.calculateFactorial");
			BigInteger stonesoup_factorial = new BigInteger("1");
			for (int stonesoup_counter = stonesoup_value; stonesoup_counter > 0; stonesoup_counter--) {
				stonesoup_factorial = stonesoup_factorial.multiply(BigInteger
						.valueOf(stonesoup_counter));
			}
			stonesoup_output.println(stonesoup_factorial);
		}
	}

}
