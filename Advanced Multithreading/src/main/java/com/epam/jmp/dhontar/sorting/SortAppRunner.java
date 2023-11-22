package com.epam.jmp.dhontar.sorting;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class SortAppRunner {

    public static final int SIZE = 1000;
    public static void main(String[] args) {
        var rd = new Random();
        var arr = new int[SIZE];
        Arrays.stream(arr).forEach(el -> el = rd.nextInt(SIZE));

        var startTime = System.currentTimeMillis();
        ForkJoinPool.commonPool().invoke(new QuickSortAction(arr));
        var parallelTime = System.currentTimeMillis() - startTime;

        var sortingResult = (isSorted(arr, arr.length)) ? "successfully" : "not successfully";
        System.out.printf(">>>>> The given array was sorted %s in ", sortingResult);
        System.out.println("parallel. Used time: " + parallelTime + " ms");

        var arr2 = new int[SIZE];
        Arrays.stream(arr2).forEach(el -> el = rd.nextInt(SIZE));
        startTime = System.currentTimeMillis();
        arr2 = Arrays.stream(arr2).sorted().toArray();
        var sequentialTime = System.currentTimeMillis() - startTime;

        var sortingSeqResult = (isSorted(arr2, arr2.length)) ? "successfully" : "not successfully";
        System.out.printf(">>>>> The given array was sorted %s ", sortingSeqResult);
        System.out.println("with stream. Used time: " + sequentialTime + " ms");

    }

    private static boolean isSorted(int[] array, int length) {
        if (array == null || length < 2)
            return true;
        if (array[length - 2] > array[length - 1])
            return false;
        return isSorted(array, length - 1);
    }
}
