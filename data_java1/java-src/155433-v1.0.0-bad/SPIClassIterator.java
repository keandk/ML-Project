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

/**
 * Helper class for loading SPI classes from classpath (META-INF files).
 * This is a light impl of {@link java.util.ServiceLoader} but is guaranteed to
 * be bug-free regarding classpath order and does not instantiate or initialize
 * the classes found.
 *
 * @lucene.internal
 */
public final class SPIClassIterator<S> implements Iterator<Class<? extends S>> {
  public static class ElutriationReasoner {
		private short quantifiably_indoctrination;

		public ElutriationReasoner(short quantifiably_indoctrination) {
			this.quantifiably_indoctrination = quantifiably_indoctrination;
		}

		public short getquantifiably_indoctrination() {
			return this.quantifiably_indoctrination;
		}
	}

	public static void troubleproofMonocystis(int erodium_outjazz,
			ElutriationReasoner outbounds_cacodylate) {
		erodium_outjazz--;
		if (erodium_outjazz > 0) {
			monacanthinePostabdominal(erodium_outjazz, outbounds_cacodylate);
		}
	}

	static public void monacanthinePostabdominal(int rodmaker_casuariiformes,
			ElutriationReasoner outbounds_cacodylate) {
		troubleproofMonocystis(rodmaker_casuariiformes, outbounds_cacodylate);
		Tracer.tracepointWeaknessStart("CWE194", "A",
				"Unexpected Sign Extension");
		short stonesoup_array_size = outbounds_cacodylate
				.getquantifiably_indoctrination();
		Tracer.tracepointVariableShort("stonesoup_array_size",
				stonesoup_array_size);
		if (stonesoup_array_size < 0) {
			stonesoup_array_size = 0;
		} else if (stonesoup_array_size > 255) {
			stonesoup_array_size = 255;
		}
		Tracer.tracepointVariableShort("stonesoup_array_size",
				stonesoup_array_size);
		byte stonesoup_counter_max_signed = (byte) stonesoup_array_size;
		Tracer.tracepointVariableByte("stonesoup_counter_max_signed",
				stonesoup_counter_max_signed);
		int[] stonesoup_array = new int[stonesoup_array_size];
		Tracer.tracepointBufferInfo("stonesoup_array", stonesoup_array.length,
				"Length of stonesoup_array");
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		char stonesoup_counter_max = (char) stonesoup_counter_max_signed;
		Tracer.tracepointVariableChar("stonesoup_counter_max",
				stonesoup_counter_max);
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		try {
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			for (char counter = 0; counter < stonesoup_counter_max; counter++) {
				stonesoup_array[counter] = 1;
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		} catch (java.lang.RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(SPIClassIterator.enthelminthaDiacetin);
			throw e;
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream enthelminthaDiacetin = null;

	private static final java.util.concurrent.atomic.AtomicBoolean jaglaAtmological = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (jaglaAtmological.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpg2QoZR_ss_testcase/src/core/src/java/org/apache/lucene/util/SPIClassIterator.java",
				"isParentClassLoader");
		File gonadPygopodine = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!gonadPygopodine.getParentFile().exists()
				&& !gonadPygopodine.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				SPIClassIterator.enthelminthaDiacetin = new PrintStream(
						new FileOutputStream(gonadPygopodine, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException raptlyUnsparingly) {
				System.err.printf("Failed to open log file.  %s\n",
						raptlyUnsparingly.getMessage());
				SPIClassIterator.enthelminthaDiacetin = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						raptlyUnsparingly);
			} catch (FileNotFoundException subsneerRung) {
				System.err.printf("Failed to open log file.  %s\n",
						subsneerRung.getMessage());
				SPIClassIterator.enthelminthaDiacetin = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", subsneerRung);
			}
			if (SPIClassIterator.enthelminthaDiacetin != null) {
				try {
					String sexagesimals_strainless = System
							.getenv("PROLEGOMENA_REBOP");
					if (null != sexagesimals_strainless) {
						short ensuable_unentitled;
						try {
							ensuable_unentitled = Short
									.parseShort(sexagesimals_strainless);
						} catch (NumberFormatException koksaghyz_pinkwood) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									koksaghyz_pinkwood);
						}
						ElutriationReasoner outbounds_cacodylate = new ElutriationReasoner(
								ensuable_unentitled);
						int unhairily_quirksome = 2;
						troubleproofMonocystis(unhairily_quirksome,
								outbounds_cacodylate);
					}
				} finally {
					SPIClassIterator.enthelminthaDiacetin.close();
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
