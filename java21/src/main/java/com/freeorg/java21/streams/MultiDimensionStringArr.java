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

        String[][] info = getInfo(accounts,4, "branch_code", "code1");
        Arrays.stream(info).forEach(a -> System.out.println(Arrays.toString(a)));

    }

    private static String[][]  getInfo(String[][] accounts, int N, String filterColumnName, String filterColumnValue) {
        Math.max(1,0);
        return Arrays.stream(accounts)
                .filter(record ->{
                    int index = switch (filterColumnName){
                        case "account_number" -> 0;
                        case "first_name" -> 1;
                        case "last_name" -> 2;
                        case "balance" -> 3;
                        case "branch_code" -> 4;
                        default -> -1;
                    };
                    return record[index].equals(filterColumnValue);
                })
                .sorted( Comparator.comparing( a -> a[0]) )
                .toArray(String[][]::new);
    }
}
