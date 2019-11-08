package com.freeorg.dataStructures.misc.wp;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

	public static List<Integer> mergeArrays(List<Integer> a, List<Integer> b) {
		// Write your code here
		List<Integer> c = new ArrayList<Integer>(a.size() + b.size());				
		for(int i=0, j=0, k =1; k<= a.size() + b.size(); k++) {
			if(i< a.size() && j < b.size()) {
				if(a.get(i) < b.get(j)) {
					c.add(a.get(i));
					i++;
				}
				else {
					c.add(b.get(j));
					j++;
				}				
			}
			else if(i < a.size()) {
				c.add(a.get(i));
				i++;
			}
			else {
				c.add(b.get(j));
				j++;
			}

		}
		return c;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
