package com.freeorg.java21.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


record Employee (
    String name,
    String city,
    Integer score
){}



public class FindEmpAvgByCity {

    static List<Employee> list = new ArrayList<>();

    static {
        list.add(new Employee("EMployee1", "Pune" ,87));
        list.add(new Employee("EMployee2", "Delhi" ,97));
        list.add(new Employee("EMployee3", "Mumbai" ,88));
        list.add(new Employee("EMployee4", "Pune" ,79));
        list.add(new Employee("EMployee5", "Nagpur" ,82));
        list.add(new Employee("EMployee6", "Delhi" ,83));
        list.add(new Employee("EMployee7", "Pune" ,89));
    }

    public static void main(String[] args) {

        // Avg Score by City

        System.out.println( "Result => " +
        list.stream()
                .collect(Collectors.groupingBy(Employee::city,
                        Collectors.averagingInt(Employee::score))));


    }
}

interface PaymentStrategy{
    void pay(Bill bill);
}
class CardPaymentStrategy implements PaymentStrategy{

    @Override
    public void pay(Bill bill) {
        //
    }
}


class UPIPaymentStrategy implements PaymentStrategy{

    @Override
    public void pay(Bill bill) {

    }
}

class PaymentGateway {
    void payTransaction(Bill bill, PaymentStrategy p){
        p.pay(bill);
    }
}