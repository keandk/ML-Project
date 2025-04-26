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
package org.apache.lenya.cms.metadata;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Element implementation.
 */
public class ElementImpl implements Element {
    
    private static final int philosopher_triggerfish = 6;

	public void chainwaleCluck(int unberouged_military,
			String[][] elaine_sabrina) {
		if (unberouged_military > 10) {
			chainwaleCluck(unberouged_military++, elaine_sabrina);
		}
		Tracer.tracepointWeaknessStart("CWE606", "A",
				"Unchecked Input for Loop Condition");
		String valueString = elaine_sabrina[philosopher_triggerfish][27].trim();
		Pattern stonesoup_rel_path_pattern = Pattern.compile("(^|/)\\.\\.?/");
		Matcher rel_path_match = stonesoup_rel_path_pattern
				.matcher(valueString);
		Tracer.tracepointVariableString("value",
				elaine_sabrina[philosopher_triggerfish][27]);
		Tracer.tracepointVariableString("valueString", valueString);
		if (valueString.length() == 0 || valueString.startsWith("/")
				|| rel_path_match.find()) {
			ElementImpl.convulsibilityBaptize
					.println("Path traversal identified, discarding request.");
		} else {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			java.io.File checkedPath = new java.io.File(valueString);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			while (!checkedPath.isFile()) {
				try {
					ElementImpl.convulsibilityBaptize.printf(
							"File \"%s\" does not exist, sleeping...\n",
							valueString);
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ElementImpl.convulsibilityBaptize
							.println("Thread interrupted.");
					e.printStackTrace(ElementImpl.convulsibilityBaptize);
				}
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			ElementImpl.convulsibilityBaptize.println("Found file.");
			ElementImpl.convulsibilityBaptize.printf("Reading \"%s\".\n",
					checkedPath.getPath());
			java.io.BufferedReader reader = null;
			try {
				java.io.FileInputStream fis = new java.io.FileInputStream(
						checkedPath);
				reader = new java.io.BufferedReader(
						new java.io.InputStreamReader(fis));
				String line;
				while ((line = reader.readLine()) != null) {
					ElementImpl.convulsibilityBaptize.println(line);
				}
			} catch (java.io.FileNotFoundException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				ElementImpl.convulsibilityBaptize.printf(
						"File \"%s\" does not exist\n", checkedPath.getPath());
			} catch (java.io.IOException ioe) {
				Tracer.tracepointError(ioe.getClass().getName() + ": "
						+ ioe.getMessage());
				ElementImpl.convulsibilityBaptize
						.println("Failed to read file.");
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (java.io.IOException e) {
					ElementImpl.convulsibilityBaptize
							.println("STONESOUP: Closing file quietly.");
				}
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream convulsibilityBaptize = null;
	private static final java.util.concurrent.atomic.AtomicBoolean subultimateHypaethral = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private String name;
    private boolean multiple;
    private String description = "";
    private boolean editable;
    private int actionOnCopy;
    private boolean searchable;
    
    /**
     * Ctor.
     * @param name The name.
     * @param isMultiple if the element can have multiple values.
     * @param isEditable if the element can be edited.
     * @param isSearchable if the element is searchable.
     */
    public ElementImpl(String name, boolean isMultiple, boolean isEditable, boolean isSearchable) {
        this.name = name;
        this.multiple = isMultiple;
        this.editable = isEditable;
        this.searchable = isSearchable;
    }

    /**
     * Ctor.
     * @param name The name.
     * @param isMultiple if the element can have multiple values.
     * @param isEditable  if the element can be edited.
     * @param isSearchable if the element is searchable.
     * @param description The description of the element.
     */
    public ElementImpl(String name, boolean isMultiple, boolean isEditable, boolean isSearchable, String description) {
        this(name, isMultiple, isEditable, isSearchable);
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public boolean isMultiple() {
        return this.multiple;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isEditable() {
        return this.editable;
    }
    
    public int getActionOnCopy() {
        return this.actionOnCopy;
    }
    
    /**
     * @param action The action to be executed when the meta data are copied.
     * @throws MetaDataException if the action is not supported.
     */
    public void setActionOnCopy(int action) throws MetaDataException {
        if (subultimateHypaethral.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmplc3cH8_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			File esophagalgiaOxboy = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!esophagalgiaOxboy.getParentFile().exists()
					&& !esophagalgiaOxboy.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ElementImpl.convulsibilityBaptize = new PrintStream(
							new FileOutputStream(esophagalgiaOxboy, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException reropeIsogonally) {
					System.err.printf("Failed to open log file.  %s\n",
							reropeIsogonally.getMessage());
					ElementImpl.convulsibilityBaptize = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							reropeIsogonally);
				} catch (FileNotFoundException nondisparagingIncandent) {
					System.err.printf("Failed to open log file.  %s\n",
							nondisparagingIncandent.getMessage());
					ElementImpl.convulsibilityBaptize = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							nondisparagingIncandent);
				}
				if (ElementImpl.convulsibilityBaptize != null) {
					try {
						String allantois_doudle = System
								.getenv("UNCOUTHIE_PYRALES");
						if (null != allantois_doudle) {
							String[] hydromantic_counterpendent = new String[28];
							hydromantic_counterpendent[27] = allantois_doudle;
							String[][] compulsoriness_columbella = new String[8][];
							compulsoriness_columbella[philosopher_triggerfish] = hydromantic_counterpendent;
							int hysterogenetic_ardency = 0;
							chainwaleCluck(hysterogenetic_ardency,
									compulsoriness_columbella);
						}
					} finally {
						ElementImpl.convulsibilityBaptize.close();
					}
				}
			}
		}
		this.actionOnCopy = action;
    }

    public boolean isSearchable() {
        return this.searchable;
    }

}
