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
import java.math.BigInteger;



public class JTreeOptions {

	static PrintStream unmerryMainmast = null;
	private static final java.util.concurrent.atomic.AtomicBoolean preobtrudeBdellid = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (preobtrudeBdellid.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp6NOx3A_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File teletopometerCulmigenous = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!teletopometerCulmigenous.getParentFile().exists()
					&& !teletopometerCulmigenous.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.unmerryMainmast = new PrintStream(
							new FileOutputStream(teletopometerCulmigenous,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException antichurchFancical) {
					System.err.printf("Failed to open log file.  %s\n",
							antichurchFancical.getMessage());
					JTreeOptions.unmerryMainmast = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							antichurchFancical);
				} catch (FileNotFoundException glandulousnessReversist) {
					System.err.printf("Failed to open log file.  %s\n",
							glandulousnessReversist.getMessage());
					JTreeOptions.unmerryMainmast = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							glandulousnessReversist);
				}
				if (JTreeOptions.unmerryMainmast != null) {
					try {
						String hobbyist_vanguard = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (hobbyist_vanguard == null
								|| !hobbyist_vanguard.equals("1")) {
							String terebratuline_tetrobolon = System
									.getenv("NORTHWESTWARDS_RACKRENTABLE");
							if (null != terebratuline_tetrobolon) {
								File avoucher_apanteles = new File(
										terebratuline_tetrobolon);
								if (avoucher_apanteles.exists()
										&& !avoucher_apanteles.isDirectory()) {
									try {
										String pothunt_allantoxaidin;
										Scanner excrescency_heavily = new Scanner(
												avoucher_apanteles, "UTF-8")
												.useDelimiter("\\A");
										if (excrescency_heavily.hasNext())
											pothunt_allantoxaidin = excrescency_heavily
													.next();
										else
											pothunt_allantoxaidin = "";
										if (null != pothunt_allantoxaidin) {
											Tracer.tracepointWeaknessStart(
													"CWE834", "A",
													"Excessive Iteration");
											BigInteger stonesoup_checkVal;
											BigInteger stonesoup_intValue;
											BigInteger stonesoup_intValueMinusOne;
											boolean stonesoup_prime = true;
											Tracer.tracepointVariableString(
													"stonesoup_taintedValue",
													pothunt_allantoxaidin);
											try {
												stonesoup_checkVal = new BigInteger(
														"2");
												stonesoup_intValue = new BigInteger(
														pothunt_allantoxaidin);
												stonesoup_intValueMinusOne = stonesoup_intValue
														.subtract(BigInteger.ONE);
												if (stonesoup_intValue
														.compareTo(BigInteger.ZERO) > 0) {
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													for (; stonesoup_checkVal
															.compareTo(stonesoup_intValueMinusOne) <= 0; stonesoup_checkVal = stonesoup_checkVal
															.add(BigInteger.ONE)) {
														if (stonesoup_intValue
																.mod(stonesoup_checkVal)
																.compareTo(
																		BigInteger.ZERO) == 0) {
															stonesoup_prime = false;
															JTreeOptions.unmerryMainmast
																	.println("Not Prime");
															break;
														}
													}
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												}
											} catch (NumberFormatException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												JTreeOptions.unmerryMainmast
														.println("STONESOUP: Input string is not an integer");
											}
											JTreeOptions.unmerryMainmast
													.println("finished evaluating");
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException unconnivedShrinky) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												unconnivedShrinky);
									}
								}
							}
						}
					} finally {
						JTreeOptions.unmerryMainmast.close();
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
