package com.webtire.tireeurekanamingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  
public class TireEurekaNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TireEurekaNamingServerApplication.class, args);
	}

}
