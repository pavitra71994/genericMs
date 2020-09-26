package com.example.examMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;

@SpringBootApplication
//@ComponentScan(basePackages="com.example.examMicroservice.serviceInterface")
public class ExamMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamMicroserviceApplication.class, args);
	}
	
	@Bean
    public HttpHeaders objHttpHeaders() {
        return new HttpHeaders();
    }

}
