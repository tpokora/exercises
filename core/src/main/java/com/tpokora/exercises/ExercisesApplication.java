package com.tpokora.exercises;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.tpokora" })
@EnableAutoConfiguration
public class ExercisesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExercisesApplication.class, args);
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "7080";
		}

		System.setProperty("server.port", webPort);
		SpringApplication.run(ExercisesApplication.class, args);

	}
}
