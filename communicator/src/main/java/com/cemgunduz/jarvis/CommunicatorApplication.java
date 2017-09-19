package com.cemgunduz.jarvis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableEurekaClient
@EnableAutoConfiguration
@ComponentScan
@EnableCircuitBreaker
public class CommunicatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunicatorApplication.class, args);
	}
}
