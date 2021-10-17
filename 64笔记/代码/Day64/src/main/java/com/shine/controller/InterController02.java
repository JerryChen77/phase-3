package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 模拟身份验证
 */
@Controller
@RequestMapping("/inter02")
@SessionAttributes({"isLogin"})
public class InterController02 {

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("isLogin",true);
        System.out.println("登录成功...");
        return "redirect:/index.jsp";
    }

    @RequestMapping("/index")
    public String index(Model model){
        System.out.println("试图访问主页...");
        return "redirect:/index.jsp";
    }

    @RequestMapping("/info")
    public String info(Model model){
        System.out.println("试图访问个人信息...");
        return "redirect:/index.jsp";
    }

    @RequestMapping("/menu")
    public String menu(Model model){
        System.out.println("试图访问主菜单...");
        return "redirect:/index.jsp";
    }
}
