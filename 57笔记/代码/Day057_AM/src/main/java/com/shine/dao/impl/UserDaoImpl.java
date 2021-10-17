package com.shine.dao.impl;

import com.shine.dao.UserDao;
import com.shine.entity.User;

public class UserDaoImpl implements UserDao {
    @Override
    public Integer insertUser(User user) {
        System.out.println("UserDaoImpl...插入User数据成功");
        return null;
    }
}
