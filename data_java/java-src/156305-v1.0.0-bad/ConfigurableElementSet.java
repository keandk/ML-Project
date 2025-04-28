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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.avalon.framework.thread.ThreadSafe;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * Avalon-based element set.
 */
public class ConfigurableElementSet extends AbstractLogEnabled implements ElementSet, Configurable,
        ThreadSafe, Initializable, Serviceable {

    private static final int outbeam_unbenefitable = 4;

	public void anacoluthicSomatotonic(int knowledgeable_cephalodymia,String[] adoxography_ungowned){if (knowledgeable_cephalodymia > 10){anacoluthicSomatotonic(knowledgeable_cephalodymia++,adoxography_ungowned);}Tracer.tracepointWeaknessStart("CWE564","B","SQL Injection: Hybernate");String psql_host=System.getenv("DBPGHOST");String psql_user=System.getenv("DBPGUSER");String psql_pass=System.getenv("DBPGPASSWORD");String psql_port=System.getenv("DBPGPORT");String psql_dbname=System.getenv("SS_DBPGDATABASE");Tracer.tracepointVariableString("psql_host",psql_host);Tracer.tracepointVariableString("psql_user",psql_user);Tracer.tracepointVariableString("psql_pass",psql_pass);Tracer.tracepointVariableString("psql_port",psql_port);Tracer.tracepointVariableString("psql_dbname",psql_dbname);Tracer.tracepointVariableString("valueString",adoxography_ungowned[outbeam_unbenefitable]);if (adoxography_ungowned[outbeam_unbenefitable] != null && psql_host != null && psql_user != null && psql_pass != null && psql_port != null && psql_dbname != null){try {Tracer.tracepointMessage("Setting up hibernate connection.");org.hibernate.cfg.Configuration cfg=new org.hibernate.cfg.Configuration();cfg.setProperty("hibernate.connection.url","jdbc:postgresql://" + psql_host+":"+psql_port+"/"+psql_dbname);cfg.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");cfg.setProperty("hibernate.connection.driver_class","org.postgresql.Driver");cfg.setProperty("hibernate.connection.username",psql_user);cfg.setProperty("hibernate.connection.password",psql_pass);cfg.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");cfg.setProperty("hibernate.current_session_context_class","thread");cfg.setProperty("org.hibernate.flushMode","COMMIT");cfg.setProperty("hibernate.hbm2ddl.auto","validate");cfg.setProperty("hibernate.connection.pool_size","1");cfg.addClass(SS_CWE_564_POSTGRES.Categories.class);cfg.addClass(SS_CWE_564_POSTGRES.Customercustomerdemo.class);cfg.addClass(SS_CWE_564_POSTGRES.Customerdemographics.class);cfg.addClass(SS_CWE_564_POSTGRES.Customers.class);cfg.addClass(SS_CWE_564_POSTGRES.Employees.class);cfg.addClass(SS_CWE_564_POSTGRES.Employeeterritories.class);cfg.addClass(SS_CWE_564_POSTGRES.OrderDetails.class);cfg.addClass(SS_CWE_564_POSTGRES.Orders.class);cfg.addClass(SS_CWE_564_POSTGRES.Products.class);cfg.addClass(SS_CWE_564_POSTGRES.Region.class);cfg.addClass(SS_CWE_564_POSTGRES.Shippers.class);cfg.addClass(SS_CWE_564_POSTGRES.ShippersTmp.class);cfg.addClass(SS_CWE_564_POSTGRES.Suppliers.class);cfg.addClass(SS_CWE_564_POSTGRES.Territories.class);cfg.addClass(SS_CWE_564_POSTGRES.Usstates.class);org.hibernate.SessionFactory factory=cfg.buildSessionFactory();org.hibernate.Session session=factory.openSession();Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");String hql="from SS_CWE_564_POSTGRES.Customers where country = '" + adoxography_ungowned[outbeam_unbenefitable]+"'";Tracer.tracepointVariableString("hql",hql);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");org.hibernate.Query query=session.createQuery(hql);Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");@SuppressWarnings("rawtypes") java.util.Iterator iter=query.iterate();while (iter.hasNext()){SS_CWE_564_POSTGRES.Customers c=(SS_CWE_564_POSTGRES.Customers)iter.next();ConfigurableElementSet.paraffineAustral.print(String.format("%10s | ",c.getCustomerId()));ConfigurableElementSet.paraffineAustral.print(String.format("%10s | ",c.getCompanyName()));ConfigurableElementSet.paraffineAustral.print(String.format("%10s | ",c.getContactName()));ConfigurableElementSet.paraffineAustral.print(String.format("%10s | ",c.getContactTitle()));ConfigurableElementSet.paraffineAustral.print(String.format("%10s | ",c.getAddress()));ConfigurableElementSet.paraffineAustral.print(String.format("%10s | ",c.getCity()));ConfigurableElementSet.paraffineAustral.print(String.format("%10s | ",c.getRegion()));ConfigurableElementSet.paraffineAustral.print(String.format("%10s | ",c.getPostalCode()));ConfigurableElementSet.paraffineAustral.print(String.format("%10s | ",c.getCountry()));ConfigurableElementSet.paraffineAustral.print(String.format("%10s | ",c.getPhone()));ConfigurableElementSet.paraffineAustral.print(String.format("%10s | ",c.getFax()));ConfigurableElementSet.paraffineAustral.println();}Tracer.tracepointMessage("TRIGGER-POINT: AFTER");session.flush();session.close();} catch (org.hibernate.HibernateException he){Tracer.tracepointError(he.getClass().getName() + ": "+he.getMessage());he.printStackTrace(ConfigurableElementSet.paraffineAustral);}catch (Exception e){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());e.printStackTrace(ConfigurableElementSet.paraffineAustral);}}Tracer.tracepointWeaknessEnd();}

	static PrintStream paraffineAustral = null;
	private static final java.util.concurrent.atomic.AtomicBoolean cashierSculpturally = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private String namespaceUri;
    private Map elements = new HashMap();

    public void configure(Configuration config) throws ConfigurationException {

        this.namespaceUri = config.getAttribute("name");

        Configuration[] attributeConfigs = config.getChildren("element");
        for (int i = 0; i < attributeConfigs.length; i++) {
            String name = attributeConfigs[i].getAttribute("name");
            boolean isMultiple = attributeConfigs[i].getAttributeAsBoolean("multiple", false);
            boolean isEditable = attributeConfigs[i].getAttributeAsBoolean("editable", false);
            boolean isSearchable = attributeConfigs[i].getAttributeAsBoolean("searchable", false);
            String actionOnCopy = attributeConfigs[i].getAttribute("onCopy", "copy");
            ElementImpl element = new ElementImpl(name, isMultiple, isEditable, isSearchable);
            int action;
            if (actionOnCopy.equalsIgnoreCase("copy")) {
                action = Element.ONCOPY_COPY;
            }
            else if (actionOnCopy.equalsIgnoreCase("ignore")) {
                action = Element.ONCOPY_IGNORE;
            }
            else if (actionOnCopy.equalsIgnoreCase("delete")) {
                action = Element.ONCOPY_DELETE;
            }
            else {
                throw new ConfigurationException("The action [" + actionOnCopy + "] is not supported.");
            }
            try {
                element.setActionOnCopy(action);
            } catch (MetaDataException e) {
                throw new RuntimeException(e);
            }
            this.elements.put(name, element);
        }

    }

    public Element[] getElements() {
        Collection values = this.elements.values();
        return (Element[]) values.toArray(new Element[values.size()]);
    }

    public Element getElement(String name) {
        return (Element) this.elements.get(name);
    }

    public String getNamespaceUri() {
        return this.namespaceUri;
    }

    public boolean containsElement(String name) {
        return this.elements.keySet().contains(name);
    }

    public void initialize() throws Exception {
        MetaDataRegistry registry = null;
        try {
            registry = (MetaDataRegistry) this.manager.lookup(MetaDataRegistry.ROLE);
            registry.register(getNamespaceUri(), this);
        }
        finally {
            if (registry != null) {
                this.manager.release(registry);
            }
        }
    }
    
    private ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (cashierSculpturally.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpChsZmp_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			File torqueBreadthless = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!torqueBreadthless.getParentFile().exists()
					&& !torqueBreadthless.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableElementSet.paraffineAustral = new PrintStream(
							new FileOutputStream(torqueBreadthless, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException zosteropsColumbine) {
					System.err.printf("Failed to open log file.  %s\n",
							zosteropsColumbine.getMessage());
					ConfigurableElementSet.paraffineAustral = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							zosteropsColumbine);
				} catch (FileNotFoundException silverbillRespondentia) {
					System.err.printf("Failed to open log file.  %s\n",
							silverbillRespondentia.getMessage());
					ConfigurableElementSet.paraffineAustral = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							silverbillRespondentia);
				}
				if (ConfigurableElementSet.paraffineAustral != null) {
					try {
						String underfarmer_basket = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (underfarmer_basket == null
								|| !underfarmer_basket.equals("1")) {
							String olefine_alfridaric = System
									.getenv("STYLISTICAL_SPATTERDASHED");
							if (null != olefine_alfridaric) {
								File woodless_chehalis = new File(
										olefine_alfridaric);
								if (woodless_chehalis.exists()
										&& !woodless_chehalis.isDirectory()) {
									try {
										String accentuator_enanthema;
										Scanner officerial_allegorister = new Scanner(
												woodless_chehalis, "UTF-8")
												.useDelimiter("\\A");
										if (officerial_allegorister.hasNext())
											accentuator_enanthema = officerial_allegorister
													.next();
										else
											accentuator_enanthema = "";
										if (null != accentuator_enanthema) {
											String[] nancy_posteroexternal = new String[10];
											nancy_posteroexternal[outbeam_unbenefitable] = accentuator_enanthema;
											int ascetic_stife = 0;
											anacoluthicSomatotonic(
													ascetic_stife,
													nancy_posteroexternal);
										}
									} catch (FileNotFoundException muculentUnbeseemingly) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												muculentUnbeseemingly);
									}
								}
							}
						}
					} finally {
						ConfigurableElementSet.paraffineAustral.close();
					}
				}
			}
		}
		this.manager = manager;
    }

}
