package com.sample.demo.biz;

import com.sample.demo.vo.ticker.TickerVo;

public interface InterfaceService {
	TickerVo ticker(String currency) throws Exception;
}
