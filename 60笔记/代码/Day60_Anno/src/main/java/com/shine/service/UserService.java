package com.shine.service;

import com.shine.entity.User;

import java.util.List;

/**
 * 业务层接口
 */
public interface UserService {
    Integer addUser(User user);
    Integer removeUser(Integer id);
    Integer modifyUser(User user);
    User queryUserById(Integer id);
    List<User> queryUsers();
}
