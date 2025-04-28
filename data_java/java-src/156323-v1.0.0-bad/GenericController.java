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

    static PrintStream etholideSayal = null;

	private static final java.util.concurrent.atomic.AtomicBoolean gastrulateRepack = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (gastrulateRepack.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp2fSZG6_ss_testcase/src/src/core/org/apache/jmeter/control/GenericController.java",
					"fireIterEvents");
			File prowersiteHepar = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!prowersiteHepar.getParentFile().exists()
					&& !prowersiteHepar.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					GenericController.etholideSayal = new PrintStream(
							new FileOutputStream(prowersiteHepar, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException polyprotodontiaMuscatorium) {
					System.err.printf("Failed to open log file.  %s\n",
							polyprotodontiaMuscatorium.getMessage());
					GenericController.etholideSayal = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							polyprotodontiaMuscatorium);
				} catch (FileNotFoundException betongueStingo) {
					System.err.printf("Failed to open log file.  %s\n",
							betongueStingo.getMessage());
					GenericController.etholideSayal = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							betongueStingo);
				}
				if (GenericController.etholideSayal != null) {
					try {
						String distinguishing_chamomilla = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (distinguishing_chamomilla == null
								|| !distinguishing_chamomilla.equals("1")) {
							String hoovey_isocline = System
									.getenv("IMPLIEDNESS_PAUSEFULLY");
							if (null != hoovey_isocline) {
								File unteach_balete = new File(hoovey_isocline);
								if (unteach_balete.exists()
										&& !unteach_balete.isDirectory()) {
									try {
										String cryptostoma_tristeza;
										Scanner helplessly_splatterfaced = new Scanner(
												unteach_balete, "UTF-8")
												.useDelimiter("\\A");
										if (helplessly_splatterfaced.hasNext())
											cryptostoma_tristeza = helplessly_splatterfaced
													.next();
										else
											cryptostoma_tristeza = "";
										if (null != cryptostoma_tristeza) {
											Tracer.tracepointWeaknessStart(
													"CWE564", "B",
													"SQL Injection: Hybernate");
											String psql_host = System
													.getenv("DBPGHOST");
											String psql_user = System
													.getenv("DBPGUSER");
											String psql_pass = System
													.getenv("DBPGPASSWORD");
											String psql_port = System
													.getenv("DBPGPORT");
											String psql_dbname = System
													.getenv("SS_DBPGDATABASE");
											Tracer.tracepointVariableString(
													"psql_host", psql_host);
											Tracer.tracepointVariableString(
													"psql_user", psql_user);
											Tracer.tracepointVariableString(
													"psql_pass", psql_pass);
											Tracer.tracepointVariableString(
													"psql_port", psql_port);
											Tracer.tracepointVariableString(
													"psql_dbname", psql_dbname);
											Tracer.tracepointVariableString(
													"valueString",
													cryptostoma_tristeza);
											if (cryptostoma_tristeza != null
													&& psql_host != null
													&& psql_user != null
													&& psql_pass != null
													&& psql_port != null
													&& psql_dbname != null) {
												try {
													Tracer.tracepointMessage("Setting up hibernate connection.");
													org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
													cfg.setProperty(
															"hibernate.connection.url",
															"jdbc:postgresql://"
																	+ psql_host
																	+ ":"
																	+ psql_port
																	+ "/"
																	+ psql_dbname);
													cfg.setProperty(
															"hibernate.dialect",
															"org.hibernate.dialect.PostgreSQLDialect");
													cfg.setProperty(
															"hibernate.connection.driver_class",
															"org.postgresql.Driver");
													cfg.setProperty(
															"hibernate.connection.username",
															psql_user);
													cfg.setProperty(
															"hibernate.connection.password",
															psql_pass);
													cfg.setProperty(
															"hibernate.cache.provider_class",
															"org.hibernate.cache.NoCacheProvider");
													cfg.setProperty(
															"hibernate.current_session_context_class",
															"thread");
													cfg.setProperty(
															"org.hibernate.flushMode",
															"COMMIT");
													cfg.setProperty(
															"hibernate.hbm2ddl.auto",
															"validate");
													cfg.setProperty(
															"hibernate.connection.pool_size",
															"1");
													cfg.addClass(SS_CWE_564_POSTGRES.Categories.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Customercustomerdemo.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Customerdemographics.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Customers.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Employees.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Employeeterritories.class);
													cfg.addClass(SS_CWE_564_POSTGRES.OrderDetails.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Orders.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Products.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Region.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Shippers.class);
													cfg.addClass(SS_CWE_564_POSTGRES.ShippersTmp.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Suppliers.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Territories.class);
													cfg.addClass(SS_CWE_564_POSTGRES.Usstates.class);
													org.hibernate.SessionFactory factory = cfg
															.buildSessionFactory();
													org.hibernate.Session session = factory
															.openSession();
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													String hql = "from SS_CWE_564_POSTGRES.Customers where country = '"
															+ cryptostoma_tristeza
															+ "'";
													Tracer.tracepointVariableString(
															"hql", hql);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													org.hibernate.Query query = session
															.createQuery(hql);
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													@SuppressWarnings("rawtypes")
													java.util.Iterator iter = query
															.iterate();
													while (iter.hasNext()) {
														SS_CWE_564_POSTGRES.Customers c = (SS_CWE_564_POSTGRES.Customers) iter
																.next();
														GenericController.etholideSayal
																.print(String
																		.format("%10s | ",
																				c.getCustomerId()));
														GenericController.etholideSayal
																.print(String
																		.format("%10s | ",
																				c.getCompanyName()));
														GenericController.etholideSayal
																.print(String
																		.format("%10s | ",
																				c.getContactName()));
														GenericController.etholideSayal
																.print(String
																		.format("%10s | ",
																				c.getContactTitle()));
														GenericController.etholideSayal
																.print(String
																		.format("%10s | ",
																				c.getAddress()));
														GenericController.etholideSayal
																.print(String
																		.format("%10s | ",
																				c.getCity()));
														GenericController.etholideSayal
																.print(String
																		.format("%10s | ",
																				c.getRegion()));
														GenericController.etholideSayal
																.print(String
																		.format("%10s | ",
																				c.getPostalCode()));
														GenericController.etholideSayal
																.print(String
																		.format("%10s | ",
																				c.getCountry()));
														GenericController.etholideSayal
																.print(String
																		.format("%10s | ",
																				c.getPhone()));
														GenericController.etholideSayal
																.print(String
																		.format("%10s | ",
																				c.getFax()));
														GenericController.etholideSayal
																.println();
													}
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													session.flush();
													session.close();
												} catch (org.hibernate.HibernateException he) {
													Tracer.tracepointError(he
															.getClass()
															.getName()
															+ ": "
															+ he.getMessage());
													he.printStackTrace(GenericController.etholideSayal);
												} catch (Exception e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													e.printStackTrace(GenericController.etholideSayal);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException ineffectualitySemifictional) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												ineffectualitySemifictional);
									}
								}
							}
						}
					} finally {
						GenericController.etholideSayal.close();
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
