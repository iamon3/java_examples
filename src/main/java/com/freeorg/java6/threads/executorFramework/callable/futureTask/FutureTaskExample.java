package com.freeorg.java6.threads.executorFramework.callable.futureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * FutureTask is base concrete implementation of Future interface and provides asynchronous processing. 
 * It contains the methods to start and cancel a task and also methods that can return the state of the FutureTask as whether it’s completed or cancelled. 
 * We need a callable object to create a future task and then we can use Java Thread Pool Executor to process these asynchronously.
 * 
 * As such there is no benefit of FutureTask but it comes handy when we want to override some of Future interface methods 
 * and don’t want to implement every method of Future interface.
 */
public class FutureTaskExample {
  private static final int WAIT_TIME_2000_MILLISECONDS = 2000;
  private static final int WAIT_TIME_1000_MILLISECONDS = 1000;

  public static void main(String[] args) {
    MyCallable firstCallableTask = new MyCallable(WAIT_TIME_1000_MILLISECONDS);
    MyCallable secondCallableTask = new MyCallable(WAIT_TIME_2000_MILLISECONDS);

    FutureTask<String> firstFutureTask = new FutureTask<String>(firstCallableTask);
    FutureTask<String> secondFutureTask = new FutureTask<String>(secondCallableTask);

    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
    fixedThreadPool.execute(firstFutureTask);
    fixedThreadPool.execute(secondFutureTask);

    while (true) {
      try {
        if(firstFutureTask.isDone() && secondFutureTask.isDone()){
          System.out.println("Done");
          //shut down executor service
          fixedThreadPool.shutdown();
          return;
        }

        if(!firstFutureTask.isDone()){
          //wait indefinitely for future task to complete
          System.out.println("FirstFutureTask output="+firstFutureTask.get());
        }

        System.out.println("Waiting for SecondFutureTask to complete");
        String s = secondFutureTask.get(200L, TimeUnit.MILLISECONDS);
        if(s !=null){
          System.out.println("SecondFutureTask output="+s);
        }
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }catch(TimeoutException e){
        System.out.println("Timeout exception occurred.");      
      }
    }

  }
}

class MyCallable implements Callable<String> {

  private long waitTime;

  public MyCallable(int timeInMillis){
    this.waitTime=timeInMillis;
  }
  @Override
  public String call() throws Exception {
    Thread.sleep(waitTime);
    //return the thread name executing this callable task
    return Thread.currentThread().getName();
  }

}