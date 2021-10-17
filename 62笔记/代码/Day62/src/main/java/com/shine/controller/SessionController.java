package com.shine.controller;

import com.shine.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/session")
public class SessionController {

    /**
     * 传递数据---session--转发
     *  session在转发过程中数据可以共享
     *  session的生命周期是一次会话
     * @param session
     * @param user
     * @return
     */
    @RequestMapping("/getData01")
    public String getData01(HttpSession session, User user){
        System.out.println("SessionController === getData01 ---> show02.jsp");
        session.setAttribute("user",user);
        return "forward:/show02.jsp";
    }

    /**
     * 传递数据---session--重定向
     *  session在重定向过程中数据可以共享
     *  session的生命周期是一次会话
     * @param session
     * @param user
     * @return
     */
    @RequestMapping("/getData02")
    public String getData02(HttpSession session, User user){
        System.out.println("SessionController === getData02 ---> show02.jsp");
        session.setAttribute("user",user);
        return "redirect:/show02.jsp";
    }
}
