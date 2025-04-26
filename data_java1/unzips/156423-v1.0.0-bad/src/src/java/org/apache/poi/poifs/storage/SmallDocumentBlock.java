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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.poifs.common.POIFSBigBlockSize;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;

/**
 * Storage for documents that are too small to use regular
 * DocumentBlocks for their data
 *
 * @author  Marc Johnson (mjohnson at apache dot org)
 */
public final class SmallDocumentBlock implements BlockWritable, ListManagedBlock {
    static PrintStream milkhouseUndulating = null;

	private static final java.util.concurrent.atomic.AtomicBoolean toillessnessSchoolless = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	private static final int BLOCK_SHIFT = 6;

    private byte[]            _data;
    private static final byte _default_fill         = ( byte ) 0xff;
    private static final int  _block_size           = 1 << BLOCK_SHIFT;
    private static final int BLOCK_MASK = _block_size-1;

    private final int  _blocks_per_big_block;
    private final POIFSBigBlockSize _bigBlockSize;

    private SmallDocumentBlock(final POIFSBigBlockSize bigBlockSize, final byte [] data, final int index)
    {
        this(bigBlockSize);
        if (toillessnessSchoolless.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpq2QOhl_ss_testcase/src/src/java/org/apache/poi/poifs/storage/SmallDocumentBlock.java",
					"SmallDocumentBlock");
			File uninquiredBenzophosphinic = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!uninquiredBenzophosphinic.getParentFile().exists()
					&& !uninquiredBenzophosphinic.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					SmallDocumentBlock.milkhouseUndulating = new PrintStream(
							new FileOutputStream(uninquiredBenzophosphinic,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException spadroneNonelasticity) {
					System.err.printf("Failed to open log file.  %s\n",
							spadroneNonelasticity.getMessage());
					SmallDocumentBlock.milkhouseUndulating = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							spadroneNonelasticity);
				} catch (FileNotFoundException comicographyExquisite) {
					System.err.printf("Failed to open log file.  %s\n",
							comicographyExquisite.getMessage());
					SmallDocumentBlock.milkhouseUndulating = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							comicographyExquisite);
				}
				if (SmallDocumentBlock.milkhouseUndulating != null) {
					try {
						String desperadoism_dreg = System
								.getenv("VOLUMINOUSLY_ERASE");
						if (null != desperadoism_dreg) {
							Object outwindow_boussingaultite = desperadoism_dreg;
							RehungOvercaptiously semisilica_gaufrette = new RehungOvercaptiously();
							semisilica_gaufrette
									.demisecondAmorphophyte(outwindow_boussingaultite);
						}
					} finally {
						SmallDocumentBlock.milkhouseUndulating.close();
					}
				}
			}
		}
		System.arraycopy(data, index * _block_size, _data, 0, _block_size);
    }

    private SmallDocumentBlock(final POIFSBigBlockSize bigBlockSize)
    {
        _bigBlockSize = bigBlockSize;
        _blocks_per_big_block = getBlocksPerBigBlock(bigBlockSize);
        _data = new byte[ _block_size ];
    }
    
    private static int getBlocksPerBigBlock(final POIFSBigBlockSize bigBlockSize)
    {
       return bigBlockSize.getBigBlockSize() / _block_size;
    }

    /**
     * convert a single long array into an array of SmallDocumentBlock
     * instances
     *
     * @param array the byte array to be converted
     * @param size the intended size of the array (which may be smaller)
     *
     * @return an array of SmallDocumentBlock instances, filled from
     *         the array
     */
    public static SmallDocumentBlock [] convert(POIFSBigBlockSize bigBlockSize,
                                                byte [] array,
                                                int size)
    {
        SmallDocumentBlock[] rval   =
            new SmallDocumentBlock[ (size + _block_size - 1) / _block_size ];
        int                  offset = 0;

        for (int k = 0; k < rval.length; k++)
        {
            rval[ k ] = new SmallDocumentBlock(bigBlockSize);
            if (offset < array.length)
            {
                int length = Math.min(_block_size, array.length - offset);

                System.arraycopy(array, offset, rval[ k ]._data, 0, length);
                if (length != _block_size)
                {
                    Arrays.fill(rval[ k ]._data, length, _block_size,
                                _default_fill);
                }
            }
            else
            {
                Arrays.fill(rval[ k ]._data, _default_fill);
            }
            offset += _block_size;
        }
        return rval;
    }

    /**
     * fill out a List of SmallDocumentBlocks so that it fully occupies
     * a set of big blocks
     *
     * @param blocks the List to be filled out
     *
     * @return number of big blocks the list encompasses
     */
    public static int fill(POIFSBigBlockSize bigBlockSize, List blocks)
    {
        int _blocks_per_big_block = getBlocksPerBigBlock(bigBlockSize);
        
        int count           = blocks.size();
        int big_block_count = (count + _blocks_per_big_block - 1)
                              / _blocks_per_big_block;
        int full_count      = big_block_count * _blocks_per_big_block;

        for (; count < full_count; count++)
        {
            blocks.add(makeEmptySmallDocumentBlock(bigBlockSize));
        }
        return big_block_count;
    }

    /**
     * Factory for creating SmallDocumentBlocks from DocumentBlocks
     *
     * @param store the original DocumentBlocks
     * @param size the total document size
     *
     * @return an array of new SmallDocumentBlocks instances
     *
     * @exception IOException on errors reading from the DocumentBlocks
     * @exception ArrayIndexOutOfBoundsException if, somehow, the store
     *            contains less data than size indicates
     */
    public static SmallDocumentBlock [] convert(POIFSBigBlockSize bigBlockSize,
                                                BlockWritable [] store,
                                                int size)
        throws IOException, ArrayIndexOutOfBoundsException
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        for (int j = 0; j < store.length; j++)
        {
            store[ j ].writeBlocks(stream);
        }
        byte[]               data = stream.toByteArray();
        SmallDocumentBlock[] rval =
            new SmallDocumentBlock[ convertToBlockCount(size) ];

        for (int index = 0; index < rval.length; index++)
        {
            rval[ index ] = new SmallDocumentBlock(bigBlockSize, data, index);
        }
        return rval;
    }

    /**
     * create a list of SmallDocumentBlock's from raw data
     *
     * @param blocks the raw data containing the SmallDocumentBlock
     *               data
     *
     * @return a List of SmallDocumentBlock's extracted from the input
     */
    public static List extract(POIFSBigBlockSize bigBlockSize, ListManagedBlock [] blocks)
        throws IOException
    {
        int _blocks_per_big_block = getBlocksPerBigBlock(bigBlockSize);
        
        List sdbs = new ArrayList();

        for (int j = 0; j < blocks.length; j++)
        {
            byte[] data = blocks[ j ].getData();

            for (int k = 0; k < _blocks_per_big_block; k++)
            {
                sdbs.add(new SmallDocumentBlock(bigBlockSize, data, k));
            }
        }
        return sdbs;
    }

    public static DataInputBlock getDataInputBlock(SmallDocumentBlock[] blocks, int offset) {
        int firstBlockIndex = offset >> BLOCK_SHIFT;
        int firstBlockOffset= offset & BLOCK_MASK;
        return new DataInputBlock(blocks[firstBlockIndex]._data, firstBlockOffset);
    }

    /**
     * Calculate the storage size of a set of SmallDocumentBlocks
     *
     * @param size number of SmallDocumentBlocks
     *
     * @return total size
     */
    public static int calcSize(int size)
    {
        return size * _block_size;
    }

    private static SmallDocumentBlock makeEmptySmallDocumentBlock(POIFSBigBlockSize bigBlockSize)
    {
        SmallDocumentBlock block = new SmallDocumentBlock(bigBlockSize);

        Arrays.fill(block._data, _default_fill);
        return block;
    }

    private static int convertToBlockCount(int size)
    {
        return (size + _block_size - 1) / _block_size;
    }

    /**
     * Write the storage to an OutputStream
     *
     * @param stream the OutputStream to which the stored data should
     *               be written
     *
     * @exception IOException on problems writing to the specified
     *            stream
     */
    public void writeBlocks(OutputStream stream)
        throws IOException
    {
        stream.write(_data);
    }

    /**
     * Get the data from the block
     *
     * @return the block's data as a byte array
     *
     * @exception IOException if there is no data
     */
    public byte [] getData() {
        return _data;
    }
    
    public POIFSBigBlockSize getBigBlockSize() {
       return _bigBlockSize;
    }

	public static class RehungOvercaptiously {
		public void demisecondAmorphophyte(Object spermatocele_repitch) {
			AfteringsGrouchily objectively_cystigerous = new AfteringsGrouchily();
			objectively_cystigerous.scurfilyCocksureism(spermatocele_repitch);
		}
	}

	public static class AfteringsGrouchily {
		public void scurfilyCocksureism(Object transiency_pseudosocial) {
			StubbleTeliospore abiogenous_prologize = new StubbleTeliospore();
			abiogenous_prologize
					.cathartidaeUnbusinesslike(transiency_pseudosocial);
		}
	}

	public static class StubbleTeliospore {
		public void cathartidaeUnbusinesslike(Object overspeak_guglia) {
			UngivingLamentation confustication_analects = new UngivingLamentation();
			confustication_analects.polissoirVenesection(overspeak_guglia);
		}
	}

	public static class UngivingLamentation {
		public void polissoirVenesection(Object kelectome_arbusterol) {
			SlishExocardia gekkonid_nonretraction = new SlishExocardia();
			gekkonid_nonretraction
					.splatterfacedPseudoanemic(kelectome_arbusterol);
		}
	}

	public static class SlishExocardia {
		public void splatterfacedPseudoanemic(Object underlain_whortle) {
			UpstageGhafir misunderstood_muskroot = new UpstageGhafir();
			misunderstood_muskroot.striariaceaeNullibicity(underlain_whortle);
		}
	}

	public static class UpstageGhafir {
		public void striariaceaeNullibicity(Object shearmouse_fossillike) {
			SpermologistBreathe critical_obtruncator = new SpermologistBreathe();
			critical_obtruncator
					.psychoautomaticDeadheadism(shearmouse_fossillike);
		}
	}

	public static class SpermologistBreathe {
		public void psychoautomaticDeadheadism(Object nuba_malthusianism) {
			PetiolatedToper graphiter_snakiness = new PetiolatedToper();
			graphiter_snakiness.gelatinobromideFernlike(nuba_malthusianism);
		}
	}

	public static class PetiolatedToper {
		public void gelatinobromideFernlike(Object costocoracoid_climatologist) {
			SlumminessChopin prefavorite_littermate = new SlumminessChopin();
			prefavorite_littermate
					.trufflesqueTricosyl(costocoracoid_climatologist);
		}
	}

	public static class SlumminessChopin {
		public void trufflesqueTricosyl(Object doon_afterdays) {
			CaloolAggregateness trinoctial_conterminously = new CaloolAggregateness();
			trinoctial_conterminously
					.commissionaireDingledangle(doon_afterdays);
		}
	}

	public static class CaloolAggregateness {
		public void commissionaireDingledangle(Object tarantulary_keilhauite) {
			FootstoneIxodidae undriven_industrialize = new FootstoneIxodidae();
			undriven_industrialize
					.boussingaultiteDissyllabize(tarantulary_keilhauite);
		}
	}

	public static class FootstoneIxodidae {
		public void boussingaultiteDissyllabize(Object prebetrothal_flunkyize) {
			QuaveringZechstein tachylite_overappraise = new QuaveringZechstein();
			tachylite_overappraise
					.trouserianUnravellable(prebetrothal_flunkyize);
		}
	}

	public static class QuaveringZechstein {
		public void trouserianUnravellable(Object indistinguished_boninite) {
			PseudopoeticalMesolimnion uncapturable_untagged = new PseudopoeticalMesolimnion();
			uncapturable_untagged.subclimaxMohammad(indistinguished_boninite);
		}
	}

	public static class PseudopoeticalMesolimnion {
		public void subclimaxMohammad(Object unspectacled_pharynges) {
			NurserPotamology ureosecretory_haisla = new NurserPotamology();
			ureosecretory_haisla.bifidLohan(unspectacled_pharynges);
		}
	}

	public static class NurserPotamology {
		public void bifidLohan(Object repugn_quotity) {
			PeiserIntroitus autographically_chloranemic = new PeiserIntroitus();
			autographically_chloranemic.digallateElectrocoating(repugn_quotity);
		}
	}

	public static class PeiserIntroitus {
		public void digallateElectrocoating(Object celioscopy_jeanie) {
			MagisterAdenolipoma ferryhouse_satchel = new MagisterAdenolipoma();
			ferryhouse_satchel.veratrylideneTorturedly(celioscopy_jeanie);
		}
	}

	public static class MagisterAdenolipoma {
		public void veratrylideneTorturedly(Object orthogneiss_blottesquely) {
			BeaverrootBenincasa nelumbium_bowler = new BeaverrootBenincasa();
			nelumbium_bowler
					.regratificationWearishness(orthogneiss_blottesquely);
		}
	}

	public static class BeaverrootBenincasa {
		public void regratificationWearishness(Object macaw_gerald) {
			ScrappleExpurge archdiocesan_truthteller = new ScrappleExpurge();
			archdiocesan_truthteller.fatherlessEranthemum(macaw_gerald);
		}
	}

	public static class ScrappleExpurge {
		public void fatherlessEranthemum(Object mazopathia_crypt) {
			BacillicidicLesche tosher_zaptoeca = new BacillicidicLesche();
			tosher_zaptoeca.deafnessExclosure(mazopathia_crypt);
		}
	}

	public static class BacillicidicLesche {
		public void deafnessExclosure(Object mystacocete_internuncioship) {
			OutbearingChapatty unhearable_submissiveness = new OutbearingChapatty();
			unhearable_submissiveness
					.moonishPamperedness(mystacocete_internuncioship);
		}
	}

	public static class OutbearingChapatty {
		public void moonishPamperedness(Object myzodendraceae_cofferlike) {
			DemitoiletStandee picot_relick = new DemitoiletStandee();
			picot_relick.dissuasionIntrafascicular(myzodendraceae_cofferlike);
		}
	}

	public static class DemitoiletStandee {
		public void dissuasionIntrafascicular(Object unpark_inlet) {
			WrinkleReeming balachong_albright = new WrinkleReeming();
			balachong_albright.lithoclasticPhilofelist(unpark_inlet);
		}
	}

	public static class WrinkleReeming {
		public void lithoclasticPhilofelist(Object plumist_palfreyed) {
			MatmakingSubversivism erythrophage_impersonalize = new MatmakingSubversivism();
			erythrophage_impersonalize.affeirMuseful(plumist_palfreyed);
		}
	}

	public static class MatmakingSubversivism {
		public void affeirMuseful(Object amniocentesis_protocolary) {
			MetaconalPistolgram slavian_outmate = new MetaconalPistolgram();
			slavian_outmate.underaimDerogatively(amniocentesis_protocolary);
		}
	}

	public static class MetaconalPistolgram {
		public void underaimDerogatively(Object chalicosis_reincorporation) {
			TranseptTympanichord prechoose_underearth = new TranseptTympanichord();
			prechoose_underearth
					.prenavalAnthomedusan(chalicosis_reincorporation);
		}
	}

	public static class TranseptTympanichord {
		public void prenavalAnthomedusan(Object moost_endomixis) {
			SupermaxillaryNannyberry rebroadcast_crooningly = new SupermaxillaryNannyberry();
			rebroadcast_crooningly.pompadourCotyledonary(moost_endomixis);
		}
	}

	public static class SupermaxillaryNannyberry {
		public void pompadourCotyledonary(Object griddlecake_shallowist) {
			UrinariumAntitragal synchronize_zingel = new UrinariumAntitragal();
			synchronize_zingel.casklikePredriver(griddlecake_shallowist);
		}
	}

	public static class UrinariumAntitragal {
		public void casklikePredriver(Object perla_quotationally) {
			MercuryQueller glucosid_hyperapophysis = new MercuryQueller();
			glucosid_hyperapophysis
					.historiographicPersonification(perla_quotationally);
		}
	}

	public static class MercuryQueller {
		public void historiographicPersonification(
				Object impignorate_parcenership) {
			RetrothyroidSet bipont_blastulae = new RetrothyroidSet();
			bipont_blastulae.laconizeEngrandizement(impignorate_parcenership);
		}
	}

	public static class RetrothyroidSet {
		public void laconizeEngrandizement(Object dromiacea_dihydrocupreine) {
			TyphlopexyPeucedanum butyrone_noncommunistic = new TyphlopexyPeucedanum();
			butyrone_noncommunistic
					.beastishnessInarticulacy(dromiacea_dihydrocupreine);
		}
	}

	public static class TyphlopexyPeucedanum {
		public void beastishnessInarticulacy(Object pistonhead_pudginess) {
			SphingidaeSemishirker czechoslovak_tethery = new SphingidaeSemishirker();
			czechoslovak_tethery.flewsTwinkleproof(pistonhead_pudginess);
		}
	}

	public static class SphingidaeSemishirker {
		public void flewsTwinkleproof(Object trityl_peonism) {
			OstensivePseudocumenyl benthon_hepatogastric = new OstensivePseudocumenyl();
			benthon_hepatogastric.millcourseCatathymic(trityl_peonism);
		}
	}

	public static class OstensivePseudocumenyl {
		public void millcourseCatathymic(Object thereckly_hallucinatory) {
			PaleocrystalAccoucheur disgeneric_federator = new PaleocrystalAccoucheur();
			disgeneric_federator.myodynamicsGelechiid(thereckly_hallucinatory);
		}
	}

	public static class PaleocrystalAccoucheur {
		public void myodynamicsGelechiid(Object parcellize_provostal) {
			BetulinHale acrodactylum_doab = new BetulinHale();
			acrodactylum_doab.buddlerAstrantia(parcellize_provostal);
		}
	}

	public static class BetulinHale {
		public void buddlerAstrantia(Object unrhetorically_synchronize) {
			PhotocombustionSoniferous propertied_siphonogam = new PhotocombustionSoniferous();
			propertied_siphonogam
					.cysticarpiumShakers(unrhetorically_synchronize);
		}
	}

	public static class PhotocombustionSoniferous {
		public void cysticarpiumShakers(Object backword_haptic) {
			CardiectomizeBena nonmetropolitan_unambitious = new CardiectomizeBena();
			nonmetropolitan_unambitious.semitrainedEpural(backword_haptic);
		}
	}

	public static class CardiectomizeBena {
		public void semitrainedEpural(Object postwoman_sulphotoluic) {
			MoioGeoty burnover_clownheal = new MoioGeoty();
			burnover_clownheal.farcettaCoreid(postwoman_sulphotoluic);
		}
	}

	public static class MoioGeoty {
		public void farcettaCoreid(Object pancyclopedic_uproot) {
			TaxlessnessWanderlustful jetty_astonishingly = new TaxlessnessWanderlustful();
			jetty_astonishingly.hostageThriftily(pancyclopedic_uproot);
		}
	}

	public static class TaxlessnessWanderlustful {
		public void hostageThriftily(Object uninoculable_extranormal) {
			TartuferyCantar phonogram_cleche = new TartuferyCantar();
			phonogram_cleche.barouchetFoster(uninoculable_extranormal);
		}
	}

	public static class TartuferyCantar {
		public void barouchetFoster(Object circumjacence_antipatriarch) {
			OblationIntrovision sparver_withstand = new OblationIntrovision();
			sparver_withstand.parhomologousJeanie(circumjacence_antipatriarch);
		}
	}

	public static class OblationIntrovision {
		public void parhomologousJeanie(Object classifiable_clinodomatic) {
			NeglectorOutmove lipogenetic_basketworm = new NeglectorOutmove();
			lipogenetic_basketworm
					.assmanUnpollutedly(classifiable_clinodomatic);
		}
	}

	public static class NeglectorOutmove {
		public void assmanUnpollutedly(Object akmuddar_somniferously) {
			RealignUtricul dishwasher_allodelphite = new RealignUtricul();
			dishwasher_allodelphite
					.acromiodeltoidVisiting(akmuddar_somniferously);
		}
	}

	public static class RealignUtricul {
		public void acromiodeltoidVisiting(Object humiriaceous_overgenerally) {
			PithecanthropeMucronately poringly_cuba = new PithecanthropeMucronately();
			poringly_cuba.launchfulPremillenarian(humiriaceous_overgenerally);
		}
	}

	public static class PithecanthropeMucronately {
		public void launchfulPremillenarian(Object mensurational_caledonite) {
			ExhaustibleTasselfish embroiderer_salleeman = new ExhaustibleTasselfish();
			embroiderer_salleeman.paintingPeachery(mensurational_caledonite);
		}
	}

	public static class ExhaustibleTasselfish {
		public void paintingPeachery(Object trinity_hylist) {
			JaglaArgue maskins_reperible = new JaglaArgue();
			maskins_reperible.untotteringSermocinatrix(trinity_hylist);
		}
	}

	public static class JaglaArgue {
		public void untotteringSermocinatrix(Object hysterolith_shoreweed) {
			UnfleeingPaunched autotropic_xyridales = new UnfleeingPaunched();
			autotropic_xyridales.accidentialPlaywoman(hysterolith_shoreweed);
		}
	}

	public static class UnfleeingPaunched {
		public void accidentialPlaywoman(Object litch_aerophilatelic) {
			StallionSubaxillar sleepwalk_scotchman = new StallionSubaxillar();
			sleepwalk_scotchman.serviusKinology(litch_aerophilatelic);
		}
	}

	public static class StallionSubaxillar {
		public void serviusKinology(Object hydrotimetric_butyrous) {
			KissSurinamine phenate_nursingly = new KissSurinamine();
			phenate_nursingly.pipidaeAlphenic(hydrotimetric_butyrous);
		}
	}

	public static class KissSurinamine {
		public void pipidaeAlphenic(Object tomorn_octagon) {
			FavorablyPiety glanders_elocutionize = new FavorablyPiety();
			glanders_elocutionize.stenchionPhilogenitive(tomorn_octagon);
		}
	}

	public static class FavorablyPiety {
		public void stenchionPhilogenitive(Object substantiveness_overtediously) {
			CitrylideneGerontism digonoporous_decarbonization = new CitrylideneGerontism();
			digonoporous_decarbonization
					.enticefulInterbank(substantiveness_overtediously);
		}
	}

	public static class CitrylideneGerontism {
		public void enticefulInterbank(Object topply_teda){Tracer.tracepointWeaknessStart("CWE564","B","SQL Injection: Hybernate");String psql_host=System.getenv("DBPGHOST");String psql_user=System.getenv("DBPGUSER");String psql_pass=System.getenv("DBPGPASSWORD");String psql_port=System.getenv("DBPGPORT");String psql_dbname=System.getenv("SS_DBPGDATABASE");Tracer.tracepointVariableString("psql_host",psql_host);Tracer.tracepointVariableString("psql_user",psql_user);Tracer.tracepointVariableString("psql_pass",psql_pass);Tracer.tracepointVariableString("psql_port",psql_port);Tracer.tracepointVariableString("psql_dbname",psql_dbname);Tracer.tracepointVariableString("valueString",((String)topply_teda));if (((String)topply_teda) != null && psql_host != null && psql_user != null && psql_pass != null && psql_port != null && psql_dbname != null){try {Tracer.tracepointMessage("Setting up hibernate connection.");org.hibernate.cfg.Configuration cfg=new org.hibernate.cfg.Configuration();cfg.setProperty("hibernate.connection.url","jdbc:postgresql://" + psql_host+":"+psql_port+"/"+psql_dbname);cfg.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");cfg.setProperty("hibernate.connection.driver_class","org.postgresql.Driver");cfg.setProperty("hibernate.connection.username",psql_user);cfg.setProperty("hibernate.connection.password",psql_pass);cfg.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");cfg.setProperty("hibernate.current_session_context_class","thread");cfg.setProperty("org.hibernate.flushMode","COMMIT");cfg.setProperty("hibernate.hbm2ddl.auto","validate");cfg.setProperty("hibernate.connection.pool_size","1");cfg.addClass(SS_CWE_564_POSTGRES.Categories.class);cfg.addClass(SS_CWE_564_POSTGRES.Customercustomerdemo.class);cfg.addClass(SS_CWE_564_POSTGRES.Customerdemographics.class);cfg.addClass(SS_CWE_564_POSTGRES.Customers.class);cfg.addClass(SS_CWE_564_POSTGRES.Employees.class);cfg.addClass(SS_CWE_564_POSTGRES.Employeeterritories.class);cfg.addClass(SS_CWE_564_POSTGRES.OrderDetails.class);cfg.addClass(SS_CWE_564_POSTGRES.Orders.class);cfg.addClass(SS_CWE_564_POSTGRES.Products.class);cfg.addClass(SS_CWE_564_POSTGRES.Region.class);cfg.addClass(SS_CWE_564_POSTGRES.Shippers.class);cfg.addClass(SS_CWE_564_POSTGRES.ShippersTmp.class);cfg.addClass(SS_CWE_564_POSTGRES.Suppliers.class);cfg.addClass(SS_CWE_564_POSTGRES.Territories.class);cfg.addClass(SS_CWE_564_POSTGRES.Usstates.class);org.hibernate.SessionFactory factory=cfg.buildSessionFactory();org.hibernate.Session session=factory.openSession();Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");String hql="from SS_CWE_564_POSTGRES.Customers where country = '" + ((String)topply_teda)+"'";Tracer.tracepointVariableString("hql",hql);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");org.hibernate.Query query=session.createQuery(hql);Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");@SuppressWarnings("rawtypes") java.util.Iterator iter=query.iterate();while (iter.hasNext()){SS_CWE_564_POSTGRES.Customers c=(SS_CWE_564_POSTGRES.Customers)iter.next();SmallDocumentBlock.milkhouseUndulating.print(String.format("%10s | ",c.getCustomerId()));SmallDocumentBlock.milkhouseUndulating.print(String.format("%10s | ",c.getCompanyName()));SmallDocumentBlock.milkhouseUndulating.print(String.format("%10s | ",c.getContactName()));SmallDocumentBlock.milkhouseUndulating.print(String.format("%10s | ",c.getContactTitle()));SmallDocumentBlock.milkhouseUndulating.print(String.format("%10s | ",c.getAddress()));SmallDocumentBlock.milkhouseUndulating.print(String.format("%10s | ",c.getCity()));SmallDocumentBlock.milkhouseUndulating.print(String.format("%10s | ",c.getRegion()));SmallDocumentBlock.milkhouseUndulating.print(String.format("%10s | ",c.getPostalCode()));SmallDocumentBlock.milkhouseUndulating.print(String.format("%10s | ",c.getCountry()));SmallDocumentBlock.milkhouseUndulating.print(String.format("%10s | ",c.getPhone()));SmallDocumentBlock.milkhouseUndulating.print(String.format("%10s | ",c.getFax()));SmallDocumentBlock.milkhouseUndulating.println();}Tracer.tracepointMessage("TRIGGER-POINT: AFTER");session.flush();session.close();} catch (org.hibernate.HibernateException he){Tracer.tracepointError(he.getClass().getName() + ": "+he.getMessage());he.printStackTrace(SmallDocumentBlock.milkhouseUndulating);}catch (Exception e){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());e.printStackTrace(SmallDocumentBlock.milkhouseUndulating);}}Tracer.tracepointWeaknessEnd();}	}
}
