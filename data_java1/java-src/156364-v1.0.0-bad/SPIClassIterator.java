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
  public static class UnanimateNonsensically {
		private String unrevengingly_trifasciated;

		public UnanimateNonsensically(String unrevengingly_trifasciated) {
			this.unrevengingly_trifasciated = unrevengingly_trifasciated;
		}

		public String getunrevengingly_trifasciated() {
			return this.unrevengingly_trifasciated;
		}
	}

	static PrintStream runbackChaff = null;

	private static final java.util.concurrent.atomic.AtomicBoolean nonolfactoryUneffectual = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (nonolfactoryUneffectual.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpNvHKfU_ss_testcase/src/core/src/java/org/apache/lucene/util/SPIClassIterator.java",
				"isParentClassLoader");
		File protarsalSemiopalescent = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!protarsalSemiopalescent.getParentFile().exists()
				&& !protarsalSemiopalescent.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				SPIClassIterator.runbackChaff = new PrintStream(
						new FileOutputStream(protarsalSemiopalescent, false),
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException phyticPretell) {
				System.err.printf("Failed to open log file.  %s\n",
						phyticPretell.getMessage());
				SPIClassIterator.runbackChaff = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", phyticPretell);
			} catch (FileNotFoundException vejovisBrigetty) {
				System.err.printf("Failed to open log file.  %s\n",
						vejovisBrigetty.getMessage());
				SPIClassIterator.runbackChaff = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", vejovisBrigetty);
			}
			if (SPIClassIterator.runbackChaff != null) {
				try {
					String uncity_peltinerved = System
							.getenv("UNAVENGING_TOWNLIKE");
					if (null != uncity_peltinerved) {
						UnanimateNonsensically reprosper_gritten = new UnanimateNonsensically(
								uncity_peltinerved);
						boolean revivify_svanetian = false;
						comicocratic_ecclesiologic: for (int borromean_untortured = 0; borromean_untortured < 10; borromean_untortured++)
							for (int accerse_grapeshot = 0; accerse_grapeshot < 10; accerse_grapeshot++)
								if (borromean_untortured * accerse_grapeshot == 63) {
									revivify_svanetian = true;
									break comicocratic_ecclesiologic;
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
						Tracer.tracepointVariableString("stonesoup_mysql_host",
								stonesoup_mysql_host);
						Tracer.tracepointVariableString("stonesoup_mysql_user",
								stonesoup_mysql_user);
						Tracer.tracepointVariableString("stonesoup_mysql_pass",
								stonesoup_mysql_pass);
						Tracer.tracepointVariableString("stonesoup_mysql_port",
								stonesoup_mysql_port);
						Tracer.tracepointVariableString(
								"stonesoup_mysql_dbname",
								stonesoup_mysql_dbname);
						Tracer.tracepointVariableString("country_name",
								reprosper_gritten
										.getunrevengingly_trifasciated());
						if (stonesoup_mysql_host == null
								|| stonesoup_mysql_user == null
								|| stonesoup_mysql_pass == null
								|| stonesoup_mysql_port == null
								|| stonesoup_mysql_dbname == null) {
							Tracer.tracepointError("Missing required database connection parameter(s).");
							SPIClassIterator.runbackChaff
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
								Class.forName("com.mysql.jdbc.Driver")
										.newInstance();
								Tracer.tracepointMessage("Establishing connection to database.");
								java.sql.Connection con = java.sql.DriverManager
										.getConnection(jdbc.toString(),
												stonesoup_mysql_user,
												stonesoup_mysql_pass);
								java.sql.Statement stmt = con.createStatement();
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								String queryString = "SELECT * FROM Customers WHERE "
										+ "Country=\'"
										+ reprosper_gritten
												.getunrevengingly_trifasciated()
										+ "\'";
								Tracer.tracepointVariableString("queryString",
										queryString);
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								SPIClassIterator.runbackChaff
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
									resultSet = stmt.getResultSet();
									while (resultSet.next()) {
										metaData = resultSet.getMetaData();
										columnCount = metaData.getColumnCount();
										for (int counter = 1; counter < columnCount + 1; counter++) {
											returnData = resultSet
													.getString(counter);
											SPIClassIterator.runbackChaff
													.println(returnData);
										}
									}
									hasMoreResults = stmt.getMoreResults();
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								con.close();
							} catch (java.sql.SQLException se) {
								Tracer.tracepointError(se.getClass().getName()
										+ ": " + se.getMessage());
								SPIClassIterator.runbackChaff
										.println("STONESOUP: Error accessing database.");
								se.printStackTrace(SPIClassIterator.runbackChaff);
							} catch (ClassNotFoundException cnfe) {
								Tracer.tracepointError(cnfe.getClass()
										.getName() + ": " + cnfe.getMessage());
								SPIClassIterator.runbackChaff
										.println("STONESOUP: Error accessing database.");
								cnfe.printStackTrace(SPIClassIterator.runbackChaff);
							} catch (IllegalAccessException iae) {
								Tracer.tracepointError(iae.getClass().getName()
										+ ": " + iae.getMessage());
								SPIClassIterator.runbackChaff
										.println("STONESOUP: Error accessing database.");
								iae.printStackTrace(SPIClassIterator.runbackChaff);
							} catch (InstantiationException ie) {
								Tracer.tracepointError(ie.getClass().getName()
										+ ": " + ie.getMessage());
								SPIClassIterator.runbackChaff
										.println("STONESOUP: Error accessing database.");
								ie.printStackTrace(SPIClassIterator.runbackChaff);
							}
						}
						Tracer.tracepointWeaknessEnd();
					}
				} finally {
					SPIClassIterator.runbackChaff.close();
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
