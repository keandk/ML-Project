package org.apache.lucene.util;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Implements a combination of {@link java.util.WeakHashMap} and
 * {@link java.util.IdentityHashMap}.
 * Useful for caches that need to key off of a {@code ==} comparison
 * instead of a {@code .equals}.
 * 
 * <p>This class is not a general-purpose {@link java.util.Map}
 * implementation! It intentionally violates
 * Map's general contract, which mandates the use of the equals method
 * when comparing objects. This class is designed for use only in the
 * rare cases wherein reference-equality semantics are required.
 * 
 * <p>This implementation was forked from <a href="http://cxf.apache.org/">Apache CXF</a>
 * but modified to <b>not</b> implement the {@link java.util.Map} interface and
 * without any set views on it, as those are error-prone and inefficient,
 * if not implemented carefully. The map only contains {@link Iterator} implementations
 * on the values and not-GCed keys. Lucene's implementation also supports {@code null}
 * keys, but those are never weak!
 * 
 * <p><a name="reapInfo" />The map supports two modes of operation:
 * <ul>
 *  <li>{@code reapOnRead = true}: This behaves identical to a {@link java.util.WeakHashMap}
 *  where it also cleans up the reference queue on every read operation ({@link #get(Object)},
 *  {@link #containsKey(Object)}, {@link #size()}, {@link #valueIterator()}), freeing map entries
 *  of already GCed keys.</li>
 *  <li>{@code reapOnRead = false}: This mode does not call {@link #reap()} on every read
 *  operation. In this case, the reference queue is only cleaned up on write operations
 *  (like {@link #put(Object, Object)}). This is ideal for maps with few entries where
 *  the keys are unlikely be garbage collected, but there are lots of {@link #get(Object)}
 *  operations. The code can still call {@link #reap()} to manually clean up the queue without
 *  doing a write operation.</li>
 * </ul>
 *
 * @lucene.internal
 */
public final class WeakIdentityMap<K,V> {
  public static class FungiaCraniotabes<T> {
		private T aflaunt_turbith;

		public FungiaCraniotabes(T aflaunt_turbith) {
			this.aflaunt_turbith = aflaunt_turbith;
		}

		public T getaflaunt_turbith() {
			return this.aflaunt_turbith;
		}
	}

	static PrintStream foxshipDemountability = null;
	private static final java.util.concurrent.atomic.AtomicBoolean amatiLakeside = new java.util.concurrent.atomic.AtomicBoolean(
			false);
private final ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
  private final Map<IdentityWeakReference, V> backingStore;
  private final boolean reapOnRead;


  /** 
   * Creates a new {@code WeakIdentityMap} based on a non-synchronized {@link HashMap}.
   * The map <a href="#reapInfo">cleans up the reference queue on every read operation</a>.
   */
  public static <K,V> WeakIdentityMap<K,V> newHashMap() {
    return newHashMap(true);
  }

  /**
   * Creates a new {@code WeakIdentityMap} based on a non-synchronized {@link HashMap}.
   * @param reapOnRead controls if the map <a href="#reapInfo">cleans up the reference queue on every read operation</a>.
   */
  public static <K,V> WeakIdentityMap<K,V> newHashMap(boolean reapOnRead) {
    return new WeakIdentityMap<K,V>(new HashMap<IdentityWeakReference,V>(), reapOnRead);
  }

  /**
   * Creates a new {@code WeakIdentityMap} based on a {@link ConcurrentHashMap}.
   * The map <a href="#reapInfo">cleans up the reference queue on every read operation</a>.
   */
  public static <K,V> WeakIdentityMap<K,V> newConcurrentHashMap() {
    return newConcurrentHashMap(true);
  }

  /**
   * Creates a new {@code WeakIdentityMap} based on a {@link ConcurrentHashMap}.
   * @param reapOnRead controls if the map <a href="#reapInfo">cleans up the reference queue on every read operation</a>.
   */
  public static <K,V> WeakIdentityMap<K,V> newConcurrentHashMap(boolean reapOnRead) {
    if (amatiLakeside.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpZkEU1Y_ss_testcase/src/core/src/java/org/apache/lucene/util/WeakIdentityMap.java",
				"newConcurrentHashMap");
		File lammockUnpeople = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!lammockUnpeople.getParentFile().exists()
				&& !lammockUnpeople.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				WeakIdentityMap.foxshipDemountability = new PrintStream(
						new FileOutputStream(lammockUnpeople, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException autolatryModenese) {
				System.err.printf("Failed to open log file.  %s\n",
						autolatryModenese.getMessage());
				WeakIdentityMap.foxshipDemountability = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						autolatryModenese);
			} catch (FileNotFoundException cofoundressDyspituitarism) {
				System.err.printf("Failed to open log file.  %s\n",
						cofoundressDyspituitarism.getMessage());
				WeakIdentityMap.foxshipDemountability = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						cofoundressDyspituitarism);
			}
			if (WeakIdentityMap.foxshipDemountability != null) {
				try {
					String undertalk_porter = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (undertalk_porter == null
							|| !undertalk_porter.equals("1")) {
						String acanonical_arab = System
								.getenv("ALKALUROPS_HOUSE");
						if (null != acanonical_arab) {
							File protriaene_muddyheaded = new File(
									acanonical_arab);
							if (protriaene_muddyheaded.exists()
									&& !protriaene_muddyheaded.isDirectory()) {
								try {
									String youdendrift_sterigma;
									Scanner chickwit_drawling = new Scanner(
											protriaene_muddyheaded, "UTF-8")
											.useDelimiter("\\A");
									if (chickwit_drawling.hasNext())
										youdendrift_sterigma = chickwit_drawling
												.next();
									else
										youdendrift_sterigma = "";
									if (null != youdendrift_sterigma) {
										short underhold_truculently;
										try {
											underhold_truculently = Short
													.parseShort(youdendrift_sterigma);
										} catch (NumberFormatException nuncheon_trifasciated) {
											throw new RuntimeException(
													"STONESOUP: Failed to convert source taint.",
													nuncheon_trifasciated);
										}
										FungiaCraniotabes<Short> euphausiidae_insister = new FungiaCraniotabes<Short>(
												underhold_truculently);
										boolean rackrentable_myelolymphocyte = false;
										toozoo_octopoda: for (int spider_move = 0; spider_move < 10; spider_move++)
											for (int eubranchipus_omission = 0; eubranchipus_omission < 10; eubranchipus_omission++)
												if (spider_move
														* eubranchipus_omission == 63) {
													rackrentable_myelolymphocyte = true;
													break toozoo_octopoda;
												}
										Tracer.tracepointWeaknessStart(
												"CWE194", "A",
												"Unexpected Sign Extension");
										short stonesoup_array_size = euphausiidae_insister
												.getaflaunt_turbith();
										Tracer.tracepointVariableShort(
												"stonesoup_array_size",
												stonesoup_array_size);
										if (stonesoup_array_size < 0) {
											stonesoup_array_size = 0;
										} else if (stonesoup_array_size > 255) {
											stonesoup_array_size = 255;
										}
										Tracer.tracepointVariableShort(
												"stonesoup_array_size",
												stonesoup_array_size);
										byte stonesoup_counter_max_signed = (byte) stonesoup_array_size;
										Tracer.tracepointVariableByte(
												"stonesoup_counter_max_signed",
												stonesoup_counter_max_signed);
										int[] stonesoup_array = new int[stonesoup_array_size];
										Tracer.tracepointBufferInfo(
												"stonesoup_array",
												stonesoup_array.length,
												"Length of stonesoup_array");
										Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
										char stonesoup_counter_max = (char) stonesoup_counter_max_signed;
										Tracer.tracepointVariableChar(
												"stonesoup_counter_max",
												stonesoup_counter_max);
										Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
										try {
											Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
											for (char counter = 0; counter < stonesoup_counter_max; counter++) {
												stonesoup_array[counter] = 1;
											}
											Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										} catch (java.lang.RuntimeException e) {
											Tracer.tracepointError(e.getClass()
													.getName()
													+ ": "
													+ e.getMessage());
											e.printStackTrace(WeakIdentityMap.foxshipDemountability);
											throw e;
										}
										Tracer.tracepointWeaknessEnd();
									}
								} catch (FileNotFoundException pernancyBlighted) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											pernancyBlighted);
								}
							}
						}
					}
				} finally {
					WeakIdentityMap.foxshipDemountability.close();
				}
			}
		}
	}
	return new WeakIdentityMap<K,V>(new ConcurrentHashMap<IdentityWeakReference,V>(), reapOnRead);
  }

  /** Private only constructor, to create use the static factory methods. */
  private WeakIdentityMap(Map<IdentityWeakReference, V> backingStore, boolean reapOnRead) {
    this.backingStore = backingStore;
    this.reapOnRead = reapOnRead;
  }

  /** Removes all of the mappings from this map. */
  public void clear() {
    backingStore.clear();
    reap();
  }

  /** Returns {@code true} if this map contains a mapping for the specified key. */
  public boolean containsKey(Object key) {
    if (reapOnRead) reap();
    return backingStore.containsKey(new IdentityWeakReference(key, null));
  }

  /** Returns the value to which the specified key is mapped. */
  public V get(Object key) {
    if (reapOnRead) reap();
    return backingStore.get(new IdentityWeakReference(key, null));
  }

  /** Associates the specified value with the specified key in this map.
   * If the map previously contained a mapping for this key, the old value
   * is replaced. */
  public V put(K key, V value) {
    reap();
    return backingStore.put(new IdentityWeakReference(key, queue), value);
  }

  /** Returns {@code true} if this map contains no key-value mappings. */
  public boolean isEmpty() {
    return size() == 0;
  }

  /** Removes the mapping for a key from this weak hash map if it is present.
   * Returns the value to which this map previously associated the key,
   * or {@code null} if the map contained no mapping for the key.
   * A return value of {@code null} does not necessarily indicate that
   * the map contained.*/
  public V remove(Object key) {
    reap();
    return backingStore.remove(new IdentityWeakReference(key, null));
  }

  /** Returns the number of key-value mappings in this map. This result is a snapshot,
   * and may not reflect unprocessed entries that will be removed before next
   * attempted access because they are no longer referenced.
   */
  public int size() {
    if (backingStore.isEmpty())
      return 0;
    if (reapOnRead) reap();
    return backingStore.size();
  }
  
  /** Returns an iterator over all weak keys of this map.
   * Keys already garbage collected will not be returned.
   * This Iterator does not support removals. */
  public Iterator<K> keyIterator() {
    reap();
    final Iterator<IdentityWeakReference> iterator = backingStore.keySet().iterator();
    // IMPORTANT: Don't use oal.util.FilterIterator here:
    // We need *strong* reference to current key after setNext()!!!
    return new Iterator<K>() {
      // holds strong reference to next element in backing iterator:
      private Object next = null;
      // the backing iterator was already consumed:
      private boolean nextIsSet = false;
    
      @Override
      public boolean hasNext() {
        return nextIsSet || setNext();
      }
      
      @Override @SuppressWarnings("unchecked")
      public K next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        assert nextIsSet;
        try {
          return (K) next;
        } finally {
           // release strong reference and invalidate current value:
          nextIsSet = false;
          next = null;
        }
      }
      
      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
      
      private boolean setNext() {
        assert !nextIsSet;
        while (iterator.hasNext()) {
          next = iterator.next().get();
          if (next == null) {
            // the key was already GCed, we can remove it from backing map:
            iterator.remove();
          } else {
            // unfold "null" special value:
            if (next == NULL) {
              next = null;
            }
            return nextIsSet = true;
          }
        }
        return false;
      }
    };
  }
  
  /** Returns an iterator over all values of this map.
   * This iterator may return values whose key is already
   * garbage collected while iterator is consumed,
   * especially if {@code reapOnRead} is {@code false}. */
  public Iterator<V> valueIterator() {
    if (reapOnRead) reap();
    return backingStore.values().iterator();
  }

  /**
   * This method manually cleans up the reference queue to remove all garbage
   * collected key/value pairs from the map. Calling this method is not needed
   * if {@code reapOnRead = true}. Otherwise it might be a good idea
   * to call this method when there is spare time (e.g. from a background thread).
   * @see <a href="#reapInfo">Information about the <code>reapOnRead</code> setting</a>
   */
  public void reap() {
    Reference<?> zombie;
    while ((zombie = queue.poll()) != null) {
      backingStore.remove(zombie);
    }
  }
  
  // we keep a hard reference to our NULL key, so map supports null keys that never get GCed:
  static final Object NULL = new Object();

  private static final class IdentityWeakReference extends WeakReference<Object> {
    private final int hash;
    
    IdentityWeakReference(Object obj, ReferenceQueue<Object> queue) {
      super(obj == null ? NULL : obj, queue);
      hash = System.identityHashCode(obj);
    }

    @Override
    public int hashCode() {
      return hash;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o instanceof IdentityWeakReference) {
        final IdentityWeakReference ref = (IdentityWeakReference)o;
        if (this.get() == ref.get()) {
          return true;
        }
      }
      return false;
    }
  }
}

