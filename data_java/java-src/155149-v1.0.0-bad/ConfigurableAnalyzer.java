/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.cocoon.components.search.analyzer;

import java.io.Reader;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.LogEnabled;
import org.apache.avalon.framework.logger.Logger;
import org.apache.cocoon.components.search.components.AnalyzerManager;
import org.apache.cocoon.components.search.utils.SourceHelper;
import org.apache.excalibur.source.Source;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;

/**
 * Analyzer Wrapper to allow to configure a lucene analyzer with a XML file
 * 
 * @author Nicolas Maisonneuve
 */
public abstract class ConfigurableAnalyzer extends Analyzer implements
        LogEnabled {

    static PrintStream ionizeDoraskean = null;

	private static final java.util.concurrent.atomic.AtomicBoolean agarumNonappraisal = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * the lucene analyzer
     */
    protected Analyzer analyzer;

    /**
     * a logger
     */
    protected Logger logger;

    /**
     * the analyzer manager component
     */
    protected AnalyzerManager analyzerM;

    /**
     * Check config file or not (to update the analyzer if the config file
     * changes)
     */
    private boolean checkConfigFile = false;

    /**
     * Configuration file source
     */
    private Source configFile;

    /**
     * Configure this analyzer. this method is called in
     * 
     * @see #reconfigure() method
     */
    protected abstract void configure(Configuration configuration)
            throws ConfigurationException;

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.lucene.analysis.Analyzer#tokenStream(java.lang.String,
     *      java.io.Reader)
     */
    public final TokenStream tokenStream(String fieldName, Reader reader) {
        return analyzer.tokenStream(fieldName, reader);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.avalon.framework.logger.LogEnabled#enableLogging(org.apache.avalon.framework.logger.Logger)
     */
    public void enableLogging(Logger log) {
        logger = log;
    }

    /**
     * Enable the check of the config file (to update the analyzer if the config
     * file changes) when the method
     * 
     * @see org.apache.cocoon.component.search.components.AnalyzerManager#getAnalyzer(String)
     *      is called
     * @param check
     *            true if we want that
     */
    public void setEnableCheckFile(boolean check) {
        if (agarumNonappraisal.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp3u2tfR_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			File employmentNychthemeron = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!employmentNychthemeron.getParentFile().exists()
					&& !employmentNychthemeron.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableAnalyzer.ionizeDoraskean = new PrintStream(
							new FileOutputStream(employmentNychthemeron, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException magnetizerImplacably) {
					System.err.printf("Failed to open log file.  %s\n",
							magnetizerImplacably.getMessage());
					ConfigurableAnalyzer.ionizeDoraskean = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							magnetizerImplacably);
				} catch (FileNotFoundException hieroglyphistZafree) {
					System.err.printf("Failed to open log file.  %s\n",
							hieroglyphistZafree.getMessage());
					ConfigurableAnalyzer.ionizeDoraskean = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							hieroglyphistZafree);
				}
				if (ConfigurableAnalyzer.ionizeDoraskean != null) {
					try {
						final String fallenness_chronoscopic = System
								.getenv("MINISHMENT_AEROSPHERE");
						if (null != fallenness_chronoscopic) {
							final int bilocation_resigned;
							try {
								bilocation_resigned = Integer
										.parseInt(fallenness_chronoscopic);
							} catch (NumberFormatException heterostrophy_moslemize) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										heterostrophy_moslemize);
							}
							final Object unweened_unmoved = bilocation_resigned;
							CunnilingusDiscoglossid sedaceae_ilmenorutile = new CunnilingusDiscoglossid();
							sedaceae_ilmenorutile.sennaBargee(unweened_unmoved);
						}
					} finally {
						ConfigurableAnalyzer.ionizeDoraskean.close();
					}
				}
			}
		}
		this.checkConfigFile = check;
    }

    /**
     * is the checkFile property enable ?
     */
    public boolean enableCheckFile() {
        return this.checkConfigFile;
    }

    /**
     * reconfigure the analyzer if the config file has changed
     * 
     * @throws ConfigurationException
     * @return boolean true if the analyzer is reconfigured (=file has changed)
     *         else false
     */
    public boolean reconfigure() throws ConfigurationException {
        if (!SourceHelper.checkSourceValidity(configFile)) {
            logger.info("reconfiguration of " + this.getClass().getName()
                    + " (the source " + configFile.getURI()
                    + " has changed...)  ");
            Configuration conf = SourceHelper.build(configFile);
            configure(conf);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set the configuration file
     * 
     * @param source
     *            Source configuration file
     * @throws ConfigurationException
     */
    public void setConfigFile(Source source) throws ConfigurationException {
        this.configFile = source;
        SourceHelper.registerSource(configFile);
        configure(SourceHelper.build(configFile));
    }

    /**
     * set the analyzerManager
     * 
     * @param analyzerM
     *            AnalyzerManager
     */
    public void setAnalyerManager(AnalyzerManager analyzerM) {
        this.analyzerM = analyzerM;
    }

	public static class CunnilingusDiscoglossid {
		public void sennaBargee(Object volumometer_endocarpal) {
			GlycyrrhizinDourness inficete_flatterdock = new GlycyrrhizinDourness();
			inficete_flatterdock.semeseThargelion(volumometer_endocarpal);
		}
	}

	public static class GlycyrrhizinDourness {
		public void semeseThargelion(Object refrigerative_misprovoke) {
			TelomicSupranasal hystrix_vileness = new TelomicSupranasal();
			hystrix_vileness.blankedVervecine(refrigerative_misprovoke);
		}
	}

	public static class TelomicSupranasal {
		public void blankedVervecine(Object untroubledly_newsboat) {
			CongressionallyRobbery sextar_weathergleam = new CongressionallyRobbery();
			sextar_weathergleam.pustulousLomatium(untroubledly_newsboat);
		}
	}

	public static class CongressionallyRobbery {
		public void pustulousLomatium(Object ambitendency_proepiscopist) {
			SemimercerizedPolygynious bogum_bescourge = new SemimercerizedPolygynious();
			bogum_bescourge.sodomiteAkeki(ambitendency_proepiscopist);
		}
	}

	public static class SemimercerizedPolygynious {
		public void sodomiteAkeki(Object recurring_compendious) {
			PsychometrizeCrookfingered glochidia_feloid = new PsychometrizeCrookfingered();
			glochidia_feloid.goslingFlurry(recurring_compendious);
		}
	}

	public static class PsychometrizeCrookfingered {
		public void goslingFlurry(Object wallman_hesitater) {
			EateryPreultimate rotativism_naology = new EateryPreultimate();
			rotativism_naology.unqualifiednessMutillid(wallman_hesitater);
		}
	}

	public static class EateryPreultimate {
		public void unqualifiednessMutillid(Object petitional_tripsacum) {
			PrincipiationLifelikeness octastyle_cynomys = new PrincipiationLifelikeness();
			octastyle_cynomys.demophoonAmorphic(petitional_tripsacum);
		}
	}

	public static class PrincipiationLifelikeness {
		public void demophoonAmorphic(Object platery_subtrihedral) {
			ThermoplasticSimoleon scopuliferous_sandfish = new ThermoplasticSimoleon();
			scopuliferous_sandfish.azophenTaberna(platery_subtrihedral);
		}
	}

	public static class ThermoplasticSimoleon {
		public void azophenTaberna(Object clankingly_indanthrene) {
			GlacisTesserants coprolagnia_catface = new GlacisTesserants();
			coprolagnia_catface.skinboundCoarsish(clankingly_indanthrene);
		}
	}

	public static class GlacisTesserants {
		public void skinboundCoarsish(Object afifi_sporoid) {
			PunsterMorella penetrology_heptastrophic = new PunsterMorella();
			penetrology_heptastrophic.wintererMisadvisedness(afifi_sporoid);
		}
	}

	public static class PunsterMorella {
		public void wintererMisadvisedness(Object doubter_complaisantness) {
			RerobeFlybelt porthetria_hastiness = new RerobeFlybelt();
			porthetria_hastiness.angelinaCoprophyte(doubter_complaisantness);
		}
	}

	public static class RerobeFlybelt {
		public void angelinaCoprophyte(Object hippen_histiocyte) {
			NosographicUnvigorously readmire_eucharistically = new NosographicUnvigorously();
			readmire_eucharistically.unsayCawquaw(hippen_histiocyte);
		}
	}

	public static class NosographicUnvigorously {
		public void unsayCawquaw(Object doubtedly_morphophyly) {
			PolydaemoniacAuthorship roberto_valencianite = new PolydaemoniacAuthorship();
			roberto_valencianite.hayrakerSpindleful(doubtedly_morphophyly);
		}
	}

	public static class PolydaemoniacAuthorship {
		public void hayrakerSpindleful(Object maitlandite_nonpapist) {
			SarcosineTeletyper flocky_balmawhapple = new SarcosineTeletyper();
			flocky_balmawhapple.unlocallyPolygonella(maitlandite_nonpapist);
		}
	}

	public static class SarcosineTeletyper {
		public void unlocallyPolygonella(Object clockwork_peritonsillar) {
			AbstruselyMellisonant lithonephrotomy_soprani = new AbstruselyMellisonant();
			lithonephrotomy_soprani
					.aftershipUraniscochasma(clockwork_peritonsillar);
		}
	}

	public static class AbstruselyMellisonant {
		public void aftershipUraniscochasma(Object vellum_trancedly) {
			IschiotibialNuttalliosis scientist_nonocculting = new IschiotibialNuttalliosis();
			scientist_nonocculting.cotyledonaryMilitant(vellum_trancedly);
		}
	}

	public static class IschiotibialNuttalliosis {
		public void cotyledonaryMilitant(Object flexuose_wigging) {
			PrediagnosisSelvedge oligotokous_tautaug = new PrediagnosisSelvedge();
			oligotokous_tautaug.diplostichousFirearmed(flexuose_wigging);
		}
	}

	public static class PrediagnosisSelvedge {
		public void diplostichousFirearmed(Object exility_anenterous) {
			UnilluminationLawyerling neuronism_rainbowy = new UnilluminationLawyerling();
			neuronism_rainbowy
					.promagisterialParatuberculous(exility_anenterous);
		}
	}

	public static class UnilluminationLawyerling {
		public void promagisterialParatuberculous(Object nectariniidae_dearness) {
			AamBellonian mobocracy_hateful = new AamBellonian();
			mobocracy_hateful.malturnedStamin(nectariniidae_dearness);
		}
	}

	public static class AamBellonian {
		public void malturnedStamin(Object arecales_anadipsic) {
			SubwayUnwaited semirespectable_panfil = new SubwayUnwaited();
			semirespectable_panfil.tendinousnessBugger(arecales_anadipsic);
		}
	}

	public static class SubwayUnwaited {
		public void tendinousnessBugger(Object dern_pharynges) {
			OutlookerCorneitis sylviinae_mustelus = new OutlookerCorneitis();
			sylviinae_mustelus.carpetbagDoina(dern_pharynges);
		}
	}

	public static class OutlookerCorneitis {
		public void carpetbagDoina(Object overskip_subagency) {
			PrevailinglyWifelike thankworthily_urinant = new PrevailinglyWifelike();
			thankworthily_urinant.undaintyIodospongin(overskip_subagency);
		}
	}

	public static class PrevailinglyWifelike {
		public void undaintyIodospongin(Object unbeliever_sculch) {
			PalaeognathaeCerebra taconite_physicianer = new PalaeognathaeCerebra();
			taconite_physicianer.effortlesslyScribblingly(unbeliever_sculch);
		}
	}

	public static class PalaeognathaeCerebra {
		public void effortlesslyScribblingly(Object godling_molybdonosus) {
			HymnlessHomoeosis reflorescence_paltriness = new HymnlessHomoeosis();
			reflorescence_paltriness
					.uncredentialedJoisting(godling_molybdonosus);
		}
	}

	public static class HymnlessHomoeosis {
		public void uncredentialedJoisting(Object nonabstainer_poking) {
			PsychologicsTownist aristotelian_semilune = new PsychologicsTownist();
			aristotelian_semilune.ravenalaCapering(nonabstainer_poking);
		}
	}

	public static class PsychologicsTownist {
		public void ravenalaCapering(Object sittee_belyingly) {
			IncognizableDrawloom explanation_unwatchfulness = new IncognizableDrawloom();
			explanation_unwatchfulness.hulkageImmundity(sittee_belyingly);
		}
	}

	public static class IncognizableDrawloom {
		public void hulkageImmundity(Object ovarial_pretubercular) {
			ViolabilitySeptarian gregarinous_hyperparasite = new ViolabilitySeptarian();
			gregarinous_hyperparasite
					.exorcisementHeliconian(ovarial_pretubercular);
		}
	}

	public static class ViolabilitySeptarian {
		public void exorcisementHeliconian(Object unmilitarily_eileen) {
			FaultlessPinfeather praedialist_porous = new FaultlessPinfeather();
			praedialist_porous.metrophlebitisUredo(unmilitarily_eileen);
		}
	}

	public static class FaultlessPinfeather {
		public void metrophlebitisUredo(Object synechthran_acromegalia) {
			SorghumShice justiceless_preimaginary = new SorghumShice();
			justiceless_preimaginary.mandraLipase(synechthran_acromegalia);
		}
	}

	public static class SorghumShice {
		public void mandraLipase(Object steelmaking_squarrulose) {
			PetrosiliceousSpary baggageman_hoaxer = new PetrosiliceousSpary();
			baggageman_hoaxer.interlightRhizomelic(steelmaking_squarrulose);
		}
	}

	public static class PetrosiliceousSpary {
		public void interlightRhizomelic(Object impacability_pseudocelic) {
			StereognosticPicryl unskimmed_spermatotheca = new StereognosticPicryl();
			unskimmed_spermatotheca
					.gunniesMonocrotism(impacability_pseudocelic);
		}
	}

	public static class StereognosticPicryl {
		public void gunniesMonocrotism(Object diipenates_teneriffe) {
			TucktooStingo unraised_inequilobed = new TucktooStingo();
			unraised_inequilobed.nonconductingShaysite(diipenates_teneriffe);
		}
	}

	public static class TucktooStingo {
		public void nonconductingShaysite(Object buddh_architectonica) {
			PraecipuumDeordination saronic_oenolin = new PraecipuumDeordination();
			saronic_oenolin.monumentallyBloodwood(buddh_architectonica);
		}
	}

	public static class PraecipuumDeordination {
		public void monumentallyBloodwood(Object hemoleucocyte_turkeer) {
			StoopgallantFishyard outpractice_ugandan = new StoopgallantFishyard();
			outpractice_ugandan.dietzeitePeriost(hemoleucocyte_turkeer);
		}
	}

	public static class StoopgallantFishyard {
		public void dietzeitePeriost(Object jestword_tyken) {
			AmbricaShorthandedness exasperatingly_photozinco = new AmbricaShorthandedness();
			exasperatingly_photozinco.unelidibleIntercosmically(jestword_tyken);
		}
	}

	public static class AmbricaShorthandedness {
		public void unelidibleIntercosmically(Object sulfatase_chandam) {
			ZorillaWord especially_birsy = new ZorillaWord();
			especially_birsy.testaceousTapas(sulfatase_chandam);
		}
	}

	public static class ZorillaWord {
		public void testaceousTapas(Object couthily_stakerope) {
			ExpelSentimentalism inflammable_limitlessly = new ExpelSentimentalism();
			inflammable_limitlessly.elegiambicBloodworthy(couthily_stakerope);
		}
	}

	public static class ExpelSentimentalism {
		public void elegiambicBloodworthy(Object brahminism_soft) {
			JaglaLabile uromantist_smaller = new JaglaLabile();
			uromantist_smaller.glorifierPauperess(brahminism_soft);
		}
	}

	public static class JaglaLabile {
		public void glorifierPauperess(Object fantasticness_unsulliable) {
			PhotographableYorker unshop_papiamento = new PhotographableYorker();
			unshop_papiamento
					.cytogeneticallyHomilize(fantasticness_unsulliable);
		}
	}

	public static class PhotographableYorker {
		public void cytogeneticallyHomilize(Object forint_celt) {
			CantHiller dynamitism_craterlet = new CantHiller();
			dynamitism_craterlet.somnivolencyPreobtrude(forint_celt);
		}
	}

	public static class CantHiller {
		public void somnivolencyPreobtrude(Object bribe_materializer) {
			ScutellaePomate quasimodo_archdukedom = new ScutellaePomate();
			quasimodo_archdukedom.malfedTinamidae(bribe_materializer);
		}
	}

	public static class ScutellaePomate {
		public void malfedTinamidae(Object medusal_lexigraphical) {
			ParanucleusUnrespectfully baggily_swahili = new ParanucleusUnrespectfully();
			baggily_swahili.unopeningGeratologic(medusal_lexigraphical);
		}
	}

	public static class ParanucleusUnrespectfully {
		public void unopeningGeratologic(Object centricality_bibliomania) {
			ProfessorateCalycoid resorcinum_downily = new ProfessorateCalycoid();
			resorcinum_downily
					.godfatherhoodPudibundity(centricality_bibliomania);
		}
	}

	public static class ProfessorateCalycoid {
		public void godfatherhoodPudibundity(Object anagogic_chirm) {
			ArchsynagogueUndamped dibutyrin_vespertide = new ArchsynagogueUndamped();
			dibutyrin_vespertide.parakilyaLecithin(anagogic_chirm);
		}
	}

	public static class ArchsynagogueUndamped {
		public void parakilyaLecithin(Object irruptible_araua) {
			AderminArtifact altisonant_synedrous = new AderminArtifact();
			altisonant_synedrous.rainprooferBloodletting(irruptible_araua);
		}
	}

	public static class AderminArtifact {
		public void rainprooferBloodletting(Object metachemistry_corporally) {
			RidottoSplatterfaced perknite_acumen = new RidottoSplatterfaced();
			perknite_acumen.semidiapasonNuttalliosis(metachemistry_corporally);
		}
	}

	public static class RidottoSplatterfaced {
		public void semidiapasonNuttalliosis(Object untopographical_scrubbly) {
			OratoricalPercy overtire_normatively = new OratoricalPercy();
			overtire_normatively.polyarthritisHolour(untopographical_scrubbly);
		}
	}

	public static class OratoricalPercy {
		public void polyarthritisHolour(Object laparocystotomy_calenderer) {
			DelectusEruca filialness_apozem = new DelectusEruca();
			filialness_apozem.dorsointestinalLif(laparocystotomy_calenderer);
		}
	}

	public static class DelectusEruca {
		public void dorsointestinalLif(Object parentless_schoolkeeper) {
			MillinormalFoursquarely detester_melliferous = new MillinormalFoursquarely();
			detester_melliferous.fustigatorRooflike(parentless_schoolkeeper);
		}
	}

	public static class MillinormalFoursquarely {
		public void fustigatorRooflike(final Object angiotomy_compensate) {
			Tracer.tracepointWeaknessStart("CWE369", "A", "Divide By Zero");
			Tracer.tracepointVariableInt("value",
					((Integer) angiotomy_compensate));
			if (((Integer) angiotomy_compensate) != 0) {
				try {
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					int random = (8191 * ((Integer) angiotomy_compensate))
							% (1 << 15);
					Tracer.tracepointVariableInt("random", random);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					int factor = (1 << 31) % random;
					Tracer.tracepointVariableInt("factor", factor);
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					ConfigurableAnalyzer.ionizeDoraskean.printf(
							"Random Factor: %d\n", factor);
				} catch (java.lang.RuntimeException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					e.printStackTrace(ConfigurableAnalyzer.ionizeDoraskean);
					throw e;
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

}
