package com.shine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shine.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/json")
public class JsonController {
    /**
     * {key:value,key:value}
     * [
     *  {},
     *  {},
     *  {}
     * ]
     *
     * Fastjson     阿里巴巴
     * Jackson      springMVC默认使用
     */

    @RequestMapping("/getJson01")
    @ResponseBody
    public User getJson01() {
        User user = new User(10011,"李逵","xiaokuikui",new Date());
        return user;
    }


    @RequestMapping(value = "/getJson02",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getJson02(){
        User user = new User(10011,"李逵","xiaokuikui",new Date());
        return "静夜思";   // /静夜思.jsp
    }

    @RequestMapping(value = "/getJson03",produces = "text/html;charset=utf-8")
    public String getJson03(){
        User user = new User(10011,"李逵","xiaokuikui",new Date());
        return "静夜思";   // /静夜思.jsp
    }

    @RequestMapping(value = "/getJson04")
    @ResponseBody
    public User getJson04(@RequestBody User user){
        user.setPassword("laokui");
        return user;   // /静夜思.jsp
    }

}
