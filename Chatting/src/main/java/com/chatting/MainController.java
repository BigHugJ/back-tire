package com.chatting;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	private MessageRepository repository;
	
	public MainController(MessageRepository repository) {
		this.repository = repository;
	}
	
	@RequestMapping("/")
	public String index() {
		
		return "Greetings from Spring Boot!";
	}
	
	@RequestMapping("/messages") 
	public List<Message>  all() {
		return repository.findAll();
	}
	
}
