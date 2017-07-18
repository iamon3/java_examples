package com.freeorg.java6.threads.executorFramework.executors.threadPool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Refer : http://www.journaldev.com/2340/java-scheduler-scheduledexecutorservice-scheduledthreadpoolexecutor-example
 * 
 * Sometimes we need to execute a task periodically or after specific delay. 
 * Java provides Timer Class through which we can achieve this but sometimes we need to run similar tasks in parallel. 
 * So creating multiple Timer objects will be an overhead to the system and it’s better to have a thread pool of scheduled tasks.
 * Java provides scheduled thread pool implementation through ScheduledThreadPoolExecutor class that implements ScheduledExecutorService interface. 
 * ScheduledExecutorService defines the contract methods to schedule a task with different options.
 */
public class ScheduledThreadPool {
  public static void main(String[] args) throws InterruptedException {
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);


    //schedule to run after sometime
    System.out.println("Current Time = "+new Date());
    for(int i=0; i<3; i++){
      Thread.sleep(1000);
      WorkerThread1 worker = new WorkerThread1("do heavy processing");
      scheduledThreadPool.schedule(worker, 10, TimeUnit.SECONDS);
    }

    //add some delay to let some threads spawn by scheduler
    Thread.sleep(30000);

    scheduledThreadPool.shutdown();
    while(!scheduledThreadPool.isTerminated()){
      //wait for all tasks to finish
    }
    System.out.println("Finished all threads");
  }
}

class WorkerThread1 implements Runnable{

  private String command;

  public WorkerThread1(String s){
    this.command=s;
  }

  @Override
  public void run() {
    System.out.println(Thread.currentThread().getName()+" Start. Time = "+new Date());
    processCommand();
    System.out.println(Thread.currentThread().getName()+" End. Time = "+new Date());
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