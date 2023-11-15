package com.epam.jmp.dhontar.cache;

import com.epam.jmp.dhontar.statistics.LFUStatistics;
import com.epam.jmp.dhontar.statistics.listener.ICacheListener;
import com.epam.jmp.dhontar.statistics.listener.StatisticListener;
import com.epam.jmp.dhontar.util.LogUtil;
import org.slf4j.Logger;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LFUCache<K, V> implements ICustomCache<K, V> {

    private static final int CACHE_SIZE = 100000;
    private static final int EVICTION_PERIOD = 5;
    private static final Logger LOGGER = LogUtil.getLogger();
    private final ICacheListener statListener;
    private final Map<K, CacheEntry> cache;
    private final int maxCacheSize;

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock writeLock = readWriteLock.writeLock();
    Lock readLock = readWriteLock.readLock();


    public LFUCache() {
        this(CACHE_SIZE, EVICTION_PERIOD, new StatisticListener());
    }

    public LFUCache(int maxCacheSize, int evictionPeriod, ICacheListener statListener) {
        this.maxCacheSize = maxCacheSize;
        this.cache = new ConcurrentHashMap<>(maxCacheSize);
        setEvictScheduler(evictionPeriod);
        this.statListener = statListener;
    }

    @Override
    public void put(K key, V value) {
        long startTime = System.nanoTime();
        try {
            writeLock.lock();
            if (cache.get(key) == null) {
                if (cache.size() == maxCacheSize) {
                    evict();
                }
                cache.put(key, new CacheEntry(value));
            } else {
                cache.get(key).value = value;
            }
        } finally {
            writeLock.unlock();
        }
        long endTime = System.nanoTime();
        statListener.onPut(startTime, endTime);
    }

    @Override
    public V getValue(K key) {
        CacheEntry entry = cache.get(key);
        if (entry != null) {
            try{
                readLock.lock();
                entry.usageCount++;
            } finally {
                readLock.unlock();
            }
            return entry.value;
        }
        return null;
    }

    private void evict() {
        synchronized (cache) {
            cache.entrySet().stream()
                    .min(Comparator.comparingInt((Map.Entry<K, CacheEntry> entry) ->
                            entry.getValue().getUsageCount()))
                    .map(Map.Entry::getKey)
                    .ifPresent(key -> {
                        cache.remove(key);
                        LOGGER.info(String.format("Evicted entry with key '%s'", key));
                    });
        }
        statListener.onEvict();
    }

    private void setEvictScheduler(int evictionPeriod) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread th = new Thread(runnable);
            th.setDaemon(true);
            return th;
        });

        scheduler.scheduleAtFixedRate(this::evict, evictionPeriod, evictionPeriod, TimeUnit.SECONDS);
    }

    public LFUStatistics getStatistics() {
        return statListener.getStatistics();
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
