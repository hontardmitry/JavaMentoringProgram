package com.epam.jmp.dhontar.statistics;

public interface ICacheListener {
    void onEvict();
    void onPut(long startTime, long endTime);
}
