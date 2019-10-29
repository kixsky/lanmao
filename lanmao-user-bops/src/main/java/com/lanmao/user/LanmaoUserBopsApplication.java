package com.lanmao.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableFeignClients(basePackages = "com.lanmao.core.share")
@ComponentScan("com.lanmao")
public class LanmaoUserBopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanmaoUserBopsApplication.class, args);
	}

}
