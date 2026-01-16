package com.freeorg.java21.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CircleWithMaxUniquelyTaggedPoints {

    public int solution(String S, int[] X, int[] Y) {
        char [] tags = S.toCharArray();

        record Point(int x, int y, int distance, char tag){};
        List<Point> points = new ArrayList<>();

        for (int i=0 ; i < X.length ; i++){
            points.add(new Point(X[i], Y[i], calcualteDistance(X[i], Y[i]),tags[i]));
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

    public int solution2(String S, int[] X, int[] Y) {
        char [] tags = S.toCharArray();

        record Point(int x, int y, int distance, char tag){};
        List<Point> points = new ArrayList<>();

        for (int i=0 ; i < X.length ; i++){
            points.add(new Point(X[i], Y[i], calcualteDistance(X[i], Y[i]),tags[i]));
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

    int calcualteDistance(int x, int y){
        return x*x + y*y;
    }

}
