package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")   // 相当于servlet中的value注解，项目中保证唯一
public class HelloController {

    @RequestMapping("/sayHi01") // 相当于servlet继承BaseServlet之后的方法名，同一个控制器中保证唯一
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

    @RequestMapping("/hi03")        // 注解的名字不一定和方法名保持一致，访问的时候访问注解名
    public String sayHi03() {
        System.out.println("我是SpringMVC的第3个案例，Hi。。。");
        // 跳转的位置
        return "index";
    }

}
