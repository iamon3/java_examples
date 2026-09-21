package com.freeorg.java21.basics.datastructures;

import com.sun.jdi.ArrayReference;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toUnmodifiableList;

public class Day9DatastructuresConversions {

    List<Map.Entry<String, Integer>> mapToEntryList(Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .collect(Collectors.toList());
    }

    Map<String, Integer> entryListToMap(List<Map.Entry<String, Integer>> entries) {
        return entries.stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue()
                ));
    }

    List<Integer> setToSortedList(Set<Integer> set) {
        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        return list;
    }

    Set<Integer> listToSet(List<Integer> list) {
        return new HashSet(list);
    }

    Integer[] listToBoxedArray(List<Integer> list) {
        return list.toArray(new Integer[list.size()]);
    }

    int[] boxedListToPrimitiveArray(List<Integer> list) {
        return list.stream()
                .mapToInt(e -> e)
                .toArray();
    }

    class Employee {

        String name;
        int age;
        double salary;
        String department;

        public List<String> getSkills() {
            return skills;
        }

        List<String> skills;

        Employee(String name, int age, double salary, String department) {
            this.name = name;
            this.age = age;
            this.salary = salary;
            this.department = department;
        }

        public double getSalary() {
            return salary;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }
    }

    Map<String, List<Employee>> departmentToEmployeeList(List<Employee> employees) {
        Map<String, List<Employee>> departmentByEmployee = new HashMap<>();
        for(Employee employee : employees){
            departmentByEmployee.computeIfAbsent(employee.getDepartment(), dept -> new ArrayList<>() ).add(employee);
        }
        return departmentByEmployee;
    }

    int[] primitiveArrayToBoxedThenBack(int[] arr) {
        Integer[] array = Arrays.stream(arr)
                .mapToObj(e -> (Integer) e)
                .toArray((len) -> new Integer[len]);

        int[] arr2 = Arrays.stream(array)
                .mapToInt(n -> (int) n)
                .toArray();
        return arr2;
    }

    Map<Integer, String> invertMap(Map<String, Integer> map) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getValue(),
                        entry -> entry.getKey(),
                        (val1, val2) -> val2
                ));
    }

    List<String> stringToWordList(String sentence){
        return Arrays.stream(sentence.split("\\s+"))
                .collect(Collectors.toList());
    }

    String wordListToString(List<String> words) {
        return words.stream()
                .collect(Collectors.joining(" "));
    }

    Map<String, Integer> arrayToIndexMap(String[] arr){
        return IntStream.range(0, arr.length)
                .boxed()
                .collect(Collectors.toMap(
                        i ->  arr[i],
                        i ->  i
                ));
    }
}
