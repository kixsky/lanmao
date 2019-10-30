package com.lanmao.web.controller;

import com.lanmao.common.annotation.IgnorePath;
import com.lanmao.common.lock.RedissonLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/v1/order")
public class OrderController {

    @Resource
    private RedissonLockUtil redissonLock;

    @IgnorePath
    @RequestMapping(value = "/lock", method = RequestMethod.GET)
    public boolean lock(@RequestParam("lockKey") String lockKey) {
        return redissonLock.lock(lockKey, 2);
    }
}
