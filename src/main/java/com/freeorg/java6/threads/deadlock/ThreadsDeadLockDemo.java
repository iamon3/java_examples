package com.freeorg.java6.threads.deadlock;

public class ThreadsDeadLockDemo {

	public static void main(String[] args) throws InterruptedException {
		Object lockA = new Object();
		Object lockB = new Object();
		Thread t1 = new Thread(new RunnableReader(lockA, lockB));
		Thread t2 = new Thread(new RunnableWriter(lockA, lockB));
		t1.join();
		t2.join();
		t2.start();
		t1.start();
	}
}

class RunnableReader implements Runnable{

	Object lockA;
	Object lockB;
	public RunnableReader(Object lockA, Object lockB) {
		this.lockA = lockA;
		this.lockB = lockB;
	}

	@Override
	public void run() {
		synchronized (lockA) {
			System.out.println(Thread.currentThread().getName() + " Reader acquired lock A. Going to acquire lock B.");
			synchronized (lockB) {
				System.out.println(Thread.currentThread().getName() + " Reader acquired lock B.");
			}
			System.out.println(Thread.currentThread().getName() + " Reader released lock B.");
		}
		System.out.println(Thread.currentThread().getName() + " Reader released lock A.");
	}
}

class RunnableWriter implements Runnable{

	Object lockA;
	Object lockB;
	public RunnableWriter(Object lockA, Object lockB) {
		this.lockA = lockA;
		this.lockB = lockB;
	}

	@Override
	public void run() {
		synchronized (lockB) {
			System.out.println(Thread.currentThread().getName() + " Writer acquired lock B. Going to acquire lock A.");
			synchronized (lockA) {
				System.out.println(Thread.currentThread().getName() + " Writer acquired lock A.");
			}
			System.out.println(Thread.currentThread().getName() + " Writer released lock A.");
		}
		System.out.println(Thread.currentThread().getName() + " Writer released lock B.");
	}
}