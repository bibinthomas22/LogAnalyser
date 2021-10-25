package com.example.LogAnalyser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.service.AppService;

@SpringBootApplication
public class LogAnalyserApplication implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAnalyserApplication.class);

	@Autowired
	private AppService service;

	public static void main(String[] args) {
		
		LOGGER.info("Started Log Analyser");
		SpringApplication.run(LogAnalyserApplication.class, args);
		
	}

	@Override
	public void run(String... args) {

		service.execute(args);

	}

}
