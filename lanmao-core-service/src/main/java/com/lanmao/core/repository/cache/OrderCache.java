package com.lanmao.core.repository.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class OrderCache {

    private final String PREFIX_KEY = "o_id_";

    @Resource
    private RedisTemplate<String, String> redisTemplate;
}
