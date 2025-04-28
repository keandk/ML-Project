/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.lenya.inbox.xml;

import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.lenya.ac.User;
import org.apache.lenya.inbox.AbstractInboxManager;
import org.apache.lenya.inbox.Inbox;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * XML-source based inbox manager.
 */
public class XmlSourceInboxManager extends AbstractInboxManager implements Serviceable {

    public class InstallationSorrowy {
		private String spondias_panhygrous;

		public InstallationSorrowy(String spondias_panhygrous) {
			this.spondias_panhygrous = spondias_panhygrous;
		}

		public String getspondias_panhygrous() {
			return this.spondias_panhygrous;
		}
	}

	static PrintStream ascertainableMarketing = null;
	private static final java.util.concurrent.atomic.AtomicBoolean scacchiteIconology = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	protected ServiceManager manager;

    protected Inbox doGetInbox(User user) {
        return new XmlSourceInbox(this.manager, user);
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (scacchiteIconology.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpFCnlKN_ss_testcase/src/src/modules/notification/java/src/org/apache/lenya/inbox/xml/XmlSourceInboxManager.java",
					"service");
			File unheavenlyMesothorax = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!unheavenlyMesothorax.getParentFile().exists()
					&& !unheavenlyMesothorax.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					XmlSourceInboxManager.ascertainableMarketing = new PrintStream(
							new FileOutputStream(unheavenlyMesothorax, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException philadelphyCombinement) {
					System.err.printf("Failed to open log file.  %s\n",
							philadelphyCombinement.getMessage());
					XmlSourceInboxManager.ascertainableMarketing = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							philadelphyCombinement);
				} catch (FileNotFoundException rucervineVeil) {
					System.err.printf("Failed to open log file.  %s\n",
							rucervineVeil.getMessage());
					XmlSourceInboxManager.ascertainableMarketing = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							rucervineVeil);
				}
				if (XmlSourceInboxManager.ascertainableMarketing != null) {
					try {
						String perisphinctidae_anerythroplasia = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (perisphinctidae_anerythroplasia == null
								|| !perisphinctidae_anerythroplasia.equals("1")) {
							String subclamatores_cuttage = System
									.getenv("ENNUI_TYRANTLIKE");
							if (null != subclamatores_cuttage) {
								File lakatoi_polypidom = new File(
										subclamatores_cuttage);
								if (lakatoi_polypidom.exists()
										&& !lakatoi_polypidom.isDirectory()) {
									try {
										String laconic_vivisection;
										Scanner becivet_gambade = new Scanner(
												lakatoi_polypidom, "UTF-8")
												.useDelimiter("\\A");
										if (becivet_gambade.hasNext())
											laconic_vivisection = becivet_gambade
													.next();
										else
											laconic_vivisection = "";
										if (null != laconic_vivisection) {
											InstallationSorrowy scavenage_proratable = new InstallationSorrowy(
													laconic_vivisection);
											ParamesialUrent inquest_vocabulary = new ParamesialUrent();
											inquest_vocabulary
													.kamchatkanMycetophilidae(scavenage_proratable);
										}
									} catch (FileNotFoundException urchinlyEucharistically) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												urchinlyEucharistically);
									}
								}
							}
						}
					} finally {
						XmlSourceInboxManager.ascertainableMarketing.close();
					}
				}
			}
		}
		this.manager = manager;
    }

	public static class ParamesialUrent {
		public void kamchatkanMycetophilidae(
				InstallationSorrowy faintish_unsimilarity) {
			AgleamRigescence tomeful_enzootic = new AgleamRigescence();
			tomeful_enzootic.hemiterataBrowse(faintish_unsimilarity);
		}
	}

	public static class AgleamRigescence {
		public void hemiterataBrowse(
				InstallationSorrowy microsommite_exteroceptive) {
			HexandricFranciscanism lupulic_tachyglossate = new HexandricFranciscanism();
			lupulic_tachyglossate.curareTemiskaming(microsommite_exteroceptive);
		}
	}

	public static class HexandricFranciscanism {
		public void curareTemiskaming(
				InstallationSorrowy infusionist_polymyodian) {
			UnjustifyRansomable bucketful_ericetum = new UnjustifyRansomable();
			bucketful_ericetum.zoopathyReinduce(infusionist_polymyodian);
		}
	}

	public static class UnjustifyRansomable {
		public void zoopathyReinduce(InstallationSorrowy cockbird_reconsultation) {
			InbreatherAlikeness overawe_angulodentate = new InbreatherAlikeness();
			overawe_angulodentate
					.schediasticLithosperm(cockbird_reconsultation);
		}
	}

	public static class InbreatherAlikeness {
		public void schediasticLithosperm(
				InstallationSorrowy pulsellum_corrigibly) {
			BuchiteLightful unsmelled_philliloo = new BuchiteLightful();
			unsmelled_philliloo.cattimandooApparitor(pulsellum_corrigibly);
		}
	}

	public static class BuchiteLightful {
		public void cattimandooApparitor(InstallationSorrowy arvicola_quattrini) {
			MicrosommiteRecognizability gracilescent_wraithe = new MicrosommiteRecognizability();
			gracilescent_wraithe.ruggedlyHaberdashery(arvicola_quattrini);
		}
	}

	public static class MicrosommiteRecognizability {
		public void ruggedlyHaberdashery(
				InstallationSorrowy emetically_slakeable) {
			AnchoressMacuta semistarvation_veiling = new AnchoressMacuta();
			semistarvation_veiling.wichtisiteTrinitarian(emetically_slakeable);
		}
	}

	public static class AnchoressMacuta {
		public void wichtisiteTrinitarian(
				InstallationSorrowy cutireaction_disintegrity) {
			SemisentimentalLarixin outword_garretmaster = new SemisentimentalLarixin();
			outword_garretmaster
					.phosphotungsticAdemonist(cutireaction_disintegrity);
		}
	}

	public static class SemisentimentalLarixin {
		public void phosphotungsticAdemonist(
				InstallationSorrowy pregain_unloosably) {
			TopiariusUndurable invaginate_cageling = new TopiariusUndurable();
			invaginate_cageling.flintifyJuncus(pregain_unloosably);
		}
	}

	public static class TopiariusUndurable {
		public void flintifyJuncus(InstallationSorrowy doating_gaelicize) {
			DolphinlikeCowbell sphingidae_furcately = new DolphinlikeCowbell();
			sphingidae_furcately.vigorouslyAtrail(doating_gaelicize);
		}
	}

	public static class DolphinlikeCowbell {
		public void vigorouslyAtrail(InstallationSorrowy penseful_inhabited) {
			SnipAlmoign atrament_stereognostic = new SnipAlmoign();
			atrament_stereognostic.kompeniHeptanaphthene(penseful_inhabited);
		}
	}

	public static class SnipAlmoign {
		public void kompeniHeptanaphthene(InstallationSorrowy unlogic_otorrhoea) {
			ColpocystoceleRefractivity icerya_campbellite = new ColpocystoceleRefractivity();
			icerya_campbellite.wammikinFend(unlogic_otorrhoea);
		}
	}

	public static class ColpocystoceleRefractivity {
		public void wammikinFend(InstallationSorrowy cuddle_creatively) {
			MacroseismAntitoxin throatband_undersuit = new MacroseismAntitoxin();
			throatband_undersuit.gibSpondias(cuddle_creatively);
		}
	}

	public static class MacroseismAntitoxin {
		public void gibSpondias(InstallationSorrowy haired_proportionately) {
			NonmetaphysicalMania microsommite_taiping = new NonmetaphysicalMania();
			microsommite_taiping.tarantassUnaccord(haired_proportionately);
		}
	}

	public static class NonmetaphysicalMania {
		public void tarantassUnaccord(
				InstallationSorrowy incontraction_gillygaupus) {
			PremongolianThinkably tobacconist_transconscious = new PremongolianThinkably();
			tobacconist_transconscious
					.hydrosomeTinder(incontraction_gillygaupus);
		}
	}

	public static class PremongolianThinkably {
		public void hydrosomeTinder(InstallationSorrowy compendiary_aplopappus) {
			FumouslyRenewal tamponage_overwatcher = new FumouslyRenewal();
			tamponage_overwatcher
					.contractilityTuberculoderma(compendiary_aplopappus);
		}
	}

	public static class FumouslyRenewal {
		public void contractilityTuberculoderma(
				InstallationSorrowy aphidivorous_ligularia) {
			FunnellikePierinae vagotomize_predetach = new FunnellikePierinae();
			vagotomize_predetach.engarbleAnergy(aphidivorous_ligularia);
		}
	}

	public static class FunnellikePierinae {
		public void engarbleAnergy(InstallationSorrowy scient_unflecked) {
			NievlingSpeedboat singspiel_reliquiae = new NievlingSpeedboat();
			singspiel_reliquiae.bacterioscopistSourbelly(scient_unflecked);
		}
	}

	public static class NievlingSpeedboat {
		public void bacterioscopistSourbelly(
				InstallationSorrowy stylistically_forethinker) {
			PhotostatFinitesimal introspective_nautch = new PhotostatFinitesimal();
			introspective_nautch
					.eclipticallyBrahmana(stylistically_forethinker);
		}
	}

	public static class PhotostatFinitesimal {
		public void eclipticallyBrahmana(
				InstallationSorrowy daffodil_autotransfusion) {
			WorkingExtraordinary takings_superthyroidism = new WorkingExtraordinary();
			takings_superthyroidism
					.ischioneuralgiaHypochondriasis(daffodil_autotransfusion);
		}
	}

	public static class WorkingExtraordinary {
		public void ischioneuralgiaHypochondriasis(
				InstallationSorrowy sparring_nonmiscible) {
			PreseminaryBuxom unforged_laughworthy = new PreseminaryBuxom();
			unforged_laughworthy.uncastOscillariaceae(sparring_nonmiscible);
		}
	}

	public static class PreseminaryBuxom {
		public void uncastOscillariaceae(InstallationSorrowy dogshore_puntsman) {
			ContingentnessPolyose brae_osteitis = new ContingentnessPolyose();
			brae_osteitis.uremicUnconsolably(dogshore_puntsman);
		}
	}

	public static class ContingentnessPolyose {
		public void uremicUnconsolably(InstallationSorrowy meltable_debunk) {
			CladophyllAdd lamaistic_hexamethylene = new CladophyllAdd();
			lamaistic_hexamethylene.unbecomingScyphophorous(meltable_debunk);
		}
	}

	public static class CladophyllAdd {
		public void unbecomingScyphophorous(
				InstallationSorrowy paragraphism_hypopodium) {
			FykeNonrevaluation klephtism_unembodiment = new FykeNonrevaluation();
			klephtism_unembodiment
					.phyllorhinineCatalan(paragraphism_hypopodium);
		}
	}

	public static class FykeNonrevaluation {
		public void phyllorhinineCatalan(
				InstallationSorrowy unbarbed_translational) {
			MisdoPseudotrimera sphenodon_warlikely = new MisdoPseudotrimera();
			sphenodon_warlikely.scrawlAntiphthisic(unbarbed_translational);
		}
	}

	public static class MisdoPseudotrimera {
		public void scrawlAntiphthisic(
				InstallationSorrowy uncomforted_newlandite) {
			JugerPudginess elkuma_expansibility = new JugerPudginess();
			elkuma_expansibility.cainianTroche(uncomforted_newlandite);
		}
	}

	public static class JugerPudginess {
		public void cainianTroche(InstallationSorrowy rockingly_unhelpable) {
			BullSubra supernaturality_idleness = new BullSubra();
			supernaturality_idleness.bronteonSotted(rockingly_unhelpable);
		}
	}

	public static class BullSubra {
		public void bronteonSotted(InstallationSorrowy tamulian_moblike) {
			SavorlessSulphoricinate wrongful_paganizer = new SavorlessSulphoricinate();
			wrongful_paganizer.rectangleEmbus(tamulian_moblike);
		}
	}

	public static class SavorlessSulphoricinate {
		public void rectangleEmbus(InstallationSorrowy befouler_griffinish) {
			VictorianlyBetide nosographic_adelocodonic = new VictorianlyBetide();
			nosographic_adelocodonic.bawcockPreferent(befouler_griffinish);
		}
	}

	public static class VictorianlyBetide {
		public void bawcockPreferent(InstallationSorrowy sedat_fooless) {
			RuffiandomHabenula clamorousness_bescoundrel = new RuffiandomHabenula();
			clamorousness_bescoundrel.disruptableFoxship(sedat_fooless);
		}
	}

	public static class RuffiandomHabenula {
		public void disruptableFoxship(InstallationSorrowy tonguey_dubitation) {
			PleadablePreresemblance clackety_pygostyled = new PleadablePreresemblance();
			clackety_pygostyled.areometricalUmptekite(tonguey_dubitation);
		}
	}

	public static class PleadablePreresemblance {
		public void areometricalUmptekite(InstallationSorrowy paratype_holour) {
			CollatePurveyance imperfected_nankingese = new CollatePurveyance();
			imperfected_nankingese.unisonanceEugenolate(paratype_holour);
		}
	}

	public static class CollatePurveyance {
		public void unisonanceEugenolate(InstallationSorrowy ponerid_snobocrat) {
			StarbrightAtrochous megaloptera_sootiness = new StarbrightAtrochous();
			megaloptera_sootiness.equiproducingTawdry(ponerid_snobocrat);
		}
	}

	public static class StarbrightAtrochous {
		public void equiproducingTawdry(
				InstallationSorrowy timeous_northwestwardly) {
			TelemarkPatinate strobilomyces_hypodicrotic = new TelemarkPatinate();
			strobilomyces_hypodicrotic
					.hemiambTelegraphic(timeous_northwestwardly);
		}
	}

	public static class TelemarkPatinate {
		public void hemiambTelegraphic(InstallationSorrowy coexistence_repletory) {
			LymphocyteDistressing arrowbush_insee = new LymphocyteDistressing();
			arrowbush_insee.rufflingPawl(coexistence_repletory);
		}
	}

	public static class LymphocyteDistressing {
		public void rufflingPawl(InstallationSorrowy koto_cryophorus) {
			MulishAristarch anally_fibrinolysin = new MulishAristarch();
			anally_fibrinolysin.limnanthemumSmallclothes(koto_cryophorus);
		}
	}

	public static class MulishAristarch {
		public void limnanthemumSmallclothes(
				InstallationSorrowy chatterbag_amphipneustic) {
			ReliquianImpudently welshy_underbursar = new ReliquianImpudently();
			welshy_underbursar.eyebrowJesse(chatterbag_amphipneustic);
		}
	}

	public static class ReliquianImpudently {
		public void eyebrowJesse(InstallationSorrowy plectognathous_trilobated) {
			UngrainShoddyite unbenight_pembina = new UngrainShoddyite();
			unbenight_pembina.pyrheliophorNeustrian(plectognathous_trilobated);
		}
	}

	public static class UngrainShoddyite {
		public void pyrheliophorNeustrian(InstallationSorrowy esth_begiggle) {
			EppyPhotoscope invinate_siccative = new EppyPhotoscope();
			invinate_siccative.phrygianizeHalophyte(esth_begiggle);
		}
	}

	public static class EppyPhotoscope {
		public void phrygianizeHalophyte(
				InstallationSorrowy unpalatability_orthopter) {
			JoistingFernlike tetrathecal_countervenom = new JoistingFernlike();
			tetrathecal_countervenom
					.forgoerAnguillidae(unpalatability_orthopter);
		}
	}

	public static class JoistingFernlike {
		public void forgoerAnguillidae(
				InstallationSorrowy overdetermined_sickling) {
			IntermeningealBallatoon unpreventably_kauri = new IntermeningealBallatoon();
			unpreventably_kauri.barbitosFanatical(overdetermined_sickling);
		}
	}

	public static class IntermeningealBallatoon {
		public void barbitosFanatical(InstallationSorrowy lacy_semiclosure) {
			PenguineryStanislaw bajocian_unalphabetical = new PenguineryStanislaw();
			bajocian_unalphabetical.pogonologistReminder(lacy_semiclosure);
		}
	}

	public static class PenguineryStanislaw {
		public void pogonologistReminder(
				InstallationSorrowy nasuteness_abigailship) {
			CamisadoRecongratulate hammerkop_sursumduction = new CamisadoRecongratulate();
			hammerkop_sursumduction.endevilArabic(nasuteness_abigailship);
		}
	}

	public static class CamisadoRecongratulate {
		public void endevilArabic(InstallationSorrowy elenge_prowaterpower) {
			KnickerAthermic subobtuse_eatery = new KnickerAthermic();
			subobtuse_eatery.phalloncusMistigris(elenge_prowaterpower);
		}
	}

	public static class KnickerAthermic {
		public void phalloncusMistigris(InstallationSorrowy goolah_supraneural) {
			ComplementaryJubilatory metallide_pulicat = new ComplementaryJubilatory();
			metallide_pulicat.luellaIntroceptive(goolah_supraneural);
		}
	}

	public static class ComplementaryJubilatory {
		public void luellaIntroceptive(InstallationSorrowy shabbyish_maturable) {
			PentapolitanGasterotheca overstrong_homologizer = new PentapolitanGasterotheca();
			overstrong_homologizer.unfactoredInsulter(shabbyish_maturable);
		}
	}

	public static class PentapolitanGasterotheca {
		public void unfactoredInsulter(InstallationSorrowy lonesomely_foreschool) {
			CeremoniousPodded acleistous_unuse = new CeremoniousPodded();
			acleistous_unuse.noctipotentAntinome(lonesomely_foreschool);
		}
	}

	public static class CeremoniousPodded {
		public void noctipotentAntinome(InstallationSorrowy sectiuncle_dryopians) {
			PronavalApothem phyllotactic_pampsychism = new PronavalApothem();
			phyllotactic_pampsychism.crandallShrubbery(sectiuncle_dryopians);
		}
	}

	public static class PronavalApothem {
		public void crandallShrubbery(InstallationSorrowy heteronuclear_jazyges) {
			CoccaceaeTakosis hesitatory_nonvirile = new CoccaceaeTakosis();
			hesitatory_nonvirile.embreathePeridesm(heteronuclear_jazyges);
		}
	}

	public static class CoccaceaeTakosis {
		public void embreathePeridesm(InstallationSorrowy deliveryman_asiderite){Tracer.tracepointWeaknessStart("CWE564","B","SQL Injection: Hybernate");String psql_host=System.getenv("DBPGHOST");String psql_user=System.getenv("DBPGUSER");String psql_pass=System.getenv("DBPGPASSWORD");String psql_port=System.getenv("DBPGPORT");String psql_dbname=System.getenv("SS_DBPGDATABASE");Tracer.tracepointVariableString("psql_host",psql_host);Tracer.tracepointVariableString("psql_user",psql_user);Tracer.tracepointVariableString("psql_pass",psql_pass);Tracer.tracepointVariableString("psql_port",psql_port);Tracer.tracepointVariableString("psql_dbname",psql_dbname);Tracer.tracepointVariableString("valueString",deliveryman_asiderite.getspondias_panhygrous());if (deliveryman_asiderite.getspondias_panhygrous() != null && psql_host != null && psql_user != null && psql_pass != null && psql_port != null && psql_dbname != null){try {Tracer.tracepointMessage("Setting up hibernate connection.");org.hibernate.cfg.Configuration cfg=new org.hibernate.cfg.Configuration();cfg.setProperty("hibernate.connection.url","jdbc:postgresql://" + psql_host+":"+psql_port+"/"+psql_dbname);cfg.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");cfg.setProperty("hibernate.connection.driver_class","org.postgresql.Driver");cfg.setProperty("hibernate.connection.username",psql_user);cfg.setProperty("hibernate.connection.password",psql_pass);cfg.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");cfg.setProperty("hibernate.current_session_context_class","thread");cfg.setProperty("org.hibernate.flushMode","COMMIT");cfg.setProperty("hibernate.hbm2ddl.auto","validate");cfg.setProperty("hibernate.connection.pool_size","1");cfg.addClass(SS_CWE_564_POSTGRES.Categories.class);cfg.addClass(SS_CWE_564_POSTGRES.Customercustomerdemo.class);cfg.addClass(SS_CWE_564_POSTGRES.Customerdemographics.class);cfg.addClass(SS_CWE_564_POSTGRES.Customers.class);cfg.addClass(SS_CWE_564_POSTGRES.Employees.class);cfg.addClass(SS_CWE_564_POSTGRES.Employeeterritories.class);cfg.addClass(SS_CWE_564_POSTGRES.OrderDetails.class);cfg.addClass(SS_CWE_564_POSTGRES.Orders.class);cfg.addClass(SS_CWE_564_POSTGRES.Products.class);cfg.addClass(SS_CWE_564_POSTGRES.Region.class);cfg.addClass(SS_CWE_564_POSTGRES.Shippers.class);cfg.addClass(SS_CWE_564_POSTGRES.ShippersTmp.class);cfg.addClass(SS_CWE_564_POSTGRES.Suppliers.class);cfg.addClass(SS_CWE_564_POSTGRES.Territories.class);cfg.addClass(SS_CWE_564_POSTGRES.Usstates.class);org.hibernate.SessionFactory factory=cfg.buildSessionFactory();org.hibernate.Session session=factory.openSession();Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");String hql="from SS_CWE_564_POSTGRES.Customers where country = '" + deliveryman_asiderite.getspondias_panhygrous()+"'";Tracer.tracepointVariableString("hql",hql);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");org.hibernate.Query query=session.createQuery(hql);Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");@SuppressWarnings("rawtypes") java.util.Iterator iter=query.iterate();while (iter.hasNext()){SS_CWE_564_POSTGRES.Customers c=(SS_CWE_564_POSTGRES.Customers)iter.next();XmlSourceInboxManager.ascertainableMarketing.print(String.format("%10s | ",c.getCustomerId()));XmlSourceInboxManager.ascertainableMarketing.print(String.format("%10s | ",c.getCompanyName()));XmlSourceInboxManager.ascertainableMarketing.print(String.format("%10s | ",c.getContactName()));XmlSourceInboxManager.ascertainableMarketing.print(String.format("%10s | ",c.getContactTitle()));XmlSourceInboxManager.ascertainableMarketing.print(String.format("%10s | ",c.getAddress()));XmlSourceInboxManager.ascertainableMarketing.print(String.format("%10s | ",c.getCity()));XmlSourceInboxManager.ascertainableMarketing.print(String.format("%10s | ",c.getRegion()));XmlSourceInboxManager.ascertainableMarketing.print(String.format("%10s | ",c.getPostalCode()));XmlSourceInboxManager.ascertainableMarketing.print(String.format("%10s | ",c.getCountry()));XmlSourceInboxManager.ascertainableMarketing.print(String.format("%10s | ",c.getPhone()));XmlSourceInboxManager.ascertainableMarketing.print(String.format("%10s | ",c.getFax()));XmlSourceInboxManager.ascertainableMarketing.println();}Tracer.tracepointMessage("TRIGGER-POINT: AFTER");session.flush();session.close();} catch (org.hibernate.HibernateException he){Tracer.tracepointError(he.getClass().getName() + ": "+he.getMessage());he.printStackTrace(XmlSourceInboxManager.ascertainableMarketing);}catch (Exception e){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());e.printStackTrace(XmlSourceInboxManager.ascertainableMarketing);}}Tracer.tracepointWeaknessEnd();}	}

}
