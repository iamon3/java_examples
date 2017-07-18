package com.freeorg.java6.threads.sequenceBythreeThreads;

import java.util.Arrays;

public class RunnableSequencePrinter implements Runnable {
	private int start;
	private int end;
	private int diff;
	private int turn;
	private volatile boolean[] turnSelectorArr;

	public RunnableSequencePrinter(int start, int end, int diff, int turn, boolean[] turnArr) {
		this.start = start;
		this.end = end;
		this.diff = diff;
		this.turn = turn;
		this.turnSelectorArr = turnArr;
	}

	@Override
	public void run() {
		for(int i=start; i<= end; i += diff){
			try {
				checkTurnAndPrint(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}

	private void checkTurnAndPrint(int i) throws InterruptedException {
		synchronized (turnSelectorArr) {
			if(isMyTurn()){
				System.out.println(Thread.currentThread().getName() + " : " + i + " ,  turns : " + Arrays.toString(turnSelectorArr) + " , Next Turn : " + nextTurn());
				turnSelectorArr[turn]=false;				
				turnSelectorArr[nextTurn()] = true;
				turnSelectorArr.notifyAll();
			}
			else{
				System.out.println("Waiting : " + Thread.currentThread().getName() + " "+ Arrays.toString(turnSelectorArr));
				turnSelectorArr.wait();
				checkTurnAndPrint(i);
				//turnSelectorArr.notifyAll();
			}
		}
	}

	private int nextTurn() {
		if(1+turn == turnSelectorArr.length){
			return 0;	
		}
		return turn +1;
	}

	private boolean isMyTurn() {
		return turnSelectorArr[turn];
	}
}
