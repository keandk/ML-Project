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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * A factory for the "lenya" scheme (virtual protocol), which is used to resolve any src="lenya:..."
 * attributes in sitemaps. This implementation constructs the path to the source document from the
 * page envelope and delegates any further resolving to the "context" source resolver of Cocoon.
 * 
 * @version $Id: LenyaSourceFactory.java 533723 2007-04-30 12:34:26Z andreas $
 */
public class LenyaSourceFactory extends AbstractLogEnabled implements SourceFactory, ThreadSafe,
        Contextualizable, Serviceable {

    private static final int compendia_gerbillinae = 3;

	static PrintStream centiloquyFurfuran = null;

	private static final java.util.concurrent.atomic.AtomicBoolean propulsorShachly = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (propulsorShachly.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpZkB2el_ss_testcase/src/src/java/org/apache/lenya/cms/cocoon/source/LenyaSourceFactory.java",
					"service");
			File oilstockPredecide = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!oilstockPredecide.getParentFile().exists()
					&& !oilstockPredecide.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					LenyaSourceFactory.centiloquyFurfuran = new PrintStream(
							new FileOutputStream(oilstockPredecide, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException quadArtemisia) {
					System.err.printf("Failed to open log file.  %s\n",
							quadArtemisia.getMessage());
					LenyaSourceFactory.centiloquyFurfuran = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							quadArtemisia);
				} catch (FileNotFoundException yieldinglyIndefensive) {
					System.err.printf("Failed to open log file.  %s\n",
							yieldinglyIndefensive.getMessage());
					LenyaSourceFactory.centiloquyFurfuran = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							yieldinglyIndefensive);
				}
				if (LenyaSourceFactory.centiloquyFurfuran != null) {
					try {
						String subparagraph_snowball = System
								.getenv("VEILING_UNNEEDFULNESS");
						if (null != subparagraph_snowball) {
							int catechetical_twanky;
							try {
								catechetical_twanky = Integer
										.parseInt(subparagraph_snowball);
							} catch (NumberFormatException dissipatedness_acinary) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										dissipatedness_acinary);
							}
							int[] adsessor_teutonicism = new int[8];
							adsessor_teutonicism[4] = catechetical_twanky;
							int[][] quizzish_angiokinesis = new int[9][];
							quizzish_angiokinesis[compendia_gerbillinae] = adsessor_teutonicism;
							boolean cumic_vector = false;
							sinistrocular_undermarshal: for (int vomitiveness_hopelessly = 0; vomitiveness_hopelessly < 10; vomitiveness_hopelessly++)
								for (int contain_necessitation = 0; contain_necessitation < 10; contain_necessitation++)
									if (vomitiveness_hopelessly
											* contain_necessitation == 63) {
										cumic_vector = true;
										break sinistrocular_undermarshal;
									}
							Tracer.tracepointWeaknessStart("CWE606", "B",
									"Uncheck Input for Loop Condition");
							char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
									.toCharArray();
							SecureRandom random = null;
							try {
								random = SecureRandom.getInstance("SHA1PRNG");
							} catch (NoSuchAlgorithmException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								LenyaSourceFactory.centiloquyFurfuran
										.println("STONESOUP: Random generator algorithm does not exist.");
							}
							Tracer.tracepointVariableInt(
									"value",
									quizzish_angiokinesis[compendia_gerbillinae][4]);
							if (random != null) {
								StringBuilder stonesoup_filename = new StringBuilder();
								LenyaSourceFactory.centiloquyFurfuran
										.println("Generating file name");
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								for (int stonesoup_counter = 0; stonesoup_counter < quizzish_angiokinesis[compendia_gerbillinae][4]; stonesoup_counter++) {
									stonesoup_filename
											.append(stonesoup_random_charset[random
													.nextInt(stonesoup_random_charset.length)]);
								}
								Tracer.tracepointVariableString(
										"stonesoup_filename",
										stonesoup_filename.toString());
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								if (stonesoup_filename.length() > 0) {
									File writePath = new File(
											stonesoup_filename.toString());
									try {
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										writePath.createNewFile();
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									} catch (IOException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										LenyaSourceFactory.centiloquyFurfuran
												.println("Failed to create file.");
										LenyaSourceFactory.centiloquyFurfuran
												.println("Error:");
										e.printStackTrace(LenyaSourceFactory.centiloquyFurfuran);
										throw new RuntimeException(
												"Unknown error in filename.", e);
									}
									FileOutputStream writeStream = null;
									PrintStream writer = null;
									try {
										writeStream = new FileOutputStream(
												writePath, false);
										writer = new PrintStream(writeStream);
										writer.println("/* This is my file */");
									} catch (FileNotFoundException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										LenyaSourceFactory.centiloquyFurfuran
												.println("Failed to create file.");
										e.printStackTrace(LenyaSourceFactory.centiloquyFurfuran);
									} finally {
										if (writer != null) {
											writer.close();
										}
									}
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						LenyaSourceFactory.centiloquyFurfuran.close();
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