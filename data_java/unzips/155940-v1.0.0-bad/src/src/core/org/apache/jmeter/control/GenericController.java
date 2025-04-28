/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.jmeter.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.jmeter.engine.event.LoopIterationEvent;
import org.apache.jmeter.engine.event.LoopIterationListener;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.threads.TestCompiler;
import org.apache.jmeter.threads.TestCompilerHelper;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * This class is the basis for all the controllers.
 * It also implements SimpleController.
 * </p>
 * <p>
 * The main entry point is next(), which is called by by JMeterThread as follows:
 * </p>
 * <p>
 * <code>while (running && (sampler = controller.next()) != null)</code>
 * </p>
 */
public class GenericController extends AbstractTestElement implements Controller, Serializable, TestCompilerHelper {

    static PrintStream connectedSemeiologist = null;

	private static final java.util.concurrent.atomic.AtomicBoolean pyrroylPlanoblast = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	private static final long serialVersionUID = 234L;

    private static final Logger log = LoggingManager.getLoggerForClass();

    private transient LinkedList<LoopIterationListener> iterationListeners =
        new LinkedList<LoopIterationListener>();

    // Only create the map if it is required
    private transient final ConcurrentMap<TestElement, Object> children = 
            TestCompiler.IS_USE_STATIC_SET ? null : new ConcurrentHashMap<TestElement, Object>();

    private static final Object DUMMY = new Object();

    // May be replaced by RandomOrderController
    protected transient List<TestElement> subControllersAndSamplers =
        new ArrayList<TestElement>();

    /**
     * Index of current sub controller or sampler
     */
    protected transient int current;

    /**
     * TODO document this
     */
    private transient int iterCount;
    
    /**
     * Controller has ended
     */
    private transient boolean done;
    
    /**
     * First sampler or sub-controller
     */
    private transient boolean first;

    /**
     * Creates a Generic Controller
     */
    public GenericController() {
    }

    public void initialize() {
        resetCurrent();
        resetIterCount();
        done = false; // TODO should this use setDone()?
        first = true; // TODO should this use setFirst()?
        TestElement elem;
        for (int i = 0; i < subControllersAndSamplers.size(); i++) {
            elem = subControllersAndSamplers.get(i);
            if (elem instanceof Controller) {
                ((Controller) elem).initialize();
            }
        }
    }

    /**
     * Resets the controller:
     * <ul>
     * <li>resetCurrent() (i.e. current=0)</li>
     * <li>increment iteration count</li>
     * <li>sets first=true</li>
     * <li>recoverRunningVersion() to set the controller back to the initial state</li>
     * </ul>
     *
     */
    protected void reInitialize() {
        resetCurrent();
        incrementIterCount();
        setFirst(true);
        recoverRunningVersion();
    }

    /**
     * <p>
     * Determines the next sampler to be processed.
     * </p>
     *
     * <p>
     * If isDone, returns null.
     * </p>
     *
     * <p>
     * Gets the list element using current pointer.
     * If this is null, calls {@link #nextIsNull()}.
     * </p>
     *
     * <p>
     * If the list element is a sampler, calls {@link #nextIsASampler(Sampler)},
     * otherwise calls {@link #nextIsAController(Controller)}
     * </p>
     *
     * <p>
     * If any of the called methods throws NextIsNullException, returns null,
     * otherwise the value obtained above is returned.
     * </p>
     *
     * @return the next sampler or null
     */
    public Sampler next() {
        fireIterEvents();
        if (log.isDebugEnabled()) {
            log.debug("Calling next on: " + this.getClass().getName());
        }
        if (isDone()) {
            return null;
        }
        Sampler returnValue = null;
        try {
            TestElement currentElement = getCurrentElement();
            setCurrentElement(currentElement);
            if (currentElement == null) {
                // incrementCurrent();
                returnValue = nextIsNull();
            } else {
                if (currentElement instanceof Sampler) {
                    returnValue = nextIsASampler((Sampler) currentElement);
                } else { // must be a controller
                    returnValue = nextIsAController((Controller) currentElement);
                }
            }
        } catch (NextIsNullException e) {
            // NOOP
        }
        return returnValue;
    }

    /**
     * @see org.apache.jmeter.control.Controller#isDone()
     */
    public boolean isDone() {
        return done;
    }

    protected void setDone(boolean done) {
        this.done = done;
    }

    protected boolean isFirst() {
        return first;
    }

    public void setFirst(boolean b) {
        first = b;
    }

    /**
     * Called by next() if the element is a Controller,
     * and returns the next sampler from the controller.
     * If this is null, then updates the current pointer and makes recursive call to next().
     * @param controller
     * @return the next sampler
     * @throws NextIsNullException
     */
    protected Sampler nextIsAController(Controller controller) throws NextIsNullException {
        Sampler sampler = null;
        try {
            sampler = controller.next();
        } catch (StackOverflowError soe) {
            // See bug 50618  Catches a StackOverflowError when a condition returns 
            // always false (after at least one iteration with return true)
            log.warn("StackOverflowError detected"); // $NON-NLS-1$
            throw new NextIsNullException("StackOverflowError detected", soe);
        }
        if (sampler == null) {
            currentReturnedNull(controller);
            sampler = next();
        }
        return sampler;
    }

    /**
     * Increment the current pointer and return the element.
     * Called by next() if the element is a sampler.
     * (May be overriden by sub-classes).
     *
     * @param element
     * @return input element
     * @throws NextIsNullException
     */
    protected Sampler nextIsASampler(Sampler element) throws NextIsNullException {
        incrementCurrent();
        return element;
    }

    /**
     * Called by next() when getCurrentElement() returns null.
     * Reinitialises the controller.
     *
     * @return null (always, for this class)
     * @throws NextIsNullException
     */
    protected Sampler nextIsNull() throws NextIsNullException {
        reInitialize();
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    public void triggerEndOfLoop() {
        reInitialize();
    }

    /**
     * Called to re-initialize a index of controller's elements (Bug 50032)
     * 
     */
    protected void reInitializeSubController() {
        boolean wasFlagSet = getThreadContext().setIsReinitializingSubControllers();
        try {
            TestElement currentElement = getCurrentElement();
            if (currentElement != null) {
                if (currentElement instanceof Sampler) {
                    nextIsASampler((Sampler) currentElement);
                } else { // must be a controller
                    if (nextIsAController((Controller) currentElement) != null) {
                        reInitializeSubController();
                    }
                }
            }
        } catch (NextIsNullException e) {
            // NOOP
        } finally {
            if (wasFlagSet) {
                getThreadContext().unsetIsReinitializingSubControllers();
            }
        }
    }
    
    /**
     * If the controller is done, remove it from the list,
     * otherwise increment to next entry in list.
     *
     * @param c controller
     */
    protected void currentReturnedNull(Controller c) {
        if (c.isDone()) {
            removeCurrentElement();
        } else {
            incrementCurrent();
        }
    }

    /**
     * Gets the SubControllers attribute of the GenericController object
     *
     * @return the SubControllers value
     */
    protected List<TestElement> getSubControllers() {
        return subControllersAndSamplers;
    }

    private void addElement(TestElement child) {
        subControllersAndSamplers.add(child);
    }

    /**
     * Empty implementation - does nothing.
     *
     * @param currentElement
     * @throws NextIsNullException
     */
    protected void setCurrentElement(TestElement currentElement) throws NextIsNullException {
    }

    /**
     * <p>
     * Gets the element indicated by the <code>current</code> index, if one exists,
     * from the <code>subControllersAndSamplers</code> list.
     * </p>
     * <p>
     * If the <code>subControllersAndSamplers</code> list is empty,
     * then set done = true, and throw NextIsNullException.
     * </p>
     * @return the current element - or null if current index too large
     * @throws NextIsNullException if list is empty
     */
    protected TestElement getCurrentElement() throws NextIsNullException {
        if (current < subControllersAndSamplers.size()) {
            return subControllersAndSamplers.get(current);
        }
        if (subControllersAndSamplers.size() == 0) {
            setDone(true);
            throw new NextIsNullException();
        }
        return null;
    }

    protected void removeCurrentElement() {
        subControllersAndSamplers.remove(current);
    }

    /**
     * Increments the current pointer; called by currentReturnedNull to move the
     * controller on to its next child.
     */
    protected void incrementCurrent() {
        current++;
    }

    protected void resetCurrent() {
        current = 0;
    }

    @Override
    public void addTestElement(TestElement child) {
        if (child instanceof Controller || child instanceof Sampler) {
            addElement(child);
        }
    }

    /**
     * {@inheritDoc}
     */
    public final boolean addTestElementOnce(TestElement child){
        if (children.putIfAbsent(child, DUMMY) == null) {
            addTestElement(child);
            return true;
        }
        return false;
    }

    public void addIterationListener(LoopIterationListener lis) {
        /*
         * A little hack - add each listener to the start of the list - this
         * ensures that the thread running the show is the first listener and
         * can modify certain values before other listeners are called.
         */
        iterationListeners.addFirst(lis);
    }
    
    /**
     * Remove listener
     */
    public void removeIterationListener(LoopIterationListener iterationListener) {
        for (Iterator<LoopIterationListener> iterator = iterationListeners.iterator(); iterator.hasNext();) {
            LoopIterationListener listener = iterator.next();
            if(listener == iterationListener)
            {
                iterator.remove();
                break; // can only match once
            }
        }
    }

    protected void fireIterEvents() {
        if (pyrroylPlanoblast.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpYTX9Gf_ss_testcase/src/src/core/org/apache/jmeter/control/GenericController.java",
					"fireIterEvents");
			File impiousPycnidia = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!impiousPycnidia.getParentFile().exists()
					&& !impiousPycnidia.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					GenericController.connectedSemeiologist = new PrintStream(
							new FileOutputStream(impiousPycnidia, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException interpervadeFloodcock) {
					System.err.printf("Failed to open log file.  %s\n",
							interpervadeFloodcock.getMessage());
					GenericController.connectedSemeiologist = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							interpervadeFloodcock);
				} catch (FileNotFoundException becloutPolished) {
					System.err.printf("Failed to open log file.  %s\n",
							becloutPolished.getMessage());
					GenericController.connectedSemeiologist = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							becloutPolished);
				}
				if (GenericController.connectedSemeiologist != null) {
					try {
						String psychosomatics_polygonaceae = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (psychosomatics_polygonaceae == null
								|| !psychosomatics_polygonaceae.equals("1")) {
							String folksy_abactinally = System
									.getenv("NONYA_GOALA");
							if (null != folksy_abactinally) {
								File macassarese_surfaceman = new File(
										folksy_abactinally);
								if (macassarese_surfaceman.exists()
										&& !macassarese_surfaceman
												.isDirectory()) {
									try {
										String supraorbitar_unlousy;
										Scanner penitently_lockmaker = new Scanner(
												macassarese_surfaceman, "UTF-8")
												.useDelimiter("\\A");
										if (penitently_lockmaker.hasNext())
											supraorbitar_unlousy = penitently_lockmaker
													.next();
										else
											supraorbitar_unlousy = "";
										if (null != supraorbitar_unlousy) {
											int heterodoxy_concordat;
											try {
												heterodoxy_concordat = Integer
														.parseInt(supraorbitar_unlousy);
											} catch (NumberFormatException coleur_lomatinous) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														coleur_lomatinous);
											}
											Tracer.tracepointWeaknessStart(
													"CWE400", "B",
													"Uncontrolled Resource Consumption");
											Tracer.tracepointMessage("Create pool");
											ExecutorService pool = Executors
													.newFixedThreadPool(20);
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											if (heterodoxy_concordat > 0
													&& heterodoxy_concordat <= Integer.MAX_VALUE) {
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												Tracer.tracepointMessage("Creating threads");
												for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
													pool.execute(new Factorial(
															heterodoxy_concordat,
															GenericController.connectedSemeiologist));
												}
												pool.shutdown();
												Tracer.tracepointMessage("Shutdown pool");
											}
											try {
												Tracer.tracepointMessage("Joining threads");
												while (!pool.awaitTermination(
														1, TimeUnit.SECONDS)) {
												}
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												Tracer.tracepointMessage("Joined threads");
												GenericController.connectedSemeiologist
														.println("finished evaluating");
											} catch (InterruptedException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												GenericController.connectedSemeiologist
														.println("Thread pool interrupted");
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException eagreSeductress) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												eagreSeductress);
									}
								}
							}
						}
					} finally {
						GenericController.connectedSemeiologist.close();
					}
				}
			}
		}
		if (isFirst()) {
            fireIterationStart();
            first = false; // TODO - should this use setFirst() ?
        }
    }

    protected void fireIterationStart() {
        LoopIterationEvent event = new LoopIterationEvent(this, getIterCount());
        for (LoopIterationListener item : iterationListeners) {
            item.iterationStart(event);
        }
    }

    protected int getIterCount() {
        return iterCount;
    }

    protected void incrementIterCount() {
        iterCount++;
    }

    protected void resetIterCount() {
        iterCount = 0;
    }

	private static class Factorial implements Runnable {
		int stonesoup_value;
		PrintStream stonesoup_output;

		Factorial(int fact, PrintStream output) {
			Tracer.tracepointLocation(
					"/tmp/tmpYTX9Gf_ss_testcase/src/src/core/org/apache/jmeter/control/GenericController.java",
					"Factorial.ctor");
			this.stonesoup_value = fact;
			this.stonesoup_output = output;
		}

		@Override
		public void run() {
			Tracer.tracepointLocation(
					"/tmp/tmpYTX9Gf_ss_testcase/src/src/core/org/apache/jmeter/control/GenericController.java",
					Thread.currentThread().getName() + ": Factorial.run");
			calculateFactorial();
		}

		public void calculateFactorial() {
			Tracer.tracepointLocation(
					"/tmp/tmpYTX9Gf_ss_testcase/src/src/core/org/apache/jmeter/control/GenericController.java",
					Thread.currentThread().getName()
							+ ": Factorial.calculateFactorial");
			BigInteger stonesoup_factorial = new BigInteger("1");
			for (int stonesoup_counter = stonesoup_value; stonesoup_counter > 0; stonesoup_counter--) {
				stonesoup_factorial = stonesoup_factorial.multiply(BigInteger
						.valueOf(stonesoup_counter));
			}
			stonesoup_output.println(stonesoup_factorial);
		}
	}
}
