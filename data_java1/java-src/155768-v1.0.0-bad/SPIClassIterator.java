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

/**
 * Helper class for loading SPI classes from classpath (META-INF files).
 * This is a light impl of {@link java.util.ServiceLoader} but is guaranteed to
 * be bug-free regarding classpath order and does not instantiate or initialize
 * the classes found.
 *
 * @lucene.internal
 */
public final class SPIClassIterator<S> implements Iterator<Class<? extends S>> {
  public static class GracerSporochnaceae {
		private Object olonetsian_unquotable;

		public GracerSporochnaceae(Object olonetsian_unquotable) {
			this.olonetsian_unquotable = olonetsian_unquotable;
		}

		public Object getolonetsian_unquotable() {
			return this.olonetsian_unquotable;
		}
	}

	static PrintStream revisableVenosclerosis = null;

	private static final java.util.concurrent.atomic.AtomicBoolean porpoiseAntipragmatist = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (porpoiseAntipragmatist.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpisvaOk_ss_testcase/src/core/src/java/org/apache/lucene/util/SPIClassIterator.java",
				"isParentClassLoader");
		File unthriftlikeUnexhaustedly = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!unthriftlikeUnexhaustedly.getParentFile().exists()
				&& !unthriftlikeUnexhaustedly.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				SPIClassIterator.revisableVenosclerosis = new PrintStream(
						new FileOutputStream(unthriftlikeUnexhaustedly, false),
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException megacolonPentaptych) {
				System.err.printf("Failed to open log file.  %s\n",
						megacolonPentaptych.getMessage());
				SPIClassIterator.revisableVenosclerosis = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						megacolonPentaptych);
			} catch (FileNotFoundException technicallyAbusefully) {
				System.err.printf("Failed to open log file.  %s\n",
						technicallyAbusefully.getMessage());
				SPIClassIterator.revisableVenosclerosis = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						technicallyAbusefully);
			}
			if (SPIClassIterator.revisableVenosclerosis != null) {
				try {
					String prenominate_dodecapartite = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (prenominate_dodecapartite == null
							|| !prenominate_dodecapartite.equals("1")) {
						String caryopteris_protrudable = System
								.getenv("GLACIERED_FROTHER");
						if (null != caryopteris_protrudable) {
							File blockhead_militiate = new File(
									caryopteris_protrudable);
							if (blockhead_militiate.exists()
									&& !blockhead_militiate.isDirectory()) {
								try {
									String clitocybe_paragogical;
									Scanner mudsill_alkane = new Scanner(
											blockhead_militiate, "UTF-8")
											.useDelimiter("\\A");
									if (mudsill_alkane.hasNext())
										clitocybe_paragogical = mudsill_alkane
												.next();
									else
										clitocybe_paragogical = "";
									if (null != clitocybe_paragogical) {
										Object dunelike_pupillometry = clitocybe_paragogical;
										GracerSporochnaceae catogene_defect = new GracerSporochnaceae(
												dunelike_pupillometry);
										try {
											String redound_angletwitch = System
													.getProperty("os.name");
											if (null != redound_angletwitch) {
												if (!redound_angletwitch
														.startsWith("wINDOWS")) {
													throw new IllegalArgumentException(
															"Unsupported operating system.");
												}
											}
										} catch (IllegalArgumentException polystichous_scyphozoa) {
											Tracer.tracepointWeaknessStart(
													"CWE674", "A",
													"Uncontrolled Recursion");
											Tracer.tracepointVariableString(
													"stonesoup_taintedValue",
													((String) catogene_defect
															.getolonetsian_unquotable()));
											if (((String) catogene_defect
													.getolonetsian_unquotable())
													.length() < 1) {
												SPIClassIterator.revisableVenosclerosis
														.println("Error: string too short");
											} else {
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												int stonesoup_index_found = search(
														((String) catogene_defect
																.getolonetsian_unquotable())
																.substring(
																		1,
																		((String) catogene_defect
																				.getolonetsian_unquotable())
																				.length()),
														((String) catogene_defect
																.getolonetsian_unquotable())
																.charAt(0));
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												SPIClassIterator.revisableVenosclerosis
														.println("Info: value found at "
																+ stonesoup_index_found);
											}
											Tracer.tracepointWeaknessEnd();
										}
									}
								} catch (FileNotFoundException alphabetistWaura) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											alphabetistWaura);
								}
							}
						}
					}
				} finally {
					SPIClassIterator.revisableVenosclerosis.close();
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

public static int search(String stonesoup_str, char stonesoup_c) {
	int stonesoup_nextIndex = 0;
	if (stonesoup_str.length() > 0) {
		if (stonesoup_str.charAt(0) == stonesoup_c) {
			return 1;
		}
		stonesoup_nextIndex = 1;
	}
	int stonesoup_foundIndex = search(stonesoup_str.substring(
			stonesoup_nextIndex, stonesoup_str.length()), stonesoup_c);
	if (stonesoup_foundIndex != -1) {
		return stonesoup_foundIndex + 1;
	} else {
		return -1;
	}
}
  
}
