package com.fh.shop.utils;

import com.alibaba.dubbo.rpc.protocol.redis.RedisProtocol;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

    private static JedisPool jedisPool;

    private RedisPool (){}

    private static void initPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(500);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(10);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        jedisPool = new JedisPool(jedisPoolConfig, "10.10.10.79", 6379);
    }

    static{
        initPool();
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }


}
