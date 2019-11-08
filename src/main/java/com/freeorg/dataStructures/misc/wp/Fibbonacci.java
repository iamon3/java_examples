package com.freeorg.dataStructures.misc.wp;

import java.util.ArrayList;
import java.util.List;

public class Fibbonacci {


	public static List<Integer> fibonacci(int n) {
		// Write your code here
		List<Integer> fibSequence = new ArrayList<>();
		fibSequence.add(0);   
		if(n == 1) {
			return fibSequence;
		}
		fibSequence.add(1);	
		if (n == 2) {
			return fibSequence;
		}

		for(int i=3; i<=n; i++) {
			System.out.println(fibSequence.size());
			fibSequence.add(fibSequence.get(fibSequence.size()-1) + fibSequence.get(fibSequence.size()-2));
		}	

		return fibSequence;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(fibonacci(4));
	}

}
