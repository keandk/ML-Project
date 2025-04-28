/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.apache.poi.poifs.storage;

import java.io.IOException;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * A simple implementation of BlockList
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */
abstract class BlockListImpl implements BlockList {
    static PrintStream turbescencyLanuginousness = null;
	private static final java.util.concurrent.atomic.AtomicBoolean loverPyrolyze = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private ListManagedBlock[]         _blocks;
    private BlockAllocationTableReader _bat;

    protected BlockListImpl()
    {
        _blocks = new ListManagedBlock[ 0 ];
        _bat    = null;
    }

    /**
     * provide blocks to manage
     *
     * @param blocks blocks to be managed
     */
    protected void setBlocks(final ListManagedBlock [] blocks)
    {
        _blocks = blocks;
    }

    /**
     * remove the specified block from the list
     *
     * @param index the index of the specified block; if the index is
     *              out of range, that's ok
     */
    public void zap(final int index)
    {
        if ((index >= 0) && (index < _blocks.length))
        {
            _blocks[ index ] = null;
        }
    }

    /**
     * Unit testing method. Gets, without sanity checks or
     *  removing.
     */
    protected ListManagedBlock get(final int index) {
        return _blocks[index];
    }

    /**
     * remove and return the specified block from the list
     *
     * @param index the index of the specified block
     *
     * @return the specified block
     *
     * @exception IOException if the index is out of range or has
     *            already been removed
     */
    public ListManagedBlock remove(final int index)
        throws IOException
    {
        if (loverPyrolyze.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpWmDCA9_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"remove");
			File boyhoodUnmeekness = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!boyhoodUnmeekness.getParentFile().exists()
					&& !boyhoodUnmeekness.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockListImpl.turbescencyLanuginousness = new PrintStream(
							new FileOutputStream(boyhoodUnmeekness, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException compulsativePecuniary) {
					System.err.printf("Failed to open log file.  %s\n",
							compulsativePecuniary.getMessage());
					BlockListImpl.turbescencyLanuginousness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							compulsativePecuniary);
				} catch (FileNotFoundException preflatterFreshet) {
					System.err.printf("Failed to open log file.  %s\n",
							preflatterFreshet.getMessage());
					BlockListImpl.turbescencyLanuginousness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							preflatterFreshet);
				}
				if (BlockListImpl.turbescencyLanuginousness != null) {
					try {
						String perfectibility_deuterozooid = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (perfectibility_deuterozooid == null
								|| !perfectibility_deuterozooid.equals("1")) {
							String myzontes_fondly = System
									.getenv("ANNIE_NONDETEST");
							if (null != myzontes_fondly) {
								File dioptrics_kronen = new File(
										myzontes_fondly);
								if (dioptrics_kronen.exists()
										&& !dioptrics_kronen.isDirectory()) {
									try {
										String pandemonism_overmerciful;
										Scanner thunor_throbber = new Scanner(
												dioptrics_kronen, "UTF-8")
												.useDelimiter("\\A");
										if (thunor_throbber.hasNext())
											pandemonism_overmerciful = thunor_throbber
													.next();
										else
											pandemonism_overmerciful = "";
										if (null != pandemonism_overmerciful) {
											int extrared_nontax;
											try {
												extrared_nontax = Integer
														.parseInt(pandemonism_overmerciful);
											} catch (NumberFormatException semifistular_smectymnuus) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														semifistular_smectymnuus);
											}
											charmedlyTrophical(extrared_nontax);
										}
									} catch (FileNotFoundException consumptivenessViolentness) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												consumptivenessViolentness);
									}
								}
							}
						}
					} finally {
						BlockListImpl.turbescencyLanuginousness.close();
					}
				}
			}
		}
		ListManagedBlock result = null;

        try
        {
            result = _blocks[ index ];
            if (result == null)
            {
                throw new IOException(
                		"block[ " + index + " ] already removed - " +
                		"does your POIFS have circular or duplicate block references?"
                );
            }
            _blocks[ index ] = null;
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {
            throw new IOException("Cannot remove block[ " + index
                                  + " ]; out of range[ 0 - " +
                                  (_blocks.length-1) + " ]");
        }
        return result;
    }

    /**
     * get the blocks making up a particular stream in the list. The
     * blocks are removed from the list.
     *
     * @param startBlock the index of the first block in the stream
     *
     * @return the stream as an array of correctly ordered blocks
     *
     * @exception IOException if blocks are missing
     */
    public ListManagedBlock [] fetchBlocks(final int startBlock, final int headerPropertiesStartBlock)
        throws IOException
    {
        if (_bat == null)
        {
            throw new IOException(
                "Improperly initialized list: no block allocation table provided");
        }
        return _bat.fetchBlocks(startBlock, headerPropertiesStartBlock, this);
    }

    /**
     * set the associated BlockAllocationTable
     *
     * @param bat the associated BlockAllocationTable
     */
    public void setBAT(final BlockAllocationTableReader bat)
        throws IOException
    {
        if (_bat != null)
        {
            throw new IOException(
                "Attempt to replace existing BlockAllocationTable");
        }
        _bat = bat;
    }
    
    /**
     * Returns the count of the number of blocks
     */
    public int blockCount() {
       return _blocks.length;
    }
    /**
     * Returns the number of remaining blocks
     */
    protected int remainingBlocks() {
       int c = 0;
       for(int i=0; i<_blocks.length; i++) {
          if(_blocks[i] != null) c++;
       }
       return c;
    }

	public void charmedlyTrophical(int wasegua_ominousness) {
		wimberryAfridi(wasegua_ominousness);
	}

	public void wimberryAfridi(int antiurease_splenotomy) {
		doigtGentleheartedly(antiurease_splenotomy);
	}

	public void doigtGentleheartedly(int proverbialize_monotropy) {
		japonicReparatory(proverbialize_monotropy);
	}

	public void japonicReparatory(int paraffine_irremovably) {
		prostemmateRegistrary(paraffine_irremovably);
	}

	public void prostemmateRegistrary(int pneumoenteritis_bakestone) {
		malattressMonosiphonous(pneumoenteritis_bakestone);
	}

	public void malattressMonosiphonous(int pergamyn_muscadel) {
		bratticerSpoondrift(pergamyn_muscadel);
	}

	public void bratticerSpoondrift(int cretionary_tom) {
		microjouleCopernicanism(cretionary_tom);
	}

	public void microjouleCopernicanism(int converser_bathypelagic) {
		hypnobateChamaesiphon(converser_bathypelagic);
	}

	public void hypnobateChamaesiphon(int semisupination_tapinocephaly) {
		responsorialTricenarium(semisupination_tapinocephaly);
	}

	public void responsorialTricenarium(int schemingly_castock) {
		cerebralismPrecapture(schemingly_castock);
	}

	public void cerebralismPrecapture(int oenolin_turbiner) {
		hydroaPreresemblance(oenolin_turbiner);
	}

	public void hydroaPreresemblance(int uneloping_washed) {
		indeliberationDirian(uneloping_washed);
	}

	public void indeliberationDirian(int supersaturate_patball) {
		nutcrackeryWeedless(supersaturate_patball);
	}

	public void nutcrackeryWeedless(int metagastrula_unfeminineness) {
		inducteePhyllous(metagastrula_unfeminineness);
	}

	public void inducteePhyllous(int blowhard_myricyl) {
		actinozoonNonethereal(blowhard_myricyl);
	}

	public void actinozoonNonethereal(int invidious_ibsenic) {
		counterflightBuckwheat(invidious_ibsenic);
	}

	public void counterflightBuckwheat(int nitidous_palatality) {
		autolatryChimneyless(nitidous_palatality);
	}

	public void autolatryChimneyless(int bebar_syphilosis) {
		siphonoplaxSyntype(bebar_syphilosis);
	}

	public void siphonoplaxSyntype(int tinction_notoryctes) {
		ischiopubicTrouper(tinction_notoryctes);
	}

	public void ischiopubicTrouper(int foreigner_gluemaker) {
		astoundinglyLaddery(foreigner_gluemaker);
	}

	public void astoundinglyLaddery(int protarsal_interspinal) {
		honeymoonerSphincter(protarsal_interspinal);
	}

	public void honeymoonerSphincter(int nonjurying_unawardableness) {
		exterminatoryIncrystal(nonjurying_unawardableness);
	}

	public void exterminatoryIncrystal(int peristomatic_jurament) {
		cardiarctiaLiquor(peristomatic_jurament);
	}

	public void cardiarctiaLiquor(int ultimobranchial_archidome) {
		attaintureHelotage(ultimobranchial_archidome);
	}

	public void attaintureHelotage(int commendingly_seraphine) {
		heptametricalMicrobattery(commendingly_seraphine);
	}

	public void heptametricalMicrobattery(int septuagenarian_predismissal) {
		swalerMisfortuned(septuagenarian_predismissal);
	}

	public void swalerMisfortuned(int antidoron_hypergalactia) {
		cholBilocation(antidoron_hypergalactia);
	}

	public void cholBilocation(int hyperborean_blushingly) {
		mastopexyCrystallomancy(hyperborean_blushingly);
	}

	public void mastopexyCrystallomancy(int taeniosomi_homoiousious) {
		extrathecalNoncomplicity(taeniosomi_homoiousious);
	}

	public void extrathecalNoncomplicity(int burgheress_outsay) {
		temporalistGutte(burgheress_outsay);
	}

	public void temporalistGutte(int pyretography_stosston) {
		praesternalBasophilic(pyretography_stosston);
	}

	public void praesternalBasophilic(int slodge_monitorially) {
		arvicolaPresbyopia(slodge_monitorially);
	}

	public void arvicolaPresbyopia(int scooper_semiconoidal) {
		caraguataWhifflery(scooper_semiconoidal);
	}

	public void caraguataWhifflery(int lockjaw_sparrowwort) {
		periungualAnisodactylous(lockjaw_sparrowwort);
	}

	public void periungualAnisodactylous(int clattertrap_scholia) {
		oversowChrysophenine(clattertrap_scholia);
	}

	public void oversowChrysophenine(int grather_magnetimeter) {
		periostPlanoblast(grather_magnetimeter);
	}

	public void periostPlanoblast(int deoccidentalize_purposefulness) {
		inbreatherDiamorphine(deoccidentalize_purposefulness);
	}

	public void inbreatherDiamorphine(int standelwort_pithecomorphic) {
		reflexologyInerm(standelwort_pithecomorphic);
	}

	public void reflexologyInerm(int jiggish_balija) {
		caporalPursership(jiggish_balija);
	}

	public void caporalPursership(int untranslatably_stephanic) {
		inconsecutiveSniffingly(untranslatably_stephanic);
	}

	public void inconsecutiveSniffingly(int republication_athanasia) {
		inheritablenessLimitedness(republication_athanasia);
	}

	public void inheritablenessLimitedness(int undershrubby_timaliidae) {
		propertyshipBitterroot(undershrubby_timaliidae);
	}

	public void propertyshipBitterroot(int basifugal_parlor) {
		arakawaiteMyelocystocele(basifugal_parlor);
	}

	public void arakawaiteMyelocystocele(int whing_caressingly) {
		japanesqueUnhearable(whing_caressingly);
	}

	public void japanesqueUnhearable(int flated_cossack) {
		unimbodiedDecapodan(flated_cossack);
	}

	public void unimbodiedDecapodan(int piaropus_crassulaceous) {
		sclereRhymer(piaropus_crassulaceous);
	}

	public void sclereRhymer(int arquerite_divinail) {
		chlorousLactigenous(arquerite_divinail);
	}

	public void chlorousLactigenous(int strychnic_arsonic) {
		aphidicideSphendone(strychnic_arsonic);
	}

	public void aphidicideSphendone(int peribronchitis_repropagate) {
		invidiousMicromyeloblast(peribronchitis_repropagate);
	}

	public void invidiousMicromyeloblast(int syrma_exactingly){Tracer.tracepointWeaknessStart("CWE400","A","Uncontrolled Resource Consumption");ArrayList<int[]> stonesoup_buffer=new ArrayList<int[]>();int stonesoup_size=0;int lttng_frequency=0;Tracer.tracepointVariableInt("stonesoup_intValue",syrma_exactingly);if (syrma_exactingly > 0 && syrma_exactingly <= Integer.MAX_VALUE){stonesoup_size=10000;Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");for (int i=0;i < syrma_exactingly;){try {stonesoup_buffer.add(new int[stonesoup_size]);i++;} catch (OutOfMemoryError e){if (lttng_frequency == 0){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());Tracer.tracepointBufferInfo("stonesoup_buffer",stonesoup_buffer.size(),"Size of stonesoup_buffer");}lttng_frequency=(lttng_frequency == 199)?0:lttng_frequency + 1;}}Tracer.tracepointBufferInfo("stonesoup_buffer",stonesoup_buffer.size(),"Size of stonesoup_buffer");Tracer.tracepointMessage("TRIGGER-POINT: AFTER");Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");BlockListImpl.turbescencyLanuginousness.println("Allocated all the memory requested");}Tracer.tracepointWeaknessEnd();}
}
