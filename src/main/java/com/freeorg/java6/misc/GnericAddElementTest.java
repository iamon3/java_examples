package com.freeorg.java6.misc;

import java.util.ArrayList;
import java.util.List;

public class GnericAddElementTest {

	public static void main(String[] args) {
		List<Animal> animals = new ArrayList<>();
		addDogTo(animals);
	}

	private static void addDogTo(List<? extends Animal> animals) {
		animals.add(new Dog());
	}

}

class Animal {

}

class Dog extends Animal {

}