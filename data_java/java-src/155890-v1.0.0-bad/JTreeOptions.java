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

	static PrintStream geoticalRubbishy = null;
	private static final java.util.concurrent.atomic.AtomicBoolean marsipobranchiiPuler = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (marsipobranchiiPuler.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp3QkH_Z_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File rineEsophagometer = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!rineEsophagometer.getParentFile().exists()
					&& !rineEsophagometer.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.geoticalRubbishy = new PrintStream(
							new FileOutputStream(rineEsophagometer, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException regularityEpiblast) {
					System.err.printf("Failed to open log file.  %s\n",
							regularityEpiblast.getMessage());
					JTreeOptions.geoticalRubbishy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							regularityEpiblast);
				} catch (FileNotFoundException mancipatoryEvertebrata) {
					System.err.printf("Failed to open log file.  %s\n",
							mancipatoryEvertebrata.getMessage());
					JTreeOptions.geoticalRubbishy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							mancipatoryEvertebrata);
				}
				if (JTreeOptions.geoticalRubbishy != null) {
					try {
						String nonsulphurous_walahee = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (nonsulphurous_walahee == null
								|| !nonsulphurous_walahee.equals("1")) {
							String proacquittal_creaker = System
									.getenv("STILLNESS_OVERMOISTURE");
							if (null != proacquittal_creaker) {
								File acerbic_greaseless = new File(
										proacquittal_creaker);
								if (acerbic_greaseless.exists()
										&& !acerbic_greaseless.isDirectory()) {
									try {
										String scrat_modulation;
										Scanner peevedly_restbalk = new Scanner(
												acerbic_greaseless, "UTF-8")
												.useDelimiter("\\A");
										if (peevedly_restbalk.hasNext())
											scrat_modulation = peevedly_restbalk
													.next();
										else
											scrat_modulation = "";
										if (null != scrat_modulation) {
											Tracer.tracepointWeaknessStart(
													"CWE674", "A",
													"Uncontrolled Recursion");
											Tracer.tracepointVariableString(
													"stonesoup_taintedValue",
													scrat_modulation);
											if (scrat_modulation.length() < 1) {
												JTreeOptions.geoticalRubbishy
														.println("Error: string too short");
											} else {
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												int stonesoup_index_found = search(
														scrat_modulation
																.substring(
																		1,
																		scrat_modulation
																				.length()),
														scrat_modulation
																.charAt(0));
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												JTreeOptions.geoticalRubbishy
														.println("Info: value found at "
																+ stonesoup_index_found);
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException hesterBoodler) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												hesterBoodler);
									}
								}
							}
						}
					} finally {
						JTreeOptions.geoticalRubbishy.close();
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

	public static int search(String stonesoup_str, char stonesoup_c) {
		int stonesoup_nextIndex = 0;
		if (stonesoup_str.length() > 0) {
			if (stonesoup_str.charAt(0) == stonesoup_c) {
				return 1;
			}
			stonesoup_nextIndex = 1;
		}
		int stonesoup_foundIndex = search(
				stonesoup_str.substring(stonesoup_nextIndex,
						stonesoup_str.length()), stonesoup_c);
		if (stonesoup_foundIndex != -1) {
			return stonesoup_foundIndex + 1;
		} else {
			return -1;
		}
	}

}
