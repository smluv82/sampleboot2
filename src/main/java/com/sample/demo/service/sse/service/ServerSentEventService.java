package com.sample.demo.service.sse.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import com.google.common.collect.Lists;
import com.sample.demo.vo.sse.ServerSentEventVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServerSentEventService {

	private List<ResponseBodyEmitter> emitterList = Lists.newArrayList();

	public void addRandomData(ResponseBodyEmitter emitter) throws Exception {
		emitterList.add(emitter);
	}

	@Scheduled(fixedRate=10_000L)
	public void eventSchedule() {
		try {
			log.info("eventSchedule init");

			if(emitterList == null || emitterList.isEmpty()) {
				log.info("emitterList is empty");
			} else {

				emitterList.parallelStream().forEach(emitter -> {
					log.info("emitter : {}", emitter);
					ServerSentEventVo sseVo = new ServerSentEventVo();
					sseVo.setRandomStr(RandomStringUtils.randomAlphabetic(10));
					sseVo.setRandomNum(RandomStringUtils.randomNumeric(15));
					sseVo.setRandomStrNum(RandomStringUtils.randomAlphanumeric(20));

					try {
						emitter.send(sseVo);
						emitter.complete();
					} catch (IOException e) {
						log.error("emitter send IOException : ", e);
						emitter.completeWithError(e);
					}
				});

				emitterList = Lists.newArrayList();
			}
		} catch(Exception e) {
			log.error("eventSchedule Exception : ", e);
		}
	}

}
