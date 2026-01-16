package com.freeorg.java21.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CircleWithMaxUniquelyTaggedPoints {

    public int solution(String S, int[] X, int[] Y) {
        char[] tags = S.toCharArray();

        record Point(int x, int y, int distance, char tag) {
        }
        ;
        List<Point> points = new ArrayList<>();

        for (int i = 0; i < X.length; i++) {
            points.add(new Point(X[i], Y[i], calcualteDistance(X[i], Y[i]), tags[i]));
        }

        List<Point> sortedPoints = points.stream()
                .sorted(Comparator.comparing(Point::distance))
                .collect(Collectors.toList());

        Set<Character> visitedTags = new HashSet<>();
        for (Point point : sortedPoints) {
            if (visitedTags.contains(point.tag())) {
                break;
            }
            visitedTags.add(point.tag());
        }

        return visitedTags.size();
    }

    int calcualteDistance(int x, int y) {
        return x * x + y * y;
    }

    public int solution2(String S, int[] X, int[] Y) {
        char[] tags = S.toCharArray();

        System.out.println("Following stream simplt prints references to arrays of 3 streams, as Stream elements are arrays not values.");
        Stream.of(Arrays.stream(X).boxed(),
                        Arrays.stream(Y).boxed(),
                        S.chars().mapToObj(c -> (char) c)
                )
                .forEach(System.out::println);

        System.out.println("Following stream is what we want as IntStream are faster than Stream.");
        record Point(int x, int y, int distance, char tag) { };
        Set<Character> seenTags = new HashSet<>();
        IntStream.range(0,
                        Math.min(Math.min(X.length, Y.length), tags.length))
                .mapToObj(i -> new Point(X[i], Y[i], calcualteDistance(X[i], Y[i]), tags[i]))
                .sorted(Comparator.comparing(Point::distance))
                .takeWhile(p -> seenTags.add(p.tag()))
                .forEach(System.out::println);

        return -1;
    }

    public static void main(String[] args) {
        CircleWithMaxUniquelyTaggedPoints s = new CircleWithMaxUniquelyTaggedPoints();
        s.solution("ABCDAE", new int[]{2,4,3,4,5,3}, new int[]{4,2,1,3,1,4});
        s.solution2("ABCDAE", new int[]{2,4,3,4,5,3}, new int[]{4,2,1,3,1,4});
    }

}
