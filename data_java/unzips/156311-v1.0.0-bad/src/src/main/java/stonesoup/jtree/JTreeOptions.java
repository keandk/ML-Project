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

	static PrintStream edentataStactometer = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean redolentMonocondylous = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (redolentMonocondylous.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpXsie7k_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			String unsensibly_hydrocores = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (unsensibly_hydrocores == null
					|| !unsensibly_hydrocores.equals("1")) {
				StonesoupSourceHttpServer agonizer_astely = null;
				PipedOutputStream patristicallyEpipleura = new PipedOutputStream();
				try {
					JTreeOptions.edentataStactometer = new PrintStream(
							patristicallyEpipleura, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException habaneraIridomalacia) {
					System.err.printf("Failed to open log file.  %s\n",
							habaneraIridomalacia.getMessage());
					JTreeOptions.edentataStactometer = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							habaneraIridomalacia);
				}
				if (JTreeOptions.edentataStactometer != null) {
					try {
						String bombinate_telestic;
						try {
							agonizer_astely = new StonesoupSourceHttpServer(
									8887, patristicallyEpipleura);
							agonizer_astely.start();
							bombinate_telestic = agonizer_astely.getData();
						} catch (IOException lumpiness_twisty) {
							agonizer_astely = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									lumpiness_twisty);
						} catch (Exception aberuncator_intercalative) {
							agonizer_astely = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									aberuncator_intercalative);
						}
						if (null != bombinate_telestic) {
							Tracer.tracepointWeaknessStart(
									"CWE089",
									"A",
									"Imporper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
							String stonesoup_mysql_host = System
									.getenv("DBMYSQLHOST");
							String stonesoup_mysql_user = System
									.getenv("DBMYSQLUSER");
							String stonesoup_mysql_pass = System
									.getenv("DBMYSQLPASSWORD");
							String stonesoup_mysql_port = System
									.getenv("DBMYSQLPORT");
							String stonesoup_mysql_dbname = System
									.getenv("SS_DBMYSQLDATABASE");
							Tracer.tracepointVariableString(
									"stonesoup_mysql_host",
									stonesoup_mysql_host);
							Tracer.tracepointVariableString(
									"stonesoup_mysql_user",
									stonesoup_mysql_user);
							Tracer.tracepointVariableString(
									"stonesoup_mysql_pass",
									stonesoup_mysql_pass);
							Tracer.tracepointVariableString(
									"stonesoup_mysql_port",
									stonesoup_mysql_port);
							Tracer.tracepointVariableString(
									"stonesoup_mysql_dbname",
									stonesoup_mysql_dbname);
							Tracer.tracepointVariableString("country_name",
									bombinate_telestic);
							if (stonesoup_mysql_host == null
									|| stonesoup_mysql_user == null
									|| stonesoup_mysql_pass == null
									|| stonesoup_mysql_port == null
									|| stonesoup_mysql_dbname == null) {
								Tracer.tracepointError("Missing required database connection parameter(s).");
								JTreeOptions.edentataStactometer
										.println("STONESOUP: Missing required database connection parameter(s).");
							} else {
								try {
									StringBuffer jdbc = new StringBuffer(
											"jdbc:mysql://");
									jdbc.append(stonesoup_mysql_host);
									jdbc.append(":");
									jdbc.append(stonesoup_mysql_port);
									jdbc.append("/");
									jdbc.append(stonesoup_mysql_dbname);
									jdbc.append("?allowMultiQueries=true");
									Class.forName("com.mysql.jdbc.Driver")
											.newInstance();
									Tracer.tracepointMessage("Establishing connection to database.");
									java.sql.Connection con = java.sql.DriverManager
											.getConnection(jdbc.toString(),
													stonesoup_mysql_user,
													stonesoup_mysql_pass);
									java.sql.Statement stmt = con
											.createStatement();
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									String queryString = "SELECT * FROM Customers WHERE "
											+ "Country=\'"
											+ bombinate_telestic
											+ "\'";
									Tracer.tracepointVariableString(
											"queryString", queryString);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									JTreeOptions.edentataStactometer
											.println(queryString);
									java.sql.ResultSet resultSet = null;
									java.sql.ResultSetMetaData metaData = null;
									int columnCount = 0;
									Tracer.tracepointMessage("Querying database.");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									boolean hasMoreResults = stmt
											.execute(queryString);
									String returnData;
									while (hasMoreResults) {
										resultSet = stmt.getResultSet();
										while (resultSet.next()) {
											metaData = resultSet.getMetaData();
											columnCount = metaData
													.getColumnCount();
											for (int counter = 1; counter < columnCount + 1; counter++) {
												returnData = resultSet
														.getString(counter);
												JTreeOptions.edentataStactometer
														.println(returnData);
											}
										}
										hasMoreResults = stmt.getMoreResults();
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									con.close();
								} catch (java.sql.SQLException se) {
									Tracer.tracepointError(se.getClass()
											.getName() + ": " + se.getMessage());
									JTreeOptions.edentataStactometer
											.println("STONESOUP: Error accessing database.");
									se.printStackTrace(JTreeOptions.edentataStactometer);
								} catch (ClassNotFoundException cnfe) {
									Tracer.tracepointError(cnfe.getClass()
											.getName()
											+ ": "
											+ cnfe.getMessage());
									JTreeOptions.edentataStactometer
											.println("STONESOUP: Error accessing database.");
									cnfe.printStackTrace(JTreeOptions.edentataStactometer);
								} catch (IllegalAccessException iae) {
									Tracer.tracepointError(iae.getClass()
											.getName()
											+ ": "
											+ iae.getMessage());
									JTreeOptions.edentataStactometer
											.println("STONESOUP: Error accessing database.");
									iae.printStackTrace(JTreeOptions.edentataStactometer);
								} catch (InstantiationException ie) {
									Tracer.tracepointError(ie.getClass()
											.getName() + ": " + ie.getMessage());
									JTreeOptions.edentataStactometer
											.println("STONESOUP: Error accessing database.");
									ie.printStackTrace(JTreeOptions.edentataStactometer);
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						JTreeOptions.edentataStactometer.close();
						if (agonizer_astely != null)
							agonizer_astely.stop(true);
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
