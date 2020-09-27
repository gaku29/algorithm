package com.gaku.java.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyTask implements Runnable{

    private int  selfState;
    private State state;
    private AtomicInteger atomicInteger;
    private Object o;



    public MyTask(int selfState, State state, AtomicInteger atomicInteger, Object o){
        this.selfState = selfState;
        this.state = state;
        this.atomicInteger = atomicInteger;
        this.o = o;
    }


    private void print() throws InterruptedException {

        synchronized (o) {

            while (state.getState() != selfState) {
                o.wait();
            }

            System.out.println(atomicInteger.getAndIncrement());
            TimeUnit.SECONDS.sleep(3);
            state.nextState();
            o.notifyAll();
        }

    }

    public void run() {
        try {
            while (true) {
                print();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
