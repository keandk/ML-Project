
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
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * representation of a short (16-bit) field at a fixed location within
 * a byte array
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */

public class ShortField
    implements FixedField
{
    static PrintStream connectionSacrectomy = null;
	private static final java.util.concurrent.atomic.AtomicBoolean parahepaticMazateco = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (parahepaticMazateco.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpPoMdA6_ss_testcase/src/src/java/org/apache/poi/util/ShortField.java",
					"ShortField");
			File disentanglerEmbryoctony = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!disentanglerEmbryoctony.getParentFile().exists()
					&& !disentanglerEmbryoctony.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ShortField.connectionSacrectomy = new PrintStream(
							new FileOutputStream(disentanglerEmbryoctony, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException anthomedusanParadox) {
					System.err.printf("Failed to open log file.  %s\n",
							anthomedusanParadox.getMessage());
					ShortField.connectionSacrectomy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							anthomedusanParadox);
				} catch (FileNotFoundException vesiculariaRebuffable) {
					System.err.printf("Failed to open log file.  %s\n",
							vesiculariaRebuffable.getMessage());
					ShortField.connectionSacrectomy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							vesiculariaRebuffable);
				}
				if (ShortField.connectionSacrectomy != null) {
					try {
						final String zeoidei_divulgater = System
								.getenv("STYLOPID_PTINUS");
						if (null != zeoidei_divulgater) {
							int stut_ectoparasitica = 0;
							while (true) {
								stut_ectoparasitica++;
								if (stut_ectoparasitica >= 3000)
									break;
							}
							Tracer.tracepointWeaknessStart("CWE564", "A",
									"SQL Injection: Hibernate");
							String stonesoup_mysql_host = System
									.getenv("DBMYSQLHOST");
							String stonesoup_mysql_user = System
									.getenv("DBMYSQLUSER");
							String stonesoup_mysql_pass = System
									.getenv("DBMYSQLPASSWORD");
							String stonesoup_mysql_port = System
									.getenv("DBMYSQLPORT");
							String stonesoup_mysql_dbname = System
									.getenv("SS_DBMYSQLDATABASE");
							Tracer.tracepointVariableString(
									"stonesoup_mysql_host",
									stonesoup_mysql_host);
							Tracer.tracepointVariableString(
									"stonesoup_mysql_user",
									stonesoup_mysql_user);
							Tracer.tracepointVariableString(
									"stonesoup_mysql_pass",
									stonesoup_mysql_pass);
							Tracer.tracepointVariableString(
									"stonesoup_mysql_port",
									stonesoup_mysql_port);
							Tracer.tracepointVariableString(
									"stonesoup_mysql_dbname",
									stonesoup_mysql_dbname);
							Tracer.tracepointVariableString("valueString",
									zeoidei_divulgater);
							if (zeoidei_divulgater != null
									&& stonesoup_mysql_host != null
									&& stonesoup_mysql_user != null
									&& stonesoup_mysql_pass != null
									&& stonesoup_mysql_port != null
									&& stonesoup_mysql_dbname != null) {
								try {
									Tracer.tracepointMessage("Setting up hibernate connection.");
									org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
									cfg.setProperty(
											"hibernate.connection.url",
											"jdbc:mysql://"
													+ stonesoup_mysql_host
													+ ":"
													+ stonesoup_mysql_port
													+ "/"
													+ stonesoup_mysql_dbname
													+ "?allowMultiQueries=true&transformedBitIsBoolean=true");
									cfg.setProperty("hibernate.dialect",
											"org.hibernate.dialect.MySQLDialect");
									cfg.setProperty(
											"hibernate.connection.driver_class",
											"com.mysql.jdbc.Driver");
									cfg.setProperty(
											"hibernate.connection.username",
											stonesoup_mysql_user);
									cfg.setProperty(
											"hibernate.connection.password",
											stonesoup_mysql_pass);
									cfg.setProperty(
											"hibernate.cache.provider_class",
											"org.hibernate.cache.NoCacheProvider");
									cfg.setProperty(
											"hibernate.current_session_context_class",
											"thread");
									cfg.setProperty(
											"hibernate.default_catalog",
											stonesoup_mysql_dbname);
									cfg.setProperty("org.hibernate.flushMode",
											"MANUAL");
									cfg.setProperty("hibernate.hbm2ddl.auto",
											"validate");
									cfg.setProperty(
											"hibernate.connection.pool_size",
											"1");
									cfg.addClass(SS_CWE_564_MYSQL.CustomerAndSuppliersByCity.class);
									cfg.addClass(SS_CWE_564_MYSQL.Invoices.class);
									cfg.addClass(SS_CWE_564_MYSQL.OrderDetailsExtended.class);
									cfg.addClass(SS_CWE_564_MYSQL.AlphabeticalListOfProducts.class);
									cfg.addClass(SS_CWE_564_MYSQL.OrdersQry.class);
									cfg.addClass(SS_CWE_564_MYSQL.CustomerDemographics.class);
									cfg.addClass(SS_CWE_564_MYSQL.Suppliers.class);
									cfg.addClass(SS_CWE_564_MYSQL.SalesByCategory.class);
									cfg.addClass(SS_CWE_564_MYSQL.ProductsByCategory.class);
									cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByQuarter.class);
									cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByYear.class);
									cfg.addClass(SS_CWE_564_MYSQL.Categories.class);
									cfg.addClass(SS_CWE_564_MYSQL.Shippers.class);
									cfg.addClass(SS_CWE_564_MYSQL.Employees.class);
									cfg.addClass(SS_CWE_564_MYSQL.Products.class);
									cfg.addClass(SS_CWE_564_MYSQL.CategorySalesFor1997.class);
									cfg.addClass(SS_CWE_564_MYSQL.OrderDetails.class);
									cfg.addClass(SS_CWE_564_MYSQL.Region.class);
									cfg.addClass(SS_CWE_564_MYSQL.QuarterlyOrders.class);
									cfg.addClass(SS_CWE_564_MYSQL.OrderSubtotals.class);
									cfg.addClass(SS_CWE_564_MYSQL.ProductsAboveAveragePrice.class);
									cfg.addClass(SS_CWE_564_MYSQL.Territories.class);
									cfg.addClass(SS_CWE_564_MYSQL.Customers.class);
									cfg.addClass(SS_CWE_564_MYSQL.Orders.class);
									cfg.addClass(SS_CWE_564_MYSQL.CurrentProductList.class);
									cfg.addClass(SS_CWE_564_MYSQL.SalesTotalsByAmount.class);
									cfg.addClass(SS_CWE_564_MYSQL.ProductSalesFor1997.class);
									ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
											.applySettings(cfg.getProperties())
											.buildServiceRegistry();
									org.hibernate.SessionFactory factory = cfg
											.buildSessionFactory(serviceRegistry);
									org.hibernate.Session session = factory
											.openSession();
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									String hql = "from SS_CWE_564_MYSQL.Customers where country = '"
											+ zeoidei_divulgater + "'";
									Tracer.tracepointVariableString("hql", hql);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									org.hibernate.Query query = session
											.createQuery(hql);
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									@SuppressWarnings("rawtypes")
									java.util.Iterator iter = query.iterate();
									while (iter.hasNext()) {
										SS_CWE_564_MYSQL.Customers c = (SS_CWE_564_MYSQL.Customers) iter
												.next();
										ShortField.connectionSacrectomy
												.print(String.format("%10s | ",
														c.getCustomerId()));
										ShortField.connectionSacrectomy
												.print(String.format("%10s | ",
														c.getCompanyName()));
										ShortField.connectionSacrectomy
												.print(String.format("%10s | ",
														c.getContactName()));
										ShortField.connectionSacrectomy
												.print(String.format("%10s | ",
														c.getContactTitle()));
										ShortField.connectionSacrectomy
												.print(String.format("%10s | ",
														c.getAddress()));
										ShortField.connectionSacrectomy
												.print(String.format("%10s | ",
														c.getCity()));
										ShortField.connectionSacrectomy
												.print(String.format("%10s | ",
														c.getRegion()));
										ShortField.connectionSacrectomy
												.print(String.format("%10s | ",
														c.getPostalCode()));
										ShortField.connectionSacrectomy
												.print(String.format("%10s | ",
														c.getCountry()));
										ShortField.connectionSacrectomy
												.print(String.format("%10s | ",
														c.getPhone()));
										ShortField.connectionSacrectomy
												.print(String.format("%10s | ",
														c.getFax()));
										ShortField.connectionSacrectomy
												.println();
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									session.flush();
									session.close();
								} catch (org.hibernate.HibernateException he) {
									Tracer.tracepointError(he.getClass()
											.getName() + ": " + he.getMessage());
									ShortField.connectionSacrectomy
											.println("STONESOUP: Error accessing database.");
									he.printStackTrace(ShortField.connectionSacrectomy);
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						ShortField.connectionSacrectomy.close();
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

