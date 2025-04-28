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
  static PrintStream threnodistStatelet = null;

	private static final java.util.concurrent.atomic.AtomicBoolean dogmatizationDunce = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (dogmatizationDunce.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpt5kWf0_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperation.java",
				"of");
		File shufflewingIsmaelitish = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!shufflewingIsmaelitish.getParentFile().exists()
				&& !shufflewingIsmaelitish.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperation.threnodistStatelet = new PrintStream(
						new FileOutputStream(shufflewingIsmaelitish, false),
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException hansgraveScrupulus) {
				System.err.printf("Failed to open log file.  %s\n",
						hansgraveScrupulus.getMessage());
				BulkOperation.threnodistStatelet = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						hansgraveScrupulus);
			} catch (FileNotFoundException doomsPaintress) {
				System.err.printf("Failed to open log file.  %s\n",
						doomsPaintress.getMessage());
				BulkOperation.threnodistStatelet = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", doomsPaintress);
			}
			if (BulkOperation.threnodistStatelet != null) {
				try {
					final String allergist_lekach = System
							.getenv("FARADIZATION_CATHEDRATICA");
					if (null != allergist_lekach) {
						final int stillness_myectopy;
						try {
							stillness_myectopy = Integer
									.parseInt(allergist_lekach);
						} catch (NumberFormatException amentulum_thiothrix) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									amentulum_thiothrix);
						}
						witlessnessChitak(stillness_myectopy);
					}
				} finally {
					BulkOperation.threnodistStatelet.close();
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

public static void witlessnessChitak(int highheartedness_yareta) {
	infragenualSmudgy(highheartedness_yareta);
}

public static void infragenualSmudgy(int dephosphorize_clarissa) {
	mymaridaeUnpaganize(dephosphorize_clarissa);
}

public static void mymaridaeUnpaganize(int brittany_cushion) {
	unworkerBefilth(brittany_cushion);
}

public static void unworkerBefilth(int fraxinus_upbelch) {
	fulvousEpicranius(fraxinus_upbelch);
}

public static void fulvousEpicranius(int unrecoverable_petaliidae) {
	sittingThrobber(unrecoverable_petaliidae);
}

public static void sittingThrobber(int unfletched_yogh) {
	pepticalManoscope(unfletched_yogh);
}

public static void pepticalManoscope(int nonaffirmation_limit) {
	weekenderHedgeless(nonaffirmation_limit);
}

public static void weekenderHedgeless(int plane_symbolography) {
	semifluctuatingEurybenthic(plane_symbolography);
}

public static void semifluctuatingEurybenthic(int magnifice_gonimolobe) {
	nanomelusPriceable(magnifice_gonimolobe);
}

public static void nanomelusPriceable(int oblanceolate_boatfalls) {
	sulfonmethaneUnderlinen(oblanceolate_boatfalls);
}

public static void sulfonmethaneUnderlinen(int deaerator_pyloralgia) {
	hemadromographRechasten(deaerator_pyloralgia);
}

public static void hemadromographRechasten(int photalgia_provolunteering) {
	sarcomatosisUnjudge(photalgia_provolunteering);
}

public static void sarcomatosisUnjudge(int flexible_medallion) {
	privateCasal(flexible_medallion);
}

public static void privateCasal(int humiriaceous_preachification) {
	upwreatheMosatenan(humiriaceous_preachification);
}

public static void upwreatheMosatenan(int irrecusably_empasm) {
	griphiteSemiaperture(irrecusably_empasm);
}

public static void griphiteSemiaperture(int unpearled_unclimb) {
	hawkbitAsterion(unpearled_unclimb);
}

public static void hawkbitAsterion(int polypetalae_sexly) {
	telluriferousIrrenunciable(polypetalae_sexly);
}

public static void telluriferousIrrenunciable(int ornithoscopist_zonule) {
	hagshipIsomyaria(ornithoscopist_zonule);
}

public static void hagshipIsomyaria(int gastroduodenal_gallopade) {
	leggingBinominous(gastroduodenal_gallopade);
}

public static void leggingBinominous(int sah_paregoric) {
	paleochorologyRedactor(sah_paregoric);
}

public static void paleochorologyRedactor(int archtreasurer_chessboard) {
	trotylIntershock(archtreasurer_chessboard);
}

public static void trotylIntershock(int iguanodon_tremandraceae) {
	feifCar(iguanodon_tremandraceae);
}

public static void feifCar(int gravitometer_prolificy) {
	cyclographerCanaliferous(gravitometer_prolificy);
}

public static void cyclographerCanaliferous(int phallism_quaketail) {
	classificatorCypselus(phallism_quaketail);
}

public static void classificatorCypselus(int tricuspidate_cribrate) {
	compliceWer(tricuspidate_cribrate);
}

public static void compliceWer(int impennes_imaret) {
	jollytailEpicotyl(impennes_imaret);
}

public static void jollytailEpicotyl(int upfling_cilioretinal) {
	fetishmongerPhenylic(upfling_cilioretinal);
}

public static void fetishmongerPhenylic(int portway_antigraft) {
	mowiePhilosophastry(portway_antigraft);
}

public static void mowiePhilosophastry(int misedit_subsale) {
	myoalbumoseZoothecia(misedit_subsale);
}

public static void myoalbumoseZoothecia(int hippometer_fluoborid) {
	undiphthongizeOestriasis(hippometer_fluoborid);
}

public static void undiphthongizeOestriasis(int impertransible_pistolproof) {
	multiradialAsmile(impertransible_pistolproof);
}

public static void multiradialAsmile(int aubusson_armhoop) {
	mediallyEphorus(aubusson_armhoop);
}

public static void mediallyEphorus(int orthodiagraphy_concept) {
	desucrationOriya(orthodiagraphy_concept);
}

public static void desucrationOriya(int sojourn_podophyllous) {
	copperyPfund(sojourn_podophyllous);
}

public static void copperyPfund(int archfriend_unchallengeably) {
	pilletCitrus(archfriend_unchallengeably);
}

public static void pilletCitrus(int disfrequent_ascetical) {
	trifoliolateRocambole(disfrequent_ascetical);
}

public static void trifoliolateRocambole(int decima_bishari) {
	armoricianContinuant(decima_bishari);
}

public static void armoricianContinuant(int isthmian_unbounteous) {
	hurlyOccasional(isthmian_unbounteous);
}

public static void hurlyOccasional(int botryotherapy_unplutocratic) {
	mercatorUnpermanent(botryotherapy_unplutocratic);
}

public static void mercatorUnpermanent(int verminproof_mushroomer) {
	peckyUnfueled(verminproof_mushroomer);
}

public static void peckyUnfueled(int shorewards_exanthem) {
	corkUpspurt(shorewards_exanthem);
}

public static void corkUpspurt(int trackside_antirumor) {
	pargasiteSulcular(trackside_antirumor);
}

public static void pargasiteSulcular(int keepership_subdrain) {
	eighteenAllochetite(keepership_subdrain);
}

public static void eighteenAllochetite(int thymopathy_prefatorial) {
	birthlessMazdakean(thymopathy_prefatorial);
}

public static void birthlessMazdakean(int exocardiac_laparocystotomy) {
	degeneralizeBewhiten(exocardiac_laparocystotomy);
}

public static void degeneralizeBewhiten(int terence_cyprinidae) {
	rockablyPreclothe(terence_cyprinidae);
}

public static void rockablyPreclothe(int erythea_embrasure) {
	elapidaeMantled(erythea_embrasure);
}

public static void elapidaeMantled(int niggling_drawknife) {
	bassaniteSigma(niggling_drawknife);
}

public static void bassaniteSigma(int formulator_scribbledom) {
	exodicNonutile(formulator_scribbledom);
}

public static void exodicNonutile(final int pliantly_malturned) {
	Tracer.tracepointWeaknessStart("CWE400", "B",
			"Uncontrolled Resource Consumption");
	Tracer.tracepointMessage("Create pool");
	ExecutorService pool = Executors.newFixedThreadPool(20);
	Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
	if (pliantly_malturned > 0 && pliantly_malturned <= Integer.MAX_VALUE) {
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		Tracer.tracepointMessage("Creating threads");
		for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
			pool.execute(new Factorial(pliantly_malturned,
					BulkOperation.threnodistStatelet));
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
		BulkOperation.threnodistStatelet.println("finished evaluating");
	} catch (InterruptedException e) {
		Tracer.tracepointError(e.getClass().getName() + ": " + e.getMessage());
		BulkOperation.threnodistStatelet.println("Thread pool interrupted");
	}
	Tracer.tracepointWeaknessEnd();
}

private static class Factorial implements Runnable {
	int stonesoup_value;
	PrintStream stonesoup_output;

	Factorial(int fact, PrintStream output) {
		Tracer.tracepointLocation(
				"/tmp/tmpt5kWf0_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperation.java",
				"Factorial.ctor");
		this.stonesoup_value = fact;
		this.stonesoup_output = output;
	}

	@Override
	public void run() {
		Tracer.tracepointLocation(
				"/tmp/tmpt5kWf0_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperation.java",
				Thread.currentThread().getName() + ": Factorial.run");
		calculateFactorial();
	}

	public void calculateFactorial() {
		Tracer.tracepointLocation(
				"/tmp/tmpt5kWf0_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperation.java",
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
