package com.epam.jmp.dhontar.statistics.listener;

import com.epam.jmp.dhontar.statistics.LFUStatistics;

public interface ICacheListener {

    void onEvict();

    void onPut(long startTime, long endTime);

    LFUStatistics getStatistics();
}
