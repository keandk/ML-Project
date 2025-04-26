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
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Element implementation.
 */
public class ElementImpl implements Element {
    
    static PrintStream electrodynamicLogman = null;
	private static final java.util.concurrent.atomic.AtomicBoolean lecideaceousHole = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (lecideaceousHole.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpPg_H_o_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			File atechnicalGarish = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!atechnicalGarish.getParentFile().exists()
					&& !atechnicalGarish.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ElementImpl.electrodynamicLogman = new PrintStream(
							new FileOutputStream(atechnicalGarish, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException extricatedUnceremented) {
					System.err.printf("Failed to open log file.  %s\n",
							extricatedUnceremented.getMessage());
					ElementImpl.electrodynamicLogman = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							extricatedUnceremented);
				} catch (FileNotFoundException isothiocyanicSemifictional) {
					System.err.printf("Failed to open log file.  %s\n",
							isothiocyanicSemifictional.getMessage());
					ElementImpl.electrodynamicLogman = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							isothiocyanicSemifictional);
				}
				if (ElementImpl.electrodynamicLogman != null) {
					try {
						String impledge_pate = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (impledge_pate == null || !impledge_pate.equals("1")) {
							String doudle_prewitness = System
									.getenv("MILLENARIANISM_TUNNELLIKE");
							if (null != doudle_prewitness) {
								File celiomyodynia_belatticed = new File(
										doudle_prewitness);
								if (celiomyodynia_belatticed.exists()
										&& !celiomyodynia_belatticed
												.isDirectory()) {
									try {
										String ceratomania_transnational;
										Scanner chirurgeon_contraproposal = new Scanner(
												celiomyodynia_belatticed,
												"UTF-8").useDelimiter("\\A");
										if (chirurgeon_contraproposal.hasNext())
											ceratomania_transnational = chirurgeon_contraproposal
													.next();
										else
											ceratomania_transnational = "";
										if (null != ceratomania_transnational) {
											int secundation_anergia;
											try {
												secundation_anergia = Integer
														.parseInt(ceratomania_transnational);
											} catch (NumberFormatException supersubtle_umbelliferone) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														supersubtle_umbelliferone);
											}
											Object accelerator_relink = secundation_anergia;
											endotrophicNexum(3, null, null,
													null, accelerator_relink,
													null, null);
										}
									} catch (FileNotFoundException undevelopingPosteroclusion) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												undevelopingPosteroclusion);
									}
								}
							}
						}
					} finally {
						ElementImpl.electrodynamicLogman.close();
					}
				}
			}
		}
		this.actionOnCopy = action;
    }

    public boolean isSearchable() {
        return this.searchable;
    }

	public void endotrophicNexum(int mesennaUnfairly,
			Object... upseyUltramicroscope) {
		Object quartettoMiliaria = null;
		int camphyleneBrutter = 0;
		for (camphyleneBrutter = 0; camphyleneBrutter < upseyUltramicroscope.length; camphyleneBrutter++) {
			if (camphyleneBrutter == mesennaUnfairly)
				quartettoMiliaria = upseyUltramicroscope[camphyleneBrutter];
		}
		boolean volumist_orchestre = false;
		sketchiness_wharp: for (int orality_loessial = 0; orality_loessial < 10; orality_loessial++)
			for (int dipeptide_haemulidae = 0; dipeptide_haemulidae < 10; dipeptide_haemulidae++)
				if (orality_loessial * dipeptide_haemulidae == 63) {
					volumist_orchestre = true;
					break sketchiness_wharp;
				}
		Tracer.tracepointWeaknessStart("CWE606", "B",
				"Uncheck Input for Loop Condition");
		char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
				.toCharArray();
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			ElementImpl.electrodynamicLogman
					.println("STONESOUP: Random generator algorithm does not exist.");
		}
		Tracer.tracepointVariableInt("value", ((Integer) quartettoMiliaria));
		if (random != null) {
			StringBuilder stonesoup_filename = new StringBuilder();
			ElementImpl.electrodynamicLogman.println("Generating file name");
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			for (int stonesoup_counter = 0; stonesoup_counter < ((Integer) quartettoMiliaria); stonesoup_counter++) {
				stonesoup_filename.append(stonesoup_random_charset[random
						.nextInt(stonesoup_random_charset.length)]);
			}
			Tracer.tracepointVariableString("stonesoup_filename",
					stonesoup_filename.toString());
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			if (stonesoup_filename.length() > 0) {
				File writePath = new File(stonesoup_filename.toString());
				try {
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					writePath.createNewFile();
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				} catch (IOException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ElementImpl.electrodynamicLogman
							.println("Failed to create file.");
					ElementImpl.electrodynamicLogman.println("Error:");
					e.printStackTrace(ElementImpl.electrodynamicLogman);
					throw new RuntimeException("Unknown error in filename.", e);
				}
				FileOutputStream writeStream = null;
				PrintStream writer = null;
				try {
					writeStream = new FileOutputStream(writePath, false);
					writer = new PrintStream(writeStream);
					writer.println("/* This is my file */");
				} catch (FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ElementImpl.electrodynamicLogman
							.println("Failed to create file.");
					e.printStackTrace(ElementImpl.electrodynamicLogman);
				} finally {
					if (writer != null) {
						writer.close();
					}
				}
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

}
