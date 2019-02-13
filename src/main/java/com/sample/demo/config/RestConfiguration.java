package com.sample.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.sample.demo.common.rest.IRestTemplate;
import com.sample.demo.common.rest.model.BithumbRestTemplate;
import com.sample.demo.common.rest.model.UpbitRestTemplate;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class RestConfiguration {
	private final Environment env;

	private RestOperations getRestTemplate(HttpComponentsClientHttpRequestFactory factory) {
		RestTemplate restTemplate = new RestTemplate(factory);
		return restTemplate;
	}

	private RestOperations getRestOperation(int readTimeout){
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setConnectTimeout(readTimeout);
		factory.setReadTimeout(readTimeout);
		return getRestTemplate(factory);
	}

//	@Bean(name="bithumbRestTemplate", autowire=Autowire.BY_NAME)
	@Bean(name="bithumbRestTemplate")
	public IRestTemplate bithumbRestTemplate() {
		return new BithumbRestTemplate(
				getRestOperation(env.getProperty("rest.bithumb.timeout", int.class, 15000))
				, env.getProperty("rest.bithumb.host")
				, env.getProperty("rest.bithumb.port")
				);
	}

	@Bean(name="upbitRestTemplate")
	public IRestTemplate upbitRestTemplate() {
		return new UpbitRestTemplate(
				getRestOperation(env.getProperty("rest.upbit.timeout", int.class, 15000))
				, env.getProperty("rest.upbit.host")
				, env.getProperty("rest.upbit.port")
				);
	}
}
