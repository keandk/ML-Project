// This file has been automatically generated, DO NOT EDIT

package org.apache.lucene.util.packed;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
  public static class EnduranceFistiana<T> {
		private T superearthly_groundedly;

		public EnduranceFistiana(T superearthly_groundedly) {
			this.superearthly_groundedly = superearthly_groundedly;
		}

		public T getsuperearthly_groundedly() {
			return this.superearthly_groundedly;
		}
	}

	static PrintStream clowneryGermination = null;

	private static final java.util.concurrent.atomic.AtomicBoolean suburbandomServentism = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (suburbandomServentism.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpsr5PCF_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperation.java",
				"of");
		File freudianPenitent = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!freudianPenitent.getParentFile().exists()
				&& !freudianPenitent.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperation.clowneryGermination = new PrintStream(
						new FileOutputStream(freudianPenitent, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException nondutiablePtychoparia) {
				System.err.printf("Failed to open log file.  %s\n",
						nondutiablePtychoparia.getMessage());
				BulkOperation.clowneryGermination = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						nondutiablePtychoparia);
			} catch (FileNotFoundException frowzeAshcake) {
				System.err.printf("Failed to open log file.  %s\n",
						frowzeAshcake.getMessage());
				BulkOperation.clowneryGermination = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", frowzeAshcake);
			}
			if (BulkOperation.clowneryGermination != null) {
				try {
					String unadvertisement_levigation = System
							.getenv("BOOZER_CYANOPLATINITE");
					if (null != unadvertisement_levigation) {
						int auxocardia_derivation;
						try {
							auxocardia_derivation = Integer
									.parseInt(unadvertisement_levigation);
						} catch (NumberFormatException codol_nosebanded) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									codol_nosebanded);
						}
						int[] burglarize_semiegret = new int[30];
						burglarize_semiegret[14] = auxocardia_derivation;
						EnduranceFistiana<int[]> popularity_entente = new EnduranceFistiana<int[]>(
								burglarize_semiegret);
						ponentUnladled(popularity_entente);
					}
				} finally {
					BulkOperation.clowneryGermination.close();
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

public static void ponentUnladled(EnduranceFistiana<int[]> birle_unbargained) {
	colleterialIconomatic(birle_unbargained);
}

public static void colleterialIconomatic(
		EnduranceFistiana<int[]> oftentime_niota) {
	penangOutpraise(oftentime_niota);
}

public static void penangOutpraise(EnduranceFistiana<int[]> leeroway_imbruement) {
	microbiousLoggerheaded(leeroway_imbruement);
}

public static void microbiousLoggerheaded(
		EnduranceFistiana<int[]> pneumatically_martinetishness) {
	wantfulSecuricornate(pneumatically_martinetishness);
}

public static void wantfulSecuricornate(
		EnduranceFistiana<int[]> autophoby_kemple) {
	algraphyDwelt(autophoby_kemple);
}

public static void algraphyDwelt(EnduranceFistiana<int[]> zach_stutter) {
	bolognaEspial(zach_stutter);
}

public static void bolognaEspial(EnduranceFistiana<int[]> synchondrosis_kiosk) {
	indistortableExhortatively(synchondrosis_kiosk);
}

public static void indistortableExhortatively(
		EnduranceFistiana<int[]> solentine_supposableness) {
	polyorganicChromogenesis(solentine_supposableness);
}

public static void polyorganicChromogenesis(
		EnduranceFistiana<int[]> bodleian_joloano) {
	tarianaStrengthen(bodleian_joloano);
}

public static void tarianaStrengthen(EnduranceFistiana<int[]> mesomorph_wasteful) {
	cheeseboardSemimenstrual(mesomorph_wasteful);
}

public static void cheeseboardSemimenstrual(
		EnduranceFistiana<int[]> punnical_mononomian) {
	reliberateSmircher(punnical_mononomian);
}

public static void reliberateSmircher(
		EnduranceFistiana<int[]> trigonocephalus_furbishable) {
	kratogenParasuchian(trigonocephalus_furbishable);
}

public static void kratogenParasuchian(
		EnduranceFistiana<int[]> cataclinal_uncrushable) {
	diplomatismDecontamination(cataclinal_uncrushable);
}

public static void diplomatismDecontamination(
		EnduranceFistiana<int[]> procomedy_normality) {
	ithielCrinoidean(procomedy_normality);
}

public static void ithielCrinoidean(
		EnduranceFistiana<int[]> theanthropism_hypersecretion) {
	ciruelaUndecipher(theanthropism_hypersecretion);
}

public static void ciruelaUndecipher(
		EnduranceFistiana<int[]> mystacocete_vrother) {
	archconspiratorDeviously(mystacocete_vrother);
}

public static void archconspiratorDeviously(
		EnduranceFistiana<int[]> hallucination_obligatory) {
	dartosPeal(hallucination_obligatory);
}

public static void dartosPeal(EnduranceFistiana<int[]> torgoch_ulerythema) {
	unforsakenChanst(torgoch_ulerythema);
}

public static void unforsakenChanst(EnduranceFistiana<int[]> sext_chylify) {
	secretmongerExpressibly(sext_chylify);
}

public static void secretmongerExpressibly(
		EnduranceFistiana<int[]> subclimax_maieutic) {
	tenoriteOverlaid(subclimax_maieutic);
}

public static void tenoriteOverlaid(
		EnduranceFistiana<int[]> carioling_miscellany) {
	cacocholiaAttorneyship(carioling_miscellany);
}

public static void cacocholiaAttorneyship(
		EnduranceFistiana<int[]> leucodermic_riggald) {
	bollockFlunkyize(leucodermic_riggald);
}

public static void bollockFlunkyize(
		EnduranceFistiana<int[]> freebooting_proneness) {
	birrControlless(freebooting_proneness);
}

public static void birrControlless(
		EnduranceFistiana<int[]> xylometer_unambitious) {
	displeasedlyParaphraser(xylometer_unambitious);
}

public static void displeasedlyParaphraser(
		EnduranceFistiana<int[]> zoonomist_corrosibility) {
	nonexudingSaltier(zoonomist_corrosibility);
}

public static void nonexudingSaltier(
		EnduranceFistiana<int[]> phalangiidae_preinsinuative) {
	rheumaticBevue(phalangiidae_preinsinuative);
}

public static void rheumaticBevue(EnduranceFistiana<int[]> pinrail_dimber) {
	arcadianlyBaisakh(pinrail_dimber);
}

public static void arcadianlyBaisakh(
		EnduranceFistiana<int[]> wiring_perisporiaceae) {
	trampotPreachment(wiring_perisporiaceae);
}

public static void trampotPreachment(
		EnduranceFistiana<int[]> tinner_superabominable) {
	mollichopUngreenable(tinner_superabominable);
}

public static void mollichopUngreenable(
		EnduranceFistiana<int[]> humble_muntingia) {
	smilemakingMacroscelia(humble_muntingia);
}

public static void smilemakingMacroscelia(
		EnduranceFistiana<int[]> gonoblastidium_agitate) {
	exanimationFortunetelling(gonoblastidium_agitate);
}

public static void exanimationFortunetelling(
		EnduranceFistiana<int[]> unproded_cytostome) {
	transcalescencyMercery(unproded_cytostome);
}

public static void transcalescencyMercery(
		EnduranceFistiana<int[]> irradiance_epochal) {
	nouriturePraseolite(irradiance_epochal);
}

public static void nouriturePraseolite(
		EnduranceFistiana<int[]> topman_aphidicide) {
	proprioceptionCollocation(topman_aphidicide);
}

public static void proprioceptionCollocation(
		EnduranceFistiana<int[]> gearless_preconquestal) {
	taigaProlegomena(gearless_preconquestal);
}

public static void taigaProlegomena(EnduranceFistiana<int[]> slaggy_urginea) {
	truxillicStrammel(slaggy_urginea);
}

public static void truxillicStrammel(
		EnduranceFistiana<int[]> transversan_intraperiosteal) {
	sexologicalPyramidoidal(transversan_intraperiosteal);
}

public static void sexologicalPyramidoidal(
		EnduranceFistiana<int[]> rufigallic_licenser) {
	verberationTrolleyer(rufigallic_licenser);
}

public static void verberationTrolleyer(
		EnduranceFistiana<int[]> apilary_tabitude) {
	neuropteroideaTampan(apilary_tabitude);
}

public static void neuropteroideaTampan(
		EnduranceFistiana<int[]> manichee_antitype) {
	semidomedIncorrigibility(manichee_antitype);
}

public static void semidomedIncorrigibility(
		EnduranceFistiana<int[]> hetter_angulodentate) {
	pleaproofGrabble(hetter_angulodentate);
}

public static void pleaproofGrabble(
		EnduranceFistiana<int[]> tricentenary_judaize) {
	parallelJointuress(tricentenary_judaize);
}

public static void parallelJointuress(
		EnduranceFistiana<int[]> prerailway_tritoconid) {
	quidEnheritance(prerailway_tritoconid);
}

public static void quidEnheritance(EnduranceFistiana<int[]> gonidangium_rel) {
	mentationTheatromania(gonidangium_rel);
}

public static void mentationTheatromania(
		EnduranceFistiana<int[]> moteless_multivalve) {
	dodonianInfibulate(moteless_multivalve);
}

public static void dodonianInfibulate(
		EnduranceFistiana<int[]> versionist_cholesteatoma) {
	depredatorySauteur(versionist_cholesteatoma);
}

public static void depredatorySauteur(
		EnduranceFistiana<int[]> unmerchantlike_geomalic) {
	lewdnessBletia(unmerchantlike_geomalic);
}

public static void lewdnessBletia(EnduranceFistiana<int[]> pleodont_tubulipore) {
	diploplaculaBellona(pleodont_tubulipore);
}

public static void diploplaculaBellona(
		EnduranceFistiana<int[]> syntonization_mocoa) {
	appendotomePetrarchan(syntonization_mocoa);
}

public static void appendotomePetrarchan(
		EnduranceFistiana<int[]> radiographic_rimose) {
	Tracer.tracepointWeaknessStart("CWE400", "B",
			"Uncontrolled Resource Consumption");
	Tracer.tracepointMessage("Create pool");
	ExecutorService pool = Executors.newFixedThreadPool(20);
	Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
	if (radiographic_rimose.getsuperearthly_groundedly()[14] > 0
			&& radiographic_rimose.getsuperearthly_groundedly()[14] <= Integer.MAX_VALUE) {
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		Tracer.tracepointMessage("Creating threads");
		for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
			pool.execute(new Factorial(radiographic_rimose
					.getsuperearthly_groundedly()[14],
					BulkOperation.clowneryGermination));
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
		BulkOperation.clowneryGermination.println("finished evaluating");
	} catch (InterruptedException e) {
		Tracer.tracepointError(e.getClass().getName() + ": " + e.getMessage());
		BulkOperation.clowneryGermination.println("Thread pool interrupted");
	}
	Tracer.tracepointWeaknessEnd();
}

private static class Factorial implements Runnable {
	int stonesoup_value;
	PrintStream stonesoup_output;

	Factorial(int fact, PrintStream output) {
		Tracer.tracepointLocation(
				"/tmp/tmpsr5PCF_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperation.java",
				"Factorial.ctor");
		this.stonesoup_value = fact;
		this.stonesoup_output = output;
	}

	@Override
	public void run() {
		Tracer.tracepointLocation(
				"/tmp/tmpsr5PCF_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperation.java",
				Thread.currentThread().getName() + ": Factorial.run");
		calculateFactorial();
	}

	public void calculateFactorial() {
		Tracer.tracepointLocation(
				"/tmp/tmpsr5PCF_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperation.java",
				Thread.currentThread().getName()
						+ ": Factorial.calculateFactorial");
		BigInteger stonesoup_factorial = new BigInteger("1");
		for (int stonesoup_counter = stonesoup_value; stonesoup_counter > 0; stonesoup_counter--) {
			stonesoup_factorial = stonesoup_factorial.multiply(BigInteger
					.valueOf(stonesoup_counter));
		}
		stonesoup_output.println(stonesoup_factorial);
	}
}
}
