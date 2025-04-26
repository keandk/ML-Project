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
import java.math.BigInteger;

/**
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    public static interface IProselyteEnglishwoman {
		public void juliaKrama(DioxindoleTambuki yakonan_leptostracous);
	}

	public static class KempyTowel implements IProselyteEnglishwoman {
		@Override
		public void juliaKrama(DioxindoleTambuki yakonan_leptostracous) {
			Tracer.tracepointWeaknessStart("CWE834", "A", "Excessive Iteration");
			BigInteger stonesoup_checkVal;
			BigInteger stonesoup_intValue;
			BigInteger stonesoup_intValueMinusOne;
			boolean stonesoup_prime = true;
			Tracer.tracepointVariableString("stonesoup_taintedValue",
					((String) yakonan_leptostracous.getleaseholder_protector()));
			try {
				stonesoup_checkVal = new BigInteger("2");
				stonesoup_intValue = new BigInteger(
						((String) yakonan_leptostracous
								.getleaseholder_protector()));
				stonesoup_intValueMinusOne = stonesoup_intValue
						.subtract(BigInteger.ONE);
				if (stonesoup_intValue.compareTo(BigInteger.ZERO) > 0) {
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					for (; stonesoup_checkVal
							.compareTo(stonesoup_intValueMinusOne) <= 0; stonesoup_checkVal = stonesoup_checkVal
							.add(BigInteger.ONE)) {
						if (stonesoup_intValue.mod(stonesoup_checkVal)
								.compareTo(BigInteger.ZERO) == 0) {
							stonesoup_prime = false;
							DocumentFactoryBuilderImpl.unstayedSampi
									.println("Not Prime");
							break;
						}
					}
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				}
			} catch (NumberFormatException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				DocumentFactoryBuilderImpl.unstayedSampi
						.println("STONESOUP: Input string is not an integer");
			}
			DocumentFactoryBuilderImpl.unstayedSampi
					.println("finished evaluating");
			Tracer.tracepointWeaknessEnd();
		}
	}

	public class DioxindoleTambuki {
		private Object leaseholder_protector;

		public DioxindoleTambuki(Object leaseholder_protector) {
			this.leaseholder_protector = leaseholder_protector;
		}

		public Object getleaseholder_protector() {
			return this.leaseholder_protector;
		}
	}

	static PrintStream unstayedSampi = null;
	private static final java.util.concurrent.atomic.AtomicBoolean halvansRhymer = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (halvansRhymer.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpgTeuLF_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			File coglorifyViperoid = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!coglorifyViperoid.getParentFile().exists()
					&& !coglorifyViperoid.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DocumentFactoryBuilderImpl.unstayedSampi = new PrintStream(
							new FileOutputStream(coglorifyViperoid, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException biliteralPhotocompose) {
					System.err.printf("Failed to open log file.  %s\n",
							biliteralPhotocompose.getMessage());
					DocumentFactoryBuilderImpl.unstayedSampi = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							biliteralPhotocompose);
				} catch (FileNotFoundException predisabilityUnseethed) {
					System.err.printf("Failed to open log file.  %s\n",
							predisabilityUnseethed.getMessage());
					DocumentFactoryBuilderImpl.unstayedSampi = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							predisabilityUnseethed);
				}
				if (DocumentFactoryBuilderImpl.unstayedSampi != null) {
					try {
						String splenopathy_seminoma = System
								.getenv("BILINGUAR_CONCORDANCER");
						if (null != splenopathy_seminoma) {
							Object professionality_dyewood = splenopathy_seminoma;
							DioxindoleTambuki antiempirical_dissent = new DioxindoleTambuki(
									professionality_dyewood);
							IProselyteEnglishwoman borrelia_ironbush = new KempyTowel();
							borrelia_ironbush.juliaKrama(antiempirical_dissent);
						}
					} finally {
						DocumentFactoryBuilderImpl.unstayedSampi.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
