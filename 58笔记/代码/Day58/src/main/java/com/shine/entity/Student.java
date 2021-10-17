package com.shine.entity;

import java.util.*;

/**
 * 实体类
 */
public class Student {
    // Array、Set、List、Map、Properties等还无法直接实现
    private String[] names;
    private Set hobby;
    private List subjects;
    private Map phones;
    private Properties friends;

    public Student() {
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public void setHobby(Set hobby) {
        this.hobby = hobby;
    }

    public void setSubjects(List subjects) {
        this.subjects = subjects;
    }

    public void setPhones(Map phones) {
        this.phones = phones;
    }

    public void setFriends(Properties friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Student{" +
                "names=" + Arrays.toString(names) +
                ", hobby=" + hobby +
                ", subjects=" + subjects +
                ", phones=" + phones +
                ", friends=" + friends +
                '}';
    }
}
