package com.shine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello User";
    }

    @RequestMapping("/login")
    public String login(){
        return "Hello Login";
    }

    @RequestMapping("/getUser01")
    public String getUser01(){
        return "Hello Login0001";
    }

    @RequestMapping("/getUser02")
    public String getUser02(){
        return "Hello Login0002";
    }

    @RequestMapping("/getUser03")
    public String getUser03(){
        return "Hello Login0003";
    }

    @RequestMapping("/getUser04")
    public String getUser04(){
        return "Hello Login0004";
    }

}
