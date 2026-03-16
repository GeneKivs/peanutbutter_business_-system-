package com.peanutbutter.peanutbutter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PeanutbutterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeanutbutterApplication.class, args);
		System.out.println("Application started");
	}

}
