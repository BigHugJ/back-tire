package com.chatting;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketChatConfig implements WebSocketMessageBrokerConfigurer {

	@Value("${origin.ips}")
	private String origin_ips;
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		System.out.println("\nRegister STOMP");
		System.out.println("\nGet Origns" + origin_ips);
		String[] ip_array=StringUtils.split(origin_ips, ",");
		System.out.println("\nGet Origns" + ip_array);

		registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins(ip_array).withSockJS();
		//registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins("http://192.168.2.35:3000", "http://192.168.2.27:3000", "http://74.14.164.223:3000").withSockJS();
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