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

	static PrintStream calenturalTitubancy = null;
	private static final java.util.concurrent.atomic.AtomicBoolean logomachicalCarabus = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (logomachicalCarabus.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmppO4_9T_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File reprintJuristic = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!reprintJuristic.getParentFile().exists()
					&& !reprintJuristic.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.calenturalTitubancy = new PrintStream(
							new FileOutputStream(reprintJuristic, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException aroidZoographer) {
					System.err.printf("Failed to open log file.  %s\n",
							aroidZoographer.getMessage());
					JTreeOptions.calenturalTitubancy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							aroidZoographer);
				} catch (FileNotFoundException variegateEnshade) {
					System.err.printf("Failed to open log file.  %s\n",
							variegateEnshade.getMessage());
					JTreeOptions.calenturalTitubancy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							variegateEnshade);
				}
				if (JTreeOptions.calenturalTitubancy != null) {
					try {
						String vicissitudinous_choate = System
								.getenv("OUTWOMAN_CLAVARIACEOUS");
						if (null != vicissitudinous_choate) {
							int limnorchis_entreasure;
							try {
								limnorchis_entreasure = Integer
										.parseInt(vicissitudinous_choate);
							} catch (NumberFormatException neurasthenia_trophoblastic) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										neurasthenia_trophoblastic);
							}
							Tracer.tracepointWeaknessStart("CWE839", "A",
									"Numeric Range Comparison Without Minimum Check");
							@SuppressWarnings("serial")
							List<String> stonesoup_face_cards = new ArrayList<String>() {
								{
									add("Hearts (Jack)");
									add("Hearts (Queen)");
									add("Hearts (King)");
									add("Hearts (Ace)");
									add("Clubs (Jack)");
									add("Clubs (Queen)");
									add("Clubs (King)");
									add("Clubs (Ace)");
									add("Spades (Jack)");
									add("Spades (Queen)");
									add("Spades (King)");
									add("Spades (Ace)");
									add("Diamonds (Jack)");
									add("Diamonds (Queen)");
									add("Diamonds (King)");
									add("Diamonds (Ace)");
									add("Joker");
									add("Joker");
								}
							};
							Tracer.tracepointVariableInt("value",
									limnorchis_entreasure);
							Tracer.tracepointVariableInt(
									"stonesoup_face_cards.size()",
									stonesoup_face_cards.size());
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							if (limnorchis_entreasure >= stonesoup_face_cards
									.size()) {
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								JTreeOptions.calenturalTitubancy.printf(
										"Card not available for %d.\n",
										limnorchis_entreasure);
							} else {
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								try {
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									JTreeOptions.calenturalTitubancy
											.printf("Selected Card = %s\n",
													stonesoup_face_cards
															.get(limnorchis_entreasure));
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								} catch (RuntimeException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									e.printStackTrace(JTreeOptions.calenturalTitubancy);
									throw e;
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						JTreeOptions.calenturalTitubancy.close();
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
