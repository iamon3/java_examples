package com.freeorg.dataStructures.misc.d11;

import java.util.List;

public class PlusMult {

	public static String plusMult(List<Integer> A) {
		int rEven = performCalculation(0, A);
		int rOdd = performCalculation(1, A);
		if(rEven > rOdd) {
			return "EVEN";
		}
		else if(rEven < rOdd) {
			return "ODD";
		}
		return "NEUTRAL";
	}

	private static int performCalculation(int i, List<Integer> A) {
		int result =A.get(i);        
        boolean addCalulation = false; // false i.e. multiply
        i +=2;
        while(i<A.size()) {
            if(!addCalulation) {
                result *= A.get(i); 
            }
            else {
                result += A.get(i);
            }
            i+=2;
            addCalulation = !addCalulation;
        }
        return result % 2;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
