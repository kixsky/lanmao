package com.lanmao.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@ComponentScan("com.lanmao")
public class LanmaoCoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanmaoCoreServiceApplication.class, args);
	}

}
