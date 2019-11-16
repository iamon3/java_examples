package com.freeorg.dataStructures.misc.cbro;

public class WordCount {

	public static int solution(String S) {
		int maxWordsCount = 0;
		int currentWordCount = 0;
		char previousChar = ' ';
		for(char currChar : S.toCharArray()) {
			if(currChar == '.' || currChar == '?' || currChar== '!') {
				if(previousChar !=' '&& previousChar != '.' && previousChar !='?' && previousChar != '!') {
					currentWordCount += 1;	
				}
				if(currentWordCount > maxWordsCount) {
					maxWordsCount = currentWordCount;
					currentWordCount = 0;
				}				
			}
			else if(currChar == ' ') {
				if(previousChar !=' '&& previousChar != '.' && previousChar !='?' && previousChar != '!') {
					currentWordCount += 1;	
				}
			}
			previousChar = currChar;
		}
		return maxWordsCount;
	}

	// 
	public static void main(String[] args) {
		String t1  = "We test coders. Give us a try?";
		String t2 = "Forget  CVs..Save time . x x";
		System.out.println(solution(t2));
	}

}
