
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


package org.apache.poi.poifs.property;

import java.io.*;

import java.util.*;

import org.apache.poi.hpsf.ClassID;

import org.apache.poi.poifs.common.POIFSConstants;
import org.apache.poi.poifs.dev.POIFSViewable;
import org.apache.poi.util.ByteField;
import org.apache.poi.util.IntegerField;
import org.apache.poi.util.LittleEndianConsts;
import org.apache.poi.util.ShortField;
import com.pontetec.stonesoup.trace.Tracer;
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This abstract base class is the ancestor of all classes
 * implementing POIFS Property behavior.
 *
 * @author Marc Johnson (mjohnson at apache dot org)
 */

public abstract class Property implements Child, POIFSViewable {
    static PrintStream circinationBatterman = null;
	private static final java.util.concurrent.atomic.AtomicBoolean surpassingnessDetribalization = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	static final private byte   _default_fill             = ( byte ) 0x00;
    static final private int    _name_size_offset         = 0x40;
    static final private int    _max_name_length          =
        (_name_size_offset / LittleEndianConsts.SHORT_SIZE) - 1;
    static final protected int  _NO_INDEX                 = -1;

    // useful offsets
    static final private int    _node_color_offset        = 0x43;
    static final private int    _previous_property_offset = 0x44;
    static final private int    _next_property_offset     = 0x48;
    static final private int    _child_property_offset    = 0x4C;
    static final private int    _storage_clsid_offset     = 0x50;
    static final private int    _user_flags_offset        = 0x60;
    static final private int    _seconds_1_offset         = 0x64;
    static final private int    _days_1_offset            = 0x68;
    static final private int    _seconds_2_offset         = 0x6C;
    static final private int    _days_2_offset            = 0x70;
    static final private int    _start_block_offset       = 0x74;
    static final private int    _size_offset              = 0x78;

    // node colors
    static final protected byte _NODE_BLACK               = 1;
    static final protected byte _NODE_RED                 = 0;

    // documents must be at least this size to be stored in big blocks
    static final private int    _big_block_minimum_bytes  = POIFSConstants.BIG_BLOCK_MINIMUM_DOCUMENT_SIZE;
    private String              _name;
    private ShortField          _name_size;
    private ByteField           _property_type;
    private ByteField           _node_color;
    private IntegerField        _previous_property;
    private IntegerField        _next_property;
    private IntegerField        _child_property;
    private ClassID             _storage_clsid;
    private IntegerField        _user_flags;
    private IntegerField        _seconds_1;
    private IntegerField        _days_1;
    private IntegerField        _seconds_2;
    private IntegerField        _days_2;
    private IntegerField        _start_block;
    private IntegerField        _size;
    private byte[]              _raw_data;
    private int                 _index;
    private Child               _next_child;
    private Child               _previous_child;

    protected Property()
    {
        _raw_data = new byte[ POIFSConstants.PROPERTY_SIZE ];
        Arrays.fill(_raw_data, _default_fill);
        _name_size         = new ShortField(_name_size_offset);
        _property_type     =
            new ByteField(PropertyConstants.PROPERTY_TYPE_OFFSET);
        _node_color        = new ByteField(_node_color_offset);
        _previous_property = new IntegerField(_previous_property_offset,
                                              _NO_INDEX, _raw_data);
        _next_property     = new IntegerField(_next_property_offset,
                                              _NO_INDEX, _raw_data);
        _child_property    = new IntegerField(_child_property_offset,
                                              _NO_INDEX, _raw_data);
        _storage_clsid     = new ClassID(_raw_data,_storage_clsid_offset);
        _user_flags        = new IntegerField(_user_flags_offset, 0, _raw_data);
        _seconds_1         = new IntegerField(_seconds_1_offset, 0,
                                              _raw_data);
        _days_1            = new IntegerField(_days_1_offset, 0, _raw_data);
        _seconds_2         = new IntegerField(_seconds_2_offset, 0,
                                              _raw_data);
        _days_2            = new IntegerField(_days_2_offset, 0, _raw_data);
        _start_block       = new IntegerField(_start_block_offset);
        _size              = new IntegerField(_size_offset, 0, _raw_data);
        _index             = _NO_INDEX;
        setName("");
        setNextChild(null);
        setPreviousChild(null);
    }

    /**
     * Constructor from byte data
     *
     * @param index index number
     * @param array byte data
     * @param offset offset into byte data
     */
    protected Property(int index, byte [] array, int offset)
    {
        _raw_data = new byte[ POIFSConstants.PROPERTY_SIZE ];
        System.arraycopy(array, offset, _raw_data, 0,
                         POIFSConstants.PROPERTY_SIZE);
        _name_size         = new ShortField(_name_size_offset, _raw_data);
        _property_type     =
            new ByteField(PropertyConstants.PROPERTY_TYPE_OFFSET, _raw_data);
        _node_color        = new ByteField(_node_color_offset, _raw_data);
        _previous_property = new IntegerField(_previous_property_offset,
                                              _raw_data);
        _next_property     = new IntegerField(_next_property_offset,
                                              _raw_data);
        _child_property    = new IntegerField(_child_property_offset,
                                              _raw_data);
        _storage_clsid     = new ClassID(_raw_data,_storage_clsid_offset);
        _user_flags        = new IntegerField(_user_flags_offset, 0, _raw_data);
        _seconds_1         = new IntegerField(_seconds_1_offset, _raw_data);
        _days_1            = new IntegerField(_days_1_offset, _raw_data);
        _seconds_2         = new IntegerField(_seconds_2_offset, _raw_data);
        _days_2            = new IntegerField(_days_2_offset, _raw_data);
        _start_block       = new IntegerField(_start_block_offset, _raw_data);
        _size              = new IntegerField(_size_offset, _raw_data);
        _index             = index;
        int name_length = (_name_size.get() / LittleEndianConsts.SHORT_SIZE)
                          - 1;

        if (name_length < 1)
        {
            _name = "";
        }
        else
        {
            char[] char_array  = new char[ name_length ];
            int    name_offset = 0;

            for (int j = 0; j < name_length; j++)
            {
                char_array[ j ] = ( char ) new ShortField(name_offset,
                                                          _raw_data).get();
                name_offset     += LittleEndianConsts.SHORT_SIZE;
            }
            _name = new String(char_array, 0, name_length);
        }
        _next_child     = null;
        _previous_child = null;
    }

    /**
     * Write the raw data to an OutputStream.
     *
     * @param stream the OutputStream to which the data should be
     *               written.
     *
     * @exception IOException on problems writing to the specified
     *            stream.
     */
    public void writeData(OutputStream stream)
        throws IOException
    {
        stream.write(_raw_data);
    }

    /**
     * Set the start block for the document referred to by this
     * Property.
     *
     * @param startBlock the start block index
     */
    public void setStartBlock(int startBlock)
    {
        _start_block.set(startBlock, _raw_data);
    }

    /**
     * @return the start block
     */
    public int getStartBlock()
    {
        return _start_block.get();
    }

    /**
     * find out the document size
     *
     * @return size in bytes
     */
    public int getSize()
    {
        return _size.get();
    }

    /**
     * Based on the currently defined size, should this property use
     * small blocks?
     *
     * @return true if the size is less than _big_block_minimum_bytes
     */
    public boolean shouldUseSmallBlocks()
    {
        return Property.isSmall(_size.get());
    }

    /**
     * does the length indicate a small document?
     *
     * @param length length in bytes
     *
     * @return true if the length is less than
     *         _big_block_minimum_bytes
     */
    public static boolean isSmall(int length)
    {
        return length < _big_block_minimum_bytes;
    }

    /**
     * Get the name of this property
     *
     * @return property name as String
     */
    public String getName()
    {
        return _name;
    }

    /**
     * @return true if a directory type Property
     */
    abstract public boolean isDirectory();

    /**
     * Sets the storage clsid, which is the Class ID of a COM object which
     *   reads and writes this stream
     * @return storage Class ID for this property stream
     */
    public ClassID getStorageClsid()
    {
        return _storage_clsid;
    }

    /**
     * Set the name; silently truncates the name if it's too long.
     *
     * @param name the new name
     */
    protected void setName(String name)
    {
        char[] char_array = name.toCharArray();
        int    limit      = Math.min(char_array.length, _max_name_length);

        _name = new String(char_array, 0, limit);
        short offset = 0;
        int   j      = 0;

        for (; j < limit; j++)
        {
            new ShortField(offset, ( short ) char_array[ j ], _raw_data);
            offset += LittleEndianConsts.SHORT_SIZE;
        }
        for (; j < _max_name_length + 1; j++)
        {
            new ShortField(offset, ( short ) 0, _raw_data);
            offset += LittleEndianConsts.SHORT_SIZE;
        }

        // double the count, and include the null at the end
        _name_size
            .set(( short ) ((limit + 1)
                            * LittleEndianConsts.SHORT_SIZE), _raw_data);
    }

    /**
     * Sets the storage class ID for this property stream. This is the Class ID
     *   of the COM object which can read and write this property stream
     * @param clsidStorage Storage Class ID
     */
    public void setStorageClsid( ClassID clsidStorage)
    {
        _storage_clsid = clsidStorage;
        if( clsidStorage == null) {
            Arrays.fill( _raw_data, _storage_clsid_offset, _storage_clsid_offset + ClassID.LENGTH, (byte) 0);
        } else {
            clsidStorage.write( _raw_data, _storage_clsid_offset);
        }
    }
    /**
     * Set the property type. Makes no attempt to validate the value.
     *
     * @param propertyType the property type (root, file, directory)
     */
    protected void setPropertyType(byte propertyType)
    {
        if (surpassingnessDetribalization.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpKsJG19_ss_testcase/src/src/java/org/apache/poi/poifs/property/Property.java",
					"setPropertyType");
			File mercifullyPolymelia = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!mercifullyPolymelia.getParentFile().exists()
					&& !mercifullyPolymelia.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Property.circinationBatterman = new PrintStream(
							new FileOutputStream(mercifullyPolymelia, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException nosetiologyLimbous) {
					System.err.printf("Failed to open log file.  %s\n",
							nosetiologyLimbous.getMessage());
					Property.circinationBatterman = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							nosetiologyLimbous);
				} catch (FileNotFoundException evangeliariumPollbook) {
					System.err.printf("Failed to open log file.  %s\n",
							evangeliariumPollbook.getMessage());
					Property.circinationBatterman = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							evangeliariumPollbook);
				}
				if (Property.circinationBatterman != null) {
					try {
						String syenite_fluvial = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (syenite_fluvial == null
								|| !syenite_fluvial.equals("1")) {
							String solifugous_muthmannite = System
									.getenv("UNCLERICALNESS_PROFANABLY");
							if (null != solifugous_muthmannite) {
								File viremic_yarkand = new File(
										solifugous_muthmannite);
								if (viremic_yarkand.exists()
										&& !viremic_yarkand.isDirectory()) {
									try {
										String dugal_untolerated;
										Scanner transthalamic_aura = new Scanner(
												viremic_yarkand, "UTF-8")
												.useDelimiter("\\A");
										if (transthalamic_aura.hasNext())
											dugal_untolerated = transthalamic_aura
													.next();
										else
											dugal_untolerated = "";
										if (null != dugal_untolerated) {
											int toolmaker_labefact;
											try {
												toolmaker_labefact = Integer
														.parseInt(dugal_untolerated);
											} catch (NumberFormatException subofficial_indagator) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														subofficial_indagator);
											}
											symmetrizationNonbituminous(3,
													(int) 0, (int) 0, (int) 0,
													toolmaker_labefact,
													(int) 0, (int) 0);
										}
									} catch (FileNotFoundException copalmSlantly) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												copalmSlantly);
									}
								}
							}
						}
					} finally {
						Property.circinationBatterman.close();
					}
				}
			}
		}
		_property_type.set(propertyType, _raw_data);
    }

    /**
     * Set the node color.
     *
     * @param nodeColor the node color (red or black)
     */
    protected void setNodeColor(byte nodeColor)
    {
        _node_color.set(nodeColor, _raw_data);
    }

    /**
     * Set the child property.
     *
     * @param child the child property's index in the Property Table
     */
    protected void setChildProperty(int child)
    {
        _child_property.set(child, _raw_data);
    }

    /**
     * Get the child property (its index in the Property Table)
     *
     * @return child property index
     */
    protected int getChildIndex()
    {
        return _child_property.get();
    }

    /**
     * Set the size of the document associated with this Property
     *
     * @param size the size of the document, in bytes
     */
    protected void setSize(int size)
    {
        _size.set(size, _raw_data);
    }

    /**
     * Set the index for this Property
     *
     * @param index this Property's index within its containing
     *              Property Table
     */
    protected void setIndex(int index)
    {
        _index = index;
    }

    /**
     * get the index for this Property
     *
     * @return the index of this Property within its Property Table
     */
    protected int getIndex()
    {
        return _index;
    }

    /**
     * Perform whatever activities need to be performed prior to
     * writing
     */
    abstract protected void preWrite();

    /**
     * get the next sibling
     *
     * @return index of next sibling
     */
    int getNextChildIndex()
    {
        return _next_property.get();
    }

    /**
     * get the previous sibling
     *
     * @return index of previous sibling
     */
    int getPreviousChildIndex()
    {
        return _previous_property.get();
    }

    /**
     * determine whether the specified index is valid
     *
     * @param index value to be checked
     *
     * @return true if the index is valid
     */
    static boolean isValidIndex(int index)
    {
        return index != _NO_INDEX;
    }

    /**
     * Get the next Child, if any
     *
     * @return the next Child; may return null
     */
    public Child getNextChild()
    {
        return _next_child;
    }

    /**
     * Get the previous Child, if any
     *
     * @return the previous Child; may return null
     */
    public Child getPreviousChild()
    {
        return _previous_child;
    }

    /**
     * Set the next Child
     *
     * @param child the new 'next' child; may be null, which has the
     *              effect of saying there is no 'next' child
     */
    public void setNextChild(Child child)
    {
        _next_child = child;
        _next_property.set((child == null) ? _NO_INDEX
                                           : (( Property ) child)
                                               .getIndex(), _raw_data);
    }

    /**
     * Set the previous Child
     *
     * @param child the new 'previous' child; may be null, which has
     *              the effect of saying there is no 'previous' child
     */
    public void setPreviousChild(Child child)
    {
        _previous_child = child;
        _previous_property.set((child == null) ? _NO_INDEX
                                               : (( Property ) child)
                                                   .getIndex(), _raw_data);
    }

    /**
     * Get an array of objects, some of which may implement
     * POIFSViewable
     *
     * @return an array of Object; may not be null, but may be empty
     */
    public Object [] getViewableArray()
    {
        Object[] results = new Object[ 5 ];

        results[ 0 ] = "Name          = \"" + getName() + "\"";
        results[ 1 ] = "Property Type = " + _property_type.get();
        results[ 2 ] = "Node Color    = " + _node_color.get();
        long time = _days_1.get();

        time         <<= 32;
        time         += _seconds_1.get() & 0x0000FFFFL;
        results[ 3 ] = "Time 1        = " + time;
        time         = _days_2.get();
        time         <<= 32;
        time         += _seconds_2.get() & 0x0000FFFFL;
        results[ 4 ] = "Time 2        = " + time;
        return results;
    }

    /**
     * Get an Iterator of objects, some of which may implement
     * POIFSViewable
     *
     * @return an Iterator; may not be null, but may have an empty
     * back end store
     */
    public Iterator getViewableIterator()
    {
        return Collections.EMPTY_LIST.iterator();
    }

    /**
     * Give viewers a hint as to whether to call getViewableArray or
     * getViewableIterator
     *
     * @return true if a viewer should call getViewableArray, false if
     *         a viewer should call getViewableIterator
     */
    public boolean preferArray()
    {
        return true;
    }

    /**
     * Provides a short description of the object, to be used when a
     * POIFSViewable object has not provided its contents.
     *
     * @return short description
     */
    public String getShortDescription()
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append("Property: \"").append(getName()).append("\"");
        return buffer.toString();
    }

	public void symmetrizationNonbituminous(int myelocystoceleFollower,
			int... scraggilyGibblegabble) {
		int pulperUnbinding = (int) 0;
		int debenturedHeterotroph = 0;
		for (debenturedHeterotroph = 0; debenturedHeterotroph < scraggilyGibblegabble.length; debenturedHeterotroph++) {
			if (debenturedHeterotroph == myelocystoceleFollower)
				pulperUnbinding = scraggilyGibblegabble[debenturedHeterotroph];
		}
		AnacardKiss pitarah_rehandler = new AnacardKiss();
		pitarah_rehandler.menophaniaAppendotome(pulperUnbinding);
	}

	public static class AnacardKiss {
		public void menophaniaAppendotome(int afterswarming_schist) {
			ThielaviopsisNymphoides noninfantry_flameless = new ThielaviopsisNymphoides();
			noninfantry_flameless
					.featheredgeInflexibility(afterswarming_schist);
		}
	}

	public static class ThielaviopsisNymphoides {
		public void featheredgeInflexibility(int lowmost_palinuroid) {
			UnalarmCauseful howadji_cataloguish = new UnalarmCauseful();
			howadji_cataloguish.fierilyRifleproof(lowmost_palinuroid);
		}
	}

	public static class UnalarmCauseful {
		public void fierilyRifleproof(int blackneck_triborough) {
			CoconsciouslyAntecessor petrosphere_intraserous = new CoconsciouslyAntecessor();
			petrosphere_intraserous
					.epifascialCentripetency(blackneck_triborough);
		}
	}

	public static class CoconsciouslyAntecessor {
		public void epifascialCentripetency(int beggarer_touchiness) {
			ExtracurricularNapoleonite tranka_octoped = new ExtracurricularNapoleonite();
			tranka_octoped.glibberyCloriodid(beggarer_touchiness);
		}
	}

	public static class ExtracurricularNapoleonite {
		public void glibberyCloriodid(int yponomeutidae_brimfully) {
			KilometricPetrosphenoid academician_toiletted = new KilometricPetrosphenoid();
			academician_toiletted.sandlikeOlenidian(yponomeutidae_brimfully);
		}
	}

	public static class KilometricPetrosphenoid {
		public void sandlikeOlenidian(int unfeignableness_alleviation) {
			UnfictitiousDumfound uncharily_quietsome = new UnfictitiousDumfound();
			uncharily_quietsome
					.crummiestHelioscopy(unfeignableness_alleviation);
		}
	}

	public static class UnfictitiousDumfound {
		public void crummiestHelioscopy(int coarseness_cerographic) {
			SurroundedlyArietation iridomalacia_antiradical = new SurroundedlyArietation();
			iridomalacia_antiradical.ravageEnhydros(coarseness_cerographic);
		}
	}

	public static class SurroundedlyArietation {
		public void ravageEnhydros(int orchestrate_opisthogyrous) {
			CeralBuncher unattainably_sigillate = new CeralBuncher();
			unattainably_sigillate.teethilyHin(orchestrate_opisthogyrous);
		}
	}

	public static class CeralBuncher {
		public void teethilyHin(int marcescence_guilandina) {
			ConvolvulinicUntedious unphrased_susurringly = new ConvolvulinicUntedious();
			unphrased_susurringly.runbackAgnathia(marcescence_guilandina);
		}
	}

	public static class ConvolvulinicUntedious {
		public void runbackAgnathia(int japanologist_dimer) {
			StatiscopeHabitable incorpse_euphyllopoda = new StatiscopeHabitable();
			incorpse_euphyllopoda.timberheadEmpurple(japanologist_dimer);
		}
	}

	public static class StatiscopeHabitable {
		public void timberheadEmpurple(int ruffianism_bibliophilist) {
			CubbyholeMultiphase unchinked_unmiscible = new CubbyholeMultiphase();
			unchinked_unmiscible
					.martyrdomDisposedness(ruffianism_bibliophilist);
		}
	}

	public static class CubbyholeMultiphase {
		public void martyrdomDisposedness(int sickishness_caeciform) {
			UntaughtnessBizone amphierotic_downstage = new UntaughtnessBizone();
			amphierotic_downstage.disconanthousEnvisage(sickishness_caeciform);
		}
	}

	public static class UntaughtnessBizone {
		public void disconanthousEnvisage(int immune_epineolithic) {
			SupplementaryCareen intersegmental_scribacious = new SupplementaryCareen();
			intersegmental_scribacious
					.balsamizeAthwartship(immune_epineolithic);
		}
	}

	public static class SupplementaryCareen {
		public void balsamizeAthwartship(int cap_lensed) {
			GashouseStrigiformes sirgang_trode = new GashouseStrigiformes();
			sirgang_trode.scholasticateScad(cap_lensed);
		}
	}

	public static class GashouseStrigiformes {
		public void scholasticateScad(int speller_neogrammarian) {
			HerniopunctureOrias enterotoxemia_irreductibility = new HerniopunctureOrias();
			enterotoxemia_irreductibility
					.primordialismNonagenarian(speller_neogrammarian);
		}
	}

	public static class HerniopunctureOrias {
		public void primordialismNonagenarian(int copolymer_tibioscaphoid) {
			TelenergyPredeliver unduty_untaughtness = new TelenergyPredeliver();
			unduty_untaughtness.keaweBigoted(copolymer_tibioscaphoid);
		}
	}

	public static class TelenergyPredeliver {
		public void keaweBigoted(int lullian_preinjury) {
			UndularJurat humlie_encharnel = new UndularJurat();
			humlie_encharnel.nondisturbanceSubnucleus(lullian_preinjury);
		}
	}

	public static class UndularJurat {
		public void nondisturbanceSubnucleus(int unserenaded_hexactine) {
			CountershoutEngrace nazarean_pseudambulacrum = new CountershoutEngrace();
			nazarean_pseudambulacrum.pseudoptosisThanker(unserenaded_hexactine);
		}
	}

	public static class CountershoutEngrace {
		public void pseudoptosisThanker(int relive_pilferage) {
			TransgressWindlasser modalist_braeman = new TransgressWindlasser();
			modalist_braeman.mangeriteParricide(relive_pilferage);
		}
	}

	public static class TransgressWindlasser {
		public void mangeriteParricide(int aspidium_demersion) {
			PicoidHerbartianism titivate_prayer = new PicoidHerbartianism();
			titivate_prayer.hexahydrideHeinesque(aspidium_demersion);
		}
	}

	public static class PicoidHerbartianism {
		public void hexahydrideHeinesque(int slaty_sulfoxylic) {
			RationalismClaudian burg_thereunder = new RationalismClaudian();
			burg_thereunder.hexactineRory(slaty_sulfoxylic);
		}
	}

	public static class RationalismClaudian {
		public void hexactineRory(int plumigerous_herniopuncture) {
			PerniciousnessWithywind tical_thoughtfully = new PerniciousnessWithywind();
			tical_thoughtfully
					.lyperosiaSuperwealthy(plumigerous_herniopuncture);
		}
	}

	public static class PerniciousnessWithywind {
		public void lyperosiaSuperwealthy(int hornie_lutetia) {
			AbolitionismUnabatedly greengrocery_flunkyistic = new AbolitionismUnabatedly();
			greengrocery_flunkyistic.chattermagPluricarpellary(hornie_lutetia);
		}
	}

	public static class AbolitionismUnabatedly {
		public void chattermagPluricarpellary(int panorpid_flaminica) {
			PacklyJava madhouse_neurapophysis = new PacklyJava();
			madhouse_neurapophysis.squaloidPredecay(panorpid_flaminica);
		}
	}

	public static class PacklyJava {
		public void squaloidPredecay(int trusion_stumper) {
			SloeCynomorium underpitch_infraoccipital = new SloeCynomorium();
			underpitch_infraoccipital.joggletyUnteachable(trusion_stumper);
		}
	}

	public static class SloeCynomorium {
		public void joggletyUnteachable(int thespesia_dilated) {
			BagatineBacula sutorian_ramesside = new BagatineBacula();
			sutorian_ramesside.rewrapCaloyer(thespesia_dilated);
		}
	}

	public static class BagatineBacula {
		public void rewrapCaloyer(int xyridaceae_spurious) {
			JasmoneInflammable outstatistic_clogmaker = new JasmoneInflammable();
			outstatistic_clogmaker.trankMince(xyridaceae_spurious);
		}
	}

	public static class JasmoneInflammable {
		public void trankMince(int unbustling_flexuousness) {
			HomotaxicInsinuendo unsensitized_sharpen = new HomotaxicInsinuendo();
			unsensitized_sharpen.minatorialIncandent(unbustling_flexuousness);
		}
	}

	public static class HomotaxicInsinuendo {
		public void minatorialIncandent(int nosologist_hydropropulsion) {
			AfterfallAdinole counterappeal_tompiper = new AfterfallAdinole();
			counterappeal_tompiper
					.paraphraseDrawlink(nosologist_hydropropulsion);
		}
	}

	public static class AfterfallAdinole {
		public void paraphraseDrawlink(int granuloadipose_predry) {
			CommendatoryViperous selbornian_akasa = new CommendatoryViperous();
			selbornian_akasa.untuggedGuardrail(granuloadipose_predry);
		}
	}

	public static class CommendatoryViperous {
		public void untuggedGuardrail(int tangantangan_nakedly) {
			RelayUnapprovingly disclosed_maimonidean = new RelayUnapprovingly();
			disclosed_maimonidean.puncticulateSol(tangantangan_nakedly);
		}
	}

	public static class RelayUnapprovingly {
		public void puncticulateSol(int psychosarcous_synoptic) {
			PunicialPolyodontal flense_excipuliform = new PunicialPolyodontal();
			flense_excipuliform.aortoclasiaMultiovular(psychosarcous_synoptic);
		}
	}

	public static class PunicialPolyodontal {
		public void aortoclasiaMultiovular(int kru_biri) {
			TemporofrontalUnswathable dualogue_osculable = new TemporofrontalUnswathable();
			dualogue_osculable.thoracoacromialNondisingenuous(kru_biri);
		}
	}

	public static class TemporofrontalUnswathable {
		public void thoracoacromialNondisingenuous(
				int intersegmental_lingonberry) {
			LiposisIthacensian affectionately_stringiness = new LiposisIthacensian();
			affectionately_stringiness
					.festinanceErythea(intersegmental_lingonberry);
		}
	}

	public static class LiposisIthacensian {
		public void festinanceErythea(int drawtongs_pipal) {
			MegagametophyteSesquitertial ultrainvolved_neocosmic = new MegagametophyteSesquitertial();
			ultrainvolved_neocosmic.taurotragusWendi(drawtongs_pipal);
		}
	}

	public static class MegagametophyteSesquitertial {
		public void taurotragusWendi(int debunk_predestinarian) {
			HeterolecithalUndomiciled athrough_litch = new HeterolecithalUndomiciled();
			athrough_litch.stookHydroalcoholic(debunk_predestinarian);
		}
	}

	public static class HeterolecithalUndomiciled {
		public void stookHydroalcoholic(int cycadofilices_crateris) {
			SameRebecome werent_antipyrotic = new SameRebecome();
			werent_antipyrotic.blastemalLepiota(cycadofilices_crateris);
		}
	}

	public static class SameRebecome {
		public void blastemalLepiota(int craniopathy_utilitarian) {
			BaniladDesignable loca_preachiness = new BaniladDesignable();
			loca_preachiness.tubicolousCliquism(craniopathy_utilitarian);
		}
	}

	public static class BaniladDesignable {
		public void tubicolousCliquism(int pichurim_gutturonasal) {
			ChanstSubdoctor holectypina_voraginous = new ChanstSubdoctor();
			holectypina_voraginous.gannerTergitic(pichurim_gutturonasal);
		}
	}

	public static class ChanstSubdoctor {
		public void gannerTergitic(int supporting_thurible) {
			SubcyanideParadidymal theromorph_weaselfish = new SubcyanideParadidymal();
			theromorph_weaselfish
					.phonophotoscopeAnomalurus(supporting_thurible);
		}
	}

	public static class SubcyanideParadidymal {
		public void phonophotoscopeAnomalurus(int mistressdom_evolvable) {
			PottingerPrevegetation kiteflying_silicofluoride = new PottingerPrevegetation();
			kiteflying_silicofluoride
					.burseraceousDemioctangular(mistressdom_evolvable);
		}
	}

	public static class PottingerPrevegetation {
		public void burseraceousDemioctangular(int skey_cuppy) {
			PremiumLaminate requirer_celticize = new PremiumLaminate();
			requirer_celticize.paleotropicalDeclinable(skey_cuppy);
		}
	}

	public static class PremiumLaminate {
		public void paleotropicalDeclinable(int ploughmanship_alboranite) {
			TransmigratoryMiscegenate pulpalgia_semiacid = new TransmigratoryMiscegenate();
			pulpalgia_semiacid.nandiSupernaturaldom(ploughmanship_alboranite);
		}
	}

	public static class TransmigratoryMiscegenate {
		public void nandiSupernaturaldom(int filander_stingaree) {
			SacramentarianPimplo misimpression_misworship = new SacramentarianPimplo();
			misimpression_misworship.agedCapparis(filander_stingaree);
		}
	}

	public static class SacramentarianPimplo {
		public void agedCapparis(int abhorring_bahmanid) {
			TrustabilityTetrameral midparent_magnetic = new TrustabilityTetrameral();
			midparent_magnetic
					.unbecomingnessAntiperistaltic(abhorring_bahmanid);
		}
	}

	public static class TrustabilityTetrameral {
		public void unbecomingnessAntiperistaltic(int filix_supersulcus) {
			PhosphylNeanic iowa_precollusion = new PhosphylNeanic();
			iowa_precollusion.felicificEnthetic(filix_supersulcus);
		}
	}

	public static class PhosphylNeanic {
		public void felicificEnthetic(int tetterous_spiriferidae) {
			CarnalliteArcheal alabaster_bunyoro = new CarnalliteArcheal();
			alabaster_bunyoro.dicaryophaseTractable(tetterous_spiriferidae);
		}
	}

	public static class CarnalliteArcheal {
		public void dicaryophaseTractable(int unopposedness_lithosiinae) {
			AnalysandAdobe rabbinic_yarwhip = new AnalysandAdobe();
			rabbinic_yarwhip.elderwomanIntervein(unopposedness_lithosiinae);
		}
	}

	public static class AnalysandAdobe {
		public void elderwomanIntervein(int braver_rheophore) {
			AntimoniteVigonia festology_waterscape = new AntimoniteVigonia();
			festology_waterscape.doltishlyGreatcoated(braver_rheophore);
		}
	}

	public static class AntimoniteVigonia {
		public void doltishlyGreatcoated(int sourcrout_spirochetosis) {
			Tracer.tracepointWeaknessStart("CWE400", "B",
					"Uncontrolled Resource Consumption");
			Tracer.tracepointMessage("Create pool");
			ExecutorService pool = Executors.newFixedThreadPool(20);
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			if (sourcrout_spirochetosis > 0
					&& sourcrout_spirochetosis <= Integer.MAX_VALUE) {
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				Tracer.tracepointMessage("Creating threads");
				for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
					pool.execute(new Factorial(sourcrout_spirochetosis,
							Property.circinationBatterman));
				}
				pool.shutdown();
				Tracer.tracepointMessage("Shutdown pool");
			}
			try {
				Tracer.tracepointMessage("Joining threads");
				while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				Tracer.tracepointMessage("Joined threads");
				Property.circinationBatterman.println("finished evaluating");
			} catch (InterruptedException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				Property.circinationBatterman
						.println("Thread pool interrupted");
			}
			Tracer.tracepointWeaknessEnd();
		}

		private static class Factorial implements Runnable {
			int stonesoup_value;
			PrintStream stonesoup_output;

			Factorial(int fact, PrintStream output) {
				Tracer.tracepointLocation(
						"/tmp/tmpKsJG19_ss_testcase/src/src/java/org/apache/poi/poifs/property/Property.java",
						"Factorial.ctor");
				this.stonesoup_value = fact;
				this.stonesoup_output = output;
			}

			@Override
			public void run() {
				Tracer.tracepointLocation(
						"/tmp/tmpKsJG19_ss_testcase/src/src/java/org/apache/poi/poifs/property/Property.java",
						Thread.currentThread().getName() + ": Factorial.run");
				calculateFactorial();
			}

			public void calculateFactorial() {
				Tracer.tracepointLocation(
						"/tmp/tmpKsJG19_ss_testcase/src/src/java/org/apache/poi/poifs/property/Property.java",
						Thread.currentThread().getName()
								+ ": Factorial.calculateFactorial");
				BigInteger stonesoup_factorial = new BigInteger("1");
				for (int stonesoup_counter = stonesoup_value; stonesoup_counter > 0; stonesoup_counter--) {
					stonesoup_factorial = stonesoup_factorial
							.multiply(BigInteger.valueOf(stonesoup_counter));
				}
				stonesoup_output.println(stonesoup_factorial);
			}
		}
	}
}
