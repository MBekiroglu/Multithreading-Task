# Multithreading-Task
### Within this task, I created two threads, one using the Thread superclass and the other with a separate class called "OtherWayToCreate" which extends Thread. 
### The aim is to have the first Thread always run first and always return 1.
### I will infer all the results obtained below:

##First Test - No Alteration

***Test Code:***
```
package com.sparta.mb;

public class Main {

    public static void main(String[] args) {
        // write your code here

        SharedCounter task = new SharedCounter();

        Thread thread1 = new Thread(task, "Thread 1");
        //thread1.setName("Thread 1");
        OtherWayToCreate thread2 = new OtherWayToCreate(task, "Thread 2");

        thread1.start();
        thread2.start();
    }
}

class SharedCounter implements Runnable {
    private int count;

    @Override
    public void run() {
        Thread.sleep(5000);
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
```    
***Returned Result:***
#### Test 1
```
I am inside run Thread 2
I am inside run Thread 1
Thread 2 = 1
Thread 1 = 2
```
#### Test 2 - Retest:
```
I am inside run Thread 1
I am inside run Thread 2
Thread 1 = 1
Thread 2 = 2
```
#### Test 3 - Retest:
```
I am inside run Thread 2
I am inside run Thread 1
Thread 2 = 1
Thread 1 = 2
```
***Issues***
Thread Scheduler is randomly selecting which thread to run.

##Second Alterations - Making Thread 1 the highest priority and Thread 2 the lowest.
***Test Code:***

```
package com.sparta.mb;

public class Main {

    public static void main(String[] args) {
        // write your code here

        SharedCounter task = new SharedCounter();

        Thread thread1 = new Thread(task, "Thread 1");
        //thread1.setName("Thread 1");
        OtherWayToCreate thread2 = new OtherWayToCreate(task, "Thread 2");

        thread1.start();
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.start();
        thread2.setPriority(Thread.MIN_PRIORITY);
    }
}
class SharedCounter implements Runnable {
    private int count;

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
```
***Returned Result:***
#### Test 1
```
I am inside run Thread 1
I am inside run Thread 2
Thread 1 = 1
Thread 2 = 2
```
#### Test 2 - Retest:
```
I am inside run Thread 1
I am inside run Thread 2
Thread 1 = 1
Thread 2 = 2
```
#### Test 3 - Retest:
```
I am inside run Thread 2
I am inside run Thread 1
Thread 2 = 1
Thread 1 = 2
```
***Issues***
Thread Scheduler is still randomly selecting although it is less frequent that Thread 2 = 1.

##Third Alterations - Making the run() method synchronised so that it is mutually exclusive and has memory visibility.
***Test Code:***
```
package com.sparta.mb;

public class Main {

    public static void main(String[] args) {
        // write your code here

        SharedCounter task = new SharedCounter();

        Thread thread1 = new Thread(task, "Thread 1");
        //thread1.setName("Thread 1");
        OtherWayToCreate thread2 = new OtherWayToCreate(task, "Thread 2");

        thread1.start();
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
```
***Returned Result:***
#### Test 1
```
I am inside run Thread 1
Thread 1 = 1
I am inside run Thread 2
Thread 2 = 2
```
#### Test 2 - Retest:
```
I am inside run Thread 1
Thread 1 = 1
I am inside run Thread 2
Thread 2 = 2
```
#### Test 3 - Retest:
```
I am inside run Thread 1
Thread 1 = 1
I am inside run Thread 2
Thread 2 = 2
```

# Conclusion
Using the synchronised keyword solved the issue as the moment Thread 1 started, it has gained the key for the method which prevented the second thread from gaining access until it had finished the method.
