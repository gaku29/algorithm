package com.gaku.java.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadSync {

    public static void main(String[] args) {


        AtomicInteger atomicInteger = new AtomicInteger(1);
        State state = new State();
        Object o = new Object();

        MyTask myTask1 = new MyTask(1, state, atomicInteger, o);
        MyTask myTask2 = new MyTask(2, state, atomicInteger, o);
        MyTask myTask3 = new MyTask(0, state, atomicInteger, o);

        new Thread(myTask1).start();
        new Thread(myTask2).start();
        new Thread(myTask3).start();


    }







}
