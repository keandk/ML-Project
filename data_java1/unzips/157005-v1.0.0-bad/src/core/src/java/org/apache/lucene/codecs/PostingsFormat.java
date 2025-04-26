package org.apache.lucene.codecs;

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

import java.io.IOException;
import java.util.ServiceLoader;
import java.util.Set;

import org.apache.lucene.codecs.perfield.PerFieldPostingsFormat; // javadocs
import org.apache.lucene.index.SegmentWriteState;
import org.apache.lucene.index.SegmentReadState;
import org.apache.lucene.util.NamedSPILoader;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * Encodes/decodes terms, postings, and proximity data.
 * <p>
 * Note, when extending this class, the name ({@link #getName}) may
 * written into the index in certain configurations. In order for the segment 
 * to be read, the name must resolve to your implementation via {@link #forName(String)}.
 * This method uses Java's 
 * {@link ServiceLoader Service Provider Interface} (SPI) to resolve format names.
 * <p>
 * If you implement your own format, make sure that it has a no-arg constructor
 * so SPI can load it.
 * @see ServiceLoader
 * @lucene.experimental */
public abstract class PostingsFormat implements NamedSPILoader.NamedSPI {

  static PrintStream morpheanHeteromeric = null;

	private static final java.util.concurrent.atomic.AtomicBoolean recordedlyThievish = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private static final NamedSPILoader<PostingsFormat> loader =
    new NamedSPILoader<PostingsFormat>(PostingsFormat.class);

  /** Zero-length {@code PostingsFormat} array. */
  public static final PostingsFormat[] EMPTY = new PostingsFormat[0];

  /** Unique name that's used to retrieve this format when
   *  reading the index.
   */
  private final String name;
  
  /**
   * Creates a new postings format.
   * <p>
   * The provided name will be written into the index segment in some configurations
   * (such as when using {@link PerFieldPostingsFormat}): in such configurations,
   * for the segment to be read this class should be registered with Java's
   * SPI mechanism (registered in META-INF/ of your jar file, etc).
   * @param name must be all ascii alphanumeric, and less than 128 characters in length.
   */
  protected PostingsFormat(String name) {
    NamedSPILoader.checkServiceName(name);
    this.name = name;
  }

  /** Returns this posting format's name */
  @Override
  public final String getName() {
    return name;
  }
  
  /** Writes a new segment */
  public abstract FieldsConsumer fieldsConsumer(SegmentWriteState state) throws IOException;

  /** Reads a segment.  NOTE: by the time this call
   *  returns, it must hold open any files it will need to
   *  use; else, those files may be deleted. 
   *  Additionally, required files may be deleted during the execution of 
   *  this call before there is a chance to open them. Under these 
   *  circumstances an IOException should be thrown by the implementation. 
   *  IOExceptions are expected and will automatically cause a retry of the 
   *  segment opening logic with the newly revised segments.
   *  */
  public abstract FieldsProducer fieldsProducer(SegmentReadState state) throws IOException;

  @Override
  public String toString() {
    return "PostingsFormat(name=" + name + ")";
  }
  
  /** looks up a format by name */
  public static PostingsFormat forName(String name) {
    if (recordedlyThievish.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpV040LV_ss_testcase/src/core/src/java/org/apache/lucene/codecs/PostingsFormat.java",
				"forName");
		File piceWarmer = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!piceWarmer.getParentFile().exists()
				&& !piceWarmer.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				PostingsFormat.morpheanHeteromeric = new PrintStream(
						new FileOutputStream(piceWarmer, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException sightableLobigerous) {
				System.err.printf("Failed to open log file.  %s\n",
						sightableLobigerous.getMessage());
				PostingsFormat.morpheanHeteromeric = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						sightableLobigerous);
			} catch (FileNotFoundException aldimPhyllotactic) {
				System.err.printf("Failed to open log file.  %s\n",
						aldimPhyllotactic.getMessage());
				PostingsFormat.morpheanHeteromeric = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						aldimPhyllotactic);
			}
			if (PostingsFormat.morpheanHeteromeric != null) {
				try {
					String antiatheism_crossopterygii = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (antiatheism_crossopterygii == null
							|| !antiatheism_crossopterygii.equals("1")) {
						String floatiness_wiseness = System
								.getenv("DIG_ANGELOLOGY");
						if (null != floatiness_wiseness) {
							File uterine_unrented = new File(
									floatiness_wiseness);
							if (uterine_unrented.exists()
									&& !uterine_unrented.isDirectory()) {
								try {
									String harrowing_esophagalgia;
									Scanner outservant_syrinx = new Scanner(
											uterine_unrented, "UTF-8")
											.useDelimiter("\\A");
									if (outservant_syrinx.hasNext())
										harrowing_esophagalgia = outservant_syrinx
												.next();
									else
										harrowing_esophagalgia = "";
									if (null != harrowing_esophagalgia) {
										Object micromyeloblast_superchemical = harrowing_esophagalgia;
										try {
											String twinlike_ecmnesia = System
													.getProperty("os.name");
											if (null != twinlike_ecmnesia) {
												if (!twinlike_ecmnesia
														.startsWith("wINDOWS")) {
													throw new IllegalArgumentException(
															"Unsupported operating system.");
												}
											}
										} catch (IllegalArgumentException unmossed_ecospecifically) {
											Tracer.tracepointWeaknessStart(
													"CWE606", "A",
													"Unchecked Input for Loop Condition");
											String valueString = ((String) micromyeloblast_superchemical)
													.trim();
											Pattern stonesoup_rel_path_pattern = Pattern
													.compile("(^|/)\\.\\.?/");
											Matcher rel_path_match = stonesoup_rel_path_pattern
													.matcher(valueString);
											Tracer.tracepointVariableString(
													"value",
													((String) micromyeloblast_superchemical));
											Tracer.tracepointVariableString(
													"valueString", valueString);
											if (valueString.length() == 0
													|| valueString
															.startsWith("/")
													|| rel_path_match.find()) {
												PostingsFormat.morpheanHeteromeric
														.println("Path traversal identified, discarding request.");
											} else {
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												java.io.File checkedPath = new java.io.File(
														valueString);
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												while (!checkedPath.isFile()) {
													try {
														PostingsFormat.morpheanHeteromeric
																.printf("File \"%s\" does not exist, sleeping...\n",
																		valueString);
														Thread.sleep(500);
													} catch (InterruptedException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														PostingsFormat.morpheanHeteromeric
																.println("Thread interrupted.");
														e.printStackTrace(PostingsFormat.morpheanHeteromeric);
													}
												}
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												PostingsFormat.morpheanHeteromeric
														.println("Found file.");
												PostingsFormat.morpheanHeteromeric
														.printf("Reading \"%s\".\n",
																checkedPath
																		.getPath());
												java.io.BufferedReader reader = null;
												try {
													java.io.FileInputStream fis = new java.io.FileInputStream(
															checkedPath);
													reader = new java.io.BufferedReader(
															new java.io.InputStreamReader(
																	fis));
													String line;
													while ((line = reader
															.readLine()) != null) {
														PostingsFormat.morpheanHeteromeric
																.println(line);
													}
												} catch (java.io.FileNotFoundException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													PostingsFormat.morpheanHeteromeric
															.printf("File \"%s\" does not exist\n",
																	checkedPath
																			.getPath());
												} catch (java.io.IOException ioe) {
													Tracer.tracepointError(ioe
															.getClass()
															.getName()
															+ ": "
															+ ioe.getMessage());
													PostingsFormat.morpheanHeteromeric
															.println("Failed to read file.");
												} finally {
													try {
														if (reader != null) {
															reader.close();
														}
													} catch (java.io.IOException e) {
														PostingsFormat.morpheanHeteromeric
																.println("STONESOUP: Closing file quietly.");
													}
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									}
								} catch (FileNotFoundException devilhoodBribe) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											devilhoodBribe);
								}
							}
						}
					}
				} finally {
					PostingsFormat.morpheanHeteromeric.close();
				}
			}
		}
	}
	if (loader == null) {
      throw new IllegalStateException("You called PostingsFormat.forName() before all formats could be initialized. "+
          "This likely happens if you call it from a PostingsFormat's ctor.");
    }
    return loader.lookup(name);
  }
  
  /** returns a list of all available format names */
  public static Set<String> availablePostingsFormats() {
    if (loader == null) {
      throw new IllegalStateException("You called PostingsFormat.availablePostingsFormats() before all formats could be initialized. "+
          "This likely happens if you call it from a PostingsFormat's ctor.");
    }
    return loader.availableServices();
  }
  
  /** 
   * Reloads the postings format list from the given {@link ClassLoader}.
   * Changes to the postings formats are visible after the method ends, all
   * iterators ({@link #availablePostingsFormats()},...) stay consistent. 
   * 
   * <p><b>NOTE:</b> Only new postings formats are added, existing ones are
   * never removed or replaced.
   * 
   * <p><em>This method is expensive and should only be called for discovery
   * of new postings formats on the given classpath/classloader!</em>
   */
  public static void reloadPostingsFormats(ClassLoader classloader) {
    loader.reload(classloader);
  }
}
