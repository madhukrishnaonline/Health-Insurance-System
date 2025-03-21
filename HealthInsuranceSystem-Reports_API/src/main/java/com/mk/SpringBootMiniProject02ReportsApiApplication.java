package com.mk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringBootMiniProject02ReportsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMiniProject02ReportsApiApplication.class, args);
	}
}