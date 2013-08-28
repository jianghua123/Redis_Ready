package com.zhaowei.redis.util;

import redis.clients.jedis.Jedis;

public class RedisUtil {
	
	
	private static Jedis jedis = new Jedis("192.168.56.101", 6379);
	
	public static Jedis getInstance() {
		return jedis;
	}

}
