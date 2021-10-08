package com.qf.mybatisplus.springboot;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.qf.mybatisplus.springboot.mapper"})
public class MybatisPlusSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusSpringbootApplication.class, args);
    }
/*
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }*/

}
