package com.icaetano.beerprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration
@SpringBootApplication
@EnableCaching
@EnableSpringDataWebSupport
public class BeerPrintApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerPrintApplication.class, args);
		System.out.println("Projeto Beer Print");
	}

}
