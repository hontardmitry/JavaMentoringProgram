package com.epam.jmp.dhontar.factorial;

import static com.epam.jmp.dhontar.factorial.FactorialFJP.FactorialTask.calculateFactorial;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FactorialFJP {

    public static final int N = 1000;

    public static void main(String[] args) {
        // Sequential calculation
        var startTime = System.nanoTime();
        BigInteger parallelResult = ForkJoinPool.commonPool().invoke(new FactorialTask(N));
        var sequentialTime = System.nanoTime() - startTime;

        // Parallel calculation
        startTime = System.nanoTime();
        BigInteger sequentialResult = calculateFactorial(1, N);
        var parallelTime = System.nanoTime() - startTime;

        System.out.println("Sequential Result: " + sequentialResult);
        System.out.println("Parallel Result: " + parallelResult);
        System.out.println("Sequential Time: " + formatInts(sequentialTime) + " nanoseconds");
        System.out.println("Parallel Time: " + formatInts(parallelTime) + " nanoseconds");
    }

    protected static class FactorialTask extends RecursiveTask<BigInteger> {
        private final int start;
        private final int end;

        public FactorialTask(int number) {
            this(1, number);
        }

        public FactorialTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected BigInteger compute() {
            if (end - start <= 10) { // Sequential threshold
                return calculateFactorial(start, end);
            } else {
                var mid = (start + end) / 2;
                var leftTask = new FactorialTask(start, mid);
                var rightTask = new FactorialTask(mid + 1, end);

                ForkJoinTask.invokeAll(leftTask, rightTask);
                return leftTask.join().multiply(rightTask.join());
            }
        }

        protected static BigInteger calculateFactorial(int start, int end) {
            var result = BigInteger.ONE;
            for (var i = start; i <= end; i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            return result;
        }
    }

    private static String formatInts(long value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }
}
