package com.freeorg.java21.basics.stringandcharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Day2StringCharArraysConversions {
    public static void main(String[] args) {
        Day2StringCharArraysConversions stringUtilDay2 = new Day2StringCharArraysConversions();
        stringUtilDay2.sortCharsInString("hackereart");
        stringUtilDay2.splitAndTrim(" apple, banana ,cherry ");
        stringUtilDay2.toCharList("Hello World");
        List l = new ArrayList();
    }

    String sortCharsInString(String s) {
        System.out.println("Input => " + s);
        if (s == null || s.isEmpty()) {
            return s;
        }
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        System.out.println("Sorted string => " + new String(chars));
        return new String(chars);
    }

    String[] splitAndTrim(String csv) {
        System.out.println("After Split and Trim => \"" + csv + "\"");
        if (null == csv || csv.isEmpty()) {
            return new String[0];
        }
        String[] result = Arrays.stream(csv.split(","))
                .map(token -> token.trim())
                .toArray(value -> new String[value]); // OR use constuctor reference .toArray(String[]::new);
        System.out.println("After Split and Trim => " + Arrays.toString(result));
        return result;
    }

    /**
     * Why this trips people up (worth internalizing): String.chars() → IntStream → elements are int (code points), NOT char.
     * This is a genuinely common Java gotcha because it feels like it should give you a Stream<Character> directly,
     * but it doesn't — historical reasons (char is only 16-bit, code points can need more).
     * Any time you call .chars() on a String, remember: you're in IntStream land,
     * and getting back to char/Character needs an explicit (char) cast inside mapToObj.
     */
    List<Character> toCharList(String s) {
        System.out.println("Before charList => " + s);
        if (null == s || s.isEmpty()) {
            return Collections.emptyList();
        }

        List<Character> charsList = s.chars()
                .mapToObj(c -> (char) c)
                .collect(toList());
        System.out.println("After toCharList => " + charsList);
        return charsList;
    }

    String fromCharArray(char[] chars){
        if(null == chars || 0 == chars.length){
            return "";
        }


        // Option 1 : Using String constructor
        return new String(chars);

        // Option 2 : Using a static factory method
        // return String.valueOf(chars);
    }
}
