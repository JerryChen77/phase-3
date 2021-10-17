package com.shine.mapper;

import com.shine.entity.User;

import java.util.List;

/**
 * 数据访问层
 */
public interface UserMapper {
    User findUserById(Integer id);

    List<User> findUserByCondition(User user);
}
