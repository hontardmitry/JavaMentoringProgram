package com.epam.jmp.dhontar.future.task6;

import static junit.framework.TestCase.assertEquals;

import com.epam.jmp.dhontar.task6.rtask.Fibonacci;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;

public class FibonacciTest {

    @Test
    public void fibonacciOfZero() {
        checkFib(0, 0);
    }

    @Test
    public void fibonacciOfTen() {
        checkFib(10, 55);
    }

    @Test
    public void fibonacciOfEleven() {
        checkFib(11, 89);
    }

    @Test
    public void fibonacciOfTestFromTask() {
        checkFib(45, 1134903170L);
    }

    private void checkFib(int n, long expectedResult) {
        long start = System.currentTimeMillis();
        long result = ForkJoinPool.commonPool().invoke(new Fibonacci(n));
        System.out.printf("Calculated in %s  ms\n", System.currentTimeMillis() - start);
        assertEquals(Optional.of(expectedResult), Optional.of(result));
        System.out.printf("The %sth fibonacci element is: %s%n", n, result);
    }
}
