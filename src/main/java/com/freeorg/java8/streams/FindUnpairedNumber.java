package com.freeorg.java8.streams;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

class FindUnpairedNumber {
    public int solution(int[] A) {
        // Implement your solution here
       if (A == null) {
           return -1;
       }
       return Arrays.stream(A)
                .boxed()
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet()
                .stream()
                .filter( entry -> entry.getValue() % 2 == 1)
                .map(Map.Entry::getKey)
                .findFirst()
               .orElse(-1);

    }

    public static void main(String[] args) {
        FindUnpairedNumber s = new FindUnpairedNumber();
        System.out.println(s.solution(new int[]{9,3,9,3,9,7,9}));
    }
}