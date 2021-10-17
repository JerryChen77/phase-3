package com.shine.controller;

import com.shine.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/rest")
@Controller
@CrossOrigin
public class RestController {

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/users")
    @ResponseBody
    public List<User> queryUsers(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("'Access-Control-Allow-Origin","*");

        System.out.println("查询所有用户...");
        User user01 = new User();
        user01.setId(10011);
        user01.setUsername("宋江");

        User user02 = new User();
        user02.setId(10012);
        user02.setUsername("晁盖");

        List<User> users = Arrays.asList(user01, user02);
        return users;
    }

    /**
     * 查询所有用户
     * @return
     */
    @PutMapping("/users")
    @ResponseBody
    public User updateUsers(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("'Access-Control-Allow-Origin","*");

        System.out.println("修改所有用户...");
        User user01 = new User();
        user01.setId(10011);
        user01.setUsername("宋江");
        return user01;


    }/**
     * 增加用户
     * @return
     */
    @PostMapping("/users")
    @ResponseBody
    public User insertUsers(User user){
        System.out.println("添加用户...");
        User user01 = new User();
        user01.setId(10011);
        user01.setUsername("宋江");
        return user01;
    }

    /**
     * 查询一个用户
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    @ResponseBody
    public User queryOneUsers(@PathVariable Integer id){
        System.out.println("查询单个用户...");
        User user01 = new User();
        user01.setId(10011);
        user01.setUsername("宋江");
        return user01;
    }

    /**
     * 查询一个用户
     * @param id
     * @return
     */
    @DeleteMapping("/users/{id}")
    @ResponseBody
    public User deleteOneUsers(@PathVariable Integer id){
        System.out.println("删除单个用户...");
        User user01 = new User();
        user01.setId(10011);
        user01.setUsername("宋江");
        return user01;
    }

}
