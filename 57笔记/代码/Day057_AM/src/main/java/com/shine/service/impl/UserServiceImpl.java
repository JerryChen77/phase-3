package com.shine.service.impl;

import com.shine.dao.UserDao;
import com.shine.dao.impl.UserDaoImpl;
import com.shine.entity.User;
import com.shine.factory.StaticBeanFactory;
import com.shine.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = (UserDao) StaticBeanFactory.getBean("userDao");

    @Override
    public Integer saveUser(User user) {
        System.out.println("UserServiceImpl...saveUser...");
        userDao.insertUser(user);
        return null;
    }
}
