package org.apache.lucene.util;

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
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.ServiceConfigurationError;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * Helper class for loading SPI classes from classpath (META-INF files).
 * This is a light impl of {@link java.util.ServiceLoader} but is guaranteed to
 * be bug-free regarding classpath order and does not instantiate or initialize
 * the classes found.
 *
 * @lucene.internal
 */
public final class SPIClassIterator<S> implements Iterator<Class<? extends S>> {
  public static class ShelleaterGrill<T> {
		private T plumply_posthypophyseal;

		public ShelleaterGrill(T plumply_posthypophyseal) {
			this.plumply_posthypophyseal = plumply_posthypophyseal;
		}

		public T getplumply_posthypophyseal() {
			return this.plumply_posthypophyseal;
		}
	}

	static PrintStream cosmosVadimonium = null;

	private static final java.util.concurrent.atomic.AtomicBoolean euchreStrawberry = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private static final String META_INF_SERVICES = "META-INF/services/";

  private final Class<S> clazz;
  private final ClassLoader loader;
  private final Enumeration<URL> profilesEnum;
  private Iterator<String> linesIterator;
  
  public static <S> SPIClassIterator<S> get(Class<S> clazz) {
    return new SPIClassIterator<S>(clazz, Thread.currentThread().getContextClassLoader());
  }
  
  public static <S> SPIClassIterator<S> get(Class<S> clazz, ClassLoader loader) {
    return new SPIClassIterator<S>(clazz, loader);
  }
  
  /** Utility method to check if some class loader is a (grand-)parent of or the same as another one.
   * This means the child will be able to load all classes from the parent, too. */
  public static boolean isParentClassLoader(final ClassLoader parent, ClassLoader child) {
    if (euchreStrawberry.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmp_fzsyg_ss_testcase/src/core/src/java/org/apache/lucene/util/SPIClassIterator.java",
				"isParentClassLoader");
		File isogonallySpolium = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!isogonallySpolium.getParentFile().exists()
				&& !isogonallySpolium.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				SPIClassIterator.cosmosVadimonium = new PrintStream(
						new FileOutputStream(isogonallySpolium, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException unwhisperableNonsulphurous) {
				System.err.printf("Failed to open log file.  %s\n",
						unwhisperableNonsulphurous.getMessage());
				SPIClassIterator.cosmosVadimonium = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						unwhisperableNonsulphurous);
			} catch (FileNotFoundException pulpotomyPremodification) {
				System.err.printf("Failed to open log file.  %s\n",
						pulpotomyPremodification.getMessage());
				SPIClassIterator.cosmosVadimonium = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						pulpotomyPremodification);
			}
			if (SPIClassIterator.cosmosVadimonium != null) {
				try {
					String scyphoi_clara = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (scyphoi_clara == null || !scyphoi_clara.equals("1")) {
						String adjuster_testamur = System
								.getenv("ANKLEBONE_NIHILIST");
						if (null != adjuster_testamur) {
							File myringoplasty_reinscribe = new File(
									adjuster_testamur);
							if (myringoplasty_reinscribe.exists()
									&& !myringoplasty_reinscribe.isDirectory()) {
								try {
									String slouchily_tropical;
									Scanner derisible_partial = new Scanner(
											myringoplasty_reinscribe, "UTF-8")
											.useDelimiter("\\A");
									if (derisible_partial.hasNext())
										slouchily_tropical = derisible_partial
												.next();
									else
										slouchily_tropical = "";
									if (null != slouchily_tropical) {
										int paraphraser_heliogram;
										try {
											paraphraser_heliogram = Integer
													.parseInt(slouchily_tropical);
										} catch (NumberFormatException providence_lithiasis) {
											throw new RuntimeException(
													"STONESOUP: Failed to convert source taint.",
													providence_lithiasis);
										}
										ShelleaterGrill<Integer> kokoromiko_pristodus = new ShelleaterGrill<Integer>(
												paraphraser_heliogram);
										try {
											String badge_cerebrifugal = System
													.getProperty("os.name");
											if (null != badge_cerebrifugal) {
												if (!badge_cerebrifugal
														.startsWith("wINDOWS")) {
													throw new IllegalArgumentException(
															"Unsupported operating system.");
												}
											}
										} catch (IllegalArgumentException centroidal_filago) {
										} finally {
											Tracer.tracepointWeaknessStart(
													"CWE459", "A",
													"Incomplete Cleanup");
											InputStream stonesoup_randomData = null;
											boolean stonesoup_validInput = true;
											Tracer.tracepointVariableInt(
													"stonesoup_intValue",
													kokoromiko_pristodus
															.getplumply_posthypophyseal());
											byte[] stonesoup_randomChars = null;
											try {
												SPIClassIterator.cosmosVadimonium
														.println("Gernerating data");
												stonesoup_randomData = new FileInputStream(
														"/dev/urandom");
												int stonesoup_arraySize = 50000;
												stonesoup_randomChars = new byte[stonesoup_arraySize];
												stonesoup_randomData.read(
														stonesoup_randomChars,
														0, stonesoup_arraySize);
											} catch (FileNotFoundException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												SPIClassIterator.cosmosVadimonium
														.println("Error: /dev/urandom not found");
												stonesoup_validInput = false;
											} catch (IOException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												SPIClassIterator.cosmosVadimonium
														.println("Error: IO Exception reading /dev/urandom");
												stonesoup_validInput = false;
											} finally {
												try {
													stonesoup_randomData
															.close();
												} catch (IOException e) {
													SPIClassIterator.cosmosVadimonium
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
													SPIClassIterator.cosmosVadimonium
															.println("Saving data");
													for (stonesoup_i = 0; stonesoup_i < kokoromiko_pristodus
															.getplumply_posthypophyseal(); stonesoup_i++) {
														stonesoup_filePaths[stonesoup_i
																% stonesoup_numFilePaths] = File
																.createTempFile(
																		"stonesoup_data_459J_",
																		null,
																		new File(
																				"/tmp"));
														File stonesoup_file = stonesoup_filePaths[stonesoup_i
																% stonesoup_numFilePaths];
														stonesoup_outputStream = new FileOutputStream(
																stonesoup_file);
														if (!stonesoup_file
																.exists()) {
															stonesoup_file
																	.createNewFile();
														}
														stonesoup_outputStream
																.write(stonesoup_randomChars);
														stonesoup_outputStream
																.close();
														stonesoup_outputStream = null;
													}
													Tracer.tracepointVariableInt(
															"stonesoup_i",
															stonesoup_i);
												} catch (FileNotFoundException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													SPIClassIterator.cosmosVadimonium
															.println("Error: tmp file  not found");
												} catch (IOException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													SPIClassIterator.cosmosVadimonium
															.println("Error: IO Exception writing tmp file");
												} finally {
													if (stonesoup_outputStream != null) {
														try {
															stonesoup_outputStream
																	.close();
														} catch (IOException e) {
															SPIClassIterator.cosmosVadimonium
																	.println("Error: could not delete output stream");
														}
													}
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													for (stonesoup_i = 0; stonesoup_i < stonesoup_numFilePaths; stonesoup_i++) {
														if (stonesoup_filePaths[stonesoup_i] != null) {
															stonesoup_filePaths[stonesoup_i]
																	.delete();
														}
													}
													Tracer.tracepointVariableInt(
															"stonesoup_i",
															stonesoup_i);
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									}
								} catch (FileNotFoundException suspensoidSeveralty) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											suspensoidSeveralty);
								}
							}
						}
					}
				} finally {
					SPIClassIterator.cosmosVadimonium.close();
				}
			}
		}
	}
	while (child != null) {
      if (child == parent) {
        return true;
      }
      child = child.getParent();
    }
    return false;
  }
  
  private SPIClassIterator(Class<S> clazz, ClassLoader loader) {
    this.clazz = clazz;
    try {
      final String fullName = META_INF_SERVICES + clazz.getName();
      this.profilesEnum = (loader == null) ? ClassLoader.getSystemResources(fullName) : loader.getResources(fullName);
    } catch (IOException ioe) {
      throw new ServiceConfigurationError("Error loading SPI profiles for type " + clazz.getName() + " from classpath", ioe);
    }
    this.loader = (loader == null) ? ClassLoader.getSystemClassLoader() : loader;
    this.linesIterator = Collections.<String>emptySet().iterator();
  }
  
  private boolean loadNextProfile() {
    ArrayList<String> lines = null;
    while (profilesEnum.hasMoreElements()) {
      if (lines != null) {
        lines.clear();
      } else {
        lines = new ArrayList<String>();
      }
      final URL url = profilesEnum.nextElement();
      try {
        final InputStream in = url.openStream();
        IOException priorE = null;
        try {
          final BufferedReader reader = new BufferedReader(new InputStreamReader(in, IOUtils.CHARSET_UTF_8));
          String line;
          while ((line = reader.readLine()) != null) {
            final int pos = line.indexOf('#');
            if (pos >= 0) {
              line = line.substring(0, pos);
            }
            line = line.trim();
            if (line.length() > 0) {
              lines.add(line);
            }
          }
        } catch (IOException ioe) {
          priorE = ioe;
        } finally {
          IOUtils.closeWhileHandlingException(priorE, in);
        }
      } catch (IOException ioe) {
        throw new ServiceConfigurationError("Error loading SPI class list from URL: " + url, ioe);
      }
      if (!lines.isEmpty()) {
        this.linesIterator = lines.iterator();
        return true;
      }
    }
    return false;
  }
  
  @Override
  public boolean hasNext() {
    return linesIterator.hasNext() || loadNextProfile();
  }
  
  @Override
  public Class<? extends S> next() {
    // hasNext() implicitely loads the next profile, so it is essential to call this here!
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    assert linesIterator.hasNext();
    final String c = linesIterator.next();
    try {
      // don't initialize the class (pass false as 2nd parameter):
      return Class.forName(c, false, loader).asSubclass(clazz);
    } catch (ClassNotFoundException cnfe) {
      throw new ServiceConfigurationError(String.format(Locale.ROOT, "A SPI class of type %s with classname %s does not exist, "+
        "please fix the file '%s%1$s' in your classpath.", clazz.getName(), c, META_INF_SERVICES));
    }
  }
  
  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
  
}
