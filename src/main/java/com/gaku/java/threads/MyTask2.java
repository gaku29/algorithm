package com.gaku.java.threads;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyTask2 implements Runnable{

    private Lock lock;

    private State state;
    private int selfState;
    private AtomicInteger atomicInteger;
    private Map<Integer, Condition> conditionMap;

    public MyTask2(Lock lock, Map<Integer, Condition> conditionMap, State state, int selfState, AtomicInteger atomicInteger) {
        this.lock = lock;
        this.state = state;
        this.selfState = selfState;
        this.atomicInteger = atomicInteger;
        this.conditionMap = conditionMap;
    }

    private void print() throws InterruptedException {

        lock.lock();

        try{
            while (state.getState() != selfState) {
                conditionMap.get(selfState).await();
            }

            System.out.println(atomicInteger.getAndIncrement());
            TimeUnit.SECONDS.sleep(1);
            state.nextState();
            conditionMap.get(state.getState()).signal();

        }finally {
            lock.unlock();
        }
    }


    public void run() {


        while(true){
            try {
                print();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
