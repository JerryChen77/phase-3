package com.shine.mapper;

import com.shine.entity.User;

import java.util.List;

/**
 * 持久层
 */
public interface UserMapper {
    Integer insertUser(User user);

    Integer deleteUser(Integer id);

    Integer updateUser(User user);

    User selectUser(Integer id);

    List<User> selectAllUser();
}
