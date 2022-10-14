package com.byhuang;

import redis.clients.jedis.Jedis;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/14 10:47
 * @description TODO
 */
public class JedisDemo {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.119.130", 6379);
        String ping = jedis.ping();
        System.out.println(ping);
        // 要关闭jedis
        jedis.close();
    }
}
