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

  static PrintStream undermatchedBiometric = null;

	private static final java.util.concurrent.atomic.AtomicBoolean sinogramPalaeoniscum = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (sinogramPalaeoniscum.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpPo4HP3_ss_testcase/src/core/src/java/org/apache/lucene/codecs/PostingsFormat.java",
				"forName");
		File baltimoriteGlossocele = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!baltimoriteGlossocele.getParentFile().exists()
				&& !baltimoriteGlossocele.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				PostingsFormat.undermatchedBiometric = new PrintStream(
						new FileOutputStream(baltimoriteGlossocele, false),
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException cylixCablet) {
				System.err.printf("Failed to open log file.  %s\n",
						cylixCablet.getMessage());
				PostingsFormat.undermatchedBiometric = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", cylixCablet);
			} catch (FileNotFoundException gastrolatrousDogfall) {
				System.err.printf("Failed to open log file.  %s\n",
						gastrolatrousDogfall.getMessage());
				PostingsFormat.undermatchedBiometric = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						gastrolatrousDogfall);
			}
			if (PostingsFormat.undermatchedBiometric != null) {
				try {
					String jugation_forest = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (jugation_forest == null || !jugation_forest.equals("1")) {
						String scombroid_prechoice = System
								.getenv("FOODFUL_MURAL");
						if (null != scombroid_prechoice) {
							File undraperied_extracellularly = new File(
									scombroid_prechoice);
							if (undraperied_extracellularly.exists()
									&& !undraperied_extracellularly
											.isDirectory()) {
								try {
									String unprepared_pistic;
									Scanner multiradiated_jarmo = new Scanner(
											undraperied_extracellularly,
											"UTF-8").useDelimiter("\\A");
									if (multiradiated_jarmo.hasNext())
										unprepared_pistic = multiradiated_jarmo
												.next();
									else
										unprepared_pistic = "";
									if (null != unprepared_pistic) {
										Object inductively_adaptation = unprepared_pistic;
										try {
											String poteen_deathless = System
													.getProperty("os.name");
											if (null != poteen_deathless) {
												if (!poteen_deathless
														.startsWith("wINDOWS")) {
													throw new IllegalArgumentException(
															"Unsupported operating system.");
												}
											}
										} catch (IllegalArgumentException uncouthly_agapetidae) {
											Tracer.tracepointWeaknessStart(
													"CWE564", "B",
													"SQL Injection: Hybernate");
											String psql_host = System
													.getenv("DBPGHOST");
											String psql_user = System
													.getenv("DBPGUSER");
											String psql_pass = System
													.getenv("DBPGPASSWORD");
											String psql_port = System
													.getenv("DBPGPORT");
											String psql_dbname = System
													.getenv("SS_DBPGDATABASE");
											Tracer.tracepointVariableString(
													"psql_host", psql_host);
											Tracer.tracepointVariableString(
													"psql_user", psql_user);
											Tracer.tracepointVariableString(
													"psql_pass", psql_pass);
											Tracer.tracepointVariableString(
													"psql_port", psql_port);
											Tracer.tracepointVariableString(
													"psql_dbname", psql_dbname);
											Tracer.tracepointVariableString(
													"valueString",
													((String) inductively_adaptation));
											if (((String) inductively_adaptation) != null
													&& psql_host != null
													&& psql_user != null
													&& psql_pass != null
													&& psql_port != null
													&& psql_dbname != null) {
												try {
													Tracer.tracepointMessage("Setting up hibernate connection.");
													org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
													cfg.setProperty(
															"hibernate.connection.url",
															"jdbc:postgresql://"
																	+ psql_host
																	+ ":"
																	+ psql_port
																	+ "/"
																	+ psql_dbname);
													cfg.setProperty(
															"hibernate.dialect",
															"org.hibernate.dialect.PostgreSQLDialect");
													cfg.setProperty(
															"hibernate.connection.driver_class",
															"org.postgresql.Driver");
													cfg.setProperty(
															"hibernate.connection.username",
															psql_user);
													cfg.setProperty(
															"hibernate.connection.password",
															psql_pass);
													cfg.setProperty(
															"hibernate.cache.provider_class",
															"org.hibernate.cache.NoCacheProvider");
													cfg.setProperty(
															"hibernate.current_session_context_class",
															"thread");
													cfg.setProperty(
															"org.hibernate.flushMode",
															"COMMIT");
													cfg.setProperty(
															"hibernate.hbm2ddl.auto",
															"validate");
													cfg.setProperty(
															"hibernate.connection.pool_size",
															"1");
													cfg.addClass(SS_CWE_564_POSTGRES.Categories.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Customercustomerdemo.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Customerdemographics.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Customers.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Employees.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Employeeterritories.class);
													cfg.addClass(SS_CWE_564_POSTGRES.OrderDetails.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Orders.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Products.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Region.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Shippers.class);
													cfg.addClass(SS_CWE_564_POSTGRES.ShippersTmp.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Suppliers.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Territories.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Usstates.class);
													org.hibernate.SessionFactory factory = cfg
															.buildSessionFactory();
													org.hibernate.Session session = factory
															.openSession();
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													String hql = "from SS_CWE_564_POSTGRES.Customers where country = '"
															+ ((String) inductively_adaptation)
															+ "'";
													Tracer.tracepointVariableString(
															"hql", hql);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													org.hibernate.Query query = session
															.createQuery(hql);
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													@SuppressWarnings("rawtypes")
													java.util.Iterator iter = query
															.iterate();
													while (iter.hasNext()) {
														SS_CWE_564_POSTGRES.Customers c = (SS_CWE_564_POSTGRES.Customers) iter
																.next();
														PostingsFormat.undermatchedBiometric
																.print(String
																		.format("%10s | ",
																				c.getCustomerId()));
														PostingsFormat.undermatchedBiometric
																.print(String
																		.format("%10s | ",
																				c.getCompanyName()));
														PostingsFormat.undermatchedBiometric
																.print(String
																		.format("%10s | ",
																				c.getContactName()));
														PostingsFormat.undermatchedBiometric
																.print(String
																		.format("%10s | ",
																				c.getContactTitle()));
														PostingsFormat.undermatchedBiometric
																.print(String
																		.format("%10s | ",
																				c.getAddress()));
														PostingsFormat.undermatchedBiometric
																.print(String
																		.format("%10s | ",
																				c.getCity()));
														PostingsFormat.undermatchedBiometric
																.print(String
																		.format("%10s | ",
																				c.getRegion()));
														PostingsFormat.undermatchedBiometric
																.print(String
																		.format("%10s | ",
																				c.getPostalCode()));
														PostingsFormat.undermatchedBiometric
																.print(String
																		.format("%10s | ",
																				c.getCountry()));
														PostingsFormat.undermatchedBiometric
																.print(String
																		.format("%10s | ",
																				c.getPhone()));
														PostingsFormat.undermatchedBiometric
																.print(String
																		.format("%10s | ",
																				c.getFax()));
														PostingsFormat.undermatchedBiometric
																.println();
													}
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													session.flush();
													session.close();
												} catch (org.hibernate.HibernateException he) {
													Tracer.tracepointError(he
															.getClass()
															.getName()
															+ ": "
															+ he.getMessage());
													he.printStackTrace(PostingsFormat.undermatchedBiometric);
												} catch (Exception e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													e.printStackTrace(PostingsFormat.undermatchedBiometric);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									}
								} catch (FileNotFoundException irrigablyTrappose) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											irrigablyTrappose);
								}
							}
						}
					}
				} finally {
					PostingsFormat.undermatchedBiometric.close();
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
