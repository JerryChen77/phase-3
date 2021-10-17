package com.shine.service.impl;

import com.shine.dao.UserDao;
import com.shine.entity.User;
import com.shine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//@Service("userService")
@Service
public class UserServiceImpl implements UserService {


    // @Resource(name = "userDao") 查找容器中对象，按照默认规则查找，可以指定名称
    @Autowired
    @Qualifier("userDaoImpl2")
    private UserDao userDao;

    @Override
    public Integer saveUser(User user) {
        System.out.println("UserServiceImpl...saveUser...");
        userDao.insertUser(user);
        return null;
    }
}
