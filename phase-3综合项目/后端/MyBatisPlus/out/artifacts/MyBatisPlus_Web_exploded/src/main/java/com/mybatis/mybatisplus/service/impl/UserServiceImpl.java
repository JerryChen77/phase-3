package com.mybatis.mybatisplus.service.impl;

import com.mybatis.mybatisplus.mapper.IUserMapper;
import com.mybatis.mybatisplus.pojo.User;
import com.mybatis.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Cjl
 * @date 2021/8/2 17:35
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    IUserMapper userMapper;

    @Override
    public Integer save(User user) {
        user.setStatus(0);
        user.setRegisterTime(new Date());
        user.setUpdateTime(new Date());
        int insert = userMapper.insert(user);
        return insert;
    }
}
