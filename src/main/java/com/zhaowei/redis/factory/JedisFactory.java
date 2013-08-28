package com.zhaowei.redis.factory;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class JedisFactory {
	private static JedisPoolConfig JEDIS_POOL_CONFIG = null;

	private static ShardedJedisPool POOL = null;

	// initial jedis config
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		if (bundle == null) {
			throw new IllegalArgumentException("[redis.properties] is not found!");
		}
		JEDIS_POOL_CONFIG = new JedisPoolConfig();
		JEDIS_POOL_CONFIG.setMaxActive(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
		JEDIS_POOL_CONFIG.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
		JEDIS_POOL_CONFIG.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));
		JEDIS_POOL_CONFIG.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
		JEDIS_POOL_CONFIG.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));

		JedisShardInfo jedisShardInfo1 = new JedisShardInfo(bundle.getString("redis1.ip"), Integer.valueOf(bundle
				.getString("redis.port")));
		JedisShardInfo jedisShardInfo2 = new JedisShardInfo(bundle.getString("redis2.ip"), Integer.valueOf(bundle
				.getString("redis.port")));

		List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
		list.add(jedisShardInfo1);
		list.add(jedisShardInfo2);
		POOL = new ShardedJedisPool(JEDIS_POOL_CONFIG, list);
	}
	
	public static ShardedJedis getJedis() {
		return POOL.getResource();
	}
	
	public static void releaseShardedJedis(ShardedJedis jedis) {
		POOL.returnResource(jedis);
	}

}
