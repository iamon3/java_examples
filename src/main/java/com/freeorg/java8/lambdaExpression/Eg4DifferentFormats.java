package com.freeorg.java8.lambdaExpression;

public class Eg4DifferentFormats {

  /**
   * 
    () -> {}                     // No parameters; void result
    () -> 42                     // No parameters, expression body
    () -> null                   // No parameters, expression body
    () -> { return 42; }         // No parameters, block body with return
    () -> { System.gc(); }       // No parameters, void block body

    // Complex block body with multiple returns
    () -> {
      if (true) return 10;
      else {
        int result = 15;
        for (int i = 1; i < 10; i++)
          result *= i;
        return result;
     }
    }                          

    (int x) -> x+1             // Single declared-type argument
    (int x) -> { return x+1; } // same as above
    (x) -> x+1                 // Single inferred-type argument, same as below
    x -> x+1                   // Parenthesis optional for single inferred-type case

    (String s) -> s.length()   // Single declared-type argument
    (Thread t) -> { t.start(); } // Single declared-type argument
    s -> s.length()              // Single inferred-type argument
    t -> { t.start(); }          // Single inferred-type argument
    (int x, int y) -> x+y      // Multiple declared-type parameters
    (x,y) -> x+y               // Multiple inferred-type parameters
    (x, final y) -> x+y        // Illegal: can't modify inferred-type parameters
    (x, int y) -> x+y          // Illegal: can't mix inferred and declared types
    
   */
  public static void main(String[] args) {
  }
}
