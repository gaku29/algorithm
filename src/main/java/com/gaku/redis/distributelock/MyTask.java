package com.gaku.redis.distributelock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class MyTask implements Runnable{

    // 锁 控制的访问对象
    private static final String BUSINESS_COUNT = "BUSINESS_COUNT";
    // 锁的key
    private static final String BUSINESS_COUNT_LOCK_KEY = "business_count_lock_key";
    private static final String BUSINESS_COUNT_LOCK_TAG = "business_count_lock_tag";

    private JedisPool jedisPool ;

    public MyTask(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * read-modify-write
     * 读取redis中的一个计数器的值，然后在本地+2，然后写回redis
     */
    @Override
    public void run() {

        DistributeLock lock  = DistributeLock.getInstance();

        // 尝试获得锁
        if (lock.getLock(BUSINESS_COUNT_LOCK_KEY, 50, BUSINESS_COUNT_LOCK_TAG)){
            System.out.println(Thread.currentThread() + "获得了锁");

            // 拿到锁后，再使用jedis资源进行处理
            doSomething();

            // 释放锁
            lock.releaseLock(BUSINESS_COUNT_LOCK_KEY, BUSINESS_COUNT_LOCK_TAG);
            System.out.println(Thread.currentThread() + "释放了锁");
        }else{
            System.out.println(Thread.currentThread() + "没有获得锁");
        }

    }

    private void doSomething() {
        try(Jedis jedis = jedisPool.getResource()) {

            String count = jedis.get(BUSINESS_COUNT);
            if (count == null) {
                jedis.set(BUSINESS_COUNT, String.valueOf(1));
            } else {
                Integer temp = Integer.valueOf(count);
                jedis.set(BUSINESS_COUNT, String.valueOf(temp + 2));
            }
        }
    }

}
