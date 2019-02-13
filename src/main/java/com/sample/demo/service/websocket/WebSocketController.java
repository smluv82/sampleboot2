package com.sample.demo.service.websocket;

import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.sample.demo.vo.websocket.Greeting;
import com.sample.demo.vo.websocket.HelloMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WebSocketController {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		log.info("hello init");
		Thread.sleep(1000);
		return new Greeting(String.join(StringUtils.EMPTY, "Hello, ", HtmlUtils.htmlEscape(message.getName()), "!!!!!"));
	}
}
