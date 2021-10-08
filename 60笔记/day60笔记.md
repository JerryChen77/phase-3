#                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             Day60笔记

## 一、Spring中的代理

### 1.1 Cglib和JDK

* bean代理对象创建的过程中，怎么选择代理方式？ 发                 v 

* DefaultAopProxyFactory类提供了判定使用哪种代理方式创建对象

```
@Override
	public AopProxy createAopProxy(AdvisedSuppor                                                                                                                                                                                                                                                                                                                  t config) throws AopConfigException {
		if (config.isOptimize() || config.isProxyTargetClass() || hasNoUserSuppliedProxyInterfaces(config)) {
			Class<?> targetClass = config.getTargetClass();
			if (targetClass == null) {
				throw new AopConfigException("TargetSource cannot determine target class: " +
						"Either an interface or a target is required for proxy creation.");
			}
			if (targetClass.isInterface() || Proxy.isProxyClass(targetClass)) {
				return new JdkDynamicAopProxy(config);
			}
			return new ObjenesisCglibAopProxy(config);
		}
		else {
			return new JdkDynamicAopProxy(config);
		}
	}
```

### 1.2 后处理器BeanPostProcessor

* 在bean init之前和之后对象bean进行处理、包装

```
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Nullable
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后处理器的before===》bean==>" + bean + ",beanName===>" + beanName);
        return bean;
    }

    @Nullable
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后处理器的after===》bean==>" + bean + ",beanName===>" + beanName);
        return bean;
    }
}
```

* 注入之后会自动工作

## 二、Spring整合MyBatis

### 2.1 整合

* 资料详情见代码

### 2.3 分页插件

```
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
```

## 三、Spring继承Junit

### 3.1 添加依赖

```
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>5.1.6.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.1.6.RELEASE</version>
</dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
</dependency>
```

### 3.2 测试

```
package com.shine;

import com.shine.entity.User;
import com.shine.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-ms.xml")
public class TestUser01 {

    @Autowired
    private UserService userService;

    @Test
    public void getUser01() {
        List<User> users = userService.queryUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
```

## 四、事务

### 4.1 概述

* 事务
* 事务特性
* 事务隔离级别
* 事务不同隔离级别可能出现的问题

### 4.2 事务管理器

* DataSourceTransactionManager
* 需要添加依赖

```
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-tx</artifactId>
    <version>5.1.6.RELEASE</version>
</dependency>
```

* 需要在配置文件的头部添加信息

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tx="http://www.springframework.org/schema/tx"
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
```

* 事务管理器需要使用DataSource，此处的DataSource必须和sqlsessionfactory的是同一个

```
<!-- 1. 引入一个事务管理器，其中依赖DataSource,借以获得连接，进而控制事务逻辑 -->
<bean id="tx" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	<property name="dataSource" ref="dataSource"></property>
</bean>
```

### 4.3 添加事务---xml版本

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tx="http://www.springframework.org/schema/tx"
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

    <!-- 配置bean -->
    <bean id="user" class="com.shine.entity.User" init-method="init" destroy-method="destroy">
        <property name="id" value="10010"></property>
    </bean>
    <bean class="com.shine.processor.MyBeanPostProcessor"></bean>

    <!--  导入jdbc配置文件  -->
    <context:property-placeholder location="classpath*:jdbc.properties"></context:property-placeholder>
    <context:component-scan base-package="com.shine.service"></context:component-scan>

    <!--  注入数据源  -->
    <!--    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">-->
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


    <tx:advice id="txAdvice" transaction-manager="tx">
        <!--    配置需要设置事务的方法    -->
        <tx:attributes>
            <tx:method name="removeUser"/>
        </tx:attributes>
    </tx:advice>

    <!--  切面  -->
    <aop:config>
        <!--    切点    -->
        <aop:pointcut id="pt" expression="execution(public * com.shine.service.*.*.* (..))"/>
        <!--    增强    -->
        <aop:advisor advice-ref="txAdvice" pointcut-ref="pt"></aop:advisor>
    </aop:config>

    <!--  扫描Mapper  -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.shine.dao"></property>
    </bean>

    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>

</beans>
```

### 4.4 注解事务版本

* 把实体类、DAO、Service都改成注解注入
* 把xml中原来的
  * tx:adcive
  * aop:config
* 删除

* 在需要开启事务的类、方法中添加事务注解
  * 可以标注整个类
  * 可以标注单个的方法

#### 业务类

```
package com.shine.service.impl;

import com.shine.dao.UserDao;
import com.shine.entity.User;
import com.shine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * *User
     * @param user
     * @return
     */
    @Override
    public Integer addUser(User user) {
        Integer ret = userDao.insertUser(user);
        return ret;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer removeUser(Integer id) {
        Integer ret = userDao.deleteUser(id);
//        System.out.println(10/0);
        return ret;
    }

    @Override
    public Integer modifyUser(User user) {
        Integer ret = userDao.updateUser(user);
        return ret;
    }

    /**
     * query*
     * @param id
     * @return
     */
    @Override
    public User queryUserById(Integer id) {
        User user = userDao.selectUser(id);
        return user;
    }

    @Override
    public List<User> queryUsers() {
        List<User> users = userDao.selectUsers();
        return users;
    }
}
```

#### 配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tx="http://www.springframework.org/schema/tx"
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
    <context:component-scan base-package="com.shine.entity,com.shine.service"></context:component-scan>

    <!--  导入jdbc配置文件  -->
    <context:property-placeholder location="classpath*:jdbc.properties"></context:property-placeholder>

    <!--  注入数据源  -->
    <!--    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">-->
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
<!--  是的AOP生效  -->
<!--    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>-->

</beans>
```

### 4.5 事务设置的通配符

* 指定方法的时候可以同时设置多个
* 也可以使用通配符选取多个方法

```xml
    <tx:advice id="txAdvice" transaction-manager="tx">
        <!--    配置需要设置事务的方法    -->
        <tx:attributes>
            <!--      标记所有User结尾的方法      -->
            <tx:method name="*User"/>
            <!--      标记所有query开头的方法     -->
            <tx:method name="query*"/>
        </tx:attributes>
    </tx:advice>
```

### 4.6 事务属性设置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tx="http://www.springframework.org/schema/tx"
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

    <!-- 配置bean -->
    <bean id="user" class="com.shine.entity.User" init-method="init" destroy-method="destroy">
        <property name="id" value="10010"></property>
    </bean>
    <bean class="com.shine.processor.MyBeanPostProcessor"></bean>

    <!--  导入jdbc配置文件  -->
    <context:property-placeholder location="classpath*:jdbc.properties"></context:property-placeholder>
    <context:component-scan base-package="com.shine.service"></context:component-scan>

    <!--  注入数据源  -->
    <!--    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">-->
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


    <tx:advice id="txAdvice" transaction-manager="tx">
        <!--    配置需要设置事务的方法    -->
        <tx:attributes>
            <!--
                事务相关设置
                    timeout
                        设置超时时间，默认是-1，无限期等待，可以设置数据
                    rollback-for
                        设定可以回滚的异常
                    no-rollback-for
                        设定不回滚的异常
                    isolation
                        事务的隔离级别
                        mysql默认的是REPEATABLE_READ
                    propagation
                        事务的传播
                        当出现Service之间有调用的时候使用
                        确认当前的业务是否开启事务
                        默认是开启的
            -->
            <!--      标记所有User结尾的方法      -->
            <tx:method name="*User" timeout="-1" rollback-for="Exception" no-rollback-for="" isolation="DEFAULT" propagation="REQUIRED"/>
            <!--      标记所有query开头的方法     -->
            <tx:method name="query*" read-only="true" propagation="SUPPORTS"/>
        </tx:attributes>
    </tx:advice>

    <!--  切面  -->
    <aop:config>
        <!--    切点    -->
        <aop:pointcut id="pt" expression="execution(public * com.shine.service.*.*.* (..))"/>
        <!--    增强    -->
        <aop:advisor advice-ref="txAdvice" pointcut-ref="pt"></aop:advisor>
    </aop:config>

    <!--  扫描Mapper  -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.shine.dao"></property>
    </bean>

    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>

</beans>
```

