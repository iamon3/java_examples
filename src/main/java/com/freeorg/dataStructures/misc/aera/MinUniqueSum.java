package com.freeorg.dataStructures.misc.aera;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MinUniqueSum {

	public static int getMinimumUniqueSum(List<Integer> arr) {		
		int minSum = 0 ;
		Map<Integer, Integer> sortedMap = new TreeMap<>();

		for(int num : arr) {
			minSum += num; 
			if(!sortedMap.containsKey(num)) {
				sortedMap.put(num, 1);
			}
		}
		return minSum;
	}

	public static int getMinimumUniqueSum1(List<Integer> arr) {
		int minSum = 0;
		Set<Integer> uniqueNumberSet = new HashSet<>();
		int nextUnique = 1;			
		for (int index = 0; index < arr.size(); index++) {

			if (!uniqueNumberSet.contains(arr.get(index))) {
				uniqueNumberSet.add(arr.get(index));
				minSum += arr.get(index);
			} else {
				nextUnique = arr.get(index);
				while (uniqueNumberSet.contains(nextUnique)) {
					nextUnique++;
				}
				arr.set(index, nextUnique);
				uniqueNumberSet.add(nextUnique);
				minSum += nextUnique;
			}
		}
		return minSum;
	}

	public static void main(String[] args) {
		System.out.println(getMinimumUniqueSum(Arrays.asList(new Integer[]{1, 2, 2})));
		System.out.println(getMinimumUniqueSum(Arrays.asList(new Integer[]{2,2,4,5})));
	}
}