package com.freeorg.dataStructures.misc.here;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tankers {
	// (2,5), 12
	// [1,2][6,0]

	// (6,9,20), 44
	// [1,2,1][4,0,1]
	private static void printVariations(int[] tankersArr, int oilQuantity) {
		Integer[] temp = new Integer[tankersArr.length];		
		boolean result = print(tankersArr, temp, 0, oilQuantity);				
	}

	private static boolean print2(int[] tankersArr, Integer[] temp, int i, int oilQuantity) {
		//System.out.println("i : " + i + " Qty : " + oilQuantity + " temp[] : " + Arrays.toString(temp));

		if (i < tankersArr.length) {
			if (i == tankersArr.length - 1) {
				if (0 == oilQuantity % tankersArr[i]) {
					temp[i] = oilQuantity / tankersArr[i];
					System.out.print(printArr(temp));
					return true;
				}
				return false;
			}
			int q=0, r = 0;
			do {
				temp[i] = q;
				r = oilQuantity - (q * tankersArr[i]);
				boolean result = print2(tankersArr, temp, i + 1, r);				
			}
			while(q * tankersArr[i] < oilQuantity);		
		}
		return false;
	}
	
	private static boolean print(int[] tankersArr, Integer[] temp, int i, int oilQuantity) {
		//System.out.println("i : " + i + " Qty : " + oilQuantity + " temp[] : " + Arrays.toString(temp));

		if (i < tankersArr.length) {
			int q = oilQuantity / tankersArr[i];
			int r = oilQuantity % tankersArr[i];
			if (i == tankersArr.length - 1) {
				if (0 == r) {
					temp[i] = q;
					System.out.print(printArr(temp));
					return true;
				}
				return false;
			}
			for (; q >= 0; q--) {
				temp[i] = q;
				r = oilQuantity - (q * tankersArr[i]);
				boolean result = print(tankersArr, temp, i + 1, r);
			}
		}
		return false;
	}

	private static String printArr(Integer[] temp) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(Integer i : temp) {
			sb.append(i).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}

	public static void main(String[] args) {
		int[] tankersArr1 = new int[] { 6, 9, 20 };
		int oilQuantity1 = 44;
		int[] tankersArr = new int[] {2, 5};
		int oilQuantity = 12;
		printVariations(tankersArr, oilQuantity);

		/*
		 * String tankersArrString = "(6,9,20)"; StringBuilder sb = new
		 * StringBuilder(tankersArrString); sb.deleteCharAt(0);
		 * sb.deleteCharAt(sb.length()-1); String[] numbersString =
		 * sb.toString().split(","); int[] tankersArr = new int[numbersString.length];
		 * for(int i =0; i < numbersString.length; i++) { tankersArr[i] =
		 * Integer.parseInt(numbersString[i]); }
		 * System.out.println(Arrays.toString(tankersArr));
		 */
	}

}
