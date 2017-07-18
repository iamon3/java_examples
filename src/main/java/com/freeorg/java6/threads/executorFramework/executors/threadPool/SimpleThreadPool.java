package com.freeorg.java6.threads.executorFramework.executors.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Java thread pool manages the pool of worker threads, it contains a queue that keeps tasks waiting to get executed. 
 * We can use ThreadPoolExecutor to create thread pool in java. 
 * Java thread pool manages the collection of Runnable threads and worker threads execute Runnable from the queue. 
 * java.util.concurrent.Executors provide implementation of java.util.concurrent.Executor interface to create the thread pool in java.
 * 
 *  In above program, we are creating fixed size thread pool of 5 worker threads. 
 *  Then we are submitting 10 jobs to this pool, since the pool size is 5, it will start working on 5 jobs 
 *  and other jobs will be in wait state, as soon as one of the job is finished, 
 *  another job from the wait queue will be picked up by worker thread and get's executed.
 */
public class SimpleThreadPool {
  public static void main(String[] args) {
    
    ExecutorService executor = Executors.newFixedThreadPool(5);
    for (int i = 0; i < 10; i++) {
      Runnable worker = new WorkerThread("" + i);
      executor.execute(worker);
    }
    executor.shutdown();
    while (!executor.isTerminated()) {
    }
    System.out.println("Finished all threads");
  }
}

class WorkerThread implements Runnable {

  private String command;

  public WorkerThread(String s){
    this.command=s;
  }

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
    processCommand();
    System.out.println(Thread.currentThread().getName()+" End.");
  }

  private void processCommand() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String toString(){
    return this.command;
  }
}
