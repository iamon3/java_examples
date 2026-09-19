package com.freeorg.java21.basics.streams;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class Day8MapFilterCollect {
    public static void main(String[] args) {
        Day8MapFilterCollect streamsUtil = new Day8MapFilterCollect();
        streamsUtil.upperCaseLongNames(List.of("Amy","Bob","Ed"), 2);
        streamsUtil.joinNamesCommaSeparated(List.of("Amy","Bob","Ed"));
    }

    List<String> upperCaseLongNames(List<String> names, int minLength){
        System.out.println("Filter min length => " +  minLength+ " Upper case long names => " + names);
        List<String> result = names.stream()
                .filter(name -> name.length() >= minLength)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Result => " + result);
        return result;
    }


    /**
     * Collectors.joining()                          // no args: just concatenates, no delimiter
     * Collectors.joining(", ")                      // delimiter only
     * Collectors.joining(", ", "[", "]")            // delimiter + prefix + suffix → e.g. "[Amy, Bob, Ed]"
     */
    String joinNamesCommaSeparated(List<String> names){
        System.out.println("Names to be joined using comma => "+names);
        String joinedNamed = names.stream()
                .collect(Collectors.joining(", "));
        System.out.println("Result after joining  => " + joinedNamed);
        return joinedNamed;
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

    Map<Boolean, List<Employee>> partitionByHighSalary(List<Employee> employees, double threshold){
        Map<Boolean, List<Employee>> resultMap = employees.stream()
                .collect(Collectors.partitioningBy(emp -> emp.getSalary() >= threshold));
        return resultMap;
    }

    double averageAge(List<Employee> employees){
        return employees.stream()
                .mapToInt(employee -> employee.getAge())
                .average()
                .orElse(0);
    }

    Map<String, Long> countByDepartment(List<Employee> employees){
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
    }

    Map<String, Double> avgSalaryByDepartment(List<Employee> employees){
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
    }

    List<String> allUniqueSkills(List<Employee> employees){
        return employees.stream()
                .flatMap(employee -> employee.getSkills().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    double totalSalaryUsingReduce(List<Employee> employees){
        return employees.stream()
                .mapToDouble(employee -> employee.getSalary())
                .reduce(0.0, (sal, sum) -> Double.sum(sal, sum));
    }
}
