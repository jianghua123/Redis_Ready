package com.zhaowei.redis.service;

import java.util.List;

public interface RedisService {
	
	public List<List<String>> activityCount(String date);

}
