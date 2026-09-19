package com.freeorg.java21.basics.stringandcharacter;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Day1StringAndStringBuilderMethods {

    public static void main(String[] args) {
        Day1StringAndStringBuilderMethods stringUtilDay1 = new Day1StringAndStringBuilderMethods();
        stringUtilDay1.reverse("the sky is blue");
        stringUtilDay1.isPalindrome("A man, a plan, a canal: Panama");
        stringUtilDay1.runLengthEncode("aaabbc");
        stringUtilDay1.countVowelsAndConsonants("Hello World");
        stringUtilDay1.areAnagrams("Dormitory!", "Dirty room");
        stringUtilDay1.alphanumericReverseUsingStringIteration("Hello World");
    }

    public boolean areAnagrams(String s1, String s2) {
        System.out.println("Are anagrams .=>  " + s1 + " , " + s2);
        if (s1 == null || s2 == null) {
            return false;
        }

        int[] counts = new int[256]; // Covers full ASCII (letters, digits, etc.)

        for (char c : s1.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                counts[Character.toLowerCase(c)]++;
            }
        }

        for (char c : s2.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                counts[Character.toLowerCase(c)]--;
            }
        }

        for (int count : counts) {
            if (count != 0) {
                System.out.println("False");
                return false;
            }
        }
        System.out.println("True");
        return true;
    }

    void isPalindrome(String s) {
        System.out.println(s);
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                System.out.println("Is palindrome => " + (left > right || left == right) + " Left = "+left + ", right = " + right);
                break;
            }
            left++;
            right--;
        }
        System.out.println("Is palindrome => " + (left > right || left == right));
    }

    private String reverse(String s) {
        System.out.println("Before reversing : " + s);

        String reversedString = Arrays.stream(s.trim().split("\\s+"))
                .collect(toList())
                .reversed().stream()
                .collect(Collectors.joining(" "));

        System.out.println("After reversing : " + reversedString);
        return reversedString;
    }

    public void countVowelsAndConsonants(String input) {
        System.out.println("Count vowels and consonents in => " + input);
        int vowels = 0;
        int consonents = 0;
        for(int i=0; i< input.length(); i++){
            if(Character.isLetter(input.charAt(i))){
                if("aeiouAEIOU".indexOf(input.charAt(i)) != -1) {
                    vowels++;
                }
                else {
                    consonents++;
                }
            }
        }
        System.out.println("Vowels => " + vowels + " , consonents =>  " + consonents);
    }

    public String runLengthEncode(String input){
        System.out.println("Before run length encoding => " + input);
        StringBuilder result = new StringBuilder();
        if(Objects.nonNull(input) && !input.isEmpty()){
            char lastChar = input.charAt(0);
            int count = 1;

            for(int i=1; i < input.length(); i++){
                if(input.charAt(i) == lastChar){
                    count += 1;
                }
                else {
                    result.append(lastChar).append(count);
                    lastChar = input.charAt(i);
                    count = 1;
                }
            }
            result.append(lastChar).append(count);
        }
        System.out.println("After run length encoding => " + result.toString());
        return result.toString();
    }

    private String alphanumericReverseUsingStringIteration(String input) {
        System.out.println("Before alphanumeric reverse => " + input);
        StringBuilder sb = new StringBuilder();
        for (int i = input.length(); i > 0; i--) {
            if (Character.isLetterOrDigit(input.charAt(i - 1))) {
                sb.append(input.charAt(i - 1));
            }
        }
        System.out.println("After alphanumeric reverse => " + sb.toString());
        return sb.toString();
    }

    private String alphanumericReverseUsingCharArray(String input) {
        System.out.println("Before alphanumeric reverse => " + input);
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        System.out.println("After alphanumeric reverse => " + sb.reverse().toString());
        return sb.reverse().toString();
    }

    public String alphanumericReverseUsingStream(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> Character.isLetterOrDigit(c))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .reverse()
                .toString();
    }
}
