package com.epam.jmp.dhontar.factorial;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<BigInteger> {

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
            rightTask.fork();

            return leftTask.compute().multiply(rightTask.join());
        }
    }

    public static BigInteger calculateFactorial(int start, int end) {
        var result = BigInteger.ONE;
        for (var i = start; i <= end; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
