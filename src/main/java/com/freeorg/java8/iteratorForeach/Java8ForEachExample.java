package com.freeorg.java8.iteratorForeach;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.lang.Integer;

/**
 * Java 8 has introduced forEach method in java.lang.Iterable interface 
 * so that while writing code we focus on business logic only. 
 * forEach method takes java.util.function.Consumer object as argument,
 * so it helps in having our business logic at a separate location that we can reuse.
 * 
 * The number of lines might increase but forEach method helps in having the logic for iteration 
 * and business logic at separate place resulting in higher separation of concern and cleaner code.
 */
public class Java8ForEachExample {

	public static void main(String[] args) {

		//creating sample Collection
		List<Integer> myList = new ArrayList<Integer>();
		for(int i=0; i<10; i++) myList.add(i);

		// Approach 1 : Older approach of traversing using Iterator. 
		// Please note that the business logic is embedded here which can not be reused.
		Iterator<Integer> it = myList.iterator();
		System.out.println("Approach 1: Old way of iteration.");
		while(it.hasNext()){
			Integer i = it.next();
			System.out.println("Iterator Value::"+i);
		}

		// Approach 2 : New approach of traversing through forEach method of Iterable with anonymous class
		System.out.println("Approach 2: New way of iteration by passing Consumer to collection's forEach() method.");
		myList.forEach(new Consumer<Integer>() {
			public void accept(Integer t) {
				System.out.println("forEach anonymous class Value::"+t);
			}
		});

		MyConsumer action = new MyConsumer();
		// Approach 3 : New approach of traversing with Consumer interface implementation.
		// Note since the business logic is taken out in different location, it can be reused.
		System.out.println("Approach 3: New way of iteration by passing Consumer to collection's forEach() method.");
		myList.forEach(action);
		
		System.out.println("Approach 4: New way of iteration by passing Consumer to Iterator's forEachRemaining() method.");
		Iterator<Integer> it2 = myList.iterator();
		it2.forEachRemaining(action);

	}

}

// Consumer implementation that can be reused
class MyConsumer implements Consumer<Integer>{
	public void accept(Integer t) {
		System.out.println("Consumer impl Value::"+t);
	}
}