package com.shine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/checkCaptcha")
@Controller
public class CaptchaController {

    /**
     * 验证验证码
     * @param captcha
     * @param session
     * @return
     */
    @RequestMapping("/check01")
    public String check01(String captcha, HttpSession session){
        // 获取存储session中的验证码
        String capCode = (String) session.getAttribute("captcha");
        System.out.println("hahaha");
        // 判断
        if (captcha.equalsIgnoreCase(capCode)){
            return "index";
        }
        return "error01";
    }
}
