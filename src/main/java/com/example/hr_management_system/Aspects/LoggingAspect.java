package com.example.hr_management_system.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.hr_management_system.Controller.*.*(..))")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        logger.info("Entering method: " + joinPoint.getSignature().getName());
        logger.info("Arguments: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.example.hr_management_system.Controller.*.*(..))", returning = "result")
    public void logAfterMethodExecution(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: " + joinPoint.getSignature().getName());
        logger.info("Return value: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.hr_management_system.Controller.*.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception in method: " + joinPoint.getSignature().getName());
        logger.error("Exception: " + error.getMessage());
    }
}
