package com.freeorg.dataStructures.misc.d11;

import java.util.ArrayList;
import java.util.List;

public class DiscountPrice {

    public static void finalPrice(List<Integer> prices) {
    	int totalCost = 0;    	
    	StringBuilder fullPriceIndices = new StringBuilder();
    	int i =0;
    	for(; i < prices.size()-1; i++) {
    		int firstLowPriceAtRight = findLowPrice(prices.get(i), i+1, prices);
    		if(firstLowPriceAtRight == 0 ) {
    			totalCost += prices.get(i);
    			fullPriceIndices.append(i).append(" ");
    		}
    		else {
    			totalCost += (prices.get(i)-firstLowPriceAtRight);
    		}
    	}    	
    	System.out.println("End Index : " + i);
    	System.out.println(totalCost+prices.get(i));    	
    	System.out.println(fullPriceIndices.append(i).toString());
    }
        
	private static int findLowPrice(int currentPrice, int startPosition, List<Integer> prices) {
		for(int j = startPosition; j< prices.size(); j++) {
			if(currentPrice <= prices.get(j)) {
				return prices.get(j);
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> l = new ArrayList<>();
		
		l.add(5);
		l.add(1);
		l.add(3);
		l.add(4);
		l.add(6);
		l.add(2);
		finalPrice(l);
	}

}
