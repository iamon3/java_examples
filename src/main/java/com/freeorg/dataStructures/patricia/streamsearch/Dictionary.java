package com.freeorg.dataStructures.patricia.streamsearch;

public interface Dictionary {
    void initDictionary(String[] input);   
	void addWord(String word);
	void searchWords(String pattern);
	int getLongestWordLength();
}
