package com.sample.demo.service.sse.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.google.common.collect.Lists;
import com.sample.demo.common.util.DateUtils;
import com.sample.demo.vo.sse.ServerSentEventVo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class ServerSentEventService {

	private List<ResponseBodyEmitter> emitterList = Lists.newArrayList();

	private ExecutorService executorService = Executors.newCachedThreadPool();
	private ExecutorService executorService2 = Executors.newCachedThreadPool();

	private ServerSentEventVo getVo() {
		ServerSentEventVo sseVo = new ServerSentEventVo();
		sseVo.setRandomStr(RandomStringUtils.randomAlphabetic(10));
		sseVo.setRandomNum(RandomStringUtils.randomNumeric(15));
		sseVo.setRandomStrNum(RandomStringUtils.randomAlphanumeric(20));
		sseVo.setDate(DateUtils.asDate(LocalDateTime.now()));
		return sseVo;
	}

	public void addRandomData(ResponseBodyEmitter emitter) throws Exception {
		emitterList.add(emitter);
	}

//	@Scheduled(fixedRate=10_000L)
	public void eventSchedule() {
		try {
			log.info("eventSchedule init");

			if(emitterList == null || emitterList.isEmpty()) {
				log.info("emitterList is empty");
			} else {

				emitterList.parallelStream().forEach(emitter -> {
					log.info("emitter : {}", emitter);
					try {
						ServerSentEventVo sseVo = getVo();

						emitter.send(sseVo);
						emitter.complete();
					} catch (Exception e) {
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

	public void handleSse(final SseEmitter emitter) throws Exception {
		log.info("handleSse call emitter : {}", emitter);

		executorService.execute(() -> {
			try {
				ServerSentEventVo sseVo = getVo();

				emitter.send(sseVo);
				emitter.complete();
			} catch(Exception e) {
				log.error("handleSse Exception : ", e);
				emitter.completeWithError(e);
			}
		});
	}

	public Flux<ServerSentEventVo> streamSse() throws Exception {
		log.info("streamSse start");

		return Flux.interval(Duration.ofSeconds(3L))
				.map(xxx -> getVo());

	}
//	public void streamSse(final SseEmitter emitter) throws Exception {
//		log.info("streamSse call emitter : {}", emitter);
//
//		executorService2.execute(() -> {
//			try {
//				while(true) {
//					ServerSentEventVo sseVo = getVo();
//
//					SseEventBuilder event = SseEmitter
//							.event()
//							.data(sseVo)
//							.id(
//									String.join("-", "smluv82", LocalTime.now().format(DateUtils.getDateTimeFormatter(DateUtils.HHmmssSSS)))
//									)
//							.name("smluv82 sse event");
//
//					log.info("event : {}", event);
//
//					emitter.send(event);
//
//					Thread.sleep(1000L);
//				}
//			}catch(Exception e) {
//				log.error("streamSse Exception : ", e);
//				emitter.completeWithError(e);
//			}
//		});
//	}
}
