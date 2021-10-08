# 	Day 69笔记

## 一、SpringSecurity

### 1.1 概述

* Spring Security 是一个功能强大且高度可定制的身份验证和访问控制框架。它是保护基于 Spring 的应用程序的事实上的标准。
* Spring Security 是一个专注于为 Java 应用程序提供身份验证和授权的框架。与所有 Spring 项目一样，Spring Security 的真正强大之处在于它可以轻松扩展以满足自定义要求

### 1.2 特性

* 对身份验证和授权的全面且可扩展的支持
* 防止会话固定、点击劫持、跨站点请求伪造等攻击
* Servlet API 集成
* 与 Spring Web MVC 的可选集成

### 1.3 资料

* https://spring.io/projects/spring-security

## 二、快速入门

### 2.1 添加依赖

```
<!-- ... other dependency elements ... -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

### 2.2 编写控制器

```
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello SpringSecurity";
    }
}
```

### 2.3 测试

* 直接访问
  * 第一次访问需要验证，验证通过之后以后可以直接访问
  * 关闭浏览器之后身份令牌失效，需要重写验证
  * 服务器重启之后需要验证

## 三、原理剖析

### 3.1 问什么去了登录页

* 被拦截
* 被过滤器链拦截

### 3.2 怎么来的密码

* PasswordEncoder
* PasswordEncoderFactories

### 3.3 得到的对象

* UserDetailsService
  * 以后设置服务
* UserDetails
  * 以后设置对象

## 四、自定义配置验证登录

### 4.1 概述

* SpringSecurity框架自动生成的密码可以用，不好用
  * 密码太长
  * 密码只在服务端能看到
  * 我们无法控制

### 4.2 配置文件

#### 特点

* 简单粗暴
* 明文用户名和密码
  * 不安全
* 任何用户访问的时候都是这个用户名和密码
  * 不安全
  * 不灵活

```
  security:
    user:
      name: zhangsan
      password: 112233
```

### 4.3 自定义配置类--固定用户名和密码

#### 自定义配置类

```java
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

              auth
              .inMemoryAuthentication()				// 基于内存的验证管理
              .passwordEncoder(new BCryptPasswordEncoder())
              .withUser("qq")						// 认证需要的用户名
              .password(new BCryptPasswordEncoder()
              .encode("123123"))					// 认证需要的密码
              .roles("ADMIN");						// 认证的角色,方法参数可以为空


     
    }
}
```



### 4.4 自定义配置类--用户名和用户名对应的密码

