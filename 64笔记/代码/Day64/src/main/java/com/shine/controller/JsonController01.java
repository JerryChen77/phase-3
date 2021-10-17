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
//        user.setHobby(new ArrayList<>());
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
