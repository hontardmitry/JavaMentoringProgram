package com.epam.jmp.dhontar.sorting;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class SortingFJP {

    public static final int SORT_THRESHOLD = 100;
    public static final int SIZE = 1000;

    protected static class QuickSortAction extends RecursiveAction {
        private final int low;
        private final int high;
        private int[] arr;

        public QuickSortAction(int[] arr, int low, int high) {
            this.low = low;
            this.high = high;
            this.arr = arr;
        }

        public QuickSortAction(int[] arr) {
            this(arr, 0, arr.length - 1);
        }

        private static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        private static int partition(int[] arr, int low, int high) {
            int pivot = arr[high];
            int i = (low - 1);

            for (int j = low; j <= high - 1; j++) {
                if (arr[j] < pivot) {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
            return (i + 1);
        }

        @Override
        protected void compute() {
            if (low < high) {
                if (arr.length < SORT_THRESHOLD) {
                    arr = Arrays.stream(arr).sorted().toArray();

                }
                int pi = partition(arr, low, high);
                var actionLow = new QuickSortAction(arr, low, pi - 1);
                var actionHigh = new QuickSortAction(arr, pi + 1, high);

                invokeAll(actionLow, actionHigh);
            }
        }
    }

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

        var sortingSeqResult = (isSorted(arr, arr.length)) ? "successfully" : "not successfully";
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
