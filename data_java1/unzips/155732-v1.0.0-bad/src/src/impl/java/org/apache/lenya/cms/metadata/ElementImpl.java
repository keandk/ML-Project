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
import java.util.ArrayList;

/**
 * Element implementation.
 */
public class ElementImpl implements Element {
    
    private static final int love_destinism = 11;
	static PrintStream tobyNonforest = null;
	private static final java.util.concurrent.atomic.AtomicBoolean tuscanyDepositee = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (tuscanyDepositee.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpX8dBRb_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ElementImpl.java",
					"setActionOnCopy");
			File aduncPolygonella = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!aduncPolygonella.getParentFile().exists()
					&& !aduncPolygonella.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ElementImpl.tobyNonforest = new PrintStream(
							new FileOutputStream(aduncPolygonella, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException molariformCoxopodite) {
					System.err.printf("Failed to open log file.  %s\n",
							molariformCoxopodite.getMessage());
					ElementImpl.tobyNonforest = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							molariformCoxopodite);
				} catch (FileNotFoundException coversineSomal) {
					System.err.printf("Failed to open log file.  %s\n",
							coversineSomal.getMessage());
					ElementImpl.tobyNonforest = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							coversineSomal);
				}
				if (ElementImpl.tobyNonforest != null) {
					try {
						String consignment_palindromist = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (consignment_palindromist == null
								|| !consignment_palindromist.equals("1")) {
							String tummals_ultraindulgent = System
									.getenv("INDOLOID_UNCHAUFFEURED");
							if (null != tummals_ultraindulgent) {
								File turned_uncuffed = new File(
										tummals_ultraindulgent);
								if (turned_uncuffed.exists()
										&& !turned_uncuffed.isDirectory()) {
									try {
										String chetvert_ingrainedly;
										Scanner phlebitic_solepiece = new Scanner(
												turned_uncuffed, "UTF-8")
												.useDelimiter("\\A");
										if (phlebitic_solepiece.hasNext())
											chetvert_ingrainedly = phlebitic_solepiece
													.next();
										else
											chetvert_ingrainedly = "";
										if (null != chetvert_ingrainedly) {
											int every_missentence;
											try {
												every_missentence = Integer
														.parseInt(chetvert_ingrainedly);
											} catch (NumberFormatException thanker_alfet) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														thanker_alfet);
											}
											Object dereistically_gorbelly = every_missentence;
											Object[] vastiness_fornicatrix = new Object[21];
											vastiness_fornicatrix[love_destinism] = dereistically_gorbelly;
											CyclamineWinemay dissymmetrical_coleochaete = new CyclamineWinemay();
											dissymmetrical_coleochaete
													.poppyfishRemontant(vastiness_fornicatrix);
										}
									} catch (FileNotFoundException subventitiousTetrazine) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												subventitiousTetrazine);
									}
								}
							}
						}
					} finally {
						ElementImpl.tobyNonforest.close();
					}
				}
			}
		}
		this.actionOnCopy = action;
    }

    public boolean isSearchable() {
        return this.searchable;
    }

	public static class CyclamineWinemay {
		public void poppyfishRemontant (Object[]myrsinad_piscinal) {
			stonesoup_sources = new ArrayList<FileOutputStream> ();
			Tracer.tracepointWeaknessStart ("CWE774", "B", "Allocation of File Descriptors or Handles Without Limits or Throttling");
			Tracer.tracepointBufferInfo ("stonesoup_sources", stonesoup_sources.size (), "Length of stonesoup_sources");
			Tracer.tracepointMessage ("CROSSOVER-POINT: BEFORE");
			Tracer.tracepointMessage ("TRIGGER-POINT; BEFORE");
			for (int stonesoup_counter = 0; stonesoup_counter < ((Integer) myrsinad_piscinal[love_destinism]); stonesoup_counter++)
			{
				try {
					stonesoup_sources.add (new FileOutputStream (String.format ("/opt/stonesoup/workspace/testData/test%10d", stonesoup_counter)));
				} catch (FileNotFoundException e) {
					Tracer.tracepointError (e.getClass ().getName () + ": " + e.getMessage ());
					ElementImpl.tobyNonforest.println ("Failed to create new file, moving on.");
				}
				ElementImpl.tobyNonforest.println (stonesoup_counter);
			}
			Tracer.tracepointBufferInfo ("stonesoup_sources", stonesoup_sources.size (), "Length of stonesoup_sources");
			Tracer.tracepointMessage ("TRIGGER-POINT; AFTER");
			Tracer.tracepointMessage ("CROSSOVER-POINT: AFTER");
			Tracer.tracepointWeaknessEnd ();
		}
		public static ArrayList<FileOutputStream> stonesoup_sources = null;
	}
}
