# Swagger

## 一、 概述

* Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。
* 总体目标是使客户端和文件系统作为服务器以同样的速度来更新。
* 文件的方法、参数和模型紧密集成到服务器端的代码，允许 API 来始终保持同步。
* Swagger 让部署管理和使用功能强大的 API 从未如此简单。

## 二、快速入门

### 2.1 步骤

* 创建SpringBoot项目
* 引入swagger依赖
  * swagger2
  * swagger-ui
* 在启动类注解Swagger配置
* 编写一个控制器
* 测试
  * http://localhost:8080/swagger-ui.html

 

### 2.2 依赖

```
<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```

### 2.3 注解配置

* 在启动类添加注解

```
@SpringBootApplication
@EnableSwagger2
public class TestSwagger01Application {
    public static void main(String[] args) {
        SpringApplication.run(TestSwagger01Application.class, args);
    }
}
```

### 2.4 控制器

* UserController

```
package com.shine.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/getUser01/{id}")
    public String getUser01(@PathVariable("id") Integer id){
        return "Hello User001";
    }
    @RequestMapping("/getUser02/{id}")
    public String getUser02(@PathVariable("id") Integer id){
        return "Hello User002";
    }
}
```

* HeroController

```
package com.shine.controller;

import com.shine.entity.Hero;
import com.shine.entity.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.spring.web.json.Json;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/hero")
public class HeroController {

    @PostMapping("/heroes/{id}")
    public String getHero01(@PathVariable("id") Integer id, Model model){
        model.addAttribute("hero","hero001");
        return "index";
    }

    @PutMapping("/heroes/{id}")
    public ModelAndView getHero02(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("hero","hero002");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/heroes/{id}")
    @ResponseBody
    public Hero getHero03(@PathVariable("id") Integer id, Model model){
        Hero hero = new Hero(10010,"zhangsan",12345,"河南","东京");
        return hero;
    }

    @DeleteMapping("/heroes/{id}")
    @ResponseBody
    public String getHero04(@PathVariable("id") Integer id, Model model){
        model.addAttribute("hero","hero001");
        return "index";
    }

    @GetMapping("/heroes")
    @ResponseBody
    public List<Hero> getHero05(@PathVariable("id") Integer id, Model model){
        Hero hero01 = new Hero(10010,"zhangsan",12345,"河南","东京");
        Hero hero02 = new Hero(10010,"zhangsan",12345,"河南","东京");
        Hero hero03 = new Hero(10010,"zhangsan",12345,"河南","东京");
        Hero hero04 = new Hero(10010,"zhangsan",12345,"河南","东京");
        List<Hero> heroes = Arrays.asList(hero01, hero02, hero03, hero04);
        return heroes;
    }

    @GetMapping("/heroesVO")
    @ResponseBody
    public ResultVO getHero06(@PathVariable("id") Integer id, Model model){
        Hero hero01 = new Hero(10011,"zhangsan",12345,"河南","东京");
        Hero hero02 = new Hero(10012,"zhangsan",12345,"河南","东京");
        Hero hero03 = new Hero(10013,"zhangsan",12345,"河南","东京");
        Hero hero04 = new Hero(10014,"zhangsan",12345,"河南","东京");
        List<Hero> heroes = Arrays.asList(hero01, hero02, hero03, hero04);

        ResultVO resultVO = new ResultVO();
        resultVO.setMessage("Success");
        resultVO.setData(heroes);
        resultVO.setSuccess(true);

        return resultVO;
    }
}
```

### 2.5 测试

* 访问下面的链接

```
http://localhost:8080/swagger-ui.html
```

## 三、Swagger配置类

### 3.1 概述

* 在引导类上添加enableSwagger2使用的都是默认信息
* 我们需要个性化的定制信息

### 3.2 SwaggerConfig配置类

* 创建config.SwaggerConfig
* 编写docket方法，添加Bean注解
* 创建docket对象，传入参数
* 编写返回ApiInfo对象的方法

```
package com.shine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 创建docket对象注入
     * @return
     */
    @Bean
    public Docket docket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        docket
                .apiInfo(appInfo())
                .groupName("test_shine")    // 组名字
            // 自定义扫描规则
            .select()
                .apis(RequestHandlerSelectors.basePackage("com.shine.controller"))  // 扫描的包
                .paths(PathSelectors.ant("/hero/**"))                    // 扫描包中匹配的内容
                .build();

        return docket;
    }

    /**
     * 返回APIInfo对象
     * @return
     */
    public ApiInfo appInfo() {
        Contact contact = new Contact(
                "百度互联",
                "http://www.baidu.com",
                "dujunqiang@1000phone.com"
        );

        List<VendorExtension> vendorExtensions = new ArrayList<>();
        vendorExtensions.add(new StringVendorExtension("公司名称","千锋互联"));
        vendorExtensions.add(new StringVendorExtension("联系电话","10086"));
        vendorExtensions.add(new StringVendorExtension("办公地址","旺田商务楼"));

        // 创建APIInfo对象
        ApiInfo apiInfo = new ApiInfo(
                "千锋互联",                         // 标题
                "用良心做教育",               // 描述
                "V10.3",                          // 版本
                "http://www.jd.com",    // 团队链接
                contact,                                   // 扩展信息
                "license",                         // 许可
                "http://www.1000phone.com",     // 许可地址
                vendorExtensions
        );
        return apiInfo;
    }
}
```

### 3.3 测试

* 看页面效果

## 四、注解实现Swagger

### 4.1 控制器注解

* @Api

```
@Api(tags = "英雄接口",value = "英雄访问接口,提供增删改查方法")
```

### 4.2 方法注解

* @ApiOperation
  * handler注解

### 4.3 参数注解

* @ApiImplicitParams
  - handler参数注解，数组
* @ApiImplicitParam
  - handler注解
  - 放入@ApiImplicitParams

```
    @ApiOperation(tags = "增加英雄",value = "增加指定id对应的Hero对象",notes = "增加指定id对应的Hero对象，返回Hero展示页")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "英雄id",dataType = "Integer",example = "10010")
    )
    @PostMapping("/heroes/{id}")
    public String getHero01(@PathVariable("id") Integer id, Model model){
        model.addAttribute("hero","hero001");
        return "index";
    }
```

```
package com.shine.controller;

import com.shine.entity.Hero;
import com.shine.entity.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.spring.web.json.Json;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/hero")
@Api(tags = "英雄接口",value = "英雄访问接口,提供增删改查方法")
public class HeroController {

    @ApiOperation(tags = "增加英雄",value = "增加指定id对应的Hero对象",notes = "增加指定id对应的Hero对象，返回Hero展示页")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "英雄id",dataType = "Integer",example = "10010")
    )
    @PostMapping("/heroes/{id}")
    public String getHero01(@PathVariable("id") Integer id, Model model){
        model.addAttribute("hero","hero001");
        return "index";
    }

    @ApiOperation(tags = "修改英雄",value = "修改指定id对应的Hero对象",notes = "修改指定id对应的Hero对象，返回Hero展示页")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "英雄id",dataType = "Integer",example = "10010")
    )
    @PutMapping("/heroes/{id}")
    public ModelAndView getHero02(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("hero","hero002");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @ApiOperation(tags = "查询英雄",value = "查询指定id对应的Hero对象",notes = "查询指定id对应的Hero对象，返回Hero对象")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "英雄id",dataType = "Integer",example = "10010")
    )
    @GetMapping("/heroes/{id}")
    @ResponseBody
    public Hero getHero03(@PathVariable("id") Integer id, Model model){
        Hero hero = new Hero(10010,"zhangsan",12345,"河南","东京");
        return hero;
    }

    @ApiOperation(tags = "删除英雄",value = "删除指定id对应的Hero对象",notes = "删除指定id对应的Hero对象，返回Hero展示页")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "英雄id",dataType = "Integer",example = "10010")
    )
    @DeleteMapping("/heroes/{id}")
    @ResponseBody
    public String getHero04(@PathVariable("id") Integer id, Model model){
        model.addAttribute("hero","hero001");
        return "index";
    }

    @ApiOperation(tags = "查询英雄",value = "查询所有的Hero对象",notes = "查询所有的Hero对象，返回Hero对象集合")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "英雄id",dataType = "Integer",example = "10010")
    )
    @GetMapping("/heroes")
    @ResponseBody
    public List<Hero> getHero05(@PathVariable("id") Integer id, Model model){
        Hero hero01 = new Hero(10010,"zhangsan",12345,"河南","东京");
        Hero hero02 = new Hero(10010,"zhangsan",12345,"河南","东京");
        Hero hero03 = new Hero(10010,"zhangsan",12345,"河南","东京");
        Hero hero04 = new Hero(10010,"zhangsan",12345,"河南","东京");
        List<Hero> heroes = Arrays.asList(hero01, hero02, hero03, hero04);
        return heroes;
    }

    @ApiOperation(tags = "查询英雄",value = "查询所有Hero对象",notes = "查询所有的Hero对象，返回Hero对象结果集")
    @GetMapping("/heroesVO")
    @ResponseBody
    public ResultVO getHero06(@PathVariable("id") Integer id, Model model){
        Hero hero01 = new Hero(10011,"zhangsan",12345,"河南","东京");
        Hero hero02 = new Hero(10012,"zhangsan",12345,"河南","东京");
        Hero hero03 = new Hero(10013,"zhangsan",12345,"河南","东京");
        Hero hero04 = new Hero(10014,"zhangsan",12345,"河南","东京");
        List<Hero> heroes = Arrays.asList(hero01, hero02, hero03, hero04);

        ResultVO resultVO = new ResultVO();
        resultVO.setMessage("Success");
        resultVO.setData(heroes);
        resultVO.setSuccess(true);

        return resultVO;
    }
}
```



### 4.4 实体类注解

* @ApiModel
  * 注解在类上面

```
@ApiModel(value = "英雄实体类",description = "对应数据库hero表")
public class Hero {
```

### 4.5 属性注解

* @ApiModelProperty


```
@ApiModelProperty(name = "name",value = "英雄名字，宋江、林冲、武松...",example = "武松")
```

```
package com.shine.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

@Component
@ApiModel(value = "英雄实体类",description = "对应数据库hero表")
public class Hero {
    @ApiModelProperty(name = "id",value = "英雄id，自增唯一，10010、10011、10086...",example = "10010")
    private Integer id;

    @ApiModelProperty(name = "name",value = "英雄名字，宋江、林冲、武松...",example = "武松")
    private String name;

    @ApiModelProperty(name = "forceValue",value = "武力值（0---100,000）12300、15000、9000...",example = "12300")
    private Integer forceValue;

    @ApiModelProperty(name = "add",value = "英雄老家地址，山东郓城、山西郓城、河南东京...",example = "河南东京")
    private String addr;

    @ApiModelProperty(name = "info",value = "英雄描述信息，武松曾经在景阳冈上空手打死一只吊睛白额虎，因此，“武松打虎”的事迹在后世广为流传。",example = "武松曾经在景阳冈上空手打死一只吊睛白额虎，因此，“武松打虎”的事迹在后世广为流传。")
    private String info;

    public Hero() {
    }

    public Hero(Integer id, String name, Integer forceValue, String addr, String info) {
        this.id = id;
        this.name = name;
        this.forceValue = forceValue;
        this.addr = addr;
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getForceValue() {
        return forceValue;
    }

    public void setForceValue(Integer forceValue) {
        this.forceValue = forceValue;
    }

    public String getAddr() {
        return addr;
    }

    public void setAdd(String add) {
        this.addr = add;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", forceValue=" + forceValue +
                ", addr='" + addr + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
```

