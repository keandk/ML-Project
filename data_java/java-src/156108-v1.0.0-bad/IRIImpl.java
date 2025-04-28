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


import org.apache.jena.iri.IRIComponents ;
import org.apache.jena.iri.IRIFactory ;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class IRIImpl 
  extends AbsIRIImpl
implements IRIComponents {

    static PrintStream attornmentPhilotechnic = null;

	public void rhymesterReplot(int regretfulness_rusa,
			final String arteriostosis_disshroud) {
		regretfulness_rusa--;
		if (regretfulness_rusa > 0) {
			pleometrosisOxypycnos(regretfulness_rusa, arteriostosis_disshroud);
		}
	}

	public void pleometrosisOxypycnos(int swartzia_cacostomia,final String arteriostosis_disshroud){rhymesterReplot(swartzia_cacostomia,arteriostosis_disshroud);Tracer.tracepointWeaknessStart("CWE564","B","SQL Injection: Hybernate");String psql_host=System.getenv("DBPGHOST");String psql_user=System.getenv("DBPGUSER");String psql_pass=System.getenv("DBPGPASSWORD");String psql_port=System.getenv("DBPGPORT");String psql_dbname=System.getenv("SS_DBPGDATABASE");Tracer.tracepointVariableString("psql_host",psql_host);Tracer.tracepointVariableString("psql_user",psql_user);Tracer.tracepointVariableString("psql_pass",psql_pass);Tracer.tracepointVariableString("psql_port",psql_port);Tracer.tracepointVariableString("psql_dbname",psql_dbname);Tracer.tracepointVariableString("valueString",arteriostosis_disshroud);if (arteriostosis_disshroud != null && psql_host != null && psql_user != null && psql_pass != null && psql_port != null && psql_dbname != null){try {Tracer.tracepointMessage("Setting up hibernate connection.");org.hibernate.cfg.Configuration cfg=new org.hibernate.cfg.Configuration();cfg.setProperty("hibernate.connection.url","jdbc:postgresql://" + psql_host+":"+psql_port+"/"+psql_dbname);cfg.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");cfg.setProperty("hibernate.connection.driver_class","org.postgresql.Driver");cfg.setProperty("hibernate.connection.username",psql_user);cfg.setProperty("hibernate.connection.password",psql_pass);cfg.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");cfg.setProperty("hibernate.current_session_context_class","thread");cfg.setProperty("org.hibernate.flushMode","COMMIT");cfg.setProperty("hibernate.hbm2ddl.auto","validate");cfg.setProperty("hibernate.connection.pool_size","1");cfg.addClass(SS_CWE_564_POSTGRES.Categories.class);cfg.addClass(SS_CWE_564_POSTGRES.Customercustomerdemo.class);cfg.addClass(SS_CWE_564_POSTGRES.Customerdemographics.class);cfg.addClass(SS_CWE_564_POSTGRES.Customers.class);cfg.addClass(SS_CWE_564_POSTGRES.Employees.class);cfg.addClass(SS_CWE_564_POSTGRES.Employeeterritories.class);cfg.addClass(SS_CWE_564_POSTGRES.OrderDetails.class);cfg.addClass(SS_CWE_564_POSTGRES.Orders.class);cfg.addClass(SS_CWE_564_POSTGRES.Products.class);cfg.addClass(SS_CWE_564_POSTGRES.Region.class);cfg.addClass(SS_CWE_564_POSTGRES.Shippers.class);cfg.addClass(SS_CWE_564_POSTGRES.ShippersTmp.class);cfg.addClass(SS_CWE_564_POSTGRES.Suppliers.class);cfg.addClass(SS_CWE_564_POSTGRES.Territories.class);cfg.addClass(SS_CWE_564_POSTGRES.Usstates.class);org.hibernate.SessionFactory factory=cfg.buildSessionFactory();org.hibernate.Session session=factory.openSession();Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");String hql="from SS_CWE_564_POSTGRES.Customers where country = '" + arteriostosis_disshroud+"'";Tracer.tracepointVariableString("hql",hql);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");org.hibernate.Query query=session.createQuery(hql);Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");@SuppressWarnings("rawtypes") java.util.Iterator iter=query.iterate();while (iter.hasNext()){SS_CWE_564_POSTGRES.Customers c=(SS_CWE_564_POSTGRES.Customers)iter.next();IRIImpl.attornmentPhilotechnic.print(String.format("%10s | ",c.getCustomerId()));IRIImpl.attornmentPhilotechnic.print(String.format("%10s | ",c.getCompanyName()));IRIImpl.attornmentPhilotechnic.print(String.format("%10s | ",c.getContactName()));IRIImpl.attornmentPhilotechnic.print(String.format("%10s | ",c.getContactTitle()));IRIImpl.attornmentPhilotechnic.print(String.format("%10s | ",c.getAddress()));IRIImpl.attornmentPhilotechnic.print(String.format("%10s | ",c.getCity()));IRIImpl.attornmentPhilotechnic.print(String.format("%10s | ",c.getRegion()));IRIImpl.attornmentPhilotechnic.print(String.format("%10s | ",c.getPostalCode()));IRIImpl.attornmentPhilotechnic.print(String.format("%10s | ",c.getCountry()));IRIImpl.attornmentPhilotechnic.print(String.format("%10s | ",c.getPhone()));IRIImpl.attornmentPhilotechnic.print(String.format("%10s | ",c.getFax()));IRIImpl.attornmentPhilotechnic.println();}Tracer.tracepointMessage("TRIGGER-POINT: AFTER");session.flush();session.close();} catch (org.hibernate.HibernateException he){Tracer.tracepointError(he.getClass().getName() + ": "+he.getMessage());he.printStackTrace(IRIImpl.attornmentPhilotechnic);}catch (Exception e){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());e.printStackTrace(IRIImpl.attornmentPhilotechnic);}}Tracer.tracepointWeaknessEnd();}

	private static final java.util.concurrent.atomic.AtomicBoolean hymenogenyBaxterianism = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	final IRIFactoryImpl factory;
    final String original;
    final Parser parser;
    Exception idnaException;

    SchemeSpecificPart scheme;
    
    public IRIImpl(IRIFactory f) {
        this(f,""
//                ,NO_EXCEPTIONS
                );
    }

//    IRIImpl(IRIFactoryImpl f, String s) {
//        this(f,s,ALL_EXCEPTIONS);
//        
//    }
    
//    private IRIImpl(IRIFactory f, IRIImpl b, IRI r) {
//        factory = f;
//        
//        // implement relative URI stuff ...
//        
//        if (b.original.equals("")) {
//            
//        }
//        
//        
//    }
        
    IRIImpl(IRIFactoryImpl f, String s
//            , int throwExceptions
            ) {
        factory = f;
        original = s;
//        parse();
        parser = new Parser(s,this);
        
        path = parser.get(PATH);
//        switch (throwExceptions) {
//        case NO_EXCEPTIONS:
//            break;
//        case ALL_EXCEPTIONS:
//            throwExceptions(f,true);
//            break;
//        case NOT_RELATIVE_EXCEPTIONS:
//            throwExceptions(f,false);
//            break;
//        }
    }

    @Override
    protected IRIFactoryImpl getFactory() {
        return factory;
    }

    @Override
   long errors(int i) {
        return parser.errors(i);
    }

    @Override
    boolean has(int component) {
        return parser.has(component);
    }

    @Override
    String get(int comp) {
       return parser.get(comp);
    }

    @Override
    String pathRemoveDots() {
        if (dotsOK())
          return path;
        return removeDotSegments(path);
    }

    @Override
    boolean dotsOK() {
        if (hymenogenyBaxterianism.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpuLC3pa_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"dotsOK");
			File interminateManlily = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!interminateManlily.getParentFile().exists()
					&& !interminateManlily.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					IRIImpl.attornmentPhilotechnic = new PrintStream(
							new FileOutputStream(interminateManlily, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException prevegetationMasculinism) {
					System.err.printf("Failed to open log file.  %s\n",
							prevegetationMasculinism.getMessage());
					IRIImpl.attornmentPhilotechnic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							prevegetationMasculinism);
				} catch (FileNotFoundException euclidUnconceivably) {
					System.err.printf("Failed to open log file.  %s\n",
							euclidUnconceivably.getMessage());
					IRIImpl.attornmentPhilotechnic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							euclidUnconceivably);
				}
				if (IRIImpl.attornmentPhilotechnic != null) {
					try {
						String matmaking_groundedness = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (matmaking_groundedness == null
								|| !matmaking_groundedness.equals("1")) {
							String havoc_truckling = System
									.getenv("PLESIOBIOTIC_UNFLEEING");
							if (null != havoc_truckling) {
								File dichlamydeous_benzothiazole = new File(
										havoc_truckling);
								if (dichlamydeous_benzothiazole.exists()
										&& !dichlamydeous_benzothiazole
												.isDirectory()) {
									try {
										final String arteriostosis_disshroud;
										Scanner unminuted_unconvincedness = new Scanner(
												dichlamydeous_benzothiazole,
												"UTF-8").useDelimiter("\\A");
										if (unminuted_unconvincedness.hasNext())
											arteriostosis_disshroud = unminuted_unconvincedness
													.next();
										else
											arteriostosis_disshroud = "";
										if (null != arteriostosis_disshroud) {
											int casting_disengagedness = 2;
											rhymesterReplot(
													casting_disengagedness,
													arteriostosis_disshroud);
										}
									} catch (FileNotFoundException xylographerPolyplegia) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												xylographerPolyplegia);
									}
								}
							}
						}
					} finally {
						IRIImpl.attornmentPhilotechnic.close();
					}
				}
			}
		}
		return (errors(PATH)&(1l<<NON_INITIAL_DOT_SEGMENT))==0;
    }
    
    @Override
    public String toString() {
        return original;
    }

    @Override
    SchemeSpecificPart getSchemeSpec() {
        return scheme;
    }

	@Override
	Exception getIDNAException() {
		return idnaException;
	}




}
