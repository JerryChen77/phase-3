package com.shine.advice;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

public class LoggerAdvice {

    Logger logger = Logger.getLogger(LoggerAdvice.class);

    public void beforeLog(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println();
        logger.info("方法开始执行,,,," + (args!=null ? Arrays.asList(args):null));
    }

}
