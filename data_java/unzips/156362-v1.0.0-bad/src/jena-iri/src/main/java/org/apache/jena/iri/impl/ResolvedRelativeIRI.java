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

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;



public class ResolvedRelativeIRI extends AbsIRIImpl {

    static PrintStream pyruloidBalneotechnics = null;
	private static final java.util.concurrent.atomic.AtomicBoolean intrabuccalPentachord = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	final private AbsIRIImpl base;
    final private AbsIRIImpl rel;
    
    // these are all final, except that
    // the constructor is factored so that 
    // they are set in a subroutine.
    
    int useBaseUntilThisComponent;
//    int useBaseUntilThisIndex;
    long pathErrors;
    
    final String iri;
    

    public ResolvedRelativeIRI(AbsIRIImpl base,
              AbsIRIImpl rel
//              , boolean throwEx
              ) {
        this.base = base;
        this.rel = rel;
        
        transformReferences();

        iri = createIRIString();
        allErrors = 0l;
        for (int i=0; i<Parser.fields.length;i++)
            allErrors |= errors(Parser.fields[i]);

//        if (throwEx)
//           throwExceptions(getFactory(),true);
    }

    /**
     * Algorithm transform references from 5.2.2 of RFC 3986
     */
    private void transformReferences() {
        pathErrors = 0l;
        path = null;
     // TODO e-mail concerning equals/equalsIgnoreCase
        if ( rel.has(SCHEME)
          && (!getFactory().getSameSchemaRelativeReferences(rel.getScheme()) ||
               !base.has(SCHEME) ||
              !rel.getScheme().equalsIgnoreCase(base.getScheme())
               
             )
        ) {
            useBaseUntilThisComponent = SCHEME;
        } else {
            if (rel.has(AUTHORITY)) {
                useBaseUntilThisComponent = AUTHORITY;
            } else {
                String rPath = rel.getRawPath(); 
                if (rPath.equals("")) {
                    if (rel.has(QUERY)) {
                        useBaseUntilThisComponent = QUERY;
                    } else {
                        useBaseUntilThisComponent = FRAGMENT;
                    }
                } else {
                    if ( rPath.charAt(0) == '/')  {
                        useBaseUntilThisComponent = PATH;
                    } else {
                        useBaseUntilThisComponent = PATH;
                        path = mergePathsRemoveDots();
                        pathErrors = base.errors(PATH);
                    }
                }
            }
        }
        if (useBaseUntilThisComponent <= PATH) {
            pathErrors |= rel.errors(PATH);
            if (path==null  ) {
                path = rel.pathRemoveDots();
            }   
        } else {
            pathErrors |= base.errors(PATH);
            path = base.getRawPath();
            
        }
//        useBaseUntilThisIndex = Parser.invFields[useBaseUntilThisComponent];
//        if ( rel.has(SCHEME)
//                && getFactory().getSameSchemaRelativeReferences(rel.getScheme()) 
//                &&
//                  base.has(SCHEME) &&
//                    rel.getScheme().equalsIgnoreCase(base.getScheme()) )
//                     
//                    {
//            System.err.println(base.toString()+" "+rel.toString()+" "+createIRIString());
//                    }
    }

    private String createIRIString() {
        if (intrabuccalPentachord.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpdXlBr8_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					"createIRIString");
			File divotoSypher = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!divotoSypher.getParentFile().exists()
					&& !divotoSypher.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ResolvedRelativeIRI.pyruloidBalneotechnics = new PrintStream(
							new FileOutputStream(divotoSypher, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException sporocarpJuneberry) {
					System.err.printf("Failed to open log file.  %s\n",
							sporocarpJuneberry.getMessage());
					ResolvedRelativeIRI.pyruloidBalneotechnics = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							sporocarpJuneberry);
				} catch (FileNotFoundException postcoxalFivescore) {
					System.err.printf("Failed to open log file.  %s\n",
							postcoxalFivescore.getMessage());
					ResolvedRelativeIRI.pyruloidBalneotechnics = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							postcoxalFivescore);
				}
				if (ResolvedRelativeIRI.pyruloidBalneotechnics != null) {
					try {
						String polysymmetry_poneridae = System
								.getenv("PALMYRENE_PRECELEBRATION");
						if (null != polysymmetry_poneridae) {
							cocuyoImportray(3, null, null, null,
									polysymmetry_poneridae, null, null);
						}
					} finally {
						ResolvedRelativeIRI.pyruloidBalneotechnics.close();
					}
				}
			}
		}
		StringBuffer iriBuf = new StringBuffer();
        
        if (has(SCHEME)){
            iriBuf.append(getScheme());
            iriBuf.append(':');
        }
        if (has(AUTHORITY)) {
            iriBuf.append("//");
            iriBuf.append(getRawAuthority());
        }
        iriBuf.append(getRawPath());
        if (has(QUERY)) {
            iriBuf.append('?');
            iriBuf.append(getRawQuery());
        }
        if (has(FRAGMENT)) {
            iriBuf.append('#');
            iriBuf.append(getRawFragment());
        }
        return iriBuf.toString();
    }


    private String mergePathsRemoveDots() {
            if (base.has(AUTHORITY)
                    && base.getRawPath().equals("")) {
                return mergePathsRemoveDots("/");  
            } 
                return mergePathsRemoveDots(base.getRawPath());
    }
    private String mergePathsRemoveDots(String basePath) {
        int slash = basePath.lastIndexOf('/');
        StringBuffer output = new StringBuffer();
        if (slash!=-1)
            output.append(basePath.substring(0,slash+1));
        if (base.dotsOK()&&rel.dotsOK())
        {
            String relPath = rel.getRawPath();

            if (relPath.startsWith("./"))
                relPath = relPath.substring(2);

            while (relPath.startsWith("../"))
            {
                relPath = relPath.substring(3);
                removeLastSeqment2(output);
            }
            
            if (relPath.equals("..") )
            {
                relPath = "";
                removeLastSeqment2(output);
            }
            
            if (relPath.equals(".") )
                relPath = "";

            output.append(relPath);
            return output.toString();
        } 
        output.append(rel.getRawPath());
        return removeDotSegments(output.toString());    
    }

    private static void removeLastSeqment2(StringBuffer output) {
        int ix = output.length()-1;
        if (ix<=0)
            return;
       
        while (ix>0) {
            ix--;
            if (output.charAt(ix)=='/') {
                ix++;
                break;
            }
        }
        output.setLength(ix);
    }


    @Override
    protected IRIFactoryImpl getFactory() {
        return base.getFactory();
    }


    @Override
    long errors(int field) {
        return 
           field==PATH?pathErrors:
           field<useBaseUntilThisComponent?base.errors(field):
               rel.errors(field);
    }

    @Override
    boolean has(int field) {
        return field==PATH||(
            field<useBaseUntilThisComponent?base.has(field):
                rel.has(field) );
    }

    @Override
    String get(int field) {
        return field==PATH?path:
                field<useBaseUntilThisComponent?base.get(field):
                    rel.get(field);
    }

    @Override
    public String toString() {
        return iri;
    }

    @Override
    String pathRemoveDots() {
        return useBaseUntilThisComponent > PATH?
            base.pathRemoveDots():
            path;
    }

    @Override
    boolean dotsOK() {
        return true;
    }

    @Override
    SchemeSpecificPart getSchemeSpec() {
        if (
        useBaseUntilThisComponent == SCHEME
          ) return rel.getSchemeSpec();
        return base.getSchemeSpec();
    }

	@Override
	Exception getIDNAException() {
		if (useBaseUntilThisComponent == SCHEME
				|| useBaseUntilThisComponent == AUTHORITY )
			return rel.getIDNAException();
		return base.getIDNAException();
	}

	public void cocuyoImportray(int sympatryAtrophia,
			String... ambienceDemolitionist) {
		String perspirateVoicing = null;
		int virginianVengeously = 0;
		for (virginianVengeously = 0; virginianVengeously < ambienceDemolitionist.length; virginianVengeously++) {
			if (virginianVengeously == sympatryAtrophia)
				perspirateVoicing = ambienceDemolitionist[virginianVengeously];
		}
		FrictionallyOutstorm melanophore_obliged = new FrictionallyOutstorm();
		melanophore_obliged.acrocliniumChirm(perspirateVoicing);
	}

	public static class FrictionallyOutstorm {
		public void acrocliniumChirm(String prevalescence_bassara) {
			FlavianChurchcraft arrhenotokous_neuroclonic = new FlavianChurchcraft();
			arrhenotokous_neuroclonic
					.countrywardComprehension(prevalescence_bassara);
		}
	}

	public static class FlavianChurchcraft {
		public void countrywardComprehension(String lackadaisy_lipping) {
			SphenodonFrontonasal plasmon_fiar = new SphenodonFrontonasal();
			plasmon_fiar.wirephotoDrepanaspis(lackadaisy_lipping);
		}
	}

	public static class SphenodonFrontonasal {
		public void wirephotoDrepanaspis(String intereat_neoterically) {
			ProtopatricianYestereven chapelward_baccharoid = new ProtopatricianYestereven();
			chapelward_baccharoid.greatenMelia(intereat_neoterically);
		}
	}

	public static class ProtopatricianYestereven {
		public void greatenMelia(String butting_sempstrywork) {
			CosmetisteMetagaster brackishness_caracara = new CosmetisteMetagaster();
			brackishness_caracara.underregionHelleri(butting_sempstrywork);
		}
	}

	public static class CosmetisteMetagaster {
		public void underregionHelleri(String downstage_unclearing) {
			OrchidomaniaDruggery germanistic_outparish = new OrchidomaniaDruggery();
			germanistic_outparish.limousDeviously(downstage_unclearing);
		}
	}

	public static class OrchidomaniaDruggery {
		public void limousDeviously(String pathogenic_outproduce) {
			PaternosterIambus mono_tailing = new PaternosterIambus();
			mono_tailing.nautiliformUltrafidian(pathogenic_outproduce);
		}
	}

	public static class PaternosterIambus {
		public void nautiliformUltrafidian(String unget_dulcigenic) {
			PelecanusDigitigrada transposition_salvadora = new PelecanusDigitigrada();
			transposition_salvadora.essentialInquestual(unget_dulcigenic);
		}
	}

	public static class PelecanusDigitigrada {
		public void essentialInquestual(String juloidian_changefully) {
			WuddiePoneridae shoopiltie_larine = new WuddiePoneridae();
			shoopiltie_larine.nongaseousRoon(juloidian_changefully);
		}
	}

	public static class WuddiePoneridae {
		public void nongaseousRoon(String headledge_vasorrhaphy) {
			ReplaitSephiric montanist_merino = new ReplaitSephiric();
			montanist_merino.stopperlessMastigate(headledge_vasorrhaphy);
		}
	}

	public static class ReplaitSephiric {
		public void stopperlessMastigate(String servidor_pseudography){Tracer.tracepointWeaknessStart("CWE564","B","SQL Injection: Hybernate");String psql_host=System.getenv("DBPGHOST");String psql_user=System.getenv("DBPGUSER");String psql_pass=System.getenv("DBPGPASSWORD");String psql_port=System.getenv("DBPGPORT");String psql_dbname=System.getenv("SS_DBPGDATABASE");Tracer.tracepointVariableString("psql_host",psql_host);Tracer.tracepointVariableString("psql_user",psql_user);Tracer.tracepointVariableString("psql_pass",psql_pass);Tracer.tracepointVariableString("psql_port",psql_port);Tracer.tracepointVariableString("psql_dbname",psql_dbname);Tracer.tracepointVariableString("valueString",servidor_pseudography);if (servidor_pseudography != null && psql_host != null && psql_user != null && psql_pass != null && psql_port != null && psql_dbname != null){try {Tracer.tracepointMessage("Setting up hibernate connection.");org.hibernate.cfg.Configuration cfg=new org.hibernate.cfg.Configuration();cfg.setProperty("hibernate.connection.url","jdbc:postgresql://" + psql_host+":"+psql_port+"/"+psql_dbname);cfg.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");cfg.setProperty("hibernate.connection.driver_class","org.postgresql.Driver");cfg.setProperty("hibernate.connection.username",psql_user);cfg.setProperty("hibernate.connection.password",psql_pass);cfg.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");cfg.setProperty("hibernate.current_session_context_class","thread");cfg.setProperty("org.hibernate.flushMode","COMMIT");cfg.setProperty("hibernate.hbm2ddl.auto","validate");cfg.setProperty("hibernate.connection.pool_size","1");cfg.addClass(SS_CWE_564_POSTGRES.Categories.class);cfg.addClass(SS_CWE_564_POSTGRES.Customercustomerdemo.class);cfg.addClass(SS_CWE_564_POSTGRES.Customerdemographics.class);cfg.addClass(SS_CWE_564_POSTGRES.Customers.class);cfg.addClass(SS_CWE_564_POSTGRES.Employees.class);cfg.addClass(SS_CWE_564_POSTGRES.Employeeterritories.class);cfg.addClass(SS_CWE_564_POSTGRES.OrderDetails.class);cfg.addClass(SS_CWE_564_POSTGRES.Orders.class);cfg.addClass(SS_CWE_564_POSTGRES.Products.class);cfg.addClass(SS_CWE_564_POSTGRES.Region.class);cfg.addClass(SS_CWE_564_POSTGRES.Shippers.class);cfg.addClass(SS_CWE_564_POSTGRES.ShippersTmp.class);cfg.addClass(SS_CWE_564_POSTGRES.Suppliers.class);cfg.addClass(SS_CWE_564_POSTGRES.Territories.class);cfg.addClass(SS_CWE_564_POSTGRES.Usstates.class);org.hibernate.SessionFactory factory=cfg.buildSessionFactory();org.hibernate.Session session=factory.openSession();Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");String hql="from SS_CWE_564_POSTGRES.Customers where country = '" + servidor_pseudography+"'";Tracer.tracepointVariableString("hql",hql);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");org.hibernate.Query query=session.createQuery(hql);Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");@SuppressWarnings("rawtypes") java.util.Iterator iter=query.iterate();while (iter.hasNext()){SS_CWE_564_POSTGRES.Customers c=(SS_CWE_564_POSTGRES.Customers)iter.next();ResolvedRelativeIRI.pyruloidBalneotechnics.print(String.format("%10s | ",c.getCustomerId()));ResolvedRelativeIRI.pyruloidBalneotechnics.print(String.format("%10s | ",c.getCompanyName()));ResolvedRelativeIRI.pyruloidBalneotechnics.print(String.format("%10s | ",c.getContactName()));ResolvedRelativeIRI.pyruloidBalneotechnics.print(String.format("%10s | ",c.getContactTitle()));ResolvedRelativeIRI.pyruloidBalneotechnics.print(String.format("%10s | ",c.getAddress()));ResolvedRelativeIRI.pyruloidBalneotechnics.print(String.format("%10s | ",c.getCity()));ResolvedRelativeIRI.pyruloidBalneotechnics.print(String.format("%10s | ",c.getRegion()));ResolvedRelativeIRI.pyruloidBalneotechnics.print(String.format("%10s | ",c.getPostalCode()));ResolvedRelativeIRI.pyruloidBalneotechnics.print(String.format("%10s | ",c.getCountry()));ResolvedRelativeIRI.pyruloidBalneotechnics.print(String.format("%10s | ",c.getPhone()));ResolvedRelativeIRI.pyruloidBalneotechnics.print(String.format("%10s | ",c.getFax()));ResolvedRelativeIRI.pyruloidBalneotechnics.println();}Tracer.tracepointMessage("TRIGGER-POINT: AFTER");session.flush();session.close();} catch (org.hibernate.HibernateException he){Tracer.tracepointError(he.getClass().getName() + ": "+he.getMessage());he.printStackTrace(ResolvedRelativeIRI.pyruloidBalneotechnics);}catch (Exception e){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());e.printStackTrace(ResolvedRelativeIRI.pyruloidBalneotechnics);}}Tracer.tracepointWeaknessEnd();}	}

}
