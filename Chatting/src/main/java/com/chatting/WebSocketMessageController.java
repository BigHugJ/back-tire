package com.chatting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

public class WebSocketMessageController {

	@Autowired
	private MessageRepository repository;

	@MessageMapping("/messaging")
	@SendTo("/topic/chats")
	public Message responseMessage(Message message) {
		Message m = repository.save(message);
		
		m.setMessage("reponse to: [" + HtmlUtils.htmlEscape(message.getMessage() + "]"));
		return m;
	}
}
