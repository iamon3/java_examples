package com.freeorg.java8.lambdaExpression;

import java.util.Comparator;
import java.util.List;

public class Eg3EffiiciencyByLazyEval {

  // Write a method to find out the maximum odd number in the range 3 to 11 
  // and return square of it.
  public static void main(String[] args) {
    
  }
  
  // One way - Older
  private static int findSquareOfMaxOdd(List<Integer> numbers) {
    int max = 0;
    for (int i : numbers) {
      if (i % 2 != 0 && i > 3 && i < 11 && i > max) {
        max = i;
      }
    }
    return max * max;
  }

  public static int findSquareOfMaxOdd1(List<Integer> numbers) {
    return numbers.stream()
        .filter(Eg3EffiiciencyByLazyEval::isOdd)      //Predicate is functional interface and
        .filter(Eg3EffiiciencyByLazyEval::isGreaterThan3) // we are using lambdas to initialize it
        .filter(Eg3EffiiciencyByLazyEval::isLessThan11)   // rather than anonymous inner classes
        .max(Comparator.naturalOrder())
        .map(i -> i * i)
        .get();
  }

  public static boolean isOdd(int i) {
    return i % 2 != 0;
  }

  public static boolean isGreaterThan3(int i){
    return i > 3;
  }

  public static boolean isLessThan11(int i){
    return i < 11;
  }
}
