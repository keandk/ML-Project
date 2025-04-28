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
  public static class SuccinicNephropathy<T> {
		private T cornage_initiatorily;

		public SuccinicNephropathy(T cornage_initiatorily) {
			this.cornage_initiatorily = cornage_initiatorily;
		}

		public T getcornage_initiatorily() {
			return this.cornage_initiatorily;
		}
	}

	static PrintStream jasminaceaeCabbagehead = null;

	private static final java.util.concurrent.atomic.AtomicBoolean oristicOxidimetry = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (oristicOxidimetry.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpdYauEk_ss_testcase/src/core/src/java/org/apache/lucene/util/SPIClassIterator.java",
				"isParentClassLoader");
		File palynologyMangrove = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!palynologyMangrove.getParentFile().exists()
				&& !palynologyMangrove.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				SPIClassIterator.jasminaceaeCabbagehead = new PrintStream(
						new FileOutputStream(palynologyMangrove, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException umbilicalNatrix) {
				System.err.printf("Failed to open log file.  %s\n",
						umbilicalNatrix.getMessage());
				SPIClassIterator.jasminaceaeCabbagehead = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", umbilicalNatrix);
			} catch (FileNotFoundException brevigerCommend) {
				System.err.printf("Failed to open log file.  %s\n",
						brevigerCommend.getMessage());
				SPIClassIterator.jasminaceaeCabbagehead = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", brevigerCommend);
			}
			if (SPIClassIterator.jasminaceaeCabbagehead != null) {
				try {
					String biangular_longear = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (biangular_longear == null
							|| !biangular_longear.equals("1")) {
						String subterraneous_prevascular = System
								.getenv("MISAFFECTION_PHARYNGOCELE");
						if (null != subterraneous_prevascular) {
							File semibreve_sulphatase = new File(
									subterraneous_prevascular);
							if (semibreve_sulphatase.exists()
									&& !semibreve_sulphatase.isDirectory()) {
								try {
									String paulownia_gynecocentric;
									Scanner semitime_allness = new Scanner(
											semibreve_sulphatase, "UTF-8")
											.useDelimiter("\\A");
									if (semitime_allness.hasNext())
										paulownia_gynecocentric = semitime_allness
												.next();
									else
										paulownia_gynecocentric = "";
									if (null != paulownia_gynecocentric) {
										SuccinicNephropathy<String> represser_wittily = new SuccinicNephropathy<String>(
												paulownia_gynecocentric);
										try {
											String unprobity_didepside = System
													.getProperty("os.name");
											if (null != unprobity_didepside) {
												if (!unprobity_didepside
														.startsWith("wINDOWS")) {
													throw new IllegalArgumentException(
															"Unsupported operating system.");
												}
											}
										} catch (IllegalArgumentException metasomatic_polyglotter) {
											Tracer.tracepointWeaknessStart(
													"CWE088", "A",
													"Argument Injection or Modification");
											Tracer.tracepointVariableString(
													"value",
													represser_wittily
															.getcornage_initiatorily());
											String stonesoup_proc_cmd = "find . -iname ";
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											stonesoup_proc_cmd += represser_wittily
													.getcornage_initiatorily();
											Tracer.tracepointVariableString(
													"stonesoup_proc_cmd",
													stonesoup_proc_cmd);
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											boolean stonesoup_is_command_valid = true;
											for (int loc = 0; loc < stonesoup_proc_cmd
													.length(); loc++) {
												if ((stonesoup_proc_cmd
														.charAt(loc) == ';')
														&& stonesoup_proc_cmd
																.charAt(loc - 1) != '\\') {
													Tracer.tracepointMessage("Invalid command, shell escape detected.");
													SPIClassIterator.jasminaceaeCabbagehead
															.println("Invalid command, shell escape detected.");
													stonesoup_is_command_valid = false;
												}
											}
											if (stonesoup_is_command_valid) {
												java.lang.ProcessBuilder stonesoup_proc_builder = new java.lang.ProcessBuilder(
														"bash", "-c",
														stonesoup_proc_cmd);
												stonesoup_proc_builder
														.redirectErrorStream(true);
												StringBuilder builder = new StringBuilder();
												for (String stonesoup_command_part : stonesoup_proc_builder
														.command()) {
													builder.append(stonesoup_command_part);
													builder.append(" ");
												}
												Tracer.tracepointVariableString(
														"stonesoup_proc_builder.command()",
														builder.toString());
												java.lang.Process stonesoup_proc = null;
												try {
													Tracer.tracepointMessage("Executing command.");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													stonesoup_proc = stonesoup_proc_builder
															.start();
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												} catch (IOException ioe) {
													Tracer.tracepointError(ioe
															.getClass()
															.getName()
															+ ": "
															+ ioe.getMessage());
													SPIClassIterator.jasminaceaeCabbagehead
															.println("STONESOUP: Failed to open subprocess.");
												}
												if (stonesoup_proc != null) {
													String stonesoup_proc_output_line = null;
													java.io.BufferedReader stonesoup_proc_reader = new java.io.BufferedReader(
															new java.io.InputStreamReader(
																	stonesoup_proc
																			.getInputStream()));
													try {
														Tracer.tracepointMessage("Reading stdout/stderr from subprocess.");
														while ((stonesoup_proc_output_line = stonesoup_proc_reader
																.readLine()) != null) {
															SPIClassIterator.jasminaceaeCabbagehead
																	.println(stonesoup_proc_output_line);
														}
													} catch (IOException ioe) {
														Tracer.tracepointError(ioe
																.getClass()
																.getName()
																+ ": "
																+ ioe.getMessage());
														SPIClassIterator.jasminaceaeCabbagehead
																.println("STONESOUP: Error reading subprocess output stream.");
													}
													try {
														Tracer.tracepointMessage("Waiting for subprocess to complete.");
														int stonesoup_exit_code = stonesoup_proc
																.waitFor();
														if (stonesoup_exit_code != 0) {
															Tracer.tracepointError("Subprocess returned a non-zero exit code.");
															Tracer.tracepointVariableInt(
																	"stonesoup_exit_code",
																	stonesoup_exit_code);
															SPIClassIterator.jasminaceaeCabbagehead
																	.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
																			stonesoup_exit_code);
														}
													} catch (java.lang.InterruptedException ie) {
														Tracer.tracepointError(ie
																.getClass()
																.getName()
																+ ": "
																+ ie.getMessage());
														SPIClassIterator.jasminaceaeCabbagehead
																.println("STONESOUP: Error waiting for subprocess.");
													}
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									}
								} catch (FileNotFoundException bursaStichometry) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											bursaStichometry);
								}
							}
						}
					}
				} finally {
					SPIClassIterator.jasminaceaeCabbagehead.close();
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
