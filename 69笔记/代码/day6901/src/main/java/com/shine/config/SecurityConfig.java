package com.shine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration  // 这个类是配置类，作用是配置信息
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *
     * @param auth          // 认证管理构建器
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pwd = passwordEncoder.encode("112233");
        System.out.println(pwd);

        auth
                .inMemoryAuthentication()       // 基于内存的验证管理
                .withUser("lisi")   // 认证需要的用户名
                .password("{noop}123123")             // 认证需要的密码
                // .password(pwd)             // 认证需要的密码
                .roles();                       // 认证的角色,方法参数可以为空
    }
}
