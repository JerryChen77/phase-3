# Day65笔记

## 一、SSM整合

### 1.1 步骤

####  1、创建Maven项目
####  2、依赖
​	 jsp、servlet
​	mysql-connector、jdbc、mybatis、spring-webmvc、mybatis-spring、aop、aspects、druid、tx
​	junit、springtest
​	slflog4j、lombok

#### 3、配置文件
​	Spring配置文件
​	Springmvc配置文件
​	web.xml
​	jdbc
​	log4j

#### 4、建库、表、实体类

#### 5、DAO-Mapper

#### 6、DAO.xml

#### 7、测试

#### 8、Service和实现类

#### 9、测试

#### 10、控制器

#### 11、客户端

### 1.2 依赖

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.shine</groupId>
    <artifactId>SSM</artifactId>
    <version>1.0-SNAPSHOT</version>

    <packaging>war</packaging>

    <dependencies>
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
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.20</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.54</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>5.1.6.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.1.6.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>5.3.8</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>5.1.6.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.1.6.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.2</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>5.1.6.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.10</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.2</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>

        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.7.7</version>
        </dependency>

        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
            <version>5.2.0</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
    </dependencies>
</project>
```

### 1.3 配置文件--applicationContext.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx.xsd">

    <!--  配置扫描路径  -->
    <context:component-scan base-package="com.shine"></context:component-scan>

    <!--  导入jdbc配置文件  -->
    <context:property-placeholder location="classpath*:jdbc.properties"></context:property-placeholder>

    <!--  注入数据源  -->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClassName" value="${jdbc.driver}"></property>
        <property name="url" value="${jdbc.url}"></property>
        <property name="username" value="${jdbc.username}"></property>
        <property name="password" value="${jdbc.password}"></property>
    </bean>

    <!--  注入sqlSessionFactory  -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"></property>

        <property name="plugins">
            <array>
                <bean class="com.github.pagehelper.PageInterceptor">
                    <!-- 这里的几个配置主要演示如何使用，如果不理解，一定要去掉下面的配置 -->
                    <property name="properties">
                        <value>
                            helperDialect=mysql
                            reasonable=true
                        </value>
                    </property>
                </bean>
            </array>
        </property>
    </bean>

    <!-- 1. 引入一个事务管理器，其中依赖DataSource,借以获得连接，进而控制事务逻辑 -->
    <bean id="tx" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <!--  开启注解事务  -->
    <tx:annotation-driven transaction-manager="tx"></tx:annotation-driven>

    <!--  扫描Mapper  -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.shine.dao"></property>
    </bean>
</beans>
```

### 1.4 Hero

```
package com.shine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Hero {
    private Integer id;
    private String name;
    private Integer forceValue;
    private String addr;
    private String info;
}
```

### 1.5 HeroDao

```
package com.shine.dao;

import com.shine.entity.Hero;

import java.util.List;

/**
 * HeroDao
 */
public interface HeroDao {
    Integer insertHero(Hero hero);
    Integer deleteHero(Integer id);
    Integer updateHero(Hero hero);
    Hero selectHeroById(Integer id);
    List<Hero> selectHeroes();
}
```

### 1.6 HeroDao.xml

* 如果路径和HeroDao没有完全对应，可能无法使用
* 可以使用build标签在prom文件中添加扫描

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.shine.dao.HeroDao">

    <!--  Integer insertHero(Hero hero);  -->
    <insert id="insertHero" parameterType="com.shine.entity.Hero">
        insert into hero(name,force_value,addr,info) values (#{name},#{forceValue},#{addr},#{info})
    </insert>

    <!--  Integer deleteHero(Integer id);  -->
    <delete id="deleteHero">
        delete from hero where id=#{id}
    </delete>

    <!--  Integer updateHero(Hero hero);  -->
    <update id="updateHero" parameterType="com.shine.entity.Hero">
        update hero set name=#{name},force_value=#{forceValue},addr=#{addr},info=#{info}
    </update>

    <!--  Hero selectHeroById(Integer id);  -->
    <select id="selectHeroById" resultType="com.shine.entity.Hero">
        select id,name,force_value forceValue,addr,info from hero where id=#{id}
    </select>

    <!--  List<Hero> selectHeroes();  -->
    <select id="selectHeroes" resultType="com.shine.entity.Hero">
        select id,name,force_value forceValue,addr,info from hero
    </select>

</mapper>
```

### 1.7 测试

```
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class HeroDaoTest {

    @Autowired
    private HeroDao heroDao;

    @Test
    public void getHero01(){
        Hero hero = heroDao.selectHeroById(2);
        System.out.println(hero);
    }
}
```

### 1.8 HeroService

```
/**
 * HeroService
 */
public interface HeroService {
    Integer saveHero(Hero hero);
    Integer removeHero(Integer id);
    Integer modifyHero(Hero hero);
    Hero queryHeroById(Integer id);
    List<Hero> queryHeroes();
}
```

```
package com.shine.service.impl;

import com.shine.dao.HeroDao;
import com.shine.entity.Hero;
import com.shine.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * HeroService实现类
 */
@Service
public class HeroServiceImpl implements HeroService {

    @Autowired
    private HeroDao heroDao;

    @Override
    public Integer saveHero(Hero hero) {
        Integer row = heroDao.insertHero(hero);
        return row;
    }

    @Override
    public Integer removeHero(Integer id) {
        Integer row = heroDao.deleteHero(id);
        return row;
    }

    @Override
    public Integer modifyHero(Hero hero) {
        Integer row = heroDao.updateHero(hero);
        return row;
    }

    @Override
    public Hero queryHeroById(Integer id) {
        Hero hero = heroDao.selectHeroById(id);
        return hero;
    }

    @Override
    public List<Hero> queryHeroes() {
        List<Hero> heroes = heroDao.selectHeroes();
        return heroes;
    }
}
```

### 1.9 测试

```
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class HeroDaoTest {

    @Autowired
    private HeroService heroService;

    @Test
    public void getHero02(){
        Hero hero = heroService.queryHeroById(2);
        System.out.println(hero);
    }

}
```

### 1.10 控制器

#### 配置文件web.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
         http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <!--  拦截器，本质上是Servlet  -->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>

        <!--      初始化的时候加载spring工厂 ，加载工厂中的内容     -->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc.xml</param-value>
        </init-param>

        <!--  默认懒加载，可以设置为启动容器马上加载  -->
        <load-on-startup>1</load-on-startup>
    </servlet>

    <!--  路径映射，设置/表示拦截所有请求  -->
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
        <!--        <url-pattern>*.action</url-pattern>-->
    </servlet-mapping>

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

    <!--  启动Spring工厂  -->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </context-param>
    
</web-app>
```

#### HeroController

```java
package com.shine.controller;


import com.shine.entity.Hero;
import com.shine.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/hero")
@Controller
public class HeroController {

    @Autowired
    private HeroService heroService;

    /**
     * 查询指定id
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/heroes/{id}")
    @ResponseBody
    public Hero getHeroById(@PathVariable Integer id, Model model){
        Hero hero = heroService.queryHeroById(id);
        return hero;
    }

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/heroes")
    @ResponseBody
    public ModelAndView getHeroes(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/showHeroes.jsp");
        List<Hero> heroes = heroService.queryHeroes();

        modelAndView.addObject("heroes",heroes);

        return modelAndView;
    }

}
```

