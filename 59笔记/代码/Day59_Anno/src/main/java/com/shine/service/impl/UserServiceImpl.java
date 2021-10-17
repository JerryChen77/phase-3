package com.shine.service.impl;

import com.shine.entity.User;
import com.shine.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 业务层实现
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public Integer saveUser(User user) {
        System.out.println("添加用户成功===========");
        return 11111;
    }

    @Override
    public Integer deleteUser(Integer id) {

        if (id < 0){
            throw new RuntimeException("用户id不存在,by id=" + id);
        }
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
