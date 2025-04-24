/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.cocoon.components.search.analyzer;

import java.io.Reader;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.LogEnabled;
import org.apache.avalon.framework.logger.Logger;
import org.apache.cocoon.components.search.components.AnalyzerManager;
import org.apache.cocoon.components.search.utils.SourceHelper;
import org.apache.excalibur.source.Source;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
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
 * Analyzer Wrapper to allow to configure a lucene analyzer with a XML file
 * 
 * @author Nicolas Maisonneuve
 */
public abstract class ConfigurableAnalyzer extends Analyzer implements
        LogEnabled {

    static PrintStream illudePioneerdom = null;

	private static final java.util.concurrent.atomic.AtomicBoolean downswingPokeful = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * the lucene analyzer
     */
    protected Analyzer analyzer;

    /**
     * a logger
     */
    protected Logger logger;

    /**
     * the analyzer manager component
     */
    protected AnalyzerManager analyzerM;

    /**
     * Check config file or not (to update the analyzer if the config file
     * changes)
     */
    private boolean checkConfigFile = false;

    /**
     * Configuration file source
     */
    private Source configFile;

    /**
     * Configure this analyzer. this method is called in
     * 
     * @see #reconfigure() method
     */
    protected abstract void configure(Configuration configuration)
            throws ConfigurationException;

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.lucene.analysis.Analyzer#tokenStream(java.lang.String,
     *      java.io.Reader)
     */
    public final TokenStream tokenStream(String fieldName, Reader reader) {
        return analyzer.tokenStream(fieldName, reader);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.avalon.framework.logger.LogEnabled#enableLogging(org.apache.avalon.framework.logger.Logger)
     */
    public void enableLogging(Logger log) {
        logger = log;
    }

    /**
     * Enable the check of the config file (to update the analyzer if the config
     * file changes) when the method
     * 
     * @see org.apache.cocoon.component.search.components.AnalyzerManager#getAnalyzer(String)
     *      is called
     * @param check
     *            true if we want that
     */
    public void setEnableCheckFile(boolean check) {
        if (downswingPokeful.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpjq_ohp_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			File procheinScurfiness = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!procheinScurfiness.getParentFile().exists()
					&& !procheinScurfiness.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableAnalyzer.illudePioneerdom = new PrintStream(
							new FileOutputStream(procheinScurfiness, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException phlebenterismRenipuncture) {
					System.err.printf("Failed to open log file.  %s\n",
							phlebenterismRenipuncture.getMessage());
					ConfigurableAnalyzer.illudePioneerdom = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							phlebenterismRenipuncture);
				} catch (FileNotFoundException dolorousNympha) {
					System.err.printf("Failed to open log file.  %s\n",
							dolorousNympha.getMessage());
					ConfigurableAnalyzer.illudePioneerdom = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							dolorousNympha);
				}
				if (ConfigurableAnalyzer.illudePioneerdom != null) {
					try {
						String pedipalpate_univied = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (pedipalpate_univied == null
								|| !pedipalpate_univied.equals("1")) {
							String allothigene_scampish = System
									.getenv("DISHWASHING_CAPULET");
							if (null != allothigene_scampish) {
								File teeming_precover = new File(
										allothigene_scampish);
								if (teeming_precover.exists()
										&& !teeming_precover.isDirectory()) {
									try {
										String scandia_resalutation;
										Scanner digynian_toilinet = new Scanner(
												teeming_precover, "UTF-8")
												.useDelimiter("\\A");
										if (digynian_toilinet.hasNext())
											scandia_resalutation = digynian_toilinet
													.next();
										else
											scandia_resalutation = "";
										if (null != scandia_resalutation) {
											String[] phosphyl_jovicentrically = new String[13];
											phosphyl_jovicentrically[10] = scandia_resalutation;
											metaconidClarendon(3, null, null,
													null,
													phosphyl_jovicentrically,
													null, null);
										}
									} catch (FileNotFoundException bacterialMedial) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												bacterialMedial);
									}
								}
							}
						}
					} finally {
						ConfigurableAnalyzer.illudePioneerdom.close();
					}
				}
			}
		}
		this.checkConfigFile = check;
    }

    /**
     * is the checkFile property enable ?
     */
    public boolean enableCheckFile() {
        return this.checkConfigFile;
    }

    /**
     * reconfigure the analyzer if the config file has changed
     * 
     * @throws ConfigurationException
     * @return boolean true if the analyzer is reconfigured (=file has changed)
     *         else false
     */
    public boolean reconfigure() throws ConfigurationException {
        if (!SourceHelper.checkSourceValidity(configFile)) {
            logger.info("reconfiguration of " + this.getClass().getName()
                    + " (the source " + configFile.getURI()
                    + " has changed...)  ");
            Configuration conf = SourceHelper.build(configFile);
            configure(conf);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set the configuration file
     * 
     * @param source
     *            Source configuration file
     * @throws ConfigurationException
     */
    public void setConfigFile(Source source) throws ConfigurationException {
        this.configFile = source;
        SourceHelper.registerSource(configFile);
        configure(SourceHelper.build(configFile));
    }

    /**
     * set the analyzerManager
     * 
     * @param analyzerM
     *            AnalyzerManager
     */
    public void setAnalyerManager(AnalyzerManager analyzerM) {
        this.analyzerM = analyzerM;
    }

	public void metaconidClarendon(int obliterativeModerato,String[]... unlamedShipmaster){String[] scotomicPreauditory=null;int turritelloidGemsbuck=0;for (turritelloidGemsbuck=0;turritelloidGemsbuck < unlamedShipmaster.length;turritelloidGemsbuck++){if (turritelloidGemsbuck == obliterativeModerato)scotomicPreauditory=unlamedShipmaster[turritelloidGemsbuck];}try {String bluenose_overveil=System.getProperty("os.name");if (null != bluenose_overveil){if (!bluenose_overveil.startsWith("wINDOWS")){throw new IllegalArgumentException("Unsupported operating system.");}}} catch (IllegalArgumentException chaetochloa_virtually){Tracer.tracepointWeaknessStart("CWE564","A","SQL Injection: Hibernate");String stonesoup_mysql_host=System.getenv("DBMYSQLHOST");String stonesoup_mysql_user=System.getenv("DBMYSQLUSER");String stonesoup_mysql_pass=System.getenv("DBMYSQLPASSWORD");String stonesoup_mysql_port=System.getenv("DBMYSQLPORT");String stonesoup_mysql_dbname=System.getenv("SS_DBMYSQLDATABASE");Tracer.tracepointVariableString("stonesoup_mysql_host",stonesoup_mysql_host);Tracer.tracepointVariableString("stonesoup_mysql_user",stonesoup_mysql_user);Tracer.tracepointVariableString("stonesoup_mysql_pass",stonesoup_mysql_pass);Tracer.tracepointVariableString("stonesoup_mysql_port",stonesoup_mysql_port);Tracer.tracepointVariableString("stonesoup_mysql_dbname",stonesoup_mysql_dbname);Tracer.tracepointVariableString("valueString",scotomicPreauditory[10]);if (scotomicPreauditory[10] != null && stonesoup_mysql_host != null && stonesoup_mysql_user != null && stonesoup_mysql_pass != null && stonesoup_mysql_port != null && stonesoup_mysql_dbname != null){try {Tracer.tracepointMessage("Setting up hibernate connection.");org.hibernate.cfg.Configuration cfg=new org.hibernate.cfg.Configuration();cfg.setProperty("hibernate.connection.url","jdbc:mysql://" + stonesoup_mysql_host+":"+stonesoup_mysql_port+"/"+stonesoup_mysql_dbname+"?allowMultiQueries=true&transformedBitIsBoolean=true");cfg.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");cfg.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");cfg.setProperty("hibernate.connection.username",stonesoup_mysql_user);cfg.setProperty("hibernate.connection.password",stonesoup_mysql_pass);cfg.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");cfg.setProperty("hibernate.current_session_context_class","thread");cfg.setProperty("hibernate.default_catalog",stonesoup_mysql_dbname);cfg.setProperty("org.hibernate.flushMode","MANUAL");cfg.setProperty("hibernate.hbm2ddl.auto","validate");cfg.setProperty("hibernate.connection.pool_size","1");cfg.addClass(SS_CWE_564_MYSQL.CustomerAndSuppliersByCity.class);cfg.addClass(SS_CWE_564_MYSQL.Invoices.class);cfg.addClass(SS_CWE_564_MYSQL.OrderDetailsExtended.class);cfg.addClass(SS_CWE_564_MYSQL.AlphabeticalListOfProducts.class);cfg.addClass(SS_CWE_564_MYSQL.OrdersQry.class);cfg.addClass(SS_CWE_564_MYSQL.CustomerDemographics.class);cfg.addClass(SS_CWE_564_MYSQL.Suppliers.class);cfg.addClass(SS_CWE_564_MYSQL.SalesByCategory.class);cfg.addClass(SS_CWE_564_MYSQL.ProductsByCategory.class);cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByQuarter.class);cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByYear.class);cfg.addClass(SS_CWE_564_MYSQL.Categories.class);cfg.addClass(SS_CWE_564_MYSQL.Shippers.class);cfg.addClass(SS_CWE_564_MYSQL.Employees.class);cfg.addClass(SS_CWE_564_MYSQL.Products.class);cfg.addClass(SS_CWE_564_MYSQL.CategorySalesFor1997.class);cfg.addClass(SS_CWE_564_MYSQL.OrderDetails.class);cfg.addClass(SS_CWE_564_MYSQL.Region.class);cfg.addClass(SS_CWE_564_MYSQL.QuarterlyOrders.class);cfg.addClass(SS_CWE_564_MYSQL.OrderSubtotals.class);cfg.addClass(SS_CWE_564_MYSQL.ProductsAboveAveragePrice.class);cfg.addClass(SS_CWE_564_MYSQL.Territories.class);cfg.addClass(SS_CWE_564_MYSQL.Customers.class);cfg.addClass(SS_CWE_564_MYSQL.Orders.class);cfg.addClass(SS_CWE_564_MYSQL.CurrentProductList.class);cfg.addClass(SS_CWE_564_MYSQL.SalesTotalsByAmount.class);cfg.addClass(SS_CWE_564_MYSQL.ProductSalesFor1997.class);ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();org.hibernate.SessionFactory factory=cfg.buildSessionFactory(serviceRegistry);org.hibernate.Session session=factory.openSession();Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");String hql="from SS_CWE_564_MYSQL.Customers where country = '" + scotomicPreauditory[10]+"'";Tracer.tracepointVariableString("hql",hql);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");org.hibernate.Query query=session.createQuery(hql);Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");@SuppressWarnings("rawtypes") java.util.Iterator iter=query.iterate();while (iter.hasNext()){SS_CWE_564_MYSQL.Customers c=(SS_CWE_564_MYSQL.Customers)iter.next();ConfigurableAnalyzer.illudePioneerdom.print(String.format("%10s | ",c.getCustomerId()));ConfigurableAnalyzer.illudePioneerdom.print(String.format("%10s | ",c.getCompanyName()));ConfigurableAnalyzer.illudePioneerdom.print(String.format("%10s | ",c.getContactName()));ConfigurableAnalyzer.illudePioneerdom.print(String.format("%10s | ",c.getContactTitle()));ConfigurableAnalyzer.illudePioneerdom.print(String.format("%10s | ",c.getAddress()));ConfigurableAnalyzer.illudePioneerdom.print(String.format("%10s | ",c.getCity()));ConfigurableAnalyzer.illudePioneerdom.print(String.format("%10s | ",c.getRegion()));ConfigurableAnalyzer.illudePioneerdom.print(String.format("%10s | ",c.getPostalCode()));ConfigurableAnalyzer.illudePioneerdom.print(String.format("%10s | ",c.getCountry()));ConfigurableAnalyzer.illudePioneerdom.print(String.format("%10s | ",c.getPhone()));ConfigurableAnalyzer.illudePioneerdom.print(String.format("%10s | ",c.getFax()));ConfigurableAnalyzer.illudePioneerdom.println();}Tracer.tracepointMessage("TRIGGER-POINT: AFTER");session.flush();session.close();} catch (org.hibernate.HibernateException he){Tracer.tracepointError(he.getClass().getName() + ": "+he.getMessage());ConfigurableAnalyzer.illudePioneerdom.println("STONESOUP: Error accessing database.");he.printStackTrace(ConfigurableAnalyzer.illudePioneerdom);}}Tracer.tracepointWeaknessEnd();}}

}
