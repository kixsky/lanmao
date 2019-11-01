package com.lanmao.mech.aop;

import com.lanmao.common.exception.BusinessException;
import com.lanmao.common.lock.RedissonLockUtil;
import com.lanmao.core.share.dto.MechDTO;
import com.lanmao.core.share.dto.UserDTO;
import com.lanmao.mech.auth.LoginHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class NotRepeatActionAspect {

    private final static int DEFAULT_TIME_OUT = 1;

    @Resource
    private RedissonLockUtil redissonLock;

    /**
     *
     * 分布式锁，不让操作频繁
     * @param joinPoint
     * @param notRepeatAction
     * @return
     * @throws Throwable
     */
    @Around("@annotation(notRepeatAction)")
    public Object doAround(ProceedingJoinPoint joinPoint, NotRepeatAction notRepeatAction) throws Throwable {
        MechDTO loginUser = LoginHolder.get();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        String actionKey = loginUser.getId() + "_" + methodName;
        boolean lockResult = redissonLock.lock(actionKey, DEFAULT_TIME_OUT);
        if (!lockResult) {
            throw new BusinessException("操作过于频繁");
        }
        try {
            return joinPoint.proceed();
        } finally {
            redissonLock.unLock(actionKey);
        }
    }
}
