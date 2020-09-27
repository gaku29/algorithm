package com.gaku.java.threads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadSync2 {


    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(1);
        State state = new State();
        Lock lock = new ReentrantLock();
        Map<Integer, Condition> conditionMap = new HashMap<Integer, Condition>();

        for(int i = 0 ; i < 100; i++){
            conditionMap.put(i, lock.newCondition());
        }

        for(int i = 0; i < 100; i++){
            MyTask2 myTask2 = new MyTask2(lock, conditionMap, state, i, atomicInteger);
            new Thread(myTask2).start();
        }




    }



}
