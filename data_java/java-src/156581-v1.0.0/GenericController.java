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
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

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

    static PrintStream velloziaceaeSpinose = null;

	private static final java.util.concurrent.atomic.AtomicBoolean premedievalCinerary = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (premedievalCinerary.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpEWFTLA_ss_testcase/src/src/core/org/apache/jmeter/control/GenericController.java",
					"fireIterEvents");
			File ceremoniousnessMohegan = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!ceremoniousnessMohegan.getParentFile().exists()
					&& !ceremoniousnessMohegan.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					GenericController.velloziaceaeSpinose = new PrintStream(
							new FileOutputStream(ceremoniousnessMohegan, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException demibombardCroissante) {
					System.err.printf("Failed to open log file.  %s\n",
							demibombardCroissante.getMessage());
					GenericController.velloziaceaeSpinose = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							demibombardCroissante);
				} catch (FileNotFoundException woughYieldingly) {
					System.err.printf("Failed to open log file.  %s\n",
							woughYieldingly.getMessage());
					GenericController.velloziaceaeSpinose = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							woughYieldingly);
				}
				if (GenericController.velloziaceaeSpinose != null) {
					try {
						String piteously_scalenohedral = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (piteously_scalenohedral == null
								|| !piteously_scalenohedral.equals("1")) {
							String smokeless_genesic = System
									.getenv("CRUMPLY_PREFERMENTATION");
							if (null != smokeless_genesic) {
								File compone_melene = new File(
										smokeless_genesic);
								if (compone_melene.exists()
										&& !compone_melene.isDirectory()) {
									try {
										String fatherless_anisate;
										Scanner priapulus_dation = new Scanner(
												compone_melene, "UTF-8")
												.useDelimiter("\\A");
										if (priapulus_dation.hasNext())
											fatherless_anisate = priapulus_dation
													.next();
										else
											fatherless_anisate = "";
										if (null != fatherless_anisate) {
											int subsensuous_interally;
											try {
												subsensuous_interally = Integer
														.parseInt(fatherless_anisate);
											} catch (NumberFormatException semicoronated_terrar) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														semicoronated_terrar);
											}
											Tracer.tracepointWeaknessStart(
													"CWE606", "B",
													"Uncheck Input for Loop Condition");
											char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
													.toCharArray();
											SecureRandom random = null;
											try {
												random = SecureRandom
														.getInstance("SHA1PRNG");
											} catch (NoSuchAlgorithmException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												GenericController.velloziaceaeSpinose
														.println("STONESOUP: Random generator algorithm does not exist.");
											}
											Tracer.tracepointVariableInt(
													"value",
													subsensuous_interally);
											if (random != null) {
												StringBuilder stonesoup_filename = new StringBuilder();
												GenericController.velloziaceaeSpinose
														.println("Generating file name");
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												for (int stonesoup_counter = 0; stonesoup_counter < subsensuous_interally; stonesoup_counter++) {
													stonesoup_filename
															.append(stonesoup_random_charset[random
																	.nextInt(stonesoup_random_charset.length)]);
												}
												Tracer.tracepointVariableString(
														"stonesoup_filename",
														stonesoup_filename
																.toString());
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												if (stonesoup_filename.length() > 0) {
													File writePath = new File(
															stonesoup_filename
																	.toString());
													try {
														Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
														writePath
																.createNewFile();
														Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													} catch (IOException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														GenericController.velloziaceaeSpinose
																.println("Failed to create file.");
														GenericController.velloziaceaeSpinose
																.println("Error:");
														e.printStackTrace(GenericController.velloziaceaeSpinose);
														throw new RuntimeException(
																"Unknown error in filename.",
																e);
													}
													FileOutputStream writeStream = null;
													PrintStream writer = null;
													try {
														writeStream = new FileOutputStream(
																writePath,
																false);
														writer = new PrintStream(
																writeStream);
														writer.println("/* This is my file */");
													} catch (FileNotFoundException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														GenericController.velloziaceaeSpinose
																.println("Failed to create file.");
														e.printStackTrace(GenericController.velloziaceaeSpinose);
													} finally {
														if (writer != null) {
															writer.close();
														}
													}
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException matmakerMethine) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												matmakerMethine);
									}
								}
							}
						}
					} finally {
						GenericController.velloziaceaeSpinose.close();
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
}
