package com.chatting;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketChatController {

	@Autowired
	private MessageRepository repository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	private List<User> currentOnlineUser ;
	
	@MessageMapping("/chatSendMessage")
	//@SendTo("/topic/jj")
	public Message sendMessage(@Payload Message webSocketChatMessage) {

		repository.save(webSocketChatMessage);
		
		String chattingPairKey = webSocketChatMessage.getMessageSender() + webSocketChatMessage.getMessageReceiver();
		if (StringUtils.compareIgnoreCase(webSocketChatMessage.getMessageSender(), webSocketChatMessage.getMessageReceiver()) > 0) {
			chattingPairKey = webSocketChatMessage.getMessageReceiver() + webSocketChatMessage.getMessageSender();
		}
		
		simpMessagingTemplate.convertAndSend("/topic/jj"+chattingPairKey, webSocketChatMessage);
		return webSocketChatMessage;
	}

	@MessageMapping("/chatNewUser")
	//@SendTo("/topic/jun")
	public Message newUser(@Payload User user, SimpMessageHeaderAccessor headerAccessor) {
		
		System.out.println("coming in a new user");
		
		currentOnlineUser = null == userRepository.findAll() ? new ArrayList<User>() : userRepository.findAll();
		 
		//headerAccessor.getSessionAttributes().put("username", webSocketChatMessage.getSender());
		Message webSocketChatMessage = new Message();
		webSocketChatMessage.setMessageSender(user.getName());
		
		Optional<User> result = currentOnlineUser.stream().filter(u->u.getName().equalsIgnoreCase(user.getName())).findFirst();
		if (result.isPresent()) {
			webSocketChatMessage.setMessageReceiver(null);
			webSocketChatMessage.setMessage("existing");
		}
		else {
			
			StringBuilder currentUserNames= new StringBuilder();
			currentOnlineUser.stream().forEach(u->currentUserNames.append(u.getName()+","));;
			currentOnlineUser.add(user);
			if (currentUserNames.length() > 1) {
				currentUserNames.deleteCharAt(currentUserNames.length()-1);
				webSocketChatMessage.setMessageReceiver(currentUserNames.toString());
			}
			
			user.setLoginTime(new Date());
			user.setOnlineStatus("Online");
			userRepository.save(user);
			
			webSocketChatMessage.setMessage("added");

		}
		
		System.out.println("end point: " + user.getName());

		simpMessagingTemplate.convertAndSend("/topic/jj"+user.getName(), webSocketChatMessage);
		
		return webSocketChatMessage;
	}

}