package com.zhaowei;

import redis.clients.jedis.Jedis;

public class JedisTest {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.56.101", 6379);
		System.out.println(jedis.get("sex1"));
		System.out.println(jedis.dbSize());
	}

}
