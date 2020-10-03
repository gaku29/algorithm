package com.gaku.redis.distributelock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用redis实现分布式锁
 * 缺点：
 * 1. 超时时间不好设置
 * 2. 集群环境，在切换主从时，可能丢失锁（这种情况下，可以使用RedLock)
 */
public class DistributeLock {

    private static final DistributeLock instance = new DistributeLock();
    private static final String PREFIX = "MyDistributeLock_";

    private JedisPool jedisPool;

    /**
     * 存储当前线程 持有锁 的个数
     */
    private ThreadLocal<Map<String, Integer>> lockers = new ThreadLocal<>();



    public static DistributeLock getInstance(){
        return instance;
    }

    private DistributeLock(){
        jedisPool = new JedisPool();
    }

    /**
     * 获得当前线程持有的锁信息
     * @return
     */
    private Map<String, Integer> getLockers(){

        Map<String, Integer>  map = lockers.get();
        if (map == null){
            lockers.set(new HashMap<>());
        }

        return lockers.get();
    }

    /**
     * 获得锁
     * @param key
     * @param expire 过期时间
     * @param tag
     * @return
     */
    public boolean getLock(String key, int expire, String tag){

        String myKey = PREFIX + key;

        Map<String, Integer>  map = getLockers();

        Integer count = map.get(myKey);

        if (count != null){
            map.put(myKey, count + 1);
            return  true;
        }

        try(Jedis jedis = jedisPool.getResource()){

            // 使用 setnx 命令，如果不存在，则设置，然后返回1； 否则，不设置，返回0
            //Long ret = jedis.setnx(PREFIX + key, "true");

            SetParams setParams = new SetParams();
            // 5秒后过期   不存在则设置
            setParams.ex(expire).nx();
            String result = jedis.set(myKey, tag, setParams);

            if(result != null && result.equalsIgnoreCase("OK")){

                map.put(myKey, 1);

                return true;
            }else {
                return false;
            }
        }
    }

    /**
     * 释放锁
     * @param key
     * @param tag 标记
     */
    public void releaseLock(String key, String tag){
        String myKey = PREFIX + key;

        Map<String, Integer> map = lockers.get();
        Integer count = map.get(myKey);
        count -= 1;
        if (count > 0){
            map.put(myKey, count);
            return;
        }
        map.remove(myKey);

        doReleaseLock(key, tag);

    }

    private void doReleaseLock(String key, String tag){
        try(Jedis jedis = jedisPool.getResource()){

            // 这里的 get  delete  ，不是原子操作
            // 需要放在redis 服务端，以lua脚本的方式实现
            String value = jedis.get(PREFIX + key);

            if (value == null){
                return;
            }

            if (value.equals(tag)){
                jedis.del(PREFIX + key);
            }else {
                // 可能锁到期后被删除， 其他线程又申请了锁
                // 认为释放成功， 但这里需要打印清楚，以便人工处理
                System.out.println("not your lock, can not delete");
                return;
            }

        }
    }
}
