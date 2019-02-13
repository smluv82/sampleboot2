package com.sample.demo.service.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sample.demo.biz.InterfaceService;
import com.sample.demo.biz.bithumb.service.BithumbService;
import com.sample.demo.biz.upbit.UpbitService;
import com.sample.demo.common.exception.SampleException;
import com.sample.demo.vo.result.ReturnStatusCode;
import com.sample.demo.vo.ticker.TickerVo;
import com.sample.demo.vo.url.ExchangeConstant;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class TickerService {
	private final BithumbService bithumbService;
	private final UpbitService upbitService;

	public InterfaceService selectExchange(final String exchange) throws Exception {
		InterfaceService resultService = null;

		switch (exchange) {
		case ExchangeConstant.BITHUMB:
			resultService = bithumbService;
			break;
		case ExchangeConstant.UPBIT:
			resultService = upbitService;
			break;
		default:
			break;
		}

		return resultService;
	}

	public TickerVo select(final String exchange, final String currency) throws Exception {
		log.info("select exchange[{}] <> currency[{}]", exchange, currency);

		InterfaceService service = Optional.ofNullable(selectExchange(exchange))
				.orElseThrow(() -> new SampleException(ReturnStatusCode.FATAL_ERROR));

		return Optional.ofNullable(service.ticker(currency))
				.orElseThrow(() ->new SampleException(ReturnStatusCode.FATAL_ERROR));
	}
}
