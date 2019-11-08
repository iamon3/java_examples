package com.freeorg.java6.exceptions;

public class ErrorTest {

	public static void main(String[] args) {
		try {
			devide(12, 0);
		}
		catch (ArithmeticException e) {
			System.out.println(e.toString());
		}
		System.out.println("Could be processed.");
		try {			
		errorDemo();
		}
		catch(Error err) {
			System.out.println("Caught error.");
		}	
		System.out.println(10+2);
	}

	static private int devide(int a, int b) throws RuntimeException{
		return a/b;
	}
	
	static private void errorDemo() {
		throw new StackOverflowError();
	}
}
