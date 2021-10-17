package com.shine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User2 {
    private Integer id;
    private String username;
    private String password;
    private Date registerTime;

    private String[] hobby;

}
