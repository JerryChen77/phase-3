# Day54笔记（重要）

## 一、JDBC

### 1.1 概述

* 我们写代码遵循MVC--三层架构
* 但是耦合性较高
* 自己需要实现的操作过多

### 1.2 持久层解决方案

* 可以使用代理模式解决
  * 告诉框架需要得到的结果
  * 告诉框架执行的sql
* 代理帮我们完成其他的操作
* 优秀的持久层框架--MyBatis

## 二、MyBatis概述

### 2.1 MyBatis简介

* MyBatis 是一款优秀的持久层框架，它支持自定义 SQL、存储过程以及高级映射。
* MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作。
* MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

 *  资料地址
    	*  https://mybatis.org/mybatis-3/zh/getting-started.html

### 2.2 安装MyBatis

#### 导入jar包

* 下载mybatis3.X版本的jar包导入使用

#### 引入依赖

* 在pom文件中添加依赖

```
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>x.x.x</version>
</dependency>
```

## 三、MyBatis初体验

### 3.1 使用流程

> 1、建表、建库
>
> 2、创建Maven项目
>
> 3、引入依赖
>
> 4、创建表对应的实体类、Dao接口--【Mapper映射器】
>
> 5、创建Mapper映射文件---【XXXmapper.xml文件】
>
> 6、创建mybatis核心文件--mybatis-config.xml--把mapper文件注册
>
> 7、测试

### 3.2 详解

#### 3.2.1 建表

```
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

#### 3.2.2 实体类

```java
package com.shine.entity;

import java.util.Date;

/**
 * 实体类
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private Date registerTime;

    public User() {
    }

    public User(Integer id, String username, String password, Date registerTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.registerTime = registerTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", registerTime=" + registerTime +
                '}';
    }
}
```

#### 3.2.3 UserDao--Mapper

```
package com.shine.dao;

import com.shine.entity.User;

/**
 * 持久层接口
 */
public interface UserDao {
    /**
     * select * from user where id=?
     * @param id
     * @return
     */
    User findById(int id);
}
```

#### 3.2.4 mapper.xml

```
<?xml version="1.0" encoding="UTF-8" ?>
<!--
    头标记
        表示本标签中能书写哪些内容
        需要遵守什么规范
        此处遵循dtd
-->
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!--
    mapper  映射器
        namespace   被映射的XxxDao的全类名
            select  执行的SQL语句的类型
                id  XxxDao中抽象方法的名字
                resultType  XXXDao中抽象方法的返回值类型，需要些全类型
                parameterType 方法的参数类型
                  select * from user where id = #{id}   XxxDao抽象方法实现时候执行的SQL语句
-->
<mapper namespace="com.shine.dao.UserDao">

    <!--  User findById(int id);  -->
    <select id="findById" resultType="com.shine.entity.User" >
        select * from user where id = #{id}
    </select>

</mapper>
```

#### 3.2.5 mybatis-config.xml

```
<?xml version="1.0" encoding="UTF-8" ?>
<!--
    mybatis的核心配置文件
    本质上时是一个configuration对象
    遵循mybatis-3-config.dtd规则
-->
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<!--
    配置的根标签
-->
<configuration>

<!--
    环境s
    default 默认使用的环境配置
        environment     具体的子环境 id是环境的唯一标识
        transactionManager  事务管理器 type是事务类型
        dataSource      连接池 type是连接池类型
        property    jdbc.properties文件中具体的属性
-->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/java2103"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>

    <!--  注册器--把XxxDao的映射文件注册  -->
    <mappers>
        <!--    注册映射文件    -->
        <mapper resource="com/shine/dao/UserDao.xml"/>
    </mappers>
</configuration>
```

#### 3.2.6 测试类

```java
import com.shine.dao.UserDao;
import com.shine.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestUser {
    @Test
    public void getUserById01() throws IOException {
        // 加载配置文件获取SQL会话工厂
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SQL会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 执行会话
        User user = sqlSession.selectOne("com.shine.dao.UserDao.findById", 1);

        // 提交事务
        System.out.println(user);
        sqlSession.commit();

    }

    @Test
    public void getUserById02() throws IOException {
        // 加载配置文件获取SQL会话工厂
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建--构造--构造器模式
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SQL会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取UserDao的代理
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.findById(2);

        // 提交事务
        System.out.println(user);
        sqlSession.commit();
        sqlSession.close();

    }
}
```

## 四、日志

### 4.1 概述

* Log4j是[Apache](https://baike.baidu.com/item/Apache/8512995)的一个开源项目，通过使用Log4j，
* 我们可以控制日志信息输送的目的地是[控制台](https://baike.baidu.com/item/控制台/2438626)、文件、[GUI](https://baike.baidu.com/item/GUI)组件，
* 甚至是套接口服务器、[NT](https://baike.baidu.com/item/NT/3443842)的事件记录器、[UNIX](https://baike.baidu.com/item/UNIX) [Syslog](https://baike.baidu.com/item/Syslog)[守护进程](https://baike.baidu.com/item/守护进程/966835)等；
* 我们也可以控制每一条日志的输出格式；
* 通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程。
* 最令人感兴趣的就是，这些可以通过一个[配置文件](https://baike.baidu.com/item/配置文件/286550)来灵活地进行配置，而不需要修改应用的代码。

### 4.2 使用方式

#### 4.2.1 导入jar包

#### 4.2.2 添加依赖

```
        <!-- https://mvnrepository.com/artifact/log4j/log4j -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
```



### 4.3 创建log4j配置文件

```
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.err
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c{1}:%L - %m%n
log4j.appender.file=org.apache.log4j.FileAppender
log4j.appender.file.File=mylog.log
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c{1}:%L - %m%n
#日志等级
# debug :
log4j.rootLogger=all level, stdout, file
```

## 五、CRUD

### 5.1 查询

#### 抽象方法

```
    // 查找
    Hero selectHero(int id);

    List<Hero> selectHeroByName(String username);

    List<Hero> selectAllHero();
```

#### mapper映射

```xml
    <!--  Hero selectHero(int id);  -->
    <select id="selectHero" resultType="com.shine.entity.Hero">
        select * from hero where id = #{id}
    </select>

    <!--  List<Hero> selectAllHero();  -->
    <select id="selectAllHero" resultType="com.shine.entity.Hero">
        select * from hero
    </select>

    <!--  List<Hero> selectHeroByName(String username);  -->
    <select id="selectHeroByName" resultType="com.shine.entity.Hero">
        select * from hero where username like concat('%',#{username},'%')
    </select>
```

#### 测试

```java
/**
     * 查询单个
     * @throws IOException
     */
    @Test
    public void getHero() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取Mapper
        HeroDao heroDao = sqlSession.getMapper(HeroDao.class);

        // 执行sql
        Hero hero = heroDao.selectHero(2);

        System.out.println(hero);

        // 提交和关闭
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 查询所有
     * @throws IOException
     */
    @Test
    public void getAllHero01() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取Mapper
        HeroDao heroDao = sqlSession.getMapper(HeroDao.class);

        // 执行sql
        List<Hero> heroes = heroDao.selectAllHero();

        for (Hero hero : heroes) {
            System.out.println(hero);
        }

        // 提交和关闭
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 模糊查询
     * @throws IOException
     */
    @Test
    public void selectHeroByName() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取Mapper
        HeroDao heroDao = sqlSession.getMapper(HeroDao.class);

        // 执行sql
        // List<Hero> heroes = heroDao.selectHeroByName("%wu%");
        List<Hero> heroes = heroDao.selectHeroByName("wu");


        for (Hero hero : heroes) {
            System.out.println(hero);
        }
        // 提交和关闭
        sqlSession.commit();
        sqlSession.close();
    }
```

### 5.2 增加

* 使用对象当做参数传入

#### 抽象方法

```
int insertHero(Hero hero);
```

#### mapper文件

```
    <!--  int insertHero(Hero hero);  -->
    <insert id="insertHero" parameterType="com.shine.entity.Hero">
        insert into hero(username,gender,age,addr,info) values(#{username},#{gender},#{age},#{addr},#{info})
    </insert>
```

#### 测试

```
@Test
    public void insertHero() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取Mapper
        HeroDao heroDao = sqlSession.getMapper(HeroDao.class);

        // 执行SQL
        Hero hero =  new Hero();
        hero.setUsername("luzhishen");
        hero.setGender("male");
        hero.setAge(33);
        hero.setAddr("天龙寺");
        hero.setInfo("倒拔垂杨柳的好汉");

        int i = heroDao.insertHero(hero);
        System.out.println(i);

        sqlSession.commit();
        sqlSession.close();
    }
```

### 5.3 删除

#### 抽象方法

```
int deleteHero(int id);
```

#### mapper文件

```
    <!--  int deleteHero(int id);  -->
    <delete id="deleteHero" parameterType="int" >
        delete from hero where id=#{id}
    </delete>
```

#### 测试

```java
 @Test
    public void deleteHero() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取Mapper
        HeroDao heroDao = sqlSession.getMapper(HeroDao.class);

        int i = heroDao.deleteHero(10);
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();

    }
```

### 5.4 修改

#### 抽象方法

```
int updateHero(Hero hero);
```

#### mapper文件

```
    <!--  int updateHero(Hero hero);  -->
    <update id="updateHero" parameterType="com.shine.entity.Hero" >
        update hero set username=#{username},gender=#{gender},age=#{age} where id=#{id}
    </update>
```

#### 测试

```java
@Test
    public void updateHero() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取Mapper
        HeroDao heroDao = sqlSession.getMapper(HeroDao.class);

        Hero hero = new Hero();
        hero.setId(1);
        hero.setUsername("及时雨");
        hero.setAge(49);
        hero.setGender("male");

        int i = heroDao.updateHero(hero);
        System.out.println(i);

        sqlSession.commit();
        sqlSession.close();
    }
```

## 六、主键回填

### 6.1 自增主键回填01

* SQL语句实现

#### 抽象方法

```
int getHeroGenId01(Hero hero);
```

#### mapper文件

```xml
    <!--  int getHeroGenId01(Hero hero);  -->
    <insert id="getHeroGenId01" parameterType="com.shine.entity.Hero">

        <selectKey order="AFTER" keyProperty="id" resultType="int">
            SELECT LAST_INSERT_ID()
        </selectKey>

        insert into hero(username,gender,age,addr,info) values(#{username},#{gender},#{age},#{addr},#{info})
    </insert>
```

#### 测试

```java
@Test
    public void getHeroGenId01() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取Mapper
        HeroDao heroDao = sqlSession.getMapper(HeroDao.class);

        Hero hero = new Hero();
        hero.setUsername("张顺");
        hero.setAge(23);
        hero.setGender("male");
        hero.setAddr("水泊梁山");
        hero.setInfo("浪里白条张顺");

        // 返回值是受影响的行数
        int genId01 = heroDao.getHeroGenId01(hero);

        // 回填的id
        System.out.println(hero.getId());
        System.out.println(hero);
        // 提交和关闭
        sqlSession.commit();
        sqlSession.close();
    }
```

### 6.2 自增主键回填02

* mapper标签添加属性

#### 抽象方法

```
int getHeroGenId02(Hero hero);
```

#### mapper文件

```xml
    <!--  int getHeroGenId02(Hero hero);  -->
    <insert id="getHeroGenId02" parameterType="com.shine.entity.Hero" useGeneratedKeys="true" keyProperty="id">
        insert into hero(username,gender,age,addr,info) values(#{username},#{gender},#{age},#{addr},#{info})
    </insert>
```

#### 测试

```java
@Test
    public void getHeroGenId02() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取Mapper
        HeroDao heroDao = sqlSession.getMapper(HeroDao.class);

        Hero hero = new Hero();
        hero.setUsername("张顺");
        hero.setAge(23);
        hero.setGender("male");
        hero.setAddr("水泊梁山");
        hero.setInfo("浪里白条张顺");

        // 返回值是受影响的行数
        int genId01 = heroDao.getHeroGenId02(hero);

        // 回填的id
        System.out.println(hero.getId());
        System.out.println(hero);
        // 提交和关闭
        sqlSession.commit();
        sqlSession.close();
    }
```

### 6.3 非自增主键回填

* UUID
  * 78006efd-da40-11eb-8121-a4bb6d1726f4

#### 抽象方法

```
int getHeroGenId03(Heros heros);
```

#### mapper文件

```xml
<!--  int getHeroGenId03(Heros heros);  -->
    <insert id="getHeroGenId03" parameterType="com.shine.entity.Heros">

        <selectKey order="BEFORE" keyProperty="id" resultType="java.lang.String">
            select REPLACE(UUID(),"-","")
        </selectKey>

        insert into t_hero(id,username,gender,age,addr,info) values(#{id},#{username},#{gender},#{age},#{addr},#{info})
    </insert>
```

#### 测试

```java
@Test
    public void getHeroGenId02() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取Mapper
        HerosDao heroDao = sqlSession.getMapper(HerosDao.class);

        Heros hero = new Heros();
        hero.setUsername("张顺");
        hero.setAge(23);
        hero.setGender("male");
        hero.setAddr("水泊梁山");
        hero.setInfo("浪里白条张顺");

        // 返回值是受影响的行数
        int genId01 = heroDao.getHeroGenId03(hero);

        // 回填的id
        System.out.println(hero.getId());
        System.out.println(hero);
        // 提交和关闭
        sqlSession.commit();
        sqlSession.close();
    }
```

## 七、mapper映射参数

### 7.1 简单参数

* 基本类型的单个参数

#### 抽象方法

```
User findById(int id);
```

#### mapper文件

```
<!--  User findById(int id);  -->
    <select id="findById" resultType="com.shine.entity.User" >
        select * from user where id = #{id}
    </select>
```

#### 测试

```java
@Test
    public void getUserById02() throws IOException {
        // 加载配置文件获取SQL会话工厂
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建--构造--构造器模式
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SQL会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取UserDao的代理
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.findById(2);

        // 提交事务
        System.out.println(user);
        sqlSession.commit();
        sqlSession.close();

    }
```

### 7.2 实体类对象参数

* 对象整体传入
* 参数类型标明全类名
* 属性和#{value}名称要保持一致

#### 抽象方法

```
int insertUser(User user);
```

#### mapper文件

```
    <!--  int insertUser(User user);  -->
    <insert id="insertUser" parameterType="com.shine.entity.User">
        insert into user(username,password) values (#{username},#{password})
    </insert>
```

#### 测试

```java
 @Test
    public void insertUser() throws IOException {
        // 创建Session工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 打开Session会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDao userDao = sqlSession.getMapper(UserDao.class);

        // 执行sql
        User user = new User();
        user.setUsername("qianyi");
        user.setPassword("yiqian");

        int i = userDao.insertUser(user);
        System.out.println(i);

        sqlSession.commit();
        sqlSession.close();

    }
```

### 7.3 多个参数传入

* 有可能一次传入多个实参
* 可以使用对象整体传入
* 也已使用多个参数同时传入

#### 7.3.1 使用param

#### 7.3.2 使用arg

#### 7.3.3 使用Param注解

```
    <!--  User findUserByNameAndPwd1(String username,String password);  -->
    <select id="findUserByNameAndPwd1" resultType="com.shine.entity.User">
        select * from user where username=#{param1} and password=#{param2}
    </select>

    <!--  User findUserByNameAndPwd2(String username,String password);  -->
    <select id="findUserByNameAndPwd2" resultType="com.shine.entity.User">
        select * from user where username=#{arg0} and password=#{arg1}
    </select>

    <!--  User findUserByNameAndPwd3(@Param("username") String username,@Param("password") String password);  -->
    <select id="findUserByNameAndPwd3" resultType="com.shine.entity.User">
        select * from user where username=#{username} and password=#{password}
    </select>
```



### 7.4 Map类型参数

* dao层的方法中参数是Map集合
  * map集合中的键要和mapper中取值的键保持一致

## 八、封装MyBatis工具类

