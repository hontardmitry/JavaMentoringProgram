package com.epam.jmp.dhontar;

import static com.epam.jmp.dhontar.util.Constants.CACHE_MAX_SIZE;
import static org.junit.Assert.assertEquals;

import com.epam.jmp.dhontar.cache.LRUGuavaCacheConfig;
import com.google.common.cache.Cache;
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
        var cacheEntriesMap = new HashMap<String, String>();
        for (var i = 0; i < CACHE_MAX_SIZE; i++) {
            cacheEntriesMap.put(Integer.toString(i), "Value" + i);
        }
        cache.putAll(cacheEntriesMap);
        System.out.println(cache.get("Exceeded"));
        for (var i = 0; i < 100; i++) {
            cacheEntriesMap.put(Integer.toString(i + CACHE_MAX_SIZE), "After" + i + CACHE_MAX_SIZE);
        }


    }

    @Test
    public void whenScheduleTriggered_thenEvict() throws ExecutionException, InterruptedException {
        LoadingCache<String, String> cache = LRUGuavaCacheConfig.getCache();
        var cacheEntriesMap = new HashMap<String, String>();
        for (var i = 0; i < 100; i++) {
            cacheEntriesMap.put(Integer.toString(i), "Value" + i);
        }
        cache.putAll(cacheEntriesMap);

        Thread.sleep(10000);


    }
}
