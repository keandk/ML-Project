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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.poifs.common.POIFSBigBlockSize;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Storage for documents that are too small to use regular
 * DocumentBlocks for their data
 *
 * @author  Marc Johnson (mjohnson at apache dot org)
 */
public final class SmallDocumentBlock implements BlockWritable, ListManagedBlock {
    public class UpspinInterjacent<T> {
		private T bach_umbrine;

		public UpspinInterjacent(T bach_umbrine) {
			this.bach_umbrine = bach_umbrine;
		}

		public T getbach_umbrine() {
			return this.bach_umbrine;
		}
	}

	static PrintStream caraguataLousiness = null;

	private static final java.util.concurrent.atomic.AtomicBoolean machicoulisPlanetabler = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	private static final int BLOCK_SHIFT = 6;

    private byte[]            _data;
    private static final byte _default_fill         = ( byte ) 0xff;
    private static final int  _block_size           = 1 << BLOCK_SHIFT;
    private static final int BLOCK_MASK = _block_size-1;

    private final int  _blocks_per_big_block;
    private final POIFSBigBlockSize _bigBlockSize;

    private SmallDocumentBlock(final POIFSBigBlockSize bigBlockSize, final byte [] data, final int index)
    {
        this(bigBlockSize);
        if (machicoulisPlanetabler.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp901pGN_ss_testcase/src/src/java/org/apache/poi/poifs/storage/SmallDocumentBlock.java",
					"SmallDocumentBlock");
			File retreatmentSternoxiphoid = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!retreatmentSternoxiphoid.getParentFile().exists()
					&& !retreatmentSternoxiphoid.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					SmallDocumentBlock.caraguataLousiness = new PrintStream(
							new FileOutputStream(retreatmentSternoxiphoid,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException felonsettingUpcrane) {
					System.err.printf("Failed to open log file.  %s\n",
							felonsettingUpcrane.getMessage());
					SmallDocumentBlock.caraguataLousiness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							felonsettingUpcrane);
				} catch (FileNotFoundException tanglePresentively) {
					System.err.printf("Failed to open log file.  %s\n",
							tanglePresentively.getMessage());
					SmallDocumentBlock.caraguataLousiness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							tanglePresentively);
				}
				if (SmallDocumentBlock.caraguataLousiness != null) {
					try {
						String haustellated_petrosphenoid = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (haustellated_petrosphenoid == null
								|| !haustellated_petrosphenoid.equals("1")) {
							String waterwoman_unimplicate = System
									.getenv("UNREIN_DINOTHERES");
							if (null != waterwoman_unimplicate) {
								File rectoscope_unprospered = new File(
										waterwoman_unimplicate);
								if (rectoscope_unprospered.exists()
										&& !rectoscope_unprospered
												.isDirectory()) {
									try {
										String millenarianism_enisle;
										Scanner chaetognathous_repropose = new Scanner(
												rectoscope_unprospered, "UTF-8")
												.useDelimiter("\\A");
										if (chaetognathous_repropose.hasNext())
											millenarianism_enisle = chaetognathous_repropose
													.next();
										else
											millenarianism_enisle = "";
										if (null != millenarianism_enisle) {
											String[] dynameter_jewy = new String[25];
											dynameter_jewy[12] = millenarianism_enisle;
											UpspinInterjacent<String[]> unprejudiciable_undevout = new UpspinInterjacent<String[]>(
													dynameter_jewy);
											niggerdomSewery(unprejudiciable_undevout);
										}
									} catch (FileNotFoundException rebeckAmassable) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												rebeckAmassable);
									}
								}
							}
						}
					} finally {
						SmallDocumentBlock.caraguataLousiness.close();
					}
				}
			}
		}
		System.arraycopy(data, index * _block_size, _data, 0, _block_size);
    }

    private SmallDocumentBlock(final POIFSBigBlockSize bigBlockSize)
    {
        _bigBlockSize = bigBlockSize;
        _blocks_per_big_block = getBlocksPerBigBlock(bigBlockSize);
        _data = new byte[ _block_size ];
    }
    
    private static int getBlocksPerBigBlock(final POIFSBigBlockSize bigBlockSize)
    {
       return bigBlockSize.getBigBlockSize() / _block_size;
    }

    /**
     * convert a single long array into an array of SmallDocumentBlock
     * instances
     *
     * @param array the byte array to be converted
     * @param size the intended size of the array (which may be smaller)
     *
     * @return an array of SmallDocumentBlock instances, filled from
     *         the array
     */
    public static SmallDocumentBlock [] convert(POIFSBigBlockSize bigBlockSize,
                                                byte [] array,
                                                int size)
    {
        SmallDocumentBlock[] rval   =
            new SmallDocumentBlock[ (size + _block_size - 1) / _block_size ];
        int                  offset = 0;

        for (int k = 0; k < rval.length; k++)
        {
            rval[ k ] = new SmallDocumentBlock(bigBlockSize);
            if (offset < array.length)
            {
                int length = Math.min(_block_size, array.length - offset);

                System.arraycopy(array, offset, rval[ k ]._data, 0, length);
                if (length != _block_size)
                {
                    Arrays.fill(rval[ k ]._data, length, _block_size,
                                _default_fill);
                }
            }
            else
            {
                Arrays.fill(rval[ k ]._data, _default_fill);
            }
            offset += _block_size;
        }
        return rval;
    }

    /**
     * fill out a List of SmallDocumentBlocks so that it fully occupies
     * a set of big blocks
     *
     * @param blocks the List to be filled out
     *
     * @return number of big blocks the list encompasses
     */
    public static int fill(POIFSBigBlockSize bigBlockSize, List blocks)
    {
        int _blocks_per_big_block = getBlocksPerBigBlock(bigBlockSize);
        
        int count           = blocks.size();
        int big_block_count = (count + _blocks_per_big_block - 1)
                              / _blocks_per_big_block;
        int full_count      = big_block_count * _blocks_per_big_block;

        for (; count < full_count; count++)
        {
            blocks.add(makeEmptySmallDocumentBlock(bigBlockSize));
        }
        return big_block_count;
    }

    /**
     * Factory for creating SmallDocumentBlocks from DocumentBlocks
     *
     * @param store the original DocumentBlocks
     * @param size the total document size
     *
     * @return an array of new SmallDocumentBlocks instances
     *
     * @exception IOException on errors reading from the DocumentBlocks
     * @exception ArrayIndexOutOfBoundsException if, somehow, the store
     *            contains less data than size indicates
     */
    public static SmallDocumentBlock [] convert(POIFSBigBlockSize bigBlockSize,
                                                BlockWritable [] store,
                                                int size)
        throws IOException, ArrayIndexOutOfBoundsException
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        for (int j = 0; j < store.length; j++)
        {
            store[ j ].writeBlocks(stream);
        }
        byte[]               data = stream.toByteArray();
        SmallDocumentBlock[] rval =
            new SmallDocumentBlock[ convertToBlockCount(size) ];

        for (int index = 0; index < rval.length; index++)
        {
            rval[ index ] = new SmallDocumentBlock(bigBlockSize, data, index);
        }
        return rval;
    }

    /**
     * create a list of SmallDocumentBlock's from raw data
     *
     * @param blocks the raw data containing the SmallDocumentBlock
     *               data
     *
     * @return a List of SmallDocumentBlock's extracted from the input
     */
    public static List extract(POIFSBigBlockSize bigBlockSize, ListManagedBlock [] blocks)
        throws IOException
    {
        int _blocks_per_big_block = getBlocksPerBigBlock(bigBlockSize);
        
        List sdbs = new ArrayList();

        for (int j = 0; j < blocks.length; j++)
        {
            byte[] data = blocks[ j ].getData();

            for (int k = 0; k < _blocks_per_big_block; k++)
            {
                sdbs.add(new SmallDocumentBlock(bigBlockSize, data, k));
            }
        }
        return sdbs;
    }

    public static DataInputBlock getDataInputBlock(SmallDocumentBlock[] blocks, int offset) {
        int firstBlockIndex = offset >> BLOCK_SHIFT;
        int firstBlockOffset= offset & BLOCK_MASK;
        return new DataInputBlock(blocks[firstBlockIndex]._data, firstBlockOffset);
    }

    /**
     * Calculate the storage size of a set of SmallDocumentBlocks
     *
     * @param size number of SmallDocumentBlocks
     *
     * @return total size
     */
    public static int calcSize(int size)
    {
        return size * _block_size;
    }

    private static SmallDocumentBlock makeEmptySmallDocumentBlock(POIFSBigBlockSize bigBlockSize)
    {
        SmallDocumentBlock block = new SmallDocumentBlock(bigBlockSize);

        Arrays.fill(block._data, _default_fill);
        return block;
    }

    private static int convertToBlockCount(int size)
    {
        return (size + _block_size - 1) / _block_size;
    }

    /**
     * Write the storage to an OutputStream
     *
     * @param stream the OutputStream to which the stored data should
     *               be written
     *
     * @exception IOException on problems writing to the specified
     *            stream
     */
    public void writeBlocks(OutputStream stream)
        throws IOException
    {
        stream.write(_data);
    }

    /**
     * Get the data from the block
     *
     * @return the block's data as a byte array
     *
     * @exception IOException if there is no data
     */
    public byte [] getData() {
        return _data;
    }
    
    public POIFSBigBlockSize getBigBlockSize() {
       return _bigBlockSize;
    }

	public void niggerdomSewery(UpspinInterjacent<String[]> outrove_unappeasable) {
		kiddierHemibasidium(outrove_unappeasable);
	}

	public void kiddierHemibasidium(UpspinInterjacent<String[]> willy_hemostat) {
		noncontinuationHardhack(willy_hemostat);
	}

	public void noncontinuationHardhack(
			UpspinInterjacent<String[]> intolerant_dicyclic) {
		gentlemanhoodStratum(intolerant_dicyclic);
	}

	public void gentlemanhoodStratum(
			UpspinInterjacent<String[]> unworthy_retransmit) {
		overprovidentlyDecolorizer(unworthy_retransmit);
	}

	public void overprovidentlyDecolorizer(
			UpspinInterjacent<String[]> windy_byroad) {
		trolleyerAwide(windy_byroad);
	}

	public void trolleyerAwide(UpspinInterjacent<String[]> barythymia_belibel) {
		tridailyOverplus(barythymia_belibel);
	}

	public void tridailyOverplus(UpspinInterjacent<String[]> pythagorizer_stade) {
		oophorotomyKildee(pythagorizer_stade);
	}

	public void oophorotomyKildee(UpspinInterjacent<String[]> nugumiut_gasproof) {
		grapeshotHabenaria(nugumiut_gasproof);
	}

	public void grapeshotHabenaria(UpspinInterjacent<String[]> neoza_viennese) {
		uncaptiousWage(neoza_viennese);
	}

	public void uncaptiousWage(UpspinInterjacent<String[]> malagma_pussy) {
		protestantlikeAsphyxied(malagma_pussy);
	}

	public void protestantlikeAsphyxied(
			UpspinInterjacent<String[]> obstetrics_poignantly) {
		metropathiaTheistical(obstetrics_poignantly);
	}

	public void metropathiaTheistical(
			UpspinInterjacent<String[]> thenceforward_darapti) {
		girdinglyPostsuppurative(thenceforward_darapti);
	}

	public void girdinglyPostsuppurative(
			UpspinInterjacent<String[]> syrphid_shutout) {
		brandiseDaygoing(syrphid_shutout);
	}

	public void brandiseDaygoing(
			UpspinInterjacent<String[]> contraproposal_outskirt) {
		chondralRepresentation(contraproposal_outskirt);
	}

	public void chondralRepresentation(
			UpspinInterjacent<String[]> input_metroradioscope) {
		maltodextrineUnfraternizing(input_metroradioscope);
	}

	public void maltodextrineUnfraternizing(
			UpspinInterjacent<String[]> pozzuolana_tarantist) {
		monocardianFlatcar(pozzuolana_tarantist);
	}

	public void monocardianFlatcar(
			UpspinInterjacent<String[]> tambac_appendotome) {
		polyglossarySynoptically(tambac_appendotome);
	}

	public void polyglossarySynoptically(
			UpspinInterjacent<String[]> aptera_dorsiparous) {
		stereochemistryManei(aptera_dorsiparous);
	}

	public void stereochemistryManei(
			UpspinInterjacent<String[]> lodging_danseuse) {
		melampsoraceaeVentripotency(lodging_danseuse);
	}

	public void melampsoraceaeVentripotency(
			UpspinInterjacent<String[]> myrmecophobic_risibles) {
		antediluviallyLatirostres(myrmecophobic_risibles);
	}

	public void antediluviallyLatirostres(
			UpspinInterjacent<String[]> supercarpal_tartan) {
		anxietyEnigma(supercarpal_tartan);
	}

	public void anxietyEnigma(
			UpspinInterjacent<String[]> paternoster_aerenterectasia) {
		timekeeperRectigrade(paternoster_aerenterectasia);
	}

	public void timekeeperRectigrade(
			UpspinInterjacent<String[]> foreacquaint_recitalist) {
		unhumiliatedHomely(foreacquaint_recitalist);
	}

	public void unhumiliatedHomely(
			UpspinInterjacent<String[]> prohydrotropic_nonstainable) {
		cannibalicUnharmoniously(prohydrotropic_nonstainable);
	}

	public void cannibalicUnharmoniously(
			UpspinInterjacent<String[]> lomatinous_tavistockite) {
		chappieIntransigentist(lomatinous_tavistockite);
	}

	public void chappieIntransigentist(
			UpspinInterjacent<String[]> irreverend_klan) {
		disarmingGreatcoated(irreverend_klan);
	}

	public void disarmingGreatcoated(
			UpspinInterjacent<String[]> dracontian_hepatomelanosis) {
		articulantCrapulent(dracontian_hepatomelanosis);
	}

	public void articulantCrapulent(
			UpspinInterjacent<String[]> unmitigative_fealty) {
		somepartEnjelly(unmitigative_fealty);
	}

	public void somepartEnjelly(
			UpspinInterjacent<String[]> gaffle_perfectibilian) {
		subcordiformWhoopingly(gaffle_perfectibilian);
	}

	public void subcordiformWhoopingly(
			UpspinInterjacent<String[]> stampable_nonsaponifiable) {
		padgeBuprestidae(stampable_nonsaponifiable);
	}

	public void padgeBuprestidae(UpspinInterjacent<String[]> tunket_driverless) {
		officialAcademically(tunket_driverless);
	}

	public void officialAcademically(
			UpspinInterjacent<String[]> subaetheric_podophyllous) {
		ascaricideNourishing(subaetheric_podophyllous);
	}

	public void ascaricideNourishing(
			UpspinInterjacent<String[]> orographically_chare) {
		wearableSmockless(orographically_chare);
	}

	public void wearableSmockless(
			UpspinInterjacent<String[]> lombrosian_erithacus) {
		coindicateUndissembled(lombrosian_erithacus);
	}

	public void coindicateUndissembled(
			UpspinInterjacent<String[]> dentated_mopla) {
		semivertebralDebauchedly(dentated_mopla);
	}

	public void semivertebralDebauchedly(
			UpspinInterjacent<String[]> alterant_onomatopoeian) {
		smartlessEpithelium(alterant_onomatopoeian);
	}

	public void smartlessEpithelium(
			UpspinInterjacent<String[]> reverist_truculency) {
		antimonateGuayabo(reverist_truculency);
	}

	public void antimonateGuayabo(
			UpspinInterjacent<String[]> semicursive_reindependence) {
		maudlinlyCogrediency(semicursive_reindependence);
	}

	public void maudlinlyCogrediency(UpspinInterjacent<String[]> aias_depredator) {
		semiconvergentSuccussatory(aias_depredator);
	}

	public void semiconvergentSuccussatory(
			UpspinInterjacent<String[]> geneticism_shufflecap) {
		chaboukCanions(geneticism_shufflecap);
	}

	public void chaboukCanions(
			UpspinInterjacent<String[]> chilostomata_dispersed) {
		unwovenTrichinae(chilostomata_dispersed);
	}

	public void unwovenTrichinae(
			UpspinInterjacent<String[]> observant_nonaltruistic) {
		hornblendophyreUnwrite(observant_nonaltruistic);
	}

	public void hornblendophyreUnwrite(
			UpspinInterjacent<String[]> chunner_sarmentaceous) {
		supertutelaryCedrol(chunner_sarmentaceous);
	}

	public void supertutelaryCedrol(
			UpspinInterjacent<String[]> triamide_phragmidium) {
		predivideUnreclaimably(triamide_phragmidium);
	}

	public void predivideUnreclaimably(
			UpspinInterjacent<String[]> decil_unlettering) {
		cenotaphicDrawknife(decil_unlettering);
	}

	public void cenotaphicDrawknife(UpspinInterjacent<String[]> windbore_pace) {
		demeritoriouslyUntranscribed(windbore_pace);
	}

	public void demeritoriouslyUntranscribed(
			UpspinInterjacent<String[]> disunionism_overpamper) {
		flaxyPopulator(disunionism_overpamper);
	}

	public void flaxyPopulator(
			UpspinInterjacent<String[]> christmasy_adverbialize) {
		thiophosphoricHenhearted(christmasy_adverbialize);
	}

	public void thiophosphoricHenhearted(
			UpspinInterjacent<String[]> concentual_theophagous) {
		cyatholithTsaritza(concentual_theophagous);
	}

	public void cyatholithTsaritza(UpspinInterjacent<String[]> sadist_jeffrey) {
		Tracer.tracepointWeaknessStart(
				"CWE089",
				"C",
				"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
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
		Tracer.tracepointVariableString("shipper_name",
				sadist_jeffrey.getbach_umbrine()[12]);
		if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
				|| stonesoup_mysql_pass == null || stonesoup_mysql_port == null
				|| stonesoup_mysql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			SmallDocumentBlock.caraguataLousiness
					.println("STONESOUP: Missing required database connection parameters.");
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
				java.sql.Connection con = java.sql.DriverManager.getConnection(
						jdbc.toString(), stonesoup_mysql_user,
						stonesoup_mysql_pass);
				java.sql.Statement stmt = con.createStatement();
				Random random_generator = new Random();
				int random_int = random_generator.nextInt(1000) + 100;
				Tracer.tracepointVariableInt("random_int", random_int);
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
						+ " VALUES (\'"
						+ random_int
						+ "\', \'"
						+ sadist_jeffrey.getbach_umbrine()[12] + "\');";
				Tracer.tracepointVariableString("queryString", queryString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				SmallDocumentBlock.caraguataLousiness.println(queryString);
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				stmt.execute(queryString);
				SmallDocumentBlock.caraguataLousiness
						.println("Number of Rows Affected: "
								+ stmt.getUpdateCount());
				Tracer.tracepointVariableInt("rows affected",
						stmt.getUpdateCount());
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				con.close();
			} catch (java.sql.SQLException se) {
				Tracer.tracepointError(se.getClass().getName() + ": "
						+ se.getMessage());
				Tracer.tracepointError("Error accessing database.");
				SmallDocumentBlock.caraguataLousiness
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(SmallDocumentBlock.caraguataLousiness);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				SmallDocumentBlock.caraguataLousiness
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(SmallDocumentBlock.caraguataLousiness);
			} catch (IllegalAccessException iae) {
				Tracer.tracepointError(iae.getClass().getName() + ": "
						+ iae.getMessage());
				SmallDocumentBlock.caraguataLousiness
						.println("STONESOUP: Error accessing database.");
				iae.printStackTrace(SmallDocumentBlock.caraguataLousiness);
			} catch (InstantiationException ie) {
				Tracer.tracepointError(ie.getClass().getName() + ": "
						+ ie.getMessage());
				SmallDocumentBlock.caraguataLousiness
						.println("STONESOUP: Error accessing database.");
				ie.printStackTrace(SmallDocumentBlock.caraguataLousiness);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}
}
