package com.zhaowei.redis.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.ShardedJedis;

import com.zhaowei.redis.factory.JedisFactory;
import com.zhaowei.redis.util.DB;

public class RedisDAOImpl {
	
	public boolean setName(String name) {
		ShardedJedis jedis = null;
		boolean result = false;
		try {
			jedis = JedisFactory.getJedis();
			jedis.set("name", name);
			result = true;
			return result;
		} finally {
			JedisFactory.releaseShardedJedis(jedis);
		}
	}

	public String getName() {
		ShardedJedis jedis = null;
		String result = null;
		try {
			jedis = JedisFactory.getJedis();
			result = jedis.get("name");
			return result;
		} finally {
			JedisFactory.releaseShardedJedis(jedis);
		}
	}

	public List<List<String>> activityCount(String date) {
		String cache_key = "com.zhaowei.redis.dao.redisdaoimpl.activityCouont." + date;
		ShardedJedis jedis = JedisFactory.getJedis();
		if (null != jedis) {

		}
		JedisFactory.releaseShardedJedis(jedis);
		List<List<String>> result = new ArrayList<List<String>>();
		try {
			ResultSet rs = DB.getInstance().querySql("select * from user");
			while (rs.next()) {
				List<String> record = new ArrayList<String>();
				record.add(rs.getString("USERID"));
				record.add(rs.getString("USERNAME"));
				record.add(rs.getString("NICKNAME"));
				record.add(rs.getString("EMAIL"));
				record.add(rs.getString("PHONE"));
				record.add(rs.getString("CITY"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
