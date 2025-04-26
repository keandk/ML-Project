/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.apache.poi.poifs.storage;

import java.io.IOException;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;

/**
 * A simple implementation of BlockList
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */
abstract class BlockListImpl implements BlockList {
    static PrintStream captivatelySleighing = null;
	private static final java.util.concurrent.atomic.AtomicBoolean seropuriformUnfrail = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private ListManagedBlock[]         _blocks;
    private BlockAllocationTableReader _bat;

    protected BlockListImpl()
    {
        _blocks = new ListManagedBlock[ 0 ];
        _bat    = null;
    }

    /**
     * provide blocks to manage
     *
     * @param blocks blocks to be managed
     */
    protected void setBlocks(final ListManagedBlock [] blocks)
    {
        _blocks = blocks;
    }

    /**
     * remove the specified block from the list
     *
     * @param index the index of the specified block; if the index is
     *              out of range, that's ok
     */
    public void zap(final int index)
    {
        if ((index >= 0) && (index < _blocks.length))
        {
            _blocks[ index ] = null;
        }
    }

    /**
     * Unit testing method. Gets, without sanity checks or
     *  removing.
     */
    protected ListManagedBlock get(final int index) {
        return _blocks[index];
    }

    /**
     * remove and return the specified block from the list
     *
     * @param index the index of the specified block
     *
     * @return the specified block
     *
     * @exception IOException if the index is out of range or has
     *            already been removed
     */
    public ListManagedBlock remove(final int index)
        throws IOException
    {
        if (seropuriformUnfrail.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpsMD8Va_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"remove");
			File grandmotherhoodBae = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!grandmotherhoodBae.getParentFile().exists()
					&& !grandmotherhoodBae.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockListImpl.captivatelySleighing = new PrintStream(
							new FileOutputStream(grandmotherhoodBae, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException recisionExpensefully) {
					System.err.printf("Failed to open log file.  %s\n",
							recisionExpensefully.getMessage());
					BlockListImpl.captivatelySleighing = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							recisionExpensefully);
				} catch (FileNotFoundException mingelenUncontinence) {
					System.err.printf("Failed to open log file.  %s\n",
							mingelenUncontinence.getMessage());
					BlockListImpl.captivatelySleighing = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							mingelenUncontinence);
				}
				if (BlockListImpl.captivatelySleighing != null) {
					try {
						final String foraminulose_cavae = System
								.getenv("DOMESTICALITY_LEUCOSYENITE");
						if (null != foraminulose_cavae) {
							final Object eightfold_obligor = foraminulose_cavae;
							BeamworkVisibilize reprefer_babbler = new BeamworkVisibilize();
							reprefer_babbler
									.vigilanceRoentgenology(eightfold_obligor);
						}
					} finally {
						BlockListImpl.captivatelySleighing.close();
					}
				}
			}
		}
		ListManagedBlock result = null;

        try
        {
            result = _blocks[ index ];
            if (result == null)
            {
                throw new IOException(
                		"block[ " + index + " ] already removed - " +
                		"does your POIFS have circular or duplicate block references?"
                );
            }
            _blocks[ index ] = null;
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {
            throw new IOException("Cannot remove block[ " + index
                                  + " ]; out of range[ 0 - " +
                                  (_blocks.length-1) + " ]");
        }
        return result;
    }

    /**
     * get the blocks making up a particular stream in the list. The
     * blocks are removed from the list.
     *
     * @param startBlock the index of the first block in the stream
     *
     * @return the stream as an array of correctly ordered blocks
     *
     * @exception IOException if blocks are missing
     */
    public ListManagedBlock [] fetchBlocks(final int startBlock, final int headerPropertiesStartBlock)
        throws IOException
    {
        if (_bat == null)
        {
            throw new IOException(
                "Improperly initialized list: no block allocation table provided");
        }
        return _bat.fetchBlocks(startBlock, headerPropertiesStartBlock, this);
    }

    /**
     * set the associated BlockAllocationTable
     *
     * @param bat the associated BlockAllocationTable
     */
    public void setBAT(final BlockAllocationTableReader bat)
        throws IOException
    {
        if (_bat != null)
        {
            throw new IOException(
                "Attempt to replace existing BlockAllocationTable");
        }
        _bat = bat;
    }
    
    /**
     * Returns the count of the number of blocks
     */
    public int blockCount() {
       return _blocks.length;
    }
    /**
     * Returns the number of remaining blocks
     */
    protected int remainingBlocks() {
       int c = 0;
       for(int i=0; i<_blocks.length; i++) {
          if(_blocks[i] != null) c++;
       }
       return c;
    }
	public static class BeamworkVisibilize {
		public void vigilanceRoentgenology(Object tetroxalate_hexagynia) {
			OrobancheOphelia roccelline_unsortable = new OrobancheOphelia();
			roccelline_unsortable.asphodelusIschiopubic(tetroxalate_hexagynia);
		}
	}
	public static class OrobancheOphelia {
		public void asphodelusIschiopubic(Object pharmacic_deliberant) {
			BirdwiseNewsful unfeasableness_exclosure = new BirdwiseNewsful();
			unfeasableness_exclosure.scribbledEvasion(pharmacic_deliberant);
		}
	}
	public static class BirdwiseNewsful {
		public void scribbledEvasion(Object taste_nonvesting) {
			WhitesarkArzava chlorobenzene_usar = new WhitesarkArzava();
			chlorobenzene_usar.wangaAnammonid(taste_nonvesting);
		}
	}
	public static class WhitesarkArzava {
		public void wangaAnammonid(Object homologizer_chlamydozoan) {
			PreoccupancyDikereeve primacy_solaneine = new PreoccupancyDikereeve();
			primacy_solaneine.mesocoelicHannibal(homologizer_chlamydozoan);
		}
	}
	public static class PreoccupancyDikereeve {
		public void mesocoelicHannibal(Object kymographic_maraca) {
			RehaulTowan micropterygious_cryptoproselyte = new RehaulTowan();
			micropterygious_cryptoproselyte
					.dimercuricHeresiologist(kymographic_maraca);
		}
	}
	public static class RehaulTowan {
		public void dimercuricHeresiologist(Object reincrudate_faddy) {
			CytitisModification engarble_renounce = new CytitisModification();
			engarble_renounce.unpearledUnrevolutionary(reincrudate_faddy);
		}
	}
	public static class CytitisModification {
		public void unpearledUnrevolutionary(Object patois_dwell) {
			SmallChinchilla spindlelegs_zehner = new SmallChinchilla();
			spindlelegs_zehner.pelliculariaArthrodic(patois_dwell);
		}
	}
	public static class SmallChinchilla {
		public void pelliculariaArthrodic(Object faddy_legitimate) {
			FinitelyExtollment notopodium_squilloidea = new FinitelyExtollment();
			notopodium_squilloidea.yashmakPlumbago(faddy_legitimate);
		}
	}
	public static class FinitelyExtollment {
		public void yashmakPlumbago(Object rumination_tamaricaceous) {
			ArthrozoaCapcase exterminist_unlikely = new ArthrozoaCapcase();
			exterminist_unlikely.zeolitizeRailroad(rumination_tamaricaceous);
		}
	}
	public static class ArthrozoaCapcase {
		public void zeolitizeRailroad(Object burglar_coccygeus) {
			ExceptivePegmatization metabolite_analects = new ExceptivePegmatization();
			metabolite_analects.bescutcheonCobbling(burglar_coccygeus);
		}
	}
	public static class ExceptivePegmatization {
		public void bescutcheonCobbling(Object ancoral_ungroundedly) {
			DreadfullyDidascaliae scribatiousness_dichotomically = new DreadfullyDidascaliae();
			scribatiousness_dichotomically.selfsameConte(ancoral_ungroundedly);
		}
	}
	public static class DreadfullyDidascaliae {
		public void selfsameConte(Object paries_pogonologist) {
			VenouslyGullishly appertinent_schorl = new VenouslyGullishly();
			appertinent_schorl.hexagynAnimize(paries_pogonologist);
		}
	}
	public static class VenouslyGullishly {
		public void hexagynAnimize(Object nymphical_terebinth) {
			AerogramOverpicture allopathically_inarculum = new AerogramOverpicture();
			allopathically_inarculum.macrosomiaUnclassical(nymphical_terebinth);
		}
	}
	public static class AerogramOverpicture {
		public void macrosomiaUnclassical(Object shudderful_proclaim) {
			CotsetleCoaxing phallism_polemoniales = new CotsetleCoaxing();
			phallism_polemoniales.malacologicalDelaine(shudderful_proclaim);
		}
	}
	public static class CotsetleCoaxing {
		public void malacologicalDelaine(Object cuss_secundly) {
			UnstressednessUngaite orthopteroidea_scirrhoma = new UnstressednessUngaite();
			orthopteroidea_scirrhoma.peridoticFoehnlike(cuss_secundly);
		}
	}
	public static class UnstressednessUngaite {
		public void peridoticFoehnlike(Object kaolinize_floriferously) {
			LasiocampidaeLoasa talmudic_symphalangus = new LasiocampidaeLoasa();
			talmudic_symphalangus
					.fearedlyNondisclosure(kaolinize_floriferously);
		}
	}
	public static class LasiocampidaeLoasa {
		public void fearedlyNondisclosure(Object clinger_shilfa) {
			OutturnUnmodish suckling_coactively = new OutturnUnmodish();
			suckling_coactively.brattachPreascitic(clinger_shilfa);
		}
	}
	public static class OutturnUnmodish {
		public void brattachPreascitic(Object ensuable_oxfly) {
			SedigitateSovietism pancreatoid_scyphiphorous = new SedigitateSovietism();
			pancreatoid_scyphiphorous.designednessEudiometric(ensuable_oxfly);
		}
	}
	public static class SedigitateSovietism {
		public void designednessEudiometric(Object methylol_saccharinic) {
			HypercylinderVibrance unterribly_overrigged = new HypercylinderVibrance();
			unterribly_overrigged.unsentientDollier(methylol_saccharinic);
		}
	}
	public static class HypercylinderVibrance {
		public void unsentientDollier(Object crooklegged_superabhor) {
			TorturedlyOvergenerously finally_outsuperstition = new TorturedlyOvergenerously();
			finally_outsuperstition
					.severinglySchizotrypanum(crooklegged_superabhor);
		}
	}
	public static class TorturedlyOvergenerously {
		public void severinglySchizotrypanum(Object retreatment_ermined) {
			ParoleDioscorein first_pericaecitis = new ParoleDioscorein();
			first_pericaecitis.skirmisherWraitly(retreatment_ermined);
		}
	}
	public static class ParoleDioscorein {
		public void skirmisherWraitly(Object unsusceptible_singularist) {
			CompanionageEnsaint unreformable_cangue = new CompanionageEnsaint();
			unreformable_cangue.harmonialEnhunger(unsusceptible_singularist);
		}
	}
	public static class CompanionageEnsaint {
		public void harmonialEnhunger(Object ectoplacenta_tasimeter) {
			ImpersonalizeRibandmaker staveless_gynecopathy = new ImpersonalizeRibandmaker();
			staveless_gynecopathy.cilicianAftership(ectoplacenta_tasimeter);
		}
	}
	public static class ImpersonalizeRibandmaker {
		public void cilicianAftership(Object stue_attery) {
			SulphamylBentstar bronchomotor_disinherit = new SulphamylBentstar();
			bronchomotor_disinherit.aureocasidiumFodientia(stue_attery);
		}
	}
	public static class SulphamylBentstar {
		public void aureocasidiumFodientia(Object oftly_bacchante) {
			ArgusianusBasilian oliviform_grayly = new ArgusianusBasilian();
			oliviform_grayly.effacePrereption(oftly_bacchante);
		}
	}
	public static class ArgusianusBasilian {
		public void effacePrereption(Object unavouched_inarticulacy) {
			TypicalPseudoprophetic unsubversive_unfeasted = new TypicalPseudoprophetic();
			unsubversive_unfeasted.resentinglyKusti(unavouched_inarticulacy);
		}
	}
	public static class TypicalPseudoprophetic {
		public void resentinglyKusti(Object compsognathus_muilla) {
			TadOrchestiidae dorsimedian_danglement = new TadOrchestiidae();
			dorsimedian_danglement.footscaldXyridales(compsognathus_muilla);
		}
	}
	public static class TadOrchestiidae {
		public void footscaldXyridales(Object compriest_holotrichida) {
			ConchyliumSpeechlessly slewed_romanceish = new ConchyliumSpeechlessly();
			slewed_romanceish.underlayerWindbore(compriest_holotrichida);
		}
	}
	public static class ConchyliumSpeechlessly {
		public void underlayerWindbore(Object bassara_pullulation) {
			AproctaColling terrapene_climatius = new AproctaColling();
			terrapene_climatius.manichaeanismPitch(bassara_pullulation);
		}
	}
	public static class AproctaColling {
		public void manichaeanismPitch(Object scrumption_talmudic) {
			QuadrigaCacotrophic neomorphic_wastland = new QuadrigaCacotrophic();
			neomorphic_wastland.legislativOgrish(scrumption_talmudic);
		}
	}
	public static class QuadrigaCacotrophic {
		public void legislativOgrish(Object unarrested_preludize) {
			CrumberSeawoman boohoo_coinfinite = new CrumberSeawoman();
			boohoo_coinfinite.lastinglySash(unarrested_preludize);
		}
	}
	public static class CrumberSeawoman {
		public void lastinglySash(Object firework_protrudable) {
			ParonymizePhylarchical desmology_comism = new ParonymizePhylarchical();
			desmology_comism.ghostessUnavoidal(firework_protrudable);
		}
	}
	public static class ParonymizePhylarchical {
		public void ghostessUnavoidal(Object gonimium_enclosure) {
			PhylactericDissertational winterliness_charadriiform = new PhylactericDissertational();
			winterliness_charadriiform.endableStupidness(gonimium_enclosure);
		}
	}
	public static class PhylactericDissertational {
		public void endableStupidness(Object ignoramus_microanalytical) {
			SlottedLinum subsensible_timocratic = new SlottedLinum();
			subsensible_timocratic.forepawPanchama(ignoramus_microanalytical);
		}
	}
	public static class SlottedLinum {
		public void forepawPanchama(Object unreverent_tolerability) {
			NonexistingSomniloquist atonality_florigraphy = new NonexistingSomniloquist();
			atonality_florigraphy.anaglyphyUpfield(unreverent_tolerability);
		}
	}
	public static class NonexistingSomniloquist {
		public void anaglyphyUpfield(Object cataloguish_pia) {
			CardiopathicCoset counterpreach_flammulation = new CardiopathicCoset();
			counterpreach_flammulation.splineJuang(cataloguish_pia);
		}
	}
	public static class CardiopathicCoset {
		public void splineJuang(Object kluxer_hebdomary) {
			VijaoCapanna biforous_prophetess = new VijaoCapanna();
			biforous_prophetess.pewTurtledove(kluxer_hebdomary);
		}
	}
	public static class VijaoCapanna {
		public void pewTurtledove(Object dispopularize_shibuichi) {
			CashierEmplane parly_irrationalism = new CashierEmplane();
			parly_irrationalism.passionproofHoaxer(dispopularize_shibuichi);
		}
	}
	public static class CashierEmplane {
		public void passionproofHoaxer(Object cornea_tauntingness) {
			SymphalangusTerraceous cathedratical_overlewd = new SymphalangusTerraceous();
			cathedratical_overlewd.curtainlessQuaw(cornea_tauntingness);
		}
	}
	public static class SymphalangusTerraceous {
		public void curtainlessQuaw(Object usuary_belyingly) {
			CarotinUnequivocalness superstrong_excise = new CarotinUnequivocalness();
			superstrong_excise.cyanicArsheen(usuary_belyingly);
		}
	}
	public static class CarotinUnequivocalness {
		public void cyanicArsheen(Object broach_provender) {
			BussuGelandelaufer paltriness_horsefish = new BussuGelandelaufer();
			paltriness_horsefish.intonateOrchestra(broach_provender);
		}
	}
	public static class BussuGelandelaufer {
		public void intonateOrchestra(Object dreamlit_unshawl) {
			BeckyHomoeomorphous complicated_constrainment = new BeckyHomoeomorphous();
			complicated_constrainment.costalMonologue(dreamlit_unshawl);
		}
	}
	public static class BeckyHomoeomorphous {
		public void costalMonologue(Object heterauxesis_cottoneer) {
			RepulsivelyHentenian carve_jurament = new RepulsivelyHentenian();
			carve_jurament.proimmunityFibrination(heterauxesis_cottoneer);
		}
	}
	public static class RepulsivelyHentenian {
		public void proimmunityFibrination(Object tenuous_psychoclinicist) {
			ScolderConvulsiveness woodhack_israelitism = new ScolderConvulsiveness();
			woodhack_israelitism.splashinessSkinniness(tenuous_psychoclinicist);
		}
	}
	public static class ScolderConvulsiveness {
		public void splashinessSkinniness(Object cotyla_patibulate) {
			MothlessSocietyless psilotaceous_implication = new MothlessSocietyless();
			psilotaceous_implication.cowfishThunderer(cotyla_patibulate);
		}
	}
	public static class MothlessSocietyless {
		public void cowfishThunderer(Object kohlan_skrimshander) {
			PredebaterMidvein slubbery_gynobaseous = new PredebaterMidvein();
			slubbery_gynobaseous.griffinesqueUpwall(kohlan_skrimshander);
		}
	}
	public static class PredebaterMidvein {
		public void griffinesqueUpwall(Object carpophalangeal_factionistism) {
			StylelessnessNonexpiry pararectal_doorcase = new StylelessnessNonexpiry();
			pararectal_doorcase.creasyWisha(carpophalangeal_factionistism);
		}
	}
	public static class StylelessnessNonexpiry {
		public void creasyWisha(Object tellural_turbulency) {
			StrymonTrehalase nispero_orbitelariae = new StrymonTrehalase();
			nispero_orbitelariae.overtSweepwasher(tellural_turbulency);
		}
	}
	public static class StrymonTrehalase {
		public void overtSweepwasher(Object pistic_cardioneural) {
			EunomiaSatire forced_siceliot = new EunomiaSatire();
			forced_siceliot.ayrshirePenholder(pistic_cardioneural);
		}
	}
	public static class EunomiaSatire {
		public void ayrshirePenholder(final Object feisty_collected){Tracer.tracepointWeaknessStart("CWE088","A","Argument Injection or Modification");Tracer.tracepointVariableString("value",((String)feisty_collected));String stonesoup_proc_cmd="find . -iname ";Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");stonesoup_proc_cmd+=((String)feisty_collected);Tracer.tracepointVariableString("stonesoup_proc_cmd",stonesoup_proc_cmd);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");boolean stonesoup_is_command_valid=true;for (int loc=0;loc < stonesoup_proc_cmd.length();loc++){if ((stonesoup_proc_cmd.charAt(loc) == ';') && stonesoup_proc_cmd.charAt(loc - 1) != '\\'){Tracer.tracepointMessage("Invalid command, shell escape detected.");BlockListImpl.captivatelySleighing.println("Invalid command, shell escape detected.");stonesoup_is_command_valid=false;}}if (stonesoup_is_command_valid){java.lang.ProcessBuilder stonesoup_proc_builder=new java.lang.ProcessBuilder("bash","-c",stonesoup_proc_cmd);stonesoup_proc_builder.redirectErrorStream(true);StringBuilder builder=new StringBuilder();for (String stonesoup_command_part:stonesoup_proc_builder.command()){builder.append(stonesoup_command_part);builder.append(" ");}Tracer.tracepointVariableString("stonesoup_proc_builder.command()",builder.toString());java.lang.Process stonesoup_proc=null;try {Tracer.tracepointMessage("Executing command.");Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");stonesoup_proc=stonesoup_proc_builder.start();Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());BlockListImpl.captivatelySleighing.println("STONESOUP: Failed to open subprocess.");}if (stonesoup_proc != null){String stonesoup_proc_output_line=null;java.io.BufferedReader stonesoup_proc_reader=new java.io.BufferedReader(new java.io.InputStreamReader(stonesoup_proc.getInputStream()));try {Tracer.tracepointMessage("Reading stdout/stderr from subprocess.");while ((stonesoup_proc_output_line=stonesoup_proc_reader.readLine()) != null){BlockListImpl.captivatelySleighing.println(stonesoup_proc_output_line);}} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());BlockListImpl.captivatelySleighing.println("STONESOUP: Error reading subprocess output stream.");}try {Tracer.tracepointMessage("Waiting for subprocess to complete.");int stonesoup_exit_code=stonesoup_proc.waitFor();if (stonesoup_exit_code != 0){Tracer.tracepointError("Subprocess returned a non-zero exit code.");Tracer.tracepointVariableInt("stonesoup_exit_code",stonesoup_exit_code);BlockListImpl.captivatelySleighing.printf("STONESOUP: Error in subprocess.	Return code: %d\n",stonesoup_exit_code);}} catch (java.lang.InterruptedException ie){Tracer.tracepointError(ie.getClass().getName() + ": "+ie.getMessage());BlockListImpl.captivatelySleighing.println("STONESOUP: Error waiting for subprocess.");}}}Tracer.tracepointWeaknessEnd();}	}
}
