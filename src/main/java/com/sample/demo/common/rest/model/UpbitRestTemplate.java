package com.sample.demo.common.rest.model;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import com.sample.demo.common.rest.IRestTemplate;
import com.sample.demo.common.rest.RestOperationImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpbitRestTemplate extends RestOperationImpl implements IRestTemplate {

	public UpbitRestTemplate(RestOperations restOperation, String host, String port){
		this.restOperations = restOperation;
		this.host = host;
		this.port = port;
	}

	@Override
	public <T> T get(String url, Object request, ParameterizedTypeReference<T> typeRef) {
		return send(url, request, typeRef, HttpMethod.GET, null);
	}

	@Override
	public <T> T post(String url, Object request, ParameterizedTypeReference<T> typeRef) {
		return send(url, request, typeRef, HttpMethod.POST, null);
	}

	@Override
	public <T> T put(String url, Object request, ParameterizedTypeReference<T> typeRef) {
		return send(url, request, typeRef, HttpMethod.PUT, null);
	}

	@Override
	public <T> T delete(String url, Object request, ParameterizedTypeReference<T> typeRef) {
		return send(url, request, typeRef, HttpMethod.DELETE, null);
	}

	@Override
	public <T> T send(String url, Object request, ParameterizedTypeReference<T> typeRef, HttpMethod httpMethod) {
		return restOperation(
				log
				, HttpMethod.GET.equals(httpMethod) ? getCallUrl(url) + String.valueOf(request) : getCallUrl(url)
				, request
				, typeRef
				, httpMethod);
	}

	@Override
	public <T> T send(String url, Object request, ParameterizedTypeReference<T> typeRef, HttpMethod httpMethod,
			HttpEntity<Object> entity) {
		return restOperation(
				log
				, HttpMethod.GET.equals(httpMethod) ? getCallUrl(url) + String.valueOf(request) : getCallUrl(url)
				, request
				, typeRef
				, httpMethod
				, entity);
	}

}
