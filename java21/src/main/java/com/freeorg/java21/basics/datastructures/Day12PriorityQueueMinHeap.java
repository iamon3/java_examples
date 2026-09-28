package com.freeorg.java21.basics.datastructures;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Day12PriorityQueueMinHeap {

    public static void main(String[] args) {
        Day12PriorityQueueMinHeap heapUtils = new Day12PriorityQueueMinHeap();
         heapUtils.kSmallest(new int[]{13,3,56,87,27,62,34,78,-2}, 4);
        heapUtils.kLargest(new int[]{13,3,56,87,27,62,34,78,-2}, 4);
        heapUtils.priorityQueueCRUD(new PriorityQueue<Integer>());
       heapUtils.employeesByYoungestFirst(List.of(
                new Employee("ashok",23, 6.0,"Accounts"),
                new Employee("Rajiv",32, 26.5,"Quality"),
                new Employee("Brian",46, 60.0,"Directos"),
                new Employee("Donald",36, 42.0,"Engineering"),
                new Employee("Harish",22, 12.0,"Engineering"),
                new Employee("Sonal",28, 28.0,"Finanace")
        ));
    }

    int[] kSmallest(int[] arr, int k) {
        System.out.println("Input arr = " + Arrays.toString(arr));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int e : arr) {
            pq.offer(e);
            System.out.println("\n\n\nAfter adding element - " + e + "heapified heap = " + pq);
        }

        int size = Math.min(k, arr.length);
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = pq.poll();
        }
        System.out.println("K smallest : " + Arrays.toString(result));
        return result;
    }

    int[] kLargest(int[] arr, int k) {
        System.out.println("Input arr = " + Arrays.toString(arr));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int e : arr) {
            pq.offer(e);
            if(pq.size() > k){
                System.out.println( "Size exceeded . Removed head " + pq.poll() + " from Priority Queue = " + pq);
            }
        }

        int size = Math.min(k, arr.length);
        int[] result = new int[size];
        for (int i = size-1; i >= 0; i--) {
            result[i] = pq.poll();
        }
        System.out.println("K Largest : " + Arrays.toString(result));
        return result;
    }

    void priorityQueueCRUD(PriorityQueue<Integer> pq) {
        int[] arr = {13, 3, 56, 87, 27, 62, 34, 78, -2};
        for (int e : arr) {
            pq.offer(e);
        }
        System.out.println("Priority Queue constructed = " + pq);

        System.out.println("contains(56): " + pq.contains(56));   // true
        System.out.println("peek: " + pq.peek());                  // -2, smallest, not removed
        System.out.println("poll: " + pq.poll());                  // -2, smallest, removed
        System.out.println("peek after poll: " + pq.peek());       // 3
        System.out.println("remove(56): " + pq.remove(56));        // true
        System.out.println("size: " + pq.size());                  // 7
    }

    PriorityQueue<Employee> employeesByYoungestFirst(List<Employee> employees) {
        System.out.println("Input employees = " + employees);
        PriorityQueue<Employee> pq = new PriorityQueue<>(Comparator.comparingInt(Employee::getAge));
        // Alternative approach - pq.addAll(employees)
        for(Employee e : employees){
            pq.offer(e);
            System.out.println("After adding employee - " + e + "\nheapified heap = " + pq);
        }
        return pq;
    }

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

    @Override
    public String toString() {
        return "\n[" +
                "'" + name + '\'' +
                ", " + age +
                ", " + salary +
                ", '" + department + '\'' +
                ']';
    }
}
