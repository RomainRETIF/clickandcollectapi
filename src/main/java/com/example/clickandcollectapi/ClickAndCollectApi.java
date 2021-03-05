package com.example.clickandcollectapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ClickAndCollectApi {

	public static void main(String[] args) {
		SpringApplication.run(ClickAndCollectApi.class, args);
	}
}
