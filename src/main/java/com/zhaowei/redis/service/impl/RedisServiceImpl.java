package com.zhaowei.redis.service.impl;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import com.zhaowei.redis.dao.RedisDAOImpl;
import com.zhaowei.redis.model.User;
import com.zhaowei.redis.service.RedisService;

public class RedisServiceImpl implements RedisService {
	
	@Resource
	private RedisDAOImpl redisDao;

	@Override
	public List<List<String>> activityCount(String date) {
		return null;
	}

	@Override
	public User getUserByName(String userName) throws NumberFormatException, ParseException {
		return redisDao.getUserByName(userName);
	}

	public RedisDAOImpl getRedisDao() {
		return redisDao;
	}

	public void setRedisDao(RedisDAOImpl redisDao) {
		this.redisDao = redisDao;
	}
}
