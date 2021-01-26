package com.chatting;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketChatConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		System.out.println("\nRegister STOMP");
		registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins("http://192.168.2.35:3000", "http://192.168.2.27:3000", "http://74.14.164.223:3000").withSockJS();

	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		System.out.println("\nRegister BROKER");

		registry.setApplicationDestinationPrefixes("/app");
		registry.enableSimpleBroker("/topic");
//		registry.enableStompBrokerRelay("/topic").setRelayHost("localhost").setRelayPort(61613).setClientLogin("guest")
//				.setClientPasscode("guest");

	}
}