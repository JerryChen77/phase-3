package com.shine.advice;

/**
 * 数据库事务通知
 */
public class TransactionAdvice {
    public void begin(){
        System.out.println("事务开启...........");
    }

    public void commit(){
        System.out.println("事务提交===================");
    }

    public void rollback(){
        System.out.println("事务回滚···········");
    }

    public void release(){
        System.out.println("释放资源0000000000000");
    }
}
