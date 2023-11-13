package com.epam.jmp.dhontar.statistics;

public class Statistics {

    private final long avgPutTime;
    private final int evictionCount;

    public Statistics(long totalPutTime, int putsCount, int evictionCount) {
        this.avgPutTime = totalPutTime / putsCount;
        this.evictionCount = evictionCount;
    }

    public long getAvgPutTime() {
        return avgPutTime;
    }

    public int getEvictionCount() {
        return evictionCount;
    }
}
