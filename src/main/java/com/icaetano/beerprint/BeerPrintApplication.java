package com.icaetano.beerprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class BeerPrintApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeerPrintApplication.class, args);
		System.out.println("Projeto Beer Print");
	}

}
