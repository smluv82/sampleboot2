package com.sample.demo.vo.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class ResultVo<T> {
	private String status;
	@JsonInclude(Include.NON_NULL)
	private String code;
	private String message;
	@JsonInclude(Include.NON_EMPTY)
	private T data;
}
