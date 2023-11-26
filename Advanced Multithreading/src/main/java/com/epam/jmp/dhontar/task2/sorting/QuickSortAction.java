package com.epam.jmp.dhontar.task2.sorting;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class QuickSortAction extends RecursiveAction {

    public static final int SORT_THRESHOLD = 100;

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
            } else {
                int pi = partition(arr, low, high);
                var actionLow = new QuickSortAction(arr, low, pi - 1);
                var actionHigh = new QuickSortAction(arr, pi + 1, high);

                invokeAll(actionLow, actionHigh);
            }
        }
    }
}