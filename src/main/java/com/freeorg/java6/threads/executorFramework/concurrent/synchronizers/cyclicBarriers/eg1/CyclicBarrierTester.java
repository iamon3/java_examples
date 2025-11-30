package com.freeorg.java6.threads.executorFramework.concurrent.synchronizers.cyclicBarriers.eg1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
	Refer : https://www.geeksforgeeks.org/java-util-concurrent-cyclicbarrier-java/
	
 	CyclicBarrier is used to make threads wait for each other. 
 	It is used when different threads process a part of computation and when all threads have completed the execution, 
 	the result needs to be combined in the parent thread. In other words, a CyclicBarrier is used when multiple thread carry out different sub tasks 
 	and the output of these sub tasks need to be combined to form the final output. 
 	After completing its execution, threads call await() method and wait for other threads to reach the barrier. 
 	Once all the threads have reached, the barriers then give the way for threads to proceed.

	Working of CyclicBarrier

	CyclicBarriers are defined in java.util.concurrent package. 
	First a new instance of a CyclicBarriers is created specifying the number of threads that the barriers should wait upon.

	CyclicBarrier newBarrier = new CyclicBarrier(numberOfThreads);
	Each and every thread does some computation and after completing its execution, calls await() methods

	Once the number of threads that called await() equals numberOfThreads, the barrier then gives a way for the waiting threads. 
	The CyclicBarrier can also be initialized with some action that is performed once all the threads have reached the barrier. 
	This action can combine/utilize the result of computation of individual thread waiting in the barrier.

	Runnable action = ... 
	//action to be performed when all threads reach the barrier;
	CyclicBarrier newBarrier = new CyclicBarrier(numberOfThreads, action);
	
	BrokenBarrierException

	A barrier breaks when any of the waiting thread leaves the barrier. 
	This happens when one or more waiting thread is interrupted or when the waiting time is completed because the thread called the await() methods with a timeout as follows:

	newBarrier.await(1000, TimeUnit.MILLISECONDS);
	// thread calling this await() 
	// methods waits for only 1000 milliseconds.
	
	When the barrier breaks due to one of more participating threads, the await() methods of all the other threads throws a BrokenThreadException. 
	Whereas, the threads that are already waiting in the barriers have their await() call terminated.

	Difference between a CyclicBarrier and a CountDownLatch

	A CountDownLatch can be used only once in a program(until its count reaches 0).
	A CyclicBarrier can be used again and again once all the threads in a barriers is released.
	
	Important Methods of CyclicBarrier:

	getParties: Returns the number of parties required to trip this barrier.
	Syntax:
	public int getParties()
	Returns:
	the number of parties required to trip this barrier

	reset: Resets the barrier to its initial state.
	Syntax:
	public void reset()
	Returns:
	void but resets the barrier to its initial state. If any parties are currently waiting at the barrier, they will return with a BrokenBarrierException.

	isBroken: Queries if this barrier is in a broken state.
	Syntax:
	public boolean isBroken()
	Returns:
	true if one or more parties broke out of this barrier due to interruption or timeout since construction or the last reset, or a barrier action failed due to an exception; false otherwise.

	getNumberWaiting: Returns the number of parties currently waiting at the barrier.
	Syntax:
	public int getNumberWaiting()
	Returns:
	the number of parties currently blocked in await()

	await: Waits until all parties have invoked await on this barrier.
	Syntax:
	public int await() throws InterruptedException, BrokenBarrierException
	Returns:
	the arrival index of the current thread, where index getParties() - 1 indicates the first to arrive and zero indicates the last to arrive.

	await: Waits until all parties have invoked await on this barrier, or the specified waiting time elapses.
	Syntax:
	public int await(long timeout, TimeUnit unit) 
	throws InterruptedException,
	BrokenBarrierException, TimeoutException
	Returns:
	the arrival index of the current thread, where index getParties() - 1 indicates the first to arrive and zero indicates the last to arrive

 */
class Computation1 implements Runnable
{
	public static int product = 0;
	public void run()
	{
		product = 2 * 3;
		try
		{
			CyclicBarrierTester.newBarrier.await();
		} 
		catch (InterruptedException | BrokenBarrierException e) 
		{
			e.printStackTrace();
		}
	}
}

class Computation2 implements Runnable
{
	public static int sum = 0;
	public void run()
	{
		// check if newBarrier is broken or not
		System.out.println("Is the barrier broken? - " + CyclicBarrierTester.newBarrier.isBroken());
		sum = 10 + 20;
		try
		{
			CyclicBarrierTester.newBarrier.await(3000, TimeUnit.MILLISECONDS);

			// number of parties waiting at the barrier
			System.out.println("Number of parties waiting at the barrier "+
					"at this point = " + CyclicBarrierTester.newBarrier.getNumberWaiting());
		} 
		catch (InterruptedException | BrokenBarrierException e) 
		{
			e.printStackTrace();
		} 
		catch (TimeoutException e) 
		{
			e.printStackTrace();
		}
	}
}


public class CyclicBarrierTester implements Runnable
{
	private static final int NO_OF_PARTIES = 3;

	public static CyclicBarrier newBarrier = new CyclicBarrier(NO_OF_PARTIES);

	public static void main(String[] args)
	{
		// parent thread
		CyclicBarrierTester test = new CyclicBarrierTester();

		Thread t1 = new Thread(test);
		t1.start();
	}
	public void run()
	{
		System.out.println("Number of parties required to trip the barrier = "+
				newBarrier.getParties());
		System.out.println("Sum of product and sum = " + (Computation1.product + 
				Computation2.sum));

		// objects on which the child thread has to run
		Computation1 comp1 = new Computation1();
		Computation2 comp2 = new Computation2();

		// creation of child thread
		Thread t1 = new Thread(comp1);
		Thread t2 = new Thread(comp2);

		// moving child thread to runnable state
		t1.start();
		t2.start();

		try
		{
			CyclicBarrierTester.newBarrier.await();
		} 
		catch (InterruptedException | BrokenBarrierException e) 
		{
			e.printStackTrace();
		}

		// barrier breaks as the number of thread waiting for the barrier
		// at this point = 3
		System.out.println("Sum of product and sum = " + (Computation1.product + 
				Computation2.sum));

		// Resetting the newBarrier
		newBarrier.reset();
		System.out.println("Barrier reset successful");
	}
}