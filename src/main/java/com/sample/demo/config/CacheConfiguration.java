package com.sample.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.sample.demo.common.redis.JsonRedisSerializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableCaching
public class CacheConfiguration {
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;

	@Autowired
	private JsonRedisSerializer jsonRedisSerializer;

//	@Bean
//	public JsonRedisSerializer jsonRedisSerializer() {
//		return new JsonRedisSerializer();
//	}

	private RedisCacheConfiguration redisCacheConfiguration() {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.disableCachingNullValues()
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonRedisSerializer))
				;
		redisCacheConfiguration.usePrefix();

		return redisCacheConfiguration;
	}
	/**
	 * redis cache manager
	 *
	 * @return
	 */
	@Bean
	public CacheManager redisCacheManager() {
//		RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
//		redisCacheManager.setUsePrefix(true);

		return RedisCacheManager
				.builder(jedisConnectionFactory)
				.cacheDefaults(redisCacheConfiguration())
				.build();
	}
}
