package com.freeorg.dataStructures.misc.d11;

import java.util.List;

public class NoOfPaths {

	public static int numberOfPaths(List<List<Integer>> a) {
		// Write your code here
		return calculatePaths(0,0, a);
	}

	private static int calculatePaths(int i, int j, List<List<Integer>> a) {
		int paths = 0;
		if(i < a.size() && j < a.get(0).size()) {
			if(i == a.size()-1 && j == a.get(0).size()-1) {
				return 1;
			}
			if(a.get(i).get(j) == 0) {
				return 0;
			}
			return 1+ calculatePaths(i+1, j, a) + calculatePaths(i, j+1, a);
		}
		return paths;
	}

	public static void main(String[] args) {	

	}

}
