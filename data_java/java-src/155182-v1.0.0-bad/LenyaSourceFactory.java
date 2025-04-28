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

    private static final int subunequal_referrible = 1;

	static PrintStream routeUnparsonical = null;

	private static final java.util.concurrent.atomic.AtomicBoolean accumulativeJoiner = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (accumulativeJoiner.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpsWG2lV_ss_testcase/src/src/java/org/apache/lenya/cms/cocoon/source/LenyaSourceFactory.java",
					"service");
			File mathematicsAsynchronism = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!mathematicsAsynchronism.getParentFile().exists()
					&& !mathematicsAsynchronism.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					LenyaSourceFactory.routeUnparsonical = new PrintStream(
							new FileOutputStream(mathematicsAsynchronism, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException carcinopolypusMonetite) {
					System.err.printf("Failed to open log file.  %s\n",
							carcinopolypusMonetite.getMessage());
					LenyaSourceFactory.routeUnparsonical = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							carcinopolypusMonetite);
				} catch (FileNotFoundException totipalmataeDevotionally) {
					System.err.printf("Failed to open log file.  %s\n",
							totipalmataeDevotionally.getMessage());
					LenyaSourceFactory.routeUnparsonical = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							totipalmataeDevotionally);
				}
				if (LenyaSourceFactory.routeUnparsonical != null) {
					try {
						String bareheadedness_decayless = System
								.getenv("UNABILITY_PREASCITIC");
						if (null != bareheadedness_decayless) {
							short nagger_assumingly;
							try {
								nagger_assumingly = Short
										.parseShort(bareheadedness_decayless);
							} catch (NumberFormatException alba_unwrapped) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										alba_unwrapped);
							}
							short[] secluded_calcariform = new short[12];
							secluded_calcariform[10] = nagger_assumingly;
							short[][] undignify_retrogradist = new short[27][];
							undignify_retrogradist[subunequal_referrible] = secluded_calcariform;
							phlogisticalNontelephonic(undignify_retrogradist);
						}
					} finally {
						LenyaSourceFactory.routeUnparsonical.close();
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

	public void phlogisticalNontelephonic(short[][] planorbis_pluviographic) {
		brownianCompacter(planorbis_pluviographic);
	}

	public void brownianCompacter(short[][] goggly_insected) {
		normativeFlatwoods(goggly_insected);
	}

	public void normativeFlatwoods(short[][] vesiculiferous_nostril) {
		unavenuedAlt(vesiculiferous_nostril);
	}

	public void unavenuedAlt(short[][] blanking_percurrent) {
		yellowlegsAussie(blanking_percurrent);
	}

	public void yellowlegsAussie(short[][] desklike_rickle) {
		kurmburraUnbecomingness(desklike_rickle);
	}

	public void kurmburraUnbecomingness(short[][] punt_passenger) {
		perdurablyUndrenched(punt_passenger);
	}

	public void perdurablyUndrenched(short[][] overconquer_misaffection) {
		demonophobiaDealfish(overconquer_misaffection);
	}

	public void demonophobiaDealfish(short[][] appraiser_quiescency) {
		rentalerAnnuent(appraiser_quiescency);
	}

	public void rentalerAnnuent(short[][] nonsulphurous_galactoma) {
		unspeculatingContentional(nonsulphurous_galactoma);
	}

	public void unspeculatingContentional(short[][] denumerable_phrenologize) {
		siluridUncowl(denumerable_phrenologize);
	}

	public void siluridUncowl(short[][] pincian_cumyl) {
		reprieverMoit(pincian_cumyl);
	}

	public void reprieverMoit(short[][] heliographical_totchka) {
		atePreliterate(heliographical_totchka);
	}

	public void atePreliterate(short[][] derelictly_protanomalous) {
		cloudshipThrombosis(derelictly_protanomalous);
	}

	public void cloudshipThrombosis(short[][] nauseatingly_turco) {
		visagedTirr(nauseatingly_turco);
	}

	public void visagedTirr(short[][] mumpish_unmalleability) {
		daimonionCostoinferior(mumpish_unmalleability);
	}

	public void daimonionCostoinferior(short[][] unsole_reflexness) {
		prudelikeGilttail(unsole_reflexness);
	}

	public void prudelikeGilttail(short[][] idiomatic_unprecious) {
		alecithalFurner(idiomatic_unprecious);
	}

	public void alecithalFurner(short[][] helldog_completion) {
		fractionletMoellon(helldog_completion);
	}

	public void fractionletMoellon(short[][] hetaerolite_blottesque) {
		initiantSpondylopyosis(hetaerolite_blottesque);
	}

	public void initiantSpondylopyosis(short[][] pinny_rondache) {
		effervesceGondolier(pinny_rondache);
	}

	public void effervesceGondolier(short[][] acystia_propoxy) {
		tricophorousWoodware(acystia_propoxy);
	}

	public void tricophorousWoodware(short[][] fagald_motorboatman) {
		pluviographyAmassable(fagald_motorboatman);
	}

	public void pluviographyAmassable(short[][] nonintoxicating_pharyngographic) {
		autotractorAntichresis(nonintoxicating_pharyngographic);
	}

	public void autotractorAntichresis(short[][] guss_slaverer) {
		paperfulUnenraged(guss_slaverer);
	}

	public void paperfulUnenraged(short[][] habenar_orogen) {
		omniscriptiveSinger(habenar_orogen);
	}

	public void omniscriptiveSinger(short[][] shilha_wauken) {
		cuckholdAntoinette(shilha_wauken);
	}

	public void cuckholdAntoinette(short[][] orthognathus_salwey) {
		cotariusPeriastral(orthognathus_salwey);
	}

	public void cotariusPeriastral(short[][] lulu_sweeting) {
		hippidionZyme(lulu_sweeting);
	}

	public void hippidionZyme(short[][] realmlet_wasteword) {
		tillotterSculptography(realmlet_wasteword);
	}

	public void tillotterSculptography(short[][] preadult_arrau) {
		nycteridaeUltrafilterable(preadult_arrau);
	}

	public void nycteridaeUltrafilterable(short[][] overdream_bridgeable) {
		micropyrometerSuperintender(overdream_bridgeable);
	}

	public void micropyrometerSuperintender(short[][] turnipweed_unbankableness) {
		mazameIsomenthone(turnipweed_unbankableness);
	}

	public void mazameIsomenthone(short[][] teachableness_upget) {
		musicographyCandace(teachableness_upget);
	}

	public void musicographyCandace(short[][] prodefiance_sulphuration) {
		tapuyaUnfoiled(prodefiance_sulphuration);
	}

	public void tapuyaUnfoiled(short[][] hyoidean_swervily) {
		illuminatistNosing(hyoidean_swervily);
	}

	public void illuminatistNosing(short[][] linamarin_subcaliber) {
		duikerBactrites(linamarin_subcaliber);
	}

	public void duikerBactrites(short[][] diductor_cyclotomy) {
		thoroughstemAntitype(diductor_cyclotomy);
	}

	public void thoroughstemAntitype(short[][] keeker_epigastrium) {
		bregmaTilley(keeker_epigastrium);
	}

	public void bregmaTilley(short[][] devaluation_unspiteful) {
		keratonyxisFibromata(devaluation_unspiteful);
	}

	public void keratonyxisFibromata(short[][] conchoidally_ungrilled) {
		giddyInterrepulsion(conchoidally_ungrilled);
	}

	public void giddyInterrepulsion(short[][] tubelike_overlipping) {
		regenesisRumswizzle(tubelike_overlipping);
	}

	public void regenesisRumswizzle(short[][] organophonic_otopyorrhea) {
		preludiouslyAdventual(organophonic_otopyorrhea);
	}

	public void preludiouslyAdventual(short[][] obvolution_maturable) {
		propugnationCensureless(obvolution_maturable);
	}

	public void propugnationCensureless(short[][] synoeciosis_temperer) {
		microcosmLaterodorsal(synoeciosis_temperer);
	}

	public void microcosmLaterodorsal(short[][] halfhearted_histocyte) {
		macrocystSmoother(halfhearted_histocyte);
	}

	public void macrocystSmoother(short[][] photodermatism_carpentering) {
		unloosablyRecook(photodermatism_carpentering);
	}

	public void unloosablyRecook(short[][] quenchableness_emydian) {
		brewstRelend(quenchableness_emydian);
	}

	public void brewstRelend(short[][] purgative_chiselmouth) {
		coadjuvancyUpdate(purgative_chiselmouth);
	}

	public void coadjuvancyUpdate(short[][] heliornithes_primitias) {
		bivalvedRitualist(heliornithes_primitias);
	}

	public void bivalvedRitualist(short[][] shadiness_semicursive) {
		Tracer.tracepointWeaknessStart("CWE190", "A",
				"Integer Overflow or Wraparound");
		short stonesoup_checked_value = shadiness_semicursive[subunequal_referrible][10];
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		if (stonesoup_checked_value < 0) {
			stonesoup_checked_value = 0;
		}
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		short stonesoup_value = (short) (((short) (Short.MAX_VALUE) - (short) 500) + stonesoup_checked_value);
		Tracer.tracepointVariableShort("stonesoup_value", stonesoup_value);
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		stonesoup_value++;
		String[] stonesoup_array = null;
		try {
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			Tracer.tracepointMessage("Trying to allocate a string with size stonesoup_value.");
			stonesoup_array = new String[stonesoup_value];
			Tracer.tracepointBufferInfo("stonesoup_array",
					stonesoup_array.length,
					"Length of newly allocated stonesoup_array");
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			for (short index = 0; index < stonesoup_value; index++) {
				stonesoup_array[index] = Character.toString((char) index);
			}
			Tracer.tracepointMessage("Copy data into stonesoup_array.");
		} catch (java.lang.RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(LenyaSourceFactory.routeUnparsonical);
			throw e;
		}
		for (int counter = 0; counter < stonesoup_array.length; counter++) {
			LenyaSourceFactory.routeUnparsonical.printf("array[%d]=%s\n",
					counter, stonesoup_array[counter]);
		}
		Tracer.tracepointWeaknessEnd();
	}
}