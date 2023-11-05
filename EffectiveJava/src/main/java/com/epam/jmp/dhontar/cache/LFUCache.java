package com.epam.jmp.dhontar.cache;

import static com.epam.jmp.dhontar.util.Constants.CACHE_MAX_SIZE;

import com.epam.jmp.dhontar.statistics.StatisticListener;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LFUCache<K, V> {
    private final StatisticListener<K> statListener;
    private final Map<K, CacheEntry> cache;

    public LFUCache() {
        this.cache = new ConcurrentHashMap<>(CACHE_MAX_SIZE);
        this.statListener = new StatisticListener<>();
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
        cache.put(key, new CacheEntry(value));
        long endTime = System.nanoTime();

        statListener.onPut(startTime, endTime);
    }

    public V get(K key) {
        CacheEntry entry = cache.get(key);
        V value = entry.getValue();
        if (value != null) {
            entry.incrementUsageCount();
        }
        return value;
    }

    private void evict() {
        K keyToRemove = cache.entrySet().stream()
                .min(Comparator.comparingInt((Map.Entry<K, CacheEntry> entry) ->
                        entry.getValue().getUsageCount().get()))
                .orElseThrow(IllegalArgumentException::new)
                .getKey();

        cache.remove(keyToRemove);
        statListener.onEvict(keyToRemove);
    }

    private class CacheEntry {
        public CacheEntry(V value) {
            this.value = value;
        }

        private final V value;
        private final AtomicInteger usageCount = new AtomicInteger();

        public void incrementUsageCount() {
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
