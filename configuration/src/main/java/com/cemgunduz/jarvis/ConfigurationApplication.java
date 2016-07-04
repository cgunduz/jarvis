package com.cemgunduz.jarvis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableConfigServer
@EnableAutoConfiguration
@EnableEurekaClient
public class ConfigurationApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigurationApplication.class).web(true).run(args);
	}
}
