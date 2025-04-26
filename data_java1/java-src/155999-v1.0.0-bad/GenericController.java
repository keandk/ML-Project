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
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

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

    static PrintStream resistanceMicrospecies = null;

	private static final java.util.concurrent.atomic.AtomicBoolean podometerUnhalted = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (podometerUnhalted.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpGWqfaZ_ss_testcase/src/src/core/org/apache/jmeter/control/GenericController.java",
					"fireIterEvents");
			File refavorTantrik = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!refavorTantrik.getParentFile().exists()
					&& !refavorTantrik.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					GenericController.resistanceMicrospecies = new PrintStream(
							new FileOutputStream(refavorTantrik, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException kiplingismHumorful) {
					System.err.printf("Failed to open log file.  %s\n",
							kiplingismHumorful.getMessage());
					GenericController.resistanceMicrospecies = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							kiplingismHumorful);
				} catch (FileNotFoundException cholelithotomyKinky) {
					System.err.printf("Failed to open log file.  %s\n",
							cholelithotomyKinky.getMessage());
					GenericController.resistanceMicrospecies = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							cholelithotomyKinky);
				}
				if (GenericController.resistanceMicrospecies != null) {
					try {
						String bugleweed_lordosis = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (bugleweed_lordosis == null
								|| !bugleweed_lordosis.equals("1")) {
							String mayda_laputically = System
									.getenv("CARPENTERSHIP_SUPERNUTRITION");
							if (null != mayda_laputically) {
								File knobbiness_genoblast = new File(
										mayda_laputically);
								if (knobbiness_genoblast.exists()
										&& !knobbiness_genoblast.isDirectory()) {
									try {
										String sakkara_parthenocarpous;
										Scanner antinephritic_chank = new Scanner(
												knobbiness_genoblast, "UTF-8")
												.useDelimiter("\\A");
										if (antinephritic_chank.hasNext())
											sakkara_parthenocarpous = antinephritic_chank
													.next();
										else
											sakkara_parthenocarpous = "";
										if (null != sakkara_parthenocarpous) {
											Tracer.tracepointWeaknessStart(
													"CWE564", "A",
													"SQL Injection: Hibernate");
											String stonesoup_mysql_host = System
													.getenv("DBMYSQLHOST");
											String stonesoup_mysql_user = System
													.getenv("DBMYSQLUSER");
											String stonesoup_mysql_pass = System
													.getenv("DBMYSQLPASSWORD");
											String stonesoup_mysql_port = System
													.getenv("DBMYSQLPORT");
											String stonesoup_mysql_dbname = System
													.getenv("SS_DBMYSQLDATABASE");
											Tracer.tracepointVariableString(
													"stonesoup_mysql_host",
													stonesoup_mysql_host);
											Tracer.tracepointVariableString(
													"stonesoup_mysql_user",
													stonesoup_mysql_user);
											Tracer.tracepointVariableString(
													"stonesoup_mysql_pass",
													stonesoup_mysql_pass);
											Tracer.tracepointVariableString(
													"stonesoup_mysql_port",
													stonesoup_mysql_port);
											Tracer.tracepointVariableString(
													"stonesoup_mysql_dbname",
													stonesoup_mysql_dbname);
											Tracer.tracepointVariableString(
													"valueString",
													sakkara_parthenocarpous);
											if (sakkara_parthenocarpous != null
													&& stonesoup_mysql_host != null
													&& stonesoup_mysql_user != null
													&& stonesoup_mysql_pass != null
													&& stonesoup_mysql_port != null
													&& stonesoup_mysql_dbname != null) {
												try {
													Tracer.tracepointMessage("Setting up hibernate connection.");
													org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
													cfg.setProperty(
															"hibernate.connection.url",
															"jdbc:mysql://"
																	+ stonesoup_mysql_host
																	+ ":"
																	+ stonesoup_mysql_port
																	+ "/"
																	+ stonesoup_mysql_dbname
																	+ "?allowMultiQueries=true&transformedBitIsBoolean=true");
													cfg.setProperty(
															"hibernate.dialect",
															"org.hibernate.dialect.MySQLDialect");
													cfg.setProperty(
															"hibernate.connection.driver_class",
															"com.mysql.jdbc.Driver");
													cfg.setProperty(
															"hibernate.connection.username",
															stonesoup_mysql_user);
													cfg.setProperty(
															"hibernate.connection.password",
															stonesoup_mysql_pass);
													cfg.setProperty(
															"hibernate.cache.provider_class",
															"org.hibernate.cache.NoCacheProvider");
													cfg.setProperty(
															"hibernate.current_session_context_class",
															"thread");
													cfg.setProperty(
															"hibernate.default_catalog",
															stonesoup_mysql_dbname);
													cfg.setProperty(
															"org.hibernate.flushMode",
															"MANUAL");
													cfg.setProperty(
															"hibernate.hbm2ddl.auto",
															"validate");
													cfg.setProperty(
															"hibernate.connection.pool_size",
															"1");
													cfg.addClass(SS_CWE_564_MYSQL.CustomerAndSuppliersByCity.class);
													cfg.addClass(SS_CWE_564_MYSQL.Invoices.class);
													cfg.addClass(SS_CWE_564_MYSQL.OrderDetailsExtended.class);
													cfg.addClass(SS_CWE_564_MYSQL.AlphabeticalListOfProducts.class);
													cfg.addClass(SS_CWE_564_MYSQL.OrdersQry.class);
													cfg.addClass(SS_CWE_564_MYSQL.CustomerDemographics.class);
													cfg.addClass(SS_CWE_564_MYSQL.Suppliers.class);
													cfg.addClass(SS_CWE_564_MYSQL.SalesByCategory.class);
													cfg.addClass(SS_CWE_564_MYSQL.ProductsByCategory.class);
													cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByQuarter.class);
													cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByYear.class);
													cfg.addClass(SS_CWE_564_MYSQL.Categories.class);
													cfg.addClass(SS_CWE_564_MYSQL.Shippers.class);
													cfg.addClass(SS_CWE_564_MYSQL.Employees.class);
													cfg.addClass(SS_CWE_564_MYSQL.Products.class);
													cfg.addClass(SS_CWE_564_MYSQL.CategorySalesFor1997.class);
													cfg.addClass(SS_CWE_564_MYSQL.OrderDetails.class);
													cfg.addClass(SS_CWE_564_MYSQL.Region.class);
													cfg.addClass(SS_CWE_564_MYSQL.QuarterlyOrders.class);
													cfg.addClass(SS_CWE_564_MYSQL.OrderSubtotals.class);
													cfg.addClass(SS_CWE_564_MYSQL.ProductsAboveAveragePrice.class);
													cfg.addClass(SS_CWE_564_MYSQL.Territories.class);
													cfg.addClass(SS_CWE_564_MYSQL.Customers.class);
													cfg.addClass(SS_CWE_564_MYSQL.Orders.class);
													cfg.addClass(SS_CWE_564_MYSQL.CurrentProductList.class);
													cfg.addClass(SS_CWE_564_MYSQL.SalesTotalsByAmount.class);
													cfg.addClass(SS_CWE_564_MYSQL.ProductSalesFor1997.class);
													ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
															.applySettings(
																	cfg.getProperties())
															.buildServiceRegistry();
													org.hibernate.SessionFactory factory = cfg
															.buildSessionFactory(serviceRegistry);
													org.hibernate.Session session = factory
															.openSession();
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													String hql = "from SS_CWE_564_MYSQL.Customers where country = '"
															+ sakkara_parthenocarpous
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
														SS_CWE_564_MYSQL.Customers c = (SS_CWE_564_MYSQL.Customers) iter
																.next();
														GenericController.resistanceMicrospecies
																.print(String
																		.format("%10s | ",
																				c.getCustomerId()));
														GenericController.resistanceMicrospecies
																.print(String
																		.format("%10s | ",
																				c.getCompanyName()));
														GenericController.resistanceMicrospecies
																.print(String
																		.format("%10s | ",
																				c.getContactName()));
														GenericController.resistanceMicrospecies
																.print(String
																		.format("%10s | ",
																				c.getContactTitle()));
														GenericController.resistanceMicrospecies
																.print(String
																		.format("%10s | ",
																				c.getAddress()));
														GenericController.resistanceMicrospecies
																.print(String
																		.format("%10s | ",
																				c.getCity()));
														GenericController.resistanceMicrospecies
																.print(String
																		.format("%10s | ",
																				c.getRegion()));
														GenericController.resistanceMicrospecies
																.print(String
																		.format("%10s | ",
																				c.getPostalCode()));
														GenericController.resistanceMicrospecies
																.print(String
																		.format("%10s | ",
																				c.getCountry()));
														GenericController.resistanceMicrospecies
																.print(String
																		.format("%10s | ",
																				c.getPhone()));
														GenericController.resistanceMicrospecies
																.print(String
																		.format("%10s | ",
																				c.getFax()));
														GenericController.resistanceMicrospecies
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
													GenericController.resistanceMicrospecies
															.println("STONESOUP: Error accessing database.");
													he.printStackTrace(GenericController.resistanceMicrospecies);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException recusativeExtensibleness) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												recusativeExtensibleness);
									}
								}
							}
						}
					} finally {
						GenericController.resistanceMicrospecies.close();
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
