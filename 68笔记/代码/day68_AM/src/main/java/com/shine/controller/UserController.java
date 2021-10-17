package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUser01")
    public String getUser01(Model model){
        model.addAttribute("username","宋思明");
        return "index";
    }

}
