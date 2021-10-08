package com.qf.mybatisplus.springboot.mybaitsconfig;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ghy
 * @version 1.0
 * @date 2021-07-30
 **/
@Configuration
public class MyMybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
