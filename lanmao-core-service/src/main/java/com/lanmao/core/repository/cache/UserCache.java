package com.lanmao.core.repository.cache;

import com.alibaba.fastjson.JSON;
import com.lanmao.core.share.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class UserCache {

    private final String PREFIX_KEY = "u_id_";

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public UserDTO getUser(Long userId) {
        String key = PREFIX_KEY + userId;
        String jsonData = redisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(jsonData)) {
            return null;
        }
        return JSON.parseObject(jsonData, UserDTO.class);
    }

    public void putUser(Long userId, UserDTO userDTO) {
        String key = PREFIX_KEY + userId;
        String jsonData = JSON.toJSONString(userDTO);
        redisTemplate.opsForValue().set(key, jsonData,300, TimeUnit.SECONDS);
    }
}
