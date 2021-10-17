package com.shine.controller;

import com.shine.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/mav")
@Controller
@SessionAttributes({"city"})
public class MavController {

    @RequestMapping("/getData01")
    public ModelAndView getData01(User user,String city){
        ModelAndView modelAndView = new ModelAndView();

        // 设置数据
        modelAndView.addObject("user",user);
        modelAndView.addObject("city",city);

        // 设置视图
        modelAndView.setViewName("forward:/showmav.jsp");
        return modelAndView;
    }

    @RequestMapping("/getData02")
    public ModelAndView getData02(User user,String city){
        ModelAndView modelAndView = new ModelAndView();

        // 设置数据
        modelAndView.addObject("user",user);
        modelAndView.addObject("city",city);

        // 设置视图
        modelAndView.setViewName("redirect:/showmav.jsp");
        return modelAndView;
    }
}
