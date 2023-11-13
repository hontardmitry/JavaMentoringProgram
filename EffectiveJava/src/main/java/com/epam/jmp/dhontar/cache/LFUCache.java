package com.epam.jmp.dhontar.cache;

import com.epam.jmp.dhontar.statistics.ICacheListener;
import com.epam.jmp.dhontar.statistics.StatisticListener;
import com.epam.jmp.dhontar.statistics.Statistics;
import com.epam.jmp.dhontar.util.LogUtil;
import org.slf4j.Logger;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LFUCache<K, V> {

    private static final Logger LOGGER = LogUtil.getLogger();
    private final ICacheListener statListener = new StatisticListener();
    private final Map<K, CacheEntry> cache;
    private int maxCacheSize = 100000;
    public int evictionPeriod = 5;

    public LFUCache() {
        this.cache = new ConcurrentHashMap<>(maxCacheSize);
        setEvictScheduler(evictionPeriod);
    }

    public LFUCache(int maxCacheSize, int evictionPeriod) {
        this.maxCacheSize = maxCacheSize;
        this.cache = new ConcurrentHashMap<>(maxCacheSize);
        setEvictScheduler(evictionPeriod);
    }

    public void put(K key, V value) {
        long startTime = System.nanoTime();
        synchronized (cache) {
            if (cache.get(key) == null) {
                if (cache.size() == maxCacheSize) {
                    evict();
                }
                cache.put(key, new CacheEntry(value));
            } else {
                cache.get(key).value = value;
            }
        }
        long endTime = System.nanoTime();
        statListener.onPut(startTime, endTime);
    }

    public V getValue(K key) {
        CacheEntry entry = cache.get(key);
        if (entry != null) {
            synchronized (cache.get(key)) {
                entry.usageCount++;
            }
            return entry.value;
        }
        return null;
    }

    private void evict() {
        synchronized (cache) {
            K keyToRemove = cache.entrySet().stream()
                    .min(Comparator.comparingInt((Map.Entry<K, CacheEntry> entry) ->
                            entry.getValue().getUsageCount()))
                    .orElseThrow(IllegalArgumentException::new)
                    .getKey();
            cache.remove(keyToRemove);
            LOGGER.info(String.format("Evicted entry with key '%s'", keyToRemove));
        }
        statListener.onEvict();
    }

    private void setEvictScheduler(int evictionPeriod){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread th = new Thread(runnable);
            th.setDaemon(true);
            return th;
        });

        scheduler.scheduleAtFixedRate(this::evict, evictionPeriod, evictionPeriod, TimeUnit.SECONDS);
    }

    public Statistics getStatistics() {
        return statListener.getStatistics();
    }

    public int getMaxCacheSize() {
        return maxCacheSize;
    }

    public int getEvictionPeriod() {
        return evictionPeriod;
    }


    private class CacheEntry {

        private CacheEntry(V value) {
            this.value = value;
        }

        private V value;
        private int usageCount;
        private int getUsageCount() {
            return usageCount;
        }
    }
}
