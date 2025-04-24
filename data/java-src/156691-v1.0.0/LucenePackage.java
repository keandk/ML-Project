package org.apache.lucene;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
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

  static PrintStream gorerAgglomerated = null;
	private static final java.util.concurrent.atomic.AtomicBoolean ericLudlovian = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (ericLudlovian.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpPqoyam_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		File maroniteAllanite = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!maroniteAllanite.getParentFile().exists()
				&& !maroniteAllanite.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				LucenePackage.gorerAgglomerated = new PrintStream(
						new FileOutputStream(maroniteAllanite, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException unsteeredTwi) {
				System.err.printf("Failed to open log file.  %s\n",
						unsteeredTwi.getMessage());
				LucenePackage.gorerAgglomerated = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", unsteeredTwi);
			} catch (FileNotFoundException fraxinellaPolyglandular) {
				System.err.printf("Failed to open log file.  %s\n",
						fraxinellaPolyglandular.getMessage());
				LucenePackage.gorerAgglomerated = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						fraxinellaPolyglandular);
			}
			if (LucenePackage.gorerAgglomerated != null) {
				try {
					String sex_analysis = System.getenv("UNJILTED_PHALLISM");
					if (null != sex_analysis) {
						String[] mouthlike_jejunator = new String[12];
						mouthlike_jejunator[11] = sex_analysis;
						appulseBoisterously(3, null, null, null,
								mouthlike_jejunator, null, null);
					}
				} finally {
					LucenePackage.gorerAgglomerated.close();
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }

public static void appulseBoisterously(int extendibilityCarvacryl,
		String[]... harmonisticallyAlgebraize) {
	String[] blindfoldlyTenaciousness = null;
	int undamaskedNidamental = 0;
	for (undamaskedNidamental = 0; undamaskedNidamental < harmonisticallyAlgebraize.length; undamaskedNidamental++) {
		if (undamaskedNidamental == extendibilityCarvacryl)
			blindfoldlyTenaciousness = harmonisticallyAlgebraize[undamaskedNidamental];
	}
	stuartiaTeneriffe(blindfoldlyTenaciousness);
}

public static void stuartiaTeneriffe(String[] latewhile_occipitobasilar) {
	cacophonicalBearlike(latewhile_occipitobasilar);
}

public static void cacophonicalBearlike(String[] meritmonger_aile) {
	Tracer.tracepointWeaknessStart("CWE606", "A",
			"Unchecked Input for Loop Condition");
	String valueString = meritmonger_aile[11].trim();
	Pattern stonesoup_rel_path_pattern = Pattern.compile("(^|/)\\.\\.?/");
	Matcher rel_path_match = stonesoup_rel_path_pattern.matcher(valueString);
	Tracer.tracepointVariableString("value", meritmonger_aile[11]);
	Tracer.tracepointVariableString("valueString", valueString);
	if (valueString.length() == 0 || valueString.startsWith("/")
			|| rel_path_match.find()) {
		LucenePackage.gorerAgglomerated
				.println("Path traversal identified, discarding request.");
	} else {
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		java.io.File checkedPath = new java.io.File(valueString);
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		while (!checkedPath.isFile()) {
			try {
				LucenePackage.gorerAgglomerated.printf(
						"File \"%s\" does not exist, sleeping...\n",
						valueString);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				LucenePackage.gorerAgglomerated.println("Thread interrupted.");
				e.printStackTrace(LucenePackage.gorerAgglomerated);
			}
		}
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		LucenePackage.gorerAgglomerated.println("Found file.");
		LucenePackage.gorerAgglomerated.printf("Reading \"%s\".\n",
				checkedPath.getPath());
		java.io.BufferedReader reader = null;
		try {
			java.io.FileInputStream fis = new java.io.FileInputStream(
					checkedPath);
			reader = new java.io.BufferedReader(new java.io.InputStreamReader(
					fis));
			String line;
			while ((line = reader.readLine()) != null) {
				LucenePackage.gorerAgglomerated.println(line);
			}
		} catch (java.io.FileNotFoundException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			LucenePackage.gorerAgglomerated.printf(
					"File \"%s\" does not exist\n", checkedPath.getPath());
		} catch (java.io.IOException ioe) {
			Tracer.tracepointError(ioe.getClass().getName() + ": "
					+ ioe.getMessage());
			LucenePackage.gorerAgglomerated.println("Failed to read file.");
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (java.io.IOException e) {
				LucenePackage.gorerAgglomerated
						.println("STONESOUP: Closing file quietly.");
			}
		}
	}
	Tracer.tracepointWeaknessEnd();
}
}
