package com.epam.jmp.dhontar;

import static com.epam.jmp.dhontar.util.Constants.CACHE_MAX_SIZE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.epam.jmp.dhontar.cache.LFUCache;
import com.epam.jmp.dhontar.statistics.StatisticListener;
import com.epam.jmp.dhontar.util.LogUtil;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.concurrent.Executors;

public class LFUCacheTest {
    private static final Logger LOGGER = LogUtil.getLogger();
    private LFUCache<String, String> cache;

    @Before
    public void setUp() {
        cache = new LFUCache<>();
    }

    @Test
    public void testPutAndGet() {
        cache.put("1", "One");
        cache.put("2", "Two");

        assertEquals("One", cache.get("1").getValue());
        assertEquals("Two", cache.get("2").getValue());
    }

    @Test
    public void testEvictionHappens() {
        fillCache(0, CACHE_MAX_SIZE);
        for (int i = 1; i < CACHE_MAX_SIZE; i++) { //emulate usages except of 0 item
            cache.get(Integer.toString(i));
        }
        // Add a new item to trigger eviction
        cache.put(Integer.toString(CACHE_MAX_SIZE), "Value100001");

        assertNull(cache.get("0")); // "2" should be evicted
        assertEquals("Value1", cache.get("1").getValue()); // "1" should still be in cache
        assertEquals("Value100001", cache.get(Integer.toString(CACHE_MAX_SIZE)).getValue());

    }
//TODO replace with eviction
    @Test
    public void testUsageCount() {
        cache.put("1", "One");
        cache.put("2", "Two");
        LFUCache<String, String>.CacheEntry entry1 = cache.get("1");
        LFUCache<String, String>.CacheEntry entry2 = cache.get("2");

        assertEquals("One", entry1.getValue());
        assertEquals("Two", entry2.getValue());

        // Access "2" multiple times to increase its usage count
        cache.get("2");
        cache.get("2");

        assertEquals(1, entry1.getUsageCount().get());
        assertEquals(3, entry2.getUsageCount().get());

        cache.put("3", "Three");
        cache.put("4", "Four");
        var entry3 = cache.get("3");
        var entry4 = cache.get("4");

        createAndRunThreadsWithGet(10, 100, "3");
        createAndRunThreadsWithGet(1, 1000, "4");

        LOGGER.info("Entry 3 usages: " + entry3.getUsageCount());
        LOGGER.info("Entry 4 usages: " + entry4.getUsageCount());
    }

    private void createAndRunThreadsWithGet(int threads, int getsPerThread, String key) {
//TODO rewrite with Executors don't forget to join threads
        for (var i = 0; i < threads; i++) {
            Thread th = new Thread(() -> {
                for (var j = 0; j < getsPerThread; j++) {
                    cache.get(key);
                }
            });
            th.start();
        }
    }
//TODO remove
    @Test
    public void testStatistics() {
        StatisticListener stats = cache.getStatistics();

        fillCache(0, CACHE_MAX_SIZE);
        System.out.println(cache.size());
        fillCache(CACHE_MAX_SIZE, 1000);
        System.out.println(cache.size());

        LOGGER.info("avg put time: " + stats.getAvgPutTime());
        LOGGER.info("puts: " + stats.getPutsCount());
        LOGGER.info("getEvictionCount: " + stats.getEvictionCount());
    }

    private void fillCache(int startIndex, int recordsCount) {
        for (var i = startIndex; i < startIndex + recordsCount; i++) {
            cache.put(Integer.toString(i), "Value" + i);
        }
    }
}
