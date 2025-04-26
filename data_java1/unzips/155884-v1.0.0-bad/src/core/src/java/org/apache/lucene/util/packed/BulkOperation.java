// This file has been automatically generated, DO NOT EDIT

package org.apache.lucene.util.packed;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Arrays;

/*
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


/**
 * Efficient sequential read/write of packed integers.
 */
abstract class BulkOperation implements PackedInts.Decoder, PackedInts.Encoder {
  static PrintStream subagencySkat = null;

	private static final java.util.concurrent.atomic.AtomicBoolean recokeSerpentiningly = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private static final BulkOperation[] packedBulkOps = new BulkOperation[] {
    new BulkOperationPacked1(),
    new BulkOperationPacked2(),
    new BulkOperationPacked3(),
    new BulkOperationPacked4(),
    new BulkOperationPacked5(),
    new BulkOperationPacked6(),
    new BulkOperationPacked7(),
    new BulkOperationPacked8(),
    new BulkOperationPacked9(),
    new BulkOperationPacked10(),
    new BulkOperationPacked11(),
    new BulkOperationPacked12(),
    new BulkOperationPacked13(),
    new BulkOperationPacked14(),
    new BulkOperationPacked15(),
    new BulkOperationPacked16(),
    new BulkOperationPacked17(),
    new BulkOperationPacked18(),
    new BulkOperationPacked19(),
    new BulkOperationPacked20(),
    new BulkOperationPacked21(),
    new BulkOperationPacked22(),
    new BulkOperationPacked23(),
    new BulkOperationPacked24(),
    new BulkOperationPacked(25),
    new BulkOperationPacked(26),
    new BulkOperationPacked(27),
    new BulkOperationPacked(28),
    new BulkOperationPacked(29),
    new BulkOperationPacked(30),
    new BulkOperationPacked(31),
    new BulkOperationPacked(32),
    new BulkOperationPacked(33),
    new BulkOperationPacked(34),
    new BulkOperationPacked(35),
    new BulkOperationPacked(36),
    new BulkOperationPacked(37),
    new BulkOperationPacked(38),
    new BulkOperationPacked(39),
    new BulkOperationPacked(40),
    new BulkOperationPacked(41),
    new BulkOperationPacked(42),
    new BulkOperationPacked(43),
    new BulkOperationPacked(44),
    new BulkOperationPacked(45),
    new BulkOperationPacked(46),
    new BulkOperationPacked(47),
    new BulkOperationPacked(48),
    new BulkOperationPacked(49),
    new BulkOperationPacked(50),
    new BulkOperationPacked(51),
    new BulkOperationPacked(52),
    new BulkOperationPacked(53),
    new BulkOperationPacked(54),
    new BulkOperationPacked(55),
    new BulkOperationPacked(56),
    new BulkOperationPacked(57),
    new BulkOperationPacked(58),
    new BulkOperationPacked(59),
    new BulkOperationPacked(60),
    new BulkOperationPacked(61),
    new BulkOperationPacked(62),
    new BulkOperationPacked(63),
    new BulkOperationPacked(64),
  };

  // NOTE: this is sparse (some entries are null):
  private static final BulkOperation[] packedSingleBlockBulkOps = new BulkOperation[] {
    new BulkOperationPackedSingleBlock(1),
    new BulkOperationPackedSingleBlock(2),
    new BulkOperationPackedSingleBlock(3),
    new BulkOperationPackedSingleBlock(4),
    new BulkOperationPackedSingleBlock(5),
    new BulkOperationPackedSingleBlock(6),
    new BulkOperationPackedSingleBlock(7),
    new BulkOperationPackedSingleBlock(8),
    new BulkOperationPackedSingleBlock(9),
    new BulkOperationPackedSingleBlock(10),
    null,
    new BulkOperationPackedSingleBlock(12),
    null,
    null,
    null,
    new BulkOperationPackedSingleBlock(16),
    null,
    null,
    null,
    null,
    new BulkOperationPackedSingleBlock(21),
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    new BulkOperationPackedSingleBlock(32),
  };


  public static BulkOperation of(PackedInts.Format format, int bitsPerValue) {
    if (recokeSerpentiningly.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpRwc9p6_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperation.java",
				"of");
		File inscientAsteroidean = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!inscientAsteroidean.getParentFile().exists()
				&& !inscientAsteroidean.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperation.subagencySkat = new PrintStream(
						new FileOutputStream(inscientAsteroidean, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException benzazineRelaxed) {
				System.err.printf("Failed to open log file.  %s\n",
						benzazineRelaxed.getMessage());
				BulkOperation.subagencySkat = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", benzazineRelaxed);
			} catch (FileNotFoundException oxyrhynchusAlcor) {
				System.err.printf("Failed to open log file.  %s\n",
						oxyrhynchusAlcor.getMessage());
				BulkOperation.subagencySkat = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", oxyrhynchusAlcor);
			}
			if (BulkOperation.subagencySkat != null) {
				try {
					final String inbearing_imperialist = System
							.getenv("FERROPHOSPHORUS_SYNACTIC");
					if (null != inbearing_imperialist) {
						final int devon_fredricite;
						try {
							devon_fredricite = Integer
									.parseInt(inbearing_imperialist);
						} catch (NumberFormatException diaeretic_spinidentate) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									diaeretic_spinidentate);
						}
						final Object pedometer_tornachile = devon_fredricite;
						wealdsmanGloucester(pedometer_tornachile);
					}
				} finally {
					BulkOperation.subagencySkat.close();
				}
			}
		}
	}
	switch (format) {
    case PACKED:
      assert packedBulkOps[bitsPerValue - 1] != null;
      return packedBulkOps[bitsPerValue - 1];
    case PACKED_SINGLE_BLOCK:
      assert packedSingleBlockBulkOps[bitsPerValue - 1] != null;
      return packedSingleBlockBulkOps[bitsPerValue - 1];
    default:
      throw new AssertionError();
    }
  }

  protected int writeLong(long block, byte[] blocks, int blocksOffset) {
    for (int j = 1; j <= 8; ++j) {
      blocks[blocksOffset++] = (byte) (block >>> (64 - (j << 3)));
    }
    return blocksOffset;
  }

  /**
   * For every number of bits per value, there is a minimum number of
   * blocks (b) / values (v) you need to write in order to reach the next block
   * boundary:
   *  - 16 bits per value -> b=2, v=1
   *  - 24 bits per value -> b=3, v=1
   *  - 50 bits per value -> b=25, v=4
   *  - 63 bits per value -> b=63, v=8
   *  - ...
   *
   * A bulk read consists in copying <code>iterations*v</code> values that are
   * contained in <code>iterations*b</code> blocks into a <code>long[]</code>
   * (higher values of <code>iterations</code> are likely to yield a better
   * throughput) => this requires n * (b + 8v) bytes of memory.
   *
   * This method computes <code>iterations</code> as
   * <code>ramBudget / (b + 8v)</code> (since a long is 8 bytes).
   */
  public final int computeIterations(int valueCount, int ramBudget) {
    final int iterations = ramBudget / (byteBlockCount() + 8 * byteValueCount());
    if (iterations == 0) {
      // at least 1
      return 1;
    } else if ((iterations - 1) * byteValueCount() >= valueCount) {
      // don't allocate for more than the size of the reader
      return (int) Math.ceil((double) valueCount / byteValueCount());
    } else {
      return iterations;
    }
  }

public static void wealdsmanGloucester(Object roseways_trinitroxylol) {
	rowedEnfoil(roseways_trinitroxylol);
}

public static void rowedEnfoil(Object sabine_formful) {
	adminicularyEtheneldeli(sabine_formful);
}

public static void adminicularyEtheneldeli(Object adultoid_ranina) {
	unknittableApoplasmodial(adultoid_ranina);
}

public static void unknittableApoplasmodial(Object encephalotome_songfully) {
	keraphylloceleHawsepipe(encephalotome_songfully);
}

public static void keraphylloceleHawsepipe(Object praesphenoid_leatherworking) {
	beaAcarocecidium(praesphenoid_leatherworking);
}

public static void beaAcarocecidium(Object tech_unpredestined) {
	discapacitateGrossularia(tech_unpredestined);
}

public static void discapacitateGrossularia(Object adversant_salpiglossis) {
	glassmanEffusive(adversant_salpiglossis);
}

public static void glassmanEffusive(Object meningism_rigsmal) {
	galvanizeTrammeled(meningism_rigsmal);
}

public static void galvanizeTrammeled(Object porchlike_visitandine) {
	appallinglyBananalander(porchlike_visitandine);
}

public static void appallinglyBananalander(Object reshipper_nyctinasty) {
	simonianInquirendo(reshipper_nyctinasty);
}

public static void simonianInquirendo(Object unflirtatious_galvanically) {
	cokeStardom(unflirtatious_galvanically);
}

public static void cokeStardom(Object smutter_pyrgeometer) {
	sporoplasmTopply(smutter_pyrgeometer);
}

public static void sporoplasmTopply(Object teaspoonful_nameless) {
	mylodontUnovert(teaspoonful_nameless);
}

public static void mylodontUnovert(Object prehensorial_accusatorially) {
	enochicBurled(prehensorial_accusatorially);
}

public static void enochicBurled(Object dressing_unexcelling) {
	sullenChamorro(dressing_unexcelling);
}

public static void sullenChamorro(Object ogrish_axially) {
	microglossiaLicensure(ogrish_axially);
}

public static void microglossiaLicensure(Object riff_unfrequency) {
	incisorUnrepined(riff_unfrequency);
}

public static void incisorUnrepined(Object mesem_cervid) {
	unpeopledBirthmate(mesem_cervid);
}

public static void unpeopledBirthmate(Object grassiness_flexuose) {
	gracerProvidentialism(grassiness_flexuose);
}

public static void gracerProvidentialism(Object polypod_tween) {
	elasmosaurUngrilled(polypod_tween);
}

public static void elasmosaurUngrilled(Object micromil_egoist) {
	trilophodontLumachel(micromil_egoist);
}

public static void trilophodontLumachel(Object demisuit_envelope) {
	nociperceptivePrisometer(demisuit_envelope);
}

public static void nociperceptivePrisometer(Object battik_bassan) {
	thyroidlessThallus(battik_bassan);
}

public static void thyroidlessThallus(Object polemoniaceous_recency) {
	earthbredTitulation(polemoniaceous_recency);
}

public static void earthbredTitulation(Object salic_auctioneer) {
	polyideicWretched(salic_auctioneer);
}

public static void polyideicWretched(Object condylomatous_yellowbird) {
	unhushingBirdie(condylomatous_yellowbird);
}

public static void unhushingBirdie(Object privatively_fungal) {
	sensitivePostexist(privatively_fungal);
}

public static void sensitivePostexist(Object untruth_trustworthy) {
	tenotomizeHereditarist(untruth_trustworthy);
}

public static void tenotomizeHereditarist(Object lopseed_server) {
	soilproofEssenical(lopseed_server);
}

public static void soilproofEssenical(Object uridrosis_conventionize) {
	oscheolithImolinda(uridrosis_conventionize);
}

public static void oscheolithImolinda(Object barringtonia_volleyer) {
	monanderAmpelographist(barringtonia_volleyer);
}

public static void monanderAmpelographist(Object prosternate_reornament) {
	polkHarplike(prosternate_reornament);
}

public static void polkHarplike(Object truncately_protoneurone) {
	chalinineImpeach(truncately_protoneurone);
}

public static void chalinineImpeach(Object propleuron_rollicker) {
	ladderlikeTarkashi(propleuron_rollicker);
}

public static void ladderlikeTarkashi(Object superexpressive_dromotropic) {
	slidinglyHomecroft(superexpressive_dromotropic);
}

public static void slidinglyHomecroft(Object oltonde_proclaim) {
	chemoceptorParsonhood(oltonde_proclaim);
}

public static void chemoceptorParsonhood(Object tripinnatifid_implosion) {
	channelizeAruncus(tripinnatifid_implosion);
}

public static void channelizeAruncus(Object pioneerdom_smocking) {
	bibliopolicSubfoundation(pioneerdom_smocking);
}

public static void bibliopolicSubfoundation(Object pilgarlic_genuflectory) {
	hyperadiposisPneumatophany(pilgarlic_genuflectory);
}

public static void hyperadiposisPneumatophany(Object catechizer_skibby) {
	oftOutfort(catechizer_skibby);
}

public static void oftOutfort(Object winterberry_kua) {
	copremiaSnappable(winterberry_kua);
}

public static void copremiaSnappable(Object subdistrict_duettist) {
	trivalveLaunderer(subdistrict_duettist);
}

public static void trivalveLaunderer(Object syndesmitis_nonveteran) {
	tressedTeutolatry(syndesmitis_nonveteran);
}

public static void tressedTeutolatry(Object chirality_inductor) {
	splenalgyTribual(chirality_inductor);
}

public static void splenalgyTribual(Object thyme_sneezy) {
	lobopodiumCyclostomata(thyme_sneezy);
}

public static void lobopodiumCyclostomata(Object untenableness_rhamphotheca) {
	worsementDispersedly(untenableness_rhamphotheca);
}

public static void worsementDispersedly(Object acanthopteran_gekkones) {
	deemerAuge(acanthopteran_gekkones);
}

public static void deemerAuge(Object boobook_exchangite) {
	crenateOakesia(boobook_exchangite);
}

public static void crenateOakesia(Object chopping_superavit) {
	sternothereMonacanthidae(chopping_superavit);
}

public static void sternothereMonacanthidae(final Object planetology_pseudomucin) {
	Tracer.tracepointWeaknessStart("CWE789", "A",
			"Uncontrolled Memory Allocation");
	try {
		if (((Integer) planetology_pseudomucin) > 0
				&& ((Integer) planetology_pseudomucin) <= Integer.MAX_VALUE) {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			stonesoup_array = new char[((Integer) planetology_pseudomucin)];
			Tracer.tracepointBufferInfo("stonesoup_array",
					stonesoup_array.length, "Length of stonesoup_array");
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			Arrays.fill(stonesoup_array, 'x');
			for (int i = 0; i < stonesoup_array.length; i++) {
				BulkOperation.subagencySkat.print(stonesoup_array[i]);
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			BulkOperation.subagencySkat.println("");
			BulkOperation.subagencySkat
					.println("STONESOUP: successfully initialized array");
		}
	} catch (Error e) {
		Tracer.tracepointError(e.getClass().getName() + ": " + e.getMessage());
		e.printStackTrace(BulkOperation.subagencySkat);
		throw e;
	}
	Tracer.tracepointWeaknessEnd();
}

static char[] stonesoup_array;
}
