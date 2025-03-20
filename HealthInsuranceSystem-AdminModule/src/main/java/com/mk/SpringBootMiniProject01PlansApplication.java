package com.mk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // (exclude = { DataSourceAutoConfiguration.class,
						// HibernateJpaAutoConfiguration.class })
//@EnableSwagger2
public class SpringBootMiniProject01PlansApplication {
	
	/*
	 * @Bean Docket docket() { return new
	 * Docket(DocumentationType.SWAGGER_2).select()
	 * .apis(RequestHandlerSelectors.basePackage("com.mk.controller")).paths(
	 * PathSelectors.any()).build(); }
	 */

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMiniProject01PlansApplication.class, args);
	}

}// class