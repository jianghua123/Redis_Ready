package com.zhaowei;

import java.text.ParseException;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhaowei.redis.service.RedisService;

import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class JedisTest extends AbstractJUnit4SpringContextTests {
	
	@Resource
	private RedisService service;
	
	@Test
	public void tGetUserByName() {
		try {
			Assert.assertNotNull(service.getUserByName("0_18610131964"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.56.102", 6379);
		System.out.println(jedis.get("sex1"));
		System.out.println(jedis.dbSize());
	}

}
