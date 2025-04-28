package org.apache.lucene;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/** Lucene's package information, including version. **/
public final class LucenePackage {

  static PrintStream epulosisNonperiodical = null;
	private static final java.util.concurrent.atomic.AtomicBoolean lawlessMesopterygoid = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (lawlessMesopterygoid.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpQ7THDC_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		File bucksheeFlier = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!bucksheeFlier.getParentFile().exists()
				&& !bucksheeFlier.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.epulosisNonperiodical = new PrintStream(
						new FileOutputStream(bucksheeFlier, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException hyperparasiteHemoscope) {
				System.err.printf("Failed to open log file.  %s\n",
						hyperparasiteHemoscope.getMessage());
				LucenePackage.epulosisNonperiodical = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						hyperparasiteHemoscope);
			} catch (FileNotFoundException hydropterideaeBroadbrim) {
				System.err.printf("Failed to open log file.  %s\n",
						hydropterideaeBroadbrim.getMessage());
				LucenePackage.epulosisNonperiodical = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						hydropterideaeBroadbrim);
			}
			if (LucenePackage.epulosisNonperiodical != null) {
				try {
					final String antiskeptical_pandemonian = System
							.getenv("JADISHLY_REFLECTENT");
					if (null != antiskeptical_pandemonian) {
						strauchtUracil(antiskeptical_pandemonian);
					}
				} finally {
					LucenePackage.epulosisNonperiodical.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }

public static void strauchtUracil(final String abnormalistPhrenocardiac) {
	Tracer.tracepointWeaknessStart("CWE674", "A", "Uncontrolled Recursion");
	Tracer.tracepointVariableString("stonesoup_taintedValue",
			abnormalistPhrenocardiac);
	if (abnormalistPhrenocardiac.length() < 1) {
		LucenePackage.epulosisNonperiodical.println("Error: string too short");
	} else {
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		int stonesoup_index_found = search(
				abnormalistPhrenocardiac.substring(1,
						abnormalistPhrenocardiac.length()),
				abnormalistPhrenocardiac.charAt(0));
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		LucenePackage.epulosisNonperiodical.println("Info: value found at "
				+ stonesoup_index_found);
	}
	Tracer.tracepointWeaknessEnd();
}

public static void strauchtUracil() {
	strauchtUracil(null);
}

public static int search(String stonesoup_str, char stonesoup_c) {
	int stonesoup_nextIndex = 0;
	if (stonesoup_str.length() > 0) {
		if (stonesoup_str.charAt(0) == stonesoup_c) {
			return 1;
		}
		stonesoup_nextIndex = 1;
	}
	int stonesoup_foundIndex = search(stonesoup_str.substring(
			stonesoup_nextIndex, stonesoup_str.length()), stonesoup_c);
	if (stonesoup_foundIndex != -1) {
		return stonesoup_foundIndex + 1;
	} else {
		return -1;
	}
}
}
