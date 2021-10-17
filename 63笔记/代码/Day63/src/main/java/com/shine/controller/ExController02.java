package com.shine.controller;

import com.shine.exception.MyException01;
import com.shine.exception.MyException02;
import com.shine.exception.MyException03;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/ex02")
@Controller
public class ExController02 {

    @RequestMapping("/e01")
    public String e01(Integer id){
        if (id % 5 == 0){
            throw new MyException01("id不能是5的倍数");
        }
        return "Success";
    }
}
