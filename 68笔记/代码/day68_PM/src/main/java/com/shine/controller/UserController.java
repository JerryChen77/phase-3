package com.shine.controller;

import com.shine.dao.UserDao;
import com.shine.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/getUser01")
    public String getUser01(Model model){
        model.addAttribute("username","宋思明");
        return "index";
    }
}
