package com.sample.demo.common.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sample.demo.common.component.ResultCodeUtil;
import com.sample.demo.common.exception.SampleException;
import com.sample.demo.vo.result.ResultVo;
import com.sample.demo.vo.result.ReturnStatusCode;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@ControllerAdvice
@Slf4j
public class SampleExceptionHandler extends ResponseEntityExceptionHandler  {
	private final ResultCodeUtil resultCodeUtil;

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(value= SampleException.class)
	public ResponseEntity<ResultVo> sampleException(Exception e) {
		log.error("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		log.error("SampleException Handler call");
		log.error("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		log.error("SampleException : ", e);
		return new ResponseEntity<>(resultCodeUtil.getResultInfo(((SampleException) e).getReturnStatusCode(), null), HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(value= Exception.class)
	public ResponseEntity<ResultVo> exceptionHandler(Exception e) {
		log.error("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		log.error("EXception Handler call");
		log.error("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		log.error("exception : ", e);
		return new ResponseEntity<>(resultCodeUtil.getResultInfo(ReturnStatusCode.FATAL_ERROR, null), HttpStatus.OK);
	}
}

