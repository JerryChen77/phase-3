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

    User selectUser2(Integer id);

    User selectUser3(Integer id);

    List<User> selectAllUser();
}
