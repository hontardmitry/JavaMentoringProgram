package com.epam.jmp.dhontar;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.epam.jmp.dhontar.cache.LFUCache;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class LFUCacheTest {

    private LFUCache<String, String> cache;
    private int maxCacheSize;
    private int evictionPeriod;

    @Before
    public void setUp() {
        cache = new LFUCache<>();
        maxCacheSize = cache.getMaxCacheSize();
        evictionPeriod = cache.getEvictionPeriod();
    }

    @Test
    public void whenPut_thenGet() {
        cache.put("1", "One");
        cache.put("2", "Two");

        assertEquals("One", cache.getValue("1"));
        assertEquals("Two", cache.getValue("2"));
    }

    @Test
    public void whenCacheSizeExceeded_thenEvict() {
        fillCache(0, maxCacheSize);
        for (int i = 1; i < maxCacheSize; i++) { //emulate usages except of 0 item
            cache.getValue(Integer.toString(i));

        }
        // Add a new item to trigger eviction
        cache.put(Integer.toString(maxCacheSize), "Value100001");

        assertNull(cache.getValue("0")); // should be evicted
        assertNotNull(cache.getValue("1"));// should still be in cache
        assertEquals("Value100001", cache.getValue(Integer.toString(maxCacheSize)));

    }
    @Test
    public void whenAddedExisting_thenNotEvict() {
        var replacingKey = "1";
        var newValue = "Value3";

        fillCache(0, maxCacheSize);
        for (int i = 1; i < maxCacheSize; i++) { //emulate usages except of 0 item
            cache.getValue(Integer.toString(i));

        }
        // Add a new item to trigger eviction
        cache.put(replacingKey, newValue);

        assertNotNull(cache.getValue("0")); // should be evicted
        assertEquals(newValue, cache.getValue(replacingKey));// should still be in cache
    }

    @Test
    public void testCorrectEvictionDetectionWithMultiThread() throws Exception {
        String key1 = "1";
        String key2 = "2";
        String value1 = "One";
        String value2 = "Two";
        cache.put(key1, value1);
        cache.put(key2, value2);
        var threads = 5;
        var getsCount = 1000;
        int perThread = getsCount / threads;

        performGetsSimultaniuslyFromThreads(threads, perThread, key1);
        performGets(cache, key2, getsCount - 1);
        waitForScheduledEvict(); //wait for scheduled evict

        assertNull(cache.getValue(key2));
    }

    private void performGetsSimultaniuslyFromThreads(int threads, int perThread, String key)
            throws InterruptedException {
        var executorService = Executors.newFixedThreadPool(threads);

        CountDownLatch readyThreadCounter = new CountDownLatch(threads);
        CountDownLatch callingThreadBlocker = new CountDownLatch(1);

        var tasks = Stream
                .generate(() -> getTask(cache, key, perThread, readyThreadCounter, callingThreadBlocker))
                .limit(threads)
                .collect(toList());

        tasks.forEach(executorService::execute);

        readyThreadCounter.await();
        callingThreadBlocker.countDown();

        executorService.shutdown();
    }

    private void fillCache(int startIndex, int count) {
        for (var i = startIndex; i < startIndex + count; i++) {
            cache.put(Integer.toString(i), "Value" + i);
        }
    }

    private void waitForScheduledEvict() {
        try {
            Thread.sleep((evictionPeriod + 1) * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Runnable getTask(LFUCache<String, String> cache,
                             String key,
                             int getsPerThread,
                             CountDownLatch readyThreadCounter,
                             CountDownLatch callingThreadBlocker) {
        return () -> {
            readyThreadCounter.countDown();
            try {
                callingThreadBlocker.await();
                performGets(cache, key, getsPerThread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    private void performGets(LFUCache<String, String> cache, String key, int getsPerThread) {
        for (var i = 0; i < getsPerThread; i++) {
            cache.getValue(key);
        }
    }

}
