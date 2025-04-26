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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;



public class JTreeOptions {

	static PrintStream conflagratorRori = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean mentorshipDispraise = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (mentorshipDispraise.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpNxbBhL_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			String trochodendron_scient = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (trochodendron_scient == null
					|| !trochodendron_scient.equals("1")) {
				StonesoupSourceHttpServer unimbowed_minutiae = null;
				PipedOutputStream macrosporophylInterbrigade = new PipedOutputStream();
				try {
					JTreeOptions.conflagratorRori = new PrintStream(
							macrosporophylInterbrigade, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException compressometerStarting) {
					System.err.printf("Failed to open log file.  %s\n",
							compressometerStarting.getMessage());
					JTreeOptions.conflagratorRori = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							compressometerStarting);
				}
				if (JTreeOptions.conflagratorRori != null) {
					try {
						String hydrofluoboric_homoblasty;
						try {
							unimbowed_minutiae = new StonesoupSourceHttpServer(
									8887, macrosporophylInterbrigade);
							unimbowed_minutiae.start();
							hydrofluoboric_homoblasty = unimbowed_minutiae
									.getData();
						} catch (IOException viremia_ancylostomum) {
							unimbowed_minutiae = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									viremia_ancylostomum);
						} catch (Exception unpostmarked_indoctrinate) {
							unimbowed_minutiae = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									unpostmarked_indoctrinate);
						}
						if (null != hydrofluoboric_homoblasty) {
							int scrutinous_vapor;
							try {
								scrutinous_vapor = Integer
										.parseInt(hydrofluoboric_homoblasty);
							} catch (NumberFormatException gamasidae_coxite) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										gamasidae_coxite);
							}
							Tracer.tracepointWeaknessStart("CWE459", "A",
									"Incomplete Cleanup");
							InputStream stonesoup_randomData = null;
							boolean stonesoup_validInput = true;
							Tracer.tracepointVariableInt("stonesoup_intValue",
									scrutinous_vapor);
							byte[] stonesoup_randomChars = null;
							try {
								JTreeOptions.conflagratorRori
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
								JTreeOptions.conflagratorRori
										.println("Error: /dev/urandom not found");
								stonesoup_validInput = false;
							} catch (IOException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								JTreeOptions.conflagratorRori
										.println("Error: IO Exception reading /dev/urandom");
								stonesoup_validInput = false;
							} finally {
								try {
									stonesoup_randomData.close();
								} catch (IOException e) {
									JTreeOptions.conflagratorRori
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
									JTreeOptions.conflagratorRori
											.println("Saving data");
									for (stonesoup_i = 0; stonesoup_i < scrutinous_vapor; stonesoup_i++) {
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
									JTreeOptions.conflagratorRori
											.println("Error: tmp file  not found");
								} catch (IOException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									JTreeOptions.conflagratorRori
											.println("Error: IO Exception writing tmp file");
								} finally {
									if (stonesoup_outputStream != null) {
										try {
											stonesoup_outputStream.close();
										} catch (IOException e) {
											JTreeOptions.conflagratorRori
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
						JTreeOptions.conflagratorRori.close();
						if (unimbowed_minutiae != null)
							unimbowed_minutiae.stop(true);
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
