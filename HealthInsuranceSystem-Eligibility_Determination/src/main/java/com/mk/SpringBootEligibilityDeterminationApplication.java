package com.mk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringBootEligibilityDeterminationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEligibilityDeterminationApplication.class, args);
	}//main
}//class