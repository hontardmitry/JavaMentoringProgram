package com.epam.jmp.dhontar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.epam.jmp.dhontar.cache.LFUCache;
import org.junit.Before;
import org.junit.Test;

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
    public void testEviction() {
        // Fill the cache to its maximum capacity
        for (int i = 1; i <= 10000; i++) {
            cache.put(Integer.toString(i), "Value" + i);
        }

        // Access "1" to make it the most frequently used
        cache.get("1");

        // Add a new item to trigger eviction
        cache.put("10001", "Value10001");

        assertNull(cache.get("2")); // "2" should be evicted
        assertEquals("One", cache.get("1")); // "1" should still be in cache
        assertEquals("Value10001", cache.get("10001"));
    }

    @Test
    public void testUsageCount() {
        cache.put("1", "One");
        cache.put("2", "Two");

        assertEquals("One", cache.get("1"));
        assertEquals("Two", cache.get("2"));

        // Access "2" multiple times to increase its usage count
        cache.get("2");
        cache.get("2");

        cache.put("3", "Three"); // Should evict "1" because it's the least frequently used

        assertNull(cache.get("1"));
        assertEquals("Two", cache.get("2"));
        assertEquals("Three", cache.get("3"));
    }
}
