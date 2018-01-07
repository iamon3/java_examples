package com.freeorg.java6.threads.executorFramework.concurrent.locks.reentrant.producerConsumer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Refer : http://javarevisited.blogspot.in/2015/06/java-lock-and-condition-example-producer-consumer.html
 * 		   https://examples.javacodegeeks.com/core-java/util/concurrent/locks-concurrent/condition/java-util-concurrent-locks-condition-example/
 */
public class ConditionExample {
	public static void main(String[] args) throws InterruptedException {
		SharedFiFoQueue sharedQueue = new SharedFiFoQueue(10);

		//Create a producer and a consumer.
		Thread producer = new ProducerThread(sharedQueue);
		Thread consumer = new ConsumerThread(sharedQueue);

		//Start both threads.
		producer.start();
		consumer.start();

		//Wait for both threads to terminate.
		producer.join();
		consumer.join();
		AtomicInteger ai = new AtomicInteger(5);
	}
}