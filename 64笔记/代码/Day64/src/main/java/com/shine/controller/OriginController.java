package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping("/origin")
@Controller
@CrossOrigin
public class OriginController {

    @RequestMapping("/getData01")
    public String getData01(){
        System.out.println("OriginController ==> getData01...");
        return "index";
    }

    @RequestMapping("/getData02")
    public String getData02(HttpSession session, HttpServletResponse response){
        response.setHeader("'Access-Control-Allow-Origin","*");

        response.setHeader("Access-Control-Allow-Credentials","true");
        System.out.println("OriginController ==> getData02...");
        session.setAttribute("data","Hello Origin");
        return "index";
    }

    @RequestMapping("/getData03")
    public String getData03(HttpSession session,HttpServletResponse response){
        response.setHeader("'Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials","true");
        System.out.println("OriginController ==> getData03...");
        Object data = session.getAttribute("data");
        System.out.println("data==>" + data);
        return "index";
    }
}
