package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/jump002")
@Controller
public class JumpController02 {

    @RequestMapping("/hello")
    public String Hello(){
        System.out.println("JUMP  ==  HELLO ==》INDEX");
        return "index";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump01")
    public String jump01(){
        System.out.println("JUMP ===  jump01--->hello ");
        return "redirect:hello";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump02")
    public String jump02(){
        System.out.println("JUMP ===  jump02--->hello ");
        return "redirect:/jump002/hello";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump04")
    public String jump04(){
        System.out.println("JUMP ===  jump04--->hello ");
        return "redirect:/index.jsp";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/jump05")
    public String jump05(){
        System.out.println("JUMP ===  jump05--->hello ");
        return "redirect:/hello/sayHi02";
    }
}
