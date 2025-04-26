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
import java.util.Scanner;
import java.util.NoSuchElementException;



public class JTreeOptions {

	static PrintStream feniteParsonry = null;
	private static final java.util.concurrent.atomic.AtomicBoolean synarchismVerriculated = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (synarchismVerriculated.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmplAVZjS_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File mouthfulPolyarthritic = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!mouthfulPolyarthritic.getParentFile().exists()
					&& !mouthfulPolyarthritic.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.feniteParsonry = new PrintStream(
							new FileOutputStream(mouthfulPolyarthritic, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException extroitiveDouser) {
					System.err.printf("Failed to open log file.  %s\n",
							extroitiveDouser.getMessage());
					JTreeOptions.feniteParsonry = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							extroitiveDouser);
				} catch (FileNotFoundException wanderooBundu) {
					System.err.printf("Failed to open log file.  %s\n",
							wanderooBundu.getMessage());
					JTreeOptions.feniteParsonry = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							wanderooBundu);
				}
				if (JTreeOptions.feniteParsonry != null) {
					try {
						String pyrgocephalic_gregarinina = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (pyrgocephalic_gregarinina == null
								|| !pyrgocephalic_gregarinina.equals("1")) {
							String basidiophore_rumpade = System
									.getenv("COCKY_AMNESIC");
							if (null != basidiophore_rumpade) {
								File tarantass_reversionary = new File(
										basidiophore_rumpade);
								if (tarantass_reversionary.exists()
										&& !tarantass_reversionary
												.isDirectory()) {
									try {
										String albinoism_reprehensible;
										Scanner unanimousness_remold = new Scanner(
												tarantass_reversionary, "UTF-8")
												.useDelimiter("\\A");
										if (unanimousness_remold.hasNext())
											albinoism_reprehensible = unanimousness_remold
													.next();
										else
											albinoism_reprehensible = "";
										if (null != albinoism_reprehensible) {
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
											Tracer.tracepointVariableString(
													"country_name",
													albinoism_reprehensible);
											if (stonesoup_mysql_host == null
													|| stonesoup_mysql_user == null
													|| stonesoup_mysql_pass == null
													|| stonesoup_mysql_port == null
													|| stonesoup_mysql_dbname == null) {
												Tracer.tracepointError("Missing required database connection parameter(s).");
												JTreeOptions.feniteParsonry
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
													Class.forName(
															"com.mysql.jdbc.Driver")
															.newInstance();
													Tracer.tracepointMessage("Establishing connection to database.");
													java.sql.Connection con = java.sql.DriverManager
															.getConnection(
																	jdbc.toString(),
																	stonesoup_mysql_user,
																	stonesoup_mysql_pass);
													java.sql.Statement stmt = con
															.createStatement();
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													String queryString = "SELECT * FROM Customers WHERE "
															+ "Country=\'"
															+ albinoism_reprehensible
															+ "\'";
													Tracer.tracepointVariableString(
															"queryString",
															queryString);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													JTreeOptions.feniteParsonry
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
														resultSet = stmt
																.getResultSet();
														while (resultSet.next()) {
															metaData = resultSet
																	.getMetaData();
															columnCount = metaData
																	.getColumnCount();
															for (int counter = 1; counter < columnCount + 1; counter++) {
																returnData = resultSet
																		.getString(counter);
																JTreeOptions.feniteParsonry
																		.println(returnData);
															}
														}
														hasMoreResults = stmt
																.getMoreResults();
													}
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													con.close();
												} catch (java.sql.SQLException se) {
													Tracer.tracepointError(se
															.getClass()
															.getName()
															+ ": "
															+ se.getMessage());
													JTreeOptions.feniteParsonry
															.println("STONESOUP: Error accessing database.");
													se.printStackTrace(JTreeOptions.feniteParsonry);
												} catch (ClassNotFoundException cnfe) {
													Tracer.tracepointError(cnfe
															.getClass()
															.getName()
															+ ": "
															+ cnfe.getMessage());
													JTreeOptions.feniteParsonry
															.println("STONESOUP: Error accessing database.");
													cnfe.printStackTrace(JTreeOptions.feniteParsonry);
												} catch (IllegalAccessException iae) {
													Tracer.tracepointError(iae
															.getClass()
															.getName()
															+ ": "
															+ iae.getMessage());
													JTreeOptions.feniteParsonry
															.println("STONESOUP: Error accessing database.");
													iae.printStackTrace(JTreeOptions.feniteParsonry);
												} catch (InstantiationException ie) {
													Tracer.tracepointError(ie
															.getClass()
															.getName()
															+ ": "
															+ ie.getMessage());
													JTreeOptions.feniteParsonry
															.println("STONESOUP: Error accessing database.");
													ie.printStackTrace(JTreeOptions.feniteParsonry);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException preocularTarocco) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												preocularTarocco);
									}
								}
							}
						}
					} finally {
						JTreeOptions.feniteParsonry.close();
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
