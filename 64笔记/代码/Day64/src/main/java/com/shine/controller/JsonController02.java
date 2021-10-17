package com.shine.controller;

import com.shine.entity.User;
import com.shine.entity.User2;
import com.shine.entity.User3;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
