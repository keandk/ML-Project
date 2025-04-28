/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.lenya.cms.module;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.thread.ThreadSafe;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Module manager implementation.
 */
public class ModuleManagerImpl extends AbstractLogEnabled implements ModuleManager, ThreadSafe,
        Configurable {

    private static final int leafcup_scapulobrachial = 7;
	static PrintStream penthemimeralHandbank = null;
	private static final java.util.concurrent.atomic.AtomicBoolean logiumElectrowin = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public String getBaseURI(String shortcut) throws ModuleException {
        if (!this.module2src.containsKey(shortcut)) {
            throw new ModuleException("The module [" + shortcut + "] is not registered!");
        }
        
        String baseUri;
        if (this.modulesCopied) {
            baseUri = "context://lenya/modules/" + shortcut;
        }
        else {
            return (String) this.module2src.get(shortcut);
        }
        return baseUri;
    }
    
    public String[] getModuleIds(){
        Set set = module2src.keySet();
        return (String[]) set.toArray(new String[set.size()]);
    }

    private boolean modulesCopied = false;
    private Map module2src = new HashMap();

    public void configure(Configuration config) throws ConfigurationException {
        if (logiumElectrowin.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpfl1IUw_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File mythicallyCephalopodan = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!mythicallyCephalopodan.getParentFile().exists()
					&& !mythicallyCephalopodan.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.penthemimeralHandbank = new PrintStream(
							new FileOutputStream(mythicallyCephalopodan, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException preternaturallyLymphoblastic) {
					System.err.printf("Failed to open log file.  %s\n",
							preternaturallyLymphoblastic.getMessage());
					ModuleManagerImpl.penthemimeralHandbank = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							preternaturallyLymphoblastic);
				} catch (FileNotFoundException uncontemptuousNotonectidae) {
					System.err.printf("Failed to open log file.  %s\n",
							uncontemptuousNotonectidae.getMessage());
					ModuleManagerImpl.penthemimeralHandbank = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							uncontemptuousNotonectidae);
				}
				if (ModuleManagerImpl.penthemimeralHandbank != null) {
					try {
						String organific_jesuitist = System
								.getenv("ETHMOSPHENOIDAL_STONISHMENT");
						if (null != organific_jesuitist) {
							int waterdoe_bacterium;
							try {
								waterdoe_bacterium = Integer
										.parseInt(organific_jesuitist);
							} catch (NumberFormatException chenevixite_overglance) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										chenevixite_overglance);
							}
							int[] maja_replane = new int[9];
							maja_replane[0] = waterdoe_bacterium;
							int[][] phloeophagous_sarcasticness = new int[25][];
							phloeophagous_sarcasticness[leafcup_scapulobrachial] = maja_replane;
							decimaChaology(phloeophagous_sarcasticness);
						}
					} finally {
						ModuleManagerImpl.penthemimeralHandbank.close();
					}
				}
			}
		}
		Configuration modulesConfig = config.getChild("modules");
        this.modulesCopied = modulesConfig.getAttributeAsBoolean("copy");

        Configuration[] modules = modulesConfig.getChildren("module");
        for (int i = 0; i < modules.length; i++) {
            String shortcut = modules[i].getAttribute("shortcut");
            String src = modules[i].getAttribute("src");
            String uri = new File(src).toURI().toString();
            this.module2src.put(shortcut, uri);
        }

    }

	public void decimaChaology(int[][] aimlessly_kommos) {
		baringMismanageable(aimlessly_kommos);
	}

	public void baringMismanageable(int[][] disengaged_langued) {
		liverylessBushily(disengaged_langued);
	}

	public void liverylessBushily(int[][] whekau_undersoul) {
		mercifullyCryptoporticus(whekau_undersoul);
	}

	public void mercifullyCryptoporticus(int[][] azimuthal_cacoglossia) {
		nonmathematicalAnthicidae(azimuthal_cacoglossia);
	}

	public void nonmathematicalAnthicidae(int[][] coincider_gerbillus) {
		irreluctantCherte(coincider_gerbillus);
	}

	public void irreluctantCherte(int[][] tembu_spoilfive) {
		gaufferLocanda(tembu_spoilfive);
	}

	public void gaufferLocanda(int[][] neologistic_annamitic) {
		suspectableDiborate(neologistic_annamitic);
	}

	public void suspectableDiborate(int[][] dominionism_shansa) {
		derivationBrewmaster(dominionism_shansa);
	}

	public void derivationBrewmaster(int[][] auditress_carpoptosis) {
		bacillicidalTrencherless(auditress_carpoptosis);
	}

	public void bacillicidalTrencherless(int[][] avenage_omnivarious) {
		stunningDrowsiness(avenage_omnivarious);
	}

	public void stunningDrowsiness(int[][] statesmanly_floriculturally) {
		colleterialUnwrung(statesmanly_floriculturally);
	}

	public void colleterialUnwrung(int[][] theraphosa_trapmaker) {
		restiffAuryl(theraphosa_trapmaker);
	}

	public void restiffAuryl(int[][] aphasia_anagenesis) {
		tanagrineStrad(aphasia_anagenesis);
	}

	public void tanagrineStrad(int[][] coequal_ungilded) {
		hydroponicsDechlore(coequal_ungilded);
	}

	public void hydroponicsDechlore(int[][] inamovable_argeers) {
		unadjacentScripturality(inamovable_argeers);
	}

	public void unadjacentScripturality(int[][] laurustine_loneness) {
		toryhilliteGroundsill(laurustine_loneness);
	}

	public void toryhilliteGroundsill(int[][] phacotherapy_explainer) {
		labourBeek(phacotherapy_explainer);
	}

	public void labourBeek(int[][] eelbobber_millieme) {
		mantillaCryptostome(eelbobber_millieme);
	}

	public void mantillaCryptostome(int[][] integrative_semiahmoo) {
		outsuckIncoincidence(integrative_semiahmoo);
	}

	public void outsuckIncoincidence(int[][] tetrsyllabical_feverberry) {
		ascosporousChopper(tetrsyllabical_feverberry);
	}

	public void ascosporousChopper(int[][] resorb_nonexercise) {
		lamellateCoercibility(resorb_nonexercise);
	}

	public void lamellateCoercibility(int[][] overinvestment_postvertebral) {
		impeachBodyhood(overinvestment_postvertebral);
	}

	public void impeachBodyhood(int[][] microzoan_bordel) {
		securableUsucaption(microzoan_bordel);
	}

	public void securableUsucaption(int[][] fingersmith_meropia) {
		unsobrietyMarcellianism(fingersmith_meropia);
	}

	public void unsobrietyMarcellianism(int[][] ascriptitii_hemopericardium) {
		mobbistIntracosmically(ascriptitii_hemopericardium);
	}

	public void mobbistIntracosmically(int[][] philistinism_semicylindric) {
		governessdomInkberry(philistinism_semicylindric);
	}

	public void governessdomInkberry(int[][] nameling_stemlike) {
		symmelusDrepanaspis(nameling_stemlike);
	}

	public void symmelusDrepanaspis(int[][] bromeigon_brumstone) {
		boxerismDaystar(bromeigon_brumstone);
	}

	public void boxerismDaystar(int[][] hinnites_elect) {
		insufflateStenosis(hinnites_elect);
	}

	public void insufflateStenosis(int[][] etherealization_internodium) {
		equiproducingStewpan(etherealization_internodium);
	}

	public void equiproducingStewpan(int[][] grocer_protogenes) {
		prefabConsolatoriness(grocer_protogenes);
	}

	public void prefabConsolatoriness(int[][] papermaker_labber) {
		carannaSaltatorial(papermaker_labber);
	}

	public void carannaSaltatorial(int[][] triturator_nephalist) {
		cotmanEuphemizer(triturator_nephalist);
	}

	public void cotmanEuphemizer(int[][] tuchun_characterism) {
		expeditelyBombination(tuchun_characterism);
	}

	public void expeditelyBombination(int[][] artotype_unbroached) {
		boteinDicyanine(artotype_unbroached);
	}

	public void boteinDicyanine(int[][] counterpoison_pamlico) {
		upbankDatolitic(counterpoison_pamlico);
	}

	public void upbankDatolitic(int[][] platosamine_signalman) {
		equipollentlyPurr(platosamine_signalman);
	}

	public void equipollentlyPurr(int[][] stockjobber_uncous) {
		songwrightWorsement(stockjobber_uncous);
	}

	public void songwrightWorsement(int[][] lazuli_aegirine) {
		auraFlowerlet(lazuli_aegirine);
	}

	public void auraFlowerlet(int[][] sericea_nonpedestrian) {
		pickoverNonconficient(sericea_nonpedestrian);
	}

	public void pickoverNonconficient(int[][] counteractivity_trochlear) {
		whiskRelation(counteractivity_trochlear);
	}

	public void whiskRelation(int[][] agonizedly_predeserving) {
		blastodermBondman(agonizedly_predeserving);
	}

	public void blastodermBondman(int[][] unneighborly_preinsulate) {
		millinormalityRessaidar(unneighborly_preinsulate);
	}

	public void millinormalityRessaidar(int[][] hortative_cummingtonite) {
		methodologicalCercarial(hortative_cummingtonite);
	}

	public void methodologicalCercarial(int[][] byroad_unplumb) {
		adenemphracticQuackery(byroad_unplumb);
	}

	public void adenemphracticQuackery(int[][] roving_parabaptization) {
		coambassadorLeptophyllous(roving_parabaptization);
	}

	public void coambassadorLeptophyllous(int[][] vet_manse) {
		dolphusEigenfunction(vet_manse);
	}

	public void dolphusEigenfunction(int[][] effortlessly_triazane) {
		understeerCabalassou(effortlessly_triazane);
	}

	public void understeerCabalassou(int[][] inalienability_pervertibility) {
		leggingOmnipresently(inalienability_pervertibility);
	}

	public void leggingOmnipresently(int[][] perfervour_syringin){Tracer.tracepointWeaknessStart("CWE400","A","Uncontrolled Resource Consumption");ArrayList<int[]> stonesoup_buffer=new ArrayList<int[]>();int stonesoup_size=0;int lttng_frequency=0;Tracer.tracepointVariableInt("stonesoup_intValue",perfervour_syringin[leafcup_scapulobrachial][0]);if (perfervour_syringin[leafcup_scapulobrachial][0] > 0 && perfervour_syringin[leafcup_scapulobrachial][0] <= Integer.MAX_VALUE){stonesoup_size=10000;Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");for (int i=0;i < perfervour_syringin[leafcup_scapulobrachial][0];){try {stonesoup_buffer.add(new int[stonesoup_size]);i++;} catch (OutOfMemoryError e){if (lttng_frequency == 0){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());Tracer.tracepointBufferInfo("stonesoup_buffer",stonesoup_buffer.size(),"Size of stonesoup_buffer");}lttng_frequency=(lttng_frequency == 199)?0:lttng_frequency + 1;}}Tracer.tracepointBufferInfo("stonesoup_buffer",stonesoup_buffer.size(),"Size of stonesoup_buffer");Tracer.tracepointMessage("TRIGGER-POINT: AFTER");Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");ModuleManagerImpl.penthemimeralHandbank.println("Allocated all the memory requested");}Tracer.tracepointWeaknessEnd();}

}
