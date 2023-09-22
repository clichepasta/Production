package com.udemy.mvcCrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MvcCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcCrudApplication.class, args);
	}

}
