package com.epam.jmp.dhontar.statistics;

public interface IEvictListener<K> {
    void onEvict(K key);
}
