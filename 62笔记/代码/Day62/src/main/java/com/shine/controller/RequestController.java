package com.shine.controller;

import com.shine.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/req")
public class RequestController {

    /**
     * 传递数据--request--转发
     *  数据在转发过程中可以共享
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("/getData01")
    public String getData01(HttpServletRequest request, User user){
        System.out.println("RequestController --- getData01---> show01.jsp");
        request.setAttribute("user",user);
        return "forward:/show01.jsp";
    }

    /**
     * 传递数据---request---重定向
     *  数据在重定向过程中不可以共享
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("/getData02")
    public String getData02(HttpServletRequest request, User user){
        System.out.println("RequestController --- getData02---> show01.jsp");
        request.setAttribute("user",user);
        return "redirect:/show01.jsp";
    }

}
