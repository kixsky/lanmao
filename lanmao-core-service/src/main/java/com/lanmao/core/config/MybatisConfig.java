package com.lanmao.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.lanmao.core.mapper")
public class MybatisConfig {
}
