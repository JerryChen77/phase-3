package com.mybatis.mybatisplus.controller;
import com.mybatis.mybatisplus.pojo.User;
import com.mybatis.mybatisplus.service.UserService;
import com.mybatis.mybatisplus.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PostMapping("/login")
    public ResultVO login(@RequestBody User user){
        ResultVO resultVO = userService.login(user.getUserName(), user.getPassword());
        return resultVO;
    }

    //查询所有用户
    @RequestMapping("/users")
    public ResultVO selectAll(){
        ResultVO resultVO = userService.selectAll();
        return resultVO;
    }


    //注册功能，未使用
    @PostMapping("/users")
    public ResultVO register(@RequestBody User user, HttpServletResponse response){
        ResultVO resultVO = new ResultVO();
        userService.save(user);
        resultVO.setSuccess(true);
        resultVO.setMessage("注册成功");
        return resultVO;
    }


}
