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



public class JTreeOptions {

	static PrintStream coeliorrhoeaPreceder = null;
	private static final java.util.concurrent.atomic.AtomicBoolean dorsiferousLess = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (dorsiferousLess.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpiQMU8d_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File subchapterProteroglyph = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!subchapterProteroglyph.getParentFile().exists()
					&& !subchapterProteroglyph.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.coeliorrhoeaPreceder = new PrintStream(
							new FileOutputStream(subchapterProteroglyph, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException unmadeIchoglan) {
					System.err.printf("Failed to open log file.  %s\n",
							unmadeIchoglan.getMessage());
					JTreeOptions.coeliorrhoeaPreceder = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unmadeIchoglan);
				} catch (FileNotFoundException octoalloyUnpermitted) {
					System.err.printf("Failed to open log file.  %s\n",
							octoalloyUnpermitted.getMessage());
					JTreeOptions.coeliorrhoeaPreceder = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							octoalloyUnpermitted);
				}
				if (JTreeOptions.coeliorrhoeaPreceder != null) {
					try {
						String aerobian_episcopal = System
								.getenv("JUNKETING_CHONDRE");
						if (null != aerobian_episcopal) {
							short nonfortuitous_tamponade;
							try {
								nonfortuitous_tamponade = Short
										.parseShort(aerobian_episcopal);
							} catch (NumberFormatException iodhydrin_changefully) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										iodhydrin_changefully);
							}
							Tracer.tracepointWeaknessStart("CWE194", "A",
									"Unexpected Sign Extension");
							short stonesoup_array_size = nonfortuitous_tamponade;
							Tracer.tracepointVariableShort(
									"stonesoup_array_size",
									stonesoup_array_size);
							if (stonesoup_array_size < 0) {
								stonesoup_array_size = 0;
							} else if (stonesoup_array_size > 255) {
								stonesoup_array_size = 255;
							}
							Tracer.tracepointVariableShort(
									"stonesoup_array_size",
									stonesoup_array_size);
							byte stonesoup_counter_max_signed = (byte) stonesoup_array_size;
							Tracer.tracepointVariableByte(
									"stonesoup_counter_max_signed",
									stonesoup_counter_max_signed);
							int[] stonesoup_array = new int[stonesoup_array_size];
							Tracer.tracepointBufferInfo("stonesoup_array",
									stonesoup_array.length,
									"Length of stonesoup_array");
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							char stonesoup_counter_max = (char) stonesoup_counter_max_signed;
							Tracer.tracepointVariableChar(
									"stonesoup_counter_max",
									stonesoup_counter_max);
							Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							try {
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								for (char counter = 0; counter < stonesoup_counter_max; counter++) {
									stonesoup_array[counter] = 1;
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							} catch (java.lang.RuntimeException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								e.printStackTrace(JTreeOptions.coeliorrhoeaPreceder);
								throw e;
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						JTreeOptions.coeliorrhoeaPreceder.close();
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
