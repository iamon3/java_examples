package com.freeorg.java7.binaryLiterals.prefix_0b;

/**
 	Refer : https://docs.oracle.com/javase/7/docs/technotes/guides/language/binary-literals.html

 	In Java SE 7, the integral types (byte, short, int, and long) can also be expressed using the binary number system. 
 	To specify a binary literal, add the prefix 0b or 0B to the number. The following examples show binary literals:

	// An 8-bit 'byte' value:
	byte aByte = (byte)0b00100001;

	// A 16-bit 'short' value:
	short aShort = (short)0b1010000101000101;

	// Some 32-bit 'int' values:
	int anInt1 = 0b10100001010001011010000101000101;
	int anInt2 = 0b101;
	int anInt3 = 0B101; // The B can be upper or lower case.

	// A 64-bit 'long' value. Note the "L" suffix:
	long aLong = 0b1010000101000101101000010100010110100001010001011010000101000101L;
 */
public class TestBinaryLiterals {

	// You can use binary literals to make a bitmap more readable:
	public static final short[] HAPPY_FACE = {
			(short)0b0000011111100000,
			(short)0b0000100000010000,
			(short)0b0001000000001000,
			(short)0b0010000000000100,
			(short)0b0100000000000010,
			(short)0b1000011001100001,
			(short)0b1000011001100001,
			(short)0b1000000000000001,
			(short)0b1000000000000001,
			(short)0b1001000000001001,
			(short)0b1000100000010001,
			(short)0b0100011111100010,
			(short)0b0010000000000100,
			(short)0b0001000000001000,
			(short)0b0000100000010000,
			(short)0b0000011111100000
	};

	/**
	 In JDK 7, you can express literal values in binary with prefix ‘0b’ (or ‘0B’) for integral types (byte, short, int and long). 
	 Before JDK 7, you can only use octal values (with prefix ‘0’) or hexadecimal values (with prefix ‘0x’ or ‘0X’). 
	
	 e.g. int sameVarOne = 0b01010000101;
			
		or if use the number formatting feature as well.
		int sameVarTwo = 0B01_010_000_101;
	 */
	public static void main(String[] args) {
		int binary = 0b1000; //2^3 = 8
        if (binary == 8){
            System.out.println(true);
        } else{
            System.out.println(false);
        }
	}
}
