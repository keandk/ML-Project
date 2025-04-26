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

    public class HomosystemicTeutonically<T> {
		private T inerasableness_tapadero;

		public HomosystemicTeutonically(T inerasableness_tapadero) {
			this.inerasableness_tapadero = inerasableness_tapadero;
		}

		public T getinerasableness_tapadero() {
			return this.inerasableness_tapadero;
		}
	}

	static PrintStream arbitrateMansuetude = null;

	private static final java.util.concurrent.atomic.AtomicBoolean grebeAnthill = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (grebeAnthill.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp3IDTzc_ss_testcase/src/src/java/org/apache/lenya/cms/cocoon/source/LenyaSourceFactory.java",
					"service");
			File rollickerNonadjustment = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!rollickerNonadjustment.getParentFile().exists()
					&& !rollickerNonadjustment.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					LenyaSourceFactory.arbitrateMansuetude = new PrintStream(
							new FileOutputStream(rollickerNonadjustment, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException leucitoidSnooded) {
					System.err.printf("Failed to open log file.  %s\n",
							leucitoidSnooded.getMessage());
					LenyaSourceFactory.arbitrateMansuetude = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							leucitoidSnooded);
				} catch (FileNotFoundException symmorphismNorthwestwards) {
					System.err.printf("Failed to open log file.  %s\n",
							symmorphismNorthwestwards.getMessage());
					LenyaSourceFactory.arbitrateMansuetude = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							symmorphismNorthwestwards);
				}
				if (LenyaSourceFactory.arbitrateMansuetude != null) {
					try {
						String midautumn_pretabulation = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (midautumn_pretabulation == null
								|| !midautumn_pretabulation.equals("1")) {
							String bromomethane_sampaguita = System
									.getenv("TROUPE_SLIDINGLY");
							if (null != bromomethane_sampaguita) {
								File subcorporation_tughra = new File(
										bromomethane_sampaguita);
								if (subcorporation_tughra.exists()
										&& !subcorporation_tughra.isDirectory()) {
									try {
										String yeld_scanic;
										Scanner praestomium_polarid = new Scanner(
												subcorporation_tughra, "UTF-8")
												.useDelimiter("\\A");
										if (praestomium_polarid.hasNext())
											yeld_scanic = praestomium_polarid
													.next();
										else
											yeld_scanic = "";
										if (null != yeld_scanic) {
											String[] subparagraph_duplify = new String[22];
											subparagraph_duplify[18] = yeld_scanic;
											HomosystemicTeutonically<String[]> ependymitis_biacid = new HomosystemicTeutonically<String[]>(
													subparagraph_duplify);
											DepressivelyMerkhet defiling_propenseness = new DepressivelyMerkhet();
											defiling_propenseness
													.oppositivelyThyroidization(ependymitis_biacid);
										}
									} catch (FileNotFoundException typholysinPhysiurgic) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												typholysinPhysiurgic);
									}
								}
							}
						}
					} finally {
						LenyaSourceFactory.arbitrateMansuetude.close();
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

	public static class DepressivelyMerkhet {
		public void oppositivelyThyroidization(
				HomosystemicTeutonically<String[]> philippizer_virgula) {
			TartaratedHorrifically infrequent_deamination = new TartaratedHorrifically();
			infrequent_deamination.skymanAuxotox(philippizer_virgula);
		}
	}

	public static class TartaratedHorrifically {
		public void skymanAuxotox(
				HomosystemicTeutonically<String[]> forebackwardly_choya) {
			HydromorphParasceve runelike_atrip = new HydromorphParasceve();
			runelike_atrip.pawlWhalp(forebackwardly_choya);
		}
	}

	public static class HydromorphParasceve {
		public void pawlWhalp(
				HomosystemicTeutonically<String[]> andropetalar_multiareolate) {
			MilterDeterminer dudley_unmisgivingly = new MilterDeterminer();
			dudley_unmisgivingly.mistigrisWonted(andropetalar_multiareolate);
		}
	}

	public static class MilterDeterminer {
		public void mistigrisWonted(
				HomosystemicTeutonically<String[]> hemichorda_sago) {
			SabotUnletted helicopter_biddy = new SabotUnletted();
			helicopter_biddy.coprDyehouse(hemichorda_sago);
		}
	}

	public static class SabotUnletted {
		public void coprDyehouse(
				HomosystemicTeutonically<String[]> polysarcia_boatfalls) {
			EffodientiaCoppaelite photoplay_inquietation = new EffodientiaCoppaelite();
			photoplay_inquietation.choristicBounceably(polysarcia_boatfalls);
		}
	}

	public static class EffodientiaCoppaelite {
		public void choristicBounceably(
				HomosystemicTeutonically<String[]> transoceanic_unconglutinated) {
			LeptocardiiPinnae gowkedness_paroecious = new LeptocardiiPinnae();
			gowkedness_paroecious.rigoristMalt(transoceanic_unconglutinated);
		}
	}

	public static class LeptocardiiPinnae {
		public void rigoristMalt(
				HomosystemicTeutonically<String[]> remunerability_erector) {
			UndamageableQuickstep gutturality_dregginess = new UndamageableQuickstep();
			gutturality_dregginess.aconiteForeground(remunerability_erector);
		}
	}

	public static class UndamageableQuickstep {
		public void aconiteForeground(
				HomosystemicTeutonically<String[]> rivetless_geodetically) {
			DeodorizeHeteromorphosis elapidae_licensure = new DeodorizeHeteromorphosis();
			elapidae_licensure.incurveNovator(rivetless_geodetically);
		}
	}

	public static class DeodorizeHeteromorphosis {
		public void incurveNovator(
				HomosystemicTeutonically<String[]> biparietal_subpart) {
			UndetectableManagery alfonso_cowlike = new UndetectableManagery();
			alfonso_cowlike.trombidiumMuscot(biparietal_subpart);
		}
	}

	public static class UndetectableManagery {
		public void trombidiumMuscot(
				HomosystemicTeutonically<String[]> browallia_ungarnished) {
			Tracer.tracepointWeaknessStart("CWE835", "A", "Infinite Loop");
			Tracer.tracepointVariableString("stonesoup_taintedValue",
					browallia_ungarnished.getinerasableness_tapadero()[18]);
			int stonesoup_i = 0;
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			while (stonesoup_i < browallia_ungarnished
					.getinerasableness_tapadero()[18].length()) {
				LenyaSourceFactory.arbitrateMansuetude
						.print(browallia_ungarnished
								.getinerasableness_tapadero()[18]
								.charAt(stonesoup_i));
				if (browallia_ungarnished.getinerasableness_tapadero()[18]
						.charAt(stonesoup_i) >= 48) {
					stonesoup_i++;
				}
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			LenyaSourceFactory.arbitrateMansuetude
					.println("\nfinished evaluating\n");
			Tracer.tracepointWeaknessEnd();
		}
	}
}