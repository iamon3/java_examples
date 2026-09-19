package com.freeorg.java21.basics.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Day6CollectionsUtils {

    public static void main(String[] args) {
        Day6CollectionsUtils collectionsUtils = new Day6CollectionsUtils();
        System.out.println("." +
                collectionsUtils.sortMapByKey(Map.of("rahul", 10,
                        "ajit", 25,
                        "pankaj", 12)));
        collectionsUtils.firstNUniqueInOrder(new int[]{4, 2, 4, 1, 2, 7}, 3);
        collectionsUtils.rotateList(Arrays.asList(1,2,3,4,5), 2);

    }

    Map<String, Integer> sortMapByKey(Map<String, Integer> map) {
        System.out.println("Sort using TreeMap => " + map);
        TreeMap<String, Integer> mapSortedByKeys = new TreeMap<String, Integer>();
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            mapSortedByKeys.put(e.getKey(), e.getValue());
        }
        System.out.println("TreeMap sorted by keys => " + mapSortedByKeys);
        return mapSortedByKeys;
    }

    List<Integer> firstNUniqueInOrder(int[] arr, int n) {
        System.out.println("First " + n + " unique integer in array => " + Arrays.toString(arr));
        LinkedHashSet<Integer> firstUniqueN = new LinkedHashSet<>(n);
        int count = 0;
        for (int i = 0; i < arr.length && count < n; i++) {
            if (firstUniqueN.add(arr[i])) {
                count++;
            }
        }
        System.out.println("First " + n + " unique integer =>  " + firstUniqueN);
        return new LinkedList<Integer>(firstUniqueN);
    }

    void rotateList(List<Integer> list, int k) {
        System.out.println("Rotate list => " + list + "by " + k);
        Collections.rotate(list, k);
        System.out.println("After right rotation " + list );
        Collections.rotate(list, -k);
        System.out.println("After left rotation " + list );
    }

    int findMaxUsingCollections(List<Integer> list){
        return Collections.max(list);
    }

    int findMinUsingCollections(List<Integer> list){
        return Collections.min(list);
    }

    Integer findMaxCustom(List<String> words) {
        Comparator<String> stringLengthComparator = (s1, s2) -> Integer.compare(s1.length(), s2.length());
        return Collections.max(words, stringLengthComparator).length();
    }
}
