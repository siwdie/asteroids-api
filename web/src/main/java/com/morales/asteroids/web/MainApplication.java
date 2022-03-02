package com.morales.asteroids.web;

import com.morales.nasa.client.api.NasaClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@ComponentScan(basePackages = {"com.morales"})
@EnableFeignClients(clients = {NasaClient.class})
@OpenAPIDefinition(info = @Info(title = "Asteroids API", version = "1.0.0", description = "Asteroids Information"))
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
