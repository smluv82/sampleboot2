package com.sample.demo.service.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.demo.common.component.ResultCodeUtil;
import com.sample.demo.service.api.service.TickerService;
import com.sample.demo.vo.result.ResultVo;
import com.sample.demo.vo.result.ReturnStatusCode;
import com.sample.demo.vo.ticker.TickerVo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ticker/{exchange}")
@AllArgsConstructor
public class TickerController {
	private final ResultCodeUtil resultCodeUtil;
	private final TickerService tickerService;

	@GetMapping("/{currency}")
	private ResponseEntity<ResultVo<TickerVo>> select(@PathVariable final String exchange, @PathVariable final String currency) throws Exception {
		log.info("ticker select");

		return new ResponseEntity<>(resultCodeUtil.getResultInfo(ReturnStatusCode.SUCCESS, tickerService.select(exchange, currency)), HttpStatus.OK);
	}
}
