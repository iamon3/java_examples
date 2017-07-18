package com.freeorg.java6.threads.oddEvenSeq;

public class TestClass {
	public static void main(String[] args) throws InterruptedException{
		TestClass t1 =new TestClass();
		SequenceGenerator evenSequence = new SequenceGenerator(2, 2, 12,t1);
		SequenceGenerator oddSequence = new SequenceGenerator(2, 1, 11,t1);

		Thread evenThread = new Thread(evenSequence);
		Thread oddThread = new Thread(oddSequence);

		evenThread.join();
		oddThread.join();
		evenThread.start();
		oddThread.start();	
	}
}
