package com.epam.jmp.dhontar.cache;

import static com.epam.jmp.dhontar.util.Constants.CACHE_MAX_SIZE;

import com.epam.jmp.dhontar.util.LogUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LRUGuavaCacheConfig {
    private static final Logger LOGGER = LogUtil.getLogger();

    private LRUGuavaCacheConfig() {}

    public static LoadingCache<String, String> getCache(){
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(CACHE_MAX_SIZE)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .removalListener((RemovalListener<String, String>) n -> {
                    if (n.wasEvicted()) {
                        LOGGER.info("Item {} : {} was removed.", n.getKey(), n.getValue());
                    }
                })
                .expireAfterAccess(5, TimeUnit.SECONDS)
                .recordStats()
                .build(new CacheLoader<>() {
                    @Override
                    public @NotNull String load(@NotNull String key) {
                        return "Value" + key;
                    }
                });

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread th = new Thread(runnable);
            th.setDaemon(true);
            return th;
        });
        scheduler.scheduleAtFixedRate(cache::cleanUp, 5, 5, TimeUnit.SECONDS);
        return cache;
    }
}
