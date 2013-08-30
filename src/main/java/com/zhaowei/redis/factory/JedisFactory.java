package com.zhaowei.redis.factory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

public class JedisFactory implements JedisCommands {
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
	
	@Deprecated
	public static ShardedJedis getJedis() {
		return POOL.getResource();
	}
	
	
	@Deprecated
	public static void releaseShardedJedis(ShardedJedis jedis) {
		POOL.returnResource(jedis);
	}

	@Override
	public Long append(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long decr(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long decrBy(String arg0, long arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean exists(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long expire(String arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long expireAt(String arg0, long arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(String arg0) {
		ShardedJedis jedis = POOL.getResource();
		try{
			return jedis.get(arg0);
		} catch (Exception e) {
			e.printStackTrace();
			POOL.returnBrokenResource(jedis);
			return null;
		} finally {
			POOL.returnResource(jedis);
		}
	}

	@Override
	public String getSet(String arg0, String arg1) {
		ShardedJedis jedis = POOL.getResource();
		try{
			return jedis.getSet(arg0, arg1);
		} catch (Exception e) {
			e.printStackTrace();
			POOL.returnBrokenResource(jedis);
			return null;
		} finally {
			POOL.returnResource(jedis);
		}
	}

	@Override
	public Boolean getbit(String arg0, long arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getrange(String arg0, long arg1, long arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long hdel(String arg0, String... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean hexists(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String hget(String arg0, String arg1) {
		ShardedJedis jedis = POOL.getResource();
		try{
			return jedis.hget(arg0, arg1);
		} catch (Exception e) {
			e.printStackTrace();
			POOL.returnBrokenResource(jedis);
			return null;
		} finally {
			POOL.returnResource(jedis);
		}
	}

	@Override
	public Map<String, String> hgetAll(String arg0) {
		ShardedJedis jedis = POOL.getResource();
		try{
			return jedis.hgetAll(arg0);
		} catch (Exception e) {
			e.printStackTrace();
			POOL.returnBrokenResource(jedis);
			return null;
		} finally {
			POOL.returnResource(jedis);
		}
	}

	@Override
	public Long hincrBy(String arg0, String arg1, long arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> hkeys(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long hlen(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> hmget(String arg0, String... arg1) {
		// TODO 需要测试参数
		ShardedJedis jedis = POOL.getResource();
		try{
			return jedis.hmget(arg0, arg1);
		} catch (Exception e) {
			e.printStackTrace();
			POOL.returnBrokenResource(jedis);
			return null;
		} finally {
			POOL.returnResource(jedis);
		}
	}

	@Override
	public String hmset(String arg0, Map<String, String> arg1) {
		ShardedJedis jedis = POOL.getResource();
		try{
			return jedis.hmset(arg0, arg1);
		} catch (Exception e) {
			e.printStackTrace();
			POOL.returnBrokenResource(jedis);
			return null;
		} finally {
			POOL.returnResource(jedis);
		}
	}

	@Override
	public Long hset(String arg0, String arg1, String arg2) {
		
		ShardedJedis jedis = POOL.getResource();
		try{
			return jedis.hset(arg0, arg1, arg2);
		} catch (Exception e) {
			e.printStackTrace();
			POOL.returnBrokenResource(jedis);
			return null;
		} finally {
			POOL.returnResource(jedis);
		}
	}

	@Override
	public Long hsetnx(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> hvals(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long incr(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long incrBy(String arg0, long arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String lindex(String arg0, long arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long linsert(String arg0, LIST_POSITION arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long llen(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String lpop(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long lpush(String arg0, String... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long lpushx(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> lrange(String arg0, long arg1, long arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long lrem(String arg0, long arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String lset(String arg0, long arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ltrim(String arg0, long arg1, long arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String rpop(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long rpush(String arg0, String... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long rpushx(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long sadd(String arg0, String... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long scard(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String set(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean setbit(String arg0, long arg1, boolean arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setex(String arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long setnx(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long setrange(String arg0, long arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean sismember(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> smembers(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> sort(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> sort(String arg0, SortingParams arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String spop(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String srandmember(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long srem(String arg0, String... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String substr(String arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long ttl(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String type(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zadd(String arg0, Map<Double, String> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zadd(String arg0, double arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zcard(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zcount(String arg0, double arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zcount(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double zincrby(String arg0, double arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrange(String arg0, long arg1, long arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String arg0, double arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String arg0, double arg1, double arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrangeByScore(String arg0, String arg1, String arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String arg0, double arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String arg0, double arg1, double arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String arg0, String arg1, String arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tuple> zrangeWithScores(String arg0, long arg1, long arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zrank(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zrem(String arg0, String... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zremrangeByRank(String arg0, long arg1, long arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zremrangeByScore(String arg0, double arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zremrangeByScore(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrevrange(String arg0, long arg1, long arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrevrangeByScore(String arg0, double arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrevrangeByScore(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrevrangeByScore(String arg0, double arg1, double arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> zrevrangeByScore(String arg0, String arg1, String arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String arg0, double arg1, double arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String arg0, double arg1, double arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String arg0, String arg1, String arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(String arg0, long arg1, long arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long zrevrank(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double zscore(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
