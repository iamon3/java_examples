package com.freeorg.java21.dynamicprgramming;

public class LongestPalindrome {

    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();

        String result = "";
        int start = 0 ;
        int end = 0;
        int maxLength = 0;

        System.out.println("Start => " + s.substring(start, s.length()));
        for(int length = s.length(); length > 1; length-- ){
            for(start=0, end = start + length - 1 ; end < s.length(); start++, end++){
                if( isPalindrome(start, end, chars) && (end - start + 1) > maxLength){
                    System.out.println("Found => " + s.substring(start, end));
                    maxLength = end - start + 1;
                }
            }
        }

        return s.substring(start, end);
    }

    boolean isPalindrome(int i, int j, char[] string){
        while(i < j){
            if(string[i] != string[j]){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println("Input babad . Output: " +  longestPalindrome.longestPalindrome("babad"));
        System.out.println("Input cbbd . Output: " +  longestPalindrome.longestPalindrome("cbbd"));
    }
}
