package com.sample.demo.biz.bithumb.service;

import java.text.MessageFormat;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.sample.demo.biz.InterfaceService;
import com.sample.demo.common.rest.IRestTemplate;
import com.sample.demo.vo.result.ResultVo;
import com.sample.demo.vo.ticker.TickerVo;
import com.sample.demo.vo.url.RestUrlEnum.BithumbUrl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class BithumbService implements InterfaceService {

	@Resource
	private final IRestTemplate bithumbRestTemplate;

	@Override
	public TickerVo ticker(final String currency) throws Exception {
		log.info("bithumb ticker currency[{}]", currency);

		ResultVo<TickerVo> resultTickerVo = bithumbRestTemplate.get(MessageFormat.format(BithumbUrl.TICKER.getUrl(), currency), StringUtils.EMPTY, new ParameterizedTypeReference<ResultVo<TickerVo>>() {});
		log.info("resultTickerVo[{}]", resultTickerVo);

		return resultTickerVo.getData();
	}
}
