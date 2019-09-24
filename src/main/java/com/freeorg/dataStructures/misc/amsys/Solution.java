package com.freeorg.dataStructures.misc.amsys;

public class Solution {

	private static final int COUNTRY_VISITED_FLAG = -2_000_000_000;

	public int solution(int[][] A) {	
				
		int countries = 0;

		for(int i = 0; i< A.length; i++) 
		{
			for (int j = 0;j < A[0].length;j++) {
				if( A[i][j] != COUNTRY_VISITED_FLAG) {
					countries +=1;
					visitCountryFrom(i,j, A[i][j], A);
				}								
			}
		}
		return countries;
	}	

	private void visitCountryFrom(int i, int j, int countryValue, int[][] A) {

		if (i < 0 || j < 0 || i >= A.length || j >= A[0].length || A[i][j] == COUNTRY_VISITED_FLAG) {
			return;
		}	

		if(A[i][j] == countryValue) {
			// visit the area of country
			A[i][j] = COUNTRY_VISITED_FLAG;
			visitCountryFrom(i, j+1, countryValue, A);
			visitCountryFrom(i+1, j, countryValue, A);		
			visitCountryFrom(i-1, j, countryValue, A);
			visitCountryFrom(i, j-1, countryValue, A);
		}					
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * Solution s = new Solution(); int[][] A = {{5, 4, 4}, {4, 3, 4}, {3, 2, 4},
	 * {2, 2, 2}, {3, 3, 4}, {1, 4, 4}, {4, 1, 1}};
	 * System.out.println(s.solution(A)); }
	 */
}
