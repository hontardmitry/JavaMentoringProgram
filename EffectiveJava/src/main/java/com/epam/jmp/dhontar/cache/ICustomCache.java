package com.epam.jmp.dhontar.cache;

import com.epam.jmp.dhontar.statistics.BaseStatistics;

import java.util.concurrent.ExecutionException;

public interface ICustomCache<K, V> {

    V getValue(K key) throws ExecutionException;

    void put(K ket, V value);

    int getMaxCacheSize();

    int getEvictionPeriod();

    BaseStatistics getStatistics();
}
