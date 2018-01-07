package com.freeorg.java6.threads.executorFramework.concurrent.synchronizers.countdownLatch.eg1;

/** 
 * 	Refer : https://www.geeksforgeeks.org/countdownlatch-in-java/

	CountDownLatch is used to make sure that a task waits for other threads before it starts. 
	To understand its application, let us consider a server where the main task can only start when all the required services have started.
	
	Working of CountDownLatch:
	When we create an object of CountDownLatch, we specify the number if threads it should wait for, 
	all such thread are required to do count down by calling CountDownLatch.countDown() once they are completed or ready to the job. 
	As soon as count reaches zero, the waiting task starts running.
	
	Facts about CountDownLatch:

	1. Creating an object of CountDownLatch by passing an int to its constructor (the count), is actually number of invited parties (threads) for an event.
	2. The thread, which is dependent on other threads to start processing, waits on until every other thread has called count down. 
	   All threads, which are waiting on await() proceed together once count down reaches to zero.
	3. countDown() method decrements the count and await() method blocks until count == 0
	
 	Java Program to demonstrate how to use CountDownLatch,
	Its used when a thread needs to wait for other threads
	before starting its work. 
*/
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo2 {

	private static final int NO_OF_PARTIES = 4;

	public static void main(String args[]) throws InterruptedException {
		// Let us create task that is going to wait for four
		// threads before it starts
		CountDownLatch latch = new CountDownLatch(NO_OF_PARTIES);

		// Let us create four worker threads and start them.
		Worker1 first = new Worker1(1000, latch, "WORKER-1");
		Worker1 second = new Worker1(2000, latch, "WORKER-2");
		Worker1 third = new Worker1(3000, latch, "WORKER-3");
		Worker1 fourth = new Worker1(4000, latch, "WORKER-4");
		first.start();
		second.start();
		third.start();
		fourth.start();

		// The main task waits for four threads
		latch.await();

		// Main thread has started
		System.out.println(Thread.currentThread().getName() +
				" has finished");
	}
}

//A class to represent threads for which the main thread
//waits.
class Worker1 extends Thread
{
	private int delay;
	private CountDownLatch latch;

	public Worker1(int delay, CountDownLatch latch,
			String name)
	{
		super(name);
		this.delay = delay;
		this.latch = latch;
	}

	@Override
	public void run()
	{
		try
		{
			Thread.sleep(delay);
			latch.countDown();
			System.out.println(Thread.currentThread().getName()
					+ " finished");
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}