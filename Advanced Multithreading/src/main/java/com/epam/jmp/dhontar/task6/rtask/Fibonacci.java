package com.epam.jmp.dhontar.task6.rtask;

import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {

    private final static int[] firstTen = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
    final int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 10) {
            return getFromFirstTen(n);
        }
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            return f2.compute() + f1.join();
    }

    private Integer getFromFirstTen(int x) {
        return firstTen[x];
    }
}