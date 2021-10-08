# Day55笔记

## 一、封装MyBatis工具类

### 1.1 概述

* 好用

### 1.2 工具类

```java
package com.shine.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * MyBatis工具类
 *  获取sqlSession对象
 *  提交事务
 *  回滚
 */
public class MyBatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;
    private static final ThreadLocal<SqlSession> THREAD_LOCAL = new ThreadLocal();

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取会话
     * @return
     * @throws IOException
     */
    public static SqlSession openSession(){
        SqlSession sqlSession = THREAD_LOCAL.get();

        if (sqlSession == null){
            sqlSession = sqlSessionFactory.openSession();
            THREAD_LOCAL.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭连接
     */
    public static void closeSession(){
        SqlSession sqlSession = openSession();
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 关闭连接
     */
    public static void rollback(){
        SqlSession sqlSession = openSession();
        sqlSession.rollback();
    }

    /**
     * 获取Mapper对象
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Object> T getMapper(Class<T> clazz){
        SqlSession sqlSession = openSession();
        return sqlSession.getMapper(clazz);
    }

}
```

## 二、全局配置

### 2.1 引入jdbc属性文件

#### 概述

* 如果用户配置信息可能频繁改变，建议写在独立文件中，通过标签引入

#### 标签

> ```
> <properties resource="org/mybatis/example/config.properties">
>   <property name="username" value="dev_user"/>
>   <property name="password" value="F2Fa3!33TYyg"/>
> </properties>
> ```

#### 引入过程

```
    <properties resource="jdbc.properties"></properties>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}"/>
                <property name="url" value="${jdbc.url}"/>
                <property name="username" value="${jdbc.username}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>
```

### 2.2 设置（settings）

* 这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为。

* ```
      <settings>
          <setting name="mapUnderscoreToCamelCase" value="true"/>
      </settings>
  ```

* | mapUnderscoreToCamelCase | 是否开启驼峰命名自动映射，即从经典数据库列名 A_COLUMN 映射到经典 Java 属性名 aColumn。 |
  | ------------------------ | ------------------------------------------------------------ |
  |                          |                                                              |

### 2.3 类型别名（typeAliases）

* 类型别名可为 Java 类型设置一个缩写名字。 它仅用于 XML 配置，意在降低冗余的全限定类名书写。
* 不推荐

#### 简写形式

* 简写一个类

```
<typeAliases>
  <typeAlias alias="Author" type="domain.blog.Author"/>
  <typeAlias alias="Blog" type="domain.blog.Blog"/>
  <typeAlias alias="Comment" type="domain.blog.Comment"/>
  <typeAlias alias="Post" type="domain.blog.Post"/>
  <typeAlias alias="Section" type="domain.blog.Section"/>
  <typeAlias alias="Tag" type="domain.blog.Tag"/>
</typeAliases>
```



* 简写一个包

```
<typeAliases>
  <package name="domain.blog"/>
</typeAliases>
```



* 注解简写

### 2.4 插件（plugins）

* MyBatis 允许你在映射语句执行过程中的某一点进行拦截调用。

#### 分页插件

> https://gitee.com/free/Mybatis_PageHelper#https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md

#### 导入依赖

```
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper</artifactId>
    <version>5.2.0</version>
</dependency>
```

#### 添加插件--在mybatis-config.xml

```
    <plugins>
        <!-- com.github.pagehelper为PageHelper类所在包名 -->
        <plugin interceptor="com.github.pagehelper.PageInterceptor">
            <!-- 使用下面的方式配置参数，后面会有所有的参数介绍 -->
            <!--
                  helperDialect：分页插件会自动检测当前的数据库链接，自动选择合适的分页方式。
                  你可以配置helperDialect属性来指定分页插件使用哪种方言。
                  -->
            <property name="helperDialect" value="mysql"/>
            <!--
                   分页合理化参数，默认值为false。
                   当该参数设置为 true 时，pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），会查询最后一页。
                   默认false 时，直接根据参数进行查询。
                  -->
            <property name="reasonable" value="true"/>
        </plugin>
    </plugins>
```

#### 测试

```
@Test
    public void getAllUser(){
        // 开启分页
        PageHelper.startPage(5,5);

        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);

        List<User> users = userMapper.findAllUser();
        // 创建pageInfo对象，封装了大量和分页相关的数据
        PageInfo pageInfo = new PageInfo(users);
        System.out.println("PageNum:" + pageInfo.getPageNum());
        System.out.println("PageSize:" + pageInfo.getPageSize());
        System.out.println("StartRow:" + pageInfo.getStartRow());
        System.out.println("Pages:" + pageInfo.getPages());
        System.out.println("List:" + pageInfo.getList());

        System.out.println("============================");

        System.out.println(pageInfo);

        /*for (User user : users) {
            System.out.println(user);
        }*/

        MyBatisUtils.closeSession();
    }
```

### 2.5 环境配置（environments）

* MyBatis 可以配置成适应多种环境，这种机制有助于将 SQL 映射应用于多种数据库之中， 现实情况下有多种理由需要这么做。

* **不过要记住：尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境。**

#### **事务管理器（transactionManager）**

* 在 MyBatis 中有两种类型的事务管理器（也就是 type="[JDBC|MANAGED]"）

#### **数据源（dataSource）**

* dataSource 元素使用标准的 JDBC 数据源接口来配置 JDBC 连接对象的资源。
* **UNPOOLED**
  *  这个数据源的实现会每次请求时打开和关闭连接。
* **POOLED**
  * 这种数据源的实现利用“池”的概念将 JDBC 连接对象组织起来
  * 避免了创建新的连接实例时所必需的初始化和认证时间。

### 2.6 映射器（mappers）

* 既然 MyBatis 的行为已经由上述元素配置完了，我们现在就要来定义 SQL 映射语句了。
*  但首先，我们需要告诉 MyBatis 到哪里去找到这些语句。

#### 使用相对于类路径的资源引用

```
<mappers>
    <!--    注册    -->
    <mapper resource="com/shine/dao/UserMapper.xml"/>
</mappers>
```

#### 使用完全限定资源定位符（URL）

```
<!-- 使用完全限定资源定位符（URL） -->
<mappers>
  <mapper url="file:///var/mappers/AuthorMapper.xml"/>
  <mapper url="file:///var/mappers/BlogMapper.xml"/>
  <mapper url="file:///var/mappers/PostMapper.xml"/>
</mappers>
```

#### 使用映射器接口实现类的完全限定类名

```
<mappers>
  <mapper class="org.mybatis.builder.AuthorMapper"/>
  <mapper class="org.mybatis.builder.BlogMapper"/>
  <mapper class="org.mybatis.builder.PostMapper"/>
</mappers>
```

#### 将包内的映射器接口实现全部注册为映射器

```
<mappers>
  <package name="org.mybatis.builder"/>
</mappers>
```

## 三、ORM映射

### 3.1 映射和映射失效

* 默认映射的规则
  * 实体类中的属性《===》数据库中的列名
* 但是很多时候属性使用驼峰命名法，数据库使用下划线连接，会导致映射失效

### 3.2 列别名

* 查询的SQL语句中使用as给列一个新的名字

```xml
    <!--   User findUserById3(int id);  -->
    <select id="findUserById3" resultType="com.shine.entity.User">
        select id,username,password,register_time as registerTime from user where id = 宝贝
    </select>
```

* 测试

### 3.3 resultMap

* 处理查询得到的结果
* 完成数据库===》对象的映射

```xml
    <!--
        id  这个resultMap的唯一标记，select中通过resultMap属性管理
        type    这个resultMap得到的结果类型
    -->
    <resultMap id="GetUserInfo4" type="com.shine.entity.User">

        <!--
            id          数据库中的主键
            column      数据库中的主键名称
            property    对象中的属性名
        -->
        <result column="register_time" property="registerTime"></result>

    </resultMap>

    <resultMap id="GetUserInfo04" type="com.shine.entity.User" extends="GetUserInfo4">
    
    </resultMap>

    <!--   User findUserById4(int id);  -->
    <select id="findUserById4" resultMap="GetUserInfo04">
        select * from user where id = #{id}
    </select>
```



## 四、MyBatis处理关联关系

### 4.1 表之间的关联关系

* 一对一

```
旅客和护照
passenger
	id	主键唯一自增
	name
	sex
	birthday
	
passport
	id	主键唯一自增
	notation
	expire
	passenger_id   唯一


```

* 一对多

```
员工和部门

一个员工有一个所属部门
一个部门可以有多个员工
```



* 多对多

```
课程
	java--i
		张三
		李四
		王五
	html--2
		张三
		王五
学生
	张三--1001
		java
		html
	李四--1002
		java
	王五--1003
		java
		html
		
学生课程表
	学生id	课程id
	1001	  1
	1001	  2
	1002	  1
	1003 	  1
	1003	  2
```

### 4.2 OneToOne

#### 建表

```mysql
CREATE TABLE `t_passenger` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

```mysql
CREATE TABLE `t_passport` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `nationality` varchar(20) DEFAULT NULL,
  `expire` date DEFAULT NULL,
  `passenger_id` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `passenger_id` (`passenger_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

#### 实体类

* passport

```
private int id;
private String nationality;
private Date expire;
private int passengerId;

private Passenger passenger;
```

* passenger

```
private int id;
private String name;
private int sex;
private Date birthday;
```

#### Mapper

```
Passport findPassportWithPassengerById(int id);
```



#### mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.shine.dao.PassportMapper">

    <resultMap id="PassportResultMap" type="com.shine.entity.Passport">
        <id column="pid" property="id"></id>
        <result column="nationality" property="nationality"></result>
        <result column="expire" property="expire"></result>
        <result column="passenger_id" property="passengerId"></result>

        <!--    对象调用属性的方式赋值
        <result column="id" property="passenger.id"></result>
        <result column="name" property="passenger.name"></result>
        <result column="sex" property="passenger.sex"></result>
        <result column="birthday" property="passenger.birthday"></result>-->

        <!--    单个对象使用    -->
        <association property="passenger" javaType="com.shine.entity.Passenger">
            <id column="id" property="id"></id>
            <result column="name" property="name"></result>
            <result column="sex" property="sex"></result>
            <result column="birthday" property="birthday"></result>
        </association>
    </resultMap>

    <!--  Passport findPassportWithPassengerById(int id);  -->
    <select id="findPassportWithPassengerById" resultMap="PassportResultMap">
        SELECT
            pr.id,
            pr.name,
            pr.sex,
            pr.birthday,
            pt.id pid,
            pt.nationality,
            pt.expire,
            pt.passenger_id
        FROM
            t_passport pt
            LEFT JOIN t_passenger pr ON pt.passenger_id = pr.id WHERE pt.id=#{id}
    </select>
</mapper>
```

#### 测试

### 4.3 OneToMany

#### 建表

```
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(255) DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

```
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

#### 实体类

#### Mapper

```
Department findDeptWithEmpById(int id);
```

#### mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.shine.dao.DepartmentMapper">
    <!--  查询部门的结果映射  -->
    <resultMap id="DeptRetMap" type="com.shine.entity.Department">
        <id column="id" property="id"></id>
        <result column="dept_name" property="deptName"></result>

       	<collection property="employees" ofType="com.shine.entity.Employee">
            <id column="eid" property="id"></id>
            <result column="emp_name" property="empName"></result>
            <result column="salary" property="salary"></result>
            <result column="dept_id" property="deptId"></result>
        </collection>

    </resultMap>

    <!--  Department findDeptWithEmpById(int id);  -->
    <select id="findDeptWithEmpById" resultMap="DeptRetMap">
        SELECT
            d.id,
            d.dept_name,
            e.id eid,
            e.emp_name,
            e.salary,
            e.dept_id
        FROM
            department d
            LEFT JOIN employee e ON d.id = e.dept_id
        WHERE
            d.id=#{id}
    </select>

</mapper>
```

#### 测试

### 4.4 ManyToMany

#### 建表

```
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

```
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

#### 实体类

#### Mapper

```
Student findStuWithSubById(int id);
```

#### mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.shine.dao.StudentMapper">

    <!--  查询学生对象映射  -->
    <resultMap id="StuRetMap" type="com.shine.entity.Student">
        <id column="id" property="id"></id>
        <result column="name" property="name"></result>
        <result column="sex" property="sex"></result>

        <collection property="subjects" ofType="com.shine.entity.Subject">
            <id column="sub_id" property="id"></id>
            <result column="sub_name" property="name"></result>
            <result column="grade" property="grade"></result>
        </collection>
    </resultMap>

    <!--  Student findStuWithSubById(int id);  -->
    <select id="findStuWithSubById" resultMap="StuRetMap">
        SELECT
            s1.id,
            s1.name,
            s1.sex,
            s2.id sub_id,
            s2.name sub_name,
            s2.grade
        FROM
            student s1
            LEFT JOIN stu_sub ss ON s1.id = ss.student_id
            LEFT JOIN SUBJECT s2 ON ss.subject_id = s2.id
        WHERE
            s1.id = #{id}
    </select>
</mapper>
```

#### 测试

### 4.5 多表查询--sql语句嵌套

#### Mapper

```
Department findDeptById(int id);

List<Employee> findEmpByDeptId(int deptId);
```

#### mapper.xml

```xml
<!-- =============================================================== -->
    <!--  员工映射  -->
    <resultMap id="EmpResultMap" type="com.shine.entity.Employee">
        <id column="id" property="id"></id>
        <result column="emp_name" property="empName"></result>
        <result column="salary" property="salary"></result>
        <result column="dept_id" property="deptId"></result>
    </resultMap>

    <!--  List<Employee> findEmpByDeptId(int deptId);  -->
    <select id="findEmpByDeptId" resultMap="EmpResultMap">
        SELECT
            *
        FROM
            employee
        WHERE
            employee.dept_id = #{deptId};
    </select>

    <!--  部门映射  -->
    <resultMap id="DeptRestultMap" type="com.shine.entity.Department">
        <id column="id" property="id"></id>
        <result column="dept_name" property="deptName"></result>

        <collection
                property="employees"
                ofType="com.shine.entity.Employee"
                select="com.shine.dao.DepartmentMapper.findEmpByDeptId"
                column="id">
        </collection>
    </resultMap>

    <!--  Department findDeptById(int id);  -->
    <select id="findDeptById" resultMap="DeptRestultMap">
        SELECT
            *
        FROM
            department
        WHERE
            department.id = #{id};
    </select>
```

