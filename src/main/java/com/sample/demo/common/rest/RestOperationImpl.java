package com.sample.demo.common.rest;

import java.net.SocketTimeoutException;

import org.slf4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.demo.common.rest.model.RestHttpClientError;
import com.sample.demo.common.rest.model.RestHttpServerError;

/**
 *
 * rest 실제 구동 operation
 * @author ikyuja79
 * @date 2017. 10. 24.
 *
 */
public class RestOperationImpl {

	ObjectMapper mapper = new ObjectMapper();

	protected RestOperations restOperations;
	protected String host = "";
	protected String port = "";

//	protected ApiAccessInfo accessInfo;

	private StringBuilder getRestUrl(String restUrl){
		StringBuilder sb = new StringBuilder();
		sb.append(this.host);
		if(null != this.port && !"".equals(this.port)){
			sb.append(":").append(this.port);
		}
		/**
		 * accessInfo 처리
		 */
		/*
		if(null != accessInfo) {
			if(null != accessInfo.getAccessPath() && !"".equals(accessInfo.getAccessPath())){
				sb.append("/").append(accessInfo.getAccessPath());
			}
			if(null != accessInfo.getAccessVersion() && !"".equals(accessInfo.getAccessVersion())){
				sb.append("/").append(accessInfo.getAccessVersion());
			}
		}
		*/
		sb.append(restUrl);
		return sb;
	}

	public String getCallUrl(String restUrl) {
		StringBuilder sb = getRestUrl(restUrl);
		if(null != sb){
			return sb.toString();
		}else{
			return "";
		}
	}

	public String getCallUrlNoAccess(String restUrl) {
		StringBuilder sb = getRestUrl(restUrl);
		if(null != sb) return sb.toString();
		else return "";
	}

	/**
	 *
	 * @param logger
	 * @param url
	 * @param request
	 * @param typeRef
	 * @param httpMethod
	 * @param entity
	 * @return
	 */
	protected <T> T restOperation(Logger logger, final String url, final Object request, final ParameterizedTypeReference<T> typeRef,
			final HttpMethod httpMethod, HttpEntity<Object> entity) {
		if(null == entity){
			entity = getEntity(request);
		}

		ResponseEntity<T> responseEntity = null;
		try {
			responseEntity = restOperations.exchange(url, httpMethod, entity, typeRef);
		} catch (ResourceAccessException raex) {
			Throwable t = raex.getCause();
			if (t != null && !(t instanceof SocketTimeoutException)) {
				logger.error("ResourceAccessException - {}", raex);
			}
		}catch (HttpClientErrorException hcex){
			try {
				RestHttpClientError errorVo = mapper.readValue(hcex.getResponseBodyAsString(), RestHttpClientError.class);
				logger.error("HttpClientErrorException - {}", errorVo.toString());
			} catch (Exception ex) {
//				ex.printStackTrace();
				logger.error("Exception - {}", ex);
			}
		}catch (HttpServerErrorException hsex){
			try {
				RestHttpServerError errorVo = mapper.readValue(hsex.getResponseBodyAsString(), RestHttpServerError.class);
				logger.error("HttpServerErrorException - {}", errorVo.toString());
			} catch (Exception ex) {
//				ex.printStackTrace();
				logger.error("Exception - {}", ex);
			}
        } catch (RestClientException rcex) {
        	logger.error("RestClientException - {}", rcex);
		} catch (Exception ex) {
            logger.error("exception - {}", ex);
        }

		if(null != responseEntity && HttpStatus.OK.equals(responseEntity.getStatusCode())){
//			System.out.println("==================================================================");
//			System.out.println(logger.getName());
//			System.out.println("==================================================================");
            return responseEntity.getBody();
        } else {
            if(null != responseEntity){
            	try {
            		logger.error("StatusCode : " + responseEntity.getStatusCode());
					logger.error(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(responseEntity));
				} catch (JsonProcessingException e) {
//					e.printStackTrace();
					logger.error("StatusCode : " + responseEntity.getStatusCode());
				}
            }
        }
        return null;
	}

	protected <T> T restOperation(Logger logger, final String url, final Object request, final ParameterizedTypeReference<T> typeRef,
			HttpMethod httpMethod) {
		return this.restOperation(logger, url, request, typeRef, httpMethod, null);
	}

	protected HttpEntity<Object> getEntity(Object request) {
		return getEntity(request, null);
	}

	protected HttpEntity<Object> getEntity(Object request, String mediaType) {
		HttpHeaders headers = new HttpHeaders();
		if(null != mediaType){
			headers.add("Content-Type", mediaType);
		}else{
			/*
			if(null != accessInfo) {
				if(null != accessInfo.getAccessPoint() && !"".equals(accessInfo.getAccessPoint())){
					headers.add("Access-Info", accessInfo.getAccessPoint());
				}
			}
			*/
			headers.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		}
		return new HttpEntity<Object>(request, headers);
	}

}

