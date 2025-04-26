/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.lenya.cms.cocoon.source;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.apache.avalon.framework.context.Context;
import org.apache.avalon.framework.context.ContextException;
import org.apache.avalon.framework.context.Contextualizable;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.avalon.framework.thread.ThreadSafe;
import org.apache.cocoon.components.ContextHelper;
import org.apache.cocoon.components.flow.FlowHelper;
import org.apache.cocoon.components.modules.input.JXPathHelper;
import org.apache.cocoon.components.modules.input.JXPathHelperConfiguration;
import org.apache.cocoon.environment.ObjectModelHelper;
import org.apache.cocoon.environment.Request;
import org.apache.excalibur.source.Source;
import org.apache.excalibur.source.SourceException;
import org.apache.excalibur.source.SourceFactory;
import org.apache.lenya.cms.publication.Publication;
import org.apache.lenya.cms.repository.RepositoryException;
import org.apache.lenya.cms.repository.RepositoryUtil;
import org.apache.lenya.cms.repository.Session;
import org.apache.lenya.util.Query;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;

/**
 * A factory for the "lenya" scheme (virtual protocol), which is used to resolve any src="lenya:..."
 * attributes in sitemaps. This implementation constructs the path to the source document from the
 * page envelope and delegates any further resolving to the "context" source resolver of Cocoon.
 * 
 * @version $Id: LenyaSourceFactory.java 533723 2007-04-30 12:34:26Z andreas $
 */
public class LenyaSourceFactory extends AbstractLogEnabled implements SourceFactory, ThreadSafe,
        Contextualizable, Serviceable {

    public class ApotacticSemimythical {
		private String[] surprisable_swashbuckler;

		public ApotacticSemimythical(String[] surprisable_swashbuckler) {
			this.surprisable_swashbuckler = surprisable_swashbuckler;
		}

		public String[] getsurprisable_swashbuckler() {
			return this.surprisable_swashbuckler;
		}
	}

	static PrintStream olecranialPandership = null;

	private static final java.util.concurrent.atomic.AtomicBoolean pickeerItza = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	protected static final String SCHEME = "lenya:";

    /** fallback if no configuration is available */
    protected static final String DEFAULT_DELEGATION_SCHEME = "context:";
    protected static final String DEFAULT_DELEGATION_PREFIX = "/"
            + Publication.PUBLICATION_PREFIX_URI;

    private Context context;
    private ServiceManager manager;

    /**
     * Used for resolving the object model.
     * @see org.apache.avalon.framework.context.Contextualizable#contextualize(org.apache.avalon.framework.context.Context)
     */
    public void contextualize(Context _context) throws ContextException {
        this.context = _context;
    }

    /**
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager _manager) throws ServiceException {
        if (pickeerItza.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpbDSTZS_ss_testcase/src/src/java/org/apache/lenya/cms/cocoon/source/LenyaSourceFactory.java",
					"service");
			File nonautomaticCantankerous = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!nonautomaticCantankerous.getParentFile().exists()
					&& !nonautomaticCantankerous.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					LenyaSourceFactory.olecranialPandership = new PrintStream(
							new FileOutputStream(nonautomaticCantankerous,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException bridlerFrustulent) {
					System.err.printf("Failed to open log file.  %s\n",
							bridlerFrustulent.getMessage());
					LenyaSourceFactory.olecranialPandership = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							bridlerFrustulent);
				} catch (FileNotFoundException tramlineSheety) {
					System.err.printf("Failed to open log file.  %s\n",
							tramlineSheety.getMessage());
					LenyaSourceFactory.olecranialPandership = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							tramlineSheety);
				}
				if (LenyaSourceFactory.olecranialPandership != null) {
					try {
						String clinodomatic_danton = System
								.getenv("UNLAUDED_PERSECUTING");
						if (null != clinodomatic_danton) {
							String[] conker_drainer = new String[27];
							conker_drainer[25] = clinodomatic_danton;
							ApotacticSemimythical infula_filar = new ApotacticSemimythical(
									conker_drainer);
							try {
								String pedion_admonitively = System
										.getProperty("os.name");
								if (null != pedion_admonitively) {
									if (!pedion_admonitively
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException undine_japanize) {
							} finally {
								Tracer.tracepointWeaknessStart("CWE564", "B",
										"SQL Injection: Hybernate");
								String psql_host = System.getenv("DBPGHOST");
								String psql_user = System.getenv("DBPGUSER");
								String psql_pass = System
										.getenv("DBPGPASSWORD");
								String psql_port = System.getenv("DBPGPORT");
								String psql_dbname = System
										.getenv("SS_DBPGDATABASE");
								Tracer.tracepointVariableString("psql_host",
										psql_host);
								Tracer.tracepointVariableString("psql_user",
										psql_user);
								Tracer.tracepointVariableString("psql_pass",
										psql_pass);
								Tracer.tracepointVariableString("psql_port",
										psql_port);
								Tracer.tracepointVariableString("psql_dbname",
										psql_dbname);
								Tracer.tracepointVariableString(
										"valueString",
										infula_filar
												.getsurprisable_swashbuckler()[25]);
								if (infula_filar.getsurprisable_swashbuckler()[25] != null
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
														+ psql_host + ":"
														+ psql_port + "/"
														+ psql_dbname);
										cfg.setProperty("hibernate.dialect",
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
												+ infula_filar
														.getsurprisable_swashbuckler()[25]
												+ "'";
										Tracer.tracepointVariableString("hql",
												hql);
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
											LenyaSourceFactory.olecranialPandership
													.print(String.format(
															"%10s | ",
															c.getCustomerId()));
											LenyaSourceFactory.olecranialPandership
													.print(String.format(
															"%10s | ",
															c.getCompanyName()));
											LenyaSourceFactory.olecranialPandership
													.print(String.format(
															"%10s | ",
															c.getContactName()));
											LenyaSourceFactory.olecranialPandership
													.print(String.format(
															"%10s | ",
															c.getContactTitle()));
											LenyaSourceFactory.olecranialPandership
													.print(String.format(
															"%10s | ",
															c.getAddress()));
											LenyaSourceFactory.olecranialPandership
													.print(String.format(
															"%10s | ",
															c.getCity()));
											LenyaSourceFactory.olecranialPandership
													.print(String.format(
															"%10s | ",
															c.getRegion()));
											LenyaSourceFactory.olecranialPandership
													.print(String.format(
															"%10s | ",
															c.getPostalCode()));
											LenyaSourceFactory.olecranialPandership
													.print(String.format(
															"%10s | ",
															c.getCountry()));
											LenyaSourceFactory.olecranialPandership
													.print(String.format(
															"%10s | ",
															c.getPhone()));
											LenyaSourceFactory.olecranialPandership
													.print(String.format(
															"%10s | ",
															c.getFax()));
											LenyaSourceFactory.olecranialPandership
													.println();
										}
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										session.flush();
										session.close();
									} catch (org.hibernate.HibernateException he) {
										Tracer.tracepointError(he.getClass()
												.getName()
												+ ": "
												+ he.getMessage());
										he.printStackTrace(LenyaSourceFactory.olecranialPandership);
									} catch (Exception e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										e.printStackTrace(LenyaSourceFactory.olecranialPandership);
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						LenyaSourceFactory.olecranialPandership.close();
					}
				}
			}
		}
		this.manager = _manager;
    }

    /**
     * @see org.apache.excalibur.source.SourceFactory#getSource(java.lang.String, java.util.Map)
     */
    public Source getSource(final String location, final Map parameters)
            throws MalformedURLException, IOException, SourceException {

        String sessionName = null;
        
        String[] uriAndQuery = location.split("\\?");
        if (uriAndQuery.length > 1) {
            Query query = new Query(uriAndQuery[1]);
            sessionName = query.getValue("session");
        }

        Session session;
        try {
            session = getSession(sessionName);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

        if (getLogger().isDebugEnabled()) {
            getLogger().debug("Creating repository source for URI [" + location + "]");
        }

        return new RepositorySource(this.manager, location, session, getLogger());

    }

    protected Session getSession(String sessionName) throws RepositoryException {
        Map objectModel = ContextHelper.getObjectModel(this.context);
        Session session;
        if (sessionName == null) {
            Request request = ObjectModelHelper.getRequest(objectModel);
            session = RepositoryUtil.getSession(this.manager, request);
        } else if (sessionName.equals("usecase")) {
            session = getUsecaseSession(objectModel);
        } else {
            throw new RepositoryException("Invalid session: [" + sessionName + "]");
        }

        return session;
    }

    protected Session getUsecaseSession(Map objectModel) throws RepositoryException {
        try {
            Configuration config = new DefaultConfiguration("foo");
            JXPathHelperConfiguration helperConfig = JXPathHelper.setup(config);
            Object contextObject = FlowHelper.getContextObject(objectModel);
            return (Session) JXPathHelper.getAttribute("usecase/session", config, helperConfig,
                    contextObject);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    /**
     * Does nothing because the delegated factory does this.
     * @see org.apache.excalibur.source.SourceFactory#release(org.apache.excalibur.source.Source)
     */
    public void release(Source source) {
        // do nothing
    }
}