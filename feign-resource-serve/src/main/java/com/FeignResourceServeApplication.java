package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import objects.InitializerService;

@SpringBootApplication
public class FeignResourceServeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignResourceServeApplication.class, args);
	}
	
	@Bean
	public InitializerService initializerService() {
		return new InitializerService();
	}

}
