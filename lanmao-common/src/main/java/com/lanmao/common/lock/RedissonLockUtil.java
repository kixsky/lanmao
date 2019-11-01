package com.lanmao.common.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedissonLockUtil {

    private final static int DEFAULT_LEASE_TIME = 15; //默认过期时间

    @Resource
    private RedissonClient redissonClient;

    public boolean lock(String lockKey, int timeout) {
        log.info("lockKey: {}, timeout: {}", lockKey, timeout);
        RLock lock = redissonClient.getLock(lockKey);
        boolean lockResult = false;
        try {
            lockResult = lock.tryLock(timeout, DEFAULT_LEASE_TIME, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.info(e.getMessage(), e);
        }
        return lockResult;
    }

    public void unLock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }

}
