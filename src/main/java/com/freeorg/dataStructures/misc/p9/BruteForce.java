package com.freeorg.dataStructures.misc.p9;

public class BruteForce {

	public static void main(String[] args) {
		int[] bulbsArr = new int[] {1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0};
		int bulbsToBeSwitchedOn = 2; // K
		int totalBulbs = 11;		 // N
		
		System.out.println(new BruteForce().findLongestConsecutiveSeriesOfBulbs(bulbsToBeSwitchedOn, bulbsArr));
	}

	private int findLongestConsecutiveSeriesOfBulbs(int bulbsToBeSwitchedOn, int[] bulbsArr) {
		
		int seqStartPosition = -1;
		int seqEndPosition = -1;
		int maxTillNowCount = 0;
		
		int currentSeqStart = -1;
		int currentSeqEnd = -1;	
		int currentSequenceCount = 0;
		for(int i=0; i< bulbsArr.length; i++) {
			System.out.println("Index => "+i);
			if(bulbsArr[i]==1) {
				// if it is starting new sequence
				if(-1 == currentSeqEnd || 0 == bulbsArr[i-1]) {
					System.out.println("Starting new sequence : " + i);
					currentSeqStart = i;
					currentSeqEnd = i;
					currentSequenceCount = 1;
				}
				// else if it is next element of a sequence
				else {
					System.out.println("Continuous sequence : " + i);
					currentSequenceCount += 1;
					currentSeqEnd = i;
				}
			}
			else {				
				// if it is breaking a sequence
				if(currentSeqEnd >= 0 && currentSeqEnd == i-1) {
					System.out.println("Breaking sequence. : " + i);
					if(currentSequenceCount > maxTillNowCount) {
						System.out.println("Update max count.");
						maxTillNowCount = currentSequenceCount;
						seqStartPosition = currentSeqStart;
						seqEndPosition = currentSeqEnd;
						
						currentSequenceCount = 0;
					}
				}
				else{
					// else its doing nothing.	
					System.out.println("No sequence at all. Do nothing.");
				}	
			}
		}
		return maxTillNowCount;
	}

}
