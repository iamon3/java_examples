package com.freeorg.dataStructures.misc.aera;

import java.util.HashMap;
import java.util.Map;

public class ProgrammerString {
    static final Map<Character, Integer> charMap = new HashMap<>();

    static {
        charMap.put('p', 1);
        charMap.put('r', 3);
        charMap.put('o', 1);
        charMap.put('g', 1);
        charMap.put('a', 1);
        charMap.put('m', 2);
        charMap.put('e', 1);
    }

    public static int programmerStrings(String s) {
        Map<Character, Integer> charMap1 = new HashMap<>(charMap);
        int endOne = -1, startTwo = -1;
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            if (charMap1.containsKey(key)) {
                Integer count = charMap1.get(key);
                if (count == 1) {
                    charMap1.remove(key);
                } else {
                    charMap1.put(key, count - 1);
                }
            }
            if (endOne == -1 && charMap1.isEmpty()) {
                endOne = i;
                charMap1 = new HashMap<>(charMap);
                break;
            }
        }
        for (int j = s.length() - 1; j > endOne; j--) {
            char key = s.charAt(j);
            if (charMap1.containsKey(key)) {
                Integer count = charMap1.get(key);
                if (count == 1) {
                    charMap1.remove(key);
                } else {
                    charMap1.put(key, count - 1);
                }
            }
            if (startTwo == -1 && charMap1.isEmpty()) {
                startTwo = j;
                break;
            }
        }
        if (charMap1.isEmpty()) {
            return startTwo - endOne - 1;
        } else
            return -1;
    }
    
    public static void main(String[] args) {
		System.out.println(programmerStrings("programmerprogrammer"));
		System.out.println(programmerStrings("progxrammerrxproxgrammer"));
	}
}