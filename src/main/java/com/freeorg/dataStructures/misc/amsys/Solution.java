package com.freeorg.dataStructures.misc.amsys;

import java.util.HashMap;
import java.util.Map;

public class Solution {

	public static String solution(String[] A) {

		Map<Object, Object> map = new HashMap<>();
		StringBuilder result = new StringBuilder();
		String[] queryParts = null;

		for(String query: A) {
			queryParts = query.split(":");
			if(0 < queryParts.length) {
				switch (queryParts[0]) {
				case "i":
					if(3 == queryParts.length) {
						map.put(queryParts[1], queryParts[2]);	
					}				
					break;
				case "j":
					if(2 == queryParts.length) {
						if(map.containsKey(queryParts[1])) {
							result.append(map.get(queryParts[1])).append(" ");
						}
						else
							result.append(-1).append(" ");
					}
					break;
				case "k":
					result.append(map.size()).append(" ");
					break;
				case "l":
					if(2 == queryParts.length) {
						map.remove(queryParts[1]);
					}
					break;
				default:
					break;
				}
			}
			queryParts = null;
		}

		return result.toString();
	}

	public static void main(String[] args) {
		String[] input1 = new String[] {"i:1:66", "j:5", "k"};
		String[] input2 = new String[] {"i:1:2", "i:66:3", "j:66", "l:1","k"};
		String[] input3 = new String[] {"i:1:2", "i:66:3", "j:66", "l:1", "k", "i:1:66", "j:5", "k"};		
		System.out.println(solution(input1));
	}
}
