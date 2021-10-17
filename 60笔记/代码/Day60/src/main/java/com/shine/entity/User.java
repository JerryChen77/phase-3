package com.shine.entity;

import java.util.Date;

public class User {
    private Integer id;
    private String username;
    private String password;
    private Date registerTime;

    public User() {
//        System.out.println("User...Cons...");
    }

    public void setId(Integer id) {
//        System.out.println("User...set...");
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public void init(){
//        System.out.println("User...init...");
    }

    public void destroy(){
//        System.out.println("User...destroy...");
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
