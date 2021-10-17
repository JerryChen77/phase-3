package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/hi")
    public String hi(){
        System.out.println("Hello===>hi");
        return "index";
    }

    @RequestMapping("/hi02")
    public String hi02(Integer id,String name){
        System.out.println("Hello===>hi02");
        System.out.println("id===>" + id);
        System.out.println("name===>" + name);
        return "forward:/index.jsp";
    }

    @RequestMapping("/hi03")
    public String hi03(Integer id,String name){
        System.out.println("Hello===>hi02");
        System.out.println("id===>" + id);
        System.out.println("name===>" + name);
        return "redirect:/index.jsp";
    }

    @RequestMapping("/hi04")
    public String hi04(Integer id,String name){
        System.out.println("Hello===>hi02");
        System.out.println("id===>" + id);
        System.out.println("name===>" + name);
//        return "forward:hi03.do";
        return "forward:hi03";
    }

}
