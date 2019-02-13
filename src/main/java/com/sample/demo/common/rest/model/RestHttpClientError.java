package com.sample.demo.common.rest.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestHttpClientError implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3532437733442269517L;
	
	@JsonIgnore
	private String cause;
	
	class StackTrace{
	}
	
	@JsonIgnore
	private StackTrace stackTrace;
	
	private String errorCode;
	private String httpStatus;
	private String message;
	private String code;
	private String localizedMessage;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocalizedMessage() {
		return localizedMessage;
	}
	public void setLocalizedMessage(String localizedMessage) {
		this.localizedMessage = localizedMessage;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString()).append(" errorCode=").append(errorCode).append(", httpStatus=")
				.append(httpStatus).append(", message=").append(message).append(", code=").append(code)
				.append(", localizedMessage=").append(localizedMessage);
		return builder.toString();
	}
	
}
