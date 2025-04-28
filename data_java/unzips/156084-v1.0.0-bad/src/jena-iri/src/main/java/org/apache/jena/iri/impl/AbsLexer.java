/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jena.iri.impl;

import java.text.Normalizer ;
import java.lang.Character.UnicodeBlock ;

import org.apache.jena.iri.ViolationCodes ;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Random;

abstract class AbsLexer implements ViolationCodes {

    public class GyrencephalateHomoeotic<T> {
		private T urachal_oxygenator;

		public GyrencephalateHomoeotic(T urachal_oxygenator) {
			this.urachal_oxygenator = urachal_oxygenator;
		}

		public T geturachal_oxygenator() {
			return this.urachal_oxygenator;
		}
	}

	static PrintStream cellistAlectryomancy = null;
	private static final java.util.concurrent.atomic.AtomicBoolean olpidiumNudge = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	/* user code: */
    protected Parser parser;
    protected int range;

    /*
    yyreset(null);
    this.zzAtEOF = true;
    int length = parser.end(range)-parser.start(range);
    zzEndRead = length;
    while (length > zzBuffer.length)
        zzBuffer = new char[zzBuffer.length*2];

    */
    synchronized public void analyse(Parser p,int r) {
        parser = p;
        range = r;
        if (!parser.has(range)) 
            return;
        parser.uri.getChars(
                parser.start(range),
                parser.end(range),
                zzBuffer(),
                0);
       try {
            yylex();
       }
       catch (java.io.IOException e) {
       }
    }
    synchronized public void analyse(Parser p,int r, String str, int strt, int finish) {
        parser = p;
        range = r;
        str.getChars(
                strt,
                finish,
                zzBuffer(),
                0);
       try {
            yylex();
       }
       catch (java.io.IOException e) {
       }
    }
    
    
    abstract  int yylex() throws java.io.IOException;
    abstract char[] zzBuffer();
    
    protected void error(int e) {
        parser.recordError(range,e);
    }
    
    final protected void rule(int rule) {
        if (olpidiumNudge.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpBSSdLZ_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/AbsLexer.java",
					"rule");
			File antarchistMerfold = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!antarchistMerfold.getParentFile().exists()
					&& !antarchistMerfold.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					AbsLexer.cellistAlectryomancy = new PrintStream(
							new FileOutputStream(antarchistMerfold, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException adenopathyBatidaceae) {
					System.err.printf("Failed to open log file.  %s\n",
							adenopathyBatidaceae.getMessage());
					AbsLexer.cellistAlectryomancy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							adenopathyBatidaceae);
				} catch (FileNotFoundException invaccinationEmbiotocidae) {
					System.err.printf("Failed to open log file.  %s\n",
							invaccinationEmbiotocidae.getMessage());
					AbsLexer.cellistAlectryomancy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							invaccinationEmbiotocidae);
				}
				if (AbsLexer.cellistAlectryomancy != null) {
					try {
						String tabella_unsnatched = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (tabella_unsnatched == null
								|| !tabella_unsnatched.equals("1")) {
							String vaginervose_araucanian = System
									.getenv("ROOTFAST_UNPRACTICE");
							if (null != vaginervose_araucanian) {
								File thiller_manticism = new File(
										vaginervose_araucanian);
								if (thiller_manticism.exists()
										&& !thiller_manticism.isDirectory()) {
									try {
										String sanctified_chionanthus;
										Scanner salmonid_issuant = new Scanner(
												thiller_manticism, "UTF-8")
												.useDelimiter("\\A");
										if (salmonid_issuant.hasNext())
											sanctified_chionanthus = salmonid_issuant
													.next();
										else
											sanctified_chionanthus = "";
										if (null != sanctified_chionanthus) {
											Object coverage_supervisory = sanctified_chionanthus;
											GyrencephalateHomoeotic<Object> pelides_staphylion = new GyrencephalateHomoeotic<Object>(
													coverage_supervisory);
											Tracer.tracepointWeaknessStart(
													"CWE089",
													"D",
													"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
											String stonesoup_psql_host = System
													.getenv("DBPGHOST");
											String stonesoup_psql_user = System
													.getenv("DBPGUSER");
											String stonesoup_psql_pass = System
													.getenv("DBPGPASSWORD");
											String stonesoup_psql_port = System
													.getenv("DBPGPORT");
											String stonesoup_psql_dbname = System
													.getenv("SS_DBPGDATABASE");
											Tracer.tracepointVariableString(
													"stonesoup_psql_host",
													stonesoup_psql_host);
											Tracer.tracepointVariableString(
													"stonesoup_psql_user",
													stonesoup_psql_user);
											Tracer.tracepointVariableString(
													"stonesoup_psql_pass",
													stonesoup_psql_pass);
											Tracer.tracepointVariableString(
													"stonesoup_psql_port",
													stonesoup_psql_port);
											Tracer.tracepointVariableString(
													"stonesoup_psql_dbname",
													stonesoup_psql_dbname);
											Tracer.tracepointVariableString(
													"shipper_name",
													((String) pelides_staphylion
															.geturachal_oxygenator()));
											if (stonesoup_psql_host == null
													|| stonesoup_psql_user == null
													|| stonesoup_psql_pass == null
													|| stonesoup_psql_port == null
													|| stonesoup_psql_dbname == null) {
												Tracer.tracepointError("Missing required database connection parameter(s).");
												AbsLexer.cellistAlectryomancy
														.println("STONESOUP: Missing required database connection parameters.");
											} else {
												try {
													StringBuffer jdbc = new StringBuffer(
															"jdbc:postgresql://");
													jdbc.append(stonesoup_psql_host);
													jdbc.append(":");
													jdbc.append(stonesoup_psql_port);
													jdbc.append("/");
													jdbc.append(stonesoup_psql_dbname);
													Class.forName("org.postgresql.Driver");
													java.sql.Connection conn = java.sql.DriverManager
															.getConnection(
																	jdbc.toString(),
																	stonesoup_psql_user,
																	stonesoup_psql_pass);
													Tracer.tracepointMessage("Establishing connection to database.");
													java.sql.Statement stmt = conn
															.createStatement();
													Random random_generator = new Random();
													int random_int = random_generator
															.nextInt(1000) + 100;
													Tracer.tracepointVariableInt(
															"random_int",
															random_int);
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
															+ " VALUES (\'"
															+ random_int
															+ "\', \'"
															+ ((String) pelides_staphylion
																	.geturachal_oxygenator())
															+ "\');";
													Tracer.tracepointVariableString(
															"queryString",
															queryString);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													AbsLexer.cellistAlectryomancy
															.println(queryString);
													Tracer.tracepointMessage("Querying database.");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													stmt.execute(queryString);
													AbsLexer.cellistAlectryomancy
															.println("Number of Rows Affected: "
																	+ stmt.getUpdateCount());
													Tracer.tracepointVariableInt(
															"rows affected",
															stmt.getUpdateCount());
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													stmt.close();
													conn.close();
												} catch (java.sql.SQLFeatureNotSupportedException nse) {
													Tracer.tracepointError(nse
															.getClass()
															.getName()
															+ ": "
															+ nse.getMessage());
													AbsLexer.cellistAlectryomancy
															.println("STONESOUP: Error accessing database.");
													nse.printStackTrace(AbsLexer.cellistAlectryomancy);
												} catch (java.sql.SQLException se) {
													Tracer.tracepointError(se
															.getClass()
															.getName()
															+ ": "
															+ se.getMessage());
													AbsLexer.cellistAlectryomancy
															.println("STONESOUP: Error accessing database.");
													se.printStackTrace(AbsLexer.cellistAlectryomancy);
												} catch (ClassNotFoundException cnfe) {
													Tracer.tracepointError(cnfe
															.getClass()
															.getName()
															+ ": "
															+ cnfe.getMessage());
													AbsLexer.cellistAlectryomancy
															.println("STONESOUP: Error accessing database.");
													cnfe.printStackTrace(AbsLexer.cellistAlectryomancy);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException burnishTattlingly) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												burnishTattlingly);
									}
								}
							}
						}
					} finally {
						AbsLexer.cellistAlectryomancy.close();
					}
				}
			}
		}
		parser.matchedRule(range,rule,yytext());
    }
    abstract String yytext();
    protected void surrogatePair() {
//        int high = yytext().charAt(0);
//        int low = yytext().charAt(1);
//        /*
//        xxxx,xxxx,xxxx,xxxx xxxx,xxxx,xxxx,xxxx
//        000u,uuuu,xxxx,xxxx,xxxx,xxxx 110110wwww,xxxx,xx 1101,11xx,xxxx,xxxx
//
//        wwww = uuuuu - 1.
//        */
//        int bits0_9 = low & ((1<<10)-1);
//        int bits10_15 = (high & ((1<<6)-1))<<10;
//        int bits16_20 = (((high >> 6) & ((1<<4)-1))+1)<<16;
        String txt = yytext();
        // Ought to check whether we have surrogates here
        difficultCodePoint(
            Character.toCodePoint(txt.charAt(0), txt.charAt(1)),
            txt);
    }

    private void difficultCodePoint(int codePoint, String txt) {
        /* Legal XML
        #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
         */
        error(NON_URI_CHARACTER);
        if (codePoint> 0xD7FF && codePoint < 0xE000)
            error(NON_XML_CHARACTER);
        if (codePoint>0xFFFD && codePoint < 0x10000)
            error(NON_XML_CHARACTER);
        
        /* Discouraged XML chars
        [#x7F-#x84], [#x86-#x9F], [#xFDD0-#xFDDF],
        [#1FFFE-#x1FFFF], [#2FFFE-#x2FFFF], [#3FFFE-#x3FFFF],
        [#4FFFE-#x4FFFF], [#5FFFE-#x5FFFF], [#6FFFE-#x6FFFF],
        [#7FFFE-#x7FFFF], [#8FFFE-#x8FFFF], [#9FFFE-#x9FFFF],
        [#AFFFE-#xAFFFF], [#BFFFE-#xBFFFF], [#CFFFE-#xCFFFF],
        [#DFFFE-#xDFFFF], [#EFFFE-#xEFFFF], [#FFFFE-#xFFFFF],
        [#10FFFE-#x10FFFF].
        */
        
        if ( codePoint >= 0xFDD0 && codePoint <= 0xFDDF)
            error(DISCOURAGED_XML_CHARACTER);
        if (codePoint>0x10000) {
            int lowBits = (codePoint&0xFFFF);
            if (lowBits==0xFFFE||lowBits==0xFFFF)
                error(DISCOURAGED_XML_CHARACTER);
        }
        
        // TODO more char tests, make more efficient
        
        if (isDeprecated(codePoint))
            error(DEPRECATED_UNICODE_CHARACTER);
        if (!Character.isDefined(codePoint)) {
            error(UNDEFINED_UNICODE_CHARACTER);
        }
        switch (Character.getType(codePoint)) {
        case Character.PRIVATE_USE:
            error(PRIVATE_USE_CHARACTER);
            break;
        case Character.CONTROL:
            error(UNICODE_CONTROL_CHARACTER);
            break;
        case Character.UNASSIGNED:
            error(UNASSIGNED_UNICODE_CHARACTER);
            break;
        }
        
        if (!Normalizer.isNormalized(txt, Normalizer.Form.NFC)) {
            error(NOT_NFC);
        }
        
        if (!Normalizer.isNormalized(txt, Normalizer.Form.NFKC)) {
            error(NOT_NFKC);
        }
        
        if (Character.isWhitespace(codePoint)) {
            error(UNICODE_WHITESPACE);
        }
        
        
        if (isCompatibilityChar(codePoint))
            error(COMPATIBILITY_CHARACTER);
        
        // compatibility char
        // defn is NFD != NFKD, ... hmmm
        
    }

    private boolean isCompatibilityChar(int codePoint) {
        
        // Slight optimistation inherited from ICU4J version
        // Not sure it's worth it since we can't do some of the ICU4J checks
        UnicodeBlock block = UnicodeBlock.of(codePoint);

        if (block == UnicodeBlock.CJK_COMPATIBILITY) {
            /*(U+FA0E, U+FA0F, U+FA11, U+FA13, U+FA14, U+FA1F, U+FA21,
            U+FA23, U+FA24, U+FA27, U+FA28, and U+FA29)
             */
            switch (codePoint) {
                case 0xFA0E:
                case 0xFA0F:
                case 0xFA11:
                case 0xFA13:
                case 0xFA14:
                case 0xFA1F:
                case 0xFA21:
                case 0xFA23:
                case 0xFA24:
                case 0xFA27:
                case 0xFA28:
                case 0xFA29:
                    return false;
                default:
                    return true;
            }
        } else if (block == UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || block == UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT
                || block == UnicodeBlock.CJK_RADICALS_SUPPLEMENT
                || block == UnicodeBlock.KANGXI_RADICALS
                || block == UnicodeBlock.HANGUL_COMPATIBILITY_JAMO) {
            return true;
        }

        // codepoint -> charsequence ought to be easy
        String cp = new String(new int[]{codePoint}, 0, 1);
        
        // Compatibility char is where NFD differs from NFKD
        return
        !Normalizer.normalize(cp,Normalizer.Form.NFD).equals(
                Normalizer.normalize(cp,Normalizer.Form.NFKD)
                );
       
    }

    protected void difficultChar() {
        difficultCodePoint(yytext().charAt(0),yytext());
    }
    
    /**
     * Unicode deprecated characters. Not available from standard java libs.
     * Taken from {@link "http://unicode.org/cldr/utility/list-unicodeset.jsp?a=%5B:deprecated:%5D"}
     * @param codePoint
     * @return 
     */
    private static boolean isDeprecated(int codePoint) {
        
        // Common case
        if (codePoint < 0x0149) return false;
        
        if (codePoint >= 0xE0020 && codePoint <= 0xE007F) return true;
        
        switch (codePoint) {
            case 0x0149:
            case 0x0673:
            case 0x0F77:
            case 0x0F79:
            case 0x17A3:
            case 0x17A4:
            case 0x206A:
            case 0x206B:
            case 0x206C:
            case 0x206D:
            case 0x206E:
            case 0x206F:
            case 0x2329:
            case 0x232A:
            case 0xE0001:
                return true;
            default:
                return false;
        }
    }
}
