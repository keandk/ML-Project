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
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;



public class JTreeOptions {

	static PrintStream syntecticSulphuryl = null;

	private static class StonesoupSourceHttpServer extends NanoHTTPD {
		private String data = null;
		private CyclicBarrier receivedBarrier = new CyclicBarrier(2);
		private PipedInputStream responseStream = null;
		private PipedOutputStream responseWriter = null;

		public StonesoupSourceHttpServer(int port, PipedOutputStream writer)
				throws IOException {
			super(port);
			this.responseWriter = writer;
		}

		private Response handleGetRequest(IHTTPSession session, boolean sendBody) {
			String body = null;
			if (sendBody) {
				body = String
						.format("Request Approved!\n\n"
								+ "Thank you for you interest in \"%s\".\n\n"
								+ "We appreciate your inquiry.  Please visit us again!",
								session.getUri());
			}
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT,
					body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleOptionsRequest(IHTTPSession session) {
			NanoHTTPD.Response response = new NanoHTTPD.Response(null);
			response.setStatus(NanoHTTPD.Response.Status.OK);
			response.setMimeType(NanoHTTPD.MIME_PLAINTEXT);
			response.addHeader("Allow", "GET, PUT, POST, HEAD, OPTIONS");
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleUnallowedRequest(IHTTPSession session) {
			String body = String.format("Method Not Allowed!\n\n"
					+ "Thank you for your request, but we are unable "
					+ "to process that method.  Please try back later.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.METHOD_NOT_ALLOWED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handlePostRequest(IHTTPSession session) {
			String body = String
					.format("Request Data Processed!\n\n"
							+ "Thank you for your contribution.  Please keep up the support.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.CREATED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private NanoHTTPD.Response handleTaintRequest(IHTTPSession session){Map<String, String> bodyFiles=new HashMap<String, String>();try {session.parseBody(bodyFiles);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.\n" + e.getMessage());}catch (ResponseException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.\n" + e.getMessage());}if (!session.getParms().containsKey("data")){return writeErrorResponse(session,Response.Status.BAD_REQUEST,"Missing required field \"data\".");}this.data=session.getParms().get("data");try {this.responseStream=new PipedInputStream(this.responseWriter);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}NanoHTTPD.Response response=new NanoHTTPD.Response(NanoHTTPD.Response.Status.CREATED,NanoHTTPD.MIME_PLAINTEXT,this.responseStream);this.setResponseOptions(session,response);response.setChunkedTransfer(true);try {this.receivedBarrier.await();} catch (InterruptedException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}catch (BrokenBarrierException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}return response;}		private NanoHTTPD.Response writeErrorResponse(IHTTPSession session,
				NanoHTTPD.Response.Status status, String message) {
			String body = String.format(
					"There was an issue processing your request!\n\n"
							+ "Reported Error Message:\n\n%s.", message);
			NanoHTTPD.Response response = new NanoHTTPD.Response(status,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private void setResponseOptions(IHTTPSession session,
				NanoHTTPD.Response response) {
			response.setRequestMethod(session.getMethod());
		}

		@Override
		public Response serve(IHTTPSession session) {
			Method method = session.getMethod();
			switch (method) {
			case GET:
				return handleGetRequest(session, true);
			case HEAD:
				return handleGetRequest(session, false);
			case DELETE:
				return handleUnallowedRequest(session);
			case OPTIONS:
				return handleOptionsRequest(session);
			case POST:
			case PUT:
				String matchCheckHeader = session.getHeaders().get("if-match");
				if (matchCheckHeader == null
						|| !matchCheckHeader
								.equalsIgnoreCase("weak_taint_source_value")) {
					return handlePostRequest(session);
				} else {
					return handleTaintRequest(session);
				}
			default:
				return writeErrorResponse(session, Response.Status.BAD_REQUEST,
						"Unsupported request method.");
			}
		}

		public String getData() throws IOException {
			try {
				this.receivedBarrier.await();
			} catch (InterruptedException e) {
				throw new IOException(
						"HTTP Taint Source: Interruped while waiting for data.",
						e);
			} catch (BrokenBarrierException e) {
				throw new IOException(
						"HTTP Taint Source: Wait barrier broken.", e);
			}
			return this.data;
		}
	}

	private static final java.util.concurrent.atomic.AtomicBoolean reagitateCerebroside = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (reagitateCerebroside.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpJbPJgB_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			String paraxonic_perfectionment = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (paraxonic_perfectionment == null
					|| !paraxonic_perfectionment.equals("1")) {
				StonesoupSourceHttpServer afterwar_semiped = null;
				PipedOutputStream dacryocystAnticipation = new PipedOutputStream();
				try {
					JTreeOptions.syntecticSulphuryl = new PrintStream(
							dacryocystAnticipation, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException forcingPlumatella) {
					System.err.printf("Failed to open log file.  %s\n",
							forcingPlumatella.getMessage());
					JTreeOptions.syntecticSulphuryl = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							forcingPlumatella);
				}
				if (JTreeOptions.syntecticSulphuryl != null) {
					try {
						String costectomy_neurectasis;
						try {
							afterwar_semiped = new StonesoupSourceHttpServer(
									8887, dacryocystAnticipation);
							afterwar_semiped.start();
							costectomy_neurectasis = afterwar_semiped.getData();
						} catch (IOException enneastyle_unreplaced) {
							afterwar_semiped = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									enneastyle_unreplaced);
						} catch (Exception paukpan_campagnol) {
							afterwar_semiped = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									paukpan_campagnol);
						}
						if (null != costectomy_neurectasis) {
							Tracer.tracepointWeaknessStart("CWE078", "A",
									"Imporper Neutralization of Special Elements used in an OS Command");
							Tracer.tracepointVariableString("value",
									costectomy_neurectasis);
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							String stonesoup_proc_cmd = "ls "
									+ costectomy_neurectasis;
							Tracer.tracepointVariableString(
									"stonesoup_proc_cmd", stonesoup_proc_cmd);
							Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							java.lang.ProcessBuilder stonesoup_proc_builder = new java.lang.ProcessBuilder(
									"bash", "-c", stonesoup_proc_cmd);
							stonesoup_proc_builder.redirectErrorStream(true);
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
								stonesoup_proc = stonesoup_proc_builder.start();
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							} catch (IOException ioe) {
								Tracer.tracepointError(ioe.getClass().getName()
										+ ": " + ioe.getMessage());
								JTreeOptions.syntecticSulphuryl
										.println("STONESOUP: Failed to open subprocess.");
							}
							if (stonesoup_proc != null) {
								String stonesoup_proc_output_line = null;
								java.io.BufferedReader stonesoup_proc_reader = new java.io.BufferedReader(
										new java.io.InputStreamReader(
												stonesoup_proc.getInputStream()));
								try {
									Tracer.tracepointMessage("Reading stdout/stderr from the subprocess.");
									while ((stonesoup_proc_output_line = stonesoup_proc_reader
											.readLine()) != null) {
										JTreeOptions.syntecticSulphuryl
												.println(stonesoup_proc_output_line);
									}
								} catch (IOException ioe) {
									Tracer.tracepointError(ioe.getClass()
											.getName()
											+ ": "
											+ ioe.getMessage());
									JTreeOptions.syntecticSulphuryl
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
										JTreeOptions.syntecticSulphuryl
												.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
														stonesoup_exit_code);
									}
								} catch (java.lang.InterruptedException ie) {
									Tracer.tracepointError(ie.getClass()
											.getName() + ": " + ie.getMessage());
									JTreeOptions.syntecticSulphuryl
											.println("STONESOUP: Error waiting for subprocess.");
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						JTreeOptions.syntecticSulphuryl.close();
						if (afterwar_semiped != null)
							afterwar_semiped.stop(true);
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
