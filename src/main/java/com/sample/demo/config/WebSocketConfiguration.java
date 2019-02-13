package com.sample.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

	/* (non-Javadoc)
	 *
	 * 클라이언트에서 WebSocket을 연결할 api를 설정
	 * 파라미터로 넘겨받는 StompEndpointRegistry의 메소드인 addEndpoint() 메서드를 통해 여러가지 end point 설정 가능
	 *
	 *
	 * @see org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer#registerStompEndpoints(org.springframework.web.socket.config.annotation.StompEndpointRegistry)
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/gs-guide-websocket").withSockJS();

//		WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//메모리 기반 메세지 브로커가 해당 api를 구독하고 있는 클라이언트에게 메세지를 전달
		registry.enableSimpleBroker("/topic");
		//서버에서 클라이언트로부터의 메세지를 받을 api의 prefix 설정
		registry.setApplicationDestinationPrefixes("/app");

//		WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
	}

}
