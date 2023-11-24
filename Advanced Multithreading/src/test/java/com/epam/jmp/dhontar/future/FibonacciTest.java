package com.epam.jmp.dhontar.future;

import com.epam.jmp.dhontar.task6.rtask.Fibonacci;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

public class FibonacciTest {

    @Test
    public void fibonacciOfZero() {
        calcFib(0);
    }

    @Test
    public void fibonacciOfOne() {
        calcFib(1);
    }

    @Test
    public void fibonacciOfTen() {
        calcFib(10);
    }

    @Test
    public void fibonacciOfEleven() {
        calcFib(11);
    }

    @Test
    public void fibonacciOfLarge() {
        calcFib(50);
    }

//    @Test
//    public void fibonacciOfNull() {
//        ForkJoinPool.commonPool().invoke(new Fibonacci(null));
//    }

    private void calcFib(int n) {
        long start = System.currentTimeMillis();
        var result = ForkJoinPool.commonPool().invoke(new Fibonacci(n));
        System.out.printf("Calculated in %s  ms\n", System.currentTimeMillis() - start);
        System.out.printf("The %sth fibonacci element is: %s%n", n, result);
    }
}
