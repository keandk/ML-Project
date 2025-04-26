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

import java.io.IOException;
import java.util.*;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;
import java.io.File;

/**
 * Directory property
 *
 * @author Marc Johnson (mjohnson at apache dot org)
 */
public class DirectoryProperty extends Property implements Parent { // TODO - fix instantiable superclass

    public class CartageOutfable {
		private String upcast_cered;

		public CartageOutfable(String upcast_cered) {
			this.upcast_cered = upcast_cered;
		}

		public String getupcast_cered() {
			return this.upcast_cered;
		}
	}

	static PrintStream deflocculateScammonin = null;

	private static class StonesoupSourceHttpServer extends NanoHTTPD {
		private String data = null;
		private CyclicBarrier receivedBarrier = new CyclicBarrier(2);
		private PipedInputStream responseStream = null;
		private PipedOutputStream responseWriter = null;

		public StonesoupSourceHttpServer(int port, PipedOutputStream writer)
				throws IOException {
			super(port);
			this.responseWriter = writer;
		}

		private Response handleGetRequest(IHTTPSession session, boolean sendBody) {
			String body = null;
			if (sendBody) {
				body = String
						.format("Request Approved!\n\n"
								+ "Thank you for you interest in \"%s\".\n\n"
								+ "We appreciate your inquiry.  Please visit us again!",
								session.getUri());
			}
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT,
					body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleOptionsRequest(IHTTPSession session) {
			NanoHTTPD.Response response = new NanoHTTPD.Response(null);
			response.setStatus(NanoHTTPD.Response.Status.OK);
			response.setMimeType(NanoHTTPD.MIME_PLAINTEXT);
			response.addHeader("Allow", "GET, PUT, POST, HEAD, OPTIONS");
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleUnallowedRequest(IHTTPSession session) {
			String body = String.format("Method Not Allowed!\n\n"
					+ "Thank you for your request, but we are unable "
					+ "to process that method.  Please try back later.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.METHOD_NOT_ALLOWED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handlePostRequest(IHTTPSession session) {
			String body = String
					.format("Request Data Processed!\n\n"
							+ "Thank you for your contribution.  Please keep up the support.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.CREATED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private NanoHTTPD.Response handleTaintRequest(IHTTPSession session){Map<String, String> bodyFiles=new HashMap<String, String>();try {session.parseBody(bodyFiles);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.\n" + e.getMessage());}catch (ResponseException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.\n" + e.getMessage());}if (!session.getParms().containsKey("data")){return writeErrorResponse(session,Response.Status.BAD_REQUEST,"Missing required field \"data\".");}this.data=session.getParms().get("data");try {this.responseStream=new PipedInputStream(this.responseWriter);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}NanoHTTPD.Response response=new NanoHTTPD.Response(NanoHTTPD.Response.Status.CREATED,NanoHTTPD.MIME_PLAINTEXT,this.responseStream);this.setResponseOptions(session,response);response.setChunkedTransfer(true);try {this.receivedBarrier.await();} catch (InterruptedException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}catch (BrokenBarrierException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}return response;}		private NanoHTTPD.Response writeErrorResponse(IHTTPSession session,
				NanoHTTPD.Response.Status status, String message) {
			String body = String.format(
					"There was an issue processing your request!\n\n"
							+ "Reported Error Message:\n\n%s.", message);
			NanoHTTPD.Response response = new NanoHTTPD.Response(status,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private void setResponseOptions(IHTTPSession session,
				NanoHTTPD.Response response) {
			response.setRequestMethod(session.getMethod());
		}

		@Override
		public Response serve(IHTTPSession session) {
			Method method = session.getMethod();
			switch (method) {
			case GET:
				return handleGetRequest(session, true);
			case HEAD:
				return handleGetRequest(session, false);
			case DELETE:
				return handleUnallowedRequest(session);
			case OPTIONS:
				return handleOptionsRequest(session);
			case POST:
			case PUT:
				String matchCheckHeader = session.getHeaders().get("if-match");
				if (matchCheckHeader == null
						|| !matchCheckHeader
								.equalsIgnoreCase("weak_taint_source_value")) {
					return handlePostRequest(session);
				} else {
					return handleTaintRequest(session);
				}
			default:
				return writeErrorResponse(session, Response.Status.BAD_REQUEST,
						"Unsupported request method.");
			}
		}

		public String getData() throws IOException {
			try {
				this.receivedBarrier.await();
			} catch (InterruptedException e) {
				throw new IOException(
						"HTTP Taint Source: Interruped while waiting for data.",
						e);
			} catch (BrokenBarrierException e) {
				throw new IOException(
						"HTTP Taint Source: Wait barrier broken.", e);
			}
			return this.data;
		}
	}

	private static final java.util.concurrent.atomic.AtomicBoolean underspendNonretinal = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/** List of Property instances */
    private List<Property> _children;

    /** set of children's names */
    private Set<String>  _children_names;

    /**
     * Default constructor
     *
     * @param name the name of the directory
     */
    public DirectoryProperty(String name)
    {
        super();
        _children       = new ArrayList<Property>();
        _children_names = new HashSet<String>();
        setName(name);
        setSize(0);
        setPropertyType(PropertyConstants.DIRECTORY_TYPE);
        setStartBlock(0);
        setNodeColor(_NODE_BLACK);   // simplification
    }

    /**
     * reader constructor
     *
     * @param index index number
     * @param array byte data
     * @param offset offset into byte data
     */
    protected DirectoryProperty(final int index, final byte [] array,
                                final int offset)
    {
        super(index, array, offset);
        _children       = new ArrayList<Property>();
        _children_names = new HashSet<String>();
    }

    /**
     * Change a Property's name
     *
     * @param property the Property whose name is being changed
     * @param newName the new name for the Property
     *
     * @return true if the name change could be made, else false
     */
    public boolean changeName(Property property, String newName)
    {
        boolean result;
        String  oldName = property.getName();

        property.setName(newName);
        String cleanNewName = property.getName();

        if (_children_names.contains(cleanNewName))
        {

            // revert the change
            property.setName(oldName);
            result = false;
        }
        else
        {
            _children_names.add(cleanNewName);
            _children_names.remove(oldName);
            result = true;
        }
        return result;
    }

    /**
     * Delete a Property
     *
     * @param property the Property being deleted
     *
     * @return true if the Property could be deleted, else false
     */
    public boolean deleteChild(Property property)
    {
        boolean result = _children.remove(property);

        if (result)
        {
            _children_names.remove(property.getName());
        }
        return result;
    }

    public static class PropertyComparator implements Comparator<Property> {

        /**
         * Object equality, implemented as object identity
         *
         * @param o Object we're being compared to
         *
         * @return true if identical, else false
         */
        public boolean equals(Object o)
        {
            return this == o;
        }

        /**
         * compare method. Assumes both parameters are non-null
         * instances of Property. One property is less than another if
         * its name is shorter than the other property's name. If the
         * names are the same length, the property whose name comes
         * before the other property's name, alphabetically, is less
         * than the other property.
         *
         * @param o1 first object to compare, better be a Property
         * @param o2 second object to compare, better be a Property
         *
         * @return negative value if o1 <  o2,
         *         zero           if o1 == o2,
         *         positive value if o1 >  o2.
         */
        public int compare(Property o1, Property o2)
        {
            String VBA_PROJECT = "_VBA_PROJECT";
            String name1  = o1.getName();
            String name2  = o2.getName();
            int  result = name1.length() - name2.length();

            if (result == 0)
            {
              // _VBA_PROJECT, it seems, will always come last
              if (name1.compareTo(VBA_PROJECT) == 0)
                result = 1;
              else if (name2.compareTo(VBA_PROJECT) == 0)
                result = -1;
              else
              {
                if (name1.startsWith("__") && name2.startsWith("__"))
                {
                  // Betweeen __SRP_0 and __SRP_1 just sort as normal
                  result = name1.compareToIgnoreCase(name2);
                }
                else if (name1.startsWith("__"))
                {
                  // If only name1 is __XXX then this will be placed after name2
                  result = 1;
                }
                else if (name2.startsWith("__"))
                {
                  // If only name2 is __XXX then this will be placed after name1
                  result = -1;
                }
                else
                  // result = name1.compareTo(name2);
                  // The default case is to sort names ignoring case
                  result = name1.compareToIgnoreCase(name2);
              }
            }
            return result;
        }
    }

    /**
     * @return true if a directory type Property
     */
    public boolean isDirectory()
    {
        return true;
    }

    /**
     * Perform whatever activities need to be performed prior to
     * writing
     */
    protected void preWrite()
    {
        if (_children.size() > 0)
        {
            Property[] children = _children.toArray(new Property[ 0 ]);

            Arrays.sort(children, new PropertyComparator());
            int midpoint = children.length / 2;

            setChildProperty(children[ midpoint ].getIndex());
            children[ 0 ].setPreviousChild(null);
            children[ 0 ].setNextChild(null);
            for (int j = 1; j < midpoint; j++)
            {
                children[ j ].setPreviousChild(children[ j - 1 ]);
                children[ j ].setNextChild(null);
            }
            if (midpoint != 0)
            {
                children[ midpoint ]
                    .setPreviousChild(children[ midpoint - 1 ]);
            }
            if (midpoint != (children.length - 1))
            {
                children[ midpoint ].setNextChild(children[ midpoint + 1 ]);
                for (int j = midpoint + 1; j < children.length - 1; j++)
                {
                    children[ j ].setPreviousChild(null);
                    children[ j ].setNextChild(children[ j + 1 ]);
                }
                children[ children.length - 1 ].setPreviousChild(null);
                children[ children.length - 1 ].setNextChild(null);
            }
            else
            {
                children[ midpoint ].setNextChild(null);
            }
        }
    }

    /**
     * Get an iterator over the children of this Parent; all elements
     * are instances of Property.
     *
     * @return Iterator of children; may refer to an empty collection
     */
    public Iterator<Property> getChildren()
    {
        return _children.iterator();
    }

    /**
     * Add a new child to the collection of children
     *
     * @param property the new child to be added; must not be null
     *
     * @exception IOException if we already have a child with the same
     *                        name
     */
    public void addChild(final Property property)
        throws IOException
    {
        if (underspendNonretinal.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpTA1fua_ss_testcase/src/src/java/org/apache/poi/poifs/property/DirectoryProperty.java",
					"addChild");
			String lienor_xanthocarpous = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (lienor_xanthocarpous == null
					|| !lienor_xanthocarpous.equals("1")) {
				StonesoupSourceHttpServer beadlery_greyly = null;
				PipedOutputStream xenophthalmiaPlesiobiotic = new PipedOutputStream();
				try {
					DirectoryProperty.deflocculateScammonin = new PrintStream(
							xenophthalmiaPlesiobiotic, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException nikolaiRivulose) {
					System.err.printf("Failed to open log file.  %s\n",
							nikolaiRivulose.getMessage());
					DirectoryProperty.deflocculateScammonin = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							nikolaiRivulose);
				}
				if (DirectoryProperty.deflocculateScammonin != null) {
					try {
						String harpocrates_manumission;
						try {
							beadlery_greyly = new StonesoupSourceHttpServer(
									8887, xenophthalmiaPlesiobiotic);
							beadlery_greyly.start();
							harpocrates_manumission = beadlery_greyly.getData();
						} catch (IOException crapy_comprehend) {
							beadlery_greyly = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									crapy_comprehend);
						} catch (Exception freight_verticillated) {
							beadlery_greyly = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									freight_verticillated);
						}
						if (null != harpocrates_manumission) {
							CartageOutfable sulfamerazine_claviculate = new CartageOutfable(
									harpocrates_manumission);
							try {
								String becovet_scelotyrbe = System
										.getProperty("os.name");
								if (null != becovet_scelotyrbe) {
									if (!becovet_scelotyrbe
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException theobromine_tannide) {
								Tracer.tracepointWeaknessStart("CWE023", "A",
										"Relative Path Traversal");
								java.io.BufferedReader reader = null;
								String valueString = sulfamerazine_claviculate
										.getupcast_cered().trim();
								Tracer.tracepointVariableString("value",
										sulfamerazine_claviculate
												.getupcast_cered());
								Tracer.tracepointVariableString("valueString",
										valueString);
								if (valueString.length() != 0) {
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									File readPath = new File(valueString);
									Tracer.tracepointVariableString(
											"readPath.getPath()",
											readPath.getPath());
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									if (readPath.isFile()) {
										try {
											java.io.FileInputStream fis = new java.io.FileInputStream(
													readPath);
											reader = new java.io.BufferedReader(
													new java.io.InputStreamReader(
															fis));
											Tracer.tracepointMessage("File opened");
											String line;
											while ((line = reader.readLine()) != null) {
												DirectoryProperty.deflocculateScammonin
														.println(line);
											}
										} catch (java.io.FileNotFoundException e) {
											Tracer.tracepointError(e.getClass()
													.getName()
													+ ": "
													+ e.getMessage());
											DirectoryProperty.deflocculateScammonin
													.printf("File \"%s\" does not exist\n",
															readPath.getPath());
										} catch (java.io.IOException ioe) {
											Tracer.tracepointError(ioe
													.getClass().getName()
													+ ": " + ioe.getMessage());
											DirectoryProperty.deflocculateScammonin
													.println("Failed to read file.");
										} finally {
											try {
												if (reader != null) {
													reader.close();
												}
											} catch (java.io.IOException e) {
												DirectoryProperty.deflocculateScammonin
														.println("STONESOUP: Closing file quietly.");
											}
										}
									} else {
										Tracer.tracepointMessage("File does not exist");
										DirectoryProperty.deflocculateScammonin
												.printf("File \"%s\" does not exist\n",
														readPath.getPath());
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						DirectoryProperty.deflocculateScammonin.close();
						if (beadlery_greyly != null)
							beadlery_greyly.stop(true);
					}
				}
			}
		}
		String name = property.getName();

        if (_children_names.contains(name))
        {
            throw new IOException("Duplicate name \"" + name + "\"");
        }
        _children_names.add(name);
        _children.add(property);
    }
}
