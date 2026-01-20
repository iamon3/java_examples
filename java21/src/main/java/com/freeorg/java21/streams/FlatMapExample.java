package com.freeorg.java21.streams;

import java.util.List;

public class FlatMapExample {

    static List<Integer> transform(List<List<Integer>> listOfList){

        return listOfList.stream()
                .flatMap( eachList -> eachList.stream())
                .filter(e  -> {
                            System.out.println("Number - " + e);  //
                            return (e % 2 == 1);
                        }
                )
                .map(e ->
                        {
                            System.out.println("Multi - " + e);
                            return e*4;
                        }
                )
                .toList();
    }

    public static void main(String[] args) {
        List<List<Integer>> input = List.of(
                List.of(1, 1, 2, 2),
                List.of(11, 22),
                List.of(22, 44)
        );
        System.out.println(transform(input));
    }
}
