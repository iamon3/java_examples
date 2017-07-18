package com.freeorg.dataStructures.patricia.streamsearch;

public interface InputCharSource {
	Character getNextChar();
	boolean isNext();
}
