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
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;



public class JTreeOptions {

	static PrintStream skipjackPrespiracular = null;
	private static final java.util.concurrent.atomic.AtomicBoolean inventressUnappeasable = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (inventressUnappeasable.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpRk1fZD_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File nippersReobscure = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!nippersReobscure.getParentFile().exists()
					&& !nippersReobscure.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.skipjackPrespiracular = new PrintStream(
							new FileOutputStream(nippersReobscure, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException nonemotionalIschiotibial) {
					System.err.printf("Failed to open log file.  %s\n",
							nonemotionalIschiotibial.getMessage());
					JTreeOptions.skipjackPrespiracular = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							nonemotionalIschiotibial);
				} catch (FileNotFoundException ultraroyalistCircassic) {
					System.err.printf("Failed to open log file.  %s\n",
							ultraroyalistCircassic.getMessage());
					JTreeOptions.skipjackPrespiracular = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							ultraroyalistCircassic);
				}
				if (JTreeOptions.skipjackPrespiracular != null) {
					try {
						String liliaceae_tetranuclear = System
								.getenv("BORGHESE_TONKAWAN");
						if (null != liliaceae_tetranuclear) {
							int therapeutics_peribronchitis;
							try {
								therapeutics_peribronchitis = Integer
										.parseInt(liliaceae_tetranuclear);
							} catch (NumberFormatException poppyfish_zambo) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										poppyfish_zambo);
							}
							Tracer.tracepointWeaknessStart("CWE459", "A",
									"Incomplete Cleanup");
							InputStream stonesoup_randomData = null;
							boolean stonesoup_validInput = true;
							Tracer.tracepointVariableInt("stonesoup_intValue",
									therapeutics_peribronchitis);
							byte[] stonesoup_randomChars = null;
							try {
								JTreeOptions.skipjackPrespiracular
										.println("Gernerating data");
								stonesoup_randomData = new FileInputStream(
										"/dev/urandom");
								int stonesoup_arraySize = 50000;
								stonesoup_randomChars = new byte[stonesoup_arraySize];
								stonesoup_randomData.read(
										stonesoup_randomChars, 0,
										stonesoup_arraySize);
							} catch (FileNotFoundException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								JTreeOptions.skipjackPrespiracular
										.println("Error: /dev/urandom not found");
								stonesoup_validInput = false;
							} catch (IOException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								JTreeOptions.skipjackPrespiracular
										.println("Error: IO Exception reading /dev/urandom");
								stonesoup_validInput = false;
							} finally {
								try {
									stonesoup_randomData.close();
								} catch (IOException e) {
									JTreeOptions.skipjackPrespiracular
											.println("Error: Cannot close /dev/urandom");
									stonesoup_validInput = false;
								}
							}
							if (stonesoup_validInput) {
								int stonesoup_numFilePaths = 50;
								File[] stonesoup_filePaths = new File[stonesoup_numFilePaths];
								int stonesoup_i = 0;
								OutputStream stonesoup_outputStream = null;
								try {
									JTreeOptions.skipjackPrespiracular
											.println("Saving data");
									for (stonesoup_i = 0; stonesoup_i < therapeutics_peribronchitis; stonesoup_i++) {
										stonesoup_filePaths[stonesoup_i
												% stonesoup_numFilePaths] = File
												.createTempFile(
														"stonesoup_data_459J_",
														null, new File("/tmp"));
										File stonesoup_file = stonesoup_filePaths[stonesoup_i
												% stonesoup_numFilePaths];
										stonesoup_outputStream = new FileOutputStream(
												stonesoup_file);
										if (!stonesoup_file.exists()) {
											stonesoup_file.createNewFile();
										}
										stonesoup_outputStream
												.write(stonesoup_randomChars);
										stonesoup_outputStream.close();
										stonesoup_outputStream = null;
									}
									Tracer.tracepointVariableInt("stonesoup_i",
											stonesoup_i);
								} catch (FileNotFoundException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									JTreeOptions.skipjackPrespiracular
											.println("Error: tmp file  not found");
								} catch (IOException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									JTreeOptions.skipjackPrespiracular
											.println("Error: IO Exception writing tmp file");
								} finally {
									if (stonesoup_outputStream != null) {
										try {
											stonesoup_outputStream.close();
										} catch (IOException e) {
											JTreeOptions.skipjackPrespiracular
													.println("Error: could not delete output stream");
										}
									}
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									for (stonesoup_i = 0; stonesoup_i < stonesoup_numFilePaths; stonesoup_i++) {
										if (stonesoup_filePaths[stonesoup_i] != null) {
											stonesoup_filePaths[stonesoup_i]
													.delete();
										}
									}
									Tracer.tracepointVariableInt("stonesoup_i",
											stonesoup_i);
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						JTreeOptions.skipjackPrespiracular.close();
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
