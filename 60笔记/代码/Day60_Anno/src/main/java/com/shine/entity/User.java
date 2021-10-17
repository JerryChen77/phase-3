package com.shine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private Date registerTime;

    @PostConstruct
    public void init(){
//        System.out.println("User...init...");
    }

    @PreDestroy
    public void destroy(){
//        System.out.println("User...destroy...");
    }

}
