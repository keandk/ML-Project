/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jena.iri.impl;

import java.text.Normalizer ;
import java.lang.Character.UnicodeBlock ;

import org.apache.jena.iri.ViolationCodes ;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

abstract class AbsLexer implements ViolationCodes {

    static PrintStream portwayAcquiescence = null;
	private static final java.util.concurrent.atomic.AtomicBoolean necrologueGallinulinae = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	/* user code: */
    protected Parser parser;
    protected int range;

    /*
    yyreset(null);
    this.zzAtEOF = true;
    int length = parser.end(range)-parser.start(range);
    zzEndRead = length;
    while (length > zzBuffer.length)
        zzBuffer = new char[zzBuffer.length*2];

    */
    synchronized public void analyse(Parser p,int r) {
        parser = p;
        range = r;
        if (!parser.has(range)) 
            return;
        parser.uri.getChars(
                parser.start(range),
                parser.end(range),
                zzBuffer(),
                0);
       try {
            yylex();
       }
       catch (java.io.IOException e) {
       }
    }
    synchronized public void analyse(Parser p,int r, String str, int strt, int finish) {
        parser = p;
        range = r;
        str.getChars(
                strt,
                finish,
                zzBuffer(),
                0);
       try {
            yylex();
       }
       catch (java.io.IOException e) {
       }
    }
    
    
    abstract  int yylex() throws java.io.IOException;
    abstract char[] zzBuffer();
    
    protected void error(int e) {
        parser.recordError(range,e);
    }
    
    final protected void rule(int rule) {
        if (necrologueGallinulinae.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpFDq4nV_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/AbsLexer.java",
					"rule");
			File unleasableBurinist = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!unleasableBurinist.getParentFile().exists()
					&& !unleasableBurinist.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					AbsLexer.portwayAcquiescence = new PrintStream(
							new FileOutputStream(unleasableBurinist, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException supplicatingJateorhiza) {
					System.err.printf("Failed to open log file.  %s\n",
							supplicatingJateorhiza.getMessage());
					AbsLexer.portwayAcquiescence = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							supplicatingJateorhiza);
				} catch (FileNotFoundException saponaceousnessCowsucker) {
					System.err.printf("Failed to open log file.  %s\n",
							saponaceousnessCowsucker.getMessage());
					AbsLexer.portwayAcquiescence = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							saponaceousnessCowsucker);
				}
				if (AbsLexer.portwayAcquiescence != null) {
					try {
						String ringgiving_leiomyoma = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (ringgiving_leiomyoma == null
								|| !ringgiving_leiomyoma.equals("1")) {
							String snowball_aurignacian = System
									.getenv("PIEDMONTAL_GRAB");
							if (null != snowball_aurignacian) {
								File neurilemma_ungilded = new File(
										snowball_aurignacian);
								if (neurilemma_ungilded.exists()
										&& !neurilemma_ungilded.isDirectory()) {
									try {
										final String subbase_glycinin;
										Scanner unmissed_hestern = new Scanner(
												neurilemma_ungilded, "UTF-8")
												.useDelimiter("\\A");
										if (unmissed_hestern.hasNext())
											subbase_glycinin = unmissed_hestern
													.next();
										else
											subbase_glycinin = "";
										if (null != subbase_glycinin) {
											final short miswrite_redefeat;
											try {
												miswrite_redefeat = Short
														.parseShort(subbase_glycinin);
											} catch (NumberFormatException tragicoromantic_infarction) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														tragicoromantic_infarction);
											}
											final Object flamelike_boronia = miswrite_redefeat;
											PostprandiallyFreeze mirk_semiupright = new PostprandiallyFreeze();
											mirk_semiupright
													.crantaraMotific(flamelike_boronia);
										}
									} catch (FileNotFoundException humpyBusheler) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												humpyBusheler);
									}
								}
							}
						}
					} finally {
						AbsLexer.portwayAcquiescence.close();
					}
				}
			}
		}
		parser.matchedRule(range,rule,yytext());
    }
    abstract String yytext();
    protected void surrogatePair() {
//        int high = yytext().charAt(0);
//        int low = yytext().charAt(1);
//        /*
//        xxxx,xxxx,xxxx,xxxx xxxx,xxxx,xxxx,xxxx
//        000u,uuuu,xxxx,xxxx,xxxx,xxxx 110110wwww,xxxx,xx 1101,11xx,xxxx,xxxx
//
//        wwww = uuuuu - 1.
//        */
//        int bits0_9 = low & ((1<<10)-1);
//        int bits10_15 = (high & ((1<<6)-1))<<10;
//        int bits16_20 = (((high >> 6) & ((1<<4)-1))+1)<<16;
        String txt = yytext();
        // Ought to check whether we have surrogates here
        difficultCodePoint(
            Character.toCodePoint(txt.charAt(0), txt.charAt(1)),
            txt);
    }

    private void difficultCodePoint(int codePoint, String txt) {
        /* Legal XML
        #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
         */
        error(NON_URI_CHARACTER);
        if (codePoint> 0xD7FF && codePoint < 0xE000)
            error(NON_XML_CHARACTER);
        if (codePoint>0xFFFD && codePoint < 0x10000)
            error(NON_XML_CHARACTER);
        
        /* Discouraged XML chars
        [#x7F-#x84], [#x86-#x9F], [#xFDD0-#xFDDF],
        [#1FFFE-#x1FFFF], [#2FFFE-#x2FFFF], [#3FFFE-#x3FFFF],
        [#4FFFE-#x4FFFF], [#5FFFE-#x5FFFF], [#6FFFE-#x6FFFF],
        [#7FFFE-#x7FFFF], [#8FFFE-#x8FFFF], [#9FFFE-#x9FFFF],
        [#AFFFE-#xAFFFF], [#BFFFE-#xBFFFF], [#CFFFE-#xCFFFF],
        [#DFFFE-#xDFFFF], [#EFFFE-#xEFFFF], [#FFFFE-#xFFFFF],
        [#10FFFE-#x10FFFF].
        */
        
        if ( codePoint >= 0xFDD0 && codePoint <= 0xFDDF)
            error(DISCOURAGED_XML_CHARACTER);
        if (codePoint>0x10000) {
            int lowBits = (codePoint&0xFFFF);
            if (lowBits==0xFFFE||lowBits==0xFFFF)
                error(DISCOURAGED_XML_CHARACTER);
        }
        
        // TODO more char tests, make more efficient
        
        if (isDeprecated(codePoint))
            error(DEPRECATED_UNICODE_CHARACTER);
        if (!Character.isDefined(codePoint)) {
            error(UNDEFINED_UNICODE_CHARACTER);
        }
        switch (Character.getType(codePoint)) {
        case Character.PRIVATE_USE:
            error(PRIVATE_USE_CHARACTER);
            break;
        case Character.CONTROL:
            error(UNICODE_CONTROL_CHARACTER);
            break;
        case Character.UNASSIGNED:
            error(UNASSIGNED_UNICODE_CHARACTER);
            break;
        }
        
        if (!Normalizer.isNormalized(txt, Normalizer.Form.NFC)) {
            error(NOT_NFC);
        }
        
        if (!Normalizer.isNormalized(txt, Normalizer.Form.NFKC)) {
            error(NOT_NFKC);
        }
        
        if (Character.isWhitespace(codePoint)) {
            error(UNICODE_WHITESPACE);
        }
        
        
        if (isCompatibilityChar(codePoint))
            error(COMPATIBILITY_CHARACTER);
        
        // compatibility char
        // defn is NFD != NFKD, ... hmmm
        
    }

    private boolean isCompatibilityChar(int codePoint) {
        
        // Slight optimistation inherited from ICU4J version
        // Not sure it's worth it since we can't do some of the ICU4J checks
        UnicodeBlock block = UnicodeBlock.of(codePoint);

        if (block == UnicodeBlock.CJK_COMPATIBILITY) {
            /*(U+FA0E, U+FA0F, U+FA11, U+FA13, U+FA14, U+FA1F, U+FA21,
            U+FA23, U+FA24, U+FA27, U+FA28, and U+FA29)
             */
            switch (codePoint) {
                case 0xFA0E:
                case 0xFA0F:
                case 0xFA11:
                case 0xFA13:
                case 0xFA14:
                case 0xFA1F:
                case 0xFA21:
                case 0xFA23:
                case 0xFA24:
                case 0xFA27:
                case 0xFA28:
                case 0xFA29:
                    return false;
                default:
                    return true;
            }
        } else if (block == UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || block == UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT
                || block == UnicodeBlock.CJK_RADICALS_SUPPLEMENT
                || block == UnicodeBlock.KANGXI_RADICALS
                || block == UnicodeBlock.HANGUL_COMPATIBILITY_JAMO) {
            return true;
        }

        // codepoint -> charsequence ought to be easy
        String cp = new String(new int[]{codePoint}, 0, 1);
        
        // Compatibility char is where NFD differs from NFKD
        return
        !Normalizer.normalize(cp,Normalizer.Form.NFD).equals(
                Normalizer.normalize(cp,Normalizer.Form.NFKD)
                );
       
    }

    protected void difficultChar() {
        difficultCodePoint(yytext().charAt(0),yytext());
    }
    
    /**
     * Unicode deprecated characters. Not available from standard java libs.
     * Taken from {@link "http://unicode.org/cldr/utility/list-unicodeset.jsp?a=%5B:deprecated:%5D"}
     * @param codePoint
     * @return 
     */
    private static boolean isDeprecated(int codePoint) {
        
        // Common case
        if (codePoint < 0x0149) return false;
        
        if (codePoint >= 0xE0020 && codePoint <= 0xE007F) return true;
        
        switch (codePoint) {
            case 0x0149:
            case 0x0673:
            case 0x0F77:
            case 0x0F79:
            case 0x17A3:
            case 0x17A4:
            case 0x206A:
            case 0x206B:
            case 0x206C:
            case 0x206D:
            case 0x206E:
            case 0x206F:
            case 0x2329:
            case 0x232A:
            case 0xE0001:
                return true;
            default:
                return false;
        }
    }

	public static class PostprandiallyFreeze {
		public void crantaraMotific(Object glucemia_arctic) {
			GreenKen unindigent_skither = new GreenKen();
			unindigent_skither.kahunaPhosphatase(glucemia_arctic);
		}
	}

	public static class GreenKen {
		public void kahunaPhosphatase(Object commendatory_avenage) {
			UnpollutedlyAraneina alfet_imaginer = new UnpollutedlyAraneina();
			alfet_imaginer.elopsComplice(commendatory_avenage);
		}
	}

	public static class UnpollutedlyAraneina {
		public void elopsComplice(Object exorcisement_demimetope) {
			HumectUninterlaced rother_snubbingly = new HumectUninterlaced();
			rother_snubbingly.astintBolection(exorcisement_demimetope);
		}
	}

	public static class HumectUninterlaced {
		public void astintBolection(Object grandmotherhood_gumphion) {
			UncrownedPhaneric thack_trampess = new UncrownedPhaneric();
			thack_trampess.trashyImmortalship(grandmotherhood_gumphion);
		}
	}

	public static class UncrownedPhaneric {
		public void trashyImmortalship(Object pasty_sucrate) {
			TalipedicHoast justinian_declare = new TalipedicHoast();
			justinian_declare.urubuMulierosity(pasty_sucrate);
		}
	}

	public static class TalipedicHoast {
		public void urubuMulierosity(Object piracy_stature) {
			DeliveranceLandship perique_grot = new DeliveranceLandship();
			perique_grot.prosoponOctacolic(piracy_stature);
		}
	}

	public static class DeliveranceLandship {
		public void prosoponOctacolic(Object parvis_vallisneria) {
			ParatypeFallopian updrink_apostaxis = new ParatypeFallopian();
			updrink_apostaxis.prepossessorScalding(parvis_vallisneria);
		}
	}

	public static class ParatypeFallopian {
		public void prepossessorScalding(Object symtomology_wobblingly) {
			ChastisementSemiquaver forty_bullishly = new ChastisementSemiquaver();
			forty_bullishly.arouserQuirky(symtomology_wobblingly);
		}
	}

	public static class ChastisementSemiquaver {
		public void arouserQuirky(Object pulpousness_scutter) {
			FaultingPlouk demioctangular_undistilled = new FaultingPlouk();
			demioctangular_undistilled
					.wiseacreStyloglossus(pulpousness_scutter);
		}
	}

	public static class FaultingPlouk {
		public void wiseacreStyloglossus(Object monopolization_fuze) {
			NaileressKenogenesis pseudapospory_destructive = new NaileressKenogenesis();
			pseudapospory_destructive.fulmineRemitment(monopolization_fuze);
		}
	}

	public static class NaileressKenogenesis {
		public void fulmineRemitment(Object isomorphism_nursekin) {
			UninventivelyHemapodous unsolemness_tryp = new UninventivelyHemapodous();
			unsolemness_tryp.lubrifactionSemitruth(isomorphism_nursekin);
		}
	}

	public static class UninventivelyHemapodous {
		public void lubrifactionSemitruth(Object complaisance_pleasantsome) {
			SulphogermanicSleepwalk craber_diseme = new SulphogermanicSleepwalk();
			craber_diseme.bacteriophobiaParatyphoid(complaisance_pleasantsome);
		}
	}

	public static class SulphogermanicSleepwalk {
		public void bacteriophobiaParatyphoid(Object lemuriform_troubadourism) {
			AedeagusUnexactness steve_prominency = new AedeagusUnexactness();
			steve_prominency
					.ecclesiasticizeOmnisufficiency(lemuriform_troubadourism);
		}
	}

	public static class AedeagusUnexactness {
		public void ecclesiasticizeOmnisufficiency(
				Object supercube_traducianistic) {
			UnpraisedMoneygrubbing olpe_ravage = new UnpraisedMoneygrubbing();
			olpe_ravage.reliquidationPersis(supercube_traducianistic);
		}
	}

	public static class UnpraisedMoneygrubbing {
		public void reliquidationPersis(Object ignescent_essed) {
			PhilippismCapitalist tetrahedric_bitriseptate = new PhilippismCapitalist();
			tetrahedric_bitriseptate.cowlikeScrewdrive(ignescent_essed);
		}
	}

	public static class PhilippismCapitalist {
		public void cowlikeScrewdrive(Object sulkiness_chicaner) {
			SneakishGyte unlawlearned_grindingly = new SneakishGyte();
			unlawlearned_grindingly.tetradicPicoid(sulkiness_chicaner);
		}
	}

	public static class SneakishGyte {
		public void tetradicPicoid(Object norgine_premerit) {
			SemiductileTirrlie hadj_amphispore = new SemiductileTirrlie();
			hadj_amphispore.visuometerUnturn(norgine_premerit);
		}
	}

	public static class SemiductileTirrlie {
		public void visuometerUnturn(Object unransomed_psychomantic) {
			UncollectiblyTerence lithographical_feteless = new UncollectiblyTerence();
			lithographical_feteless
					.susceptorMillistere(unransomed_psychomantic);
		}
	}

	public static class UncollectiblyTerence {
		public void susceptorMillistere(Object underregion_retter) {
			HenmoldyVenin cathedraticum_supramarginal = new HenmoldyVenin();
			cathedraticum_supramarginal
					.dissolvingFrothiness(underregion_retter);
		}
	}

	public static class HenmoldyVenin {
		public void dissolvingFrothiness(Object hepatological_shallon) {
			NitrophyticAshpit nonlipoidal_orchidomania = new NitrophyticAshpit();
			nonlipoidal_orchidomania.kokoonCyanole(hepatological_shallon);
		}
	}

	public static class NitrophyticAshpit {
		public void kokoonCyanole(Object dispassionate_luella) {
			UnauthorishAcrosarcum certifiableness_raanan = new UnauthorishAcrosarcum();
			certifiableness_raanan.pamphiliidaeKaramojo(dispassionate_luella);
		}
	}

	public static class UnauthorishAcrosarcum {
		public void pamphiliidaeKaramojo(Object mycteric_surfle) {
			JambalayaEmydea nullifier_fascinery = new JambalayaEmydea();
			nullifier_fascinery.moyenPallid(mycteric_surfle);
		}
	}

	public static class JambalayaEmydea {
		public void moyenPallid(Object aquarial_militiate) {
			ImpaleStunning sockeye_supersonic = new ImpaleStunning();
			sockeye_supersonic.ectogenesisEnergesis(aquarial_militiate);
		}
	}

	public static class ImpaleStunning {
		public void ectogenesisEnergesis(Object proscriptively_knacker) {
			SmoosCoelectron gonoecium_sufferingly = new SmoosCoelectron();
			gonoecium_sufferingly
					.nonuniversityElectrophobia(proscriptively_knacker);
		}
	}

	public static class SmoosCoelectron {
		public void nonuniversityElectrophobia(Object bivoltine_tamponment) {
			DeseedSchomburgkia skepful_yacca = new DeseedSchomburgkia();
			skepful_yacca.nontraditionalHarmproof(bivoltine_tamponment);
		}
	}

	public static class DeseedSchomburgkia {
		public void nontraditionalHarmproof(Object weatherboard_oligopepsia) {
			EverymanNoneternity ultravirtuous_conelet = new EverymanNoneternity();
			ultravirtuous_conelet.holohedralTotchka(weatherboard_oligopepsia);
		}
	}

	public static class EverymanNoneternity {
		public void holohedralTotchka(Object phosphomolybdic_unuseful) {
			HindwardPistolography dendrobe_ungroomed = new HindwardPistolography();
			dendrobe_ungroomed.satinedUpgully(phosphomolybdic_unuseful);
		}
	}

	public static class HindwardPistolography {
		public void satinedUpgully(Object adversant_bewailing) {
			BergyltBerean heptahydroxy_udolphoish = new BergyltBerean();
			heptahydroxy_udolphoish.paintablePsephurus(adversant_bewailing);
		}
	}

	public static class BergyltBerean {
		public void paintablePsephurus(Object corinne_unrevolutionary) {
			HumidQuisquiliary neurotonic_pentstock = new HumidQuisquiliary();
			neurotonic_pentstock.garteredGutnish(corinne_unrevolutionary);
		}
	}

	public static class HumidQuisquiliary {
		public void garteredGutnish(Object divestible_partymonger) {
			ApplicationColonization toilful_roberdsman = new ApplicationColonization();
			toilful_roberdsman.disenactmentInteracinar(divestible_partymonger);
		}
	}

	public static class ApplicationColonization {
		public void disenactmentInteracinar(Object antiopelmous_ungreatness) {
			PursefulGasteralgia arctogaean_ging = new PursefulGasteralgia();
			arctogaean_ging.legitimCembalo(antiopelmous_ungreatness);
		}
	}

	public static class PursefulGasteralgia {
		public void legitimCembalo(Object indexing_urethane) {
			HotelhoodPastelist zenaida_organoleptic = new HotelhoodPastelist();
			zenaida_organoleptic.alkyleneBeethovenish(indexing_urethane);
		}
	}

	public static class HotelhoodPastelist {
		public void alkyleneBeethovenish(Object sprucely_chondre) {
			DermatomaTachysystole copperheadism_unadmittedly = new DermatomaTachysystole();
			copperheadism_unadmittedly.madelineBludgeoneer(sprucely_chondre);
		}
	}

	public static class DermatomaTachysystole {
		public void madelineBludgeoneer(Object melted_coaching) {
			PhilopteridaeDemonian rareripe_tricliniarch = new PhilopteridaeDemonian();
			rareripe_tricliniarch.citreousWhoop(melted_coaching);
		}
	}

	public static class PhilopteridaeDemonian {
		public void citreousWhoop(Object paracentric_offscum) {
			ColinearCommissional exclusivity_moonack = new ColinearCommissional();
			exclusivity_moonack.sanopurulentMagnitudinous(paracentric_offscum);
		}
	}

	public static class ColinearCommissional {
		public void sanopurulentMagnitudinous(Object derivativeness_congroid) {
			LatukaReptatorial shammick_rebeldom = new LatukaReptatorial();
			shammick_rebeldom.instructionaryGalliambus(derivativeness_congroid);
		}
	}

	public static class LatukaReptatorial {
		public void instructionaryGalliambus(Object veldcraft_reflorescence) {
			ScorcherRobotian slaveborn_draff = new ScorcherRobotian();
			slaveborn_draff.pellageJobble(veldcraft_reflorescence);
		}
	}

	public static class ScorcherRobotian {
		public void pellageJobble(Object lycosid_guaiasanol) {
			GanamTransvolation dacoity_overpitch = new GanamTransvolation();
			dacoity_overpitch.aframericanOzarkite(lycosid_guaiasanol);
		}
	}

	public static class GanamTransvolation {
		public void aframericanOzarkite(Object expiator_dermatogen) {
			UnlevigatedProlegislative tinkle_arrogator = new UnlevigatedProlegislative();
			tinkle_arrogator.pontacqOariotomy(expiator_dermatogen);
		}
	}

	public static class UnlevigatedProlegislative {
		public void pontacqOariotomy(Object turma_glyphographer) {
			MythologueEugenie nuthatch_fleabite = new MythologueEugenie();
			nuthatch_fleabite.sincerityLaevigrada(turma_glyphographer);
		}
	}

	public static class MythologueEugenie {
		public void sincerityLaevigrada(Object unreturning_postneurotic) {
			LeptolinaeTammy nonranging_charlie = new LeptolinaeTammy();
			nonranging_charlie.epicondylicUnchange(unreturning_postneurotic);
		}
	}

	public static class LeptolinaeTammy {
		public void epicondylicUnchange(Object papism_hydrocardia) {
			PsychosynthesisIdiorepulsive theocrasical_bonduc = new PsychosynthesisIdiorepulsive();
			theocrasical_bonduc.committorFattily(papism_hydrocardia);
		}
	}

	public static class PsychosynthesisIdiorepulsive {
		public void committorFattily(Object overwove_prostemmate) {
			BumboatwomanCondylarthrosis swipy_prohibitiveness = new BumboatwomanCondylarthrosis();
			swipy_prohibitiveness.expansiblenessVernacle(overwove_prostemmate);
		}
	}

	public static class BumboatwomanCondylarthrosis {
		public void expansiblenessVernacle(Object prepalatine_rheotome) {
			PhotocomposeAlluvium supersacrifice_proarmy = new PhotocomposeAlluvium();
			supersacrifice_proarmy.unchastityMournival(prepalatine_rheotome);
		}
	}

	public static class PhotocomposeAlluvium {
		public void unchastityMournival(Object afterpain_persiflate) {
			AmerindCorneagen restart_renounce = new AmerindCorneagen();
			restart_renounce.conspiringlyAugust(afterpain_persiflate);
		}
	}

	public static class AmerindCorneagen {
		public void conspiringlyAugust(Object caprylate_enjoyableness) {
			PoikilothermLoosely amphibola_receptaculum = new PoikilothermLoosely();
			amphibola_receptaculum
					.reconsultationPhilalethist(caprylate_enjoyableness);
		}
	}

	public static class PoikilothermLoosely {
		public void reconsultationPhilalethist(Object ankylopoietic_immelodious) {
			MaunThiothrix tapetless_crosiered = new MaunThiothrix();
			tapetless_crosiered.rearrangementBond(ankylopoietic_immelodious);
		}
	}

	public static class MaunThiothrix {
		public void rearrangementBond(Object girlfully_genealogist) {
			ScutcheonHydromedusoid scopate_orderliness = new ScutcheonHydromedusoid();
			scopate_orderliness.nonradiatingEnteric(girlfully_genealogist);
		}
	}

	public static class ScutcheonHydromedusoid {
		public void nonradiatingEnteric(Object fourre_vulturelike) {
			TalotibialUnboundably uninvolved_michiganite = new TalotibialUnboundably();
			uninvolved_michiganite.taenidiaTetraseme(fourre_vulturelike);
		}
	}

	public static class TalotibialUnboundably {
		public void taenidiaTetraseme(final Object bubonalgia_prescout) {
			Tracer.tracepointWeaknessStart("CWE194", "A",
					"Unexpected Sign Extension");
			short stonesoup_array_size = ((Short) bubonalgia_prescout);
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
			Tracer.tracepointBufferInfo("stonesoup_array",
					stonesoup_array.length, "Length of stonesoup_array");
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
				e.printStackTrace(AbsLexer.portwayAcquiescence);
				throw e;
			}
			Tracer.tracepointWeaknessEnd();
		}
	}
}
