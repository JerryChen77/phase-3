package com.shine.entity;

import java.util.Date;

/**
 * User类
 *  对应java2103数据库中的user表
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private Date registerTime;

    public User() {
    }

    public User(Integer id, String username, String password, Date registerTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.registerTime = registerTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", registerTime=" + registerTime +
                '}';
    }
}
