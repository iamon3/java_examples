package com.freeorg.dataStructures.misc.cbro;

import java.util.Arrays;

public class Ranks {

	public static int solution(int[] ranks) {
		Arrays.parallelSort(ranks);
		int reportCount = 0;
		int bs = 0, be = 0;
		for(int i=1; i<ranks.length; i++) {
			if(ranks[i] != ranks[be]) {
				if(ranks[i] == 1+ranks[be]) {
					reportCount += (1+be-bs);					
				}
				bs = be = i;
			}
			else {
				be++;
			}
		}
		return reportCount;
	}
	public static void main(String[] args) {
		int[] i1 = new int[] {3,4,3,0,2,2,3,0,0};
		int[] i2 = new int[] {4,2,0};
		int[] i3 = new int[] {4,4,3,3,1,0};
		System.out.println(solution(i1));
		System.out.println(solution(i2));
		System.out.println(solution(i3));
	}

}
