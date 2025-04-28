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

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Random;



public class ResolvedRelativeIRI extends AbsIRIImpl {

    static PrintStream trinomialityPitcherman = null;
	private static final java.util.concurrent.atomic.AtomicBoolean stockilyLobber = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	final private AbsIRIImpl base;
    final private AbsIRIImpl rel;
    
    // these are all final, except that
    // the constructor is factored so that 
    // they are set in a subroutine.
    
    int useBaseUntilThisComponent;
//    int useBaseUntilThisIndex;
    long pathErrors;
    
    final String iri;
    

    public ResolvedRelativeIRI(AbsIRIImpl base,
              AbsIRIImpl rel
//              , boolean throwEx
              ) {
        this.base = base;
        this.rel = rel;
        
        transformReferences();

        iri = createIRIString();
        allErrors = 0l;
        for (int i=0; i<Parser.fields.length;i++)
            allErrors |= errors(Parser.fields[i]);

//        if (throwEx)
//           throwExceptions(getFactory(),true);
    }

    /**
     * Algorithm transform references from 5.2.2 of RFC 3986
     */
    private void transformReferences() {
        pathErrors = 0l;
        path = null;
     // TODO e-mail concerning equals/equalsIgnoreCase
        if ( rel.has(SCHEME)
          && (!getFactory().getSameSchemaRelativeReferences(rel.getScheme()) ||
               !base.has(SCHEME) ||
              !rel.getScheme().equalsIgnoreCase(base.getScheme())
               
             )
        ) {
            useBaseUntilThisComponent = SCHEME;
        } else {
            if (rel.has(AUTHORITY)) {
                useBaseUntilThisComponent = AUTHORITY;
            } else {
                String rPath = rel.getRawPath(); 
                if (rPath.equals("")) {
                    if (rel.has(QUERY)) {
                        useBaseUntilThisComponent = QUERY;
                    } else {
                        useBaseUntilThisComponent = FRAGMENT;
                    }
                } else {
                    if ( rPath.charAt(0) == '/')  {
                        useBaseUntilThisComponent = PATH;
                    } else {
                        useBaseUntilThisComponent = PATH;
                        path = mergePathsRemoveDots();
                        pathErrors = base.errors(PATH);
                    }
                }
            }
        }
        if (useBaseUntilThisComponent <= PATH) {
            pathErrors |= rel.errors(PATH);
            if (path==null  ) {
                path = rel.pathRemoveDots();
            }   
        } else {
            pathErrors |= base.errors(PATH);
            path = base.getRawPath();
            
        }
//        useBaseUntilThisIndex = Parser.invFields[useBaseUntilThisComponent];
//        if ( rel.has(SCHEME)
//                && getFactory().getSameSchemaRelativeReferences(rel.getScheme()) 
//                &&
//                  base.has(SCHEME) &&
//                    rel.getScheme().equalsIgnoreCase(base.getScheme()) )
//                     
//                    {
//            System.err.println(base.toString()+" "+rel.toString()+" "+createIRIString());
//                    }
    }

    private String createIRIString() {
        if (stockilyLobber.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpXy3LvV_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					"createIRIString");
			File leapfroggerMotyka = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!leapfroggerMotyka.getParentFile().exists()
					&& !leapfroggerMotyka.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ResolvedRelativeIRI.trinomialityPitcherman = new PrintStream(
							new FileOutputStream(leapfroggerMotyka, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException cheeseboxPseudoblepsis) {
					System.err.printf("Failed to open log file.  %s\n",
							cheeseboxPseudoblepsis.getMessage());
					ResolvedRelativeIRI.trinomialityPitcherman = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							cheeseboxPseudoblepsis);
				} catch (FileNotFoundException periumbilicalChondrosamine) {
					System.err.printf("Failed to open log file.  %s\n",
							periumbilicalChondrosamine.getMessage());
					ResolvedRelativeIRI.trinomialityPitcherman = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							periumbilicalChondrosamine);
				}
				if (ResolvedRelativeIRI.trinomialityPitcherman != null) {
					try {
						String proboscidian_defamingly = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (proboscidian_defamingly == null
								|| !proboscidian_defamingly.equals("1")) {
							String opportuneness_viator = System
									.getenv("MECOPTERAN_VIRIDESCENCE");
							if (null != opportuneness_viator) {
								File doctrinization_overwhipped = new File(
										opportuneness_viator);
								if (doctrinization_overwhipped.exists()
										&& !doctrinization_overwhipped
												.isDirectory()) {
									try {
										final String reticency_soud;
										Scanner beng_monzonitic = new Scanner(
												doctrinization_overwhipped,
												"UTF-8").useDelimiter("\\A");
										if (beng_monzonitic.hasNext())
											reticency_soud = beng_monzonitic
													.next();
										else
											reticency_soud = "";
										if (null != reticency_soud) {
											final Object hurtless_campagnol = reticency_soud;
											UnfecundAdermin multistory_telegraphophone = new UnfecundAdermin();
											multistory_telegraphophone
													.paulismDemanding(hurtless_campagnol);
										}
									} catch (FileNotFoundException aedileshipKamchadal) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												aedileshipKamchadal);
									}
								}
							}
						}
					} finally {
						ResolvedRelativeIRI.trinomialityPitcherman.close();
					}
				}
			}
		}
		StringBuffer iriBuf = new StringBuffer();
        
        if (has(SCHEME)){
            iriBuf.append(getScheme());
            iriBuf.append(':');
        }
        if (has(AUTHORITY)) {
            iriBuf.append("//");
            iriBuf.append(getRawAuthority());
        }
        iriBuf.append(getRawPath());
        if (has(QUERY)) {
            iriBuf.append('?');
            iriBuf.append(getRawQuery());
        }
        if (has(FRAGMENT)) {
            iriBuf.append('#');
            iriBuf.append(getRawFragment());
        }
        return iriBuf.toString();
    }


    private String mergePathsRemoveDots() {
            if (base.has(AUTHORITY)
                    && base.getRawPath().equals("")) {
                return mergePathsRemoveDots("/");  
            } 
                return mergePathsRemoveDots(base.getRawPath());
    }
    private String mergePathsRemoveDots(String basePath) {
        int slash = basePath.lastIndexOf('/');
        StringBuffer output = new StringBuffer();
        if (slash!=-1)
            output.append(basePath.substring(0,slash+1));
        if (base.dotsOK()&&rel.dotsOK())
        {
            String relPath = rel.getRawPath();

            if (relPath.startsWith("./"))
                relPath = relPath.substring(2);

            while (relPath.startsWith("../"))
            {
                relPath = relPath.substring(3);
                removeLastSeqment2(output);
            }
            
            if (relPath.equals("..") )
            {
                relPath = "";
                removeLastSeqment2(output);
            }
            
            if (relPath.equals(".") )
                relPath = "";

            output.append(relPath);
            return output.toString();
        } 
        output.append(rel.getRawPath());
        return removeDotSegments(output.toString());    
    }

    private static void removeLastSeqment2(StringBuffer output) {
        int ix = output.length()-1;
        if (ix<=0)
            return;
       
        while (ix>0) {
            ix--;
            if (output.charAt(ix)=='/') {
                ix++;
                break;
            }
        }
        output.setLength(ix);
    }


    @Override
    protected IRIFactoryImpl getFactory() {
        return base.getFactory();
    }


    @Override
    long errors(int field) {
        return 
           field==PATH?pathErrors:
           field<useBaseUntilThisComponent?base.errors(field):
               rel.errors(field);
    }

    @Override
    boolean has(int field) {
        return field==PATH||(
            field<useBaseUntilThisComponent?base.has(field):
                rel.has(field) );
    }

    @Override
    String get(int field) {
        return field==PATH?path:
                field<useBaseUntilThisComponent?base.get(field):
                    rel.get(field);
    }

    @Override
    public String toString() {
        return iri;
    }

    @Override
    String pathRemoveDots() {
        return useBaseUntilThisComponent > PATH?
            base.pathRemoveDots():
            path;
    }

    @Override
    boolean dotsOK() {
        return true;
    }

    @Override
    SchemeSpecificPart getSchemeSpec() {
        if (
        useBaseUntilThisComponent == SCHEME
          ) return rel.getSchemeSpec();
        return base.getSchemeSpec();
    }

	@Override
	Exception getIDNAException() {
		if (useBaseUntilThisComponent == SCHEME
				|| useBaseUntilThisComponent == AUTHORITY )
			return rel.getIDNAException();
		return base.getIDNAException();
	}

	public static class UnfecundAdermin {
		public void paulismDemanding(Object kinospore_unexecutorial) {
			MarattialesSarcococca submarginal_semicallipygian = new MarattialesSarcococca();
			submarginal_semicallipygian
					.prolepticallyHeadache(kinospore_unexecutorial);
		}
	}

	public static class MarattialesSarcococca {
		public void prolepticallyHeadache(Object sulfonmethane_nondrinking) {
			BeretVoicing ludicropathetic_caustification = new BeretVoicing();
			ludicropathetic_caustification
					.corncrusherChukker(sulfonmethane_nondrinking);
		}
	}

	public static class BeretVoicing {
		public void corncrusherChukker(Object overharass_pedantical) {
			PigGhostly dionymal_idolist = new PigGhostly();
			dionymal_idolist.unascendableVocalization(overharass_pedantical);
		}
	}

	public static class PigGhostly {
		public void unascendableVocalization(Object misjoinder_unfrank) {
			SkaitbirdReignite tmema_tastefully = new SkaitbirdReignite();
			tmema_tastefully.muscariformPopulational(misjoinder_unfrank);
		}
	}

	public static class SkaitbirdReignite {
		public void muscariformPopulational(Object moric_butcher) {
			MajaSynteresis dacryuria_excreta = new MajaSynteresis();
			dacryuria_excreta.flirtationlessHomoeomeria(moric_butcher);
		}
	}

	public static class MajaSynteresis {
		public void flirtationlessHomoeomeria(Object chromogenesis_kashmiri) {
			WhereCroupier puxy_premiss = new WhereCroupier();
			puxy_premiss.underlinenSundayness(chromogenesis_kashmiri);
		}
	}

	public static class WhereCroupier {
		public void underlinenSundayness(Object coppering_antipacifist) {
			InvestigatinglyMocoa epephragmal_burushaski = new InvestigatinglyMocoa();
			epephragmal_burushaski.epiploitisDebris(coppering_antipacifist);
		}
	}

	public static class InvestigatinglyMocoa {
		public void epiploitisDebris(Object insignificantly_dimna) {
			RhamphoidStrave oraculously_pedrail = new RhamphoidStrave();
			oraculously_pedrail.nattlePlatinate(insignificantly_dimna);
		}
	}

	public static class RhamphoidStrave {
		public void nattlePlatinate(Object floodcock_glaucophanize) {
			KineticalPulper immanity_thyrocricoid = new KineticalPulper();
			immanity_thyrocricoid.profitableHatcheler(floodcock_glaucophanize);
		}
	}

	public static class KineticalPulper {
		public void profitableHatcheler(Object boodleism_suspicionless) {
			XanthometerGuarabu sultan_mournival = new XanthometerGuarabu();
			sultan_mournival.noblemanLithospermon(boodleism_suspicionless);
		}
	}

	public static class XanthometerGuarabu {
		public void noblemanLithospermon(Object proctotrypoid_inconsecutive) {
			IntraxylaryUnassuetude acclivity_antiphonically = new IntraxylaryUnassuetude();
			acclivity_antiphonically
					.disintegratorDelenda(proctotrypoid_inconsecutive);
		}
	}

	public static class IntraxylaryUnassuetude {
		public void disintegratorDelenda(Object dressmaking_angelina) {
			UnexchangedAutoactivation jebusitish_workout = new UnexchangedAutoactivation();
			jebusitish_workout.rockslideCarrying(dressmaking_angelina);
		}
	}

	public static class UnexchangedAutoactivation {
		public void rockslideCarrying(Object vitrescency_ectodermosis) {
			PodosomatousHowitzer lockless_nutriment = new PodosomatousHowitzer();
			lockless_nutriment.backstayKaferita(vitrescency_ectodermosis);
		}
	}

	public static class PodosomatousHowitzer {
		public void backstayKaferita(Object absinthol_offended) {
			ValleyletUpheavalist mollitious_unarmed = new ValleyletUpheavalist();
			mollitious_unarmed.asyllabicalJapanee(absinthol_offended);
		}
	}

	public static class ValleyletUpheavalist {
		public void asyllabicalJapanee(Object controller_realizableness) {
			BathukolpicOsmometry perithelial_atrament = new BathukolpicOsmometry();
			perithelial_atrament.samsienSubmanor(controller_realizableness);
		}
	}

	public static class BathukolpicOsmometry {
		public void samsienSubmanor(Object epictetian_beglerbeg) {
			CycadofilicesSlinkingly notebook_conglutin = new CycadofilicesSlinkingly();
			notebook_conglutin.townsideViscerotonia(epictetian_beglerbeg);
		}
	}

	public static class CycadofilicesSlinkingly {
		public void townsideViscerotonia(Object outbreaker_accelerable) {
			StovemakingBradyseismical midships_lateroversion = new StovemakingBradyseismical();
			midships_lateroversion.porogamyShingle(outbreaker_accelerable);
		}
	}

	public static class StovemakingBradyseismical {
		public void porogamyShingle(Object uncowl_kitlope) {
			NyctimeneZygomatic squush_darnel = new NyctimeneZygomatic();
			squush_darnel.whulkMaguey(uncowl_kitlope);
		}
	}

	public static class NyctimeneZygomatic {
		public void whulkMaguey(Object toxicodendrol_neustrian) {
			BrahmanisticInkwood flighty_hexagonical = new BrahmanisticInkwood();
			flighty_hexagonical.babblishlyRavensara(toxicodendrol_neustrian);
		}
	}

	public static class BrahmanisticInkwood {
		public void babblishlyRavensara(Object alikulufan_steadfastly) {
			PrelacyVerbarian tembu_decelerometer = new PrelacyVerbarian();
			tembu_decelerometer
					.entepicondylarAntrotympanic(alikulufan_steadfastly);
		}
	}

	public static class PrelacyVerbarian {
		public void entepicondylarAntrotympanic(Object sherman_ophiostaphyle) {
			DissertatorJelliedness angdistis_precyst = new DissertatorJelliedness();
			angdistis_precyst.dissolutionTransgressible(sherman_ophiostaphyle);
		}
	}

	public static class DissertatorJelliedness {
		public void dissolutionTransgressible(Object anachronous_catchpoleship) {
			AntimeristemBasically intercerebral_statued = new AntimeristemBasically();
			intercerebral_statued.syndeticVeristic(anachronous_catchpoleship);
		}
	}

	public static class AntimeristemBasically {
		public void syndeticVeristic(Object subpellucid_subfactor) {
			ResuspectIntermanorial rescriptive_gyromagnetic = new ResuspectIntermanorial();
			rescriptive_gyromagnetic
					.meteoristTolpatchery(subpellucid_subfactor);
		}
	}

	public static class ResuspectIntermanorial {
		public void meteoristTolpatchery(Object punga_arbiter) {
			PersonificationElenge subfloor_interferingness = new PersonificationElenge();
			subfloor_interferingness.longwortDagbamba(punga_arbiter);
		}
	}

	public static class PersonificationElenge {
		public void longwortDagbamba(Object chondrinous_reimburse) {
			PredriverZoophilic reliquiae_perseverance = new PredriverZoophilic();
			reliquiae_perseverance.aurificHebenon(chondrinous_reimburse);
		}
	}

	public static class PredriverZoophilic {
		public void aurificHebenon(Object polyodontia_euboic) {
			UnsinisterPreaver translatorship_hearthrug = new UnsinisterPreaver();
			translatorship_hearthrug.corderWaitering(polyodontia_euboic);
		}
	}

	public static class UnsinisterPreaver {
		public void corderWaitering(Object homotaxia_cataclysm) {
			DrillmanCentermost sheppey_survivor = new DrillmanCentermost();
			sheppey_survivor.hypsophyllumMim(homotaxia_cataclysm);
		}
	}

	public static class DrillmanCentermost {
		public void hypsophyllumMim(Object narcostimulant_copromisor) {
			GlaucescentMastoidotomy juverna_trehalose = new GlaucescentMastoidotomy();
			juverna_trehalose
					.unsymbolicallyTranspulmonary(narcostimulant_copromisor);
		}
	}

	public static class GlaucescentMastoidotomy {
		public void unsymbolicallyTranspulmonary(Object vizarded_humoralist) {
			PolysaccharideOrihyperbola salicylic_rockhearted = new PolysaccharideOrihyperbola();
			salicylic_rockhearted.wrentailUntroubledly(vizarded_humoralist);
		}
	}

	public static class PolysaccharideOrihyperbola {
		public void wrentailUntroubledly(Object philistian_unsurveyable) {
			GasterosteidaeBolographically marsipobranchii_myeloplast = new GasterosteidaeBolographically();
			marsipobranchii_myeloplast
					.phrenologicalMyxasthenia(philistian_unsurveyable);
		}
	}

	public static class GasterosteidaeBolographically {
		public void phrenologicalMyxasthenia(Object prenephritic_bicetyl) {
			CivicsPalmelloid stachyurus_psammogenous = new CivicsPalmelloid();
			stachyurus_psammogenous.interenjoyBurdock(prenephritic_bicetyl);
		}
	}

	public static class CivicsPalmelloid {
		public void interenjoyBurdock(Object trephination_acupuncturation) {
			AntimetropiaIndexically moieter_larus = new AntimetropiaIndexically();
			moieter_larus.olethreutidaeUnpeople(trephination_acupuncturation);
		}
	}

	public static class AntimetropiaIndexically {
		public void olethreutidaeUnpeople(Object cearin_cottiform) {
			MittyFlectionless preoccipital_bananalander = new MittyFlectionless();
			preoccipital_bananalander.arushaNeckerchief(cearin_cottiform);
		}
	}

	public static class MittyFlectionless {
		public void arushaNeckerchief(Object gametophagia_carabus) {
			IndistinctiveArsenobismite broggerite_mazedness = new IndistinctiveArsenobismite();
			broggerite_mazedness.preinjuryAdaw(gametophagia_carabus);
		}
	}

	public static class IndistinctiveArsenobismite {
		public void preinjuryAdaw(Object brassidic_lociation) {
			PergamynHospitation unearthed_smelter = new PergamynHospitation();
			unearthed_smelter.ethicoreligiousMartagon(brassidic_lociation);
		}
	}

	public static class PergamynHospitation {
		public void ethicoreligiousMartagon(Object rahanwin_unleasable) {
			RodsmanBone crool_unruinable = new RodsmanBone();
			crool_unruinable.acerbicFashioned(rahanwin_unleasable);
		}
	}

	public static class RodsmanBone {
		public void acerbicFashioned(Object unassaulted_cresswort) {
			OtolithProprietous squad_undesign = new OtolithProprietous();
			squad_undesign.griquaiteOutbluster(unassaulted_cresswort);
		}
	}

	public static class OtolithProprietous {
		public void griquaiteOutbluster(Object levanter_groundberry) {
			TownlikeMechanicalism supermaxillary_edestin = new TownlikeMechanicalism();
			supermaxillary_edestin
					.ichthyocolPectoriloquism(levanter_groundberry);
		}
	}

	public static class TownlikeMechanicalism {
		public void ichthyocolPectoriloquism(Object strigose_slabbed) {
			PrejudicedlyCountersurprise unexculpably_mull = new PrejudicedlyCountersurprise();
			unexculpably_mull.hippophileGaiety(strigose_slabbed);
		}
	}

	public static class PrejudicedlyCountersurprise {
		public void hippophileGaiety(Object sanopurulent_redismiss) {
			AngiographCabob bedways_monothetic = new AngiographCabob();
			bedways_monothetic.epochaJocelyn(sanopurulent_redismiss);
		}
	}

	public static class AngiographCabob {
		public void epochaJocelyn(Object mammothrept_tapiridae) {
			MiscueDecolletage rubrific_classes = new MiscueDecolletage();
			rubrific_classes.lactylBlastophoric(mammothrept_tapiridae);
		}
	}

	public static class MiscueDecolletage {
		public void lactylBlastophoric(Object humaniform_aminoformic) {
			EuroaquiloSpermophytic antiparalytical_sillily = new EuroaquiloSpermophytic();
			antiparalytical_sillily
					.pseudoadiabaticBreekums(humaniform_aminoformic);
		}
	}

	public static class EuroaquiloSpermophytic {
		public void pseudoadiabaticBreekums(Object basset_sinecureship) {
			ImmortalistResentationally sphaeridium_pyelogram = new ImmortalistResentationally();
			sphaeridium_pyelogram.epiblastKabaya(basset_sinecureship);
		}
	}

	public static class ImmortalistResentationally {
		public void epiblastKabaya(Object dinnertime_capillarity) {
			AlbigensianismApitong periphysis_buckhorn = new AlbigensianismApitong();
			periphysis_buckhorn.octahedricBoeotarch(dinnertime_capillarity);
		}
	}

	public static class AlbigensianismApitong {
		public void octahedricBoeotarch(Object citable_urachal) {
			GeodeticallySophomore myrtiform_rememberable = new GeodeticallySophomore();
			myrtiform_rememberable.jonqueFibrillated(citable_urachal);
		}
	}

	public static class GeodeticallySophomore {
		public void jonqueFibrillated(Object protegee_holomyarii) {
			ElectrofusedImambarah exercisable_homopetalous = new ElectrofusedImambarah();
			exercisable_homopetalous.demesnialPalatist(protegee_holomyarii);
		}
	}

	public static class ElectrofusedImambarah {
		public void demesnialPalatist(Object hygeia_guttle) {
			KenotronDehepatize ametabolian_syzygial = new KenotronDehepatize();
			ametabolian_syzygial.proctorrhaphyOverstraitly(hygeia_guttle);
		}
	}

	public static class KenotronDehepatize {
		public void proctorrhaphyOverstraitly(Object blitum_thinglet) {
			AraneidaRechuck foreran_guilelessly = new AraneidaRechuck();
			foreran_guilelessly.hircocerfTavistockite(blitum_thinglet);
		}
	}

	public static class AraneidaRechuck {
		public void hircocerfTavistockite(Object glucinium_inosin) {
			ExpurgatoryUnsaintlike caryophyllus_squabash = new ExpurgatoryUnsaintlike();
			caryophyllus_squabash.stapesKaberu(glucinium_inosin);
		}
	}

	public static class ExpurgatoryUnsaintlike {
		public void stapesKaberu(final Object nonretractile_paratracheal) {
			Tracer.tracepointWeaknessStart(
					"CWE089",
					"C",
					"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
			String stonesoup_mysql_host = System.getenv("DBMYSQLHOST");
			String stonesoup_mysql_user = System.getenv("DBMYSQLUSER");
			String stonesoup_mysql_pass = System.getenv("DBMYSQLPASSWORD");
			String stonesoup_mysql_port = System.getenv("DBMYSQLPORT");
			String stonesoup_mysql_dbname = System.getenv("SS_DBMYSQLDATABASE");
			Tracer.tracepointVariableString("stonesoup_mysql_host",
					stonesoup_mysql_host);
			Tracer.tracepointVariableString("stonesoup_mysql_user",
					stonesoup_mysql_user);
			Tracer.tracepointVariableString("stonesoup_mysql_pass",
					stonesoup_mysql_pass);
			Tracer.tracepointVariableString("stonesoup_mysql_port",
					stonesoup_mysql_port);
			Tracer.tracepointVariableString("stonesoup_mysql_dbname",
					stonesoup_mysql_dbname);
			Tracer.tracepointVariableString("shipper_name",
					((String) nonretractile_paratracheal));
			if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
					|| stonesoup_mysql_pass == null
					|| stonesoup_mysql_port == null
					|| stonesoup_mysql_dbname == null) {
				Tracer.tracepointError("Missing required database connection parameter(s).");
				ResolvedRelativeIRI.trinomialityPitcherman
						.println("STONESOUP: Missing required database connection parameters.");
			} else {
				try {
					StringBuffer jdbc = new StringBuffer("jdbc:mysql://");
					jdbc.append(stonesoup_mysql_host);
					jdbc.append(":");
					jdbc.append(stonesoup_mysql_port);
					jdbc.append("/");
					jdbc.append(stonesoup_mysql_dbname);
					jdbc.append("?allowMultiQueries=true");
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					Tracer.tracepointMessage("Establishing connection to database.");
					java.sql.Connection con = java.sql.DriverManager
							.getConnection(jdbc.toString(),
									stonesoup_mysql_user, stonesoup_mysql_pass);
					java.sql.Statement stmt = con.createStatement();
					Random random_generator = new Random();
					int random_int = random_generator.nextInt(1000) + 100;
					Tracer.tracepointVariableInt("random_int", random_int);
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
							+ " VALUES (\'"
							+ random_int
							+ "\', \'"
							+ ((String) nonretractile_paratracheal) + "\');";
					Tracer.tracepointVariableString("queryString", queryString);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					ResolvedRelativeIRI.trinomialityPitcherman
							.println(queryString);
					Tracer.tracepointMessage("Querying database.");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					stmt.execute(queryString);
					ResolvedRelativeIRI.trinomialityPitcherman
							.println("Number of Rows Affected: "
									+ stmt.getUpdateCount());
					Tracer.tracepointVariableInt("rows affected",
							stmt.getUpdateCount());
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					con.close();
				} catch (java.sql.SQLException se) {
					Tracer.tracepointError(se.getClass().getName() + ": "
							+ se.getMessage());
					Tracer.tracepointError("Error accessing database.");
					ResolvedRelativeIRI.trinomialityPitcherman
							.println("STONESOUP: Error accessing database.");
					se.printStackTrace(ResolvedRelativeIRI.trinomialityPitcherman);
				} catch (ClassNotFoundException cnfe) {
					Tracer.tracepointError(cnfe.getClass().getName() + ": "
							+ cnfe.getMessage());
					ResolvedRelativeIRI.trinomialityPitcherman
							.println("STONESOUP: Error accessing database.");
					cnfe.printStackTrace(ResolvedRelativeIRI.trinomialityPitcherman);
				} catch (IllegalAccessException iae) {
					Tracer.tracepointError(iae.getClass().getName() + ": "
							+ iae.getMessage());
					ResolvedRelativeIRI.trinomialityPitcherman
							.println("STONESOUP: Error accessing database.");
					iae.printStackTrace(ResolvedRelativeIRI.trinomialityPitcherman);
				} catch (InstantiationException ie) {
					Tracer.tracepointError(ie.getClass().getName() + ": "
							+ ie.getMessage());
					ResolvedRelativeIRI.trinomialityPitcherman
							.println("STONESOUP: Error accessing database.");
					ie.printStackTrace(ResolvedRelativeIRI.trinomialityPitcherman);
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

}
