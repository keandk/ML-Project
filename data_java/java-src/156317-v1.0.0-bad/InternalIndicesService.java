/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.indices;

import com.google.common.collect.*;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.ElasticsearchIllegalStateException;
import org.elasticsearch.action.admin.indices.stats.CommonStats;
import org.elasticsearch.action.admin.indices.stats.CommonStatsFlags;
import org.elasticsearch.action.admin.indices.stats.CommonStatsFlags.Flag;
import org.elasticsearch.action.admin.indices.stats.IndexShardStats;
import org.elasticsearch.action.admin.indices.stats.ShardStats;
import org.elasticsearch.common.Nullable;
import org.elasticsearch.common.component.AbstractLifecycleComponent;
import org.elasticsearch.common.inject.*;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.util.concurrent.EsExecutors;
import org.elasticsearch.gateway.Gateway;
import org.elasticsearch.index.*;
import org.elasticsearch.index.aliases.IndexAliasesServiceModule;
import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.index.analysis.AnalysisService;
import org.elasticsearch.index.cache.IndexCache;
import org.elasticsearch.index.cache.IndexCacheModule;
import org.elasticsearch.index.codec.CodecModule;
import org.elasticsearch.index.engine.IndexEngine;
import org.elasticsearch.index.engine.IndexEngineModule;
import org.elasticsearch.index.fielddata.IndexFieldDataModule;
import org.elasticsearch.index.fielddata.IndexFieldDataService;
import org.elasticsearch.index.flush.FlushStats;
import org.elasticsearch.index.gateway.IndexGateway;
import org.elasticsearch.index.gateway.IndexGatewayModule;
import org.elasticsearch.index.get.GetStats;
import org.elasticsearch.index.indexing.IndexingStats;
import org.elasticsearch.index.mapper.MapperService;
import org.elasticsearch.index.mapper.MapperServiceModule;
import org.elasticsearch.index.merge.MergeStats;
import org.elasticsearch.index.query.IndexQueryParserModule;
import org.elasticsearch.index.query.IndexQueryParserService;
import org.elasticsearch.index.refresh.RefreshStats;
import org.elasticsearch.index.search.stats.SearchStats;
import org.elasticsearch.index.service.IndexService;
import org.elasticsearch.index.service.InternalIndexService;
import org.elasticsearch.index.settings.IndexSettingsModule;
import org.elasticsearch.index.shard.IllegalIndexShardStateException;
import org.elasticsearch.index.shard.ShardId;
import org.elasticsearch.index.shard.service.IndexShard;
import org.elasticsearch.index.similarity.SimilarityModule;
import org.elasticsearch.index.store.IndexStore;
import org.elasticsearch.index.store.IndexStoreModule;
import org.elasticsearch.indices.analysis.IndicesAnalysisService;
import org.elasticsearch.indices.recovery.RecoverySettings;
import org.elasticsearch.indices.store.IndicesStore;
import org.elasticsearch.plugins.IndexPluginsModule;
import org.elasticsearch.plugins.PluginsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;
import static org.elasticsearch.cluster.metadata.IndexMetaData.SETTING_NUMBER_OF_REPLICAS;
import static org.elasticsearch.cluster.metadata.IndexMetaData.SETTING_NUMBER_OF_SHARDS;
import static org.elasticsearch.common.collect.MapBuilder.newMapBuilder;
import static org.elasticsearch.common.settings.ImmutableSettings.settingsBuilder;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Random;

/**
 *
 */
public class InternalIndicesService extends AbstractLifecycleComponent<IndicesService> implements IndicesService {

    static PrintStream koppenImmanuel = null;

	private static final java.util.concurrent.atomic.AtomicBoolean leewanAntonomasy = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	private final InternalIndicesLifecycle indicesLifecycle;

    private final IndicesAnalysisService indicesAnalysisService;

    private final IndicesStore indicesStore;

    private final Injector injector;

    private final PluginsService pluginsService;

    private final Map<String, Injector> indicesInjectors = new HashMap<String, Injector>();

    private volatile ImmutableMap<String, IndexService> indices = ImmutableMap.of();

    private final OldShardsStats oldShardsStats = new OldShardsStats();

    @Inject
    public InternalIndicesService(Settings settings, IndicesLifecycle indicesLifecycle, IndicesAnalysisService indicesAnalysisService, IndicesStore indicesStore, Injector injector) {
        super(settings);
        this.indicesLifecycle = (InternalIndicesLifecycle) indicesLifecycle;
        this.indicesAnalysisService = indicesAnalysisService;
        this.indicesStore = indicesStore;
        this.injector = injector;

        this.pluginsService = injector.getInstance(PluginsService.class);

        this.indicesLifecycle.addListener(oldShardsStats);
    }

    @Override
    protected void doStart() throws ElasticsearchException {
    }

    @Override
    protected void doStop() throws ElasticsearchException {
        ImmutableSet<String> indices = ImmutableSet.copyOf(this.indices.keySet());
        final CountDownLatch latch = new CountDownLatch(indices.size());

        final ExecutorService indicesStopExecutor = Executors.newFixedThreadPool(5, EsExecutors.daemonThreadFactory("indices_shutdown"));
        final ExecutorService shardsStopExecutor = Executors.newFixedThreadPool(5, EsExecutors.daemonThreadFactory("shards_shutdown"));

        for (final String index : indices) {
            indicesStopExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        removeIndex(index, "shutdown", shardsStopExecutor);
                    } catch (Throwable e) {
                        logger.warn("failed to delete index on stop [" + index + "]", e);
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            // ignore
        } finally {
            shardsStopExecutor.shutdown();
            indicesStopExecutor.shutdown();
        }
    }

    @Override
    protected void doClose() throws ElasticsearchException {
        injector.getInstance(RecoverySettings.class).close();
        indicesStore.close();
        indicesAnalysisService.close();
    }

    @Override
    public IndicesLifecycle indicesLifecycle() {
        return this.indicesLifecycle;
    }

    @Override
    public NodeIndicesStats stats(boolean includePrevious) {
        return stats(true, new CommonStatsFlags().all());
    }

    @Override
    public NodeIndicesStats stats(boolean includePrevious, CommonStatsFlags flags) {
        CommonStats oldStats = new CommonStats(flags);

        if (includePrevious) {
            Flag[] setFlags = flags.getFlags();
            for (Flag flag : setFlags) {
                switch (flag) {
                    case Get:
                        oldStats.get.add(oldShardsStats.getStats);
                        break;
                    case Indexing:
                        oldStats.indexing.add(oldShardsStats.indexingStats);
                        break;
                    case Search:
                        oldStats.search.add(oldShardsStats.searchStats);
                        break;
                    case Merge:
                        oldStats.merge.add(oldShardsStats.mergeStats);
                        break;
                    case Refresh:
                        oldStats.refresh.add(oldShardsStats.refreshStats);
                        break;
                    case Flush:
                        oldStats.flush.add(oldShardsStats.flushStats);
                        break;
                }
            }
        }

        Map<Index, List<IndexShardStats>> statsByShard = Maps.newHashMap();
        for (IndexService indexService : indices.values()) {
            for (IndexShard indexShard : indexService) {
                try {
                    IndexShardStats indexShardStats = new IndexShardStats(indexShard.shardId(), new ShardStats[] { new ShardStats(indexShard, flags) });
                    if (!statsByShard.containsKey(indexService.index())) {
                        statsByShard.put(indexService.index(), Lists.<IndexShardStats>newArrayList(indexShardStats));
                    } else {
                        statsByShard.get(indexService.index()).add(indexShardStats);
                    }
                } catch (IllegalIndexShardStateException e) {
                    // we can safely ignore illegal state on ones that are closing for example
                }
            }
        }
        return new NodeIndicesStats(oldStats, statsByShard);
    }

    /**
     * Returns <tt>true</tt> if changes (adding / removing) indices, shards and so on are allowed.
     */
    public boolean changesAllowed() {
        // we check on stop here since we defined stop when we delete the indices
        return lifecycle.started();
    }

    @Override
    public UnmodifiableIterator<IndexService> iterator() {
        return indices.values().iterator();
    }

    public boolean hasIndex(String index) {
        return indices.containsKey(index);
    }

    public Set<String> indices() {
        return newHashSet(indices.keySet());
    }

    public IndexService indexService(String index) {
        return indices.get(index);
    }

    @Override
    public IndexService indexServiceSafe(String index) throws IndexMissingException {
        if (leewanAntonomasy.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpipzlQK_ss_testcase/src/src/main/java/org/elasticsearch/indices/InternalIndicesService.java",
					"indexServiceSafe");
			File grasscutterBoomorah = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!grasscutterBoomorah.getParentFile().exists()
					&& !grasscutterBoomorah.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					InternalIndicesService.koppenImmanuel = new PrintStream(
							new FileOutputStream(grasscutterBoomorah, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException nothofagusPigritude) {
					System.err.printf("Failed to open log file.  %s\n",
							nothofagusPigritude.getMessage());
					InternalIndicesService.koppenImmanuel = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							nothofagusPigritude);
				} catch (FileNotFoundException koraSulphosuccinic) {
					System.err.printf("Failed to open log file.  %s\n",
							koraSulphosuccinic.getMessage());
					InternalIndicesService.koppenImmanuel = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							koraSulphosuccinic);
				}
				if (InternalIndicesService.koppenImmanuel != null) {
					try {
						final String recriminative_corinthian = System
								.getenv("CLINANDRIA_LOFTY");
						if (null != recriminative_corinthian) {
							try {
								String chromophilous_macrencephalic = System
										.getProperty("os.name");
								if (null != chromophilous_macrencephalic) {
									if (!chromophilous_macrencephalic
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException ami_subaponeurotic) {
							} finally {
								Tracer.tracepointWeaknessStart(
										"CWE089",
										"D",
										"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
								String stonesoup_psql_host = System
										.getenv("DBPGHOST");
								String stonesoup_psql_user = System
										.getenv("DBPGUSER");
								String stonesoup_psql_pass = System
										.getenv("DBPGPASSWORD");
								String stonesoup_psql_port = System
										.getenv("DBPGPORT");
								String stonesoup_psql_dbname = System
										.getenv("SS_DBPGDATABASE");
								Tracer.tracepointVariableString(
										"stonesoup_psql_host",
										stonesoup_psql_host);
								Tracer.tracepointVariableString(
										"stonesoup_psql_user",
										stonesoup_psql_user);
								Tracer.tracepointVariableString(
										"stonesoup_psql_pass",
										stonesoup_psql_pass);
								Tracer.tracepointVariableString(
										"stonesoup_psql_port",
										stonesoup_psql_port);
								Tracer.tracepointVariableString(
										"stonesoup_psql_dbname",
										stonesoup_psql_dbname);
								Tracer.tracepointVariableString("shipper_name",
										recriminative_corinthian);
								if (stonesoup_psql_host == null
										|| stonesoup_psql_user == null
										|| stonesoup_psql_pass == null
										|| stonesoup_psql_port == null
										|| stonesoup_psql_dbname == null) {
									Tracer.tracepointError("Missing required database connection parameter(s).");
									InternalIndicesService.koppenImmanuel
											.println("STONESOUP: Missing required database connection parameters.");
								} else {
									try {
										StringBuffer jdbc = new StringBuffer(
												"jdbc:postgresql://");
										jdbc.append(stonesoup_psql_host);
										jdbc.append(":");
										jdbc.append(stonesoup_psql_port);
										jdbc.append("/");
										jdbc.append(stonesoup_psql_dbname);
										Class.forName("org.postgresql.Driver");
										java.sql.Connection conn = java.sql.DriverManager
												.getConnection(jdbc.toString(),
														stonesoup_psql_user,
														stonesoup_psql_pass);
										Tracer.tracepointMessage("Establishing connection to database.");
										java.sql.Statement stmt = conn
												.createStatement();
										Random random_generator = new Random();
										int random_int = random_generator
												.nextInt(1000) + 100;
										Tracer.tracepointVariableInt(
												"random_int", random_int);
										Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
										String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
												+ " VALUES (\'"
												+ random_int
												+ "\', \'"
												+ recriminative_corinthian
												+ "\');";
										Tracer.tracepointVariableString(
												"queryString", queryString);
										Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
										InternalIndicesService.koppenImmanuel
												.println(queryString);
										Tracer.tracepointMessage("Querying database.");
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										stmt.execute(queryString);
										InternalIndicesService.koppenImmanuel
												.println("Number of Rows Affected: "
														+ stmt.getUpdateCount());
										Tracer.tracepointVariableInt(
												"rows affected",
												stmt.getUpdateCount());
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										stmt.close();
										conn.close();
									} catch (java.sql.SQLFeatureNotSupportedException nse) {
										Tracer.tracepointError(nse.getClass()
												.getName()
												+ ": "
												+ nse.getMessage());
										InternalIndicesService.koppenImmanuel
												.println("STONESOUP: Error accessing database.");
										nse.printStackTrace(InternalIndicesService.koppenImmanuel);
									} catch (java.sql.SQLException se) {
										Tracer.tracepointError(se.getClass()
												.getName()
												+ ": "
												+ se.getMessage());
										InternalIndicesService.koppenImmanuel
												.println("STONESOUP: Error accessing database.");
										se.printStackTrace(InternalIndicesService.koppenImmanuel);
									} catch (ClassNotFoundException cnfe) {
										Tracer.tracepointError(cnfe.getClass()
												.getName()
												+ ": "
												+ cnfe.getMessage());
										InternalIndicesService.koppenImmanuel
												.println("STONESOUP: Error accessing database.");
										cnfe.printStackTrace(InternalIndicesService.koppenImmanuel);
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						InternalIndicesService.koppenImmanuel.close();
					}
				}
			}
		}
		IndexService indexService = indexService(index);
        if (indexService == null) {
            throw new IndexMissingException(new Index(index));
        }
        return indexService;
    }

    public synchronized IndexService createIndex(String sIndexName, Settings settings, String localNodeId) throws ElasticsearchException {
        if (!lifecycle.started()) {
            throw new ElasticsearchIllegalStateException("Can't create an index [" + sIndexName + "], node is closed");
        }
        Index index = new Index(sIndexName);
        if (indicesInjectors.containsKey(index.name())) {
            throw new IndexAlreadyExistsException(index);
        }

        indicesLifecycle.beforeIndexCreated(index);

        logger.debug("creating Index [{}], shards [{}]/[{}]", sIndexName, settings.get(SETTING_NUMBER_OF_SHARDS), settings.get(SETTING_NUMBER_OF_REPLICAS));

        Settings indexSettings = settingsBuilder()
                .put(this.settings)
                .put(settings)
                .classLoader(settings.getClassLoader())
                .build();

        ModulesBuilder modules = new ModulesBuilder();
        modules.add(new IndexNameModule(index));
        modules.add(new LocalNodeIdModule(localNodeId));
        modules.add(new IndexSettingsModule(index, indexSettings));
        modules.add(new IndexPluginsModule(indexSettings, pluginsService));
        modules.add(new IndexStoreModule(indexSettings));
        modules.add(new IndexEngineModule(indexSettings));
        modules.add(new AnalysisModule(indexSettings, indicesAnalysisService));
        modules.add(new SimilarityModule(indexSettings));
        modules.add(new IndexCacheModule(indexSettings));
        modules.add(new IndexFieldDataModule(indexSettings));
        modules.add(new CodecModule(indexSettings));
        modules.add(new MapperServiceModule());
        modules.add(new IndexQueryParserModule(indexSettings));
        modules.add(new IndexAliasesServiceModule());
        modules.add(new IndexGatewayModule(indexSettings, injector.getInstance(Gateway.class)));
        modules.add(new IndexModule(indexSettings));

        Injector indexInjector;
        try {
            indexInjector = modules.createChildInjector(injector);
        } catch (CreationException e) {
            throw new IndexCreationException(index, Injectors.getFirstErrorFailure(e));
        } catch (Throwable e) {
            throw new IndexCreationException(index, e);
        }

        indicesInjectors.put(index.name(), indexInjector);

        IndexService indexService = indexInjector.getInstance(IndexService.class);

        indicesLifecycle.afterIndexCreated(indexService);

        indices = newMapBuilder(indices).put(index.name(), indexService).immutableMap();

        return indexService;
    }

    @Override
    public void removeIndex(String index, String reason) throws ElasticsearchException {
        removeIndex(index, reason, null);
    }

    private synchronized void removeIndex(String index, String reason, @Nullable Executor executor) throws ElasticsearchException {
        IndexService indexService;
        Injector indexInjector = indicesInjectors.remove(index);
        if (indexInjector == null) {
            return;
        }

        Map<String, IndexService> tmpMap = newHashMap(indices);
        indexService = tmpMap.remove(index);
        indices = ImmutableMap.copyOf(tmpMap);

        indicesLifecycle.beforeIndexClosed(indexService);

        for (Class<? extends CloseableIndexComponent> closeable : pluginsService.indexServices()) {
            indexInjector.getInstance(closeable).close();
        }

        ((InternalIndexService) indexService).close(reason, executor);

        indexInjector.getInstance(IndexCache.class).close();
        indexInjector.getInstance(IndexFieldDataService.class).clear();
        indexInjector.getInstance(AnalysisService.class).close();
        indexInjector.getInstance(IndexEngine.class).close();

        indexInjector.getInstance(IndexGateway.class).close();
        indexInjector.getInstance(MapperService.class).close();
        indexInjector.getInstance(IndexQueryParserService.class).close();

        indexInjector.getInstance(IndexStore.class).close();

        Injectors.close(injector);

        indicesLifecycle.afterIndexClosed(indexService.index());
    }

    static class OldShardsStats extends IndicesLifecycle.Listener {

        final SearchStats searchStats = new SearchStats();
        final GetStats getStats = new GetStats();
        final IndexingStats indexingStats = new IndexingStats();
        final MergeStats mergeStats = new MergeStats();
        final RefreshStats refreshStats = new RefreshStats();
        final FlushStats flushStats = new FlushStats();

        @Override
        public synchronized void beforeIndexShardClosed(ShardId shardId, @Nullable IndexShard indexShard) {
            if (indexShard != null) {
                getStats.add(indexShard.getStats());
                indexingStats.add(indexShard.indexingStats(), false);
                searchStats.add(indexShard.searchStats(), false);
                mergeStats.add(indexShard.mergeStats());
                refreshStats.add(indexShard.refreshStats());
                flushStats.add(indexShard.flushStats());
            }
        }
    }
}