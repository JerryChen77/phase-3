package com.shine.advice;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Order(100)
public class LoggerAdvice {

    @Pointcut("execution(public * com.shine.service.impl.UserServiceImpl.*(..))")
    public void pt(){}

    Logger logger = Logger.getLogger(LoggerAdvice.class);

    @Before("pt()")
    public void beforeLog(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println();
        logger.info("方法开始执行,,,," + (args!=null ? Arrays.asList(args):null));
    }

}
