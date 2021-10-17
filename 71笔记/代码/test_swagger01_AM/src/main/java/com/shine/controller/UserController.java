package com.shine.controller;

import com.shine.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"用户控制器"},value = "用户模块的操作都在这里,提供了增删改查的handler")
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation(tags = {"查询用户"},value = "查询用户的handler,需要id参数")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "用户id",value = "用户的id",example = "10010")
    )
    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable Integer id){
        User user = new User();
        user.setId(id);
        return user.toString();
    }

    @ApiOperation(tags = {"查询用户"},value = "查询所有用户的handler")
    @GetMapping("/users")
    public String getUsers(){
        User user = new User();
        return user.toString();
    }

    @ApiOperation(tags = {"删除用户"},value = "删除用户的handler,需要id参数")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "用户id",value = "用户的id",example = "10010")
    )
    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable Integer id){
        User user = new User();
        user.setId(id);
        return "删除id为:" + id;
    }

    @ApiOperation(tags = {"修改用户"},value = "修改用户的handler,需要User对象参数")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "用户对象",value = "用户的对象")
    )
    @PutMapping("/users")
    public String modifyUserById(@RequestBody User user){
        return "修改:user";
    }

    @ApiOperation(tags = {"添加用户"},value = "添加用户的handler,需要user对象参数")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "用户对象",value = "用户的对象")
    )
    @PostMapping("/users")
    public String addUserById(@RequestBody User user){
        return "添加:user";
    }

}
