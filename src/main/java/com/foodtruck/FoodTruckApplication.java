package com.foodtruck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ComponentScan("com.foodtruck")
public class FoodTruckApplication  {
	public static void main(String[] args) {
		SpringApplication.run(FoodTruckApplication.class, args);
	}

}
