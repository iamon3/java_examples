package com.freeorg.dataStructures.misc.qlys;

public class Result {

	public static String breakPalindrome(String s) {
		// Write your code here		
		char[] cs = s.toCharArray();
		for(int i=0 ; i< cs.length; i++) {
			for(char ch = 'a'; ch <= 'z' ; ch++ ) {				
				char old = cs[i];
				cs[i] = ch;				
				String tempS = new String(cs);
				if(!isPalindrome(tempS)) {
					return tempS;
				}		
				cs[i] = old;
			}
		}
		return "IMPOSSIBLE";
	}
	
    static boolean isPalindrome(String str) 
    { 
        int i = 0, j = str.length() - 1; 
        while (i < j) { 
            if (str.charAt(i) != str.charAt(j)) 
                return false; 
            i++; 
            j--; 
        }    
        return true; 
    } 
	
	public static void main(String[] args) {
		System.out.println(breakPalindrome("bab"));

	}

}
