package com.zhaowei.redis.service.impl;

import java.util.List;

import com.zhaowei.redis.dao.RedisDAOImpl;
import com.zhaowei.redis.service.RedisService;
import com.zhaowei.redis.util.RedisUtil;

public class RedisServiceImpl implements RedisService {
	
	private RedisDAOImpl dao = new RedisDAOImpl();

	@Override
	public List<List<String>> activityCount(String date) {
		return null;
	}
	
	

}
