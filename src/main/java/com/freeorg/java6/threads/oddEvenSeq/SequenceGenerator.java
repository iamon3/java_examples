package com.freeorg.java6.threads.oddEvenSeq;

public class SequenceGenerator implements Runnable {
	private static volatile boolean turn = true;

	protected int diff;
	protected int start;
	protected int end;
	private TestClass monitor ;
	private boolean isOddThread;

	public SequenceGenerator(int d, int start, int end, Object o){
		this.diff = d;
		this.start = start;
		this.end= end;
		this.monitor =(TestClass)o;
		isOddThread = (1 == start % 2);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			printSequence();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printSequence() throws InterruptedException{
		int i=this.start;
		for(;i<this.end;i+=diff){		
			synchronized (monitor) {
				if(!(isOddThread ^ turn)){
					System.out.println(i);
					turn = !turn;
					monitor.notify();
				}
				else{
					monitor.wait();
					System.out.println(i);
					turn = !turn;
					monitor.notify();
				}			
			}
		}
	}
}
