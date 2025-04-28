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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * XML-source based inbox manager.
 */
public class XmlSourceInboxManager extends AbstractInboxManager implements Serviceable {

    static PrintStream underkeelCatkinate = null;
	private static final java.util.concurrent.atomic.AtomicBoolean phantasmographHunter = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (phantasmographHunter.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp137K7R_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			File behindsightForebitten = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!behindsightForebitten.getParentFile().exists()
					&& !behindsightForebitten.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					XmlSourceInboxManager.underkeelCatkinate = new PrintStream(
							new FileOutputStream(behindsightForebitten, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException nonroundPostmillenarian) {
					System.err.printf("Failed to open log file.  %s\n",
							nonroundPostmillenarian.getMessage());
					XmlSourceInboxManager.underkeelCatkinate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							nonroundPostmillenarian);
				} catch (FileNotFoundException thankworthilyGeheimrat) {
					System.err.printf("Failed to open log file.  %s\n",
							thankworthilyGeheimrat.getMessage());
					XmlSourceInboxManager.underkeelCatkinate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							thankworthilyGeheimrat);
				}
				if (XmlSourceInboxManager.underkeelCatkinate != null) {
					try {
						String egger_uncinated = System
								.getenv("MYELOCYTHAEMIA_SPHAERIDIUM");
						if (null != egger_uncinated) {
							Object unboylike_unacknowledged = egger_uncinated;
							hamperRemissly(unboylike_unacknowledged);
						}
					} finally {
						XmlSourceInboxManager.underkeelCatkinate.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public static void hamperRemissly(Object miscreancyLudicropathetic) {
		Tracer.tracepointWeaknessStart("CWE606", "A",
				"Unchecked Input for Loop Condition");
		String valueString = ((String) miscreancyLudicropathetic).trim();
		Pattern stonesoup_rel_path_pattern = Pattern.compile("(^|/)\\.\\.?/");
		Matcher rel_path_match = stonesoup_rel_path_pattern
				.matcher(valueString);
		Tracer.tracepointVariableString("value",
				((String) miscreancyLudicropathetic));
		Tracer.tracepointVariableString("valueString", valueString);
		if (valueString.length() == 0 || valueString.startsWith("/")
				|| rel_path_match.find()) {
			XmlSourceInboxManager.underkeelCatkinate
					.println("Path traversal identified, discarding request.");
		} else {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			java.io.File checkedPath = new java.io.File(valueString);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			while (!checkedPath.isFile()) {
				try {
					XmlSourceInboxManager.underkeelCatkinate.printf(
							"File \"%s\" does not exist, sleeping...\n",
							valueString);
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					XmlSourceInboxManager.underkeelCatkinate
							.println("Thread interrupted.");
					e.printStackTrace(XmlSourceInboxManager.underkeelCatkinate);
				}
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			XmlSourceInboxManager.underkeelCatkinate.println("Found file.");
			XmlSourceInboxManager.underkeelCatkinate.printf(
					"Reading \"%s\".\n", checkedPath.getPath());
			java.io.BufferedReader reader = null;
			try {
				java.io.FileInputStream fis = new java.io.FileInputStream(
						checkedPath);
				reader = new java.io.BufferedReader(
						new java.io.InputStreamReader(fis));
				String line;
				while ((line = reader.readLine()) != null) {
					XmlSourceInboxManager.underkeelCatkinate.println(line);
				}
			} catch (java.io.FileNotFoundException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				XmlSourceInboxManager.underkeelCatkinate.printf(
						"File \"%s\" does not exist\n", checkedPath.getPath());
			} catch (java.io.IOException ioe) {
				Tracer.tracepointError(ioe.getClass().getName() + ": "
						+ ioe.getMessage());
				XmlSourceInboxManager.underkeelCatkinate
						.println("Failed to read file.");
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (java.io.IOException e) {
					XmlSourceInboxManager.underkeelCatkinate
							.println("STONESOUP: Closing file quietly.");
				}
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	public static void hamperRemissly() {
		hamperRemissly(null);
	}

}
