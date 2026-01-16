package com.freeorg.java21.streams;

import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstUniqueChar {
    // AABBCCFUH // n
    // LinkedHashMap<Char, Count> = <"A,2", "B,2", "C,2", "U,1", "H,1"> // 26
    //                            =  n+26 = O(n)

    static void firstUniqueStream(String input){
        input
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toMap(Function.identity(), c -> 1, Integer::sum))
                .entrySet().stream()
                .filter(characterIntegerEntry -> characterIntegerEntry.getValue() == 1)
                .findFirst().ifPresentOrElse(System.out::println, () -> System.out.println("No unique character found"));
    }


    static void firstUniqueStream2(String input){
        input
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(characterIntegerEntry -> characterIntegerEntry.getValue() == 1)
                .findFirst().ifPresentOrElse(System.out::println, () -> System.out.println("No unique character found"));
    }

    public static void main(String[] args) {
        System.out.print("Unique -> " );
        firstUniqueStream("AABBCCFUH");
        System.out.println("Unique -> " );
        firstUniqueStream2("ABCCFABUH");

        System.out.println("Unique -> " );
        firstUniqueStream("ABCCFABFUHHU");
    }
}


