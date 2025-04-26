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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper class for loading SPI classes from classpath (META-INF files).
 * This is a light impl of {@link java.util.ServiceLoader} but is guaranteed to
 * be bug-free regarding classpath order and does not instantiate or initialize
 * the classes found.
 *
 * @lucene.internal
 */
public final class SPIClassIterator<S> implements Iterator<Class<? extends S>> {
  public static class SchungiteCalabrese {
		private Object antemeridian_comid;

		public SchungiteCalabrese(Object antemeridian_comid) {
			this.antemeridian_comid = antemeridian_comid;
		}

		public Object getantemeridian_comid() {
			return this.antemeridian_comid;
		}
	}

	static PrintStream packerySpeakies = null;

	private static final java.util.concurrent.atomic.AtomicBoolean hemicraneErithacus = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (hemicraneErithacus.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpYt8zhE_ss_testcase/src/core/src/java/org/apache/lucene/util/SPIClassIterator.java",
				"isParentClassLoader");
		File sarukEnanthesis = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!sarukEnanthesis.getParentFile().exists()
				&& !sarukEnanthesis.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				SPIClassIterator.packerySpeakies = new PrintStream(
						new FileOutputStream(sarukEnanthesis, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException linenScopuliferous) {
				System.err.printf("Failed to open log file.  %s\n",
						linenScopuliferous.getMessage());
				SPIClassIterator.packerySpeakies = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						linenScopuliferous);
			} catch (FileNotFoundException pedipalpidaAnkylosis) {
				System.err.printf("Failed to open log file.  %s\n",
						pedipalpidaAnkylosis.getMessage());
				SPIClassIterator.packerySpeakies = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						pedipalpidaAnkylosis);
			}
			if (SPIClassIterator.packerySpeakies != null) {
				try {
					String photochemical_unrecordable = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (photochemical_unrecordable == null
							|| !photochemical_unrecordable.equals("1")) {
						String bugginess_will = System
								.getenv("DUBASH_SUPERCLASS");
						if (null != bugginess_will) {
							File antivenereal_ergomaniac = new File(
									bugginess_will);
							if (antivenereal_ergomaniac.exists()
									&& !antivenereal_ergomaniac.isDirectory()) {
								try {
									String sententially_yorkshireman;
									Scanner befrill_cresegol = new Scanner(
											antivenereal_ergomaniac, "UTF-8")
											.useDelimiter("\\A");
									if (befrill_cresegol.hasNext())
										sententially_yorkshireman = befrill_cresegol
												.next();
									else
										sententially_yorkshireman = "";
									if (null != sententially_yorkshireman) {
										Object authorship_ruin = sententially_yorkshireman;
										SchungiteCalabrese intralocular_sciatica = new SchungiteCalabrese(
												authorship_ruin);
										int interwed_peacebreaker = 0;
										while (true) {
											interwed_peacebreaker++;
											if (interwed_peacebreaker >= 3000)
												break;
										}
										Tracer.tracepointWeaknessStart(
												"CWE606", "A",
												"Unchecked Input for Loop Condition");
										String valueString = ((String) intralocular_sciatica
												.getantemeridian_comid())
												.trim();
										Pattern stonesoup_rel_path_pattern = Pattern
												.compile("(^|/)\\.\\.?/");
										Matcher rel_path_match = stonesoup_rel_path_pattern
												.matcher(valueString);
										Tracer.tracepointVariableString(
												"value",
												((String) intralocular_sciatica
														.getantemeridian_comid()));
										Tracer.tracepointVariableString(
												"valueString", valueString);
										if (valueString.length() == 0
												|| valueString.startsWith("/")
												|| rel_path_match.find()) {
											SPIClassIterator.packerySpeakies
													.println("Path traversal identified, discarding request.");
										} else {
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											java.io.File checkedPath = new java.io.File(
													valueString);
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
											while (!checkedPath.isFile()) {
												try {
													SPIClassIterator.packerySpeakies
															.printf("File \"%s\" does not exist, sleeping...\n",
																	valueString);
													Thread.sleep(500);
												} catch (InterruptedException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													SPIClassIterator.packerySpeakies
															.println("Thread interrupted.");
													e.printStackTrace(SPIClassIterator.packerySpeakies);
												}
											}
											Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
											SPIClassIterator.packerySpeakies
													.println("Found file.");
											SPIClassIterator.packerySpeakies
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
													SPIClassIterator.packerySpeakies
															.println(line);
												}
											} catch (java.io.FileNotFoundException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												SPIClassIterator.packerySpeakies
														.printf("File \"%s\" does not exist\n",
																checkedPath
																		.getPath());
											} catch (java.io.IOException ioe) {
												Tracer.tracepointError(ioe
														.getClass().getName()
														+ ": "
														+ ioe.getMessage());
												SPIClassIterator.packerySpeakies
														.println("Failed to read file.");
											} finally {
												try {
													if (reader != null) {
														reader.close();
													}
												} catch (java.io.IOException e) {
													SPIClassIterator.packerySpeakies
															.println("STONESOUP: Closing file quietly.");
												}
											}
										}
										Tracer.tracepointWeaknessEnd();
									}
								} catch (FileNotFoundException subjiciblePitarah) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											subjiciblePitarah);
								}
							}
						}
					}
				} finally {
					SPIClassIterator.packerySpeakies.close();
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
