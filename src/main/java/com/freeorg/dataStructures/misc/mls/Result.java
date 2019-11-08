package com.freeorg.dataStructures.misc.mls;

import java.util.ArrayList;
import java.util.List;

public class Result {

	public static List<String> missingWorkds(String s, String t){
		
		ArrayList<String> result = new ArrayList<>();
		String[] ss = s.split(" ");
		String[] tt = t.split(" ");
		int i = 0;			
		for(String word : ss) {
			if(i< tt.length) {
				if(!tt[i].equals(word)) {
					result.add(word);
				}
				else {
					i++;
				}
				
			}else {
				result.add(word);
			}						
		}
		
		return result;
	}
	public static void main(String[] args) {
		String s = "I am using HackerRank to improve programming";
		String t = "am HackerRank to improve";
		System.out.println(missingWorkds(s, t));
	}

}
