package com.sample.demo.service.redis;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.demo.biz.redis.RedisService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/redis")
public class RedisController {
	private final RedisService redisService;

	@GetMapping("/create")
	private ResponseEntity<String> redisInsert() {
		log.debug("test redis insert");
		try {
			return new ResponseEntity<>(redisService.create(), HttpStatus.OK);
		}catch(Exception e) {
			log.error("Exception : ", e);
			return new ResponseEntity<>(ExceptionUtils.getStackTrace(e), HttpStatus.OK);
		}
	}

	@GetMapping("/get/{redisKey}")
	private ResponseEntity<String> get(@PathVariable final String redisKey) {
		log.debug("test redis get");

		try {
			return new ResponseEntity<>(redisService.get(redisKey), HttpStatus.OK);
		} catch(Exception e) {
			log.error("Exception : ", e);
			return new ResponseEntity<>(ExceptionUtils.getStackTrace(e), HttpStatus.OK);
		}
	}
}
