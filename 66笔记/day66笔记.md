# Day 66笔记

## 一、依赖传递

### 1.1 概述

* 当我们添加了一个依赖A的时候
  * 依赖A中需要依赖B、C才能工作
    * 又会自动添加依赖B和C

### 1.2 依赖冲突

* A依赖传递添加了B和C依赖，版本是2.X
* AA依赖传递添加了BB和CC依赖，版本是3.X
* 可能产生冲突，报一些诡异的错误

### 1.3 解决方案

#### maven默认解决方案

* 声明早的先被使用
  * 在maven的pom文件中，相同的依赖，声明在前面的被先使用到
* 距离项目近的先被使用
  * 依赖传递的时候可能会导入大量的其他依赖
  * 这些大量的依赖可能名字相同，版本不同
  * 谁的文件夹层级低，谁先被执行

#### 自定义解决

* 依赖排除

  ```
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>5.2.5.RELEASE</version>
      <exclusions>
          <exclusion>
              <groupId>org.springframework</groupId>
              <artifactId>spring-core</artifactId>
          </exclusion>
      </exclusions>
  </dependency>
  ```

  * 可以把指定类型的依赖从当前的依赖传递中排除

* 版本锁定
  - 一定会用到
* 声明版本

```
    <!--  属性集，我们定义了大量的版本号  -->
    <properties>
        <bean.version>5.1.6.RELEASE</bean.version>
        <spring.version>5.1.6.RELEASE</spring.version>
        <servlet.version>2.4</servlet.version>
    </properties>
```

* 锁定版本

```
<!--  依赖管理，锁定依赖的版本  -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-beans</artifactId>
                <version>${bean.version}</version>
            </dependency>

            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-webmvc</artifactId>
                <version>${spring.version}</version>
            </dependency>

            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-aspects</artifactId>
                <version>5.1.6.RELEASE</version>
            </dependency>
      
      </dependencies>
    </dependencyManagement>
```

* 添加依赖

```
 <!--  添加依赖，真正引入依赖的标签  -->
    <dependencies>
        <!--    排除可能出现问题的依赖    -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${spring.version}</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-beans</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
        </dependency>
     </dependencies>
```

## 二、继承

### 2.1 概述

* 不再是java中的继承
* 项目与项目之间的关系

### 2.2 项目继承产生的方式

* 创建项目
  * 在项目中创建module
  * 一个项目中可以创建多个module
  * 子模块能使用父模块中的数据
* 父模块一般没有具体的工作
  * 统一版本
  * 整合子模块
* 继承需要在子类模块中声明父模块的GAV

* 继承层级可以实现多级继承
  * 层级不宜太多，不好管理

* 父模块声明的打包方式
  * pom

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.shine</groupId>
    <artifactId>SSM</artifactId>
    <version>1.0-SNAPSHOT</version>

    <modules>
        <module>ssm_entity</module>
        <module>ssm_dao</module>
    </modules>

    <packaging>pom</packaging>
```

* 子模块

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>SSM</artifactId>
        <groupId>com.shine</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <modelVersion>4.0.0</modelVersion>

    <artifactId>ssm_entity</artifactId>

</project>
```

* 模块能同时当做父模块和子模块

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>SSM</artifactId>
        <groupId>com.shine</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>ssm_dao</artifactId>
    <packaging>pom</packaging>
    <modules>
        <module>ssm_dao_user</module>
    </modules>

</project>
```

## 三、聚合

### 3.1 概述

* 随着技术的发展，项目做得越来越大
  * 层级越来越多
  * 模块越来越多
* 开发的人员、模块越来越多
* 最终这些开发出来的内容要整合到一起
* 最好有一个父模块，整合所有的子模块，进行统一的管理和操作

## 四、拆分

### 4.1 概述

* 随着技术的发展，项目做得越来越大，可以把项目拆成N和单元分别开发
* 最后把项目整合

### 4.2 拆分的方式

* 按照模块拆分
* 按照层级拆分

## 五、拆分&聚合Hero

### 5.1 概述

* 把最后一个SSM案例拆分
  * 按照层级拆分
  * entity
  * dao
  * service
  * controller

### 5.2 写入代码

* 按照调用顺序写入

