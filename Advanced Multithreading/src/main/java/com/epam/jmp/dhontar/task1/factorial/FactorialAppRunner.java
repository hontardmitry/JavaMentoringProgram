package com.epam.jmp.dhontar.task1.factorial;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.concurrent.ForkJoinPool;

public class FactorialAppRunner {
    public static final int N = 1000;
    public static void main(String[] args) {
        // Sequential calculation
        var startTime = System.nanoTime();
        BigInteger parallelResult = ForkJoinPool.commonPool().invoke(new FactorialTask(N));
        var sequentialTime = System.nanoTime() - startTime;

        // Parallel calculation
        startTime = System.nanoTime();
        BigInteger sequentialResult = FactorialTask.calculateFactorial(1, N);
        var parallelTime = System.nanoTime() - startTime;

        System.out.println("Sequential Result: " + sequentialResult);
        System.out.println("Parallel Result: " + parallelResult);
        System.out.println("Sequential Time: " + formatInts(sequentialTime) + " nanoseconds");
        System.out.println("Parallel Time: " + formatInts(parallelTime) + " nanoseconds");
    }

    private static String formatInts(long value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }
}
