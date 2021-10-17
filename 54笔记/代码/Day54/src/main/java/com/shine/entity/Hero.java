package com.shine.entity;

/**
 * hero实体类
 */
public class Hero {
    private int id;
    private String username;
    private String gender;
    private int age;
    private String addr;
    private String info;

    public Hero() {
    }

    public Hero(int id, String username, String gender, int age, String addr, String info) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.age = age;
        this.addr = addr;
        this.info = info;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
