package com.sample.demo.common.exception;

import com.sample.demo.vo.result.ReturnStatusCode;

public class SampleException extends Exception {
	private static final long serialVersionUID = -3154948336243601855L;

	private ReturnStatusCode returnStatusCode;

	public SampleException(ReturnStatusCode returnStatusCode) {
		this.returnStatusCode = returnStatusCode;
	}

	public ReturnStatusCode getReturnStatusCode() {
		return returnStatusCode;
	}

	public void setReturnStatusCode(ReturnStatusCode returnStatusCode) {
		this.returnStatusCode = returnStatusCode;
	}
}
