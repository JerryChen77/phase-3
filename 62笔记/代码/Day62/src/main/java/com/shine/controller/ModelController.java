package com.shine.controller;

import com.shine.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.UUID;

@Controller
@RequestMapping("/model")
@SessionAttributes({"city"})
public class ModelController {

    /**
     * Model数据模型，可以存取数据--转发
     *  默认会把数据也存入request域中
     *  可以使用注解：@SessionAttributes({"city"})把对应名称的数据存入session中
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/getData01")
    public String getData01(Model model, User user){
        System.out.println("ModelController === getData01 ===> show03.jsp");
        Model user1 = model.addAttribute("user", user);
        System.out.println(user1);

        return "forward:/show03.jsp";
    }

    /**
     * 重定向
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/getData02")
    public String getData02(Model model, User user){
        System.out.println("ModelController === getData02 ===> show03.jsp");
        Model user1 = model.addAttribute("user", user);
        System.out.println(user1);
        return "redirect:/show03.jsp";
    }

    /**
     * 重定向
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/getData03")
    public String getData03(Model model,User user,String city,String area){
        System.out.println("ModelController === getData03 ===> show04.jsp");

        model.addAttribute("user",user);
        model.addAttribute("city",city);
        model.addAttribute("area",area);

        return "forward:/show04.jsp";
    }

    /**
     * 重定向
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/getData04")
    public String getData04(Model model,User user,String city,String area){
        System.out.println("ModelController === getData04 ===> show04.jsp");

        model.addAttribute("user",user);
        model.addAttribute("city",city);
        model.addAttribute("area",area);

        return "redirect:/show04.jsp";
    }

    /**
     * 重定向
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/getData05")
    public String getData05(){
        System.out.println("ModelController === getData05 ===> show04.jsp");
        return "redirect:/show04.jsp";
    }

    /**
     * 重定向
     * @return
     */
    @RequestMapping("/getData06")
    public String getData06(SessionStatus status){
        System.out.println("ModelController === getData06 ===> show04.jsp");
        // 清空session中SessionAttributes注解声明的数据
        status.setComplete();
        return "redirect:/show04.jsp";
    }

}
