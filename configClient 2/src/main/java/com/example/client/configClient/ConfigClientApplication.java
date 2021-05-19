package com.example.client.configClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class ConfigClientApplication {

	@Value("${origin.ips}")
	private String userRole;
//	@Value("${msg}")
//	private String msg;
//	
	@RequestMapping("/user") 
	public String getUserRole() {
		return userRole;
	}
	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

}
