package com.sample.demo.common.rest.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Rest HttpServerErrorException 발생 시 binding 객체
 * 
 * 
 * @author ikyuja79
 * @date 2017. 11. 10.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestHttpServerError implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -353564933189213983L;
	
	private long timestamp;
	private int status;
	private String error;
	private String exception;
	private String message;
	private String path;
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString()).append(" timestamp=").append(timestamp).append(", status=").append(status)
				.append(", error=").append(error).append(", exception=").append(exception).append(", message=")
				.append(message).append(", path=").append(path);
		return builder.toString();
	}
}
