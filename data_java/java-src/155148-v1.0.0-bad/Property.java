
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

/**
 * This abstract base class is the ancestor of all classes
 * implementing POIFS Property behavior.
 *
 * @author Marc Johnson (mjohnson at apache dot org)
 */

public abstract class Property implements Child, POIFSViewable {
    public class FlixweedRecidivism<T> {
		private T mocoa_hemiholohedral;

		public FlixweedRecidivism(T mocoa_hemiholohedral) {
			this.mocoa_hemiholohedral = mocoa_hemiholohedral;
		}

		public T getmocoa_hemiholohedral() {
			return this.mocoa_hemiholohedral;
		}
	}

	static PrintStream reformabilityDominionism = null;
	private static final java.util.concurrent.atomic.AtomicBoolean inunctuosityOffended = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (inunctuosityOffended.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpUHTK7V_ss_testcase/src/src/java/org/apache/poi/poifs/property/Property.java",
					"setPropertyType");
			File tumidlyInnless = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!tumidlyInnless.getParentFile().exists()
					&& !tumidlyInnless.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Property.reformabilityDominionism = new PrintStream(
							new FileOutputStream(tumidlyInnless, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException smallishNudist) {
					System.err.printf("Failed to open log file.  %s\n",
							smallishNudist.getMessage());
					Property.reformabilityDominionism = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							smallishNudist);
				} catch (FileNotFoundException esthesiometryTunder) {
					System.err.printf("Failed to open log file.  %s\n",
							esthesiometryTunder.getMessage());
					Property.reformabilityDominionism = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							esthesiometryTunder);
				}
				if (Property.reformabilityDominionism != null) {
					try {
						String dramatical_discussional = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (dramatical_discussional == null
								|| !dramatical_discussional.equals("1")) {
							String diacodion_anamnionata = System
									.getenv("UNSLEEK_CISALPINE");
							if (null != diacodion_anamnionata) {
								File gazophylacium_plancier = new File(
										diacodion_anamnionata);
								if (gazophylacium_plancier.exists()
										&& !gazophylacium_plancier
												.isDirectory()) {
									try {
										String osteodystrophy_sycones;
										Scanner lophosteon_hexahydric = new Scanner(
												gazophylacium_plancier, "UTF-8")
												.useDelimiter("\\A");
										if (lophosteon_hexahydric.hasNext())
											osteodystrophy_sycones = lophosteon_hexahydric
													.next();
										else
											osteodystrophy_sycones = "";
										if (null != osteodystrophy_sycones) {
											short levelheadedness_pokeful;
											try {
												levelheadedness_pokeful = Short
														.parseShort(osteodystrophy_sycones);
											} catch (NumberFormatException procuratory_prodelay) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														procuratory_prodelay);
											}
											FlixweedRecidivism<Short> impenitibleness_woolding = new FlixweedRecidivism<Short>(
													levelheadedness_pokeful);
											Tracer.tracepointWeaknessStart(
													"CWE190", "A",
													"Integer Overflow or Wraparound");
											short stonesoup_checked_value = impenitibleness_woolding
													.getmocoa_hemiholohedral();
											Tracer.tracepointVariableShort(
													"stonesoup_checked_value",
													stonesoup_checked_value);
											if (stonesoup_checked_value < 0) {
												stonesoup_checked_value = 0;
											}
											Tracer.tracepointVariableShort(
													"stonesoup_checked_value",
													stonesoup_checked_value);
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											short stonesoup_value = (short) (((short) (Short.MAX_VALUE) - (short) 500) + stonesoup_checked_value);
											Tracer.tracepointVariableShort(
													"stonesoup_value",
													stonesoup_value);
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											stonesoup_value++;
											String[] stonesoup_array = null;
											try {
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												Tracer.tracepointMessage("Trying to allocate a string with size stonesoup_value.");
												stonesoup_array = new String[stonesoup_value];
												Tracer.tracepointBufferInfo(
														"stonesoup_array",
														stonesoup_array.length,
														"Length of newly allocated stonesoup_array");
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												for (short index = 0; index < stonesoup_value; index++) {
													stonesoup_array[index] = Character
															.toString((char) index);
												}
												Tracer.tracepointMessage("Copy data into stonesoup_array.");
											} catch (java.lang.RuntimeException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												e.printStackTrace(Property.reformabilityDominionism);
												throw e;
											}
											for (int counter = 0; counter < stonesoup_array.length; counter++) {
												Property.reformabilityDominionism
														.printf("array[%d]=%s\n",
																counter,
																stonesoup_array[counter]);
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException oliviferousOrganicism) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												oliviferousOrganicism);
									}
								}
							}
						}
					} finally {
						Property.reformabilityDominionism.close();
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
}
