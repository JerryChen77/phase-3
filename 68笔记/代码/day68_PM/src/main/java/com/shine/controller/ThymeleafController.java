package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/thymeleaf")
@Controller
public class ThymeleafController {

    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("username","小班");
        return "index";
    }

}
