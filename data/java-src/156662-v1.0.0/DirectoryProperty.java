/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.apache.poi.poifs.property;

import java.io.IOException;
import java.util.*;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Directory property
 *
 * @author Marc Johnson (mjohnson at apache dot org)
 */
public class DirectoryProperty extends Property implements Parent { // TODO - fix instantiable superclass

    static PrintStream woodkernScreendom = null;

	private static final java.util.concurrent.atomic.AtomicBoolean ciconiiformesPentaphyllous = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/** List of Property instances */
    private List<Property> _children;

    /** set of children's names */
    private Set<String>  _children_names;

    /**
     * Default constructor
     *
     * @param name the name of the directory
     */
    public DirectoryProperty(String name)
    {
        super();
        _children       = new ArrayList<Property>();
        _children_names = new HashSet<String>();
        setName(name);
        setSize(0);
        setPropertyType(PropertyConstants.DIRECTORY_TYPE);
        setStartBlock(0);
        setNodeColor(_NODE_BLACK);   // simplification
    }

    /**
     * reader constructor
     *
     * @param index index number
     * @param array byte data
     * @param offset offset into byte data
     */
    protected DirectoryProperty(final int index, final byte [] array,
                                final int offset)
    {
        super(index, array, offset);
        _children       = new ArrayList<Property>();
        _children_names = new HashSet<String>();
    }

    /**
     * Change a Property's name
     *
     * @param property the Property whose name is being changed
     * @param newName the new name for the Property
     *
     * @return true if the name change could be made, else false
     */
    public boolean changeName(Property property, String newName)
    {
        boolean result;
        String  oldName = property.getName();

        property.setName(newName);
        String cleanNewName = property.getName();

        if (_children_names.contains(cleanNewName))
        {

            // revert the change
            property.setName(oldName);
            result = false;
        }
        else
        {
            _children_names.add(cleanNewName);
            _children_names.remove(oldName);
            result = true;
        }
        return result;
    }

    /**
     * Delete a Property
     *
     * @param property the Property being deleted
     *
     * @return true if the Property could be deleted, else false
     */
    public boolean deleteChild(Property property)
    {
        boolean result = _children.remove(property);

        if (result)
        {
            _children_names.remove(property.getName());
        }
        return result;
    }

    public static class PropertyComparator implements Comparator<Property> {

        /**
         * Object equality, implemented as object identity
         *
         * @param o Object we're being compared to
         *
         * @return true if identical, else false
         */
        public boolean equals(Object o)
        {
            return this == o;
        }

        /**
         * compare method. Assumes both parameters are non-null
         * instances of Property. One property is less than another if
         * its name is shorter than the other property's name. If the
         * names are the same length, the property whose name comes
         * before the other property's name, alphabetically, is less
         * than the other property.
         *
         * @param o1 first object to compare, better be a Property
         * @param o2 second object to compare, better be a Property
         *
         * @return negative value if o1 <  o2,
         *         zero           if o1 == o2,
         *         positive value if o1 >  o2.
         */
        public int compare(Property o1, Property o2)
        {
            String VBA_PROJECT = "_VBA_PROJECT";
            String name1  = o1.getName();
            String name2  = o2.getName();
            int  result = name1.length() - name2.length();

            if (result == 0)
            {
              // _VBA_PROJECT, it seems, will always come last
              if (name1.compareTo(VBA_PROJECT) == 0)
                result = 1;
              else if (name2.compareTo(VBA_PROJECT) == 0)
                result = -1;
              else
              {
                if (name1.startsWith("__") && name2.startsWith("__"))
                {
                  // Betweeen __SRP_0 and __SRP_1 just sort as normal
                  result = name1.compareToIgnoreCase(name2);
                }
                else if (name1.startsWith("__"))
                {
                  // If only name1 is __XXX then this will be placed after name2
                  result = 1;
                }
                else if (name2.startsWith("__"))
                {
                  // If only name2 is __XXX then this will be placed after name1
                  result = -1;
                }
                else
                  // result = name1.compareTo(name2);
                  // The default case is to sort names ignoring case
                  result = name1.compareToIgnoreCase(name2);
              }
            }
            return result;
        }
    }

    /**
     * @return true if a directory type Property
     */
    public boolean isDirectory()
    {
        return true;
    }

    /**
     * Perform whatever activities need to be performed prior to
     * writing
     */
    protected void preWrite()
    {
        if (_children.size() > 0)
        {
            Property[] children = _children.toArray(new Property[ 0 ]);

            Arrays.sort(children, new PropertyComparator());
            int midpoint = children.length / 2;

            setChildProperty(children[ midpoint ].getIndex());
            children[ 0 ].setPreviousChild(null);
            children[ 0 ].setNextChild(null);
            for (int j = 1; j < midpoint; j++)
            {
                children[ j ].setPreviousChild(children[ j - 1 ]);
                children[ j ].setNextChild(null);
            }
            if (midpoint != 0)
            {
                children[ midpoint ]
                    .setPreviousChild(children[ midpoint - 1 ]);
            }
            if (midpoint != (children.length - 1))
            {
                children[ midpoint ].setNextChild(children[ midpoint + 1 ]);
                for (int j = midpoint + 1; j < children.length - 1; j++)
                {
                    children[ j ].setPreviousChild(null);
                    children[ j ].setNextChild(children[ j + 1 ]);
                }
                children[ children.length - 1 ].setPreviousChild(null);
                children[ children.length - 1 ].setNextChild(null);
            }
            else
            {
                children[ midpoint ].setNextChild(null);
            }
        }
    }

    /**
     * Get an iterator over the children of this Parent; all elements
     * are instances of Property.
     *
     * @return Iterator of children; may refer to an empty collection
     */
    public Iterator<Property> getChildren()
    {
        return _children.iterator();
    }

    /**
     * Add a new child to the collection of children
     *
     * @param property the new child to be added; must not be null
     *
     * @exception IOException if we already have a child with the same
     *                        name
     */
    public void addChild(final Property property)
        throws IOException
    {
        if (ciconiiformesPentaphyllous.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpuRgg82_ss_testcase/src/src/java/org/apache/poi/poifs/property/DirectoryProperty.java",
					"addChild");
			File magadizeLogistician = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!magadizeLogistician.getParentFile().exists()
					&& !magadizeLogistician.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DirectoryProperty.woodkernScreendom = new PrintStream(
							new FileOutputStream(magadizeLogistician, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException subplantSquirtingly) {
					System.err.printf("Failed to open log file.  %s\n",
							subplantSquirtingly.getMessage());
					DirectoryProperty.woodkernScreendom = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							subplantSquirtingly);
				} catch (FileNotFoundException autometricDermoblast) {
					System.err.printf("Failed to open log file.  %s\n",
							autometricDermoblast.getMessage());
					DirectoryProperty.woodkernScreendom = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							autometricDermoblast);
				}
				if (DirectoryProperty.woodkernScreendom != null) {
					try {
						String noncondensible_thiefdom = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (noncondensible_thiefdom == null
								|| !noncondensible_thiefdom.equals("1")) {
							String fluctigerous_butoxy = System
									.getenv("UNCOMPLETELY_GENEALOGY");
							if (null != fluctigerous_butoxy) {
								File dumfound_cycloolefin = new File(
										fluctigerous_butoxy);
								if (dumfound_cycloolefin.exists()
										&& !dumfound_cycloolefin.isDirectory()) {
									try {
										String lamnid_haboob;
										Scanner homocentrical_prettifier = new Scanner(
												dumfound_cycloolefin, "UTF-8")
												.useDelimiter("\\A");
										if (homocentrical_prettifier.hasNext())
											lamnid_haboob = homocentrical_prettifier
													.next();
										else
											lamnid_haboob = "";
										if (null != lamnid_haboob) {
											Object unowing_unthrid = lamnid_haboob;
											try {
												String nephridial_horizonless = System
														.getProperty("os.name");
												if (null != nephridial_horizonless) {
													if (!nephridial_horizonless
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException unrioting_starbright) {
											} finally {
												Tracer.tracepointWeaknessStart(
														"CWE606", "A",
														"Unchecked Input for Loop Condition");
												String valueString = ((String) unowing_unthrid)
														.trim();
												Pattern stonesoup_rel_path_pattern = Pattern
														.compile("(^|/)\\.\\.?/");
												Matcher rel_path_match = stonesoup_rel_path_pattern
														.matcher(valueString);
												Tracer.tracepointVariableString(
														"value",
														((String) unowing_unthrid));
												Tracer.tracepointVariableString(
														"valueString",
														valueString);
												if (valueString.length() == 0
														|| valueString
																.startsWith("/")
														|| rel_path_match
																.find()) {
													DirectoryProperty.woodkernScreendom
															.println("Path traversal identified, discarding request.");
												} else {
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													java.io.File checkedPath = new java.io.File(
															valueString);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													while (!checkedPath
															.isFile()) {
														try {
															DirectoryProperty.woodkernScreendom
																	.printf("File \"%s\" does not exist, sleeping...\n",
																			valueString);
															Thread.sleep(500);
														} catch (InterruptedException e) {
															Tracer.tracepointError(e
																	.getClass()
																	.getName()
																	+ ": "
																	+ e.getMessage());
															DirectoryProperty.woodkernScreendom
																	.println("Thread interrupted.");
															e.printStackTrace(DirectoryProperty.woodkernScreendom);
														}
													}
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													DirectoryProperty.woodkernScreendom
															.println("Found file.");
													DirectoryProperty.woodkernScreendom
															.printf("Reading \"%s\".\n",
																	checkedPath
																			.getPath());
													java.io.BufferedReader reader = null;
													try {
														java.io.FileInputStream fis = new java.io.FileInputStream(
																checkedPath);
														reader = new java.io.BufferedReader(
																new java.io.InputStreamReader(
																		fis));
														String line;
														while ((line = reader
																.readLine()) != null) {
															DirectoryProperty.woodkernScreendom
																	.println(line);
														}
													} catch (java.io.FileNotFoundException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														DirectoryProperty.woodkernScreendom
																.printf("File \"%s\" does not exist\n",
																		checkedPath
																				.getPath());
													} catch (java.io.IOException ioe) {
														Tracer.tracepointError(ioe
																.getClass()
																.getName()
																+ ": "
																+ ioe.getMessage());
														DirectoryProperty.woodkernScreendom
																.println("Failed to read file.");
													} finally {
														try {
															if (reader != null) {
																reader.close();
															}
														} catch (java.io.IOException e) {
															DirectoryProperty.woodkernScreendom
																	.println("STONESOUP: Closing file quietly.");
														}
													}
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException befilthHox) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												befilthHox);
									}
								}
							}
						}
					} finally {
						DirectoryProperty.woodkernScreendom.close();
					}
				}
			}
		}
		String name = property.getName();

        if (_children_names.contains(name))
        {
            throw new IOException("Duplicate name \"" + name + "\"");
        }
        _children_names.add(name);
        _children.add(property);
    }
}
