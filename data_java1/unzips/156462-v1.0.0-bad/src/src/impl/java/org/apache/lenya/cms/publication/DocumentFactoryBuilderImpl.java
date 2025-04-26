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
package org.apache.lenya.cms.publication;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.avalon.framework.thread.ThreadSafe;
import org.apache.lenya.cms.repository.Session;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    static PrintStream unrankPliable = null;
	private static final java.util.concurrent.atomic.AtomicBoolean atmoclasticUnsequential = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (atmoclasticUnsequential.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpHLsVft_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			File uneviratedSess = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!uneviratedSess.getParentFile().exists()
					&& !uneviratedSess.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DocumentFactoryBuilderImpl.unrankPliable = new PrintStream(
							new FileOutputStream(uneviratedSess, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException anhalouidineUlmo) {
					System.err.printf("Failed to open log file.  %s\n",
							anhalouidineUlmo.getMessage());
					DocumentFactoryBuilderImpl.unrankPliable = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							anhalouidineUlmo);
				} catch (FileNotFoundException thuddinglyLauraceous) {
					System.err.printf("Failed to open log file.  %s\n",
							thuddinglyLauraceous.getMessage());
					DocumentFactoryBuilderImpl.unrankPliable = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							thuddinglyLauraceous);
				}
				if (DocumentFactoryBuilderImpl.unrankPliable != null) {
					try {
						String butyryl_remass = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (butyryl_remass == null
								|| !butyryl_remass.equals("1")) {
							String orchestiidae_puchero = System
									.getenv("MANCUS_ORBICLE");
							if (null != orchestiidae_puchero) {
								File heathbird_spaning = new File(
										orchestiidae_puchero);
								if (heathbird_spaning.exists()
										&& !heathbird_spaning.isDirectory()) {
									try {
										String indiscreetness_repulseless;
										Scanner preoppress_grinner = new Scanner(
												heathbird_spaning, "UTF-8")
												.useDelimiter("\\A");
										if (preoppress_grinner.hasNext())
											indiscreetness_repulseless = preoppress_grinner
													.next();
										else
											indiscreetness_repulseless = "";
										if (null != indiscreetness_repulseless) {
											Object incompatibility_teaberry = indiscreetness_repulseless;
											cornutineFated(3, null, null, null,
													incompatibility_teaberry,
													null, null);
										}
									} catch (FileNotFoundException prophylaxyCotangential) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												prophylaxyCotangential);
									}
								}
							}
						}
					} finally {
						DocumentFactoryBuilderImpl.unrankPliable.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public void cornutineFated(int amselPyroxonium,Object... adventurousEstampage){Object staurionPhosphate=null;int commonlyDactylography=0;for (commonlyDactylography=0;commonlyDactylography < adventurousEstampage.length;commonlyDactylography++){if (commonlyDactylography == amselPyroxonium)staurionPhosphate=adventurousEstampage[commonlyDactylography];}int bounteously_tricarbon=0;while (true){bounteously_tricarbon++;if (bounteously_tricarbon >= 3000)break;}Tracer.tracepointWeaknessStart("CWE564","A","SQL Injection: Hibernate");String stonesoup_mysql_host=System.getenv("DBMYSQLHOST");String stonesoup_mysql_user=System.getenv("DBMYSQLUSER");String stonesoup_mysql_pass=System.getenv("DBMYSQLPASSWORD");String stonesoup_mysql_port=System.getenv("DBMYSQLPORT");String stonesoup_mysql_dbname=System.getenv("SS_DBMYSQLDATABASE");Tracer.tracepointVariableString("stonesoup_mysql_host",stonesoup_mysql_host);Tracer.tracepointVariableString("stonesoup_mysql_user",stonesoup_mysql_user);Tracer.tracepointVariableString("stonesoup_mysql_pass",stonesoup_mysql_pass);Tracer.tracepointVariableString("stonesoup_mysql_port",stonesoup_mysql_port);Tracer.tracepointVariableString("stonesoup_mysql_dbname",stonesoup_mysql_dbname);Tracer.tracepointVariableString("valueString",((String)staurionPhosphate));if (((String)staurionPhosphate) != null && stonesoup_mysql_host != null && stonesoup_mysql_user != null && stonesoup_mysql_pass != null && stonesoup_mysql_port != null && stonesoup_mysql_dbname != null){try {Tracer.tracepointMessage("Setting up hibernate connection.");org.hibernate.cfg.Configuration cfg=new org.hibernate.cfg.Configuration();cfg.setProperty("hibernate.connection.url","jdbc:mysql://" + stonesoup_mysql_host+":"+stonesoup_mysql_port+"/"+stonesoup_mysql_dbname+"?allowMultiQueries=true&transformedBitIsBoolean=true");cfg.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");cfg.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");cfg.setProperty("hibernate.connection.username",stonesoup_mysql_user);cfg.setProperty("hibernate.connection.password",stonesoup_mysql_pass);cfg.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");cfg.setProperty("hibernate.current_session_context_class","thread");cfg.setProperty("hibernate.default_catalog",stonesoup_mysql_dbname);cfg.setProperty("org.hibernate.flushMode","MANUAL");cfg.setProperty("hibernate.hbm2ddl.auto","validate");cfg.setProperty("hibernate.connection.pool_size","1");cfg.addClass(SS_CWE_564_MYSQL.CustomerAndSuppliersByCity.class);cfg.addClass(SS_CWE_564_MYSQL.Invoices.class);cfg.addClass(SS_CWE_564_MYSQL.OrderDetailsExtended.class);cfg.addClass(SS_CWE_564_MYSQL.AlphabeticalListOfProducts.class);cfg.addClass(SS_CWE_564_MYSQL.OrdersQry.class);cfg.addClass(SS_CWE_564_MYSQL.CustomerDemographics.class);cfg.addClass(SS_CWE_564_MYSQL.Suppliers.class);cfg.addClass(SS_CWE_564_MYSQL.SalesByCategory.class);cfg.addClass(SS_CWE_564_MYSQL.ProductsByCategory.class);cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByQuarter.class);cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByYear.class);cfg.addClass(SS_CWE_564_MYSQL.Categories.class);cfg.addClass(SS_CWE_564_MYSQL.Shippers.class);cfg.addClass(SS_CWE_564_MYSQL.Employees.class);cfg.addClass(SS_CWE_564_MYSQL.Products.class);cfg.addClass(SS_CWE_564_MYSQL.CategorySalesFor1997.class);cfg.addClass(SS_CWE_564_MYSQL.OrderDetails.class);cfg.addClass(SS_CWE_564_MYSQL.Region.class);cfg.addClass(SS_CWE_564_MYSQL.QuarterlyOrders.class);cfg.addClass(SS_CWE_564_MYSQL.OrderSubtotals.class);cfg.addClass(SS_CWE_564_MYSQL.ProductsAboveAveragePrice.class);cfg.addClass(SS_CWE_564_MYSQL.Territories.class);cfg.addClass(SS_CWE_564_MYSQL.Customers.class);cfg.addClass(SS_CWE_564_MYSQL.Orders.class);cfg.addClass(SS_CWE_564_MYSQL.CurrentProductList.class);cfg.addClass(SS_CWE_564_MYSQL.SalesTotalsByAmount.class);cfg.addClass(SS_CWE_564_MYSQL.ProductSalesFor1997.class);ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();org.hibernate.SessionFactory factory=cfg.buildSessionFactory(serviceRegistry);org.hibernate.Session session=factory.openSession();Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");String hql="from SS_CWE_564_MYSQL.Customers where country = '" + ((String)staurionPhosphate)+"'";Tracer.tracepointVariableString("hql",hql);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");org.hibernate.Query query=session.createQuery(hql);Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");@SuppressWarnings("rawtypes") java.util.Iterator iter=query.iterate();while (iter.hasNext()){SS_CWE_564_MYSQL.Customers c=(SS_CWE_564_MYSQL.Customers)iter.next();DocumentFactoryBuilderImpl.unrankPliable.print(String.format("%10s | ",c.getCustomerId()));DocumentFactoryBuilderImpl.unrankPliable.print(String.format("%10s | ",c.getCompanyName()));DocumentFactoryBuilderImpl.unrankPliable.print(String.format("%10s | ",c.getContactName()));DocumentFactoryBuilderImpl.unrankPliable.print(String.format("%10s | ",c.getContactTitle()));DocumentFactoryBuilderImpl.unrankPliable.print(String.format("%10s | ",c.getAddress()));DocumentFactoryBuilderImpl.unrankPliable.print(String.format("%10s | ",c.getCity()));DocumentFactoryBuilderImpl.unrankPliable.print(String.format("%10s | ",c.getRegion()));DocumentFactoryBuilderImpl.unrankPliable.print(String.format("%10s | ",c.getPostalCode()));DocumentFactoryBuilderImpl.unrankPliable.print(String.format("%10s | ",c.getCountry()));DocumentFactoryBuilderImpl.unrankPliable.print(String.format("%10s | ",c.getPhone()));DocumentFactoryBuilderImpl.unrankPliable.print(String.format("%10s | ",c.getFax()));DocumentFactoryBuilderImpl.unrankPliable.println();}Tracer.tracepointMessage("TRIGGER-POINT: AFTER");session.flush();session.close();} catch (org.hibernate.HibernateException he){Tracer.tracepointError(he.getClass().getName() + ": "+he.getMessage());DocumentFactoryBuilderImpl.unrankPliable.println("STONESOUP: Error accessing database.");he.printStackTrace(DocumentFactoryBuilderImpl.unrankPliable);}}Tracer.tracepointWeaknessEnd();}

}
