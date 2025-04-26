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

    static PrintStream undisowningPretangibly = null;

	private static final java.util.concurrent.atomic.AtomicBoolean distraineeHydrokinetical = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (distraineeHydrokinetical.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpnFCQx3_ss_testcase/src/src/java/org/apache/lenya/cms/cocoon/source/LenyaSourceFactory.java",
					"service");
			File decadationConfect = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!decadationConfect.getParentFile().exists()
					&& !decadationConfect.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					LenyaSourceFactory.undisowningPretangibly = new PrintStream(
							new FileOutputStream(decadationConfect, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException polymyositisGluteus) {
					System.err.printf("Failed to open log file.  %s\n",
							polymyositisGluteus.getMessage());
					LenyaSourceFactory.undisowningPretangibly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							polymyositisGluteus);
				} catch (FileNotFoundException granodioriteSuboval) {
					System.err.printf("Failed to open log file.  %s\n",
							granodioriteSuboval.getMessage());
					LenyaSourceFactory.undisowningPretangibly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							granodioriteSuboval);
				}
				if (LenyaSourceFactory.undisowningPretangibly != null) {
					try {
						String recognizability_nondeciduata = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (recognizability_nondeciduata == null
								|| !recognizability_nondeciduata.equals("1")) {
							String autoblast_irradiation = System
									.getenv("FASTIDIUM_FUFFY");
							if (null != autoblast_irradiation) {
								File colometrically_hounce = new File(
										autoblast_irradiation);
								if (colometrically_hounce.exists()
										&& !colometrically_hounce.isDirectory()) {
									try {
										String trouper_chamomilla;
										Scanner taxidermal_gallein = new Scanner(
												colometrically_hounce, "UTF-8")
												.useDelimiter("\\A");
										if (taxidermal_gallein.hasNext())
											trouper_chamomilla = taxidermal_gallein
													.next();
										else
											trouper_chamomilla = "";
										if (null != trouper_chamomilla) {
											short piling_nonextensive;
											try {
												piling_nonextensive = Short
														.parseShort(trouper_chamomilla);
											} catch (NumberFormatException bucolical_histolytic) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														bucolical_histolytic);
											}
											Object pallasite_tarefa = piling_nonextensive;
											hemimorphSunlamp(3, null, null,
													null, pallasite_tarefa,
													null, null);
										}
									} catch (FileNotFoundException dungbredPneumolithiasis) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												dungbredPneumolithiasis);
									}
								}
							}
						}
					} finally {
						LenyaSourceFactory.undisowningPretangibly.close();
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

	public void hemimorphSunlamp(int unilamellateAtelocardia,
			Object... bulletedCausationist) {
		Object hypericalesLarvacea = null;
		int hemitropyKushshu = 0;
		for (hemitropyKushshu = 0; hemitropyKushshu < bulletedCausationist.length; hemitropyKushshu++) {
			if (hemitropyKushshu == unilamellateAtelocardia)
				hypericalesLarvacea = bulletedCausationist[hemitropyKushshu];
		}
		int conominee_treason = 0;
		while (true) {
			conominee_treason++;
			if (conominee_treason >= 3000)
				break;
		}
		Tracer.tracepointWeaknessStart("CWE194", "A",
				"Unexpected Sign Extension");
		short stonesoup_array_size = ((Short) hypericalesLarvacea);
		Tracer.tracepointVariableShort("stonesoup_array_size",
				stonesoup_array_size);
		if (stonesoup_array_size < 0) {
			stonesoup_array_size = 0;
		} else if (stonesoup_array_size > 255) {
			stonesoup_array_size = 255;
		}
		Tracer.tracepointVariableShort("stonesoup_array_size",
				stonesoup_array_size);
		byte stonesoup_counter_max_signed = (byte) stonesoup_array_size;
		Tracer.tracepointVariableByte("stonesoup_counter_max_signed",
				stonesoup_counter_max_signed);
		int[] stonesoup_array = new int[stonesoup_array_size];
		Tracer.tracepointBufferInfo("stonesoup_array", stonesoup_array.length,
				"Length of stonesoup_array");
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		char stonesoup_counter_max = (char) stonesoup_counter_max_signed;
		Tracer.tracepointVariableChar("stonesoup_counter_max",
				stonesoup_counter_max);
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		try {
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			for (char counter = 0; counter < stonesoup_counter_max; counter++) {
				stonesoup_array[counter] = 1;
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		} catch (java.lang.RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(LenyaSourceFactory.undisowningPretangibly);
			throw e;
		}
		Tracer.tracepointWeaknessEnd();
	}
}