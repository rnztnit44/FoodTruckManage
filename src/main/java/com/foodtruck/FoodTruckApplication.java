package com.foodtruck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.foodtruck")
@EnableJpaRepositories
@EnableAutoConfiguration
public class FoodTruckApplication  {
	public static void main(String[] args) {
		SpringApplication.run(FoodTruckApplication.class, args);
	}

}
