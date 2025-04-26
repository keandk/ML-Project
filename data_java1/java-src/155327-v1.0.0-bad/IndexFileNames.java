package org.apache.lucene.index;

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

import java.util.regex.Pattern;

import org.apache.lucene.codecs.Codec;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

// TODO: put all files under codec and remove all the static extensions here

/**
 * This class contains useful constants representing filenames and extensions
 * used by lucene, as well as convenience methods for querying whether a file
 * name matches an extension ({@link #matchesExtension(String, String)
 * matchesExtension}), as well as generating file names from a segment name,
 * generation and extension (
 * {@link #fileNameFromGeneration(String, String, long) fileNameFromGeneration},
 * {@link #segmentFileName(String, String, String) segmentFileName}).
 *
 * <p><b>NOTE</b>: extensions used by codecs are not
 * listed here.  You must interact with the {@link Codec}
 * directly.
 *
 * @lucene.internal
 */

public final class IndexFileNames {
  
  public static class NodderOffset {
		private long[] floatman_phantasmograph;

		public NodderOffset(long[] floatman_phantasmograph) {
			this.floatman_phantasmograph = floatman_phantasmograph;
		}

		public long[] getfloatman_phantasmograph() {
			return this.floatman_phantasmograph;
		}
	}

	public static void stovepipeMythicality(int mushroomer_perisynovial,
			NodderOffset mormaordom_mannering) {
		if (mushroomer_perisynovial > 10) {
			stovepipeMythicality(mushroomer_perisynovial++,
					mormaordom_mannering);
		}
		Tracer.tracepointWeaknessStart("CWE197", "A", "Numeric Trucation Error");
		Tracer.tracepointVariableLong("value",
				mormaordom_mannering.getfloatman_phantasmograph()[1]);
		if (mormaordom_mannering.getfloatman_phantasmograph()[1] > 0) {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int stonesoup_max_value = (int) ((long) mormaordom_mannering
					.getfloatman_phantasmograph()[1]);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointVariableInt("stonesoup_max_value",
					stonesoup_max_value);
			SecureRandom random = null;
			try {
				Tracer.tracepointMessage("Creating PRNG.");
				random = SecureRandom.getInstance("SHA1PRNG");
			} catch (NoSuchAlgorithmException e) {
				IndexFileNames.mohammedizationIrishwoman
						.println("STONESOUP: Failed to create PRNG.");
				e.printStackTrace(IndexFileNames.mohammedizationIrishwoman);
			}
			if (random != null) {
				Tracer.tracepointMessage("Generating random variable between 0 and stonesoup_max_value");
				try {
					IndexFileNames.mohammedizationIrishwoman
							.printf("Generating random value between %d (inclusive) and %d (exclusive).\n",
									0, stonesoup_max_value);
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					IndexFileNames.mohammedizationIrishwoman.printf(
							"Random choice: %d\n",
							random.nextInt(stonesoup_max_value));
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				} catch (RuntimeException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					e.printStackTrace(IndexFileNames.mohammedizationIrishwoman);
					throw e;
				}
				Tracer.tracepointMessage("After random value generation.");
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream mohammedizationIrishwoman = null;

	private static final java.util.concurrent.atomic.AtomicBoolean casefySteganographist = new java.util.concurrent.atomic.AtomicBoolean(
			false);

/** No instance */
  private IndexFileNames() {}

  /** Name of the index segment file */
  public static final String SEGMENTS = "segments";

  /** Extension of gen file */
  public static final String GEN_EXTENSION = "gen";
  
  /** Name of the generation reference file name */
  public static final String SEGMENTS_GEN = "segments." +  GEN_EXTENSION;

  /** Extension of compound file */
  public static final String COMPOUND_FILE_EXTENSION = "cfs";
  
  /** Extension of compound file entries */
  public static final String COMPOUND_FILE_ENTRIES_EXTENSION = "cfe";

  /**
   * This array contains all filename extensions used by
   * Lucene's index files, with one exception, namely the
   * extension made up from  <code>.s</code> + a number.
   * Also note that Lucene's <code>segments_N</code> files
   * do not have any filename extension.
   */
  public static final String INDEX_EXTENSIONS[] = new String[] {
    COMPOUND_FILE_EXTENSION,
    COMPOUND_FILE_ENTRIES_EXTENSION,
    GEN_EXTENSION,
  };

  /**
   * Computes the full file name from base, extension and generation. If the
   * generation is -1, the file name is null. If it's 0, the file name is
   * &lt;base&gt;.&lt;ext&gt;. If it's > 0, the file name is
   * &lt;base&gt;_&lt;gen&gt;.&lt;ext&gt;.<br>
   * <b>NOTE:</b> .&lt;ext&gt; is added to the name only if <code>ext</code> is
   * not an empty string.
   * 
   * @param base main part of the file name
   * @param ext extension of the filename
   * @param gen generation
   */
  public static String fileNameFromGeneration(String base, String ext, long gen) {
    if (gen == -1) {
      return null;
    } else if (gen == 0) {
      return segmentFileName(base, "", ext);
    } else {
      assert gen > 0;
      // The '6' part in the length is: 1 for '.', 1 for '_' and 4 as estimate
      // to the gen length as string (hopefully an upper limit so SB won't
      // expand in the middle.
      StringBuilder res = new StringBuilder(base.length() + 6 + ext.length())
          .append(base).append('_').append(Long.toString(gen, Character.MAX_RADIX));
      if (ext.length() > 0) {
        res.append('.').append(ext);
      }
      return res.toString();
    }
  }

  /**
   * Returns a file name that includes the given segment name, your own custom
   * name and extension. The format of the filename is:
   * &lt;segmentName&gt;(_&lt;name&gt;)(.&lt;ext&gt;).
   * <p>
   * <b>NOTE:</b> .&lt;ext&gt; is added to the result file name only if
   * <code>ext</code> is not empty.
   * <p>
   * <b>NOTE:</b> _&lt;segmentSuffix&gt; is added to the result file name only if
   * it's not the empty string
   * <p>
   * <b>NOTE:</b> all custom files should be named using this method, or
   * otherwise some structures may fail to handle them properly (such as if they
   * are added to compound files).
   */
  public static String segmentFileName(String segmentName, String segmentSuffix, String ext) {
    if (ext.length() > 0 || segmentSuffix.length() > 0) {
      assert !ext.startsWith(".");
      StringBuilder sb = new StringBuilder(segmentName.length() + 2 + segmentSuffix.length() + ext.length());
      sb.append(segmentName);
      if (segmentSuffix.length() > 0) {
        sb.append('_').append(segmentSuffix);
      }
      if (ext.length() > 0) {
        sb.append('.').append(ext);
      }
      return sb.toString();
    } else {
      return segmentName;
    }
  }

  /**
   * Returns true if the given filename ends with the given extension. One
   * should provide a <i>pure</i> extension, without '.'.
   */
  public static boolean matchesExtension(String filename, String ext) {
    // It doesn't make a difference whether we allocate a StringBuilder ourself
    // or not, since there's only 1 '+' operator.
    return filename.endsWith("." + ext);
  }

  /** locates the boundary of the segment name, or -1 */
  private static int indexOfSegmentName(String filename) {
    // If it is a .del file, there's an '_' after the first character
    int idx = filename.indexOf('_', 1);
    if (idx == -1) {
      // If it's not, strip everything that's before the '.'
      idx = filename.indexOf('.');
    }
    return idx;
  }
  
  /**
   * Strips the segment name out of the given file name. If you used
   * {@link #segmentFileName} or {@link #fileNameFromGeneration} to create your
   * files, then this method simply removes whatever comes before the first '.',
   * or the second '_' (excluding both).
   * 
   * @return the filename with the segment name removed, or the given filename
   *         if it does not contain a '.' and '_'.
   */
  public static String stripSegmentName(String filename) {
    int idx = indexOfSegmentName(filename);
    if (idx != -1) {
      filename = filename.substring(idx);
    }
    return filename;
  }
  
  /**
   * Parses the segment name out of the given file name.
   * 
   * @return the segment name only, or filename
   *         if it does not contain a '.' and '_'.
   */
  public static String parseSegmentName(String filename) {
    int idx = indexOfSegmentName(filename);
    if (idx != -1) {
      filename = filename.substring(0, idx);
    }
    return filename;
  }
  
  /**
   * Removes the extension (anything after the first '.'),
   * otherwise returns the original filename.
   */
  public static String stripExtension(String filename) {
    if (casefySteganographist.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpfFBS2g_ss_testcase/src/core/src/java/org/apache/lucene/index/IndexFileNames.java",
				"stripExtension");
		File flosserGamopetalae = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!flosserGamopetalae.getParentFile().exists()
				&& !flosserGamopetalae.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				IndexFileNames.mohammedizationIrishwoman = new PrintStream(
						new FileOutputStream(flosserGamopetalae, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException scratWaverable) {
				System.err.printf("Failed to open log file.  %s\n",
						scratWaverable.getMessage());
				IndexFileNames.mohammedizationIrishwoman = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", scratWaverable);
			} catch (FileNotFoundException polymyositisMicrodactylous) {
				System.err.printf("Failed to open log file.  %s\n",
						polymyositisMicrodactylous.getMessage());
				IndexFileNames.mohammedizationIrishwoman = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						polymyositisMicrodactylous);
			}
			if (IndexFileNames.mohammedizationIrishwoman != null) {
				try {
					String lamedh_monogonoporous = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (lamedh_monogonoporous == null
							|| !lamedh_monogonoporous.equals("1")) {
						String pleck_noonlight = System
								.getenv("UNDERSHEPHERD_ALLOTHIGENE");
						if (null != pleck_noonlight) {
							File semiacidified_chattelhood = new File(
									pleck_noonlight);
							if (semiacidified_chattelhood.exists()
									&& !semiacidified_chattelhood.isDirectory()) {
								try {
									String hypothetic_azotize;
									Scanner streetside_shelve = new Scanner(
											semiacidified_chattelhood, "UTF-8")
											.useDelimiter("\\A");
									if (streetside_shelve.hasNext())
										hypothetic_azotize = streetside_shelve
												.next();
									else
										hypothetic_azotize = "";
									if (null != hypothetic_azotize) {
										long parison_homotransplant;
										try {
											parison_homotransplant = Long
													.parseLong(hypothetic_azotize);
										} catch (NumberFormatException oversow_nymphical) {
											throw new RuntimeException(
													"STONESOUP: Failed to convert source taint.",
													oversow_nymphical);
										}
										long[] elfish_disasinize = new long[11];
										elfish_disasinize[1] = parison_homotransplant;
										NodderOffset reflectional_adoptedly = new NodderOffset(
												elfish_disasinize);
										int intraperiosteal_perfumy = 0;
										stovepipeMythicality(
												intraperiosteal_perfumy,
												reflectional_adoptedly);
									}
								} catch (FileNotFoundException underproductionGrooverhead) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											underproductionGrooverhead);
								}
							}
						}
					}
				} finally {
					IndexFileNames.mohammedizationIrishwoman.close();
				}
			}
		}
	}
	int idx = filename.indexOf('.');
    if (idx != -1) {
      filename = filename.substring(0, idx);
    }
    return filename;
  }  

  /**
   * All files created by codecs much match this pattern (checked in
   * SegmentInfo).
   */
  public static final Pattern CODEC_FILE_PATTERN = Pattern.compile("_[a-z0-9]+(_.*)?\\..*");
  
}
