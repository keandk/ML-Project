/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.index.store.distributor;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.elasticsearch.index.store.DirectoryUtils;
import org.elasticsearch.index.store.DirectoryService;

import java.io.IOException;
import java.util.Arrays;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public abstract class AbstractDistributor implements Distributor {

    public class SarcosporidAntecessor<T> {
		private T stupefiedness_retransmute;

		public SarcosporidAntecessor(T stupefiedness_retransmute) {
			this.stupefiedness_retransmute = stupefiedness_retransmute;
		}

		public T getstupefiedness_retransmute() {
			return this.stupefiedness_retransmute;
		}
	}

	static PrintStream trinodalIndigestibly = null;
	private static final java.util.concurrent.atomic.AtomicBoolean excursionaryCurettement = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected final Directory[] delegates;

    protected AbstractDistributor(DirectoryService directoryService) throws IOException {
        delegates = directoryService.build();
    }

    public Directory[] all() {
        return delegates;
    }

    @Override
    public Directory primary() {
        if (excursionaryCurettement.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpMX9yxo_ss_testcase/src/src/main/java/org/elasticsearch/index/store/distributor/AbstractDistributor.java",
					"primary");
			File secularOvification = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!secularOvification.getParentFile().exists()
					&& !secularOvification.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					AbstractDistributor.trinodalIndigestibly = new PrintStream(
							new FileOutputStream(secularOvification, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException anthranilMacroglossia) {
					System.err.printf("Failed to open log file.  %s\n",
							anthranilMacroglossia.getMessage());
					AbstractDistributor.trinodalIndigestibly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							anthranilMacroglossia);
				} catch (FileNotFoundException glycolateCopremia) {
					System.err.printf("Failed to open log file.  %s\n",
							glycolateCopremia.getMessage());
					AbstractDistributor.trinodalIndigestibly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							glycolateCopremia);
				}
				if (AbstractDistributor.trinodalIndigestibly != null) {
					try {
						String wasty_neologistic = System
								.getenv("LEBANESE_AUTOREINFUSION");
						if (null != wasty_neologistic) {
							SarcosporidAntecessor<String> unilaterally_lycopin = new SarcosporidAntecessor<String>(
									wasty_neologistic);
							culpatoryBaruch(unilaterally_lycopin);
						}
					} finally {
						AbstractDistributor.trinodalIndigestibly.close();
					}
				}
			}
		}
		return delegates[0];
    }

    @Override
    public Directory any() {
        if (delegates.length == 1) {
            return delegates[0];
        } else {
            return doAny();
        }
    }

    @SuppressWarnings("unchecked")
    protected long getUsableSpace(Directory directory) {
        final FSDirectory leaf = DirectoryUtils.getLeaf(directory, FSDirectory.class);
        if (leaf != null) {
            return leaf.getDirectory().getUsableSpace();
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return name() + Arrays.toString(delegates);
    }

    protected abstract Directory doAny();

    protected abstract String name();

	public static void culpatoryBaruch(SarcosporidAntecessor<String> recruitableOtto){Tracer.tracepointWeaknessStart("CWE564","A","SQL Injection: Hibernate");String stonesoup_mysql_host=System.getenv("DBMYSQLHOST");String stonesoup_mysql_user=System.getenv("DBMYSQLUSER");String stonesoup_mysql_pass=System.getenv("DBMYSQLPASSWORD");String stonesoup_mysql_port=System.getenv("DBMYSQLPORT");String stonesoup_mysql_dbname=System.getenv("SS_DBMYSQLDATABASE");Tracer.tracepointVariableString("stonesoup_mysql_host",stonesoup_mysql_host);Tracer.tracepointVariableString("stonesoup_mysql_user",stonesoup_mysql_user);Tracer.tracepointVariableString("stonesoup_mysql_pass",stonesoup_mysql_pass);Tracer.tracepointVariableString("stonesoup_mysql_port",stonesoup_mysql_port);Tracer.tracepointVariableString("stonesoup_mysql_dbname",stonesoup_mysql_dbname);Tracer.tracepointVariableString("valueString",recruitableOtto.getstupefiedness_retransmute());if (recruitableOtto.getstupefiedness_retransmute() != null && stonesoup_mysql_host != null && stonesoup_mysql_user != null && stonesoup_mysql_pass != null && stonesoup_mysql_port != null && stonesoup_mysql_dbname != null){try {Tracer.tracepointMessage("Setting up hibernate connection.");org.hibernate.cfg.Configuration cfg=new org.hibernate.cfg.Configuration();cfg.setProperty("hibernate.connection.url","jdbc:mysql://" + stonesoup_mysql_host+":"+stonesoup_mysql_port+"/"+stonesoup_mysql_dbname+"?allowMultiQueries=true&transformedBitIsBoolean=true");cfg.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");cfg.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");cfg.setProperty("hibernate.connection.username",stonesoup_mysql_user);cfg.setProperty("hibernate.connection.password",stonesoup_mysql_pass);cfg.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");cfg.setProperty("hibernate.current_session_context_class","thread");cfg.setProperty("hibernate.default_catalog",stonesoup_mysql_dbname);cfg.setProperty("org.hibernate.flushMode","MANUAL");cfg.setProperty("hibernate.hbm2ddl.auto","validate");cfg.setProperty("hibernate.connection.pool_size","1");cfg.addClass(SS_CWE_564_MYSQL.CustomerAndSuppliersByCity.class);cfg.addClass(SS_CWE_564_MYSQL.Invoices.class);cfg.addClass(SS_CWE_564_MYSQL.OrderDetailsExtended.class);cfg.addClass(SS_CWE_564_MYSQL.AlphabeticalListOfProducts.class);cfg.addClass(SS_CWE_564_MYSQL.OrdersQry.class);cfg.addClass(SS_CWE_564_MYSQL.CustomerDemographics.class);cfg.addClass(SS_CWE_564_MYSQL.Suppliers.class);cfg.addClass(SS_CWE_564_MYSQL.SalesByCategory.class);cfg.addClass(SS_CWE_564_MYSQL.ProductsByCategory.class);cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByQuarter.class);cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByYear.class);cfg.addClass(SS_CWE_564_MYSQL.Categories.class);cfg.addClass(SS_CWE_564_MYSQL.Shippers.class);cfg.addClass(SS_CWE_564_MYSQL.Employees.class);cfg.addClass(SS_CWE_564_MYSQL.Products.class);cfg.addClass(SS_CWE_564_MYSQL.CategorySalesFor1997.class);cfg.addClass(SS_CWE_564_MYSQL.OrderDetails.class);cfg.addClass(SS_CWE_564_MYSQL.Region.class);cfg.addClass(SS_CWE_564_MYSQL.QuarterlyOrders.class);cfg.addClass(SS_CWE_564_MYSQL.OrderSubtotals.class);cfg.addClass(SS_CWE_564_MYSQL.ProductsAboveAveragePrice.class);cfg.addClass(SS_CWE_564_MYSQL.Territories.class);cfg.addClass(SS_CWE_564_MYSQL.Customers.class);cfg.addClass(SS_CWE_564_MYSQL.Orders.class);cfg.addClass(SS_CWE_564_MYSQL.CurrentProductList.class);cfg.addClass(SS_CWE_564_MYSQL.SalesTotalsByAmount.class);cfg.addClass(SS_CWE_564_MYSQL.ProductSalesFor1997.class);ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();org.hibernate.SessionFactory factory=cfg.buildSessionFactory(serviceRegistry);org.hibernate.Session session=factory.openSession();Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");String hql="from SS_CWE_564_MYSQL.Customers where country = '" + recruitableOtto.getstupefiedness_retransmute()+"'";Tracer.tracepointVariableString("hql",hql);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");org.hibernate.Query query=session.createQuery(hql);Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");@SuppressWarnings("rawtypes") java.util.Iterator iter=query.iterate();while (iter.hasNext()){SS_CWE_564_MYSQL.Customers c=(SS_CWE_564_MYSQL.Customers)iter.next();AbstractDistributor.trinodalIndigestibly.print(String.format("%10s | ",c.getCustomerId()));AbstractDistributor.trinodalIndigestibly.print(String.format("%10s | ",c.getCompanyName()));AbstractDistributor.trinodalIndigestibly.print(String.format("%10s | ",c.getContactName()));AbstractDistributor.trinodalIndigestibly.print(String.format("%10s | ",c.getContactTitle()));AbstractDistributor.trinodalIndigestibly.print(String.format("%10s | ",c.getAddress()));AbstractDistributor.trinodalIndigestibly.print(String.format("%10s | ",c.getCity()));AbstractDistributor.trinodalIndigestibly.print(String.format("%10s | ",c.getRegion()));AbstractDistributor.trinodalIndigestibly.print(String.format("%10s | ",c.getPostalCode()));AbstractDistributor.trinodalIndigestibly.print(String.format("%10s | ",c.getCountry()));AbstractDistributor.trinodalIndigestibly.print(String.format("%10s | ",c.getPhone()));AbstractDistributor.trinodalIndigestibly.print(String.format("%10s | ",c.getFax()));AbstractDistributor.trinodalIndigestibly.println();}Tracer.tracepointMessage("TRIGGER-POINT: AFTER");session.flush();session.close();} catch (org.hibernate.HibernateException he){Tracer.tracepointError(he.getClass().getName() + ": "+he.getMessage());AbstractDistributor.trinodalIndigestibly.println("STONESOUP: Error accessing database.");he.printStackTrace(AbstractDistributor.trinodalIndigestibly);}}Tracer.tracepointWeaknessEnd();}

	public static void culpatoryBaruch() {
		culpatoryBaruch(null);
	}

}
