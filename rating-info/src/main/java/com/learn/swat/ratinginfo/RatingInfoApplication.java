package com.learn.swat.ratinginfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RatingInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingInfoApplication.class, args);
	}

}
