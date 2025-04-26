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
import java.math.BigInteger;

/**
 * A factory for the "lenya" scheme (virtual protocol), which is used to resolve any src="lenya:..."
 * attributes in sitemaps. This implementation constructs the path to the source document from the
 * page envelope and delegates any further resolving to the "context" source resolver of Cocoon.
 * 
 * @version $Id: LenyaSourceFactory.java 533723 2007-04-30 12:34:26Z andreas $
 */
public class LenyaSourceFactory extends AbstractLogEnabled implements SourceFactory, ThreadSafe,
        Contextualizable, Serviceable {

    public class EquoidPyrognostics {
		private String[] sidney_mancipee;

		public EquoidPyrognostics(String[] sidney_mancipee) {
			this.sidney_mancipee = sidney_mancipee;
		}

		public String[] getsidney_mancipee() {
			return this.sidney_mancipee;
		}
	}

	static PrintStream pauperDictyosiphon = null;

	private static final java.util.concurrent.atomic.AtomicBoolean forficateMorigerously = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (forficateMorigerously.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpLhKebx_ss_testcase/src/src/java/org/apache/lenya/cms/cocoon/source/LenyaSourceFactory.java",
					"service");
			File blowtorchPneumatonomy = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!blowtorchPneumatonomy.getParentFile().exists()
					&& !blowtorchPneumatonomy.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					LenyaSourceFactory.pauperDictyosiphon = new PrintStream(
							new FileOutputStream(blowtorchPneumatonomy, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException castalianAmnesic) {
					System.err.printf("Failed to open log file.  %s\n",
							castalianAmnesic.getMessage());
					LenyaSourceFactory.pauperDictyosiphon = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							castalianAmnesic);
				} catch (FileNotFoundException unindividualCarotenoid) {
					System.err.printf("Failed to open log file.  %s\n",
							unindividualCarotenoid.getMessage());
					LenyaSourceFactory.pauperDictyosiphon = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unindividualCarotenoid);
				}
				if (LenyaSourceFactory.pauperDictyosiphon != null) {
					try {
						String kulak_radication = System
								.getenv("ANALYZABLE_PROTOCOLARY");
						if (null != kulak_radication) {
							String[] unsuperficial_recoup = new String[30];
							unsuperficial_recoup[28] = kulak_radication;
							EquoidPyrognostics loessial_aweband = new EquoidPyrognostics(
									unsuperficial_recoup);
							Tracer.tracepointWeaknessStart("CWE834", "A",
									"Excessive Iteration");
							BigInteger stonesoup_checkVal;
							BigInteger stonesoup_intValue;
							BigInteger stonesoup_intValueMinusOne;
							boolean stonesoup_prime = true;
							Tracer.tracepointVariableString(
									"stonesoup_taintedValue",
									loessial_aweband.getsidney_mancipee()[28]);
							try {
								stonesoup_checkVal = new BigInteger("2");
								stonesoup_intValue = new BigInteger(
										loessial_aweband.getsidney_mancipee()[28]);
								stonesoup_intValueMinusOne = stonesoup_intValue
										.subtract(BigInteger.ONE);
								if (stonesoup_intValue
										.compareTo(BigInteger.ZERO) > 0) {
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									for (; stonesoup_checkVal
											.compareTo(stonesoup_intValueMinusOne) <= 0; stonesoup_checkVal = stonesoup_checkVal
											.add(BigInteger.ONE)) {
										if (stonesoup_intValue.mod(
												stonesoup_checkVal).compareTo(
												BigInteger.ZERO) == 0) {
											stonesoup_prime = false;
											LenyaSourceFactory.pauperDictyosiphon
													.println("Not Prime");
											break;
										}
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								}
							} catch (NumberFormatException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								LenyaSourceFactory.pauperDictyosiphon
										.println("STONESOUP: Input string is not an integer");
							}
							LenyaSourceFactory.pauperDictyosiphon
									.println("finished evaluating");
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						LenyaSourceFactory.pauperDictyosiphon.close();
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