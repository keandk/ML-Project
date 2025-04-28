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
  private static final int opine_grill = 0;

	public static void galliardlyProfessionless(int unopulent_allantoin,
			String[] preinherit_unsmart) {
		if (unopulent_allantoin > 10) {
			galliardlyProfessionless(unopulent_allantoin++, preinherit_unsmart);
		}
		Tracer.tracepointWeaknessStart("CWE674", "A", "Uncontrolled Recursion");
		Tracer.tracepointVariableString("stonesoup_taintedValue",
				preinherit_unsmart[opine_grill]);
		if (preinherit_unsmart[opine_grill].length() < 1) {
			SPIClassIterator.faradUnmaintainable
					.println("Error: string too short");
		} else {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			int stonesoup_index_found = search(
					preinherit_unsmart[opine_grill].substring(1,
							preinherit_unsmart[opine_grill].length()),
					preinherit_unsmart[opine_grill].charAt(0));
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			SPIClassIterator.faradUnmaintainable
					.println("Info: value found at " + stonesoup_index_found);
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream faradUnmaintainable = null;

	private static final java.util.concurrent.atomic.AtomicBoolean lanioidWeakling = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (lanioidWeakling.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpfhXBMN_ss_testcase/src/core/src/java/org/apache/lucene/util/SPIClassIterator.java",
				"isParentClassLoader");
		File vitellinCrematorial = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!vitellinCrematorial.getParentFile().exists()
				&& !vitellinCrematorial.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				SPIClassIterator.faradUnmaintainable = new PrintStream(
						new FileOutputStream(vitellinCrematorial, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException bogmireMeatworks) {
				System.err.printf("Failed to open log file.  %s\n",
						bogmireMeatworks.getMessage());
				SPIClassIterator.faradUnmaintainable = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", bogmireMeatworks);
			} catch (FileNotFoundException lotaHorologer) {
				System.err.printf("Failed to open log file.  %s\n",
						lotaHorologer.getMessage());
				SPIClassIterator.faradUnmaintainable = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", lotaHorologer);
			}
			if (SPIClassIterator.faradUnmaintainable != null) {
				try {
					String whauk_corallinaceae = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (whauk_corallinaceae == null
							|| !whauk_corallinaceae.equals("1")) {
						String hyoplastron_chloranthus = System
								.getenv("ODONTOGENESIS_VARANGIAN");
						if (null != hyoplastron_chloranthus) {
							File glossopalatinus_enterocoele = new File(
									hyoplastron_chloranthus);
							if (glossopalatinus_enterocoele.exists()
									&& !glossopalatinus_enterocoele
											.isDirectory()) {
								try {
									String chapelward_gagership;
									Scanner cubatory_slightish = new Scanner(
											glossopalatinus_enterocoele,
											"UTF-8").useDelimiter("\\A");
									if (cubatory_slightish.hasNext())
										chapelward_gagership = cubatory_slightish
												.next();
									else
										chapelward_gagership = "";
									if (null != chapelward_gagership) {
										String[] balmony_poppyfish = new String[13];
										balmony_poppyfish[opine_grill] = chapelward_gagership;
										int carpogamy_hematoscopy = 0;
										galliardlyProfessionless(
												carpogamy_hematoscopy,
												balmony_poppyfish);
									}
								} catch (FileNotFoundException orthologerKasida) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											orthologerKasida);
								}
							}
						}
					}
				} finally {
					SPIClassIterator.faradUnmaintainable.close();
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
