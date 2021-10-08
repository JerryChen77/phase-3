package com.mybatis.mybatisplus.conf;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册MyMybatisPlus的分页插件
 * @author Cjl
 * @date 2021/8/1 19:54
 */

@Configuration
public class MyBatisPlusConf {
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
