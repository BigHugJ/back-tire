package com.chatting;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestMessageController {
	
	private MessageRepository repository;
	
	public RestMessageController(MessageRepository repository) {
		this.repository = repository;
	}
	
	@RequestMapping("/")
	public String index() {
		
		return "Greetings from Spring Boot!";
	}
	
	@CrossOrigin
	@GetMapping("/messages/{id}")
	public Message getMessage(@PathVariable Long id) throws Exception {
		return repository.findById(id).orElseThrow(()-> new Exception());
	}
	
	@CrossOrigin
	@GetMapping("/messages") 
	public List<Message>  getAllMessage() {
		return repository.findAll();
	}
	
	@CrossOrigin
	@PostMapping("/messages") 
	public Long postMessage(@RequestBody Message message) {
		return repository.save(message).getId();
	}
}
