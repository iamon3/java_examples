package com.freeorg.java6.threads.readerWriter.eg1;

/**
 */
public class DBConnection {
    static boolean flag = false;

    public static void read(){
       System.out.println("In DBConnection read(). in thread id : " + Thread.currentThread().getId());
    }

    public static void write(){
        System.out.println("In DBConnection write(). in thread id: " + Thread.currentThread().getId());
    }
}
