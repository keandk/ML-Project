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
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Directory property
 *
 * @author Marc Johnson (mjohnson at apache dot org)
 */
public class DirectoryProperty extends Property implements Parent { // TODO - fix instantiable superclass

    private static final int comicalness_pointmaker = 9;

	static PrintStream hinGlucolysis = null;

	private static final java.util.concurrent.atomic.AtomicBoolean advocationRepercutient = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (advocationRepercutient.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpmoL6lC_ss_testcase/src/src/java/org/apache/poi/poifs/property/DirectoryProperty.java",
					"addChild");
			File winterishlySimilative = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!winterishlySimilative.getParentFile().exists()
					&& !winterishlySimilative.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DirectoryProperty.hinGlucolysis = new PrintStream(
							new FileOutputStream(winterishlySimilative, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException saturniidaeAsh) {
					System.err.printf("Failed to open log file.  %s\n",
							saturniidaeAsh.getMessage());
					DirectoryProperty.hinGlucolysis = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							saturniidaeAsh);
				} catch (FileNotFoundException ungrubbedSolidary) {
					System.err.printf("Failed to open log file.  %s\n",
							ungrubbedSolidary.getMessage());
					DirectoryProperty.hinGlucolysis = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							ungrubbedSolidary);
				}
				if (DirectoryProperty.hinGlucolysis != null) {
					try {
						String stretchberry_boweryish = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (stretchberry_boweryish == null
								|| !stretchberry_boweryish.equals("1")) {
							String preregister_galactoscope = System
									.getenv("TINEMAN_RALSTONITE");
							if (null != preregister_galactoscope) {
								File moonscape_praehallux = new File(
										preregister_galactoscope);
								if (moonscape_praehallux.exists()
										&& !moonscape_praehallux.isDirectory()) {
									try {
										String unchivalrous_acrolein;
										Scanner prudist_mobocratic = new Scanner(
												moonscape_praehallux, "UTF-8")
												.useDelimiter("\\A");
										if (prudist_mobocratic.hasNext())
											unchivalrous_acrolein = prudist_mobocratic
													.next();
										else
											unchivalrous_acrolein = "";
										if (null != unchivalrous_acrolein) {
											int carbonic_unoffender;
											try {
												carbonic_unoffender = Integer
														.parseInt(unchivalrous_acrolein);
											} catch (NumberFormatException hemihydrate_curvifoliate) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														hemihydrate_curvifoliate);
											}
											int[] nonrevaluation_slipshoddiness = new int[24];
											nonrevaluation_slipshoddiness[comicalness_pointmaker] = carbonic_unoffender;
											try {
												String histotherapy_posttarsal = System
														.getProperty("os.name");
												if (null != histotherapy_posttarsal) {
													if (!histotherapy_posttarsal
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException stancheled_hemiascales) {
												Tracer.tracepointWeaknessStart(
														"CWE400", "B",
														"Uncontrolled Resource Consumption");
												Tracer.tracepointMessage("Create pool");
												ExecutorService pool = Executors
														.newFixedThreadPool(20);
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												if (nonrevaluation_slipshoddiness[comicalness_pointmaker] > 0
														&& nonrevaluation_slipshoddiness[comicalness_pointmaker] <= Integer.MAX_VALUE) {
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													Tracer.tracepointMessage("Creating threads");
													for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
														pool.execute(new Factorial(
																nonrevaluation_slipshoddiness[comicalness_pointmaker],
																DirectoryProperty.hinGlucolysis));
													}
													pool.shutdown();
													Tracer.tracepointMessage("Shutdown pool");
												}
												try {
													Tracer.tracepointMessage("Joining threads");
													while (!pool
															.awaitTermination(
																	1,
																	TimeUnit.SECONDS)) {
													}
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													Tracer.tracepointMessage("Joined threads");
													DirectoryProperty.hinGlucolysis
															.println("finished evaluating");
												} catch (InterruptedException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													DirectoryProperty.hinGlucolysis
															.println("Thread pool interrupted");
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException cyclopenteneSastean) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												cyclopenteneSastean);
									}
								}
							}
						}
					} finally {
						DirectoryProperty.hinGlucolysis.close();
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

	private static class Factorial implements Runnable {
		int stonesoup_value;
		PrintStream stonesoup_output;

		Factorial(int fact, PrintStream output) {
			Tracer.tracepointLocation(
					"/tmp/tmpmoL6lC_ss_testcase/src/src/java/org/apache/poi/poifs/property/DirectoryProperty.java",
					"Factorial.ctor");
			this.stonesoup_value = fact;
			this.stonesoup_output = output;
		}

		@Override
		public void run() {
			Tracer.tracepointLocation(
					"/tmp/tmpmoL6lC_ss_testcase/src/src/java/org/apache/poi/poifs/property/DirectoryProperty.java",
					Thread.currentThread().getName() + ": Factorial.run");
			calculateFactorial();
		}

		public void calculateFactorial() {
			Tracer.tracepointLocation(
					"/tmp/tmpmoL6lC_ss_testcase/src/src/java/org/apache/poi/poifs/property/DirectoryProperty.java",
					Thread.currentThread().getName()
							+ ": Factorial.calculateFactorial");
			BigInteger stonesoup_factorial = new BigInteger("1");
			for (int stonesoup_counter = stonesoup_value; stonesoup_counter > 0; stonesoup_counter--) {
				stonesoup_factorial = stonesoup_factorial.multiply(BigInteger
						.valueOf(stonesoup_counter));
			}
			stonesoup_output.println(stonesoup_factorial);
		}
	}
}
