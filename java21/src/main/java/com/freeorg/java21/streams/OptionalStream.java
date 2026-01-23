package com.freeorg.java21.streams;

import java.util.List;
import java.util.Optional;

public class OptionalStream {

    record Car(String number, String type){}

    record User(String name, Optional<Car> car){}

    // Mainly this line Stream.flatMap() expects Streams . Optional.stream() gives Stream of 0 or 1 element which can be used in Stream flatMap
    // It cleanly skip empty Optional elements.
    // In the output Sam is excluded cleanly
    void printCars(List<User> usersFound){
        usersFound.stream()
                .flatMap(user -> user.car().stream())
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        OptionalStream optionalStream = new OptionalStream();

        optionalStream.printCars(
                List.of(
                        new User("Raju", Optional.of(new Car("MH12 AB 1876", "SUV"))),
                        new User("Sam", Optional.empty()),
                        new User("Pintu", Optional.of(new Car("MH14 FR 2326", "SUV")))
                )
        );
    }
}
