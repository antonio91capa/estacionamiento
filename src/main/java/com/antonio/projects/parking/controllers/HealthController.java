package com.antonio.projects.parking.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthController {

	private static final Logger log = LoggerFactory.getLogger(HealthController.class);

	@GetMapping
	public ResponseEntity<String> healthCheck() {
		log.info("Index Success");
		return ResponseEntity.ok().body("Success");
	}

}
