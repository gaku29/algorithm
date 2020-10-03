package com.gaku.redis.distributelock;

import org.junit.Test;
import redis.clients.jedis.JedisPool;

import static org.junit.Assert.*;

public class DistributeLockTest {

    @Test
    public void distributeLockTest(){
        Thread[] threads = new Thread[100];

        JedisPool jedisPool = new JedisPool();

        Runnable runnable = new MyTask(jedisPool);

        for(Thread thread : threads){
            thread = new Thread(runnable);
            thread.start();
        }
    }

    @Test
    public void getLock() {

    }
}