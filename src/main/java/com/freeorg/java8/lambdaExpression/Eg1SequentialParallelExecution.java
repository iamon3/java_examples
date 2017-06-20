package com.freeorg.java8.lambdaExpression;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class Eg1SequentialParallelExecution {

  public static void main(String[] args) {
    System.out.println("Is Prime : " + isPrime(11));    
  }

  private static boolean isPrime(int number) {
    IntPredicate isDivisible = index -> number % index == 0;
    return number > 1 && IntStream.range(2, number).noneMatch(isDivisible);
  }
}
