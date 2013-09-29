package com.zhaowei.redis.service;

import java.text.ParseException;
import java.util.List;

import com.zhaowei.redis.model.User;

public interface RedisService {
	
	public List<List<String>> activityCount(String date);
	
	/**
	 * 测试获取USER对象
	 * @param userName
	 * @return
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public User getUserByName(String userName) throws NumberFormatException, ParseException;
	

}
