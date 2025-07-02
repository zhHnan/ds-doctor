package com.hnz.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @Author：hnz
 * @Project：ds-doctor
 * @name：ServiceLogAspect
 * @Date：2025/6/29 19:58
 * @Filename：ServiceLogAspect
 */

@Aspect
@Slf4j
@Component
public class ServiceLogAspect {

    @Around("execution(* com.hnz.service..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        String point = joinPoint.getTarget().getClass().getName() + "."
                + joinPoint.getSignature().getName();
        stopWatch.stop();
        log.info("【{}】耗时：{}ms", point, stopWatch.getTotalTimeMillis());
        return result;
    }
}
