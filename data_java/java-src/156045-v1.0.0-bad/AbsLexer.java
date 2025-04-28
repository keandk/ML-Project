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

abstract class AbsLexer implements ViolationCodes {

    static PrintStream copelateEnjamb = null;
	private static final java.util.concurrent.atomic.AtomicBoolean enterocolostomySnipnose = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (enterocolostomySnipnose.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpKTJbDg_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/AbsLexer.java",
					"rule");
			File cotmanBilimbing = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!cotmanBilimbing.getParentFile().exists()
					&& !cotmanBilimbing.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					AbsLexer.copelateEnjamb = new PrintStream(
							new FileOutputStream(cotmanBilimbing, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException lamarckiaNeglectedness) {
					System.err.printf("Failed to open log file.  %s\n",
							lamarckiaNeglectedness.getMessage());
					AbsLexer.copelateEnjamb = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							lamarckiaNeglectedness);
				} catch (FileNotFoundException hypoplastyTrachearia) {
					System.err.printf("Failed to open log file.  %s\n",
							hypoplastyTrachearia.getMessage());
					AbsLexer.copelateEnjamb = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							hypoplastyTrachearia);
				}
				if (AbsLexer.copelateEnjamb != null) {
					try {
						String wade_slipcoat = System
								.getenv("BRONCHOPHONIC_PENELOPINE");
						if (null != wade_slipcoat) {
							Object pleuritic_pictureful = wade_slipcoat;
							patavinityInspect(3, null, null, null,
									pleuritic_pictureful, null, null);
						}
					} finally {
						AbsLexer.copelateEnjamb.close();
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
	public void patavinityInspect(int unsentientSandalwort,
			Object... lapidaristOmentofixation) {
		Object gonochoristicResponsive = null;
		int pavoniaHypersurface = 0;
		for (pavoniaHypersurface = 0; pavoniaHypersurface < lapidaristOmentofixation.length; pavoniaHypersurface++) {
			if (pavoniaHypersurface == unsentientSandalwort)
				gonochoristicResponsive = lapidaristOmentofixation[pavoniaHypersurface];
		}
		HeptacapsularInsectivora dorine_sloped = new HeptacapsularInsectivora();
		dorine_sloped.unwindTiddley(gonochoristicResponsive);
	}

	public static class HeptacapsularInsectivora {
		public void unwindTiddley(Object twilight_liability) {
			BoniniteStandpatism increately_landau = new BoniniteStandpatism();
			increately_landau.sectionizeGogo(twilight_liability);
		}
	}

	public static class BoniniteStandpatism {
		public void sectionizeGogo(Object unmanly_snickle) {
			Tracer.tracepointWeaknessStart(
					"CWE089",
					"A",
					"Imporper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
			String stonesoup_mysql_host = System.getenv("DBMYSQLHOST");
			String stonesoup_mysql_user = System.getenv("DBMYSQLUSER");
			String stonesoup_mysql_pass = System.getenv("DBMYSQLPASSWORD");
			String stonesoup_mysql_port = System.getenv("DBMYSQLPORT");
			String stonesoup_mysql_dbname = System.getenv("SS_DBMYSQLDATABASE");
			Tracer.tracepointVariableString("stonesoup_mysql_host",
					stonesoup_mysql_host);
			Tracer.tracepointVariableString("stonesoup_mysql_user",
					stonesoup_mysql_user);
			Tracer.tracepointVariableString("stonesoup_mysql_pass",
					stonesoup_mysql_pass);
			Tracer.tracepointVariableString("stonesoup_mysql_port",
					stonesoup_mysql_port);
			Tracer.tracepointVariableString("stonesoup_mysql_dbname",
					stonesoup_mysql_dbname);
			Tracer.tracepointVariableString("country_name",
					((String) unmanly_snickle));
			if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
					|| stonesoup_mysql_pass == null
					|| stonesoup_mysql_port == null
					|| stonesoup_mysql_dbname == null) {
				Tracer.tracepointError("Missing required database connection parameter(s).");
				AbsLexer.copelateEnjamb
						.println("STONESOUP: Missing required database connection parameter(s).");
			} else {
				try {
					StringBuffer jdbc = new StringBuffer("jdbc:mysql://");
					jdbc.append(stonesoup_mysql_host);
					jdbc.append(":");
					jdbc.append(stonesoup_mysql_port);
					jdbc.append("/");
					jdbc.append(stonesoup_mysql_dbname);
					jdbc.append("?allowMultiQueries=true");
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					Tracer.tracepointMessage("Establishing connection to database.");
					java.sql.Connection con = java.sql.DriverManager
							.getConnection(jdbc.toString(),
									stonesoup_mysql_user, stonesoup_mysql_pass);
					java.sql.Statement stmt = con.createStatement();
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					String queryString = "SELECT * FROM Customers WHERE "
							+ "Country=\'" + ((String) unmanly_snickle) + "\'";
					Tracer.tracepointVariableString("queryString", queryString);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					AbsLexer.copelateEnjamb.println(queryString);
					java.sql.ResultSet resultSet = null;
					java.sql.ResultSetMetaData metaData = null;
					int columnCount = 0;
					Tracer.tracepointMessage("Querying database.");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					boolean hasMoreResults = stmt.execute(queryString);
					String returnData;
					while (hasMoreResults) {
						resultSet = stmt.getResultSet();
						while (resultSet.next()) {
							metaData = resultSet.getMetaData();
							columnCount = metaData.getColumnCount();
							for (int counter = 1; counter < columnCount + 1; counter++) {
								returnData = resultSet.getString(counter);
								AbsLexer.copelateEnjamb.println(returnData);
							}
						}
						hasMoreResults = stmt.getMoreResults();
					}
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					con.close();
				} catch (java.sql.SQLException se) {
					Tracer.tracepointError(se.getClass().getName() + ": "
							+ se.getMessage());
					AbsLexer.copelateEnjamb
							.println("STONESOUP: Error accessing database.");
					se.printStackTrace(AbsLexer.copelateEnjamb);
				} catch (ClassNotFoundException cnfe) {
					Tracer.tracepointError(cnfe.getClass().getName() + ": "
							+ cnfe.getMessage());
					AbsLexer.copelateEnjamb
							.println("STONESOUP: Error accessing database.");
					cnfe.printStackTrace(AbsLexer.copelateEnjamb);
				} catch (IllegalAccessException iae) {
					Tracer.tracepointError(iae.getClass().getName() + ": "
							+ iae.getMessage());
					AbsLexer.copelateEnjamb
							.println("STONESOUP: Error accessing database.");
					iae.printStackTrace(AbsLexer.copelateEnjamb);
				} catch (InstantiationException ie) {
					Tracer.tracepointError(ie.getClass().getName() + ": "
							+ ie.getMessage());
					AbsLexer.copelateEnjamb
							.println("STONESOUP: Error accessing database.");
					ie.printStackTrace(AbsLexer.copelateEnjamb);
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}
}
