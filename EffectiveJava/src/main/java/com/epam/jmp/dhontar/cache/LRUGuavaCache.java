package com.epam.jmp.dhontar.cache;

import com.epam.jmp.dhontar.statistics.LRUStatistics;
import com.epam.jmp.dhontar.util.LogUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LRUGuavaCache<K, V> implements ICustomCache<K, V> {

    private static final Logger LOGGER = LogUtil.getLogger();
    private final LoadingCache<K, V> cache;
    private int maxCacheSize = 100000;
    public int evictionPeriod = 5;

    public LRUGuavaCache() {
        this.cache = setCache(maxCacheSize, evictionPeriod);
    }

    public LRUGuavaCache(int maxCacheSize, int evictionPeriod) {
        this.maxCacheSize = maxCacheSize;
        this.evictionPeriod = evictionPeriod;
        this.cache = setCache(maxCacheSize, evictionPeriod);
    }

    private LoadingCache<K, V> setCache(int maxCacheSize, int evictionPeriod) {
        LoadingCache<K, V> cache = CacheBuilder.newBuilder()
                .maximumSize(maxCacheSize)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .removalListener((RemovalListener<K, V>) n -> {
                    if (n.wasEvicted()) {
                        LOGGER.info("Item {} : {} was removed.", n.getKey(), n.getValue());
                    }
                })
                .expireAfterAccess(evictionPeriod, TimeUnit.SECONDS)
                .recordStats()
                .build(new CacheLoader<>() {
                    @Override
                    public @NotNull V load(@NotNull K key) {
                        return (V) ("Value" + key);
                    }
                });

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread th = new Thread(runnable);
            th.setDaemon(true);
            return th;
        });
        scheduler.scheduleAtFixedRate(cache::cleanUp, evictionPeriod, evictionPeriod, TimeUnit.SECONDS);
        return cache;
    }

    @Override
    public V getValue(K key) {
        try {
            return cache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public LRUStatistics getStatistics() {
        return new LRUStatistics(cache.stats().evictionCount());
    }
}
