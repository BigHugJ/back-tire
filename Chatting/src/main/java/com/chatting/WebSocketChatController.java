package com.chatting;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
	
	private String channel;
	
	@MessageMapping("/chatSendMessage")
	//@SendTo("/topic/jj")
	public Message sendMessage(@Payload Message webSocketChatMessage) {

		//repository.save(webSocketChatMessage);
		
		simpMessagingTemplate.convertAndSend("/topic/jj"+channel, webSocketChatMessage);
		return webSocketChatMessage;
	}

	@MessageMapping("/chatChannel")
	//@SendTo("/topic/jj")
	public Message addChannel(@Payload Message webSocketChatMessage) {

		//repository.save(webSocketChatMessage);
		channel = webSocketChatMessage.getMessage();
		//webSocketChatMessage.setMessage("channel added");
		Message channelMessage = new Message();
		channelMessage.setMessageReceiver(webSocketChatMessage.getMessage());
		channelMessage.setMessageSender(webSocketChatMessage.getMessageSender());
		channelMessage.setMessage("chatting");
		simpMessagingTemplate.convertAndSend("/topic/jj"+webSocketChatMessage.getMessageReceiver(), channelMessage);
		return webSocketChatMessage;
	}

	@MessageMapping("/chatNewUser")
	@SendTo("/topic/broadcastUsers")
	public Message newUser(@Payload User user, SimpMessageHeaderAccessor headerAccessor) {
		
		System.out.println("coming in a new user: " + user.getName());
				 
		//headerAccessor.getSessionAttributes().put("username", webSocketChatMessage.getSender());
		currentOnlineUser = null == userRepository.findAll() ? new ArrayList<User>() : userRepository.findAll();
		StringBuilder currentUserNames= new StringBuilder();
		currentOnlineUser.stream().forEach(u->currentUserNames.append(u.getName()+","));
				
		Optional<User> result = currentOnlineUser.stream().filter(u->u.getName().equalsIgnoreCase(user.getName())).findFirst();
		String rMsg = "";
		if (result.isPresent()) {
			rMsg = "existing";
		}
		else {					
			
			user.setLoginTime(new Date());
			user.setOnlineStatus("Online");
			User u = userRepository.save(user);
			rMsg = String.valueOf(u.getId());
			currentOnlineUser.add(u);
			currentUserNames.append(user.getName());
		}
		String allUsers = currentUserNames.toString().endsWith(",") ? currentUserNames.deleteCharAt(currentUserNames.length()-1).toString() : currentUserNames.toString();
		Message webSocketChatMessage = new Message();
		webSocketChatMessage.setMessageSender(user.getName());
		webSocketChatMessage.setMessageReceiver(allUsers);
		webSocketChatMessage.setMessage(rMsg);
		webSocketChatMessage.setMessageDate(new Date());
		//simpMessagingTemplate.convertAndSend("/topic/jj"+user.getName(), webSocketChatMessage);
		
		return webSocketChatMessage;
	}

	@MessageMapping("/chatDeleteUser")
	public Message deleteUser(@Payload User user, SimpMessageHeaderAccessor headerAccessor) {
		System.out.println("deleting an user");

		Optional<User> result = currentOnlineUser.stream().filter(u->u.getName().equalsIgnoreCase(user.getName())).findFirst();
		if (result.isPresent()) {
			userRepository.deleteById(result.get().getId());
		}
		
		Iterator<User> iter = currentOnlineUser.iterator();
		while(iter.hasNext()) {
			User u = (User) iter.next();
			if (u.getName().equals(user.getName())) {
				currentOnlineUser.remove(u);
				break;
			}
		}
		return null;
	}

}
