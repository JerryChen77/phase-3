package com.shine.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user3")
public class UserController3 {

    @Value("${user3.id}")
    private Integer id;

    @Value("${user3.username}")

    private String username;

    @Value("${user3.password}")
    private String password;

    @Value("${user3.names[0]}")
    private String names0;

    @Value("${user3.hobby[0]}")
    private String hobby0;

    @Value("${user3.phones[zhangsan]}")
    private String phones0;

    @RequestMapping("/getInfo01")
    public String getInfo01(){
        System.out.println("user3.id==>" + id);
        System.out.println("user3.username==>" + username);
        System.out.println("user3.password==>" + password);
        System.out.println("user3.names0==>" + names0);
        System.out.println("user3.hobby0==>" + hobby0);
        System.out.println("user3.phone0==>" + phones0);
        return "Hello";
    }
}
