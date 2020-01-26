package com.sparta.mb;

public class Main {

    public static void main(String[] args) {
        // write your code here

        SharedCounter task = new SharedCounter();

        Thread thread1 = new Thread(task, "Thread 1");
        //thread1.setName("Thread 1");
        OtherWayToCreate thread2 = new OtherWayToCreate(task, "Thread 2");

        thread1.start();
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread2.start();
        thread2.setPriority(Thread.MIN_PRIORITY);
    }
}

class SharedCounter implements Runnable {
    private int count;
    synchronized
    @Override
    public void run() {

            //Thread.sleep(5000);
            System.out.println("I am inside run " + Thread.currentThread().getName());

        incrementCounter();
        System.out.println(Thread.currentThread().getName() + " = " + getCount());

    }

    public int getCount() {
        return count;
    }

    public void incrementCounter() {
        ++count;
    }
}


