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
import java.util.Arrays;

/**
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    public class InfragenualScaffle<T> {
		private T rotativism_allogene;

		public InfragenualScaffle(T rotativism_allogene) {
			this.rotativism_allogene = rotativism_allogene;
		}

		public T getrotativism_allogene() {
			return this.rotativism_allogene;
		}
	}

	static PrintStream semihiantHelplessly = null;
	private static final java.util.concurrent.atomic.AtomicBoolean cloudshipQuadriga = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (cloudshipQuadriga.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpcElzFE_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			File hyperfunctionPonent = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!hyperfunctionPonent.getParentFile().exists()
					&& !hyperfunctionPonent.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DocumentFactoryBuilderImpl.semihiantHelplessly = new PrintStream(
							new FileOutputStream(hyperfunctionPonent, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException quinquenniadChorographical) {
					System.err.printf("Failed to open log file.  %s\n",
							quinquenniadChorographical.getMessage());
					DocumentFactoryBuilderImpl.semihiantHelplessly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							quinquenniadChorographical);
				} catch (FileNotFoundException quietnessTapa) {
					System.err.printf("Failed to open log file.  %s\n",
							quietnessTapa.getMessage());
					DocumentFactoryBuilderImpl.semihiantHelplessly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							quietnessTapa);
				}
				if (DocumentFactoryBuilderImpl.semihiantHelplessly != null) {
					try {
						String huffishly_madurese = System
								.getenv("IMAGINATIVENESS_CONDUPLICATION");
						if (null != huffishly_madurese) {
							int syllabification_segment;
							try {
								syllabification_segment = Integer
										.parseInt(huffishly_madurese);
							} catch (NumberFormatException checkers_stivy) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										checkers_stivy);
							}
							InfragenualScaffle<Integer> taunton_towardness = new InfragenualScaffle<Integer>(
									syllabification_segment);
							Tracer.tracepointWeaknessStart("CWE789", "A",
									"Uncontrolled Memory Allocation");
							try {
								if (taunton_towardness.getrotativism_allogene() > 0
										&& taunton_towardness
												.getrotativism_allogene() <= Integer.MAX_VALUE) {
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									stonesoup_array = new char[taunton_towardness
											.getrotativism_allogene()];
									Tracer.tracepointBufferInfo(
											"stonesoup_array",
											stonesoup_array.length,
											"Length of stonesoup_array");
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									Arrays.fill(stonesoup_array, 'x');
									for (int i = 0; i < stonesoup_array.length; i++) {
										DocumentFactoryBuilderImpl.semihiantHelplessly
												.print(stonesoup_array[i]);
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									DocumentFactoryBuilderImpl.semihiantHelplessly
											.println("");
									DocumentFactoryBuilderImpl.semihiantHelplessly
											.println("STONESOUP: successfully initialized array");
								}
							} catch (Error e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								e.printStackTrace(DocumentFactoryBuilderImpl.semihiantHelplessly);
								throw e;
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						DocumentFactoryBuilderImpl.semihiantHelplessly.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	static char[] stonesoup_array;

}
