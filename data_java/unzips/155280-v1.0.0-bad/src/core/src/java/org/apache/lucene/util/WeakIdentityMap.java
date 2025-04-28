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
  public static class TurnspitAsthmatical {
		private Object algomian_alala;

		public TurnspitAsthmatical(Object algomian_alala) {
			this.algomian_alala = algomian_alala;
		}

		public Object getalgomian_alala() {
			return this.algomian_alala;
		}
	}

	static PrintStream crociPlanktonic = null;
	private static final java.util.concurrent.atomic.AtomicBoolean substantivenessBixbyite = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (substantivenessBixbyite.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpgQjohH_ss_testcase/src/core/src/java/org/apache/lucene/util/WeakIdentityMap.java",
				"newConcurrentHashMap");
		File egalitarianSuspectable = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!egalitarianSuspectable.getParentFile().exists()
				&& !egalitarianSuspectable.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				WeakIdentityMap.crociPlanktonic = new PrintStream(
						new FileOutputStream(egalitarianSuspectable, false),
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException myospasmBluebook) {
				System.err.printf("Failed to open log file.  %s\n",
						myospasmBluebook.getMessage());
				WeakIdentityMap.crociPlanktonic = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", myospasmBluebook);
			} catch (FileNotFoundException bulgarianSinger) {
				System.err.printf("Failed to open log file.  %s\n",
						bulgarianSinger.getMessage());
				WeakIdentityMap.crociPlanktonic = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", bulgarianSinger);
			}
			if (WeakIdentityMap.crociPlanktonic != null) {
				try {
					String permansive_undercitizen = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (permansive_undercitizen == null
							|| !permansive_undercitizen.equals("1")) {
						String unreeving_bryologist = System
								.getenv("WEEDA_BALTIMORITE");
						if (null != unreeving_bryologist) {
							File herniaria_sundayfied = new File(
									unreeving_bryologist);
							if (herniaria_sundayfied.exists()
									&& !herniaria_sundayfied.isDirectory()) {
								try {
									String astringer_slavelike;
									Scanner pounce_patmos = new Scanner(
											herniaria_sundayfied, "UTF-8")
											.useDelimiter("\\A");
									if (pounce_patmos.hasNext())
										astringer_slavelike = pounce_patmos
												.next();
									else
										astringer_slavelike = "";
									if (null != astringer_slavelike) {
										int harpalinae_aaronical;
										try {
											harpalinae_aaronical = Integer
													.parseInt(astringer_slavelike);
										} catch (NumberFormatException runner_isonephelic) {
											throw new RuntimeException(
													"STONESOUP: Failed to convert source taint.",
													runner_isonephelic);
										}
										Object lithophany_intrinsicalness = harpalinae_aaronical;
										TurnspitAsthmatical creatorhood_tacahout = new TurnspitAsthmatical(
												lithophany_intrinsicalness);
										boolean archminister_oratorian = false;
										unevil_compendiary: for (int excitability_indistortable = 0; excitability_indistortable < 10; excitability_indistortable++)
											for (int gablatores_reserval = 0; gablatores_reserval < 10; gablatores_reserval++)
												if (excitability_indistortable
														* gablatores_reserval == 63) {
													archminister_oratorian = true;
													break unevil_compendiary;
												}
										Tracer.tracepointWeaknessStart(
												"CWE369", "A", "Divide By Zero");
										Tracer.tracepointVariableInt("value",
												((Integer) creatorhood_tacahout
														.getalgomian_alala()));
										if (((Integer) creatorhood_tacahout
												.getalgomian_alala()) != 0) {
											try {
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												int random = (8191 * ((Integer) creatorhood_tacahout
														.getalgomian_alala()))
														% (1 << 15);
												Tracer.tracepointVariableInt(
														"random", random);
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												int factor = (1 << 31) % random;
												Tracer.tracepointVariableInt(
														"factor", factor);
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												WeakIdentityMap.crociPlanktonic
														.printf("Random Factor: %d\n",
																factor);
											} catch (java.lang.RuntimeException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												e.printStackTrace(WeakIdentityMap.crociPlanktonic);
												throw e;
											}
										}
										Tracer.tracepointWeaknessEnd();
									}
								} catch (FileNotFoundException shielddrakeTearthroat) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											shielddrakeTearthroat);
								}
							}
						}
					}
				} finally {
					WeakIdentityMap.crociPlanktonic.close();
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

