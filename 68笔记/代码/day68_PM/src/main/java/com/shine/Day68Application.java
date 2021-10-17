package com.shine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.shine")
public class Day68Application {

    public static void main(String[] args) {
        SpringApplication.run(Day68Application.class, args);
    }

}
