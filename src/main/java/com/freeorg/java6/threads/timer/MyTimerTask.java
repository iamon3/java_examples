package com.freeorg.java6.threads.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Refer :  http://www.journaldev.com/1050/java-timer-timertask-example
 * Java java.util.Timer is a utility class that can be used to schedule a thread to be executed at certain time in future. 
 * Java Timer class can be used to schedule a task to be run one-time or to be run at regular intervals.
 * java.util.TimerTask is an abstract class that implements Runnable interface 
 * and we need to extend this class to create our own TimerTask that can be scheduled using java Timer class.
 *
 * Java Timer class is thread safe and multiple threads can share a single Timer object without need for external synchronization. 
 * Timer class uses java.util.TaskQueue to add tasks at given regular interval and at any time there can be only one thread running the TimerTask, 
 * for example if you are creating a Timer to run every 10 seconds but single thread execution takes 20 seconds, 
 * then Timer object will keep adding tasks to the queue and as soon as one thread is finished, 
 * it will notify the queue and another thread will start executing. 
 * Java Timer class uses Object wait and notify methods to schedule the tasks.
 * 
 * Timer class contains several schedule() methods to schedule a task to run once at given date or after some delay. 
 * There are several scheduleAtFixedRate() methods to run a task periodically with certain interval.
 * While scheduling tasks using Timer, you should make sure that time interval is more than normal thread execution, 
 * otherwise tasks queue size will keep growing and eventually task will be executing always. 
 * That’s all for a quick roundup on Java Timer and Java TimerTask.
 */
public class MyTimerTask extends TimerTask {

  @Override
  public void run() {
    System.out.println("Timer task started at:"+new Date());
    completeTask();
    System.out.println("Timer task finished at:"+new Date());
  }

  private void completeTask() {
    try {
      //assuming it takes 20 secs to complete the task
      Thread.sleep(20000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String args[]){
    TimerTask timerTask = new MyTimerTask();
    //running timer task as daemon thread
    Timer timer = new Timer(true);
    timer.scheduleAtFixedRate(timerTask, 0, 10*1000);
    System.out.println("TimerTask started");
    //cancel after sometime
    try {
      Thread.sleep(120000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    timer.cancel();
    System.out.println("TimerTask cancelled");
    try {
      Thread.sleep(30000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}