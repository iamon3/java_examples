package com.freeorg.dataStructures.misc.p9;

import java.util.LinkedList;

public class MaxBulbsOn {

	private int findNextZeroPosition(int i, int[] bulbsArr) {
		while(i<bulbsArr.length) {
			if(bulbsArr[i]==0) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	private void findMax(int bulbsToBeSwitchedOn, int[] bulbsArr) {
		
		LinkedList<Integer> offBulbsPositions = new LinkedList<>();
				
		// Initially switch on first K switched off bulbs and note their positions
		for(int i =0; i< bulbsToBeSwitchedOn; i++) {
			int bulbOffPosition = findNextZeroPosition(i, bulbsArr);
			offBulbsPositions.add(bulbOffPosition);
			// switch on bulb
			bulbsArr[bulbOffPosition] = 1;
		}
		
		// Count Max
		int maxTillNowCount = 0;		
		int currentSequenceCount = 0;
		int lastBulbOnPosition = 0;		
		int firstBulbOnPosition = 0;
		
		LinkedList<Integer> tempBulbsPositions = new LinkedList<>(offBulbsPositions);
		LinkedList<Integer> maxBulbsSwitchedOnSequence = new LinkedList<>();
		
		while(lastBulbOnPosition < bulbsArr.length) {
			// find next bulb off position			
			int nextBulbOffPosition = findNextZeroPosition(tempBulbsPositions.getLast()+1, bulbsArr);
			if(-1 < nextBulbOffPosition) {
				offBulbsPositions.add(nextBulbOffPosition);			
				currentSequenceCount = nextBulbOffPosition - firstBulbOnPosition;
				if(currentSequenceCount > maxTillNowCount) {
					maxTillNowCount = currentSequenceCount;
					maxBulbsSwitchedOnSequence.clear();
					maxBulbsSwitchedOnSequence.addAll(tempBulbsPositions);
				}
				currentSequenceCount = 0;
				firstBulbOnPosition = tempBulbsPositions.remove()+1;
				bulbsArr[firstBulbOnPosition] = 0;
				tempBulbsPositions.add(nextBulbOffPosition);
				bulbsArr[nextBulbOffPosition] = 1;
				lastBulbOnPosition = nextBulbOffPosition;			
			}
			else {
				lastBulbOnPosition = bulbsArr.length;
			}
		}
												
		System.out.println("All Bulbs off position : " + offBulbsPositions);
		System.out.println("Maximum bulbs on in sequnce : "+ maxTillNowCount);
		System.out.println("Max Bulbs On Sequence : " + maxBulbsSwitchedOnSequence);		
	}

	public static void main(String[] args) {
		int[] bulbsArr = new int[] {1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0};
		int bulbsToBeSwitchedOn = 2; // K
		int[] bulbsArr1 = new int[] {1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0};		
		int bulbsToBeSwitchedOn1 = 3; // K
		new MaxBulbsOn().findMax(bulbsToBeSwitchedOn, bulbsArr);
	}
}
