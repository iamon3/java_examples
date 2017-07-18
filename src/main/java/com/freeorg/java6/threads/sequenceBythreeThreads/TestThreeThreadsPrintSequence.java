package com.freeorg.java6.threads.sequenceBythreeThreads;

public class TestThreeThreadsPrintSequence {

	public static void main(String[] args) throws InterruptedException {
		boolean[] turnArr = new boolean[]{true,false,false};
		Thread t1 = new Thread(new RunnableSequencePrinter(1,30, 3, 0,turnArr));
		Thread t2 = new Thread(new RunnableSequencePrinter(2,30, 3, 1,turnArr));
		Thread t3 = new Thread(new RunnableSequencePrinter(3,30, 3, 2, turnArr));
		
		t1.join();
		t2.join();
		t3.join();
		
		t2.start();
		t3.start();
		t1.start();
	}
}
