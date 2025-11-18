package com.dkmo.living_chatting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LivingChattingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivingChattingApplication.class, args);
		System.out.println("teste");
	}
}
