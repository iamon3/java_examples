package com.freeorg.java6.threads.executorFramework.executors.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Executors class provide simple implementation of ExecutorService using ThreadPoolExecutor 
 * but ThreadPoolExecutor provides much more feature than that. 
 * We can specify the number of threads that will be alive when we create ThreadPoolExecutor instance 
 * and we can limit the size of thread pool and create our own RejectedExecutionHandler implementation to handle the jobs that can't fit in the worker queue.
 * 
 * ThreadPoolExecutor provides several methods using which we can find out the current state of executor, pool size, active thread count and task count. 
 * So we have a monitor thread that will print the executor information at certain time interval.
 * If you want to schedule a task to run with delay or periodically then you can use ScheduledThreadPoolExecutor class.
 */
public class WorkerPool {

  public static void main(String args[]) throws InterruptedException{
    //RejectedExecutionHandler implementation
    RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
    //Get the ThreadFactory implementation to use
    ThreadFactory threadFactory = Executors.defaultThreadFactory();
    //creating the ThreadPoolExecutor
    // Notice that while initializing the ThreadPoolExecutor, we are keeping initial pool size as 2, maximum pool size to 4 and work queue size as 2. 
    // So if there are 4 running tasks and more tasks are submitted, the work queue will hold only 2 of them and rest of them will be handled by RejectedExecutionHandlerImpl.
    ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);
    //start the monitoring thread
    MyMonitorThread monitor = new MyMonitorThread(executorPool, 3);
    Thread monitorThread = new Thread(monitor);
    monitorThread.start();
    //submit work to the thread pool
    for(int i=0; i<10; i++){
      executorPool.execute(new WorkerThread("cmd"+i));
    }

    Thread.sleep(30000);
    //shut down the pool
    executorPool.shutdown();
    //shut down the monitor thread
    Thread.sleep(5000);
    monitor.shutdown();     
  }
}

class MyMonitorThread implements Runnable
{
  private ThreadPoolExecutor executor;
  private int seconds;
  private boolean run=true;

  public MyMonitorThread(ThreadPoolExecutor executor, int delay)
  {
    this.executor = executor;
    this.seconds=delay;
  }
  public void shutdown(){
    this.run=false;
  }
  @Override
  public void run()
  {
    while(run){
      System.out.println(
          String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
              this.executor.getPoolSize(),
              this.executor.getCorePoolSize(),
              this.executor.getActiveCount(),
              this.executor.getCompletedTaskCount(),
              this.executor.getTaskCount(),
              this.executor.isShutdown(),
              this.executor.isTerminated()));
      try {
        Thread.sleep(seconds*1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}

class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {

  @Override
  public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
      System.out.println(r.toString() + " is rejected");
  }

}