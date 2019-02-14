package com.sample.demo.service.telegram;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.demo.common.component.TelegramBotComponent;
import com.sample.demo.common.util.DateUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/telegram")
public class TelegramController {
	private final TelegramBotComponent telegramBotComponent;

	@GetMapping("/send/test/{message}")
	private ResponseEntity<String> sendMessageTest(@PathVariable final String message) throws Exception {
		log.info("sendMessageTest message[{}]", message);
		telegramBotComponent.sendMessage(String.join("-", LocalDateTime.now().format(DateUtils.getDateTimeFormatter(DateUtils.YYYYMMDDHHmmssSSS_DASH)), message));

		return new ResponseEntity<>("test", HttpStatus.OK);
	}
}
