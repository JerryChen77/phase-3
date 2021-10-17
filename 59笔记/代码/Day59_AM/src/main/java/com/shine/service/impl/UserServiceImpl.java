package com.shine.service.impl;

import com.shine.entity.User;
import com.shine.service.UserService;

/**
 * 业务层实现
 */
public class UserServiceImpl implements UserService {
    @Override
    public Integer saveUser(User user) {
        System.out.println("添加用户成功===========");
        return 11111;
    }

    @Override
    public Integer deleteUser(Integer id) {
        System.out.println("删除用户成功-----------");
        return 22222;
    }

    @Override
    public void updateUser(User user) {
        System.out.println("修改用户成功·········");
    }

    @Override
    public Integer selectUser(Integer id) {
        System.out.println("查询用户成功+++++++++++++++++");
        return 33333;
    }
}
