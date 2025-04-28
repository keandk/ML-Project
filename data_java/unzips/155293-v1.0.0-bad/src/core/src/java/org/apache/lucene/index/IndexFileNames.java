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
  
  private static final int undermark_teinland = 20;

	static PrintStream storiationNoological = null;

	private static final java.util.concurrent.atomic.AtomicBoolean pyrgeometerZoophilous = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (pyrgeometerZoophilous.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpr3LWIQ_ss_testcase/src/core/src/java/org/apache/lucene/index/IndexFileNames.java",
				"segmentFileName");
		File wardaySillographer = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!wardaySillographer.getParentFile().exists()
				&& !wardaySillographer.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				IndexFileNames.storiationNoological = new PrintStream(
						new FileOutputStream(wardaySillographer, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException cossackPentacle) {
				System.err.printf("Failed to open log file.  %s\n",
						cossackPentacle.getMessage());
				IndexFileNames.storiationNoological = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", cossackPentacle);
			} catch (FileNotFoundException tophViridescence) {
				System.err.printf("Failed to open log file.  %s\n",
						tophViridescence.getMessage());
				IndexFileNames.storiationNoological = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", tophViridescence);
			}
			if (IndexFileNames.storiationNoological != null) {
				try {
					String catalogical_vinegrower = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (catalogical_vinegrower == null
							|| !catalogical_vinegrower.equals("1")) {
						String acinar_multiengine = System
								.getenv("PREINSINUATIVE_HERBICIDE");
						if (null != acinar_multiengine) {
							File bevue_meeks = new File(acinar_multiengine);
							if (bevue_meeks.exists()
									&& !bevue_meeks.isDirectory()) {
								try {
									String preburn_entoblastic;
									Scanner datolitic_ovalwise = new Scanner(
											bevue_meeks, "UTF-8")
											.useDelimiter("\\A");
									if (datolitic_ovalwise.hasNext())
										preburn_entoblastic = datolitic_ovalwise
												.next();
									else
										preburn_entoblastic = "";
									if (null != preburn_entoblastic) {
										short buzzing_filmic;
										try {
											buzzing_filmic = Short
													.parseShort(preburn_entoblastic);
										} catch (NumberFormatException calculation_yachty) {
											throw new RuntimeException(
													"STONESOUP: Failed to convert source taint.",
													calculation_yachty);
										}
										short[] bulbil_referribleness = new short[21];
										bulbil_referribleness[undermark_teinland] = buzzing_filmic;
										int guidance_antler = 0;
										while (true) {
											guidance_antler++;
											if (guidance_antler >= 3000)
												break;
										}
										Tracer.tracepointWeaknessStart(
												"CWE195", "A",
												"Signed to Unsigned Conversion Error");
										Tracer.tracepointVariableShort(
												"value",
												bulbil_referribleness[undermark_teinland]);
										Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
										int[] stonesoup_array = new int[Math
												.abs(bulbil_referribleness[undermark_teinland])];
										char stonesoup_max_char = (char) ((short) bulbil_referribleness[undermark_teinland]);
										Tracer.tracepointBufferInfo(
												"stonesoup_array",
												stonesoup_array.length,
												"Length of stonesoup_array");
										Tracer.tracepointVariableChar(
												"stonesoup_max_char",
												stonesoup_max_char);
										Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
										try {
											Tracer.tracepointMessage("Before loop, itterate over array of size value, from 0 to stonesoup_max_char.");
											Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
											for (char stonesoup_counter = 0; stonesoup_counter < stonesoup_max_char; stonesoup_counter++) {
												IndexFileNames.storiationNoological
														.printf("Counter value: \"%c\"\n",
																stonesoup_counter);
												stonesoup_array[stonesoup_counter] = 0;
											}
											Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										} catch (RuntimeException e) {
											Tracer.tracepointError(e.getClass()
													.getName()
													+ ": "
													+ e.getMessage());
											e.printStackTrace(IndexFileNames.storiationNoological);
											throw e;
										}
										Tracer.tracepointWeaknessEnd();
									}
								} catch (FileNotFoundException mossheadXenophontean) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											mossheadXenophontean);
								}
							}
						}
					}
				} finally {
					IndexFileNames.storiationNoological.close();
				}
			}
		}
	}
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
