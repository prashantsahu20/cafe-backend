package com.cts.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.cts.service..*(..))")
    public void serviceLayer() {}

    @Before("serviceLayer()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: " + joinPoint.getSignature().getName() + " in class " + joinPoint.getTarget().getClass().getSimpleName());
    }

    @After("serviceLayer()")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Exiting method: " + joinPoint.getSignature().getName() + " in class " + joinPoint.getTarget().getClass().getSimpleName());
    }

    @AfterReturning(pointcut = "serviceLayer()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method " + joinPoint.getSignature().getName() + " in class " + joinPoint.getTarget().getClass().getSimpleName() + " returned: " + result);
    }

    @AfterThrowing(pointcut = "serviceLayer()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Method " + joinPoint.getSignature().getName() + " in class " + joinPoint.getTarget().getClass().getSimpleName() + " threw an exception: " + error);
    }
}
