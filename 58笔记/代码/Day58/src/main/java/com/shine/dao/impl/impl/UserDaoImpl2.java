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
public class UserDaoImpl2 implements UserDao {
    @Value("jdbc:mysql://localhost:3306/java2103?useSSL=false&serverTimezone=GMT&characterEncoding=UTF8&useUnicode=true")
    private String url;

    @Value("com.mysql.jdbc.Driver")
    private String driverClassName;

    @Value("root")
    private String username;

    @Value("root")
    private String password;

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Override
    public Integer insertUser(User user) {
        System.out.println("UserDaoImpl22222...insertUser...");
        return null;
    }
}
