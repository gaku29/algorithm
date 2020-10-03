package com.gaku.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisTest {

    public static void main(String[] args) {
        JedisPool pool = new JedisPool();

        // jedis 不是线程安全的，所以要限制在单个线程中使用
        // try-with-resource  确保调用jedis.close()，将连接归还给pool
        // jedis本身不支持重试，如果需要重试，则需要自己写代码了（可以自己封装一下）
        try(Jedis jedis = pool.getResource()){
//            testString(jedis);
//            testHash(jedis);
//            testList(jedis);
//            testSet(jedis);
            testZSet(jedis);
//            testPipe(jedis);
        }
    }

    private static void testPipe(Jedis jedis) {
        jedis.pipelined();
    }

    private static void testZSet(Jedis jedis) {

    }

    private static void testSet(Jedis jedis) {
        // set 无序集合， 内部是用dict字典实现的，其实就是hash，只是value是NULL，所以查询O（1）
        String key = "myset";
        String key1 = "myset1";

        jedis.del(key);
        jedis.del(key1);

        for(int i = 0; i < 5; i++){
            jedis.sadd(key, String.valueOf(i));
        }

        for(int i = 4; i < 10; i++){
            jedis.sadd(key1, String.valueOf(i));
        }

        // 集合都交集
        System.out.println(jedis.sinter(key,key1));
        // 差集
        System.out.println(jedis.sdiff(key, key1));

        // 并集
        System.out.println(jedis.sunion(key, key1));

        jedis.del(key);
        jedis.del(key1);

    }

    private static void testList(Jedis jedis) {
        // list， 双向列表, 可以用作栈和队列
        // lpush  rpush  lpop rpop
        // lrang  O(n) 慎用
        // ltrim  O(n) 慎用
        // llen
        // lindex O(n) 慎用

        String key = "mylist1";

        for(int i = 0; i < 100; i++){
            jedis.lpush(key, String.valueOf(i));
        }


        System.out.println(jedis.llen(key));

        List<String> abc = jedis.lrange(key, 0, -1);
        System.out.println(abc);

        for(int i = 0; i <= 101; i++){
            System.out.println(jedis.lpop(key));
        }


    }

    private static void testHash(Jedis jedis) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "小明");
        map.put("age", "19");
        map.put("sex", "boy");
        jedis.hset("person_xiaoming", map);
        Map<String, String> ret = jedis.hgetAll("person_xiaoming");
        System.out.println(ret);

    }

    private static void testString(Jedis jedis){
        jedis.del("my_key_from_jedis");
        String  ret = jedis.get("my_key_from_jedis");
        System.out.println(ret);
        jedis.set("my_key_from_jedis", "my_value_from_jedis");
        System.out.println(jedis.get("my_key_from_jedis"));

    }
}
