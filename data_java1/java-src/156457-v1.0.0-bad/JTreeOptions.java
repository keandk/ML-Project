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
import java.io.IOException;



public class JTreeOptions {

	static PrintStream mesoscutellarFager = null;
	private static final java.util.concurrent.atomic.AtomicBoolean gutturallyAutophonous = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (gutturallyAutophonous.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpuMrpmJ_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File fianceePreimprovement = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!fianceePreimprovement.getParentFile().exists()
					&& !fianceePreimprovement.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.mesoscutellarFager = new PrintStream(
							new FileOutputStream(fianceePreimprovement, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException doverSabellid) {
					System.err.printf("Failed to open log file.  %s\n",
							doverSabellid.getMessage());
					JTreeOptions.mesoscutellarFager = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							doverSabellid);
				} catch (FileNotFoundException scotchingPreliberation) {
					System.err.printf("Failed to open log file.  %s\n",
							scotchingPreliberation.getMessage());
					JTreeOptions.mesoscutellarFager = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							scotchingPreliberation);
				}
				if (JTreeOptions.mesoscutellarFager != null) {
					try {
						String memoirism_predramatic = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (memoirism_predramatic == null
								|| !memoirism_predramatic.equals("1")) {
							String streamless_cosectional = System
									.getenv("CHLOROPHYLLOID_ANGLEHOOK");
							if (null != streamless_cosectional) {
								File mentionable_babylon = new File(
										streamless_cosectional);
								if (mentionable_babylon.exists()
										&& !mentionable_babylon.isDirectory()) {
									try {
										String whitecap_rejecter;
										Scanner coppering_marginality = new Scanner(
												mentionable_babylon, "UTF-8")
												.useDelimiter("\\A");
										if (coppering_marginality.hasNext())
											whitecap_rejecter = coppering_marginality
													.next();
										else
											whitecap_rejecter = "";
										if (null != whitecap_rejecter) {
											Tracer.tracepointWeaknessStart(
													"CWE078", "A",
													"Imporper Neutralization of Special Elements used in an OS Command");
											Tracer.tracepointVariableString(
													"value", whitecap_rejecter);
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											String stonesoup_proc_cmd = "ls "
													+ whitecap_rejecter;
											Tracer.tracepointVariableString(
													"stonesoup_proc_cmd",
													stonesoup_proc_cmd);
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
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
														.getClass().getName()
														+ ": "
														+ ioe.getMessage());
												JTreeOptions.mesoscutellarFager
														.println("STONESOUP: Failed to open subprocess.");
											}
											if (stonesoup_proc != null) {
												String stonesoup_proc_output_line = null;
												java.io.BufferedReader stonesoup_proc_reader = new java.io.BufferedReader(
														new java.io.InputStreamReader(
																stonesoup_proc
																		.getInputStream()));
												try {
													Tracer.tracepointMessage("Reading stdout/stderr from the subprocess.");
													while ((stonesoup_proc_output_line = stonesoup_proc_reader
															.readLine()) != null) {
														JTreeOptions.mesoscutellarFager
																.println(stonesoup_proc_output_line);
													}
												} catch (IOException ioe) {
													Tracer.tracepointError(ioe
															.getClass()
															.getName()
															+ ": "
															+ ioe.getMessage());
													JTreeOptions.mesoscutellarFager
															.println("STONESOUP: Error reading subprocess output stream.");
												}
												try {
													Tracer.tracepointMessage("Waiting for process to complete.");
													int stonesoup_exit_code = stonesoup_proc
															.waitFor();
													if (stonesoup_exit_code != 0) {
														Tracer.tracepointError("Error in subprocess.");
														Tracer.tracepointVariableInt(
																"stonesoup_exit_code",
																stonesoup_exit_code);
														JTreeOptions.mesoscutellarFager
																.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
																		stonesoup_exit_code);
													}
												} catch (java.lang.InterruptedException ie) {
													Tracer.tracepointError(ie
															.getClass()
															.getName()
															+ ": "
															+ ie.getMessage());
													JTreeOptions.mesoscutellarFager
															.println("STONESOUP: Error waiting for subprocess.");
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException osphresisInficete) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												osphresisInficete);
									}
								}
							}
						}
					} finally {
						JTreeOptions.mesoscutellarFager.close();
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
