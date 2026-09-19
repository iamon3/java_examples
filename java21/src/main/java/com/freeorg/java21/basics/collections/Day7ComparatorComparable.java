package com.freeorg.java21.basics.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day7ComparatorComparable {
    public static void main(String[] args) {
        Day7ComparatorComparable comparatorComparable = new Day7ComparatorComparable();

    }
    void implementComparator(){

        class Employee implements Comparable<Employee>{
            String name;
            int age;
            double salary;
            String department;

            Employee(String name, int age, double salary, String department) {
                this.name = name;
                this.age = age;
                this.salary = salary;
                this.department = department;
            }

            @Override
            public int compareTo(Employee o) {
                return Double.compare(this.salary, o.salary);
            }
        }
    }

    class Employee {
        String name;

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        int age;

        public double getSalary() {
            return salary;
        }

        public String getDepartment() {
            return department;
        }

        double salary;
        String department;

        Employee(String name, int age, double salary, String department) {
            this.name = name;
            this.age = age;
            this.salary = salary;
            this.department = department;
        }
    }

    void sortByAgeThenName(List<Employee> employees){
        Comparator<Employee> employeeComparator = Comparator.comparing((Employee employee) -> employee.getAge())
                .thenComparing(Employee::getName);
        Collections.sort(employees, employeeComparator);
    }

    void sortBySalaryDescending(List<Employee> employees){
        Comparator<Employee> salaryComparator = Comparator.comparing((Employee emp) -> emp.getSalary()).reversed();
        Collections.sort(employees, salaryComparator);
    }

    List<Employee> sortByDeptThenSalaryDesc(List<Employee> employees){
        Comparator<Employee> byDeptThenBySalDescending = Comparator.comparing( Employee::getDepartment)
                .thenComparing(Employee::getSalary, Comparator.reverseOrder());
        return employees.stream()
                .sorted(byDeptThenBySalDescending)
                .collect(Collectors.toList());
    }

    void sortByNameLengthThenAlpha(List<Employee> employees){
        Comparator<Employee> byNameLengthThenByAlphabet = Comparator.comparingInt((Employee e) -> e.getName().length())
                .thenComparing(Employee::getName);
        Collections.sort(employees, byNameLengthThenByAlphabet);
    }

    void sortByDeptNullsLast(List<Employee> employees){
        Comparator<Employee> byDeptAndNullLast = Comparator.comparing(Employee::getDepartment, Comparator.nullsLast(Comparator.naturalOrder()));
        Collections.sort(employees, byDeptAndNullLast);
    }

    Employee findYoungestInDept(List<Employee> employees, String dept){
        return employees.stream()
                .filter(e -> Objects.equals(e.getDepartment(),dept))
                .min(Comparator.comparingInt(Employee::getAge))
                .orElse(null);
    }

    void sortByMultipleDescending(List<Employee> employees){
        Comparator<Employee> c = Comparator.comparing(Employee::getDepartment)
                .thenComparing(Employee::getAge, Comparator.reverseOrder())
                .thenComparing(Employee::getSalary, Comparator.reverseOrder());
        Collections.sort(employees, c);
    }
}
