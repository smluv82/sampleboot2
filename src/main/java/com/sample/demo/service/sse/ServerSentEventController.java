package com.sample.demo.service.sse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.sample.demo.service.sse.service.ServerSentEventService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/sse")
public class ServerSentEventController {
	private static final Long TIMEOUT = 1000L;

	private final ServerSentEventService serverSentEventService;

	/**
	 * ResponseBodyEmitter
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value="/test", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	private ResponseBodyEmitter sseTest() throws Exception {
		log.info("sseTest init");
//		SseEmitter emitter = new SseEmitter(TIMEOUT);
		SseEmitter emitter = new SseEmitter();
		serverSentEventService.addRandomData(emitter);

		return emitter;
	}
}
