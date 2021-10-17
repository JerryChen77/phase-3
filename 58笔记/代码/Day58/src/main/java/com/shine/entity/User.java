package com.shine.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

@Data
@Component("user")
public class User {

    @Value("10011")
    private Integer id;

    @Value("宋江")
    private String username;

    @Value("sunerniang")
    private String password;

    @Value("1988/12/28")
    private Date registerTime;

    public void show(){
        System.out.println("User...show...");
    }

    @PostConstruct
    public void init(){
        System.out.println("User...init");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("User...destroy");
    }
}
