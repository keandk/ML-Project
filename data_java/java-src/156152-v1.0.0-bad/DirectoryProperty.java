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

    static PrintStream thousandweightPitapatation = null;

	public void ketoheptoseParelectronomy(int preadjectival_handistroke,
			final String[] malpublication_aptotic) {
		preadjectival_handistroke--;
		if (preadjectival_handistroke > 0) {
			hanafiUncourteous(preadjectival_handistroke, malpublication_aptotic);
		}
	}

	public void hanafiUncourteous(int pinnotheridae_noncancellable,final String[] malpublication_aptotic){ketoheptoseParelectronomy(pinnotheridae_noncancellable,malpublication_aptotic);Tracer.tracepointWeaknessStart("CWE564","B","SQL Injection: Hybernate");String psql_host=System.getenv("DBPGHOST");String psql_user=System.getenv("DBPGUSER");String psql_pass=System.getenv("DBPGPASSWORD");String psql_port=System.getenv("DBPGPORT");String psql_dbname=System.getenv("SS_DBPGDATABASE");Tracer.tracepointVariableString("psql_host",psql_host);Tracer.tracepointVariableString("psql_user",psql_user);Tracer.tracepointVariableString("psql_pass",psql_pass);Tracer.tracepointVariableString("psql_port",psql_port);Tracer.tracepointVariableString("psql_dbname",psql_dbname);Tracer.tracepointVariableString("valueString",malpublication_aptotic[6]);if (malpublication_aptotic[6] != null && psql_host != null && psql_user != null && psql_pass != null && psql_port != null && psql_dbname != null){try {Tracer.tracepointMessage("Setting up hibernate connection.");org.hibernate.cfg.Configuration cfg=new org.hibernate.cfg.Configuration();cfg.setProperty("hibernate.connection.url","jdbc:postgresql://" + psql_host+":"+psql_port+"/"+psql_dbname);cfg.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");cfg.setProperty("hibernate.connection.driver_class","org.postgresql.Driver");cfg.setProperty("hibernate.connection.username",psql_user);cfg.setProperty("hibernate.connection.password",psql_pass);cfg.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");cfg.setProperty("hibernate.current_session_context_class","thread");cfg.setProperty("org.hibernate.flushMode","COMMIT");cfg.setProperty("hibernate.hbm2ddl.auto","validate");cfg.setProperty("hibernate.connection.pool_size","1");cfg.addClass(SS_CWE_564_POSTGRES.Categories.class);cfg.addClass(SS_CWE_564_POSTGRES.Customercustomerdemo.class);cfg.addClass(SS_CWE_564_POSTGRES.Customerdemographics.class);cfg.addClass(SS_CWE_564_POSTGRES.Customers.class);cfg.addClass(SS_CWE_564_POSTGRES.Employees.class);cfg.addClass(SS_CWE_564_POSTGRES.Employeeterritories.class);cfg.addClass(SS_CWE_564_POSTGRES.OrderDetails.class);cfg.addClass(SS_CWE_564_POSTGRES.Orders.class);cfg.addClass(SS_CWE_564_POSTGRES.Products.class);cfg.addClass(SS_CWE_564_POSTGRES.Region.class);cfg.addClass(SS_CWE_564_POSTGRES.Shippers.class);cfg.addClass(SS_CWE_564_POSTGRES.ShippersTmp.class);cfg.addClass(SS_CWE_564_POSTGRES.Suppliers.class);cfg.addClass(SS_CWE_564_POSTGRES.Territories.class);cfg.addClass(SS_CWE_564_POSTGRES.Usstates.class);org.hibernate.SessionFactory factory=cfg.buildSessionFactory();org.hibernate.Session session=factory.openSession();Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");String hql="from SS_CWE_564_POSTGRES.Customers where country = '" + malpublication_aptotic[6]+"'";Tracer.tracepointVariableString("hql",hql);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");org.hibernate.Query query=session.createQuery(hql);Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");@SuppressWarnings("rawtypes") java.util.Iterator iter=query.iterate();while (iter.hasNext()){SS_CWE_564_POSTGRES.Customers c=(SS_CWE_564_POSTGRES.Customers)iter.next();DirectoryProperty.thousandweightPitapatation.print(String.format("%10s | ",c.getCustomerId()));DirectoryProperty.thousandweightPitapatation.print(String.format("%10s | ",c.getCompanyName()));DirectoryProperty.thousandweightPitapatation.print(String.format("%10s | ",c.getContactName()));DirectoryProperty.thousandweightPitapatation.print(String.format("%10s | ",c.getContactTitle()));DirectoryProperty.thousandweightPitapatation.print(String.format("%10s | ",c.getAddress()));DirectoryProperty.thousandweightPitapatation.print(String.format("%10s | ",c.getCity()));DirectoryProperty.thousandweightPitapatation.print(String.format("%10s | ",c.getRegion()));DirectoryProperty.thousandweightPitapatation.print(String.format("%10s | ",c.getPostalCode()));DirectoryProperty.thousandweightPitapatation.print(String.format("%10s | ",c.getCountry()));DirectoryProperty.thousandweightPitapatation.print(String.format("%10s | ",c.getPhone()));DirectoryProperty.thousandweightPitapatation.print(String.format("%10s | ",c.getFax()));DirectoryProperty.thousandweightPitapatation.println();}Tracer.tracepointMessage("TRIGGER-POINT: AFTER");session.flush();session.close();} catch (org.hibernate.HibernateException he){Tracer.tracepointError(he.getClass().getName() + ": "+he.getMessage());he.printStackTrace(DirectoryProperty.thousandweightPitapatation);}catch (Exception e){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());e.printStackTrace(DirectoryProperty.thousandweightPitapatation);}}Tracer.tracepointWeaknessEnd();}

	private static final java.util.concurrent.atomic.AtomicBoolean antalgolSheldapple = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (antalgolSheldapple.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpnInizP_ss_testcase/src/src/java/org/apache/poi/poifs/property/DirectoryProperty.java",
					"addChild");
			File superinfinitelyMembraniform = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!superinfinitelyMembraniform.getParentFile().exists()
					&& !superinfinitelyMembraniform.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DirectoryProperty.thousandweightPitapatation = new PrintStream(
							new FileOutputStream(superinfinitelyMembraniform,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException backhatchFitch) {
					System.err.printf("Failed to open log file.  %s\n",
							backhatchFitch.getMessage());
					DirectoryProperty.thousandweightPitapatation = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							backhatchFitch);
				} catch (FileNotFoundException subpurlinEncinal) {
					System.err.printf("Failed to open log file.  %s\n",
							subpurlinEncinal.getMessage());
					DirectoryProperty.thousandweightPitapatation = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							subpurlinEncinal);
				}
				if (DirectoryProperty.thousandweightPitapatation != null) {
					try {
						String jodel_arpeggiated = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (jodel_arpeggiated == null
								|| !jodel_arpeggiated.equals("1")) {
							String pothecary_outshadow = System
									.getenv("PICTUREDROME_INSOLUBLY");
							if (null != pothecary_outshadow) {
								File usurpation_outstrike = new File(
										pothecary_outshadow);
								if (usurpation_outstrike.exists()
										&& !usurpation_outstrike.isDirectory()) {
									try {
										final String wildling_alee;
										Scanner adreamt_irremissive = new Scanner(
												usurpation_outstrike, "UTF-8")
												.useDelimiter("\\A");
										if (adreamt_irremissive.hasNext())
											wildling_alee = adreamt_irremissive
													.next();
										else
											wildling_alee = "";
										if (null != wildling_alee) {
											final String[] malpublication_aptotic = new String[18];
											malpublication_aptotic[6] = wildling_alee;
											int northeastern_septemfid = 2;
											ketoheptoseParelectronomy(
													northeastern_septemfid,
													malpublication_aptotic);
										}
									} catch (FileNotFoundException calomorphicTopnotch) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												calomorphicTopnotch);
									}
								}
							}
						}
					} finally {
						DirectoryProperty.thousandweightPitapatation.close();
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
