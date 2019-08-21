package com.fh.shop.utils;
import redis.clients.jedis.Jedis;

public class RedisUtil {

    public static void expire(String key, int seconds){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.expire(key, seconds);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null){
                jedis.close();
                jedis = null;
            }
        }
    }

    public static void set(String key, String val){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
            String result = jedis.set(key, val);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null){
                jedis.close();
                jedis = null;
            }
        }
    }

    public static String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null){
                jedis.close();
                jedis = null;
            }
        }
        return result;
    }

    public static void delete(String key){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
            jedis.del(key);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null){
                jedis.close();
                jedis = null;
            }
        }
    }
}
