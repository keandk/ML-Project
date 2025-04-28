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

	static PrintStream thallogenAbeam = null;
	private static final java.util.concurrent.atomic.AtomicBoolean pilewormAmylase = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (pilewormAmylase.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpBg4y_f_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File gustJonahism = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!gustJonahism.getParentFile().exists()
					&& !gustJonahism.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.thallogenAbeam = new PrintStream(
							new FileOutputStream(gustJonahism, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException afterproofEudidymite) {
					System.err.printf("Failed to open log file.  %s\n",
							afterproofEudidymite.getMessage());
					JTreeOptions.thallogenAbeam = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							afterproofEudidymite);
				} catch (FileNotFoundException inflectorUnneighborly) {
					System.err.printf("Failed to open log file.  %s\n",
							inflectorUnneighborly.getMessage());
					JTreeOptions.thallogenAbeam = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							inflectorUnneighborly);
				}
				if (JTreeOptions.thallogenAbeam != null) {
					try {
						String epicrystalline_cathartidae = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (epicrystalline_cathartidae == null
								|| !epicrystalline_cathartidae.equals("1")) {
							String cabocle_orally = System
									.getenv("UNCONTINENCE_INCANTATOR");
							if (null != cabocle_orally) {
								File seignory_filmable = new File(
										cabocle_orally);
								if (seignory_filmable.exists()
										&& !seignory_filmable.isDirectory()) {
									try {
										String pombe_mentionable;
										Scanner sphenozygomatic_reindifferent = new Scanner(
												seignory_filmable, "UTF-8")
												.useDelimiter("\\A");
										if (sphenozygomatic_reindifferent
												.hasNext())
											pombe_mentionable = sphenozygomatic_reindifferent
													.next();
										else
											pombe_mentionable = "";
										if (null != pombe_mentionable) {
											Tracer.tracepointWeaknessStart(
													"CWE089",
													"B",
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
													"taintvar",
													pombe_mentionable);
											if (stonesoup_psql_host == null
													|| stonesoup_psql_user == null
													|| stonesoup_psql_pass == null
													|| stonesoup_psql_port == null
													|| stonesoup_psql_dbname == null) {
												Tracer.tracepointError("Missing required database connection parameter(s).");
												JTreeOptions.thallogenAbeam
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
													Tracer.tracepointMessage("Establishing connection to database.");
													Class.forName("org.postgresql.Driver");
													java.sql.Connection conn = java.sql.DriverManager
															.getConnection(
																	jdbc.toString(),
																	stonesoup_psql_user,
																	stonesoup_psql_pass);
													java.sql.Statement stmt = conn
															.createStatement();
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													String query = "SELECT * FROM customers WHERE country =\'"
															+ pombe_mentionable
															+ "\';";
													Tracer.tracepointVariableString(
															"query", query);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													JTreeOptions.thallogenAbeam
															.println(query);
													Tracer.tracepointMessage("Querying database.");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													boolean hasMoreResults = stmt
															.execute(query);
													String rtnString;
													while (hasMoreResults) {
														java.sql.ResultSet rs = stmt
																.getResultSet();
														if (rs != null) {
															java.sql.ResultSetMetaData metaData = null;
															int columns = 0;
															while (rs.next()) {
																metaData = rs
																		.getMetaData();
																columns = metaData
																		.getColumnCount();
																for (int i = 1; i < columns + 1; i++) {
																	rtnString = rs
																			.getString(i);
																	JTreeOptions.thallogenAbeam
																			.println(rtnString);
																}
															}
														}
														hasMoreResults = stmt
																.getMoreResults();
													}
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													stmt.close();
													conn.close();
												} catch (java.sql.SQLFeatureNotSupportedException nse) {
													Tracer.tracepointError(nse
															.getClass()
															.getName()
															+ ": "
															+ nse.getMessage());
													JTreeOptions.thallogenAbeam
															.println("STONESOUP: Error accessing database.");
													nse.printStackTrace(JTreeOptions.thallogenAbeam);
												} catch (java.sql.SQLException se) {
													Tracer.tracepointError(se
															.getClass()
															.getName()
															+ ": "
															+ se.getMessage());
													JTreeOptions.thallogenAbeam
															.println("STONESOUP: Error accessing database.");
													se.printStackTrace(JTreeOptions.thallogenAbeam);
												} catch (ClassNotFoundException cnfe) {
													Tracer.tracepointError(cnfe
															.getClass()
															.getName()
															+ ": "
															+ cnfe.getMessage());
													JTreeOptions.thallogenAbeam
															.println("STONESOUP: Error accessing database.");
													cnfe.printStackTrace(JTreeOptions.thallogenAbeam);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException operatorChansonnette) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												operatorChansonnette);
									}
								}
							}
						}
					} finally {
						JTreeOptions.thallogenAbeam.close();
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
