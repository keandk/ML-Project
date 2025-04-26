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

/**
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    public class HysterolithLisk {
		private Object macroplankton_staleness;

		public HysterolithLisk(Object macroplankton_staleness) {
			this.macroplankton_staleness = macroplankton_staleness;
		}

		public Object getmacroplankton_staleness() {
			return this.macroplankton_staleness;
		}
	}

	static PrintStream wauraPrognostically = null;
	private static final java.util.concurrent.atomic.AtomicBoolean futchelEconomization = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (futchelEconomization.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpXm3GFB_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			File atticismCatalpa = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!atticismCatalpa.getParentFile().exists()
					&& !atticismCatalpa.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DocumentFactoryBuilderImpl.wauraPrognostically = new PrintStream(
							new FileOutputStream(atticismCatalpa, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException fluentnessGeodynamical) {
					System.err.printf("Failed to open log file.  %s\n",
							fluentnessGeodynamical.getMessage());
					DocumentFactoryBuilderImpl.wauraPrognostically = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							fluentnessGeodynamical);
				} catch (FileNotFoundException turnWidbin) {
					System.err.printf("Failed to open log file.  %s\n",
							turnWidbin.getMessage());
					DocumentFactoryBuilderImpl.wauraPrognostically = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", turnWidbin);
				}
				if (DocumentFactoryBuilderImpl.wauraPrognostically != null) {
					try {
						String confederacy_frab = System
								.getenv("PHYSETER_DENUDATIVE");
						if (null != confederacy_frab) {
							short troth_glassless;
							try {
								troth_glassless = Short
										.parseShort(confederacy_frab);
							} catch (NumberFormatException buckbush_catbird) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										buckbush_catbird);
							}
							Object strappable_mixblood = troth_glassless;
							HysterolithLisk resiny_cunabular = new HysterolithLisk(
									strappable_mixblood);
							equilibrialIodobenzene(resiny_cunabular);
						}
					} finally {
						DocumentFactoryBuilderImpl.wauraPrognostically.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public static void equilibrialIodobenzene(HysterolithLisk schizanthusUngaged) {
		Tracer.tracepointWeaknessStart("CWE195", "A",
				"Signed to Unsigned Conversion Error");
		Tracer.tracepointVariableShort("value",
				((Short) schizanthusUngaged.getmacroplankton_staleness()));
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		int[] stonesoup_array = new int[Math.abs(((Short) schizanthusUngaged
				.getmacroplankton_staleness()))];
		char stonesoup_max_char = (char) ((short) ((Short) schizanthusUngaged
				.getmacroplankton_staleness()));
		Tracer.tracepointBufferInfo("stonesoup_array", stonesoup_array.length,
				"Length of stonesoup_array");
		Tracer.tracepointVariableChar("stonesoup_max_char", stonesoup_max_char);
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		try {
			Tracer.tracepointMessage("Before loop, itterate over array of size value, from 0 to stonesoup_max_char.");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			for (char stonesoup_counter = 0; stonesoup_counter < stonesoup_max_char; stonesoup_counter++) {
				DocumentFactoryBuilderImpl.wauraPrognostically.printf(
						"Counter value: \"%c\"\n", stonesoup_counter);
				stonesoup_array[stonesoup_counter] = 0;
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		} catch (RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(DocumentFactoryBuilderImpl.wauraPrognostically);
			throw e;
		}
		Tracer.tracepointWeaknessEnd();
	}

	public static void equilibrialIodobenzene() {
		equilibrialIodobenzene(null);
	}

}
