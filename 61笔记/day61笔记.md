# Day61笔记

### 一、SpringMVC

------

#### 1.1 引言

> **java开源框架，Spring Framework的一个独立模块。**
>
> **MVC框架,在项目中开辟MVC层次架构**    
>
> **对控制器中的功能 包装 简化 扩展践行工厂模式，功能架构在工厂之上**

#### 1.2 MVC架构

##### 1.2.1 概念

| 名称       | 职责                                                         |
| ---------- | ------------------------------------------------------------ |
| Model      | 模型：即业务模型，负责完成业务中的数据通信处理，对应项目中的 service和dao |
| View       | 视图：渲染数据，生成页面。对应项目中的Jsp                    |
| Controller | 控制器：直接对接请求，控制MVC流程，调度模型，选择视图。对应项目中的Servlet |

##### 1.2.2 好处

> - MVC是现下软件开发中的最流行的代码结构形态;
> - 人们根据负责的不同逻辑，将项目中的代码分成 M V C 3个层次;
> - 层次内部职责单一，层次之间耦合度低;
> - 符合低耦合 高内聚的设计理念。也实际有利于项目的长期维护。

## 二、入门案例

### 2.1 流程

#### 2.1.1 创建Maven项目

#### 2.1.2 导入依赖

```
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.1.6.RELEASE</version>
</dependency>
```

#### 2.1.3 创建web项目结构

* 创建webapp
* 创建WEB-INF
* 创建web.xml
* index.jsp

#### 2.1.4 创建Spring配置文件

* applicationContext

* springContext

* springmvcContext.xml

#### 2.1.5 创建控制器

#### 2.1.6 部署运行

### 2.2 入门案例

#### 依赖

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.shine</groupId>
    <artifactId>Day61</artifactId>
    <version>1.0-SNAPSHOT</version>

    <packaging>war</packaging>

    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.1.6.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.4</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.0</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>

        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.7.7</version>
        </dependency>

    </dependencies>
</project>
```



#### springmvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd
       http://www.springframework.org/schema/mvc
       https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--  扫描路径  -->
    <context:component-scan base-package="com.shine.controller"></context:component-scan>

    <!--  mvc驱动  -->
    <mvc:annotation-driven></mvc:annotation-driven>

    <!-- 视图解析器
     作用：1.捕获后端控制器的返回值="index"
          2.解析： 在返回值的前后 拼接 ==> "/index.jsp"			-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!-- 前缀 -->
        <property name="prefix" value="/"></property>
        <!-- 后缀 -->
        <property name="suffix" value=".jsp"></property>
    </bean>

</beans>
```

#### web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <servlet>
        <servlet-name>mvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!-- 局部参数：声明配置文件位置，Spring的配置文件 -->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc01.xml</param-value>
        </init-param>
        <!-- Servlet启动时刻：可选  懒加载，饿汉式 -->
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>mvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

</web-app>
```

#### 控制器

```java
package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/sayHi01")
    public String sayHi01() {
        System.out.println("我是SpringMVC的第一个案例，Hi。。。");
        // 跳转的位置
        return "/index.jsp";
    }

    @RequestMapping("/sayHi02")
    public String sayHi02() {
        System.out.println("我是SpringMVC的第2个案例，Hi。。。");
        // 跳转的位置
        return "index";
    }

}
```

### 2.3 入门注意

* 控制器注解
  * 相当于servlet的value

* 控制器中的方法
  * 方法也需要注解
  * servlet中的方法名字
  * 注解名字不一定和方法名字相同

## 三、收参

### 3.1 简单参数

#### 控制器

```java
package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/param")
public class ParamController {

    /**
     * 参数名称和方法的形参名字相同
     *  参数可以少于形参
     *  参数可以多于形参
     *  数据类型必须和形参保持一致
     *  数据和形参顺序无要求
     *
     * @param id
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/getParam01")  // http://localhost:8080/Day61/param/getParam01?id=10010&username=zhang&password=lisi
    public String getParam01(Integer id,String username,String password){
        System.out.println("id==>" + id + ",username==>" + username + ",password==>" + password);
        return "index";
    }

}
```

#### 客户端

```xml
<%--
  Created by IntelliJ IDEA.
  User: Dushine2008
  Date: 2021/7/12
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>param01</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/param/getParam01" method="post">
            id:<input type="text" name="id">
            <br>

            昵称:<input type="text" name="username">
            <br>

            密码:<input type="password" name="password">
            <br>

            <input type="submit" value="提交">
        </form>
    </body>
</html>
```

#### 可以接收日期对象

* 格式
  * 2020/12/11
  * 2020/12/12 12:35:25

### 3.2 对象接收参数

* 如果传来多条数据，可以直接使用对象接收这些参数
* 名词映射：
  * 对象的属性和传递参数的名字一样就能自动赋值

* 对象和普通参数可以组合使用，互相不影响

```java
    /**
     * 使用对象接收参数
     *  对象的属性会自定赋值传递的参数：类似ORM映射
     *      只会获取参数和属性一样的数据，多了或者少了没有影响
     *  比较常用
     * @param user
     * @return
     */
    @RequestMapping("/getParam03")
    public String getParam03(User user){
        System.out.println("user==>" + user);
        return "index";
    }

    /**
     * 对象和普通参数一起使用
     *  各自赋值，没有影响
     * @param user
     * @param username
     * @return
     */
    @RequestMapping("/getParam04")
    public String getParam04(User user,String username){
        System.out.println("user==>" + user);
        System.out.println("username===>" + username);
        return "index";
    }
```

### 3.3 数组收参

* springmvc提供接收数组的功能
  * 对象中的参数是数组也能直接接收

​	

```java
    /**
     * 接收数组
     * @param hobby
     * @return
     */
    @RequestMapping("/getParam05")
    public String getParam05(String[] hobby){
        if (hobby != null){
            for (String s : hobby) {
                System.out.println(s);
            }
        }
        return "index";
    }

    /**
     * 接收包含数组的对象
     * @param hobby
     * @return
     */
    @RequestMapping("/getParam06")
    public String getParam06(User2 user2){
        System.out.println(user2);
        return "index";
    }
```

### 3.4 集合收参

* 接收多个对象放入集合
* 通过索引和属性调用组合的方式收参

#### 客户端代码

```xml
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/param/getParam07">
            id:<input type="text" name="users[0].id">
            <br>
            昵称:<input type="text" name="users[0].username">
            <br>
            密码:<input type="password" name="users[0].password">
            <br>
            生日:<input type="text" name="users[0].registerTime">
            <br>

            <hr>

            id:<input type="text" name="users[1].id">
            <br>
            昵称:<input type="text" name="users[1].username">
            <br>
            密码:<input type="password" name="users[1].password">
            <br>
            生日:<input type="text" name="users[1].registerTime">
            <br>
            <input type="submit" value="提交">
        </form>
    </body>
</html>
```

#### 服务端代码

```java
    /**
     * 接收包含集合的对象
     * @param users
     * http://cccxxx?users[0].id=10011&users[0].username=zhang&users[0].password=sisi&users[0].registerTime=2018/12/15
     * @return
     */
    @RequestMapping("/getParam07")
//获得集合参数时，要将集合参数包装到一个POJO中才可以。User3中包含List<User> users属性。
    public String getParam07(User3 users){        
        System.out.println(users);
        return "index";
    }
```

### 3.5 路径收参

```java
    /**
     * 路径中编写占位符获取参数的数据
     *  注意名字、类型
     * http://localhost:8080/day61/param/getParam08/{id}
     * http://localhost:8080/day61/param/getParam08/110011
     * @return
     */
    @RequestMapping("/getParam08/{uid}")
    public String getParam08(@PathVariable("uid")  Integer id){
        System.out.println("id==>" + id);
        return "index";
    }

    /**
     * 路径收参
     *  接收多个数据
     * @param id
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/getParam09/{id}/{username}/{password}")
    public String getParam09(@PathVariable("id")  Integer id,@PathVariable("username") String username,@PathVariable("password") String password){
        System.out.println("id==>" + id + ",username===>" + username + ",password===>" + password);
        return "index";
    }
```

* 请求连接

```
http://localhost:8080/Day61/param/getParam09/110011/zhangfei/lisisi
```

### 3.6 乱码

* 当服务端和客户端字符编码格式不一样的时候会产生乱码的情况

```java
    /**
     * 乱码问题解决
     *  前后端字符集设置一致
     *  Tomcat服务器设置UTF-8===》能解决get方式
     *  设置过滤器
     * @param username
     * @return
     */
    @RequestMapping("/getParam10")
    public String getParam10(String username){
        System.out.println("username===>" + username);
        return "index";
    }
```

#### 配置文件

```xml
    <!-- 此过滤器会进行：request.setCharactorEncoding("utf-8"); -->
    <filter>
        <filter-name>encoding</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encoding</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

## 四、跳转

### 4.1 转发

* 使用关键字forward:跳转的位置

```java
package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/jump001")
@Controller
public class JumpController01 {

    @RequestMapping("/hello")
    public String Hello(){
        System.out.println("JUMP  ==  HELLO ==》INDEX");
        return "index";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump01")
    public String jump01(){
        System.out.println("JUMP ===  jump01--->hello ");
        return "forward:hello";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump02")
    public String jump02(){
        System.out.println("JUMP ===  jump02--->hello ");
        return "forward:/jump/hello";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump03")
    public String jump03(){
        System.out.println("JUMP ===  jump03--->hello ");
        return "forword:hello";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump04")
    public String jump04(){
        System.out.println("JUMP ===  jump04--->hello ");
        return "forward:/index.jsp";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump05")
    public String jump05(){
        System.out.println("JUMP ===  jump05--->hello ");
        return "forward:/hello/sayHi02";
    }
}
```

### 4.2 重定向

```java
package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/jump002")
@Controller
public class JumpController02 {

    @RequestMapping("/hello")
    public String Hello(){
        System.out.println("JUMP  ==  HELLO ==》INDEX");
        return "index";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump01")
    public String jump01(){
        System.out.println("JUMP ===  jump01--->hello ");
        return "redirect:hello";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump02")
    public String jump02(){
        System.out.println("JUMP ===  jump02--->hello ");
        return "redirect:/jump002/hello";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump04")
    public String jump04(){
        System.out.println("JUMP ===  jump04--->hello ");
        return "redirect:/index.jsp";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump05")
    public String jump05(){
        System.out.println("JUMP ===  jump05--->hello ");
        return "redirect:/hello/sayHi02";
    }
}
```

### 4.3 转发 OR 重定向

* 转发的操作地址栏不会发生变化
  * 刷新的时候会把整个请求链上的操作全部执行一次
  * 适合查询，每次都能拿到最新的结果
  * 不适合增删改
* 重定向地址会发生改变
  * 刷新的操作和原本的操作没有任何关系
  * 适合增删查

```java
package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/jump003")
@Controller
public class JumpController03 {

    @RequestMapping("/hello")
    public String Hello(){
        System.out.println("JUMP003  ==  HELLO ==》INDEX");
        return "index";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump01")
    public String jump01(){
        System.out.println("JUMP003 ===  转发--删除数据 --->hello ");
        return "forward:hello";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump02")
    public String jump02(){
        System.out.println("JUMP003 ===  重定向--删除数据 --->hello ");
        return "redirect:hello";
    }
}
```

