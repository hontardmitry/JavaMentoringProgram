package com.epam.jmp.dhontar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.epam.jmp.dhontar.cache.LRUGuavaCacheConfig;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class LRUGuavaCacheTest {

    @Test
    public void checkPutAll() {
        var cache = LRUGuavaCacheConfig.getCache();

        var cacheEntriesMap = new HashMap<String, String>();
        cacheEntriesMap.put("1", "FIRST");
        cacheEntriesMap.put("2", "SECOND");
        cache.putAll(cacheEntriesMap);

        assertEquals(2, cache.size());
    }

    @Test
    public void whenGetNotPresentInCache_thenUseLoader() throws ExecutionException {
        LoadingCache<String, String> cache = LRUGuavaCacheConfig.getCache();
        assertEquals(cache.get("100"), "Value100");
    }

    @Test
    public void whenCacheSizeExceeded_thenEvict() throws ExecutionException {
        LoadingCache<String, String> cache = LRUGuavaCacheConfig.getCache();
        for (var i = 0; i < 100000; i++) {
            var key = Integer.toString(i);
            cache.put(key, "Value" + i);
            if (i != 0) {
                for (var j = 0; j < 10; j++) {
                    cache.get(key);
                }
            }
        }
        assertTrue(cache.stats().evictionCount() > 0);
    }

    @Test
    public void whenScheduleIsTriggered_thenEvict() throws InterruptedException {
        LoadingCache<String, String> cache = LRUGuavaCacheConfig.getCache();
        var cacheEntriesMap = new HashMap<String, String>();
        for (var i = 0; i < 100; i++) {
            cacheEntriesMap.put(Integer.toString(i), "Value" + i);
        }
        cache.putAll(cacheEntriesMap);

        Thread.sleep(6000);

        assertTrue(cache.stats().evictionCount() > 0);
    }
}
