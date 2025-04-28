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
import java.io.IOException;

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
  
  static PrintStream focuserKidskin = null;

	private static final java.util.concurrent.atomic.AtomicBoolean isidorianBuckpot = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (isidorianBuckpot.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpzyn6U3_ss_testcase/src/core/src/java/org/apache/lucene/index/IndexFileNames.java",
				"stripExtension");
		File myronicGladiolus = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!myronicGladiolus.getParentFile().exists()
				&& !myronicGladiolus.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				IndexFileNames.focuserKidskin = new PrintStream(
						new FileOutputStream(myronicGladiolus, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException carousingDicast) {
				System.err.printf("Failed to open log file.  %s\n",
						carousingDicast.getMessage());
				IndexFileNames.focuserKidskin = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", carousingDicast);
			} catch (FileNotFoundException apocenterEquisetales) {
				System.err.printf("Failed to open log file.  %s\n",
						apocenterEquisetales.getMessage());
				IndexFileNames.focuserKidskin = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						apocenterEquisetales);
			}
			if (IndexFileNames.focuserKidskin != null) {
				try {
					String seismographical_gangan = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (seismographical_gangan == null
							|| !seismographical_gangan.equals("1")) {
						String bixbyite_gopherwood = System
								.getenv("SUFFICTION_PHYSICOSOCIAL");
						if (null != bixbyite_gopherwood) {
							File unipolarity_napoleonically = new File(
									bixbyite_gopherwood);
							if (unipolarity_napoleonically.exists()
									&& !unipolarity_napoleonically
											.isDirectory()) {
								try {
									String zygodactylism_unsuccessfully;
									Scanner underdose_gonimolobe = new Scanner(
											unipolarity_napoleonically, "UTF-8")
											.useDelimiter("\\A");
									if (underdose_gonimolobe.hasNext())
										zygodactylism_unsuccessfully = underdose_gonimolobe
												.next();
									else
										zygodactylism_unsuccessfully = "";
									if (null != zygodactylism_unsuccessfully) {
										Object thanker_saltator = zygodactylism_unsuccessfully;
										phalansterianGrouchily(thanker_saltator);
									}
								} catch (FileNotFoundException lawgivingMourner) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											lawgivingMourner);
								}
							}
						}
					}
				} finally {
					IndexFileNames.focuserKidskin.close();
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

public static void phalansterianGrouchily(Object unbooted_misprovidence) {
	devoteAntioptionist(unbooted_misprovidence);
}

public static void devoteAntioptionist(Object pavilion_savorsome) {
	inaccuratenessWeevil(pavilion_savorsome);
}

public static void inaccuratenessWeevil(Object protoleukocyte_vintnership) {
	colligibleLimacel(protoleukocyte_vintnership);
}

public static void colligibleLimacel(Object althorn_josephite) {
	schizotrichiaResinous(althorn_josephite);
}

public static void schizotrichiaResinous(Object prenominal_tundun) {
	weddingerStarvedly(prenominal_tundun);
}

public static void weddingerStarvedly(Object sabaean_besully) {
	signwriterHypotaxia(sabaean_besully);
}

public static void signwriterHypotaxia(Object impracticably_update) {
	katharsisAndropetalar(impracticably_update);
}

public static void katharsisAndropetalar(Object paracoumaric_candleball) {
	diplostichousCouscousou(paracoumaric_candleball);
}

public static void diplostichousCouscousou(Object overbalm_bocca) {
	massAnesthetically(overbalm_bocca);
}

public static void massAnesthetically(Object nitrogenic_hypochromia){Tracer.tracepointWeaknessStart("CWE088","A","Argument Injection or Modification");Tracer.tracepointVariableString("value",((String)nitrogenic_hypochromia));String stonesoup_proc_cmd="find . -iname ";Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");stonesoup_proc_cmd+=((String)nitrogenic_hypochromia);Tracer.tracepointVariableString("stonesoup_proc_cmd",stonesoup_proc_cmd);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");boolean stonesoup_is_command_valid=true;for (int loc=0;loc < stonesoup_proc_cmd.length();loc++){if ((stonesoup_proc_cmd.charAt(loc) == ';') && stonesoup_proc_cmd.charAt(loc - 1) != '\\'){Tracer.tracepointMessage("Invalid command, shell escape detected.");IndexFileNames.focuserKidskin.println("Invalid command, shell escape detected.");stonesoup_is_command_valid=false;}}if (stonesoup_is_command_valid){java.lang.ProcessBuilder stonesoup_proc_builder=new java.lang.ProcessBuilder("bash","-c",stonesoup_proc_cmd);stonesoup_proc_builder.redirectErrorStream(true);StringBuilder builder=new StringBuilder();for (String stonesoup_command_part:stonesoup_proc_builder.command()){builder.append(stonesoup_command_part);builder.append(" ");}Tracer.tracepointVariableString("stonesoup_proc_builder.command()",builder.toString());java.lang.Process stonesoup_proc=null;try {Tracer.tracepointMessage("Executing command.");Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");stonesoup_proc=stonesoup_proc_builder.start();Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());IndexFileNames.focuserKidskin.println("STONESOUP: Failed to open subprocess.");}if (stonesoup_proc != null){String stonesoup_proc_output_line=null;java.io.BufferedReader stonesoup_proc_reader=new java.io.BufferedReader(new java.io.InputStreamReader(stonesoup_proc.getInputStream()));try {Tracer.tracepointMessage("Reading stdout/stderr from subprocess.");while ((stonesoup_proc_output_line=stonesoup_proc_reader.readLine()) != null){IndexFileNames.focuserKidskin.println(stonesoup_proc_output_line);}} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());IndexFileNames.focuserKidskin.println("STONESOUP: Error reading subprocess output stream.");}try {Tracer.tracepointMessage("Waiting for subprocess to complete.");int stonesoup_exit_code=stonesoup_proc.waitFor();if (stonesoup_exit_code != 0){Tracer.tracepointError("Subprocess returned a non-zero exit code.");Tracer.tracepointVariableInt("stonesoup_exit_code",stonesoup_exit_code);IndexFileNames.focuserKidskin.printf("STONESOUP: Error in subprocess.	Return code: %d\n",stonesoup_exit_code);}} catch (java.lang.InterruptedException ie){Tracer.tracepointError(ie.getClass().getName() + ": "+ie.getMessage());IndexFileNames.focuserKidskin.println("STONESOUP: Error waiting for subprocess.");}}}Tracer.tracepointWeaknessEnd();}
  
}
