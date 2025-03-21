package com.mk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringBootMiniProject01PlansApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMiniProject01PlansApplication.class, args);
	}
}// class