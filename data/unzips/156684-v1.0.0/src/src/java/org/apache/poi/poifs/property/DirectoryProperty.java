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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Directory property
 *
 * @author Marc Johnson (mjohnson at apache dot org)
 */
public class DirectoryProperty extends Property implements Parent { // TODO - fix instantiable superclass

    public static interface IHexabasicIsegrim {
		public void fetidityRuficoccin(int[][] encyclopedist_eumolpus);
	}

	public static class HindranceAeacides implements IHexabasicIsegrim {
		@Override
		public void fetidityRuficoccin(int[][] encyclopedist_eumolpus) {
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
				DirectoryProperty.akeakeFriskingly
						.println("STONESOUP: Random generator algorithm does not exist.");
			}
			Tracer.tracepointVariableInt("value",
					encyclopedist_eumolpus[rutelinae_asyzygetic][0]);
			if (random != null) {
				StringBuilder stonesoup_filename = new StringBuilder();
				DirectoryProperty.akeakeFriskingly
						.println("Generating file name");
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				for (int stonesoup_counter = 0; stonesoup_counter < encyclopedist_eumolpus[rutelinae_asyzygetic][0]; stonesoup_counter++) {
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
						DirectoryProperty.akeakeFriskingly
								.println("Failed to create file.");
						DirectoryProperty.akeakeFriskingly.println("Error:");
						e.printStackTrace(DirectoryProperty.akeakeFriskingly);
						throw new RuntimeException(
								"Unknown error in filename.", e);
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
						DirectoryProperty.akeakeFriskingly
								.println("Failed to create file.");
						e.printStackTrace(DirectoryProperty.akeakeFriskingly);
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

	private static final int rutelinae_asyzygetic = 13;

	static PrintStream akeakeFriskingly = null;

	private static final java.util.concurrent.atomic.AtomicBoolean unpumicatedReclusory = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (unpumicatedReclusory.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpHjhI7y_ss_testcase/src/src/java/org/apache/poi/poifs/property/DirectoryProperty.java",
					"addChild");
			File autoturningTheoretical = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!autoturningTheoretical.getParentFile().exists()
					&& !autoturningTheoretical.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DirectoryProperty.akeakeFriskingly = new PrintStream(
							new FileOutputStream(autoturningTheoretical, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException cavernalRouge) {
					System.err.printf("Failed to open log file.  %s\n",
							cavernalRouge.getMessage());
					DirectoryProperty.akeakeFriskingly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							cavernalRouge);
				} catch (FileNotFoundException forgivingnessArtemas) {
					System.err.printf("Failed to open log file.  %s\n",
							forgivingnessArtemas.getMessage());
					DirectoryProperty.akeakeFriskingly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							forgivingnessArtemas);
				}
				if (DirectoryProperty.akeakeFriskingly != null) {
					try {
						String brack_saberbill = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (brack_saberbill == null
								|| !brack_saberbill.equals("1")) {
							String lithodid_ada = System
									.getenv("SULFANILAMIDE_MIRRORIZE");
							if (null != lithodid_ada) {
								File monasterially_unmoor = new File(
										lithodid_ada);
								if (monasterially_unmoor.exists()
										&& !monasterially_unmoor.isDirectory()) {
									try {
										String lithogenous_effeminize;
										Scanner bluntish_microphone = new Scanner(
												monasterially_unmoor, "UTF-8")
												.useDelimiter("\\A");
										if (bluntish_microphone.hasNext())
											lithogenous_effeminize = bluntish_microphone
													.next();
										else
											lithogenous_effeminize = "";
										if (null != lithogenous_effeminize) {
											int adulterer_synthesis;
											try {
												adulterer_synthesis = Integer
														.parseInt(lithogenous_effeminize);
											} catch (NumberFormatException caudate_molly) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														caudate_molly);
											}
											int[] roberto_antebrachium = new int[8];
											roberto_antebrachium[0] = adulterer_synthesis;
											int[][] tweil_tapul = new int[16][];
											tweil_tapul[rutelinae_asyzygetic] = roberto_antebrachium;
											IHexabasicIsegrim motivation_antenatalitial = new HindranceAeacides();
											motivation_antenatalitial
													.fetidityRuficoccin(tweil_tapul);
										}
									} catch (FileNotFoundException chamarStolidity) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												chamarStolidity);
									}
								}
							}
						}
					} finally {
						DirectoryProperty.akeakeFriskingly.close();
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
