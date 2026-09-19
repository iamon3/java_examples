package com.freeorg.java21.basics.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;


public class Day5CollectionsUtilities {
    public static void main(String[] args) {
        Day5CollectionsUtilities collectionsUtil = new Day5CollectionsUtilities();
        collectionsUtil.getUniqueSorted(
                new int[]{5, 20, 3, 1, 45, 2, 1, 4, 5, 3, 13}
        );
        collectionsUtil.charFrequency("aabbbcdddd");
        collectionsUtil.charFrequencyUsingStream("aabbbcdddd");
        collectionsUtil.topNKeys(
                Map.of("a", 1,
                        "b", 4,
                        "c", 8,
                        "d", 2,
                        "e", 3),
                3
        );
        collectionsUtil.topNKeysUsingStream(
                Map.of("a", 1,
                        "b", 4,
                        "c", 8,
                        "d", 2,
                        "e", 3),
                3
        );
    }

    void swapAndReverse(List<Integer> list) {
        Collections.swap(list, 0, list.size() - 1);
        Collections.reverse(list);
    }

    List<Integer> getUniqueSorted(int[] arr) {
        System.out.println("Before unique Sorted input => " + Arrays.toString(arr));
        Set<Integer> uniqueSet = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.toSet());
        List<Integer> uniqList = new ArrayList<>(uniqueSet);
        Collections.sort(uniqList);
        System.out.println("After uniq sorted list => " + uniqList);
        return uniqList;
    }

    Map<Character, Integer> charFrequency(String s) {
        System.out.println("charFrequency iterative input => " + s);
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.merge(c, 1, (a, b) -> Integer.sum(a, b));
        }
        System.out.println("charFrequenct iterative map  => " + frequencyMap);
        return frequencyMap;
    }

    Map<Character, Integer> charFrequencyUsingStream(String s) {
        System.out.println("charFrequency stream input => " + s);
        Map<Character, Integer> frequencyMap = s.chars()
                .mapToObj(c -> (char) c)
                .collect(toMap(c -> c, c -> 1, (a, b) -> Integer.sum(a, b)));
        System.out.println("charFrequenct stream map  => " + frequencyMap);
        return frequencyMap;
    }

    List<String> topNKeys(Map<String, Integer> map, int n) {
        System.out.println("topNKeys n => " + n + ", map => " + map);
        ArrayList<Map.Entry<String, Integer>> entriesList = new ArrayList<>(map.entrySet());
        Collections.sort(entriesList, Comparator.comparing(entry -> entry.getValue(), Comparator.reverseOrder()));
        List<String> topNSortedKeys = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            topNSortedKeys.add(entriesList.get(i).getKey());
        }
        System.out.println("topNKeys n => " + topNSortedKeys);
        return topNSortedKeys;
    }

    List<String> topNKeysUsingStream(Map<String, Integer> map, int n) {
        System.out.println("topNKeys using stream n => " + n + ", map => " + map);
        List<String> topNSortedKeys = map.entrySet().stream()
                //.sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(e -> e.getKey())
                .limit(n)
                .collect(toList());
        System.out.println("topNKeys n => " + topNSortedKeys);
        return topNSortedKeys;
    }
}
