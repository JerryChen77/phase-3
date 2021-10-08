package com.mybatis.mybatisplus.controller;

import com.mybatis.mybatisplus.pojo.User;
import com.mybatis.mybatisplus.service.UserService;
import com.mybatis.mybatisplus.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


/**
 * @author Cjl
 * @date 2021/8/2 20:18
 */

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController  {
    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResultVO register(User user, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        ResultVO resultVO = new ResultVO();
        userService.save(user);
        resultVO.setSuccess(true);
        resultVO.setMessage("注册成功");
        return resultVO;
    }

    @PostMapping("/login")
    public ResultVO login(User user ,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");

        try {
            User loginUser = null;
            //
            if("tom".equals(user.getUserName()) && "123".equals(user.getPassword())) {
                loginUser = user;
                return ResultVO.ok("登录成功", loginUser);
            }
            return ResultVO.error("用户名或密码错误");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.error("登录失败", "当前登录人数过多，请稍后再试！");
        }
    }

    @RequestMapping("/u")
    public ResultVO get(){
        ResultVO resultVO = new ResultVO();
        resultVO.setMessage("123");
        return resultVO;
    }



}
