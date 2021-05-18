package com.surfer;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
@ComponentScan({ "com.controller", "com.services", "com.aop" })
@EntityScan("com.entities")
@EnableJpaRepositories("com.repositories")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		// Returns a prepared Docket instance
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(regex("/api.*"))
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Surfing App REST API", 
	      "This is the Surfing APIs provied by spring boot for school project ", 
	      "1.0", 
	      "Free to use", 
	      new Contact("Alaa,Sara,Jesper", "www.example.com", "Jensen@jensen.com"), 
	      "License of API", "API license URL", Collections.emptyList());
	}

}