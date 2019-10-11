package com.lanmao.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LanmaoCoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanmaoCoreServiceApplication.class, args);
	}

}
