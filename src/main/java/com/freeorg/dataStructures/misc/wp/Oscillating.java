package com.freeorg.dataStructures.misc.wp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Oscillating {

	public static String changedSort(String s)
    {
		Map<Character, Integer> charMap = new HashMap<>();
		List<Character> charsList = new ArrayList<>();
		StringBuilder result = new StringBuilder();
		
		char[] charArr = s.toCharArray();
		Arrays.sort(charArr);
		
		for(Character c : charArr) {
			if(!charMap.containsKey(c)) {
				charMap.put(c, 1);
				charsList.add(c);
				result.append(c);
			}
			else {
				charMap.put(c, charMap.get(c)+1);
			}						
		}
				
		return result.toString();
    }
	
	private static int findSmallest(List<Character> chars) {
		
		return 0;
	}

	public static void main(String[] args) {
		// 

	}

}
