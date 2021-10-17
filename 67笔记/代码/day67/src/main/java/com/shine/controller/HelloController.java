package com.shine.controller;

import com.shine.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/hi")
    public String sayHi(){
        return "Hello SpringBoot!";
    }

    @Value("${name}")
    private String name;

    @RequestMapping("/getInfo01")
    public String getInfo01(){
        System.out.println("name==>" + name);
        return "Hello";
    }

    @Value("${user.id}")
    private Integer id;

    @Value("${user.username}")

    private String username;

    @Value("${user.password}")
    private String password;

    @RequestMapping("/getInfo02")
    public String getInfo02(){
        System.out.println("user.id==>" + id);
        System.out.println("user.username==>" + username);
        System.out.println("user.password==>" + password);
        return "Hello";
    }

}
