# Day 59笔记

## 一、JDK动态代理

### 1.1 概述

* 动态代理
  * 代理的具体实现在创建对象的时候根据需要动态加入
  * 对比静态代理，静态代理是直接创建代理类，在类中定义好具体的实现
  * 不同的代理场景
    * 动态代理，程序运行中，动态的生成代理对象，由代理对象去增强被代理对象功能。
    * 静态代理，创建代理类，在类中定义代理内容，再创建对象
* jdk动态代理
  * 代理和被代理对象都实现了共同的接口，相当于兄弟关系

### 1.2 租房案例

#### 接口

```java
public interface ZuFang {
    Integer chuZu(Integer money);
}
```

#### 被代理目标

```java
public class FangDong implements ZuFang {
    @Override
    public Integer chuZu(Integer money) {
        System.out.println("房东收到房租:" + money);
        return money;
    }
}
```

#### 测试

* 代理和被代理实现共同的接口

```java
@Test
    public void testFang(){
        FangDong target = new FangDong();

        /**
         * ClassLoader loader,      代理目标的类加载器
         * Class<?>[] interfaces,   代理目标的接口S
         * InvocationHandler h
         */
        ZuFang proxy = (ZuFang) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {

                    /**
                     *
                     * @param proxy     代理对象，一般用不到
                     * @param method    代理的方法
                     * @param args      代理方法中接收到的参数们
                     * @return 代理对象
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("中介发广告");
                        System.out.println("中介带看房");
                        System.out.println("中介签合同");
                        System.out.println("中介收到房租:" + args[0]);

                        args[0] = (Integer) args[0] - 400;

                        // 代理目标方法调用之后的返回值
                        Object result = method.invoke(target, args);

                        System.out.println("中介提供售后服务...");

                        return result;
                    }
                }
        );

        Integer money = proxy.chuZu(2000);
        System.out.println(money);

    }
```

### 1.3 转账案例

#### AccountService

```
public interface AccountService {
    Integer transfer(Integer money,String fromAccount,String toAccount);
}
```

#### 被代理目标

```java
public class AccountServiceImpl implements AccountService {
    @Override
    public Integer transfer(Integer money, String fromAccount, String toAccount) {
        if (money < 0){
            throw new RuntimeException("转账金额不能为负数...");
        }
        return 1;
    }
}
```

#### 测试

```java
package com.shine;

import com.shine.service.AccountService;
import com.shine.service.impl.AccountServiceImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestAccount {

    @Test
    public void testAccount(){

        AccountServiceImpl target = new AccountServiceImpl();
        /**
         * ClassLoader loader,      代理目标的类加载器
         * Class<?>[] interfaces,   代理目标的接口S
         * InvocationHandler h
         */
        AccountService proxy = (AccountService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * @param proxy     代理对象，一般用不到
                     * @param method    代理的方法
                     * @param args      代理方法中接收到的参数们
                     * @return 代理对象
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("交易开始....");
                        Object result = null;
                        try {
                            result = method.invoke(target, args);
                            System.out.println("转账成功");
                            System.out.println("打印转账凭证");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("转账失败,请重新操作");
                        } finally {
                            System.out.println("交易结束,收尾");
                        }
                        return result;
                    }
                }
        );
        proxy.transfer(-2000,"" ,"");

    }


    public Object getProxy(Class clazz){
        Object result = Proxy.newProxyInstance(
                clazz.getClassLoader(),
                clazz.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * @param proxy     代理对象，一般用不到
                     * @param method    代理的方法
                     * @param args      代理方法中接收到的参数们
                     * @return 代理对象
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("交易开始....");
                        Object result = null;
                        try {
                            result = method.invoke(clazz.newInstance(), args);
                            System.out.println("转账成功");
                            System.out.println("打印转账凭证");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("转账失败,请重新操作");
                        } finally {
                            System.out.println("交易结束,收尾");
                        }
                        return result;
                    }
                }
        );
        return result;
    }

    @Test
    public void tt() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(""));
        SqlSession sqlSession = sqlSessionFactory.openSession();

//        sqlSession.getMapper()

    }

}
```

## 二、Cglib动态代理

### 2.1 概述

* 基于继承
* 代理和被代理对象产生继承关系
* 需要导入cglib依赖

```xml
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>3.3.0</version>
</dependency>
```

```
Enhancer cnh = new Enhancer();//1.创建字节码曾强对象
enh.setSuperclass(os.getClass());//2.设置父类（等价于实现原始类接口）
enh.setCallback(new InvocationHandler(){//3.设置回调函数（额外功能代码）
```

### 2.2 Cglib实现租房代理

#### FangDong

```java
public class FangDong {
    public Integer chuZu(Integer money) {
        System.out.println("房东收到房租:" + money);
        return money;
    }
}
```

#### 测试

```java
package com.shine;

import com.shine.proxyjdk.FangDong;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TestCglibProxy {

    @Test
    public void testFang() {
        FangDong target = new FangDong();

        FangDong proxy = (FangDong) Enhancer.create(
                target.getClass(),
                new MethodInterceptor() {
                    /**
                     * @param proxy     代理对象，一般不用
                     * @param method    被代理方法
                     * @param args      方法中传入的参数
                     * @param methodProxy   方法代理
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                        System.out.println("中介发广告");
                        System.out.println("中介带看房");
                        System.out.println("中介签合同");
                        System.out.println("中介收到房租:" + args[0]);

                        args[0] = (Integer) args[0] - 400;

                        // 代理目标方法调用之后的返回值
                        Object result = method.invoke(target, args);

                        System.out.println("中介提供售后服务...");

                        return result;
                    }
                }
        );
        proxy.chuZu(2000);
    }
}
```

## 三、AOP

### 3.1 概述

* AOP（Aspect Oriented Programming），即面向切面编程，利用一种称为"横切"的技术，剖开封装的对象内部，并将那些影响了多个类的公共行为封装到一个可重用模块，并将其命名为"Aspect"，即切面。
* 所谓"切面"，简单说就是那些与业务无关，却为业务模块所共同调用的逻辑或责任封装起来，便于减少系统的重复代码，降低模块之间的耦合度，并有利于未来的可操作性和可维护性。
* Spring的AOP就是通过动态代理的方式添加功能实现代理

### 3.2 AOP术语

- 连接点(Joinpoint)：连接点是程序类中客观存在的方法，可被Spring拦截并切入内容。
  - 客观存在的方法，可以被增加功能
  - 比如业务层中有增删改查的4个方法，都是连接点
- 切入点(Pointcut)：被Spring切入连接点。
  - 实际被增强的方法
  - 比如业务层中有增删改查的4个方法，都是连接点
  - 增删被增加了功能，增删就是切入点
- 通知、增强(Advice)：可以为切入点添加额外功能，分为：前置通知、后置通知、异常通知、最终通知、环绕通知等。
  - 实际增加的功能
- 目标对象(Target)：代理的目标对象
  - 要被增强的对象
- 引介(Introduction)：一种特殊的增强，可在运行期为类动态添加Field和Method。
- 织入(Weaving)：把通知应用到具体的类，进而创建新的代理类的过程。
  - 把增强应用到目标
- 代理(Proxy)：被AOP织入通知后，产生的结果类。
  - 产生的结果
- 切面(Aspect)：由切点和通知组成，将横切逻辑织入切面所指定的连接点中。
  - 增强 + 切点 == 切面

### 3.3 添加依赖

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aspects</artifactId>
    <version>5.1.6.RELEASE</version>
</dependency>
```

### 3.4 体验AOP

#### User

```java
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private Date registerTime;
}
```

#### UserService

```java
package com.shine.service;

import com.shine.entity.User;

public interface UserService {
    /**
     * 增
     *  add
     *  insert
     *  save
     *
     * 删
     * delete
     * remove
     *
     * 改
     *  update
     *  modify
     *
     * 查
     * select
     * find
     */

    Integer saveUser(User user);

    Integer deleteUser(Integer id);

    void updateUser(User user);

    Integer selectUser(Integer id);

}
```

#### 业务实现

```java
package com.shine.service.impl;

import com.shine.entity.User;
import com.shine.service.UserService;

/**
 * 业务层实现
 */
public class UserServiceImpl implements UserService {
    @Override
    public Integer saveUser(User user) {
        System.out.println("添加用户成功===========");
        return 11111;
    }

    @Override
    public Integer deleteUser(Integer id) {
        System.out.println("删除用户成功-----------");
        return 22222;
    }

    @Override
    public void updateUser(User user) {
        System.out.println("修改用户成功·········");
    }

    @Override
    public Integer selectUser(Integer id) {
        System.out.println("查询用户成功+++++++++++++++++");
        return 33333;
    }
}
```

#### applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--  1、把实体对象注入  -->
    <bean id="userService" class="com.shine.service.impl.UserServiceImpl"></bean>
    <bean id="transactionAdvice" class="com.shine.advice.TransactionAdvice"></bean>


    <!--  2、配置事务通知  -->
    <aop:config>
        <!--    选取被添加功能的方法    -->
        <aop:pointcut id="pt" expression="execution(public void com.shine.service.impl.UserServiceImpl.updateUser(com.shine.entity.User))"/>

        <!--    配置切面，标明引用内容的id    -->
        <aop:aspect ref="transactionAdvice">
            <!--
                  aop:before
                        方法执行前执行
                  aop:after-returning
                        方法返回结果的时候
                  aop:throwing
                        方法出现异常的时候
                  aop:after
                        方法彻底结束的时候
                  method
                        增强的内容
                  pointcut-ref
                        切入点
            -->
            <aop:before method="begin" pointcut-ref="pt"></aop:before>
            <aop:after-returning method="commit" pointcut-ref="pt"></aop:after-returning>
            <aop:after-throwing method="rollback" pointcut-ref="pt"></aop:after-throwing>
            <aop:after method="release" pointcut-ref="pt"></aop:after>
        </aop:aspect>

    </aop:config>

    <!--  使得aop设置生效  -->
    <aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy>

</beans>
```

#### 测试

```java
    @Test
    public void testBegin(){
        ClassPathXmlApplicationContext ioc = 
        	new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService = ioc.getBean(UserServiceImpl.class);

        userService.updateUser(new User());

    }
```

### 3.5 通配符

```
    <!--  2、配置事务通知  -->
    <aop:config>
        <!--    
                pointcut
                    配置切点
                    选取被添加功能的方法  
                expression
                    选取方法的表达式
                    可以使用通配符
                    *
                        某一位置的任意内容
                    ..
                       在参数列表中使用
                       忽略类型和数量
                
         -->
        <aop:pointcut id="pt" expression="execution(public * com.shine.service.impl.UserServiceImpl.*(..))"/>
```

### 3.6 环绕通知

* 在业务整个周期都能参与的通知

### 3.7 获取被代理目标的参数的数据

* 需要在代理方法中加入参数
  * JoinPoint
    * 获取参数 			 		joinPoint.getArgs();
    * 获取方法名称               joinPoint.getSignature().getName();
  * ProceedingJoinPoint
    * 定义了获取结果的方法  joinPoint.proceed();

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--  1、把实体对象注入  -->
    <bean id="userService" class="com.shine.service.impl.UserServiceImpl"></bean>
    <bean id="transactionAdvice" class="com.shine.advice.TransactionAdvice"></bean>


    <!--  2、配置事务通知  -->
    <aop:config>
        <!--
                pointcut
                    配置切点
                    选取被添加功能的方法
                expression
                    选取方法的表达式
                    可以使用通配符
                    *
                        某一位置的任意内容
                    ..
                       在参数列表中使用
                       忽略类型和数量

         -->
        <aop:pointcut id="pt" expression="execution(public * com.shine.service.impl.UserServiceImpl.*(..))"/>

        <!--    配置切面，标明引用内容的id    -->
        <aop:aspect ref="transactionAdvice">
            <!--
                  aop:before
                        方法执行前执行
                  aop:after-returning
                        方法返回结果的时候
                  aop:throwing
                        方法出现异常的时候
                  aop:after
                        方法彻底结束的时候
                  method
                        增强的内容
                  pointcut-ref
                        切入点

            <aop:before method="begin" pointcut-ref="pt"></aop:before>
            <aop:after-returning method="commit" pointcut-ref="pt"></aop:after-returning>
            <aop:after-throwing method="rollback" pointcut-ref="pt"></aop:after-throwing>
            <aop:after method="release" pointcut-ref="pt"></aop:after>
            -->
            <aop:around method="around" pointcut-ref="pt"></aop:around>

        </aop:aspect>

    </aop:config>

    <!--  使得aop设置生效  -->
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>

</beans>
```

### 3.8 SpringAOP创建对象的方式

* 使用的动态代理
* 默认使用JDK动态代理
  * 可以通过设置选择Cglib动态代理的方式

### 3.9 获取业务方法异常信息

* 需要添加属性throws属性，属性可以在增强中获取到

### 3.10 添加多个增强

#### 创建增强

```java
package com.shine.advice;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

public class LoggerAdvice {

    Logger logger = Logger.getLogger(LoggerAdvice.class);

    public void beforeLog(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println();
        logger.info("方法开始执行,,,," + (args!=null ? Arrays.asList(args):null));
    }

}
```

#### 配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--  1、把实体对象注入  -->
    <bean id="userService" class="com.shine.service.impl.UserServiceImpl"></bean>
    <bean id="transactionAdvice" class="com.shine.advice.TransactionAdvice"></bean>
    <bean id="loggerAdvice" class="com.shine.advice.LoggerAdvice"></bean>

        <!--   配置日志切面     -->
        <aop:aspect ref="loggerAdvice">
            <aop:after-returning method="beforeLog" pointcut-ref="pt"></aop:after-returning>
        </aop:aspect>

    </aop:config>

    <!--  使得aop设置生效  -->
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>

</beans>

```

## 四、Spring整合MyBatis【掌握】

