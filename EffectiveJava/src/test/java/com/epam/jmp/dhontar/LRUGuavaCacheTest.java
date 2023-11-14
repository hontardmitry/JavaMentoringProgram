package com.epam.jmp.dhontar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.epam.jmp.dhontar.cache.LRUGuavaCache;
import org.junit.Before;
import org.junit.Test;

public class LRUGuavaCacheTest {

    private static final String key1 = "1";
    private static final String key2 = "2";
    private static final String value1 = "One";
    private static final String value2 = "Two";
    private LRUGuavaCache<String, String> cache;
    private int maxCacheSize;
    private int evictionPeriod;

    @Before
    public void setUp() {
        cache = new LRUGuavaCache<>();
    }

    @Test
    public void whenPut_thenGet() {
        cache.put(key1, value1);
        cache.put(key2, value2);

        assertEquals(value1, cache.getValue(key1));
        assertEquals(value2, cache.getValue(key2));
    }

    @Test
    public void whenGetNotPresentInCache_thenUseLoader() {
        assertEquals(cache.getValue("100"), "Value100");
    }

    @Test
    public void whenCacheSizeExceeded_thenEvict() {
        for (var i = 0; i < maxCacheSize; i++) {
            var key = Integer.toString(i);
            cache.put(key, "Value" + i);
            if (i != 0) {
                for (var j = 0; j < 10; j++) {
                    cache.getValue(key);
                }
            }
        }

        assertTrue(cache.getStatistics().getEvictionCount() > 0);
    }

    @Test
    public void whenScheduleIsTriggered_thenEvict() throws InterruptedException {
        for (var i = 0; i < 100; i++) {
            cache.put(Integer.toString(i), "Value" + i);
        }
        Thread.sleep((evictionPeriod + 1) * 1000L); //wait scheduled eviction

        assertTrue(cache.getStatistics().getEvictionCount() > 0);
    }

}
