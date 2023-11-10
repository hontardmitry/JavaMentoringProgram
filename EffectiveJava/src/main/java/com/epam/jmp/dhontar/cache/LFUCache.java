package com.epam.jmp.dhontar.cache;

import static com.epam.jmp.dhontar.util.Constants.CACHE_MAX_SIZE;

import com.epam.jmp.dhontar.statistics.StatisticListener;
import com.epam.jmp.dhontar.util.LogUtil;
import org.slf4j.Logger;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LFUCache<K, V> {
    private static final Logger LOGGER = LogUtil.getLogger();
    private final StatisticListener statListener;
    private final Map<K, CacheEntry> cache;

    public LFUCache() {
        this.cache = new ConcurrentHashMap<>(CACHE_MAX_SIZE);
        this.statListener = new StatisticListener();
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread th = new Thread(runnable);
            th.setDaemon(true);
            return th;
        });

        // Schedule a task to remove the least frequently used item every 5 seconds
        scheduler.scheduleAtFixedRate(this::evict, 5, 5, TimeUnit.SECONDS);
    }

    public void put(K key, V value) {
        long startTime = System.nanoTime();
        if (cache.size() == CACHE_MAX_SIZE) {
            evict();
        }
        synchronized (cache) {
            cache.put(key, new CacheEntry(value));
        }
        long endTime = System.nanoTime();

        statListener.onPut(startTime, endTime);
    }

    public synchronized CacheEntry get(K key) {
            CacheEntry entry = cache.get(key);
            if (entry != null) {
                entry.incrementUsageCount();
            }
            return entry;
    }

    public int size() {
        return cache.size();
    }

    private void evict() {
        synchronized (cache) {
            K keyToRemove = cache.entrySet().stream()
                    .min(Comparator.comparingInt((Map.Entry<K, CacheEntry> entry) ->
                            entry.getValue().getUsageCount().get()))
                    .orElseThrow(IllegalArgumentException::new)
                    .getKey();
                cache.remove(keyToRemove);
            LOGGER.info(String.format("Evicted entry with key '%s'", keyToRemove));
        }
        statListener.onEvict();
    }

    public StatisticListener getStatistics() {
        return statListener;
    }

    //TODO close class
    public class CacheEntry {
        private CacheEntry(V value) {
            this.value = value;
        }

        private final V value;
        private final AtomicInteger usageCount = new AtomicInteger();

        private void incrementUsageCount() {
            this.usageCount.incrementAndGet();
        }

        public V getValue() {
            return value;
        }

        public AtomicInteger getUsageCount() {
            return usageCount;
        }
    }
}
