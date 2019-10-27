package com.lanmao.mech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.lanmao.core.share")
public class LanmaoMechBopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanmaoMechBopsApplication.class, args);
	}

}
