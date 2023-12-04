package com.epam.jmp.dhontar.future.task6;

import static org.junit.Assert.assertTrue;

import com.epam.jmp.dhontar.task6.raction.SquareOfDoubles;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class SquareOfDoublesTest {

    private static final int ARRAY_LENGTH = 500_000_000;
    private final static double[] DOUBLES = new double[ARRAY_LENGTH];

    @BeforeClass
    public static void fillArray() {
        var random = new Random();
        for (var i = 0; i < ARRAY_LENGTH; i++) {
            DOUBLES[i] = random.nextDouble();
        }
    }

    @Test
    public void calcAndCheckSqr() {
        var start = System.currentTimeMillis();
        var parallelResult = sumOfSquares(ForkJoinPool.commonPool(), DOUBLES);
        var timeForFjp = System.currentTimeMillis() - start;
        System.out.printf("Calculated with FJP in %s ms\n", timeForFjp);
        System.out.println(parallelResult);

        start = System.currentTimeMillis();
        Arrays.stream(DOUBLES).map(d -> d * d).sum();
        var timeForStream = System.currentTimeMillis() - start;
        System.out.printf("Calculated with Stream API in %s ms\n", timeForStream);

        assertTrue(timeForFjp < timeForStream);
    }


    private double sumOfSquares(ForkJoinPool pool, double[] array) {
        SquareOfDoubles a = new SquareOfDoubles(array, 0, ARRAY_LENGTH, null);
        pool.invoke(a);
        return a.getResult();
    }
}
