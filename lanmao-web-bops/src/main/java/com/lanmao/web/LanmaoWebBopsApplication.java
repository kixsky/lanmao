package com.lanmao.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.lanmao.core.share")
@ComponentScan("com.lanmao")
public class LanmaoWebBopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanmaoWebBopsApplication.class, args);
	}

}
