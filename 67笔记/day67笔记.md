# Day67笔记

## 一、SpringBoot

### 1.1 概述

* Spring Boot是由Pivotal团队提供的全新[框架](https://baike.baidu.com/item/框架/1212667)，其设计目的是用来[简化](https://baike.baidu.com/item/简化/3374416)新[Spring](https://baike.baidu.com/item/Spring/85061)应用的初始搭建以及开发过程。
* 该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。
* 通过这种方式，Spring Boot致力于在蓬勃发展的快速应用开发领域(rapid application development)成为领导者。

### 1.2 特性

* SpringBoot基于Spring4.0设计，不仅继承了Spring框架原有的优秀特性，而且还通过简化配置来进一步简化了Spring应用的整个搭建和开发过程。

* 另外SpringBoot通过集成大量的框架使得依赖包的版本冲突，以及引用的不稳定性等问题得到了很好的解决。

  SpringBoot所具备的特征有：

  （1）可以创建独立的[Spring](https://baike.baidu.com/item/Spring/85061)应用程序，并且基于其Maven或Gradle插件，可以创建可执行的JARs和WARs；

  （2）内嵌Tomcat或Jetty等Servlet容器；

  （3）提供自动配置的“starter”项目对象模型（POMS）以简化[Maven](https://baike.baidu.com/item/Maven/6094909)配置；

  （4）尽可能自动配置Spring容器；

  （5）提供准备好的特性，如指标、健康检查和外部化配置；

  （6）绝对没有代码生成，不需要XML配置。

## 二、快速入门

### 2.1 创建SpringBoot项目

### 2.2 创建controller

### 2.3 访问

### 2.4 启动SpringBoot项目

#### 2.4.1 项目内启动

* 运行main

#### 2.4.2 命令行启动

* java -jar C:\Users\Dushine2008\Desktop\day67-0.0.1-SNAPSHOT.jar

#### 2.4.3 mvn指令启动

* 在插件中双击spring-boot:run
* 在命令提示符中输入mvn spring-boot:run

## 三、配置文件

### 3.1 文件格式

* properties
* yaml/yml（目前使用）
* xml

### 3.2 优先级

#### 同文件夹下配置文件的优先级

* properties类型的文件在同文件夹下优先级最高

#### 不同文件夹下哪个位置优先级最高

* 项目根目录下的配置文件优先级最高

### 3.3 多环境配置

* 测试环境
* 开发环境
* 发布环境
* 备份
* ... ...

#### 在默认的配置文件中引入其他配置文件

```yml
spring:
  profiles:
    active: bak
```

#### 命令提示符中设置

```
java -jar C:\Users\Dushine2008\Desktop\day67-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

#### 图形化界面中设置

```
-Dspring.profiles.active=test
```

### 3.4 热部署

* 在不重启服务的情况下更新模块中的内容

#### 添加依赖

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>
```

#### 设置

```
settings==>Build===>Comliler===>Build Project auto...
```

#### 激活

* 在pom文件中使用组合键
  * ctrl + alt + shift + /
  * 勾选auto Mark allow...

## 四、yml文件基本

### 4.1 语法

```
对象a:
  属性b:
  	数据c: 数据
```

```
${对象a.对象b.数据c}
```

### 4.2 配置文件

```
#server:
#  port: 8081

#spring:
#  profiles:
#    active: bak

name: zhangsan

user:
  id: 10010
  username: lisisi
  password: sisili

user2:
  id: 10012
  username: wangwu
  password: wuwang
  names: [xiaowu,dawu,wuer]

# User3对应的数据
user3:
  id: 10013
  username: zhaoliu
  password: liuzhao
  names: [xiaoliu,xiaozhao]
  hobby:
    - swim
    - basketball
    - football
  phones:
    zhangsan: 10086
    lisisi: 10010
```

### 4.3 User3

```
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component  // 把对象注入spring容器，user3
public class User3 {
    private Integer id;
    private String username;
    private String password;
    private String[] names;
    private List<String> hobby;
    private Map<String,String> phones;
}
```

### 4.4 控制器

```
package com.shine.controller;

        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user3")
public class UserController3 {

    @Value("${user3.id}")
    private Integer id;

    @Value("${user3.username}")

    private String username;

    @Value("${user3.password}")
    private String password;

    @Value("${user3.names[0]}")
    private String names0;

    @Value("${user3.hobby[0]}")
    private String hobby0;

    @Value("${user3.phones[zhangsan]}")
    private String phones0;

    @RequestMapping("/getInfo01")
    public String getInfo01(){
        System.out.println("user3.id==>" + id);
        System.out.println("user3.username==>" + username);
        System.out.println("user3.password==>" + password);
        System.out.println("user3.names0==>" + names0);
        System.out.println("user3.hobby0==>" + hobby0);
        System.out.println("user3.phone0==>" + phones0);
        return "Hello";
    }
}
```

### 测试

```
http://localhost:8080/user3/getInfo01
```

## 五、注解

### 5.1 @Bean

* 创建对象，注入容器
* 相当于spring配置文件中的bean标签

#### User5

```
package com.shine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Component  // 把对象注入spring容器，user5
public class User5 {
    private Integer id;
    private String username;
    private String password;
}
```

#### 配置类

```
import com.shine.entity.User5;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 当前类标记为配置类，作用等同于配置文件
 */
@Configuration
public class UserConfig {

    /**
         <bean id=user5 class=com.shine.entity.User5>
            <property name=id>112233</property>
            <property name=username>112233</property>
            <property name=password>112233</property>
         </bean>
     */

    @Bean
    public User5 user5(){
        User5 user5 = new User5();
        user5.setId(112233);
        user5.setUsername("赵子龙");
        user5.setPassword("xiaolong");
        return user5;
    }

}
```

#### 测试

```
@RestController
@RequestMapping("/user5")
public class UserController5 {
    @Autowired
    private User5 user5;

    @RequestMapping("/getInfo01")
    public String getInfo01(){
        System.out.println("user5==>" + user5);
        return "Hello";
    }
}
```

### 5.2 @SpringBootConfiguration

* 该注解是@Configuration在起作用
* 表示把当前对象当做一个配置类使用，功能类似xml格式的配置文件

### 5.3 @EnableAutoConfiguration

* 是一个组合注解
  * @AutoConfigurationPackage
  * @Import(AutoConfigurationImportSelector.class)