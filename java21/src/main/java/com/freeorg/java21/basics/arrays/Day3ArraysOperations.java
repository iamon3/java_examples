package com.freeorg.java21.basics.arrays;

import java.util.Arrays;

public class Day3ArraysOperations {
    public static void main(String[] args) {
        Day3ArraysOperations arraysUtil = new Day3ArraysOperations();
        arraysUtil.removeDuplicates(
                new int[]{4, 2, 2, 7, 1, 4}
        );
        arraysUtil.arraysEqual(
                new int[]{1, 2, 3, 4, 5},
                new int[]{1, 2, 3, 4, 5}
        );

        arraysUtil.binarySearchIndex(
                new int[]{1, 3, 7, 9}
                , 3
        );
        arraysUtil.copyAndExpand(
                new int[]{1, 2, 3, 4, 5},
                10
        );

        arraysUtil.sumJaggedArray(
                new int[][] {
                        {1,2,3},
                        {4},
                        {5,6}
                }
        );
        arraysUtil.sumJaggedArrayUsingFlatMap(
                new int[][] {
                        {1,2,3},
                        {4},
                        {5,6}
                }
        );
        arraysUtil.print2D(new int[][] {
                {1,2,3},
                {4},
                {},
                {5,6}
        });
    }

    int[] removeDuplicates(int[] arr) {
        System.out.println("Before removing duplicate => " + Arrays.toString(arr));
        int[] deDuplicateArray = Arrays.stream(arr)
                .distinct()
                .sorted()
                .toArray();
        System.out.println("After removing duplicate => " + Arrays.toString(deDuplicateArray));
        return deDuplicateArray;
    }

    boolean arraysEqual(int[] a, int[] b) {
        System.out.println("Check if equal => a = " + Arrays.toString(a) + ", b = " + Arrays.toString(b));
        boolean equals = Arrays.equals(a, b);
        System.out.println("Are equal ? => " + equals);
        return equals;
    }

    int binarySearchIndex(int[] sortedArr, int target) {
        System.out.println("Given element => " + target + ", Sorted Array " + Arrays.toString(sortedArr));
        int result = Arrays.binarySearch(sortedArr, target);
        System.out.println("Binary search returned index =>. " + result);
        return result >= 0 ? result : -1;
    }

    int[] copyAndExpand(int[] arr, int newSize) {
        System.out.println("Requested new size => " + newSize + ", Before copy initial array => " + Arrays.toString(arr));
        int[] copiedArray = Arrays.copyOf(arr, newSize);
        System.out.println("New copied array => " + Arrays.toString(copiedArray));
        return copiedArray;
    }

    int sumJaggedArray(int[][] jagged) {
        System.out.println("Before mapping to Int Array => " + Arrays.toString(Arrays.stream(jagged).map(row -> Arrays.toString(row)).toArray()));
        Integer sum = Arrays.stream(jagged)
                .mapToInt(row -> (int) Arrays.stream(row)
                        .sum()
                )
                .sum();
        System.out.println("Sum => " + sum);
        return sum;
    }

    int sumJaggedArrayUsingFlatMap(int[][] jagged) {
        System.out.println("Before using flatMap Array => " + Arrays.toString(Arrays.stream(jagged).map(row -> Arrays.toString(row)).toArray()));
        int sum = Arrays.stream(jagged)
                .flatMapToInt(row -> Arrays.stream(row))
                .sum();
        System.out.println("After using flatMap Sum => " + sum);
        return sum;
    }

    void print2D(int[][] matrix){
        System.out.println("Print2D matrix => " + Arrays.deepToString(matrix));
    }
}
