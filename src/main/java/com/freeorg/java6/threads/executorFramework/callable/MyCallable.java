package com.freeorg.java6.threads.executorFramework.callable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Java Callable tasks return java.util.concurrent.Future object. 
 * Using Java Future object, we can find out the status of the Callable task and get the returned Object. 
 * It provides get() method that can wait for the Callable to finish and then return the result.
 * Java Future provides cancel() method to cancel the associated Callable task. 
 * There is an overloaded version of get() method where we can specify the time to wait for the result, 
 * it’s useful to avoid current thread getting blocked for longer time. 
 * There are isDone() and isCancelled() methods to find out the current status of associated Callable task.
 */
public class MyCallable implements Callable<String> {

  @Override
  public String call() throws Exception {
      Thread.sleep(1000);
      //return the thread name executing this callable task
      return Thread.currentThread().getName();
  }
  
  public static void main(String args[]){
      //Get ExecutorService from Executors utility class, thread pool size is 10
      ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
      //create a list to hold the Future object associated with Callable
      List<Future<String>> futureList = new ArrayList<Future<String>>();
      //Create MyCallable instance
      Callable<String> myCallableTask = new MyCallable();
      // submit Callable tasks to be executed by thread pool
      for(int submittedCallableTasksCount=0; submittedCallableTasksCount< 100; submittedCallableTasksCount++){
          Future<String> future = fixedThreadPool.submit(myCallableTask);
          //add Future to the list, we can get return value using Future
          futureList.add(future);
      }
      for(Future<String> eachFuture : futureList){
          try {
              //print the return value of Future, notice the output delay in console
              // because Future.get() waits for task to get completed
              System.out.println(new Date()+ "::"+eachFuture.get());
          } catch (InterruptedException | ExecutionException e) {
              e.printStackTrace();
          }
      }
      //shut down the executor service now
      fixedThreadPool.shutdown();
  }
}