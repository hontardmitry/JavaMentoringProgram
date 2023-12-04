package com.epam.jmp.dhontar.task6.rtask;

import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Long> {

    private final static long[] FIRST_TEN = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
    private final int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    public Long compute() {
        if (n <= 10) {
            return getFromFirstTen(n);
        }
            var f1 = new Fibonacci(n - 1);
            f1.fork();
            var f2 = new Fibonacci(n - 2);
            return f2.compute() + f1.join();
    }

    private Long getFromFirstTen(int x) {
        return FIRST_TEN[x];
    }
}