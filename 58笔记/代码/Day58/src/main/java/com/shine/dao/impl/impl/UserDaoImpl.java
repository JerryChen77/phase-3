package com.shine.dao.impl.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.shine.dao.UserDao;
import com.shine.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

//@Component
@Repository
@Scope("singleton")
public class UserDaoImpl implements UserDao {


    @Override
    public Integer insertUser(User user) {
        System.out.println("UserDaoImpl...insertUser...");
        return null;
    }
}
