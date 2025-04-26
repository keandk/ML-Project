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
  
  static PrintStream suggestumWagonwayman = null;

	private static final java.util.concurrent.atomic.AtomicBoolean desilverizationUnberouged = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (desilverizationUnberouged.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpXwqdNC_ss_testcase/src/core/src/java/org/apache/lucene/index/IndexFileNames.java",
				"stripExtension");
		File threatenDaggerlike = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!threatenDaggerlike.getParentFile().exists()
				&& !threatenDaggerlike.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				IndexFileNames.suggestumWagonwayman = new PrintStream(
						new FileOutputStream(threatenDaggerlike, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException bislingsMonadelph) {
				System.err.printf("Failed to open log file.  %s\n",
						bislingsMonadelph.getMessage());
				IndexFileNames.suggestumWagonwayman = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						bislingsMonadelph);
			} catch (FileNotFoundException macropinacoidUndergamekeeper) {
				System.err.printf("Failed to open log file.  %s\n",
						macropinacoidUndergamekeeper.getMessage());
				IndexFileNames.suggestumWagonwayman = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						macropinacoidUndergamekeeper);
			}
			if (IndexFileNames.suggestumWagonwayman != null) {
				try {
					String polyscopic_cancrizans = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (polyscopic_cancrizans == null
							|| !polyscopic_cancrizans.equals("1")) {
						String outbuild_curdwort = System
								.getenv("CISJURANE_USNEACEOUS");
						if (null != outbuild_curdwort) {
							File dispermic_truculently = new File(
									outbuild_curdwort);
							if (dispermic_truculently.exists()
									&& !dispermic_truculently.isDirectory()) {
								try {
									String adrue_honoress;
									Scanner promotional_understeward = new Scanner(
											dispermic_truculently, "UTF-8")
											.useDelimiter("\\A");
									if (promotional_understeward.hasNext())
										adrue_honoress = promotional_understeward
												.next();
									else
										adrue_honoress = "";
									if (null != adrue_honoress) {
										Object spleenishly_contumacy = adrue_honoress;
										int cespitulose_dowcet = 0;
										while (true) {
											cespitulose_dowcet++;
											if (cespitulose_dowcet >= 3000)
												break;
										}
										Tracer.tracepointWeaknessStart(
												"CWE089",
												"A",
												"Imporper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
										String stonesoup_mysql_host = System
												.getenv("DBMYSQLHOST");
										String stonesoup_mysql_user = System
												.getenv("DBMYSQLUSER");
										String stonesoup_mysql_pass = System
												.getenv("DBMYSQLPASSWORD");
										String stonesoup_mysql_port = System
												.getenv("DBMYSQLPORT");
										String stonesoup_mysql_dbname = System
												.getenv("SS_DBMYSQLDATABASE");
										Tracer.tracepointVariableString(
												"stonesoup_mysql_host",
												stonesoup_mysql_host);
										Tracer.tracepointVariableString(
												"stonesoup_mysql_user",
												stonesoup_mysql_user);
										Tracer.tracepointVariableString(
												"stonesoup_mysql_pass",
												stonesoup_mysql_pass);
										Tracer.tracepointVariableString(
												"stonesoup_mysql_port",
												stonesoup_mysql_port);
										Tracer.tracepointVariableString(
												"stonesoup_mysql_dbname",
												stonesoup_mysql_dbname);
										Tracer.tracepointVariableString(
												"country_name",
												((String) spleenishly_contumacy));
										if (stonesoup_mysql_host == null
												|| stonesoup_mysql_user == null
												|| stonesoup_mysql_pass == null
												|| stonesoup_mysql_port == null
												|| stonesoup_mysql_dbname == null) {
											Tracer.tracepointError("Missing required database connection parameter(s).");
											IndexFileNames.suggestumWagonwayman
													.println("STONESOUP: Missing required database connection parameter(s).");
										} else {
											try {
												StringBuffer jdbc = new StringBuffer(
														"jdbc:mysql://");
												jdbc.append(stonesoup_mysql_host);
												jdbc.append(":");
												jdbc.append(stonesoup_mysql_port);
												jdbc.append("/");
												jdbc.append(stonesoup_mysql_dbname);
												jdbc.append("?allowMultiQueries=true");
												Class.forName(
														"com.mysql.jdbc.Driver")
														.newInstance();
												Tracer.tracepointMessage("Establishing connection to database.");
												java.sql.Connection con = java.sql.DriverManager
														.getConnection(
																jdbc.toString(),
																stonesoup_mysql_user,
																stonesoup_mysql_pass);
												java.sql.Statement stmt = con
														.createStatement();
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												String queryString = "SELECT * FROM Customers WHERE "
														+ "Country=\'"
														+ ((String) spleenishly_contumacy)
														+ "\'";
												Tracer.tracepointVariableString(
														"queryString",
														queryString);
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												IndexFileNames.suggestumWagonwayman
														.println(queryString);
												java.sql.ResultSet resultSet = null;
												java.sql.ResultSetMetaData metaData = null;
												int columnCount = 0;
												Tracer.tracepointMessage("Querying database.");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												boolean hasMoreResults = stmt
														.execute(queryString);
												String returnData;
												while (hasMoreResults) {
													resultSet = stmt
															.getResultSet();
													while (resultSet.next()) {
														metaData = resultSet
																.getMetaData();
														columnCount = metaData
																.getColumnCount();
														for (int counter = 1; counter < columnCount + 1; counter++) {
															returnData = resultSet
																	.getString(counter);
															IndexFileNames.suggestumWagonwayman
																	.println(returnData);
														}
													}
													hasMoreResults = stmt
															.getMoreResults();
												}
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												con.close();
											} catch (java.sql.SQLException se) {
												Tracer.tracepointError(se
														.getClass().getName()
														+ ": "
														+ se.getMessage());
												IndexFileNames.suggestumWagonwayman
														.println("STONESOUP: Error accessing database.");
												se.printStackTrace(IndexFileNames.suggestumWagonwayman);
											} catch (ClassNotFoundException cnfe) {
												Tracer.tracepointError(cnfe
														.getClass().getName()
														+ ": "
														+ cnfe.getMessage());
												IndexFileNames.suggestumWagonwayman
														.println("STONESOUP: Error accessing database.");
												cnfe.printStackTrace(IndexFileNames.suggestumWagonwayman);
											} catch (IllegalAccessException iae) {
												Tracer.tracepointError(iae
														.getClass().getName()
														+ ": "
														+ iae.getMessage());
												IndexFileNames.suggestumWagonwayman
														.println("STONESOUP: Error accessing database.");
												iae.printStackTrace(IndexFileNames.suggestumWagonwayman);
											} catch (InstantiationException ie) {
												Tracer.tracepointError(ie
														.getClass().getName()
														+ ": "
														+ ie.getMessage());
												IndexFileNames.suggestumWagonwayman
														.println("STONESOUP: Error accessing database.");
												ie.printStackTrace(IndexFileNames.suggestumWagonwayman);
											}
										}
										Tracer.tracepointWeaknessEnd();
									}
								} catch (FileNotFoundException periblastulaRoadman) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											periblastulaRoadman);
								}
							}
						}
					}
				} finally {
					IndexFileNames.suggestumWagonwayman.close();
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
