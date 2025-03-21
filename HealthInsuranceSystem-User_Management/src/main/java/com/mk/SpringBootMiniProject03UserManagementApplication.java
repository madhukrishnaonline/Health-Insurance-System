package com.mk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringBootMiniProject03UserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMiniProject03UserManagementApplication.class, args);
	}
}