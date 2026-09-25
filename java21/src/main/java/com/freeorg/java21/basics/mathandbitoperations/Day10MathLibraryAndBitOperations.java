package com.freeorg.java21.basics.mathandbitoperations;

import java.math.BigInteger;
import java.net.http.HttpClient;
import java.util.Optional;

public class Day10MathLibraryAndBitOperations {

    int parseAndSumSafely(String[] numStrings) {
        int sum = 0;
        for (String number : numStrings) {
            try {
                sum = Math.addExact(sum , Integer.parseInt(number));
            } catch (NumberFormatException | ArithmeticException e) {
                System.out.println("Invalid or very big input : " + number);
            }
        }
        return sum;
    }

    boolean isPowerOfTwo(int n){
        return n > 0 && 0 == (n & (n-1));
    }

    int countSetBits(int n) {
       return Integer.bitCount(n);
    }

    // The idiom: multiply, round, divide back
    double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    int gcdUsingBigInt(int a, int b) {
        return BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue();
    }

    int[] swapWithoutTemp(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        return new int[]{a,b};
    }

    boolean isEvenBitwise(int n) {
        return  (n & 1) == 0;
    }

    int fastMultiplyByPowerOfTwo(int n, int power) {
        return n << power;
    }

    Integer safeParseOrDefault(String s, int defaultVal) {
        Optional<Integer> parsed;
        try {
            parsed = Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException nfe) {
            parsed = Optional.empty();
        }
        return parsed.orElse(defaultVal);
    }
}

