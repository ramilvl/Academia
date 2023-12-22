package com.project.OnlineLearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.project.OnlineLearning.entity")
public class OnlineLearningApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnlineLearningApplication.class, args);
	}
}