package com.chatting;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestMessageController {
	
	private MessageRepository repository;
	private UserRepository userRepository;
	
	public RestMessageController(MessageRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
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
		return (List<Message>) repository.findAll();
	}
	
	@CrossOrigin
	@PostMapping("/message") 
	public Long postMessage(@RequestBody Message message) {
		return repository.save(message).getId();
	}
	
	@CrossOrigin
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Integer id) throws Exception {
		return userRepository.findById(id).orElseThrow(()-> new Exception());
	}
	
	@CrossOrigin
	@GetMapping("/users")
	public List<User> getUsers() throws Exception {
		return (List<User>)userRepository.findAll();
	}
	
	@CrossOrigin
	@PostMapping("/user") 
	public Integer postUser(@RequestBody User user) {
		return userRepository.save(user).getId();
	}
	
	@CrossOrigin
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Integer id) throws Exception {
		userRepository.deleteById(id);
	}
}
