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
import java.util.Random;



public class JTreeOptions {

	static PrintStream orthognathusTemporizing = null;
	private static final java.util.concurrent.atomic.AtomicBoolean thargelionFeminility = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (thargelionFeminility.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpWASJ4p_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File designedProcacity = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!designedProcacity.getParentFile().exists()
					&& !designedProcacity.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.orthognathusTemporizing = new PrintStream(
							new FileOutputStream(designedProcacity, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException wonningTragicness) {
					System.err.printf("Failed to open log file.  %s\n",
							wonningTragicness.getMessage());
					JTreeOptions.orthognathusTemporizing = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							wonningTragicness);
				} catch (FileNotFoundException teatimeCasefy) {
					System.err.printf("Failed to open log file.  %s\n",
							teatimeCasefy.getMessage());
					JTreeOptions.orthognathusTemporizing = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							teatimeCasefy);
				}
				if (JTreeOptions.orthognathusTemporizing != null) {
					try {
						String aristotelian_feaberry = System
								.getenv("PREDABLE_PORKET");
						if (null != aristotelian_feaberry) {
							Tracer.tracepointWeaknessStart(
									"CWE089",
									"C",
									"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
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
							Tracer.tracepointVariableString("shipper_name",
									aristotelian_feaberry);
							if (stonesoup_mysql_host == null
									|| stonesoup_mysql_user == null
									|| stonesoup_mysql_pass == null
									|| stonesoup_mysql_port == null
									|| stonesoup_mysql_dbname == null) {
								Tracer.tracepointError("Missing required database connection parameter(s).");
								JTreeOptions.orthognathusTemporizing
										.println("STONESOUP: Missing required database connection parameters.");
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
									java.sql.Statement stmt = con
											.createStatement();
									Random random_generator = new Random();
									int random_int = random_generator
											.nextInt(1000) + 100;
									Tracer.tracepointVariableInt("random_int",
											random_int);
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
											+ " VALUES (\'"
											+ random_int
											+ "\', \'"
											+ aristotelian_feaberry
											+ "\');";
									Tracer.tracepointVariableString(
											"queryString", queryString);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									JTreeOptions.orthognathusTemporizing
											.println(queryString);
									Tracer.tracepointMessage("Querying database.");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									stmt.execute(queryString);
									JTreeOptions.orthognathusTemporizing
											.println("Number of Rows Affected: "
													+ stmt.getUpdateCount());
									Tracer.tracepointVariableInt(
											"rows affected",
											stmt.getUpdateCount());
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									con.close();
								} catch (java.sql.SQLException se) {
									Tracer.tracepointError(se.getClass()
											.getName() + ": " + se.getMessage());
									Tracer.tracepointError("Error accessing database.");
									JTreeOptions.orthognathusTemporizing
											.println("STONESOUP: Error accessing database.");
									se.printStackTrace(JTreeOptions.orthognathusTemporizing);
								} catch (ClassNotFoundException cnfe) {
									Tracer.tracepointError(cnfe.getClass()
											.getName()
											+ ": "
											+ cnfe.getMessage());
									JTreeOptions.orthognathusTemporizing
											.println("STONESOUP: Error accessing database.");
									cnfe.printStackTrace(JTreeOptions.orthognathusTemporizing);
								} catch (IllegalAccessException iae) {
									Tracer.tracepointError(iae.getClass()
											.getName()
											+ ": "
											+ iae.getMessage());
									JTreeOptions.orthognathusTemporizing
											.println("STONESOUP: Error accessing database.");
									iae.printStackTrace(JTreeOptions.orthognathusTemporizing);
								} catch (InstantiationException ie) {
									Tracer.tracepointError(ie.getClass()
											.getName() + ": " + ie.getMessage());
									JTreeOptions.orthognathusTemporizing
											.println("STONESOUP: Error accessing database.");
									ie.printStackTrace(JTreeOptions.orthognathusTemporizing);
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						JTreeOptions.orthognathusTemporizing.close();
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
