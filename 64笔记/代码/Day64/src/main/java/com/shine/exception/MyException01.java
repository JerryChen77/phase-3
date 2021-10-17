package com.shine.exception;

/**
 * 自定义异常01
 */
public class MyException01 extends RuntimeException {
    public MyException01() {
    }

    public MyException01(String message) {
        super(message);
    }
}
