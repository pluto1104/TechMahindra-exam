package com.exam.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ExamApplication.class);
	    application.setWebApplicationType(WebApplicationType.NONE);
	    application.run(args);
	}

}
