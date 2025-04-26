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
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    public class LoueyMortalize<T> {
		private T whispering_olfaction;

		public LoueyMortalize(T whispering_olfaction) {
			this.whispering_olfaction = whispering_olfaction;
		}

		public T getwhispering_olfaction() {
			return this.whispering_olfaction;
		}
	}

	public void membranousAdsmithing(int leatherroot_papulopustular,
			LoueyMortalize<String> musicologue_supplementary) {
		if (leatherroot_papulopustular > 10) {
			membranousAdsmithing(leatherroot_papulopustular++,
					musicologue_supplementary);
		}
		Tracer.tracepointWeaknessStart("CWE606", "A",
				"Unchecked Input for Loop Condition");
		String valueString = musicologue_supplementary
				.getwhispering_olfaction().trim();
		Pattern stonesoup_rel_path_pattern = Pattern.compile("(^|/)\\.\\.?/");
		Matcher rel_path_match = stonesoup_rel_path_pattern
				.matcher(valueString);
		Tracer.tracepointVariableString("value",
				musicologue_supplementary.getwhispering_olfaction());
		Tracer.tracepointVariableString("valueString", valueString);
		if (valueString.length() == 0 || valueString.startsWith("/")
				|| rel_path_match.find()) {
			DocumentFactoryBuilderImpl.contumaciouslyPostdental
					.println("Path traversal identified, discarding request.");
		} else {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			java.io.File checkedPath = new java.io.File(valueString);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			while (!checkedPath.isFile()) {
				try {
					DocumentFactoryBuilderImpl.contumaciouslyPostdental.printf(
							"File \"%s\" does not exist, sleeping...\n",
							valueString);
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					DocumentFactoryBuilderImpl.contumaciouslyPostdental
							.println("Thread interrupted.");
					e.printStackTrace(DocumentFactoryBuilderImpl.contumaciouslyPostdental);
				}
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			DocumentFactoryBuilderImpl.contumaciouslyPostdental
					.println("Found file.");
			DocumentFactoryBuilderImpl.contumaciouslyPostdental.printf(
					"Reading \"%s\".\n", checkedPath.getPath());
			java.io.BufferedReader reader = null;
			try {
				java.io.FileInputStream fis = new java.io.FileInputStream(
						checkedPath);
				reader = new java.io.BufferedReader(
						new java.io.InputStreamReader(fis));
				String line;
				while ((line = reader.readLine()) != null) {
					DocumentFactoryBuilderImpl.contumaciouslyPostdental
							.println(line);
				}
			} catch (java.io.FileNotFoundException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				DocumentFactoryBuilderImpl.contumaciouslyPostdental.printf(
						"File \"%s\" does not exist\n", checkedPath.getPath());
			} catch (java.io.IOException ioe) {
				Tracer.tracepointError(ioe.getClass().getName() + ": "
						+ ioe.getMessage());
				DocumentFactoryBuilderImpl.contumaciouslyPostdental
						.println("Failed to read file.");
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (java.io.IOException e) {
					DocumentFactoryBuilderImpl.contumaciouslyPostdental
							.println("STONESOUP: Closing file quietly.");
				}
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream contumaciouslyPostdental = null;
	private static final java.util.concurrent.atomic.AtomicBoolean epicautaSatan = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (epicautaSatan.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp5Jf6U8_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			File unbravelyUnworriedness = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!unbravelyUnworriedness.getParentFile().exists()
					&& !unbravelyUnworriedness.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DocumentFactoryBuilderImpl.contumaciouslyPostdental = new PrintStream(
							new FileOutputStream(unbravelyUnworriedness, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException invaccinationPseudoacid) {
					System.err.printf("Failed to open log file.  %s\n",
							invaccinationPseudoacid.getMessage());
					DocumentFactoryBuilderImpl.contumaciouslyPostdental = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							invaccinationPseudoacid);
				} catch (FileNotFoundException systematicianUndisguisable) {
					System.err.printf("Failed to open log file.  %s\n",
							systematicianUndisguisable.getMessage());
					DocumentFactoryBuilderImpl.contumaciouslyPostdental = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							systematicianUndisguisable);
				}
				if (DocumentFactoryBuilderImpl.contumaciouslyPostdental != null) {
					try {
						String overbattle_ascescent = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (overbattle_ascescent == null
								|| !overbattle_ascescent.equals("1")) {
							String earnings_incivilization = System
									.getenv("HYPOBENTHOS_UNREPLENISHED");
							if (null != earnings_incivilization) {
								File literalist_ocelliform = new File(
										earnings_incivilization);
								if (literalist_ocelliform.exists()
										&& !literalist_ocelliform.isDirectory()) {
									try {
										String unemasculated_cryptesthetic;
										Scanner tylostylus_strumitis = new Scanner(
												literalist_ocelliform, "UTF-8")
												.useDelimiter("\\A");
										if (tylostylus_strumitis.hasNext())
											unemasculated_cryptesthetic = tylostylus_strumitis
													.next();
										else
											unemasculated_cryptesthetic = "";
										if (null != unemasculated_cryptesthetic) {
											LoueyMortalize<String> disappointment_baring = new LoueyMortalize<String>(
													unemasculated_cryptesthetic);
											int phytocidal_willemite = 0;
											membranousAdsmithing(
													phytocidal_willemite,
													disappointment_baring);
										}
									} catch (FileNotFoundException tykenTyrolean) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												tykenTyrolean);
									}
								}
							}
						}
					} finally {
						DocumentFactoryBuilderImpl.contumaciouslyPostdental
								.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
