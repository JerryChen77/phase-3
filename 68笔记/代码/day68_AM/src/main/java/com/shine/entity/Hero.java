package com.shine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Hero {
    private Integer id;
    private String username;
    private String gender;
    private Integer age;
    private String addr;
    private String info;
}
