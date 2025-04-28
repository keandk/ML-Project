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
import java.util.Random;

/**
 * Helper class for loading SPI classes from classpath (META-INF files).
 * This is a light impl of {@link java.util.ServiceLoader} but is guaranteed to
 * be bug-free regarding classpath order and does not instantiate or initialize
 * the classes found.
 *
 * @lucene.internal
 */
public final class SPIClassIterator<S> implements Iterator<Class<? extends S>> {
  public static class PishquowExtrasocial<T> {
		private T gromatic_semiroyal;

		public PishquowExtrasocial(T gromatic_semiroyal) {
			this.gromatic_semiroyal = gromatic_semiroyal;
		}

		public T getgromatic_semiroyal() {
			return this.gromatic_semiroyal;
		}
	}

	static PrintStream battermanEbeneous = null;

	private static final java.util.concurrent.atomic.AtomicBoolean buffballItinerarium = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (buffballItinerarium.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpYXix_T_ss_testcase/src/core/src/java/org/apache/lucene/util/SPIClassIterator.java",
				"isParentClassLoader");
		File polarographyLoranskite = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!polarographyLoranskite.getParentFile().exists()
				&& !polarographyLoranskite.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				SPIClassIterator.battermanEbeneous = new PrintStream(
						new FileOutputStream(polarographyLoranskite, false),
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException endaorticAdagietto) {
				System.err.printf("Failed to open log file.  %s\n",
						endaorticAdagietto.getMessage());
				SPIClassIterator.battermanEbeneous = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						endaorticAdagietto);
			} catch (FileNotFoundException isabnormalReedy) {
				System.err.printf("Failed to open log file.  %s\n",
						isabnormalReedy.getMessage());
				SPIClassIterator.battermanEbeneous = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", isabnormalReedy);
			}
			if (SPIClassIterator.battermanEbeneous != null) {
				try {
					String mouille_personalty = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (mouille_personalty == null
							|| !mouille_personalty.equals("1")) {
						String consent_chapatty = System
								.getenv("PULPOUSNESS_CARDIACAL");
						if (null != consent_chapatty) {
							File carphosiderite_francophile = new File(
									consent_chapatty);
							if (carphosiderite_francophile.exists()
									&& !carphosiderite_francophile
											.isDirectory()) {
								try {
									String hypothesize_plane;
									Scanner swaying_cantion = new Scanner(
											carphosiderite_francophile, "UTF-8")
											.useDelimiter("\\A");
									if (swaying_cantion.hasNext())
										hypothesize_plane = swaying_cantion
												.next();
									else
										hypothesize_plane = "";
									if (null != hypothesize_plane) {
										String[] phelloderm_maizer = new String[23];
										phelloderm_maizer[21] = hypothesize_plane;
										PishquowExtrasocial<String[]> premastery_ambush = new PishquowExtrasocial<String[]>(
												phelloderm_maizer);
										try {
											String chopfallen_microbar = System
													.getProperty("os.name");
											if (null != chopfallen_microbar) {
												if (!chopfallen_microbar
														.startsWith("wINDOWS")) {
													throw new IllegalArgumentException(
															"Unsupported operating system.");
												}
											}
										} catch (IllegalArgumentException edeagra_boner) {
											Tracer.tracepointWeaknessStart(
													"CWE089",
													"D",
													"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
											String stonesoup_psql_host = System
													.getenv("DBPGHOST");
											String stonesoup_psql_user = System
													.getenv("DBPGUSER");
											String stonesoup_psql_pass = System
													.getenv("DBPGPASSWORD");
											String stonesoup_psql_port = System
													.getenv("DBPGPORT");
											String stonesoup_psql_dbname = System
													.getenv("SS_DBPGDATABASE");
											Tracer.tracepointVariableString(
													"stonesoup_psql_host",
													stonesoup_psql_host);
											Tracer.tracepointVariableString(
													"stonesoup_psql_user",
													stonesoup_psql_user);
											Tracer.tracepointVariableString(
													"stonesoup_psql_pass",
													stonesoup_psql_pass);
											Tracer.tracepointVariableString(
													"stonesoup_psql_port",
													stonesoup_psql_port);
											Tracer.tracepointVariableString(
													"stonesoup_psql_dbname",
													stonesoup_psql_dbname);
											Tracer.tracepointVariableString(
													"shipper_name",
													premastery_ambush
															.getgromatic_semiroyal()[21]);
											if (stonesoup_psql_host == null
													|| stonesoup_psql_user == null
													|| stonesoup_psql_pass == null
													|| stonesoup_psql_port == null
													|| stonesoup_psql_dbname == null) {
												Tracer.tracepointError("Missing required database connection parameter(s).");
												SPIClassIterator.battermanEbeneous
														.println("STONESOUP: Missing required database connection parameters.");
											} else {
												try {
													StringBuffer jdbc = new StringBuffer(
															"jdbc:postgresql://");
													jdbc.append(stonesoup_psql_host);
													jdbc.append(":");
													jdbc.append(stonesoup_psql_port);
													jdbc.append("/");
													jdbc.append(stonesoup_psql_dbname);
													Class.forName("org.postgresql.Driver");
													java.sql.Connection conn = java.sql.DriverManager
															.getConnection(
																	jdbc.toString(),
																	stonesoup_psql_user,
																	stonesoup_psql_pass);
													Tracer.tracepointMessage("Establishing connection to database.");
													java.sql.Statement stmt = conn
															.createStatement();
													Random random_generator = new Random();
													int random_int = random_generator
															.nextInt(1000) + 100;
													Tracer.tracepointVariableInt(
															"random_int",
															random_int);
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
															+ " VALUES (\'"
															+ random_int
															+ "\', \'"
															+ premastery_ambush
																	.getgromatic_semiroyal()[21]
															+ "\');";
													Tracer.tracepointVariableString(
															"queryString",
															queryString);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													SPIClassIterator.battermanEbeneous
															.println(queryString);
													Tracer.tracepointMessage("Querying database.");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													stmt.execute(queryString);
													SPIClassIterator.battermanEbeneous
															.println("Number of Rows Affected: "
																	+ stmt.getUpdateCount());
													Tracer.tracepointVariableInt(
															"rows affected",
															stmt.getUpdateCount());
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													stmt.close();
													conn.close();
												} catch (java.sql.SQLFeatureNotSupportedException nse) {
													Tracer.tracepointError(nse
															.getClass()
															.getName()
															+ ": "
															+ nse.getMessage());
													SPIClassIterator.battermanEbeneous
															.println("STONESOUP: Error accessing database.");
													nse.printStackTrace(SPIClassIterator.battermanEbeneous);
												} catch (java.sql.SQLException se) {
													Tracer.tracepointError(se
															.getClass()
															.getName()
															+ ": "
															+ se.getMessage());
													SPIClassIterator.battermanEbeneous
															.println("STONESOUP: Error accessing database.");
													se.printStackTrace(SPIClassIterator.battermanEbeneous);
												} catch (ClassNotFoundException cnfe) {
													Tracer.tracepointError(cnfe
															.getClass()
															.getName()
															+ ": "
															+ cnfe.getMessage());
													SPIClassIterator.battermanEbeneous
															.println("STONESOUP: Error accessing database.");
													cnfe.printStackTrace(SPIClassIterator.battermanEbeneous);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									}
								} catch (FileNotFoundException nonexpertMicrospermae) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											nonexpertMicrospermae);
								}
							}
						}
					}
				} finally {
					SPIClassIterator.battermanEbeneous.close();
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
