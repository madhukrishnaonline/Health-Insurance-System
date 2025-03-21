package com.mk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringBootApplicationRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplicationRegistrationApplication.class, args);
	}//main
}//class