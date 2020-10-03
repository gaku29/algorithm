package com.gaku.redis.tx;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * watch + transaction 相当于乐观锁
 * 也可以通过lua脚本实现
 */
public class RedisTransactionTest {

    public static void main(String[] args) {

        Jedis jedis = new Jedis();
        String userId = "abc";
        String key = keyFor(userId);

        jedis.setnx(key, String.valueOf(5));
        System.out.println(doubleAccount(jedis, userId));
        jedis.close();

    }

    /**
     * 乐观锁
     * 悲观锁的话，可以考虑redis 分布式锁
     * @param jedis
     * @param userId
     * @return
     */
    private static int doubleAccount(Jedis jedis, String userId) {

        String key = keyFor(userId);

        int newValue = 0;


        jedis.pipelined();

        while (true) {

            jedis.watch(key);
            int value = Integer.parseInt(jedis.get(key));
            value *= 2;
            Transaction tx = jedis.multi();
            tx.set(key, String.valueOf(value));
            Response<String> nnn = tx.get(key);
            List<Object> res = tx.exec();
            if (res != null) {
                newValue = Integer.parseInt(nnn.get());
                break;
            }else{

            }
        }

        return newValue;
    }

    private static String keyFor(String userId) {
        return String.format("account_%s", userId);
    }
}
