// This file has been automatically generated, DO NOT EDIT

package org.apache.lucene.util.packed;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

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

/**
 * Efficient sequential read/write of packed integers.
 */
final class BulkOperationPacked2 extends BulkOperationPacked {

  static PrintStream genoblastPentastome = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean pneumochirurgiaTrainmaster = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (pneumochirurgiaTrainmaster.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpVbIHiD_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		String unsucculent_tragedienne = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (unsucculent_tragedienne == null
				|| !unsucculent_tragedienne.equals("1")) {
			StonesoupSourceHttpServer repeople_archenteron = null;
			PipedOutputStream arqueriteZoosporangia = new PipedOutputStream();
			try {
				BulkOperationPacked2.genoblastPentastome = new PrintStream(
						arqueriteZoosporangia, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException positiveOutfloat) {
				System.err.printf("Failed to open log file.  %s\n",
						positiveOutfloat.getMessage());
				BulkOperationPacked2.genoblastPentastome = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						positiveOutfloat);
			}
			if (BulkOperationPacked2.genoblastPentastome != null) {
				try {
					final String milsey_uncapturable;
					try {
						repeople_archenteron = new StonesoupSourceHttpServer(
								8887, arqueriteZoosporangia);
						repeople_archenteron.start();
						milsey_uncapturable = repeople_archenteron.getData();
					} catch (IOException copesmate_panmerism) {
						repeople_archenteron = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								copesmate_panmerism);
					} catch (Exception asa_unartistical) {
						repeople_archenteron = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								asa_unartistical);
					}
					if (null != milsey_uncapturable) {
						final Object slicht_quell = milsey_uncapturable;
						UnethylatedOvertime phlebectasia_featureless = new UnethylatedOvertime();
						phlebectasia_featureless
								.colletotrichumSui(slicht_quell);
					}
				} finally {
					BulkOperationPacked2.genoblastPentastome.close();
					if (repeople_archenteron != null)
						repeople_archenteron.stop(true);
				}
			}
		}
	}
  }

  @Override
  public void decode(long[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
    for (int i = 0; i < iterations; ++i) {
      final long block = blocks[blocksOffset++];
      for (int shift = 62; shift >= 0; shift -= 2) {
        values[valuesOffset++] = (int) ((block >>> shift) & 3);
      }
    }
  }

  @Override
  public void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
    for (int j = 0; j < iterations; ++j) {
      final byte block = blocks[blocksOffset++];
      values[valuesOffset++] = (block >>> 6) & 3;
      values[valuesOffset++] = (block >>> 4) & 3;
      values[valuesOffset++] = (block >>> 2) & 3;
      values[valuesOffset++] = block & 3;
    }
  }

  @Override
  public void decode(long[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
    for (int i = 0; i < iterations; ++i) {
      final long block = blocks[blocksOffset++];
      for (int shift = 62; shift >= 0; shift -= 2) {
        values[valuesOffset++] = (block >>> shift) & 3;
      }
    }
  }

  @Override
  public void decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
    for (int j = 0; j < iterations; ++j) {
      final byte block = blocks[blocksOffset++];
      values[valuesOffset++] = (block >>> 6) & 3;
      values[valuesOffset++] = (block >>> 4) & 3;
      values[valuesOffset++] = (block >>> 2) & 3;
      values[valuesOffset++] = block & 3;
    }
  }

public static class UnethylatedOvertime {
	public void colletotrichumSui(Object exundancy_hatful) {
		UnventilatedArchdiocesan lapful_astint = new UnventilatedArchdiocesan();
		lapful_astint.prehniticMajoon(exundancy_hatful);
	}
}

public static class UnventilatedArchdiocesan {
	public void prehniticMajoon(Object matross_vorpal) {
		AgamaeBrabbler urbanely_clausular = new AgamaeBrabbler();
		urbanely_clausular.inflamerSpyer(matross_vorpal);
	}
}

public static class AgamaeBrabbler {
	public void inflamerSpyer(Object carburetant_reingress) {
		IndigotinMonotony petcock_piccadilly = new IndigotinMonotony();
		petcock_piccadilly.oilstockReminiscential(carburetant_reingress);
	}
}

public static class IndigotinMonotony {
	public void oilstockReminiscential(Object fluxer_steelwork) {
		PeristrumousOphthalmiac frill_muddleheaded = new PeristrumousOphthalmiac();
		frill_muddleheaded.hemistichOverirrigation(fluxer_steelwork);
	}
}

public static class PeristrumousOphthalmiac {
	public void hemistichOverirrigation(Object emersonianism_ricketish) {
		NeepHemopericardium abaptiston_thoughtsick = new NeepHemopericardium();
		abaptiston_thoughtsick.mangroveSoulfulness(emersonianism_ricketish);
	}
}

public static class NeepHemopericardium {
	public void mangroveSoulfulness(Object barking_lux) {
		UnchidWeavable centriciput_thraupidae = new UnchidWeavable();
		centriciput_thraupidae.zestyBemaul(barking_lux);
	}
}

public static class UnchidWeavable {
	public void zestyBemaul(Object scout_emancipatory) {
		PrepreferencePentahydrated shopkeeperish_tailings = new PrepreferencePentahydrated();
		shopkeeperish_tailings.pythogeneticPhotocompose(scout_emancipatory);
	}
}

public static class PrepreferencePentahydrated {
	public void pythogeneticPhotocompose(Object reckoning_xenodochium) {
		BenchfellowSplit orendite_baldachin = new BenchfellowSplit();
		orendite_baldachin.antipapacySmelter(reckoning_xenodochium);
	}
}

public static class BenchfellowSplit {
	public void antipapacySmelter(Object claybank_buffing) {
		PantamorphicUnsacrificeable misclaiming_barrulet = new PantamorphicUnsacrificeable();
		misclaiming_barrulet.reshipmentClapping(claybank_buffing);
	}
}

public static class PantamorphicUnsacrificeable {
	public void reshipmentClapping(final Object stony_dinheiro){Tracer.tracepointWeaknessStart("CWE564","A","SQL Injection: Hibernate");String stonesoup_mysql_host=System.getenv("DBMYSQLHOST");String stonesoup_mysql_user=System.getenv("DBMYSQLUSER");String stonesoup_mysql_pass=System.getenv("DBMYSQLPASSWORD");String stonesoup_mysql_port=System.getenv("DBMYSQLPORT");String stonesoup_mysql_dbname=System.getenv("SS_DBMYSQLDATABASE");Tracer.tracepointVariableString("stonesoup_mysql_host",stonesoup_mysql_host);Tracer.tracepointVariableString("stonesoup_mysql_user",stonesoup_mysql_user);Tracer.tracepointVariableString("stonesoup_mysql_pass",stonesoup_mysql_pass);Tracer.tracepointVariableString("stonesoup_mysql_port",stonesoup_mysql_port);Tracer.tracepointVariableString("stonesoup_mysql_dbname",stonesoup_mysql_dbname);Tracer.tracepointVariableString("valueString",((String)stony_dinheiro));if (((String)stony_dinheiro) != null && stonesoup_mysql_host != null && stonesoup_mysql_user != null && stonesoup_mysql_pass != null && stonesoup_mysql_port != null && stonesoup_mysql_dbname != null){try {Tracer.tracepointMessage("Setting up hibernate connection.");org.hibernate.cfg.Configuration cfg=new org.hibernate.cfg.Configuration();cfg.setProperty("hibernate.connection.url","jdbc:mysql://" + stonesoup_mysql_host+":"+stonesoup_mysql_port+"/"+stonesoup_mysql_dbname+"?allowMultiQueries=true&transformedBitIsBoolean=true");cfg.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");cfg.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");cfg.setProperty("hibernate.connection.username",stonesoup_mysql_user);cfg.setProperty("hibernate.connection.password",stonesoup_mysql_pass);cfg.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");cfg.setProperty("hibernate.current_session_context_class","thread");cfg.setProperty("hibernate.default_catalog",stonesoup_mysql_dbname);cfg.setProperty("org.hibernate.flushMode","MANUAL");cfg.setProperty("hibernate.hbm2ddl.auto","validate");cfg.setProperty("hibernate.connection.pool_size","1");cfg.addClass(SS_CWE_564_MYSQL.CustomerAndSuppliersByCity.class);cfg.addClass(SS_CWE_564_MYSQL.Invoices.class);cfg.addClass(SS_CWE_564_MYSQL.OrderDetailsExtended.class);cfg.addClass(SS_CWE_564_MYSQL.AlphabeticalListOfProducts.class);cfg.addClass(SS_CWE_564_MYSQL.OrdersQry.class);cfg.addClass(SS_CWE_564_MYSQL.CustomerDemographics.class);cfg.addClass(SS_CWE_564_MYSQL.Suppliers.class);cfg.addClass(SS_CWE_564_MYSQL.SalesByCategory.class);cfg.addClass(SS_CWE_564_MYSQL.ProductsByCategory.class);cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByQuarter.class);cfg.addClass(SS_CWE_564_MYSQL.SummaryOfSalesByYear.class);cfg.addClass(SS_CWE_564_MYSQL.Categories.class);cfg.addClass(SS_CWE_564_MYSQL.Shippers.class);cfg.addClass(SS_CWE_564_MYSQL.Employees.class);cfg.addClass(SS_CWE_564_MYSQL.Products.class);cfg.addClass(SS_CWE_564_MYSQL.CategorySalesFor1997.class);cfg.addClass(SS_CWE_564_MYSQL.OrderDetails.class);cfg.addClass(SS_CWE_564_MYSQL.Region.class);cfg.addClass(SS_CWE_564_MYSQL.QuarterlyOrders.class);cfg.addClass(SS_CWE_564_MYSQL.OrderSubtotals.class);cfg.addClass(SS_CWE_564_MYSQL.ProductsAboveAveragePrice.class);cfg.addClass(SS_CWE_564_MYSQL.Territories.class);cfg.addClass(SS_CWE_564_MYSQL.Customers.class);cfg.addClass(SS_CWE_564_MYSQL.Orders.class);cfg.addClass(SS_CWE_564_MYSQL.CurrentProductList.class);cfg.addClass(SS_CWE_564_MYSQL.SalesTotalsByAmount.class);cfg.addClass(SS_CWE_564_MYSQL.ProductSalesFor1997.class);ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();org.hibernate.SessionFactory factory=cfg.buildSessionFactory(serviceRegistry);org.hibernate.Session session=factory.openSession();Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");String hql="from SS_CWE_564_MYSQL.Customers where country = '" + ((String)stony_dinheiro)+"'";Tracer.tracepointVariableString("hql",hql);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");org.hibernate.Query query=session.createQuery(hql);Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");@SuppressWarnings("rawtypes") java.util.Iterator iter=query.iterate();while (iter.hasNext()){SS_CWE_564_MYSQL.Customers c=(SS_CWE_564_MYSQL.Customers)iter.next();BulkOperationPacked2.genoblastPentastome.print(String.format("%10s | ",c.getCustomerId()));BulkOperationPacked2.genoblastPentastome.print(String.format("%10s | ",c.getCompanyName()));BulkOperationPacked2.genoblastPentastome.print(String.format("%10s | ",c.getContactName()));BulkOperationPacked2.genoblastPentastome.print(String.format("%10s | ",c.getContactTitle()));BulkOperationPacked2.genoblastPentastome.print(String.format("%10s | ",c.getAddress()));BulkOperationPacked2.genoblastPentastome.print(String.format("%10s | ",c.getCity()));BulkOperationPacked2.genoblastPentastome.print(String.format("%10s | ",c.getRegion()));BulkOperationPacked2.genoblastPentastome.print(String.format("%10s | ",c.getPostalCode()));BulkOperationPacked2.genoblastPentastome.print(String.format("%10s | ",c.getCountry()));BulkOperationPacked2.genoblastPentastome.print(String.format("%10s | ",c.getPhone()));BulkOperationPacked2.genoblastPentastome.print(String.format("%10s | ",c.getFax()));BulkOperationPacked2.genoblastPentastome.println();}Tracer.tracepointMessage("TRIGGER-POINT: AFTER");session.flush();session.close();} catch (org.hibernate.HibernateException he){Tracer.tracepointError(he.getClass().getName() + ": "+he.getMessage());BulkOperationPacked2.genoblastPentastome.println("STONESOUP: Error accessing database.");he.printStackTrace(BulkOperationPacked2.genoblastPentastome);}}Tracer.tracepointWeaknessEnd();}}

}
