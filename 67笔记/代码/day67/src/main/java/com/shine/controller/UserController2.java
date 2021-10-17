package com.shine.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user2")
public class UserController2 {

    @Value("${user2.id}")
    private Integer id;

    @Value("${user2.username}")

    private String username;

    @Value("${user2.password}")
    private String password;

    @Value("${user2.names[0]}")
    private String names0;

    @RequestMapping("/getInfo01")
    public String getInfo01(){
        System.out.println();
        System.out.println("user2.id==>" + id);
        System.out.println("user2.username==>" + username);
        System.out.println("user2.password==>" + password);
        System.out.println("user2.names0==>" + names0);
        return "Hello";
    }

}
