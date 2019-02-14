package com.sample.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.sample.demo.vo.telegram.TelegramBotVo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
public class TelegramBotConfiguration {
	private final Environment env;

	@Bean
	public TelegramBotVo telegramBotVo() {
		return TelegramBotVo
				.builder()
				.botName(env.getProperty("telegram.bot.username"))
				.token(env.getProperty("telegram.bot.token"))
				.chatId(env.getProperty("telegram.bot.chatId"))
				.build();
	}
}
