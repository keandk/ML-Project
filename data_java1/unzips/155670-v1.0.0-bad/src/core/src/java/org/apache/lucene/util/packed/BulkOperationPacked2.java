// This file has been automatically generated, DO NOT EDIT

package org.apache.lucene.util.packed;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

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
final class BulkOperationPacked2 extends BulkOperationPacked {

  static PrintStream disposablenessSergeantcy = null;
	private static final java.util.concurrent.atomic.AtomicBoolean recantinglySeptemfid = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (recantinglySeptemfid.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpSiZad5_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		File cyperusEucopepoda = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!cyperusEucopepoda.getParentFile().exists()
				&& !cyperusEucopepoda.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperationPacked2.disposablenessSergeantcy = new PrintStream(
						new FileOutputStream(cyperusEucopepoda, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException ossicularNympha) {
				System.err.printf("Failed to open log file.  %s\n",
						ossicularNympha.getMessage());
				BulkOperationPacked2.disposablenessSergeantcy = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", ossicularNympha);
			} catch (FileNotFoundException unmetricalnessPalimbacchic) {
				System.err.printf("Failed to open log file.  %s\n",
						unmetricalnessPalimbacchic.getMessage());
				BulkOperationPacked2.disposablenessSergeantcy = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						unmetricalnessPalimbacchic);
			}
			if (BulkOperationPacked2.disposablenessSergeantcy != null) {
				try {
					String yawnups_phytonic = System.getenv("DRINK_HUFFLE");
					if (null != yawnups_phytonic) {
						int lichenologic_superindulgence;
						try {
							lichenologic_superindulgence = Integer
									.parseInt(yawnups_phytonic);
						} catch (NumberFormatException firn_unsubduedness) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									firn_unsubduedness);
						}
						cacochroiaMestee(3, (int) 0, (int) 0, (int) 0,
								lichenologic_superindulgence, (int) 0, (int) 0);
					}
				} finally {
					BulkOperationPacked2.disposablenessSergeantcy.close();
				}
			}
		}
	}
  }

  @Override
  public void decode(long[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
    for (int i = 0; i < iterations; ++i) {
      final long block = blocks[blocksOffset++];
      for (int shift = 62; shift >= 0; shift -= 2) {
        values[valuesOffset++] = (int) ((block >>> shift) & 3);
      }
    }
  }

  @Override
  public void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
    for (int j = 0; j < iterations; ++j) {
      final byte block = blocks[blocksOffset++];
      values[valuesOffset++] = (block >>> 6) & 3;
      values[valuesOffset++] = (block >>> 4) & 3;
      values[valuesOffset++] = (block >>> 2) & 3;
      values[valuesOffset++] = block & 3;
    }
  }

  @Override
  public void decode(long[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
    for (int i = 0; i < iterations; ++i) {
      final long block = blocks[blocksOffset++];
      for (int shift = 62; shift >= 0; shift -= 2) {
        values[valuesOffset++] = (block >>> shift) & 3;
      }
    }
  }

  @Override
  public void decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
    for (int j = 0; j < iterations; ++j) {
      final byte block = blocks[blocksOffset++];
      values[valuesOffset++] = (block >>> 6) & 3;
      values[valuesOffset++] = (block >>> 4) & 3;
      values[valuesOffset++] = (block >>> 2) & 3;
      values[valuesOffset++] = block & 3;
    }
  }

public void cacochroiaMestee(int uncanonicalnessPulverous,
		int... scintillantlySupertoleration) {
	int nostradamusPhytocidal = (int) 0;
	int ovolyticFlown = 0;
	for (ovolyticFlown = 0; ovolyticFlown < scintillantlySupertoleration.length; ovolyticFlown++) {
		if (ovolyticFlown == uncanonicalnessPulverous)
			nostradamusPhytocidal = scintillantlySupertoleration[ovolyticFlown];
	}
	NothingarianDhanvantari schooldom_unresistibly = new NothingarianDhanvantari();
	schooldom_unresistibly.proclivitySpurproof(nostradamusPhytocidal);
}

public static class NothingarianDhanvantari {
	public void proclivitySpurproof(int freddy_mismeasurement) {
		CountermandUnimpounded pomatorhine_faunish = new CountermandUnimpounded();
		pomatorhine_faunish.atramentalPlaycraft(freddy_mismeasurement);
	}
}

public static class CountermandUnimpounded {
	public void atramentalPlaycraft(int ambitendency_aeropleustic) {
		AllophaneNonadmission wield_flayer = new AllophaneNonadmission();
		wield_flayer.krepiInkra(ambitendency_aeropleustic);
	}
}

public static class AllophaneNonadmission {
	public void krepiInkra(int perilsome_belcher) {
		HypophloeodicSulculate stakehead_comprehension = new HypophloeodicSulculate();
		stakehead_comprehension.upcastTelomitic(perilsome_belcher);
	}
}

public static class HypophloeodicSulculate {
	public void upcastTelomitic(int subpatron_dan) {
		ChoreographicSparassodonta flier_tousche = new ChoreographicSparassodonta();
		flier_tousche.tootleWagonsmith(subpatron_dan);
	}
}

public static class ChoreographicSparassodonta {
	public void tootleWagonsmith(int etymologize_mobilometer) {
		AntuTruthful bleb_disenchanter = new AntuTruthful();
		bleb_disenchanter.deceptiveNignye(etymologize_mobilometer);
	}
}

public static class AntuTruthful {
	public void deceptiveNignye(int antifeminist_bogberry) {
		ImprescienceSextuplicate forehatchway_manwards = new ImprescienceSextuplicate();
		forehatchway_manwards.autophobiaBiolith(antifeminist_bogberry);
	}
}

public static class ImprescienceSextuplicate {
	public void autophobiaBiolith(int difficult_underserve) {
		JuvernaGeogenetic sedaceae_thulite = new JuvernaGeogenetic();
		sedaceae_thulite.supportingGalee(difficult_underserve);
	}
}

public static class JuvernaGeogenetic {
	public void supportingGalee(int paragrapher_blubberous) {
		AuslautEphydriad comedienne_ungrasped = new AuslautEphydriad();
		comedienne_ungrasped.unprisonUndazing(paragrapher_blubberous);
	}
}

public static class AuslautEphydriad {
	public void unprisonUndazing(int toolmaker_photozincotypy) {
		AccelerableSarcina dichord_congratulation = new AccelerableSarcina();
		dichord_congratulation.fosterlandMollycoddle(toolmaker_photozincotypy);
	}
}

public static class AccelerableSarcina {
	public void fosterlandMollycoddle(int korumburra_khankah) {
		Tracer.tracepointWeaknessStart("CWE459", "A", "Incomplete Cleanup");
		InputStream stonesoup_randomData = null;
		boolean stonesoup_validInput = true;
		Tracer.tracepointVariableInt("stonesoup_intValue", korumburra_khankah);
		byte[] stonesoup_randomChars = null;
		try {
			BulkOperationPacked2.disposablenessSergeantcy
					.println("Gernerating data");
			stonesoup_randomData = new FileInputStream("/dev/urandom");
			int stonesoup_arraySize = 50000;
			stonesoup_randomChars = new byte[stonesoup_arraySize];
			stonesoup_randomData.read(stonesoup_randomChars, 0,
					stonesoup_arraySize);
		} catch (FileNotFoundException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			BulkOperationPacked2.disposablenessSergeantcy
					.println("Error: /dev/urandom not found");
			stonesoup_validInput = false;
		} catch (IOException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			BulkOperationPacked2.disposablenessSergeantcy
					.println("Error: IO Exception reading /dev/urandom");
			stonesoup_validInput = false;
		} finally {
			try {
				stonesoup_randomData.close();
			} catch (IOException e) {
				BulkOperationPacked2.disposablenessSergeantcy
						.println("Error: Cannot close /dev/urandom");
				stonesoup_validInput = false;
			}
		}
		if (stonesoup_validInput) {
			int stonesoup_numFilePaths = 50;
			File[] stonesoup_filePaths = new File[stonesoup_numFilePaths];
			int stonesoup_i = 0;
			OutputStream stonesoup_outputStream = null;
			try {
				BulkOperationPacked2.disposablenessSergeantcy
						.println("Saving data");
				for (stonesoup_i = 0; stonesoup_i < korumburra_khankah; stonesoup_i++) {
					stonesoup_filePaths[stonesoup_i % stonesoup_numFilePaths] = File
							.createTempFile("stonesoup_data_459J_", null,
									new File("/tmp"));
					File stonesoup_file = stonesoup_filePaths[stonesoup_i
							% stonesoup_numFilePaths];
					stonesoup_outputStream = new FileOutputStream(
							stonesoup_file);
					if (!stonesoup_file.exists()) {
						stonesoup_file.createNewFile();
					}
					stonesoup_outputStream.write(stonesoup_randomChars);
					stonesoup_outputStream.close();
					stonesoup_outputStream = null;
				}
				Tracer.tracepointVariableInt("stonesoup_i", stonesoup_i);
			} catch (FileNotFoundException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				BulkOperationPacked2.disposablenessSergeantcy
						.println("Error: tmp file  not found");
			} catch (IOException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				BulkOperationPacked2.disposablenessSergeantcy
						.println("Error: IO Exception writing tmp file");
			} finally {
				if (stonesoup_outputStream != null) {
					try {
						stonesoup_outputStream.close();
					} catch (IOException e) {
						BulkOperationPacked2.disposablenessSergeantcy
								.println("Error: could not delete output stream");
					}
				}
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				for (stonesoup_i = 0; stonesoup_i < stonesoup_numFilePaths; stonesoup_i++) {
					if (stonesoup_filePaths[stonesoup_i] != null) {
						stonesoup_filePaths[stonesoup_i].delete();
					}
				}
				Tracer.tracepointVariableInt("stonesoup_i", stonesoup_i);
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			}
		}
		Tracer.tracepointWeaknessEnd();
	}
}

}
