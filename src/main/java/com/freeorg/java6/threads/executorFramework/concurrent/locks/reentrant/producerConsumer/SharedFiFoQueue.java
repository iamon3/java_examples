package com.freeorg.java6.threads.executorFramework.concurrent.locks.reentrant.producerConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedFiFoQueue {

	private Object[] elems = null;
	private int current = 0;
	private int placeIndex = 0;
	private int removeIndex = 0;

	private final Lock reentrantLock = new ReentrantLock();
	private final Condition isEmptyCondition = reentrantLock.newCondition();
	private final Condition isFullCondition = reentrantLock.newCondition();

	public SharedFiFoQueue(int capacity) {
		this.elems = new Object[capacity];
	}

	public void add(Object elem) throws InterruptedException {
		reentrantLock.lock();
		while(current >= elems.length)
			isFullCondition.await();

		elems[placeIndex] = elem;

		//We need the modulo, in order to avoid going out of bounds.
		placeIndex = (placeIndex + 1) % elems.length;

		++current;

		//Notify the consumer that there is data available.
		isEmptyCondition.signal();

		reentrantLock.unlock();
	}

	public Object remove() throws InterruptedException {
		Object elem = null;

		reentrantLock.lock();
		while(current <= 0)
			isEmptyCondition.await();

		elem = elems[removeIndex];

		//We need the modulo, in order to avoid going out of bounds.
		removeIndex = (removeIndex + 1) % elems.length;

		--current;

		//Notify the producer that there is space available.
		isFullCondition.signal();

		reentrantLock.unlock();

		return elem;
	}
}