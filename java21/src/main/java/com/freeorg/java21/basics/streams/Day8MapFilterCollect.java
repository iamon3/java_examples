package com.freeorg.java21.basics.streams;

import java.util.*;

import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.toUnmodifiableList;

public class Day8MapFilterCollect {
    public static void main(String[] args) {
        Day8MapFilterCollect streamsUtil = new Day8MapFilterCollect();
        streamsUtil.upperCaseLongNames(List.of("Amy", "Bob", "Ed"), 2);
        streamsUtil.joinNamesCommaSeparated(List.of("Amy", "Bob", "Ed"));
    }

    List<String> upperCaseLongNames(List<String> names, int minLength) {
        System.out.println("Filter min length => " + minLength + " Upper case long names => " + names);
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
    String joinNamesCommaSeparated(List<String> names) {
        System.out.println("Names to be joined using comma => " + names);
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

    Map<Boolean, List<Employee>> partitionByHighSalary(List<Employee> employees, double threshold) {
        Map<Boolean, List<Employee>> resultMap = employees.stream()
                .collect(Collectors.partitioningBy(emp -> emp.getSalary() >= threshold));
        return resultMap;
    }

    double averageAge(List<Employee> employees) {
        return employees.stream()
                .mapToInt(employee -> employee.getAge())
                .average()
                .orElse(0);
    }

    Map<String, Long> countByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
    }

    Map<String, Double> avgSalaryByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
    }

    List<String> allUniqueSkills(List<Employee> employees) {
        return employees.stream()
                .flatMap(employee -> employee.getSkills().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    double totalSalaryUsingReduce(List<Employee> employees) {
        return employees.stream()
                .mapToDouble(employee -> employee.getSalary())
                .reduce(0.0, (sal, sum) -> Double.sum(sal, sum));
    }

    Optional<Employee> highestPaid(List<Employee> employees) {
        return employees.stream()
                .reduce(BinaryOperator.maxBy(Comparator.comparingDouble(employee -> employee.getSalary())));
    }

    List<Integer> squaresOfOneToTen() {
        return IntStream.rangeClosed(1, 10)
                .mapToObj(i -> i * i)
                .collect(Collectors.toList());
    }

    Map<String, String> employeeNameByDeptFirstOnly(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.toMap(Employee::getDepartment, Employee::getName,
                        (emp1Name, emp2Name2) -> emp1Name));
    }

    TreeSet<String> uniqueSortedDeptNames(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toCollection(() -> new TreeSet<>()));
    }

    TreeMap<String, Double> salaryByNameSorted(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.toMap(Employee::getName,
                        Employee::getSalary,
                        (sal1, sal2) -> sal1,
                        () -> new TreeMap<>()));
    }

    /**
     * The concrete mechanism behind this warning:
     * Java Streams are lazy — intermediate operations like map/peek/filter don't run until a terminal operation (collect, forEach, findFirst, etc.)
     * actually pulls elements through the pipeline, and even then, the JDK is free to skip processing elements it can prove aren't needed for the final result.
     * For example, stream().peek(...).findFirst() might only run peek's action on the first element, since nothing downstream needs the rest
     * — this is explicitly called out in the Stream.peek Javadoc as implementation-dependent behavior, not a guarantee.
     * So peek is safe and appropriate for temporary debugging output (like you did here — printing is harmless even if skipped),
     * but genuinely unsafe for anything where correctness depends on the side effect running exactly once per element
     * (e.g., incrementing a counter, writing to a database, collecting into an external list).
     */
    List<Integer> debugSquaresWithPeek(List<Integer> numbers) {
        return numbers.stream()
                .map(x -> x * x)
                // peek() is for debugging only — the JDK does not guarantee peek's
                // action will run for every element in all cases. If the stream
                // pipeline is optimized (e.g. short-circuited by findFirst/limit,
                // or certain terminal ops skip unnecessary processing), peek's
                // side effects may be skipped or run fewer times than expected.
                // Never rely on peek to mutate external state or perform "real" work.
                .peek(square -> System.out.println(square))
                .collect(Collectors.toList());
    }
}
