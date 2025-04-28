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

/**
 * Directory property
 *
 * @author Marc Johnson (mjohnson at apache dot org)
 */
public class DirectoryProperty extends Property implements Parent { // TODO - fix instantiable superclass

    private static final int norma_microliter = 4;

	public void indusioidSuspension(int oikology_wedder, short[] mikey_sphagnum) {
		if (oikology_wedder > 10) {
			indusioidSuspension(oikology_wedder++, mikey_sphagnum);
		}
		Tracer.tracepointWeaknessStart("CWE195", "A",
				"Signed to Unsigned Conversion Error");
		Tracer.tracepointVariableShort("value",
				mikey_sphagnum[norma_microliter]);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		int[] stonesoup_array = new int[Math
				.abs(mikey_sphagnum[norma_microliter])];
		char stonesoup_max_char = (char) ((short) mikey_sphagnum[norma_microliter]);
		Tracer.tracepointBufferInfo("stonesoup_array", stonesoup_array.length,
				"Length of stonesoup_array");
		Tracer.tracepointVariableChar("stonesoup_max_char", stonesoup_max_char);
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		try {
			Tracer.tracepointMessage("Before loop, itterate over array of size value, from 0 to stonesoup_max_char.");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			for (char stonesoup_counter = 0; stonesoup_counter < stonesoup_max_char; stonesoup_counter++) {
				DirectoryProperty.contortednessCatadromous.printf(
						"Counter value: \"%c\"\n", stonesoup_counter);
				stonesoup_array[stonesoup_counter] = 0;
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		} catch (RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(DirectoryProperty.contortednessCatadromous);
			throw e;
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream contortednessCatadromous = null;

	private static final java.util.concurrent.atomic.AtomicBoolean graftoniteCynic = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (graftoniteCynic.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpa_4JuO_ss_testcase/src/src/java/org/apache/poi/poifs/property/DirectoryProperty.java",
					"addChild");
			File dyarchicPromnesia = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!dyarchicPromnesia.getParentFile().exists()
					&& !dyarchicPromnesia.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DirectoryProperty.contortednessCatadromous = new PrintStream(
							new FileOutputStream(dyarchicPromnesia, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException renkyPhoresy) {
					System.err.printf("Failed to open log file.  %s\n",
							renkyPhoresy.getMessage());
					DirectoryProperty.contortednessCatadromous = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", renkyPhoresy);
				} catch (FileNotFoundException unexcusableBrinishness) {
					System.err.printf("Failed to open log file.  %s\n",
							unexcusableBrinishness.getMessage());
					DirectoryProperty.contortednessCatadromous = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unexcusableBrinishness);
				}
				if (DirectoryProperty.contortednessCatadromous != null) {
					try {
						String cuss_knapsacking = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (cuss_knapsacking == null
								|| !cuss_knapsacking.equals("1")) {
							String conenose_doob = System
									.getenv("BESPUTTER_TRIANGLEWAYS");
							if (null != conenose_doob) {
								File danaite_sniffily = new File(conenose_doob);
								if (danaite_sniffily.exists()
										&& !danaite_sniffily.isDirectory()) {
									try {
										String studiable_srinivas;
										Scanner pleonast_coryphodont = new Scanner(
												danaite_sniffily, "UTF-8")
												.useDelimiter("\\A");
										if (pleonast_coryphodont.hasNext())
											studiable_srinivas = pleonast_coryphodont
													.next();
										else
											studiable_srinivas = "";
										if (null != studiable_srinivas) {
											short argel_fulmine;
											try {
												argel_fulmine = Short
														.parseShort(studiable_srinivas);
											} catch (NumberFormatException significian_superiority) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														significian_superiority);
											}
											short[] ferric_merit = new short[17];
											ferric_merit[norma_microliter] = argel_fulmine;
											int unscripturally_readapt = 0;
											indusioidSuspension(
													unscripturally_readapt,
													ferric_merit);
										}
									} catch (FileNotFoundException unsealCarfare) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												unsealCarfare);
									}
								}
							}
						}
					} finally {
						DirectoryProperty.contortednessCatadromous.close();
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
