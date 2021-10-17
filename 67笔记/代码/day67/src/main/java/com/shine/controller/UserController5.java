package com.shine.controller;

import com.shine.entity.User;
import com.shine.entity.User4;
import com.shine.entity.User5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user5")
public class UserController5 {
    @Autowired
    private User5 user5;

    @Autowired
    private User4 user4;



    @RequestMapping("/getInfo01")
    public String getInfo01(){
        System.out.println("user4 = " + user4);
        System.out.println("user5==>" + user5);
        return "Hello";
    }
}
