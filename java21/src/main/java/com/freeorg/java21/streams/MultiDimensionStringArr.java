package com.freeorg.java21.streams;

import java.util.Arrays;
import java.util.Comparator;

public class MultiDimensionStringArr {
    public static void main(String[] args) {
        String[][] accounts = new String [][] {
                {"123434", "fname1", "lname1", "`8764", "code1"},
                {"45234", "fname2", "lname2", "54123", "code2"},
                {"1456761", "fname3", "lname3", "1231234e", "code1"},
                {"35426", "fname4", "lname4", "675", "code2"}
        };

        String[][] info = getInfo(accounts);
        Arrays.stream(info).forEach(a -> System.out.println(Arrays.toString(a)));

    }

    private static String[][]  getInfo(String[][] accounts) {
        return Arrays.stream(accounts)
                .filter(a -> a[4].equals("code1"))
                .sorted( Comparator.comparing( a -> a[0]) )
                .toArray(String[][]::new);
    }
}
