package com.shine.dao;

import com.shine.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 持久层接口
 */
public interface UserDao {
    /**
     * select * from user where id=?
     * @param id
     * @return
     */
    User findById(int id);

    int insertUser(User user);

    /**
     * 通过用户名和密码查询
     * @param username
     * @param password
     * @return
     */
    User findUserByNameAndPwd1(String username,String password);

    /**
     * 通过用户名和密码查询
     * @param username
     * @param password
     * @return
     */
    User findUserByNameAndPwd2(String username,String password);

    /**
     * 通过用户名和密码查询
     * @param username
     * @param password
     * @return
     */
    User findUserByNameAndPwd3(@Param("username") String username,@Param("password") String password);

    /**
     * 通过用户名和密码查询
     * @param username
     * @param password
     * @return
     */
    User findUserByNameAndPwd4(Map map);
}
