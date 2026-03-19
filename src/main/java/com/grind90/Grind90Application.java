package com.grind90;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Grind90Application {


	public static void main(String[] args) {
		SpringApplication.run(Grind90Application.class, args);
	}

	@Bean
	CommandLineRunner printApiKey(@Value("${gemini.api.key}") String key) {
		return args -> {
			System.out.println("=================================");
			System.out.println("Gemini API Key used by Spring:");
			System.out.println(key);
			System.out.println("=================================");
		};
	}
	@Bean
	CommandLineRunner showPropertySource(org.springframework.core.env.Environment env) {
		return args -> {
			System.out.println("PROPERTY VALUE = " + env.getProperty("gemini.api.key"));
		};
	}


}
