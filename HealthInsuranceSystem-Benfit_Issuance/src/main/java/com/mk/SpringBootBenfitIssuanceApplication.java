package com.mk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringBootBenfitIssuanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBenfitIssuanceApplication.class, args);
	}//main
}//class