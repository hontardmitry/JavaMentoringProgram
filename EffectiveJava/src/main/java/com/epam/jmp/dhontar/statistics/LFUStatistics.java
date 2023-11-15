package com.epam.jmp.dhontar.statistics;

public class LFUStatistics extends BaseStatistics{

    private final long avgPutTime;

    public LFUStatistics(long totalPutTime, long putsCount, long evictionCount) {
        super(evictionCount);
        this.avgPutTime = totalPutTime / putsCount;
    }

    public long getAvgPutTime() {
        return avgPutTime;
    }
}
