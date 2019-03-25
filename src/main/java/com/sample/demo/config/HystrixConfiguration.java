package com.sample.demo.config;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Configuration;

@EnableCircuitBreaker
@Configuration
public class HystrixConfiguration {

}
