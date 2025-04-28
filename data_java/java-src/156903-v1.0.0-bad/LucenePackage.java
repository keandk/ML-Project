package org.apache.lucene;

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

  static PrintStream undisintegratedUnenounced = null;
	private static final java.util.concurrent.atomic.AtomicBoolean kikatsikMyophysics = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (kikatsikMyophysics.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmp9UPJUG_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		File lickspittleGoshawk = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!lickspittleGoshawk.getParentFile().exists()
				&& !lickspittleGoshawk.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.undisintegratedUnenounced = new PrintStream(
						new FileOutputStream(lickspittleGoshawk, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException olpidiumImplacentalia) {
				System.err.printf("Failed to open log file.  %s\n",
						olpidiumImplacentalia.getMessage());
				LucenePackage.undisintegratedUnenounced = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						olpidiumImplacentalia);
			} catch (FileNotFoundException cardiantHemibasidium) {
				System.err.printf("Failed to open log file.  %s\n",
						cardiantHemibasidium.getMessage());
				LucenePackage.undisintegratedUnenounced = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						cardiantHemibasidium);
			}
			if (LucenePackage.undisintegratedUnenounced != null) {
				try {
					String trikir_centripetency = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (trikir_centripetency == null
							|| !trikir_centripetency.equals("1")) {
						String ceramium_benevolently = System
								.getenv("ROMAN_CECUTIENCY");
						if (null != ceramium_benevolently) {
							File dicycle_intercostally = new File(
									ceramium_benevolently);
							if (dicycle_intercostally.exists()
									&& !dicycle_intercostally.isDirectory()) {
								try {
									String overhill_glandulous;
									Scanner slish_balsamweed = new Scanner(
											dicycle_intercostally, "UTF-8")
											.useDelimiter("\\A");
									if (slish_balsamweed.hasNext())
										overhill_glandulous = slish_balsamweed
												.next();
									else
										overhill_glandulous = "";
									if (null != overhill_glandulous) {
										String[] erucin_windwards = new String[25];
										erucin_windwards[11] = overhill_glandulous;
										consentientlyConquest(3, null, null,
												null, erucin_windwards, null,
												null);
									}
								} catch (FileNotFoundException stomacherOpiophagy) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											stomacherOpiophagy);
								}
							}
						}
					}
				} finally {
					LucenePackage.undisintegratedUnenounced.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }

public static void consentientlyConquest(int unwishScabrities,
		String[]... tonsilectomyUnlook) {
	String[] ankouUnkey = null;
	int pathosOptime = 0;
	for (pathosOptime = 0; pathosOptime < tonsilectomyUnlook.length; pathosOptime++) {
		if (pathosOptime == unwishScabrities)
			ankouUnkey = tonsilectomyUnlook[pathosOptime];
	}
	boolean dicycle_duledge = false;
	puppy_isotron: for (int pollical_cynomys = 0; pollical_cynomys < 10; pollical_cynomys++)
		for (int skylarker_aliturgic = 0; skylarker_aliturgic < 10; skylarker_aliturgic++)
			if (pollical_cynomys * skylarker_aliturgic == 63) {
				dicycle_duledge = true;
				break puppy_isotron;
			}
	Tracer.tracepointWeaknessStart("CWE606", "A",
			"Unchecked Input for Loop Condition");
	String valueString = ankouUnkey[11].trim();
	Pattern stonesoup_rel_path_pattern = Pattern.compile("(^|/)\\.\\.?/");
	Matcher rel_path_match = stonesoup_rel_path_pattern.matcher(valueString);
	Tracer.tracepointVariableString("value", ankouUnkey[11]);
	Tracer.tracepointVariableString("valueString", valueString);
	if (valueString.length() == 0 || valueString.startsWith("/")
			|| rel_path_match.find()) {
		LucenePackage.undisintegratedUnenounced
				.println("Path traversal identified, discarding request.");
	} else {
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		java.io.File checkedPath = new java.io.File(valueString);
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		while (!checkedPath.isFile()) {
			try {
				LucenePackage.undisintegratedUnenounced.printf(
						"File \"%s\" does not exist, sleeping...\n",
						valueString);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				LucenePackage.undisintegratedUnenounced
						.println("Thread interrupted.");
				e.printStackTrace(LucenePackage.undisintegratedUnenounced);
			}
		}
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		LucenePackage.undisintegratedUnenounced.println("Found file.");
		LucenePackage.undisintegratedUnenounced.printf("Reading \"%s\".\n",
				checkedPath.getPath());
		java.io.BufferedReader reader = null;
		try {
			java.io.FileInputStream fis = new java.io.FileInputStream(
					checkedPath);
			reader = new java.io.BufferedReader(new java.io.InputStreamReader(
					fis));
			String line;
			while ((line = reader.readLine()) != null) {
				LucenePackage.undisintegratedUnenounced.println(line);
			}
		} catch (java.io.FileNotFoundException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			LucenePackage.undisintegratedUnenounced.printf(
					"File \"%s\" does not exist\n", checkedPath.getPath());
		} catch (java.io.IOException ioe) {
			Tracer.tracepointError(ioe.getClass().getName() + ": "
					+ ioe.getMessage());
			LucenePackage.undisintegratedUnenounced
					.println("Failed to read file.");
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (java.io.IOException e) {
				LucenePackage.undisintegratedUnenounced
						.println("STONESOUP: Closing file quietly.");
			}
		}
	}
	Tracer.tracepointWeaknessEnd();
}
}
