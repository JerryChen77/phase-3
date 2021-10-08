# Day 56笔记

## 一、缓存

### 1.1 概述

* 内存中的一块存储空间，服务于某个应用程序，旨在将频繁读取的数据临时保存在内存中，便于二次快速访问。
* 缓存优先级：先二级缓存，再一级缓存，缓存有隔离性，一级缓存每个SqlSession都是独立，二级缓存每个namespace都是独立的

> 无缓存：用户在访问相同数据时，需要发起多次对数据库的直接访问，导致产生大量IO、读写硬盘的操作，效率低下

![](..\资料\无缓存.png)

> 有缓存：首次访问时，查询数据库，将数据存储到缓存中；再次访问时，直接访问缓存，减少IO、硬盘读写次数、提高效率

![](..\资料\有缓存.png)

### 1.2 一级缓存

* sqlSession级别的缓存是一级缓存，默认开启
* sqlSession级别的缓存工作区在内存
* 多次同构查询得到的是同一个结果

#### User

```
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private Date registerTime;
```

#### Mapper

```
 User selectUser(Integer id);
```

#### mapper.xml

```
    <!-- User selectUser(Integer id); -->
    <select id="selectUser" resultType="com.shine.entity.User">
        select * from user where id = #{id}
    </select>
```

#### 测试

```java
package com.shine.mapper;

import com.shine.entity.User;
import com.shine.utils.MyBatisUtils;
import org.junit.Test;

public class UserMapperTest {
    @Test
    public void getUser(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);
        User user = userMapper.selectUser(1);
        System.out.println(user);
        MyBatisUtils.commit();

        UserMapper userMapper2 = MyBatisUtils.getMapper(UserMapper.class);
        User user2 = userMapper2.selectUser(1);
        System.out.println(user2);
        MyBatisUtils.commit();
    }

    @Test
    public void getUserById(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);
        User user = userMapper.selectUser(1);
        System.out.println(user);

        User user1 = userMapper.selectUser(1);
        System.out.println(user1);

        System.out.println(user == user1);

    }
}
```

### 1.3 一级缓存失效的情况

* 多次同构查询中间如果发生了下面的情况，缓存会失效
  * 增删改
  * 提交事务
  * 关闭sqlsession

#### mapper.xml

```
    <!--  Integer insertUser(User user);  -->
    <insert id="insertUser" parameterType="com.shine.entity.User">
        insert into user(username,password) values(#{username},#{password})
    </insert>

    <!--  Integer deleteUser(Integer id);  -->
    <delete id="deleteUser">
        delete from user where id=#{id}
    </delete>
```

#### 测试

```
@Test
    public void getUser02(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);
        User user01 = userMapper.selectUser(1);
        System.out.println(user01);

        User user02 = new User();
        user02.setUsername("bajie");
        user02.setPassword("jieba");
        Integer ret02 = userMapper.insertUser(user02);
        System.out.println("ret02==" + ret02);

        User user03 = userMapper.selectUser(1);
        System.out.println(user03);

        System.out.println(user01 == user03);

    }

    @Test
    public void getUser03(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);
        User user01 = userMapper.selectUser(1);
        System.out.println(user01);

        Integer ret02 = userMapper.deleteUser(35);
        System.out.println("ret02==" + ret02);

        User user03 = userMapper.selectUser(1);
        System.out.println(user03);

        System.out.println(user01 == user03);

    }

    @Test
    public void getUser04(){
        SqlSession sqlSession01 = MyBatisUtils.openSession();
        UserMapper userMapper01 = sqlSession01.getMapper(UserMapper.class);

        User user01 = userMapper01.selectUser(2);
        System.out.println(user01);
        sqlSession01.commit();

        User user02 = userMapper01.selectUser(2);
        System.out.println(user02);

    }

    @Test
    public void getUser05(){
        SqlSession sqlSession01 = MyBatisUtils.openSession();
        UserMapper userMapper01 = sqlSession01.getMapper(UserMapper.class);

        User user01 = userMapper01.selectUser(2);
        System.out.println(user01);
        sqlSession01.close();


        SqlSession sqlSession02 = MyBatisUtils.openSession();
        UserMapper userMapper02 = sqlSession02.getMapper(UserMapper.class);
        User user02 = userMapper02.selectUser(2);
        System.out.println(user02);
        sqlSession02.close();

    }
```

### 1.4 二级缓存

* sqlSessionFactory级别

* 存储在磁盘

* 使用二级缓存需要手动开启

  * 设置settings

  ```
  <configuration>
  	<properties .../>
    	
    	<!-- 注意书写位置 -->
      <settings>
          <setting name="cacheEnabled" value="true"/> <!-- mybaits-config.xml中开启全局缓存（默认开启） -->
      </settings>
    
    	<typeAliases></typeAliases>
  </configuration>
  ```

  * 给指定的mapper设置缓存

  ```
  <cache /> <!-- 指定缓存 -->
  ```

* 二级缓存写入的时间是事务提交之后

* 缓存写入的对象需要被序列化

  * 实现接口Serializable

* 二级缓存写入的时间

  * 一次sqlsession提交或者关闭之后

### 1.5 二级缓存失效

* 发生了增删改的操作--事务提交之后

#### 测试

```java

    @Test
    public void getUser06(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);
        User user01 = userMapper.selectUser(1);
        System.out.println(user01);
        MyBatisUtils.commit();

        UserMapper userMapper02 = MyBatisUtils.getMapper(UserMapper.class);
        User user02 = userMapper02.selectUser(1);
        System.out.println(user02);
        MyBatisUtils.commit();

        UserMapper userMapper03 = MyBatisUtils.getMapper(UserMapper.class);
        User user03 = userMapper03.selectUser(1);
        System.out.println(user03);
        MyBatisUtils.commit();

        System.out.println(user01 == user03);
    }


    @Test
    public void getUser07(){
        UserMapper userMapper = MyBatisUtils.getMapper(UserMapper.class);
        User user01 = userMapper.selectUser(1);
        System.out.println(user01);
        MyBatisUtils.commit();

        UserMapper userMapper02 = MyBatisUtils.getMapper(UserMapper.class);
        User user02 = new User();
        user02.setUsername("shaseng");
        user02.setPassword("sengsha");
        userMapper02.insertUser(user02);
        MyBatisUtils.commit();

        UserMapper userMapper03 = MyBatisUtils.getMapper(UserMapper.class);
        User user03 = userMapper03.selectUser(1);
        System.out.println(user03);
        MyBatisUtils.commit();

        System.out.println(user01 == user03);
    }

```

## 二、动态SQL

### 2.1 概述

* 以往问题
  * SQL语句是硬编码的，不能改变
  * 有些场景SQL语句需要经常变化
* MyBatis的映射文件中支持在基础SQL上添加一些逻辑操作，并动态拼接成完整的SQL之后再执行，以达到SQL复用、简化编程的效果。

### 2.2 动态SQL语法

* if
* where
* foreach
* set
* choose...when...otherwise
* sql...include

### 2.3 if+where

* 判断条件是否成立
  * 如果成立，执行
  * 没有else
* 多种条件的场景适用
* 插入一条数据
  * 数据的列不详
  * 用户
    * id
    * username
    * password
    * register_time
    * addr
    * gender
    * ... ...

#### 建表SQL

```
CREATE TABLE `hero` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```

#### Hero类

```
private Integer id;
private String username;
private Integer age;
private String addr;
private String info;
```

#### HeroMapper

```
List<Hero> selectHero(Hero hero);
```

#### mapper.xml

```xml
<!--  Integer selectHero(Hero hero);  -->
    <select id="selectHero" parameterType="com.shine.entity.Hero" resultType="com.shine.entity.Hero">
        select * from hero
        <where>
            <if test="id != null">	
                and id=#{id}		
                		<!--where子句中满足条件的if，在不需要时会自动忽略前后缀（如：and|or）-->
            </if>

            <if test="username != null">
                and username=#{username}
            </if>

            <if test="age != null">
                and age=#{age}
            </if>

            <if test="addr != null">
                and addr like #{addr}
            </if>

            <if test="info != null">
                and info like #{info}
            </if>
        </where>

    </select>
```

#### 测试

```
    @Test
    public void getHero(){
        Hero hero = new Hero();
        hero.setUsername("wuyong");
        hero.setAge(36);
        hero.setAddr("%山东%");
        HeroMapper heroMapper = MyBatisUtils.getMapper(HeroMapper.class);
        List<Hero> heroes = heroMapper.selectHero(hero);

        System.out.println(heroes);
    }
```

### 2.4 foreach

#### Mapper

```
Integer insertHeroes(List<Hero> heroes);
List<Hero> selectHeroesById(List<Integer> ids);
```

#### mapper.xml

```
<!--  Integer insertHeroes(List<Hero> heroes);  -->
    <insert id="insertHeroes" parameterType="com.shine.entity.Hero">
        insert into hero(username,gender,age,addr,info) values

        <foreach collection="heroes" item="hero" separator=",">
            (#{hero.username},#{hero.gender},#{hero.age},#{hero.addr},#{hero.info})
        </foreach>

    </insert>

    <!--  List<Hero> selectHeroesById(List<Integer> ids);  -->
    <select id="selectHeroesById" parameterType="java.lang.Integer" resultType="com.shine.entity.Hero">
    <!--
        foreach     遍历集合
        collection  被遍历的集合，名称可以是list、collection
        item        集合中的元素
        separator   多个元素之间的分隔符
        open        前缀，拼接SQL开始的内容
        close       后缀，拼接SQL结束的内容
        index       索引
    -->
        select * from hero
        <where>
            <if test="list != null">
                and id
                <foreach collection="list" item="id" separator="," open="in (" close=")" >
                    #{id}
                </foreach>
            </if>
        </where>
    </select>
```

### 2.5 set

* 执行update时候使用

#### Mapper

```
Integer updateHero(Hero hero);
```

#### mapper.xml

```xml
    <!--  Integer updateHero(Hero hero);  -->
    <update id="updateHero" parameterType="com.shine.entity.Hero">
        update hero
            <set>
                <if test="username != null and username.trim().length>0">
                    username=#{username},
                </if>		<!--  set子句中满足条件的if，在不需要时会自动忽略后缀（如：，）  -->
                		
                <if test="gender != null and gender.trim().length>0">g
                    ender=#{gender},					
                </if>
                
                <if test="age != null">age=#{age},</if>
                <if test="addr != null and addr.trim().length>0">addr=#{addr},</if>
                <if test="info != null and info.trim().length>0">info=#{info}</if>
            </set>
        <where>
            and id=#{id}
        </where>
    </update>
```

### 2.6 trim

* 等价替换where或者trim,	prefixOverrides="前缀" 	suffixOverrides="后缀"   当不需要时自动忽略前后缀

#### Mapper

```
Integer updateHero2(Hero hero);

List<Hero> selectHero2(Hero hero);
```

#### mapper.xml

```
<!--  Integer updateHero2(Hero hero);  -->
    <update id="updateHero2" parameterType="com.shine.entity.Hero">
        update hero
        <trim prefix="set" prefixOverrides="," suffixOverrides=",">
            <if test="username != null and username.trim().length>0">username=#{username},</if>
            <if test="gender != null and gender.trim().length>0">gender=#{gender},</if>
            <if test="age != null">age=#{age},</if>
            <if test="addr != null and addr.trim().length>0">addr=#{addr},</if>
            <if test="info != null and info.trim().length>0">info=#{info}</if>
        </trim>
        <where>
            and id=#{id}
        </where>
    </update>

    <!--  Integer selectHero2(Hero hero);  -->
    <select id="selectHero2" parameterType="com.shine.entity.Hero" resultType="com.shine.entity.Hero">
        select * from hero
        <trim prefix="where" prefixOverrides="and |or">
            <if test="id != null">and id=#{id}</if>
            <if test="username != null">and username=#{username}</if>
            <if test="age != null">and age=#{age}</if>
            <if test="addr != null">and addr like #{addr}</if>
            <if test="info != null">and info like #{info}</if>
        </trim>
    </select>
```

### 2.7 choose...when...otherwise

* 类似java中的switch

#### Mapper

```
List<Hero> selectHeroByOrder(Hero hero);
```

#### mapper.xml

```
    <!--  List<Hero> selectHeroByOrder(User user);  -->
    <select id="selectHeroByOrder" resultType="com.shine.entity.Hero" parameterType="com.shine.entity.Hero">
        select * from hero
        <where>
            <choose>
                <when test="id != null">and id=#{id}</when>
                <when test="username != null">and username=#{username}</when>
                <when test="gender != null">and gender=#{gender}</when>
                <when test="age != null">and age=#{age}</when>
                <when test="addr != null">and addr like #{addr}</when>
                <when test="info != null">and info like #{info}</when>
                <otherwise>and id=1</otherwise>
            </choose>
        </where>
    </select>
```

### 2.8 sql...include

* 提取相同、重复的SQL语句，封装成一个独立的sql片段
* 可以在include标签中引用，达到复用的目的

#### Mapper

```
User selectUser2(Integer id);

User selectUser3(Integer id);
```

#### mapper.xml

```
    <!-- User selectUser2(Integer id); -->
    <select id="selectUser2" resultType="com.shine.entity.User">
        select id,username,password,register_time as registerTime from user where id = #{id}
    </select>

    <!-- User selectUser3(Integer id); -->
    <select id="selectUser3" resultType="com.shine.entity.User">
        select <include refid="UserSql"></include> from user where id = #{id}
    </select>

    <sql id="UserSql">id,username,password,register_time as registerTime</sql>
```

## 三、注解

### 3.1 概述

* 通过在接口中直接添加MyBatis注解，完成CRUD。
* 采用注解的方式代替xml文件中的各种crud标签

> - [注意：接口注解定义完毕后，需将接口全限定名注册到mybatis-config.xml的< mappers >中。]()
> - [经验：注解模式属于硬编码到.java文件中，失去了使用配置文件外部修改的优势，可结合需求选用。]()

### 3.2 体验简单使用

#### Employee

```
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Integer id;
    private String empName;
    private Double salary;
    private Integer deptId;
}
```

#### EmployeeMapper

```java
public interface EmployeeMapper {
    @Select("select * from employee where id=#{id}")
    Employee selectEmployeeById(Integer id);

    @Select("select id,emp_name empName,salary,dept_id deptId from employee where id=#{id}")
    Employee selectEmployeeById2(Integer id);

    @Select("select * from employee where id=#{id}")
    @Results(id = "EmpRetMap",
        value = {
              @Result(column = "emp_name",property = "empName"),
              @Result(column = "dept_id",property = "deptId")
        })
    Employee selectEmployeeById3(Integer id);
}
```

#### 测试

### 3.3 注解多表联查

#### Department

```
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private Integer id;
    private String deptName;

    private List<Employee> employees;
}
```

#### DepartmentMapper

```java
package com.shine.mapper;

import com.shine.entity.Department;
import com.shine.entity.Employee;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface DepartmentMapper {

    @Select("select * from employee where dept_id=#{id}")
    @Results(id = "EmpRetMap",
            value = {
                    @Result(column = "emp_name",property = "empName"),
                    @Result(column = "dept_id",property = "deptId")
            })
    List<Employee> findEmpByDeptId(Integer id);



    @Select("select * from department where id=#{id}")
    @Results(id = "DeptRetMap",
    value = {
            @Result(column = "dept_name",property = "deptName"),
            @Result(column = "id",
                    property = "employees",
                    many = @Many(select = "com.shine.mapper.DepartmentMapper.findEmpByDeptId",fetchType= FetchType.EAGER)
            )
    })
    Department findDeptById(Integer id);
}
```

