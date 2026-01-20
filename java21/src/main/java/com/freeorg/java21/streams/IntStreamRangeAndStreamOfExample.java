package com.freeorg.java21.streams;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntStreamRangeAndStreamOfExample {

    public int circleWithMaxUniquelyTaggedPointsIterative(String S, int[] X, int[] Y) {
        char[] tags = S.toCharArray();

        record Point(int x, int y, int distance, char tag) {
        }
        ;
        Set<Point> sortedPoints = new TreeSet<>(Comparator.comparing(Point::distance).thenComparing(Point::tag));
        Set<Character> visitedTags = new HashSet<>();
        for (int i = 0; i < X.length; i++) {
            if (visitedTags.contains(tags[i])) {
                System.out.println("Sorted Points => " + sortedPoints);
                return visitedTags.size();
            }
            sortedPoints.add(new Point(X[i], Y[i], calcualteDistance(X[i], Y[i]), tags[i]));
            visitedTags.add(tags[i]);
        }
        System.out.println("Sorted Points => " + sortedPoints);
        return 0;
    }

    int calcualteDistance(int x, int y) {
        return (int) Math.sqrt(x * x + y * y);
    }

    public int circleWithMaxUniquelyTaggedPointsStream(String S, int[] X, int[] Y) {
        char[] tags = S.toCharArray();

        System.out.println("Following stream simply prints references to arrays of 3 streams, as Stream elements are arrays not values.");
        Stream.of(Arrays.stream(X).boxed(),
                        Arrays.stream(Y).boxed(),
                        S.chars().mapToObj(c -> (char) c)
                )
                .forEach(System.out::println);

        System.out.println("Sorted Points =>  ");
        record Point(int x, int y, int distance, char tag) {
        }
        ;
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
        IntStreamRangeAndStreamOfExample s = new IntStreamRangeAndStreamOfExample();
        s.circleWithMaxUniquelyTaggedPointsIterative("ABCDAE", new int[]{2, 4, 3, 4, 5, 3}, new int[]{4, 2, 1, 3, 1, 4});
        s.circleWithMaxUniquelyTaggedPointsStream("ABCDAE", new int[]{2, 4, 3, 4, 5, 3}, new int[]{4, 2, 1, 3, 1, 4});
        s.circleWithMaxUniquelyTaggedPointsIterative("ABCDAE", new int[]{2, 4, 3, 4, 5, 3}, new int[]{4, 2, 1, 3, 1, 4});
    }

}
