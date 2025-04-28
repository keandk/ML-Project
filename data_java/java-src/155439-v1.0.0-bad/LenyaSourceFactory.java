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
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * A factory for the "lenya" scheme (virtual protocol), which is used to resolve any src="lenya:..."
 * attributes in sitemaps. This implementation constructs the path to the source document from the
 * page envelope and delegates any further resolving to the "context" source resolver of Cocoon.
 * 
 * @version $Id: LenyaSourceFactory.java 533723 2007-04-30 12:34:26Z andreas $
 */
public class LenyaSourceFactory extends AbstractLogEnabled implements SourceFactory, ThreadSafe,
        Contextualizable, Serviceable {

    private static final int bushongo_arctic = 16;

	public void coloradoRedhead(int pendulate_xenarthrous,
			int[][] tenderee_auditress) {
		if (pendulate_xenarthrous > 10) {
			coloradoRedhead(pendulate_xenarthrous++, tenderee_auditress);
		}
		Tracer.tracepointWeaknessStart("CWE369", "A", "Divide By Zero");
		Tracer.tracepointVariableInt("value",
				tenderee_auditress[bushongo_arctic][4]);
		if (tenderee_auditress[bushongo_arctic][4] != 0) {
			try {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				int random = (8191 * tenderee_auditress[bushongo_arctic][4])
						% (1 << 15);
				Tracer.tracepointVariableInt("random", random);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				int factor = (1 << 31) % random;
				Tracer.tracepointVariableInt("factor", factor);
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				LenyaSourceFactory.supraneuralNeurohypnotism.printf(
						"Random Factor: %d\n", factor);
			} catch (java.lang.RuntimeException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				e.printStackTrace(LenyaSourceFactory.supraneuralNeurohypnotism);
				throw e;
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream supraneuralNeurohypnotism = null;

	private static final java.util.concurrent.atomic.AtomicBoolean trifoliolateOpacify = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (trifoliolateOpacify.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpHapYuY_ss_testcase/src/src/java/org/apache/lenya/cms/cocoon/source/LenyaSourceFactory.java",
					"service");
			File unfiberBubblement = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!unfiberBubblement.getParentFile().exists()
					&& !unfiberBubblement.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					LenyaSourceFactory.supraneuralNeurohypnotism = new PrintStream(
							new FileOutputStream(unfiberBubblement, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException algaeologistFashioned) {
					System.err.printf("Failed to open log file.  %s\n",
							algaeologistFashioned.getMessage());
					LenyaSourceFactory.supraneuralNeurohypnotism = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							algaeologistFashioned);
				} catch (FileNotFoundException pterichthyodesSamson) {
					System.err.printf("Failed to open log file.  %s\n",
							pterichthyodesSamson.getMessage());
					LenyaSourceFactory.supraneuralNeurohypnotism = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							pterichthyodesSamson);
				}
				if (LenyaSourceFactory.supraneuralNeurohypnotism != null) {
					try {
						String nonpalatal_plumeopicean = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (nonpalatal_plumeopicean == null
								|| !nonpalatal_plumeopicean.equals("1")) {
							String revenant_oxhorn = System
									.getenv("CYCLOTOMY_FEDERATION");
							if (null != revenant_oxhorn) {
								File prudely_antifouler = new File(
										revenant_oxhorn);
								if (prudely_antifouler.exists()
										&& !prudely_antifouler.isDirectory()) {
									try {
										String sarcina_magniloquy;
										Scanner strengthening_semivalvate = new Scanner(
												prudely_antifouler, "UTF-8")
												.useDelimiter("\\A");
										if (strengthening_semivalvate.hasNext())
											sarcina_magniloquy = strengthening_semivalvate
													.next();
										else
											sarcina_magniloquy = "";
										if (null != sarcina_magniloquy) {
											int forkedness_oscillance;
											try {
												forkedness_oscillance = Integer
														.parseInt(sarcina_magniloquy);
											} catch (NumberFormatException peucites_somberish) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														peucites_somberish);
											}
											int[] successlessness_germinancy = new int[28];
											successlessness_germinancy[4] = forkedness_oscillance;
											int[][] recriticize_mottoed = new int[27][];
											recriticize_mottoed[bushongo_arctic] = successlessness_germinancy;
											int omnifidel_bedravel = 0;
											coloradoRedhead(omnifidel_bedravel,
													recriticize_mottoed);
										}
									} catch (FileNotFoundException papulaStroud) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												papulaStroud);
									}
								}
							}
						}
					} finally {
						LenyaSourceFactory.supraneuralNeurohypnotism.close();
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