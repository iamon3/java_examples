package com.freeorg.java21.datastructures;

import java.util.Map;
import java.util.stream.Collectors;

public class DuplicateCharsUsingStreams {

    public static void main(String[] args) {
        String input = "My name is Ajit";

        Map<Character, Long> charCounts = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        System.out.println(charCounts);
    }
}
