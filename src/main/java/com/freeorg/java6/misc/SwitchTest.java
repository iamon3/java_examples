package com.freeorg.java6.misc;

public class SwitchTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 2;
		byte b = 0b0111_1111;
		float fl = 25.75067f;
		byte a = 127;
		System.out.println(b);
		System.out.println(a);
		switch (x) {
		case 1:
			System.out.println("1");
			break;
		case 3:
			System.out.println("3");
			break;
		default:
			System.out.println("default");
			break;
		}
	}

}
