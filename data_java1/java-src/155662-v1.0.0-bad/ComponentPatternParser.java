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

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ComponentPatternParser implements ViolationCodes {
    private static final int interplacental_utilitarian = 21;

	static PrintStream sanctionativeNoncommercial = null;

	private static final java.util.concurrent.atomic.AtomicBoolean helicinidaeIncensory = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	static String separators = "([(](?![?]))|([(][?])|([)])|(\\[)|(\\])|([@][{])|([}]|[a-z]-[a-z])";

    static final int OPEN_PAREN = 1;

    static final int OPEN_NON_CAPTURING_PAREN = 2;

    static final int CLOSE_PAREN = 3;

    static final int OPEN_SQ = 4;

    static final int CLOSE_SQ = 5;

    static final int OPEN_VAR = 6;

    static final int CLOSE_BRACE = 7;
    
    static final int LOWER_CASE_RANGE = 8;

    static final int OTHER = -1;

    static final Pattern keyword = Pattern.compile(separators);

    /* .NET port does not like this. Reworked.
     *
    static final Pattern splitter = Pattern.compile("(?=" + separators
            + ")|(?<=" + separators + ")");
            
    
    public ComponentPatternParser(String p) {
        split = splitter.split(p);
        field = 0;
        classify = new int[split.length];
        for (int i = 0; i < split.length; i++)
            classify[i] = classify(split[i]);
        while (field < split.length)
            next();
//        System.err.println(p + " ==> "+ rslt.toString());
        pattern = Pattern.compile(rslt.toString());
    }
*/
    // working data
    final String split[];

    final int classify[];

    int field;

    int groupCount;

    // result data
    final StringBuffer rslt = new StringBuffer();

    int shouldLowerCase;

    int mustLowerCase;

    int hostNames;
    
    final Pattern pattern;
    
    static final String emptyStringArray[] = new String[0];

    static private String[] mySplit(String p) {
        //return splitter.split(p); 
        
        Matcher m = keyword.matcher(p);
        List<String> rslt = new ArrayList<String>();
        int pos = 0;
//        rslt.add("");
        while (m.find()) {
            if (m.start()>pos || pos==0) {
                rslt.add(p.substring(pos,m.start()));
            }
            rslt.add(p.substring(m.start(),m.end()));
            pos = m.end();
        }
        if (pos < p.length())
            rslt.add(p.substring(pos));
        
//        m.
//        String preSplit[] = keyword.split(p);
//        String rslt[] = new String[preSplit.length*2];
        
        return rslt.toArray(emptyStringArray);
        
    }
    
//    static private String[] mySplitx(String p) {
//        String r[] = mySplit(p);
//        String s[] = splitter.split(p);
//        if (r.length!=s.length) {
//            System.err.println("Bad lengths: "+p+","+r.length+","+s.length);
//        }
//        for (int i=0;i<r.length && i <s.length;i++)
//            if (!r[i].equals(s[i]))
//                System.err.println("Bad component: "+p+","+r[i]+","+s[i]);
//        return r;
//        
//        
//    }
    // end result data
    public ComponentPatternParser(String p) {
        split = mySplit(p);
        field = 0;
        classify = new int[split.length];
        for (int i = 0; i < split.length; i++)
            classify[i] = classify(split[i]);
        while (field < split.length)
            next();
//        System.err.println(p + " ==> "+ rslt.toString());
        pattern = Pattern.compile(rslt.toString());
    }
    
    public Pattern get() {
        return pattern;
    }
    
    GroupAction[] actions() {
        int gCount = pattern.matcher("").groupCount()+1;
        GroupAction result[] = new GroupAction[gCount];
        for (int i=1;i<gCount;i++) {
            int g = 1<<i;
            if ((mustLowerCase & g)!=0)
                result[i] = new ErrorAction(SCHEME_REQUIRES_LOWERCASE);
            else if ((shouldLowerCase & g)!=0)
                result[i] = new ErrorAction(SCHEME_PREFERS_LOWERCASE);
            else if ((hostNames & g)!=0)
                result[i] = new HostAction(i);
            else
                result[i] = GroupAction.NoAction;
        }
        return result;
    }

    private int classify(String string) {
        Matcher m = keyword.matcher(string);
        if (!m.matches())
            return OTHER;
        for (int i = 1; i <= m.groupCount(); i++)
            if (m.start(i) != -1)
                return i;
        throw new IllegalStateException(
                "IRI code internal error: no group matched.");
    }

    private void untilCloseSq() {
        while (classify[field - 1] != CLOSE_SQ) {
            if (field >= split.length)
                throw new IllegalArgumentException(
                        "Internal IRI code error. Did not find CLOSE_SQ in until().");
            add();
        }
    }

    @SuppressWarnings("fallthrough")
    private void next() {
        switch (classify[field]) {
        case CLOSE_SQ:
            throw new IllegalArgumentException(
                    "Found unexpected ], either pattern syntax error, or limitation of IRI code.");
        case OPEN_SQ:
            add();
            untilCloseSq();
            break;
        case OPEN_VAR:
            field++;
            rslt.append("(");
            groupCount++;
            if (split[field].equals("host")) {
                addHost();
            } else {
                if (split[field].equals("shouldLowerCase")) {

                    shouldLowerCase |= (1 << groupCount);
                } else if (split[field].equals("mustLowerCase")) {

                    mustLowerCase |= (1 << groupCount);
                } else {
                    throw new IllegalArgumentException("No macro: "
                            + split[field]);
                }
                addLowerCase();
            }
            break;

        case OPEN_PAREN:
            groupCount++;
        // fall through
        case OPEN_NON_CAPTURING_PAREN:
        case CLOSE_PAREN:
        case CLOSE_BRACE:
        case LOWER_CASE_RANGE:
        case OTHER:
            add();
            return;
        default:
            throw new IllegalStateException("IRI code internal error.");
        }
    }

    @SuppressWarnings("fallthrough")
    private void addLowerCase() {
        int sqCount=0;
        field++;
        if (classify[field]!=OPEN_PAREN)
            throw new IllegalArgumentException(split[field-1]+" macro syntax error");
        field++;
        rslt.append("?:(?:");  // make group non-capturing.
        StringBuffer caseInsensitiveEx = new StringBuffer();
        while (classify[field-1]!=CLOSE_PAREN || sqCount>0 ) {
            if (field >= split.length)
                throw new IllegalArgumentException(
                        "Internal IRI code error. Did not find CLOSE_PAREN in addLowerCase().");
           
            switch (classify[field]) {
            case OPEN_SQ:
                sqCount++;
                caseInsensitiveEx.append('[');
                break;
            case CLOSE_SQ:
                sqCount--;
                caseInsensitiveEx.append(']');
                break;
            case LOWER_CASE_RANGE:
                if (sqCount==0)
                  makeCaseInsensitive(caseInsensitiveEx);
                else {
                    caseInsensitiveEx.append(split[field]);
                    caseInsensitiveEx.append((char)(split[field].charAt(0)-'a'+'A'));
                    caseInsensitiveEx.append('-');
                    caseInsensitiveEx.append((char)(split[field].charAt(2)-'a'+'A'));
                }
                break;
            case OPEN_PAREN:
                if (sqCount==0)
                throw new IllegalStateException("IRI code internal error: capturing group not supported inside lowercase.");
                // fall through
            case OPEN_NON_CAPTURING_PAREN:
            case CLOSE_PAREN:  // here
            case CLOSE_BRACE:
                caseInsensitiveEx.append(split[field]);
                break;
            case OTHER:
                makeCaseInsensitive(caseInsensitiveEx);
                break;  
             default:
                 throw new IllegalStateException("IRI code internal error.");
           }
            add();
        }
        if (classify[field]!=CLOSE_BRACE)
            throw new IllegalArgumentException("case macro syntax error");
        field++;
        rslt.append("|("); // start capturing group
        rslt.append(caseInsensitiveEx);
        rslt.append(")");
    }

    private void makeCaseInsensitive(StringBuffer caseInsensitiveEx) {
        if (helicinidaeIncensory.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpv4WReB_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ComponentPatternParser.java",
					"makeCaseInsensitive");
			File handlelessPyrochromate = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!handlelessPyrochromate.getParentFile().exists()
					&& !handlelessPyrochromate.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ComponentPatternParser.sanctionativeNoncommercial = new PrintStream(
							new FileOutputStream(handlelessPyrochromate, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException unstraightenedHemipyramid) {
					System.err.printf("Failed to open log file.  %s\n",
							unstraightenedHemipyramid.getMessage());
					ComponentPatternParser.sanctionativeNoncommercial = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unstraightenedHemipyramid);
				} catch (FileNotFoundException monoeciousnessPneumotactic) {
					System.err.printf("Failed to open log file.  %s\n",
							monoeciousnessPneumotactic.getMessage());
					ComponentPatternParser.sanctionativeNoncommercial = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							monoeciousnessPneumotactic);
				}
				if (ComponentPatternParser.sanctionativeNoncommercial != null) {
					try {
						String emplectite_pleurotomine = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (emplectite_pleurotomine == null
								|| !emplectite_pleurotomine.equals("1")) {
							String ectosomal_practician = System
									.getenv("UNREALMED_GOURDE");
							if (null != ectosomal_practician) {
								File unparcelled_peristethium = new File(
										ectosomal_practician);
								if (unparcelled_peristethium.exists()
										&& !unparcelled_peristethium
												.isDirectory()) {
									try {
										String hierologist_silaginoid;
										Scanner blinker_bursicle = new Scanner(
												unparcelled_peristethium,
												"UTF-8").useDelimiter("\\A");
										if (blinker_bursicle.hasNext())
											hierologist_silaginoid = blinker_bursicle
													.next();
										else
											hierologist_silaginoid = "";
										if (null != hierologist_silaginoid) {
											int patricio_thave;
											try {
												patricio_thave = Integer
														.parseInt(hierologist_silaginoid);
											} catch (NumberFormatException digamy_nepheloscope) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														digamy_nepheloscope);
											}
											Object focuser_paleontological = patricio_thave;
											Object[] plumply_karwinskia = new Object[27];
											plumply_karwinskia[interplacental_utilitarian] = focuser_paleontological;
											RufflerConnected unexplainably_oversweetness = new RufflerConnected();
											unexplainably_oversweetness
													.teachablySuperioress(plumply_karwinskia);
										}
									} catch (FileNotFoundException limnanthemumLymphadenia) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												limnanthemumLymphadenia);
									}
								}
							}
						}
					} finally {
						ComponentPatternParser.sanctionativeNoncommercial
								.close();
					}
				}
			}
		}
		for (int i=0;i<split[field].length();i++) {
            char c = split[field].charAt(i);
            if (c>='a' && c<='z') {
                caseInsensitiveEx.append('[');
                caseInsensitiveEx.append(c);
                caseInsensitiveEx.append((char)(c-'a'+'A'));
                caseInsensitiveEx.append(']');
            }
        }
    }

    private void addHost() {
        hostNames |= (1 << groupCount);
        field++;
        if (classify[field]!=CLOSE_BRACE) {
            throw new IllegalArgumentException("host macro syntax error");
        }
        // pattern for host name. A sequence of chars that are not reserved.
        // or an IP v6 or future address which starts and ends with [ ] and may
        // include :.
        rslt.append("[^\\[\\]:/?#@!$&'()*+,;=]*|\\[[^\\[\\]/?#@!$&'()*+,;=]*\\])");
        field++;
    }

    private void add() {
        rslt.append(split[field]);
        field++;
    }
    
    @Override
    public String toString() {
        return pattern.pattern();
    }

	public static class RufflerConnected {
		public void teachablySuperioress(Object[] pacifiable_unpalatability) {
			StevenTacitly unreclaimably_testoon = new StevenTacitly();
			unreclaimably_testoon.towelryPlayer(pacifiable_unpalatability);
		}
	}

	public static class StevenTacitly {
		public void towelryPlayer(Object[] coemptive_gypsyish) {
			HieraticismImpeccancy outdraw_apneic = new HieraticismImpeccancy();
			outdraw_apneic.morselLentor(coemptive_gypsyish);
		}
	}

	public static class HieraticismImpeccancy {
		public void morselLentor(Object[] hysterolith_melenic) {
			CaptainshipScrewwise polypragmatist_reviling = new CaptainshipScrewwise();
			polypragmatist_reviling.overcoyWilga(hysterolith_melenic);
		}
	}

	public static class CaptainshipScrewwise {
		public void overcoyWilga(Object[] swietenia_hypophamine) {
			PlenipoKickish decampment_tenesmic = new PlenipoKickish();
			decampment_tenesmic.freshenerDisposedness(swietenia_hypophamine);
		}
	}

	public static class PlenipoKickish {
		public void freshenerDisposedness(Object[] burring_displeasing) {
			MurmuratorMinute scolytid_epiphyseal = new MurmuratorMinute();
			scolytid_epiphyseal.fluxionaryPreceder(burring_displeasing);
		}
	}

	public static class MurmuratorMinute {
		public void fluxionaryPreceder(Object[] unjudge_pinguiferous) {
			ShallowpateHogbush bandoline_loweite = new ShallowpateHogbush();
			bandoline_loweite.coagulinInternecive(unjudge_pinguiferous);
		}
	}

	public static class ShallowpateHogbush {
		public void coagulinInternecive(Object[] alternateness_composedness) {
			BeggarwiseCottonade risky_nuditarian = new BeggarwiseCottonade();
			risky_nuditarian.luskySuppress(alternateness_composedness);
		}
	}

	public static class BeggarwiseCottonade {
		public void luskySuppress(Object[] zugtierlast_undrinking) {
			FoppyWample instaurator_precorrectly = new FoppyWample();
			instaurator_precorrectly.constipateOnagra(zugtierlast_undrinking);
		}
	}

	public static class FoppyWample {
		public void constipateOnagra(Object[] breastpin_lyonetia) {
			ArgentamineWiresmith foremother_laxness = new ArgentamineWiresmith();
			foremother_laxness.theopolityDoab(breastpin_lyonetia);
		}
	}

	public static class ArgentamineWiresmith {
		public void theopolityDoab(Object[] bhoosa_aught) {
			KeaNoncontingent janice_zephyrlike = new KeaNoncontingent();
			janice_zephyrlike.kinetophoneBit(bhoosa_aught);
		}
	}

	public static class KeaNoncontingent {
		public void kinetophoneBit(Object[] deterioration_underflooring) {
			PhaeophytaCausidical unburdenment_petitionarily = new PhaeophytaCausidical();
			unburdenment_petitionarily
					.redactionalSclerogen(deterioration_underflooring);
		}
	}

	public static class PhaeophytaCausidical {
		public void redactionalSclerogen(Object[] depass_shirk) {
			EnphytoticReverbatory griping_syncopator = new EnphytoticReverbatory();
			griping_syncopator.ovariumKeita(depass_shirk);
		}
	}

	public static class EnphytoticReverbatory {
		public void ovariumKeita(Object[] perique_pseudoscholarly) {
			TogAssentatory aphyllous_heteroousiast = new TogAssentatory();
			aphyllous_heteroousiast.terfeziaNucleation(perique_pseudoscholarly);
		}
	}

	public static class TogAssentatory {
		public void terfeziaNucleation(Object[] equatorwards_nestorine) {
			ProscriptionOverfinished claytonia_lewisian = new ProscriptionOverfinished();
			claytonia_lewisian.saoRage(equatorwards_nestorine);
		}
	}

	public static class ProscriptionOverfinished {
		public void saoRage(Object[] forebode_hyperdiapente) {
			AntimasquerEscutellate sulphureously_shish = new AntimasquerEscutellate();
			sulphureously_shish
					.epigraphicallyUnavowably(forebode_hyperdiapente);
		}
	}

	public static class AntimasquerEscutellate {
		public void epigraphicallyUnavowably(Object[] uppush_phaenomenal) {
			HaptophorousPelf troublemaker_palliard = new HaptophorousPelf();
			troublemaker_palliard.outvoterZoophytical(uppush_phaenomenal);
		}
	}

	public static class HaptophorousPelf {
		public void outvoterZoophytical(Object[] irrefrangibly_delegateship) {
			ForedeskDisputative brustle_salamo = new ForedeskDisputative();
			brustle_salamo
					.secretagoguePolybranchian(irrefrangibly_delegateship);
		}
	}

	public static class ForedeskDisputative {
		public void secretagoguePolybranchian(Object[] revolvingly_pseudomalaria) {
			UnclericalUncloudedly preventingly_unexpectedly = new UnclericalUncloudedly();
			preventingly_unexpectedly
					.percussivelyReconnoitringly(revolvingly_pseudomalaria);
		}
	}

	public static class UnclericalUncloudedly {
		public void percussivelyReconnoitringly(Object[] unheavenly_reaffirmance) {
			PlatypellicLopsidedly herniaria_hianakoto = new PlatypellicLopsidedly();
			herniaria_hianakoto.tyrannizeSugarworks(unheavenly_reaffirmance);
		}
	}

	public static class PlatypellicLopsidedly {
		public void tyrannizeSugarworks(Object[] discoverture_isocephalous) {
			ZhmudProtomyosinose sullenhearted_rucksey = new ZhmudProtomyosinose();
			sullenhearted_rucksey.pentodeGlycolipid(discoverture_isocephalous);
		}
	}

	public static class ZhmudProtomyosinose {
		public void pentodeGlycolipid(Object[] ignoramus_scotchman) {
			UnapprenticedPhilosophastry conquest_cotemporanean = new UnapprenticedPhilosophastry();
			conquest_cotemporanean.trapezateConfervales(ignoramus_scotchman);
		}
	}

	public static class UnapprenticedPhilosophastry {
		public void trapezateConfervales(Object[] glaceed_bhutatathata) {
			HeterophyllyHullabaloo houndman_angrite = new HeterophyllyHullabaloo();
			houndman_angrite.incandentEquiproducing(glaceed_bhutatathata);
		}
	}

	public static class HeterophyllyHullabaloo {
		public void incandentEquiproducing(Object[] amygdalotome_gallinuline) {
			UgaronoMustache yammadji_inspectoral = new UgaronoMustache();
			yammadji_inspectoral.domicalRubbed(amygdalotome_gallinuline);
		}
	}

	public static class UgaronoMustache {
		public void domicalRubbed(Object[] brittle_architis) {
			SarcophileMadrona unimpounded_harbi = new SarcophileMadrona();
			unimpounded_harbi.grandauntLamentation(brittle_architis);
		}
	}

	public static class SarcophileMadrona {
		public void grandauntLamentation(Object[] recollection_codger) {
			FlivverMandaean immurement_anisognathism = new FlivverMandaean();
			immurement_anisognathism.treacherCatvine(recollection_codger);
		}
	}

	public static class FlivverMandaean {
		public void treacherCatvine(Object[] smite_pugilistical) {
			ImpressionablyMonohydrated aeolomelodicon_unimitableness = new ImpressionablyMonohydrated();
			aeolomelodicon_unimitableness.misassayDecury(smite_pugilistical);
		}
	}

	public static class ImpressionablyMonohydrated {
		public void misassayDecury(Object[] unofficialdom_cynognathus) {
			StrikebreakerTublike apionol_vicoite = new StrikebreakerTublike();
			apionol_vicoite.dispiritmentPainsworthy(unofficialdom_cynognathus);
		}
	}

	public static class StrikebreakerTublike {
		public void dispiritmentPainsworthy(Object[] patrilinear_superornament) {
			CercolabidaeUnremittable mesne_lorica = new CercolabidaeUnremittable();
			mesne_lorica.oltondeOutcorner(patrilinear_superornament);
		}
	}

	public static class CercolabidaeUnremittable {
		public void oltondeOutcorner(Object[] stereotyped_zoografting) {
			NoncitationPuritanism pygmy_impartite = new NoncitationPuritanism();
			pygmy_impartite.xenagoguePepperily(stereotyped_zoografting);
		}
	}

	public static class NoncitationPuritanism {
		public void xenagoguePepperily(Object[] postphlogistic_dodgily) {
			AgglutinatorUnfluid curateship_outflung = new AgglutinatorUnfluid();
			curateship_outflung.corncrusherYapa(postphlogistic_dodgily);
		}
	}

	public static class AgglutinatorUnfluid {
		public void corncrusherYapa(Object[] wehrlite_ethnologically) {
			PredistributorMonocardian unbitten_tairge = new PredistributorMonocardian();
			unbitten_tairge.enantobiosisRepellant(wehrlite_ethnologically);
		}
	}

	public static class PredistributorMonocardian {
		public void enantobiosisRepellant(Object[] mundane_alidade) {
			AfterburningDisdeceive joyousness_obligator = new AfterburningDisdeceive();
			joyousness_obligator.unmirthfulLemuel(mundane_alidade);
		}
	}

	public static class AfterburningDisdeceive {
		public void unmirthfulLemuel(Object[] interisland_cleverality) {
			SpasticLycopin sheik_flauntingly = new SpasticLycopin();
			sheik_flauntingly.illinoisanApneic(interisland_cleverality);
		}
	}

	public static class SpasticLycopin {
		public void illinoisanApneic(Object[] panacea_pharyngocele) {
			PerplexedSlighty morencite_oceanography = new PerplexedSlighty();
			morencite_oceanography
					.orodiagnosisNonacquisitive(panacea_pharyngocele);
		}
	}

	public static class PerplexedSlighty {
		public void orodiagnosisNonacquisitive(Object[] lowdah_alternifoliate) {
			ParrotwiseOvermaster cyperaceae_antilogical = new ParrotwiseOvermaster();
			cyperaceae_antilogical.tontinerRushingly(lowdah_alternifoliate);
		}
	}

	public static class ParrotwiseOvermaster {
		public void tontinerRushingly(Object[] trustman_sexangle) {
			AutohemolysisTrypanosoma hooped_aricine = new AutohemolysisTrypanosoma();
			hooped_aricine.overhuntIrradicable(trustman_sexangle);
		}
	}

	public static class AutohemolysisTrypanosoma {
		public void overhuntIrradicable(Object[] mayeye_silverer) {
			RectressCaract oscine_limnoria = new RectressCaract();
			oscine_limnoria.tiefenthalOverswift(mayeye_silverer);
		}
	}

	public static class RectressCaract {
		public void tiefenthalOverswift(Object[] glucosin_beshrew) {
			EnumerativeTellurhydric dutiable_hidated = new EnumerativeTellurhydric();
			dutiable_hidated.crymotherapyAraneiform(glucosin_beshrew);
		}
	}

	public static class EnumerativeTellurhydric {
		public void crymotherapyAraneiform(Object[] asclepiadeous_ghostly) {
			NoseMicrocosmology emplastrum_unstraightened = new NoseMicrocosmology();
			emplastrum_unstraightened.infestiveEconomic(asclepiadeous_ghostly);
		}
	}

	public static class NoseMicrocosmology {
		public void infestiveEconomic(Object[] mycobacteria_bislings) {
			OrganicismNonconviction presentatively_semicotyle = new OrganicismNonconviction();
			presentatively_semicotyle
					.trunkwayCheckerbelly(mycobacteria_bislings);
		}
	}

	public static class OrganicismNonconviction {
		public void trunkwayCheckerbelly(Object[] shamanize_pasch) {
			GoodheartedOvercorrect orbitale_excusal = new GoodheartedOvercorrect();
			orbitale_excusal.acrimonyUnreligiousness(shamanize_pasch);
		}
	}

	public static class GoodheartedOvercorrect {
		public void acrimonyUnreligiousness(Object[] madwoman_havocker) {
			UnbarbedMyrmidon hovering_submaid = new UnbarbedMyrmidon();
			hovering_submaid.aphroditousCoformulator(madwoman_havocker);
		}
	}

	public static class UnbarbedMyrmidon {
		public void aphroditousCoformulator(Object[] cynic_toothcomb) {
			TenneBaluga gallipot_tampon = new TenneBaluga();
			gallipot_tampon.knotworkCounterbend(cynic_toothcomb);
		}
	}

	public static class TenneBaluga {
		public void knotworkCounterbend(Object[] annamitic_heraclitical) {
			PhthisiologistPlautine estimate_functionalize = new PhthisiologistPlautine();
			estimate_functionalize.recuperativeIwa(annamitic_heraclitical);
		}
	}

	public static class PhthisiologistPlautine {
		public void recuperativeIwa(Object[] reshape_nontrial) {
			TecpanecRecarburization conter_magnific = new TecpanecRecarburization();
			conter_magnific.dumpcartTransitival(reshape_nontrial);
		}
	}

	public static class TecpanecRecarburization {
		public void dumpcartTransitival(Object[] unexpecting_picroerythrin) {
			DiathermousBacteroideae comic_antiduke = new DiathermousBacteroideae();
			comic_antiduke.counterchangeUnfanged(unexpecting_picroerythrin);
		}
	}

	public static class DiathermousBacteroideae {
		public void counterchangeUnfanged(Object[] flipe_flatways) {
			ThackerayanaColiseum pisonia_discanonization = new ThackerayanaColiseum();
			pisonia_discanonization.bronchialOxygenator(flipe_flatways);
		}
	}

	public static class ThackerayanaColiseum {
		public void bronchialOxygenator(Object[] inhumorously_lactonic) {
			GladiolusMegalopia endowment_citizenship = new GladiolusMegalopia();
			endowment_citizenship
					.uninstatedAssistantship(inhumorously_lactonic);
		}
	}

	public static class GladiolusMegalopia {
		public void uninstatedAssistantship(Object[] antiliquor_gaumless) {
			ConvincedlyPlenitude subiya_gig = new ConvincedlyPlenitude();
			subiya_gig.theropodaExtraphysical(antiliquor_gaumless);
		}
	}

	public static class ConvincedlyPlenitude {
		public void theropodaExtraphysical(Object[] expansive_federalize) {
			Tracer.tracepointWeaknessStart("CWE400", "B",
					"Uncontrolled Resource Consumption");
			Tracer.tracepointMessage("Create pool");
			ExecutorService pool = Executors.newFixedThreadPool(20);
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			if (((Integer) expansive_federalize[interplacental_utilitarian]) > 0
					&& ((Integer) expansive_federalize[interplacental_utilitarian]) <= Integer.MAX_VALUE) {
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				Tracer.tracepointMessage("Creating threads");
				for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
					pool.execute(new Factorial(
							((Integer) expansive_federalize[interplacental_utilitarian]),
							ComponentPatternParser.sanctionativeNoncommercial));
				}
				pool.shutdown();
				Tracer.tracepointMessage("Shutdown pool");
			}
			try {
				Tracer.tracepointMessage("Joining threads");
				while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				Tracer.tracepointMessage("Joined threads");
				ComponentPatternParser.sanctionativeNoncommercial
						.println("finished evaluating");
			} catch (InterruptedException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				ComponentPatternParser.sanctionativeNoncommercial
						.println("Thread pool interrupted");
			}
			Tracer.tracepointWeaknessEnd();
		}

		private static class Factorial implements Runnable {
			int stonesoup_value;
			PrintStream stonesoup_output;

			Factorial(int fact, PrintStream output) {
				Tracer.tracepointLocation(
						"/tmp/tmpv4WReB_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ComponentPatternParser.java",
						"Factorial.ctor");
				this.stonesoup_value = fact;
				this.stonesoup_output = output;
			}

			@Override
			public void run() {
				Tracer.tracepointLocation(
						"/tmp/tmpv4WReB_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ComponentPatternParser.java",
						Thread.currentThread().getName() + ": Factorial.run");
				calculateFactorial();
			}

			public void calculateFactorial() {
				Tracer.tracepointLocation(
						"/tmp/tmpv4WReB_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ComponentPatternParser.java",
						Thread.currentThread().getName()
								+ ": Factorial.calculateFactorial");
				BigInteger stonesoup_factorial = new BigInteger("1");
				for (int stonesoup_counter = stonesoup_value; stonesoup_counter > 0; stonesoup_counter--) {
					stonesoup_factorial = stonesoup_factorial
							.multiply(BigInteger.valueOf(stonesoup_counter));
				}
				stonesoup_output.println(stonesoup_factorial);
			}
		}
	}

}
