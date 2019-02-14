package com.sample.demo.vo.telegram;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TelegramBotVo {
	private String botName;
	private String token;
	private String chatId;
}
