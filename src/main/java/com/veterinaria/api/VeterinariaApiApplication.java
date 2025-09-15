package com.veterinaria.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class VeterinariaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeterinariaApiApplication.class, args);
	}

}
