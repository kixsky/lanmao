package com.lanmao.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@ComponentScan("com.lanmao")
@EnableTransactionManagement
public class LanmaoCoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanmaoCoreServiceApplication.class, args);
	}

}
