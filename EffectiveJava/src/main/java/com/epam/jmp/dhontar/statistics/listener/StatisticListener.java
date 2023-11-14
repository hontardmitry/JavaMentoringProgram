package com.epam.jmp.dhontar.statistics.listener;

import com.epam.jmp.dhontar.statistics.LFUStatistics;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class StatisticListener implements ICacheListener {

    private final AtomicLong totalPutTime = new AtomicLong();
    private final AtomicInteger putsCount = new AtomicInteger();
    private final AtomicInteger evictionCount = new AtomicInteger();

    @Override
    public void onEvict() {
        evictionCount.getAndIncrement();
    }

    @Override
    public void onPut(long startTime, long endTime) {
        totalPutTime.getAndAdd(endTime - startTime);
        putsCount.getAndIncrement();
    }

    @Override
    public LFUStatistics getStatistics() {
        return new LFUStatistics(totalPutTime.get(), putsCount.get(), evictionCount.get());
    }
}
