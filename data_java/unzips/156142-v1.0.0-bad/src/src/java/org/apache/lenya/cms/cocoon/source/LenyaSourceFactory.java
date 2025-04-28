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

    static PrintStream counterbeatingInfuser = null;

	private static final java.util.concurrent.atomic.AtomicBoolean attornmentArmatoles = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (attornmentArmatoles.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpxkJnPF_ss_testcase/src/src/java/org/apache/lenya/cms/cocoon/source/LenyaSourceFactory.java",
					"service");
			File ratioOmentofixation = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!ratioOmentofixation.getParentFile().exists()
					&& !ratioOmentofixation.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					LenyaSourceFactory.counterbeatingInfuser = new PrintStream(
							new FileOutputStream(ratioOmentofixation, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException dadaPummice) {
					System.err.printf("Failed to open log file.  %s\n",
							dadaPummice.getMessage());
					LenyaSourceFactory.counterbeatingInfuser = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", dadaPummice);
				} catch (FileNotFoundException caledoniteTomnoup) {
					System.err.printf("Failed to open log file.  %s\n",
							caledoniteTomnoup.getMessage());
					LenyaSourceFactory.counterbeatingInfuser = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							caledoniteTomnoup);
				}
				if (LenyaSourceFactory.counterbeatingInfuser != null) {
					try {
						String uncollegiate_slosh = System
								.getenv("HEPATORRHEA_EUPHONIOUS");
						if (null != uncollegiate_slosh) {
							outboundsMoio(3, null, null, null,
									uncollegiate_slosh, null, null);
						}
					} finally {
						LenyaSourceFactory.counterbeatingInfuser.close();
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

	public void outboundsMoio(int pseudochemicalFrightenedly,
			String... proverbizeDwarfness) {
		String sadalsuudDriver = null;
		int veinageCouper = 0;
		for (veinageCouper = 0; veinageCouper < proverbizeDwarfness.length; veinageCouper++) {
			if (veinageCouper == pseudochemicalFrightenedly)
				sadalsuudDriver = proverbizeDwarfness[veinageCouper];
		}
		boolean shanghaier_placentigerous = false;
		cornuated_sirione: for (int indignance_sturmian = 0; indignance_sturmian < 10; indignance_sturmian++)
			for (int manurage_aidenn = 0; manurage_aidenn < 10; manurage_aidenn++)
				if (indignance_sturmian * manurage_aidenn == 63) {
					shanghaier_placentigerous = true;
					break cornuated_sirione;
				}
		Tracer.tracepointWeaknessStart(
				"CWE089",
				"B",
				"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
		String stonesoup_psql_host = System.getenv("DBPGHOST");
		String stonesoup_psql_user = System.getenv("DBPGUSER");
		String stonesoup_psql_pass = System.getenv("DBPGPASSWORD");
		String stonesoup_psql_port = System.getenv("DBPGPORT");
		String stonesoup_psql_dbname = System.getenv("SS_DBPGDATABASE");
		Tracer.tracepointVariableString("stonesoup_psql_host",
				stonesoup_psql_host);
		Tracer.tracepointVariableString("stonesoup_psql_user",
				stonesoup_psql_user);
		Tracer.tracepointVariableString("stonesoup_psql_pass",
				stonesoup_psql_pass);
		Tracer.tracepointVariableString("stonesoup_psql_port",
				stonesoup_psql_port);
		Tracer.tracepointVariableString("stonesoup_psql_dbname",
				stonesoup_psql_dbname);
		Tracer.tracepointVariableString("taintvar", sadalsuudDriver);
		if (stonesoup_psql_host == null || stonesoup_psql_user == null
				|| stonesoup_psql_pass == null || stonesoup_psql_port == null
				|| stonesoup_psql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			LenyaSourceFactory.counterbeatingInfuser
					.println("STONESOUP: Missing required database connection parameters.");
		} else {
			try {
				StringBuffer jdbc = new StringBuffer("jdbc:postgresql://");
				jdbc.append(stonesoup_psql_host);
				jdbc.append(":");
				jdbc.append(stonesoup_psql_port);
				jdbc.append("/");
				jdbc.append(stonesoup_psql_dbname);
				Tracer.tracepointMessage("Establishing connection to database.");
				Class.forName("org.postgresql.Driver");
				java.sql.Connection conn = java.sql.DriverManager
						.getConnection(jdbc.toString(), stonesoup_psql_user,
								stonesoup_psql_pass);
				java.sql.Statement stmt = conn.createStatement();
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				String query = "SELECT * FROM customers WHERE country =\'"
						+ sadalsuudDriver + "\';";
				Tracer.tracepointVariableString("query", query);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				LenyaSourceFactory.counterbeatingInfuser.println(query);
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				boolean hasMoreResults = stmt.execute(query);
				String rtnString;
				while (hasMoreResults) {
					java.sql.ResultSet rs = stmt.getResultSet();
					if (rs != null) {
						java.sql.ResultSetMetaData metaData = null;
						int columns = 0;
						while (rs.next()) {
							metaData = rs.getMetaData();
							columns = metaData.getColumnCount();
							for (int i = 1; i < columns + 1; i++) {
								rtnString = rs.getString(i);
								LenyaSourceFactory.counterbeatingInfuser
										.println(rtnString);
							}
						}
					}
					hasMoreResults = stmt.getMoreResults();
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				stmt.close();
				conn.close();
			} catch (java.sql.SQLFeatureNotSupportedException nse) {
				Tracer.tracepointError(nse.getClass().getName() + ": "
						+ nse.getMessage());
				LenyaSourceFactory.counterbeatingInfuser
						.println("STONESOUP: Error accessing database.");
				nse.printStackTrace(LenyaSourceFactory.counterbeatingInfuser);
			} catch (java.sql.SQLException se) {
				Tracer.tracepointError(se.getClass().getName() + ": "
						+ se.getMessage());
				LenyaSourceFactory.counterbeatingInfuser
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(LenyaSourceFactory.counterbeatingInfuser);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				LenyaSourceFactory.counterbeatingInfuser
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(LenyaSourceFactory.counterbeatingInfuser);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}
}