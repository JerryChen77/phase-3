package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/jump003")
@Controller
public class JumpController03 {

    @RequestMapping("/hello")
    public String Hello(){
        System.out.println("JUMP003  ==  HELLO ==》INDEX");
        return "index";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump01")
    public String jump01(){
        System.out.println("JUMP003 ===  转发--删除数据 --->hello ");
        return "forward:hello";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump02")
    public String jump02(){
        System.out.println("JUMP003 ===  重定向--删除数据 --->hello ");
        return "redirect:hello";
    }
}
