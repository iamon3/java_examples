package com.freeorg.java21.streams;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Given an array find the smallest missing positive number
 * arr[] = {2, -3, 4, 1, 1, 7}
 * <p>
 * answer = 3
 *
 */

public class FindMinMissingPositive {

    int findMinMissingPositiveStream(int[] arr){
        System.out.println("Starting stream stuff => " + Arrays.toString(arr));

        int[] positiveElementsSortedArr = Arrays.stream(arr)
                .filter( e ->  e > 0)
                .sorted()
                .distinct()
                .toArray();

        // Count how many elements form a perfect sequence (1, 2, 3...) starting from index 0.
        // The missing number is simply the count of correct elements + 1.
        return (int) IntStream.iterate(0, i -> i < positiveElementsSortedArr.length && positiveElementsSortedArr[i] == i + 1, i -> i + 1)
                .count() + 1;
    }


    /**
     * Performs a reduction on the elements of this stream, using the provided identity value and an associative accumulation function, and returns the reduced value. This is equivalent to:
     *
     * int result = identity;
     * for (int element : this stream)
     *     result = accumulator.applyAsInt(result, element)
     * return result;
     *
     *
     * but is not constrained to execute sequentially.
     * The identity value must be an identity for the accumulator function. This means that for all x, accumulator.apply(identity, x) is equal to x. The accumulator function must be an associative function.
     * This is a terminal operation.
     * Params:
     * identity – the identity value for the accumulating function
     * op – an associative, non-interfering, stateless function for combining two values
     * Returns:
     * the result of the reduction
     * API Note:
     * Sum, min and max are all special cases of reduction that can be expressed using this method. For example, summing a stream can be expressed as:
     *
     * int sum = integers.reduce(0, (a, b) -> a+b);
     *
     * or more compactly:
     *
     * int sum = integers.reduce(0, Integer::sum);
     *
     * While this may seem a more roundabout way to perform an aggregation compared to simply mutating a running total in a loop, reduction operations parallelize more gracefully, without needing additional synchronization and with greatly reduced risk of data races
     *
     * @param arr
     * @return
     */
    int findMinMissingPositiveStreamConcise(int[] arr){
        System.out.println("Starting stream stuff => " + Arrays.toString(arr));

        /**
         * Instead of collecting to an array and then iterating again, you can use reduce with an identity value of 1 (the first expected positive integer). The accumulator function will check if the current element matches the expected number:
         * •
         * If it matches, it increments the expected number.
         * •
         * If it doesn't match (meaning there is a gap), it keeps the current expected number.
         * <p>
         * Note: reduce() will iterate over ALL elements. It does not short-circuit when the gap is found.
         * */
        return Arrays.stream(arr)
                .filter(e -> e > 0)
                .sorted()
                .distinct()
                .reduce(1, (expected, element) -> element == expected ? expected + 1 : expected);
    }


    public static void main(String[] args) {
        FindMinMissingPositive s = new FindMinMissingPositive();
        System.out.println("Answer 1 => " + s.findMinMissingPositiveStream(new int[]{2, -3, 4, 1, 1, 7}));
        System.out.println("Answer 2 => " + s.findMinMissingPositiveStreamConcise(new int[]{2, -3, 4, 1, 1, 7}));
    }
}
