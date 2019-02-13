package com.sample.demo.common.component;

import org.springframework.stereotype.Component;

import com.sample.demo.vo.result.ResultVo;
import com.sample.demo.vo.result.ReturnStatusCode;

@Component
public class ResultCodeUtil {
	private ResultCodeUtil() {}

	public <R> ResultVo<R> getResultInfo(ReturnStatusCode code, R r) {
		ResultVo<R> result = new ResultVo<>();
		result.setStatus(code.getStatus());
		result.setMessage(code.getMessage());
		result.setData(r);
		return result;
	}
}
