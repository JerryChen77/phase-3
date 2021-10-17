package com.shine.dao;

import com.shine.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据访问层
 */
@Repository
public interface UserDao {
    Integer insertUser(User user);
    Integer deleteUser(Integer id);
    Integer updateUser(User user);
    User selectUser(Integer id);
    List<User> selectUsers();
}
