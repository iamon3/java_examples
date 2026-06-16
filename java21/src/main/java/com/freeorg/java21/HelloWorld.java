package com.freeorg.java21;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, Java 21!");

        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 85);
        scores.put("Bob", 95);
        scores.put("Charlie", 80);
        scores.put("David", 90);

/*        TreeMap<Integer, Map.Entry<String, Integer>> scoresEntriesByValueTreeMap = new TreeMap<>();
        for(Map.Entry<String, Integer> score : scores.entrySet()){
            scoresEntriesByValueTreeMap.put(score.getValue(), score);
        }
        scores.clear();

        System.out.println("Intermediate treemap = " + scoresEntriesByValueTreeMap);

        for (Map.Entry<Integer, Map.Entry<String, Integer>> scoreEntry: scoresEntriesByValueTreeMap.entrySet()){
            scores.put(scoreEntry.getValue().getKey(), scoreEntry.getValue().getValue());
        }

        System.out.println("Result using option 1 = " + scores);*/


        Map<String, Integer> sortedScores = scores.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        TreeMap::new
                ));

//
        System.out.println("Sorted Result = " + sortedScores);
    }
}
