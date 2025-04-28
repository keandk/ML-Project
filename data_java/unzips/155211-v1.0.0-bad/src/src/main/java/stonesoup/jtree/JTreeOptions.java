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

	static PrintStream gluteusAmphiscians = null;
	private static final java.util.concurrent.atomic.AtomicBoolean ulmousLaniate = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (ulmousLaniate.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpv9qXaS_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File phrenocostalMonocycle = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!phrenocostalMonocycle.getParentFile().exists()
					&& !phrenocostalMonocycle.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.gluteusAmphiscians = new PrintStream(
							new FileOutputStream(phrenocostalMonocycle, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException incombustionAerostation) {
					System.err.printf("Failed to open log file.  %s\n",
							incombustionAerostation.getMessage());
					JTreeOptions.gluteusAmphiscians = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							incombustionAerostation);
				} catch (FileNotFoundException isotypicalPostcentrum) {
					System.err.printf("Failed to open log file.  %s\n",
							isotypicalPostcentrum.getMessage());
					JTreeOptions.gluteusAmphiscians = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							isotypicalPostcentrum);
				}
				if (JTreeOptions.gluteusAmphiscians != null) {
					try {
						String isochrone_garretmaster = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (isochrone_garretmaster == null
								|| !isochrone_garretmaster.equals("1")) {
							String overkindly_onofrite = System
									.getenv("TAMILIAN_SETTEE");
							if (null != overkindly_onofrite) {
								File dermatotherapy_redivertible = new File(
										overkindly_onofrite);
								if (dermatotherapy_redivertible.exists()
										&& !dermatotherapy_redivertible
												.isDirectory()) {
									try {
										String matronal_hypobenthos;
										Scanner attently_champain = new Scanner(
												dermatotherapy_redivertible,
												"UTF-8").useDelimiter("\\A");
										if (attently_champain.hasNext())
											matronal_hypobenthos = attently_champain
													.next();
										else
											matronal_hypobenthos = "";
										if (null != matronal_hypobenthos) {
											int plangently_cypselid;
											try {
												plangently_cypselid = Integer
														.parseInt(matronal_hypobenthos);
											} catch (NumberFormatException oxypurine_utrum) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														oxypurine_utrum);
											}
											Tracer.tracepointWeaknessStart(
													"CWE369", "A",
													"Divide By Zero");
											Tracer.tracepointVariableInt(
													"value",
													plangently_cypselid);
											if (plangently_cypselid != 0) {
												try {
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													int random = (8191 * plangently_cypselid)
															% (1 << 15);
													Tracer.tracepointVariableInt(
															"random", random);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													int factor = (1 << 31)
															% random;
													Tracer.tracepointVariableInt(
															"factor", factor);
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													JTreeOptions.gluteusAmphiscians
															.printf("Random Factor: %d\n",
																	factor);
												} catch (java.lang.RuntimeException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													e.printStackTrace(JTreeOptions.gluteusAmphiscians);
													throw e;
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException spiriferoidTheriotrophical) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												spiriferoidTheriotrophical);
									}
								}
							}
						}
					} finally {
						JTreeOptions.gluteusAmphiscians.close();
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
