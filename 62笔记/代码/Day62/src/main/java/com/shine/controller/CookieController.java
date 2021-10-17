package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/cook")
public class CookieController {

    @RequestMapping("/getCookie01")
    public String getCookie01(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie01 = new Cookie("username","lisi");
        Cookie cookie02 = new Cookie("password","sili");

        response.addCookie(cookie01);
        response.addCookie(cookie02);

        return "forward:/show05.jsp";
    }

    @RequestMapping("/getCookie02")
    public String getCookie02(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + "====" + cookie.getValue());
        }
        return "forward:/show05.jsp";
    }
}
