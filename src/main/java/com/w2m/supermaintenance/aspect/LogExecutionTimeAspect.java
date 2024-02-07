package com.w2m.supermaintenance.aspect;

import com.w2m.supermaintenance.annotation.LogExecutionTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogExecutionTimeAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogExecutionTimeAspect.class);

    @Pointcut("@annotation(com.w2m.supermaintenance.annotation.LogExecutionTime)")
    public void annotateLogExecutionTime() {}

    @Around("annotateLogExecutionTime()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        
        try {
            return joinPoint.proceed(); // Ejecuta el método anotado.
        } finally {
            long executionTime = System.currentTimeMillis() - start;
            logger.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        }
    }
}