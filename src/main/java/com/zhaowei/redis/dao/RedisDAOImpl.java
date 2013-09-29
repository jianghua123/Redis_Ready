package com.zhaowei.redis.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.zhaowei.redis.model.User;
import com.zhaowei.redis.pool.JedisPool;
import com.zhaowei.redis.pool.RedisDBKeys;

public class RedisDAOImpl extends SqlMapClientDaoSupport {

	private static final Log log = LogFactory.getLog(RedisDAOImpl.class);

	@Resource
	private JedisPool jedisPool;

	public User getUserByName(String userName) throws NumberFormatException, ParseException {
		Map<String, String> user = jedisPool.hgetAll(RedisDBKeys.USER + userName);
		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		if (null != user && user.size() > 0) {
			log.info("getDatafromRedis");
			return new User(user.get("userid"), user.get("username"), user.get("nickname"), user.get("email"),
					user.get("phone"), user.get("city"), Integer.parseInt(user.get("source")), user.get("imei"),
					format.parse(user.get("createtime")), user.get("sid"));
		}
		log.info("getDatafromDB");
		User result = (User) getSqlMapClientTemplate().queryForObject("getUserByName", userName);
		if (null != result) {
			String mainKey = RedisDBKeys.USER + userName;
			jedisPool.hset(mainKey, "userid", result.getUserId());
			jedisPool.hset(mainKey, "username", result.getUserName());
			jedisPool.hset(mainKey, "nickname", result.getNickName());
			jedisPool.hset(mainKey, "email", result.getEmail());
			jedisPool.hset(mainKey, "phone", result.getPhone());
			jedisPool.hset(mainKey, "city", result.getCity());
			jedisPool.hset(mainKey, "source", String.valueOf(result.getSource()));
			jedisPool.hset(mainKey, "imei", result.getImei());
			jedisPool.hset(mainKey, "createtime", format.format(result.getCreateTime()));
			jedisPool.hset(mainKey, "sid", result.getSid());
		}
		return result;
	}

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
}
