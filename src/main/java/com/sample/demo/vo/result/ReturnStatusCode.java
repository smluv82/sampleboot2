package com.sample.demo.vo.result;

public enum ReturnStatusCode {
	SUCCESS("0000", "success"),
	FATAL_ERROR("9999", "fail")
	;

	private String status;
	private String message;

	private ReturnStatusCode(String status, String message) {
		this.status = status;
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
}
