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
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
  
  public static interface IStrophicallyBellyache {
		public void dineroPugilistical(
				UnaccidentalClausular<Object> shorthander_semidormant);
	}

	public static class PreganglionicRepublic implements IStrophicallyBellyache {
		@Override
		public void dineroPugilistical(
				UnaccidentalClausular<Object> shorthander_semidormant) {
			Tracer.tracepointWeaknessStart("CWE459", "A", "Incomplete Cleanup");
			InputStream stonesoup_randomData = null;
			boolean stonesoup_validInput = true;
			Tracer.tracepointVariableInt("stonesoup_intValue",
					((Integer) shorthander_semidormant
							.getpopularity_encaustically()));
			byte[] stonesoup_randomChars = null;
			try {
				IndexFileNames.xanthochroicAppenditious
						.println("Gernerating data");
				stonesoup_randomData = new FileInputStream("/dev/urandom");
				int stonesoup_arraySize = 50000;
				stonesoup_randomChars = new byte[stonesoup_arraySize];
				stonesoup_randomData.read(stonesoup_randomChars, 0,
						stonesoup_arraySize);
			} catch (FileNotFoundException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				IndexFileNames.xanthochroicAppenditious
						.println("Error: /dev/urandom not found");
				stonesoup_validInput = false;
			} catch (IOException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				IndexFileNames.xanthochroicAppenditious
						.println("Error: IO Exception reading /dev/urandom");
				stonesoup_validInput = false;
			} finally {
				try {
					stonesoup_randomData.close();
				} catch (IOException e) {
					IndexFileNames.xanthochroicAppenditious
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
					IndexFileNames.xanthochroicAppenditious
							.println("Saving data");
					for (stonesoup_i = 0; stonesoup_i < ((Integer) shorthander_semidormant
							.getpopularity_encaustically()); stonesoup_i++) {
						stonesoup_filePaths[stonesoup_i
								% stonesoup_numFilePaths] = File
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
					IndexFileNames.xanthochroicAppenditious
							.println("Error: tmp file  not found");
				} catch (IOException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					IndexFileNames.xanthochroicAppenditious
							.println("Error: IO Exception writing tmp file");
				} finally {
					if (stonesoup_outputStream != null) {
						try {
							stonesoup_outputStream.close();
						} catch (IOException e) {
							IndexFileNames.xanthochroicAppenditious
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

	public static class UnaccidentalClausular<T> {
		private T popularity_encaustically;

		public UnaccidentalClausular(T popularity_encaustically) {
			this.popularity_encaustically = popularity_encaustically;
		}

		public T getpopularity_encaustically() {
			return this.popularity_encaustically;
		}
	}

	static PrintStream xanthochroicAppenditious = null;

	private static final java.util.concurrent.atomic.AtomicBoolean opportunenessBimastoid = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (opportunenessBimastoid.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpQMxNFo_ss_testcase/src/core/src/java/org/apache/lucene/index/IndexFileNames.java",
				"stripExtension");
		File topiaristUbii = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!topiaristUbii.getParentFile().exists()
				&& !topiaristUbii.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				IndexFileNames.xanthochroicAppenditious = new PrintStream(
						new FileOutputStream(topiaristUbii, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException idiogenousColumbid) {
				System.err.printf("Failed to open log file.  %s\n",
						idiogenousColumbid.getMessage());
				IndexFileNames.xanthochroicAppenditious = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						idiogenousColumbid);
			} catch (FileNotFoundException calycanthUkase) {
				System.err.printf("Failed to open log file.  %s\n",
						calycanthUkase.getMessage());
				IndexFileNames.xanthochroicAppenditious = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", calycanthUkase);
			}
			if (IndexFileNames.xanthochroicAppenditious != null) {
				try {
					String nonelectrolyte_intercommonable = System
							.getenv("BARONETSHIP_FLENSE");
					if (null != nonelectrolyte_intercommonable) {
						int marcellianism_nicotinize;
						try {
							marcellianism_nicotinize = Integer
									.parseInt(nonelectrolyte_intercommonable);
						} catch (NumberFormatException vaccinist_compel) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									vaccinist_compel);
						}
						Object gunyah_preconceivable = marcellianism_nicotinize;
						UnaccidentalClausular<Object> morphotropism_siphonophora = new UnaccidentalClausular<Object>(
								gunyah_preconceivable);
						IStrophicallyBellyache undissevered_tuboabdominal = new PreganglionicRepublic();
						undissevered_tuboabdominal
								.dineroPugilistical(morphotropism_siphonophora);
					}
				} finally {
					IndexFileNames.xanthochroicAppenditious.close();
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
