package com.chatting;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
	@Bean
	CommandLineRunner initDatabase(MessageRepository repository) {

		return args -> {
			repository.save(new Message("Hello, it is the first message", "Me", "You", new Date()));
			repository.save(new Message("Hello, it is the second message", "You", "Me", new Date()));
		};
	}
}
