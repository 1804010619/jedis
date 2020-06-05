package com.wanglimin.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * @author wanglimin
 * @date 2020-06-05 10:31
 */
public class JedisUtils {
    private static JedisPool jedisPool;
    private static String host ;
    private static int port;
    private static int maxTotal;
    private static int maxIdel;
    static {
        ResourceBundle rb = ResourceBundle.getBundle("redis");
        maxTotal = Integer.parseInt(rb.getString("redis.maxTotal"));
        maxIdel = Integer.parseInt(rb.getString("redis.maxIdel"));
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdel);
        host = rb.getString("redis.host");
        port = Integer.parseInt(rb.getString("redis.port"));
        jedisPool = new JedisPool(jedisPoolConfig,host,port);
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
    public static void main(String[] args){
        Jedis jedis = JedisUtils.getJedis();
        String name = jedis.get("name");
        System.out.println(name);
    }
}
