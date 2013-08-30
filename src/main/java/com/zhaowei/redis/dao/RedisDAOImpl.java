package com.zhaowei.redis.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.zhaowei.redis.factory.JedisFactory;
import com.zhaowei.redis.factory.RedisDBKeys;
import com.zhaowei.redis.model.User;

public class RedisDAOImpl extends SqlMapClientDaoSupport {

	private JedisFactory cachePool;

	public User getUserByName(String name) throws NumberFormatException, ParseException {
		Map<String, String> user = cachePool.hgetAll(RedisDBKeys.USER + name);
		SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		if (null != user && user.size() > 0) {
			return new User(user.get("userid"), user.get("username"), user.get("nickname"), user.get("email"),
					user.get("phone"), user.get("city"), Integer.parseInt(user.get("source")), user.get("imei"), format.parse(user.get("createtime")),
					user.get("sid"));
			
			User result = (User) getSqlMapClientTemplate().queryForObject("getUserByName", parameterObject)
		}
		
		
	}
}
