package com.epam.jmp.dhontar.statistics;

import static com.epam.jmp.dhontar.util.LogUtil.getLogger;

import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class StatisticListener<K> implements IEvictListener<K>, IPutListener {
    private static final Logger LOGGER = getLogger();
    private final AtomicLong totalPutTime = new AtomicLong();
    private final AtomicInteger putsCount = new AtomicInteger();
    private final AtomicInteger evictionCount = new AtomicInteger();

    @Override
    public void onEvict(K key) {
        this.evictionCount.incrementAndGet();
        LOGGER.info(String.format("Evicted: %s", key.toString()));
    }

    @Override
    public void onPut(long startTime, long endTime) {
        totalPutTime.addAndGet(endTime - startTime);
        putsCount.incrementAndGet();
    }

    public long getAvgPutTime() {
        return totalPutTime.get() / putsCount.get();
    }

    public int getEvictionCount() {
        return evictionCount.get();
    }
}
