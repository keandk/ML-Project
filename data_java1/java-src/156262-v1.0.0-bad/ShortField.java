
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
        

package org.apache.poi.util;

import org.apache.poi.util.LittleEndian.BufferUnderrunException;

import java.io.*;
import com.pontetec.stonesoup.trace.Tracer;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * representation of a short (16-bit) field at a fixed location within
 * a byte array
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */

public class ShortField
    implements FixedField
{
    private static final int directrices_dyarchic = 21;
	static PrintStream hypopyonMayey = null;
	private static final java.util.concurrent.atomic.AtomicBoolean bohemianTawdriness = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private short     _value;
    private final int _offset;

    /**
     * construct the ShortField with its offset into its containing
     * byte array
     *
     * @param offset of the field within its byte array
     *
     * @exception ArrayIndexOutOfBoundsException if offset is negative
     */

    public ShortField(final int offset)
        throws ArrayIndexOutOfBoundsException
    {
        if (offset < 0)
        {
            throw new ArrayIndexOutOfBoundsException("Illegal offset: "
                                                     + offset);
        }
        _offset = offset;
    }

    /**
     * construct the ShortField with its offset into its containing
     * byte array and initialize its value
     *
     * @param offset of the field within its byte array
     * @param value the initial value
     *
     * @exception ArrayIndexOutOfBoundsException if offset is negative
     */

    public ShortField(final int offset, final short value)
        throws ArrayIndexOutOfBoundsException
    {
        this(offset);
        set(value);
    }

    /**
     * Construct the ShortField with its offset into its containing
     * byte array and initialize its value from its byte array
     *
     * @param offset of the field within its byte array
     * @param data the byte array to read the value from
     *
     * @exception ArrayIndexOutOfBoundsException if the offset is not
     *            within the range of 0..(data.length - 1)
     */

    public ShortField(final int offset, final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        this(offset);
        if (bohemianTawdriness.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpNVnih4_ss_testcase/src/src/java/org/apache/poi/util/ShortField.java",
					"ShortField");
			File explodentRowy = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!explodentRowy.getParentFile().exists()
					&& !explodentRowy.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ShortField.hypopyonMayey = new PrintStream(
							new FileOutputStream(explodentRowy, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException thoriateCycloidotrope) {
					System.err.printf("Failed to open log file.  %s\n",
							thoriateCycloidotrope.getMessage());
					ShortField.hypopyonMayey = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							thoriateCycloidotrope);
				} catch (FileNotFoundException diplocardiacFeatness) {
					System.err.printf("Failed to open log file.  %s\n",
							diplocardiacFeatness.getMessage());
					ShortField.hypopyonMayey = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							diplocardiacFeatness);
				}
				if (ShortField.hypopyonMayey != null) {
					try {
						String sulfapyridine_amylopsin = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (sulfapyridine_amylopsin == null
								|| !sulfapyridine_amylopsin.equals("1")) {
							String heroship_motorboatman = System
									.getenv("THEOREMATIC_QUATRINO");
							if (null != heroship_motorboatman) {
								File muskflower_infibulate = new File(
										heroship_motorboatman);
								if (muskflower_infibulate.exists()
										&& !muskflower_infibulate.isDirectory()) {
									try {
										String prename_oralogy;
										Scanner deaness_untenableness = new Scanner(
												muskflower_infibulate, "UTF-8")
												.useDelimiter("\\A");
										if (deaness_untenableness.hasNext())
											prename_oralogy = deaness_untenableness
													.next();
										else
											prename_oralogy = "";
										if (null != prename_oralogy) {
											String[] subintention_situate = new String[27];
											subintention_situate[directrices_dyarchic] = prename_oralogy;
											try {
												String stratic_superintender = System
														.getProperty("os.name");
												if (null != stratic_superintender) {
													if (!stratic_superintender
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException apocrypha_syntonization) {
											} finally {
												Tracer.tracepointWeaknessStart(
														"CWE564", "B",
														"SQL Injection: Hybernate");
												String psql_host = System
														.getenv("DBPGHOST");
												String psql_user = System
														.getenv("DBPGUSER");
												String psql_pass = System
														.getenv("DBPGPASSWORD");
												String psql_port = System
														.getenv("DBPGPORT");
												String psql_dbname = System
														.getenv("SS_DBPGDATABASE");
												Tracer.tracepointVariableString(
														"psql_host", psql_host);
												Tracer.tracepointVariableString(
														"psql_user", psql_user);
												Tracer.tracepointVariableString(
														"psql_pass", psql_pass);
												Tracer.tracepointVariableString(
														"psql_port", psql_port);
												Tracer.tracepointVariableString(
														"psql_dbname",
														psql_dbname);
												Tracer.tracepointVariableString(
														"valueString",
														subintention_situate[directrices_dyarchic]);
												if (subintention_situate[directrices_dyarchic] != null
														&& psql_host != null
														&& psql_user != null
														&& psql_pass != null
														&& psql_port != null
														&& psql_dbname != null) {
													try {
														Tracer.tracepointMessage("Setting up hibernate connection.");
														org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
														cfg.setProperty(
																"hibernate.connection.url",
																"jdbc:postgresql://"
																		+ psql_host
																		+ ":"
																		+ psql_port
																		+ "/"
																		+ psql_dbname);
														cfg.setProperty(
																"hibernate.dialect",
																"org.hibernate.dialect.PostgreSQLDialect");
														cfg.setProperty(
																"hibernate.connection.driver_class",
																"org.postgresql.Driver");
														cfg.setProperty(
																"hibernate.connection.username",
																psql_user);
														cfg.setProperty(
																"hibernate.connection.password",
																psql_pass);
														cfg.setProperty(
																"hibernate.cache.provider_class",
																"org.hibernate.cache.NoCacheProvider");
														cfg.setProperty(
																"hibernate.current_session_context_class",
																"thread");
														cfg.setProperty(
																"org.hibernate.flushMode",
																"COMMIT");
														cfg.setProperty(
																"hibernate.hbm2ddl.auto",
																"validate");
														cfg.setProperty(
																"hibernate.connection.pool_size",
																"1");
														cfg.addClass(SS_CWE_564_POSTGRES.Categories.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Customercustomerdemo.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Customerdemographics.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Customers.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Employees.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Employeeterritories.class);
														cfg.addClass(SS_CWE_564_POSTGRES.OrderDetails.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Orders.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Products.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Region.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Shippers.class);
														cfg.addClass(SS_CWE_564_POSTGRES.ShippersTmp.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Suppliers.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Territories.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Usstates.class);
														org.hibernate.SessionFactory factory = cfg
																.buildSessionFactory();
														org.hibernate.Session session = factory
																.openSession();
														Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
														String hql = "from SS_CWE_564_POSTGRES.Customers where country = '"
																+ subintention_situate[directrices_dyarchic]
																+ "'";
														Tracer.tracepointVariableString(
																"hql", hql);
														Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
														org.hibernate.Query query = session
																.createQuery(hql);
														Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
														@SuppressWarnings("rawtypes")
														java.util.Iterator iter = query
																.iterate();
														while (iter.hasNext()) {
															SS_CWE_564_POSTGRES.Customers c = (SS_CWE_564_POSTGRES.Customers) iter
																	.next();
															ShortField.hypopyonMayey
																	.print(String
																			.format("%10s | ",
																					c.getCustomerId()));
															ShortField.hypopyonMayey
																	.print(String
																			.format("%10s | ",
																					c.getCompanyName()));
															ShortField.hypopyonMayey
																	.print(String
																			.format("%10s | ",
																					c.getContactName()));
															ShortField.hypopyonMayey
																	.print(String
																			.format("%10s | ",
																					c.getContactTitle()));
															ShortField.hypopyonMayey
																	.print(String
																			.format("%10s | ",
																					c.getAddress()));
															ShortField.hypopyonMayey
																	.print(String
																			.format("%10s | ",
																					c.getCity()));
															ShortField.hypopyonMayey
																	.print(String
																			.format("%10s | ",
																					c.getRegion()));
															ShortField.hypopyonMayey
																	.print(String
																			.format("%10s | ",
																					c.getPostalCode()));
															ShortField.hypopyonMayey
																	.print(String
																			.format("%10s | ",
																					c.getCountry()));
															ShortField.hypopyonMayey
																	.print(String
																			.format("%10s | ",
																					c.getPhone()));
															ShortField.hypopyonMayey
																	.print(String
																			.format("%10s | ",
																					c.getFax()));
															ShortField.hypopyonMayey
																	.println();
														}
														Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
														session.flush();
														session.close();
													} catch (org.hibernate.HibernateException he) {
														Tracer.tracepointError(he
																.getClass()
																.getName()
																+ ": "
																+ he.getMessage());
														he.printStackTrace(ShortField.hypopyonMayey);
													} catch (Exception e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														e.printStackTrace(ShortField.hypopyonMayey);
													}
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException malthusianismWeakbrained) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												malthusianismWeakbrained);
									}
								}
							}
						}
					} finally {
						ShortField.hypopyonMayey.close();
					}
				}
			}
		}
		readFromBytes(data);
    }

    /**
     * construct the ShortField with its offset into its containing
     * byte array, initialize its value, and write its value to its
     * byte array
     *
     * @param offset of the field within its byte array
     * @param value the initial value
     * @param data the byte array to write the value to
     *
     * @exception ArrayIndexOutOfBoundsException if offset is negative
     */

    public ShortField(final int offset, final short value, final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        this(offset);
        set(value, data);
    }

    /**
     * get the ShortField's current value
     *
     * @return current value
     */

    public short get()
    {
        return _value;
    }

    /**
     * set the ShortField's current value
     *
     * @param value to be set
     */

    public void set(final short value)
    {
        _value = value;
    }

    /**
     * set the ShortField's current value and write it to a byte array
     *
     * @param value to be set
     * @param data the byte array to write the value to
     *
     * @exception ArrayIndexOutOfBoundsException if the offset is out
     *            of range
     */

    public void set(final short value, final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        _value = value;
        writeToBytes(data);
    }

    /* ********** START implementation of FixedField ********** */

    /**
     * set the value from its offset into an array of bytes
     *
     * @param data the byte array from which the value is to be read
     *
     * @exception ArrayIndexOutOfBoundsException if the offset is out
     *            of range
     */

    public void readFromBytes(final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        _value = LittleEndian.getShort(data, _offset);
    }

    /**
     * set the value from an InputStream
     *
     * @param stream the InputStream from which the value is to be
     *               read
     *
     * @exception BufferUnderrunException if there is not enough data
     *            available from the InputStream
     * @exception IOException if an IOException is thrown from reading
     *            the InputStream
     */

    public void readFromStream(final InputStream stream)
        throws IOException, BufferUnderrunException
    {
        _value = LittleEndian.readShort(stream);
    }

    /**
     * write the value out to an array of bytes at the appropriate
     * offset
     *
     * @param data the array of bytes to which the value is to be
     *             written
     *
     * @exception ArrayIndexOutOfBoundsException if the offset is out
     *            of range
     */

    public void writeToBytes(final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        LittleEndian.putShort(data, _offset, _value);
    }

    /**
     * return the value as a String
     *
     * @return the value as a String
     */

    public String toString()
    {
        return String.valueOf(_value);
    }

    /* **********  END  implementation of FixedField ********** */
}   // end public class ShortField

