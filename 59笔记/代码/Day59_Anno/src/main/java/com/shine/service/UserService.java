package com.shine.service;

import com.shine.entity.User;

public interface UserService {
    /**
     * 增
     *  add
     *  insert
     *  save
     *
     * 删
     * delete
     * remove
     *
     * 改
     *  update
     *  modify
     *
     * 查
     * select
     * find
     */

    Integer saveUser(User user);

    Integer deleteUser(Integer id);

    void updateUser(User user);

    Integer selectUser(Integer id);

}


