package com.freeorg.java8.lambdaExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Eg2PassBehaviorToMethods {

  public static void main(String[] args) {
    List<Integer> numbers = new ArrayList<Integer>();
    numbers.add(2);
    numbers.add(3);
    numbers.add(6);
    numbers.add(7);
    
    //sum of all numbers
    System.out.println("sum of all numbers : " +sumWithCondition(numbers, n -> true));
    //sum of all even numbers
    System.out.println("sum of all even numbers : " + sumWithCondition(numbers, i -> i%2==0));
    //sum of all numbers greater than 5
    System.out.println("sum of all numbers greater than 5 : " + sumWithCondition(numbers, i -> i>5));  
  }
  
  public static int sumWithCondition(List<Integer> numbers, Predicate<Integer> predicate) {
    return numbers.parallelStream()
            .filter(predicate)
            .mapToInt(i -> i)
            .sum();
  }
}
