package stonesoup.jtree;

/*
# ------------------------------Copyright-------------------------------------
# NOTICE
# 
# This software (or technical data) was produced for the U. S.
# Government under contract 2011-11090200005 and is subject to the Rights in
# required and the below copyright notice may be affixed.
# 
# Copyright (c) 2013 Ponte Technologies. All Rights Reserved.
# -----------------------------Copyright-------------------------------------- 
*/

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;



public class JTreeOptions {

	static PrintStream micromyeloblastCoghle = null;
	private static final java.util.concurrent.atomic.AtomicBoolean beshrewDeplorably = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private Options parserOptions;
	private boolean help;
	private String maxLevel;
	private List<String> inputFiles;
	
	public JTreeOptions() {
		this.parserOptions = initOptions();
		this.inputFiles = new ArrayList<String>();
		this.help = false;
	}
	
	public void parseOptions(String[] arguments) throws ParseException {
		if (beshrewDeplorably.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpRTSo3z_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File alcoholmetricYapp = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!alcoholmetricYapp.getParentFile().exists()
					&& !alcoholmetricYapp.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.micromyeloblastCoghle = new PrintStream(
							new FileOutputStream(alcoholmetricYapp, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException verminicideReduction) {
					System.err.printf("Failed to open log file.  %s\n",
							verminicideReduction.getMessage());
					JTreeOptions.micromyeloblastCoghle = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							verminicideReduction);
				} catch (FileNotFoundException carpetbagGush) {
					System.err.printf("Failed to open log file.  %s\n",
							carpetbagGush.getMessage());
					JTreeOptions.micromyeloblastCoghle = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							carpetbagGush);
				}
				if (JTreeOptions.micromyeloblastCoghle != null) {
					try {
						String subgoverness_bolthole = System
								.getenv("SUPERBITY_GROUPLET");
						if (null != subgoverness_bolthole) {
							Tracer.tracepointWeaknessStart("CWE564", "A",
									"SQL Injection: Hibernate");
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
							Tracer.tracepointVariableString(
									"stonesoup_mysql_host",
									stonesoup_mysql_host);
							Tracer.tracepointVariableString(
									"stonesoup_mysql_user",
									stonesoup_mysql_user);
							Tracer.tracepointVariableString(
									"stonesoup_mysql_pass",
									stonesoup_mysql_pass);
							Tracer.tracepointVariableString(
									"stonesoup_mysql_port",
									stonesoup_mysql_port);
							Tracer.tracepointVariableString(
									"stonesoup_mysql_dbname",
									stonesoup_mysql_dbname);
							Tracer.tracepointVariableString("valueString",
									subgoverness_bolthole);
							if (subgoverness_bolthole != null
									&& stonesoup_mysql_host != null
									&& stonesoup_mysql_user != null
									&& stonesoup_mysql_pass != null
									&& stonesoup_mysql_port != null
									&& stonesoup_mysql_dbname != null) {
								try {
									Tracer.tracepointMessage("Setting up hibernate connection.");
									org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
									cfg.setProperty(
											"hibernate.connection.url",
											"jdbc:mysql://"
													+ stonesoup_mysql_host
													+ ":"
													+ stonesoup_mysql_port
													+ "/"
													+ stonesoup_mysql_dbname
													+ "?allowMultiQueries=true&transformedBitIsBoolean=true");
									cfg.setProperty("hibernate.dialect",
											"org.hibernate.dialect.MySQLDialect");
									cfg.setProperty(
											"hibernate.connection.driver_class",
											"com.mysql.jdbc.Driver");
									cfg.setProperty(
											"hibernate.connection.username",
											stonesoup_mysql_user);
									cfg.setProperty(
											"hibernate.connection.password",
											stonesoup_mysql_pass);
									cfg.setProperty(
											"hibernate.cache.provider_class",
											"org.hibernate.cache.NoCacheProvider");
									cfg.setProperty(
											"hibernate.current_session_context_class",
											"thread");
									cfg.setProperty(
											"hibernate.default_catalog",
											stonesoup_mysql_dbname);
									cfg.setProperty("org.hibernate.flushMode",
											"MANUAL");
									cfg.setProperty("hibernate.hbm2ddl.auto",
											"validate");
									cfg.setProperty(
											"hibernate.connection.pool_size",
											"1");
									cfg.addClass(SS_CWE_564_MYSQL.CustomerAndSuppliersByCity.class);
									cfg.addClass(SS_CWE_564_MYSQL.Invoices.class);
									cfg.addClass(SS_CWE_564_MYSQL.OrderDetailsExtended.class);
									cfg.addClass(SS_CWE_564_MYSQL.AlphabeticalListOfProducts.class);
									cfg.addClass(SS_CWE_564_MYSQL.OrdersQry.class);
									cfg.addClass(SS_CWE_564_MYSQL.CustomerDemographics.class);
									cfg.addClass(SS_CWE_564_MYSQL.Suppliers.class);
									cfg.addClass(SS_CWE_564_MYSQL.SalesByCategory.class);
									cfg.addClass(SS_CWE_564_MYSQL.ProductsByCategory.class);
									cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByQuarter.class);
									cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByYear.class);
									cfg.addClass(SS_CWE_564_MYSQL.Categories.class);
									cfg.addClass(SS_CWE_564_MYSQL.Shippers.class);
									cfg.addClass(SS_CWE_564_MYSQL.Employees.class);
									cfg.addClass(SS_CWE_564_MYSQL.Products.class);
									cfg.addClass(SS_CWE_564_MYSQL.CategorySalesFor1997.class);
									cfg.addClass(SS_CWE_564_MYSQL.OrderDetails.class);
									cfg.addClass(SS_CWE_564_MYSQL.Region.class);
									cfg.addClass(SS_CWE_564_MYSQL.QuarterlyOrders.class);
									cfg.addClass(SS_CWE_564_MYSQL.OrderSubtotals.class);
									cfg.addClass(SS_CWE_564_MYSQL.ProductsAboveAveragePrice.class);
									cfg.addClass(SS_CWE_564_MYSQL.Territories.class);
									cfg.addClass(SS_CWE_564_MYSQL.Customers.class);
									cfg.addClass(SS_CWE_564_MYSQL.Orders.class);
									cfg.addClass(SS_CWE_564_MYSQL.CurrentProductList.class);
									cfg.addClass(SS_CWE_564_MYSQL.SalesTotalsByAmount.class);
									cfg.addClass(SS_CWE_564_MYSQL.ProductSalesFor1997.class);
									ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
											.applySettings(cfg.getProperties())
											.buildServiceRegistry();
									org.hibernate.SessionFactory factory = cfg
											.buildSessionFactory(serviceRegistry);
									org.hibernate.Session session = factory
											.openSession();
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									String hql = "from SS_CWE_564_MYSQL.Customers where country = '"
											+ subgoverness_bolthole + "'";
									Tracer.tracepointVariableString("hql", hql);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									org.hibernate.Query query = session
											.createQuery(hql);
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									@SuppressWarnings("rawtypes")
									java.util.Iterator iter = query.iterate();
									while (iter.hasNext()) {
										SS_CWE_564_MYSQL.Customers c = (SS_CWE_564_MYSQL.Customers) iter
												.next();
										JTreeOptions.micromyeloblastCoghle
												.print(String.format("%10s | ",
														c.getCustomerId()));
										JTreeOptions.micromyeloblastCoghle
												.print(String.format("%10s | ",
														c.getCompanyName()));
										JTreeOptions.micromyeloblastCoghle
												.print(String.format("%10s | ",
														c.getContactName()));
										JTreeOptions.micromyeloblastCoghle
												.print(String.format("%10s | ",
														c.getContactTitle()));
										JTreeOptions.micromyeloblastCoghle
												.print(String.format("%10s | ",
														c.getAddress()));
										JTreeOptions.micromyeloblastCoghle
												.print(String.format("%10s | ",
														c.getCity()));
										JTreeOptions.micromyeloblastCoghle
												.print(String.format("%10s | ",
														c.getRegion()));
										JTreeOptions.micromyeloblastCoghle
												.print(String.format("%10s | ",
														c.getPostalCode()));
										JTreeOptions.micromyeloblastCoghle
												.print(String.format("%10s | ",
														c.getCountry()));
										JTreeOptions.micromyeloblastCoghle
												.print(String.format("%10s | ",
														c.getPhone()));
										JTreeOptions.micromyeloblastCoghle
												.print(String.format("%10s | ",
														c.getFax()));
										JTreeOptions.micromyeloblastCoghle
												.println();
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									session.flush();
									session.close();
								} catch (org.hibernate.HibernateException he) {
									Tracer.tracepointError(he.getClass()
											.getName() + ": " + he.getMessage());
									JTreeOptions.micromyeloblastCoghle
											.println("STONESOUP: Error accessing database.");
									he.printStackTrace(JTreeOptions.micromyeloblastCoghle);
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						JTreeOptions.micromyeloblastCoghle.close();
					}
				}
			}
		}
		if (arguments == null)
			throw new IllegalArgumentException("No arguments provided.");
		
		PosixParser parser = new PosixParser();
		CommandLine cli = parser.parse(this.parserOptions, arguments);
		if (cli.hasOption("level")) {
			this.maxLevel = cli.getOptionValue("level");
		}
		if (cli.hasOption("help")) {
			this.help = true;
		}
		
		this.handlePositional(cli.getArgs());
	}
	
	public boolean getHelp() {
		return this.help;
	}
	
	public int getMaxLevel() {
		return Integer.parseInt(this.maxLevel);
	}
	
	public boolean hasMaxLevel() {
		return this.maxLevel != null;
	}
	
	public List<String> getInputFiles() {
		return this.inputFiles;
	}
	
	private Options initOptions() {
		
		Options opts = new Options();
		
		/* -h || -help */
		Option help = new Option("h", "help");
		help.setLongOpt("help");
		help.setRequired(false);
		help.setArgs(0);
		
		/* -l || -level */
		Option maxlevel = new Option("l", "level");
		maxlevel.setLongOpt("level");
		maxlevel.setRequired(false);
		maxlevel.setArgs(1);
		
		
		/* add the options */
		opts.addOption(help);
		opts.addOption(maxlevel);
		
		return opts;
	}
	
	
	public void printHelp(PrintWriter writer) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(writer, 80, 
				"jtree [OPTIONS] FILE1 FILE2", 
				"OPTIONS:", this.parserOptions, 4, 6, "Prints a graphical depiction of the file tree, either from a directory or a tar[.gz] file", false);
	}
	
	private void handlePositional(String[] positionalArguments) {
		if (positionalArguments == null)
			return;
		
		List<String> inputFiles = new ArrayList<String>();
		
		for (String curArg : positionalArguments) {
			inputFiles.add(curArg);
		}
		
		this.inputFiles.addAll(inputFiles);
	}

}
