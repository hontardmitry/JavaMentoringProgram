package com.epam.jmp.dhontar;

import static com.epam.jmp.dhontar.util.Constants.CACHE_MAX_SIZE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.epam.jmp.dhontar.cache.LFUCache;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class LFUCacheTest {
    private LFUCache<String, String> cache;

    @Before
    public void setUp() {
        cache = new LFUCache<>();
    }

    @Test
    public void testPutAndGet() {
        cache.put("1", "One");
        cache.put("2", "Two");

        assertEquals("One", cache.get("1"));
        assertEquals("Two", cache.get("2"));
    }

    @Test
    public void testEvictionOnOverflow() {
        fillCache(0, CACHE_MAX_SIZE);
        for (int i = 1; i < CACHE_MAX_SIZE; i++) { //emulate usages except of 0 item
            cache.get(Integer.toString(i));

        }
        // Add a new item to trigger eviction
        cache.put(Integer.toString(CACHE_MAX_SIZE), "Value100001");

        assertNull(cache.get("0")); // should be evicted
        assertNotNull(cache.get("1"));// should still be in cache
        assertEquals("Value100001", cache.get(Integer.toString(CACHE_MAX_SIZE)));

    }
    @Test
    public void testCorrectEvictionDetectionWithMultiThread() {
        String key1 = "1";
        String key2 = "2";
        cache.put(key1, "One");
        cache.put(key2, "Two");

        createAndRunThreadsWithGet(10, 1000, key1);
        createAndRunThreadsWithGet(1, 999, key2);
        waitSeconds(6); //wait for scheduled evict

        assertNull(cache.get(key2));
    }

    private void createAndRunThreadsWithGet(int threadsCount, int getsCount, String key) {
        var executorService = Executors.newFixedThreadPool(threadsCount);
        CountDownLatch latch = new CountDownLatch(threadsCount);
        for (var i = 0; i < getsCount; i++) {
            executorService.execute(() -> cache.get(key));
            latch.countDown();
        }
        executorService.shutdown();
    }

    private void fillCache(int startIndex, int recordsCount) {
        for (var i = startIndex; i < startIndex + recordsCount; i++) {
            cache.put(Integer.toString(i), "Value" + i);
        }
    }

    private void waitSeconds(int seconds){
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
