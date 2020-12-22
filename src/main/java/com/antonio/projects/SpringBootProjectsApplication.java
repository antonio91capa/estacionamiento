package com.antonio.projects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.antonio.projects.parking.models", "com.antonio.projects.parking"})
public class SpringBootProjectsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectsApplication.class, args);
	}

}
