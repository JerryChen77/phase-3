package com.shine.dao;

import com.shine.entity.User;

import java.util.List;

/**
 * 持久层
 */
public interface UserMapper {

    User findUserById(int id);
    User findUserById2(int id);
    User findUserById3(int id);
    User findUserById4(int id);

    List<User> findAllUser();
}
