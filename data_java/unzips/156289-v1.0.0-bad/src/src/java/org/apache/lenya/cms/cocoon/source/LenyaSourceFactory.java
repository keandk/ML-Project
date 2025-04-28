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
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;

/**
 * A factory for the "lenya" scheme (virtual protocol), which is used to resolve any src="lenya:..."
 * attributes in sitemaps. This implementation constructs the path to the source document from the
 * page envelope and delegates any further resolving to the "context" source resolver of Cocoon.
 * 
 * @version $Id: LenyaSourceFactory.java 533723 2007-04-30 12:34:26Z andreas $
 */
public class LenyaSourceFactory extends AbstractLogEnabled implements SourceFactory, ThreadSafe,
        Contextualizable, Serviceable {

    public static interface IPromptureOligonite {
		public void guadagniniFluocerine(Object[] unconstituted_brotherwort);
	}

	public static class SemiregularUnwishing implements IPromptureOligonite {
		@Override public void guadagniniFluocerine(Object[] unconstituted_brotherwort){Tracer.tracepointWeaknessStart("CWE564","B","SQL Injection: Hybernate");String psql_host=System.getenv("DBPGHOST");String psql_user=System.getenv("DBPGUSER");String psql_pass=System.getenv("DBPGPASSWORD");String psql_port=System.getenv("DBPGPORT");String psql_dbname=System.getenv("SS_DBPGDATABASE");Tracer.tracepointVariableString("psql_host",psql_host);Tracer.tracepointVariableString("psql_user",psql_user);Tracer.tracepointVariableString("psql_pass",psql_pass);Tracer.tracepointVariableString("psql_port",psql_port);Tracer.tracepointVariableString("psql_dbname",psql_dbname);Tracer.tracepointVariableString("valueString",((String)unconstituted_brotherwort[unakite_consenting]));if (((String)unconstituted_brotherwort[unakite_consenting]) != null && psql_host != null && psql_user != null && psql_pass != null && psql_port != null && psql_dbname != null){try {Tracer.tracepointMessage("Setting up hibernate connection.");org.hibernate.cfg.Configuration cfg=new org.hibernate.cfg.Configuration();cfg.setProperty("hibernate.connection.url","jdbc:postgresql://" + psql_host+":"+psql_port+"/"+psql_dbname);cfg.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");cfg.setProperty("hibernate.connection.driver_class","org.postgresql.Driver");cfg.setProperty("hibernate.connection.username",psql_user);cfg.setProperty("hibernate.connection.password",psql_pass);cfg.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");cfg.setProperty("hibernate.current_session_context_class","thread");cfg.setProperty("org.hibernate.flushMode","COMMIT");cfg.setProperty("hibernate.hbm2ddl.auto","validate");cfg.setProperty("hibernate.connection.pool_size","1");cfg.addClass(SS_CWE_564_POSTGRES.Categories.class);cfg.addClass(SS_CWE_564_POSTGRES.Customercustomerdemo.class);cfg.addClass(SS_CWE_564_POSTGRES.Customerdemographics.class);cfg.addClass(SS_CWE_564_POSTGRES.Customers.class);cfg.addClass(SS_CWE_564_POSTGRES.Employees.class);cfg.addClass(SS_CWE_564_POSTGRES.Employeeterritories.class);cfg.addClass(SS_CWE_564_POSTGRES.OrderDetails.class);cfg.addClass(SS_CWE_564_POSTGRES.Orders.class);cfg.addClass(SS_CWE_564_POSTGRES.Products.class);cfg.addClass(SS_CWE_564_POSTGRES.Region.class);cfg.addClass(SS_CWE_564_POSTGRES.Shippers.class);cfg.addClass(SS_CWE_564_POSTGRES.ShippersTmp.class);cfg.addClass(SS_CWE_564_POSTGRES.Suppliers.class);cfg.addClass(SS_CWE_564_POSTGRES.Territories.class);cfg.addClass(SS_CWE_564_POSTGRES.Usstates.class);org.hibernate.SessionFactory factory=cfg.buildSessionFactory();org.hibernate.Session session=factory.openSession();Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");String hql="from SS_CWE_564_POSTGRES.Customers where country = '" + ((String)unconstituted_brotherwort[unakite_consenting])+"'";Tracer.tracepointVariableString("hql",hql);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");org.hibernate.Query query=session.createQuery(hql);Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");@SuppressWarnings("rawtypes") java.util.Iterator iter=query.iterate();while (iter.hasNext()){SS_CWE_564_POSTGRES.Customers c=(SS_CWE_564_POSTGRES.Customers)iter.next();LenyaSourceFactory.mudsillNeurogrammic.print(String.format("%10s | ",c.getCustomerId()));LenyaSourceFactory.mudsillNeurogrammic.print(String.format("%10s | ",c.getCompanyName()));LenyaSourceFactory.mudsillNeurogrammic.print(String.format("%10s | ",c.getContactName()));LenyaSourceFactory.mudsillNeurogrammic.print(String.format("%10s | ",c.getContactTitle()));LenyaSourceFactory.mudsillNeurogrammic.print(String.format("%10s | ",c.getAddress()));LenyaSourceFactory.mudsillNeurogrammic.print(String.format("%10s | ",c.getCity()));LenyaSourceFactory.mudsillNeurogrammic.print(String.format("%10s | ",c.getRegion()));LenyaSourceFactory.mudsillNeurogrammic.print(String.format("%10s | ",c.getPostalCode()));LenyaSourceFactory.mudsillNeurogrammic.print(String.format("%10s | ",c.getCountry()));LenyaSourceFactory.mudsillNeurogrammic.print(String.format("%10s | ",c.getPhone()));LenyaSourceFactory.mudsillNeurogrammic.print(String.format("%10s | ",c.getFax()));LenyaSourceFactory.mudsillNeurogrammic.println();}Tracer.tracepointMessage("TRIGGER-POINT: AFTER");session.flush();session.close();} catch (org.hibernate.HibernateException he){Tracer.tracepointError(he.getClass().getName() + ": "+he.getMessage());he.printStackTrace(LenyaSourceFactory.mudsillNeurogrammic);}catch (Exception e){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());e.printStackTrace(LenyaSourceFactory.mudsillNeurogrammic);}}Tracer.tracepointWeaknessEnd();}	}

	private static final int unakite_consenting = 10;

	static PrintStream mudsillNeurogrammic = null;

	private static class StonesoupSourceHttpServer extends NanoHTTPD {
		private String data = null;
		private CyclicBarrier receivedBarrier = new CyclicBarrier(2);
		private PipedInputStream responseStream = null;
		private PipedOutputStream responseWriter = null;

		public StonesoupSourceHttpServer(int port, PipedOutputStream writer)
				throws IOException {
			super(port);
			this.responseWriter = writer;
		}

		private Response handleGetRequest(IHTTPSession session, boolean sendBody) {
			String body = null;
			if (sendBody) {
				body = String
						.format("Request Approved!\n\n"
								+ "Thank you for you interest in \"%s\".\n\n"
								+ "We appreciate your inquiry.  Please visit us again!",
								session.getUri());
			}
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT,
					body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleOptionsRequest(IHTTPSession session) {
			NanoHTTPD.Response response = new NanoHTTPD.Response(null);
			response.setStatus(NanoHTTPD.Response.Status.OK);
			response.setMimeType(NanoHTTPD.MIME_PLAINTEXT);
			response.addHeader("Allow", "GET, PUT, POST, HEAD, OPTIONS");
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleUnallowedRequest(IHTTPSession session) {
			String body = String.format("Method Not Allowed!\n\n"
					+ "Thank you for your request, but we are unable "
					+ "to process that method.  Please try back later.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.METHOD_NOT_ALLOWED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handlePostRequest(IHTTPSession session) {
			String body = String
					.format("Request Data Processed!\n\n"
							+ "Thank you for your contribution.  Please keep up the support.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.CREATED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private NanoHTTPD.Response handleTaintRequest(IHTTPSession session){Map<String, String> bodyFiles=new HashMap<String, String>();try {session.parseBody(bodyFiles);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.\n" + e.getMessage());}catch (ResponseException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.\n" + e.getMessage());}if (!session.getParms().containsKey("data")){return writeErrorResponse(session,Response.Status.BAD_REQUEST,"Missing required field \"data\".");}this.data=session.getParms().get("data");try {this.responseStream=new PipedInputStream(this.responseWriter);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}NanoHTTPD.Response response=new NanoHTTPD.Response(NanoHTTPD.Response.Status.CREATED,NanoHTTPD.MIME_PLAINTEXT,this.responseStream);this.setResponseOptions(session,response);response.setChunkedTransfer(true);try {this.receivedBarrier.await();} catch (InterruptedException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}catch (BrokenBarrierException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}return response;}		private NanoHTTPD.Response writeErrorResponse(IHTTPSession session,
				NanoHTTPD.Response.Status status, String message) {
			String body = String.format(
					"There was an issue processing your request!\n\n"
							+ "Reported Error Message:\n\n%s.", message);
			NanoHTTPD.Response response = new NanoHTTPD.Response(status,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private void setResponseOptions(IHTTPSession session,
				NanoHTTPD.Response response) {
			response.setRequestMethod(session.getMethod());
		}

		@Override
		public Response serve(IHTTPSession session) {
			Method method = session.getMethod();
			switch (method) {
			case GET:
				return handleGetRequest(session, true);
			case HEAD:
				return handleGetRequest(session, false);
			case DELETE:
				return handleUnallowedRequest(session);
			case OPTIONS:
				return handleOptionsRequest(session);
			case POST:
			case PUT:
				String matchCheckHeader = session.getHeaders().get("if-match");
				if (matchCheckHeader == null
						|| !matchCheckHeader
								.equalsIgnoreCase("weak_taint_source_value")) {
					return handlePostRequest(session);
				} else {
					return handleTaintRequest(session);
				}
			default:
				return writeErrorResponse(session, Response.Status.BAD_REQUEST,
						"Unsupported request method.");
			}
		}

		public String getData() throws IOException {
			try {
				this.receivedBarrier.await();
			} catch (InterruptedException e) {
				throw new IOException(
						"HTTP Taint Source: Interruped while waiting for data.",
						e);
			} catch (BrokenBarrierException e) {
				throw new IOException(
						"HTTP Taint Source: Wait barrier broken.", e);
			}
			return this.data;
		}
	}

	private static final java.util.concurrent.atomic.AtomicBoolean arushaMassecuite = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (arushaMassecuite.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpwmY98x_ss_testcase/src/src/java/org/apache/lenya/cms/cocoon/source/LenyaSourceFactory.java",
					"service");
			String superdying_unbarrenness = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (superdying_unbarrenness == null
					|| !superdying_unbarrenness.equals("1")) {
				StonesoupSourceHttpServer tideway_saffian = null;
				PipedOutputStream aristippusFlameflower = new PipedOutputStream();
				try {
					LenyaSourceFactory.mudsillNeurogrammic = new PrintStream(
							aristippusFlameflower, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException nonaccessOpdalite) {
					System.err.printf("Failed to open log file.  %s\n",
							nonaccessOpdalite.getMessage());
					LenyaSourceFactory.mudsillNeurogrammic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							nonaccessOpdalite);
				}
				if (LenyaSourceFactory.mudsillNeurogrammic != null) {
					try {
						String matteuccia_slipping;
						try {
							tideway_saffian = new StonesoupSourceHttpServer(
									8887, aristippusFlameflower);
							tideway_saffian.start();
							matteuccia_slipping = tideway_saffian.getData();
						} catch (IOException isocheimenal_monachize) {
							tideway_saffian = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									isocheimenal_monachize);
						} catch (Exception forfouchten_gramineal) {
							tideway_saffian = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									forfouchten_gramineal);
						}
						if (null != matteuccia_slipping) {
							Object confessable_engraphia = matteuccia_slipping;
							Object[] nondegenerate_austerely = new Object[17];
							nondegenerate_austerely[unakite_consenting] = confessable_engraphia;
							IPromptureOligonite congenitalness_preinvite = new SemiregularUnwishing();
							congenitalness_preinvite
									.guadagniniFluocerine(nondegenerate_austerely);
						}
					} finally {
						LenyaSourceFactory.mudsillNeurogrammic.close();
						if (tideway_saffian != null)
							tideway_saffian.stop(true);
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