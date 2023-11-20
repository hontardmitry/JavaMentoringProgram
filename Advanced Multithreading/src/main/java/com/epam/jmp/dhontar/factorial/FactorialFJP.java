package com.epam.jmp.dhontar.factorial;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FactorialFJP extends RecursiveTask<BigInteger> {

    private final int start;
    private final int end;

    public FactorialFJP(int number) {
        this(1, number);
    }

    public FactorialFJP(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected BigInteger compute() {
        if (end - start <= 10) { // Sequential threshold
            return calculateFactorial(start, end);
        } else {
            int mid = (start + end) / 2;
            FactorialFJP leftTask = new FactorialFJP(start, mid);
            FactorialFJP rightTask = new FactorialFJP(mid + 1, end);

            ForkJoinTask.invokeAll(leftTask, rightTask);
            return leftTask.join().multiply(rightTask.join());
        }
    }

    private static BigInteger calculateFactorial(int start, int end) {
        var result = BigInteger.ONE;
        for (var i = start; i <= end; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static void main(String[] args) {

        int n = 100;

        // Sequential calculation
        long startTime = System.nanoTime();
        ForkJoinTask<BigInteger> task = new FactorialFJP(n);
        BigInteger parallelResult = ForkJoinPool.commonPool().invoke(task);
        long sequentialTime = System.nanoTime() - startTime;

        // Parallel calculation
        startTime = System.nanoTime();
        BigInteger sequentialResult = calculateFactorial(1, n);
        long parallelTime = System.nanoTime() - startTime;

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
