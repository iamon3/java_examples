package com.freeorg.dataStructures.misc.amsys;

public class MaxThreeDigit {

	public int solution(int[] A) {
		
		int maxResult = -10_001;
		
		for(int n : A) {			
			if(isThreeDigitInteger(n) &&  n > maxResult) {
				maxResult = n;
			}
		}
        return maxResult;
    }
	
	private boolean isThreeDigitInteger(int n) {
		
		return 3 == countDigits(n);
	}

	private int countDigits(int n) {
		if (n == 0) return 0;
		return 1 + countDigits(n/10);
	}

	public static void main(String[] args) {
		// int[] A = {-6, -91, 1011, -100, 84, -22, 000, 1, 473};
		int[] A = {-6, -91, 1011, -100, -84, -22, 000, -1, -473};
		MaxThreeDigit s = new MaxThreeDigit();
		System.out.println(s.solution(A));
	}

}
