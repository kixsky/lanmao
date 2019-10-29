package com.lanmao.common.lock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisLock {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     *
     * 加锁
     * @param lockKey
     * @param clientId
     * @param timeout
     * @return
     */
    public boolean lock(String lockKey, String clientId, int timeout) {
        return redisTemplate.opsForValue().setIfPresent(lockKey, clientId, timeout, TimeUnit.SECONDS);
    }


    /**
     *
     * 释放锁
     * @param lockKey
     * @param clientId
     */
    public void release(String lockKey, String clientId) {
        String tempClientId = redisTemplate.opsForValue().get(lockKey);
        if (clientId.equals(tempClientId)) {
            redisTemplate.delete(lockKey);
        }
    }
}
