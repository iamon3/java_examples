package com.freeorg.java6.misc;

import java.util.ArrayList;
import java.util.List;

public class GnericAddElementTest {

	public static void main(String[] args) {
		List<Animal> animals = new ArrayList<>();
		addDogTo(animals);
	}

	private static void addDogTo(List<? extends Animal> animals) {
        // animals.add(new Dog());
//        error: incompatible types: Dog cannot be converted to CAP#1
//        animals.add(new Dog());
//		            ^
//        where CAP#1 is a fresh type-variable:
//        CAP#1 extends Animal from capture of ? extends Animal

	}

}

class Animal {

}

class Dog extends Animal {

}