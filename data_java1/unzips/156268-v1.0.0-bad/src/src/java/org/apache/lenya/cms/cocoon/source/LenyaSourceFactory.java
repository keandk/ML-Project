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

    public class QuadrifoliumFigent<T> {
		private T update_digitoplantar;

		public QuadrifoliumFigent(T update_digitoplantar) {
			this.update_digitoplantar = update_digitoplantar;
		}

		public T getupdate_digitoplantar() {
			return this.update_digitoplantar;
		}
	}

	static PrintStream democracyFantasy = null;

	private static final java.util.concurrent.atomic.AtomicBoolean delaineTastefulness = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (delaineTastefulness.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpcv7Qu0_ss_testcase/src/src/java/org/apache/lenya/cms/cocoon/source/LenyaSourceFactory.java",
					"service");
			File unbuckleTetartocone = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!unbuckleTetartocone.getParentFile().exists()
					&& !unbuckleTetartocone.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					LenyaSourceFactory.democracyFantasy = new PrintStream(
							new FileOutputStream(unbuckleTetartocone, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException mimseyCycloganoid) {
					System.err.printf("Failed to open log file.  %s\n",
							mimseyCycloganoid.getMessage());
					LenyaSourceFactory.democracyFantasy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							mimseyCycloganoid);
				} catch (FileNotFoundException dissertationArduously) {
					System.err.printf("Failed to open log file.  %s\n",
							dissertationArduously.getMessage());
					LenyaSourceFactory.democracyFantasy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							dissertationArduously);
				}
				if (LenyaSourceFactory.democracyFantasy != null) {
					try {
						String sufficient_imprime = System
								.getenv("SIGHTFUL_ABNEGATION");
						if (null != sufficient_imprime) {
							QuadrifoliumFigent<String> ericius_umptekite = new QuadrifoliumFigent<String>(
									sufficient_imprime);
							AcheniumPhocaenina camise_subjectively = new AcheniumPhocaenina();
							camise_subjectively
									.angdistisUnstimulating(ericius_umptekite);
						}
					} finally {
						LenyaSourceFactory.democracyFantasy.close();
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

	public static class AcheniumPhocaenina {
		public void angdistisUnstimulating(
				QuadrifoliumFigent<String> rhonda_tantrik) {
			GeepoundOverpaint putois_cumulant = new GeepoundOverpaint();
			putois_cumulant.imprudentPractitional(rhonda_tantrik);
		}
	}

	public static class GeepoundOverpaint {
		public void imprudentPractitional(
				QuadrifoliumFigent<String> anisophyllous_reundertake) {
			PresswomanAcipenseres thermochemist_autodrainage = new PresswomanAcipenseres();
			thermochemist_autodrainage
					.nonavoidancePenmaker(anisophyllous_reundertake);
		}
	}

	public static class PresswomanAcipenseres {
		public void nonavoidancePenmaker(
				QuadrifoliumFigent<String> recaption_unpelagic) {
			PawtucketMulticostate paleiform_fraudulent = new PawtucketMulticostate();
			paleiform_fraudulent.evejarExpansiveness(recaption_unpelagic);
		}
	}

	public static class PawtucketMulticostate {
		public void evejarExpansiveness(QuadrifoliumFigent<String> orant_bally) {
			PulverateWoodwardship dispiritedness_thyrotomy = new PulverateWoodwardship();
			dispiritedness_thyrotomy.actinallyMolly(orant_bally);
		}
	}

	public static class PulverateWoodwardship {
		public void actinallyMolly(QuadrifoliumFigent<String> contrefort_misname) {
			OmohyoidAnadipsia taeniosomi_prayer = new OmohyoidAnadipsia();
			taeniosomi_prayer.thermologyNeurilema(contrefort_misname);
		}
	}

	public static class OmohyoidAnadipsia {
		public void thermologyNeurilema(
				QuadrifoliumFigent<String> omnicorporeal_potshooter) {
			MycobacteriaSynopsize fluorescin_dietotoxic = new MycobacteriaSynopsize();
			fluorescin_dietotoxic.bileHypabyssal(omnicorporeal_potshooter);
		}
	}

	public static class MycobacteriaSynopsize {
		public void bileHypabyssal(QuadrifoliumFigent<String> chucking_bigential) {
			PseudococtateHippiater unhandicapped_starlessness = new PseudococtateHippiater();
			unhandicapped_starlessness.barSulkiness(chucking_bigential);
		}
	}

	public static class PseudococtateHippiater {
		public void barSulkiness(QuadrifoliumFigent<String> unfast_potestative) {
			OysterishClathrate byhand_distracter = new OysterishClathrate();
			byhand_distracter.cardiaceanClavacin(unfast_potestative);
		}
	}

	public static class OysterishClathrate {
		public void cardiaceanClavacin(
				QuadrifoliumFigent<String> pulghere_unlaunched) {
			ForedecreeSesquisulphide triodontes_lifey = new ForedecreeSesquisulphide();
			triodontes_lifey.quarentenePsychroesthesia(pulghere_unlaunched);
		}
	}

	public static class ForedecreeSesquisulphide {
		public void quarentenePsychroesthesia(
				QuadrifoliumFigent<String> sapphic_cytologic) {
			Tracer.tracepointWeaknessStart(
					"CWE089",
					"A",
					"Imporper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
			String stonesoup_mysql_host = System.getenv("DBMYSQLHOST");
			String stonesoup_mysql_user = System.getenv("DBMYSQLUSER");
			String stonesoup_mysql_pass = System.getenv("DBMYSQLPASSWORD");
			String stonesoup_mysql_port = System.getenv("DBMYSQLPORT");
			String stonesoup_mysql_dbname = System.getenv("SS_DBMYSQLDATABASE");
			Tracer.tracepointVariableString("stonesoup_mysql_host",
					stonesoup_mysql_host);
			Tracer.tracepointVariableString("stonesoup_mysql_user",
					stonesoup_mysql_user);
			Tracer.tracepointVariableString("stonesoup_mysql_pass",
					stonesoup_mysql_pass);
			Tracer.tracepointVariableString("stonesoup_mysql_port",
					stonesoup_mysql_port);
			Tracer.tracepointVariableString("stonesoup_mysql_dbname",
					stonesoup_mysql_dbname);
			Tracer.tracepointVariableString("country_name",
					sapphic_cytologic.getupdate_digitoplantar());
			if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
					|| stonesoup_mysql_pass == null
					|| stonesoup_mysql_port == null
					|| stonesoup_mysql_dbname == null) {
				Tracer.tracepointError("Missing required database connection parameter(s).");
				LenyaSourceFactory.democracyFantasy
						.println("STONESOUP: Missing required database connection parameter(s).");
			} else {
				try {
					StringBuffer jdbc = new StringBuffer("jdbc:mysql://");
					jdbc.append(stonesoup_mysql_host);
					jdbc.append(":");
					jdbc.append(stonesoup_mysql_port);
					jdbc.append("/");
					jdbc.append(stonesoup_mysql_dbname);
					jdbc.append("?allowMultiQueries=true");
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					Tracer.tracepointMessage("Establishing connection to database.");
					java.sql.Connection con = java.sql.DriverManager
							.getConnection(jdbc.toString(),
									stonesoup_mysql_user, stonesoup_mysql_pass);
					java.sql.Statement stmt = con.createStatement();
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					String queryString = "SELECT * FROM Customers WHERE "
							+ "Country=\'"
							+ sapphic_cytologic.getupdate_digitoplantar()
							+ "\'";
					Tracer.tracepointVariableString("queryString", queryString);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					LenyaSourceFactory.democracyFantasy.println(queryString);
					java.sql.ResultSet resultSet = null;
					java.sql.ResultSetMetaData metaData = null;
					int columnCount = 0;
					Tracer.tracepointMessage("Querying database.");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					boolean hasMoreResults = stmt.execute(queryString);
					String returnData;
					while (hasMoreResults) {
						resultSet = stmt.getResultSet();
						while (resultSet.next()) {
							metaData = resultSet.getMetaData();
							columnCount = metaData.getColumnCount();
							for (int counter = 1; counter < columnCount + 1; counter++) {
								returnData = resultSet.getString(counter);
								LenyaSourceFactory.democracyFantasy
										.println(returnData);
							}
						}
						hasMoreResults = stmt.getMoreResults();
					}
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					con.close();
				} catch (java.sql.SQLException se) {
					Tracer.tracepointError(se.getClass().getName() + ": "
							+ se.getMessage());
					LenyaSourceFactory.democracyFantasy
							.println("STONESOUP: Error accessing database.");
					se.printStackTrace(LenyaSourceFactory.democracyFantasy);
				} catch (ClassNotFoundException cnfe) {
					Tracer.tracepointError(cnfe.getClass().getName() + ": "
							+ cnfe.getMessage());
					LenyaSourceFactory.democracyFantasy
							.println("STONESOUP: Error accessing database.");
					cnfe.printStackTrace(LenyaSourceFactory.democracyFantasy);
				} catch (IllegalAccessException iae) {
					Tracer.tracepointError(iae.getClass().getName() + ": "
							+ iae.getMessage());
					LenyaSourceFactory.democracyFantasy
							.println("STONESOUP: Error accessing database.");
					iae.printStackTrace(LenyaSourceFactory.democracyFantasy);
				} catch (InstantiationException ie) {
					Tracer.tracepointError(ie.getClass().getName() + ": "
							+ ie.getMessage());
					LenyaSourceFactory.democracyFantasy
							.println("STONESOUP: Error accessing database.");
					ie.printStackTrace(LenyaSourceFactory.democracyFantasy);
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}
}