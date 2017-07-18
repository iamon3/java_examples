package com.freeorg.java6.threads.executorFramework.concurrent.atomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * http://www.journaldev.com/1095/atomicinteger-java
 * Java 5 java.util.concurrent.atomic provides wrapper classes for int and long that can be used 
 * to achieve this atomic operation without usage of Synchronization.
 * Benefits of using Concurrency classes for atomic operation is that we don't need to worry about synchronization. 
 * This improves code readability and chance of errors are reduced. 
 * Also atomic operation concurrency classes are assumed to be more efficient that synchronization which involves locking resources.
 */
public class JavaAtomic {

  public static void main(String[] args) throws InterruptedException {

    ProcessingThread pt = new ProcessingThread();
    Thread t1 = new Thread(pt, "t1");
    t1.start();
    Thread t2 = new Thread(pt, "t2");
    t2.start();
    t1.join();
    t2.join();
    System.out.println("Processing count=" + pt.getCount());
  }
}

class ProcessingThread implements Runnable {
  private AtomicInteger count = new AtomicInteger();

  @Override
  public void run() {
    for (int i = 1; i < 5; i++) {
      processSomething(i);
      count.incrementAndGet();
    }
  }

  public int getCount() {
    return this.count.get();
  }

  private void processSomething(int i) {
    // processing some job
    try {
      Thread.sleep(i * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}