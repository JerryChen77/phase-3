package com.shine.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.List;

/**
 * 数据库事务通知
 */
public class TransactionAdvice {
    public void begin(JoinPoint joinPoint){
        joinPoint.getArgs();
        System.out.println("事务开启...........");
    }

    public void commit(Object result){
        System.out.println("事务提交=================== + " + result);
    }

    public void rollback(Exception e){
        System.err.println(e.getMessage());;
        System.out.println("事务回滚···········");
    }

    public void release(){
        System.out.println("释放资源0000000000000");
    }


    public Object around(ProceedingJoinPoint joinPoint){
        // 获取代理目标方法中携带的参数
        Object[] args = joinPoint.getArgs();

        Object result = null;

        String methodName = joinPoint.getSignature().getName();
        try {
            System.out.println("事务开启...........");
            result = joinPoint.proceed();
            System.out.println(methodName + "执行核心通知内容,参数是:" + (args!=null ? Arrays.asList(args):null));

            System.out.println("事务提交===================");
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("事务回滚···········");
        } finally {
            System.out.println("释放资源0000000000000");
        }
        return result;
    }
}
