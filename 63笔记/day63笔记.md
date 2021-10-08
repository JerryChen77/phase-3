# Day63笔记

## 一、Jackson细节

### User

```
package com.shine.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;

    @JsonProperty("userName")
    private String username;

    @JsonIgnore
    private String password;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date registerTime;
}
```

### User2

```
package com.shine.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shine.serializer.MySerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User2 {
    private Integer id;
    // 属性别名
    @JsonProperty("userName")
    private String username;

    // 忽略指定项
    @JsonIgnore
    private String password;

    // 自定义格式化
    @JsonSerialize(using = MySerializer.class)
    private Double salary;

    // 格式化
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date registerTime;

    // 忽略空
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> hobby;
}
```

### 服务端

```
package com.shine.controller;

import com.shine.entity.User;
import com.shine.entity.User2;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/json01")
public class JsonController01 {


    @RequestMapping("/getJson01")
    public User getJson01(){
        User user = new User(10011,"zhangsan","lisisis",new Date());
        return user;
    }

    @RequestMapping("/getJson02")
    public String getJson02(@RequestBody User user){
        System.out.println("user : " + user);
        return "Success";
    }


    @RequestMapping("/getJson03")
    public User2 getJson03(){
        User2 user = new User2();
        user.setId(112233);
        user.setUsername("lisisi");
        user.setHobby(new ArrayList<>());
        return user;
    }

    @RequestMapping("/getJson04")
    public User2 getJson04(){
        User2 user = new User2();
        user.setId(112233);
        user.setUsername("lisisi");
        user.setSalary(12345.6789);
        return user;
    }
}
```

### 客户端

```
<%--
  Created by IntelliJ IDEA.
  User: Dushine2008
  Date: 2021/7/14
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Title</title>
        <script type="text/javascript" src="js/jquery-3.6.0.js"></script>
    </head>
    <body>
        <button onclick="getJson()">获取user</button>
        <button onclick="getJson2()">获取user2</button>
        <button onclick="getJson3()">获取user3</button>
        <button onclick="sendJson()">发送user</button>
    </body>

    <script type="text/javascript">
        // 接送服务端发来的json数据
        function getJson() {
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/json01/getJson01",
                contentType:"application/json",
                success:function (ret) {
                    console.log(ret);
                }
            })
        }
        // 接送服务端发来的json数据
        function getJson2() {
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/json01/getJson03",
                contentType:"application/json",
                success:function (ret) {
                    console.log(ret);
                }
            })
        }
        // 接送服务端发来的json数据
        function getJson3() {
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/json01/getJson04",
                contentType:"application/json",
                success:function (ret) {
                    console.log(ret);
                }
            })
        }

        // 客户端向服务端发送json数据
        function sendJson() {
            var user = {id:100111,userName:"李逵",password:"xiaokui",registerTime:Date};
            var jsonUser = JSON.stringify(user);
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/json01/getJson02",
                data:jsonUser,
                contentType:"application/json",
                dataType:"json",
                success:function (ret) {
                    console.log(ret);
                }
            })
        }
    </script>
</html>
```

#### MySerializer

```
package com.shine.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 自定义小数的取舍方式
 */
public class MySerializer extends JsonSerializer<Double> {
    @Override
    public void serialize(Double value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String number = BigDecimal.valueOf(value).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        // 输出 四舍五入后的值
        jsonGenerator.writeNumber(number);
    }
}
```



## 二、FastJson

### 2.1 概述

* 国产，更好用

### 2.2 依赖

```
<!-- FastJson -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.54</version>
</dependency>
```

### 2.3 安装

```
<mvc:annotation-driven>
    <!-- 安装FastJson,转换器 -->
    <mvc:message-converters>
        <bean class="com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter">
            <!-- 声明转换类型:json -->
            <property name="supportedMediaTypes">
                <list>
                    <value>application/json</value>
                </list>
            </property>
        </bean>
    </mvc:message-converters>
</mvc:annotation-driven>
```

### 2.4 使用

```
@ResponseBody  @RequestBody @RestController 使用方法不变
```

### 2.5 注解

```
- 日期格式化：@JSONField(format="yyyy/MM/dd")
- 属性名修改：@JSONField(name="birth"）
- 忽略属性：@JSONField(serialize = false)
- 包含null值：@JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)  默认会忽略所有null值,有此注解会输出null
  - @JSONField(serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)  null的String输出为""
- 自定义序列化：@JSONField(serializeUsing = MySerializer2.class)
```

### 2.6 客户端

```
@RestController
@RequestMapping("/json02")
public class JsonController02 {

    @RequestMapping("/getJson01")
    public User3 getJson01(){
        User3 user = new User3();
        user.setId(112233);
        user.setUsername("songjiang");
        user.setPassword("666777");
        user.setSalary(1234.5678);
        user.setRegisterTime(new Date());
        return user;
    }
}
```

### 2.7 客户端

```
<%--
  Created by IntelliJ IDEA.
  User: Dushine2008
  Date: 2021/7/14
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Title</title>
        <script type="text/javascript" src="js/jquery-3.6.0.js"></script>
    </head>
    <body>
        <button onclick="getJson()">获取user</button>
        <button onclick="getJson2()">获取user2</button>
        <button onclick="getJson3()">获取user3</button>
        <button onclick="sendJson()">发送user</button>
    </body>

    <script type="text/javascript">
        // 接送服务端发来的json数据
        function getJson() {
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath}/json02/getJson01",
                contentType:"application/json",
                success:function (ret) {
                    console.log(ret);
                }
            })
        }
    </script>

</html>
```

### User3

```
package com.shine.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shine.serializer.MySerializable;
import com.shine.serializer.MySerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User3 {
    private Integer id;

    // 属性别名
    @JSONField(name="userName")
    private String username;

    // 忽略属性
    @JSONField(serialize = false)
    private String password;

    // 自定义格式化
    @JSONField(serializeUsing = MySerializable.class)
    private Double salary;

    // 日期格式化
    @JSONField(format="yyyy/MM/dd")
    private Date registerTime;

    // 优化null值
    @JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private List<String> hobby;
}
```

### 2.8 MySerializable

```
package com.shine.serializer;


import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 格式化小数输出
 */
public class MySerializable implements ObjectSerializer {

    @Override
    public void write(JSONSerializer jsonSerializer, Object object, Object o1, Type type, int i) throws IOException {
        Double value = (Double) object;     // salary属性值
        String text = value + "元";         // 在salary后拼接 “元”
        jsonSerializer.write(text);         // 输出拼接后的内容
    }
}
```

## 三、异常解析器

### 3.1 概述

* Controller中的每个Handler自己处理异常
* 此种处理方案，异常处理逻辑，分散在各个handler中，不利于集中管理
* 处理异常的操作重复比较多，可以提取出来集中处理

### 3.2 自定义异常解析器

```
package com.shine.resolver;

import com.shine.exception.MyException01;
import com.shine.exception.MyException02;
import com.shine.exception.MyException03;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * 异常解析器
 */
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();

        // 判断异常的类型
        if (ex instanceof MyException01){
            modelAndView.setViewName("redirect:/error01.jsp");
        } else if (ex instanceof SQLException){
            modelAndView.setViewName("redirect:/error02.jsp");
        } else if (ex instanceof NullPointerException){
            modelAndView.setViewName("redirect:/error03.jsp");
        }
        return modelAndView;
    }
}
```

### 3.3 配置文件

```
<!--
    声明异常解析器
    自动执行，连id不用配置
-->
<bean class="com.shine.resolver.MyExceptionResolver"></bean>
```

### 3.4  服务端

```
package com.shine.controller;

import com.shine.exception.MyException01;
import com.shine.exception.MyException02;
import com.shine.exception.MyException03;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/ex02")
@Controller
public class ExController02 {

    @RequestMapping("/e01")
    public String e01(Integer id){
        if (id % 5 == 0){
            throw new MyException01("id不能是5的倍数");
        }
        return "Success";
    }
}
```

## 四、拦截器

### 4.1 概述

* 在请求到达之前进行预处理

* 作用：抽取handler中的冗余功能
  * 比如身份验证

### 4.2 自定义拦截器

```java
package com.shine.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyLoginInterceptor implements HandlerInterceptor {
    /**
     * 请求到达之前处理
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        // System.out.println("MyLoginInterceptor === preHandle ===》");
        // 判断session中的数据是否合法
        if (session.getAttribute("isLogin") != null){
            // 验证成功,放行,执行后面的handler
            return true;
        }
        // 重定向到登录页面
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return false;
    }

    /**
     * 请求结束之后处理
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // System.out.println("MyLoginInterceptor === postHandle ===》");
    }

    /**
     * 视图加载完成之后处理
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // System.out.println("MyLoginInterceptor === afterCompletion ===》");
    }
}
```

### 4.3 配置

```xml
<mvc:interceptors>
    <mvc:interceptor>
        <mvc:mapping path="/inter02/index"/>
        <mvc:mapping path="/inter02/menu"/>
        <mvc:mapping path="/inter02/info"/>
        <!-- <mvc:mapping path="/inter/test*"/>test开头 -->
        <!-- <mvc:mapping path="/inter/**"/>  /** 任意多级任意路径 -->
        <!--<mvc:exclude-mapping path="/inter/a/**"/>   不拦截此路径-->
        <bean class="com.shine.interceptor.MyLoginInterceptor"></bean>   <!--自定义的拦截器类-->
    </mvc:interceptor>
</mvc:interceptors>
```

### 4.4 服务端

```java
package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 模拟身份验证
 */
@Controller
@RequestMapping("/inter02")
@SessionAttributes({"isLogin"})
public class InterController02 {

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("isLogin",true);
        System.out.println("登录成功...");
        return "redirect:/index.jsp";
    }

    @RequestMapping("/index")
    public String index(Model model){
        System.out.println("试图访问主页...");
        return "redirect:/index.jsp";
    }

    @RequestMapping("/info")
    public String info(Model model){
        System.out.println("试图访问个人信息...");
        return "redirect:/index.jsp";
    }

    @RequestMapping("/menu")
    public String menu(Model model){
        System.out.println("试图访问主菜单...");
        return "redirect:/index.jsp";
    }
}
```

## 五、文件上传

### 5.1 概述

* 常用的功能，会用

### 5.2 依赖

```xml
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.4</version>
        </dependency>

        <!-- 已经在生产环境中添加了servlet依赖，排除依赖需要的servlet -->
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>1.3.3</version>
            <exclusions>
                <exclusion>
                    <groupId>javax.servlet</groupId>
                    <artifactId>servlet-api</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
```

### 5.3 客户端01



```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <%--  必须设置enctype的类型 ,method必须是post --%>
        <form action="${pageContext.request.contextPath}/upload01/up01" enctype="multipart/form-data" method="post">
            选择文件:
            <input type="file" name="multipartFile" />
            <br>

            <input type="submit" value="上传">
        </form>
    </body>
</html>
```

### 5.4 客户端02

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <%--  必须设置enctype的类型 ,method必须是post --%>
        <form action="${pageContext.request.contextPath}/upload01/up02" enctype="multipart/form-data" method="post">
            选择文件:
            <input type="file" name="multipartFile" />
            <br>

            <input type="submit" value="上传">
        </form>
    </body>
</html>

```

### 5.5 服务端

```java
package com.shine.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequestMapping("/upload01")
@Controller
public class UploadController01 {

    /**
     * 获取文件名和文件类型
     * @param multipartFile
     * @return
     */
    @RequestMapping("/up01")
    public String up01(MultipartFile multipartFile){
        String filename = multipartFile.getOriginalFilename();
        System.out.println("filename==>" + filename);

        String contentType = multipartFile.getContentType();
        System.out.println("contentType==>" + contentType);

        return "index";
    }

    @RequestMapping("/up02")
    public String up02(MultipartFile multipartFile, HttpSession session) throws IOException {
        // 获取需要存入的路径
        String realPath = session.getServletContext().getRealPath("/files");

        // 获取原始文件名
        String filename = multipartFile.getOriginalFilename();
        // 存储文件的时候文件名需要怎么操作?   保证文件名的唯一性
        // 生成唯一的文件名
        String uniqueName = UUID.randomUUID().toString();

        // 拼接文件的后缀名
        filename = uniqueName + "-" + filename;
        System.out.println(filename);

        // 把文件写入服务器
        multipartFile.transferTo(new File(realPath + "\\" + filename));

        return "index";
    }
}
```

### 5.6 配置

```xml
    <!-- 上传解析器
	     id必须是：“multipartResolver”
    -->
    <bean id="multipartResolver" 
    		class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <!-- 最大可上传的文件大小  单位：byte  超出后会抛出MaxUploadSizeExceededException异常，可以异常解析器捕获 -->
        <property name="maxUploadSize" value="1048576"></property>
    </bean>
```

