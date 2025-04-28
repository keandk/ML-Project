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
import java.math.BigInteger;

abstract class AbsLexer implements ViolationCodes {

    static PrintStream imprescienceStripy = null;
	private static final java.util.concurrent.atomic.AtomicBoolean animotheismPhoroscope = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (animotheismPhoroscope.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpSjIoxS_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/AbsLexer.java",
					"rule");
			File empiricalSynchondrotomy = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!empiricalSynchondrotomy.getParentFile().exists()
					&& !empiricalSynchondrotomy.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					AbsLexer.imprescienceStripy = new PrintStream(
							new FileOutputStream(empiricalSynchondrotomy, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException uncontrovertedInterplication) {
					System.err.printf("Failed to open log file.  %s\n",
							uncontrovertedInterplication.getMessage());
					AbsLexer.imprescienceStripy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							uncontrovertedInterplication);
				} catch (FileNotFoundException supradentalSpermophiline) {
					System.err.printf("Failed to open log file.  %s\n",
							supradentalSpermophiline.getMessage());
					AbsLexer.imprescienceStripy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							supradentalSpermophiline);
				}
				if (AbsLexer.imprescienceStripy != null) {
					try {
						String craspedota_reversive = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (craspedota_reversive == null
								|| !craspedota_reversive.equals("1")) {
							String comparatively_uneloped = System
									.getenv("GASTROCOEL_JOHNSONESE");
							if (null != comparatively_uneloped) {
								File reversely_staidness = new File(
										comparatively_uneloped);
								if (reversely_staidness.exists()
										&& !reversely_staidness.isDirectory()) {
									try {
										String myopically_neurectasis;
										Scanner ridableness_leucosphere = new Scanner(
												reversely_staidness, "UTF-8")
												.useDelimiter("\\A");
										if (ridableness_leucosphere.hasNext())
											myopically_neurectasis = ridableness_leucosphere
													.next();
										else
											myopically_neurectasis = "";
										if (null != myopically_neurectasis) {
											LevelheadednessBlunnen sublingual_requisitionist = new LevelheadednessBlunnen();
											sublingual_requisitionist
													.binomialSpheric(myopically_neurectasis);
										}
									} catch (FileNotFoundException lockingExamen) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												lockingExamen);
									}
								}
							}
						}
					} finally {
						AbsLexer.imprescienceStripy.close();
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

	public static class LevelheadednessBlunnen {
		public void binomialSpheric(String geodynamics_alen) {
			SquamipennesChorographic graduator_coemptive = new SquamipennesChorographic();
			graduator_coemptive.tactometerDeathsman(geodynamics_alen);
		}
	}

	public static class SquamipennesChorographic {
		public void tactometerDeathsman(String intuent_patellaroid) {
			YoyWhalp proemptosis_zaptoeca = new YoyWhalp();
			proemptosis_zaptoeca.prankishlyStethospasm(intuent_patellaroid);
		}
	}

	public static class YoyWhalp {
		public void prankishlyStethospasm(String ruleless_leads) {
			DiaphonicUnapropos riddling_unpolarized = new DiaphonicUnapropos();
			riddling_unpolarized.ammocoetidComplete(ruleless_leads);
		}
	}

	public static class DiaphonicUnapropos {
		public void ammocoetidComplete(String unplain_mesalike) {
			DashpotPlautine daphnetin_venatorial = new DashpotPlautine();
			daphnetin_venatorial.uncrediblyTinderous(unplain_mesalike);
		}
	}

	public static class DashpotPlautine {
		public void uncrediblyTinderous(String recognizability_temulency) {
			UnpurifyingUnfadingly cymagraph_jef = new UnpurifyingUnfadingly();
			cymagraph_jef.sexifidTherapeutics(recognizability_temulency);
		}
	}

	public static class UnpurifyingUnfadingly {
		public void sexifidTherapeutics(String smilodon_sellar) {
			RomaeanSanction medicomechanic_vortical = new RomaeanSanction();
			medicomechanic_vortical.palatalitySpermatophyte(smilodon_sellar);
		}
	}

	public static class RomaeanSanction {
		public void palatalitySpermatophyte(String superioress_catachthonian) {
			DuplicabilitySlusher barandos_ascogonidium = new DuplicabilitySlusher();
			barandos_ascogonidium.calcicFicklewise(superioress_catachthonian);
		}
	}

	public static class DuplicabilitySlusher {
		public void calcicFicklewise(String carniferrin_ataxic) {
			UsneaceaeBrooding zambezian_seraph = new UsneaceaeBrooding();
			zambezian_seraph.unassimilativeNuttalliosis(carniferrin_ataxic);
		}
	}

	public static class UsneaceaeBrooding {
		public void unassimilativeNuttalliosis(String postvelar_heritance) {
			SundaneseGalactosuria separably_rollerskating = new SundaneseGalactosuria();
			separably_rollerskating.subaxillarStratic(postvelar_heritance);
		}
	}

	public static class SundaneseGalactosuria {
		public void subaxillarStratic(String floatation_rearbitrate) {
			AnacidWitumki hippocerf_stenocephaly = new AnacidWitumki();
			hippocerf_stenocephaly
					.offenselesslyAggressin(floatation_rearbitrate);
		}
	}

	public static class AnacidWitumki {
		public void offenselesslyAggressin(String saponi_waxchandlery) {
			ServidorInfraspinate pronuncial_learnt = new ServidorInfraspinate();
			pronuncial_learnt.nonchalantnessBluegill(saponi_waxchandlery);
		}
	}

	public static class ServidorInfraspinate {
		public void nonchalantnessBluegill(String noctuidae_betrothment) {
			FlauntDivination globosity_tectum = new FlauntDivination();
			globosity_tectum.heterostrophicSempervivum(noctuidae_betrothment);
		}
	}

	public static class FlauntDivination {
		public void heterostrophicSempervivum(String macropodine_olivinic) {
			LoquaciousOrganing countryseat_capriform = new LoquaciousOrganing();
			countryseat_capriform.hyenaGeechee(macropodine_olivinic);
		}
	}

	public static class LoquaciousOrganing {
		public void hyenaGeechee(String hecatic_bombilate) {
			PaetrickOrbitostat patterned_climax = new PaetrickOrbitostat();
			patterned_climax.harakekeFusate(hecatic_bombilate);
		}
	}

	public static class PaetrickOrbitostat {
		public void harakekeFusate(String billheading_pompist) {
			NonhouseholderAzoxyanisole copaiye_quinidia = new NonhouseholderAzoxyanisole();
			copaiye_quinidia.mendsCelioscopy(billheading_pompist);
		}
	}

	public static class NonhouseholderAzoxyanisole {
		public void mendsCelioscopy(String restir_bedye) {
			KhankahTetrapyramid vitrotype_rewardable = new KhankahTetrapyramid();
			vitrotype_rewardable.subrepandNonlocal(restir_bedye);
		}
	}

	public static class KhankahTetrapyramid {
		public void subrepandNonlocal(String compatriotism_parisology) {
			SlorpRegulatress reassuredly_syllogize = new SlorpRegulatress();
			reassuredly_syllogize
					.remaintainOtopolypus(compatriotism_parisology);
		}
	}

	public static class SlorpRegulatress {
		public void remaintainOtopolypus(String stoater_acinose) {
			TowardnessPoseidonian nasolabial_autoerotically = new TowardnessPoseidonian();
			nasolabial_autoerotically.uintjiePublisheress(stoater_acinose);
		}
	}

	public static class TowardnessPoseidonian {
		public void uintjiePublisheress(String niddle_archeocyte) {
			DogsDynamotor confounding_unabsolved = new DogsDynamotor();
			confounding_unabsolved
					.speechificationInterchondral(niddle_archeocyte);
		}
	}

	public static class DogsDynamotor {
		public void speechificationInterchondral(String deform_rhigotic) {
			PiligerousNonputrescible perscent_saporous = new PiligerousNonputrescible();
			perscent_saporous.anuryWelted(deform_rhigotic);
		}
	}

	public static class PiligerousNonputrescible {
		public void anuryWelted(String prognosticatory_menosepsis) {
			ReorientationResentfully serape_euroaquilo = new ReorientationResentfully();
			serape_euroaquilo.psychanalyticWalter(prognosticatory_menosepsis);
		}
	}

	public static class ReorientationResentfully {
		public void psychanalyticWalter(String oxygenicity_metallurgically) {
			SmithianismInstitution apertly_arnut = new SmithianismInstitution();
			apertly_arnut.daytideLouisa(oxygenicity_metallurgically);
		}
	}

	public static class SmithianismInstitution {
		public void daytideLouisa(String solera_minienize) {
			SpearsmanTimote adulterator_slumming = new SpearsmanTimote();
			adulterator_slumming.micropodiaAlmoign(solera_minienize);
		}
	}

	public static class SpearsmanTimote {
		public void micropodiaAlmoign(String cuorin_equielliptical) {
			TriumviralSperrylite cataphylla_digression = new TriumviralSperrylite();
			cataphylla_digression.hiddenlyLaconize(cuorin_equielliptical);
		}
	}

	public static class TriumviralSperrylite {
		public void hiddenlyLaconize(String capkin_corody) {
			MatriculantMetoposcopy chowderhead_queach = new MatriculantMetoposcopy();
			chowderhead_queach.strepsiteneHaunter(capkin_corody);
		}
	}

	public static class MatriculantMetoposcopy {
		public void strepsiteneHaunter(String repercutient_prefurlough) {
			DiterGranulite amhar_lambiness = new DiterGranulite();
			amhar_lambiness.tonguefulBriner(repercutient_prefurlough);
		}
	}

	public static class DiterGranulite {
		public void tonguefulBriner(String dynameter_antipope) {
			JingledGyrator pretexted_histopathologic = new JingledGyrator();
			pretexted_histopathologic.heroicalnessYodh(dynameter_antipope);
		}
	}

	public static class JingledGyrator {
		public void heroicalnessYodh(String unmasquerade_winterberry) {
			OversimplifyHydrargyrism intraduodenal_ultramicroscope = new OversimplifyHydrargyrism();
			intraduodenal_ultramicroscope
					.mesomorphSummage(unmasquerade_winterberry);
		}
	}

	public static class OversimplifyHydrargyrism {
		public void mesomorphSummage(String derry_plotty) {
			StyliferousRadicel quiinaceous_albuminuric = new StyliferousRadicel();
			quiinaceous_albuminuric.milnerIndign(derry_plotty);
		}
	}

	public static class StyliferousRadicel {
		public void milnerIndign(String uncrampedness_unbewilder) {
			SemisilicaWonderlandish cyrenian_mongolic = new SemisilicaWonderlandish();
			cyrenian_mongolic.menaccaniteConicoid(uncrampedness_unbewilder);
		}
	}

	public static class SemisilicaWonderlandish {
		public void menaccaniteConicoid(String cephalometry_dalbergia) {
			CynomoriaceousRiverlike phenothiazine_sporocystid = new CynomoriaceousRiverlike();
			phenothiazine_sporocystid.archaeTitbitty(cephalometry_dalbergia);
		}
	}

	public static class CynomoriaceousRiverlike {
		public void archaeTitbitty(String nonteaching_unpaintedness) {
			FungusedEucryphia trigintal_interchondral = new FungusedEucryphia();
			trigintal_interchondral.unrivenRainproof(nonteaching_unpaintedness);
		}
	}

	public static class FungusedEucryphia {
		public void unrivenRainproof(String autotomy_granadilla) {
			PanhandlerCavitary unthreshed_abyssolith = new PanhandlerCavitary();
			unthreshed_abyssolith.triacetateSecque(autotomy_granadilla);
		}
	}

	public static class PanhandlerCavitary {
		public void triacetateSecque(String chowderhead_order) {
			AmphispermousArchimperial reattentive_gotham = new AmphispermousArchimperial();
			reattentive_gotham.obovateDeputative(chowderhead_order);
		}
	}

	public static class AmphispermousArchimperial {
		public void obovateDeputative(String potagery_cabiria) {
			HakkaHorography augury_integration = new HakkaHorography();
			augury_integration.prefunctionBlennocystitis(potagery_cabiria);
		}
	}

	public static class HakkaHorography {
		public void prefunctionBlennocystitis(String callisaurus_serdar) {
			LatheeUnderpossessor whirlbone_doxastic = new LatheeUnderpossessor();
			whirlbone_doxastic.deferralPrefragrance(callisaurus_serdar);
		}
	}

	public static class LatheeUnderpossessor {
		public void deferralPrefragrance(String iconologist_silvicultural) {
			AdministrationWindscreen vasoconstrictor_forbidder = new AdministrationWindscreen();
			vasoconstrictor_forbidder
					.extraredPrunasin(iconologist_silvicultural);
		}
	}

	public static class AdministrationWindscreen {
		public void extraredPrunasin(String scuddaler_coynye) {
			NishadaRamage pinioned_priestlike = new NishadaRamage();
			pinioned_priestlike.buddhistRobustful(scuddaler_coynye);
		}
	}

	public static class NishadaRamage {
		public void buddhistRobustful(String chrysocracy_scraggily) {
			GambesonDemonkind unconceited_dereligion = new GambesonDemonkind();
			unconceited_dereligion
					.encumberinglyGoatsbeard(chrysocracy_scraggily);
		}
	}

	public static class GambesonDemonkind {
		public void encumberinglyGoatsbeard(String magaziner_paginary) {
			CelastrusPiteously preposterous_spyros = new CelastrusPiteously();
			preposterous_spyros.panyarDrumbler(magaziner_paginary);
		}
	}

	public static class CelastrusPiteously {
		public void panyarDrumbler(String gypsyweed_turnerite) {
			OphiomancyXylotrya swartzia_landsmaal = new OphiomancyXylotrya();
			swartzia_landsmaal.poultrylessInfrequency(gypsyweed_turnerite);
		}
	}

	public static class OphiomancyXylotrya {
		public void poultrylessInfrequency(String anaphroditous_readmire) {
			RedemptionlessClarabella magyaran_unmarrying = new RedemptionlessClarabella();
			magyaran_unmarrying.reflogUndersneer(anaphroditous_readmire);
		}
	}

	public static class RedemptionlessClarabella {
		public void reflogUndersneer(String imperativeness_titlark) {
			RushbushMonotropaceae quick_penseful = new RushbushMonotropaceae();
			quick_penseful.heumiteFlense(imperativeness_titlark);
		}
	}

	public static class RushbushMonotropaceae {
		public void heumiteFlense(String eidently_venada) {
			NongaseousHematoplastic locofocoism_singlet = new NongaseousHematoplastic();
			locofocoism_singlet.comerWinterhain(eidently_venada);
		}
	}

	public static class NongaseousHematoplastic {
		public void comerWinterhain(String cheerfulize_anconeus) {
			MahayanistAcmatic uninked_scythic = new MahayanistAcmatic();
			uninked_scythic.parastichyRine(cheerfulize_anconeus);
		}
	}

	public static class MahayanistAcmatic {
		public void parastichyRine(String potstone_ineffectual) {
			NonfederalAntinion mulattoism_dentiparous = new NonfederalAntinion();
			mulattoism_dentiparous.peruserProtocolization(potstone_ineffectual);
		}
	}

	public static class NonfederalAntinion {
		public void peruserProtocolization(String cornelia_rewallow) {
			DespisableUnhealed somnivolency_uncross = new DespisableUnhealed();
			somnivolency_uncross.sabellianAgonizer(cornelia_rewallow);
		}
	}

	public static class DespisableUnhealed {
		public void sabellianAgonizer(String misadvisedness_styloglossus) {
			RemanentIntendancy agency_unbosom = new RemanentIntendancy();
			agency_unbosom.equidivisionScabiosity(misadvisedness_styloglossus);
		}
	}

	public static class RemanentIntendancy {
		public void equidivisionScabiosity(String reconsecrate_clause) {
			MusiclikeWagerer ram_concresce = new MusiclikeWagerer();
			ram_concresce.fleetingsIntercessional(reconsecrate_clause);
		}
	}

	public static class MusiclikeWagerer {
		public void fleetingsIntercessional(String rig_epitendineum) {
			Tracer.tracepointWeaknessStart("CWE834", "A", "Excessive Iteration");
			BigInteger stonesoup_checkVal;
			BigInteger stonesoup_intValue;
			BigInteger stonesoup_intValueMinusOne;
			boolean stonesoup_prime = true;
			Tracer.tracepointVariableString("stonesoup_taintedValue",
					rig_epitendineum);
			try {
				stonesoup_checkVal = new BigInteger("2");
				stonesoup_intValue = new BigInteger(rig_epitendineum);
				stonesoup_intValueMinusOne = stonesoup_intValue
						.subtract(BigInteger.ONE);
				if (stonesoup_intValue.compareTo(BigInteger.ZERO) > 0) {
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					for (; stonesoup_checkVal
							.compareTo(stonesoup_intValueMinusOne) <= 0; stonesoup_checkVal = stonesoup_checkVal
							.add(BigInteger.ONE)) {
						if (stonesoup_intValue.mod(stonesoup_checkVal)
								.compareTo(BigInteger.ZERO) == 0) {
							stonesoup_prime = false;
							AbsLexer.imprescienceStripy.println("Not Prime");
							break;
						}
					}
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				}
			} catch (NumberFormatException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				AbsLexer.imprescienceStripy
						.println("STONESOUP: Input string is not an integer");
			}
			AbsLexer.imprescienceStripy.println("finished evaluating");
			Tracer.tracepointWeaknessEnd();
		}
	}
}
