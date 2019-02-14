package com.sample.demo.common.component;

import org.springframework.stereotype.Component;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.sample.demo.vo.telegram.TelegramBotVo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * telegram bot을 만든다.
 * 1. BotFater를 검색하여 /newbot을 입력 후,
 *    봇 이름과 다른명으로 봇을 또 준다.
 * 2. 만들어지면  토큰 값을 보내준다.
 * 3. 생성된 봇 확인 후, 텔레그램 그룹창을 만들고 해당 봇을 추가.
 * 4. dummy message을 보낸 후 브라우저에서
 *     https://api.telegram.org/bot<YourBOTToken>/getUpdates
 *     <YourBOTToken> 부분에 위에서 생성된 토큰 값을 넣는다.
 * 5. 결과의 json data에서 chat.id부분을 찾아 config로 추가하면 끝
 *
 * @author smluv82
 *
 */
@Slf4j
@AllArgsConstructor
@Component
public class TelegramBotComponent {
	private final TelegramBotVo telegramBotVo;

	/**
	 * @param messageText
	 * @return
	 */
	public boolean sendMessage(final String messageText) {
		log.info("sendMessage messageText : {}", messageText);
		log.info("telegramBotVo[{}]", telegramBotVo);
		TelegramBot bot = new TelegramBot(telegramBotVo.getToken());
		SendMessage request = new SendMessage(telegramBotVo.getChatId(), messageText)
				.parseMode(ParseMode.HTML)
				.disableWebPagePreview(true)
				.disableNotification(false);
		SendResponse sendResponse = bot.execute(request);

		return sendResponse.isOk();
	}
}
