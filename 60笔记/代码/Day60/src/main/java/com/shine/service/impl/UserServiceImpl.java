package com.shine.service.impl;

import com.shine.dao.UserDao;
import com.shine.entity.User;
import com.shine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * *User
     * @param user
     * @return
     */
    @Override
    public Integer addUser(User user) {
        Integer ret = userDao.insertUser(user);
        return ret;
    }

    @Override
    public Integer removeUser(Integer id) {
        Integer ret = userDao.deleteUser(id);
//        System.out.println(10/0);
        return ret;
    }

    @Override
    public Integer modifyUser(User user) throws SQLException {
        Integer ret = userDao.updateUser(user);
        try {
            throw new SQLException("sql语句异常...");
        } catch (SQLException e) {
            e.printStackTrace();
            // throw new RuntimeException("检查发现编译时异常，送你一个大的");
        }
        return ret;
    }

    /**
     * query*
     * @param id
     * @return
     */
    @Override
    public User queryUserById(Integer id) {
        User user = userDao.selectUser(id);
        return user;
    }

    @Override
    public List<User> queryUsers() {
        List<User> users = userDao.selectUsers();
        return users;
    }
}
