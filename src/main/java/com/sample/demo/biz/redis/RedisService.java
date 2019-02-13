package com.sample.demo.biz.redis;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sample.demo.common.redis.RedisDataRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisService {

	@Autowired
	@Qualifier("redisAPI")
	private RedisDataRepository<String, Object> redisData;

	public String create() throws Exception{
		final String redisKey = String.join("-", "test", RandomStringUtils.randomAlphanumeric(5));
		redisData.setData(redisKey, "testtest");
		return redisKey;
	}

	public String get(final String redisKey) throws Exception {
		return String.valueOf(redisData.getData(redisKey));
	}
}
