package com.freeorg.dataStructures.misc.amsys;

public class PhoneNumberFormat {

	public String solution(String S) {
		
		StringBuilder reformattedPhoneNumber = new StringBuilder(); 
		StringBuilder tempSb = new StringBuilder();
		
		for(Character c : S.toCharArray()) {
			if(Character.isDigit(c)) {				
				tempSb.append(c);
			}
			if(tempSb.length() == 3) {
				reformattedPhoneNumber.append(tempSb.toString()).append("-");
				tempSb.setLength(0);
			}
		}
		
		if(tempSb.length() > 0) {
			if(2 == tempSb.length()) {
				reformattedPhoneNumber.append(tempSb.toString());
			}
			else if(1 == tempSb.length()) {
				if(0 == reformattedPhoneNumber.length()) {
					reformattedPhoneNumber.append(tempSb.toString());
				}
				else {
					Character lastDigit = reformattedPhoneNumber.charAt(reformattedPhoneNumber.length()-2);
					reformattedPhoneNumber.deleteCharAt(reformattedPhoneNumber.length()-2);					
					reformattedPhoneNumber.append(lastDigit).append(tempSb.toString());
				}
			}
		}
		else {
			reformattedPhoneNumber.deleteCharAt(reformattedPhoneNumber.length()-1);
		}
		
		tempSb = null;
		return reformattedPhoneNumber.toString();
    }
	
	
	public static void main(String[] args) {
		PhoneNumberFormat s = new PhoneNumberFormat();
		String s1 = "00-44  48 5555 8361";
		String s2 = "0 - 22 1985--324";
		String s3 = "555372654";
		
		System.out.println(s.solution(s1));
		System.out.println(s.solution(s2));
		System.out.println(s.solution(s3));
	}

}
