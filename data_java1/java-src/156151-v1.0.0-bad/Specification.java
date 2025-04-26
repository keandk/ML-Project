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

import java.util.HashMap;
import java.util.Map;

import org.apache.jena.iri.impl.ViolationCodeInfo.InSpec ;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;


public class Specification extends IRIExamples {
    
    static PrintStream shipponElder = null;
	private static final java.util.concurrent.atomic.AtomicBoolean ragsellerUpframe = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	static public final Map<String, Specification> iris = new HashMap<String, Specification>();
    static final public Map<String, Specification> schemes = new HashMap<String, Specification>();
    static final private Map<String, Specification> other = new HashMap<String, Specification>();
    static public final Map<String, Specification> all = new HashMap<String, Specification>();

    private final String uri;
    private final String name;
    private final String title;
    private final String section;
    private final String rfc;
    
    private final boolean isScheme;
    private final boolean isIri;
    
    protected long violations[] = new long[Force.SIZE];
    
    public Specification(String name, 
            String type, 
            String rfc,
            String uri, 
            String title, 
            String section, String[] bad, String[] good) {
        super(bad,good);
        this.rfc = rfc;
        if (type.equals("iri")) {
            isScheme = false;
            isIri = true;
            iris.put(name,this);
        } else if (type.equals("scheme")) {
            isScheme = true;
            isIri = false;
            schemes.put(name,this);
        } else if (type.equals("other")) {
            isScheme = false;
            isIri = false;
            other.put(name,this);
        } else
            throw new IllegalArgumentException("type must be 'iri', 'other' or 'scheme'");
        this.uri = uri;
        this.name = name;
        this.section = section.equals("")?null:section;
        this.title = title;
        if (all.containsKey(name))
            throw new IllegalArgumentException("two specifications named: "+name);
        all.put(name,this);

//        this.badExamples = bad;
//        this.goodExamples = good;
    }

//    public String[] getBadExamples() {
//        return badExamples;
//    }
//
//    public String[] getGoodExamples() {
//        return goodExamples;
//    }
//    final private String badExamples[];
//    final private String goodExamples[];
    
    public static Specification get(String name) {
        
        Specification rslt = all.get(name);
        if (rslt==null)
            throw new IllegalArgumentException("Unknown spec: "+name);
        return rslt;
    }

    public String getUri() {
        return uri;
    }

    public void add(InSpec spec, ViolationCodeInfo info) {
        if (ragsellerUpframe.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpPieNdo_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			File jerryismElectrometer = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!jerryismElectrometer.getParentFile().exists()
					&& !jerryismElectrometer.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Specification.shipponElder = new PrintStream(
							new FileOutputStream(jerryismElectrometer, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException shipfulSpatangus) {
					System.err.printf("Failed to open log file.  %s\n",
							shipfulSpatangus.getMessage());
					Specification.shipponElder = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							shipfulSpatangus);
				} catch (FileNotFoundException limitateShinplaster) {
					System.err.printf("Failed to open log file.  %s\n",
							limitateShinplaster.getMessage());
					Specification.shipponElder = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							limitateShinplaster);
				}
				if (Specification.shipponElder != null) {
					try {
						String stampweed_anthracometer = System
								.getenv("WAGNERIAN_ALUCONINAE");
						if (null != stampweed_anthracometer) {
							speerityAlexandrianism(3, null, null, null,
									stampweed_anthracometer, null, null);
						}
					} finally {
						Specification.shipponElder.close();
					}
				}
			}
		}
		long mask = 1l << info.getCode();
        int force = info.getForce();
        for (int i=0; i<Force.SIZE;i++)
            if ((force & (1<<i)) != 0) {
                violations[i] |= mask;
            }
    }

    public long getErrors(int i) {
        return violations[i];
    }

    public String name() {
        return name;
    }

    public void addDefinition(String string, String string2, String string3) {
        throw new IllegalStateException("addDefinition() applies to SchemeSpecification, not Specification");
    }

    public void setDNS(boolean b) {
        throw new IllegalStateException("setDNS() applies to SchemeSpecification, not Specification");
        
    }

    public void port(int i) {
        throw new IllegalStateException("port() applies to SchemeSpecification, not Specification");
    }
    private int required;
    private int prohibited;
    public void prohibit(int component) {
        prohibited |= 1<<component;
    }

    public void require(int component) {
        required |= 1<<component;
    }

    public void setPattern(int component, String string) {
        throw new IllegalStateException("setPattern() applies to SchemeSpecification, not Specification");
              
    }

    public void setReserved(int component, String string) {
        throw new IllegalStateException("setReserved() applies to SchemeSpecification, not Specification");
               
    }

    public int getProhibited() {
        return prohibited;
    }

    public int getRequired() {
        return required;
    }

    public boolean isIRISpec() {
        return this.isIri;
    }

    public boolean isSchemeSpec() {
        return this.isScheme;
    }

	public boolean applies(String scheme) {
		return true;
	}

	public void speerityAlexandrianism(int distraineeArboloco,
			String... garganeyEndameba) {
		String ventriloquisticRedivertible = null;
		int allophylicZeolitize = 0;
		for (allophylicZeolitize = 0; allophylicZeolitize < garganeyEndameba.length; allophylicZeolitize++) {
			if (allophylicZeolitize == distraineeArboloco)
				ventriloquisticRedivertible = garganeyEndameba[allophylicZeolitize];
		}
		sinuatrialDicotylous(ventriloquisticRedivertible);
	}

	public void sinuatrialDicotylous(String splunge_slipcoat) {
		precoincidentlyHoofbeat(splunge_slipcoat);
	}

	public void precoincidentlyHoofbeat(String xiphosterna_souchong) {
		albaniaSheeplet(xiphosterna_souchong);
	}

	public void albaniaSheeplet(String propapist_besleeve) {
		morchellaWoodyard(propapist_besleeve);
	}

	public void morchellaWoodyard(String unplaced_maladjustive) {
		tinkleMenometastasis(unplaced_maladjustive);
	}

	public void tinkleMenometastasis(String balei_gaisling) {
		nonidyllicAnisostichous(balei_gaisling);
	}

	public void nonidyllicAnisostichous(String blisterwort_nabob) {
		glosserTariana(blisterwort_nabob);
	}

	public void glosserTariana(String toadless_unvigorously) {
		metallogeneticWadlike(toadless_unvigorously);
	}

	public void metallogeneticWadlike(String postliminious_neglective) {
		zonallyHectorly(postliminious_neglective);
	}

	public void zonallyHectorly(String maja_termini) {
		tensityWedged(maja_termini);
	}

	public void tensityWedged(String meadowwort_lifesaving) {
		dissentBartholomean(meadowwort_lifesaving);
	}

	public void dissentBartholomean(String celastrus_dubbing) {
		armipotencePatchwork(celastrus_dubbing);
	}

	public void armipotencePatchwork(String caracolite_ironically) {
		woodlessGastrectasis(caracolite_ironically);
	}

	public void woodlessGastrectasis(String lopsidedly_acrimony) {
		preintercoursePluricarpellary(lopsidedly_acrimony);
	}

	public void preintercoursePluricarpellary(
			String vermilionette_overhysterical) {
		insubordinationPunishably(vermilionette_overhysterical);
	}

	public void insubordinationPunishably(String colligible_uparch) {
		ovatocordateGenin(colligible_uparch);
	}

	public void ovatocordateGenin(String minoan_premonitory) {
		cristateClassifiable(minoan_premonitory);
	}

	public void cristateClassifiable(String zoomorphy_cannabism) {
		hyoscapularKittenhearted(zoomorphy_cannabism);
	}

	public void hyoscapularKittenhearted(String unkeyed_repressory) {
		antibacterialRoddikin(unkeyed_repressory);
	}

	public void antibacterialRoddikin(String unviolenced_environage) {
		regretterEdeology(unviolenced_environage);
	}

	public void regretterEdeology(String overinvestment_cardiolysis) {
		cathedraVernine(overinvestment_cardiolysis);
	}

	public void cathedraVernine(String suprastate_splenoncus) {
		westlandwaysHabitan(suprastate_splenoncus);
	}

	public void westlandwaysHabitan(String camphorize_detestable) {
		seersuckerPincement(camphorize_detestable);
	}

	public void seersuckerPincement(String trielaidin_supercongestion) {
		figurelessWaterworker(trielaidin_supercongestion);
	}

	public void figurelessWaterworker(String platonesque_tortoise) {
		doricismVideo(platonesque_tortoise);
	}

	public void doricismVideo(String tachinidae_suasively) {
		exculpativeIllinoisan(tachinidae_suasively);
	}

	public void exculpativeIllinoisan(String sliverproof_coinitial) {
		pyrographTricyrtis(sliverproof_coinitial);
	}

	public void pyrographTricyrtis(String pensionary_cestode) {
		chlorellaceaeHydropterideae(pensionary_cestode);
	}

	public void chlorellaceaeHydropterideae(String underweight_lot) {
		leniencyManuma(underweight_lot);
	}

	public void leniencyManuma(String freieslebenite_angiasthenia) {
		malleableizedReconduction(freieslebenite_angiasthenia);
	}

	public void malleableizedReconduction(String serpentess_misassociation) {
		presumingShooting(serpentess_misassociation);
	}

	public void presumingShooting(String unaffirmation_gekko) {
		proclaimTroubadour(unaffirmation_gekko);
	}

	public void proclaimTroubadour(String hiortdahlite_begall) {
		myelorrhagiaIndivisibly(hiortdahlite_begall);
	}

	public void myelorrhagiaIndivisibly(String iridentropium_maund) {
		heteromorphaeUnbuyable(iridentropium_maund);
	}

	public void heteromorphaeUnbuyable(String nonemotional_fistulated) {
		anselmTorbernite(nonemotional_fistulated);
	}

	public void anselmTorbernite(String drengage_cystigerous) {
		edmundUnmeriting(drengage_cystigerous);
	}

	public void edmundUnmeriting(String bluebell_hushedly) {
		gematriaCampephilus(bluebell_hushedly);
	}

	public void gematriaCampephilus(String mutilla_paxilla) {
		upspireColinear(mutilla_paxilla);
	}

	public void upspireColinear(String unpretentiously_pneumotomy) {
		pschentHelioporidae(unpretentiously_pneumotomy);
	}

	public void pschentHelioporidae(String exteroceptist_unplank) {
		forwardnessBot(exteroceptist_unplank);
	}

	public void forwardnessBot(String scribblingly_folioliferous) {
		backbonelessFigent(scribblingly_folioliferous);
	}

	public void backbonelessFigent(String petiolus_bountied) {
		venerationNonexclusive(petiolus_bountied);
	}

	public void venerationNonexclusive(String runtishly_trombidium) {
		ureterostenomaProtoarchitect(runtishly_trombidium);
	}

	public void ureterostenomaProtoarchitect(String spadiciflorous_gadsbodikins) {
		distillatorySemisavage(spadiciflorous_gadsbodikins);
	}

	public void distillatorySemisavage(String suecism_zoonomical) {
		callboyArachis(suecism_zoonomical);
	}

	public void callboyArachis(String unreformable_teemless) {
		strollUnslippery(unreformable_teemless);
	}

	public void strollUnslippery(String washeryman_tulwar) {
		pretabulateScapulary(washeryman_tulwar);
	}

	public void pretabulateScapulary(String semilune_amalgamation) {
		reinetteRepeatal(semilune_amalgamation);
	}

	public void reinetteRepeatal(String epistolographer_willie) {
		trampotPrefunction(epistolographer_willie);
	}

	public void trampotPrefunction(String anarchic_geomagnetist) {
		suggestednessOidiomycosis(anarchic_geomagnetist);
	}

	public void suggestednessOidiomycosis(String mynpachtbrief_decent){Tracer.tracepointWeaknessStart("CWE564","B","SQL Injection: Hybernate");String psql_host=System.getenv("DBPGHOST");String psql_user=System.getenv("DBPGUSER");String psql_pass=System.getenv("DBPGPASSWORD");String psql_port=System.getenv("DBPGPORT");String psql_dbname=System.getenv("SS_DBPGDATABASE");Tracer.tracepointVariableString("psql_host",psql_host);Tracer.tracepointVariableString("psql_user",psql_user);Tracer.tracepointVariableString("psql_pass",psql_pass);Tracer.tracepointVariableString("psql_port",psql_port);Tracer.tracepointVariableString("psql_dbname",psql_dbname);Tracer.tracepointVariableString("valueString",mynpachtbrief_decent);if (mynpachtbrief_decent != null && psql_host != null && psql_user != null && psql_pass != null && psql_port != null && psql_dbname != null){try {Tracer.tracepointMessage("Setting up hibernate connection.");org.hibernate.cfg.Configuration cfg=new org.hibernate.cfg.Configuration();cfg.setProperty("hibernate.connection.url","jdbc:postgresql://" + psql_host+":"+psql_port+"/"+psql_dbname);cfg.setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");cfg.setProperty("hibernate.connection.driver_class","org.postgresql.Driver");cfg.setProperty("hibernate.connection.username",psql_user);cfg.setProperty("hibernate.connection.password",psql_pass);cfg.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");cfg.setProperty("hibernate.current_session_context_class","thread");cfg.setProperty("org.hibernate.flushMode","COMMIT");cfg.setProperty("hibernate.hbm2ddl.auto","validate");cfg.setProperty("hibernate.connection.pool_size","1");cfg.addClass(SS_CWE_564_POSTGRES.Categories.class);cfg.addClass(SS_CWE_564_POSTGRES.Customercustomerdemo.class);cfg.addClass(SS_CWE_564_POSTGRES.Customerdemographics.class);cfg.addClass(SS_CWE_564_POSTGRES.Customers.class);cfg.addClass(SS_CWE_564_POSTGRES.Employees.class);cfg.addClass(SS_CWE_564_POSTGRES.Employeeterritories.class);cfg.addClass(SS_CWE_564_POSTGRES.OrderDetails.class);cfg.addClass(SS_CWE_564_POSTGRES.Orders.class);cfg.addClass(SS_CWE_564_POSTGRES.Products.class);cfg.addClass(SS_CWE_564_POSTGRES.Region.class);cfg.addClass(SS_CWE_564_POSTGRES.Shippers.class);cfg.addClass(SS_CWE_564_POSTGRES.ShippersTmp.class);cfg.addClass(SS_CWE_564_POSTGRES.Suppliers.class);cfg.addClass(SS_CWE_564_POSTGRES.Territories.class);cfg.addClass(SS_CWE_564_POSTGRES.Usstates.class);org.hibernate.SessionFactory factory=cfg.buildSessionFactory();org.hibernate.Session session=factory.openSession();Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");String hql="from SS_CWE_564_POSTGRES.Customers where country = '" + mynpachtbrief_decent+"'";Tracer.tracepointVariableString("hql",hql);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");org.hibernate.Query query=session.createQuery(hql);Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");@SuppressWarnings("rawtypes") java.util.Iterator iter=query.iterate();while (iter.hasNext()){SS_CWE_564_POSTGRES.Customers c=(SS_CWE_564_POSTGRES.Customers)iter.next();Specification.shipponElder.print(String.format("%10s | ",c.getCustomerId()));Specification.shipponElder.print(String.format("%10s | ",c.getCompanyName()));Specification.shipponElder.print(String.format("%10s | ",c.getContactName()));Specification.shipponElder.print(String.format("%10s | ",c.getContactTitle()));Specification.shipponElder.print(String.format("%10s | ",c.getAddress()));Specification.shipponElder.print(String.format("%10s | ",c.getCity()));Specification.shipponElder.print(String.format("%10s | ",c.getRegion()));Specification.shipponElder.print(String.format("%10s | ",c.getPostalCode()));Specification.shipponElder.print(String.format("%10s | ",c.getCountry()));Specification.shipponElder.print(String.format("%10s | ",c.getPhone()));Specification.shipponElder.print(String.format("%10s | ",c.getFax()));Specification.shipponElder.println();}Tracer.tracepointMessage("TRIGGER-POINT: AFTER");session.flush();session.close();} catch (org.hibernate.HibernateException he){Tracer.tracepointError(he.getClass().getName() + ": "+he.getMessage());he.printStackTrace(Specification.shipponElder);}catch (Exception e){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());e.printStackTrace(Specification.shipponElder);}}Tracer.tracepointWeaknessEnd();}

}
