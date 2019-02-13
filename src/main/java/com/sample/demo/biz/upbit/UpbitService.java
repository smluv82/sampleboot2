package com.sample.demo.biz.upbit;

import java.text.MessageFormat;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.sample.demo.biz.InterfaceService;
import com.sample.demo.common.exception.SampleException;
import com.sample.demo.common.rest.IRestTemplate;
import com.sample.demo.vo.result.ReturnStatusCode;
import com.sample.demo.vo.ticker.TickerVo;
import com.sample.demo.vo.url.RestUrlEnum.UpbitUrl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class UpbitService implements InterfaceService {
	@Resource
	private final IRestTemplate upbitRestTemplate;

	@Override
	public TickerVo ticker(String currency) throws Exception {
		log.info("upbit ticker currency[{}]", currency);

//		TickerVo resultTickerVo = upbitRestTemplate.get(MessageFormat.format(BithumbUrl.TICKER.getUrl(), currency), StringUtils.EMPTY, new ParameterizedTypeReference<List<TickerVo>>() {});
		List<TickerVo> result = upbitRestTemplate.get(MessageFormat.format(UpbitUrl.TICKER.getUrl(), currency), StringUtils.EMPTY, new ParameterizedTypeReference<List<TickerVo>>() {});

		if(result == null || result.isEmpty()) {
			throw new SampleException(ReturnStatusCode.FATAL_ERROR);
		}

		log.debug("result[{}]", result.get(0));
		return result.get(0);
	}

}
