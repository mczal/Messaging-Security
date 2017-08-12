package com.test.dana;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessagingSecurityApplication {

	private static final Logger logger = LoggerFactory.getLogger(MessagingSecurityApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MessagingSecurityApplication.class, args);
	}
}
