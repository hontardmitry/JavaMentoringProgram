package com.epam.jmp.dhontar.statistics;

public abstract class BaseStatistics {

    private final long evictionCount;

    protected BaseStatistics(long evictionCount) {
        this.evictionCount = evictionCount;
    }

    public long getEvictionCount() {
        return evictionCount;
    }
}
