package com.freeorg.java21.basics.arrays;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Day4MultiDimensionalArrayOperations {
    public static void main(String[] args) {
        Day4MultiDimensionalArrayOperations arrayUtil = new Day4MultiDimensionalArrayOperations();
        arrayUtil.createJaggedArray(3);
        arrayUtil.columnSums(new int[][]{
                {1, 2, 3},
                {1, 2, 3},
                {1, 2, 3}
        });
        arrayUtil.mergeRows(new int[][]{
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8}
        });
        arrayUtil.deepCopy2D(new int[][]{
                {1, 2, 3},
                {5, 6}
        });

        arrayUtil.isMagicSquareRowSum(new int[][]{
                {1, 2, 3},
                {3, 3},
                {1, 1, 1, 1, 1, 1}
        }, 6);
        arrayUtil.flattenAndChunk(new int[]{1,2,3,4,5,6}, 2);
    }

    int[][] createJaggedArray(int rows) {
        System.out.println("Create Jagged array of rows => " + rows);
        int[][] array = IntStream.range(0, rows)
                .mapToObj(rowNumber -> {
                    int[] row = new int[rowNumber + 1];
                    Arrays.fill(row, rowNumber + 1);
                    return row;
                })
                .toArray(int[][]::new);
        System.out.println("After creating jagged array => " + Arrays.deepToString(array));
        return array;
    }

    int[] columnSums(int[][] matrix) {
        System.out.println("Before column sum Input matrix => " + Arrays.deepToString(matrix));
        int[] colSumArr = IntStream.range(0, matrix[0].length)
                .map(col ->
                        IntStream.range(0, matrix.length)
                                .map(row -> matrix[row][col])
                                .sum()
                )
                .toArray();
        System.out.println("After Column Sum array => " + Arrays.toString(colSumArr));
        return colSumArr;
    }

    int[][] mergeRows(int[][] matrix) {
        System.out.println("Before merge rows Input matrix => " + Arrays.deepToString(matrix));
        int[][] mergedArray = IntStream.iterate(0, row -> (row < matrix.length), row -> {
                    return row + 2;
                })
                .mapToObj(row -> {
                            return IntStream.range(0, matrix[row].length)
                                    .map(col -> matrix[row][col] + matrix[row + 1][col])
                                    .toArray();
                        }
                )
                .toArray(int[][]::new);
        System.out.println("After merge rows" + Arrays.deepToString(mergedArray));
        return mergedArray;
    }

    int[][] deepCopy2D(int[][] matrix) {
        System.out.println("Before Deep Copy Array => " + Arrays.deepToString(matrix));
        int[][] copiedArray = Arrays.stream(matrix)
                .map(row -> Arrays.stream(row).toArray())
                .toArray(value -> new int[value][]);
        System.out.println("After Deep Copy Array => " + Arrays.deepToString(copiedArray));
        return copiedArray;
    }

    boolean isMagicSquareRowSum(int[][] matrix, int target) {
        System.out.println("Before magic square calculation target sum => " + target + ", Array => " + Arrays.deepToString(matrix));
        boolean anyMismatch = Arrays.stream(matrix)
                .map(row -> Arrays.stream(row).sum())
                .filter(rowSum -> rowSum != target)
                .findFirst().isEmpty();
        System.out.println("After magic square seach => " + anyMismatch);
        return anyMismatch;
    }

    int[][] flattenAndChunk(int[] flat, int chunkSize) {
        System.out.println("Input chunkSize => " + chunkSize + ", flat => " + Arrays.toString(flat));
        int[][] chunkedArr = IntStream.iterate(0, row -> row < flat.length, row -> row + chunkSize)
                .mapToObj(row -> IntStream.range(row, row + chunkSize)
                        .map(i -> flat[i])
                        .toArray())
                .toArray(int[][]::new);
        System.out.println("Flatten and Chunked 2d Array => " + Arrays.deepToString(chunkedArr));
        return chunkedArr;
    }
}
