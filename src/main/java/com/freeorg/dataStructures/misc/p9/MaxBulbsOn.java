package com.freeorg.dataStructures.misc.p9;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class MaxBulbsOn {

	public static void main1(String[] args) {
		int[] bulbsArr = new int[] {0};		
		int bulbsToBeSwitchedOn = 1; // K
		
		int[] bulbsArr1 = new int[] {0,1};		
		int bulbsToBeSwitchedOn1 = 1; // K
		
		int[] bulbsArr2 = new int[] {1,0};		
		int bulbsToBeSwitchedOn2 = 1; // K
		
		int[] bulbsArr3 = new int[] {1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0};
		int bulbsToBeSwitchedOn3 = 2; // K
		
		int[] bulbsArr4 = new int[] {1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0};		
		int bulbsToBeSwitchedOn4 = 3; // K
		
		int[] bulbsArr5 = new int[] {0,0};		
		int bulbsToBeSwitchedOn5 = 1; // K
		
		int[] bulbsArr6 = new int[] {0,1};		
		int bulbsToBeSwitchedOn6 = 0; // K
		
		new MaxBulbsOn().findMax(bulbsToBeSwitchedOn6, bulbsArr6);
	}
	
	public static void main(String[] args) {
		MaxBulbsOn maxBulbsOn = new MaxBulbsOn();

		Scanner scanner = null;
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(new File("bulbsOutput.txt")));
			scanner = new Scanner(new File("bulbsInput.txt"));
			int noOfTests = Integer.parseInt(scanner.nextLine());

			for (int i = 1; i <= noOfTests; i++) {

				// 1. Read Input from file
				int totalNoOfBulbs = Integer.parseInt(scanner.nextLine().trim());
				int noOfSwitchedOffBulbs = Integer.parseInt(scanner.nextLine().trim());
				int[] bulbsStates = parseBulbStates(scanner.nextLine().trim().split(" "));

				// 2. Process input
				BulbsOutput bulbsOutput = maxBulbsOn.findMax(noOfSwitchedOffBulbs, bulbsStates);
				bulbsStates = null;

				// 3. Write output
				bufferedWriter.write(Integer.toString(bulbsOutput.maxTillNowCount));
				bufferedWriter.newLine();
				bufferedWriter.write(bulbsOutput.switchedOnBulbPositions.toString());
				bufferedWriter.newLine();
				bufferedWriter.write("######################");
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			scanner.close();			
		}
	}

	private static int[] parseBulbStates(String[] states) {
		int[] bulbsStates = new int[states.length];
		for (int i = 0; i < bulbsStates.length; i++) {
			bulbsStates[i] = Integer.parseInt(states[i]);
		}
		return bulbsStates;
	}

	private BulbsOutput findMax(int bulbsToBeSwitchedOn, int[] bulbsArr) {

		LinkedList<Integer> offBulbsPositions = new LinkedList<>();

		// Initially switch on first K switched off bulbs and note their positions
		for (int i = 0; i < bulbsToBeSwitchedOn; i++) {
			int bulbOffPosition = findNextZeroPosition(i, bulbsArr);
			if(-1 < bulbOffPosition) {
				offBulbsPositions.add(bulbOffPosition);
				// switch on bulb
				bulbsArr[bulbOffPosition] = 1;	
			}
		}

		// Count Max
		int maxTillNowCount = 0;
		int currentSequenceCount = 0;
		int lastBulbOnPosition = 0;
		int firstBulbOnPosition = 0;

		LinkedList<Integer> tempBulbsPositions = new LinkedList<>(offBulbsPositions);
		LinkedList<Integer> switchedOnBulbPositions = new LinkedList<>();

		while ((lastBulbOnPosition < bulbsArr.length) && !tempBulbsPositions.isEmpty()) {
			// find next bulb off position
			int nextBulbOffPosition = findNextZeroPosition(tempBulbsPositions.getLast() + 1, bulbsArr);
			if (-1 < nextBulbOffPosition) {
				offBulbsPositions.add(nextBulbOffPosition);
				currentSequenceCount = nextBulbOffPosition - firstBulbOnPosition;
				if (currentSequenceCount > maxTillNowCount) {
					maxTillNowCount = currentSequenceCount;
					switchedOnBulbPositions.clear();
					switchedOnBulbPositions.addAll(tempBulbsPositions);
				}
				currentSequenceCount = 0;
				firstBulbOnPosition = tempBulbsPositions.remove() + 1;
				bulbsArr[firstBulbOnPosition] = 0;
				tempBulbsPositions.add(nextBulbOffPosition);
				bulbsArr[nextBulbOffPosition] = 1;
				lastBulbOnPosition = nextBulbOffPosition;
			} else {
				lastBulbOnPosition = bulbsArr.length;
				if(switchedOnBulbPositions.isEmpty() && !tempBulbsPositions.isEmpty()) {
					switchedOnBulbPositions.addAll(tempBulbsPositions);
					maxTillNowCount = lastBulbOnPosition -firstBulbOnPosition;
				}				
				tempBulbsPositions.clear();
			}
		}
		
		return new BulbsOutput(maxTillNowCount, switchedOnBulbPositions);
	}
	
	private int findNextZeroPosition(int i, int[] bulbsArr) {
		while (i < bulbsArr.length) {
			if (bulbsArr[i] == 0) {
				return i;
			}
			i++;
		}
		return -1;
	}
}

class BulbsOutput {

	int maxTillNowCount; 
	LinkedList<Integer> switchedOnBulbPositions;

	public BulbsOutput(int maxTillNowCount, LinkedList<Integer> switchedOnBulbPositions) {
		this.maxTillNowCount = maxTillNowCount;
		this.switchedOnBulbPositions = switchedOnBulbPositions;
	}
}
